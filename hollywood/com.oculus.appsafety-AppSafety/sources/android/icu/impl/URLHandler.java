package android.icu.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.JarURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public abstract class URLHandler {
    private static final boolean DEBUG = ICUDebug.enabled("URLHandler");
    public static final String PROPNAME = "urlhandler.props";
    private static final Map<String, Method> handlers;

    public interface URLVisitor {
        void visit(String str);
    }

    public abstract void guide(URLVisitor uRLVisitor, boolean z, boolean z2);

    static {
        Map<String, Method> h = null;
        BufferedReader br = null;
        try {
            InputStream is = ClassLoaderUtil.getClassLoader(URLHandler.class).getResourceAsStream(PROPNAME);
            if (is != null) {
                Class<?>[] params = {URL.class};
                br = new BufferedReader(new InputStreamReader(is));
                String line = br.readLine();
                while (true) {
                    if (line == null) {
                        break;
                    }
                    String line2 = line.trim();
                    if (line2.length() != 0) {
                        if (line2.charAt(0) != '#') {
                            int ix = line2.indexOf(61);
                            if (ix != -1) {
                                String key = line2.substring(0, ix).trim();
                                try {
                                    Method m = Class.forName(line2.substring(ix + 1).trim()).getDeclaredMethod("get", params);
                                    if (h == null) {
                                        h = new HashMap<>();
                                    }
                                    h.put(key, m);
                                } catch (ClassNotFoundException e) {
                                    if (DEBUG) {
                                        System.err.println(e);
                                    }
                                } catch (NoSuchMethodException e2) {
                                    if (DEBUG) {
                                        System.err.println(e2);
                                    }
                                } catch (SecurityException e3) {
                                    if (DEBUG) {
                                        System.err.println(e3);
                                    }
                                }
                            } else if (DEBUG) {
                                PrintStream printStream = System.err;
                                printStream.println("bad urlhandler line: '" + line2 + "'");
                            }
                        }
                    }
                    line = br.readLine();
                }
                br.close();
            }
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e4) {
                }
            }
        } catch (Throwable th) {
            if (0 != 0) {
                try {
                    br.close();
                } catch (IOException e5) {
                }
            }
            throw th;
        }
        handlers = h;
    }

    public static URLHandler get(URL url) {
        Method m;
        if (url == null) {
            return null;
        }
        String protocol = url.getProtocol();
        Map<String, Method> map = handlers;
        if (!(map == null || (m = map.get(protocol)) == null)) {
            try {
                URLHandler handler = (URLHandler) m.invoke(null, url);
                if (handler != null) {
                    return handler;
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
        String protocol = url.getProtocol();
        try {
            if (protocol.equals("file")) {
                return new FileURLHandler(url);
            }
            if (protocol.equals("jar") || protocol.equals("wsjar")) {
                return new JarURLHandler(url);
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    /* access modifiers changed from: private */
    public static class FileURLHandler extends URLHandler {
        File file;

        FileURLHandler(URL url) {
            try {
                this.file = new File(url.toURI());
            } catch (URISyntaxException e) {
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
        public void guide(URLVisitor v, boolean recurse, boolean strip) {
            if (this.file.isDirectory()) {
                process(v, recurse, strip, "/", this.file.listFiles());
            } else {
                v.visit(this.file.getName());
            }
        }

        private void process(URLVisitor v, boolean recurse, boolean strip, String path, File[] files) {
            if (files != null) {
                for (File f : files) {
                    if (!f.isDirectory()) {
                        v.visit(strip ? f.getName() : path + f.getName());
                    } else if (recurse) {
                        process(v, recurse, strip, path + f.getName() + '/', f.listFiles());
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
            String urlStr;
            int idx;
            try {
                this.prefix = url.getPath();
                int ix = this.prefix.lastIndexOf("!/");
                if (ix >= 0) {
                    this.prefix = this.prefix.substring(ix + 2);
                }
                if (!url.getProtocol().equals("jar") && (idx = (urlStr = url.toString()).indexOf(":")) != -1) {
                    url = new URL("jar" + urlStr.substring(idx));
                }
                this.jarFile = ((JarURLConnection) url.openConnection()).getJarFile();
            } catch (Exception e) {
                if (URLHandler.DEBUG) {
                    PrintStream printStream = System.err;
                    printStream.println("icurb jar error: " + ((Object) e));
                }
                throw new IllegalArgumentException("jar error: " + e.getMessage());
            }
        }

        @Override // android.icu.impl.URLHandler
        public void guide(URLVisitor v, boolean recurse, boolean strip) {
            try {
                Enumeration<JarEntry> entries = this.jarFile.entries();
                while (entries.hasMoreElements()) {
                    JarEntry entry = entries.nextElement();
                    if (!entry.isDirectory()) {
                        String name = entry.getName();
                        if (name.startsWith(this.prefix)) {
                            String name2 = name.substring(this.prefix.length());
                            int ix = name2.lastIndexOf(47);
                            if (ix <= 0 || recurse) {
                                if (strip && ix != -1) {
                                    name2 = name2.substring(ix + 1);
                                }
                                v.visit(name2);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                if (URLHandler.DEBUG) {
                    PrintStream printStream = System.err;
                    printStream.println("icurb jar error: " + ((Object) e));
                }
            }
        }
    }

    public void guide(URLVisitor visitor, boolean recurse) {
        guide(visitor, recurse, true);
    }
}
