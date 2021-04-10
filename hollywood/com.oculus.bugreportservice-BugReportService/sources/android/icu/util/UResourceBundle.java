package android.icu.util;

import android.icu.impl.ICUResourceBundle;
import android.icu.impl.ICUResourceBundleReader;
import android.icu.impl.ResourceBundleWrapper;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

public abstract class UResourceBundle extends ResourceBundle {
    private static Map ROOT_CACHE = new ConcurrentHashMap();

    /* access modifiers changed from: private */
    public enum RootType {
        MISSING,
        ICU,
        JAVA
    }

    /* access modifiers changed from: protected */
    public abstract String getBaseName();

    public String getKey() {
        return null;
    }

    /* access modifiers changed from: protected */
    public abstract String getLocaleID();

    /* access modifiers changed from: protected */
    public abstract UResourceBundle getParent();

    public int getSize() {
        return 1;
    }

    public int getType() {
        return -1;
    }

    public abstract ULocale getULocale();

    /* access modifiers changed from: protected */
    public UResourceBundle handleGet(int i, HashMap hashMap, UResourceBundle uResourceBundle) {
        return null;
    }

    /* access modifiers changed from: protected */
    public UResourceBundle handleGet(String str, HashMap hashMap, UResourceBundle uResourceBundle) {
        return null;
    }

    /* access modifiers changed from: protected */
    public String[] handleGetStringArray() {
        return null;
    }

    /* access modifiers changed from: protected */
    public boolean isTopLevelResource() {
        return true;
    }

    public static UResourceBundle getBundleInstance(String str, String str2) {
        return getBundleInstance(str, str2, ICUResourceBundle.ICU_DATA_CLASS_LOADER, false);
    }

    public static UResourceBundle getBundleInstance(String str, String str2, ClassLoader classLoader) {
        return getBundleInstance(str, str2, classLoader, false);
    }

    protected static UResourceBundle getBundleInstance(String str, String str2, ClassLoader classLoader, boolean z) {
        return instantiateBundle(str, str2, classLoader, z);
    }

    public static UResourceBundle getBundleInstance(String str) {
        if (str == null) {
            str = "android/icu/impl/data/icudt63b";
        }
        return getBundleInstance(str, ULocale.getDefault().getBaseName(), ICUResourceBundle.ICU_DATA_CLASS_LOADER, false);
    }

    public static UResourceBundle getBundleInstance(String str, Locale locale) {
        if (str == null) {
            str = "android/icu/impl/data/icudt63b";
        }
        return getBundleInstance(str, (locale == null ? ULocale.getDefault() : ULocale.forLocale(locale)).getBaseName(), ICUResourceBundle.ICU_DATA_CLASS_LOADER, false);
    }

    public static UResourceBundle getBundleInstance(String str, ULocale uLocale) {
        if (str == null) {
            str = "android/icu/impl/data/icudt63b";
        }
        if (uLocale == null) {
            uLocale = ULocale.getDefault();
        }
        return getBundleInstance(str, uLocale.getBaseName(), ICUResourceBundle.ICU_DATA_CLASS_LOADER, false);
    }

