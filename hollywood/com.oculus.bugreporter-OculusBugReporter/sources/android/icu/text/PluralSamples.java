package android.icu.text;

import android.icu.text.PluralRules;
import android.icu.util.Output;
import dalvik.system.VMRuntime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

@Deprecated
public class PluralSamples {
    private static final int LIMIT_FRACTION_SAMPLES = 3;
    private static final int[] TENS = {1, 10, 100, 1000, VMRuntime.SDK_VERSION_CUR_DEVELOPMENT, 100000, 1000000};
    private final Set<PluralRules.FixedDecimal> _fractionSamples;
    private final Map<String, Set<PluralRules.FixedDecimal>> _keyFractionSamplesMap;
    @Deprecated
    public final Map<String, Boolean> _keyLimitedMap;
    private final Map<String, List<Double>> _keySamplesMap;
    private PluralRules pluralRules;

    @Deprecated
    public PluralSamples(PluralRules pluralRules2) {
        this.pluralRules = pluralRules2;
        Set<String> keywords = pluralRules2.getKeywords();
        Map<String, Boolean> temp = new HashMap<>();
        for (String k : keywords) {
            temp.put(k, pluralRules2.isLimited(k));
        }
        this._keyLimitedMap = temp;
        Map<String, List<Double>> sampleMap = new HashMap<>();
        int keywordsRemaining = keywords.size();
        int i = 0;
        while (keywordsRemaining > 0 && i < 128) {
            keywordsRemaining = addSimpleSamples(pluralRules2, 3, sampleMap, keywordsRemaining, ((double) i) / 2.0d);
            i++;
        }
        int keywordsRemaining2 = addSimpleSamples(pluralRules2, 3, sampleMap, keywordsRemaining, 1000000.0d);
        Map<String, Set<PluralRules.FixedDecimal>> sampleFractionMap = new HashMap<>();
        Set<PluralRules.FixedDecimal> mentioned = new TreeSet<>();
        Map<String, Set<PluralRules.FixedDecimal>> foundKeywords = new HashMap<>();
        for (PluralRules.FixedDecimal s : mentioned) {
            addRelation(foundKeywords, pluralRules2.select(s), s);
        }
        if (foundKeywords.size() != keywords.size()) {
            int i2 = 1;
            while (true) {
                if (i2 >= 1000) {
                    int i3 = 10;
                    while (true) {
                        if (i3 >= 1000) {
                            System.out.println("Failed to find sample for each keyword: " + ((Object) foundKeywords) + "\n\t" + ((Object) pluralRules2) + "\n\t" + ((Object) mentioned));
                            break;
                        } else if (addIfNotPresent(((double) i3) / 10.0d, mentioned, foundKeywords)) {
                            break;
                        } else {
                            i3++;
                        }
                    }
                } else if (addIfNotPresent((double) i2, mentioned, foundKeywords)) {
                    break;
                } else {
                    i2++;
                }
            }
        }
        mentioned.add(new PluralRules.FixedDecimal(0L));
        mentioned.add(new PluralRules.FixedDecimal(1L));
        mentioned.add(new PluralRules.FixedDecimal(2L));
        mentioned.add(new PluralRules.FixedDecimal(0.1d, 1));
        mentioned.add(new PluralRules.FixedDecimal(1.99d, 2));
        mentioned.addAll(fractions(mentioned));
        for (PluralRules.FixedDecimal s2 : mentioned) {
            String keyword = pluralRules2.select(s2);
            Set<PluralRules.FixedDecimal> list = sampleFractionMap.get(keyword);
            if (list == null) {
                list = new LinkedHashSet<>();
                sampleFractionMap.put(keyword, list);
            }
            list.add(s2);
        }
        if (keywordsRemaining2 > 0) {
            for (String k2 : keywords) {
                if (!sampleMap.containsKey(k2)) {
                    sampleMap.put(k2, Collections.emptyList());
                }
                if (!sampleFractionMap.containsKey(k2)) {
                    sampleFractionMap.put(k2, Collections.emptySet());
                }
            }
        }
        for (Map.Entry<String, List<Double>> entry : sampleMap.entrySet()) {
            sampleMap.put(entry.getKey(), Collections.unmodifiableList(entry.getValue()));
        }
        for (Map.Entry<String, Set<PluralRules.FixedDecimal>> entry2 : sampleFractionMap.entrySet()) {
            sampleFractionMap.put(entry2.getKey(), Collections.unmodifiableSet(entry2.getValue()));
        }
        this._keySamplesMap = sampleMap;
        this._keyFractionSamplesMap = sampleFractionMap;
        this._fractionSamples = Collections.unmodifiableSet(mentioned);
    }

    private int addSimpleSamples(PluralRules pluralRules2, int MAX_SAMPLES, Map<String, List<Double>> sampleMap, int keywordsRemaining, double val) {
        String keyword = pluralRules2.select(val);
        boolean keyIsLimited = this._keyLimitedMap.get(keyword).booleanValue();
        List<Double> list = sampleMap.get(keyword);
        if (list == null) {
            list = new ArrayList(MAX_SAMPLES);
            sampleMap.put(keyword, list);
        } else if (!keyIsLimited && list.size() == MAX_SAMPLES) {
            return keywordsRemaining;
        }
        list.add(Double.valueOf(val));
        if (keyIsLimited || list.size() != MAX_SAMPLES) {
            return keywordsRemaining;
        }
        return keywordsRemaining - 1;
    }

    private void addRelation(Map<String, Set<PluralRules.FixedDecimal>> foundKeywords, String keyword, PluralRules.FixedDecimal s) {
        Set<PluralRules.FixedDecimal> set = foundKeywords.get(keyword);
        if (set == null) {
            HashSet hashSet = new HashSet();
            set = hashSet;
            foundKeywords.put(keyword, hashSet);
        }
        set.add(s);
    }

