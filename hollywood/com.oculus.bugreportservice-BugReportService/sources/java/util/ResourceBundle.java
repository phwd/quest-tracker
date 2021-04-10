package java.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.jar.JarEntry;
import sun.util.locale.BaseLocale;
import sun.util.locale.LocaleObjectCache;

public abstract class ResourceBundle {
    private static final ResourceBundle NONEXISTENT_BUNDLE = new ResourceBundle() {
        /* class java.util.ResourceBundle.AnonymousClass1 */

        @Override // java.util.ResourceBundle
        public Enumeration getKeys() {
            return null;
        }

        /* access modifiers changed from: protected */
        @Override // java.util.ResourceBundle
        public Object handleGetObject(String str) {
            return null;
        }

        public String toString() {
            return "NONEXISTENT_BUNDLE";
        }
    };
    private static final ConcurrentMap cacheList = new ConcurrentHashMap(32);
    private static final ReferenceQueue referenceQueue = new ReferenceQueue();
    private volatile CacheKey cacheKey;
    private volatile boolean expired;
    private Locale locale = null;
    private String name;
    protected ResourceBundle parent = null;

    /* access modifiers changed from: private */
    public interface CacheKeyReference {
        CacheKey getCacheKey();
    }

    public abstract Enumeration getKeys();

    /* access modifiers changed from: protected */
    public abstract Object handleGetObject(String str);

    public final String getString(String str) {
        return (String) getObject(str);
    }

    public final String[] getStringArray(String str) {
        return (String[]) getObject(str);
    }

    public final Object getObject(String str) {
        Object handleGetObject = handleGetObject(str);
        if (handleGetObject == null) {
            ResourceBundle resourceBundle = this.parent;
            if (resourceBundle != null) {
                handleGetObject = resourceBundle.getObject(str);
            }
            if (handleGetObject == null) {
                throw new MissingResourceException("Can't find resource for bundle " + getClass().getName() + ", key " + str, getClass().getName(), str);
            }
        }
        return handleGetObject;
    }

    public Locale getLocale() {
        return this.locale;
    }

