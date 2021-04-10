package java.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.jar.JarEntry;
import sun.reflect.CallerSensitive;
import sun.reflect.Reflection;
import sun.util.locale.BaseLocale;
import sun.util.locale.LocaleObjectCache;

public abstract class ResourceBundle {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int INITIAL_CACHE_SIZE = 32;
    private static final ResourceBundle NONEXISTENT_BUNDLE = new ResourceBundle() {
        /* class java.util.ResourceBundle.AnonymousClass1 */

        @Override // java.util.ResourceBundle
        public Enumeration<String> getKeys() {
            return null;
        }

        /* access modifiers changed from: protected */
        @Override // java.util.ResourceBundle
        public Object handleGetObject(String key) {
            return null;
        }

        public String toString() {
            return "NONEXISTENT_BUNDLE";
        }
    };
    private static final ConcurrentMap<CacheKey, BundleReference> cacheList = new ConcurrentHashMap(32);
    private static final ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
    private volatile CacheKey cacheKey;
    private volatile boolean expired;
    private volatile Set<String> keySet;
    private Locale locale = null;
    private String name;
    protected ResourceBundle parent = null;

    /* access modifiers changed from: private */
    public interface CacheKeyReference {
        CacheKey getCacheKey();
    }

    public abstract Enumeration<String> getKeys();

    /* access modifiers changed from: protected */
    public abstract Object handleGetObject(String str);

    public String getBaseBundleName() {
        return this.name;
    }

    public final String getString(String key) {
        return (String) getObject(key);
    }

    public final String[] getStringArray(String key) {
        return (String[]) getObject(key);
    }

    public final Object getObject(String key) {
        Object obj = handleGetObject(key);
        if (obj == null) {
            ResourceBundle resourceBundle = this.parent;
            if (resourceBundle != null) {
                obj = resourceBundle.getObject(key);
            }
            if (obj == null) {
                throw new MissingResourceException("Can't find resource for bundle " + getClass().getName() + ", key " + key, getClass().getName(), key);
            }
        }
        return obj;
    }

    public Locale getLocale() {
        return this.locale;
    }

    private static ClassLoader getLoader(Class<?> caller) {
        ClassLoader cl = caller == null ? null : caller.getClassLoader();
        if (cl == null) {
            return RBClassLoader.INSTANCE;
        }
        return cl;
    }

    /* access modifiers changed from: private */
    public static class RBClassLoader extends ClassLoader {
        private static final RBClassLoader INSTANCE = ((RBClassLoader) AccessController.doPrivileged(new PrivilegedAction<RBClassLoader>() {
            /* class java.util.ResourceBundle.RBClassLoader.AnonymousClass1 */

            @Override // java.security.PrivilegedAction
            public RBClassLoader run() {
                return new RBClassLoader();
            }
        }));
        private static final ClassLoader loader = ClassLoader.getSystemClassLoader();

        private RBClassLoader() {
        }

        @Override // java.lang.ClassLoader
        public Class<?> loadClass(String name) throws ClassNotFoundException {
            ClassLoader classLoader = loader;
            if (classLoader != null) {
                return classLoader.loadClass(name);
            }
            return Class.forName(name);
        }

        @Override // java.lang.ClassLoader
        public URL getResource(String name) {
            ClassLoader classLoader = loader;
            if (classLoader != null) {
                return classLoader.getResource(name);
            }
            return ClassLoader.getSystemResource(name);
        }

        @Override // java.lang.ClassLoader
        public InputStream getResourceAsStream(String name) {
            ClassLoader classLoader = loader;
            if (classLoader != null) {
                return classLoader.getResourceAsStream(name);
            }
            return ClassLoader.getSystemResourceAsStream(name);
        }
    }

    /* access modifiers changed from: protected */
    public void setParent(ResourceBundle parent2) {
        this.parent = parent2;
    }

    /* access modifiers changed from: private */
    public static class CacheKey implements Cloneable {
        private Throwable cause;
        private volatile long expirationTime;
        private String format;
        private int hashCodeCache;
        private volatile long loadTime;
        private LoaderReference loaderRef;
        private Locale locale;
        private String name;

