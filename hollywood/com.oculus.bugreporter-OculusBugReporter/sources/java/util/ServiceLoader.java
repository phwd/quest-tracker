package java.util;

import android.icu.text.PluralRules;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

public final class ServiceLoader<S> implements Iterable<S> {
    private static final String PREFIX = "META-INF/services/";
    private final ClassLoader loader;
    private ServiceLoader<S>.LazyIterator lookupIterator;
    private LinkedHashMap<String, S> providers = new LinkedHashMap<>();
    private final Class<S> service;

    public void reload() {
        this.providers.clear();
        this.lookupIterator = new LazyIterator(this.service, this.loader);
    }

    private ServiceLoader(Class<S> svc, ClassLoader cl) {
        this.service = (Class) Objects.requireNonNull(svc, "Service interface cannot be null");
        this.loader = cl == null ? ClassLoader.getSystemClassLoader() : cl;
        reload();
    }

    /* access modifiers changed from: private */
    public static void fail(Class<?> service2, String msg, Throwable cause) throws ServiceConfigurationError {
        throw new ServiceConfigurationError(service2.getName() + PluralRules.KEYWORD_RULE_SEPARATOR + msg, cause);
    }

    private static void fail(Class<?> service2, String msg) throws ServiceConfigurationError {
        throw new ServiceConfigurationError(service2.getName() + PluralRules.KEYWORD_RULE_SEPARATOR + msg);
    }

    private static void fail(Class<?> service2, URL u, int line, String msg) throws ServiceConfigurationError {
        fail(service2, ((Object) u) + ":" + line + PluralRules.KEYWORD_RULE_SEPARATOR + msg);
    }

    private int parseLine(Class<?> service2, URL u, BufferedReader r, int lc, List<String> names) throws IOException, ServiceConfigurationError {
        String ln = r.readLine();
        if (ln == null) {
            return -1;
        }
        int ci = ln.indexOf(35);
        if (ci >= 0) {
            ln = ln.substring(0, ci);
        }
        String ln2 = ln.trim();
        int n = ln2.length();
        if (n != 0) {
            if (ln2.indexOf(32) >= 0 || ln2.indexOf(9) >= 0) {
                fail(service2, u, lc, "Illegal configuration-file syntax");
            }
            int cp = ln2.codePointAt(0);
            if (!Character.isJavaIdentifierStart(cp)) {
                fail(service2, u, lc, "Illegal provider-class name: " + ln2);
            }
            int i = Character.charCount(cp);
            while (i < n) {
                int cp2 = ln2.codePointAt(i);
                if (!Character.isJavaIdentifierPart(cp2) && cp2 != 46) {
                    fail(service2, u, lc, "Illegal provider-class name: " + ln2);
                }
                i += Character.charCount(cp2);
            }
            if (!this.providers.containsKey(ln2) && !names.contains(ln2)) {
                names.add(ln2);
            }
        }
        return lc + 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0049 A[SYNTHETIC, Splitter:B:26:0x0049] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0051 A[Catch:{ IOException -> 0x004d }] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0061 A[SYNTHETIC, Splitter:B:36:0x0061] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0069 A[Catch:{ IOException -> 0x0065 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.Iterator<java.lang.String> parse(java.lang.Class<?> r10, java.net.URL r11) throws java.util.ServiceConfigurationError {
        /*
        // Method dump skipped, instructions count: 115
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.ServiceLoader.parse(java.lang.Class, java.net.URL):java.util.Iterator");
    }

    /* access modifiers changed from: private */
    public class LazyIterator implements Iterator<S> {
        Enumeration<URL> configs;
        ClassLoader loader;
        String nextName;
        Iterator<String> pending;
        Class<S> service;

        private LazyIterator(Class<S> service2, ClassLoader loader2) {
            this.configs = null;
            this.pending = null;
            this.nextName = null;
            this.service = service2;
            this.loader = loader2;
        }

        private boolean hasNextService() {
            if (this.nextName != null) {
                return true;
            }
            if (this.configs == null) {
                try {
                    String fullName = ServiceLoader.PREFIX + this.service.getName();
                    if (this.loader == null) {
                        this.configs = ClassLoader.getSystemResources(fullName);
                    } else {
                        this.configs = this.loader.getResources(fullName);
                    }
                } catch (IOException x) {
                    ServiceLoader.fail(this.service, "Error locating configuration files", x);
                }
            }
            while (true) {
                Iterator<String> it = this.pending;
                if (it != null && it.hasNext()) {
                    this.nextName = this.pending.next();
                    return true;
                } else if (!this.configs.hasMoreElements()) {
                    return false;
                } else {
                    this.pending = ServiceLoader.this.parse(this.service, this.configs.nextElement());
                }
            }
        }

        private S nextService() {
            if (hasNextService()) {
                String cn = this.nextName;
                this.nextName = null;
                Class<?> c = null;
                try {
                    c = Class.forName(cn, false, this.loader);
                } catch (ClassNotFoundException x) {
                    Class<S> cls = this.service;
                    ServiceLoader.fail(cls, "Provider " + cn + " not found", x);
                }
                if (!this.service.isAssignableFrom(c)) {
                    ClassCastException cce = new ClassCastException(this.service.getCanonicalName() + " is not assignable from " + c.getCanonicalName());
                    Class<S> cls2 = this.service;
                    ServiceLoader.fail(cls2, "Provider " + cn + " not a subtype", cce);
                }
                try {
                    S p = this.service.cast(c.newInstance());
                    ServiceLoader.this.providers.put(cn, p);
                    return p;
                } catch (Throwable x2) {
                    Class<S> cls3 = this.service;
                    ServiceLoader.fail(cls3, "Provider " + cn + " could not be instantiated", x2);
                    throw new Error();
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
        public S next() {
            return (S) nextService();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    @Override // java.lang.Iterable
    public Iterator<S> iterator() {
        return new Iterator<S>() {
            /* class java.util.ServiceLoader.AnonymousClass1 */
            Iterator<Map.Entry<String, S>> knownProviders = ServiceLoader.this.providers.entrySet().iterator();

            @Override // java.util.Iterator
            public boolean hasNext() {
                if (this.knownProviders.hasNext()) {
                    return true;
                }
                return ServiceLoader.this.lookupIterator.hasNext();
            }

            @Override // java.util.Iterator
            public S next() {
                return this.knownProviders.hasNext() ? this.knownProviders.next().getValue() : (S) ServiceLoader.this.lookupIterator.next();
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public static <S> ServiceLoader<S> load(Class<S> service2, ClassLoader loader2) {
        return new ServiceLoader<>(service2, loader2);
    }

    public static <S> ServiceLoader<S> load(Class<S> service2) {
        return load(service2, Thread.currentThread().getContextClassLoader());
    }

    public static <S> ServiceLoader<S> loadInstalled(Class<S> service2) {
        ClassLoader prev = null;
        for (ClassLoader cl = ClassLoader.getSystemClassLoader(); cl != null; cl = cl.getParent()) {
            prev = cl;
        }
        return load(service2, prev);
    }

    public static <S> S loadFromSystemProperty(Class<S> service2) {
        try {
            String className = System.getProperty(service2.getName());
            if (className != null) {
                return (S) ClassLoader.getSystemClassLoader().loadClass(className).newInstance();
            }
            return null;
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    public String toString() {
        return "java.util.ServiceLoader[" + this.service.getName() + "]";
    }
}
