package android.icu.impl;

import android.icu.impl.ICUResourceBundleImpl;
import android.icu.impl.ICUResourceBundleReader;
import android.icu.impl.URLHandler;
import android.icu.impl.UResource;
import android.icu.util.ULocale;
import android.icu.util.UResourceBundle;
import android.icu.util.UResourceBundleIterator;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;

public class ICUResourceBundle extends UResourceBundle {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int ALIAS = 3;
    public static final int ARRAY16 = 9;
    private static CacheBase<String, ICUResourceBundle, Loader> BUNDLE_CACHE = new SoftCache<String, ICUResourceBundle, Loader>() {
        /* class android.icu.impl.ICUResourceBundle.AnonymousClass1 */

        /* access modifiers changed from: protected */
        public ICUResourceBundle createInstance(String unusedKey, Loader loader) {
            return loader.load();
        }
    };
    private static final boolean DEBUG = ICUDebug.enabled("localedata");
    private static final String DEFAULT_TAG = "default";
    private static final String FULL_LOCALE_NAMES_LIST = "fullLocaleNames.lst";
    private static CacheBase<String, AvailEntry, ClassLoader> GET_AVAILABLE_CACHE = new SoftCache<String, AvailEntry, ClassLoader>() {
        /* class android.icu.impl.ICUResourceBundle.AnonymousClass3 */

        /* access modifiers changed from: protected */
        public AvailEntry createInstance(String key, ClassLoader loader) {
            return new AvailEntry(key, loader);
        }
    };
    private static final char HYPHEN = '-';
    private static final String ICUDATA = "ICUDATA";
    public static final ClassLoader ICU_DATA_CLASS_LOADER = ClassLoaderUtil.getClassLoader(ICUData.class);
    private static final String ICU_RESOURCE_INDEX = "res_index";
    protected static final String INSTALLED_LOCALES = "InstalledLocales";
    private static final String LOCALE = "LOCALE";
    public static final String NO_INHERITANCE_MARKER = "∅∅∅";
    public static final int RES_BOGUS = -1;
    private static final char RES_PATH_SEP_CHAR = '/';
    private static final String RES_PATH_SEP_STR = "/";
    public static final int STRING_V2 = 6;
    public static final int TABLE16 = 5;
    public static final int TABLE32 = 4;
    private ICUResourceBundle container;
    protected String key;
    WholeBundle wholeBundle;

    public enum OpenType {
        LOCALE_DEFAULT_ROOT,
        LOCALE_ROOT,
        LOCALE_ONLY,
        DIRECT
    }

    /* access modifiers changed from: protected */
    public static final class WholeBundle {
        String baseName;
        ClassLoader loader;
        String localeID;
        ICUResourceBundleReader reader;
        Set<String> topLevelKeys;
        ULocale ulocale;

        WholeBundle(String baseName2, String localeID2, ClassLoader loader2, ICUResourceBundleReader reader2) {
            this.baseName = baseName2;
            this.localeID = localeID2;
            this.ulocale = new ULocale(localeID2);
            this.loader = loader2;
            this.reader = reader2;
        }
    }

    /* access modifiers changed from: private */
    public static abstract class Loader {
        /* access modifiers changed from: package-private */
        public abstract ICUResourceBundle load();

