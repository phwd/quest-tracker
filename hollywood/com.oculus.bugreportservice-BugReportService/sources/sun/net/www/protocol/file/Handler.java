package sun.net.www.protocol.file;

import java.io.File;
import java.io.IOException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import sun.net.www.ParseUtil;

public class Handler extends URLStreamHandler {
    /* access modifiers changed from: protected */
    @Override // java.net.URLStreamHandler
    public void parseURL(URL url, String str, int i, int i2) {
        super.parseURL(url, str.replace(File.separatorChar, '/'), i, i2);
    }

    @Override // java.net.URLStreamHandler
    public synchronized URLConnection openConnection(URL url) {
        return openConnection(url, null);
    }

    @Override // java.net.URLStreamHandler
    public synchronized URLConnection openConnection(URL url, Proxy proxy) {
        URLConnection uRLConnection;
        String str;
        String host = url.getHost();
        if (host == null || host.equals("") || host.equals("~") || host.equalsIgnoreCase("localhost")) {
            return createFileURLConnection(url, new File(ParseUtil.decode(url.getPath())));
        }
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(url.getFile());
            if (url.getRef() == null) {
                str = "";
            } else {
                str = "#" + url.getRef();
            }
            sb.append(str);
            URL url2 = new URL("ftp", host, sb.toString());
            uRLConnection = proxy != null ? url2.openConnection(proxy) : url2.openConnection();
        } catch (IOException unused) {
            uRLConnection = null;
        }
        if (uRLConnection != null) {
            return uRLConnection;
        }
        throw new IOException("Unable to connect to: " + url.toExternalForm());
    }

    /* access modifiers changed from: protected */
    public URLConnection createFileURLConnection(URL url, File file) {
        return new FileURLConnection(url, file);
    }

    /* access modifiers changed from: protected */
    @Override // java.net.URLStreamHandler
    public boolean hostsEqual(URL url, URL url2) {
        String host = url.getHost();
        String host2 = url2.getHost();
        if ("localhost".equalsIgnoreCase(host) && (host2 == null || "".equals(host2))) {
            return true;
        }
        if (!"localhost".equalsIgnoreCase(host2) || (host != null && !"".equals(host))) {
            return super.hostsEqual(url, url2);
        }
        return true;
    }
}
