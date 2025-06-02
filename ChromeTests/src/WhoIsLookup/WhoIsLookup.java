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
                result.append(line).append("\n");
            }

        } catch (IOException e) {
            return "Error: " + e.getMessage();
        }

        return result.toString();
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
    }
}

