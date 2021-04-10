package sun.net.util;

import java.net.URL;

public class URLUtil {
    public static String urlNoFragString(URL url) {
        StringBuilder sb = new StringBuilder();
        String protocol = url.getProtocol();
        if (protocol != null) {
            sb.append(protocol.toLowerCase());
            sb.append("://");
        }
        String host = url.getHost();
        if (host != null) {
            sb.append(host.toLowerCase());
            int port = url.getPort();
            if (port == -1) {
                port = url.getDefaultPort();
            }
            if (port != -1) {
                sb.append(":");
                sb.append(port);
            }
        }
        String file = url.getFile();
        if (file != null) {
            sb.append(file);
        }
        return sb.toString();
    }
}
