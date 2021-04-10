package android.icu.impl;

import android.icu.impl.ICUBinary;
import android.icu.impl.Normalizer2Impl;
import android.icu.impl.Trie2;
import android.icu.lang.UCharacter;
import android.icu.lang.UScript;
import android.icu.text.Normalizer2;
import android.icu.text.UTF16;
import android.icu.text.UnicodeSet;
import android.icu.util.CodePointMap;
import android.icu.util.CodePointTrie;
import android.icu.util.ICUException;
import android.icu.util.VersionInfo;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.MissingResourceException;

public final class UCharacterProperty {
    private static final int GC_CC_MASK = getMask(15);
    private static final int GC_CN_MASK = getMask(0);
    private static final int GC_CS_MASK = getMask(18);
    private static final int GC_ZL_MASK = getMask(13);
    private static final int GC_ZP_MASK = getMask(14);
    private static final int GC_ZS_MASK = getMask(12);
    private static final int GC_Z_MASK = ((GC_ZS_MASK | GC_ZL_MASK) | GC_ZP_MASK);
    public static final UCharacterProperty INSTANCE;
    private static final int[] gcbToHst = {0, 0, 0, 0, 1, 0, 4, 5, 3, 2};
    BinaryProperty[] binProps = {new BinaryProperty(1, 256), new BinaryProperty(1, 128), new BinaryProperty(5) {
        /* class android.icu.impl.UCharacterProperty.AnonymousClass1 */

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.BinaryProperty
        public boolean contains(int i) {
            return UBiDiProps.INSTANCE.isBidiControl(i);
        }
    }, new BinaryProperty(5) {
        /* class android.icu.impl.UCharacterProperty.AnonymousClass2 */

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.BinaryProperty
        public boolean contains(int i) {
            return UBiDiProps.INSTANCE.isMirrored(i);
        }
    }, new BinaryProperty(1, 2), new BinaryProperty(1, 524288), new BinaryProperty(1, 1048576), new BinaryProperty(1, 1024), new BinaryProperty(1, 2048), new BinaryProperty(8) {
        /* class android.icu.impl.UCharacterProperty.AnonymousClass3 */

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.BinaryProperty
        public boolean contains(int i) {
            Normalizer2Impl normalizer2Impl = Norm2AllModes.getNFCInstance().impl;
            return normalizer2Impl.isCompNo(normalizer2Impl.getNorm16(i));
        }
    }, new BinaryProperty(1, 67108864), new BinaryProperty(1, 8192), new BinaryProperty(1, 16384), new BinaryProperty(1, 64), new BinaryProperty(1, 4), new BinaryProperty(1, 33554432), new BinaryProperty(1, 16777216), new BinaryProperty(1, 512), new BinaryProperty(1, 32768), new BinaryProperty(1, 65536), new BinaryProperty(5) {
        /* class android.icu.impl.UCharacterProperty.AnonymousClass4 */

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.BinaryProperty
        public boolean contains(int i) {
            return UBiDiProps.INSTANCE.isJoinControl(i);
        }
    }, new BinaryProperty(1, 2097152), new CaseBinaryProperty(22), new BinaryProperty(1, 32), new BinaryProperty(1, 4096), new BinaryProperty(1, 8), new BinaryProperty(1, 131072), new CaseBinaryProperty(27), new BinaryProperty(1, 16), new BinaryProperty(1, 262144), new CaseBinaryProperty(30), new BinaryProperty(1, 1), new BinaryProperty(1, 8388608), new BinaryProperty(1, 4194304), new CaseBinaryProperty(34), new BinaryProperty(1, 134217728), new BinaryProperty(1, 268435456), new NormInertBinaryProperty(8, 37), new NormInertBinaryProperty(9, 38), new NormInertBinaryProperty(8, 39), new NormInertBinaryProperty(9, 40), new BinaryProperty(11) {
        /* class android.icu.impl.UCharacterProperty.AnonymousClass5 */

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.BinaryProperty
        public boolean contains(int i) {
            Normalizer2Impl normalizer2Impl = Norm2AllModes.getNFCInstance().impl;
            normalizer2Impl.ensureCanonIterData();
            return normalizer2Impl.isCanonSegmentStarter(i);
        }
    }, new BinaryProperty(1, 536870912), new BinaryProperty(1, 1073741824), new BinaryProperty(6) {
        /* class android.icu.impl.UCharacterProperty.AnonymousClass6 */

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.BinaryProperty
        public boolean contains(int i) {
            return UCharacter.isUAlphabetic(i) || UCharacter.isDigit(i);
        }
    }, new BinaryProperty(1) {
        /* class android.icu.impl.UCharacterProperty.AnonymousClass7 */

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.BinaryProperty
        public boolean contains(int i) {
            return i <= 159 ? i == 9 || i == 32 : UCharacter.getType(i) == 12;
        }
    }, new BinaryProperty(1) {
        /* class android.icu.impl.UCharacterProperty.AnonymousClass8 */

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.BinaryProperty
        public boolean contains(int i) {
            return UCharacterProperty.isgraphPOSIX(i);
        }
    }, new BinaryProperty(1) {
        /* class android.icu.impl.UCharacterProperty.AnonymousClass9 */

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.BinaryProperty
        public boolean contains(int i) {
            return UCharacter.getType(i) == 12 || UCharacterProperty.isgraphPOSIX(i);
        }
    }, new BinaryProperty(1) {
        /* class android.icu.impl.UCharacterProperty.AnonymousClass10 */

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.BinaryProperty
        public boolean contains(int i) {
            return (i <= 102 && i >= 65 && (i <= 70 || i >= 97)) || (i >= 65313 && i <= 65350 && (i <= 65318 || i >= 65345)) || UCharacter.getType(i) == 9;
        }
    }, new CaseBinaryProperty(49), new CaseBinaryProperty(50), new CaseBinaryProperty(51), new CaseBinaryProperty(52), new CaseBinaryProperty(53), new BinaryProperty(7) {
        /* class android.icu.impl.UCharacterProperty.AnonymousClass11 */

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.BinaryProperty
        public boolean contains(int i) {
            String decomposition = Norm2AllModes.getNFCInstance().impl.getDecomposition(i);
            if (decomposition != null) {
                i = decomposition.codePointAt(0);
                if (Character.charCount(i) != decomposition.length()) {
                    i = -1;
                }
            } else if (i < 0) {
                return false;
            }
            if (i < 0) {
                return !UCharacter.foldCase(decomposition, true).equals(decomposition);
            }
            UCaseProps uCaseProps = UCaseProps.INSTANCE;
            UCaseProps.dummyStringBuilder.setLength(0);
            if (uCaseProps.toFullFolding(i, UCaseProps.dummyStringBuilder, 0) >= 0) {
                return true;
            }
            return false;
        }
    }, new CaseBinaryProperty(55), new BinaryProperty(10) {
        /* class android.icu.impl.UCharacterProperty.AnonymousClass12 */

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.BinaryProperty
        public boolean contains(int i) {
            Normalizer2Impl normalizer2Impl = Norm2AllModes.getNFKC_CFInstance().impl;
            String valueOf = UTF16.valueOf(i);
            StringBuilder sb = new StringBuilder();
            normalizer2Impl.compose(valueOf, 0, valueOf.length(), false, true, new Normalizer2Impl.ReorderingBuffer(normalizer2Impl, sb, 5));
            return !Normalizer2Impl.UTF16Plus.equal(sb, valueOf);
        }
    }, new BinaryProperty(2, 268435456), new BinaryProperty(2, 536870912), new BinaryProperty(2, 1073741824), new BinaryProperty(2, Integer.MIN_VALUE), new BinaryProperty(2, 134217728), new BinaryProperty(2) {
        /* class android.icu.impl.UCharacterProperty.AnonymousClass13 */

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.BinaryProperty
        public boolean contains(int i) {
            return 127462 <= i && i <= 127487;
        }
    }, new BinaryProperty(1, Integer.MIN_VALUE), new BinaryProperty(2, 67108864)};
    IntProperty[] intProps = {new BiDiIntProperty() {
        /* class android.icu.impl.UCharacterProperty.AnonymousClass14 */

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.IntProperty
        public int getValue(int i) {
            return UBiDiProps.INSTANCE.getClass(i);
        }
    }, new IntProperty(0, 130816, 8), new CombiningClassIntProperty(8) {
        /* class android.icu.impl.UCharacterProperty.AnonymousClass15 */

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.IntProperty
        public int getValue(int i) {
            return Normalizer2.getNFDInstance().getCombiningClass(i);
        }
    }, new IntProperty(2, 31, 0), new IntProperty(0, 917504, 17), new IntProperty(1) {
        /* class android.icu.impl.UCharacterProperty.AnonymousClass16 */

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.IntProperty
        public int getMaxValue(int i) {
            return 29;
        }

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.IntProperty
        public int getValue(int i) {
            return UCharacterProperty.this.getType(i);
        }
    }, new BiDiIntProperty() {
        /* class android.icu.impl.UCharacterProperty.AnonymousClass17 */

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.IntProperty
        public int getValue(int i) {
            return UBiDiProps.INSTANCE.getJoiningGroup(i);
        }
    }, new BiDiIntProperty() {
        /* class android.icu.impl.UCharacterProperty.AnonymousClass18 */

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.IntProperty
        public int getValue(int i) {
            return UBiDiProps.INSTANCE.getJoiningType(i);
        }
    }, new IntProperty(2, 66060288, 20), new IntProperty(1) {
        /* class android.icu.impl.UCharacterProperty.AnonymousClass19 */

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.IntProperty
        public int getMaxValue(int i) {
            return 3;
        }

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.IntProperty
        public int getValue(int i) {
            return UCharacterProperty.ntvGetType(UCharacterProperty.getNumericTypeValue(UCharacterProperty.this.getProperty(i)));
        }
    }, new IntProperty(0, 255, 0) {
        /* class android.icu.impl.UCharacterProperty.AnonymousClass20 */

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.IntProperty
        public int getValue(int i) {
            return UScript.getScript(i);
        }
    }, new IntProperty(2) {
        /* class android.icu.impl.UCharacterProperty.AnonymousClass21 */

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.IntProperty
        public int getMaxValue(int i) {
            return 5;
        }

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.IntProperty
        public int getValue(int i) {
            int additional = (UCharacterProperty.this.getAdditional(i, 2) & 992) >>> 5;
            if (additional < UCharacterProperty.gcbToHst.length) {
                return UCharacterProperty.gcbToHst[additional];
            }
            return 0;
        }
    }, new NormQuickCheckIntProperty(8, 4108, 1), new NormQuickCheckIntProperty(9, 4109, 1), new NormQuickCheckIntProperty(8, 4110, 2), new NormQuickCheckIntProperty(9, 4111, 2), new CombiningClassIntProperty(8) {
        /* class android.icu.impl.UCharacterProperty.AnonymousClass22 */

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.IntProperty
        public int getValue(int i) {
            return Norm2AllModes.getNFCInstance().impl.getFCD16(i) >> 8;
        }
    }, new CombiningClassIntProperty(8) {
        /* class android.icu.impl.UCharacterProperty.AnonymousClass23 */

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.IntProperty
        public int getValue(int i) {
            return Norm2AllModes.getNFCInstance().impl.getFCD16(i) & 255;
        }
    }, new IntProperty(2, 992, 5), new IntProperty(2, 1015808, 15), new IntProperty(2, 31744, 10), new BiDiIntProperty() {
        /* class android.icu.impl.UCharacterProperty.AnonymousClass24 */

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.IntProperty
        public int getValue(int i) {
            return UBiDiProps.INSTANCE.getPairedBracketType(i);
        }
    }, new IntProperty(12) {
        /* class android.icu.impl.UCharacterProperty.AnonymousClass25 */

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.IntProperty
        public int getMaxValue(int i) {
            return 14;
        }

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.IntProperty
        public int getValue(int i) {
            return InPCTrie.INSTANCE.get(i);
        }
    }, new IntProperty(13) {
        /* class android.icu.impl.UCharacterProperty.AnonymousClass26 */

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.IntProperty
        public int getMaxValue(int i) {
            return 35;
        }

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.IntProperty
        public int getValue(int i) {
            return InSCTrie.INSTANCE.get(i);
        }
    }, new IntProperty(14) {
        /* class android.icu.impl.UCharacterProperty.AnonymousClass27 */

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.IntProperty
        public int getMaxValue(int i) {
            return 3;
        }

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.IntProperty
        public int getValue(int i) {
            return VoTrie.INSTANCE.get(i);
        }
    }};
    int m_additionalColumnsCount_;
    Trie2_16 m_additionalTrie_;
    int[] m_additionalVectors_;
    int m_maxBlockScriptValue_;
    int m_maxJTGValue_;
    public char[] m_scriptExtensions_;
    public Trie2_16 m_trie_;
    public VersionInfo m_unicodeVersion_;

