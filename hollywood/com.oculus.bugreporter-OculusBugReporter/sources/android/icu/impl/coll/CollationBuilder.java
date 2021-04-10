package android.icu.impl.coll;

import android.icu.impl.Norm2AllModes;
import android.icu.impl.Normalizer2Impl;
import android.icu.impl.coll.CollationDataBuilder;
import android.icu.impl.coll.CollationRuleParser;
import android.icu.text.CanonicalIterator;
import android.icu.text.Normalizer2;
import android.icu.text.UnicodeSet;
import android.icu.text.UnicodeSetIterator;
import android.icu.util.ULocale;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v4.view.InputDeviceCompat;
import android.support.v4.view.ViewCompat;
import java.text.ParseException;

public final class CollationBuilder extends CollationRuleParser.Sink {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final UnicodeSet COMPOSITES = new UnicodeSet("[:NFD_QC=N:]");
    private static final boolean DEBUG = false;
    private static final int HAS_BEFORE2 = 64;
    private static final int HAS_BEFORE3 = 32;
    private static final int IS_TAILORED = 8;
    private static final int MAX_INDEX = 1048575;
    private CollationTailoring base;
    private CollationData baseData;
    private long[] ces = new long[31];
    private int cesLength;
    private CollationDataBuilder dataBuilder;
    private boolean fastLatinEnabled;
    private Normalizer2 fcd = Norm2AllModes.getFCDNormalizer2();
    private Normalizer2Impl nfcImpl = Norm2AllModes.getNFCInstance().impl;
    private Normalizer2 nfd = Normalizer2.getNFDInstance();
    private UVector64 nodes;
    private UnicodeSet optimizeSet = new UnicodeSet();
    private CollationRootElements rootElements;
    private UVector32 rootPrimaryIndexes;
    private long variableTop;

    static {
        COMPOSITES.remove(Normalizer2Impl.Hangul.HANGUL_BASE, Normalizer2Impl.Hangul.HANGUL_END);
    }

    private static final class BundleImporter implements CollationRuleParser.Importer {
        BundleImporter() {
        }

        @Override // android.icu.impl.coll.CollationRuleParser.Importer
        public String getRules(String localeID, String collationType) {
            return CollationLoader.loadRules(new ULocale(localeID), collationType);
        }
    }

    public CollationBuilder(CollationTailoring b) {
        this.base = b;
        this.baseData = b.data;
        this.rootElements = new CollationRootElements(b.data.rootElements);
        this.variableTop = 0;
        this.dataBuilder = new CollationDataBuilder();
        this.fastLatinEnabled = true;
        this.cesLength = 0;
        this.rootPrimaryIndexes = new UVector32();
        this.nodes = new UVector64();
        this.nfcImpl.ensureCanonIterData();
        this.dataBuilder.initForTailoring(this.baseData);
    }

    public CollationTailoring parseAndBuild(String ruleString) throws ParseException {
        if (this.baseData.rootElements != null) {
            CollationTailoring tailoring = new CollationTailoring(this.base.settings);
            CollationRuleParser parser = new CollationRuleParser(this.baseData);
            this.variableTop = this.base.settings.readOnly().variableTop;
            parser.setSink(this);
            parser.setImporter(new BundleImporter());
            CollationSettings ownedSettings = tailoring.settings.copyOnWrite();
            parser.parse(ruleString, ownedSettings);
            if (this.dataBuilder.hasMappings()) {
                makeTailoredCEs();
                closeOverComposites();
                finalizeCEs();
                this.optimizeSet.add(0, 127);
                this.optimizeSet.add(192, 255);
                this.optimizeSet.remove(Normalizer2Impl.Hangul.HANGUL_BASE, Normalizer2Impl.Hangul.HANGUL_END);
                this.dataBuilder.optimize(this.optimizeSet);
                tailoring.ensureOwnedData();
                if (this.fastLatinEnabled) {
                    this.dataBuilder.enableFastLatin();
                }
                this.dataBuilder.build(tailoring.ownedData);
                this.dataBuilder = null;
            } else {
                tailoring.data = this.baseData;
            }
            ownedSettings.fastLatinOptions = CollationFastLatin.getOptions(tailoring.data, ownedSettings, ownedSettings.fastLatinPrimaries);
            tailoring.setRules(ruleString);
            tailoring.setVersion(this.base.version, 0);
            return tailoring;
        }
        throw new UnsupportedOperationException("missing root elements data, tailoring not supported");
    }

