package android.icu.impl;

import android.icu.impl.ICUService;
import android.icu.util.ULocale;
import java.util.Set;

public class ICULocaleService extends ICUService {
    private ULocale fallbackLocale;
    private String fallbackLocaleName;

    public ICULocaleService(String str) {
        super(str);
    }

    public Object get(ULocale uLocale, ULocale[] uLocaleArr) {
        return get(uLocale, -1, uLocaleArr);
    }

    public Object get(ULocale uLocale, int i, ULocale[] uLocaleArr) {
        ICUService.Key createKey = createKey(uLocale, i);
        if (uLocaleArr == null) {
            return getKey(createKey);
        }
        String[] strArr = new String[1];
        Object key = getKey(createKey, strArr);
        if (key != null) {
            int indexOf = strArr[0].indexOf("/");
            if (indexOf >= 0) {
                strArr[0] = strArr[0].substring(indexOf + 1);
            }
            uLocaleArr[0] = new ULocale(strArr[0]);
        }
        return key;
    }

    public static class LocaleKey extends ICUService.Key {
        private String currentID;
        private String fallbackID;
        private int kind;
        private String primaryID;
        private int varstart;

        public static LocaleKey createWithCanonical(ULocale uLocale, String str, int i) {
            if (uLocale == null) {
                return null;
            }
            String name = uLocale.getName();
            return new LocaleKey(name, name, str, i);
        }

        protected LocaleKey(String str, String str2, String str3, int i) {
            super(str);
            this.kind = i;
            if (str2 == null || str2.equalsIgnoreCase("root")) {
                this.primaryID = "";
                this.fallbackID = null;
            } else {
                int indexOf = str2.indexOf(64);
                if (indexOf != 4 || !str2.regionMatches(true, 0, "root", 0, 4)) {
                    this.primaryID = str2;
                    this.varstart = indexOf;
                    if (str3 == null || this.primaryID.equals(str3)) {
                        this.fallbackID = "";
                    } else {
                        this.fallbackID = str3;
                    }
                } else {
                    this.primaryID = str2.substring(4);
                    this.varstart = 0;
                    this.fallbackID = null;
                }
            }
            int i2 = this.varstart;
            this.currentID = i2 == -1 ? this.primaryID : this.primaryID.substring(0, i2);
        }

        public String prefix() {
            if (this.kind == -1) {
                return null;
            }
            return Integer.toString(kind());
        }

        public int kind() {
            return this.kind;
        }

        @Override // android.icu.impl.ICUService.Key
        public String canonicalID() {
            return this.primaryID;
        }

        @Override // android.icu.impl.ICUService.Key
        public String currentID() {
            return this.currentID;
        }

        @Override // android.icu.impl.ICUService.Key
        public String currentDescriptor() {
            String currentID2 = currentID();
            if (currentID2 == null) {
                return currentID2;
            }
            StringBuilder sb = new StringBuilder();
            if (this.kind != -1) {
                sb.append(prefix());
            }
            sb.append('/');
            sb.append(currentID2);
            int i = this.varstart;
            if (i != -1) {
                String str = this.primaryID;
                sb.append(str.substring(i, str.length()));
            }
            return sb.toString();
        }

        public ULocale currentLocale() {
            if (this.varstart == -1) {
                return new ULocale(this.currentID);
            }
            return new ULocale(this.currentID + this.primaryID.substring(this.varstart));
        }

        @Override // android.icu.impl.ICUService.Key
        public boolean fallback() {
            int lastIndexOf = this.currentID.lastIndexOf(95);
            if (lastIndexOf != -1) {
                do {
                    lastIndexOf--;
                    if (lastIndexOf < 0) {
                        break;
                    }
                } while (this.currentID.charAt(lastIndexOf) == '_');
                this.currentID = this.currentID.substring(0, lastIndexOf + 1);
                return true;
            }
            String str = this.fallbackID;
            if (str != null) {
                this.currentID = str;
                if (str.length() == 0) {
                    this.fallbackID = null;
                } else {
                    this.fallbackID = "";
                }
                return true;
            }
            this.currentID = null;
            return false;
        }
    }

    public static abstract class LocaleKeyFactory implements ICUService.Factory {
        protected final String name = null;
        protected final boolean visible;

        /* access modifiers changed from: protected */
        public abstract Set getSupportedIDs();

        /* access modifiers changed from: protected */
        public abstract Object handleCreate(ULocale uLocale, int i, ICUService iCUService);

        protected LocaleKeyFactory(boolean z) {
            this.visible = z;
        }

        @Override // android.icu.impl.ICUService.Factory
        public Object create(ICUService.Key key, ICUService iCUService) {
            if (!handlesKey(key)) {
                return null;
            }
            LocaleKey localeKey = (LocaleKey) key;
            return handleCreate(localeKey.currentLocale(), localeKey.kind(), iCUService);
        }

        /* access modifiers changed from: protected */
        public boolean handlesKey(ICUService.Key key) {
            if (key == null) {
                return false;
            }
            return getSupportedIDs().contains(key.currentID());
        }

        public String toString() {
            super.toString();
            throw null;
        }
    }

    public static class ICUResourceBundleFactory extends LocaleKeyFactory {
        protected final String bundleName;

        public ICUResourceBundleFactory() {
            this("android/icu/impl/data/icudt63b");
        }

        public ICUResourceBundleFactory(String str) {
            super(true);
            this.bundleName = str;
        }

        /* access modifiers changed from: protected */
        @Override // android.icu.impl.ICULocaleService.LocaleKeyFactory
        public Set getSupportedIDs() {
            return ICUResourceBundle.getFullLocaleNameSet(this.bundleName, loader());
        }

        /* access modifiers changed from: protected */
        public ClassLoader loader() {
            return ClassLoaderUtil.getClassLoader(getClass());
        }

        @Override // android.icu.impl.ICULocaleService.LocaleKeyFactory
        public String toString() {
            new StringBuilder();
            super.toString();
            throw null;
        }
    }

    public String validateFallbackLocale() {
        ULocale uLocale = ULocale.getDefault();
        if (uLocale != this.fallbackLocale) {
            synchronized (this) {
                if (uLocale != this.fallbackLocale) {
                    this.fallbackLocale = uLocale;
                    this.fallbackLocaleName = uLocale.getBaseName();
                    clearServiceCache();
                }
            }
        }
        return this.fallbackLocaleName;
    }

    public ICUService.Key createKey(ULocale uLocale, int i) {
        return LocaleKey.createWithCanonical(uLocale, validateFallbackLocale(), i);
    }
}
