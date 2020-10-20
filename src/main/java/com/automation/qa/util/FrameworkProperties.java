//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.automation.qa.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;

public class FrameworkProperties {
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(FrameworkProperties.class));
    private Properties properties;

    public FrameworkProperties() {
    }

    public Properties readProjEnvConfig(String projectSettingsFile) throws Exception {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(projectSettingsFile));
            this.properties = new Properties();

            try {
                this.properties.load(reader);
                reader.close();
            } catch (IOException var4) {
                var4.printStackTrace();
            }
        } catch (FileNotFoundException var5) {
            LOGGER.error("Configuration.properties not found at " + projectSettingsFile, var5);
        }

        return this.properties;
    }
}