    /* access modifiers changed from: package-private */
    @Override // android.icu.impl.coll.CollationRuleParser.Sink
    public void addReset(int strength, CharSequence str) {
        int index;
        int previousWeight16;
        String str2;
        if (str.charAt(0) == 65534) {
            this.ces[0] = getSpecialResetPosition(str);
            this.cesLength = 1;
        } else {
            this.cesLength = this.dataBuilder.getCEs(this.nfd.normalize(str), this.ces, 0);
            if (this.cesLength > 31) {
                throw new IllegalArgumentException("reset position maps to too many collation elements (more than 31)");
            }
        }
        if (strength != 15) {
            int index2 = findOrInsertNodeForCEs(strength);
            long node = this.nodes.elementAti(index2);
            while (strengthFromNode(node) > strength) {
                index2 = previousIndexFromNode(node);
                node = this.nodes.elementAti(index2);
            }
            if (strengthFromNode(node) == strength && isTailoredNode(node)) {
                index = previousIndexFromNode(node);
            } else if (strength == 0) {
                long p = weight32FromNode(node);
                if (p == 0) {
                    throw new UnsupportedOperationException("reset primary-before ignorable not possible");
                } else if (p <= this.rootElements.getFirstPrimary()) {
                    throw new UnsupportedOperationException("reset primary-before first non-ignorable not supported");
                } else if (p != 4278321664L) {
                    index = findOrInsertNodeForPrimary(this.rootElements.getPrimaryBefore(p, this.baseData.isCompressiblePrimary(p)));
                    while (true) {
                        int nextIndex = nextIndexFromNode(this.nodes.elementAti(index));
                        if (nextIndex == 0) {
                            break;
                        }
                        index = nextIndex;
                    }
                } else {
                    throw new UnsupportedOperationException("reset primary-before [first trailing] not supported");
                }
            } else {
                int index3 = findCommonNode(index2, 1);
                if (strength >= 2) {
                    index3 = findCommonNode(index3, 2);
                }
                long node2 = this.nodes.elementAti(index3);
                if (strengthFromNode(node2) != strength) {
                    index = findOrInsertWeakNode(index3, getWeight16Before(index3, node2, strength), strength);
                } else if (weight16FromNode(node2) == 0) {
                    if (strength == 1) {
                        str2 = "reset secondary-before secondary ignorable not possible";
                    } else {
                        str2 = "reset tertiary-before completely ignorable not possible";
                    }
                    throw new UnsupportedOperationException(str2);
                } else {
                    int weight16 = getWeight16Before(index3, node2, strength);
                    int previousIndex = previousIndexFromNode(node2);
                    int i = previousIndex;
                    while (true) {
                        long node3 = this.nodes.elementAti(i);
                        int previousStrength = strengthFromNode(node3);
                        if (previousStrength >= strength) {
                            if (previousStrength == strength && !isTailoredNode(node3)) {
                                previousWeight16 = weight16FromNode(node3);
                                break;
                            }
                            i = previousIndexFromNode(node3);
                        } else {
                            previousWeight16 = Collation.COMMON_WEIGHT16;
                            break;
                        }
                    }
                    if (previousWeight16 == weight16) {
                        index = previousIndex;
                    } else {
                        index = insertNodeBetween(previousIndex, index3, nodeFromWeight16(weight16) | nodeFromStrength(strength));
                    }
                }
                strength = ceStrength(this.ces[this.cesLength - 1]);
            }
            this.ces[this.cesLength - 1] = tempCEFromIndexAndStrength(index, strength);
        }
    }

    private int getWeight16Before(int index, long node, int level) {
        int t;
        int s;
        if (strengthFromNode(node) == 2) {
            t = weight16FromNode(node);
        } else {
            t = Collation.COMMON_WEIGHT16;
        }
        while (strengthFromNode(node) > 1) {
            node = this.nodes.elementAti(previousIndexFromNode(node));
        }
        if (isTailoredNode(node)) {
            return 256;
        }
        if (strengthFromNode(node) == 1) {
            s = weight16FromNode(node);
        } else {
            s = Collation.COMMON_WEIGHT16;
        }
        while (strengthFromNode(node) > 0) {
            node = this.nodes.elementAti(previousIndexFromNode(node));
        }
        if (isTailoredNode(node)) {
            return 256;
        }
        long p = weight32FromNode(node);
        if (level == 1) {
            return this.rootElements.getSecondaryBefore(p, s);
        }
        return this.rootElements.getTertiaryBefore(p, s, t);
    }

