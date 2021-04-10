package android.icu.impl.coll;

import android.icu.util.CharsTrie;
import android.support.v4.media.session.PlaybackStateCompat;
import java.lang.reflect.Array;

/* access modifiers changed from: package-private */
public final class CollationFastLatinBuilder {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final long CONTRACTION_FLAG = 2147483648L;
    private static final int NUM_SPECIAL_GROUPS = 4;
    private long ce0 = 0;
    private long ce1 = 0;
    private long[][] charCEs = ((long[][]) Array.newInstance(long.class, 448, 2));
    private UVector64 contractionCEs = new UVector64();
    private long firstDigitPrimary = 0;
    private long firstLatinPrimary = 0;
    private long firstShortPrimary = 0;
    private int headerLength = 0;
    private long lastLatinPrimary = 0;
    long[] lastSpecialPrimaries = new long[4];
    private char[] miniCEs = null;
    private StringBuilder result = new StringBuilder();
    private boolean shortPrimaryOverflow = false;
    private UVector64 uniqueCEs = new UVector64();

    private static final int compareInt64AsUnsigned(long a, long b) {
        long a2 = a - Long.MIN_VALUE;
        long b2 = b - Long.MIN_VALUE;
        if (a2 < b2) {
            return -1;
        }
        if (a2 > b2) {
            return 1;
        }
        return 0;
    }

