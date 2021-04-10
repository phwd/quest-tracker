package javax.xml.xpath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Properties;
import libcore.io.IoUtils;

final class XPathFactoryFinder {
    private static final int DEFAULT_LINE_LENGTH = 80;
    private static final Class SERVICE_CLASS = XPathFactory.class;
    private static final String SERVICE_ID = ("META-INF/services/" + SERVICE_CLASS.getName());
    private static boolean debug;
    private final ClassLoader classLoader;

    static {
        boolean z = false;
        debug = false;
        String val = System.getProperty("jaxp.debug");
        if (val != null && !"false".equals(val)) {
            z = true;
        }
        debug = z;
    }

    /* access modifiers changed from: private */
    public static class CacheHolder {
        private static Properties cacheProps = new Properties();

        private CacheHolder() {
        }

        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0062, code lost:
            r5 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
            r3.close();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x0067, code lost:
            r6 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x0068, code lost:
            r4.addSuppressed(r6);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x006b, code lost:
            throw r5;
         */
        static {
            /*
            // Method dump skipped, instructions count: 119
            */
            throw new UnsupportedOperationException("Method not decompiled: javax.xml.xpath.XPathFactoryFinder.CacheHolder.<clinit>():void");
        }
    }

    /* access modifiers changed from: private */
    public static void debugPrintln(String msg) {
        if (debug) {
            PrintStream printStream = System.err;
            printStream.println("JAXP: " + msg);
        }
    }

    public XPathFactoryFinder(ClassLoader loader) {
        this.classLoader = loader;
        if (debug) {
            debugDisplayClassLoader();
        }
    }

    private void debugDisplayClassLoader() {
        if (this.classLoader == Thread.currentThread().getContextClassLoader()) {
            debugPrintln("using thread context class loader (" + ((Object) this.classLoader) + ") for search");
        } else if (this.classLoader == ClassLoader.getSystemClassLoader()) {
            debugPrintln("using system class loader (" + ((Object) this.classLoader) + ") for search");
        } else {
            debugPrintln("using class loader (" + ((Object) this.classLoader) + ") for search");
        }
    }

    public XPathFactory newFactory(String uri) {
        if (uri != null) {
            XPathFactory f = _newFactory(uri);
            if (debug) {
                if (f != null) {
                    debugPrintln("factory '" + f.getClass().getName() + "' was found for " + uri);
                } else {
                    debugPrintln("unable to find a factory for " + uri);
                }
            }
            return f;
        }
        throw new NullPointerException("uri == null");
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x007f A[Catch:{ Exception -> 0x00a2 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private javax.xml.xpath.XPathFactory _newFactory(java.lang.String r7) {
        /*
        // Method dump skipped, instructions count: 294
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.xml.xpath.XPathFactoryFinder._newFactory(java.lang.String):javax.xml.xpath.XPathFactory");
    }

    /* access modifiers changed from: package-private */
    public XPathFactory createInstance(String className) {
        Class clazz;
        try {
            if (debug) {
                debugPrintln("instantiating " + className);
            }
            if (this.classLoader != null) {
                clazz = this.classLoader.loadClass(className);
            } else {
                clazz = Class.forName(className);
            }
            if (debug) {
                debugPrintln("loaded it from " + which(clazz));
            }
            Object o = clazz.newInstance();
            if (o instanceof XPathFactory) {
                return (XPathFactory) o;
            }
            if (!debug) {
                return null;
            }
            debugPrintln(className + " is not assignable to " + SERVICE_CLASS.getName());
            return null;
        } catch (VirtualMachineError vme) {
            throw vme;
        } catch (ThreadDeath td) {
            throw td;
        } catch (Throwable t) {
            if (!debug) {
                return null;
            }
            debugPrintln("failed to instantiate " + className);
            t.printStackTrace();
            return null;
        }
    }

    private XPathFactory loadFromServicesFile(String uri, String resourceName, InputStream in) {
        BufferedReader rd;
        if (debug) {
            debugPrintln("Reading " + resourceName);
        }
        try {
            rd = new BufferedReader(new InputStreamReader(in, "UTF-8"), 80);
        } catch (UnsupportedEncodingException e) {
            rd = new BufferedReader(new InputStreamReader(in), 80);
        }
        XPathFactory resultFactory = null;
        while (true) {
            try {
                String factoryClassName = rd.readLine();
                if (factoryClassName == null) {
                    break;
                }
                int hashIndex = factoryClassName.indexOf(35);
                if (hashIndex != -1) {
                    factoryClassName = factoryClassName.substring(0, hashIndex);
                }
                String factoryClassName2 = factoryClassName.trim();
                if (factoryClassName2.length() != 0) {
                    try {
                        XPathFactory foundFactory = createInstance(factoryClassName2);
                        if (foundFactory.isObjectModelSupported(uri)) {
                            resultFactory = foundFactory;
                            break;
                        }
                    } catch (Exception e2) {
                    }
                }
            } catch (IOException e3) {
            }
        }
        IoUtils.closeQuietly(rd);
        return resultFactory;
    }

    private Iterable<URL> createServiceFileIterator() {
        ClassLoader classLoader2 = this.classLoader;
        if (classLoader2 == null) {
            return Collections.singleton(XPathFactoryFinder.class.getClassLoader().getResource(SERVICE_ID));
        }
        try {
            Enumeration<URL> e = classLoader2.getResources(SERVICE_ID);
            if (debug && !e.hasMoreElements()) {
                debugPrintln("no " + SERVICE_ID + " file was found");
            }
            return Collections.list(e);
        } catch (IOException e2) {
            if (debug) {
                debugPrintln("failed to enumerate resources " + SERVICE_ID);
                e2.printStackTrace();
            }
            return Collections.emptySet();
        }
    }

    private static String which(Class clazz) {
        return which(clazz.getName(), clazz.getClassLoader());
    }

    private static String which(String classname, ClassLoader loader) {
        String classnameAsResource = classname.replace('.', '/') + ".class";
        if (loader == null) {
            loader = ClassLoader.getSystemClassLoader();
        }
        URL it = loader.getResource(classnameAsResource);
        if (it != null) {
            return it.toString();
        }
        return null;
    }
}