    private long getSpecialResetPosition(CharSequence str) {
        boolean isBoundary;
        int strength;
        long ce;
        int strength2;
        CollationRuleParser.Position pos = CollationRuleParser.POSITION_VALUES[str.charAt(1) - 10240];
        switch (pos) {
            case FIRST_TERTIARY_IGNORABLE:
                return 0;
            case LAST_TERTIARY_IGNORABLE:
                return 0;
            case FIRST_SECONDARY_IGNORABLE:
                int index = nextIndexFromNode(this.nodes.elementAti(findOrInsertNodeForRootCE(0, 2)));
                if (index != 0) {
                    long node = this.nodes.elementAti(index);
                    if (isTailoredNode(node) && strengthFromNode(node) == 2) {
                        return tempCEFromIndexAndStrength(index, 2);
                    }
                }
                return this.rootElements.getFirstTertiaryCE();
            case LAST_SECONDARY_IGNORABLE:
                ce = this.rootElements.getLastTertiaryCE();
                strength = 2;
                isBoundary = false;
                break;
            case FIRST_PRIMARY_IGNORABLE:
                long node2 = this.nodes.elementAti(findOrInsertNodeForRootCE(0, 1));
                while (true) {
                    int nextIndexFromNode = nextIndexFromNode(node2);
                    int index2 = nextIndexFromNode;
                    if (nextIndexFromNode != 0 && (strength2 = strengthFromNode((node2 = this.nodes.elementAti(index2)))) >= 1) {
                        if (strength2 == 1) {
                            if (isTailoredNode(node2)) {
                                if (nodeHasBefore3(node2)) {
                                    index2 = nextIndexFromNode(this.nodes.elementAti(nextIndexFromNode(node2)));
                                }
                                return tempCEFromIndexAndStrength(index2, 1);
                            }
                        }
                    }
                }
                strength = 1;
                isBoundary = false;
                ce = this.rootElements.getFirstSecondaryCE();
                break;
            case LAST_PRIMARY_IGNORABLE:
                ce = this.rootElements.getLastSecondaryCE();
                strength = 1;
                isBoundary = false;
                break;
            case FIRST_VARIABLE:
                ce = this.rootElements.getFirstPrimaryCE();
                strength = 0;
                isBoundary = true;
                break;
            case LAST_VARIABLE:
                ce = this.rootElements.lastCEWithPrimaryBefore(this.variableTop + 1);
                strength = 0;
                isBoundary = false;
                break;
            case FIRST_REGULAR:
                ce = this.rootElements.firstCEWithPrimaryAtLeast(this.variableTop + 1);
                strength = 0;
                isBoundary = true;
                break;
            case LAST_REGULAR:
                ce = this.rootElements.firstCEWithPrimaryAtLeast(this.baseData.getFirstPrimaryForGroup(17));
                strength = 0;
                isBoundary = false;
                break;
            case FIRST_IMPLICIT:
                ce = this.baseData.getSingleCE(19968);
                strength = 0;
                isBoundary = false;
                break;
            case LAST_IMPLICIT:
                throw new UnsupportedOperationException("reset to [last implicit] not supported");
            case FIRST_TRAILING:
                ce = Collation.makeCE(4278321664L);
                strength = 0;
                isBoundary = true;
                break;
            case LAST_TRAILING:
                throw new IllegalArgumentException("LDML forbids tailoring to U+FFFF");
            default:
                return 0;
        }
        int index3 = findOrInsertNodeForRootCE(ce, strength);
        long node3 = this.nodes.elementAti(index3);
        if ((pos.ordinal() & 1) == 0) {
            if (!nodeHasAnyBefore(node3) && isBoundary) {
                int nextIndexFromNode2 = nextIndexFromNode(node3);
                index3 = nextIndexFromNode2;
                if (nextIndexFromNode2 != 0) {
                    node3 = this.nodes.elementAti(index3);
                    ce = tempCEFromIndexAndStrength(index3, strength);
                } else {
                    long p = ce >>> 32;
                    ce = Collation.makeCE(this.rootElements.getPrimaryAfter(p, this.rootElements.findPrimary(p), this.baseData.isCompressiblePrimary(p)));
                    index3 = findOrInsertNodeForRootCE(ce, 0);
                    node3 = this.nodes.elementAti(index3);
                }
            }
            if (!nodeHasAnyBefore(node3)) {
                return ce;
            }
            if (nodeHasBefore2(node3)) {
                index3 = nextIndexFromNode(this.nodes.elementAti(nextIndexFromNode(node3)));
                node3 = this.nodes.elementAti(index3);
            }
            if (nodeHasBefore3(node3)) {
                index3 = nextIndexFromNode(this.nodes.elementAti(nextIndexFromNode(node3)));
            }
            return tempCEFromIndexAndStrength(index3, strength);
        }
        while (true) {
            int nextIndex = nextIndexFromNode(node3);
            if (nextIndex != 0) {
                long nextNode = this.nodes.elementAti(nextIndex);
                if (strengthFromNode(nextNode) >= strength) {
                    index3 = nextIndex;
                    node3 = nextNode;
                }
            }
        }
        if (isTailoredNode(node3)) {
            return tempCEFromIndexAndStrength(index3, strength);
        }
        return ce;
    }

    /* access modifiers changed from: package-private */
    @Override // android.icu.impl.coll.CollationRuleParser.Sink
    public void addRelation(int strength, CharSequence prefix, CharSequence str, CharSequence extension) {
        String nfdPrefix;
        if (prefix.length() == 0) {
            nfdPrefix = "";
        } else {
            nfdPrefix = this.nfd.normalize(prefix);
        }
        String nfdString = this.nfd.normalize(str);
        int nfdLength = nfdString.length();
        if (nfdLength >= 2) {
            char c = nfdString.charAt(0);
            if (Normalizer2Impl.Hangul.isJamoL(c) || Normalizer2Impl.Hangul.isJamoV(c)) {
                throw new UnsupportedOperationException("contractions starting with conjoining Jamo L or V not supported");
            }
            char c2 = nfdString.charAt(nfdLength - 1);
            if (Normalizer2Impl.Hangul.isJamoL(c2) || (Normalizer2Impl.Hangul.isJamoV(c2) && Normalizer2Impl.Hangul.isJamoL(nfdString.charAt(nfdLength - 2)))) {
                throw new UnsupportedOperationException("contractions ending with conjoining Jamo L or L+V not supported");
            }
        }
        if (strength != 15) {
            int index = findOrInsertNodeForCEs(strength);
            long ce = this.ces[this.cesLength - 1];
            if (strength == 0 && !isTempCE(ce) && (ce >>> 32) == 0) {
                throw new UnsupportedOperationException("tailoring primary after ignorables not supported");
            } else if (strength == 3 && ce == 0) {
                throw new UnsupportedOperationException("tailoring quaternary after tertiary ignorables not supported");
            } else {
                int index2 = insertTailoredNodeAfter(index, strength);
                int tempStrength = ceStrength(ce);
                if (strength < tempStrength) {
                    tempStrength = strength;
                }
                this.ces[this.cesLength - 1] = tempCEFromIndexAndStrength(index2, tempStrength);
            }
        }
        setCaseBits(nfdString);
        int cesLengthBeforeExtension = this.cesLength;
        if (extension.length() != 0) {
            this.cesLength = this.dataBuilder.getCEs(this.nfd.normalize(extension), this.ces, this.cesLength);
            if (this.cesLength > 31) {
                throw new IllegalArgumentException("extension string adds too many collation elements (more than 31 total)");
            }
        }
        int ce32 = -1;
        if ((!nfdPrefix.contentEquals(prefix) || !nfdString.contentEquals(str)) && !ignorePrefix(prefix) && !ignoreString(str)) {
            ce32 = addIfDifferent(prefix, str, this.ces, this.cesLength, -1);
        }
        addWithClosure(nfdPrefix, nfdString, this.ces, this.cesLength, ce32);
        this.cesLength = cesLengthBeforeExtension;
    }