        CacheKey(String baseName, Locale locale2, ClassLoader loader) {
            this.name = baseName;
            this.locale = locale2;
            if (loader == null) {
                this.loaderRef = null;
            } else {
                this.loaderRef = new LoaderReference(loader, ResourceBundle.referenceQueue, this);
            }
            calculateHashCode();
        }

        /* access modifiers changed from: package-private */
        public String getName() {
            return this.name;
        }

        /* access modifiers changed from: package-private */
        public CacheKey setName(String baseName) {
            if (!this.name.equals(baseName)) {
                this.name = baseName;
                calculateHashCode();
            }
            return this;
        }

        /* access modifiers changed from: package-private */
        public Locale getLocale() {
            return this.locale;
        }

        /* access modifiers changed from: package-private */
        public CacheKey setLocale(Locale locale2) {
            if (!this.locale.equals(locale2)) {
                this.locale = locale2;
                calculateHashCode();
            }
            return this;
        }

        /* access modifiers changed from: package-private */
        public ClassLoader getLoader() {
            LoaderReference loaderReference = this.loaderRef;
            if (loaderReference != null) {
                return (ClassLoader) loaderReference.get();
            }
            return null;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            try {
                CacheKey otherEntry = (CacheKey) other;
                if (this.hashCodeCache != otherEntry.hashCodeCache || !this.name.equals(otherEntry.name) || !this.locale.equals(otherEntry.locale)) {
                    return false;
                }
                if (this.loaderRef != null) {
                    ClassLoader loader = (ClassLoader) this.loaderRef.get();
                    LoaderReference loaderReference = otherEntry.loaderRef;
                    if (loaderReference == null || loader == null || loader != loaderReference.get()) {
                        return false;
                    }
                    return true;
                } else if (otherEntry.loaderRef == null) {
                    return true;
                } else {
                    return false;
                }
            } catch (ClassCastException | NullPointerException e) {
                return false;
            }
        }

        public int hashCode() {
            return this.hashCodeCache;
        }

        private void calculateHashCode() {
            this.hashCodeCache = this.name.hashCode() << 3;
            this.hashCodeCache ^= this.locale.hashCode();
            ClassLoader loader = getLoader();
            if (loader != null) {
                this.hashCodeCache ^= loader.hashCode();
            }
        }

        public Object clone() {
            try {
                CacheKey clone = (CacheKey) super.clone();
                if (this.loaderRef != null) {
                    clone.loaderRef = new LoaderReference((ClassLoader) this.loaderRef.get(), ResourceBundle.referenceQueue, clone);
                }
                clone.cause = null;
                return clone;
            } catch (CloneNotSupportedException e) {
                throw new InternalError(e);
            }
        }

        /* access modifiers changed from: package-private */
        public String getFormat() {
            return this.format;
        }