    private static final class InPCTrie {
        static final CodePointTrie INSTANCE = UCharacterProperty.makeTrie("3irTBzý\u0002r\u000b\u0002zzzzzz@zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzÀzÿz?\u0001~\u0001¾\u0001~\u0001þ\u0001>\u0002~\u0002¼\u0002ü\u0002<\u0003{\u0003>\u0002»\u0003û\u00039\u0004w\u0004­\u0004á\u0004!\u00051\u0005q\u0005\u0005Ù\u0005\u0019\u0006V\u0006·\u0002Æ\u0002Ò\u0002Æ\u0002í\u0002zz\u0010z z0z@zPz`zpzzz\u0010z z0zzz\u0010z z0zzz\u0010z z0zzz\u0010z z0zzz\u0010z z0zzz\u0010z z0zzz\u0010z z0zzz\u0010z z0zzz z°zÀzÐzàzðzÿz\u000f\u0001\u001f\u0001/\u0001?\u0001O\u0001_\u0001o\u0001~\u0001\u0001\u0001®\u0001¾\u0001Î\u0001Þ\u0001î\u0001~\u0001\u0001\u0001®\u0001þ\u0001\u000e\u0002\u001e\u0002.\u0002>\u0002N\u0002^\u0002n\u0002~\u0002\u0002\u0002®\u0002¼\u0002Ì\u0002Ü\u0002ì\u0002ü\u0002\f\u0003\u001c\u0003,\u0003<\u0003L\u0003\\\u0003l\u0003{\u0003\u0003\u0003«\u0003>\u0002N\u0002^\u0002n\u0002»\u0003Ë\u0003Û\u0003ë\u0003û\u0003\u000b\u0004\u001b\u0004+\u00049\u0004I\u0004Y\u0004i\u0004w\u0004\u0004\u0004§\u0004­\u0004½\u0004Í\u0004Ý\u0004á\u0004ñ\u0004\u0001\u0005\u0011\u0005!\u00051\u0005A\u0005Q\u00051\u0005A\u0005Q\u0005a\u0005q\u0005\u0005\u0005¡\u0005\u0005©\u0005¹\u0005É\u0005Ù\u0005é\u0005ù\u0005\t\u0006\u0019\u0006)\u00069\u0006I\u0006V\u0006f\u0006v\u0006\u0006zzzz\u0006\u0006zz©\u0006¸\u0006Ç\u0006Õ\u0006å\u0006zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzó\u0006zzó\u0006zz\u0001\u0007zz\u0001\u0007zzzzzz\u000b\u0007\u001b\u0007)\u0007zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz9\u0007I\u0007zzzzzzzzzzzzzzY\u0007h\u0007zzzzzzr\u0007zzzzzz~\u0007\u0007\u0007zzzzzzzzzzzzzzzz«\u0007zzzz·\u0007Ç\u0007zzÌ\u0007,\u0005zzzÜ\u0007zzzzzzê\u0007û\u0003zzzzú\u0007\u0007\bzzzzzzzzzzzzzzzzzz\u0017\b'\b5\bzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz³\u0002?\bzzL\bzzzzzzzzzz\u0001\u0001zzzzX\bd\bzzt\b\bzzzz\bzz \bû\u0003zzzzzzzzz°\bÀ\bzz¹\u0002zzzzÇ\bÖ\bã\bzzzzñ\bzzzzzz\u0001\t½\u0002zz\u0011\tQ\u0001zzzzzzzzzzzzzzzzzzzzzzzzzzzz!\tzz0\tzzzz@\tzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzP\tzzzzX\tf\tzzzzzzzzzzzv\tzzzzzzzz-\u0005zz\t\tË\u0003zzzzY\u0006zzzzz\t®\tzzzzzz»\tË\tzzzzzzzzzzzzzzzzzzqzÛ\tzzÿzzzzzæ\tö\tO\u0001\u0004\n+\u0005zzzzzzzzzzzzzzzz\t\u0014\no\u0001zzzzzzzzzz$\n3\nzzzzzzzzzzzzzzzzzzzzzzzzzzë\u0002C\nãz\u0014\u0002zzzzzzS\n¾\u0002zzzzzzzzzzc\ns\nzzzzzzzzzz{\n\nzzzzzzzzzzzzzzzzzzzzzzzzzz\n¦\nzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzµ\nzzzzÂ\nzzÑ\nzzzzÝ\nç\nzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzë\u0002÷\nzzzzzzzzzz\u0007\u000b\u000f\u000b\u001e\u000bzzzzzzzzzzzzzz-\u000b<\u000bzzzzzzD\u000bT\u000bzzzzzzzzzzzzzzzzzzzzzzzzzzzzzza\u000bzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzEzMzMzMz]z}zz½zÝz\u0002z\u0002zìz\n\u0001)\u0001I\u0001\u0002z\u0002z\u0002z\u0002z\u0002z\u0002z\u0002z\u0002z\u0002z\u0002z\u0002z\u0002z\u0002z\u0002z\u0002z\u0002z\u0002z\u0002z\u0002z\u0002z\u0002z\u0002z\u0002z\u0002z\u0002z\u0002z\u0002z\u0002z\u0002z\u0002z\u0002z\u0002zi\u0001\u0001\u0002z\u0002z\u0002z\u0002z\u0002z\u0002z\u0002z\u0002z\u0002z\u0002z¨\u0001\u0002z\u0002zÈ\u0001æ\u0001\u0003\u0002!\u0002?\u0002_\u0002}\u0002\u0002zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\b\b\b\u0007zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\b\u0007\u0001z\u0007\u0004\u0007\u0001\u0001\u0001\u0001\b\b\b\b\u0007\u0007\u0007\u0007\u0001\u0004\u0007z\b\u0001\b\b\b\u0001\u0001zzzzzzzzzz\u0001\u0001zzzzzzzzzzzzzzzzzzzzzzzzzzzz\b\u0007\u0007zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\u0001z\u0007\u0004\u0007\u0001\u0001\u0001\u0001zz\u0004\u0004zz\u0005\u0005\u0001zzzzzzzzz\u0007zzzzzzzzzz\u0001\u0001zzzzzzzzzzzzzzzzzzzzzzzzzz\bz\b\b\u0007zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\u0001z\u0007\u0004\u0007\u0001\u0001zzzz\b\bzz\b\b\u0001zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\b\bzzz\u0001zzzzzzzzzz\u0007\u0001\u0001\u0001\u0001\bz\b\b\rz\u0007\u0007\u0001zzzzzzzzzzzzzzzzzzzz\u0001\u0001zzzzzzzzzzzzzzzzzzzzzz\b\b\b\b\b\bz\b\u0007\u0007zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\u0001z\u0007\b\u0007\u0001\u0001\u0001\u0001zz\u0004\u000bzz\u0005\f\u0001zzzzzzzz\b\rzzzzzzzzzz\u0001\u0001zzzzzzzzzzzzzzzzzzzzzzzzzzzz\bzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\u0007\u0007\b\u0007\u0007zzz\u0004\u0004\u0004z\u0005\u0005\u0005\bzzzzzzzzz\u0007zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\b\u0007\u0007\u0007\bzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\b\b\u0007\u0007\u0007\u0007z\b\b\tz\b\b\b\bzzzzzzz\b\u0001zzzzzzzzzzz\u0001\u0001zzzzzzzzzzzzzzzzzzzzzzzzzzzz\r\u0007\u0007\u0007\u0007z\b\r\rz\r\r\b\bzzzzzzz\u0007\u0007zzzzzzzzzzz\u0001\u0001zzzzzzzzzzzzzzzzzzzzzzzzzzzz\b\b\u0007\u0007zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\b\bz\u0007\u0007\u0007\u0001\u0001z\u0004\u0004\u0004z\u0005\u0005\u0005\bzzzzzzzzz\u0007zzzzzzzzzz\u0001\u0001zzzzzzzzzzzzzzzzzzzzzzzzzzzz\u0007\u0007zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\bzzzz\u0007\u0007\u0007\b\b\u0001z\u0001z\u0007\u0004\u000b\u0004\u0005\f\u0005\u0007zzzzzzzzzzzzzzzzzz\u0007\u0007zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\u0007\b\u0007\u0007\b\b\b\b\u0001\u0001\u0001zzzzz\u000e\u000e\u000e\u000e\u000e\u0007z\b\b\b\b\b\b\b\bzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\u0007\b\u0007\u0007\b\b\b\b\u0001\u0001z\b\u0001zzz\u000e\u000e\u000e\u000e\u000ezzz\b\b\b\b\b\bzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\u0001\u0001zzzzzzzzzzzzzzzzzzzzzzzzzzz\u0001z\u0001z\bzzzz\u0007\u0004zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\u0001\b\t\u0001\u0001\t\t\t\t\b\b\b\b\b\u0007\b\t\b\b\u0001z\b\bzzzzz\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001z\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001zzzzzz\u0001zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\u0007\u0007\b\b\u0001\u0004\b\b\b\b\b\u0001\u0007z\b\u0007z\u0001\u0001zzzzzz\u0007\u0007\u0001\u0001zzzz\u0001\u0001z\u0007\u0007\u0007zz\u0007\u0007\u0007\u0007\u0007\u0007\u0007zz\b\b\b\bzzzzzzzzzzz\u0001\u0007\u0004\b\b\u0007\u0007\u0007\u0007\u0007\u0007\u0001z\u0007zzzzzzzzzz\u0007\u0007\u0007\bzz\b\u0001\u0001zzzzzzzzzzz\b\u0001zzzzzzzzzzzz\u0007\b\b\b\b\u0001\u0001\u0001\u000b\f\u0005\u0004\u0004\u0004\u0005\u0005\b\u0007\u0007\b\b\b\b\b\b\bz\bzzzzzzzzz\bzz\b\b\u0001\u0007\u0007\r\r\b\b\u0007\u0007\u0007zzzz\u0007\u0007\u0001\u0007\u0007\u0007\u0007\u0007\u0007\u0001\b\u0001zzzz\u0007\u0007\u0007\u0007\u0007\u000e\u000e\u000e\u0007\u0007\u000e\u0007\u0007\u0007\u0007\u0007zzzzzzz\u0007\u0007zzzzzzz\b\u0001\u0004\u0007\bzzzzz\u0004\u0001\u0007\b\b\b\u0001\u0001\u0001\u0001z\u0007\b\u0007\u0007\b\b\b\b\u0001\u0001\b\u0001\u0007\u0004\u0004\u0004\b\b\b\b\b\b\b\b\b\bzz\u0001\b\b\b\b\u0007zzzzzzzzzzz\b\u0007\b\b\u0001\u0001\u0001\u0003\t\n\u0004\u0004\u0005\u0005\b\r\u0007zzzzzzzzzzz\b\u0001\b\b\bz\u0007\u0001\u0001\b\u0001\u0004\u0007\b\b\u0007z\u0001\u0001zzzzzz\b\u0007\b\b\u0007\u0007\u0007\b\u0007\bzzzz\u0007\u0007\u0007\u0004\u0004\u000b\u0007\u0007\u0001\b\b\b\b\u0004\u0004\b\u0001zzzzzzzz\b\b\bz\u0006\u0001\u0001\u0001\u0001\u0001\b\b\u0001\u0001\u0001\u0001\b\u0007\u0006\u0006\u0006\u0006\u0006\u0006\u0006zzzz\u0001zzzz\bzz\u0007zzzzzzzz\bzzzz\bzzzz\u0007\u0007\u0001\b\u0007zzzzzzzz\u0007\u0007\u0007\u0007\u0007\u0007\u0007\u0007\u0007\u0007\u0007\u0007\u0001\bzzzzzzzzzz\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\bzzzzzzzzzzzzz\bzzzzzzzzzzz\u0001\u0001\u0001zzzzzzz\u0001\u0001\u0001\b\u0001\u0001\u0001\u0001\bzzz\b\u0007\u0007\b\b\u0001\u0001\u0004\u0004\b\u0007\u0007\u0002\u0003zzzzzzzzzzzzzzz\b\b\b\b\u0001\b\u0004\b\u0001\u0007\u0004\u0001\u0001zzzzzzzzz\bzzzzzzzz\b\u0007zzzzzzzzzzz\u0007\b\u0007zz\b\u0007\b\b\u0001\u000e\u000e\b\b\u000e\u0007\u000e\u000e\u0007\b\bzzzzzzzzzzz\u0004\u0001\b\u0004\u0007zzz\u0007\u0007\b\u0007\u0007\u0001\u0007\u0007z\u0007\u0001zz\u0006\u0001\u0001z\b\u0006zzzzz\u0001\u0001\u0001\bzzzzzzzz\b\u0001\u0001zzzzz\u0007\b\u0007zzzzzzzzzzzzz\b\b\b\b\u0001\u0001\u0001\u0001\b\b\b\b\bzzzzzzzzz\u0007\u0004\u0007\u0001\u0001\b\b\u0007\u0007\u0001\u0001zzzzzzz\b\b\b\u0001\u0001\u0004\b\t\t\b\u0001\u0001z\bzzzzzzzzzzz\u0007\u0004\u0007\u0001\u0001\u0001\u0001\u0001\u0001\b\b\b\r\u0007zzzzzzzz\u0001z\b\u0001zzzzzzzzzzzz\u0007\u0007\u0007\u0001\b\b\r\r\b\u0007\b\bzzzzzz\bz\u0007\u0004\u0007\u0001\u0001\b\b\b\b\u0001\u0001zzzzzzzzzzz\u0001\u0001z\u0007\u0007\b\u0007\u0007\u0007\u0007zz\u0004\u0004zz\u0005\u0005\u0007zz\u0007\u0007zz\b\b\b\b\b\b\bzzz\u0007\u0007\u0001\b\b\u0007\u0001zzzzzzzzz\u0007\u0004\u0007\u0001\u0001\u0001\u0001\u0001\u0001\u0004\b\u000b\u0005\u0007\u0005\b\u0007\u0001\u0001zzzzzzzzzzzz\u0004\u0007\u0001\u0001\u0001\u0001zz\u0004\u000b\u0005\f\b\b\u0007\u0001\u0007\u0007\u0007\u0001\u0001\u0001\u0001\u0001\u0001\b\b\u0007\u0007\b\u0007\u0001zzzzzzzzzzz\b\u0007\b\u0004\u0007\u0001\u0001\b\b\b\b\u0007\u0001zzzzzzzzzzzzz\u0001z\b\u0007\u0007\b\b\u0001\u0001\u0004\b\u0001\b\b\bzzzzzzzzzzzz\u0007\u0004\u0007\u0001\u0001\u0001\b\b\b\b\b\u0007\u0001\u0001zzzzz\b\u0001\u0001\b\b\b\b\b\b\u0001zzzzz\u0001\u0001\b\b\b\b\u0007z\u0001\u0001\u0001\u0001z\b\u0001\u0001\b\b\b\u0007\u0007\u0001\u0001\u0001zzzzzzzzzz\u0001\u0001\u0001\u0001\u0001\u0001\b\u0007\bzzzzzzz\b\b\u0001\u0001\u0001\u0001\u0001z\b\b\b\b\b\b\u0007\u0001zz\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001z\u0007\u0001\u0001\u0001\u0001\u0001\u0001\u0004\u0001\b\u0007\b\bzzzzzzzzz\b\b\b\b\b\u0001zzz\bz\b\bz\b\b\u0001\b\u0001zz\u0001zzzzzzzzzz\u0007\u0007\u0007\u0007\u0007z\b\bz\u0007\u0007\b\u0007zzzzzzzzz\b\u0001\u0004\u0007zzzzzzzzzz");
    }