    private int findOrInsertNodeForCEs(int strength) {
        long ce;
        while (true) {
            int i = this.cesLength;
            if (i == 0) {
                this.ces[0] = 0;
                ce = 0;
                this.cesLength = 1;
                break;
            }
            ce = this.ces[i - 1];
            if (ceStrength(ce) <= strength) {
                break;
            }
            this.cesLength--;
        }
        if (isTempCE(ce)) {
            return indexFromTempCE(ce);
        }
        if (((int) (ce >>> 56)) != 254) {
            return findOrInsertNodeForRootCE(ce, strength);
        }
        throw new UnsupportedOperationException("tailoring relative to an unassigned code point not supported");
    }

    private int findOrInsertNodeForRootCE(long ce, int strength) {
        int index = findOrInsertNodeForPrimary(ce >>> 32);
        if (strength < 1) {
            return index;
        }
        int lower32 = (int) ce;
        int index2 = findOrInsertWeakNode(index, lower32 >>> 16, 1);
        if (strength >= 2) {
            return findOrInsertWeakNode(index2, lower32 & Collation.ONLY_TERTIARY_MASK, 2);
        }
        return index2;
    }

    private static final int binarySearchForRootPrimaryNode(int[] rootPrimaryIndexes2, int length, long[] nodes2, long p) {
        if (length == 0) {
            return -1;
        }
        int start = 0;
        int limit = length;
        while (true) {
            int i = (int) ((((long) start) + ((long) limit)) / 2);
            long nodePrimary = nodes2[rootPrimaryIndexes2[i]] >>> 32;
            if (p == nodePrimary) {
                return i;
            }
            if (p < nodePrimary) {
                if (i == start) {
                    return ~start;
                }
                limit = i;
            } else if (i == start) {
                return ~(start + 1);
            } else {
                start = i;
            }
        }
    }

    private int findOrInsertNodeForPrimary(long p) {
        int rootIndex = binarySearchForRootPrimaryNode(this.rootPrimaryIndexes.getBuffer(), this.rootPrimaryIndexes.size(), this.nodes.getBuffer(), p);
        if (rootIndex >= 0) {
            return this.rootPrimaryIndexes.elementAti(rootIndex);
        }
        int index = this.nodes.size();
        this.nodes.addElement(nodeFromWeight32(p));
        this.rootPrimaryIndexes.insertElementAt(index, ~rootIndex);
        return index;
    }

    private int findOrInsertWeakNode(int index, int weight16, int level) {
        int nextIndex;
        if (weight16 == 1280) {
            return findCommonNode(index, level);
        }
        long node = this.nodes.elementAti(index);
        if (weight16 != 0 && weight16 < 1280) {
            int hasThisLevelBefore = level == 1 ? 64 : 32;
            if ((((long) hasThisLevelBefore) & node) == 0) {
                long commonNode = nodeFromWeight16(Collation.COMMON_WEIGHT16) | nodeFromStrength(level);
                if (level == 1) {
                    commonNode |= 32 & node;
                    node &= -33;
                }
                this.nodes.setElementAt(((long) hasThisLevelBefore) | node, index);
                int nextIndex2 = nextIndexFromNode(node);
                int index2 = insertNodeBetween(index, nextIndex2, nodeFromWeight16(weight16) | nodeFromStrength(level));
                insertNodeBetween(index2, nextIndex2, commonNode);
                return index2;
            }
        }
        while (true) {
            nextIndex = nextIndexFromNode(node);
            if (nextIndex == 0) {
                break;
            }
            node = this.nodes.elementAti(nextIndex);
            int nextStrength = strengthFromNode(node);
            if (nextStrength <= level) {
                if (nextStrength < level) {
                    break;
                } else if (isTailoredNode(node)) {
                    continue;
                } else {
                    int nextWeight16 = weight16FromNode(node);
                    if (nextWeight16 == weight16) {
                        return nextIndex;
                    }
                    if (nextWeight16 > weight16) {
                        break;
                    }
                }
            }
            index = nextIndex;
        }
        return insertNodeBetween(index, nextIndex, nodeFromWeight16(weight16) | nodeFromStrength(level));
    }

    private int insertTailoredNodeAfter(int index, int strength) {
        int nextIndex;
        if (strength >= 1) {
            index = findCommonNode(index, 1);
            if (strength >= 2) {
                index = findCommonNode(index, 2);
            }
        }
        long node = this.nodes.elementAti(index);
        while (true) {
            nextIndex = nextIndexFromNode(node);
            if (nextIndex == 0) {
                break;
            }
            node = this.nodes.elementAti(nextIndex);
            if (strengthFromNode(node) <= strength) {
                break;
            }
            index = nextIndex;
        }
        return insertNodeBetween(index, nextIndex, nodeFromStrength(strength) | 8);
    }

    private int insertNodeBetween(int index, int nextIndex, long node) {
        int newIndex = this.nodes.size();
        this.nodes.addElement(node | nodeFromPreviousIndex(index) | nodeFromNextIndex(nextIndex));
        this.nodes.setElementAt(changeNodeNextIndex(this.nodes.elementAti(index), newIndex), index);
        if (nextIndex != 0) {
            this.nodes.setElementAt(changeNodePreviousIndex(this.nodes.elementAti(nextIndex), newIndex), nextIndex);
        }
        return newIndex;
    }

    private int findCommonNode(int index, int strength) {
        long node = this.nodes.elementAti(index);
        if (strengthFromNode(node) >= strength) {
            return index;
        }
        if (strength != 1 ? !nodeHasBefore3(node) : !nodeHasBefore2(node)) {
            return index;
        }
        long node2 = this.nodes.elementAti(nextIndexFromNode(node));
        while (true) {
            int index2 = nextIndexFromNode(node2);
            node2 = this.nodes.elementAti(index2);
            if (!isTailoredNode(node2) && strengthFromNode(node2) <= strength && weight16FromNode(node2) >= 1280) {
                return index2;
            }
        }
    }

