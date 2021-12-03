package ru.sfedu.Kursovaya;

import ru.sfedu.Kursovaya.utils.OtherUtils.ConfigurationUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;
import ru.sfedu.Kursovaya.utils.OtherUtils.Constants;

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