    private static final class InSCTrie {
        static final CodePointTrie INSTANCE = UCharacterProperty.makeTrie("3irTBzB\u0003x\u000f\u0004z@zzzz@z`zz@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@zÔz\u0012\u0001R\u0001\u0001Ï\u0001\r\u0002L\u0002\u0002Ê\u0002\b\u0003F\u0003\u0003Ä\u0003\u0002\u0004A\u0004\u0004¿\u0004ý\u0004=\u0005}\u0005¼\u0005ü\u0005;\u0006{\u0006\u0006Û\u0006\u001b\u0007X\u0007ø\u0002\u000b\u0003\u0017\u0003\u000b\u00032\u0003zz\u0010z z0z@zPz`zpz`zpzzzz¤z´zÄz@zPz`zpz@zPz`zpz@zPz`zpz@zPz`zpz@zPz`zpz@zPz`zpz@zPz`zpz@zPz`zpzÔzäzôz\u0004\u0001\u0012\u0001\"\u00012\u0001B\u0001R\u0001b\u0001r\u0001\u0001\u0001 \u0001°\u0001À\u0001Ï\u0001ß\u0001ï\u0001ÿ\u0001\r\u0002\u001d\u0002-\u0002=\u0002L\u0002\\\u0002l\u0002|\u0002\u0002\u0002ª\u0002º\u0002Ê\u0002Ú\u0002ê\u0002ú\u0002\b\u0003\u0018\u0003(\u00038\u0003F\u0003V\u0003f\u0003v\u0003\u0003\u0003¤\u0003´\u0003Ä\u0003Ô\u0003ä\u0003ô\u0003\u0002\u0004\u0012\u0004\"\u00042\u0004A\u0004Q\u0004a\u0004q\u0004\u0004\u0004\u0004¯\u0004¿\u0004Ï\u0004ß\u0004ï\u0004ý\u0004\r\u0005\u001d\u0005-\u0005=\u0005M\u0005]\u0005m\u0005}\u0005\u0005\u0005­\u0005¼\u0005Ì\u0005Ü\u0005ì\u0005ü\u0005\f\u0006\u001c\u0006,\u0006;\u0006K\u0006[\u0006k\u0006{\u0006\u0006\u0006«\u0006\u0006«\u0006»\u0006Ë\u0006Û\u0006ë\u0006û\u0006\u000b\u0007\u001b\u0007+\u0007;\u0007K\u0007X\u0007h\u0007x\u0007\u0007ézéz\u0007£\u0007³\u0007Ã\u0007Ò\u0007á\u0007ï\u0007ÿ\u0007@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z\u000f\b\u001d\bæz\u001d\bæz-\b\u000f\b=\bézézM\bY\bc\br\b0z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z\bl\u0001\b¢\b-\u0002éz²\bÂ\bézézt\u0003Ò\bá\b0z@z@zézñ\bézéz\u0001\t\u000e\t\u001e\t*\t0z0z@z@z@z@z@z@z:\tæzézJ\tV\t0z@z@zf\tézu\t\tézéz\t¥\tézézµ\tÂ\tÒ\t@z@z@z@z@z@z@z@zâ\tð\tþ\t@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z\b\n\u0014\n$\n@z@z@z@z@zZ\u00072\n@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@ztz@z@z@zB\nézO\n@zéz_\nm\n|\nÖzçzéz\n\n0z¨\n¶\nÆ\nézÔ\nézä\nó\n@z@z\u0003\u000bézéz\u0012\u000b\u00020z\"\u000b2\u000bãzéz\bB\u000bR\u000b0zéza\u000bézézézq\u000b\u000b@z\u000b¡\u000b@z@z@z@z@z@z@z@z@z@z@z@z±\u000bÁ\u000bÎ\u000b0zÞ\u000bî\u000bézø\u000b1z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z\b\fæzéz\b\u0018\f&\f0\f@\fP\fézéz`\f@z@z@z@zp\féz\b\f\f \féz­\fÕzèzéz½\fÍ\f0zº\u00065zázë\u0003\bÝ\f@z@z@z@zí\fm\u0001ü\fßzéz\f\r\u001c\r0z,\rb\u0001r\u0001<\r\b\u0003L\r\\\rí\t@z@z@z@z@z@z@z@zÛzézézl\r\u0000\r\r@z@z\rézéz\u001f\t©\r0z@z@z@z@z@z@z@z@z@z@zÛzézÿz¹\rÉ\rÑ\r@z@zÛzézézá\rñ\r0z@z@zßzéz\u0001\u000e\u000e\u000e0z@z@z@zéz\u001e\u000e.\u000e>\u000e@z@z@z@z@z@z@z@z@z@z@z@zßzéz\bN\u000e@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z^\u000eézézk\u000e{\u000e\u000eézéz\u000e¡\u000e@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z±\u000eézÿzÁ\u000eÑ\u000e»\u0006á\u000eU\u0005ézï\u000e+\u0007ÿ\u000e@z@z@z@z\u000f\u000fézéz\u001e\u000f.\u000f0z>\u000fézJ\u000fW\u000f0z@z@z@z@z@z@z@z@z@z@z@z@z@z@zézg\u000f@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@zEzUzUzUzezz¥zÅzåz\u0004z\u0004zõz\u0014\u00014\u0001T\u0001\u0004zt\u0001\u0004z}\u0001\u0004z\u0004z\u0004z\u0004z\u0004z\u0004z\u0004z\u0004z\u0004z\u0004z\u0004z\u0004z\u0004z\u0004z\u0004z\u0004z\u0004z\u0004z\u0004z\u0004z\u0004z\u0004z\u0004z\u0004z\u0004z\u0004z\u0004z\u0004z\u0004z\u0004z\u0004z\u0004z\u0001½\u0001\u0004z\u0004z\u0004z\u0004z\u0004z\u0004z\u0004z\u0004z\u0004z\u0004zÝ\u0001\u0004z\u0004zý\u0001\u001d\u0002=\u0002]\u0002}\u0002\u0002½\u0002Ø\u0002zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\fzz\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\fzzzzzzzzzzzzzzzzz\u001c\u001czzzzzzzzzzzzzzzzzzzzzzz\fzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\u0002\u0002\u0002 #################\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\"\"\u0017\u0001\"\"\"\"\"\"\"\"\"\"\"\"\"\u001f\"\"z\u0004\u0004zz\"\"\"\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005##\"\"zz\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018zz######\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\f\u0002\u0002 z########zz##zz##\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005z\u0005\u0005\u0005\u0005\u0005\u0005\u0005z\u0005zzz\u0005\u0005\u0005\u0005zz\u0017\u0001\"\"\"\"\"zz\"\"zz\"\"\u001f\u0006zzzzzzzz\"zzzz\u0005\u0005z\u0005##\"\"zz\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0005\u0005zzzzzzzzzz\u0002z\u001cz\u0002\u0002 z######zzzz##zz##\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005z\u0005\u0005\u0005\u0005\u0005\u0005\u0005z\u0005\u0005z\u0005\u0005z\u0005\u0005zz\u0017z\"\"\"zzzz\"\"zz\"\"\u001fzzz\u0004zzzzzzz\u0005\u0005\u0005\u0005z\u0005zzzzzzz\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0002\u0012\f\fz\u000bzzzzzzzzzz\u0002\u0002 z#########z###z##\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005z\u0005\u0005\u0005\u0005\u0005\u0005\u0005z\u0005\u0005z\u0005\u0005\u0005\u0005\u0005zz\u0017\u0001\"\"\"\"\"\"z\"\"\"z\"\"\u001fzzzzzzzzzzzzzzzzzz##\"\"zz\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018zzzzzzzzz\u0005\u0004\u0004\u0004\u0017\u0017\u0017z\u0002\u0002 z########zz##zz##\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005z\u0005\u0005\u0005\u0005\u0005\u0005\u0005z\u0005\u0005z\u0005\u0005\u0005\u0005\u0005zz\u0017\u0001\"\"\"\"\"zz\"\"zz\"\"\u001fzzzzzzzz\"\"zzzz\u0005\u0005z\u0005##\"\"zz\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018z\u0005zzzzzzzzzzzzzz\u0002\u0015z######zzz###z###\u0005zzz\u0005\u0005z\u0005z\u0005\u0005zzz\u0005\u0005zzz\u0005\u0005\u0005zzz\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005zzzz\"\"\"zzz\"\"\"z\"\"\"\u001fzzzzzzzzz\"zzzzzzzzzzzzzz\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018zzzzzzzzzzzzzzzz\u0002\u0002\u0002 \u0002########z###z###\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005z\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005zzz\u0001\"\"\"\"\"z\"\"\"z\"\"\"\u001fzzzzzzz\"\"z\u0005\u0005\u0005zzzzz##\"\"zz\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018zzzzzzzzzzzzzzzz\u0002\u0002 z########z###z###\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005z\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005z\u0005\u0005\u0005\u0005\u0005zz\u0017\u0001\"\"\"\"\"z\"\"\"z\"\"\"\u001fzzzzzzz\"\"zzzzzzz\u0005z##\"\"zz\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018z\u0011\u0011zzzzzzzzzzzzz\u0002\u0002\u0002 z########z###z###\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u001a\u001a\u0001\"\"\"\"\"z\"\"\"z\"\"\"\u001f\rzzzzz\u0006\u0006\u0006\"zzzzzzz###\"\"zz\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018zzzzzzzzzz\u0006\u0006\u0006\u0006\u0006\u0006zz\u0002 z##################zzz\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005z\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005z\u0005zz\u0005\u0005\u0005\u0005\u0005\u0005\u0005zzz\u001fzzzz\"\"\"\"\"\"z\"z\"\"\"\"\"\"\"\"zzzzzz\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018zz\"\"zzzzzzzzzzzz\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005z\"\"\"\"\"\"\"\"\"\"\u001azzzzz\"\"\"\"\"\"z\"\u001e\u001e\u001e\u001e\n\u0002\u001az\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\u0005\u0005z\u0005zz\u0005\u0005z\u0005zz\u0005zzzzzz\u0005\u0005\u0005\u0005z\u0005\u0005\u0005\u0005\u0005\u0005\u0005z\u0005\u0005\u0005z\u0005z\u0005zz\u0005\u0005z\u0005\u0005z\"\"\"\"\"\"\"\"\"\"z\"\u000b\u000bzz\"\"\"\"\"zzz\u001e\u001e\u001e\u001ez\u0002zz\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018zz\u0005\u0005\u0005\u0005zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018z\u001cz\u001cz\u0017zzzzzz\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005z\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005zzzz\"\"\"\"\"\"\"\"\"\"\"\"\"\u0002 \"\"\u0002\u0002\u001a\u0001zz\b\b\b\b\b\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000fz\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000fzzzzzz\u001czzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\u0005##########\"\"\"\"\"\"\u0002\u001e \u0013\u001a\u000b\u000b\u000b\u000b\u0005\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018z\fzz\fz\u0005\u0005####\"\"\"\"\u0005\u0005\u0005\u0005\u000b\u000b\u0005\"\u001e\u001e\u0005\u0005\"\"\u001e\u001e\u001e\u001e\u001e\u0005\u0005\"\"\"\"\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u000b\"\"\"\"\u001e\u001e\u001e\u001e\u001e\u001e\u001e\u0005\u001e\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u001e\u001e\"\"zz###\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005z\u0005\u0005\"\"\u001azzzzzzzzzzz\u0005\u0005\"\"zzzzzzzzzzzz\u0005z\"\"zzzzzzzzzzzz\u0005\u0005\u0005#############zz\"\"\"\"\"\"\"\"\"\"\u0002 \"\u001b\u001b\u001c\u0010\n\u001c\u001c\u001a\u0013\u001czzzzzzzz\u0001\u001czz\f\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\"\"\"\"\"\"\"\"\"\u000f\u000f\u000fzzzz\u0007\u0007\u0002\u0007\u0007\u0007\u0007\u0007\u0007\u0007\"\u001czzzz\u0005\u0005\u0005!!!!!!!!!!!zz\u001d\u001d\u001d\u001d\u001dzzzzzzzzzzz\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\u0007\u0007\u0007\u0007\u0007\u0007\u0007\u001e\u001ezzzzzz\u0005\u0005\u0005\u0005\u0005\u0005\u0005\"\"\"\"\"zzzz\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005###\u0005\u0005\u000b\u000b\u000f\u0007\u0007\t\u000f\u000f\u000f\u000fz\u0013\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\u0002\u001e\u001e\u001e\u001e\u001e\u001a\u001c\u001czz\u001c\u0002\u0002\u0002\u0010 ###########\u0005\u0005\u0005\u0005\u0017\"\"\"\"\"\"\"\"\"\"\"\u001f\u0005\u0005\u0005\u0005\u0005\u0005\u0005zzzz\u0002\u0010 #######\u0005\u0005\u0005\u0005\u0005\u0005\u000f\u000f\u000f\"\"\"\"\"\"\u001a\u0013\u000f\u000f\u0005\u0005\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0001\u0005\u0005\u0005\u0007\u0007\u0005\u0005\u0005\u0005##\u0017\"\"\"\"\"\"\"\"\"\u0007\u0007\u001a\u001azzzzzzzzzzzz\u0005\u0005\u0005\u0005\u000f\u000f\"\"\"\"\"\"\"\u0007\u0007\u0007\u0007\u0002\u0002\u001c\u0017zzzzzzzz\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018zzz\u0005\u0005\u0005\u0004\u0004\u0004z\u0004\u0004\u0004\u0004\u0004\u0004\u0004\u0004\u0004\u0004\u0004\u0004zzzzzzzzzzzzzz  \u0004\u0011\u0011\u0004\u0004\u0004zzzzzzzzzzz\u001czzzzzzzzzzzz\u0016\u0014zz\f\f\f\f\fzzzzzzzzzzz\u001c\u001c\u001czzzzzzzzzzz##z###\u001a\u0005\u0005\u0005\u0005\u0002\u0005\u0005\u0005\u0005\"\"\"\"\"zzzzzzzz\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005!!\u0005\u0005\u0005\u0005!\u000f\u000f\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u000f\u0005\u0002zzzzzzzzzzzz\u0005\u0005\u0005\u0005\u000b\"\"\"\"\"\"\"\"\"\"\"\u001f\u0002zzzzzzzzzz\u0004\u0004\u0004\u0004\u0004\u0004\u0004\u0004\u0004\u0004\u0004\u0004\u0004\u0004\u0004\u0004\u0002\u0002zzzzzzzzzz#\"\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0005\u0005\u0005\u0005\u0005\u0005!!!!!!!!!\u001e\u001e\u001ezz\u0005\u0005\u0005\u0005\u0005\u0005\u0005\"\"\"\"\"\"\"\"\u0007\u0007\u0007\u001azzzzzzzzzzzz\u0002\u0002\u0010 #####\u0005\u0005\u0005###\u0005\u0005\u0005\u0017\"\"\"\"\"\"\"\"\"\u000f\u000b\u000b\u0005\u0005\u0005\u0005\u0005\"z\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0005\u0005\u0005\u0005\u0005z\"\"\"\u000b\u000b\u000b\u000bzzzzzzzzz\u0007\u0007\u0007\u0007\u0007\u0007\u0007\u0007\u0007\u0007\u0007\u0007\u0007\u0007zz\u0005\u0005\u0005\f\f\fzzz\u0005\u001e\u001e\u001e\u0005\u0005\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\u001e\u001d\u001e\u001dzzzzzzzzzzzzz##\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\"\"\"\"\"zzzzz \u0013zzzzzzzzz\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005##\u0005#\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0007\u0007\u0007\u0007\u0007\"\"\"\"\"\"\"\"z\u001e\u001azz\u0005\"\"\"z\"\"zzzzz\"\"\u0002 \u0005\u0005\u0005\u0005z\u0005\u0005\u0005z\u0005\u0005\u0005\u0005\u0005\u0005\u0005zz\u0017\u0017\u0017zzzz\u0013\u0002\u0002 \u0011\u0011###########\"\"\"\"\"\"\u001fzzzzzzzzz\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018zzzzzzzzzzzzzzz\u0019\u0002\u0002 ##########\u0005\u0005\u0005\"\"\"\"\"\"\"\"\"\u001f\u0017zzzzz\u0002\u0002 ####\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\"\"\"\u0013\u001az\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018zzzz\u0005\"\"zzzzzzzzz!!!!!\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0017zzzzzzzzzzzz\u0005\u0005\u0005\"\"\"\"\"\"\"\"\"\"\"\"\"\u001f\u0001\u000e\u000ezzzzz\u001c\u0017\"\"zzz\"\"\"\"\u0002\u001f\u0017\u0012zzzzzz\u0004z####\u0005\u0005\u0005z\u0005z\u0005\u0005\u0005\u0005z\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005zzzzzzz\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0002\"\"\"\"\"\"\"\"\"\u0017\u001azzzzz\u0002\u0002\u0002 z########zz#\u0005z\u0005\u0005z\u0005\u0005\u0005\u0005\u0005z\u0017\u0017\u0001\"\"zzzzzzz\"zzzzzz\u0002\u0002##\"\"zz\u0004\u0004\u0004\u0004\u0004\u0004\u0004zzz\u0005\u0005\u0005\u0005\u0005\"\"\"\"\"\"\"\"\"\"\"\u001f\u0002\u0002 \u0017\u0001zzzzzzzz\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018zzzz\u001cz##############\u0005\u0002 \u001f\u0017\u0001zzzzzzzzzzz\"\"\"\"\"\"zz\"\"\"\"\u0002\u0002 \u001f\u0017zzzzzzzzzzzzzzz####\"\"zz\"\"\"\"\"\"\"\"\"\"\"\"\"\u0002 \u001f\"zzzzzzzzzzzzzzz\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0002 \"\"\"\"\"\"\u001f\u0017zzzzzzzz\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005zz\u000b\u000b\u000b\"\"\"\"\"\"\"\"\"\"\"\u001azzzz\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018zzzz\"\"\"\"\"\"\"\u0002 \u001f\u0017zzzzz#\"\"\"\"\"\"\"\"\"\"\u0005\u0005\u0005\u0005\u0005\u001c\u001a\u0002\u0002\u0002\u0002 \u000e\u000b\u000b\u000b\u000b\fzzzzz\fz\u0013zzzzzzzz#\"\"\"\"\"\"\"\"\"\"\"\u0005\u0005\u0005\u0005zz\u000e\u000e\u000e\u000e\u0007\u0007\u0007\u0007\u0007\u0007\u0002 \u0012\u0013zzz\u0001zz#########z####\u0005\u0005\"\"\"\"\"\"\"z\"\"\"\"\u0002\u0002 \u001f\u0001zzzzzzzzzzzzzzz\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018zzz\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\"\"\"\"\"\u0002\u0002zzzzzzzzz#######z##z#\u0005\u0005\u0005\u0005\"\"\"\"\"\"zzz\"z\"\"z\"\u0002 \u0017\"\u001a\u0013\r\u000bzzzzzzzz######z##z##\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\"\"\"\"\"z\"\"\u0002 \u0013zzzzzzzz\u0005\u0005\f\"\"\"\"zzzzzzzzzz");
    }