    private void setCaseBits(CharSequence nfdString) {
        int numTailoredPrimaries = 0;
        for (int i = 0; i < this.cesLength; i++) {
            if (ceStrength(this.ces[i]) == 0) {
                numTailoredPrimaries++;
            }
        }
        long cases = 0;
        int i2 = 14;
        if (numTailoredPrimaries > 0) {
            UTF16CollationIterator baseCEs = new UTF16CollationIterator(this.baseData, false, nfdString, 0);
            int baseCEsLength = baseCEs.fetchCEs() - 1;
            int lastCase = 0;
            int numBasePrimaries = 0;
            int i3 = 0;
            while (true) {
                if (i3 >= baseCEsLength) {
                    break;
                }
                long ce = baseCEs.getCE(i3);
                if ((ce >>> 32) != 0) {
                    numBasePrimaries++;
                    int c = (((int) ce) >> i2) & 3;
                    if (numBasePrimaries < numTailoredPrimaries) {
                        cases |= ((long) c) << ((numBasePrimaries - 1) * 2);
                    } else if (numBasePrimaries == numTailoredPrimaries) {
                        lastCase = c;
                    } else if (c != lastCase) {
                        lastCase = 1;
                        break;
                    }
                }
                i3++;
                i2 = 14;
            }
            if (numBasePrimaries >= numTailoredPrimaries) {
                cases |= ((long) lastCase) << ((numTailoredPrimaries - 1) * 2);
            }
        }
        for (int i4 = 0; i4 < this.cesLength; i4++) {
            long ce2 = this.ces[i4] & -49153;
            int strength = ceStrength(ce2);
            if (strength == 0) {
                ce2 |= (3 & cases) << 14;
                cases >>>= 2;
            } else if (strength == 2) {
                ce2 |= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
            }
            this.ces[i4] = ce2;
        }
    }

    /* access modifiers changed from: package-private */
    @Override // android.icu.impl.coll.CollationRuleParser.Sink
    public void suppressContractions(UnicodeSet set) {
        this.dataBuilder.suppressContractions(set);
    }

    /* access modifiers changed from: package-private */
    @Override // android.icu.impl.coll.CollationRuleParser.Sink
    public void optimize(UnicodeSet set) {
        this.optimizeSet.addAll(set);
    }

    private int addWithClosure(CharSequence nfdPrefix, CharSequence nfdString, long[] newCEs, int newCEsLength, int ce32) {
        int ce322 = addOnlyClosure(nfdPrefix, nfdString, newCEs, newCEsLength, addIfDifferent(nfdPrefix, nfdString, newCEs, newCEsLength, ce32));
        addTailComposites(nfdPrefix, nfdString);
        return ce322;
    }

    private int addOnlyClosure(CharSequence nfdPrefix, CharSequence nfdString, long[] newCEs, int newCEsLength, int ce32) {
        if (nfdPrefix.length() == 0) {
            CanonicalIterator stringIter = new CanonicalIterator(nfdString.toString());
            int ce322 = ce32;
            while (true) {
                String str = stringIter.next();
                if (str == null) {
                    return ce322;
                }
                if (!ignoreString(str) && !str.contentEquals(nfdString)) {
                    ce322 = addIfDifferent("", str, newCEs, newCEsLength, ce322);
                }
            }
        } else {
            CanonicalIterator prefixIter = new CanonicalIterator(nfdPrefix.toString());
            CanonicalIterator stringIter2 = new CanonicalIterator(nfdString.toString());
            int ce323 = ce32;
            while (true) {
                String prefix = prefixIter.next();
                if (prefix == null) {
                    return ce323;
                }
                if (!ignorePrefix(prefix)) {
                    boolean samePrefix = prefix.contentEquals(nfdPrefix);
                    int ce324 = ce323;
                    while (true) {
                        String str2 = stringIter2.next();
                        if (str2 == null) {
                            break;
                        } else if (!ignoreString(str2) && (!samePrefix || !str2.contentEquals(nfdString))) {
                            ce324 = addIfDifferent(prefix, str2, newCEs, newCEsLength, ce324);
                        }
                    }
                    stringIter2.reset();
                    ce323 = ce324;
                }
            }
        }
    }

    private void addTailComposites(CharSequence nfdPrefix, CharSequence nfdString) {
        int newCEsLength;
        int indexAfterLastStarter = nfdString.length();
        while (indexAfterLastStarter != 0) {
            int lastStarter = Character.codePointBefore(nfdString, indexAfterLastStarter);
            if (this.nfd.getCombiningClass(lastStarter) != 0) {
                indexAfterLastStarter -= Character.charCount(lastStarter);
            } else if (!Normalizer2Impl.Hangul.isJamoL(lastStarter)) {
                UnicodeSet composites = new UnicodeSet();
                if (this.nfcImpl.getCanonStartSet(lastStarter, composites)) {
                    StringBuilder newNFDString = new StringBuilder();
                    StringBuilder newString = new StringBuilder();
                    long[] newCEs = new long[31];
                    UnicodeSetIterator iter = new UnicodeSetIterator(composites);
                    while (iter.next()) {
                        int composite = iter.codepoint;
                        if (mergeCompositeIntoString(nfdString, indexAfterLastStarter, composite, this.nfd.getDecomposition(composite), newNFDString, newString) && (newCEsLength = this.dataBuilder.getCEs(nfdPrefix, newNFDString, newCEs, 0)) <= 31) {
                            int ce32 = addIfDifferent(nfdPrefix, newString, newCEs, newCEsLength, -1);
                            if (ce32 != -1) {
                                addOnlyClosure(nfdPrefix, newNFDString, newCEs, newCEsLength, ce32);
                            }
                        }
                    }
                    return;
                }
                return;
            } else {
                return;
            }
        }
    }

