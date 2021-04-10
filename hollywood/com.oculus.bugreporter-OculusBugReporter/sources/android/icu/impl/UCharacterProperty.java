package android.icu.impl;

import android.icu.impl.ICUBinary;
import android.icu.impl.Normalizer2Impl;
import android.icu.impl.Trie2;
import android.icu.lang.UCharacter;
import android.icu.lang.UProperty;
import android.icu.lang.UScript;
import android.icu.text.Normalizer2;
import android.icu.text.UTF16;
import android.icu.text.UnicodeSet;
import android.icu.util.CodePointMap;
import android.icu.util.CodePointTrie;
import android.icu.util.ICUException;
import android.icu.util.VersionInfo;
import android.support.v4.view.MotionEventCompat;
import dalvik.system.VMDebug;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.MissingResourceException;

public final class UCharacterProperty {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int AGE_SHIFT_ = 24;
    private static final int ALPHABETIC_PROPERTY_ = 8;
    private static final int ASCII_HEX_DIGIT_PROPERTY_ = 7;
    private static final int BLOCK_MASK_ = 130816;
    private static final int BLOCK_SHIFT_ = 8;
    private static final int CGJ = 847;
    private static final int CR = 13;
    private static final int DASH_PROPERTY_ = 1;
    private static final String DATA_FILE_NAME_ = "uprops.icu";
    private static final int DATA_FORMAT = 1431335535;
    private static final int DECOMPOSITION_TYPE_MASK_ = 31;
    private static final int DEFAULT_IGNORABLE_CODE_POINT_PROPERTY_ = 19;
    private static final int DEL = 127;
    private static final int DEPRECATED_PROPERTY_ = 20;
    private static final int DIACRITIC_PROPERTY_ = 10;
    private static final int EAST_ASIAN_MASK_ = 917504;
    private static final int EAST_ASIAN_SHIFT_ = 17;
    private static final int EXTENDER_PROPERTY_ = 11;
    private static final int FIGURESP = 8199;
    private static final int FIRST_NIBBLE_SHIFT_ = 4;
    private static final int GCB_MASK = 992;
    private static final int GCB_SHIFT = 5;
    private static final int GC_CC_MASK = getMask(15);
    private static final int GC_CN_MASK = getMask(0);
    private static final int GC_CS_MASK = getMask(18);
    private static final int GC_ZL_MASK = getMask(13);
    private static final int GC_ZP_MASK = getMask(14);
    private static final int GC_ZS_MASK = getMask(12);
    private static final int GC_Z_MASK = ((GC_ZS_MASK | GC_ZL_MASK) | GC_ZP_MASK);
    private static final int GRAPHEME_BASE_PROPERTY_ = 26;
    private static final int GRAPHEME_EXTEND_PROPERTY_ = 13;
    private static final int GRAPHEME_LINK_PROPERTY_ = 14;
    private static final int HAIRSP = 8202;
    private static final int HEX_DIGIT_PROPERTY_ = 6;
    private static final int HYPHEN_PROPERTY_ = 2;
    private static final int IDEOGRAPHIC_PROPERTY_ = 9;
    private static final int IDS_BINARY_OPERATOR_PROPERTY_ = 15;
    private static final int IDS_TRINARY_OPERATOR_PROPERTY_ = 16;
    private static final int ID_CONTINUE_PROPERTY_ = 25;
    private static final int ID_START_PROPERTY_ = 24;
    private static final int INHSWAP = 8298;
    public static final UCharacterProperty INSTANCE;
    private static final int LAST_NIBBLE_MASK_ = 15;
    public static final char LATIN_CAPITAL_LETTER_I_WITH_DOT_ABOVE_ = 304;
    public static final char LATIN_SMALL_LETTER_DOTLESS_I_ = 305;
    public static final char LATIN_SMALL_LETTER_I_ = 'i';
    private static final int LB_MASK = 66060288;
    private static final int LB_SHIFT = 20;
    private static final int LOGICAL_ORDER_EXCEPTION_PROPERTY_ = 21;
    private static final int MATH_PROPERTY_ = 5;
    static final int MY_MASK = 30;
    private static final int NBSP = 160;
    private static final int NL = 133;
    private static final int NNBSP = 8239;
    private static final int NOMDIG = 8303;
    private static final int NONCHARACTER_CODE_POINT_PROPERTY_ = 12;
    private static final int NTV_BASE60_START_ = 768;
    private static final int NTV_DECIMAL_START_ = 1;
    private static final int NTV_DIGIT_START_ = 11;
    private static final int NTV_FRACTION20_START_ = 804;
    private static final int NTV_FRACTION_START_ = 176;
    private static final int NTV_LARGE_START_ = 480;
    private static final int NTV_NONE_ = 0;
    private static final int NTV_NUMERIC_START_ = 21;
    private static final int NTV_RESERVED_START_ = 828;
    private static final int NUMERIC_TYPE_VALUE_SHIFT_ = 6;
    private static final int PATTERN_SYNTAX = 29;
    private static final int PATTERN_WHITE_SPACE = 30;
    private static final int PREPENDED_CONCATENATION_MARK = 31;
    private static final int PROPS_2_EMOJI = 28;
    private static final int PROPS_2_EMOJI_COMPONENT = 27;
    private static final int PROPS_2_EMOJI_MODIFIER = 30;
    private static final int PROPS_2_EMOJI_MODIFIER_BASE = 31;
    private static final int PROPS_2_EMOJI_PRESENTATION = 29;
    private static final int PROPS_2_EXTENDED_PICTOGRAPHIC = 26;
    private static final int QUOTATION_MARK_PROPERTY_ = 3;
    private static final int RADICAL_PROPERTY_ = 17;
    private static final int RLM = 8207;
    private static final int SB_MASK = 1015808;
    private static final int SB_SHIFT = 15;
    public static final int SCRIPT_MASK_ = 255;
    public static final int SCRIPT_X_MASK = 12583167;
    public static final int SCRIPT_X_WITH_COMMON = 4194304;
    public static final int SCRIPT_X_WITH_INHERITED = 8388608;
    public static final int SCRIPT_X_WITH_OTHER = 12582912;
    public static final int SRC_BIDI = 5;
    public static final int SRC_CASE = 4;
    public static final int SRC_CASE_AND_NORM = 7;
    public static final int SRC_CHAR = 1;
    public static final int SRC_CHAR_AND_PROPSVEC = 6;
    public static final int SRC_COUNT = 15;
    public static final int SRC_INPC = 12;
    public static final int SRC_INSC = 13;
    public static final int SRC_NAMES = 3;
    public static final int SRC_NFC = 8;
    public static final int SRC_NFC_CANON_ITER = 11;
    public static final int SRC_NFKC = 9;
    public static final int SRC_NFKC_CF = 10;
    public static final int SRC_NONE = 0;
    public static final int SRC_PROPSVEC = 2;
    public static final int SRC_VO = 14;
    private static final int S_TERM_PROPERTY_ = 27;
    private static final int TAB = 9;
    private static final int TERMINAL_PUNCTUATION_PROPERTY_ = 4;
    public static final int TYPE_MASK = 31;
    private static final int UNIFIED_IDEOGRAPH_PROPERTY_ = 18;
    private static final int U_A = 65;
    private static final int U_F = 70;
    private static final int U_FW_A = 65313;
    private static final int U_FW_F = 65318;
    private static final int U_FW_Z = 65338;
    private static final int U_FW_a = 65345;
    private static final int U_FW_f = 65350;
    private static final int U_FW_z = 65370;
    private static final int U_Z = 90;
    private static final int U_a = 97;
    private static final int U_f = 102;
    private static final int U_z = 122;
    private static final int VARIATION_SELECTOR_PROPERTY_ = 28;
    private static final int WB_MASK = 31744;
    private static final int WB_SHIFT = 10;
    private static final int WHITE_SPACE_PROPERTY_ = 0;
    private static final int WJ = 8288;
    private static final int XID_CONTINUE_PROPERTY_ = 23;
    private static final int XID_START_PROPERTY_ = 22;
    private static final int ZWNBSP = 65279;
    private static final int[] gcbToHst = {0, 0, 0, 0, 1, 0, 4, 5, 3, 2};
    BinaryProperty[] binProps = {new BinaryProperty(1, 256), new BinaryProperty(1, 128), new BinaryProperty(5) {
        /* class android.icu.impl.UCharacterProperty.AnonymousClass1 */

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.BinaryProperty
        public boolean contains(int c) {
            return UBiDiProps.INSTANCE.isBidiControl(c);
        }
    }, new BinaryProperty(5) {
        /* class android.icu.impl.UCharacterProperty.AnonymousClass2 */

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.BinaryProperty
        public boolean contains(int c) {
            return UBiDiProps.INSTANCE.isMirrored(c);
        }
    }, new BinaryProperty(1, 2), new BinaryProperty(1, 524288), new BinaryProperty(1, 1048576), new BinaryProperty(1, 1024), new BinaryProperty(1, 2048), new BinaryProperty(8) {
        /* class android.icu.impl.UCharacterProperty.AnonymousClass3 */

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.BinaryProperty
        public boolean contains(int c) {
            Normalizer2Impl impl = Norm2AllModes.getNFCInstance().impl;
            return impl.isCompNo(impl.getNorm16(c));
        }
    }, new BinaryProperty(1, 67108864), new BinaryProperty(1, 8192), new BinaryProperty(1, 16384), new BinaryProperty(1, 64), new BinaryProperty(1, 4), new BinaryProperty(1, 33554432), new BinaryProperty(1, 16777216), new BinaryProperty(1, 512), new BinaryProperty(1, 32768), new BinaryProperty(1, 65536), new BinaryProperty(5) {
        /* class android.icu.impl.UCharacterProperty.AnonymousClass4 */

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.BinaryProperty
        public boolean contains(int c) {
            return UBiDiProps.INSTANCE.isJoinControl(c);
        }
    }, new BinaryProperty(1, 2097152), new CaseBinaryProperty(22), new BinaryProperty(1, 32), new BinaryProperty(1, 4096), new BinaryProperty(1, 8), new BinaryProperty(1, 131072), new CaseBinaryProperty(27), new BinaryProperty(1, 16), new BinaryProperty(1, 262144), new CaseBinaryProperty(30), new BinaryProperty(1, 1), new BinaryProperty(1, 8388608), new BinaryProperty(1, 4194304), new CaseBinaryProperty(34), new BinaryProperty(1, 134217728), new BinaryProperty(1, VMDebug.KIND_THREAD_EXT_ALLOCATED_OBJECTS), new NormInertBinaryProperty(8, 37), new NormInertBinaryProperty(9, 38), new NormInertBinaryProperty(8, 39), new NormInertBinaryProperty(9, 40), new BinaryProperty(11) {
        /* class android.icu.impl.UCharacterProperty.AnonymousClass5 */

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.BinaryProperty
        public boolean contains(int c) {
            return Norm2AllModes.getNFCInstance().impl.ensureCanonIterData().isCanonSegmentStarter(c);
        }
    }, new BinaryProperty(1, VMDebug.KIND_THREAD_EXT_ALLOCATED_BYTES), new BinaryProperty(1, VMDebug.KIND_THREAD_EXT_FREED_OBJECTS), new BinaryProperty(6) {
        /* class android.icu.impl.UCharacterProperty.AnonymousClass6 */

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.BinaryProperty
        public boolean contains(int c) {
            return UCharacter.isUAlphabetic(c) || UCharacter.isDigit(c);
        }
    }, new BinaryProperty(1) {
        /* class android.icu.impl.UCharacterProperty.AnonymousClass7 */

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.BinaryProperty
        public boolean contains(int c) {
            return c <= 159 ? c == 9 || c == 32 : UCharacter.getType(c) == 12;
        }
    }, new BinaryProperty(1) {
        /* class android.icu.impl.UCharacterProperty.AnonymousClass8 */

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.BinaryProperty
        public boolean contains(int c) {
            return UCharacterProperty.isgraphPOSIX(c);
        }
    }, new BinaryProperty(1) {
        /* class android.icu.impl.UCharacterProperty.AnonymousClass9 */

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.BinaryProperty
        public boolean contains(int c) {
            return UCharacter.getType(c) == 12 || UCharacterProperty.isgraphPOSIX(c);
        }
    }, new BinaryProperty(1) {
        /* class android.icu.impl.UCharacterProperty.AnonymousClass10 */

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.BinaryProperty
        public boolean contains(int c) {
            if ((c > 102 || c < 65 || (c > 70 && c < 97)) && ((c < UCharacterProperty.U_FW_A || c > UCharacterProperty.U_FW_f || (c > UCharacterProperty.U_FW_F && c < UCharacterProperty.U_FW_a)) && UCharacter.getType(c) != 9)) {
                return false;
            }
            return true;
        }
    }, new CaseBinaryProperty(49), new CaseBinaryProperty(50), new CaseBinaryProperty(51), new CaseBinaryProperty(52), new CaseBinaryProperty(53), new BinaryProperty(7) {
        /* class android.icu.impl.UCharacterProperty.AnonymousClass11 */

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.BinaryProperty
        public boolean contains(int c) {
            String nfd = Norm2AllModes.getNFCInstance().impl.getDecomposition(c);
            if (nfd != null) {
                c = nfd.codePointAt(0);
                if (Character.charCount(c) != nfd.length()) {
                    c = -1;
                }
            } else if (c < 0) {
                return false;
            }
            if (c < 0) {
                return true ^ UCharacter.foldCase(nfd, true).equals(nfd);
            }
            UCaseProps csp = UCaseProps.INSTANCE;
            UCaseProps.dummyStringBuilder.setLength(0);
            if (csp.toFullFolding(c, UCaseProps.dummyStringBuilder, 0) >= 0) {
                return true;
            }
            return false;
        }
    }, new CaseBinaryProperty(55), new BinaryProperty(10) {
        /* class android.icu.impl.UCharacterProperty.AnonymousClass12 */

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.BinaryProperty
        public boolean contains(int c) {
            Normalizer2Impl kcf = Norm2AllModes.getNFKC_CFInstance().impl;
            String src = UTF16.valueOf(c);
            StringBuilder dest = new StringBuilder();
            kcf.compose(src, 0, src.length(), false, true, new Normalizer2Impl.ReorderingBuffer(kcf, dest, 5));
            return !Normalizer2Impl.UTF16Plus.equal(dest, src);
        }
    }, new BinaryProperty(2, VMDebug.KIND_THREAD_EXT_ALLOCATED_OBJECTS), new BinaryProperty(2, VMDebug.KIND_THREAD_EXT_ALLOCATED_BYTES), new BinaryProperty(2, VMDebug.KIND_THREAD_EXT_FREED_OBJECTS), new BinaryProperty(2, Integer.MIN_VALUE), new BinaryProperty(2, 134217728), new BinaryProperty(2) {
        /* class android.icu.impl.UCharacterProperty.AnonymousClass13 */

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.BinaryProperty
        public boolean contains(int c) {
            return 127462 <= c && c <= 127487;
        }
    }, new BinaryProperty(1, Integer.MIN_VALUE), new BinaryProperty(2, 67108864)};
    IntProperty[] intProps = {new BiDiIntProperty() {
        /* class android.icu.impl.UCharacterProperty.AnonymousClass14 */

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.IntProperty
        public int getValue(int c) {
            return UBiDiProps.INSTANCE.getClass(c);
        }
    }, new IntProperty(0, BLOCK_MASK_, 8), new CombiningClassIntProperty(8) {
        /* class android.icu.impl.UCharacterProperty.AnonymousClass15 */

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.IntProperty
        public int getValue(int c) {
            return Normalizer2.getNFDInstance().getCombiningClass(c);
        }
    }, new IntProperty(2, 31, 0), new IntProperty(0, 917504, 17), new IntProperty(1) {
        /* class android.icu.impl.UCharacterProperty.AnonymousClass16 */

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.IntProperty
        public int getValue(int c) {
            return UCharacterProperty.this.getType(c);
        }

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.IntProperty
        public int getMaxValue(int which) {
            return 29;
        }
    }, new BiDiIntProperty() {
        /* class android.icu.impl.UCharacterProperty.AnonymousClass17 */

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.IntProperty
        public int getValue(int c) {
            return UBiDiProps.INSTANCE.getJoiningGroup(c);
        }
    }, new BiDiIntProperty() {
        /* class android.icu.impl.UCharacterProperty.AnonymousClass18 */

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.IntProperty
        public int getValue(int c) {
            return UBiDiProps.INSTANCE.getJoiningType(c);
        }
    }, new IntProperty(2, LB_MASK, 20), new IntProperty(1) {
        /* class android.icu.impl.UCharacterProperty.AnonymousClass19 */

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.IntProperty
        public int getValue(int c) {
            return UCharacterProperty.ntvGetType(UCharacterProperty.getNumericTypeValue(UCharacterProperty.this.getProperty(c)));
        }

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.IntProperty
        public int getMaxValue(int which) {
            return 3;
        }
    }, new IntProperty(0, 255, 0) {
        /* class android.icu.impl.UCharacterProperty.AnonymousClass20 */

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.IntProperty
        public int getValue(int c) {
            return UScript.getScript(c);
        }
    }, new IntProperty(2) {
        /* class android.icu.impl.UCharacterProperty.AnonymousClass21 */

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.IntProperty
        public int getValue(int c) {
            int gcb = (UCharacterProperty.this.getAdditional(c, 2) & UCharacterProperty.GCB_MASK) >>> 5;
            if (gcb < UCharacterProperty.gcbToHst.length) {
                return UCharacterProperty.gcbToHst[gcb];
            }
            return 0;
        }

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.IntProperty
        public int getMaxValue(int which) {
            return 5;
        }
    }, new NormQuickCheckIntProperty(8, UProperty.NFD_QUICK_CHECK, 1), new NormQuickCheckIntProperty(9, UProperty.NFKD_QUICK_CHECK, 1), new NormQuickCheckIntProperty(8, UProperty.NFC_QUICK_CHECK, 2), new NormQuickCheckIntProperty(9, UProperty.NFKC_QUICK_CHECK, 2), new CombiningClassIntProperty(8) {
        /* class android.icu.impl.UCharacterProperty.AnonymousClass22 */

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.IntProperty
        public int getValue(int c) {
            return Norm2AllModes.getNFCInstance().impl.getFCD16(c) >> 8;
        }
    }, new CombiningClassIntProperty(8) {
        /* class android.icu.impl.UCharacterProperty.AnonymousClass23 */

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.IntProperty
        public int getValue(int c) {
            return Norm2AllModes.getNFCInstance().impl.getFCD16(c) & 255;
        }
    }, new IntProperty(2, GCB_MASK, 5), new IntProperty(2, SB_MASK, 15), new IntProperty(2, WB_MASK, 10), new BiDiIntProperty() {
        /* class android.icu.impl.UCharacterProperty.AnonymousClass24 */

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.IntProperty
        public int getValue(int c) {
            return UBiDiProps.INSTANCE.getPairedBracketType(c);
        }
    }, new IntProperty(12) {
        /* class android.icu.impl.UCharacterProperty.AnonymousClass25 */

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.IntProperty
        public int getValue(int c) {
            return InPCTrie.INSTANCE.get(c);
        }

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.IntProperty
        public int getMaxValue(int which) {
            return 14;
        }
    }, new IntProperty(13) {
        /* class android.icu.impl.UCharacterProperty.AnonymousClass26 */

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.IntProperty
        public int getValue(int c) {
            return InSCTrie.INSTANCE.get(c);
        }

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.IntProperty
        public int getMaxValue(int which) {
            return 35;
        }
    }, new IntProperty(14) {
        /* class android.icu.impl.UCharacterProperty.AnonymousClass27 */

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.IntProperty
        public int getValue(int c) {
            return VoTrie.INSTANCE.get(c);
        }

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.IntProperty
        public int getMaxValue(int which) {
            return 3;
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

    static {
        try {
            INSTANCE = new UCharacterProperty();
        } catch (IOException e) {
            throw new MissingResourceException(e.getMessage(), "", "");
        }
    }

    /* access modifiers changed from: private */
    public static final CodePointTrie makeTrie(String data) {
        byte[] bytes = new byte[data.length()];
        for (int i = 0; i < bytes.length; i++) {
            char c = data.charAt(i);
            if (c == 0) {
                c = 'z';
            } else if (c == 'z') {
                c = 0;
            }
            bytes[i] = (byte) c;
        }
        return CodePointTrie.fromBinary(null, null, ByteBuffer.wrap(bytes));
    }

    private static final class InPCTrie {
        static final CodePointTrie INSTANCE = UCharacterProperty.makeTrie("3irTBzý\u0002r\u000b\u0002zzzzzz@zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzÀzÿz?\u0001~\u0001¾\u0001~\u0001þ\u0001>\u0002~\u0002¼\u0002ü\u0002<\u0003{\u0003>\u0002»\u0003û\u00039\u0004w\u0004­\u0004á\u0004!\u00051\u0005q\u0005\u0005Ù\u0005\u0019\u0006V\u0006·\u0002Æ\u0002Ò\u0002Æ\u0002í\u0002zz\u0010z z0z@zPz`zpzzz\u0010z z0zzz\u0010z z0zzz\u0010z z0zzz\u0010z z0zzz\u0010z z0zzz\u0010z z0zzz\u0010z z0zzz\u0010z z0zzz z°zÀzÐzàzðzÿz\u000f\u0001\u001f\u0001/\u0001?\u0001O\u0001_\u0001o\u0001~\u0001\u0001\u0001®\u0001¾\u0001Î\u0001Þ\u0001î\u0001~\u0001\u0001\u0001®\u0001þ\u0001\u000e\u0002\u001e\u0002.\u0002>\u0002N\u0002^\u0002n\u0002~\u0002\u0002\u0002®\u0002¼\u0002Ì\u0002Ü\u0002ì\u0002ü\u0002\f\u0003\u001c\u0003,\u0003<\u0003L\u0003\\\u0003l\u0003{\u0003\u0003\u0003«\u0003>\u0002N\u0002^\u0002n\u0002»\u0003Ë\u0003Û\u0003ë\u0003û\u0003\u000b\u0004\u001b\u0004+\u00049\u0004I\u0004Y\u0004i\u0004w\u0004\u0004\u0004§\u0004­\u0004½\u0004Í\u0004Ý\u0004á\u0004ñ\u0004\u0001\u0005\u0011\u0005!\u00051\u0005A\u0005Q\u00051\u0005A\u0005Q\u0005a\u0005q\u0005\u0005\u0005¡\u0005\u0005©\u0005¹\u0005É\u0005Ù\u0005é\u0005ù\u0005\t\u0006\u0019\u0006)\u00069\u0006I\u0006V\u0006f\u0006v\u0006\u0006zzzz\u0006\u0006zz©\u0006¸\u0006Ç\u0006Õ\u0006å\u0006zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzó\u0006zzó\u0006zz\u0001\u0007zz\u0001\u0007zzzzzz\u000b\u0007\u001b\u0007)\u0007zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz9\u0007I\u0007zzzzzzzzzzzzzzY\u0007h\u0007zzzzzzr\u0007zzzzzz~\u0007\u0007\u0007zzzzzzzzzzzzzzzz«\u0007zzzz·\u0007Ç\u0007zzÌ\u0007,\u0005zzzÜ\u0007zzzzzzê\u0007û\u0003zzzzú\u0007\u0007\bzzzzzzzzzzzzzzzzzz\u0017\b'\b5\bzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz³\u0002?\bzzL\bzzzzzzzzzz\u0001\u0001zzzzX\bd\bzzt\b\bzzzz\bzz \bû\u0003zzzzzzzzz°\bÀ\bzz¹\u0002zzzzÇ\bÖ\bã\bzzzzñ\bzzzzzz\u0001\t½\u0002zz\u0011\tQ\u0001zzzzzzzzzzzzzzzzzzzzzzzzzzzz!\tzz0\tzzzz@\tzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzP\tzzzzX\tf\tzzzzzzzzzzzv\tzzzzzzzz-\u0005zz\t\tË\u0003zzzzY\u0006zzzzz\t®\tzzzzzz»\tË\tzzzzzzzzzzzzzzzzzzqzÛ\tzzÿzzzzzæ\tö\tO\u0001\u0004\n+\u0005zzzzzzzzzzzzzzzz\t\u0014\no\u0001zzzzzzzzzz$\n3\nzzzzzzzzzzzzzzzzzzzzzzzzzzë\u0002C\nãz\u0014\u0002zzzzzzS\n¾\u0002zzzzzzzzzzc\ns\nzzzzzzzzzz{\n\nzzzzzzzzzzzzzzzzzzzzzzzzzz\n¦\nzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzµ\nzzzzÂ\nzzÑ\nzzzzÝ\nç\nzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzë\u0002÷\nzzzzzzzzzz\u0007\u000b\u000f\u000b\u001e\u000bzzzzzzzzzzzzzz-\u000b<\u000bzzzzzzD\u000bT\u000bzzzzzzzzzzzzzzzzzzzzzzzzzzzzzza\u000bzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzEzMzMzMz]z}zz½zÝz\u0002z\u0002zìz\n\u0001)\u0001I\u0001\u0002z\u0002z\u0002z\u0002z\u0002z\u0002z\u0002z\u0002z\u0002z\u0002z\u0002z\u0002z\u0002z\u0002z\u0002z\u0002z\u0002z\u0002z\u0002z\u0002z\u0002z\u0002z\u0002z\u0002z\u0002z\u0002z\u0002z\u0002z\u0002z\u0002z\u0002z\u0002zi\u0001\u0001\u0002z\u0002z\u0002z\u0002z\u0002z\u0002z\u0002z\u0002z\u0002z\u0002z¨\u0001\u0002z\u0002zÈ\u0001æ\u0001\u0003\u0002!\u0002?\u0002_\u0002}\u0002\u0002zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\b\b\b\u0007zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\b\u0007\u0001z\u0007\u0004\u0007\u0001\u0001\u0001\u0001\b\b\b\b\u0007\u0007\u0007\u0007\u0001\u0004\u0007z\b\u0001\b\b\b\u0001\u0001zzzzzzzzzz\u0001\u0001zzzzzzzzzzzzzzzzzzzzzzzzzzzz\b\u0007\u0007zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\u0001z\u0007\u0004\u0007\u0001\u0001\u0001\u0001zz\u0004\u0004zz\u0005\u0005\u0001zzzzzzzzz\u0007zzzzzzzzzz\u0001\u0001zzzzzzzzzzzzzzzzzzzzzzzzzz\bz\b\b\u0007zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\u0001z\u0007\u0004\u0007\u0001\u0001zzzz\b\bzz\b\b\u0001zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\b\bzzz\u0001zzzzzzzzzz\u0007\u0001\u0001\u0001\u0001\bz\b\b\rz\u0007\u0007\u0001zzzzzzzzzzzzzzzzzzzz\u0001\u0001zzzzzzzzzzzzzzzzzzzzzz\b\b\b\b\b\bz\b\u0007\u0007zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\u0001z\u0007\b\u0007\u0001\u0001\u0001\u0001zz\u0004\u000bzz\u0005\f\u0001zzzzzzzz\b\rzzzzzzzzzz\u0001\u0001zzzzzzzzzzzzzzzzzzzzzzzzzzzz\bzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\u0007\u0007\b\u0007\u0007zzz\u0004\u0004\u0004z\u0005\u0005\u0005\bzzzzzzzzz\u0007zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\b\u0007\u0007\u0007\bzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\b\b\u0007\u0007\u0007\u0007z\b\b\tz\b\b\b\bzzzzzzz\b\u0001zzzzzzzzzzz\u0001\u0001zzzzzzzzzzzzzzzzzzzzzzzzzzzz\r\u0007\u0007\u0007\u0007z\b\r\rz\r\r\b\bzzzzzzz\u0007\u0007zzzzzzzzzzz\u0001\u0001zzzzzzzzzzzzzzzzzzzzzzzzzzzz\b\b\u0007\u0007zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\b\bz\u0007\u0007\u0007\u0001\u0001z\u0004\u0004\u0004z\u0005\u0005\u0005\bzzzzzzzzz\u0007zzzzzzzzzz\u0001\u0001zzzzzzzzzzzzzzzzzzzzzzzzzzzz\u0007\u0007zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\bzzzz\u0007\u0007\u0007\b\b\u0001z\u0001z\u0007\u0004\u000b\u0004\u0005\f\u0005\u0007zzzzzzzzzzzzzzzzzz\u0007\u0007zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\u0007\b\u0007\u0007\b\b\b\b\u0001\u0001\u0001zzzzz\u000e\u000e\u000e\u000e\u000e\u0007z\b\b\b\b\b\b\b\bzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\u0007\b\u0007\u0007\b\b\b\b\u0001\u0001z\b\u0001zzz\u000e\u000e\u000e\u000e\u000ezzz\b\b\b\b\b\bzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\u0001\u0001zzzzzzzzzzzzzzzzzzzzzzzzzzz\u0001z\u0001z\bzzzz\u0007\u0004zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\u0001\b\t\u0001\u0001\t\t\t\t\b\b\b\b\b\u0007\b\t\b\b\u0001z\b\bzzzzz\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001z\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001zzzzzz\u0001zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\u0007\u0007\b\b\u0001\u0004\b\b\b\b\b\u0001\u0007z\b\u0007z\u0001\u0001zzzzzz\u0007\u0007\u0001\u0001zzzz\u0001\u0001z\u0007\u0007\u0007zz\u0007\u0007\u0007\u0007\u0007\u0007\u0007zz\b\b\b\bzzzzzzzzzzz\u0001\u0007\u0004\b\b\u0007\u0007\u0007\u0007\u0007\u0007\u0001z\u0007zzzzzzzzzz\u0007\u0007\u0007\bzz\b\u0001\u0001zzzzzzzzzzz\b\u0001zzzzzzzzzzzz\u0007\b\b\b\b\u0001\u0001\u0001\u000b\f\u0005\u0004\u0004\u0004\u0005\u0005\b\u0007\u0007\b\b\b\b\b\b\bz\bzzzzzzzzz\bzz\b\b\u0001\u0007\u0007\r\r\b\b\u0007\u0007\u0007zzzz\u0007\u0007\u0001\u0007\u0007\u0007\u0007\u0007\u0007\u0001\b\u0001zzzz\u0007\u0007\u0007\u0007\u0007\u000e\u000e\u000e\u0007\u0007\u000e\u0007\u0007\u0007\u0007\u0007zzzzzzz\u0007\u0007zzzzzzz\b\u0001\u0004\u0007\bzzzzz\u0004\u0001\u0007\b\b\b\u0001\u0001\u0001\u0001z\u0007\b\u0007\u0007\b\b\b\b\u0001\u0001\b\u0001\u0007\u0004\u0004\u0004\b\b\b\b\b\b\b\b\b\bzz\u0001\b\b\b\b\u0007zzzzzzzzzzz\b\u0007\b\b\u0001\u0001\u0001\u0003\t\n\u0004\u0004\u0005\u0005\b\r\u0007zzzzzzzzzzz\b\u0001\b\b\bz\u0007\u0001\u0001\b\u0001\u0004\u0007\b\b\u0007z\u0001\u0001zzzzzz\b\u0007\b\b\u0007\u0007\u0007\b\u0007\bzzzz\u0007\u0007\u0007\u0004\u0004\u000b\u0007\u0007\u0001\b\b\b\b\u0004\u0004\b\u0001zzzzzzzz\b\b\bz\u0006\u0001\u0001\u0001\u0001\u0001\b\b\u0001\u0001\u0001\u0001\b\u0007\u0006\u0006\u0006\u0006\u0006\u0006\u0006zzzz\u0001zzzz\bzz\u0007zzzzzzzz\bzzzz\bzzzz\u0007\u0007\u0001\b\u0007zzzzzzzz\u0007\u0007\u0007\u0007\u0007\u0007\u0007\u0007\u0007\u0007\u0007\u0007\u0001\bzzzzzzzzzz\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\bzzzzzzzzzzzzz\bzzzzzzzzzzz\u0001\u0001\u0001zzzzzzz\u0001\u0001\u0001\b\u0001\u0001\u0001\u0001\bzzz\b\u0007\u0007\b\b\u0001\u0001\u0004\u0004\b\u0007\u0007\u0002\u0003zzzzzzzzzzzzzzz\b\b\b\b\u0001\b\u0004\b\u0001\u0007\u0004\u0001\u0001zzzzzzzzz\bzzzzzzzz\b\u0007zzzzzzzzzzz\u0007\b\u0007zz\b\u0007\b\b\u0001\u000e\u000e\b\b\u000e\u0007\u000e\u000e\u0007\b\bzzzzzzzzzzz\u0004\u0001\b\u0004\u0007zzz\u0007\u0007\b\u0007\u0007\u0001\u0007\u0007z\u0007\u0001zz\u0006\u0001\u0001z\b\u0006zzzzz\u0001\u0001\u0001\bzzzzzzzz\b\u0001\u0001zzzzz\u0007\b\u0007zzzzzzzzzzzzz\b\b\b\b\u0001\u0001\u0001\u0001\b\b\b\b\bzzzzzzzzz\u0007\u0004\u0007\u0001\u0001\b\b\u0007\u0007\u0001\u0001zzzzzzz\b\b\b\u0001\u0001\u0004\b\t\t\b\u0001\u0001z\bzzzzzzzzzzz\u0007\u0004\u0007\u0001\u0001\u0001\u0001\u0001\u0001\b\b\b\r\u0007zzzzzzzz\u0001z\b\u0001zzzzzzzzzzzz\u0007\u0007\u0007\u0001\b\b\r\r\b\u0007\b\bzzzzzz\bz\u0007\u0004\u0007\u0001\u0001\b\b\b\b\u0001\u0001zzzzzzzzzzz\u0001\u0001z\u0007\u0007\b\u0007\u0007\u0007\u0007zz\u0004\u0004zz\u0005\u0005\u0007zz\u0007\u0007zz\b\b\b\b\b\b\bzzz\u0007\u0007\u0001\b\b\u0007\u0001zzzzzzzzz\u0007\u0004\u0007\u0001\u0001\u0001\u0001\u0001\u0001\u0004\b\u000b\u0005\u0007\u0005\b\u0007\u0001\u0001zzzzzzzzzzzz\u0004\u0007\u0001\u0001\u0001\u0001zz\u0004\u000b\u0005\f\b\b\u0007\u0001\u0007\u0007\u0007\u0001\u0001\u0001\u0001\u0001\u0001\b\b\u0007\u0007\b\u0007\u0001zzzzzzzzzzz\b\u0007\b\u0004\u0007\u0001\u0001\b\b\b\b\u0007\u0001zzzzzzzzzzzzz\u0001z\b\u0007\u0007\b\b\u0001\u0001\u0004\b\u0001\b\b\bzzzzzzzzzzzz\u0007\u0004\u0007\u0001\u0001\u0001\b\b\b\b\b\u0007\u0001\u0001zzzzz\b\u0001\u0001\b\b\b\b\b\b\u0001zzzzz\u0001\u0001\b\b\b\b\u0007z\u0001\u0001\u0001\u0001z\b\u0001\u0001\b\b\b\u0007\u0007\u0001\u0001\u0001zzzzzzzzzz\u0001\u0001\u0001\u0001\u0001\u0001\b\u0007\bzzzzzzz\b\b\u0001\u0001\u0001\u0001\u0001z\b\b\b\b\b\b\u0007\u0001zz\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001z\u0007\u0001\u0001\u0001\u0001\u0001\u0001\u0004\u0001\b\u0007\b\bzzzzzzzzz\b\b\b\b\b\u0001zzz\bz\b\bz\b\b\u0001\b\u0001zz\u0001zzzzzzzzzz\u0007\u0007\u0007\u0007\u0007z\b\bz\u0007\u0007\b\u0007zzzzzzzzz\b\u0001\u0004\u0007zzzzzzzzzz");

        private InPCTrie() {
        }
    }

    private static final class InSCTrie {
        static final CodePointTrie INSTANCE = UCharacterProperty.makeTrie("3irTBzB\u0003x\u000f\u0004z@zzzz@z`zz@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@zÔz\u0012\u0001R\u0001\u0001Ï\u0001\r\u0002L\u0002\u0002Ê\u0002\b\u0003F\u0003\u0003Ä\u0003\u0002\u0004A\u0004\u0004¿\u0004ý\u0004=\u0005}\u0005¼\u0005ü\u0005;\u0006{\u0006\u0006Û\u0006\u001b\u0007X\u0007ø\u0002\u000b\u0003\u0017\u0003\u000b\u00032\u0003zz\u0010z z0z@zPz`zpz`zpzzzz¤z´zÄz@zPz`zpz@zPz`zpz@zPz`zpz@zPz`zpz@zPz`zpz@zPz`zpz@zPz`zpz@zPz`zpzÔzäzôz\u0004\u0001\u0012\u0001\"\u00012\u0001B\u0001R\u0001b\u0001r\u0001\u0001\u0001 \u0001°\u0001À\u0001Ï\u0001ß\u0001ï\u0001ÿ\u0001\r\u0002\u001d\u0002-\u0002=\u0002L\u0002\\\u0002l\u0002|\u0002\u0002\u0002ª\u0002º\u0002Ê\u0002Ú\u0002ê\u0002ú\u0002\b\u0003\u0018\u0003(\u00038\u0003F\u0003V\u0003f\u0003v\u0003\u0003\u0003¤\u0003´\u0003Ä\u0003Ô\u0003ä\u0003ô\u0003\u0002\u0004\u0012\u0004\"\u00042\u0004A\u0004Q\u0004a\u0004q\u0004\u0004\u0004\u0004¯\u0004¿\u0004Ï\u0004ß\u0004ï\u0004ý\u0004\r\u0005\u001d\u0005-\u0005=\u0005M\u0005]\u0005m\u0005}\u0005\u0005\u0005­\u0005¼\u0005Ì\u0005Ü\u0005ì\u0005ü\u0005\f\u0006\u001c\u0006,\u0006;\u0006K\u0006[\u0006k\u0006{\u0006\u0006\u0006«\u0006\u0006«\u0006»\u0006Ë\u0006Û\u0006ë\u0006û\u0006\u000b\u0007\u001b\u0007+\u0007;\u0007K\u0007X\u0007h\u0007x\u0007\u0007ézéz\u0007£\u0007³\u0007Ã\u0007Ò\u0007á\u0007ï\u0007ÿ\u0007@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z\u000f\b\u001d\bæz\u001d\bæz-\b\u000f\b=\bézézM\bY\bc\br\b0z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z\bl\u0001\b¢\b-\u0002éz²\bÂ\bézézt\u0003Ò\bá\b0z@z@zézñ\bézéz\u0001\t\u000e\t\u001e\t*\t0z0z@z@z@z@z@z@z:\tæzézJ\tV\t0z@z@zf\tézu\t\tézéz\t¥\tézézµ\tÂ\tÒ\t@z@z@z@z@z@z@z@zâ\tð\tþ\t@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z\b\n\u0014\n$\n@z@z@z@z@zZ\u00072\n@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@ztz@z@z@zB\nézO\n@zéz_\nm\n|\nÖzçzéz\n\n0z¨\n¶\nÆ\nézÔ\nézä\nó\n@z@z\u0003\u000bézéz\u0012\u000b\u00020z\"\u000b2\u000bãzéz\bB\u000bR\u000b0zéza\u000bézézézq\u000b\u000b@z\u000b¡\u000b@z@z@z@z@z@z@z@z@z@z@z@z±\u000bÁ\u000bÎ\u000b0zÞ\u000bî\u000bézø\u000b1z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z\b\fæzéz\b\u0018\f&\f0\f@\fP\fézéz`\f@z@z@z@zp\féz\b\f\f \féz­\fÕzèzéz½\fÍ\f0zº\u00065zázë\u0003\bÝ\f@z@z@z@zí\fm\u0001ü\fßzéz\f\r\u001c\r0z,\rb\u0001r\u0001<\r\b\u0003L\r\\\rí\t@z@z@z@z@z@z@z@zÛzézézl\r\u0000\r\r@z@z\rézéz\u001f\t©\r0z@z@z@z@z@z@z@z@z@z@zÛzézÿz¹\rÉ\rÑ\r@z@zÛzézézá\rñ\r0z@z@zßzéz\u0001\u000e\u000e\u000e0z@z@z@zéz\u001e\u000e.\u000e>\u000e@z@z@z@z@z@z@z@z@z@z@z@zßzéz\bN\u000e@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z^\u000eézézk\u000e{\u000e\u000eézéz\u000e¡\u000e@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z±\u000eézÿzÁ\u000eÑ\u000e»\u0006á\u000eU\u0005ézï\u000e+\u0007ÿ\u000e@z@z@z@z\u000f\u000fézéz\u001e\u000f.\u000f0z>\u000fézJ\u000fW\u000f0z@z@z@z@z@z@z@z@z@z@z@z@z@z@zézg\u000f@z@z@z@z@z@z@z@z@z@z@z@z@z@z@z@zEzUzUzUzezz¥zÅzåz\u0004z\u0004zõz\u0014\u00014\u0001T\u0001\u0004zt\u0001\u0004z}\u0001\u0004z\u0004z\u0004z\u0004z\u0004z\u0004z\u0004z\u0004z\u0004z\u0004z\u0004z\u0004z\u0004z\u0004z\u0004z\u0004z\u0004z\u0004z\u0004z\u0004z\u0004z\u0004z\u0004z\u0004z\u0004z\u0004z\u0004z\u0004z\u0004z\u0004z\u0004z\u0004z\u0001½\u0001\u0004z\u0004z\u0004z\u0004z\u0004z\u0004z\u0004z\u0004z\u0004z\u0004zÝ\u0001\u0004z\u0004zý\u0001\u001d\u0002=\u0002]\u0002}\u0002\u0002½\u0002Ø\u0002zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\fzz\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\fzzzzzzzzzzzzzzzzz\u001c\u001czzzzzzzzzzzzzzzzzzzzzzz\fzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\u0002\u0002\u0002 #################\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\"\"\u0017\u0001\"\"\"\"\"\"\"\"\"\"\"\"\"\u001f\"\"z\u0004\u0004zz\"\"\"\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005##\"\"zz\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018zz######\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\f\u0002\u0002 z########zz##zz##\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005z\u0005\u0005\u0005\u0005\u0005\u0005\u0005z\u0005zzz\u0005\u0005\u0005\u0005zz\u0017\u0001\"\"\"\"\"zz\"\"zz\"\"\u001f\u0006zzzzzzzz\"zzzz\u0005\u0005z\u0005##\"\"zz\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0005\u0005zzzzzzzzzz\u0002z\u001cz\u0002\u0002 z######zzzz##zz##\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005z\u0005\u0005\u0005\u0005\u0005\u0005\u0005z\u0005\u0005z\u0005\u0005z\u0005\u0005zz\u0017z\"\"\"zzzz\"\"zz\"\"\u001fzzz\u0004zzzzzzz\u0005\u0005\u0005\u0005z\u0005zzzzzzz\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0002\u0012\f\fz\u000bzzzzzzzzzz\u0002\u0002 z#########z###z##\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005z\u0005\u0005\u0005\u0005\u0005\u0005\u0005z\u0005\u0005z\u0005\u0005\u0005\u0005\u0005zz\u0017\u0001\"\"\"\"\"\"z\"\"\"z\"\"\u001fzzzzzzzzzzzzzzzzzz##\"\"zz\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018zzzzzzzzz\u0005\u0004\u0004\u0004\u0017\u0017\u0017z\u0002\u0002 z########zz##zz##\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005z\u0005\u0005\u0005\u0005\u0005\u0005\u0005z\u0005\u0005z\u0005\u0005\u0005\u0005\u0005zz\u0017\u0001\"\"\"\"\"zz\"\"zz\"\"\u001fzzzzzzzz\"\"zzzz\u0005\u0005z\u0005##\"\"zz\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018z\u0005zzzzzzzzzzzzzz\u0002\u0015z######zzz###z###\u0005zzz\u0005\u0005z\u0005z\u0005\u0005zzz\u0005\u0005zzz\u0005\u0005\u0005zzz\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005zzzz\"\"\"zzz\"\"\"z\"\"\"\u001fzzzzzzzzz\"zzzzzzzzzzzzzz\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018zzzzzzzzzzzzzzzz\u0002\u0002\u0002 \u0002########z###z###\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005z\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005zzz\u0001\"\"\"\"\"z\"\"\"z\"\"\"\u001fzzzzzzz\"\"z\u0005\u0005\u0005zzzzz##\"\"zz\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018zzzzzzzzzzzzzzzz\u0002\u0002 z########z###z###\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005z\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005z\u0005\u0005\u0005\u0005\u0005zz\u0017\u0001\"\"\"\"\"z\"\"\"z\"\"\"\u001fzzzzzzz\"\"zzzzzzz\u0005z##\"\"zz\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018z\u0011\u0011zzzzzzzzzzzzz\u0002\u0002\u0002 z########z###z###\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u001a\u001a\u0001\"\"\"\"\"z\"\"\"z\"\"\"\u001f\rzzzzz\u0006\u0006\u0006\"zzzzzzz###\"\"zz\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018zzzzzzzzzz\u0006\u0006\u0006\u0006\u0006\u0006zz\u0002 z##################zzz\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005z\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005z\u0005zz\u0005\u0005\u0005\u0005\u0005\u0005\u0005zzz\u001fzzzz\"\"\"\"\"\"z\"z\"\"\"\"\"\"\"\"zzzzzz\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018zz\"\"zzzzzzzzzzzz\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005z\"\"\"\"\"\"\"\"\"\"\u001azzzzz\"\"\"\"\"\"z\"\u001e\u001e\u001e\u001e\n\u0002\u001az\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\u0005\u0005z\u0005zz\u0005\u0005z\u0005zz\u0005zzzzzz\u0005\u0005\u0005\u0005z\u0005\u0005\u0005\u0005\u0005\u0005\u0005z\u0005\u0005\u0005z\u0005z\u0005zz\u0005\u0005z\u0005\u0005z\"\"\"\"\"\"\"\"\"\"z\"\u000b\u000bzz\"\"\"\"\"zzz\u001e\u001e\u001e\u001ez\u0002zz\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018zz\u0005\u0005\u0005\u0005zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018z\u001cz\u001cz\u0017zzzzzz\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005z\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005zzzz\"\"\"\"\"\"\"\"\"\"\"\"\"\u0002 \"\"\u0002\u0002\u001a\u0001zz\b\b\b\b\b\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000fz\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000fzzzzzz\u001czzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\u0005##########\"\"\"\"\"\"\u0002\u001e \u0013\u001a\u000b\u000b\u000b\u000b\u0005\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018z\fzz\fz\u0005\u0005####\"\"\"\"\u0005\u0005\u0005\u0005\u000b\u000b\u0005\"\u001e\u001e\u0005\u0005\"\"\u001e\u001e\u001e\u001e\u001e\u0005\u0005\"\"\"\"\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u000b\"\"\"\"\u001e\u001e\u001e\u001e\u001e\u001e\u001e\u0005\u001e\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u001e\u001e\"\"zz###\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005z\u0005\u0005\"\"\u001azzzzzzzzzzz\u0005\u0005\"\"zzzzzzzzzzzz\u0005z\"\"zzzzzzzzzzzz\u0005\u0005\u0005#############zz\"\"\"\"\"\"\"\"\"\"\u0002 \"\u001b\u001b\u001c\u0010\n\u001c\u001c\u001a\u0013\u001czzzzzzzz\u0001\u001czz\f\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\"\"\"\"\"\"\"\"\"\u000f\u000f\u000fzzzz\u0007\u0007\u0002\u0007\u0007\u0007\u0007\u0007\u0007\u0007\"\u001czzzz\u0005\u0005\u0005!!!!!!!!!!!zz\u001d\u001d\u001d\u001d\u001dzzzzzzzzzzz\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\u0007\u0007\u0007\u0007\u0007\u0007\u0007\u001e\u001ezzzzzz\u0005\u0005\u0005\u0005\u0005\u0005\u0005\"\"\"\"\"zzzz\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005###\u0005\u0005\u000b\u000b\u000f\u0007\u0007\t\u000f\u000f\u000f\u000fz\u0013\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\u0002\u001e\u001e\u001e\u001e\u001e\u001a\u001c\u001czz\u001c\u0002\u0002\u0002\u0010 ###########\u0005\u0005\u0005\u0005\u0017\"\"\"\"\"\"\"\"\"\"\"\u001f\u0005\u0005\u0005\u0005\u0005\u0005\u0005zzzz\u0002\u0010 #######\u0005\u0005\u0005\u0005\u0005\u0005\u000f\u000f\u000f\"\"\"\"\"\"\u001a\u0013\u000f\u000f\u0005\u0005\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0001\u0005\u0005\u0005\u0007\u0007\u0005\u0005\u0005\u0005##\u0017\"\"\"\"\"\"\"\"\"\u0007\u0007\u001a\u001azzzzzzzzzzzz\u0005\u0005\u0005\u0005\u000f\u000f\"\"\"\"\"\"\"\u0007\u0007\u0007\u0007\u0002\u0002\u001c\u0017zzzzzzzz\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018zzz\u0005\u0005\u0005\u0004\u0004\u0004z\u0004\u0004\u0004\u0004\u0004\u0004\u0004\u0004\u0004\u0004\u0004\u0004zzzzzzzzzzzzzz  \u0004\u0011\u0011\u0004\u0004\u0004zzzzzzzzzzz\u001czzzzzzzzzzzz\u0016\u0014zz\f\f\f\f\fzzzzzzzzzzz\u001c\u001c\u001czzzzzzzzzzz##z###\u001a\u0005\u0005\u0005\u0005\u0002\u0005\u0005\u0005\u0005\"\"\"\"\"zzzzzzzz\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005!!\u0005\u0005\u0005\u0005!\u000f\u000f\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u000f\u0005\u0002zzzzzzzzzzzz\u0005\u0005\u0005\u0005\u000b\"\"\"\"\"\"\"\"\"\"\"\u001f\u0002zzzzzzzzzz\u0004\u0004\u0004\u0004\u0004\u0004\u0004\u0004\u0004\u0004\u0004\u0004\u0004\u0004\u0004\u0004\u0002\u0002zzzzzzzzzz#\"\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0005\u0005\u0005\u0005\u0005\u0005!!!!!!!!!\u001e\u001e\u001ezz\u0005\u0005\u0005\u0005\u0005\u0005\u0005\"\"\"\"\"\"\"\"\u0007\u0007\u0007\u001azzzzzzzzzzzz\u0002\u0002\u0010 #####\u0005\u0005\u0005###\u0005\u0005\u0005\u0017\"\"\"\"\"\"\"\"\"\u000f\u000b\u000b\u0005\u0005\u0005\u0005\u0005\"z\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0005\u0005\u0005\u0005\u0005z\"\"\"\u000b\u000b\u000b\u000bzzzzzzzzz\u0007\u0007\u0007\u0007\u0007\u0007\u0007\u0007\u0007\u0007\u0007\u0007\u0007\u0007zz\u0005\u0005\u0005\f\f\fzzz\u0005\u001e\u001e\u001e\u0005\u0005\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\u001e\u001d\u001e\u001dzzzzzzzzzzzzz##\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\"\"\"\"\"zzzzz \u0013zzzzzzzzz\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005##\u0005#\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0007\u0007\u0007\u0007\u0007\"\"\"\"\"\"\"\"z\u001e\u001azz\u0005\"\"\"z\"\"zzzzz\"\"\u0002 \u0005\u0005\u0005\u0005z\u0005\u0005\u0005z\u0005\u0005\u0005\u0005\u0005\u0005\u0005zz\u0017\u0017\u0017zzzz\u0013\u0002\u0002 \u0011\u0011###########\"\"\"\"\"\"\u001fzzzzzzzzz\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018zzzzzzzzzzzzzzz\u0019\u0002\u0002 ##########\u0005\u0005\u0005\"\"\"\"\"\"\"\"\"\u001f\u0017zzzzz\u0002\u0002 ####\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\"\"\"\u0013\u001az\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018zzzz\u0005\"\"zzzzzzzzz!!!!!\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0017zzzzzzzzzzzz\u0005\u0005\u0005\"\"\"\"\"\"\"\"\"\"\"\"\"\u001f\u0001\u000e\u000ezzzzz\u001c\u0017\"\"zzz\"\"\"\"\u0002\u001f\u0017\u0012zzzzzz\u0004z####\u0005\u0005\u0005z\u0005z\u0005\u0005\u0005\u0005z\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005zzzzzzz\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0002\"\"\"\"\"\"\"\"\"\u0017\u001azzzzz\u0002\u0002\u0002 z########zz#\u0005z\u0005\u0005z\u0005\u0005\u0005\u0005\u0005z\u0017\u0017\u0001\"\"zzzzzzz\"zzzzzz\u0002\u0002##\"\"zz\u0004\u0004\u0004\u0004\u0004\u0004\u0004zzz\u0005\u0005\u0005\u0005\u0005\"\"\"\"\"\"\"\"\"\"\"\u001f\u0002\u0002 \u0017\u0001zzzzzzzz\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018zzzz\u001cz##############\u0005\u0002 \u001f\u0017\u0001zzzzzzzzzzz\"\"\"\"\"\"zz\"\"\"\"\u0002\u0002 \u001f\u0017zzzzzzzzzzzzzzz####\"\"zz\"\"\"\"\"\"\"\"\"\"\"\"\"\u0002 \u001f\"zzzzzzzzzzzzzzz\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0002 \"\"\"\"\"\"\u001f\u0017zzzzzzzz\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005zz\u000b\u000b\u000b\"\"\"\"\"\"\"\"\"\"\"\u001azzzz\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018zzzz\"\"\"\"\"\"\"\u0002 \u001f\u0017zzzzz#\"\"\"\"\"\"\"\"\"\"\u0005\u0005\u0005\u0005\u0005\u001c\u001a\u0002\u0002\u0002\u0002 \u000e\u000b\u000b\u000b\u000b\fzzzzz\fz\u0013zzzzzzzz#\"\"\"\"\"\"\"\"\"\"\"\u0005\u0005\u0005\u0005zz\u000e\u000e\u000e\u000e\u0007\u0007\u0007\u0007\u0007\u0007\u0002 \u0012\u0013zzz\u0001zz#########z####\u0005\u0005\"\"\"\"\"\"\"z\"\"\"\"\u0002\u0002 \u001f\u0001zzzzzzzzzzzzzzz\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018\u0018zzz\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\u000f\"\"\"\"\"\u0002\u0002zzzzzzzzz#######z##z#\u0005\u0005\u0005\u0005\"\"\"\"\"\"zzz\"z\"\"z\"\u0002 \u0017\"\u001a\u0013\r\u000bzzzzzzzz######z##z##\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\u0005\"\"\"\"\"z\"\"\u0002 \u0013zzzzzzzz\u0005\u0005\f\"\"\"\"zzzzzzzzzz");

        private InSCTrie() {
        }
    }

    private static final class VoTrie {
        static final CodePointTrie INSTANCE = UCharacterProperty.makeTrie("3irTBzL\u0004<\u0003\fzzz\bzz@zYzzzzzzzzzzzzzzzzÐzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz;\u0003U\u0003c\u0003y\u0003\u0003·\u0003Ò\u0003ì\u0003U\u0003U\u0003U\u0003\f\u0004U\u0003U\u0003U\u0003\f\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004,\u0004U\u0003U\u0003U\u0003\f\u0004U\u0003U\u0003U\u0003\f\u0004zz\u0010z z0z@zPz`zpzYzizyzzz¨z¸zÈzzz\u0010z z0zzz\u0010z z0zzz\u0010z z0zzz\u0010z z0zÐzàzðzz\u0001zz\u0010z z0zzz\u0010z z0zzz\u0010z z0zzz\u0010z z0zzz\u0010z z0zzz\u0010z z0zzz\u0010z z0zzz\u0010z z0zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz©zz\u001e\u0001,\u0001®zªzzzzzzzzzzzzz\u0003\u0001<\u0001zzL\u0001X\u0001f\u0001\u000b\u0001u\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0001zzzzzzzzzzzzzzrzzzözzzzzzzzzzzzzzzzzzzzzzzzz\u0001\u0010\u0001\u0001zzzzzzzz\u0003\u0001\u0010\u0001\u0015\u0001zzìz¨\u0001¶\u0001\u000e\u0001\u0010\u0001\u0010\u0001Æ\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001zzzzzzzzzzzzzzzzzzzz\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0016\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0018\u0001\n\u0001\u0010\u0001Ò\u0001zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\u000e\u0001\u0010\u0001zzzz\u0016\u0001zzzzzzzzzz\b\u0001\u0010\u0001â\u0001\u0014\u0001\u0010\u0001zzzzzzzzzzzzzzzz\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001ñ\u0001ÿ\u0001\u0010\u0001\u000e\u0002\u001d\u0002\u0010\u0001*\u0002\u0010\u00017\u0002F\u0002V\u0002\u0010\u0001*\u0002\u0010\u00017\u0002a\u0002\u0010\u0001\u0010\u0001n\u0002\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001~\u0002\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001~\u0002~\u0002~\u0002~\u0002~\u0002\u0002\u0010\u0001\u0002\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\u0010\u0001\u0010\u0001zzzzzzzzzzzzzzzz\u0010\u0001zz\u0010\u0001\u0017\u0001\u0002ª\u0002zzzzzzzzzzzzzzzzzzº\u0002É\u0002\u0010\u0001Ù\u0002\u0010\u0001é\u0002ø\u0002zzzzzzzzzzzzzz\b\u0003\u0018\u0003zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\u0010\u0001\u0010\u0001zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001zzzzzzzz\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001zzzzzzzzzzzzzzzzzzzzzzzzzzzz\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001zzzzzzzzzzzzzzzz(\u0003\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0012\u0001zz¨z¨z¨z¨z¨z¨zÈz\fzèzz\u0001\u0015\u0001\fz\fz\fz4\u0001S\u0001r\u0001\u0001\fz«\u0001\fzË\u0001ë\u0001\u000b\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002ûz\fzC\u0002\fz#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002\fz\fz\fz\fz#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002øz\fzb\u0002\fz\fz\fz\fz\u0002\fz\fz\fz\fz\fz\u0002\fz\fzýz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz#\u0002#\u0002¹\u0002\fz\fz\fz\fz\fz#\u0002z\u0001\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz¼\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002øz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fzÚ\u0002øz\fz\fz\fz\fz\fz\fz\fz\fz#\u0002ú\u0002\fz\fz#\u0002ýz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz#\u0002\u001a\u0003#\u0002#\u0002Èzµ\u0002\fz\fz#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002#\u0002\u001b\u0003\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fz\fzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\u0003z\u0003zzzz\u0003zz\u0003zzzzzzzzzz\u0003\u0003\u0003zzzzzzzzzzzzzzzzzzzzzzz\u0003zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\u0003zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz\u0003\u0003zzzzzzzzzzzzzzzzzzzz\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003zzzzzzzzz\u0003\u0003zzz\u0003zzzz\u0003\u0003\u0003zzzzzz\u0003z\u0003\u0003\u0003zzzzzzzzzzz\u0003\u0003z\u0003\u0003\u0003\u0003\u0003\u0003\u0003zzzzz\u0003\u0003z\u0003\u0003zzzzzz\u0003\u0003\u0003\u0003z\u0003z\u0003z\u0003zzzz\u0003zzzzz\u0003\u0003\u0003\u0003\u0003\u0003z\u0003\u0003z\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003zz\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003zzzz\u0003\u0003\u0003\u0003\u0003\u0001\u0001\u0003zzzz\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003z\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003zzzz\u0003\u0003\u0003z\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003zzzzzzzzzzzz\u0003\u0003z\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0002\u0002\u0003\u0003\u0003\u0003\u0003\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0003\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0002\u0003\u0002\u0003\u0002\u0003\u0002\u0003\u0002\u0003\u0003\u0003\u0003\u0003\u0003\u0002\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0002\u0003\u0002\u0003\u0002\u0003\u0003\u0003\u0003\u0003\u0003\u0002\u0003\u0003\u0003\u0003\u0003\u0002\u0002\u0003\u0003\u0003\u0003\u0002\u0002\u0003\u0003\u0003\u0001\u0002\u0003\u0002\u0003\u0002\u0003\u0002\u0003\u0002\u0003\u0003\u0003\u0003\u0003\u0003\u0002\u0002\u0003\u0003\u0003\u0003\u0003\u0001\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0002\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0002\u0002\u0002\u0002\u0002\u0002\u0002\u0002\u0002\u0002\u0002\u0002\u0002\u0002\u0002\u0002\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0002\u0002\u0002\u0002\u0002\u0003\u0003\u0003\u0003\u0003z\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0003\u0003zzzz\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003z\u0002\u0003\u0003\u0003\u0003\u0003\u0003\u0001\u0001\u0003\u0003\u0002z\u0002\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0001\u0001zzz\u0002\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0001\u0001\u0001\u0001\u0001zzzzzzzzzzzzzzz\u0003\u0003\u0003\u0001\u0003\u0003\u0003\u0003zzzzzzzz\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003zzz\u0003\u0003zz\u0002\u0002\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003zzzz");

        private VoTrie() {
        }
    }

    public final int getProperty(int ch) {
        return this.m_trie_.get(ch);
    }

    public int getAdditional(int codepoint, int column) {
        if (column >= this.m_additionalColumnsCount_) {
            return 0;
        }
        return this.m_additionalVectors_[this.m_additionalTrie_.get(codepoint) + column];
    }

    public VersionInfo getAge(int codepoint) {
        int version = getAdditional(codepoint, 0) >> 24;
        return VersionInfo.getInstance((version >> 4) & 15, version & 15, 0, 0);
    }

    /* access modifiers changed from: private */
    public static final boolean isgraphPOSIX(int c) {
        return (getMask(UCharacter.getType(c)) & (((GC_CC_MASK | GC_CS_MASK) | GC_CN_MASK) | GC_Z_MASK)) == 0;
    }

    private class BinaryProperty {
        int column;
        int mask;

        BinaryProperty(int column2, int mask2) {
            this.column = column2;
            this.mask = mask2;
        }

        BinaryProperty(int source) {
            this.column = source;
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
        public boolean contains(int c) {
            return (UCharacterProperty.this.getAdditional(c, this.column) & this.mask) != 0;
        }
    }

    private class CaseBinaryProperty extends BinaryProperty {
        int which;

        CaseBinaryProperty(int which2) {
            super(4);
            this.which = which2;
        }

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.BinaryProperty
        public boolean contains(int c) {
            return UCaseProps.INSTANCE.hasBinaryProperty(c, this.which);
        }
    }

    private class NormInertBinaryProperty extends BinaryProperty {
        int which;

        NormInertBinaryProperty(int source, int which2) {
            super(source);
            this.which = which2;
        }

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.BinaryProperty
        public boolean contains(int c) {
            return Norm2AllModes.getN2WithImpl(this.which - 37).isInert(c);
        }
    }

    public boolean hasBinaryProperty(int c, int which) {
        if (which < 0 || 65 <= which) {
            return false;
        }
        return this.binProps[which].contains(c);
    }

    public int getType(int c) {
        return getProperty(c) & 31;
    }

    private class IntProperty {
        int column;
        int mask;
        int shift;

        IntProperty(int column2, int mask2, int shift2) {
            this.column = column2;
            this.mask = mask2;
            this.shift = shift2;
        }

        IntProperty(int source) {
            this.column = source;
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
        public int getValue(int c) {
            return (UCharacterProperty.this.getAdditional(c, this.column) & this.mask) >>> this.shift;
        }

        /* access modifiers changed from: package-private */
        public int getMaxValue(int which) {
            return (UCharacterProperty.this.getMaxValues(this.column) & this.mask) >>> this.shift;
        }
    }

    private class BiDiIntProperty extends IntProperty {
        BiDiIntProperty() {
            super(5);
        }

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.IntProperty
        public int getMaxValue(int which) {
            return UBiDiProps.INSTANCE.getMaxValue(which);
        }
    }

    private class CombiningClassIntProperty extends IntProperty {
        CombiningClassIntProperty(int source) {
            super(source);
        }

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.IntProperty
        public int getMaxValue(int which) {
            return 255;
        }
    }

    private class NormQuickCheckIntProperty extends IntProperty {
        int max;
        int which;

        NormQuickCheckIntProperty(int source, int which2, int max2) {
            super(source);
            this.which = which2;
            this.max = max2;
        }

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.IntProperty
        public int getValue(int c) {
            return Norm2AllModes.getN2WithImpl(this.which - 4108).getQuickCheck(c);
        }

        /* access modifiers changed from: package-private */
        @Override // android.icu.impl.UCharacterProperty.IntProperty
        public int getMaxValue(int which2) {
            return this.max;
        }
    }

    public int getIntPropertyValue(int c, int which) {
        if (which < 4096) {
            if (which < 0 || which >= 65) {
                return 0;
            }
            return this.binProps[which].contains(c) ? 1 : 0;
        } else if (which < 4121) {
            return this.intProps[which - 4096].getValue(c);
        } else {
            if (which == 8192) {
                return getMask(getType(c));
            }
            return 0;
        }
    }

    public int getIntPropertyMaxValue(int which) {
        if (which < 4096) {
            if (which < 0 || which >= 65) {
                return -1;
            }
            return 1;
        } else if (which < 4121) {
            return this.intProps[which - 4096].getMaxValue(which);
        } else {
            return -1;
        }
    }

    /* access modifiers changed from: package-private */
    public final int getSource(int which) {
        if (which < 0) {
            return 0;
        }
        if (which < 65) {
            return this.binProps[which].getSource();
        }
        if (which < 4096) {
            return 0;
        }
        if (which < 4121) {
            return this.intProps[which - 4096].getSource();
        }
        if (which < 16384) {
            if (which == 8192 || which == 12288) {
                return 1;
            }
            return 0;
        } else if (which < 16398) {
            switch (which) {
                case 16384:
                    return 2;
                case UProperty.BIDI_MIRRORING_GLYPH /*{ENCODED_INT: 16385}*/:
                    return 5;
                case 16386:
                case UProperty.LOWERCASE_MAPPING /*{ENCODED_INT: 16388}*/:
                case UProperty.SIMPLE_CASE_FOLDING /*{ENCODED_INT: 16390}*/:
                case UProperty.SIMPLE_LOWERCASE_MAPPING /*{ENCODED_INT: 16391}*/:
                case UProperty.SIMPLE_TITLECASE_MAPPING /*{ENCODED_INT: 16392}*/:
                case UProperty.SIMPLE_UPPERCASE_MAPPING /*{ENCODED_INT: 16393}*/:
                case UProperty.TITLECASE_MAPPING /*{ENCODED_INT: 16394}*/:
                case UProperty.UPPERCASE_MAPPING /*{ENCODED_INT: 16396}*/:
                    return 4;
                case UProperty.ISO_COMMENT /*{ENCODED_INT: 16387}*/:
                case UProperty.NAME /*{ENCODED_INT: 16389}*/:
                case UProperty.UNICODE_1_NAME /*{ENCODED_INT: 16395}*/:
                    return 3;
                default:
                    return 0;
            }
        } else if (which != 28672) {
            return 0;
        } else {
            return 2;
        }
    }

    public int getMaxValues(int column) {
        if (column == 0) {
            return this.m_maxBlockScriptValue_;
        }
        if (column != 2) {
            return 0;
        }
        return this.m_maxJTGValue_;
    }

    public static final int getMask(int type) {
        return 1 << type;
    }

    public static int getEuropeanDigit(int ch) {
        if (ch > 122 && ch < U_FW_A) {
            return -1;
        }
        int i = 65;
        if (ch < 65) {
            return -1;
        }
        if ((ch > 90 && ch < 97) || ch > U_FW_z) {
            return -1;
        }
        if (ch > U_FW_Z && ch < U_FW_a) {
            return -1;
        }
        if (ch <= 122) {
            int i2 = ch + 10;
            if (ch > 90) {
                i = 97;
            }
            return i2 - i;
        } else if (ch <= U_FW_Z) {
            return (ch + 10) - U_FW_A;
        } else {
            return (ch + 10) - U_FW_a;
        }
    }

    public int digit(int c) {
        int value = getNumericTypeValue(getProperty(c)) - 1;
        if (value <= 9) {
            return value;
        }
        return -1;
    }

    public int getNumericValue(int c) {
        int ntv = getNumericTypeValue(getProperty(c));
        if (ntv == 0) {
            return getEuropeanDigit(c);
        }
        if (ntv < 11) {
            return ntv - 1;
        }
        if (ntv < 21) {
            return ntv - 11;
        }
        if (ntv < 176) {
            return ntv - 21;
        }
        if (ntv < NTV_LARGE_START_) {
            return -2;
        }
        if (ntv < 768) {
            int mant = (ntv >> 5) - 14;
            int exp = (ntv & 31) + 2;
            if (exp >= 9 && (exp != 9 || mant > 2)) {
                return -2;
            }
            int numValue = mant;
            do {
                numValue *= 10;
                exp--;
            } while (exp > 0);
            return numValue;
        } else if (ntv >= 804) {
            return ntv < NTV_RESERVED_START_ ? -2 : -2;
        } else {
            int numValue2 = (ntv >> 2) - 191;
            int exp2 = (ntv & 3) + 1;
            if (exp2 == 1) {
                return numValue2 * 60;
            }
            if (exp2 == 2) {
                return numValue2 * 3600;
            }
            if (exp2 == 3) {
                return numValue2 * 216000;
            }
            if (exp2 != 4) {
                return numValue2;
            }
            return numValue2 * 12960000;
        }
    }

    public double getUnicodeNumericValue(int c) {
        int ntv = getNumericTypeValue(getProperty(c));
        if (ntv == 0) {
            return -1.23456789E8d;
        }
        if (ntv < 11) {
            return (double) (ntv - 1);
        }
        if (ntv < 21) {
            return (double) (ntv - 11);
        }
        if (ntv < 176) {
            return (double) (ntv - 21);
        }
        if (ntv < NTV_LARGE_START_) {
            return ((double) ((ntv >> 4) - 12)) / ((double) ((ntv & 15) + 1));
        }
        if (ntv < 768) {
            int exp = (ntv & 31) + 2;
            double numValue = (double) ((ntv >> 5) - 14);
            while (exp >= 4) {
                numValue *= 10000.0d;
                exp -= 4;
            }
            if (exp == 1) {
                return numValue * 10.0d;
            }
            if (exp == 2) {
                return numValue * 100.0d;
            }
            if (exp != 3) {
                return numValue;
            }
            return numValue * 1000.0d;
        } else if (ntv < 804) {
            int numValue2 = (ntv >> 2) - 191;
            int exp2 = (ntv & 3) + 1;
            if (exp2 == 1) {
                numValue2 *= 60;
            } else if (exp2 == 2) {
                numValue2 *= 3600;
            } else if (exp2 == 3) {
                numValue2 *= 216000;
            } else if (exp2 == 4) {
                numValue2 *= 12960000;
            }
            return (double) numValue2;
        } else if (ntv >= NTV_RESERVED_START_) {
            return -1.23456789E8d;
        } else {
            int frac20 = ntv - 804;
            return ((double) (((frac20 & 3) * 2) + 1)) / ((double) (20 << (frac20 >> 2)));
        }
    }

    /* access modifiers changed from: private */
    public static final int getNumericTypeValue(int props) {
        return props >> 6;
    }

    /* access modifiers changed from: private */
    public static final int ntvGetType(int ntv) {
        if (ntv == 0) {
            return 0;
        }
        if (ntv < 11) {
            return 1;
        }
        if (ntv < 21) {
            return 2;
        }
        return 3;
    }

    private UCharacterProperty() throws IOException {
        if (this.binProps.length != 65) {
            throw new ICUException("binProps.length!=UProperty.BINARY_LIMIT");
        } else if (this.intProps.length == 25) {
            ByteBuffer bytes = ICUBinary.getRequiredData(DATA_FILE_NAME_);
            this.m_unicodeVersion_ = ICUBinary.readHeaderAndDataVersion(bytes, DATA_FORMAT, new IsAcceptable());
            int propertyOffset = bytes.getInt();
            bytes.getInt();
            bytes.getInt();
            int additionalOffset = bytes.getInt();
            int additionalVectorsOffset = bytes.getInt();
            this.m_additionalColumnsCount_ = bytes.getInt();
            int scriptExtensionsOffset = bytes.getInt();
            int reservedOffset7 = bytes.getInt();
            bytes.getInt();
            bytes.getInt();
            this.m_maxBlockScriptValue_ = bytes.getInt();
            this.m_maxJTGValue_ = bytes.getInt();
            ICUBinary.skipBytes(bytes, 16);
            this.m_trie_ = Trie2_16.createFromSerialized(bytes);
            int expectedTrieLength = (propertyOffset - 16) * 4;
            int trieLength = this.m_trie_.getSerializedLength();
            if (trieLength <= expectedTrieLength) {
                ICUBinary.skipBytes(bytes, expectedTrieLength - trieLength);
                ICUBinary.skipBytes(bytes, (additionalOffset - propertyOffset) * 4);
                if (this.m_additionalColumnsCount_ > 0) {
                    this.m_additionalTrie_ = Trie2_16.createFromSerialized(bytes);
                    int expectedTrieLength2 = (additionalVectorsOffset - additionalOffset) * 4;
                    int trieLength2 = this.m_additionalTrie_.getSerializedLength();
                    if (trieLength2 <= expectedTrieLength2) {
                        ICUBinary.skipBytes(bytes, expectedTrieLength2 - trieLength2);
                        this.m_additionalVectors_ = ICUBinary.getInts(bytes, scriptExtensionsOffset - additionalVectorsOffset, 0);
                    } else {
                        throw new IOException("uprops.icu: not enough bytes for additional-properties trie");
                    }
                }
                int numChars = (reservedOffset7 - scriptExtensionsOffset) * 2;
                if (numChars > 0) {
                    this.m_scriptExtensions_ = ICUBinary.getChars(bytes, numChars, 0);
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
        public boolean isDataVersionAcceptable(byte[] version) {
            return version[0] == 7;
        }
    }

    public UnicodeSet addPropertyStarts(UnicodeSet set) {
        Iterator<Trie2.Range> trieIterator = this.m_trie_.iterator();
        while (trieIterator.hasNext()) {
            Trie2.Range range = trieIterator.next();
            if (range.leadSurrogate) {
                break;
            }
            set.add(range.startCodePoint);
        }
        set.add(9);
        set.add(10);
        set.add(14);
        set.add(28);
        set.add(32);
        set.add(133);
        set.add(134);
        set.add(127);
        set.add(HAIRSP);
        set.add(8208);
        set.add(INHSWAP);
        set.add(8304);
        set.add(ZWNBSP);
        set.add(MotionEventCompat.ACTION_POINTER_INDEX_MASK);
        set.add(160);
        set.add(161);
        set.add(FIGURESP);
        set.add(8200);
        set.add(NNBSP);
        set.add(8240);
        set.add(12295);
        set.add(12296);
        set.add(19968);
        set.add(19969);
        set.add(20108);
        set.add(20109);
        set.add(19977);
        set.add(19978);
        set.add(22235);
        set.add(22236);
        set.add(20116);
        set.add(20117);
        set.add(20845);
        set.add(20846);
        set.add(19971);
        set.add(19972);
        set.add(20843);
        set.add(20844);
        set.add(20061);
        set.add(20062);
        set.add(97);
        set.add(123);
        set.add(65);
        set.add(91);
        set.add(U_FW_a);
        set.add(65371);
        set.add(U_FW_A);
        set.add(65339);
        set.add(103);
        set.add(71);
        set.add(65351);
        set.add(65319);
        set.add(WJ);
        set.add(65520);
        set.add(65532);
        set.add(917504);
        set.add(921600);
        set.add(CGJ);
        set.add(848);
        return set;
    }

    public void upropsvec_addPropertyStarts(UnicodeSet set) {
        if (this.m_additionalColumnsCount_ > 0) {
            Iterator<Trie2.Range> trieIterator = this.m_additionalTrie_.iterator();
            while (trieIterator.hasNext()) {
                Trie2.Range range = trieIterator.next();
                if (!range.leadSurrogate) {
                    set.add(range.startCodePoint);
                } else {
                    return;
                }
            }
        }
    }

    public UnicodeSet ulayout_addPropertyStarts(int src, UnicodeSet set) {
        CodePointTrie trie;
        switch (src) {
            case 12:
                trie = InPCTrie.INSTANCE;
                break;
            case 13:
                trie = InSCTrie.INSTANCE;
                break;
            case 14:
                trie = VoTrie.INSTANCE;
                break;
            default:
                throw new IllegalStateException();
        }
        CodePointMap.Range range = new CodePointMap.Range();
        for (int start = 0; trie.getRange(start, null, range); start = range.getEnd() + 1) {
            set.add(start);
        }
        return set;
    }
}
