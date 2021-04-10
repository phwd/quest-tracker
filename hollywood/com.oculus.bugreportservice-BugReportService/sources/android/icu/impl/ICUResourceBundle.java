package android.icu.impl;

import android.icu.impl.ICUResourceBundleImpl;
import android.icu.impl.ICUResourceBundleReader;
import android.icu.impl.URLHandler;
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
    private static CacheBase BUNDLE_CACHE = new SoftCache() {
        /* class android.icu.impl.ICUResourceBundle.AnonymousClass1 */

        /* access modifiers changed from: protected */
        public ICUResourceBundle createInstance(String str, Loader loader) {
            return loader.load();
        }
    };
    private static final boolean DEBUG = ICUDebug.enabled("localedata");
    private static CacheBase GET_AVAILABLE_CACHE = new SoftCache() {
        /* class android.icu.impl.ICUResourceBundle.AnonymousClass3 */

        /* access modifiers changed from: protected */
        public AvailEntry createInstance(String str, ClassLoader classLoader) {
            return new AvailEntry(str, classLoader);
        }
    };
    public static final ClassLoader ICU_DATA_CLASS_LOADER = ClassLoaderUtil.getClassLoader(ICUData.class);
    private ICUResourceBundle container;
    protected String key;
    WholeBundle wholeBundle;

    public enum OpenType {
        LOCALE_DEFAULT_ROOT,
        LOCALE_ROOT,
        LOCALE_ONLY,
        DIRECT
    }

    public int hashCode() {
        return 42;
    }

    /* access modifiers changed from: protected */
    public static final class WholeBundle {
        String baseName;
        ClassLoader loader;
        String localeID;
        ICUResourceBundleReader reader;
        Set topLevelKeys;
        ULocale ulocale;

        WholeBundle(String str, String str2, ClassLoader classLoader, ICUResourceBundleReader iCUResourceBundleReader) {
            this.baseName = str;
            this.localeID = str2;
            this.ulocale = new ULocale(str2);
            this.loader = classLoader;
            this.reader = iCUResourceBundleReader;
        }
    }

    /* access modifiers changed from: private */
    public static abstract class Loader {
        /* access modifiers changed from: package-private */
        public abstract ICUResourceBundle load();

        private Loader() {
        }
    }

    public ICUResourceBundle getWithFallback(String str) {
        ICUResourceBundle findResourceWithFallback = findResourceWithFallback(str, this, null);
        if (findResourceWithFallback == null) {
            throw new MissingResourceException("Can't find resource for bundle " + getClass().getName() + ", key " + getType(), str, getKey());
        } else if (findResourceWithFallback.getType() != 0 || !findResourceWithFallback.getString().equals("∅∅∅")) {
            return findResourceWithFallback;
        } else {
            throw new MissingResourceException("Encountered NO_INHERITANCE_MARKER", str, getKey());
        }
    }

    public ICUResourceBundle at(int i) {
        return (ICUResourceBundle) handleGet(i, (HashMap) null, this);
    }

    public ICUResourceBundle at(String str) {
        if (this instanceof ICUResourceBundleImpl.ResourceTable) {
            return (ICUResourceBundle) handleGet(str, (HashMap) null, this);
        }
        return null;
    }

    @Override // android.icu.util.UResourceBundle
    public ICUResourceBundle findTopLevel(String str) {
        return (ICUResourceBundle) super.findTopLevel(str);
    }

    public ICUResourceBundle findWithFallback(String str) {
        return findResourceWithFallback(str, this, null);
    }

    public String findStringWithFallback(String str) {
        return findStringWithFallback(str, this, null);
    }

    public String getStringWithFallback(String str) {
        String findStringWithFallback = findStringWithFallback(str, this, null);
        if (findStringWithFallback == null) {
            throw new MissingResourceException("Can't find resource for bundle " + getClass().getName() + ", key " + getType(), str, getKey());
        } else if (!findStringWithFallback.equals("∅∅∅")) {
            return findStringWithFallback;
        } else {
            throw new MissingResourceException("Encountered NO_INHERITANCE_MARKER", str, getKey());
        }
    }

    public void getAllItemsWithFallbackNoFail(String str, UResource$Sink uResource$Sink) {
        try {
            getAllItemsWithFallback(str, uResource$Sink);
        } catch (MissingResourceException unused) {
        }
    }

    public void getAllItemsWithFallback(String str, UResource$Sink uResource$Sink) {
        int countPathKeys = countPathKeys(str);
        if (countPathKeys != 0) {
            int resDepth = getResDepth();
            String[] strArr = new String[(resDepth + countPathKeys)];
            getResPathKeys(str, countPathKeys, strArr, resDepth);
            ICUResourceBundle findResourceWithFallback = findResourceWithFallback(strArr, resDepth, this, null);
            if (findResourceWithFallback != null) {
                this = findResourceWithFallback;
            } else {
                throw new MissingResourceException("Can't find resource for bundle " + getClass().getName() + ", key " + getType(), str, getKey());
            }
        }
        this.getAllItemsWithFallback(new UResource$Key(), new ICUResourceBundleReader.ReaderValue(), uResource$Sink);
    }

    private void getAllItemsWithFallback(UResource$Key uResource$Key, ICUResourceBundleReader.ReaderValue readerValue, UResource$Sink uResource$Sink) {
        ICUResourceBundleImpl iCUResourceBundleImpl = (ICUResourceBundleImpl) this;
        readerValue.reader = iCUResourceBundleImpl.wholeBundle.reader;
        readerValue.res = iCUResourceBundleImpl.getResource();
        String str = this.key;
        if (str == null) {
            str = "";
        }
        uResource$Key.setString(str);
        uResource$Sink.put(uResource$Key, readerValue, this.parent == null);
        ResourceBundle resourceBundle = this.parent;
        if (resourceBundle != null) {
            ICUResourceBundle iCUResourceBundle = (ICUResourceBundle) resourceBundle;
            int resDepth = getResDepth();
            if (resDepth != 0) {
                String[] strArr = new String[resDepth];
                getResPathKeys(strArr, resDepth);
                iCUResourceBundle = findResourceWithFallback(strArr, 0, iCUResourceBundle, null);
            }
            if (iCUResourceBundle != null) {
                iCUResourceBundle.getAllItemsWithFallback(uResource$Key, readerValue, uResource$Sink);
            }
        }
    }

    public static Set getFullLocaleNameSet(String str, ClassLoader classLoader) {
        return getAvailEntry(str, classLoader).getFullLocaleNameSet();
    }

    @Override // android.icu.util.UResourceBundle, java.util.ResourceBundle
    public Locale getLocale() {
        return getULocale().toLocale();
    }

    private static final void addLocaleIDsFromIndexBundle(String str, ClassLoader classLoader, Set set) {
        try {
            UResourceBundleIterator iterator = ((ICUResourceBundle) ((ICUResourceBundle) UResourceBundle.instantiateBundle(str, "res_index", classLoader, true)).get("InstalledLocales")).getIterator();
            iterator.reset();
            while (iterator.hasNext()) {
                set.add(iterator.next().getKey());
            }
        } catch (MissingResourceException unused) {
            if (DEBUG) {
                PrintStream printStream = System.out;
                printStream.println("couldn't find " + str + '/' + "res_index" + ".res");
                Thread.dumpStack();
            }
        }
    }

    private static final void addBundleBaseNamesFromClassLoader(final String str, final ClassLoader classLoader, final Set set) {
        AccessController.doPrivileged(new PrivilegedAction() {
            /* class android.icu.impl.ICUResourceBundle.AnonymousClass2 */

            @Override // java.security.PrivilegedAction
            public Void run() {
                try {
                    Enumeration resources = ClassLoader.this.getResources(str);
                    if (resources == null) {
                        return null;
                    }
                    AnonymousClass1 r2 = new URLHandler.URLVisitor() {
                        /* class android.icu.impl.ICUResourceBundle.AnonymousClass2.AnonymousClass1 */

                        @Override // android.icu.impl.URLHandler.URLVisitor
                        public void visit(String str) {
                            if (str.endsWith(".res")) {
                                set.add(str.substring(0, str.length() - 4));
                            }
                        }
                    };
                    while (resources.hasMoreElements()) {
                        URL url = (URL) resources.nextElement();
                        URLHandler uRLHandler = URLHandler.get(url);
                        if (uRLHandler != null) {
                            uRLHandler.guide(r2, false);
                        } else if (ICUResourceBundle.DEBUG) {
                            PrintStream printStream = System.out;
                            printStream.println("handler for " + url + " is null");
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

    private static void addLocaleIDsFromListFile(String str, ClassLoader classLoader, Set set) {
        try {
            InputStream resourceAsStream = classLoader.getResourceAsStream(str + "fullLocaleNames.lst");
            if (resourceAsStream != null) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream, "ASCII"));
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            return;
                        }
                        if (readLine.length() != 0 && !readLine.startsWith("#")) {
                            set.add(readLine);
                        }
                    } finally {
                        bufferedReader.close();
                    }
                }
            }
        } catch (IOException unused) {
        }
    }

    /* access modifiers changed from: private */
    public static Set createFullLocaleNameSet(String str, ClassLoader classLoader) {
        String str2;
        String str3;
        if (str.endsWith("/")) {
            str2 = str;
        } else {
            str2 = str + "/";
        }
        HashSet hashSet = new HashSet();
        if (!ICUConfig.get("android.icu.impl.ICUResourceBundle.skipRuntimeLocaleResourceScan", "false").equalsIgnoreCase("true")) {
            addBundleBaseNamesFromClassLoader(str2, classLoader, hashSet);
            if (str.startsWith("android/icu/impl/data/icudt63b")) {
                if (str.length() == 30) {
                    str3 = "";
                } else {
                    str3 = str.charAt(30) == '/' ? str.substring(31) : null;
                }
                if (str3 != null) {
                    ICUBinary.addBaseNamesInFileFolder(str3, ".res", hashSet);
                }
            }
            hashSet.remove("res_index");
            Iterator it = hashSet.iterator();
            while (it.hasNext()) {
                String str4 = (String) it.next();
                if ((str4.length() == 1 || str4.length() > 3) && str4.indexOf(95) < 0) {
                    it.remove();
                }
            }
        }
        if (hashSet.isEmpty()) {
            if (DEBUG) {
                System.out.println("unable to enumerate data files in " + str);
            }
            addLocaleIDsFromListFile(str2, classLoader, hashSet);
        }
        if (hashSet.isEmpty()) {
            addLocaleIDsFromIndexBundle(str, classLoader, hashSet);
        }
        hashSet.remove("root");
        hashSet.add(ULocale.ROOT.toString());
        return Collections.unmodifiableSet(hashSet);
    }

    /* access modifiers changed from: private */
    public static final class AvailEntry {
        private volatile Set fullNameSet;
        private ClassLoader loader;
        private String prefix;

        AvailEntry(String str, ClassLoader classLoader) {
            this.prefix = str;
            this.loader = classLoader;
        }

        /* access modifiers changed from: package-private */
        public Set getFullLocaleNameSet() {
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

    private static AvailEntry getAvailEntry(String str, ClassLoader classLoader) {
        return (AvailEntry) GET_AVAILABLE_CACHE.getInstance(str, classLoader);
    }

    private static final ICUResourceBundle findResourceWithFallback(String str, UResourceBundle uResourceBundle, UResourceBundle uResourceBundle2) {
        if (str.length() == 0) {
            return null;
        }
        ICUResourceBundle iCUResourceBundle = (ICUResourceBundle) uResourceBundle;
        int resDepth = iCUResourceBundle.getResDepth();
        int countPathKeys = countPathKeys(str);
        String[] strArr = new String[(resDepth + countPathKeys)];
        getResPathKeys(str, countPathKeys, strArr, resDepth);
        return findResourceWithFallback(strArr, resDepth, iCUResourceBundle, uResourceBundle2);
    }

    private static final ICUResourceBundle findResourceWithFallback(String[] strArr, int i, ICUResourceBundle iCUResourceBundle, UResourceBundle uResourceBundle) {
        if (uResourceBundle == null) {
            uResourceBundle = iCUResourceBundle;
        }
        while (true) {
            int i2 = i + 1;
            ICUResourceBundle iCUResourceBundle2 = (ICUResourceBundle) iCUResourceBundle.handleGet(strArr[i], (HashMap) null, uResourceBundle);
            if (iCUResourceBundle2 == null) {
                int i3 = i2 - 1;
                ICUResourceBundle parent = iCUResourceBundle.getParent();
                if (parent == null) {
                    return null;
                }
                int resDepth = iCUResourceBundle.getResDepth();
                if (i3 != resDepth) {
                    String[] strArr2 = new String[((strArr.length - i3) + resDepth)];
                    System.arraycopy(strArr, i3, strArr2, resDepth, strArr.length - i3);
                    strArr = strArr2;
                }
                iCUResourceBundle.getResPathKeys(strArr, resDepth);
                iCUResourceBundle = parent;
                i = 0;
            } else if (i2 == strArr.length) {
                return iCUResourceBundle2;
            } else {
                iCUResourceBundle = iCUResourceBundle2;
                i = i2;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:48:0x00be  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00bd A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final java.lang.String findStringWithFallback(java.lang.String r13, android.icu.util.UResourceBundle r14, android.icu.util.UResourceBundle r15) {
        /*
        // Method dump skipped, instructions count: 202
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

    private void getResPathKeys(String[] strArr, int i) {
        while (i > 0) {
            i--;
            strArr[i] = this.key;
            this = this.container;
        }
    }

    private static int countPathKeys(String str) {
        if (str.isEmpty()) {
            return 0;
        }
        int i = 1;
        for (int i2 = 0; i2 < str.length(); i2++) {
            if (str.charAt(i2) == '/') {
                i++;
            }
        }
        return i;
    }

    private static void getResPathKeys(String str, int i, String[] strArr, int i2) {
        if (i != 0) {
            if (i == 1) {
                strArr[i2] = str;
                return;
            }
            int i3 = 0;
            while (true) {
                int indexOf = str.indexOf(47, i3);
                int i4 = i2 + 1;
                strArr[i2] = str.substring(i3, indexOf);
                if (i == 2) {
                    strArr[i4] = str.substring(indexOf + 1);
                    return;
                }
                i3 = indexOf + 1;
                i--;
                i2 = i4;
            }
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ICUResourceBundle)) {
            return false;
        }
        ICUResourceBundle iCUResourceBundle = (ICUResourceBundle) obj;
        if (!getBaseName().equals(iCUResourceBundle.getBaseName()) || !getLocaleID().equals(iCUResourceBundle.getLocaleID())) {
            return false;
        }
        return true;
    }

    public static ICUResourceBundle getBundleInstance(String str, String str2, ClassLoader classLoader, boolean z) {
        return getBundleInstance(str, str2, classLoader, z ? OpenType.DIRECT : OpenType.LOCALE_DEFAULT_ROOT);
    }

    public static ICUResourceBundle getBundleInstance(String str, ULocale uLocale, OpenType openType) {
        if (uLocale == null) {
            uLocale = ULocale.getDefault();
        }
        return getBundleInstance(str, uLocale.getBaseName(), ICU_DATA_CLASS_LOADER, openType);
    }

    public static ICUResourceBundle getBundleInstance(String str, String str2, ClassLoader classLoader, OpenType openType) {
        ICUResourceBundle iCUResourceBundle;
        if (str == null) {
            str = "android/icu/impl/data/icudt63b";
        }
        String baseName = ULocale.getBaseName(str2);
        if (openType == OpenType.LOCALE_DEFAULT_ROOT) {
            iCUResourceBundle = instantiateBundle(str, baseName, ULocale.getDefault().getBaseName(), classLoader, openType);
        } else {
            iCUResourceBundle = instantiateBundle(str, baseName, null, classLoader, openType);
        }
        if (iCUResourceBundle != null) {
            return iCUResourceBundle;
        }
        throw new MissingResourceException("Could not find the bundle " + str + "/" + baseName + ".res", "", "");
    }

    /* access modifiers changed from: private */
    public static boolean localeIDStartsWithLangSubtag(String str, String str2) {
        return str.startsWith(str2) && (str.length() == str2.length() || str.charAt(str2.length()) == '_');
    }

    /* access modifiers changed from: private */
    public static ICUResourceBundle instantiateBundle(final String str, final String str2, final String str3, final ClassLoader classLoader, final OpenType openType) {
        String str4;
        final String fullName = ICUResourceBundleReader.getFullName(str, str2);
        char ordinal = (char) (openType.ordinal() + 48);
        if (openType != OpenType.LOCALE_DEFAULT_ROOT) {
            str4 = fullName + '#' + ordinal;
        } else {
            str4 = fullName + '#' + ordinal + '#' + str3;
        }
        return (ICUResourceBundle) BUNDLE_CACHE.getInstance(str4, new Loader() {
            /* class android.icu.impl.ICUResourceBundle.AnonymousClass4 */

            @Override // android.icu.impl.ICUResourceBundle.Loader
            public ICUResourceBundle load() {
                ICUResourceBundle createBundle;
                if (ICUResourceBundle.DEBUG) {
                    PrintStream printStream = System.out;
                    printStream.println("Creating " + String.this);
                }
                String str = str.indexOf(46) == -1 ? "root" : "";
                String str2 = str2.isEmpty() ? str : str2;
                ICUResourceBundle createBundle2 = ICUResourceBundle.createBundle(str, str2, classLoader);
                if (ICUResourceBundle.DEBUG) {
                    PrintStream printStream2 = System.out;
                    StringBuilder sb = new StringBuilder();
                    sb.append("The bundle created is: ");
                    sb.append(createBundle2);
                    sb.append(" and openType=");
                    sb.append(openType);
                    sb.append(" and bundle.getNoFallback=");
                    sb.append(createBundle2 != null && createBundle2.getNoFallback());
                    printStream2.println(sb.toString());
                }
                if (openType == OpenType.DIRECT) {
                    return createBundle2;
                }
                if (createBundle2 != null && createBundle2.getNoFallback()) {
                    return createBundle2;
                }
                if (createBundle2 == null) {
                    int lastIndexOf = str2.lastIndexOf(95);
                    if (lastIndexOf != -1) {
                        createBundle = ICUResourceBundle.instantiateBundle(str, str2.substring(0, lastIndexOf), str3, classLoader, openType);
                    } else if (openType == OpenType.LOCALE_DEFAULT_ROOT && !ICUResourceBundle.localeIDStartsWithLangSubtag(str3, str2)) {
                        String str3 = str;
                        String str4 = str3;
                        createBundle = ICUResourceBundle.instantiateBundle(str3, str4, str4, classLoader, openType);
                    } else if (openType == OpenType.LOCALE_ONLY || str.isEmpty()) {
                        return createBundle2;
                    } else {
                        createBundle = ICUResourceBundle.createBundle(str, str, classLoader);
                    }
                    return createBundle;
                }
                ICUResourceBundle iCUResourceBundle = null;
                String localeID = createBundle2.getLocaleID();
                int lastIndexOf2 = localeID.lastIndexOf(95);
                String findString = ((ICUResourceBundleImpl.ResourceTable) createBundle2).findString("%%Parent");
                if (findString != null) {
                    iCUResourceBundle = ICUResourceBundle.instantiateBundle(str, findString, str3, classLoader, openType);
                } else if (lastIndexOf2 != -1) {
                    iCUResourceBundle = ICUResourceBundle.instantiateBundle(str, localeID.substring(0, lastIndexOf2), str3, classLoader, openType);
                } else if (!localeID.equals(str)) {
                    iCUResourceBundle = ICUResourceBundle.instantiateBundle(str, str, str3, classLoader, openType);
                }
                if (createBundle2.equals(iCUResourceBundle)) {
                    return createBundle2;
                }
                createBundle2.setParent(iCUResourceBundle);
                return createBundle2;
            }
        });
    }

    /* access modifiers changed from: package-private */
    public ICUResourceBundle get(String str, HashMap hashMap, UResourceBundle uResourceBundle) {
        ICUResourceBundle iCUResourceBundle = (ICUResourceBundle) handleGet(str, hashMap, uResourceBundle);
        if (iCUResourceBundle == null) {
            iCUResourceBundle = getParent();
            if (iCUResourceBundle != null) {
                iCUResourceBundle = iCUResourceBundle.get(str, hashMap, uResourceBundle);
            }
            if (iCUResourceBundle == null) {
                String fullName = ICUResourceBundleReader.getFullName(getBaseName(), getLocaleID());
                throw new MissingResourceException("Can't find resource for bundle " + fullName + ", key " + str, getClass().getName(), str);
            }
        }
        return iCUResourceBundle;
    }

    public static ICUResourceBundle createBundle(String str, String str2, ClassLoader classLoader) {
        ICUResourceBundleReader reader = ICUResourceBundleReader.getReader(str, str2, classLoader);
        if (reader == null) {
            return null;
        }
        return getBundle(reader, str, str2, classLoader);
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

    @Override // android.icu.util.UResourceBundle
    public ICUResourceBundle getParent() {
        return (ICUResourceBundle) this.parent;
    }

    /* access modifiers changed from: protected */
    @Override // java.util.ResourceBundle
    public void setParent(ResourceBundle resourceBundle) {
        this.parent = resourceBundle;
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

    private static ICUResourceBundle getBundle(ICUResourceBundleReader iCUResourceBundleReader, String str, String str2, ClassLoader classLoader) {
        int rootResource = iCUResourceBundleReader.getRootResource();
        if (ICUResourceBundleReader.URES_IS_TABLE(ICUResourceBundleReader.RES_GET_TYPE(rootResource))) {
            ICUResourceBundleImpl.ResourceTable resourceTable = new ICUResourceBundleImpl.ResourceTable(new WholeBundle(str, str2, classLoader, iCUResourceBundleReader), rootResource);
            String findString = resourceTable.findString("%%ALIAS");
            return findString != null ? (ICUResourceBundle) UResourceBundle.getBundleInstance(str, findString) : resourceTable;
        }
        throw new IllegalStateException("Invalid format error");
    }

    protected ICUResourceBundle(WholeBundle wholeBundle2) {
        this.wholeBundle = wholeBundle2;
    }

    protected ICUResourceBundle(ICUResourceBundle iCUResourceBundle, String str) {
        this.key = str;
        this.wholeBundle = iCUResourceBundle.wholeBundle;
        this.container = iCUResourceBundle;
        this.parent = iCUResourceBundle.parent;
    }

    protected static ICUResourceBundle getAliasedResource(ICUResourceBundle iCUResourceBundle, String[] strArr, int i, String str, int i2, HashMap hashMap, UResourceBundle uResourceBundle) {
        String str2;
        String str3;
        String str4;
        String[] strArr2;
        int i3;
        int indexOf;
        WholeBundle wholeBundle2 = iCUResourceBundle.wholeBundle;
        ClassLoader classLoader = wholeBundle2.loader;
        String alias = wholeBundle2.reader.getAlias(i2);
        HashMap hashMap2 = hashMap == null ? new HashMap() : hashMap;
        if (hashMap2.get(alias) == null) {
            hashMap2.put(alias, "");
            ICUResourceBundle iCUResourceBundle2 = null;
            int i4 = 0;
            if (alias.indexOf(47) == 0) {
                int indexOf2 = alias.indexOf(47, 1);
                int i5 = indexOf2 + 1;
                int indexOf3 = alias.indexOf(47, i5);
                str3 = alias.substring(1, indexOf2);
                if (indexOf3 < 0) {
                    str2 = alias.substring(i5);
                    str4 = null;
                } else {
                    str2 = alias.substring(i5, indexOf3);
                    str4 = alias.substring(indexOf3 + 1, alias.length());
                }
                if (str3.equals("ICUDATA")) {
                    classLoader = ICU_DATA_CLASS_LOADER;
                    str3 = "android/icu/impl/data/icudt63b";
                } else if (str3.indexOf("ICUDATA") > -1 && (indexOf = str3.indexOf(45)) > -1) {
                    str3 = "android/icu/impl/data/icudt63b/" + str3.substring(indexOf + 1, str3.length());
                    classLoader = ICU_DATA_CLASS_LOADER;
                }
            } else {
                int indexOf4 = alias.indexOf(47);
                if (indexOf4 != -1) {
                    String substring = alias.substring(0, indexOf4);
                    str4 = alias.substring(indexOf4 + 1);
                    str2 = substring;
                } else {
                    str2 = alias;
                    str4 = null;
                }
                str3 = wholeBundle2.baseName;
            }
            if (str3.equals("LOCALE")) {
                String str5 = wholeBundle2.baseName;
                String substring2 = alias.substring(8, alias.length());
                ICUResourceBundle iCUResourceBundle3 = (ICUResourceBundle) uResourceBundle;
                while (true) {
                    ICUResourceBundle iCUResourceBundle4 = iCUResourceBundle3.container;
                    if (iCUResourceBundle4 == null) {
                        break;
                    }
                    iCUResourceBundle3 = iCUResourceBundle4;
                }
                iCUResourceBundle2 = findResourceWithFallback(substring2, iCUResourceBundle3, null);
            } else {
                ICUResourceBundle bundleInstance = getBundleInstance(str3, str2, classLoader, false);
                if (str4 != null) {
                    i3 = countPathKeys(str4);
                    if (i3 > 0) {
                        strArr2 = new String[i3];
                        getResPathKeys(str4, i3, strArr2, 0);
                    } else {
                        strArr2 = strArr;
                    }
                } else if (strArr != null) {
                    strArr2 = strArr;
                    i3 = i;
                } else {
                    int resDepth = iCUResourceBundle.getResDepth();
                    int i6 = resDepth + 1;
                    String[] strArr3 = new String[i6];
                    iCUResourceBundle.getResPathKeys(strArr3, resDepth);
                    strArr3[resDepth] = str;
                    i3 = i6;
                    strArr2 = strArr3;
                }
                if (i3 > 0) {
                    iCUResourceBundle2 = bundleInstance;
                    while (iCUResourceBundle2 != null && i4 < i3) {
                        iCUResourceBundle2 = iCUResourceBundle2.get(strArr2[i4], hashMap2, uResourceBundle);
                        i4++;
                    }
                }
            }
            if (iCUResourceBundle2 != null) {
                return iCUResourceBundle2;
            }
            throw new MissingResourceException(wholeBundle2.localeID, wholeBundle2.baseName, str);
        }
        throw new IllegalArgumentException("Circular references in the resource bundles");
    }

    public final Set getTopLevelKeySet() {
        return this.wholeBundle.topLevelKeys;
    }

    public final void setTopLevelKeySet(Set set) {
        this.wholeBundle.topLevelKeys = set;
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.util.UResourceBundle
    public boolean isTopLevelResource() {
        return this.container == null;
    }
}