    private boolean mergeCompositeIntoString(CharSequence nfdString, int indexAfterLastStarter, int composite, CharSequence decomp, StringBuilder newNFDString, StringBuilder newString) {
        boolean z = true;
        int lastStarterLength = Character.offsetByCodePoints(decomp, 0, 1);
        if (lastStarterLength == decomp.length() || equalSubSequences(nfdString, indexAfterLastStarter, decomp, lastStarterLength)) {
            return false;
        }
        newNFDString.setLength(0);
        newNFDString.append(nfdString, 0, indexAfterLastStarter);
        newString.setLength(0);
        newString.append(nfdString, 0, indexAfterLastStarter - lastStarterLength);
        newString.appendCodePoint(composite);
        int sourceIndex = indexAfterLastStarter;
        int decompIndex = lastStarterLength;
        int sourceChar = -1;
        int sourceCC = 0;
        int decompCC = 0;
        while (true) {
            if (sourceChar < 0) {
                if (sourceIndex >= nfdString.length()) {
                    break;
                }
                sourceChar = Character.codePointAt(nfdString, sourceIndex);
                sourceCC = this.nfd.getCombiningClass(sourceChar);
            }
            if (decompIndex >= decomp.length()) {
                break;
            }
            int decompChar = Character.codePointAt(decomp, decompIndex);
            decompCC = this.nfd.getCombiningClass(decompChar);
            if (decompCC == 0 || sourceCC < decompCC) {
                return false;
            }
            if (decompCC < sourceCC) {
                newNFDString.appendCodePoint(decompChar);
                decompIndex += Character.charCount(decompChar);
            } else if (decompChar != sourceChar) {
                return false;
            } else {
                newNFDString.appendCodePoint(decompChar);
                decompIndex += Character.charCount(decompChar);
                sourceIndex += Character.charCount(decompChar);
                sourceChar = -1;
            }
            z = true;
        }
        if (sourceChar >= 0) {
            if (sourceCC < decompCC) {
                return false;
            }
            newNFDString.append(nfdString, sourceIndex, nfdString.length());
            newString.append(nfdString, sourceIndex, nfdString.length());
        } else if (decompIndex < decomp.length()) {
            newNFDString.append(decomp, decompIndex, decomp.length());
        }
        return z;
    }

    private boolean equalSubSequences(CharSequence left, int leftStart, CharSequence right, int rightStart) {
        int leftLength = left.length();
        if (leftLength - leftStart != right.length() - rightStart) {
            return false;
        }
        while (leftStart < leftLength) {
            int leftStart2 = leftStart + 1;
            int rightStart2 = rightStart + 1;
            if (left.charAt(leftStart) != right.charAt(rightStart)) {
                return false;
            }
            leftStart = leftStart2;
            rightStart = rightStart2;
        }
        return true;
    }

    private boolean ignorePrefix(CharSequence s) {
        return !isFCD(s);
    }

    private boolean ignoreString(CharSequence s) {
        return !isFCD(s) || Normalizer2Impl.Hangul.isHangul(s.charAt(0));
    }

    private boolean isFCD(CharSequence s) {
        return this.fcd.isNormalized(s);
    }

    private void closeOverComposites() {
        UnicodeSetIterator iter = new UnicodeSetIterator(COMPOSITES);
        while (iter.next()) {
            this.cesLength = this.dataBuilder.getCEs(this.nfd.getDecomposition(iter.codepoint), this.ces, 0);
            if (this.cesLength <= 31) {
                addIfDifferent("", iter.getString(), this.ces, this.cesLength, -1);
            }
        }
    }

    private int addIfDifferent(CharSequence prefix, CharSequence str, long[] newCEs, int newCEsLength, int ce32) {
        long[] oldCEs = new long[31];
        if (!sameCEs(newCEs, newCEsLength, oldCEs, this.dataBuilder.getCEs(prefix, str, oldCEs, 0))) {
            if (ce32 == -1) {
                ce32 = this.dataBuilder.encodeCEs(newCEs, newCEsLength);
            }
            this.dataBuilder.addCE32(prefix, str, ce32);
        }
        return ce32;
    }

    private static boolean sameCEs(long[] ces1, int ces1Length, long[] ces2, int ces2Length) {
        if (ces1Length != ces2Length) {
            return false;
        }
        for (int i = 0; i < ces1Length; i++) {
            if (ces1[i] != ces2[i]) {
                return false;
            }
        }
        return true;
    }

    private static final int alignWeightRight(int w) {
        if (w != 0) {
            while ((w & 255) == 0) {
                w >>>= 8;
            }
        }
        return w;
    }