    private static final int binarySearch(long[] list, int limit, long ce) {
        if (limit == 0) {
            return -1;
        }
        int start = 0;
        while (true) {
            int i = (int) ((((long) start) + ((long) limit)) / 2);
            int cmp = compareInt64AsUnsigned(ce, list[i]);
            if (cmp == 0) {
                return i;
            }
            if (cmp < 0) {
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

    CollationFastLatinBuilder() {
    }

    /* access modifiers changed from: package-private */
    public boolean forData(CollationData data) {
        if (this.result.length() != 0) {
            throw new IllegalStateException("attempt to reuse a CollationFastLatinBuilder");
        } else if (!loadGroups(data)) {
            return false;
        } else {
            this.firstShortPrimary = this.firstDigitPrimary;
            getCEs(data);
            encodeUniqueCEs();
            if (this.shortPrimaryOverflow) {
                this.firstShortPrimary = this.firstLatinPrimary;
                resetCEs();
                getCEs(data);
                encodeUniqueCEs();
            }
            boolean ok = !this.shortPrimaryOverflow;
            if (ok) {
                encodeCharCEs();
                encodeContractions();
            }
            this.contractionCEs.removeAllElements();
            this.uniqueCEs.removeAllElements();
            return ok;
        }
    }

    /* access modifiers changed from: package-private */
    public char[] getHeader() {
        int i = this.headerLength;
        char[] resultArray = new char[i];
        this.result.getChars(0, i, resultArray, 0);
        return resultArray;
    }

    /* access modifiers changed from: package-private */
    public char[] getTable() {
        int length = this.result.length();
        int i = this.headerLength;
        char[] resultArray = new char[(length - i)];
        StringBuilder sb = this.result;
        sb.getChars(i, sb.length(), resultArray, 0);
        return resultArray;
    }

    private boolean loadGroups(CollationData data) {
        this.headerLength = 5;
        this.result.append((char) (this.headerLength | 512));
        for (int i = 0; i < 4; i++) {
            this.lastSpecialPrimaries[i] = data.getLastPrimaryForGroup(i + 4096);
            if (this.lastSpecialPrimaries[i] == 0) {
                return false;
            }
            this.result.append(0);
        }
        this.firstDigitPrimary = data.getFirstPrimaryForGroup(4100);
        this.firstLatinPrimary = data.getFirstPrimaryForGroup(25);
        this.lastLatinPrimary = data.getLastPrimaryForGroup(25);
        if (this.firstDigitPrimary == 0 || this.firstLatinPrimary == 0) {
            return false;
        }
        return true;
    }

    private boolean inSameGroup(long p, long q) {
        long j = this.firstShortPrimary;
        if (p >= j) {
            return q >= j;
        }
        if (q >= j) {
            return false;
        }
        long lastVariablePrimary = this.lastSpecialPrimaries[3];
        if (p > lastVariablePrimary) {
            return q > lastVariablePrimary;
        }
        if (q > lastVariablePrimary) {
            return false;
        }
        int i = 0;
        while (true) {
            long lastPrimary = this.lastSpecialPrimaries[i];
            if (p <= lastPrimary) {
                return q <= lastPrimary;
            }
            if (q <= lastPrimary) {
                return false;
            }
            i++;
        }
    }

    private void resetCEs() {
        this.contractionCEs.removeAllElements();
        this.uniqueCEs.removeAllElements();
        this.shortPrimaryOverflow = false;
        this.result.setLength(this.headerLength);
    }

    private void getCEs(CollationData data) {
        char c;
        int ce32;
        CollationData d;
        char c2 = 0;
        int i = 0;
        while (true) {
            if (c2 == 384) {
                c = 8192;
            } else if (c2 == 8256) {
                this.contractionCEs.addElement(511);
                return;
            } else {
                c = c2;
            }
            int ce322 = data.getCE32(c);
            if (ce322 == 192) {
                CollationData d2 = data.base;
                ce32 = d2.getCE32(c);
                d = d2;
            } else {
                ce32 = ce322;
                d = data;
            }
            if (getCEsFromCE32(d, c, ce32)) {
                long[][] jArr = this.charCEs;
                long[] jArr2 = jArr[i];
                long j = this.ce0;
                jArr2[0] = j;
                jArr[i][1] = this.ce1;
                addUniqueCE(j);
                addUniqueCE(this.ce1);
            } else {
                long[][] jArr3 = this.charCEs;
                long[] jArr4 = jArr3[i];
                this.ce0 = Collation.NO_CE;
                jArr4[0] = 4311744768L;
                long[] jArr5 = jArr3[i];
                this.ce1 = 0;
                jArr5[1] = 0;
            }
            if (c == 0 && !isContractionCharCE(this.ce0)) {
                addContractionEntry(511, this.ce0, this.ce1);
                long[][] jArr6 = this.charCEs;
                jArr6[0][0] = 6442450944L;
                jArr6[0][1] = 0;
            }
            i++;
            c2 = (char) (c + 1);
        }
    }

    private boolean getCEsFromCE32(CollationData data, int c, int ce32) {
        int ce322 = data.getFinalCE32(ce32);
        this.ce1 = 0;
        if (Collation.isSimpleOrLongCE32(ce322)) {
            this.ce0 = Collation.ceFromCE32(ce322);
        } else {
            int tagFromCE32 = Collation.tagFromCE32(ce322);
            if (tagFromCE32 == 4) {
                this.ce0 = Collation.latinCE0FromCE32(ce322);
                this.ce1 = Collation.latinCE1FromCE32(ce322);
            } else if (tagFromCE32 == 5) {
                int index = Collation.indexFromCE32(ce322);
                int length = Collation.lengthFromCE32(ce322);
                if (length > 2) {
                    return false;
                }
                this.ce0 = Collation.ceFromCE32(data.ce32s[index]);
                if (length == 2) {
                    this.ce1 = Collation.ceFromCE32(data.ce32s[index + 1]);
                }
            } else if (tagFromCE32 == 6) {
                int index2 = Collation.indexFromCE32(ce322);
                int length2 = Collation.lengthFromCE32(ce322);
                if (length2 > 2) {
                    return false;
                }
                this.ce0 = data.ces[index2];
                if (length2 == 2) {
                    this.ce1 = data.ces[index2 + 1];
                }
            } else if (tagFromCE32 == 9) {
                return getCEsFromContractionCE32(data, ce322);
            } else {
                if (tagFromCE32 != 14) {
                    return false;
                }
                this.ce0 = data.getCEFromOffsetCE32(c, ce322);
            }
        }
        long j = this.ce0;
        if (j != 0) {
            long p0 = j >>> 32;
            if (p0 == 0 || p0 > this.lastLatinPrimary) {
                return false;
            }
            int lower32_0 = (int) j;
            if ((p0 < this.firstShortPrimary && (lower32_0 & -16384) != 83886080) || (lower32_0 & Collation.ONLY_TERTIARY_MASK) < 1280) {
                return false;
            }
            long j2 = this.ce1;
            if (j2 != 0) {
                long p1 = j2 >>> 32;
                if (p1 != 0 ? !inSameGroup(p0, p1) : p0 < this.firstShortPrimary) {
                    return false;
                }
                int lower32_1 = (int) this.ce1;
                if ((lower32_1 >>> 16) == 0) {
                    return false;
                }
                if (p1 != 0) {
                    if (p1 < this.firstShortPrimary && (lower32_1 & -16384) != 83886080) {
                        return false;
                    }
                }
                if ((lower32_0 & Collation.ONLY_TERTIARY_MASK) < 1280) {
                    return false;
                }
            }
            if (((this.ce0 | this.ce1) & 192) != 0) {
                return false;
            }
            return true;
        } else if (this.ce1 == 0) {
            return true;
        } else {
            return false;
        }
    }

    private boolean getCEsFromContractionCE32(CollationData data, int ce32) {
        int i;
        boolean z;
        int trieIndex = Collation.indexFromCE32(ce32);
        int ce322 = data.getCE32FromContexts(trieIndex);
        int contractionIndex = this.contractionCEs.size();
        if (getCEsFromCE32(data, -1, ce322)) {
            addContractionEntry(511, this.ce0, this.ce1);
        } else {
            addContractionEntry(511, Collation.NO_CE, 0);
        }
        int i2 = 0;
        CharsTrie.Iterator suffixes = CharsTrie.iterator(data.contexts, trieIndex + 2, 0);
        int prevX = -1;
        boolean addContraction = false;
        while (suffixes.hasNext()) {
            CharsTrie.Entry entry = suffixes.next();
            CharSequence suffix = entry.chars;
            int x = CollationFastLatin.getCharIndex(suffix.charAt(i2));
            if (x >= 0) {
                if (x != prevX) {
                    if (addContraction) {
                        i = 1;
                        addContractionEntry(prevX, this.ce0, this.ce1);
                    } else {
                        i = 1;
                    }
                    int ce323 = entry.value;
                    if (suffix.length() == i) {
                        if (getCEsFromCE32(data, -1, ce323)) {
                            z = true;
                            addContraction = z;
                            prevX = x;
                            i2 = 0;
                        }
                    }
                    addContractionEntry(x, Collation.NO_CE, 0);
                    z = false;
                    addContraction = z;
                    prevX = x;
                    i2 = 0;
                } else if (addContraction) {
                    addContractionEntry(x, Collation.NO_CE, 0);
                    addContraction = false;
                    i2 = 0;
                }
            }
            i2 = 0;
        }
        if (addContraction) {
            addContractionEntry(prevX, this.ce0, this.ce1);
        }
        this.ce0 = 6442450944L | ((long) contractionIndex);
        this.ce1 = 0;
        return true;
    }

    private void addContractionEntry(int x, long cce0, long cce1) {
        this.contractionCEs.addElement((long) x);
        this.contractionCEs.addElement(cce0);
        this.contractionCEs.addElement(cce1);
        addUniqueCE(cce0);
        addUniqueCE(cce1);
    }

    private void addUniqueCE(long ce) {
        long ce2;
        int i;
        if (ce != 0 && (ce >>> 32) != 1 && (i = binarySearch(this.uniqueCEs.getBuffer(), this.uniqueCEs.size(), (ce2 = ce & -49153))) < 0) {
            this.uniqueCEs.insertElementAt(ce2, ~i);
        }
    }

    private int getMiniCE(long ce) {
        return this.miniCEs[binarySearch(this.uniqueCEs.getBuffer(), this.uniqueCEs.size(), ce & -49153)];
    }

    private void encodeUniqueCEs() {
        long lastGroupPrimary;
        int group;
        int group2;
        this.miniCEs = new char[this.uniqueCEs.size()];
        int group3 = 0;
        long lastGroupPrimary2 = this.lastSpecialPrimaries[0];
        long prevPrimary = 0;
        int prevSecondary = 0;
        int pri = 0;
        int sec = 0;
        int ter = 0;
        for (int i = 0; i < this.uniqueCEs.size(); i++) {
            long ce = this.uniqueCEs.elementAti(i);
            long p = ce >>> 32;
            int group4 = group3;
            if (p != prevPrimary) {
                while (true) {
                    if (p <= lastGroupPrimary2) {
                        group2 = group4;
                        break;
                    }
                    this.result.setCharAt(group4 + 1, (char) pri);
                    group2 = group4 + 1;
                    if (group2 >= 4) {
                        lastGroupPrimary2 = 4294967295L;
                        break;
                    } else {
                        lastGroupPrimary2 = this.lastSpecialPrimaries[group2];
                        group4 = group2;
                    }
                }
                lastGroupPrimary = lastGroupPrimary2;
                group = group2;
                if (p < this.firstShortPrimary) {
                    if (pri == 0) {
                        pri = 3072;
                    } else if (pri < 4088) {
                        pri += 8;
                    } else {
                        this.miniCEs[i] = 1;
                        group3 = group;
                        lastGroupPrimary2 = lastGroupPrimary;
                    }
                } else if (pri < 4096) {
                    pri = 4096;
                } else if (pri < 63488) {
                    pri += 1024;
                } else {
                    this.shortPrimaryOverflow = true;
                    this.miniCEs[i] = 1;
                    group3 = group;
                    lastGroupPrimary2 = lastGroupPrimary;
                }
                prevPrimary = p;
                prevSecondary = Collation.COMMON_WEIGHT16;
                sec = 160;
                ter = 0;
            } else {
                group = group4;
                lastGroupPrimary = lastGroupPrimary2;
            }
            int lower32 = (int) ce;
            int s = lower32 >>> 16;
            if (s != prevSecondary) {
                if (pri == 0) {
                    if (sec == 0) {
                        sec = CollationFastLatin.LATIN_LIMIT;
                    } else if (sec < 992) {
                        sec += 32;
                    } else {
                        this.miniCEs[i] = 1;
                        lastGroupPrimary2 = lastGroupPrimary;
                        group3 = group;
                    }
                } else if (s < 1280) {
                    if (sec == 160) {
                        sec = 0;
                    } else if (sec < 128) {
                        sec += 32;
                    } else {
                        this.miniCEs[i] = 1;
                        lastGroupPrimary2 = lastGroupPrimary;
                        group3 = group;
                    }
                } else if (s == 1280) {
                    sec = 160;
                } else if (sec < 192) {
                    sec = 192;
                } else if (sec < 352) {
                    sec += 32;
                } else {
                    this.miniCEs[i] = 1;
                    lastGroupPrimary2 = lastGroupPrimary;
                    group3 = group;
                }
                prevSecondary = s;
                ter = 0;
            }
            if ((lower32 & Collation.ONLY_TERTIARY_MASK) > 1280) {
                if (ter < 7) {
                    ter++;
                } else {
                    this.miniCEs[i] = 1;
                    lastGroupPrimary2 = lastGroupPrimary;
                    group3 = group;
                }
            }
            if (3072 > pri || pri > 4088) {
                this.miniCEs[i] = (char) (pri | sec | ter);
            } else {
                this.miniCEs[i] = (char) (pri | ter);
            }
            lastGroupPrimary2 = lastGroupPrimary;
            group3 = group;
        }
    }

    private void encodeCharCEs() {
        int miniCEsStart = this.result.length();
        for (int i = 0; i < 448; i++) {
            this.result.append(0);
        }
        int indexBase = this.result.length();
        for (int i2 = 0; i2 < 448; i2++) {
            long ce = this.charCEs[i2][0];
            if (!isContractionCharCE(ce)) {
                int miniCE = encodeTwoCEs(ce, this.charCEs[i2][1]);
                if ((miniCE >>> 16) > 0) {
                    int expansionIndex = this.result.length() - indexBase;
                    if (expansionIndex > 1023) {
                        miniCE = 1;
                    } else {
                        StringBuilder sb = this.result;
                        sb.append((char) (miniCE >> 16));
                        sb.append((char) miniCE);
                        miniCE = expansionIndex | 2048;
                    }
                }
                this.result.setCharAt(miniCEsStart + i2, (char) miniCE);
            }
        }
    }

    private void encodeContractions() {
        long ce;
        int i;
        int i2 = 448;
        int indexBase = this.headerLength + 448;
        int firstContractionIndex = this.result.length();
        int i3 = 0;
        while (i3 < i2) {
            long ce2 = this.charCEs[i3][0];
            if (isContractionCharCE(ce2)) {
                int contractionIndex = this.result.length() - indexBase;
                if (contractionIndex > 1023) {
                    this.result.setCharAt(this.headerLength + i3, 1);
                } else {
                    boolean firstTriple = true;
                    int index = ((int) ce2) & Integer.MAX_VALUE;
                    while (true) {
                        long x = this.contractionCEs.elementAti(index);
                        if (x == 511 && !firstTriple) {
                            break;
                        }
                        int miniCE = encodeTwoCEs(this.contractionCEs.elementAti(index + 1), this.contractionCEs.elementAti(index + 2));
                        if (miniCE == 1) {
                            i = i3;
                            ce = ce2;
                            this.result.append((char) ((int) (x | 512)));
                        } else {
                            i = i3;
                            ce = ce2;
                            if ((miniCE >>> 16) == 0) {
                                this.result.append((char) ((int) (PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID | x)));
                                this.result.append((char) miniCE);
                            } else {
                                this.result.append((char) ((int) (1536 | x)));
                                StringBuilder sb = this.result;
                                sb.append((char) (miniCE >> 16));
                                sb.append((char) miniCE);
                            }
                        }
                        firstTriple = false;
                        index += 3;
                        i3 = i;
                        ce2 = ce;
                        i2 = 448;
                    }
                    this.result.setCharAt(this.headerLength + i3, (char) (contractionIndex | 1024));
                }
            }
            i3++;
        }
        if (this.result.length() > firstContractionIndex) {
            this.result.append((char) 511);
        }
    }

    private int encodeTwoCEs(long first, long second) {
        if (first == 0) {
            return 0;
        }
        if (first == Collation.NO_CE) {
            return 1;
        }
        int miniCE = getMiniCE(first);
        if (miniCE == 1) {
            return miniCE;
        }
        if (miniCE >= 4096) {
            miniCE |= ((((int) first) & Collation.CASE_MASK) >> 11) + 8;
        }
        if (second == 0) {
            return miniCE;
        }
        int miniCE1 = getMiniCE(second);
        if (miniCE1 == 1) {
            return miniCE1;
        }
        int case1 = ((int) second) & Collation.CASE_MASK;
        if (miniCE >= 4096 && (miniCE & 992) == 160) {
            int sec1 = miniCE1 & 992;
            int ter1 = miniCE1 & 7;
            if (sec1 >= 384 && case1 == 0 && ter1 == 0) {
                return (miniCE & -993) | sec1;
            }
        }
        if (miniCE1 <= 992 || 4096 <= miniCE1) {
            miniCE1 |= (case1 >> 11) + 8;
        }
        return (miniCE << 16) | miniCE1;
    }

    private static boolean isContractionCharCE(long ce) {
        return (ce >>> 32) == 1 && ce != Collation.NO_CE;
    }
}
