package javax.xml.datatype;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Properties;
import libcore.io.IoUtils;

final class FactoryFinder {
    private static final String CLASS_NAME = "javax.xml.datatype.FactoryFinder";
    private static final int DEFAULT_LINE_LENGTH = 80;
    private static boolean debug;

    static {
        boolean z = false;
        debug = false;
        String val = System.getProperty("jaxp.debug");
        if (val != null && !"false".equals(val)) {
            z = true;
        }
        debug = z;
    }

    private static class CacheHolder {
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
            throw new UnsupportedOperationException("Method not decompiled: javax.xml.datatype.FactoryFinder.CacheHolder.<clinit>():void");
        }
    }

    private FactoryFinder() {
    }

    /* access modifiers changed from: private */
    public static void debugPrintln(String msg) {
        if (debug) {
            PrintStream printStream = System.err;
            printStream.println("javax.xml.datatype.FactoryFinder:" + msg);
        }
    }

    private static ClassLoader findClassLoader() throws ConfigurationError {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if (debug) {
            debugPrintln("Using context class loader: " + ((Object) classLoader));
        }
        if (classLoader == null) {
            classLoader = FactoryFinder.class.getClassLoader();
            if (debug) {
                debugPrintln("Using the class loader of FactoryFinder: " + ((Object) classLoader));
            }
        }
        return classLoader;
    }

    static Object newInstance(String className, ClassLoader classLoader) throws ConfigurationError {
        Class spiClass;
        if (classLoader == null) {
            try {
                spiClass = Class.forName(className);
            } catch (ClassNotFoundException x) {
                throw new ConfigurationError("Provider " + className + " not found", x);
            } catch (Exception x2) {
                throw new ConfigurationError("Provider " + className + " could not be instantiated: " + ((Object) x2), x2);
            }
        } else {
            spiClass = classLoader.loadClass(className);
        }
        if (debug) {
            debugPrintln("Loaded " + className + " from " + which(spiClass));
        }
        return spiClass.newInstance();
    }

    static Object find(String factoryId, String fallbackClassName) throws ConfigurationError {
        ClassLoader classLoader = findClassLoader();
        String systemProp = System.getProperty(factoryId);
        if (systemProp == null || systemProp.length() <= 0) {
            try {
                String factoryClassName = CacheHolder.cacheProps.getProperty(factoryId);
                if (debug) {
                    debugPrintln("found " + factoryClassName + " in $java.home/jaxp.properties");
                }
                if (factoryClassName != null) {
                    return newInstance(factoryClassName, classLoader);
                }
            } catch (Exception ex) {
                if (debug) {
                    ex.printStackTrace();
                }
            }
            Object provider = findJarServiceProvider(factoryId);
            if (provider != null) {
                return provider;
            }
            if (fallbackClassName != null) {
                if (debug) {
                    debugPrintln("loaded from fallback value: " + fallbackClassName);
                }
                return newInstance(fallbackClassName, classLoader);
            }
            throw new ConfigurationError("Provider for " + factoryId + " cannot be found", null);
        }
        if (debug) {
            debugPrintln("found " + systemProp + " in the system property " + factoryId);
        }
        return newInstance(systemProp, classLoader);
    }

    private static Object findJarServiceProvider(String factoryId) throws ConfigurationError {
        BufferedReader rd;
        String serviceId = "META-INF/services/" + factoryId;
        InputStream is = null;
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        if (cl != null) {
            is = cl.getResourceAsStream(serviceId);
        }
        if (is == null) {
            cl = FactoryFinder.class.getClassLoader();
            is = cl.getResourceAsStream(serviceId);
        }
        if (is == null) {
            return null;
        }
        if (debug) {
            debugPrintln("found jar resource=" + serviceId + " using ClassLoader: " + ((Object) cl));
        }
        try {
            rd = new BufferedReader(new InputStreamReader(is, "UTF-8"), 80);
        } catch (UnsupportedEncodingException e) {
            rd = new BufferedReader(new InputStreamReader(is), 80);
        }
        try {
            String factoryClassName = rd.readLine();
            if (factoryClassName == null || "".equals(factoryClassName)) {
                return null;
            }
            if (debug) {
                debugPrintln("found in resource, value=" + factoryClassName);
            }
            return newInstance(factoryClassName, cl);
        } catch (IOException e2) {
            return null;
        } finally {
            IoUtils.closeQuietly(rd);
        }
    }

    /* access modifiers changed from: package-private */
    public static class ConfigurationError extends Error {
        private static final long serialVersionUID = -3644413026244211347L;
        private Exception exception;

        ConfigurationError(String msg, Exception x) {
            super(msg);
            this.exception = x;
        }

        /* access modifiers changed from: package-private */
        public Exception getException() {
            return this.exception;
        }
    }

    private static String which(Class clazz) {
        URL it;
        try {
            String classnameAsResource = clazz.getName().replace('.', '/') + ".class";
            ClassLoader loader = clazz.getClassLoader();
            if (loader != null) {
                it = loader.getResource(classnameAsResource);
            } else {
                it = ClassLoader.getSystemResource(classnameAsResource);
            }
            if (it != null) {
                return it.toString();
            }
            return "unknown location";
        } catch (VirtualMachineError vme) {
            throw vme;
        } catch (ThreadDeath td) {
            throw td;
        } catch (Throwable t) {
            if (!debug) {
                return "unknown location";
            }
            t.printStackTrace();
            return "unknown location";
        }
    }
}
