package sun.net.www.protocol.jar;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import sun.net.www.ParseUtil;

public class Handler extends URLStreamHandler {
    /* access modifiers changed from: protected */
    @Override // java.net.URLStreamHandler
    public URLConnection openConnection(URL url) {
        return new JarURLConnection(url, this);
    }

    private static int indexOfBangSlash(String str) {
        int length = str.length();
        while (true) {
            int lastIndexOf = str.lastIndexOf(33, length);
            if (lastIndexOf == -1) {
                return -1;
            }
            if (lastIndexOf != str.length() - 1) {
                int i = lastIndexOf + 1;
                if (str.charAt(i) == '/') {
                    return i;
                }
            }
            length = lastIndexOf - 1;
        }
    }

    /* access modifiers changed from: protected */
    @Override // java.net.URLStreamHandler
    public boolean sameFile(URL url, URL url2) {
        if (!url.getProtocol().equals("jar") || !url2.getProtocol().equals("jar")) {
            return false;
        }
        String file = url.getFile();
        String file2 = url2.getFile();
        int indexOf = file.indexOf("!/");
        int indexOf2 = file2.indexOf("!/");
        if (indexOf == -1 || indexOf2 == -1) {
            return super.sameFile(url, url2);
        }
        if (!file.substring(indexOf + 2).equals(file2.substring(indexOf2 + 2))) {
            return false;
        }
        try {
            if (!super.sameFile(new URL(file.substring(0, indexOf)), new URL(file2.substring(0, indexOf2)))) {
                return false;
            }
            return true;
        } catch (MalformedURLException unused) {
            return super.sameFile(url, url2);
        }
    }

    /* access modifiers changed from: protected */
    @Override // java.net.URLStreamHandler
    public int hashCode(URL url) {
        int i;
        int hashCode;
        String protocol = url.getProtocol();
        int hashCode2 = protocol != null ? protocol.hashCode() + 0 : 0;
        String file = url.getFile();
        int indexOf = file.indexOf("!/");
        if (indexOf == -1) {
            hashCode = file.hashCode();
        } else {
            String substring = file.substring(0, indexOf);
            try {
                i = new URL(substring).hashCode();
            } catch (MalformedURLException unused) {
                i = substring.hashCode();
            }
            hashCode2 += i;
            hashCode = file.substring(indexOf + 2).hashCode();
        }
        return hashCode2 + hashCode;
    }

    /* access modifiers changed from: protected */
    @Override // java.net.URLStreamHandler
    public void parseURL(URL url, String str, int i, int i2) {
        String str2;
        String str3;
        int indexOf = str.indexOf(35, i2);
        boolean z = indexOf == i;
        String str4 = null;
        if (indexOf > -1) {
            String substring = str.substring(indexOf + 1, str.length());
            if (z) {
                str4 = url.getFile();
            }
            str2 = substring;
        } else {
            str2 = null;
        }
        boolean equalsIgnoreCase = str.length() >= 4 ? str.substring(0, 4).equalsIgnoreCase("jar:") : false;
        String substring2 = str.substring(i, i2);
        if (equalsIgnoreCase) {
            parseAbsoluteSpec(substring2);
        } else if (!z) {
            String parseContextSpec = parseContextSpec(url, substring2);
            int indexOfBangSlash = indexOfBangSlash(parseContextSpec);
            substring2 = parseContextSpec.substring(0, indexOfBangSlash) + new ParseUtil().canonizeString(parseContextSpec.substring(indexOfBangSlash));
        } else {
            str3 = str4;
            setURL(url, "jar", "", -1, str3, str2);
        }
        str3 = substring2;
        setURL(url, "jar", "", -1, str3, str2);
    }

    private String parseAbsoluteSpec(String str) {
        int indexOfBangSlash = indexOfBangSlash(str);
        if (indexOfBangSlash != -1) {
            try {
                new URL(str.substring(0, indexOfBangSlash - 1));
                return str;
            } catch (MalformedURLException e) {
                throw new NullPointerException("invalid url: " + str + " (" + e + ")");
            }
        } else {
            throw new NullPointerException("no !/ in spec");
        }
    }

    private String parseContextSpec(URL url, String str) {
        String file = url.getFile();
        if (str.startsWith("/")) {
            int indexOfBangSlash = indexOfBangSlash(file);
            if (indexOfBangSlash != -1) {
                file = file.substring(0, indexOfBangSlash);
            } else {
                throw new NullPointerException("malformed context url:" + url + ": no !/");
            }
        }
        if (!file.endsWith("/") && !str.startsWith("/")) {
            int lastIndexOf = file.lastIndexOf(47);
            if (lastIndexOf != -1) {
                file = file.substring(0, lastIndexOf + 1);
            } else {
                throw new NullPointerException("malformed context url:" + url);
            }
        }
        return file + str;
    }
}
