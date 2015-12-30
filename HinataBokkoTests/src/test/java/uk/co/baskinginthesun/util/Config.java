package uk.co.baskinginthesun.util;

import com.typesafe.config.ConfigFactory;

public class Config {

    static {
        final com.typesafe.config.Config config = ConfigFactory.load();
        hostAndPort = config.getString("hostAndPort");
    }

    private static String hostAndPort;

    public static String urlWithPath(final String path) {
        if (path.length() > 0) {
            return String.format("http://%s/%s/", Config.hostAndPort, path);
        } else {
            return String.format("http://%s/%s", Config.hostAndPort, path);
        }
    }

}
