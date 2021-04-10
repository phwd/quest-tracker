package sun.util.locale;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LanguageTag {
    private static final Map GRANDFATHERED = new HashMap();
    private List extensions = Collections.emptyList();
    private List extlangs = Collections.emptyList();
    private String language = "";
    private String privateuse = "";
    private String region = "";
    private String script = "";
    private List variants = Collections.emptyList();

    static {
        String[][] strArr = {new String[]{"art-lojban", "jbo"}, new String[]{"cel-gaulish", "xtg-x-cel-gaulish"}, new String[]{"en-GB-oed", "en-GB-x-oed"}, new String[]{"i-ami", "ami"}, new String[]{"i-bnn", "bnn"}, new String[]{"i-default", "en-x-i-default"}, new String[]{"i-enochian", "und-x-i-enochian"}, new String[]{"i-hak", "hak"}, new String[]{"i-klingon", "tlh"}, new String[]{"i-lux", "lb"}, new String[]{"i-mingo", "see-x-i-mingo"}, new String[]{"i-navajo", "nv"}, new String[]{"i-pwn", "pwn"}, new String[]{"i-tao", "tao"}, new String[]{"i-tay", "tay"}, new String[]{"i-tsu", "tsu"}, new String[]{"no-bok", "nb"}, new String[]{"no-nyn", "nn"}, new String[]{"sgn-BE-FR", "sfb"}, new String[]{"sgn-BE-NL", "vgt"}, new String[]{"sgn-CH-DE", "sgg"}, new String[]{"zh-guoyu", "cmn"}, new String[]{"zh-hakka", "hak"}, new String[]{"zh-min", "nan-x-zh-min"}, new String[]{"zh-min-nan", "nan"}, new String[]{"zh-xiang", "hsn"}};
        for (String[] strArr2 : strArr) {
            Map map = GRANDFATHERED;
            String str = strArr2[0];
            LocaleUtils.toLowerString(str);
            map.put(str, strArr2);
        }
    }

    private LanguageTag() {
    }

    public static LanguageTag parse(String str, ParseStatus parseStatus) {
        StringTokenIterator stringTokenIterator;
        if (parseStatus == null) {
            parseStatus = new ParseStatus();
        } else {
            parseStatus.reset();
        }
        Map map = GRANDFATHERED;
        LocaleUtils.toLowerString(str);
        String[] strArr = (String[]) map.get(str);
        if (strArr != null) {
            stringTokenIterator = new StringTokenIterator(strArr[1], "-");
        } else {
            stringTokenIterator = new StringTokenIterator(str, "-");
        }
        LanguageTag languageTag = new LanguageTag();
        if (languageTag.parseLanguage(stringTokenIterator, parseStatus)) {
            languageTag.parseExtlangs(stringTokenIterator, parseStatus);
            languageTag.parseScript(stringTokenIterator, parseStatus);
            languageTag.parseRegion(stringTokenIterator, parseStatus);
            languageTag.parseVariants(stringTokenIterator, parseStatus);
            languageTag.parseExtensions(stringTokenIterator, parseStatus);
        }
        languageTag.parsePrivateuse(stringTokenIterator, parseStatus);
        if (!stringTokenIterator.isDone() && !parseStatus.isError()) {
            String current = stringTokenIterator.current();
            parseStatus.errorIndex = stringTokenIterator.currentStart();
            if (current.length() == 0) {
                parseStatus.errorMsg = "Empty subtag";
            } else {
                parseStatus.errorMsg = "Invalid subtag: " + current;
            }
        }
        return languageTag;
    }

    private boolean parseLanguage(StringTokenIterator stringTokenIterator, ParseStatus parseStatus) {
        if (stringTokenIterator.isDone() || parseStatus.isError()) {
            return false;
        }
        String current = stringTokenIterator.current();
        if (!isLanguage(current)) {
            return false;
        }
        this.language = current;
        parseStatus.parseLength = stringTokenIterator.currentEnd();
        stringTokenIterator.next();
        return true;
    }

    private boolean parseExtlangs(StringTokenIterator stringTokenIterator, ParseStatus parseStatus) {
        boolean z = false;
        if (!stringTokenIterator.isDone() && !parseStatus.isError()) {
            while (!stringTokenIterator.isDone()) {
                String current = stringTokenIterator.current();
                if (isExtlang(current)) {
                    z = true;
                    if (this.extlangs.isEmpty()) {
                        this.extlangs = new ArrayList(3);
                    }
                    this.extlangs.add(current);
                    parseStatus.parseLength = stringTokenIterator.currentEnd();
                    stringTokenIterator.next();
                    if (this.extlangs.size() == 3) {
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        return z;
    }

    private boolean parseScript(StringTokenIterator stringTokenIterator, ParseStatus parseStatus) {
        if (stringTokenIterator.isDone() || parseStatus.isError()) {
            return false;
        }
        String current = stringTokenIterator.current();
        if (!isScript(current)) {
            return false;
        }
        this.script = current;
        parseStatus.parseLength = stringTokenIterator.currentEnd();
        stringTokenIterator.next();
        return true;
    }

    private boolean parseRegion(StringTokenIterator stringTokenIterator, ParseStatus parseStatus) {
        if (stringTokenIterator.isDone() || parseStatus.isError()) {
            return false;
        }
        String current = stringTokenIterator.current();
        if (!isRegion(current)) {
            return false;
        }
        this.region = current;
        parseStatus.parseLength = stringTokenIterator.currentEnd();
        stringTokenIterator.next();
        return true;
    }

    private boolean parseVariants(StringTokenIterator stringTokenIterator, ParseStatus parseStatus) {
        boolean z = false;
        if (!stringTokenIterator.isDone() && !parseStatus.isError()) {
            while (!stringTokenIterator.isDone()) {
                String current = stringTokenIterator.current();
                if (!isVariant(current)) {
                    break;
                }
                z = true;
                if (this.variants.isEmpty()) {
                    this.variants = new ArrayList(3);
                }
                this.variants.add(current);
                parseStatus.parseLength = stringTokenIterator.currentEnd();
                stringTokenIterator.next();
            }
        }
        return z;
    }

    private boolean parseExtensions(StringTokenIterator stringTokenIterator, ParseStatus parseStatus) {
        boolean z = false;
        if (!stringTokenIterator.isDone() && !parseStatus.isError()) {
            while (true) {
                if (stringTokenIterator.isDone()) {
                    break;
                }
                String current = stringTokenIterator.current();
                if (!isExtensionSingleton(current)) {
                    break;
                }
                int currentStart = stringTokenIterator.currentStart();
                StringBuilder sb = new StringBuilder(current);
                stringTokenIterator.next();
                while (!stringTokenIterator.isDone()) {
                    String current2 = stringTokenIterator.current();
                    if (!isExtensionSubtag(current2)) {
                        break;
                    }
                    sb.append("-");
                    sb.append(current2);
                    parseStatus.parseLength = stringTokenIterator.currentEnd();
                    stringTokenIterator.next();
                }
                if (parseStatus.parseLength <= currentStart) {
                    parseStatus.errorIndex = currentStart;
                    parseStatus.errorMsg = "Incomplete extension '" + current + "'";
                    break;
                }
                if (this.extensions.isEmpty()) {
                    this.extensions = new ArrayList(4);
                }
                this.extensions.add(sb.toString());
                z = true;
            }
        }
        return z;
    }

    private boolean parsePrivateuse(StringTokenIterator stringTokenIterator, ParseStatus parseStatus) {
        if (stringTokenIterator.isDone() || parseStatus.isError()) {
            return false;
        }
        String current = stringTokenIterator.current();
        if (!isPrivateusePrefix(current)) {
            return false;
        }
        int currentStart = stringTokenIterator.currentStart();
        StringBuilder sb = new StringBuilder(current);
        stringTokenIterator.next();
        while (!stringTokenIterator.isDone()) {
            String current2 = stringTokenIterator.current();
            if (!isPrivateuseSubtag(current2)) {
                break;
            }
            sb.append("-");
            sb.append(current2);
            parseStatus.parseLength = stringTokenIterator.currentEnd();
            stringTokenIterator.next();
        }
        if (parseStatus.parseLength <= currentStart) {
            parseStatus.errorIndex = currentStart;
            parseStatus.errorMsg = "Incomplete privateuse";
            return false;
        }
        this.privateuse = sb.toString();
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:54:0x00e9  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0136  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0139  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x013e  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0172  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static sun.util.locale.LanguageTag parseLocale(sun.util.locale.BaseLocale r11, sun.util.locale.LocaleExtensions r12) {
        /*
        // Method dump skipped, instructions count: 389
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.util.locale.LanguageTag.parseLocale(sun.util.locale.BaseLocale, sun.util.locale.LocaleExtensions):sun.util.locale.LanguageTag");
    }

    public String getLanguage() {
        return this.language;
    }

    public List getExtlangs() {
        if (this.extlangs.isEmpty()) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(this.extlangs);
    }

    public String getScript() {
        return this.script;
    }

    public String getRegion() {
        return this.region;
    }

    public List getVariants() {
        if (this.variants.isEmpty()) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(this.variants);
    }

    public List getExtensions() {
        if (this.extensions.isEmpty()) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(this.extensions);
    }

    public String getPrivateuse() {
        return this.privateuse;
    }

    public static boolean isLanguage(String str) {
        int length = str.length();
        return length >= 2 && length <= 8 && LocaleUtils.isAlphaString(str);
    }

    public static boolean isExtlang(String str) {
        return str.length() == 3 && LocaleUtils.isAlphaString(str);
    }

    public static boolean isScript(String str) {
        return str.length() == 4 && LocaleUtils.isAlphaString(str);
    }

    public static boolean isRegion(String str) {
        return (str.length() == 2 && LocaleUtils.isAlphaString(str)) || (str.length() == 3 && LocaleUtils.isNumericString(str));
    }

    public static boolean isVariant(String str) {
        int length = str.length();
        if (length >= 5 && length <= 8) {
            return LocaleUtils.isAlphaNumericString(str);
        }
        if (length != 4) {
            return false;
        }
        if (!LocaleUtils.isNumeric(str.charAt(0)) || !LocaleUtils.isAlphaNumeric(str.charAt(1)) || !LocaleUtils.isAlphaNumeric(str.charAt(2)) || !LocaleUtils.isAlphaNumeric(str.charAt(3))) {
            return false;
        }
        return true;
    }

    public static boolean isExtensionSingleton(String str) {
        if (str.length() != 1 || !LocaleUtils.isAlphaString(str) || LocaleUtils.caseIgnoreMatch("x", str)) {
            return false;
        }
        return true;
    }

    public static boolean isExtensionSingletonChar(char c) {
        return isExtensionSingleton(String.valueOf(c));
    }

    public static boolean isExtensionSubtag(String str) {
        int length = str.length();
        return length >= 2 && length <= 8 && LocaleUtils.isAlphaNumericString(str);
    }

    public static boolean isPrivateusePrefix(String str) {
        if (str.length() != 1 || !LocaleUtils.caseIgnoreMatch("x", str)) {
            return false;
        }
        return true;
    }

    public static boolean isPrivateusePrefixChar(char c) {
        return LocaleUtils.caseIgnoreMatch("x", String.valueOf(c));
    }

    public static boolean isPrivateuseSubtag(String str) {
        int length = str.length();
        if (length < 1 || length > 8 || !LocaleUtils.isAlphaNumericString(str)) {
            return false;
        }
        return true;
    }

    public static String canonicalizeLanguage(String str) {
        LocaleUtils.toLowerString(str);
        return str;
    }

    public static String canonicalizeScript(String str) {
        LocaleUtils.toTitleString(str);
        return str;
    }

    public static String canonicalizeRegion(String str) {
        LocaleUtils.toUpperString(str);
        return str;
    }

    public static String canonicalizeExtension(String str) {
        LocaleUtils.toLowerString(str);
        return str;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.language.length() > 0) {
            sb.append(this.language);
            for (String str : this.extlangs) {
                sb.append("-");
                sb.append(str);
            }
            if (this.script.length() > 0) {
                sb.append("-");
                sb.append(this.script);
            }
            if (this.region.length() > 0) {
                sb.append("-");
                sb.append(this.region);
            }
            for (String str2 : this.variants) {
                sb.append("-");
                sb.append(str2);
            }
            for (String str3 : this.extensions) {
                sb.append("-");
                sb.append(str3);
            }
        }
        if (this.privateuse.length() > 0) {
            if (sb.length() > 0) {
                sb.append("-");
            }
            sb.append(this.privateuse);
        }
        return sb.toString();
    }
}
