package sun.net.www;

import java.net.URL;
import java.util.HashMap;

public abstract class URLConnection extends java.net.URLConnection {
    private static HashMap proxiedHosts = new HashMap();
    private int contentLength = -1;
    protected MessageHeader properties = new MessageHeader();

    public URLConnection(URL url) {
        super(url);
    }

    public void setProperties(MessageHeader messageHeader) {
        this.properties = messageHeader;
    }

    @Override // java.net.URLConnection
    public String getHeaderField(String str) {
        try {
            getInputStream();
            MessageHeader messageHeader = this.properties;
            if (messageHeader == null) {
                return null;
            }
            return messageHeader.findValue(str);
        } catch (Exception unused) {
            return null;
        }
    }
}
