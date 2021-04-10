package android.icu.impl.locale;

import android.icu.impl.locale.InternalLocaleBuilder;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

public class LocaleExtensions {
    public static final LocaleExtensions CALENDAR_JAPANESE = new LocaleExtensions();
    public static final LocaleExtensions EMPTY_EXTENSIONS = new LocaleExtensions();
    private static final SortedMap EMPTY_MAP = Collections.unmodifiableSortedMap(new TreeMap());
    public static final LocaleExtensions NUMBER_THAI = new LocaleExtensions();
    private String _id;
    private SortedMap _map;

    static {
        LocaleExtensions localeExtensions = EMPTY_EXTENSIONS;
        localeExtensions._id = "";
        localeExtensions._map = EMPTY_MAP;
        LocaleExtensions localeExtensions2 = CALENDAR_JAPANESE;
        localeExtensions2._id = "u-ca-japanese";
        localeExtensions2._map = new TreeMap();
        CALENDAR_JAPANESE._map.put('u', UnicodeLocaleExtension.CA_JAPANESE);
        LocaleExtensions localeExtensions3 = NUMBER_THAI;
        localeExtensions3._id = "u-nu-thai";
        localeExtensions3._map = new TreeMap();
        NUMBER_THAI._map.put('u', UnicodeLocaleExtension.NU_THAI);
    }

    private LocaleExtensions() {
    }

    LocaleExtensions(Map map, Set set, Map map2) {
        TreeSet treeSet;
        boolean z = true;
        boolean z2 = map != null && map.size() > 0;
        boolean z3 = set != null && set.size() > 0;
        z = (map2 == null || map2.size() <= 0) ? false : z;
        if (z2 || z3 || z) {
            this._map = new TreeMap();
            if (z2) {
                for (Map.Entry entry : map.entrySet()) {
                    char lower = AsciiUtil.toLower(((InternalLocaleBuilder.CaseInsensitiveChar) entry.getKey()).value());
                    String str = (String) entry.getValue();
                    if (!LanguageTag.isPrivateusePrefixChar(lower) || (str = InternalLocaleBuilder.removePrivateuseVariant(str)) != null) {
                        this._map.put(Character.valueOf(lower), new Extension(lower, AsciiUtil.toLowerString(str)));
                    }
                }
            }
            if (z3 || z) {
                TreeMap treeMap = null;
                if (z3) {
                    treeSet = new TreeSet();
                    Iterator it = set.iterator();
                    while (it.hasNext()) {
                        treeSet.add(AsciiUtil.toLowerString(((InternalLocaleBuilder.CaseInsensitiveString) it.next()).value()));
                    }
                } else {
                    treeSet = null;
                }
                if (z) {
                    treeMap = new TreeMap();
                    for (Map.Entry entry2 : map2.entrySet()) {
                        treeMap.put(AsciiUtil.toLowerString(((InternalLocaleBuilder.CaseInsensitiveString) entry2.getKey()).value()), AsciiUtil.toLowerString((String) entry2.getValue()));
                    }
                }
                this._map.put('u', new UnicodeLocaleExtension(treeSet, treeMap));
            }
            if (this._map.size() == 0) {
                this._map = EMPTY_MAP;
                this._id = "";
                return;
            }
            this._id = toID(this._map);
            return;
        }
        this._map = EMPTY_MAP;
        this._id = "";
    }

    public Set getKeys() {
        return Collections.unmodifiableSet(this._map.keySet());
    }

    public Extension getExtension(Character ch) {
        return (Extension) this._map.get(Character.valueOf(AsciiUtil.toLower(ch.charValue())));
    }

    public String getUnicodeLocaleType(String str) {
        Extension extension = (Extension) this._map.get('u');
        if (extension == null) {
            return null;
        }
        return ((UnicodeLocaleExtension) extension).getUnicodeLocaleType(AsciiUtil.toLowerString(str));
    }

    private static String toID(SortedMap sortedMap) {
        StringBuilder sb = new StringBuilder();
        Extension extension = null;
        for (Map.Entry entry : sortedMap.entrySet()) {
            char charValue = ((Character) entry.getKey()).charValue();
            Extension extension2 = (Extension) entry.getValue();
            if (LanguageTag.isPrivateusePrefixChar(charValue)) {
                extension = extension2;
            } else {
                if (sb.length() > 0) {
                    sb.append("-");
                }
                sb.append(extension2);
            }
        }
        if (extension != null) {
            if (sb.length() > 0) {
                sb.append("-");
            }
            sb.append(extension);
        }
        return sb.toString();
    }

    public String toString() {
        return this._id;
    }

    public int hashCode() {
        return this._id.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LocaleExtensions)) {
            return false;
        }
        return this._id.equals(((LocaleExtensions) obj)._id);
    }
}