    private static final class VoTrie {
        static final CodePointTrie INSTANCE = UCharacterProperty.makeTrie("3irTBzL\u0004<\u0003\fzzz\bzz@zYzzzzzzzzzzzzzzzzÐzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz;\u0003U\u0003c\u0003y\u0003\u0003·\u0003Ò\u0003ì\u0003U\u0003U\u0003U\u0003\f\u0004U\u0003U\u0003U\u0003\f\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004U\u0003U\u0003U\u0003\f\u0004U\u0003U\u0003U\u0003\f\u0004zz\u0010z z0z@zPz`zpzYzizyzzz¨z¸zÈzzz\u0010z z0zzz\u0010z z0zzz\u0010z z0zzz\u0010z z0zÐzàzðzz\u0001zz\u0010z z0zzz\u0010z z0zzz\u0010z z0zzz\u0010z z0zzz\u0010z z0zzz\u0010z z0zzz\u0010z z0zzz\u0010z z0zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz©zz\u001e\u0001,\u0001®zªzzzzzzzzzzzzz\u0003\u0001<\u0001zzL\u0001X\u0001f\u0001\u000b\u0001u\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0001zzzzzzzzzzzzzzrzzzözzzzzzzzzzzzzzzzzzzzzzzzz\u0001\u0010\u0001\u0001zzzzzzzz\u0003\u0001\u0010\u0001\u0015\u0001zzìz¨\u0001¶\u0001\u000e\u0001\u0010\u0001\u0010\u0001Æ\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001zzzzzzzzzzzzzzzzzzzz\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0016\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0018\u0001\n\u0001\u0010\u0001Ò\u0001zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\u000e\u0001\u0010\u0001zzzz\u0016\u0001zzzzzzzzzz\b\u0001\u0010\u0001â\u0001\u0014\u0001\u0010\u0001zzzzzzzzzzzzzzzz\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001ñ\u0001ÿ\u0001\u0010\u0001\u000e\u0002\u001d\u0002\u0010\u0001*\u0002\u0010\u00017\u0002F\u0002V\u0002\u0010\u0001*\u0002\u0010\u00017\u0002a\u0002\u0010\u0001\u0010\u0001n\u0002\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001~\u0002\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001~\u0002~\u0002~\u0002~\u0002~\u0002\u0002\u0010\u0001\u0002\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\u0010\u0001\u0010\u0001zzzzzzzzzzzzzzzz\u0010\u0001zz\u0010\u0001\u0017\u0001\u0002ª\u0002zzzzzzzzzzzzzzzzzzº\u0002É\u0002\u0010\u0001Ù\u0002\u0010\u0001é\u0002ø\u0002zzzzzzzzzzzzzz\b\u0003\u0018\u0003zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\u0010\u0001\u0010\u0001zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001zzzzzzzz\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001zzzzzzzzzzzzzzzzzzzzzzzzzzzz\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001zzzzzzzzzzzzzzzz(\u0003\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0012\u0001zz¨z¨z¨z¨z¨z¨zÈz\fzèzz\u0001\u0015\u0001\fz\fz\fz4\u0001S\u0001r\u0001\u0001\fz«\u0001\fzË\u0001ë\u0001\u000b\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002ûz\fzC\u0002\fz#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002\fz\fz\fz\fz#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002øz\fzb\u0002\fz\fz\fz\fz\u0002\fz\fz\fz\fz\fz\u0002\fz\fzýz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz#\u0002#\u0002¹\u0002\fz\fz\fz\fz\fz#\u0002z\u0001\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz¼\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002øz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fzÚ\u0002øz\fz\fz\fz\fz\fz\fz\fz\fz#\u0002ú\u0002\fz\fz#\u0002ýz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz#\u0002\u001a\u0003#\u0002#\u0002Èzµ\u0002\fz\fz#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002\u001b\u0003\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\u0003z\u0003zzzz\u0003zz\u0003zzzzzzzzzz\u0003\u0003\u0003zzzzzzzzzzzzzzzzzzzzzzz\u0003zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\u0003zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\u0003\u0003zzzzzzzzzzzzzzzzzzzz\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003zzzzzzzzz\u0003\u0003zzz\u0003zzzz\u0003\u0003\u0003zzzzzz\u0003z\u0003\u0003\u0003zzzzzzzzzzz\u0003\u0003z\u0003\u0003\u0003\u0003\u0003\u0003\u0003zzzzz\u0003\u0003z\u0003\u0003zzzzzz\u0003\u0003\u0003\u0003z\u0003z\u0003z\u0003zzzz\u0003zzzzz\u0003\u0003\u0003\u0003\u0003\u0003z\u0003\u0003z\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003zz\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003zzzz\u0003\u0003\u0003\u0003\u0003\u0001\u0001\u0003zzzz\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003z\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003zzzz\u0003\u0003\u0003z\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003zzzzzzzzzzzz\u0003\u0003z\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0002\u0002\u0003\u0003\u0003\u0003\u0003\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0003\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0002\u0003\u0002\u0003\u0002\u0003\u0002\u0003\u0002\u0003\u0003\u0003\u0003\u0003\u0003\u0002\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0002\u0003\u0002\u0003\u0002\u0003\u0003\u0003\u0003\u0003\u0003\u0002\u0003\u0003\u0003\u0003\u0003\u0002\u0002\u0003\u0003\u0003\u0003\u0002\u0002\u0003\u0003\u0003\u0001\u0002\u0003\u0002\u0003\u0002\u0003\u0002\u0003\u0002\u0003\u0003\u0003\u0003\u0003\u0003\u0002\u0002\u0003\u0003\u0003\u0003\u0003\u0001\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0002\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0002\u0002\u0002\u0002\u0002\u0002\u0002\u0002\u0002\u0002\u0002\u0002\u0002\u0002\u0002\u0002\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0002\u0002\u0002\u0002\u0002\u0003\u0003\u0003\u0003\u0003z\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0003\u0003zzzz\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003z\u0002\u0003\u0003\u0003\u0003\u0003\u0003\u0001\u0001\u0003\u0003\u0002z\u0002\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0001\u0001zzz\u0002\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0001\u0001\u0001\u0001\u0001zzzzzzzzzzzzzzz\u0003\u0003\u0003\u0001\u0003\u0003\u0003\u0003zzzzzzzz\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003zzz\u0003\u0003zz\u0002\u0002\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003zzzz");
    }

