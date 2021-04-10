package android.icu.impl.locale;

import android.icu.impl.locale.AsciiUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import sun.security.x509.PolicyInformation;

public class LanguageTag {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Map<AsciiUtil.CaseInsensitiveKey, String[]> GRANDFATHERED = new HashMap();
    private static final boolean JDKIMPL = false;
    public static final String PRIVATEUSE = "x";
    public static final String PRIVUSE_VARIANT_PREFIX = "lvariant";
    public static final String SEP = "-";
    public static String UNDETERMINED = sun.util.locale.LanguageTag.UNDETERMINED;
    private List<String> _extensions = Collections.emptyList();
    private List<String> _extlangs = Collections.emptyList();
    private String _language = "";
    private String _privateuse = "";
    private String _region = "";
    private String _script = "";
    private List<String> _variants = Collections.emptyList();

    static {
        String[][] entries = {new String[]{"art-lojban", "jbo"}, new String[]{"cel-gaulish", "xtg-x-cel-gaulish"}, new String[]{"en-GB-oed", "en-GB-x-oed"}, new String[]{"i-ami", "ami"}, new String[]{"i-bnn", "bnn"}, new String[]{"i-default", "en-x-i-default"}, new String[]{"i-enochian", "und-x-i-enochian"}, new String[]{"i-hak", "hak"}, new String[]{"i-klingon", "tlh"}, new String[]{"i-lux", "lb"}, new String[]{"i-mingo", "see-x-i-mingo"}, new String[]{"i-navajo", "nv"}, new String[]{"i-pwn", "pwn"}, new String[]{"i-tao", "tao"}, new String[]{"i-tay", "tay"}, new String[]{"i-tsu", "tsu"}, new String[]{"no-bok", "nb"}, new String[]{"no-nyn", "nn"}, new String[]{"sgn-BE-FR", "sfb"}, new String[]{"sgn-BE-NL", "vgt"}, new String[]{"sgn-CH-DE", "sgg"}, new String[]{"zh-guoyu", "cmn"}, new String[]{"zh-hakka", "hak"}, new String[]{"zh-min", "nan-x-zh-min"}, new String[]{"zh-min-nan", "nan"}, new String[]{"zh-xiang", "hsn"}};
        for (String[] e : entries) {
            GRANDFATHERED.put(new AsciiUtil.CaseInsensitiveKey(e[0]), e);
        }
    }

    private LanguageTag() {
    }

    public static LanguageTag parse(String languageTag, ParseStatus sts) {
        StringTokenIterator itr;
        if (sts == null) {
            sts = new ParseStatus();
        } else {
            sts.reset();
        }
        boolean isGrandfathered = false;
        String[] gfmap = GRANDFATHERED.get(new AsciiUtil.CaseInsensitiveKey(languageTag));
        if (gfmap != null) {
            itr = new StringTokenIterator(gfmap[1], "-");
            isGrandfathered = true;
        } else {
            itr = new StringTokenIterator(languageTag, "-");
        }
        LanguageTag tag = new LanguageTag();
        if (tag.parseLanguage(itr, sts)) {
            if (tag._language.length() <= 3) {
                tag.parseExtlangs(itr, sts);
            }
            tag.parseScript(itr, sts);
            tag.parseRegion(itr, sts);
            tag.parseVariants(itr, sts);
            tag.parseExtensions(itr, sts);
        }
        tag.parsePrivateuse(itr, sts);
        if (isGrandfathered) {
            sts._parseLength = languageTag.length();
        } else if (!itr.isDone() && !sts.isError()) {
            String s = itr.current();
            sts._errorIndex = itr.currentStart();
            if (s.length() == 0) {
                sts._errorMsg = "Empty subtag";
            } else {
                sts._errorMsg = "Invalid subtag: " + s;
            }
        }
        return tag;
    }

