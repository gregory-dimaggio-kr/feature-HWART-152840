package com.kroger.pharmacy.csr.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 *
 * Class used to get the property values from property files
 *
 */
public class ConfigLoader {

    private Properties properties = new Properties();

    private static Log logger = LogFactory.getLog(ConfigLoader.class);

    private static final Map<String, String> profileToURIMap = new HashMap<String, String>();
    private static final String LOCAL = "local";
    private static final String TEST = "test";
    private static final String STAGE = "stage";
    private static final String PROD = "prod";

    static {
        profileToURIMap.put("test", "app-test.properties");
        profileToURIMap.put("stage", "app-stage.properties");
        profileToURIMap.put("prod", "app-prod.properties");
        profileToURIMap.put(LOCAL, "app-local.properties");
    }

    public ConfigLoader() throws IOException {

        String profile = getProfile();
        logger.info("Profile : " + profile);
        String fileName = getFilePath(profile);
        InputStream input = getClass().getClassLoader().getResourceAsStream(fileName);
        if (input == null) {
            System.out.println("Sorry, unable to find app properties");
            return;
        }

        properties.load(input);

    }

    public String getClientId() {
        return properties.getProperty("pingone.client_id");
    }

    public String getClientSecret() {
        return properties.getProperty("pingone.client_secret");
    }

    public String getTokenEndpoint() {
        return properties.getProperty("pingone.token_endpoint");
    }

    public String getRedirectUri() {
        return properties.getProperty("pingone.callback_endpoint");
    }

    public String getAuthEndPoint() {
        return properties.getProperty("pingone.authorize_endpoint");
    }

    public String getLogoutPoint() {
        return properties.getProperty("pingone.logout_endpoint");
    }


    private static String getProfileFromServer() {
        try {
            // Set up the JNDI context
            Context initialContext = new InitialContext();

            return lookupProfile(initialContext);
        } catch (NamingException ex) {
            // Return default profile if both lookups fail
            return LOCAL;
        }
    }

    private static String getProfile() {
        String profile = getProfileFromServer().toLowerCase();
        if (profile.contains(TEST)) {
            return TEST;
        } else if (profile.contains("staging") || profile.contains(STAGE)) {
            return STAGE;
        } else if (profile.contains(PROD)) {
            return PROD;
        } else {
            return LOCAL;
        }
    }

    private static String lookupProfile(Context initialContext) throws NamingException {
        try {
            return (String) initialContext.lookup("spring.profiles.active");
        } catch (NamingException e) {
            return (String) initialContext.lookup("java:comp/env/spring.profiles.active");
        }
    }

    public static String getFilePath(String profile) {
        String uri = profileToURIMap.containsKey(profile) ? profileToURIMap.get(profile) : profileToURIMap.get(LOCAL);
        return uri;
    }
}