        /* access modifiers changed from: package-private */
        public void setFormat(String format2) {
            this.format = format2;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setCause(Throwable cause2) {
            Throwable th = this.cause;
            if (th == null) {
                this.cause = cause2;
            } else if (th instanceof ClassNotFoundException) {
                this.cause = cause2;
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private Throwable getCause() {
            return this.cause;
        }

        public String toString() {
            String l = this.locale.toString();
            if (l.length() == 0) {
                if (this.locale.getVariant().length() != 0) {
                    l = "__" + this.locale.getVariant();
                } else {
                    l = "\"\"";
                }
            }
            return "CacheKey[" + this.name + ", lc=" + l + ", ldr=" + ((Object) getLoader()) + "(format=" + this.format + ")]";
        }
    }

    /* access modifiers changed from: private */
    public static class LoaderReference extends WeakReference<ClassLoader> implements CacheKeyReference {
        private CacheKey cacheKey;

        LoaderReference(ClassLoader referent, ReferenceQueue<Object> q, CacheKey key) {
            super(referent, q);
            this.cacheKey = key;
        }

        @Override // java.util.ResourceBundle.CacheKeyReference
        public CacheKey getCacheKey() {
            return this.cacheKey;
        }
    }

    /* access modifiers changed from: private */
    public static class BundleReference extends SoftReference<ResourceBundle> implements CacheKeyReference {
        private CacheKey cacheKey;

        BundleReference(ResourceBundle referent, ReferenceQueue<Object> q, CacheKey key) {
            super(referent, q);
            this.cacheKey = key;
        }

        @Override // java.util.ResourceBundle.CacheKeyReference
        public CacheKey getCacheKey() {
            return this.cacheKey;
        }
    }

    @CallerSensitive
    public static final ResourceBundle getBundle(String baseName) {
        return getBundleImpl(baseName, Locale.getDefault(), getLoader(Reflection.getCallerClass()), getDefaultControl(baseName));
    }

    @CallerSensitive
    public static final ResourceBundle getBundle(String baseName, Control control) {
        return getBundleImpl(baseName, Locale.getDefault(), getLoader(Reflection.getCallerClass()), control);
    }

    @CallerSensitive
    public static final ResourceBundle getBundle(String baseName, Locale locale2) {
        return getBundleImpl(baseName, locale2, getLoader(Reflection.getCallerClass()), getDefaultControl(baseName));
    }

    @CallerSensitive
    public static final ResourceBundle getBundle(String baseName, Locale targetLocale, Control control) {
        return getBundleImpl(baseName, targetLocale, getLoader(Reflection.getCallerClass()), control);
    }

    public static ResourceBundle getBundle(String baseName, Locale locale2, ClassLoader loader) {
        if (loader != null) {
            return getBundleImpl(baseName, locale2, loader, getDefaultControl(baseName));
        }
        throw new NullPointerException();
    }

    public static ResourceBundle getBundle(String baseName, Locale targetLocale, ClassLoader loader, Control control) {
        if (loader != null && control != null) {
            return getBundleImpl(baseName, targetLocale, loader, control);
        }
        throw new NullPointerException();
    }

    private static Control getDefaultControl(String baseName) {
        return Control.INSTANCE;
    }

    private static ResourceBundle getBundleImpl(String baseName, Locale locale2, ClassLoader loader, Control control) {
        ResourceBundle bundle;
        if (locale2 == null || control == null) {
            throw new NullPointerException();
        }
        CacheKey cacheKey2 = new CacheKey(baseName, locale2, loader);
        ResourceBundle bundle2 = null;
        BundleReference bundleRef = cacheList.get(cacheKey2);
        if (bundleRef != null) {
            bundle2 = (ResourceBundle) bundleRef.get();
        }
        if (isValidBundle(bundle2) && hasValidParentChain(bundle2)) {
            return bundle2;
        }
        boolean isKnownControl = control == Control.INSTANCE || (control instanceof SingleFormatControl);
        List<String> formats = control.getFormats(baseName);
        if (isKnownControl || checkList(formats)) {
            ResourceBundle bundle3 = bundle2;
            ResourceBundle baseBundle = null;
            Locale targetLocale = locale2;
            while (true) {
                if (targetLocale == null) {
                    break;
                }
                List<Locale> candidateLocales = control.getCandidateLocales(baseName, targetLocale);
                if (isKnownControl || checkList(candidateLocales)) {
                    bundle = findBundle(cacheKey2, candidateLocales, formats, 0, control, baseBundle);
                    if (isValidBundle(bundle)) {
                        boolean isBaseBundle = Locale.ROOT.equals(bundle.locale);
                        if (isBaseBundle && !bundle.locale.equals(locale2)) {
                            if (candidateLocales.size() == 1) {
                                if (bundle.locale.equals(candidateLocales.get(0))) {
                                    break;
                                }
                            }
                            if (isBaseBundle && baseBundle == null) {
                                baseBundle = bundle;
                            }
                        }
                    }
                    targetLocale = control.getFallbackLocale(baseName, targetLocale);
                    bundle3 = bundle;
                } else {
                    throw new IllegalArgumentException("Invalid Control: getCandidateLocales");
                }
            }
            bundle3 = bundle;
            if (bundle3 != null) {
                return bundle3;
            }
            if (baseBundle == null) {
                throwMissingResourceException(baseName, locale2, cacheKey2.getCause());
            }
            return baseBundle;
        }
        throw new IllegalArgumentException("Invalid Control: getFormats");
    }

    private static boolean checkList(List<?> a) {
        boolean valid = a != null && !a.isEmpty();
        if (valid) {
            int size = a.size();
            int i = 0;
            while (valid && i < size) {
                valid = a.get(i) != null;
                i++;
            }
        }
        return valid;
    }

    private static ResourceBundle findBundle(CacheKey cacheKey2, List<Locale> candidateLocales, List<String> formats, int index, Control control, ResourceBundle baseBundle) {
        Locale targetLocale = candidateLocales.get(index);
        ResourceBundle parent2 = null;
        if (index != candidateLocales.size() - 1) {
            parent2 = findBundle(cacheKey2, candidateLocales, formats, index + 1, control, baseBundle);
        } else if (baseBundle != null && Locale.ROOT.equals(targetLocale)) {
            return baseBundle;
        }
        while (true) {
            Object ref = referenceQueue.poll();
            if (ref == null) {
                break;
            }
            cacheList.remove(((CacheKeyReference) ref).getCacheKey());
        }
        boolean expiredBundle = false;
        cacheKey2.setLocale(targetLocale);
        ResourceBundle bundle = findBundleInCache(cacheKey2, control);
        if (isValidBundle(bundle) && !(expiredBundle = bundle.expired)) {
            if (bundle.parent == parent2) {
                return bundle;
            }
            BundleReference bundleRef = cacheList.get(cacheKey2);
            if (bundleRef != null && bundleRef.get() == bundle) {
                cacheList.remove(cacheKey2, bundleRef);
            }
        }
        if (bundle != NONEXISTENT_BUNDLE) {
            CacheKey constKey = (CacheKey) cacheKey2.clone();
            try {
                ResourceBundle bundle2 = loadBundle(cacheKey2, formats, control, expiredBundle);
                if (bundle2 != null) {
                    if (bundle2.parent == null) {
                        bundle2.setParent(parent2);
                    }
                    bundle2.locale = targetLocale;
                    return putBundleInCache(cacheKey2, bundle2, control);
                }
                putBundleInCache(cacheKey2, NONEXISTENT_BUNDLE, control);
                if (constKey.getCause() instanceof InterruptedException) {
                    Thread.currentThread().interrupt();
                }
            } finally {
                if (constKey.getCause() instanceof InterruptedException) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        return parent2;
    }

    private static ResourceBundle loadBundle(CacheKey cacheKey2, List<String> formats, Control control, boolean reload) {
        Locale targetLocale = cacheKey2.getLocale();
        int size = formats.size();
        ResourceBundle bundle = null;
        int i = 0;
        while (true) {
            if (i >= size) {
                break;
            }
            String format = formats.get(i);
            try {
                bundle = control.newBundle(cacheKey2.getName(), targetLocale, format, cacheKey2.getLoader(), reload);
            } catch (LinkageError error) {
                cacheKey2.setCause(error);
            } catch (Exception cause) {
                cacheKey2.setCause(cause);
            }
            if (bundle != null) {
                cacheKey2.setFormat(format);
                bundle.name = cacheKey2.getName();
                bundle.locale = targetLocale;
                bundle.expired = false;
                break;
            }
            i++;
        }
        return bundle;
    }

    private static boolean isValidBundle(ResourceBundle bundle) {
        return (bundle == null || bundle == NONEXISTENT_BUNDLE) ? false : true;
    }

    private static boolean hasValidParentChain(ResourceBundle bundle) {
        long now = System.currentTimeMillis();
        while (bundle != null) {
            if (bundle.expired) {
                return false;
            }
            CacheKey key = bundle.cacheKey;
            if (key != null) {
                long expirationTime = key.expirationTime;
                if (expirationTime >= 0 && expirationTime <= now) {
                    return false;
                }
            }
            bundle = bundle.parent;
        }
        return true;
    }

    private static void throwMissingResourceException(String baseName, Locale locale2, Throwable cause) {
        if (cause instanceof MissingResourceException) {
            cause = null;
        }
        throw new MissingResourceException("Can't find bundle for base name " + baseName + ", locale " + ((Object) locale2), baseName + "_" + ((Object) locale2), "", cause);
    }

    private static ResourceBundle findBundleInCache(CacheKey cacheKey2, Control control) {
        ResourceBundle bundle;
        Throwable th;
        BundleReference bundleRef = cacheList.get(cacheKey2);
        if (bundleRef == null || (bundle = (ResourceBundle) bundleRef.get()) == null) {
            return null;
        }
        ResourceBundle p = bundle.parent;
        if (p == null || !p.expired) {
            CacheKey key = bundleRef.getCacheKey();
            long expirationTime = key.expirationTime;
            if (bundle.expired || expirationTime < 0) {
                return bundle;
            }
            if (expirationTime > System.currentTimeMillis()) {
                return bundle;
            }
            if (bundle != NONEXISTENT_BUNDLE) {
                synchronized (bundle) {
                    try {
                        long expirationTime2 = key.expirationTime;
                        try {
                            if (!bundle.expired && expirationTime2 >= 0) {
                                if (expirationTime2 <= System.currentTimeMillis()) {
                                    try {
                                        bundle.expired = control.needsReload(key.getName(), key.getLocale(), key.getFormat(), key.getLoader(), bundle, key.loadTime);
                                    } catch (Exception e) {
                                        cacheKey2.setCause(e);
                                    }
                                    if (bundle.expired) {
                                        bundle.cacheKey = null;
                                        cacheList.remove(cacheKey2, bundleRef);
                                    } else {
                                        try {
                                            setExpirationTime(key, control);
                                        } catch (Throwable th2) {
                                            th = th2;
                                            while (true) {
                                                try {
                                                    break;
                                                } catch (Throwable th3) {
                                                    th = th3;
                                                }
                                            }
                                            throw th;
                                        }
                                    }
                                }
                            }
                            return bundle;
                        } catch (Throwable th4) {
                            th = th4;
                            while (true) {
                                break;
                            }
                            throw th;
                        }
                    } catch (Throwable th5) {
                        th = th5;
                        while (true) {
                            break;
                        }
                        throw th;
                    }
                }
            } else {
                cacheList.remove(cacheKey2, bundleRef);
                return null;
            }
        } else {
            bundle.expired = true;
            bundle.cacheKey = null;
            cacheList.remove(cacheKey2, bundleRef);
            return null;
        }
    }

    private static ResourceBundle putBundleInCache(CacheKey cacheKey2, ResourceBundle bundle, Control control) {
        setExpirationTime(cacheKey2, control);
        if (cacheKey2.expirationTime == -1) {
            return bundle;
        }
        CacheKey key = (CacheKey) cacheKey2.clone();
        BundleReference bundleRef = new BundleReference(bundle, referenceQueue, key);
        bundle.cacheKey = key;
        BundleReference result = cacheList.putIfAbsent(key, bundleRef);
        if (result == null) {
            return bundle;
        }
        ResourceBundle rb = (ResourceBundle) result.get();
        if (rb == null || rb.expired) {
            cacheList.put(key, bundleRef);
            return bundle;
        }
        bundle.cacheKey = null;
        bundleRef.clear();
        return rb;
    }

    private static void setExpirationTime(CacheKey cacheKey2, Control control) {
        long ttl = control.getTimeToLive(cacheKey2.getName(), cacheKey2.getLocale());
        if (ttl >= 0) {
            long now = System.currentTimeMillis();
            cacheKey2.loadTime = now;
            cacheKey2.expirationTime = now + ttl;
        } else if (ttl >= -2) {
            cacheKey2.expirationTime = ttl;
        } else {
            throw new IllegalArgumentException("Invalid Control: TTL=" + ttl);
        }
    }

    @CallerSensitive
    public static final void clearCache() {
        clearCache(getLoader(Reflection.getCallerClass()));
    }

    public static final void clearCache(ClassLoader loader) {
        if (loader != null) {
            Set<CacheKey> set = cacheList.keySet();
            for (CacheKey key : set) {
                if (key.getLoader() == loader) {
                    set.remove(key);
                }
            }
            return;
        }
        throw new NullPointerException();
    }

    public boolean containsKey(String key) {
        if (key != null) {
            for (ResourceBundle rb = this; rb != null; rb = rb.parent) {
                if (rb.handleKeySet().contains(key)) {
                    return true;
                }
            }
            return false;
        }
        throw new NullPointerException();
    }

    public Set<String> keySet() {
        Set<String> keys = new HashSet<>();
        for (ResourceBundle rb = this; rb != null; rb = rb.parent) {
            keys.addAll(rb.handleKeySet());
        }
        return keys;
    }

    /* access modifiers changed from: protected */
    public Set<String> handleKeySet() {
        if (this.keySet == null) {
            synchronized (this) {
                if (this.keySet == null) {
                    Set<String> keys = new HashSet<>();
                    Enumeration<String> enumKeys = getKeys();
                    while (enumKeys.hasMoreElements()) {
                        String key = enumKeys.nextElement();
                        if (handleGetObject(key) != null) {
                            keys.add(key);
                        }
                    }
                    this.keySet = keys;
                }
            }
        }
        return this.keySet;
    }

    public static class Control {
        private static final CandidateListCache CANDIDATES_CACHE = new CandidateListCache();
        public static final List<String> FORMAT_CLASS = Collections.unmodifiableList(Arrays.asList("java.class"));
        public static final List<String> FORMAT_DEFAULT = Collections.unmodifiableList(Arrays.asList("java.class", "java.properties"));
        public static final List<String> FORMAT_PROPERTIES = Collections.unmodifiableList(Arrays.asList("java.properties"));
        private static final Control INSTANCE = new Control();
        public static final long TTL_DONT_CACHE = -1;
        public static final long TTL_NO_EXPIRATION_CONTROL = -2;

        protected Control() {
        }

        public static final Control getControl(List<String> formats) {
            if (formats.equals(FORMAT_PROPERTIES)) {
                return SingleFormatControl.PROPERTIES_ONLY;
            }
            if (formats.equals(FORMAT_CLASS)) {
                return SingleFormatControl.CLASS_ONLY;
            }
            if (formats.equals(FORMAT_DEFAULT)) {
                return INSTANCE;
            }
            throw new IllegalArgumentException();
        }

        public static final Control getNoFallbackControl(List<String> formats) {
            if (formats.equals(FORMAT_DEFAULT)) {
                return NoFallbackControl.NO_FALLBACK;
            }
            if (formats.equals(FORMAT_PROPERTIES)) {
                return NoFallbackControl.PROPERTIES_ONLY_NO_FALLBACK;
            }
            if (formats.equals(FORMAT_CLASS)) {
                return NoFallbackControl.CLASS_ONLY_NO_FALLBACK;
            }
            throw new IllegalArgumentException();
        }

        public List<String> getFormats(String baseName) {
            if (baseName != null) {
                return FORMAT_DEFAULT;
            }
            throw new NullPointerException();
        }

        public List<Locale> getCandidateLocales(String baseName, Locale locale) {
            if (baseName != null) {
                return new ArrayList((Collection) CANDIDATES_CACHE.get(locale.getBaseLocale()));
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public static class CandidateListCache extends LocaleObjectCache<BaseLocale, List<Locale>> {
            private CandidateListCache() {
            }

            /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
            /* access modifiers changed from: protected */
            /* JADX WARNING: Code restructure failed: missing block: B:31:0x0080, code lost:
                if (r2.equals("TW") == false) goto L_0x00ab;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:62:0x00e2, code lost:
                if (r1.equals("Hans") != false) goto L_0x00e6;
             */
            /* JADX WARNING: Removed duplicated region for block: B:46:0x00ae A[ADDED_TO_REGION] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public java.util.List<java.util.Locale> createObject(sun.util.locale.BaseLocale r16) {
                /*
                // Method dump skipped, instructions count: 354
                */
                throw new UnsupportedOperationException("Method not decompiled: java.util.ResourceBundle.Control.CandidateListCache.createObject(sun.util.locale.BaseLocale):java.util.List");
            }

            private static List<Locale> getDefaultList(String language, String script, String region, String variant) {
                List<String> variants = null;
                if (variant.length() > 0) {
                    variants = new LinkedList<>();
                    int idx = variant.length();
                    while (idx != -1) {
                        variants.add(variant.substring(0, idx));
                        idx = variant.lastIndexOf(95, idx - 1);
                    }
                }
                List<Locale> list = new LinkedList<>();
                if (variants != null) {
                    for (String v : variants) {
                        list.add(Locale.getInstance(language, script, region, v, null));
                    }
                }
                if (region.length() > 0) {
                    list.add(Locale.getInstance(language, script, region, "", null));
                }
                if (script.length() > 0) {
                    list.add(Locale.getInstance(language, script, "", "", null));
                    if (variants != null) {
                        for (String v2 : variants) {
                            list.add(Locale.getInstance(language, "", region, v2, null));
                        }
                    }
                    if (region.length() > 0) {
                        list.add(Locale.getInstance(language, "", region, "", null));
                    }
                }
                if (language.length() > 0) {
                    list.add(Locale.getInstance(language, "", "", "", null));
                }
                list.add(Locale.ROOT);
                return list;
            }
        }

        public Locale getFallbackLocale(String baseName, Locale locale) {
            if (baseName != null) {
                Locale defaultLocale = Locale.getDefault();
                if (locale.equals(defaultLocale)) {
                    return null;
                }
                return defaultLocale;
            }
            throw new NullPointerException();
        }

        public ResourceBundle newBundle(String baseName, Locale locale, String format, final ClassLoader loader, final boolean reload) throws IllegalAccessException, InstantiationException, IOException {
            String bundleName = toBundleName(baseName, locale);
            if (format.equals("java.class")) {
                try {
                    Class<?> loadClass = loader.loadClass(bundleName);
                    if (ResourceBundle.class.isAssignableFrom(loadClass)) {
                        return (ResourceBundle) loadClass.newInstance();
                    }
                    throw new ClassCastException(loadClass.getName() + " cannot be cast to ResourceBundle");
                } catch (ClassNotFoundException e) {
                    return null;
                }
            } else if (format.equals("java.properties")) {
                final String resourceName = toResourceName0(bundleName, "properties");
                if (resourceName == null) {
                    return null;
                }
                try {
                    InputStream stream = (InputStream) AccessController.doPrivileged(new PrivilegedExceptionAction<InputStream>() {
                        /* class java.util.ResourceBundle.Control.AnonymousClass1 */

                        @Override // java.security.PrivilegedExceptionAction
                        public InputStream run() throws IOException {
                            URLConnection connection;
                            if (!reload) {
                                return loader.getResourceAsStream(resourceName);
                            }
                            URL url = loader.getResource(resourceName);
                            if (url == null || (connection = url.openConnection()) == null) {
                                return null;
                            }
                            connection.setUseCaches(false);
                            return connection.getInputStream();
                        }
                    });
                    if (stream == null) {
                        return null;
                    }
                    try {
                        return new PropertyResourceBundle(new InputStreamReader(stream, StandardCharsets.UTF_8));
                    } finally {
                        stream.close();
                    }
                } catch (PrivilegedActionException e2) {
                    throw ((IOException) e2.getException());
                }
            } else {
                throw new IllegalArgumentException("unknown format: " + format);
            }
        }

        public long getTimeToLive(String baseName, Locale locale) {
            if (baseName != null && locale != null) {
                return -2;
            }
            throw new NullPointerException();
        }

        public boolean needsReload(String baseName, Locale locale, String format, ClassLoader loader, ResourceBundle bundle, long loadTime) {
            String format2;
            NullPointerException npe;
            if (bundle != null) {
                if (format.equals("java.class") || format.equals("java.properties")) {
                    format2 = format.substring(5);
                } else {
                    format2 = format;
                }
                try {
                    try {
                        String resourceName = toResourceName0(toBundleName(baseName, locale), format2);
                        if (resourceName == null) {
                            return false;
                        }
                        try {
                            URL url = loader.getResource(resourceName);
                            if (url == null) {
                                return false;
                            }
                            long lastModified = 0;
                            URLConnection connection = url.openConnection();
                            boolean result = false;
                            if (connection != null) {
                                connection.setUseCaches(false);
                                if (connection instanceof JarURLConnection) {
                                    JarEntry ent = ((JarURLConnection) connection).getJarEntry();
                                    if (ent != null) {
                                        lastModified = ent.getTime();
                                        if (lastModified == -1) {
                                            lastModified = 0;
                                        }
                                    }
                                } else {
                                    lastModified = connection.getLastModified();
                                }
                            }
                            if (lastModified >= loadTime) {
                                result = true;
                            }
                            return result;
                        } catch (NullPointerException npe2) {
                            throw npe2;
                        } catch (Exception e) {
                            return false;
                        }
                    } catch (NullPointerException e2) {
                        npe = e2;
                        throw npe;
                    } catch (Exception e3) {
                        return false;
                    }
                } catch (NullPointerException e4) {
                    npe = e4;
                    throw npe;
                } catch (Exception e5) {
                    return false;
                }
            } else {
                throw new NullPointerException();
            }
        }

        public String toBundleName(String baseName, Locale locale) {
            if (locale == Locale.ROOT) {
                return baseName;
            }
            String language = locale.getLanguage();
            String script = locale.getScript();
            String country = locale.getCountry();
            String variant = locale.getVariant();
            if (language == "" && country == "" && variant == "") {
                return baseName;
            }
            StringBuilder sb = new StringBuilder(baseName);
            sb.append('_');
            if (script != "") {
                if (variant != "") {
                    sb.append(language);
                    sb.append('_');
                    sb.append(script);
                    sb.append('_');
                    sb.append(country);
                    sb.append('_');
                    sb.append(variant);
                } else if (country != "") {
                    sb.append(language);
                    sb.append('_');
                    sb.append(script);
                    sb.append('_');
                    sb.append(country);
                } else {
                    sb.append(language);
                    sb.append('_');
                    sb.append(script);
                }
            } else if (variant != "") {
                sb.append(language);
                sb.append('_');
                sb.append(country);
                sb.append('_');
                sb.append(variant);
            } else if (country != "") {
                sb.append(language);
                sb.append('_');
                sb.append(country);
            } else {
                sb.append(language);
            }
            return sb.toString();
        }

        public final String toResourceName(String bundleName, String suffix) {
            StringBuilder sb = new StringBuilder(bundleName.length() + 1 + suffix.length());
            sb.append(bundleName.replace('.', '/'));
            sb.append('.');
            sb.append(suffix);
            return sb.toString();
        }

        private String toResourceName0(String bundleName, String suffix) {
            if (bundleName.contains("://")) {
                return null;
            }
            return toResourceName(bundleName, suffix);
        }
    }

    /* access modifiers changed from: private */
    public static class SingleFormatControl extends Control {
        private static final Control CLASS_ONLY = new SingleFormatControl(FORMAT_CLASS);
        private static final Control PROPERTIES_ONLY = new SingleFormatControl(FORMAT_PROPERTIES);
        private final List<String> formats;

        protected SingleFormatControl(List<String> formats2) {
            this.formats = formats2;
        }

        @Override // java.util.ResourceBundle.Control
        public List<String> getFormats(String baseName) {
            if (baseName != null) {
                return this.formats;
            }
            throw new NullPointerException();
        }
    }

    private static final class NoFallbackControl extends SingleFormatControl {
        private static final Control CLASS_ONLY_NO_FALLBACK = new NoFallbackControl(FORMAT_CLASS);
        private static final Control NO_FALLBACK = new NoFallbackControl(FORMAT_DEFAULT);
        private static final Control PROPERTIES_ONLY_NO_FALLBACK = new NoFallbackControl(FORMAT_PROPERTIES);

        protected NoFallbackControl(List<String> formats) {
            super(formats);
        }

        @Override // java.util.ResourceBundle.Control
        public Locale getFallbackLocale(String baseName, Locale locale) {
            if (baseName != null && locale != null) {
                return null;
            }
            throw new NullPointerException();
        }
    }
}
