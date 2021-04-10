package sun.util.locale;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;

public class UnicodeLocaleExtension extends Extension {
    public static final UnicodeLocaleExtension CA_JAPANESE = new UnicodeLocaleExtension("ca", "japanese");
    public static final UnicodeLocaleExtension NU_THAI = new UnicodeLocaleExtension("nu", "thai");
    private final Set attributes;
    private final Map keywords;

    @Override // sun.util.locale.Extension
    public /* bridge */ /* synthetic */ String getID() {
        return super.getID();
    }

    @Override // sun.util.locale.Extension
    public /* bridge */ /* synthetic */ String getValue() {
        return super.getValue();
    }

    @Override // sun.util.locale.Extension
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    private UnicodeLocaleExtension(String str, String str2) {
        super('u', str + "-" + str2);
        this.attributes = Collections.emptySet();
        this.keywords = Collections.singletonMap(str, str2);
    }

    UnicodeLocaleExtension(SortedSet sortedSet, SortedMap sortedMap) {
        super('u');
        if (sortedSet != null) {
            this.attributes = sortedSet;
        } else {
            this.attributes = Collections.emptySet();
        }
        if (sortedMap != null) {
            this.keywords = sortedMap;
        } else {
            this.keywords = Collections.emptyMap();
        }
        if (!this.attributes.isEmpty() || !this.keywords.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (String str : this.attributes) {
                sb.append("-");
                sb.append(str);
            }
            for (Map.Entry entry : this.keywords.entrySet()) {
                String str2 = (String) entry.getValue();
                sb.append("-");
                sb.append((String) entry.getKey());
                if (str2.length() > 0) {
                    sb.append("-");
                    sb.append(str2);
                }
            }
            setValue(sb.substring(1));
        }
    }

    public Set getUnicodeLocaleAttributes() {
        Set set = this.attributes;
        if (set == Collections.EMPTY_SET) {
            return set;
        }
        return Collections.unmodifiableSet(set);
    }

    public Set getUnicodeLocaleKeys() {
        Map map = this.keywords;
        if (map == Collections.EMPTY_MAP) {
            return Collections.emptySet();
        }
        return Collections.unmodifiableSet(map.keySet());
    }

    public String getUnicodeLocaleType(String str) {
        return (String) this.keywords.get(str);
    }

    public static boolean isSingletonChar(char c) {
        return 'u' == LocaleUtils.toLower(c);
    }

    public static boolean isAttribute(String str) {
        int length = str.length();
        return length >= 3 && length <= 8 && LocaleUtils.isAlphaNumericString(str);
    }

    public static boolean isKey(String str) {
        return str.length() == 2 && LocaleUtils.isAlphaNumericString(str);
    }
}