    private void makeTailoredCEs() {
        long[] nodesArray;
        int rpi;
        CollationWeights tertiaries;
        int nextIndex;
        int q;
        int nextIndex2;
        int t;
        int i;
        int i2;
        long p;
        int strength;
        int strength2;
        int sLimit;
        int q2;
        long p2;
        int rpi2;
        int tLimit;
        int t2;
        int tLimit2;
        CollationWeights primaries = new CollationWeights();
        CollationWeights secondaries = new CollationWeights();
        CollationWeights tertiaries2 = new CollationWeights();
        long[] nodesArray2 = this.nodes.getBuffer();
        int rpi3 = 0;
        while (rpi3 < this.rootPrimaryIndexes.size()) {
            long node = nodesArray2[this.rootPrimaryIndexes.elementAti(rpi3)];
            long p3 = weight32FromNode(node);
            int s = p3 == 0 ? 0 : Collation.COMMON_WEIGHT16;
            int pIndex = p3 == 0 ? 0 : this.rootElements.findPrimary(p3);
            int nextIndex3 = nextIndexFromNode(node);
            int t3 = s;
            int pIndex2 = 0;
            boolean pIsTailored = false;
            boolean sIsTailored = false;
            boolean tIsTailored = false;
            while (nextIndex3 != 0) {
                long node2 = nodesArray2[nextIndex3];
                int nextIndex4 = nextIndexFromNode(node2);
                int strength3 = strengthFromNode(node2);
                if (strength3 != 3) {
                    if (strength3 != 2) {
                        if (strength3 != 1) {
                            tertiaries = tertiaries2;
                            i2 = Collation.COMMON_WEIGHT16;
                            if (!pIsTailored) {
                                int pCount = countTailoredNodes(nodesArray2, nextIndex4, 0) + 1;
                                boolean isCompressible = this.baseData.isCompressiblePrimary(p3);
                                long pLimit = this.rootElements.getPrimaryAfter(p3, pIndex, isCompressible);
                                primaries.initForPrimary(isCompressible);
                                nextIndex = nextIndex4;
                                q = pIndex;
                                i = 0;
                                rpi = rpi3;
                                nodesArray = nodesArray2;
                                if (primaries.allocWeights(p3, pLimit, pCount)) {
                                    pIsTailored = true;
                                } else {
                                    throw new UnsupportedOperationException("primary tailoring gap too small");
                                }
                            } else {
                                nextIndex = nextIndex4;
                                q = pIndex;
                                nodesArray = nodesArray2;
                                rpi = rpi3;
                                i = 0;
                            }
                            s = 1280;
                            sIsTailored = false;
                            p = primaries.nextWeight();
                        } else if (isTailoredNode(node2)) {
                            if (!sIsTailored) {
                                int sCount = countTailoredNodes(nodesArray2, nextIndex4, 1) + 1;
                                if (s == 0) {
                                    s = this.rootElements.getSecondaryBoundary() + InputDeviceCompat.SOURCE_ANY;
                                    sLimit = (int) (this.rootElements.getFirstSecondaryCE() >> 16);
                                } else if (!pIsTailored) {
                                    sLimit = this.rootElements.getSecondaryAfter(pIndex, s);
                                } else if (s == 256) {
                                    sLimit = 1280;
                                } else {
                                    sLimit = this.rootElements.getSecondaryBoundary();
                                }
                                if (s == 1280) {
                                    s = this.rootElements.getLastCommonSecondary();
                                }
                                secondaries.initForSecondary();
                                tertiaries = tertiaries2;
                                p = p3;
                                strength = strength3;
                                strength2 = pIndex;
                                i2 = 1280;
                                if (secondaries.allocWeights((long) s, (long) sLimit, sCount)) {
                                    sIsTailored = true;
                                } else {
                                    throw new UnsupportedOperationException("secondary tailoring gap too small");
                                }
                            } else {
                                strength = strength3;
                                strength2 = pIndex;
                                tertiaries = tertiaries2;
                                p = p3;
                                i2 = Collation.COMMON_WEIGHT16;
                            }
                            s = (int) secondaries.nextWeight();
                            nextIndex = nextIndex4;
                            q = strength2;
                            nodesArray = nodesArray2;
                            rpi = rpi3;
                            i = 0;
                        } else {
                            tertiaries = tertiaries2;
                            p = p3;
                            i2 = Collation.COMMON_WEIGHT16;
                            s = weight16FromNode(node2);
                            nextIndex = nextIndex4;
                            q = pIndex;
                            sIsTailored = false;
                            nodesArray = nodesArray2;
                            rpi = rpi3;
                            i = 0;
                        }
                        t = s == 0 ? i : i2;
                        tIsTailored = false;
                        p3 = p;
                    } else if (isTailoredNode(node2)) {
                        if (!tIsTailored) {
                            int tCount = countTailoredNodes(nodesArray2, nextIndex4, 2) + 1;
                            if (t3 == 0) {
                                rpi2 = rpi3;
                                t2 = this.rootElements.getTertiaryBoundary() - 256;
                                p2 = p3;
                                tLimit2 = ((int) this.rootElements.getFirstTertiaryCE()) & Collation.ONLY_TERTIARY_MASK;
                            } else {
                                rpi2 = rpi3;
                                p2 = p3;
                                if (!pIsTailored && !sIsTailored) {
                                    tLimit2 = this.rootElements.getTertiaryAfter(pIndex, s, t3);
                                    t2 = t3;
                                } else if (t3 == 256) {
                                    tLimit2 = Collation.COMMON_WEIGHT16;
                                    t2 = t3;
                                } else {
                                    tLimit2 = this.rootElements.getTertiaryBoundary();
                                    t2 = t3;
                                }
                            }
                            tertiaries2.initForTertiary();
                            long j = (long) t2;
                            q2 = pIndex2;
                            long j2 = (long) tLimit2;
                            tLimit = 0;
                            if (tertiaries2.allocWeights(j, j2, tCount)) {
                                tIsTailored = true;
                            } else {
                                throw new UnsupportedOperationException("tertiary tailoring gap too small");
                            }
                        } else {
                            rpi2 = rpi3;
                            p2 = p3;
                            q2 = pIndex2;
                            tLimit = 0;
                        }
                        t = (int) tertiaries2.nextWeight();
                        nextIndex = nextIndex4;
                        nodesArray = nodesArray2;
                        q = pIndex;
                        tertiaries = tertiaries2;
                        rpi = rpi2;
                        p3 = p2;
                    } else {
                        t = weight16FromNode(node2);
                        tIsTailored = false;
                        nextIndex = nextIndex4;
                        nodesArray = nodesArray2;
                        q = pIndex;
                        tertiaries = tertiaries2;
                        rpi = rpi3;
                    }
                    nextIndex2 = 0;
                    t3 = t;
                } else if (pIndex2 != 3) {
                    nextIndex = nextIndex4;
                    rpi = rpi3;
                    nodesArray = nodesArray2;
                    nextIndex2 = pIndex2 + 1;
                    tertiaries = tertiaries2;
                    q = pIndex;
                } else {
                    throw new UnsupportedOperationException("quaternary tailoring gap too small");
                }
                if (isTailoredNode(node2)) {
                    nodesArray[nextIndex3] = Collation.makeCE(p3, s, t3, nextIndex2);
                }
                pIndex = q;
                nextIndex3 = nextIndex;
                tertiaries2 = tertiaries;
                rpi3 = rpi;
                nodesArray2 = nodesArray;
                pIndex2 = nextIndex2;
            }
            rpi3++;
            tertiaries2 = tertiaries2;
        }
    }

