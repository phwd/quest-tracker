package sun.net.www;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class URLConnection extends java.net.URLConnection {
    private static HashMap<String, Void> proxiedHosts = new HashMap<>();
    private int contentLength = -1;
    private String contentType;
    protected MessageHeader properties = new MessageHeader();

    public URLConnection(URL u) {
        super(u);
    }

    public MessageHeader getProperties() {
        return this.properties;
    }

    public void setProperties(MessageHeader properties2) {
        this.properties = properties2;
    }

    @Override // java.net.URLConnection
    public void setRequestProperty(String key, String value) {
        if (this.connected) {
            throw new IllegalAccessError("Already connected");
        } else if (key != null) {
            this.properties.set(key, value);
        } else {
            throw new NullPointerException("key cannot be null");
        }
    }

    @Override // java.net.URLConnection
    public void addRequestProperty(String key, String value) {
        if (this.connected) {
            throw new IllegalStateException("Already connected");
        } else if (key == null) {
            throw new NullPointerException("key is null");
        }
    }

    @Override // java.net.URLConnection
    public String getRequestProperty(String key) {
        if (!this.connected) {
            return null;
        }
        throw new IllegalStateException("Already connected");
    }

    @Override // java.net.URLConnection
    public Map<String, List<String>> getRequestProperties() {
        if (!this.connected) {
            return Collections.emptyMap();
        }
        throw new IllegalStateException("Already connected");
    }

    @Override // java.net.URLConnection
    public String getHeaderField(String name) {
        try {
            getInputStream();
            MessageHeader messageHeader = this.properties;
            if (messageHeader == null) {
                return null;
            }
            return messageHeader.findValue(name);
        } catch (Exception e) {
            return null;
        }
    }

    @Override // java.net.URLConnection
    public String getHeaderFieldKey(int n) {
        try {
            getInputStream();
            MessageHeader props = this.properties;
            if (props == null) {
                return null;
            }
            return props.getKey(n);
        } catch (Exception e) {
            return null;
        }
    }

    @Override // java.net.URLConnection
    public String getHeaderField(int n) {
        try {
            getInputStream();
            MessageHeader props = this.properties;
            if (props == null) {
                return null;
            }
            return props.getValue(n);
        } catch (Exception e) {
            return null;
        }
    }

    @Override // java.net.URLConnection
    public String getContentType() {
        if (this.contentType == null) {
            this.contentType = getHeaderField("content-type");
        }
        if (this.contentType == null) {
            String ct = null;
            try {
                ct = guessContentTypeFromStream(getInputStream());
            } catch (IOException e) {
            }
            String ce = this.properties.findValue("content-encoding");
            if (ct == null && (ct = this.properties.findValue("content-type")) == null) {
                if (this.url.getFile().endsWith("/")) {
                    ct = "text/html";
                } else {
                    ct = guessContentTypeFromName(this.url.getFile());
                }
            }
            if (ct == null || (ce != null && !ce.equalsIgnoreCase("7bit") && !ce.equalsIgnoreCase("8bit") && !ce.equalsIgnoreCase("binary"))) {
                ct = "content/unknown";
            }
            setContentType(ct);
        }
        return this.contentType;
    }

    public void setContentType(String type) {
        this.contentType = type;
        this.properties.set("content-type", type);
    }

    @Override // java.net.URLConnection
    public int getContentLength() {
        try {
            getInputStream();
            int l = this.contentLength;
            if (l >= 0) {
                return l;
            }
            try {
                l = Integer.parseInt(this.properties.findValue("content-length"));
                setContentLength(l);
                return l;
            } catch (Exception e) {
                return l;
            }
        } catch (Exception e2) {
            return -1;
        }
    }

    /* access modifiers changed from: protected */
    public void setContentLength(int length) {
        this.contentLength = length;
        this.properties.set("content-length", String.valueOf(length));
    }

    public boolean canCache() {
        return this.url.getFile().indexOf(63) < 0;
    }

    public void close() {
        this.url = null;
    }

    public static synchronized void setProxiedHost(String host) {
        synchronized (URLConnection.class) {
            proxiedHosts.put(host.toLowerCase(), null);
        }
    }

    public static synchronized boolean isProxiedHost(String host) {
        boolean containsKey;
        synchronized (URLConnection.class) {
            containsKey = proxiedHosts.containsKey(host.toLowerCase());
        }
        return containsKey;
    }
}
