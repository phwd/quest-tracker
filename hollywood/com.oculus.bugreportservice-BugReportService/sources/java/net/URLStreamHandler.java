package java.net;

import java.util.Objects;

public abstract class URLStreamHandler {
    /* access modifiers changed from: protected */
    public int getDefaultPort() {
        return -1;
    }

    /* access modifiers changed from: protected */
    public abstract URLConnection openConnection(URL url);

    /* access modifiers changed from: protected */
    public URLConnection openConnection(URL url, Proxy proxy) {
        throw new UnsupportedOperationException("Method not implemented.");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x017c  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0185  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void parseURL(java.net.URL r19, java.lang.String r20, int r21, int r22) {
        /*
        // Method dump skipped, instructions count: 725
        */
        throw new UnsupportedOperationException("Method not decompiled: java.net.URLStreamHandler.parseURL(java.net.URL, java.lang.String, int, int):void");
    }

    /* access modifiers changed from: protected */
    public boolean equals(URL url, URL url2) {
        return Objects.equals(url.getRef(), url2.getRef()) && Objects.equals(url.getQuery(), url2.getQuery()) && sameFile(url, url2);
    }

    /* access modifiers changed from: protected */
    public int hashCode(URL url) {
        return Objects.hash(url.getRef(), url.getQuery(), url.getProtocol(), url.getFile(), url.getHost(), Integer.valueOf(url.getPort()));
    }

    /* access modifiers changed from: protected */
    public boolean sameFile(URL url, URL url2) {
        if (url.getProtocol() != url2.getProtocol() && (url.getProtocol() == null || !url.getProtocol().equalsIgnoreCase(url2.getProtocol()))) {
            return false;
        }
        if (url.getFile() != url2.getFile() && (url.getFile() == null || !url.getFile().equals(url2.getFile()))) {
            return false;
        }
        if ((url.getPort() != -1 ? url.getPort() : url.handler.getDefaultPort()) == (url2.getPort() != -1 ? url2.getPort() : url2.handler.getDefaultPort()) && hostsEqual(url, url2)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean hostsEqual(URL url, URL url2) {
        if (url.getHost() == null || url2.getHost() == null) {
            return url.getHost() == null && url2.getHost() == null;
        }
        return url.getHost().equalsIgnoreCase(url2.getHost());
    }

    /* access modifiers changed from: protected */
    public String toExternalForm(URL url) {
        int length = url.getProtocol().length() + 1;
        if (url.getAuthority() != null && url.getAuthority().length() > 0) {
            length += url.getAuthority().length() + 2;
        }
        if (url.getPath() != null) {
            length += url.getPath().length();
        }
        if (url.getQuery() != null) {
            length += url.getQuery().length() + 1;
        }
        if (url.getRef() != null) {
            length += url.getRef().length() + 1;
        }
        StringBuilder sb = new StringBuilder(length);
        sb.append(url.getProtocol());
        sb.append(":");
        if (url.getAuthority() != null) {
            sb.append("//");
            sb.append(url.getAuthority());
        }
        String file = url.getFile();
        if (file != null) {
            sb.append(file);
        }
        if (url.getRef() != null) {
            sb.append("#");
            sb.append(url.getRef());
        }
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public void setURL(URL url, String str, String str2, int i, String str3, String str4, String str5, String str6, String str7) {
        if (this == url.handler) {
            url.set(url.getProtocol(), str2, i, str3, str4, str5, str6, str7);
            return;
        }
        throw new SecurityException("handler for url different from this handler");
    }

    /* access modifiers changed from: protected */
    public void setURL(URL url, String str, String str2, int i, String str3, String str4) {
        String str5;
        String str6;
        String str7;
        String str8;
        String str9;
        String str10;
        String str11 = str2;
        if (str11 == null || str2.length() == 0) {
            str7 = str11;
            str6 = null;
            str5 = null;
        } else {
            if (i == -1) {
                str10 = str11;
            } else {
                str10 = str2 + ":" + i;
            }
            int lastIndexOf = str2.lastIndexOf(64);
            if (lastIndexOf != -1) {
                str5 = str2.substring(0, lastIndexOf);
                str11 = str2.substring(lastIndexOf + 1);
            } else {
                str5 = null;
            }
            str6 = str10;
            str7 = str11;
        }
        if (str3 != null) {
            int lastIndexOf2 = str3.lastIndexOf(63);
            if (lastIndexOf2 != -1) {
                String substring = str3.substring(lastIndexOf2 + 1);
                str9 = str3.substring(0, lastIndexOf2);
                str8 = substring;
            } else {
                str9 = str3;
                str8 = null;
            }
        } else {
            str9 = null;
            str8 = null;
        }
        setURL(url, str, str7, i, str6, str5, str9, str8, str4);
    }
}
