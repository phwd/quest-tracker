package android.icu.impl;

import java.io.File;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.JarURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public abstract class URLHandler {
    private static final boolean DEBUG = ICUDebug.enabled("URLHandler");
    private static final Map handlers;

    public interface URLVisitor {
        void visit(String str);
    }

    public abstract void guide(URLVisitor uRLVisitor, boolean z, boolean z2);

    /* JADX WARNING: Removed duplicated region for block: B:49:0x00cb A[Catch:{ all -> 0x00d8 }] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00d2 A[SYNTHETIC, Splitter:B:51:0x00d2] */
    static {
        /*
        // Method dump skipped, instructions count: 223
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.URLHandler.<clinit>():void");
    }

    public static URLHandler get(URL url) {
        Method method;
        if (url == null) {
            return null;
        }
        String protocol = url.getProtocol();
        Map map = handlers;
        if (!(map == null || (method = (Method) map.get(protocol)) == null)) {
            try {
                URLHandler uRLHandler = (URLHandler) method.invoke(null, url);
                if (uRLHandler != null) {
                    return uRLHandler;
                }
            } catch (IllegalAccessException e) {
                if (DEBUG) {
                    System.err.println(e);
                }
            } catch (IllegalArgumentException e2) {
                if (DEBUG) {
                    System.err.println(e2);
                }
            } catch (InvocationTargetException e3) {
                if (DEBUG) {
                    System.err.println(e3);
                }
            }
        }
        return getDefault(url);
    }

    protected static URLHandler getDefault(URL url) {
        URLHandler jarURLHandler;
        String protocol = url.getProtocol();
        try {
            if (protocol.equals("file")) {
                jarURLHandler = new FileURLHandler(url);
            } else if (!protocol.equals("jar") && !protocol.equals("wsjar")) {
                return null;
            } else {
                jarURLHandler = new JarURLHandler(url);
            }
            return jarURLHandler;
        } catch (Exception unused) {
            return null;
        }
    }

    /* access modifiers changed from: private */
    public static class FileURLHandler extends URLHandler {
        File file;

        FileURLHandler(URL url) {
            try {
                this.file = new File(url.toURI());
            } catch (URISyntaxException unused) {
            }
            File file2 = this.file;
            if (file2 == null || !file2.exists()) {
                if (URLHandler.DEBUG) {
                    PrintStream printStream = System.err;
                    printStream.println("file does not exist - " + url.toString());
                }
                throw new IllegalArgumentException();
            }
        }

        @Override // android.icu.impl.URLHandler
        public void guide(URLVisitor uRLVisitor, boolean z, boolean z2) {
            if (this.file.isDirectory()) {
                process(uRLVisitor, z, z2, "/", this.file.listFiles());
            } else {
                uRLVisitor.visit(this.file.getName());
            }
        }

        private void process(URLVisitor uRLVisitor, boolean z, boolean z2, String str, File[] fileArr) {
            if (fileArr != null) {
                for (File file2 : fileArr) {
                    if (!file2.isDirectory()) {
                        uRLVisitor.visit(z2 ? file2.getName() : str + file2.getName());
                    } else if (z) {
                        process(uRLVisitor, z, z2, str + file2.getName() + '/', file2.listFiles());
                    }
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static class JarURLHandler extends URLHandler {
        JarFile jarFile;
        String prefix;

        JarURLHandler(URL url) {
            String url2;
            int indexOf;
            try {
                this.prefix = url.getPath();
                int lastIndexOf = this.prefix.lastIndexOf("!/");
                if (lastIndexOf >= 0) {
                    this.prefix = this.prefix.substring(lastIndexOf + 2);
                }
                if (!url.getProtocol().equals("jar") && (indexOf = (url2 = url.toString()).indexOf(":")) != -1) {
                    url = new URL("jar" + url2.substring(indexOf));
                }
                this.jarFile = ((JarURLConnection) url.openConnection()).getJarFile();
            } catch (Exception e) {
                if (URLHandler.DEBUG) {
                    PrintStream printStream = System.err;
                    printStream.println("icurb jar error: " + e);
                }
                throw new IllegalArgumentException("jar error: " + e.getMessage());
            }
        }

        @Override // android.icu.impl.URLHandler
        public void guide(URLVisitor uRLVisitor, boolean z, boolean z2) {
            try {
                Enumeration entries = this.jarFile.entries();
                while (entries.hasMoreElements()) {
                    JarEntry jarEntry = (JarEntry) entries.nextElement();
                    if (!jarEntry.isDirectory()) {
                        String name = jarEntry.getName();
                        if (name.startsWith(this.prefix)) {
                            String substring = name.substring(this.prefix.length());
                            int lastIndexOf = substring.lastIndexOf(47);
                            if (lastIndexOf <= 0 || z) {
                                if (z2 && lastIndexOf != -1) {
                                    substring = substring.substring(lastIndexOf + 1);
                                }
                                uRLVisitor.visit(substring);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                if (URLHandler.DEBUG) {
                    PrintStream printStream = System.err;
                    printStream.println("icurb jar error: " + e);
                }
            }
        }
    }

    public void guide(URLVisitor uRLVisitor, boolean z) {
        guide(uRLVisitor, z, true);
    }
}
