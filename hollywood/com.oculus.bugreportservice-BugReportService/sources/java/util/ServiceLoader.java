package java.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

public final class ServiceLoader implements Iterable {
    private final ClassLoader loader;
    private LazyIterator lookupIterator;
    private LinkedHashMap providers = new LinkedHashMap();
    private final Class service;

    static /* synthetic */ void access$100(Class cls, String str, Throwable th) {
        fail(cls, str, th);
        throw null;
    }

    public void reload() {
        this.providers.clear();
        this.lookupIterator = new LazyIterator(this.service, this.loader);
    }

    private ServiceLoader(Class cls, ClassLoader classLoader) {
        Objects.requireNonNull(cls, "Service interface cannot be null");
        this.service = cls;
        this.loader = classLoader == null ? ClassLoader.getSystemClassLoader() : classLoader;
        reload();
    }

    private static void fail(Class cls, String str, Throwable th) {
        throw new ServiceConfigurationError(cls.getName() + ": " + str, th);
    }

    private static void fail(Class cls, String str) {
        throw new ServiceConfigurationError(cls.getName() + ": " + str);
    }

    private static void fail(Class cls, URL url, int i, String str) {
        fail(cls, url + ":" + i + ": " + str);
        throw null;
    }

    private int parseLine(Class cls, URL url, BufferedReader bufferedReader, int i, List list) {
        String readLine = bufferedReader.readLine();
        if (readLine == null) {
            return -1;
        }
        int indexOf = readLine.indexOf(35);
        if (indexOf >= 0) {
            readLine = readLine.substring(0, indexOf);
        }
        String trim = readLine.trim();
        int length = trim.length();
        if (length != 0) {
            if (trim.indexOf(32) >= 0 || trim.indexOf(9) >= 0) {
                fail(cls, url, i, "Illegal configuration-file syntax");
                throw null;
            }
            int codePointAt = trim.codePointAt(0);
            if (Character.isJavaIdentifierStart(codePointAt)) {
                int charCount = Character.charCount(codePointAt);
                while (charCount < length) {
                    int codePointAt2 = trim.codePointAt(charCount);
                    if (Character.isJavaIdentifierPart(codePointAt2) || codePointAt2 == 46) {
                        charCount += Character.charCount(codePointAt2);
                    } else {
                        fail(cls, url, i, "Illegal provider-class name: " + trim);
                        throw null;
                    }
                }
                if (!this.providers.containsKey(trim) && !list.contains(trim)) {
                    list.add(trim);
                }
            } else {
                fail(cls, url, i, "Illegal provider-class name: " + trim);
                throw null;
            }
        }
        return i + 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0050 A[SYNTHETIC, Splitter:B:34:0x0050] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0058 A[Catch:{ IOException -> 0x0054 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.Iterator parse(java.lang.Class r12, java.net.URL r13) {
        /*
            r11 = this;
            java.lang.String r0 = "Error closing configuration file"
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            r8 = 0
            java.io.InputStream r9 = r13.openStream()     // Catch:{ IOException -> 0x0044, all -> 0x0040 }
            java.io.BufferedReader r10 = new java.io.BufferedReader     // Catch:{ IOException -> 0x003d, all -> 0x003a }
            java.io.InputStreamReader r1 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x003d, all -> 0x003a }
            java.lang.String r2 = "utf-8"
            r1.<init>(r9, r2)     // Catch:{ IOException -> 0x003d, all -> 0x003a }
            r10.<init>(r1)     // Catch:{ IOException -> 0x003d, all -> 0x003a }
            r1 = 1
            r5 = r1
        L_0x001a:
            r1 = r11
            r2 = r12
            r3 = r13
            r4 = r10
            r6 = r7
            int r5 = r1.parseLine(r2, r3, r4, r5, r6)     // Catch:{ IOException -> 0x0038 }
            if (r5 < 0) goto L_0x0026
            goto L_0x001a
        L_0x0026:
            r10.close()     // Catch:{ IOException -> 0x0033 }
            if (r9 == 0) goto L_0x002e
            r9.close()     // Catch:{ IOException -> 0x0033 }
        L_0x002e:
            java.util.Iterator r11 = r7.iterator()
            return r11
        L_0x0033:
            r11 = move-exception
            fail(r12, r0, r11)
            throw r8
        L_0x0038:
            r11 = move-exception
            goto L_0x0047
        L_0x003a:
            r11 = move-exception
            r10 = r8
            goto L_0x004e
        L_0x003d:
            r11 = move-exception
            r10 = r8
            goto L_0x0047
        L_0x0040:
            r11 = move-exception
            r9 = r8
            r10 = r9
            goto L_0x004e
        L_0x0044:
            r11 = move-exception
            r9 = r8
            r10 = r9
        L_0x0047:
            java.lang.String r13 = "Error reading configuration file"
            fail(r12, r13, r11)     // Catch:{ all -> 0x004d }
            throw r8
        L_0x004d:
            r11 = move-exception
        L_0x004e:
            if (r10 == 0) goto L_0x0056
            r10.close()     // Catch:{ IOException -> 0x0054 }
            goto L_0x0056
        L_0x0054:
            r11 = move-exception
            goto L_0x005c
        L_0x0056:
            if (r9 == 0) goto L_0x0060
            r9.close()     // Catch:{ IOException -> 0x0054 }
            goto L_0x0060
        L_0x005c:
            fail(r12, r0, r11)
            throw r8
        L_0x0060:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.ServiceLoader.parse(java.lang.Class, java.net.URL):java.util.Iterator");
    }

    /* access modifiers changed from: private */
    public class LazyIterator implements Iterator {
        Enumeration configs;
        ClassLoader loader;
        String nextName;
        Iterator pending;
        Class service;

        private LazyIterator(Class cls, ClassLoader classLoader) {
            this.configs = null;
            this.pending = null;
            this.nextName = null;
            this.service = cls;
            this.loader = classLoader;
        }

        private boolean hasNextService() {
            if (this.nextName != null) {
                return true;
            }
            if (this.configs == null) {
                try {
                    String str = "META-INF/services/" + this.service.getName();
                    if (this.loader == null) {
                        this.configs = ClassLoader.getSystemResources(str);
                    } else {
                        this.configs = this.loader.getResources(str);
                    }
                } catch (IOException e) {
                    ServiceLoader.access$100(this.service, "Error locating configuration files", e);
                    throw null;
                }
            }
            while (true) {
                Iterator it = this.pending;
                if (it != null && it.hasNext()) {
                    this.nextName = (String) this.pending.next();
                    return true;
                } else if (!this.configs.hasMoreElements()) {
                    return false;
                } else {
                    this.pending = ServiceLoader.this.parse(this.service, (URL) this.configs.nextElement());
                }
            }
        }

        private Object nextService() {
            if (hasNextService()) {
                String str = this.nextName;
                this.nextName = null;
                try {
                    Class cls = Class.forName(str, false, this.loader);
                    if (this.service.isAssignableFrom(cls)) {
                        try {
                            Class cls2 = this.service;
                            Object newInstance = cls.newInstance();
                            cls2.cast(newInstance);
                            ServiceLoader.this.providers.put(str, newInstance);
                            return newInstance;
                        } catch (Throwable th) {
                            Class cls3 = this.service;
                            ServiceLoader.access$100(cls3, "Provider " + str + " could not be instantiated", th);
                            throw null;
                        }
                    } else {
                        ClassCastException classCastException = new ClassCastException(this.service.getCanonicalName() + " is not assignable from " + cls.getCanonicalName());
                        Class cls4 = this.service;
                        ServiceLoader.access$100(cls4, "Provider " + str + " not a subtype", classCastException);
                        throw null;
                    }
                } catch (ClassNotFoundException e) {
                    Class cls5 = this.service;
                    ServiceLoader.access$100(cls5, "Provider " + str + " not found", e);
                    throw null;
                }
            } else {
                throw new NoSuchElementException();
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return hasNextService();
        }

        @Override // java.util.Iterator
        public Object next() {
            return nextService();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    @Override // java.lang.Iterable
    public Iterator iterator() {
        return new Iterator() {
            /* class java.util.ServiceLoader.AnonymousClass1 */
            Iterator knownProviders = ServiceLoader.this.providers.entrySet().iterator();

            @Override // java.util.Iterator
            public boolean hasNext() {
                if (this.knownProviders.hasNext()) {
                    return true;
                }
                return ServiceLoader.this.lookupIterator.hasNext();
            }

            @Override // java.util.Iterator
            public Object next() {
                if (this.knownProviders.hasNext()) {
                    return ((Map.Entry) this.knownProviders.next()).getValue();
                }
                return ServiceLoader.this.lookupIterator.next();
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public static ServiceLoader load(Class cls, ClassLoader classLoader) {
        return new ServiceLoader(cls, classLoader);
    }

    public static ServiceLoader load(Class cls) {
        return load(cls, Thread.currentThread().getContextClassLoader());
    }

    public String toString() {
        return "java.util.ServiceLoader[" + this.service.getName() + "]";
    }
}
