package e_log;

import e_store.ProductImporter;

public class LoggingService {
    private final Logger logger;

    public LoggingService(String logFile) {
        this.logger = new Logger(logFile, ProductImporter.isEnabled);
    }

    public void logAction(String user, String action) {
        logger.log(user + " - " + action);
    }
}

