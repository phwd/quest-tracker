package android.icu.impl;

import android.icu.util.ULocale;
import android.icu.util.UResourceBundle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public final class ResourceBundleWrapper extends UResourceBundle {
    private static CacheBase BUNDLE_CACHE = new SoftCache() {
        /* class android.icu.impl.ResourceBundleWrapper.AnonymousClass1 */

        /* access modifiers changed from: protected */
        public ResourceBundleWrapper createInstance(String str, Loader loader) {
            return loader.load();
        }
    };
    private static final boolean DEBUG = ICUDebug.enabled("resourceBundleWrapper");
    private String baseName;
    private ResourceBundle bundle;
    private List keys;
    private String localeID;

    /* access modifiers changed from: private */
    public static abstract class Loader {
        /* access modifiers changed from: package-private */
        public abstract ResourceBundleWrapper load();

        private Loader() {
        }
    }

    private ResourceBundleWrapper(ResourceBundle resourceBundle) {
        this.bundle = null;
        this.localeID = null;
        this.baseName = null;
        this.keys = null;
        this.bundle = resourceBundle;
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.util.UResourceBundle, java.util.ResourceBundle
    public Object handleGetObject(String str) {
        Object obj;
        ResourceBundleWrapper resourceBundleWrapper = this;
        while (true) {
            if (resourceBundleWrapper == null) {
                obj = null;
                break;
            }
            try {
                obj = resourceBundleWrapper.bundle.getObject(str);
                break;
            } catch (MissingResourceException unused) {
                resourceBundleWrapper = (ResourceBundleWrapper) resourceBundleWrapper.getParent();
            }
        }
        if (obj != null) {
            return obj;
        }
        throw new MissingResourceException("Can't find resource for bundle " + this.baseName + ", key " + str, ResourceBundleWrapper.class.getName(), str);
    }

    @Override // android.icu.util.UResourceBundle, java.util.ResourceBundle
    public Enumeration getKeys() {
        return Collections.enumeration(this.keys);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void initKeysVector() {
        this.keys = new ArrayList();
        for (ResourceBundleWrapper resourceBundleWrapper = this; resourceBundleWrapper != null; resourceBundleWrapper = (ResourceBundleWrapper) resourceBundleWrapper.getParent()) {
            Enumeration keys2 = resourceBundleWrapper.bundle.getKeys();
            while (keys2.hasMoreElements()) {
                String str = (String) keys2.nextElement();
                if (!this.keys.contains(str)) {
                    this.keys.add(str);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.util.UResourceBundle
    public String getLocaleID() {
        return this.localeID;
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.util.UResourceBundle
    public String getBaseName() {
        return this.bundle.getClass().getName().replace('.', '/');
    }

    @Override // android.icu.util.UResourceBundle
    public ULocale getULocale() {
        return new ULocale(this.localeID);
    }

    @Override // android.icu.util.UResourceBundle
    public UResourceBundle getParent() {
        return (UResourceBundle) this.parent;
    }

    public static ResourceBundleWrapper getBundleInstance(String str, String str2, ClassLoader classLoader, boolean z) {
        ResourceBundleWrapper resourceBundleWrapper;
        if (classLoader == null) {
            classLoader = ClassLoaderUtil.getClassLoader();
        }
        if (z) {
            resourceBundleWrapper = instantiateBundle(str, str2, null, classLoader, z);
        } else {
            resourceBundleWrapper = instantiateBundle(str, str2, ULocale.getDefault().getBaseName(), classLoader, z);
        }
        if (resourceBundleWrapper != null) {
            return resourceBundleWrapper;
        }
        String str3 = str.indexOf(47) >= 0 ? "/" : "_";
        throw new MissingResourceException("Could not find the bundle " + str + str3 + str2, "", "");
    }

    /* access modifiers changed from: private */
    public static boolean localeIDStartsWithLangSubtag(String str, String str2) {
        return str.startsWith(str2) && (str.length() == str2.length() || str.charAt(str2.length()) == '_');
    }

    /* access modifiers changed from: private */
    public static ResourceBundleWrapper instantiateBundle(final String str, final String str2, final String str3, final ClassLoader classLoader, final boolean z) {
        final String str4;
        String str5;
        if (str2.isEmpty()) {
            str4 = str;
        } else {
            str4 = str + '_' + str2;
        }
        if (z) {
            str5 = str4;
        } else {
            str5 = str4 + '#' + str3;
        }
        return (ResourceBundleWrapper) BUNDLE_CACHE.getInstance(str5, new Loader() {
            /* class android.icu.impl.ResourceBundleWrapper.AnonymousClass2 */

            /* JADX WARNING: Can't wrap try/catch for region: R(5:41|46|47|48|49) */
            /* JADX WARNING: Code restructure failed: missing block: B:50:0x00da, code lost:
                r1 = e;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:51:0x00db, code lost:
                r8 = r3;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:70:0x0116, code lost:
                if (r14 == false) goto L_0x011c;
             */
            /* JADX WARNING: Failed to process nested try/catch */
            /* JADX WARNING: Missing exception handler attribute for start block: B:48:0x00d9 */
            /* JADX WARNING: Missing exception handler attribute for start block: B:52:0x00dd */
            /* JADX WARNING: Removed duplicated region for block: B:18:0x0070  */
            /* JADX WARNING: Removed duplicated region for block: B:21:0x007b  */
            /* JADX WARNING: Removed duplicated region for block: B:27:0x0085 A[SYNTHETIC, Splitter:B:27:0x0085] */
            /* JADX WARNING: Removed duplicated region for block: B:67:0x0112  */
            /* JADX WARNING: Removed duplicated region for block: B:78:0x0127  */
            /* JADX WARNING: Removed duplicated region for block: B:81:0x0132  */
            /* JADX WARNING: Removed duplicated region for block: B:83:0x0139  */
            /* JADX WARNING: Removed duplicated region for block: B:84:0x013d  */
            @Override // android.icu.impl.ResourceBundleWrapper.Loader
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public android.icu.impl.ResourceBundleWrapper load() {
                /*
                // Method dump skipped, instructions count: 358
                */
                throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.ResourceBundleWrapper.AnonymousClass2.load():android.icu.impl.ResourceBundleWrapper");
            }
        });
    }
}
