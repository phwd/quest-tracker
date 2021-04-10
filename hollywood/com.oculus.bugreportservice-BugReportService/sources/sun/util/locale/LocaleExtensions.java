package sun.util.locale;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import sun.util.locale.InternalLocaleBuilder;

public class LocaleExtensions {
    public static final LocaleExtensions CALENDAR_JAPANESE = new LocaleExtensions("u-ca-japanese", (Character) 'u', (Extension) UnicodeLocaleExtension.CA_JAPANESE);
    public static final LocaleExtensions NUMBER_THAI = new LocaleExtensions("u-nu-thai", (Character) 'u', (Extension) UnicodeLocaleExtension.NU_THAI);
    private final Map extensionMap;
    private final String id;

    private LocaleExtensions(String str, Character ch, Extension extension) {
        this.id = str;
        this.extensionMap = Collections.singletonMap(ch, extension);
    }

    LocaleExtensions(Map map, Set set, Map map2) {
        SortedSet sortedSet;
        boolean z = !LocaleUtils.isEmpty(map);
        boolean z2 = !LocaleUtils.isEmpty(set);
        boolean z3 = !LocaleUtils.isEmpty(map2);
        if (z || z2 || z3) {
            TreeMap treeMap = new TreeMap();
            if (z) {
                for (Map.Entry entry : map.entrySet()) {
                    char lower = LocaleUtils.toLower(((InternalLocaleBuilder.CaseInsensitiveChar) entry.getKey()).value());
                    String str = (String) entry.getValue();
                    if (!LanguageTag.isPrivateusePrefixChar(lower) || (str = InternalLocaleBuilder.removePrivateuseVariant(str)) != null) {
                        Character valueOf = Character.valueOf(lower);
                        LocaleUtils.toLowerString(str);
                        treeMap.put(valueOf, new Extension(lower, str));
                    }
                }
            }
            if (z2 || z3) {
                TreeMap treeMap2 = null;
                if (z2) {
                    sortedSet = new TreeSet();
                    Iterator it = set.iterator();
                    while (it.hasNext()) {
                        String value = ((InternalLocaleBuilder.CaseInsensitiveString) it.next()).value();
                        LocaleUtils.toLowerString(value);
                        sortedSet.add(value);
                    }
                } else {
                    sortedSet = null;
                }
                if (z3) {
                    treeMap2 = new TreeMap();
                    for (Map.Entry entry2 : map2.entrySet()) {
                        String value2 = ((InternalLocaleBuilder.CaseInsensitiveString) entry2.getKey()).value();
                        LocaleUtils.toLowerString(value2);
                        String str2 = (String) entry2.getValue();
                        LocaleUtils.toLowerString(str2);
                        treeMap2.put(value2, str2);
                    }
                }
                treeMap.put('u', new UnicodeLocaleExtension(sortedSet, treeMap2));
            }
            if (treeMap.isEmpty()) {
                this.id = "";
                this.extensionMap = Collections.emptyMap();
                return;
            }
            this.id = toID(treeMap);
            this.extensionMap = treeMap;
            return;
        }
        this.id = "";
        this.extensionMap = Collections.emptyMap();
    }

    public Set getKeys() {
        if (this.extensionMap.isEmpty()) {
            return Collections.emptySet();
        }
        return Collections.unmodifiableSet(this.extensionMap.keySet());
    }

    public Extension getExtension(Character ch) {
        return (Extension) this.extensionMap.get(Character.valueOf(LocaleUtils.toLower(ch.charValue())));
    }

    public String getExtensionValue(Character ch) {
        Extension extension = (Extension) this.extensionMap.get(Character.valueOf(LocaleUtils.toLower(ch.charValue())));
        if (extension == null) {
            return null;
        }
        return extension.getValue();
    }

    public Set getUnicodeLocaleAttributes() {
        Extension extension = (Extension) this.extensionMap.get('u');
        if (extension == null) {
            return Collections.emptySet();
        }
        return ((UnicodeLocaleExtension) extension).getUnicodeLocaleAttributes();
    }

    public Set getUnicodeLocaleKeys() {
        Extension extension = (Extension) this.extensionMap.get('u');
        if (extension == null) {
            return Collections.emptySet();
        }
        return ((UnicodeLocaleExtension) extension).getUnicodeLocaleKeys();
    }

    public String getUnicodeLocaleType(String str) {
        Extension extension = (Extension) this.extensionMap.get('u');
        if (extension == null) {
            return null;
        }
        LocaleUtils.toLowerString(str);
        return ((UnicodeLocaleExtension) extension).getUnicodeLocaleType(str);
    }

    public boolean isEmpty() {
        return this.extensionMap.isEmpty();
    }

    public static boolean isValidKey(char c) {
        return LanguageTag.isExtensionSingletonChar(c) || LanguageTag.isPrivateusePrefixChar(c);
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
        return this.id;
    }

    public String getID() {
        return this.id;
    }

    public int hashCode() {
        return this.id.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LocaleExtensions)) {
            return false;
        }
        return this.id.equals(((LocaleExtensions) obj).id);
    }
}
