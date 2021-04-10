package sun.util.locale;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class InternalLocaleBuilder {
    private static final CaseInsensitiveChar PRIVATEUSE_KEY = new CaseInsensitiveChar("x");
    private Map extensions;
    private String language = "";
    private String region = "";
    private String script = "";
    private Set uattributes;
    private Map ukeywords;
    private String variant = "";

    private InternalLocaleBuilder setExtensions(List list, String str) {
        clearExtensions();
        if (!LocaleUtils.isEmpty(list)) {
            HashSet hashSet = new HashSet(list.size());
            Iterator it = list.iterator();
            while (it.hasNext()) {
                String str2 = (String) it.next();
                CaseInsensitiveChar caseInsensitiveChar = new CaseInsensitiveChar(str2);
                if (!hashSet.contains(caseInsensitiveChar)) {
                    if (UnicodeLocaleExtension.isSingletonChar(caseInsensitiveChar.value())) {
                        setUnicodeLocaleExtension(str2.substring(2));
                    } else {
                        if (this.extensions == null) {
                            this.extensions = new HashMap(4);
                        }
                        this.extensions.put(caseInsensitiveChar, str2.substring(2));
                    }
                }
                hashSet.add(caseInsensitiveChar);
            }
        }
        if (str != null && str.length() > 0) {
            if (this.extensions == null) {
                this.extensions = new HashMap(1);
            }
            this.extensions.put(new CaseInsensitiveChar(str), str.substring(2));
        }
        return this;
    }

    public InternalLocaleBuilder setLanguageTag(LanguageTag languageTag) {
        clear();
        if (!languageTag.getExtlangs().isEmpty()) {
            this.language = (String) languageTag.getExtlangs().get(0);
        } else {
            String language2 = languageTag.getLanguage();
            if (!language2.equals("und")) {
                this.language = language2;
            }
        }
        this.script = languageTag.getScript();
        this.region = languageTag.getRegion();
        List variants = languageTag.getVariants();
        if (!variants.isEmpty()) {
            StringBuilder sb = new StringBuilder((String) variants.get(0));
            int size = variants.size();
            for (int i = 1; i < size; i++) {
                sb.append("_");
                sb.append((String) variants.get(i));
            }
            this.variant = sb.toString();
        }
        setExtensions(languageTag.getExtensions(), languageTag.getPrivateuse());
        return this;
    }

    public InternalLocaleBuilder clear() {
        this.language = "";
        this.script = "";
        this.region = "";
        this.variant = "";
        clearExtensions();
        return this;
    }

    public InternalLocaleBuilder clearExtensions() {
        Map map = this.extensions;
        if (map != null) {
            map.clear();
        }
        Set set = this.uattributes;
        if (set != null) {
            set.clear();
        }
        Map map2 = this.ukeywords;
        if (map2 != null) {
            map2.clear();
        }
        return this;
    }

    public BaseLocale getBaseLocale() {
        String str;
        int i;
        String str2 = this.language;
        String str3 = this.script;
        String str4 = this.region;
        String str5 = this.variant;
        Map map = this.extensions;
        if (!(map == null || (str = (String) map.get(PRIVATEUSE_KEY)) == null)) {
            StringTokenIterator stringTokenIterator = new StringTokenIterator(str, "-");
            boolean z = false;
            while (true) {
                if (stringTokenIterator.isDone()) {
                    i = -1;
                    break;
                } else if (z) {
                    i = stringTokenIterator.currentStart();
                    break;
                } else {
                    if (LocaleUtils.caseIgnoreMatch(stringTokenIterator.current(), "lvariant")) {
                        z = true;
                    }
                    stringTokenIterator.next();
                }
            }
            if (i != -1) {
                StringBuilder sb = new StringBuilder(str5);
                if (sb.length() != 0) {
                    sb.append("_");
                }
                sb.append(str.substring(i).replaceAll("-", "_"));
                str5 = sb.toString();
            }
        }
        return BaseLocale.getInstance(str2, str3, str4, str5);
    }

    public LocaleExtensions getLocaleExtensions() {
        if (LocaleUtils.isEmpty(this.extensions) && LocaleUtils.isEmpty(this.uattributes) && LocaleUtils.isEmpty(this.ukeywords)) {
            return null;
        }
        LocaleExtensions localeExtensions = new LocaleExtensions(this.extensions, this.uattributes, this.ukeywords);
        if (localeExtensions.isEmpty()) {
            return null;
        }
        return localeExtensions;
    }

    static String removePrivateuseVariant(String str) {
        boolean z;
        StringTokenIterator stringTokenIterator = new StringTokenIterator(str, "-");
        int i = -1;
        while (true) {
            if (stringTokenIterator.isDone()) {
                z = false;
                break;
            } else if (i != -1) {
                z = true;
                break;
            } else {
                if (LocaleUtils.caseIgnoreMatch(stringTokenIterator.current(), "lvariant")) {
                    i = stringTokenIterator.currentStart();
                }
                stringTokenIterator.next();
            }
        }
        if (!z) {
            return str;
        }
        if (i == 0) {
            return null;
        }
        return str.substring(0, i - 1);
    }

    private void setUnicodeLocaleExtension(String str) {
        String str2;
        Set set = this.uattributes;
        if (set != null) {
            set.clear();
        }
        Map map = this.ukeywords;
        if (map != null) {
            map.clear();
        }
        StringTokenIterator stringTokenIterator = new StringTokenIterator(str, "-");
        while (!stringTokenIterator.isDone() && UnicodeLocaleExtension.isAttribute(stringTokenIterator.current())) {
            if (this.uattributes == null) {
                this.uattributes = new HashSet(4);
            }
            this.uattributes.add(new CaseInsensitiveString(stringTokenIterator.current()));
            stringTokenIterator.next();
        }
        CaseInsensitiveString caseInsensitiveString = null;
        int i = -1;
        int i2 = -1;
        while (!stringTokenIterator.isDone()) {
            String str3 = "";
            if (caseInsensitiveString != null) {
                if (UnicodeLocaleExtension.isKey(stringTokenIterator.current())) {
                    if (i == -1) {
                        str2 = str3;
                    } else {
                        str2 = str.substring(i, i2);
                    }
                    if (this.ukeywords == null) {
                        this.ukeywords = new HashMap(4);
                    }
                    this.ukeywords.put(caseInsensitiveString, str2);
                    caseInsensitiveString = new CaseInsensitiveString(stringTokenIterator.current());
                    if (this.ukeywords.containsKey(caseInsensitiveString)) {
                        caseInsensitiveString = null;
                    }
                    i = -1;
                    i2 = -1;
                } else {
                    if (i == -1) {
                        i = stringTokenIterator.currentStart();
                    }
                    i2 = stringTokenIterator.currentEnd();
                }
            } else if (UnicodeLocaleExtension.isKey(stringTokenIterator.current())) {
                caseInsensitiveString = new CaseInsensitiveString(stringTokenIterator.current());
                Map map2 = this.ukeywords;
                if (map2 != null && map2.containsKey(caseInsensitiveString)) {
                    caseInsensitiveString = null;
                }
            }
            if (stringTokenIterator.hasNext()) {
                stringTokenIterator.next();
            } else if (caseInsensitiveString != null) {
                if (i != -1) {
                    str3 = str.substring(i, i2);
                }
                if (this.ukeywords == null) {
                    this.ukeywords = new HashMap(4);
                }
                this.ukeywords.put(caseInsensitiveString, str3);
                return;
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static final class CaseInsensitiveString {
        private final String lowerStr;
        private final String str;

        CaseInsensitiveString(String str2) {
            this.str = str2;
            LocaleUtils.toLowerString(str2);
            this.lowerStr = str2;
        }

        public String value() {
            return this.str;
        }

        public int hashCode() {
            return this.lowerStr.hashCode();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof CaseInsensitiveString)) {
                return false;
            }
            return this.lowerStr.equals(((CaseInsensitiveString) obj).lowerStr);
        }
    }

    /* access modifiers changed from: package-private */
    public static final class CaseInsensitiveChar {
        private final char ch;
        private final char lowerCh;

        private CaseInsensitiveChar(String str) {
            this(str.charAt(0));
        }

        CaseInsensitiveChar(char c) {
            this.ch = c;
            this.lowerCh = LocaleUtils.toLower(this.ch);
        }

        public char value() {
            return this.ch;
        }

        public int hashCode() {
            return this.lowerCh;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof CaseInsensitiveChar)) {
                return false;
            }
            return this.lowerCh == ((CaseInsensitiveChar) obj).lowerCh;
        }
    }
}