    private boolean addIfNotPresent(double d, Set<PluralRules.FixedDecimal> mentioned, Map<String, Set<PluralRules.FixedDecimal>> foundKeywords) {
        PluralRules.FixedDecimal numberInfo = new PluralRules.FixedDecimal(d);
        String keyword = this.pluralRules.select(numberInfo);
        if (foundKeywords.containsKey(keyword) && !keyword.equals(PluralRules.KEYWORD_OTHER)) {
            return false;
        }
        addRelation(foundKeywords, keyword, numberInfo);
        mentioned.add(numberInfo);
        if (!keyword.equals(PluralRules.KEYWORD_OTHER) || foundKeywords.get(PluralRules.KEYWORD_OTHER).size() <= 1) {
            return false;
        }
        return true;
    }

    private Set<PluralRules.FixedDecimal> fractions(Set<PluralRules.FixedDecimal> original) {
        List<Integer> ints;
        Set<Integer> result;
        PluralSamples pluralSamples = this;
        Set<PluralRules.FixedDecimal> toAddTo = new HashSet<>();
        Set<Integer> result2 = new HashSet<>();
        for (PluralRules.FixedDecimal base1 : original) {
            result2.add(Integer.valueOf((int) base1.integerValue));
        }
        List<Integer> ints2 = new ArrayList<>(result2);
        Set<String> keywords = new HashSet<>();
        int j = 0;
        while (j < ints2.size()) {
            Integer base = ints2.get(j);
            String keyword = pluralSamples.pluralRules.select((double) base.intValue());
            if (!keywords.contains(keyword)) {
                keywords.add(keyword);
                toAddTo.add(new PluralRules.FixedDecimal((double) base.intValue(), 1));
                toAddTo.add(new PluralRules.FixedDecimal((double) base.intValue(), 2));
                Integer fract = pluralSamples.getDifferentCategory(ints2, keyword);
                if (fract.intValue() >= TENS[2]) {
                    toAddTo.add(new PluralRules.FixedDecimal(((Object) base) + "." + ((Object) fract)));
                } else {
                    for (int visibleFractions = 1; visibleFractions < 3; visibleFractions++) {
                        int i = 1;
                        while (i <= visibleFractions) {
                            if (fract.intValue() >= TENS[i]) {
                                result = result2;
                                ints = ints2;
                            } else {
                                result = result2;
                                ints = ints2;
                                toAddTo.add(new PluralRules.FixedDecimal(((double) base.intValue()) + (((double) fract.intValue()) / ((double) TENS[i])), visibleFractions));
                            }
                            i++;
                            result2 = result;
                            ints2 = ints;
                        }
                    }
                }
            }
            j++;
            pluralSamples = this;
            result2 = result2;
            ints2 = ints2;
        }
        return toAddTo;
    }

    private Integer getDifferentCategory(List<Integer> ints, String keyword) {
        for (int i = ints.size() - 1; i >= 0; i--) {
            Integer other = ints.get(i);
            if (!this.pluralRules.select((double) other.intValue()).equals(keyword)) {
                return other;
            }
        }
        return 37;
    }

    @Deprecated
    public PluralRules.KeywordStatus getStatus(String keyword, int offset, Set<Double> explicits, Output<Double> uniqueValue) {
        if (uniqueValue != null) {
            uniqueValue.value = null;
        }
        if (!this.pluralRules.getKeywords().contains(keyword)) {
            return PluralRules.KeywordStatus.INVALID;
        }
        Collection<Double> values = this.pluralRules.getAllKeywordValues(keyword);
        if (values == null) {
            return PluralRules.KeywordStatus.UNBOUNDED;
        }
        int originalSize = values.size();
        if (explicits == null) {
            explicits = Collections.emptySet();
        }
        if (originalSize <= explicits.size()) {
            HashSet<Double> subtractedSet = new HashSet<>(values);
            for (Double explicit : explicits) {
                subtractedSet.remove(Double.valueOf(explicit.doubleValue() - ((double) offset)));
            }
            if (subtractedSet.size() == 0) {
                return PluralRules.KeywordStatus.SUPPRESSED;
            }
            if (uniqueValue != null && subtractedSet.size() == 1) {
                uniqueValue.value = (T) subtractedSet.iterator().next();
            }
            return originalSize == 1 ? PluralRules.KeywordStatus.UNIQUE : PluralRules.KeywordStatus.BOUNDED;
        } else if (originalSize != 1) {
            return PluralRules.KeywordStatus.BOUNDED;
        } else {
            if (uniqueValue != null) {
                uniqueValue.value = (T) values.iterator().next();
            }
            return PluralRules.KeywordStatus.UNIQUE;
        }
    }

    /* access modifiers changed from: package-private */
    public Map<String, List<Double>> getKeySamplesMap() {
        return this._keySamplesMap;
    }

    /* access modifiers changed from: package-private */
    public Map<String, Set<PluralRules.FixedDecimal>> getKeyFractionSamplesMap() {
        return this._keyFractionSamplesMap;
    }

    /* access modifiers changed from: package-private */
    public Set<PluralRules.FixedDecimal> getFractionSamples() {
        return this._fractionSamples;
    }

    /* access modifiers changed from: package-private */
    public Collection<Double> getAllKeywordValues(String keyword) {
        if (!this.pluralRules.getKeywords().contains(keyword)) {
            return Collections.emptyList();
        }
        Collection<Double> result = getKeySamplesMap().get(keyword);
        if (result.size() <= 2 || this._keyLimitedMap.get(keyword).booleanValue()) {
            return result;
        }
        return null;
    }
}
