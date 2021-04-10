package java.net;

import java.security.Permission;
import java.util.Date;

public abstract class HttpURLConnection extends URLConnection {
    private static boolean followRedirects = true;
    private static final String[] methods = {"GET", "POST", "HEAD", "OPTIONS", "PUT", "DELETE", "TRACE"};
    protected int chunkLength = -1;
    protected int fixedContentLength = -1;
    protected long fixedContentLengthLong = -1;
    protected boolean instanceFollowRedirects = followRedirects;
    protected String method = "GET";
    protected int responseCode = -1;
    protected String responseMessage = null;

    protected HttpURLConnection(URL url) {
        super(url);
    }

    public static boolean getFollowRedirects() {
        return followRedirects;
    }

    @Override // java.net.URLConnection
    public long getHeaderFieldDate(String str, long j) {
        String headerField = getHeaderField(str);
        try {
            if (headerField.indexOf("GMT") == -1) {
                headerField = headerField + " GMT";
            }
            return Date.parse(headerField);
        } catch (Exception unused) {
            return j;
        }
    }

    @Override // java.net.URLConnection
    public Permission getPermission() {
        int port = this.url.getPort();
        if (port < 0) {
            port = 80;
        }
        return new SocketPermission(this.url.getHost() + ":" + port, "connect");
    }
}