    /* access modifiers changed from: protected */
    public void setParent(ResourceBundle resourceBundle) {
        this.parent = resourceBundle;
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

        CacheKey(String str, Locale locale2, ClassLoader classLoader) {
            this.name = str;
            this.locale = locale2;
            if (classLoader == null) {
                this.loaderRef = null;
            } else {
                this.loaderRef = new LoaderReference(classLoader, ResourceBundle.referenceQueue, this);
            }
            calculateHashCode();
        }

        /* access modifiers changed from: package-private */
        public String getName() {
            return this.name;
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

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            try {
                CacheKey cacheKey = (CacheKey) obj;
                if (this.hashCodeCache != cacheKey.hashCodeCache || !this.name.equals(cacheKey.name) || !this.locale.equals(cacheKey.locale)) {
                    return false;
                }
                if (this.loaderRef == null) {
                    return cacheKey.loaderRef == null;
                }
                ClassLoader classLoader = (ClassLoader) this.loaderRef.get();
                return (cacheKey.loaderRef == null || classLoader == null || classLoader != cacheKey.loaderRef.get()) ? false : true;
            } catch (ClassCastException | NullPointerException unused) {
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
                this.hashCodeCache = loader.hashCode() ^ this.hashCodeCache;
            }
        }

        public Object clone() {
            try {
                CacheKey cacheKey = (CacheKey) super.clone();
                if (this.loaderRef != null) {
                    cacheKey.loaderRef = new LoaderReference((ClassLoader) this.loaderRef.get(), ResourceBundle.referenceQueue, cacheKey);
                }
                cacheKey.cause = null;
                return cacheKey;
            } catch (CloneNotSupportedException e) {
                throw new InternalError(e);
            }
        }

        /* access modifiers changed from: package-private */
        public String getFormat() {
            return this.format;
        }

        /* access modifiers changed from: package-private */
        public void setFormat(String str) {
            this.format = str;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void setCause(Throwable th) {
            Throwable th2 = this.cause;
            if (th2 == null) {
                this.cause = th;
            } else if (th2 instanceof ClassNotFoundException) {
                this.cause = th;
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private Throwable getCause() {
            return this.cause;
        }

        public String toString() {
            String locale2 = this.locale.toString();
            if (locale2.length() == 0) {
                if (this.locale.getVariant().length() != 0) {
                    locale2 = "__" + this.locale.getVariant();
                } else {
                    locale2 = "\"\"";
                }
            }
            return "CacheKey[" + this.name + ", lc=" + locale2 + ", ldr=" + getLoader() + "(format=" + this.format + ")]";
        }
    }

    /* access modifiers changed from: private */
    public static class LoaderReference extends WeakReference implements CacheKeyReference {
        private CacheKey cacheKey;

        LoaderReference(ClassLoader classLoader, ReferenceQueue referenceQueue, CacheKey cacheKey2) {
            super(classLoader, referenceQueue);
            this.cacheKey = cacheKey2;
        }

        @Override // java.util.ResourceBundle.CacheKeyReference
        public CacheKey getCacheKey() {
            return this.cacheKey;
        }
    }

    /* access modifiers changed from: private */
    public static class BundleReference extends SoftReference implements CacheKeyReference {
        private CacheKey cacheKey;

        BundleReference(ResourceBundle resourceBundle, ReferenceQueue referenceQueue, CacheKey cacheKey2) {
            super(resourceBundle, referenceQueue);
            this.cacheKey = cacheKey2;
        }

        @Override // java.util.ResourceBundle.CacheKeyReference
        public CacheKey getCacheKey() {
            return this.cacheKey;
        }
    }

    public static ResourceBundle getBundle(String str, Locale locale2, ClassLoader classLoader) {
        if (classLoader != null) {
            return getBundleImpl(str, locale2, classLoader, getDefaultControl(str));
        }
        throw new NullPointerException();
    }

    private static Control getDefaultControl(String str) {
        return Control.INSTANCE;
    }

    private static ResourceBundle getBundleImpl(String str, Locale locale2, ClassLoader classLoader, Control control) {
        if (locale2 == null || control == null) {
            throw new NullPointerException();
        }
        CacheKey cacheKey2 = new CacheKey(str, locale2, classLoader);
        BundleReference bundleReference = (BundleReference) cacheList.get(cacheKey2);
        ResourceBundle resourceBundle = bundleReference != null ? (ResourceBundle) bundleReference.get() : null;
        if (isValidBundle(resourceBundle) && hasValidParentChain(resourceBundle)) {
            return resourceBundle;
        }
        boolean z = control == Control.INSTANCE || (control instanceof SingleFormatControl);
        List formats = control.getFormats(str);
        if (z || checkList(formats)) {
            Locale locale3 = locale2;
            ResourceBundle resourceBundle2 = null;
            while (locale3 != null) {
                List candidateLocales = control.getCandidateLocales(str, locale3);
                if (z || checkList(candidateLocales)) {
                    resourceBundle = findBundle(cacheKey2, candidateLocales, formats, 0, control, resourceBundle2);
                    if (isValidBundle(resourceBundle)) {
                        boolean equals = Locale.ROOT.equals(resourceBundle.locale);
                        if (!equals || resourceBundle.locale.equals(locale2) || (candidateLocales.size() == 1 && resourceBundle.locale.equals(candidateLocales.get(0)))) {
                            break;
                        } else if (equals && resourceBundle2 == null) {
                            resourceBundle2 = resourceBundle;
                        }
                    }
                    locale3 = control.getFallbackLocale(str, locale3);
                } else {
                    throw new IllegalArgumentException("Invalid Control: getCandidateLocales");
                }
            }
            if (resourceBundle != null) {
                return resourceBundle;
            }
            if (resourceBundle2 != null) {
                return resourceBundle2;
            }
            throwMissingResourceException(str, locale2, cacheKey2.getCause());
            throw null;
        }
        throw new IllegalArgumentException("Invalid Control: getFormats");
    }

    private static boolean checkList(List list) {
        boolean z = list != null && !list.isEmpty();
        if (z) {
            int size = list.size();
            int i = 0;
            while (z && i < size) {
                z = list.get(i) != null;
                i++;
            }
        }
        return z;
    }

    private static ResourceBundle findBundle(CacheKey cacheKey2, List list, List list2, int i, Control control, ResourceBundle resourceBundle) {
        ResourceBundle resourceBundle2;
        Locale locale2 = (Locale) list.get(i);
        if (i != list.size() - 1) {
            resourceBundle2 = findBundle(cacheKey2, list, list2, i + 1, control, resourceBundle);
        } else if (resourceBundle != null && Locale.ROOT.equals(locale2)) {
            return resourceBundle;
        } else {
            resourceBundle2 = null;
        }
        while (true) {
            Reference poll = referenceQueue.poll();
            if (poll == null) {
                break;
            }
            cacheList.remove(((CacheKeyReference) poll).getCacheKey());
        }
        boolean z = false;
        cacheKey2.setLocale(locale2);
        ResourceBundle findBundleInCache = findBundleInCache(cacheKey2, control);
        if (isValidBundle(findBundleInCache) && !(z = findBundleInCache.expired)) {
            if (findBundleInCache.parent == resourceBundle2) {
                return findBundleInCache;
            }
            BundleReference bundleReference = (BundleReference) cacheList.get(cacheKey2);
            if (bundleReference != null && bundleReference.get() == findBundleInCache) {
                cacheList.remove(cacheKey2, bundleReference);
            }
        }
        if (findBundleInCache != NONEXISTENT_BUNDLE) {
            CacheKey cacheKey3 = (CacheKey) cacheKey2.clone();
            try {
                ResourceBundle loadBundle = loadBundle(cacheKey2, list2, control, z);
                if (loadBundle != null) {
                    if (loadBundle.parent == null) {
                        loadBundle.setParent(resourceBundle2);
                    }
                    loadBundle.locale = locale2;
                    return putBundleInCache(cacheKey2, loadBundle, control);
                }
                putBundleInCache(cacheKey2, NONEXISTENT_BUNDLE, control);
                if (cacheKey3.getCause() instanceof InterruptedException) {
                    Thread.currentThread().interrupt();
                }
            } finally {
                if (cacheKey3.getCause() instanceof InterruptedException) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        return resourceBundle2;
    }

    private static ResourceBundle loadBundle(CacheKey cacheKey2, List list, Control control, boolean z) {
        Locale locale2 = cacheKey2.getLocale();
        int size = list.size();
        ResourceBundle resourceBundle = null;
        int i = 0;
        while (true) {
            if (i >= size) {
                break;
            }
            String str = (String) list.get(i);
            try {
                resourceBundle = control.newBundle(cacheKey2.getName(), locale2, str, cacheKey2.getLoader(), z);
            } catch (LinkageError e) {
                cacheKey2.setCause(e);
            } catch (Exception e2) {
                cacheKey2.setCause(e2);
            }
            if (resourceBundle != null) {
                cacheKey2.setFormat(str);
                resourceBundle.name = cacheKey2.getName();
                resourceBundle.locale = locale2;
                resourceBundle.expired = false;
                break;
            }
            i++;
        }
        return resourceBundle;
    }

    private static boolean isValidBundle(ResourceBundle resourceBundle) {
        return (resourceBundle == null || resourceBundle == NONEXISTENT_BUNDLE) ? false : true;
    }

    private static boolean hasValidParentChain(ResourceBundle resourceBundle) {
        long currentTimeMillis = System.currentTimeMillis();
        while (resourceBundle != null) {
            if (resourceBundle.expired) {
                return false;
            }
            CacheKey cacheKey2 = resourceBundle.cacheKey;
            if (cacheKey2 != null) {
                long j = cacheKey2.expirationTime;
                if (j >= 0 && j <= currentTimeMillis) {
                    return false;
                }
            }
            resourceBundle = resourceBundle.parent;
        }
        return true;
    }

    private static void throwMissingResourceException(String str, Locale locale2, Throwable th) {
        if (th instanceof MissingResourceException) {
            th = null;
        }
        throw new MissingResourceException("Can't find bundle for base name " + str + ", locale " + locale2, str + "_" + locale2, "", th);
    }

    private static ResourceBundle findBundleInCache(CacheKey cacheKey2, Control control) {
        ResourceBundle resourceBundle;
        BundleReference bundleReference = (BundleReference) cacheList.get(cacheKey2);
        if (bundleReference == null || (resourceBundle = (ResourceBundle) bundleReference.get()) == null) {
            return null;
        }
        ResourceBundle resourceBundle2 = resourceBundle.parent;
        if (resourceBundle2 == null || !resourceBundle2.expired) {
            CacheKey cacheKey3 = bundleReference.getCacheKey();
            long j = cacheKey3.expirationTime;
            if (!resourceBundle.expired && j >= 0 && j <= System.currentTimeMillis()) {
                if (resourceBundle != NONEXISTENT_BUNDLE) {
                    synchronized (resourceBundle) {
                        long j2 = cacheKey3.expirationTime;
                        if (!resourceBundle.expired && j2 >= 0 && j2 <= System.currentTimeMillis()) {
                            try {
                                resourceBundle.expired = control.needsReload(cacheKey3.getName(), cacheKey3.getLocale(), cacheKey3.getFormat(), cacheKey3.getLoader(), resourceBundle, cacheKey3.loadTime);
                            } catch (Exception e) {
                                cacheKey2.setCause(e);
                            }
                            if (resourceBundle.expired) {
                                resourceBundle.cacheKey = null;
                                cacheList.remove(cacheKey2, bundleReference);
                            } else {
                                setExpirationTime(cacheKey3, control);
                            }
                        }
                    }
                } else {
                    cacheList.remove(cacheKey2, bundleReference);
                    return null;
                }
            }
            return resourceBundle;
        }
        resourceBundle.expired = true;
        resourceBundle.cacheKey = null;
        cacheList.remove(cacheKey2, bundleReference);
        return null;
    }

    private static ResourceBundle putBundleInCache(CacheKey cacheKey2, ResourceBundle resourceBundle, Control control) {
        setExpirationTime(cacheKey2, control);
        if (cacheKey2.expirationTime == -1) {
            return resourceBundle;
        }
        CacheKey cacheKey3 = (CacheKey) cacheKey2.clone();
        BundleReference bundleReference = new BundleReference(resourceBundle, referenceQueue, cacheKey3);
        resourceBundle.cacheKey = cacheKey3;
        BundleReference bundleReference2 = (BundleReference) cacheList.putIfAbsent(cacheKey3, bundleReference);
        if (bundleReference2 == null) {
            return resourceBundle;
        }
        ResourceBundle resourceBundle2 = (ResourceBundle) bundleReference2.get();
        if (resourceBundle2 == null || resourceBundle2.expired) {
            cacheList.put(cacheKey3, bundleReference);
            return resourceBundle;
        }
        resourceBundle.cacheKey = null;
        bundleReference.clear();
        return resourceBundle2;
    }

    private static void setExpirationTime(CacheKey cacheKey2, Control control) {
        long timeToLive = control.getTimeToLive(cacheKey2.getName(), cacheKey2.getLocale());
        if (timeToLive >= 0) {
            long currentTimeMillis = System.currentTimeMillis();
            cacheKey2.loadTime = currentTimeMillis;
            cacheKey2.expirationTime = currentTimeMillis + timeToLive;
        } else if (timeToLive >= -2) {
            cacheKey2.expirationTime = timeToLive;
        } else {
            throw new IllegalArgumentException("Invalid Control: TTL=" + timeToLive);
        }
    }

    public static class Control {
        private static final CandidateListCache CANDIDATES_CACHE = new CandidateListCache();
        public static final List FORMAT_CLASS = Collections.unmodifiableList(Arrays.asList("java.class"));
        public static final List FORMAT_DEFAULT = Collections.unmodifiableList(Arrays.asList("java.class", "java.properties"));
        public static final List FORMAT_PROPERTIES = Collections.unmodifiableList(Arrays.asList("java.properties"));
        private static final Control INSTANCE = new Control();

        protected Control() {
        }

        public List getFormats(String str) {
            if (str != null) {
                return FORMAT_DEFAULT;
            }
            throw new NullPointerException();
        }

        public List getCandidateLocales(String str, Locale locale) {
            if (str != null) {
                return new ArrayList((Collection) CANDIDATES_CACHE.get(locale.getBaseLocale()));
            }
            throw new NullPointerException();
        }

        /* access modifiers changed from: private */
        public static class CandidateListCache extends LocaleObjectCache {
            private CandidateListCache() {
            }

            /* access modifiers changed from: protected */
            public List createObject(BaseLocale baseLocale) {
                boolean z;
                boolean z2;
                String str;
                String language = baseLocale.getLanguage();
                String script = baseLocale.getScript();
                String region = baseLocale.getRegion();
                String variant = baseLocale.getVariant();
                if (!language.equals("no")) {
                    str = variant;
                    z = false;
                    z2 = false;
                } else if (!region.equals("NO") || !variant.equals("NY")) {
                    str = variant;
                    z2 = false;
                    z = true;
                } else {
                    str = "";
                    z = false;
                    z2 = true;
                }
                if (language.equals("nb") || z) {
                    List<Locale> defaultList = getDefaultList("nb", script, region, str);
                    LinkedList linkedList = new LinkedList();
                    for (Locale locale : defaultList) {
                        linkedList.add(locale);
                        if (locale.getLanguage().length() == 0) {
                            break;
                        }
                        linkedList.add(Locale.getInstance("no", locale.getScript(), locale.getCountry(), locale.getVariant(), null));
                    }
                    return linkedList;
                } else if (language.equals("nn") || z2) {
                    List defaultList2 = getDefaultList("nn", script, region, str);
                    int size = defaultList2.size() - 1;
                    int i = size + 1;
                    defaultList2.add(size, Locale.getInstance("no", "NO", "NY"));
                    defaultList2.add(i, Locale.getInstance("no", "NO", ""));
                    defaultList2.add(i + 1, Locale.getInstance("no", "", ""));
                    return defaultList2;
                } else {
                    if (language.equals("zh")) {
                        char c = 65535;
                        if (script.length() == 0 && region.length() > 0) {
                            int hashCode = region.hashCode();
                            if (hashCode != 2155) {
                                if (hashCode != 2307) {
                                    if (hashCode != 2466) {
                                        if (hashCode != 2644) {
                                            if (hashCode == 2691 && region.equals("TW")) {
                                                c = 0;
                                            }
                                        } else if (region.equals("SG")) {
                                            c = 4;
                                        }
                                    } else if (region.equals("MO")) {
                                        c = 2;
                                    }
                                } else if (region.equals("HK")) {
                                    c = 1;
                                }
                            } else if (region.equals("CN")) {
                                c = 3;
                            }
                            if (c == 0 || c == 1 || c == 2) {
                                script = "Hant";
                            } else if (c == 3 || c == 4) {
                                script = "Hans";
                            }
                        } else if (script.length() > 0 && region.length() == 0) {
                            switch (script.hashCode()) {
                                case 2241694:
                                    if (script.equals("Hans")) {
                                        c = 0;
                                        break;
                                    }
                                    break;
                                case 2241695:
                                    if (script.equals("Hant")) {
                                        c = 1;
                                        break;
                                    }
                                    break;
                            }
                            if (c == 0) {
                                region = "CN";
                            } else if (c == 1) {
                                region = "TW";
                            }
                        }
                    }
                    return getDefaultList(language, script, region, str);
                }
            }

            private static List getDefaultList(String str, String str2, String str3, String str4) {
                LinkedList<String> linkedList;
                if (str4.length() > 0) {
                    linkedList = new LinkedList();
                    int length = str4.length();
                    while (length != -1) {
                        linkedList.add(str4.substring(0, length));
                        length = str4.lastIndexOf(95, length - 1);
                    }
                } else {
                    linkedList = null;
                }
                LinkedList linkedList2 = new LinkedList();
                if (linkedList != null) {
                    for (String str5 : linkedList) {
                        linkedList2.add(Locale.getInstance(str, str2, str3, str5, null));
                    }
                }
                if (str3.length() > 0) {
                    linkedList2.add(Locale.getInstance(str, str2, str3, "", null));
                }
                if (str2.length() > 0) {
                    linkedList2.add(Locale.getInstance(str, str2, "", "", null));
                    if (linkedList != null) {
                        for (String str6 : linkedList) {
                            linkedList2.add(Locale.getInstance(str, "", str3, str6, null));
                        }
                    }
                    if (str3.length() > 0) {
                        linkedList2.add(Locale.getInstance(str, "", str3, "", null));
                    }
                }
                if (str.length() > 0) {
                    linkedList2.add(Locale.getInstance(str, "", "", "", null));
                }
                linkedList2.add(Locale.ROOT);
                return linkedList2;
            }
        }

        public Locale getFallbackLocale(String str, Locale locale) {
            if (str != null) {
                Locale locale2 = Locale.getDefault();
                if (locale.equals(locale2)) {
                    return null;
                }
                return locale2;
            }
            throw new NullPointerException();
        }

        public ResourceBundle newBundle(String str, Locale locale, String str2, final ClassLoader classLoader, final boolean z) {
            String bundleName = toBundleName(str, locale);
            if (str2.equals("java.class")) {
                try {
                    Class loadClass = classLoader.loadClass(bundleName);
                    if (ResourceBundle.class.isAssignableFrom(loadClass)) {
                        return (ResourceBundle) loadClass.newInstance();
                    }
                    throw new ClassCastException(loadClass.getName() + " cannot be cast to ResourceBundle");
                } catch (ClassNotFoundException unused) {
                    return null;
                }
            } else if (str2.equals("java.properties")) {
                final String resourceName0 = toResourceName0(bundleName, "properties");
                if (resourceName0 == null) {
                    return null;
                }
                try {
                    InputStream inputStream = (InputStream) AccessController.doPrivileged(new PrivilegedExceptionAction() {
                        /* class java.util.ResourceBundle.Control.AnonymousClass1 */

                        @Override // java.security.PrivilegedExceptionAction
                        public InputStream run() {
                            URLConnection openConnection;
                            if (!z) {
                                return classLoader.getResourceAsStream(resourceName0);
                            }
                            URL resource = classLoader.getResource(resourceName0);
                            if (resource == null || (openConnection = resource.openConnection()) == null) {
                                return null;
                            }
                            openConnection.setUseCaches(false);
                            return openConnection.getInputStream();
                        }
                    });
                    if (inputStream == null) {
                        return null;
                    }
                    try {
                        return new PropertyResourceBundle(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                    } finally {
                        inputStream.close();
                    }
                } catch (PrivilegedActionException e) {
                    throw ((IOException) e.getException());
                }
            } else {
                throw new IllegalArgumentException("unknown format: " + str2);
            }
        }

        public long getTimeToLive(String str, Locale locale) {
            if (str != null && locale != null) {
                return -2;
            }
            throw new NullPointerException();
        }

        public boolean needsReload(String str, Locale locale, String str2, ClassLoader classLoader, ResourceBundle resourceBundle, long j) {
            URL resource;
            if (resourceBundle != null) {
                if (str2.equals("java.class") || str2.equals("java.properties")) {
                    str2 = str2.substring(5);
                }
                try {
                    String resourceName0 = toResourceName0(toBundleName(str, locale), str2);
                    if (resourceName0 == null || (resource = classLoader.getResource(resourceName0)) == null) {
                        return false;
                    }
                    URLConnection openConnection = resource.openConnection();
                    long j2 = 0;
                    if (openConnection != null) {
                        openConnection.setUseCaches(false);
                        if (openConnection instanceof JarURLConnection) {
                            JarEntry jarEntry = ((JarURLConnection) openConnection).getJarEntry();
                            if (jarEntry != null) {
                                long time = jarEntry.getTime();
                                if (time != -1) {
                                    j2 = time;
                                }
                            }
                        } else {
                            j2 = openConnection.getLastModified();
                        }
                    }
                    if (j2 >= j) {
                        return true;
                    }
                    return false;
                } catch (NullPointerException e) {
                    throw e;
                } catch (Exception unused) {
                    return false;
                }
            } else {
                throw new NullPointerException();
            }
        }

        public String toBundleName(String str, Locale locale) {
            if (locale == Locale.ROOT) {
                return str;
            }
            String language = locale.getLanguage();
            String script = locale.getScript();
            String country = locale.getCountry();
            String variant = locale.getVariant();
            if (language == "" && country == "" && variant == "") {
                return str;
            }
            StringBuilder sb = new StringBuilder(str);
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

        public final String toResourceName(String str, String str2) {
            StringBuilder sb = new StringBuilder(str.length() + 1 + str2.length());
            sb.append(str.replace('.', '/'));
            sb.append('.');
            sb.append(str2);
            return sb.toString();
        }

        private String toResourceName0(String str, String str2) {
            if (str.contains("://")) {
                return null;
            }
            return toResourceName(str, str2);
        }
    }

    /* access modifiers changed from: private */
    public static class SingleFormatControl extends Control {
        private static final Control CLASS_ONLY = new SingleFormatControl(Control.FORMAT_CLASS);
        private static final Control PROPERTIES_ONLY = new SingleFormatControl(Control.FORMAT_PROPERTIES);
        private final List formats;

        protected SingleFormatControl(List list) {
            this.formats = list;
        }

        @Override // java.util.ResourceBundle.Control
        public List getFormats(String str) {
            if (str != null) {
                return this.formats;
            }
            throw new NullPointerException();
        }
    }
}
