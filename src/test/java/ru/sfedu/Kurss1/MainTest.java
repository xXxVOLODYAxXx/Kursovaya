package ru.sfedu.Kurss1;

import ru.sfedu.Kurss1.utils.ConfigurationUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;
import ru.sfedu.Kurss1.utils.Constants;

class MainTest {
    @Test
    public void configTest(){
        final Logger log = LogManager.getLogger(Main.class);
        String conf=null;
        String ae=Constants.KEY;
        {
            try {
                conf = ConfigurationUtil.getConfigurationEntry(Constants.KEY);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
            assertEquals("value",conf);
    }

}