        private Loader() {
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0084  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x008c A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00c1  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00ca A[ADDED_TO_REGION, LOOP:2: B:35:0x00a0->B:45:0x00ca, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00ce A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x0142  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x0171  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x0093 A[ADDED_TO_REGION, EDGE_INSN: B:83:0x0093->B:34:0x0093 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x00cc A[EDGE_INSN: B:86:0x00cc->B:46:0x00cc ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final android.icu.util.ULocale getFunctionalEquivalent(java.lang.String r20, java.lang.ClassLoader r21, java.lang.String r22, java.lang.String r23, android.icu.util.ULocale r24, boolean[] r25, boolean r26) {
        /*
        // Method dump skipped, instructions count: 395
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.ICUResourceBundle.getFunctionalEquivalent(java.lang.String, java.lang.ClassLoader, java.lang.String, java.lang.String, android.icu.util.ULocale, boolean[], boolean):android.icu.util.ULocale");
    }

    public static final String[] getKeywordValues(String baseName, String keyword) {
        Set<String> keywords = new HashSet<>();
        ULocale[] locales = getAvailEntry(baseName, ICU_DATA_CLASS_LOADER).getULocaleList();
        for (int i = 0; i < locales.length; i++) {
            try {
                Enumeration<String> e = ((ICUResourceBundle) UResourceBundle.getBundleInstance(baseName, locales[i]).getObject(keyword)).getKeys();
                while (e.hasMoreElements()) {
                    String s = e.nextElement();
                    if (!DEFAULT_TAG.equals(s) && !s.startsWith("private-")) {
                        keywords.add(s);
                    }
                }
            } catch (Throwable th) {
            }
        }
        return (String[]) keywords.toArray(new String[0]);
    }

    public ICUResourceBundle getWithFallback(String path) throws MissingResourceException {
        ICUResourceBundle result = findResourceWithFallback(path, this, null);
        if (result == null) {
            throw new MissingResourceException("Can't find resource for bundle " + getClass().getName() + ", key " + getType(), path, getKey());
        } else if (result.getType() != 0 || !result.getString().equals(NO_INHERITANCE_MARKER)) {
            return result;
        } else {
            throw new MissingResourceException("Encountered NO_INHERITANCE_MARKER", path, getKey());
        }
    }

    public ICUResourceBundle at(int index) {
        return (ICUResourceBundle) handleGet(index, (HashMap<String, String>) null, this);
    }

    public ICUResourceBundle at(String key2) {
        if (this instanceof ICUResourceBundleImpl.ResourceTable) {
            return (ICUResourceBundle) handleGet(key2, (HashMap<String, String>) null, this);
        }
        return null;
    }

    @Override // android.icu.util.UResourceBundle
    public ICUResourceBundle findTopLevel(int index) {
        return (ICUResourceBundle) super.findTopLevel(index);
    }

    @Override // android.icu.util.UResourceBundle
    public ICUResourceBundle findTopLevel(String aKey) {
        return (ICUResourceBundle) super.findTopLevel(aKey);
    }

    public ICUResourceBundle findWithFallback(String path) {
        return findResourceWithFallback(path, this, null);
    }

    public String findStringWithFallback(String path) {
        return findStringWithFallback(path, this, null);
    }

    public String getStringWithFallback(String path) throws MissingResourceException {
        String result = findStringWithFallback(path, this, null);
        if (result == null) {
            throw new MissingResourceException("Can't find resource for bundle " + getClass().getName() + ", key " + getType(), path, getKey());
        } else if (!result.equals(NO_INHERITANCE_MARKER)) {
            return result;
        } else {
            throw new MissingResourceException("Encountered NO_INHERITANCE_MARKER", path, getKey());
        }
    }

    public void getAllItemsWithFallbackNoFail(String path, UResource.Sink sink) {
        try {
            getAllItemsWithFallback(path, sink);
        } catch (MissingResourceException e) {
        }
    }

    public void getAllItemsWithFallback(String path, UResource.Sink sink) throws MissingResourceException {
        ICUResourceBundle rb;
        int numPathKeys = countPathKeys(path);
        if (numPathKeys == 0) {
            rb = this;
        } else {
            int depth = getResDepth();
            String[] pathKeys = new String[(depth + numPathKeys)];
            getResPathKeys(path, numPathKeys, pathKeys, depth);
            ICUResourceBundle rb2 = findResourceWithFallback(pathKeys, depth, this, null);
            if (rb2 != null) {
                rb = rb2;
            } else {
                throw new MissingResourceException("Can't find resource for bundle " + getClass().getName() + ", key " + getType(), path, getKey());
            }
        }
        rb.getAllItemsWithFallback(new UResource.Key(), new ICUResourceBundleReader.ReaderValue(), sink);
    }

    private void getAllItemsWithFallback(UResource.Key key2, ICUResourceBundleReader.ReaderValue readerValue, UResource.Sink sink) {
        ICUResourceBundle rb;
        ICUResourceBundleImpl impl = (ICUResourceBundleImpl) this;
        readerValue.reader = impl.wholeBundle.reader;
        readerValue.res = impl.getResource();
        String str = this.key;
        if (str == null) {
            str = "";
        }
        key2.setString(str);
        sink.put(key2, readerValue, this.parent == null);
        if (this.parent != null) {
            ICUResourceBundle parentBundle = (ICUResourceBundle) this.parent;
            int depth = getResDepth();
            if (depth == 0) {
                rb = parentBundle;
            } else {
                String[] pathKeys = new String[depth];
                getResPathKeys(pathKeys, depth);
                rb = findResourceWithFallback(pathKeys, 0, parentBundle, null);
            }
            if (rb != null) {
                rb.getAllItemsWithFallback(key2, readerValue, sink);
            }
        }
    }

    public static Set<String> getAvailableLocaleNameSet(String bundlePrefix, ClassLoader loader) {
        return getAvailEntry(bundlePrefix, loader).getLocaleNameSet();
    }

    public static Set<String> getFullLocaleNameSet() {
        return getFullLocaleNameSet(ICUData.ICU_BASE_NAME, ICU_DATA_CLASS_LOADER);
    }

    public static Set<String> getFullLocaleNameSet(String bundlePrefix, ClassLoader loader) {
        return getAvailEntry(bundlePrefix, loader).getFullLocaleNameSet();
    }

    public static Set<String> getAvailableLocaleNameSet() {
        return getAvailableLocaleNameSet(ICUData.ICU_BASE_NAME, ICU_DATA_CLASS_LOADER);
    }

    public static final ULocale[] getAvailableULocales(String baseName, ClassLoader loader) {
        return getAvailEntry(baseName, loader).getULocaleList();
    }

    public static final ULocale[] getAvailableULocales() {
        return getAvailableULocales(ICUData.ICU_BASE_NAME, ICU_DATA_CLASS_LOADER);
    }

    public static final Locale[] getAvailableLocales(String baseName, ClassLoader loader) {
        return getAvailEntry(baseName, loader).getLocaleList();
    }

    public static final Locale[] getAvailableLocales() {
        return getAvailEntry(ICUData.ICU_BASE_NAME, ICU_DATA_CLASS_LOADER).getLocaleList();
    }

    public static final Locale[] getLocaleList(ULocale[] ulocales) {
        ArrayList<Locale> list = new ArrayList<>(ulocales.length);
        HashSet<Locale> uniqueSet = new HashSet<>();
        for (ULocale uLocale : ulocales) {
            Locale loc = uLocale.toLocale();
            if (!uniqueSet.contains(loc)) {
                list.add(loc);
                uniqueSet.add(loc);
            }
        }
        return (Locale[]) list.toArray(new Locale[list.size()]);
    }

    @Override // android.icu.util.UResourceBundle, java.util.ResourceBundle
    public Locale getLocale() {
        return getULocale().toLocale();
    }

    /* access modifiers changed from: private */
    public static final ULocale[] createULocaleList(String baseName, ClassLoader root) {
        ICUResourceBundle bundle = (ICUResourceBundle) ((ICUResourceBundle) UResourceBundle.instantiateBundle(baseName, ICU_RESOURCE_INDEX, root, true)).get(INSTALLED_LOCALES);
        int i = 0;
        ULocale[] locales = new ULocale[bundle.getSize()];
        UResourceBundleIterator iter = bundle.getIterator();
        iter.reset();
        while (iter.hasNext()) {
            String locstr = iter.next().getKey();
            if (locstr.equals("root")) {
                locales[i] = ULocale.ROOT;
                i++;
            } else {
                locales[i] = new ULocale(locstr);
                i++;
            }
        }
        return locales;
    }

    private static final void addLocaleIDsFromIndexBundle(String baseName, ClassLoader root, Set<String> locales) {
        try {
            UResourceBundleIterator iter = ((ICUResourceBundle) ((ICUResourceBundle) UResourceBundle.instantiateBundle(baseName, ICU_RESOURCE_INDEX, root, true)).get(INSTALLED_LOCALES)).getIterator();
            iter.reset();
            while (iter.hasNext()) {
                locales.add(iter.next().getKey());
            }
        } catch (MissingResourceException e) {
            if (DEBUG) {
                PrintStream printStream = System.out;
                printStream.println("couldn't find " + baseName + RES_PATH_SEP_CHAR + ICU_RESOURCE_INDEX + ".res");
                Thread.dumpStack();
            }
        }
    }

    private static final void addBundleBaseNamesFromClassLoader(final String bn, final ClassLoader root, final Set<String> names) {
        AccessController.doPrivileged(new PrivilegedAction<Void>() {
            /* class android.icu.impl.ICUResourceBundle.AnonymousClass2 */

            @Override // java.security.PrivilegedAction
            public Void run() {
                try {
                    Enumeration<URL> urls = ClassLoader.this.getResources(bn);
                    if (urls == null) {
                        return null;
                    }
                    URLHandler.URLVisitor v = new URLHandler.URLVisitor() {
                        /* class android.icu.impl.ICUResourceBundle.AnonymousClass2.AnonymousClass1 */

                        @Override // android.icu.impl.URLHandler.URLVisitor
                        public void visit(String s) {
                            if (s.endsWith(".res")) {
                                names.add(s.substring(0, s.length() - 4));
                            }
                        }
                    };
                    while (urls.hasMoreElements()) {
                        URL url = urls.nextElement();
                        URLHandler handler = URLHandler.get(url);
                        if (handler != null) {
                            handler.guide(v, false);
                        } else if (ICUResourceBundle.DEBUG) {
                            PrintStream printStream = System.out;
                            printStream.println("handler for " + ((Object) url) + " is null");
                        }
                    }
                    return null;
                } catch (IOException e) {
                    if (ICUResourceBundle.DEBUG) {
                        PrintStream printStream2 = System.out;
                        printStream2.println("ouch: " + e.getMessage());
                    }
                }
            }
        });
    }

    private static void addLocaleIDsFromListFile(String bn, ClassLoader root, Set<String> locales) {
        try {
            InputStream s = root.getResourceAsStream(bn + FULL_LOCALE_NAMES_LIST);
            if (s != null) {
                BufferedReader br = new BufferedReader(new InputStreamReader(s, "ASCII"));
                while (true) {
                    try {
                        String line = br.readLine();
                        if (line == null) {
                            return;
                        }
                        if (line.length() != 0 && !line.startsWith("#")) {
                            locales.add(line);
                        }
                    } finally {
                        br.close();
                    }
                }
            }
        } catch (IOException e) {
        }
    }

    /* access modifiers changed from: private */
    public static Set<String> createFullLocaleNameSet(String baseName, ClassLoader loader) {
        String bn;
        String folder;
        if (baseName.endsWith(RES_PATH_SEP_STR)) {
            bn = baseName;
        } else {
            bn = baseName + RES_PATH_SEP_STR;
        }
        Set<String> set = new HashSet<>();
        if (!ICUConfig.get("android.icu.impl.ICUResourceBundle.skipRuntimeLocaleResourceScan", "false").equalsIgnoreCase("true")) {
            addBundleBaseNamesFromClassLoader(bn, loader, set);
            if (baseName.startsWith(ICUData.ICU_BASE_NAME)) {
                if (baseName.length() == ICUData.ICU_BASE_NAME.length()) {
                    folder = "";
                } else if (baseName.charAt(ICUData.ICU_BASE_NAME.length()) == '/') {
                    folder = baseName.substring(ICUData.ICU_BASE_NAME.length() + 1);
                } else {
                    folder = null;
                }
                if (folder != null) {
                    ICUBinary.addBaseNamesInFileFolder(folder, ".res", set);
                }
            }
            set.remove(ICU_RESOURCE_INDEX);
            Iterator<String> iter = set.iterator();
            while (iter.hasNext()) {
                String name = iter.next();
                if ((name.length() == 1 || name.length() > 3) && name.indexOf(95) < 0) {
                    iter.remove();
                }
            }
        }
        if (set.isEmpty()) {
            if (DEBUG) {
                System.out.println("unable to enumerate data files in " + baseName);
            }
            addLocaleIDsFromListFile(bn, loader, set);
        }
        if (set.isEmpty()) {
            addLocaleIDsFromIndexBundle(baseName, loader, set);
        }
        set.remove("root");
        set.add(ULocale.ROOT.toString());
        return Collections.unmodifiableSet(set);
    }

    /* access modifiers changed from: private */
    public static Set<String> createLocaleNameSet(String baseName, ClassLoader loader) {
        HashSet<String> set = new HashSet<>();
        addLocaleIDsFromIndexBundle(baseName, loader, set);
        return Collections.unmodifiableSet(set);
    }

    /* access modifiers changed from: private */
    public static final class AvailEntry {
        private volatile Set<String> fullNameSet;
        private ClassLoader loader;
        private volatile Locale[] locales;
        private volatile Set<String> nameSet;
        private String prefix;
        private volatile ULocale[] ulocales;

        AvailEntry(String prefix2, ClassLoader loader2) {
            this.prefix = prefix2;
            this.loader = loader2;
        }

        /* access modifiers changed from: package-private */
        public ULocale[] getULocaleList() {
            if (this.ulocales == null) {
                synchronized (this) {
                    if (this.ulocales == null) {
                        this.ulocales = ICUResourceBundle.createULocaleList(this.prefix, this.loader);
                    }
                }
            }
            return this.ulocales;
        }

        /* access modifiers changed from: package-private */
        public Locale[] getLocaleList() {
            if (this.locales == null) {
                getULocaleList();
                synchronized (this) {
                    if (this.locales == null) {
                        this.locales = ICUResourceBundle.getLocaleList(this.ulocales);
                    }
                }
            }
            return this.locales;
        }

        /* access modifiers changed from: package-private */
        public Set<String> getLocaleNameSet() {
            if (this.nameSet == null) {
                synchronized (this) {
                    if (this.nameSet == null) {
                        this.nameSet = ICUResourceBundle.createLocaleNameSet(this.prefix, this.loader);
                    }
                }
            }
            return this.nameSet;
        }

        /* access modifiers changed from: package-private */
        public Set<String> getFullLocaleNameSet() {
            if (this.fullNameSet == null) {
                synchronized (this) {
                    if (this.fullNameSet == null) {
                        this.fullNameSet = ICUResourceBundle.createFullLocaleNameSet(this.prefix, this.loader);
                    }
                }
            }
            return this.fullNameSet;
        }
    }

    private static AvailEntry getAvailEntry(String key2, ClassLoader loader) {
        return GET_AVAILABLE_CACHE.getInstance(key2, loader);
    }

    private static final ICUResourceBundle findResourceWithFallback(String path, UResourceBundle actualBundle, UResourceBundle requested) {
        if (path.length() == 0) {
            return null;
        }
        ICUResourceBundle base = (ICUResourceBundle) actualBundle;
        int depth = base.getResDepth();
        int numPathKeys = countPathKeys(path);
        String[] keys = new String[(depth + numPathKeys)];
        getResPathKeys(path, numPathKeys, keys, depth);
        return findResourceWithFallback(keys, depth, base, requested);
    }

    /* JADX INFO: Multiple debug info for r5v2 java.lang.String: [D('depth' int), D('subKey' java.lang.String)] */
    private static final ICUResourceBundle findResourceWithFallback(String[] keys, int depth, ICUResourceBundle base, UResourceBundle requested) {
        if (requested == null) {
            requested = base;
        }
        while (true) {
            int depth2 = depth + 1;
            ICUResourceBundle sub = (ICUResourceBundle) base.handleGet(keys[depth], (HashMap<String, String>) null, requested);
            if (sub == null) {
                int depth3 = depth2 - 1;
                ICUResourceBundle nextBase = base.getParent();
                if (nextBase == null) {
                    return null;
                }
                int baseDepth = base.getResDepth();
                if (depth3 != baseDepth) {
                    String[] newKeys = new String[((keys.length - depth3) + baseDepth)];
                    System.arraycopy(keys, depth3, newKeys, baseDepth, keys.length - depth3);
                    keys = newKeys;
                }
                base.getResPathKeys(keys, baseDepth);
                base = nextBase;
                depth = 0;
            } else if (depth2 == keys.length) {
                return sub;
            } else {
                base = sub;
                depth = depth2;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: Multiple debug info for r7v4 java.lang.String: [D('depth' int), D('subKey' java.lang.String)] */
    /* JADX INFO: Multiple debug info for r4v14 android.icu.impl.ICUResourceBundleReader$Container: [D('type' int), D('readerContainer' android.icu.impl.ICUResourceBundleReader$Container)] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00ee  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00ec A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.String findStringWithFallback(java.lang.String r18, android.icu.util.UResourceBundle r19, android.icu.util.UResourceBundle r20) {
        /*
        // Method dump skipped, instructions count: 253
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.ICUResourceBundle.findStringWithFallback(java.lang.String, android.icu.util.UResourceBundle, android.icu.util.UResourceBundle):java.lang.String");
    }

    private int getResDepth() {
        ICUResourceBundle iCUResourceBundle = this.container;
        if (iCUResourceBundle == null) {
            return 0;
        }
        return iCUResourceBundle.getResDepth() + 1;
    }

    private void getResPathKeys(String[] keys, int depth) {
        ICUResourceBundle b = this;
        while (depth > 0) {
            depth--;
            keys[depth] = b.key;
            b = b.container;
        }
    }

    private static int countPathKeys(String path) {
        if (path.isEmpty()) {
            return 0;
        }
        int num = 1;
        for (int i = 0; i < path.length(); i++) {
            if (path.charAt(i) == '/') {
                num++;
            }
        }
        return num;
    }

    private static void getResPathKeys(String path, int num, String[] keys, int start) {
        if (num != 0) {
            if (num == 1) {
                keys[start] = path;
                return;
            }
            int i = 0;
            while (true) {
                int j = path.indexOf(47, i);
                int start2 = start + 1;
                keys[start] = path.substring(i, j);
                if (num == 2) {
                    keys[start2] = path.substring(j + 1);
                    return;
                }
                i = j + 1;
                num--;
                start = start2;
            }
        }
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ICUResourceBundle)) {
            return false;
        }
        ICUResourceBundle o = (ICUResourceBundle) other;
        if (!getBaseName().equals(o.getBaseName()) || !getLocaleID().equals(o.getLocaleID())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return 42;
    }

    public static ICUResourceBundle getBundleInstance(String baseName, String localeID, ClassLoader root, boolean disableFallback) {
        return getBundleInstance(baseName, localeID, root, disableFallback ? OpenType.DIRECT : OpenType.LOCALE_DEFAULT_ROOT);
    }

    public static ICUResourceBundle getBundleInstance(String baseName, ULocale locale, OpenType openType) {
        if (locale == null) {
            locale = ULocale.getDefault();
        }
        return getBundleInstance(baseName, locale.getBaseName(), ICU_DATA_CLASS_LOADER, openType);
    }

    public static ICUResourceBundle getBundleInstance(String baseName, String localeID, ClassLoader root, OpenType openType) {
        ICUResourceBundle b;
        if (baseName == null) {
            baseName = ICUData.ICU_BASE_NAME;
        }
        String localeID2 = ULocale.getBaseName(localeID);
        if (openType == OpenType.LOCALE_DEFAULT_ROOT) {
            b = instantiateBundle(baseName, localeID2, ULocale.getDefault().getBaseName(), root, openType);
        } else {
            b = instantiateBundle(baseName, localeID2, null, root, openType);
        }
        if (b != null) {
            return b;
        }
        throw new MissingResourceException("Could not find the bundle " + baseName + RES_PATH_SEP_STR + localeID2 + ".res", "", "");
    }

    /* access modifiers changed from: private */
    public static boolean localeIDStartsWithLangSubtag(String localeID, String lang) {
        return localeID.startsWith(lang) && (localeID.length() == lang.length() || localeID.charAt(lang.length()) == '_');
    }

    /* access modifiers changed from: private */
    public static ICUResourceBundle instantiateBundle(final String baseName, final String localeID, final String defaultID, final ClassLoader root, final OpenType openType) {
        String cacheKey;
        final String fullName = ICUResourceBundleReader.getFullName(baseName, localeID);
        char openTypeChar = (char) (openType.ordinal() + 48);
        if (openType != OpenType.LOCALE_DEFAULT_ROOT) {
            cacheKey = fullName + '#' + openTypeChar;
        } else {
            cacheKey = fullName + '#' + openTypeChar + '#' + defaultID;
        }
        return BUNDLE_CACHE.getInstance(cacheKey, new Loader() {
            /* class android.icu.impl.ICUResourceBundle.AnonymousClass4 */

            @Override // android.icu.impl.ICUResourceBundle.Loader
            public ICUResourceBundle load() {
                if (ICUResourceBundle.DEBUG) {
                    PrintStream printStream = System.out;
                    printStream.println("Creating " + String.this);
                }
                String rootLocale = baseName.indexOf(46) == -1 ? "root" : "";
                String localeName = localeID.isEmpty() ? rootLocale : localeID;
                ICUResourceBundle b = ICUResourceBundle.createBundle(baseName, localeName, root);
                if (ICUResourceBundle.DEBUG) {
                    PrintStream printStream2 = System.out;
                    StringBuilder sb = new StringBuilder();
                    sb.append("The bundle created is: ");
                    sb.append((Object) b);
                    sb.append(" and openType=");
                    sb.append((Object) openType);
                    sb.append(" and bundle.getNoFallback=");
                    sb.append(b != null && b.getNoFallback());
                    printStream2.println(sb.toString());
                }
                if (openType == OpenType.DIRECT || (b != null && b.getNoFallback())) {
                    return b;
                }
                if (b == null) {
                    int i = localeName.lastIndexOf(95);
                    if (i != -1) {
                        return ICUResourceBundle.instantiateBundle(baseName, localeName.substring(0, i), defaultID, root, openType);
                    } else if (openType == OpenType.LOCALE_DEFAULT_ROOT && !ICUResourceBundle.localeIDStartsWithLangSubtag(defaultID, localeName)) {
                        String str = baseName;
                        String str2 = defaultID;
                        return ICUResourceBundle.instantiateBundle(str, str2, str2, root, openType);
                    } else if (openType == OpenType.LOCALE_ONLY || rootLocale.isEmpty()) {
                        return b;
                    } else {
                        return ICUResourceBundle.createBundle(baseName, rootLocale, root);
                    }
                } else {
                    UResourceBundle parent = null;
                    String localeName2 = b.getLocaleID();
                    int i2 = localeName2.lastIndexOf(95);
                    String parentLocaleName = ((ICUResourceBundleImpl.ResourceTable) b).findString("%%Parent");
                    if (parentLocaleName != null) {
                        parent = ICUResourceBundle.instantiateBundle(baseName, parentLocaleName, defaultID, root, openType);
                    } else if (i2 != -1) {
                        parent = ICUResourceBundle.instantiateBundle(baseName, localeName2.substring(0, i2), defaultID, root, openType);
                    } else if (!localeName2.equals(rootLocale)) {
                        parent = ICUResourceBundle.instantiateBundle(baseName, rootLocale, defaultID, root, openType);
                    }
                    if (b.equals(parent)) {
                        return b;
                    }
                    b.setParent(parent);
                    return b;
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public ICUResourceBundle get(String aKey, HashMap<String, String> aliasesVisited, UResourceBundle requested) {
        ICUResourceBundle obj = (ICUResourceBundle) handleGet(aKey, aliasesVisited, requested);
        if (obj == null) {
            obj = getParent();
            if (obj != null) {
                obj = obj.get(aKey, aliasesVisited, requested);
            }
            if (obj == null) {
                String fullName = ICUResourceBundleReader.getFullName(getBaseName(), getLocaleID());
                throw new MissingResourceException("Can't find resource for bundle " + fullName + ", key " + aKey, getClass().getName(), aKey);
            }
        }
        return obj;
    }

    public static ICUResourceBundle createBundle(String baseName, String localeID, ClassLoader root) {
        ICUResourceBundleReader reader = ICUResourceBundleReader.getReader(baseName, localeID, root);
        if (reader == null) {
            return null;
        }
        return getBundle(reader, baseName, localeID, root);
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.util.UResourceBundle
    public String getLocaleID() {
        return this.wholeBundle.localeID;
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.util.UResourceBundle
    public String getBaseName() {
        return this.wholeBundle.baseName;
    }

    @Override // android.icu.util.UResourceBundle
    public ULocale getULocale() {
        return this.wholeBundle.ulocale;
    }

    public boolean isRoot() {
        return this.wholeBundle.localeID.isEmpty() || this.wholeBundle.localeID.equals("root");
    }

    @Override // android.icu.util.UResourceBundle
    public ICUResourceBundle getParent() {
        return (ICUResourceBundle) this.parent;
    }

    /* access modifiers changed from: protected */
    @Override // java.util.ResourceBundle
    public void setParent(ResourceBundle parent) {
        this.parent = parent;
    }

    @Override // android.icu.util.UResourceBundle
    public String getKey() {
        return this.key;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean getNoFallback() {
        return this.wholeBundle.reader.getNoFallback();
    }

    private static ICUResourceBundle getBundle(ICUResourceBundleReader reader, String baseName, String localeID, ClassLoader loader) {
        int rootRes = reader.getRootResource();
        if (ICUResourceBundleReader.URES_IS_TABLE(ICUResourceBundleReader.RES_GET_TYPE(rootRes))) {
            ICUResourceBundleImpl.ResourceTable rootTable = new ICUResourceBundleImpl.ResourceTable(new WholeBundle(baseName, localeID, loader, reader), rootRes);
            String aliasString = rootTable.findString("%%ALIAS");
            if (aliasString != null) {
                return (ICUResourceBundle) UResourceBundle.getBundleInstance(baseName, aliasString);
            }
            return rootTable;
        }
        throw new IllegalStateException("Invalid format error");
    }

    protected ICUResourceBundle(WholeBundle wholeBundle2) {
        this.wholeBundle = wholeBundle2;
    }

    protected ICUResourceBundle(ICUResourceBundle container2, String key2) {
        this.key = key2;
        this.wholeBundle = container2.wholeBundle;
        this.container = container2;
        this.parent = container2.parent;
    }

    protected static ICUResourceBundle getAliasedResource(ICUResourceBundle base, String[] keys, int depth, String key2, int _resource, HashMap<String, String> aliasesVisited, UResourceBundle requested) {
        HashMap<String, String> aliasesVisited2;
        String locale;
        String bundleName;
        ClassLoader loaderToUse;
        String[] keys2;
        int numKeys;
        ClassLoader loaderToUse2;
        WholeBundle wholeBundle2 = base.wholeBundle;
        ClassLoader loaderToUse3 = wholeBundle2.loader;
        String keyPath = null;
        String rpath = wholeBundle2.reader.getAlias(_resource);
        if (aliasesVisited == null) {
            aliasesVisited2 = new HashMap<>();
        } else {
            aliasesVisited2 = aliasesVisited;
        }
        if (aliasesVisited2.get(rpath) == null) {
            aliasesVisited2.put(rpath, "");
            if (rpath.indexOf(47) == 0) {
                int i = rpath.indexOf(47, 1);
                int j = rpath.indexOf(47, i + 1);
                bundleName = rpath.substring(1, i);
                if (j < 0) {
                    locale = rpath.substring(i + 1);
                } else {
                    locale = rpath.substring(i + 1, j);
                    keyPath = rpath.substring(j + 1, rpath.length());
                }
                if (bundleName.equals(ICUDATA)) {
                    bundleName = ICUData.ICU_BASE_NAME;
                    loaderToUse = ICU_DATA_CLASS_LOADER;
                } else {
                    if (bundleName.indexOf(ICUDATA) > -1) {
                        int idx = bundleName.indexOf(45);
                        if (idx > -1) {
                            bundleName = "android/icu/impl/data/icudt63b/" + bundleName.substring(idx + 1, bundleName.length());
                            loaderToUse = ICU_DATA_CLASS_LOADER;
                        } else {
                            loaderToUse2 = loaderToUse3;
                        }
                    } else {
                        loaderToUse2 = loaderToUse3;
                    }
                    loaderToUse = loaderToUse2;
                }
            } else {
                int i2 = rpath.indexOf(47);
                if (i2 != -1) {
                    String locale2 = rpath.substring(0, i2);
                    keyPath = rpath.substring(i2 + 1);
                    locale = locale2;
                } else {
                    locale = rpath;
                }
                bundleName = wholeBundle2.baseName;
                loaderToUse = loaderToUse3;
            }
            ICUResourceBundle sub = null;
            if (bundleName.equals(LOCALE)) {
                String bundleName2 = wholeBundle2.baseName;
                String keyPath2 = rpath.substring(LOCALE.length() + 2, rpath.length());
                ICUResourceBundle bundle = (ICUResourceBundle) requested;
                while (bundle.container != null) {
                    bundle = bundle.container;
                }
                sub = findResourceWithFallback(keyPath2, bundle, null);
            } else {
                ICUResourceBundle bundle2 = getBundleInstance(bundleName, locale, loaderToUse, false);
                if (keyPath != null) {
                    numKeys = countPathKeys(keyPath);
                    if (numKeys > 0) {
                        keys2 = new String[numKeys];
                        getResPathKeys(keyPath, numKeys, keys2, 0);
                    } else {
                        keys2 = keys;
                    }
                } else if (keys != null) {
                    numKeys = depth;
                    keys2 = keys;
                } else {
                    int depth2 = base.getResDepth();
                    numKeys = depth2 + 1;
                    keys2 = new String[numKeys];
                    base.getResPathKeys(keys2, depth2);
                    keys2[depth2] = key2;
                }
                if (numKeys > 0) {
                    sub = bundle2;
                    int i3 = 0;
                    while (sub != null && i3 < numKeys) {
                        sub = sub.get(keys2[i3], aliasesVisited2, requested);
                        i3++;
                        loaderToUse = loaderToUse;
                    }
                }
            }
            if (sub != null) {
                return sub;
            }
            throw new MissingResourceException(wholeBundle2.localeID, wholeBundle2.baseName, key2);
        }
        throw new IllegalArgumentException("Circular references in the resource bundles");
    }

    @Deprecated
    public final Set<String> getTopLevelKeySet() {
        return this.wholeBundle.topLevelKeys;
    }

    @Deprecated
    public final void setTopLevelKeySet(Set<String> keySet) {
        this.wholeBundle.topLevelKeys = keySet;
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.util.UResourceBundle
    public Enumeration<String> handleGetKeys() {
        return Collections.enumeration(handleKeySet());
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.util.UResourceBundle
    public boolean isTopLevelResource() {
        return this.container == null;
    }
}
