package hw_4;

import java.net.*;
import java.io.*;

public class URLImageDownloader {

    public static void main(String[] args) throws IOException {
        String urlString = "https://www.uni-sofia.bg/var/ezwebin_site/storage/images/media/images/su_logo/821342-1-bul-BG/su_logo_imagelarge.jpg";
        String fileName = urlString.substring(urlString.lastIndexOf("/") + 1);

        InputStream urlStream = null;
        OutputStream fileStream = null;

        try {
            URL imageURL = new URL(urlString);
            urlStream = imageURL.openStream();
            fileStream = new FileOutputStream(fileName);

            byte[] buffer = new byte[4096];
            int bytesInBuffer;

            while ((bytesInBuffer = urlStream.read(buffer)) != -1) {
                fileStream.write(buffer, 0, bytesInBuffer);
            }
        }
        catch (RuntimeException e) {
            System.out.println(e.toString());
        }
        finally {
            if (urlStream != null) {
                urlStream.close();
            }
            if (fileStream != null) {
                fileStream.close();
            }
        }
    }
}
