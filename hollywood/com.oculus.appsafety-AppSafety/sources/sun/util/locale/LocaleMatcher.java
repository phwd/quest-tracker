package sun.util.locale;

import android.icu.impl.number.Padder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public final class LocaleMatcher {
    public static List<Locale> filter(List<Locale.LanguageRange> priorityList, Collection<Locale> locales, Locale.FilteringMode mode) {
        if (priorityList.isEmpty() || locales.isEmpty()) {
            return new ArrayList();
        }
        List<String> tags = new ArrayList<>();
        for (Locale locale : locales) {
            tags.add(locale.toLanguageTag());
        }
        List<String> filteredTags = filterTags(priorityList, tags, mode);
        List<Locale> filteredLocales = new ArrayList<>(filteredTags.size());
        for (String tag : filteredTags) {
            filteredLocales.add(Locale.forLanguageTag(tag));
        }
        return filteredLocales;
    }

    public static List<String> filterTags(List<Locale.LanguageRange> priorityList, Collection<String> tags, Locale.FilteringMode mode) {
        String range;
        if (priorityList.isEmpty() || tags.isEmpty()) {
            return new ArrayList();
        }
        if (mode == Locale.FilteringMode.EXTENDED_FILTERING) {
            return filterExtended(priorityList, tags);
        }
        ArrayList<Locale.LanguageRange> list = new ArrayList<>();
        for (Locale.LanguageRange lr : priorityList) {
            String range2 = lr.getRange();
            if (!range2.startsWith("*-") && range2.indexOf("-*") == -1) {
                list.add(lr);
            } else if (mode == Locale.FilteringMode.AUTOSELECT_FILTERING) {
                return filterExtended(priorityList, tags);
            } else {
                if (mode == Locale.FilteringMode.MAP_EXTENDED_RANGES) {
                    if (range2.charAt(0) == '*') {
                        range = "*";
                    } else {
                        range = range2.replaceAll("-[*]", "");
                    }
                    list.add(new Locale.LanguageRange(range, lr.getWeight()));
                } else if (mode == Locale.FilteringMode.REJECT_EXTENDED_RANGES) {
                    throw new IllegalArgumentException("An extended range \"" + range2 + "\" found in REJECT_EXTENDED_RANGES mode.");
                }
            }
        }
        return filterBasic(list, tags);
    }

    private static List<String> filterBasic(List<Locale.LanguageRange> priorityList, Collection<String> tags) {
        int len;
        List<String> list = new ArrayList<>();
        for (Locale.LanguageRange lr : priorityList) {
            String range = lr.getRange();
            if (range.equals("*")) {
                return new ArrayList(tags);
            }
            for (String tag : tags) {
                String tag2 = tag.toLowerCase();
                if (tag2.startsWith(range) && ((tag2.length() == (len = range.length()) || tag2.charAt(len) == '-') && !list.contains(tag2))) {
                    list.add(tag2);
                }
            }
        }
        return list;
    }

    private static List<String> filterExtended(List<Locale.LanguageRange> priorityList, Collection<String> tags) {
        List<String> list = new ArrayList<>();
        for (Locale.LanguageRange lr : priorityList) {
            String range = lr.getRange();
            if (range.equals("*")) {
                return new ArrayList(tags);
            }
            String[] rangeSubtags = range.split("-");
            for (String tag : tags) {
                String tag2 = tag.toLowerCase();
                String[] tagSubtags = tag2.split("-");
                if (rangeSubtags[0].equals(tagSubtags[0]) || rangeSubtags[0].equals("*")) {
                    int rangeIndex = 1;
                    int tagIndex = 1;
                    while (rangeIndex < rangeSubtags.length && tagIndex < tagSubtags.length) {
                        if (rangeSubtags[rangeIndex].equals("*")) {
                            rangeIndex++;
                        } else if (!rangeSubtags[rangeIndex].equals(tagSubtags[tagIndex])) {
                            if (tagSubtags[tagIndex].length() == 1 && !tagSubtags[tagIndex].equals("*")) {
                                break;
                            }
                            tagIndex++;
                        } else {
                            rangeIndex++;
                            tagIndex++;
                        }
                    }
                    if (rangeSubtags.length == rangeIndex && !list.contains(tag2)) {
                        list.add(tag2);
                    }
                }
            }
        }
        return list;
    }

    public static Locale lookup(List<Locale.LanguageRange> priorityList, Collection<Locale> locales) {
        if (priorityList.isEmpty() || locales.isEmpty()) {
            return null;
        }
        List<String> tags = new ArrayList<>();
        for (Locale locale : locales) {
            tags.add(locale.toLanguageTag());
        }
        String lookedUpTag = lookupTag(priorityList, tags);
        if (lookedUpTag == null) {
            return null;
        }
        return Locale.forLanguageTag(lookedUpTag);
    }

    public static String lookupTag(List<Locale.LanguageRange> priorityList, Collection<String> tags) {
        if (priorityList.isEmpty() || tags.isEmpty()) {
            return null;
        }
        for (Locale.LanguageRange lr : priorityList) {
            String range = lr.getRange();
            if (!range.equals("*")) {
                String rangeForRegex = range.replace("*", "\\p{Alnum}*");
                while (rangeForRegex.length() > 0) {
                    for (String tag : tags) {
                        String tag2 = tag.toLowerCase();
                        if (tag2.matches(rangeForRegex)) {
                            return tag2;
                        }
                    }
                    int index = rangeForRegex.lastIndexOf(45);
                    if (index >= 0) {
                        rangeForRegex = rangeForRegex.substring(0, index);
                        if (rangeForRegex.lastIndexOf(45) == rangeForRegex.length() - 2) {
                            rangeForRegex = rangeForRegex.substring(0, rangeForRegex.length() - 2);
                        }
                    } else {
                        rangeForRegex = "";
                    }
                }
                continue;
            }
        }
        return null;
    }

    /* JADX INFO: Multiple debug info for r1v7 java.lang.String: [D('ranges' java.lang.String), D('equiv' java.lang.String)] */
    public static List<Locale.LanguageRange> parse(String ranges) {
        String ranges2;
        double w;
        String r;
        int i;
        String[] langRanges;
        String ranges3;
        int i2;
        String[] langRanges2;
        String ranges4 = ranges.replace(Padder.FALLBACK_PADDING_STRING, "").toLowerCase();
        if (ranges4.startsWith("accept-language:")) {
            ranges2 = ranges4.substring(16);
        } else {
            ranges2 = ranges4;
        }
        String[] langRanges3 = ranges2.split(",");
        List<Locale.LanguageRange> list = new ArrayList<>(langRanges3.length);
        List<String> tempList = new ArrayList<>();
        int length = langRanges3.length;
        int i3 = 0;
        int numOfRanges = 0;
        int numOfRanges2 = 0;
        while (numOfRanges2 < length) {
            String range = langRanges3[numOfRanges2];
            int index = range.indexOf(";q=");
            if (index == -1) {
                r = range;
                w = 1.0d;
            } else {
                r = range.substring(i3, index);
                int index2 = index + 3;
                try {
                    w = Double.parseDouble(range.substring(index2));
                    if (w < 0.0d || w > 1.0d) {
                        throw new IllegalArgumentException("weight=" + w + " for language range \"" + r + "\". It must be between " + 0.0d + " and " + 1.0d + ".");
                    }
                } catch (Exception e) {
                    throw new IllegalArgumentException("weight=\"" + range.substring(index2) + "\" for language range \"" + r + "\"");
                }
            }
            if (!tempList.contains(r)) {
                Locale.LanguageRange lr = new Locale.LanguageRange(r, w);
                int index3 = numOfRanges;
                int j = 0;
                while (true) {
                    if (j >= numOfRanges) {
                        break;
                    } else if (list.get(j).getWeight() < w) {
                        index3 = j;
                        break;
                    } else {
                        j++;
                    }
                }
                list.add(index3, lr);
                numOfRanges++;
                tempList.add(r);
                String equivalent = getEquivalentForRegionAndVariant(r);
                if (equivalent != null && !tempList.contains(equivalent)) {
                    list.add(index3 + 1, new Locale.LanguageRange(equivalent, w));
                    numOfRanges++;
                    tempList.add(equivalent);
                }
                String[] equivalents = getEquivalentsForLanguage(r);
                if (equivalents != null) {
                    int length2 = equivalents.length;
                    int numOfRanges3 = numOfRanges;
                    int numOfRanges4 = 0;
                    while (numOfRanges4 < length2) {
                        String equiv = equivalents[numOfRanges4];
                        if (!tempList.contains(equiv)) {
                            langRanges2 = langRanges3;
                            i2 = length;
                            list.add(index3 + 1, new Locale.LanguageRange(equiv, w));
                            numOfRanges3++;
                            tempList.add(equiv);
                        } else {
                            langRanges2 = langRanges3;
                            i2 = length;
                        }
                        String equivalent2 = getEquivalentForRegionAndVariant(equiv);
                        if (equivalent2 != null && !tempList.contains(equivalent2)) {
                            list.add(index3 + 1, new Locale.LanguageRange(equivalent2, w));
                            numOfRanges3++;
                            tempList.add(equivalent2);
                        }
                        numOfRanges4++;
                        ranges2 = ranges2;
                        langRanges3 = langRanges2;
                        length = i2;
                    }
                    ranges3 = ranges2;
                    langRanges = langRanges3;
                    i = length;
                    numOfRanges = numOfRanges3;
                } else {
                    ranges3 = ranges2;
                    langRanges = langRanges3;
                    i = length;
                }
            } else {
                ranges3 = ranges2;
                langRanges = langRanges3;
                i = length;
            }
            numOfRanges2++;
            ranges2 = ranges3;
            langRanges3 = langRanges;
            length = i;
            i3 = 0;
        }
        return list;
    }

    private static String replaceFirstSubStringMatch(String range, String substr, String replacement) {
        int pos = range.indexOf(substr);
        if (pos == -1) {
            return range;
        }
        return range.substring(0, pos) + replacement + range.substring(substr.length() + pos);
    }

    private static String[] getEquivalentsForLanguage(String range) {
        String r = range;
        while (r.length() > 0) {
            if (LocaleEquivalentMaps.singleEquivMap.containsKey(r)) {
                return new String[]{replaceFirstSubStringMatch(range, r, LocaleEquivalentMaps.singleEquivMap.get(r))};
            } else if (LocaleEquivalentMaps.multiEquivsMap.containsKey(r)) {
                String[] equivs = LocaleEquivalentMaps.multiEquivsMap.get(r);
                String[] result = new String[equivs.length];
                for (int i = 0; i < equivs.length; i++) {
                    result[i] = replaceFirstSubStringMatch(range, r, equivs[i]);
                }
                return result;
            } else {
                int index = r.lastIndexOf(45);
                if (index == -1) {
                    return null;
                }
                r = r.substring(0, index);
            }
        }
        return null;
    }

    private static String getEquivalentForRegionAndVariant(String range) {
        int len;
        int extensionKeyIndex = getExtentionKeyIndex(range);
        for (String subtag : LocaleEquivalentMaps.regionVariantEquivMap.keySet()) {
            int index = range.indexOf(subtag);
            if (index != -1 && ((extensionKeyIndex == Integer.MIN_VALUE || index <= extensionKeyIndex) && (range.length() == (len = subtag.length() + index) || range.charAt(len) == '-'))) {
                return replaceFirstSubStringMatch(range, subtag, LocaleEquivalentMaps.regionVariantEquivMap.get(subtag));
            }
        }
        return null;
    }

    private static int getExtentionKeyIndex(String s) {
        char[] c = s.toCharArray();
        int index = Integer.MIN_VALUE;
        for (int i = 1; i < c.length; i++) {
            if (c[i] == '-') {
                if (i - index == 2) {
                    return index;
                }
                index = i;
            }
        }
        return Integer.MIN_VALUE;
    }

    public static List<Locale.LanguageRange> mapEquivalents(List<Locale.LanguageRange> priorityList, Map<String, List<String>> map) {
        if (priorityList.isEmpty()) {
            return new ArrayList();
        }
        if (map == null || map.isEmpty()) {
            return new ArrayList(priorityList);
        }
        Map<String, String> keyMap = new HashMap<>();
        for (String key : map.keySet()) {
            keyMap.put(key.toLowerCase(), key);
        }
        List<Locale.LanguageRange> list = new ArrayList<>();
        for (Locale.LanguageRange lr : priorityList) {
            String range = lr.getRange();
            String r = range;
            boolean hasEquivalent = false;
            while (true) {
                if (r.length() <= 0) {
                    break;
                } else if (keyMap.containsKey(r)) {
                    hasEquivalent = true;
                    List<String> equivalents = map.get(keyMap.get(r));
                    if (equivalents != null) {
                        int len = r.length();
                        Iterator<String> it = equivalents.iterator();
                        while (it.hasNext()) {
                            list.add(new Locale.LanguageRange(it.next().toLowerCase() + range.substring(len), lr.getWeight()));
                        }
                    }
                } else {
                    int index = r.lastIndexOf(45);
                    if (index == -1) {
                        break;
                    }
                    r = r.substring(0, index);
                }
            }
            if (!hasEquivalent) {
                list.add(lr);
            }
        }
        return list;
    }

    private LocaleMatcher() {
    }
}