    public static int getEuropeanDigit(int i) {
        if (i > 122 && i < 65313) {
            return -1;
        }
        int i2 = 65;
        if (i < 65) {
            return -1;
        }
        if ((i > 90 && i < 97) || i > 65370) {
            return -1;
        }
        if (i > 65338 && i < 65345) {
            return -1;
        }
        if (i > 122) {
            return i <= 65338 ? (i + 10) - 65313 : (i + 10) - 65345;
        }
        int i3 = i + 10;
        if (i > 90) {
            i2 = 97;
        }
        return i3 - i2;
    }

    public static final int getMask(int i) {
        return 1 << i;
    }

    /* access modifiers changed from: private */
    public static final int getNumericTypeValue(int i) {
        return i >> 6;
    }

    /* access modifiers changed from: private */
    public static final int ntvGetType(int i) {
        if (i == 0) {
            return 0;
        }
        if (i < 11) {
            return 1;
        }
        return i < 21 ? 2 : 3;
    }

    /* access modifiers changed from: private */
    public static final CodePointTrie makeTrie(String str) {
        byte[] bArr = new byte[str.length()];
        for (int i = 0; i < bArr.length; i++) {
            char charAt = str.charAt(i);
            if (charAt == 0) {
                charAt = 'z';
            } else if (charAt == 'z') {
                charAt = 0;
            }
            bArr[i] = (byte) charAt;
        }
        return CodePointTrie.fromBinary(null, null, ByteBuffer.wrap(bArr));
    }