    @Override // java.util.ResourceBundle
    public Locale getLocale() {
        return getULocale().toLocale();
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:7:0x0019 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0, types: [java.lang.ClassLoader] */
    /* JADX WARN: Type inference failed for: r3v1, types: [java.lang.ClassLoader] */
    /* JADX WARN: Type inference failed for: r3v6, types: [android.icu.util.UResourceBundle$RootType] */
    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        android.icu.impl.ResourceBundleWrapper.getBundleInstance(r2, r0, (java.lang.ClassLoader) r3, true);
        r3 = android.icu.util.UResourceBundle.RootType.JAVA;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0026, code lost:
        r3 = android.icu.util.UResourceBundle.RootType.MISSING;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x0020 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.icu.util.UResourceBundle.RootType getRootType(java.lang.String r2, java.lang.ClassLoader r3) {
        /*
            java.util.Map r0 = android.icu.util.UResourceBundle.ROOT_CACHE
            java.lang.Object r0 = r0.get(r2)
            android.icu.util.UResourceBundle$RootType r0 = (android.icu.util.UResourceBundle.RootType) r0
            if (r0 != 0) goto L_0x002e
            r0 = 46
            int r0 = r2.indexOf(r0)
            r1 = -1
            if (r0 != r1) goto L_0x0016
            java.lang.String r0 = "root"
            goto L_0x0018
        L_0x0016:
            java.lang.String r0 = ""
        L_0x0018:
            r1 = 1
            android.icu.impl.ICUResourceBundle.getBundleInstance(r2, r0, r3, r1)     // Catch:{ MissingResourceException -> 0x0020 }
            android.icu.util.UResourceBundle$RootType r3 = android.icu.util.UResourceBundle.RootType.ICU     // Catch:{ MissingResourceException -> 0x0020 }
        L_0x001e:
            r0 = r3
            goto L_0x0029
        L_0x0020:
            android.icu.impl.ResourceBundleWrapper.getBundleInstance(r2, r0, r3, r1)     // Catch:{ MissingResourceException -> 0x0026 }
            android.icu.util.UResourceBundle$RootType r3 = android.icu.util.UResourceBundle.RootType.JAVA     // Catch:{ MissingResourceException -> 0x0026 }
            goto L_0x001e
        L_0x0026:
            android.icu.util.UResourceBundle$RootType r3 = android.icu.util.UResourceBundle.RootType.MISSING
            goto L_0x001e
        L_0x0029:
            java.util.Map r3 = android.icu.util.UResourceBundle.ROOT_CACHE
            r3.put(r2, r0)
        L_0x002e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.util.UResourceBundle.getRootType(java.lang.String, java.lang.ClassLoader):android.icu.util.UResourceBundle$RootType");
    }

    private static void setRootType(String str, RootType rootType) {
        ROOT_CACHE.put(str, rootType);
    }

    protected static UResourceBundle instantiateBundle(String str, String str2, ClassLoader classLoader, boolean z) {
        int i = AnonymousClass1.$SwitchMap$android$icu$util$UResourceBundle$RootType[getRootType(str, classLoader).ordinal()];
        if (i == 1) {
            return ICUResourceBundle.getBundleInstance(str, str2, classLoader, z);
        }
        if (i == 2) {
            return ResourceBundleWrapper.getBundleInstance(str, str2, classLoader, z);
        }
        try {
            ICUResourceBundle bundleInstance = ICUResourceBundle.getBundleInstance(str, str2, classLoader, z);
            setRootType(str, RootType.ICU);
            return bundleInstance;
        } catch (MissingResourceException unused) {
            ResourceBundleWrapper bundleInstance2 = ResourceBundleWrapper.getBundleInstance(str, str2, classLoader, z);
            setRootType(str, RootType.JAVA);
            return bundleInstance2;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: android.icu.util.UResourceBundle$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$icu$util$UResourceBundle$RootType = new int[RootType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            /*
                android.icu.util.UResourceBundle$RootType[] r0 = android.icu.util.UResourceBundle.RootType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                android.icu.util.UResourceBundle.AnonymousClass1.$SwitchMap$android$icu$util$UResourceBundle$RootType = r0
                int[] r0 = android.icu.util.UResourceBundle.AnonymousClass1.$SwitchMap$android$icu$util$UResourceBundle$RootType     // Catch:{ NoSuchFieldError -> 0x0014 }
                android.icu.util.UResourceBundle$RootType r1 = android.icu.util.UResourceBundle.RootType.ICU     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = android.icu.util.UResourceBundle.AnonymousClass1.$SwitchMap$android$icu$util$UResourceBundle$RootType     // Catch:{ NoSuchFieldError -> 0x001f }
                android.icu.util.UResourceBundle$RootType r1 = android.icu.util.UResourceBundle.RootType.JAVA     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = android.icu.util.UResourceBundle.AnonymousClass1.$SwitchMap$android$icu$util$UResourceBundle$RootType     // Catch:{ NoSuchFieldError -> 0x002a }
                android.icu.util.UResourceBundle$RootType r1 = android.icu.util.UResourceBundle.RootType.MISSING     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: android.icu.util.UResourceBundle.AnonymousClass1.<clinit>():void");
        }
    }

    public ByteBuffer getBinary() {
        throw new UResourceTypeMismatchException("");
    }

    public String getString() {
        throw new UResourceTypeMismatchException("");
    }

    public String[] getStringArray() {
        throw new UResourceTypeMismatchException("");
    }

    public byte[] getBinary(byte[] bArr) {
        throw new UResourceTypeMismatchException("");
    }

    public int[] getIntVector() {
        throw new UResourceTypeMismatchException("");
    }

    public int getInt() {
        throw new UResourceTypeMismatchException("");
    }

    public UResourceBundle get(String str) {
        UResourceBundle findTopLevel = findTopLevel(str);
        if (findTopLevel != null) {
            return findTopLevel;
        }
        String fullName = ICUResourceBundleReader.getFullName(getBaseName(), getLocaleID());
        throw new MissingResourceException("Can't find resource for bundle " + fullName + ", key " + str, getClass().getName(), str);
    }

    /* access modifiers changed from: protected */
    public UResourceBundle findTopLevel(String str) {
        for (UResourceBundle uResourceBundle = this; uResourceBundle != null; uResourceBundle = uResourceBundle.getParent()) {
            UResourceBundle handleGet = uResourceBundle.handleGet(str, (HashMap) null, this);
            if (handleGet != null) {
                return handleGet;
            }
        }
        return null;
    }

    public String getString(int i) {
        ICUResourceBundle iCUResourceBundle = (ICUResourceBundle) get(i);
        if (iCUResourceBundle.getType() == 0) {
            return iCUResourceBundle.getString();
        }
        throw new UResourceTypeMismatchException("");
    }

    public UResourceBundle get(int i) {
        UResourceBundle handleGet = handleGet(i, (HashMap) null, this);
        if (handleGet == null) {
            handleGet = getParent();
            if (handleGet != null) {
                handleGet = handleGet.get(i);
            }
            if (handleGet == null) {
                throw new MissingResourceException("Can't find resource for bundle " + getClass().getName() + ", key " + getKey(), getClass().getName(), getKey());
            }
        }
        return handleGet;
    }

    @Override // java.util.ResourceBundle
    public Enumeration getKeys() {
        return Collections.enumeration(keySet());
    }

    public Set keySet() {
        Set set;
        TreeSet treeSet;
        ICUResourceBundle iCUResourceBundle = null;
        if (!isTopLevelResource() || !(this instanceof ICUResourceBundle)) {
            set = null;
        } else {
            iCUResourceBundle = (ICUResourceBundle) this;
            set = iCUResourceBundle.getTopLevelKeySet();
        }
        if (set == null) {
            if (!isTopLevelResource()) {
                return handleKeySet();
            }
            ResourceBundle resourceBundle = this.parent;
            if (resourceBundle == null) {
                treeSet = new TreeSet();
            } else if (resourceBundle instanceof UResourceBundle) {
                treeSet = new TreeSet(((UResourceBundle) resourceBundle).keySet());
            } else {
                treeSet = new TreeSet();
                Enumeration keys = this.parent.getKeys();
                while (keys.hasMoreElements()) {
                    treeSet.add((String) keys.nextElement());
                }
            }
            treeSet.addAll(handleKeySet());
            set = Collections.unmodifiableSet(treeSet);
            if (iCUResourceBundle != null) {
                iCUResourceBundle.setTopLevelKeySet(set);
            }
        }
        return set;
    }

    /* access modifiers changed from: protected */
    public Set handleKeySet() {
        return Collections.emptySet();
    }

    public UResourceBundleIterator getIterator() {
        return new UResourceBundleIterator(this);
    }

    /* access modifiers changed from: protected */
    @Override // java.util.ResourceBundle
    public Object handleGetObject(String str) {
        return handleGetObjectImpl(str, this);
    }

    private Object handleGetObjectImpl(String str, UResourceBundle uResourceBundle) {
        Object resolveObject = resolveObject(str, uResourceBundle);
        if (resolveObject == null) {
            UResourceBundle parent = getParent();
            if (parent != null) {
                resolveObject = parent.handleGetObjectImpl(str, uResourceBundle);
            }
            if (resolveObject == null) {
                throw new MissingResourceException("Can't find resource for bundle " + getClass().getName() + ", key " + str, getClass().getName(), str);
            }
        }
        return resolveObject;
    }

    private Object resolveObject(String str, UResourceBundle uResourceBundle) {
        if (getType() == 0) {
            return getString();
        }
        UResourceBundle handleGet = handleGet(str, (HashMap) null, uResourceBundle);
        if (handleGet == null) {
            return handleGet;
        }
        if (handleGet.getType() == 0) {
            return handleGet.getString();
        }
        try {
            return handleGet.getType() == 8 ? handleGet.handleGetStringArray() : handleGet;
        } catch (UResourceTypeMismatchException unused) {
            return handleGet;
        }
    }
}