    private boolean parseLanguage(StringTokenIterator itr, ParseStatus sts) {
        if (itr.isDone() || sts.isError()) {
            return false;
        }
        String s = itr.current();
        if (!isLanguage(s)) {
            return false;
        }
        this._language = s;
        sts._parseLength = itr.currentEnd();
        itr.next();
        return true;
    }

    private boolean parseExtlangs(StringTokenIterator itr, ParseStatus sts) {
        if (itr.isDone() || sts.isError()) {
            return false;
        }
        boolean found = false;
        while (!itr.isDone()) {
            String s = itr.current();
            if (isExtlang(s)) {
                found = true;
                if (this._extlangs.isEmpty()) {
                    this._extlangs = new ArrayList(3);
                }
                this._extlangs.add(s);
                sts._parseLength = itr.currentEnd();
                itr.next();
                if (this._extlangs.size() == 3) {
                    break;
                }
            } else {
                break;
            }
        }
        return found;
    }

    private boolean parseScript(StringTokenIterator itr, ParseStatus sts) {
        if (itr.isDone() || sts.isError()) {
            return false;
        }
        String s = itr.current();
        if (!isScript(s)) {
            return false;
        }
        this._script = s;
        sts._parseLength = itr.currentEnd();
        itr.next();
        return true;
    }

    private boolean parseRegion(StringTokenIterator itr, ParseStatus sts) {
        if (itr.isDone() || sts.isError()) {
            return false;
        }
        String s = itr.current();
        if (!isRegion(s)) {
            return false;
        }
        this._region = s;
        sts._parseLength = itr.currentEnd();
        itr.next();
        return true;
    }

    private boolean parseVariants(StringTokenIterator itr, ParseStatus sts) {
        if (itr.isDone() || sts.isError()) {
            return false;
        }
        boolean found = false;
        while (!itr.isDone()) {
            String s = itr.current();
            if (!isVariant(s)) {
                break;
            }
            found = true;
            if (this._variants.isEmpty()) {
                this._variants = new ArrayList(3);
            }
            this._variants.add(s);
            sts._parseLength = itr.currentEnd();
            itr.next();
        }
        return found;
    }

    private boolean parseExtensions(StringTokenIterator itr, ParseStatus sts) {
        if (itr.isDone() || sts.isError()) {
            return false;
        }
        boolean found = false;
        while (true) {
            if (itr.isDone()) {
                break;
            }
            String s = itr.current();
            if (!isExtensionSingleton(s)) {
                break;
            }
            int start = itr.currentStart();
            StringBuilder sb = new StringBuilder(s);
            itr.next();
            while (!itr.isDone()) {
                String s2 = itr.current();
                if (!isExtensionSubtag(s2)) {
                    break;
                }
                sb.append("-");
                sb.append(s2);
                sts._parseLength = itr.currentEnd();
                itr.next();
            }
            if (sts._parseLength <= start) {
                sts._errorIndex = start;
                sts._errorMsg = "Incomplete extension '" + s + "'";
                break;
            }
            if (this._extensions.size() == 0) {
                this._extensions = new ArrayList(4);
            }
            this._extensions.add(sb.toString());
            found = true;
        }
        return found;
    }

    private boolean parsePrivateuse(StringTokenIterator itr, ParseStatus sts) {
        if (itr.isDone() || sts.isError()) {
            return false;
        }
        String s = itr.current();
        if (!isPrivateusePrefix(s)) {
            return false;
        }
        int start = itr.currentStart();
        StringBuilder sb = new StringBuilder(s);
        itr.next();
        while (!itr.isDone()) {
            String s2 = itr.current();
            if (!isPrivateuseSubtag(s2)) {
                break;
            }
            sb.append("-");
            sb.append(s2);
            sts._parseLength = itr.currentEnd();
            itr.next();
        }
        if (sts._parseLength <= start) {
            sts._errorIndex = start;
            sts._errorMsg = "Incomplete privateuse";
            return false;
        }
        this._privateuse = sb.toString();
        return true;
    }