    public final int getProperty(int i) {
        return this.m_trie_.get(i);
    }

    public int getAdditional(int i, int i2) {
        if (i2 >= this.m_additionalColumnsCount_) {
            return 0;
        }
        return this.m_additionalVectors_[this.m_additionalTrie_.get(i) + i2];
    }

    public VersionInfo getAge(int i) {
        int additional = getAdditional(i, 0) >> 24;
        return VersionInfo.getInstance((additional >> 4) & 15, additional & 15, 0, 0);
    }

    static {
        try {
            INSTANCE = new UCharacterProperty();
        } catch (IOException e) {
            throw new MissingResourceException(e.getMessage(), "", "");
        }
    }

    /* access modifiers changed from: private */
    public static final boolean isgraphPOSIX(int i) {
        return (getMask(UCharacter.getType(i)) & (((GC_CC_MASK | GC_CS_MASK) | GC_CN_MASK) | GC_Z_MASK)) == 0;
    }

    private class BinaryProperty {
        int column;
        int mask;

        BinaryProperty(int i, int i2) {
            this.column = i;
            this.mask = i2;
        }

        BinaryProperty(int i) {
            this.column = i;
            this.mask = 0;
        }

        /* access modifiers changed from: package-private */
        public final int getSource() {
            if (this.mask == 0) {
                return this.column;
            }
            return 2;
        }

