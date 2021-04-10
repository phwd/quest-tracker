package sun.net.www.protocol.ftp;

import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

public class Handler extends URLStreamHandler {
    /* access modifiers changed from: protected */
    @Override // java.net.URLStreamHandler
    public int getDefaultPort() {
        return 21;
    }

    /* access modifiers changed from: protected */
    @Override // java.net.URLStreamHandler
    public boolean equals(URL url, URL url2) {
        String userInfo = url.getUserInfo();
        String userInfo2 = url2.getUserInfo();
        return super.equals(url, url2) && (userInfo != null ? userInfo.equals(userInfo2) : userInfo2 == null);
    }

    /* access modifiers changed from: protected */
    @Override // java.net.URLStreamHandler
    public URLConnection openConnection(URL url) {
        return openConnection(url, null);
    }

    /* access modifiers changed from: protected */
    @Override // java.net.URLStreamHandler
    public URLConnection openConnection(URL url, Proxy proxy) {
        return new FtpURLConnection(url, proxy);
    }
}
