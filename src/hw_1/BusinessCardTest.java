package hw_1;

import org.junit.jupiter.api.Test;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BusinessCardTest {

    @Test
    void writeText() throws IOException {
        String testText = "test123";

        Path file = Files.createTempFile("temp", ".txt");

        FileOutputStream fileOStream = new FileOutputStream(file.toFile());
        BusinessCard.writeText(fileOStream, testText);
        fileOStream.close();

        String fileText = Files.readString(file);
        assertEquals(testText + "\n", fileText);

        Files.delete(file);
    }

    @Test
    void createCard() throws IOException {
        String myTestInfo = "Ivan Petrov\nivan@example.com\nUASG\nswimming\n";
        String expected =
                "BEGIN:VCARD\n" +
                "VERSION:4.0\n" +
                "FN:Ivan Petrov\n" +
                "EMAIL;TYPE=work:ivan@example.com\n" +
                "ORG:UASG\n" +
                "NOTE: I am interested in swimming\n" +
                "END:VCARD\n";

        InputStream inputStream = new ByteArrayInputStream(myTestInfo.getBytes());

        Path tempFile = Files.createTempFile("temp", ".txt");

        BusinessCard.createCard(inputStream, tempFile.toString());

        String fileText = Files.readString(tempFile);
        assertEquals(expected, fileText);

        Files.delete(tempFile);
    }
}
