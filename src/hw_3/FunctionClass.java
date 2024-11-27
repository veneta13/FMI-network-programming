package hw_3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class FunctionClass {
    public static void main(String[] args) throws IOException {

        String mainDirName = "src/hw_3/";
        String zipFileName = mainDirName + "hw3Test.zip";

        File unzipDir = new File(mainDirName + "unzipped");

        try {
            unzip(zipFileName, unzipDir);
        }
        catch (Exception e) {
            System.out.println("Error unzipping:");
            System.out.println(e.getMessage());
            return;
        }

        try {
            zip(mainDirName, unzipDir);
        }
        catch (Exception e) {
            System.out.println("Error zipping:");
            System.out.println(e.getMessage());
        }
    }

    static void unzip(String zipFileName, File unzipDir) throws IOException {
        ZipInputStream zipIStream = new ZipInputStream(new FileInputStream(zipFileName));
        ZipEntry currentEntry = zipIStream.getNextEntry();

        File combinedFile = new File(unzipDir + File.separator + "combined.txt");
        new File(combinedFile.getParent()).mkdirs();
        FileOutputStream combinedOStream = new FileOutputStream(combinedFile, true);

        while (currentEntry != null) {
            String currentName = currentEntry.getName();

            File newFile = new File(unzipDir + File.separator + currentName);
            new File(newFile.getParent()).mkdirs();
            FileOutputStream fileOStream = new FileOutputStream(newFile);

            byte[] buffer = new byte[1024];
            int fileLength;
            while ((fileLength = zipIStream.read(buffer)) > 0) {
                fileOStream.write(buffer, 0, fileLength);
                combinedOStream.write(buffer, 0, fileLength);
            }

            fileOStream.close();
            zipIStream.closeEntry();

            currentEntry = zipIStream.getNextEntry();
        }

        zipIStream.closeEntry();
        zipIStream.close();
    }

    static void zip(String mainDirName, File unzipDir) throws IOException {
        File srcFile = new File(unzipDir.getAbsolutePath());
        File[] filesToZip = srcFile.listFiles();

        if (filesToZip == null) {
            throw new IOException("Failed to list files in " + unzipDir.getAbsolutePath());
        }

        File newZipFile = new File(mainDirName + File.separator + "zippedAgain.zip");
        new File(newZipFile.getParent()).mkdirs();

        FileOutputStream fileOStream = new FileOutputStream(newZipFile);
        ZipOutputStream zipOStream = new ZipOutputStream(fileOStream);

        for (File file : filesToZip) {
            byte[] buffer = new byte[1024];
            FileInputStream fileIStream = new FileInputStream(file);
            zipOStream.putNextEntry(new ZipEntry(file.getName()));

            int fileLength;
            while ((fileLength = fileIStream.read(buffer)) > 0) {
                zipOStream.write(buffer, 0, fileLength);
            }

            zipOStream.closeEntry();
            fileIStream.close();
        }
        zipOStream.close();
    }
}
