package me.minidigger.minilauncher.util;

import me.minidigger.minilauncher.Constants;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

/**
 * Created by mbenndorf on 25.04.2017.
 */
public class FileUtils {

    private static final Logger logger = Logger.getLogger(FileUtils.class.getName());

    public static String httpGet(String url, String lineSeparator) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", Constants.launcherName + " v" + Constants.launcherVersion);
        String response = connection.getResponseCode() + " " + connection.getResponseMessage();
        if (!response.startsWith("200")) {
            logger.severe("Invalid response : " + response + "(" + url + ")");
            return null;
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        StringBuilder builder = new StringBuilder();
        while ((line = in.readLine()) != null) {
            builder.append(line);
            if (lineSeparator != null) {
                builder.append(lineSeparator);
            }
        }
        in.close();
        return builder.toString();
    }


    public static String getFileContent(File file, String lineSeparator) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder builder = new StringBuilder();
        try {
            String line = reader.readLine();
            while (line != null) {
                builder.append(line);
                if (lineSeparator != null) {
                    builder.append(lineSeparator);
                }
                line = reader.readLine();
            }
        } finally {
            reader.close();
        }
        return builder.toString();
    }
}
