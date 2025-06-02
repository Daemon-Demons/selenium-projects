package WhoIsLookup;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class WhoIsLookup {

    public static String getWhois(String domainName) {
        StringBuilder result = new StringBuilder();

        try (Socket socket = new Socket("whois.verisign-grs.com", 43)) {
            OutputStream out = socket.getOutputStream();
            out.write((domainName + "\r\n").getBytes());
            out.flush();

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line;

            while ((line = in.readLine()) != null) {
                result.append(line.replaceAll(",", " ")).append("\n");  // Avoid breaking CSV format
            }

        } catch (IOException e) {
            return "Error: " + e.getMessage();
        }

        return result.toString();
    }

    public static void exportToCSV(String domain, String whoisData) {
        String filename = "whois_master.csv";
        boolean fileExists = new File(filename).exists();

        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("Domain,WHOIS Info");
            for (String line : whoisData.split("\n")) {
                writer.printf("\"%s\",\"%s\"%n", domain, line);
            }
            System.out.println("\nWHOIS data exported to: " + filename);
        } catch (IOException e) {
            System.out.println("Error writing to CSV: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a domain name (e.g., example.com): ");
        String domain = scanner.nextLine().trim();

        if (domain.isEmpty()) {
            System.out.println("No domain entered. Exiting.");
            return;
        }

        System.out.println("\nLooking up WHOIS information for: " + domain);
        String whoisInfo = getWhois(domain);
        System.out.println("\nWHOIS Data:\n");
        System.out.println(whoisInfo);

        exportToCSV(domain, whoisInfo);
    }
}
