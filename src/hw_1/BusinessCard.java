package hw_1;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class BusinessCard {
    static void writeText(FileOutputStream file, String text) {
        try {
            byte[] nameBytes = text.getBytes();
            file.write(nameBytes);
            file.write("\n".getBytes());
        } catch (IOException e) {
            e.toString();
        }
    }

    static void createCard(InputStream personInfoFile, String path) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(personInfoFile));
             FileOutputStream outputFile = new FileOutputStream(path)) {

            String name = reader.readLine();
            String email = reader.readLine();
            String studentInfo = reader.readLine();
            String interests = reader.readLine();

            writeText(outputFile, "BEGIN:VCARD\nVERSION:4.0");
            writeText(outputFile, "FN:" + name);
            writeText(outputFile, "EMAIL;TYPE=work:" + email);
            writeText(outputFile, "ORG:" + studentInfo);
            writeText(outputFile, "NOTE: I am interested in " + interests);
            writeText(outputFile, "END:VCARD");

        } catch (IOException e) {
            e.toString();
        }
    }

    public static void main(String[] args) {
        String myInfo = "Veneta\nfn82184@g.fmi.uni-sofia.bg\nFMI\nreading, coding\n";
        InputStream myInfoStream = new ByteArrayInputStream(myInfo.getBytes(StandardCharsets.UTF_8));
        createCard(myInfoStream, "src/hw_1/business_card.vcf");
    }
}
