package android.icu.impl.locale;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public final class InternalLocaleBuilder {
    private static final CaseInsensitiveChar PRIVUSE_KEY = new CaseInsensitiveChar("x".charAt(0));
    private HashMap _extensions;
    private String _language = "";
    private String _region = "";
    private String _script = "";
    private HashSet _uattributes;
    private HashMap _ukeywords;
    private String _variant = "";

    public InternalLocaleBuilder addUnicodeLocaleAttribute(String str) {
        if (str == null || !UnicodeLocaleExtension.isAttribute(str)) {
            throw new LocaleSyntaxException("Ill-formed Unicode locale attribute: " + str);
        }
        if (this._uattributes == null) {
            this._uattributes = new HashSet(4);
        }
        this._uattributes.add(new CaseInsensitiveString(str));
        return this;
    }

    public InternalLocaleBuilder setUnicodeLocaleKeyword(String str, String str2) {
        if (UnicodeLocaleExtension.isKey(str)) {
            CaseInsensitiveString caseInsensitiveString = new CaseInsensitiveString(str);
            if (str2 == null) {
                HashMap hashMap = this._ukeywords;
                if (hashMap != null) {
                    hashMap.remove(caseInsensitiveString);
                }
            } else {
                if (str2.length() != 0) {
                    StringTokenIterator stringTokenIterator = new StringTokenIterator(str2.replaceAll("_", "-"), "-");
                    while (!stringTokenIterator.isDone()) {
                        if (UnicodeLocaleExtension.isTypeSubtag(stringTokenIterator.current())) {
                            stringTokenIterator.next();
                        } else {
                            throw new LocaleSyntaxException("Ill-formed Unicode locale keyword type: " + str2, stringTokenIterator.currentStart());
                        }
                    }
                }
                if (this._ukeywords == null) {
                    this._ukeywords = new HashMap(4);
                }
                this._ukeywords.put(caseInsensitiveString, str2);
            }
            return this;
        }
        throw new LocaleSyntaxException("Ill-formed Unicode locale keyword key: " + str);
    }

    public InternalLocaleBuilder setExtension(char c, String str) {
        boolean z;
        boolean isPrivateusePrefixChar = LanguageTag.isPrivateusePrefixChar(c);
        if (isPrivateusePrefixChar || LanguageTag.isExtensionSingletonChar(c)) {
            boolean z2 = str == null || str.length() == 0;
            CaseInsensitiveChar caseInsensitiveChar = new CaseInsensitiveChar(c);
            if (!z2) {
                String replaceAll = str.replaceAll("_", "-");
                StringTokenIterator stringTokenIterator = new StringTokenIterator(replaceAll, "-");
                while (!stringTokenIterator.isDone()) {
                    String current = stringTokenIterator.current();
                    if (isPrivateusePrefixChar) {
                        z = LanguageTag.isPrivateuseSubtag(current);
                    } else {
                        z = LanguageTag.isExtensionSubtag(current);
                    }
                    if (z) {
                        stringTokenIterator.next();
                    } else {
                        throw new LocaleSyntaxException("Ill-formed extension value: " + current, stringTokenIterator.currentStart());
                    }
                }
                if (UnicodeLocaleExtension.isSingletonChar(caseInsensitiveChar.value())) {
                    setUnicodeLocaleExtension(replaceAll);
                } else {
                    if (this._extensions == null) {
                        this._extensions = new HashMap(4);
                    }
                    this._extensions.put(caseInsensitiveChar, replaceAll);
                }
            } else if (UnicodeLocaleExtension.isSingletonChar(caseInsensitiveChar.value())) {
                HashSet hashSet = this._uattributes;
                if (hashSet != null) {
                    hashSet.clear();
                }
                HashMap hashMap = this._ukeywords;
                if (hashMap != null) {
                    hashMap.clear();
                }
            } else {
                HashMap hashMap2 = this._extensions;
                if (hashMap2 != null && hashMap2.containsKey(caseInsensitiveChar)) {
                    this._extensions.remove(caseInsensitiveChar);
                }
            }
            return this;
        }
        throw new LocaleSyntaxException("Ill-formed extension key: " + c);
    }

    private InternalLocaleBuilder setExtensions(List list, String str) {
        clearExtensions();
        if (list != null && list.size() > 0) {
            HashSet hashSet = new HashSet(list.size());
            Iterator it = list.iterator();
            while (it.hasNext()) {
                String str2 = (String) it.next();
                CaseInsensitiveChar caseInsensitiveChar = new CaseInsensitiveChar(str2.charAt(0));
                if (!hashSet.contains(caseInsensitiveChar)) {
                    if (UnicodeLocaleExtension.isSingletonChar(caseInsensitiveChar.value())) {
                        setUnicodeLocaleExtension(str2.substring(2));
                    } else {
                        if (this._extensions == null) {
                            this._extensions = new HashMap(4);
                        }
                        this._extensions.put(caseInsensitiveChar, str2.substring(2));
                    }
                }
            }
        }
        if (str != null && str.length() > 0) {
            if (this._extensions == null) {
                this._extensions = new HashMap(1);
            }
            this._extensions.put(new CaseInsensitiveChar(str.charAt(0)), str.substring(2));
        }
        return this;
    }

    public InternalLocaleBuilder setLanguageTag(LanguageTag languageTag) {
        clear();
        if (languageTag.getExtlangs().size() > 0) {
            this._language = (String) languageTag.getExtlangs().get(0);
        } else {
            String language = languageTag.getLanguage();
            if (!language.equals(LanguageTag.UNDETERMINED)) {
                this._language = language;
            }
        }
        this._script = languageTag.getScript();
        this._region = languageTag.getRegion();
        List variants = languageTag.getVariants();
        if (variants.size() > 0) {
            StringBuilder sb = new StringBuilder((String) variants.get(0));
            for (int i = 1; i < variants.size(); i++) {
                sb.append("_");
                sb.append((String) variants.get(i));
            }
            this._variant = sb.toString();
        }
        setExtensions(languageTag.getExtensions(), languageTag.getPrivateuse());
        return this;
    }

    public InternalLocaleBuilder setLocale(BaseLocale baseLocale, LocaleExtensions localeExtensions) {
        Set<Character> set;
        int checkVariants;
        String language = baseLocale.getLanguage();
        String script = baseLocale.getScript();
        String region = baseLocale.getRegion();
        String variant = baseLocale.getVariant();
        if (language.length() > 0 && !LanguageTag.isLanguage(language)) {
            throw new LocaleSyntaxException("Ill-formed language: " + language);
        } else if (script.length() > 0 && !LanguageTag.isScript(script)) {
            throw new LocaleSyntaxException("Ill-formed script: " + script);
        } else if (region.length() > 0 && !LanguageTag.isRegion(region)) {
            throw new LocaleSyntaxException("Ill-formed region: " + region);
        } else if (variant.length() <= 0 || (checkVariants = checkVariants(variant, "_")) == -1) {
            this._language = language;
            this._script = script;
            this._region = region;
            this._variant = variant;
            clearExtensions();
            if (localeExtensions == null) {
                set = null;
            } else {
                set = localeExtensions.getKeys();
            }
            if (set != null) {
                for (Character ch : set) {
                    Extension extension = localeExtensions.getExtension(ch);
                    if (extension instanceof UnicodeLocaleExtension) {
                        UnicodeLocaleExtension unicodeLocaleExtension = (UnicodeLocaleExtension) extension;
                        for (String str : unicodeLocaleExtension.getUnicodeLocaleAttributes()) {
                            if (this._uattributes == null) {
                                this._uattributes = new HashSet(4);
                            }
                            this._uattributes.add(new CaseInsensitiveString(str));
                        }
                        for (String str2 : unicodeLocaleExtension.getUnicodeLocaleKeys()) {
                            if (this._ukeywords == null) {
                                this._ukeywords = new HashMap(4);
                            }
                            this._ukeywords.put(new CaseInsensitiveString(str2), unicodeLocaleExtension.getUnicodeLocaleType(str2));
                        }
                    } else {
                        if (this._extensions == null) {
                            this._extensions = new HashMap(4);
                        }
                        this._extensions.put(new CaseInsensitiveChar(ch.charValue()), extension.getValue());
                    }
                }
            }
            return this;
        } else {
            throw new LocaleSyntaxException("Ill-formed variant: " + variant, checkVariants);
        }
    }

    public InternalLocaleBuilder clear() {
        this._language = "";
        this._script = "";
        this._region = "";
        this._variant = "";
        clearExtensions();
        return this;
    }

    public InternalLocaleBuilder clearExtensions() {
        HashMap hashMap = this._extensions;
        if (hashMap != null) {
            hashMap.clear();
        }
        HashSet hashSet = this._uattributes;
        if (hashSet != null) {
            hashSet.clear();
        }
        HashMap hashMap2 = this._ukeywords;
        if (hashMap2 != null) {
            hashMap2.clear();
        }
        return this;
    }

    public BaseLocale getBaseLocale() {
        String str;
        int i;
        String str2 = this._language;
        String str3 = this._script;
        String str4 = this._region;
        String str5 = this._variant;
        HashMap hashMap = this._extensions;
        if (!(hashMap == null || (str = (String) hashMap.get(PRIVUSE_KEY)) == null)) {
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
                    if (AsciiUtil.caseIgnoreMatch(stringTokenIterator.current(), "lvariant")) {
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
        HashSet hashSet;
        HashMap hashMap;
        HashMap hashMap2 = this._extensions;
        if ((hashMap2 == null || hashMap2.size() == 0) && (((hashSet = this._uattributes) == null || hashSet.size() == 0) && ((hashMap = this._ukeywords) == null || hashMap.size() == 0))) {
            return LocaleExtensions.EMPTY_EXTENSIONS;
        }
        return new LocaleExtensions(this._extensions, this._uattributes, this._ukeywords);
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
                if (AsciiUtil.caseIgnoreMatch(stringTokenIterator.current(), "lvariant")) {
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

    private int checkVariants(String str, String str2) {
        StringTokenIterator stringTokenIterator = new StringTokenIterator(str, str2);
        while (!stringTokenIterator.isDone()) {
            if (!LanguageTag.isVariant(stringTokenIterator.current())) {
                return stringTokenIterator.currentStart();
            }
            stringTokenIterator.next();
        }
        return -1;
    }

    private void setUnicodeLocaleExtension(String str) {
        String str2;
        HashSet hashSet = this._uattributes;
        if (hashSet != null) {
            hashSet.clear();
        }
        HashMap hashMap = this._ukeywords;
        if (hashMap != null) {
            hashMap.clear();
        }
        StringTokenIterator stringTokenIterator = new StringTokenIterator(str, "-");
        while (!stringTokenIterator.isDone() && UnicodeLocaleExtension.isAttribute(stringTokenIterator.current())) {
            if (this._uattributes == null) {
                this._uattributes = new HashSet(4);
            }
            this._uattributes.add(new CaseInsensitiveString(stringTokenIterator.current()));
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
                    if (this._ukeywords == null) {
                        this._ukeywords = new HashMap(4);
                    }
                    this._ukeywords.put(caseInsensitiveString, str2);
                    caseInsensitiveString = new CaseInsensitiveString(stringTokenIterator.current());
                    if (this._ukeywords.containsKey(caseInsensitiveString)) {
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
                HashMap hashMap2 = this._ukeywords;
                if (hashMap2 != null && hashMap2.containsKey(caseInsensitiveString)) {
                    caseInsensitiveString = null;
                }
            }
            if (stringTokenIterator.hasNext()) {
                stringTokenIterator.next();
            } else if (caseInsensitiveString != null) {
                if (i != -1) {
                    str3 = str.substring(i, i2);
                }
                if (this._ukeywords == null) {
                    this._ukeywords = new HashMap(4);
                }
                this._ukeywords.put(caseInsensitiveString, str3);
                return;
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static class CaseInsensitiveString {
        private String _s;

        CaseInsensitiveString(String str) {
            this._s = str;
        }

        public String value() {
            return this._s;
        }

        public int hashCode() {
            return AsciiUtil.toLowerString(this._s).hashCode();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof CaseInsensitiveString)) {
                return false;
            }
            return AsciiUtil.caseIgnoreMatch(this._s, ((CaseInsensitiveString) obj).value());
        }
    }

    /* access modifiers changed from: package-private */
    public static class CaseInsensitiveChar {
        private char _c;

        CaseInsensitiveChar(char c) {
            this._c = c;
        }

        public char value() {
            return this._c;
        }

        public int hashCode() {
            return AsciiUtil.toLower(this._c);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof CaseInsensitiveChar)) {
                return false;
            }
            return this._c == AsciiUtil.toLower(((CaseInsensitiveChar) obj).value());
        }
    }
}