    public static LanguageTag parseLocale(BaseLocale baseLocale, LocaleExtensions localeExtensions) {
        String script;
        String language;
        LanguageTag tag = new LanguageTag();
        String language2 = baseLocale.getLanguage();
        String script2 = baseLocale.getScript();
        String region = baseLocale.getRegion();
        String variant = baseLocale.getVariant();
        boolean hasSubtag = false;
        String privuseVar = null;
        if (language2.length() > 0 && isLanguage(language2)) {
            if (language2.equals("iw")) {
                language2 = "he";
            } else if (language2.equals("ji")) {
                language2 = "yi";
            } else if (language2.equals("in")) {
                language2 = PolicyInformation.ID;
            }
            tag._language = language2;
        }
        if (script2.length() > 0 && isScript(script2)) {
            tag._script = canonicalizeScript(script2);
            hasSubtag = true;
        }
        if (region.length() > 0 && isRegion(region)) {
            tag._region = canonicalizeRegion(region);
            hasSubtag = true;
        }
        if (variant.length() > 0) {
            List<String> variants = null;
            StringTokenIterator varitr = new StringTokenIterator(variant, "_");
            while (!varitr.isDone()) {
                String var = varitr.current();
                if (!isVariant(var)) {
                    break;
                }
                if (variants == null) {
                    variants = new ArrayList<>();
                }
                variants.add(canonicalizeVariant(var));
                varitr.next();
            }
            if (variants != null) {
                tag._variants = variants;
                hasSubtag = true;
            }
            if (!varitr.isDone()) {
                StringBuilder buf = new StringBuilder();
                while (!varitr.isDone()) {
                    String prvv = varitr.current();
                    if (!isPrivateuseSubtag(prvv)) {
                        break;
                    }
                    if (buf.length() > 0) {
                        buf.append("-");
                    }
                    buf.append(AsciiUtil.toLowerString(prvv));
                    varitr.next();
                }
                if (buf.length() > 0) {
                    privuseVar = buf.toString();
                }
            }
        }
        List<String> extensions = null;
        String privateuse = null;
        for (Character locextKey : localeExtensions.getKeys()) {
            Extension ext = localeExtensions.getExtension(locextKey);
            if (isPrivateusePrefixChar(locextKey.charValue())) {
                privateuse = ext.getValue();
                language = language2;
                script = script2;
            } else {
                if (extensions == null) {
                    extensions = new ArrayList<>();
                }
                language = language2;
                StringBuilder sb = new StringBuilder();
                script = script2;
                sb.append(locextKey.toString());
                sb.append("-");
                sb.append(ext.getValue());
                extensions.add(sb.toString());
            }
            language2 = language;
            script2 = script;
        }
        if (extensions != null) {
            tag._extensions = extensions;
            hasSubtag = true;
        }
        if (privuseVar != null) {
            privateuse = privateuse == null ? "lvariant-" + privuseVar : privateuse + "-" + "lvariant" + "-" + privuseVar.replace("_", "-");
        }
        if (privateuse != null) {
            tag._privateuse = privateuse;
        }
        if (tag._language.length() == 0 && (hasSubtag || privateuse == null)) {
            tag._language = UNDETERMINED;
        }
        return tag;
    }

    public String getLanguage() {
        return this._language;
    }

    public List<String> getExtlangs() {
        return Collections.unmodifiableList(this._extlangs);
    }

    public String getScript() {
        return this._script;
    }

    public String getRegion() {
        return this._region;
    }

    public List<String> getVariants() {
        return Collections.unmodifiableList(this._variants);
    }

    public List<String> getExtensions() {
        return Collections.unmodifiableList(this._extensions);
    }

    public String getPrivateuse() {
        return this._privateuse;
    }

    public static boolean isLanguage(String s) {
        return s.length() >= 2 && s.length() <= 8 && AsciiUtil.isAlphaString(s);
    }

