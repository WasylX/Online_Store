package e_log;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger implements AutoCloseable {
    private static String logFile;
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Logger(String logFile, boolean isEnabled) {
        this.logFile = logFile;
    }

    public static void log(String message) {
        String timestamp = LocalDateTime.now().format(dateFormatter);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true))) {
            writer.write(timestamp + " - " + message + System.lineSeparator());
        } catch (IOException e) {
            System.err.println("An error occurred while logging: " + e.getMessage());
        }
    }


    public void close() {
    }
}

