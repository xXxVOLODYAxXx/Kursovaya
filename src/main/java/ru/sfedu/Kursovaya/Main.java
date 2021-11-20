package ru.sfedu.Kursovaya;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.sfedu.Kursovaya.utils.ConfigurationUtil;
import ru.sfedu.Kursovaya.utils.Constants;
import java.io.IOException;


public class Main {
    private static final Logger log = LogManager.getLogger(Main.class);
    private static String conf = null;
    private static String ae=Constants.KEY;
    static {
        try {
            conf = ConfigurationUtil.getConfigurationEntry(Constants.KEY);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException{
        logBasicSystemInfo();
        log.info(conf);
        log.info(Constants.TWO_NUMBER_NINES);
        log.info(ae);
    }

    public Main() {
        log.debug("TestClient[0]: starting application.........");
    }

    public static void logBasicSystemInfo() {
        log.info("Launching the application...");
        log.info("Operating System: " + System.getProperty("os.name") + " " + System.getProperty("os.version"));
        log.info("JRE: " + System.getProperty("java.version"));
        log.info("Java Launched From: " + System.getProperty("java.home"));
        log.info("Class Path: " + System.getProperty("java.class.path"));
        log.info("Library Path: " + System.getProperty("java.library.path"));
        log.info("User Home Directory: " + System.getProperty("user.home"));
        log.info("User Working Directory: " + System.getProperty("user.dir"));
        log.info("Test INFO logging.");
    }
}