    public static boolean isExtlang(String s) {
        return s.length() == 3 && AsciiUtil.isAlphaString(s);
    }

    public static boolean isScript(String s) {
        return s.length() == 4 && AsciiUtil.isAlphaString(s);
    }

    public static boolean isRegion(String s) {
        return (s.length() == 2 && AsciiUtil.isAlphaString(s)) || (s.length() == 3 && AsciiUtil.isNumericString(s));
    }

    public static boolean isVariant(String s) {
        int len = s.length();
        if (len >= 5 && len <= 8) {
            return AsciiUtil.isAlphaNumericString(s);
        }
        if (len != 4 || !AsciiUtil.isNumeric(s.charAt(0)) || !AsciiUtil.isAlphaNumeric(s.charAt(1)) || !AsciiUtil.isAlphaNumeric(s.charAt(2)) || !AsciiUtil.isAlphaNumeric(s.charAt(3))) {
            return false;
        }
        return true;
    }

    public static boolean isExtensionSingleton(String s) {
        if (s.length() != 1 || !AsciiUtil.isAlphaString(s) || AsciiUtil.caseIgnoreMatch("x", s)) {
            return false;
        }
        return true;
    }

    public static boolean isExtensionSingletonChar(char c) {
        return isExtensionSingleton(String.valueOf(c));
    }

    public static boolean isExtensionSubtag(String s) {
        return s.length() >= 2 && s.length() <= 8 && AsciiUtil.isAlphaNumericString(s);
    }

    public static boolean isPrivateusePrefix(String s) {
        if (s.length() != 1 || !AsciiUtil.caseIgnoreMatch("x", s)) {
            return false;
        }
        return true;
    }

    public static boolean isPrivateusePrefixChar(char c) {
        return AsciiUtil.caseIgnoreMatch("x", String.valueOf(c));
    }

    public static boolean isPrivateuseSubtag(String s) {
        return s.length() >= 1 && s.length() <= 8 && AsciiUtil.isAlphaNumericString(s);
    }

    public static String canonicalizeLanguage(String s) {
        return AsciiUtil.toLowerString(s);
    }

    public static String canonicalizeExtlang(String s) {
        return AsciiUtil.toLowerString(s);
    }

    public static String canonicalizeScript(String s) {
        return AsciiUtil.toTitleString(s);
    }

    public static String canonicalizeRegion(String s) {
        return AsciiUtil.toUpperString(s);
    }

    public static String canonicalizeVariant(String s) {
        return AsciiUtil.toLowerString(s);
    }

    public static String canonicalizeExtension(String s) {
        return AsciiUtil.toLowerString(s);
    }

    public static String canonicalizeExtensionSingleton(String s) {
        return AsciiUtil.toLowerString(s);
    }

    public static String canonicalizeExtensionSubtag(String s) {
        return AsciiUtil.toLowerString(s);
    }

    public static String canonicalizePrivateuse(String s) {
        return AsciiUtil.toLowerString(s);
    }

    public static String canonicalizePrivateuseSubtag(String s) {
        return AsciiUtil.toLowerString(s);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this._language.length() > 0) {
            sb.append(this._language);
            for (String extlang : this._extlangs) {
                sb.append("-");
                sb.append(extlang);
            }
            if (this._script.length() > 0) {
                sb.append("-");
                sb.append(this._script);
            }
            if (this._region.length() > 0) {
                sb.append("-");
                sb.append(this._region);
            }
            for (String variant : this._variants) {
                sb.append("-");
                sb.append(variant);
            }
            for (String extension : this._extensions) {
                sb.append("-");
                sb.append(extension);
            }
        }
        if (this._privateuse.length() > 0) {
            if (sb.length() > 0) {
                sb.append("-");
            }
            sb.append(this._privateuse);
        }
        return sb.toString();
    }
}