        /* access modifiers changed from: package-private */
        public boolean contains(int i) {
            return (this.mask & UCharacterProperty.this.getAdditional(i, this.column)) != 0;
        }
    }

    private class CaseBinaryProperty extends BinaryProperty {
        int which;

        CaseBinaryProperty(int i) {
            super(4);
            this.which = i;
        }

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.BinaryProperty
        public boolean contains(int i) {
            return UCaseProps.INSTANCE.hasBinaryProperty(i, this.which);
        }
    }

    private class NormInertBinaryProperty extends BinaryProperty {
        int which;

        NormInertBinaryProperty(int i, int i2) {
            super(i);
            this.which = i2;
        }

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.BinaryProperty
        public boolean contains(int i) {
            return Norm2AllModes.getN2WithImpl(this.which - 37).isInert(i);
        }
    }

    public boolean hasBinaryProperty(int i, int i2) {
        if (i2 < 0 || 65 <= i2) {
            return false;
        }
        return this.binProps[i2].contains(i);
    }

    public int getType(int i) {
        return getProperty(i) & 31;
    }

    private class IntProperty {
        int column;
        int mask;
        int shift;

        IntProperty(int i, int i2, int i3) {
            this.column = i;
            this.mask = i2;
            this.shift = i3;
        }

        IntProperty(int i) {
            this.column = i;
            this.mask = 0;
        }

        /* access modifiers changed from: package-private */
        public final int getSource() {
            if (this.mask == 0) {
                return this.column;
            }
            return 2;
        }

        /* access modifiers changed from: package-private */
        public int getValue(int i) {
            return (UCharacterProperty.this.getAdditional(i, this.column) & this.mask) >>> this.shift;
        }

        /* access modifiers changed from: package-private */
        public int getMaxValue(int i) {
            return (UCharacterProperty.this.getMaxValues(this.column) & this.mask) >>> this.shift;
        }
    }

    private class BiDiIntProperty extends IntProperty {
        BiDiIntProperty() {
            super(5);
        }

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.IntProperty
        public int getMaxValue(int i) {
            return UBiDiProps.INSTANCE.getMaxValue(i);
        }
    }

    private class CombiningClassIntProperty extends IntProperty {
        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.IntProperty
        public int getMaxValue(int i) {
            return 255;
        }

        CombiningClassIntProperty(int i) {
            super(i);
        }
    }

    private class NormQuickCheckIntProperty extends IntProperty {
        int max;
        int which;

        NormQuickCheckIntProperty(int i, int i2, int i3) {
            super(i);
            this.which = i2;
            this.max = i3;
        }

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.IntProperty
        public int getValue(int i) {
            return Norm2AllModes.getN2WithImpl(this.which - 4108).getQuickCheck(i);
        }

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.IntProperty
        public int getMaxValue(int i) {
            return this.max;
        }
    }

    public int getIntPropertyValue(int i, int i2) {
        if (i2 < 4096) {
            if (i2 < 0 || i2 >= 65) {
                return 0;
            }
            return this.binProps[i2].contains(i) ? 1 : 0;
        } else if (i2 < 4121) {
            return this.intProps[i2 - 4096].getValue(i);
        } else {
            if (i2 == 8192) {
                return getMask(getType(i));
            }
            return 0;
        }
    }

