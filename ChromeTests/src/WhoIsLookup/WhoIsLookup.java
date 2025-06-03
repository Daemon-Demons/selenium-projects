package WhoIsLookup;

import java.io.*;
import java.util.*;
import java.net.Socket;

public class WhoIsLookup {

    private static final String CSV_FILE = "reports/whois_lookup_master.csv";

    // Define the WHOIS fields to extract and export as columns
    private static final List<String> TARGET_FIELDS = Arrays.asList(
        "Domain Name",
        "Registry Domain ID",
        "Registrar WHOIS Server",
        "Registrar URL",
        "Updated Date",
        "Creation Date",
        "Registry Expiry Date",
        "Registrar",
        "Registrar Abuse Contact Email",
        "Registrar Abuse Contact Phone"
    );

    public static String whoisQuery(String server, String domain) {
        StringBuilder response = new StringBuilder();
        try (Socket socket = new Socket(server, 43)) {
            socket.getOutputStream().write((domain + "\r\n").getBytes());
            socket.getOutputStream().flush();
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line).append("\n");
            }
        } catch (IOException e) {
            System.out.println("Error querying " + server + ": " + e.getMessage());
        }
        return response.toString();
    }

    public static Map<String, String> parseWhois(String raw, String domain) {
        Map<String, String> values = new LinkedHashMap<>();
        values.put("Domain", domain);  // Always include the domain name

        // Initialize all fields with empty strings
        for (String field : TARGET_FIELDS) {
            values.put(field, "");
        }

        for (String line : raw.split("\n")) {
            line = line.trim();  // Trim spaces

            for (String field : TARGET_FIELDS) {
                String prefix = field + ":";
                if (line.toLowerCase().startsWith(prefix.toLowerCase())) {
                    String value = line.substring(prefix.length()).trim();
                    values.put(field, value);
                }
            }
        }

        return values;
    }


    public static void appendToCsv(Map<String, String> rowData) {
        boolean fileExists = new File(CSV_FILE).exists();
        try (PrintWriter writer = new PrintWriter(new FileWriter(CSV_FILE, true))) {
            if (!fileExists) {
                // Write header
                writer.println(String.join(",", rowData.keySet()));
            }

            // Write data
            for (String value : rowData.values()) {
                writer.print("\"" + value.replace("\"", "'") + "\",");
            }
            writer.println();
            System.out.println("Appended data to " + CSV_FILE);
        } catch (IOException e) {
            System.out.println("Error writing csv: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a domain name: ");
        String domain = sc.nextLine().trim();

        // Step 1: Primary WHOIS query
        String primary = whoisQuery("whois.verisign-grs.com", domain);

        // Step 2: Check if a Registrar WHOIS Server is specified
        String registrarWhois = null;
        for (String line : primary.split("\n")) {
            if (line.startsWith("Registrar WHOIS Server:")) {
                registrarWhois = line.split(":", 2)[1].trim();
                break;
            }
        }

        // Step 3: Perform final query to registrar (if available)
        String finalResponse = primary;
        if (registrarWhois != null && !registrarWhois.isEmpty()) {
            System.out.println("Found Registrar WHOIS Server: " + registrarWhois);
            finalResponse = whoisQuery(registrarWhois, domain);
        }

        // Step 4: Parse and export
        Map<String, String> data = parseWhois(finalResponse, domain);
        data.forEach((k, v) -> System.out.println(k + ": " + v));
        appendToCsv(data);
    }
}