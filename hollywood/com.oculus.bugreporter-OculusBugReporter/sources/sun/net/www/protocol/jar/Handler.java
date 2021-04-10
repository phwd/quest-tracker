package sun.net.www.protocol.jar;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import sun.net.www.ParseUtil;

public class Handler extends URLStreamHandler {
    private static final String separator = "!/";

    /* access modifiers changed from: protected */
    @Override // java.net.URLStreamHandler
    public URLConnection openConnection(URL u) throws IOException {
        return new JarURLConnection(u, this);
    }

    private static int indexOfBangSlash(String spec) {
        int indexOfBang = spec.length();
        while (true) {
            int indexOfBang2 = spec.lastIndexOf(33, indexOfBang);
            if (indexOfBang2 == -1) {
                return -1;
            }
            if (indexOfBang2 != spec.length() - 1 && spec.charAt(indexOfBang2 + 1) == '/') {
                return indexOfBang2 + 1;
            }
            indexOfBang = indexOfBang2 - 1;
        }
    }

    /* access modifiers changed from: protected */
    @Override // java.net.URLStreamHandler
    public boolean sameFile(URL u1, URL u2) {
        if (!u1.getProtocol().equals("jar") || !u2.getProtocol().equals("jar")) {
            return false;
        }
        String file1 = u1.getFile();
        String file2 = u2.getFile();
        int sep1 = file1.indexOf(separator);
        int sep2 = file2.indexOf(separator);
        if (sep1 == -1 || sep2 == -1) {
            return super.sameFile(u1, u2);
        }
        if (!file1.substring(sep1 + 2).equals(file2.substring(sep2 + 2))) {
            return false;
        }
        try {
            if (!super.sameFile(new URL(file1.substring(0, sep1)), new URL(file2.substring(0, sep2)))) {
                return false;
            }
            return true;
        } catch (MalformedURLException e) {
            return super.sameFile(u1, u2);
        }
    }

    /* access modifiers changed from: protected */
    @Override // java.net.URLStreamHandler
    public int hashCode(URL u) {
        int h;
        int h2 = 0;
        String protocol = u.getProtocol();
        if (protocol != null) {
            h2 = 0 + protocol.hashCode();
        }
        String file = u.getFile();
        int sep = file.indexOf(separator);
        if (sep == -1) {
            return file.hashCode() + h2;
        }
        String fileWithoutEntry = file.substring(0, sep);
        try {
            h = h2 + new URL(fileWithoutEntry).hashCode();
        } catch (MalformedURLException e) {
            h = h2 + fileWithoutEntry.hashCode();
        }
        return h + file.substring(sep + 2).hashCode();
    }

    /* access modifiers changed from: protected */
    @Override // java.net.URLStreamHandler
    public void parseURL(URL url, String spec, int start, int limit) {
        String ref;
        boolean absoluteSpec;
        String file;
        String file2 = null;
        int refPos = spec.indexOf(35, limit);
        boolean refOnly = refPos == start;
        if (refPos > -1) {
            String ref2 = spec.substring(refPos + 1, spec.length());
            if (refOnly) {
                file2 = url.getFile();
                ref = ref2;
            } else {
                ref = ref2;
            }
        } else {
            ref = null;
        }
        if (spec.length() >= 4) {
            absoluteSpec = spec.substring(0, 4).equalsIgnoreCase("jar:");
        } else {
            absoluteSpec = false;
        }
        String spec2 = spec.substring(start, limit);
        if (absoluteSpec) {
            file = parseAbsoluteSpec(spec2);
        } else if (!refOnly) {
            String file3 = parseContextSpec(url, spec2);
            int bangSlash = indexOfBangSlash(file3);
            file = file3.substring(0, bangSlash) + new ParseUtil().canonizeString(file3.substring(bangSlash));
        } else {
            file = file2;
        }
        setURL(url, "jar", "", -1, file, ref);
    }

    private String parseAbsoluteSpec(String spec) {
        int index = indexOfBangSlash(spec);
        if (index != -1) {
            try {
                new URL(spec.substring(0, index - 1));
                return spec;
            } catch (MalformedURLException e) {
                throw new NullPointerException("invalid url: " + spec + " (" + ((Object) e) + ")");
            }
        } else {
            throw new NullPointerException("no !/ in spec");
        }
    }

    private String parseContextSpec(URL url, String spec) {
        String ctxFile = url.getFile();
        if (spec.startsWith("/")) {
            int bangSlash = indexOfBangSlash(ctxFile);
            if (bangSlash != -1) {
                ctxFile = ctxFile.substring(0, bangSlash);
            } else {
                throw new NullPointerException("malformed context url:" + ((Object) url) + ": no !/");
            }
        }
        if (!ctxFile.endsWith("/") && !spec.startsWith("/")) {
            int lastSlash = ctxFile.lastIndexOf(47);
            if (lastSlash != -1) {
                ctxFile = ctxFile.substring(0, lastSlash + 1);
            } else {
                throw new NullPointerException("malformed context url:" + ((Object) url));
            }
        }
        return ctxFile + spec;
    }
}