    public int getIntPropertyMaxValue(int i) {
        if (i < 4096) {
            return (i < 0 || i >= 65) ? -1 : 1;
        }
        if (i < 4121) {
            return this.intProps[i - 4096].getMaxValue(i);
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public final int getSource(int i) {
        if (i < 0) {
            return 0;
        }
        if (i < 65) {
            return this.binProps[i].getSource();
        }
        if (i < 4096) {
            return 0;
        }
        if (i < 4121) {
            return this.intProps[i - 4096].getSource();
        }
        if (i < 16384) {
            if (i == 8192 || i == 12288) {
                return 1;
            }
            return 0;
        } else if (i < 16398) {
            switch (i) {
                case 16384:
                    return 2;
                case 16385:
                    return 5;
                case 16386:
                case 16388:
                case 16390:
                case 16391:
                case 16392:
                case 16393:
                case 16394:
                case 16396:
                    return 4;
                case 16387:
                case 16389:
                case 16395:
                    return 3;
                default:
                    return 0;
            }
        } else if (i != 28672) {
            return 0;
        } else {
            return 2;
        }
    }

    public int getMaxValues(int i) {
        if (i == 0) {
            return this.m_maxBlockScriptValue_;
        }
        if (i != 2) {
            return 0;
        }
        return this.m_maxJTGValue_;
    }

    public int digit(int i) {
        int numericTypeValue = getNumericTypeValue(getProperty(i)) - 1;
        if (numericTypeValue <= 9) {
            return numericTypeValue;
        }
        return -1;
    }

    public double getUnicodeNumericValue(int i) {
        int i2;
        int i3;
        int i4;
        double d;
        int i5;
        int numericTypeValue = getNumericTypeValue(getProperty(i));
        if (numericTypeValue == 0) {
            return -1.23456789E8d;
        }
        if (numericTypeValue < 11) {
            i5 = numericTypeValue - 1;
        } else if (numericTypeValue < 21) {
            i5 = numericTypeValue - 11;
        } else if (numericTypeValue < 176) {
            i5 = numericTypeValue - 21;
        } else {
            if (numericTypeValue < 480) {
                i2 = (numericTypeValue >> 4) - 12;
                i3 = (numericTypeValue & 15) + 1;
            } else if (numericTypeValue < 768) {
                int i6 = (numericTypeValue & 31) + 2;
                double d2 = (double) ((numericTypeValue >> 5) - 14);
                while (i6 >= 4) {
                    d2 *= 10000.0d;
                    i6 -= 4;
                }
                if (i6 == 1) {
                    d = 10.0d;
                } else if (i6 == 2) {
                    d = 100.0d;
                } else if (i6 != 3) {
                    return d2;
                } else {
                    d = 1000.0d;
                }
                return d2 * d;
            } else if (numericTypeValue < 804) {
                int i7 = (numericTypeValue >> 2) - 191;
                int i8 = (numericTypeValue & 3) + 1;
                if (i8 == 1) {
                    i7 *= 60;
                } else if (i8 != 2) {
                    if (i8 == 3) {
                        i4 = 216000;
                    } else if (i8 == 4) {
                        i4 = 12960000;
                    }
                    i7 *= i4;
                } else {
                    i7 *= 3600;
                }
                return (double) i7;
            } else if (numericTypeValue >= 828) {
                return -1.23456789E8d;
            } else {
                int i9 = numericTypeValue - 804;
                i2 = ((i9 & 3) * 2) + 1;
                i3 = 20 << (i9 >> 2);
            }
            return ((double) i2) / ((double) i3);
        }
        return (double) i5;
    }

    private UCharacterProperty() {
        if (this.binProps.length != 65) {
            throw new ICUException("binProps.length!=UProperty.BINARY_LIMIT");
        } else if (this.intProps.length == 25) {
            ByteBuffer requiredData = ICUBinary.getRequiredData("uprops.icu");
            this.m_unicodeVersion_ = ICUBinary.readHeaderAndDataVersion(requiredData, 1431335535, new IsAcceptable());
            int i = requiredData.getInt();
            requiredData.getInt();
            requiredData.getInt();
            int i2 = requiredData.getInt();
            int i3 = requiredData.getInt();
            this.m_additionalColumnsCount_ = requiredData.getInt();
            int i4 = requiredData.getInt();
            int i5 = requiredData.getInt();
            requiredData.getInt();
            requiredData.getInt();
            this.m_maxBlockScriptValue_ = requiredData.getInt();
            this.m_maxJTGValue_ = requiredData.getInt();
            ICUBinary.skipBytes(requiredData, 16);
            this.m_trie_ = Trie2_16.createFromSerialized(requiredData);
            int i6 = (i - 16) * 4;
            int serializedLength = this.m_trie_.getSerializedLength();
            if (serializedLength <= i6) {
                ICUBinary.skipBytes(requiredData, i6 - serializedLength);
                ICUBinary.skipBytes(requiredData, (i2 - i) * 4);
                if (this.m_additionalColumnsCount_ > 0) {
                    this.m_additionalTrie_ = Trie2_16.createFromSerialized(requiredData);
                    int i7 = (i3 - i2) * 4;
                    int serializedLength2 = this.m_additionalTrie_.getSerializedLength();
                    if (serializedLength2 <= i7) {
                        ICUBinary.skipBytes(requiredData, i7 - serializedLength2);
                        this.m_additionalVectors_ = ICUBinary.getInts(requiredData, i4 - i3, 0);
                    } else {
                        throw new IOException("uprops.icu: not enough bytes for additional-properties trie");
                    }
                }
                int i8 = (i5 - i4) * 2;
                if (i8 > 0) {
                    this.m_scriptExtensions_ = ICUBinary.getChars(requiredData, i8, 0);
                    return;
                }
                return;
            }
            throw new IOException("uprops.icu: not enough bytes for main trie");
        } else {
            throw new ICUException("intProps.length!=(UProperty.INT_LIMIT-UProperty.INT_START)");
        }
    }

    private static final class IsAcceptable implements ICUBinary.Authenticate {
        private IsAcceptable() {
        }

        @Override // android.icu.impl.ICUBinary.Authenticate
        public boolean isDataVersionAcceptable(byte[] bArr) {
            return bArr[0] == 7;
        }
    }

    public UnicodeSet addPropertyStarts(UnicodeSet unicodeSet) {
        Iterator it = this.m_trie_.iterator();
        while (it.hasNext()) {
            Trie2.Range range = (Trie2.Range) it.next();
            if (range.leadSurrogate) {
                break;
            }
            unicodeSet.add(range.startCodePoint);
        }
        unicodeSet.add(9);
        unicodeSet.add(10);
        unicodeSet.add(14);
        unicodeSet.add(28);
        unicodeSet.add(32);
        unicodeSet.add(133);
        unicodeSet.add(134);
        unicodeSet.add(127);
        unicodeSet.add(8202);
        unicodeSet.add(8208);
        unicodeSet.add(8298);
        unicodeSet.add(8304);
        unicodeSet.add(65279);
        unicodeSet.add(65280);
        unicodeSet.add(160);
        unicodeSet.add(161);
        unicodeSet.add(8199);
        unicodeSet.add(8200);
        unicodeSet.add(8239);
        unicodeSet.add(8240);
        unicodeSet.add(12295);
        unicodeSet.add(12296);
        unicodeSet.add(19968);
        unicodeSet.add(19969);
        unicodeSet.add(20108);
        unicodeSet.add(20109);
        unicodeSet.add(19977);
        unicodeSet.add(19978);
        unicodeSet.add(22235);
        unicodeSet.add(22236);
        unicodeSet.add(20116);
        unicodeSet.add(20117);
        unicodeSet.add(20845);
        unicodeSet.add(20846);
        unicodeSet.add(19971);
        unicodeSet.add(19972);
        unicodeSet.add(20843);
        unicodeSet.add(20844);
        unicodeSet.add(20061);
        unicodeSet.add(20062);
        unicodeSet.add(97);
        unicodeSet.add(123);
        unicodeSet.add(65);
        unicodeSet.add(91);
        unicodeSet.add(65345);
        unicodeSet.add(65371);
        unicodeSet.add(65313);
        unicodeSet.add(65339);
        unicodeSet.add(103);
        unicodeSet.add(71);
        unicodeSet.add(65351);
        unicodeSet.add(65319);
        unicodeSet.add(8288);
        unicodeSet.add(65520);
        unicodeSet.add(65532);
        unicodeSet.add(917504);
        unicodeSet.add(921600);
        unicodeSet.add(847);
        unicodeSet.add(848);
        return unicodeSet;
    }

    public void upropsvec_addPropertyStarts(UnicodeSet unicodeSet) {
        if (this.m_additionalColumnsCount_ > 0) {
            Iterator it = this.m_additionalTrie_.iterator();
            while (it.hasNext()) {
                Trie2.Range range = (Trie2.Range) it.next();
                if (!range.leadSurrogate) {
                    unicodeSet.add(range.startCodePoint);
                } else {
                    return;
                }
            }
        }
    }

    public UnicodeSet ulayout_addPropertyStarts(int i, UnicodeSet unicodeSet) {
        CodePointTrie codePointTrie;
        switch (i) {
            case 12:
                codePointTrie = InPCTrie.INSTANCE;
                break;
            case 13:
                codePointTrie = InSCTrie.INSTANCE;
                break;
            case 14:
                codePointTrie = VoTrie.INSTANCE;
                break;
            default:
                throw new IllegalStateException();
        }
        CodePointMap.Range range = new CodePointMap.Range();
        for (int i2 = 0; codePointTrie.getRange(i2, null, range); i2 = range.getEnd() + 1) {
            unicodeSet.add(i2);
        }
        return unicodeSet;
    }
}