    private static int countTailoredNodes(long[] nodesArray, int i, int strength) {
        int count = 0;
        while (i != 0) {
            long node = nodesArray[i];
            if (strengthFromNode(node) < strength) {
                break;
            }
            if (strengthFromNode(node) == strength) {
                if (!isTailoredNode(node)) {
                    break;
                }
                count++;
            }
            i = nextIndexFromNode(node);
        }
        return count;
    }

    /* access modifiers changed from: private */
    public static final class CEFinalizer implements CollationDataBuilder.CEModifier {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private long[] finalCEs;

        CEFinalizer(long[] ces) {
            this.finalCEs = ces;
        }

        @Override // android.icu.impl.coll.CollationDataBuilder.CEModifier
        public long modifyCE32(int ce32) {
            if (CollationBuilder.isTempCE32(ce32)) {
                return this.finalCEs[CollationBuilder.indexFromTempCE32(ce32)] | ((long) ((ce32 & 192) << 8));
            }
            return Collation.NO_CE;
        }

        @Override // android.icu.impl.coll.CollationDataBuilder.CEModifier
        public long modifyCE(long ce) {
            if (CollationBuilder.isTempCE(ce)) {
                return this.finalCEs[CollationBuilder.indexFromTempCE(ce)] | (49152 & ce);
            }
            return Collation.NO_CE;
        }
    }

    private void finalizeCEs() {
        CollationDataBuilder newBuilder = new CollationDataBuilder();
        newBuilder.initForTailoring(this.baseData);
        newBuilder.copyFrom(this.dataBuilder, new CEFinalizer(this.nodes.getBuffer()));
        this.dataBuilder = newBuilder;
    }

    private static long tempCEFromIndexAndStrength(int index, int strength) {
        return (((long) (1040384 & index)) << 43) + 4629700417037541376L + (((long) (index & 8128)) << 42) + ((long) ((index & 63) << 24)) + ((long) (strength << 8));
    }

    /* access modifiers changed from: private */
    public static int indexFromTempCE(long tempCE) {
        long tempCE2 = tempCE - 4629700417037541376L;
        return (((int) (tempCE2 >> 43)) & 1040384) | (((int) (tempCE2 >> 42)) & 8128) | (((int) (tempCE2 >> 24)) & 63);
    }

    private static int strengthFromTempCE(long tempCE) {
        return (((int) tempCE) >> 8) & 3;
    }

    /* access modifiers changed from: private */
    public static boolean isTempCE(long ce) {
        int sec = ((int) ce) >>> 24;
        return 6 <= sec && sec <= 69;
    }

    /* access modifiers changed from: private */
    public static int indexFromTempCE32(int tempCE32) {
        int tempCE322 = tempCE32 - 1077937696;
        return ((tempCE322 >> 11) & 1040384) | ((tempCE322 >> 10) & 8128) | ((tempCE322 >> 8) & 63);
    }

    /* access modifiers changed from: private */
    public static boolean isTempCE32(int ce32) {
        return (ce32 & 255) >= 2 && 6 <= ((ce32 >> 8) & 255) && ((ce32 >> 8) & 255) <= 69;
    }

    private static int ceStrength(long ce) {
        if (isTempCE(ce)) {
            return strengthFromTempCE(ce);
        }
        if ((-72057594037927936L & ce) != 0) {
            return 0;
        }
        if ((((int) ce) & ViewCompat.MEASURED_STATE_MASK) != 0) {
            return 1;
        }
        if (ce != 0) {
            return 2;
        }
        return 15;
    }

    private static long nodeFromWeight32(long weight32) {
        return weight32 << 32;
    }

    private static long nodeFromWeight16(int weight16) {
        return ((long) weight16) << 48;
    }

    private static long nodeFromPreviousIndex(int previous) {
        return ((long) previous) << 28;
    }

    private static long nodeFromNextIndex(int next) {
        return (long) (next << 8);
    }

    private static long nodeFromStrength(int strength) {
        return (long) strength;
    }

    private static long weight32FromNode(long node) {
        return node >>> 32;
    }

    private static int weight16FromNode(long node) {
        return ((int) (node >> 48)) & 65535;
    }

    private static int previousIndexFromNode(long node) {
        return ((int) (node >> 28)) & MAX_INDEX;
    }

    private static int nextIndexFromNode(long node) {
        return (((int) node) >> 8) & MAX_INDEX;
    }

    private static int strengthFromNode(long node) {
        return ((int) node) & 3;
    }

    private static boolean nodeHasBefore2(long node) {
        return (64 & node) != 0;
    }

    private static boolean nodeHasBefore3(long node) {
        return (32 & node) != 0;
    }

    private static boolean nodeHasAnyBefore(long node) {
        return (96 & node) != 0;
    }

    private static boolean isTailoredNode(long node) {
        return (8 & node) != 0;
    }

    private static long changeNodePreviousIndex(long node, int previous) {
        return (-281474708275201L & node) | nodeFromPreviousIndex(previous);
    }

    private static long changeNodeNextIndex(long node, int next) {
        return (-268435201 & node) | nodeFromNextIndex(next);
    }
}
