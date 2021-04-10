package android.icu.text;

import android.icu.impl.Normalizer2Impl;
import android.icu.lang.UCharacter;
import android.icu.text.UTF16;
import android.icu.util.LocaleData;
import android.icu.util.ULocale;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public final class AlphabeticIndex<V> implements Iterable<Bucket<V>> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String BASE = "﷐";
    private static final char CGJ = 847;
    private static final int GC_CN_MASK = 1;
    private static final int GC_LL_MASK = 4;
    private static final int GC_LM_MASK = 16;
    private static final int GC_LO_MASK = 32;
    private static final int GC_LT_MASK = 8;
    private static final int GC_LU_MASK = 2;
    private static final int GC_L_MASK = 62;
    private static final Comparator<String> binaryCmp = new UTF16.StringComparator(true, false, 0);
    private BucketList<V> buckets;
    private RuleBasedCollator collatorExternal;
    private final RuleBasedCollator collatorOriginal;
    private final RuleBasedCollator collatorPrimaryOnly;
    private final List<String> firstCharsInScripts;
    private String inflowLabel;
    private final UnicodeSet initialLabels;
    private List<Record<V>> inputList;
    private int maxLabelCount;
    private String overflowLabel;
    private final Comparator<Record<V>> recordComparator;
    private String underflowLabel;

    public static final class ImmutableIndex<V> implements Iterable<Bucket<V>> {
        private final BucketList<V> buckets;
        private final Collator collatorPrimaryOnly;

        private ImmutableIndex(BucketList<V> bucketList, Collator collatorPrimaryOnly2) {
            this.buckets = bucketList;
            this.collatorPrimaryOnly = collatorPrimaryOnly2;
        }

        public int getBucketCount() {
            return this.buckets.getBucketCount();
        }

        public int getBucketIndex(CharSequence name) {
            return this.buckets.getBucketIndex(name, this.collatorPrimaryOnly);
        }

        public Bucket<V> getBucket(int index) {
            if (index < 0 || index >= this.buckets.getBucketCount()) {
                return null;
            }
            return (Bucket) ((BucketList) this.buckets).immutableVisibleList.get(index);
        }

        @Override // java.lang.Iterable
        public Iterator<Bucket<V>> iterator() {
            return this.buckets.iterator();
        }
    }

    public AlphabeticIndex(ULocale locale) {
        this(locale, null);
    }

    public AlphabeticIndex(Locale locale) {
        this(ULocale.forLocale(locale), null);
    }

    public AlphabeticIndex(RuleBasedCollator collator) {
        this(null, collator);
    }

    private AlphabeticIndex(ULocale locale, RuleBasedCollator collator) {
        this.recordComparator = new Comparator<Record<V>>() {
            /* class android.icu.text.AlphabeticIndex.AnonymousClass1 */

            @Override // java.util.Comparator
            public /* bridge */ /* synthetic */ int compare(Object obj, Object obj2) {
                return compare((Record) ((Record) obj), (Record) ((Record) obj2));
            }

            public int compare(Record<V> o1, Record<V> o2) {
                return AlphabeticIndex.this.collatorOriginal.compare(((Record) o1).name, ((Record) o2).name);
            }
        };
        this.initialLabels = new UnicodeSet();
        this.overflowLabel = "…";
        this.underflowLabel = "…";
        this.inflowLabel = "…";
        this.maxLabelCount = 99;
        this.collatorOriginal = collator != null ? collator : (RuleBasedCollator) Collator.getInstance(locale);
        try {
            this.collatorPrimaryOnly = this.collatorOriginal.cloneAsThawed();
            this.collatorPrimaryOnly.setStrength(0);
            this.collatorPrimaryOnly.freeze();
            this.firstCharsInScripts = getFirstCharactersInScripts();
            Collections.sort(this.firstCharsInScripts, this.collatorPrimaryOnly);
            while (!this.firstCharsInScripts.isEmpty()) {
                if (this.collatorPrimaryOnly.compare(this.firstCharsInScripts.get(0), "") == 0) {
                    this.firstCharsInScripts.remove(0);
                } else if (!addChineseIndexCharacters() && locale != null) {
                    addIndexExemplars(locale);
                    return;
                } else {
                    return;
                }
            }
            throw new IllegalArgumentException("AlphabeticIndex requires some non-ignorable script boundary strings");
        } catch (Exception e) {
            throw new IllegalStateException("Collator cannot be cloned", e);
        }
    }

    public AlphabeticIndex<V> addLabels(UnicodeSet additions) {
        this.initialLabels.addAll(additions);
        this.buckets = null;
        return this;
    }

    public AlphabeticIndex<V> addLabels(ULocale... additions) {
        for (ULocale addition : additions) {
            addIndexExemplars(addition);
        }
        this.buckets = null;
        return this;
    }

    public AlphabeticIndex<V> addLabels(Locale... additions) {
        for (Locale addition : additions) {
            addIndexExemplars(ULocale.forLocale(addition));
        }
        this.buckets = null;
        return this;
    }

    public AlphabeticIndex<V> setOverflowLabel(String overflowLabel2) {
        this.overflowLabel = overflowLabel2;
        this.buckets = null;
        return this;
    }

    public String getUnderflowLabel() {
        return this.underflowLabel;
    }

    public AlphabeticIndex<V> setUnderflowLabel(String underflowLabel2) {
        this.underflowLabel = underflowLabel2;
        this.buckets = null;
        return this;
    }

    public String getOverflowLabel() {
        return this.overflowLabel;
    }

    public AlphabeticIndex<V> setInflowLabel(String inflowLabel2) {
        this.inflowLabel = inflowLabel2;
        this.buckets = null;
        return this;
    }

    public String getInflowLabel() {
        return this.inflowLabel;
    }

    public int getMaxLabelCount() {
        return this.maxLabelCount;
    }

    public AlphabeticIndex<V> setMaxLabelCount(int maxLabelCount2) {
        this.maxLabelCount = maxLabelCount2;
        this.buckets = null;
        return this;
    }

    private List<String> initLabels() {
        boolean checkDistinct;
        Normalizer2 nfkdNormalizer = Normalizer2.getNFKDInstance();
        List<String> indexCharacters = new ArrayList<>();
        String firstScriptBoundary = this.firstCharsInScripts.get(0);
        List<String> list = this.firstCharsInScripts;
        String overflowBoundary = list.get(list.size() - 1);
        Iterator<String> it = this.initialLabels.iterator();
        while (it.hasNext()) {
            String item = it.next();
            if (!UTF16.hasMoreCodePointsThan(item, 1)) {
                checkDistinct = false;
            } else if (item.charAt(item.length() - 1) != '*' || item.charAt(item.length() - 2) == '*') {
                checkDistinct = true;
            } else {
                item = item.substring(0, item.length() - 1);
                checkDistinct = false;
            }
            if (this.collatorPrimaryOnly.compare(item, firstScriptBoundary) >= 0 && this.collatorPrimaryOnly.compare(item, overflowBoundary) < 0) {
                if (!checkDistinct || this.collatorPrimaryOnly.compare(item, separated(item)) != 0) {
                    int insertionPoint = Collections.binarySearch(indexCharacters, item, this.collatorPrimaryOnly);
                    if (insertionPoint < 0) {
                        indexCharacters.add(~insertionPoint, item);
                    } else if (isOneLabelBetterThanOther(nfkdNormalizer, item, indexCharacters.get(insertionPoint))) {
                        indexCharacters.set(insertionPoint, item);
                    }
                }
            }
        }
        int size = indexCharacters.size() - 1;
        if (size > this.maxLabelCount) {
            int count = 0;
            int old = -1;
            Iterator<String> it2 = indexCharacters.iterator();
            while (it2.hasNext()) {
                count++;
                it2.next();
                int bump = (this.maxLabelCount * count) / size;
                if (bump == old) {
                    it2.remove();
                } else {
                    old = bump;
                }
            }
        }
        return indexCharacters;
    }

    private static String fixLabel(String current) {
        if (!current.startsWith(BASE)) {
            return current;
        }
        int rest = current.charAt(BASE.length());
        if (10240 >= rest || rest > 10495) {
            return current.substring(BASE.length());
        }
        StringBuilder sb = new StringBuilder();
        sb.append(rest - 10240);
        sb.append("劃");
        return sb.toString();
    }

    private void addIndexExemplars(ULocale locale) {
        UnicodeSet exemplars = LocaleData.getExemplarSet(locale, 0, 2);
        if (exemplars == null || exemplars.isEmpty()) {
            UnicodeSet exemplars2 = LocaleData.getExemplarSet(locale, 0, 0).cloneAsThawed();
            if (exemplars2.containsSome(97, 122) || exemplars2.isEmpty()) {
                exemplars2.addAll(97, 122);
            }
            if (exemplars2.containsSome(Normalizer2Impl.Hangul.HANGUL_BASE, Normalizer2Impl.Hangul.HANGUL_END)) {
                exemplars2.remove(Normalizer2Impl.Hangul.HANGUL_BASE, Normalizer2Impl.Hangul.HANGUL_END).add(Normalizer2Impl.Hangul.HANGUL_BASE).add(45208).add(45796).add(46972).add(47560).add(48148).add(49324).add(50500).add(51088).add(52264).add(52852).add(53440).add(54028).add(54616);
            }
            if (exemplars2.containsSome(4608, 4991)) {
                UnicodeSet ethiopic = new UnicodeSet("[ሀለሐመሠረሰሸቀቈቐቘበቨተቸኀኈነኘአከኰኸዀወዐዘዠየደዸጀገጐጘጠጨጰጸፀፈፐፘ]");
                ethiopic.retainAll(exemplars2);
                exemplars2.remove(4608, 4991).addAll(ethiopic);
            }
            Iterator<String> it = exemplars2.iterator();
            while (it.hasNext()) {
                this.initialLabels.add(UCharacter.toUpperCase(locale, it.next()));
            }
            return;
        }
        this.initialLabels.addAll(exemplars);
    }

    private boolean addChineseIndexCharacters() {
        UnicodeSet contractions = new UnicodeSet();
        try {
            this.collatorPrimaryOnly.internalAddContractions(BASE.charAt(0), contractions);
            if (contractions.isEmpty()) {
                return false;
            }
            this.initialLabels.addAll(contractions);
            Iterator<String> it = contractions.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                String s = it.next();
                char c = s.charAt(s.length() - 1);
                if ('A' <= c && c <= 'Z') {
                    this.initialLabels.add(65, 90);
                    break;
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private String separated(String item) {
        StringBuilder result = new StringBuilder();
        char last = item.charAt(0);
        result.append(last);
        for (int i = 1; i < item.length(); i++) {
            char ch = item.charAt(i);
            if (!UCharacter.isHighSurrogate(last) || !UCharacter.isLowSurrogate(ch)) {
                result.append(CGJ);
            }
            result.append(ch);
            last = ch;
        }
        return result.toString();
    }

    public ImmutableIndex<V> buildImmutableIndex() {
        BucketList<V> immutableBucketList;
        List<Record<V>> list = this.inputList;
        if (list == null || list.isEmpty()) {
            if (this.buckets == null) {
                this.buckets = createBucketList();
            }
            immutableBucketList = this.buckets;
        } else {
            immutableBucketList = createBucketList();
        }
        return new ImmutableIndex<>(immutableBucketList, this.collatorPrimaryOnly);
    }

    public List<String> getBucketLabels() {
        initBuckets();
        ArrayList<String> result = new ArrayList<>();
        Iterator<Bucket<V>> it = this.buckets.iterator();
        while (it.hasNext()) {
            result.add(it.next().getLabel());
        }
        return result;
    }

    public RuleBasedCollator getCollator() {
        if (this.collatorExternal == null) {
            try {
                this.collatorExternal = (RuleBasedCollator) this.collatorOriginal.clone();
            } catch (Exception e) {
                throw new IllegalStateException("Collator cannot be cloned", e);
            }
        }
        return this.collatorExternal;
    }

    public AlphabeticIndex<V> addRecord(CharSequence name, V data) {
        this.buckets = null;
        if (this.inputList == null) {
            this.inputList = new ArrayList();
        }
        this.inputList.add(new Record<>(name, data));
        return this;
    }

    public int getBucketIndex(CharSequence name) {
        initBuckets();
        return this.buckets.getBucketIndex(name, this.collatorPrimaryOnly);
    }

    public AlphabeticIndex<V> clearRecords() {
        List<Record<V>> list = this.inputList;
        if (list != null && !list.isEmpty()) {
            this.inputList.clear();
            this.buckets = null;
        }
        return this;
    }

    public int getBucketCount() {
        initBuckets();
        return this.buckets.getBucketCount();
    }

    public int getRecordCount() {
        List<Record<V>> list = this.inputList;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override // java.lang.Iterable
    public Iterator<Bucket<V>> iterator() {
        initBuckets();
        return this.buckets.iterator();
    }

    private void initBuckets() {
        String upperBoundary;
        Bucket<V> nextBucket;
        if (this.buckets == null) {
            this.buckets = createBucketList();
            List<Record<V>> list = this.inputList;
            if (list != null && !list.isEmpty()) {
                Collections.sort(this.inputList, this.recordComparator);
                Iterator<Bucket<V>> bucketIterator = this.buckets.fullIterator();
                Bucket<V> currentBucket = bucketIterator.next();
                if (bucketIterator.hasNext()) {
                    nextBucket = bucketIterator.next();
                    upperBoundary = ((Bucket) nextBucket).lowerBoundary;
                } else {
                    nextBucket = null;
                    upperBoundary = null;
                }
                Iterator<Record<V>> it = this.inputList.iterator();
                while (it.hasNext()) {
                    Record<V> r = it.next();
                    while (upperBoundary != null && this.collatorPrimaryOnly.compare(((Record) r).name, upperBoundary) >= 0) {
                        currentBucket = nextBucket;
                        if (bucketIterator.hasNext()) {
                            nextBucket = bucketIterator.next();
                            upperBoundary = ((Bucket) nextBucket).lowerBoundary;
                        } else {
                            upperBoundary = null;
                        }
                    }
                    Bucket<V> bucket = currentBucket;
                    if (((Bucket) bucket).displayBucket != null) {
                        bucket = ((Bucket) bucket).displayBucket;
                    }
                    if (((Bucket) bucket).records == null) {
                        ((Bucket) bucket).records = new ArrayList();
                    }
                    ((Bucket) bucket).records.add(r);
                }
            }
        }
    }

    private static boolean isOneLabelBetterThanOther(Normalizer2 nfkdNormalizer, String one, String other) {
        String n1 = nfkdNormalizer.normalize(one);
        String n2 = nfkdNormalizer.normalize(other);
        int result = n1.codePointCount(0, n1.length()) - n2.codePointCount(0, n2.length());
        if (result == 0) {
            int result2 = binaryCmp.compare(n1, n2);
            if (result2 != 0) {
                if (result2 < 0) {
                    return true;
                }
                return false;
            } else if (binaryCmp.compare(one, other) < 0) {
                return true;
            } else {
                return false;
            }
        } else if (result < 0) {
            return true;
        } else {
            return false;
        }
    }

    public static class Record<V> {
        private final V data;
        private final CharSequence name;

        private Record(CharSequence name2, V data2) {
            this.name = name2;
            this.data = data2;
        }

        public CharSequence getName() {
            return this.name;
        }

        public V getData() {
            return this.data;
        }

        public String toString() {
            return ((Object) this.name) + "=" + ((Object) this.data);
        }
    }

    public static class Bucket<V> implements Iterable<Record<V>> {
        private Bucket<V> displayBucket;
        private int displayIndex;
        private final String label;
        private final LabelType labelType;
        private final String lowerBoundary;
        private List<Record<V>> records;

        public enum LabelType {
            NORMAL,
            UNDERFLOW,
            INFLOW,
            OVERFLOW
        }

        private Bucket(String label2, String lowerBoundary2, LabelType labelType2) {
            this.label = label2;
            this.lowerBoundary = lowerBoundary2;
            this.labelType = labelType2;
        }

        public String getLabel() {
            return this.label;
        }

        public LabelType getLabelType() {
            return this.labelType;
        }

        public int size() {
            List<Record<V>> list = this.records;
            if (list == null) {
                return 0;
            }
            return list.size();
        }

        @Override // java.lang.Iterable
        public Iterator<Record<V>> iterator() {
            List<Record<V>> list = this.records;
            if (list == null) {
                return Collections.emptyList().iterator();
            }
            return list.iterator();
        }

        public String toString() {
            return "{labelType=" + ((Object) this.labelType) + ", lowerBoundary=" + this.lowerBoundary + ", label=" + this.label + "}";
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x0100  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0168  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.icu.text.AlphabeticIndex.BucketList<V> createBucketList() {
        /*
        // Method dump skipped, instructions count: 539
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.text.AlphabeticIndex.createBucketList():android.icu.text.AlphabeticIndex$BucketList");
    }

    /* access modifiers changed from: private */
    public static class BucketList<V> implements Iterable<Bucket<V>> {
        private final ArrayList<Bucket<V>> bucketList;
        private final List<Bucket<V>> immutableVisibleList;

        private BucketList(ArrayList<Bucket<V>> bucketList2, ArrayList<Bucket<V>> publicBucketList) {
            this.bucketList = bucketList2;
            int displayIndex = 0;
            Iterator<Bucket<V>> it = publicBucketList.iterator();
            while (it.hasNext()) {
                ((Bucket) it.next()).displayIndex = displayIndex;
                displayIndex++;
            }
            this.immutableVisibleList = Collections.unmodifiableList(publicBucketList);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private int getBucketCount() {
            return this.immutableVisibleList.size();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private int getBucketIndex(CharSequence name, Collator collatorPrimaryOnly) {
            int start = 0;
            int limit = this.bucketList.size();
            while (start + 1 < limit) {
                int i = (start + limit) / 2;
                if (collatorPrimaryOnly.compare(name, ((Bucket) this.bucketList.get(i)).lowerBoundary) < 0) {
                    limit = i;
                } else {
                    start = i;
                }
            }
            Bucket<V> bucket = this.bucketList.get(start);
            if (((Bucket) bucket).displayBucket != null) {
                bucket = ((Bucket) bucket).displayBucket;
            }
            return ((Bucket) bucket).displayIndex;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private Iterator<Bucket<V>> fullIterator() {
            return this.bucketList.iterator();
        }

        @Override // java.lang.Iterable
        public Iterator<Bucket<V>> iterator() {
            return this.immutableVisibleList.iterator();
        }
    }

    private static boolean hasMultiplePrimaryWeights(RuleBasedCollator coll, long variableTop, String s) {
        long[] ces;
        boolean seenPrimary = false;
        for (long ce : coll.internalGetCEs(s)) {
            if ((ce >>> 32) > variableTop) {
                if (seenPrimary) {
                    return true;
                }
                seenPrimary = true;
            }
        }
        return false;
    }

    @Deprecated
    public List<String> getFirstCharactersInScripts() {
        List<String> dest = new ArrayList<>(200);
        UnicodeSet set = new UnicodeSet();
        this.collatorPrimaryOnly.internalAddContractions(64977, set);
        if (!set.isEmpty()) {
            Iterator<String> it = set.iterator();
            while (it.hasNext()) {
                String boundary = it.next();
                if (((1 << UCharacter.getType(boundary.codePointAt(1))) & 63) != 0) {
                    dest.add(boundary);
                }
            }
            return dest;
        }
        throw new UnsupportedOperationException("AlphabeticIndex requires script-first-primary contractions");
    }
}
