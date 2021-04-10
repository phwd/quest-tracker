package android.icu.text;

import android.icu.impl.Norm2AllModes;
import android.icu.impl.Normalizer2Impl;
import android.icu.impl.UCaseProps;
import android.icu.lang.UCharacter;
import android.icu.util.ICUCloneNotSupportedException;
import java.nio.CharBuffer;
import java.text.CharacterIterator;

public final class Normalizer implements Cloneable {
    public static final int COMPARE_CODE_POINT_ORDER = 32768;
    private static final int COMPARE_EQUIV = 524288;
    public static final int COMPARE_IGNORE_CASE = 65536;
    @Deprecated
    public static final int COMPARE_NORM_OPTIONS_SHIFT = 20;
    @Deprecated
    public static final Mode COMPOSE = NFC;
    @Deprecated
    public static final Mode COMPOSE_COMPAT = NFKC;
    @Deprecated
    public static final Mode DECOMP = NFD;
    @Deprecated
    public static final Mode DECOMP_COMPAT = NFKD;
    @Deprecated
    public static final Mode DEFAULT = NFC;
    @Deprecated
    public static final int DONE = -1;
    @Deprecated
    public static final Mode FCD = new FCDMode();
    public static final int FOLD_CASE_DEFAULT = 0;
    public static final int FOLD_CASE_EXCLUDE_SPECIAL_I = 1;
    @Deprecated
    public static final int IGNORE_HANGUL = 1;
    public static final int INPUT_IS_FCD = 131072;
    public static final QuickCheckResult MAYBE = new QuickCheckResult(2);
    @Deprecated
    public static final Mode NFC = new NFCMode();
    @Deprecated
    public static final Mode NFD = new NFDMode();
    @Deprecated
    public static final Mode NFKC = new NFKCMode();
    @Deprecated
    public static final Mode NFKD = new NFKDMode();
    public static final QuickCheckResult NO = new QuickCheckResult(0);
    @Deprecated
    public static final Mode NONE = new NONEMode();
    @Deprecated
    public static final Mode NO_OP = NONE;
    @Deprecated
    public static final int UNICODE_3_2 = 32;
    public static final QuickCheckResult YES = new QuickCheckResult(1);
    private StringBuilder buffer;
    private int bufferPos;
    private int currentIndex;
    private Mode mode;
    private int nextIndex;
    private Normalizer2 norm2;
    private int options;
    private UCharacterIterator text;

    /* access modifiers changed from: private */
    public static final class ModeImpl {
        private final Normalizer2 normalizer2;

        private ModeImpl(Normalizer2 n2) {
            this.normalizer2 = n2;
        }
    }

    private static final class NFDModeImpl {
        private static final ModeImpl INSTANCE = new ModeImpl(Normalizer2.getNFDInstance());

        private NFDModeImpl() {
        }
    }

    private static final class NFKDModeImpl {
        private static final ModeImpl INSTANCE = new ModeImpl(Normalizer2.getNFKDInstance());

        private NFKDModeImpl() {
        }
    }

    private static final class NFCModeImpl {
        private static final ModeImpl INSTANCE = new ModeImpl(Normalizer2.getNFCInstance());

        private NFCModeImpl() {
        }
    }

    /* access modifiers changed from: private */
    public static final class NFKCModeImpl {
        private static final ModeImpl INSTANCE = new ModeImpl(Normalizer2.getNFKCInstance());

        private NFKCModeImpl() {
        }
    }

    private static final class FCDModeImpl {
        private static final ModeImpl INSTANCE = new ModeImpl(Norm2AllModes.getFCDNormalizer2());

        private FCDModeImpl() {
        }
    }

    private static final class Unicode32 {
        private static final UnicodeSet INSTANCE = new UnicodeSet("[:age=3.2:]").freeze();

        private Unicode32() {
        }
    }

    private static final class NFD32ModeImpl {
        private static final ModeImpl INSTANCE = new ModeImpl(new FilteredNormalizer2(Normalizer2.getNFDInstance(), Unicode32.INSTANCE));

        private NFD32ModeImpl() {
        }
    }

    private static final class NFKD32ModeImpl {
        private static final ModeImpl INSTANCE = new ModeImpl(new FilteredNormalizer2(Normalizer2.getNFKDInstance(), Unicode32.INSTANCE));

        private NFKD32ModeImpl() {
        }
    }

    private static final class NFC32ModeImpl {
        private static final ModeImpl INSTANCE = new ModeImpl(new FilteredNormalizer2(Normalizer2.getNFCInstance(), Unicode32.INSTANCE));

        private NFC32ModeImpl() {
        }
    }

    private static final class NFKC32ModeImpl {
        private static final ModeImpl INSTANCE = new ModeImpl(new FilteredNormalizer2(Normalizer2.getNFKCInstance(), Unicode32.INSTANCE));

        private NFKC32ModeImpl() {
        }
    }

    private static final class FCD32ModeImpl {
        private static final ModeImpl INSTANCE = new ModeImpl(new FilteredNormalizer2(Norm2AllModes.getFCDNormalizer2(), Unicode32.INSTANCE));

        private FCD32ModeImpl() {
        }
    }

    @Deprecated
    public static abstract class Mode {
        /* access modifiers changed from: protected */
        @Deprecated
        public abstract Normalizer2 getNormalizer2(int i);

        @Deprecated
        protected Mode() {
        }
    }

    private static final class NONEMode extends Mode {
        private NONEMode() {
        }

        /* access modifiers changed from: protected */
        @Override // android.icu.text.Normalizer.Mode
        public Normalizer2 getNormalizer2(int options) {
            return Norm2AllModes.NOOP_NORMALIZER2;
        }
    }

    private static final class NFDMode extends Mode {
        private NFDMode() {
        }

        /* access modifiers changed from: protected */
        @Override // android.icu.text.Normalizer.Mode
        public Normalizer2 getNormalizer2(int options) {
            return ((options & 32) != 0 ? NFD32ModeImpl.INSTANCE : NFDModeImpl.INSTANCE).normalizer2;
        }
    }

    private static final class NFKDMode extends Mode {
        private NFKDMode() {
        }

        /* access modifiers changed from: protected */
        @Override // android.icu.text.Normalizer.Mode
        public Normalizer2 getNormalizer2(int options) {
            return ((options & 32) != 0 ? NFKD32ModeImpl.INSTANCE : NFKDModeImpl.INSTANCE).normalizer2;
        }
    }

    private static final class NFCMode extends Mode {
        private NFCMode() {
        }

        /* access modifiers changed from: protected */
        @Override // android.icu.text.Normalizer.Mode
        public Normalizer2 getNormalizer2(int options) {
            return ((options & 32) != 0 ? NFC32ModeImpl.INSTANCE : NFCModeImpl.INSTANCE).normalizer2;
        }
    }

    private static final class NFKCMode extends Mode {
        private NFKCMode() {
        }

        /* access modifiers changed from: protected */
        @Override // android.icu.text.Normalizer.Mode
        public Normalizer2 getNormalizer2(int options) {
            return ((options & 32) != 0 ? NFKC32ModeImpl.INSTANCE : NFKCModeImpl.INSTANCE).normalizer2;
        }
    }

    private static final class FCDMode extends Mode {
        private FCDMode() {
        }

        /* access modifiers changed from: protected */
        @Override // android.icu.text.Normalizer.Mode
        public Normalizer2 getNormalizer2(int options) {
            return ((options & 32) != 0 ? FCD32ModeImpl.INSTANCE : FCDModeImpl.INSTANCE).normalizer2;
        }
    }

    public static final class QuickCheckResult {
        private QuickCheckResult(int value) {
        }
    }

    @Deprecated
    public Normalizer(String str, Mode mode2, int opt) {
        this.text = UCharacterIterator.getInstance(str);
        this.mode = mode2;
        this.options = opt;
        this.norm2 = mode2.getNormalizer2(opt);
        this.buffer = new StringBuilder();
    }

    @Deprecated
    public Normalizer(CharacterIterator iter, Mode mode2, int opt) {
        this.text = UCharacterIterator.getInstance((CharacterIterator) iter.clone());
        this.mode = mode2;
        this.options = opt;
        this.norm2 = mode2.getNormalizer2(opt);
        this.buffer = new StringBuilder();
    }

    @Deprecated
    public Normalizer(UCharacterIterator iter, Mode mode2, int options2) {
        try {
            this.text = (UCharacterIterator) iter.clone();
            this.mode = mode2;
            this.options = options2;
            this.norm2 = mode2.getNormalizer2(options2);
            this.buffer = new StringBuilder();
        } catch (CloneNotSupportedException e) {
            throw new ICUCloneNotSupportedException(e);
        }
    }

    @Deprecated
    public Object clone() {
        try {
            Normalizer copy = (Normalizer) super.clone();
            copy.text = (UCharacterIterator) this.text.clone();
            copy.mode = this.mode;
            copy.options = this.options;
            copy.norm2 = this.norm2;
            copy.buffer = new StringBuilder(this.buffer);
            copy.bufferPos = this.bufferPos;
            copy.currentIndex = this.currentIndex;
            copy.nextIndex = this.nextIndex;
            return copy;
        } catch (CloneNotSupportedException e) {
            throw new ICUCloneNotSupportedException(e);
        }
    }

    private static final Normalizer2 getComposeNormalizer2(boolean compat, int options2) {
        return (compat ? NFKC : NFC).getNormalizer2(options2);
    }

    private static final Normalizer2 getDecomposeNormalizer2(boolean compat, int options2) {
        return (compat ? NFKD : NFD).getNormalizer2(options2);
    }

    @Deprecated
    public static String compose(String str, boolean compat) {
        return compose(str, compat, 0);
    }

    @Deprecated
    public static String compose(String str, boolean compat, int options2) {
        return getComposeNormalizer2(compat, options2).normalize(str);
    }

    @Deprecated
    public static int compose(char[] source, char[] target, boolean compat, int options2) {
        return compose(source, 0, source.length, target, 0, target.length, compat, options2);
    }

    @Deprecated
    public static int compose(char[] src, int srcStart, int srcLimit, char[] dest, int destStart, int destLimit, boolean compat, int options2) {
        CharBuffer srcBuffer = CharBuffer.wrap(src, srcStart, srcLimit - srcStart);
        CharsAppendable app = new CharsAppendable(dest, destStart, destLimit);
        getComposeNormalizer2(compat, options2).normalize(srcBuffer, app);
        return app.length();
    }

    @Deprecated
    public static String decompose(String str, boolean compat) {
        return decompose(str, compat, 0);
    }

    @Deprecated
    public static String decompose(String str, boolean compat, int options2) {
        return getDecomposeNormalizer2(compat, options2).normalize(str);
    }

    @Deprecated
    public static int decompose(char[] source, char[] target, boolean compat, int options2) {
        return decompose(source, 0, source.length, target, 0, target.length, compat, options2);
    }

    @Deprecated
    public static int decompose(char[] src, int srcStart, int srcLimit, char[] dest, int destStart, int destLimit, boolean compat, int options2) {
        CharBuffer srcBuffer = CharBuffer.wrap(src, srcStart, srcLimit - srcStart);
        CharsAppendable app = new CharsAppendable(dest, destStart, destLimit);
        getDecomposeNormalizer2(compat, options2).normalize(srcBuffer, app);
        return app.length();
    }

    @Deprecated
    public static String normalize(String str, Mode mode2, int options2) {
        return mode2.getNormalizer2(options2).normalize(str);
    }

    @Deprecated
    public static String normalize(String src, Mode mode2) {
        return normalize(src, mode2, 0);
    }

    @Deprecated
    public static int normalize(char[] source, char[] target, Mode mode2, int options2) {
        return normalize(source, 0, source.length, target, 0, target.length, mode2, options2);
    }

    @Deprecated
    public static int normalize(char[] src, int srcStart, int srcLimit, char[] dest, int destStart, int destLimit, Mode mode2, int options2) {
        CharBuffer srcBuffer = CharBuffer.wrap(src, srcStart, srcLimit - srcStart);
        CharsAppendable app = new CharsAppendable(dest, destStart, destLimit);
        mode2.getNormalizer2(options2).normalize(srcBuffer, app);
        return app.length();
    }

    @Deprecated
    public static String normalize(int char32, Mode mode2, int options2) {
        if (mode2 != NFD || options2 != 0) {
            return normalize(UTF16.valueOf(char32), mode2, options2);
        }
        String decomposition = Normalizer2.getNFCInstance().getDecomposition(char32);
        if (decomposition == null) {
            return UTF16.valueOf(char32);
        }
        return decomposition;
    }

    @Deprecated
    public static String normalize(int char32, Mode mode2) {
        return normalize(char32, mode2, 0);
    }

    @Deprecated
    public static QuickCheckResult quickCheck(String source, Mode mode2) {
        return quickCheck(source, mode2, 0);
    }

    @Deprecated
    public static QuickCheckResult quickCheck(String source, Mode mode2, int options2) {
        return mode2.getNormalizer2(options2).quickCheck(source);
    }

    @Deprecated
    public static QuickCheckResult quickCheck(char[] source, Mode mode2, int options2) {
        return quickCheck(source, 0, source.length, mode2, options2);
    }

    @Deprecated
    public static QuickCheckResult quickCheck(char[] source, int start, int limit, Mode mode2, int options2) {
        return mode2.getNormalizer2(options2).quickCheck(CharBuffer.wrap(source, start, limit - start));
    }

    @Deprecated
    public static boolean isNormalized(char[] src, int start, int limit, Mode mode2, int options2) {
        return mode2.getNormalizer2(options2).isNormalized(CharBuffer.wrap(src, start, limit - start));
    }

    @Deprecated
    public static boolean isNormalized(String str, Mode mode2, int options2) {
        return mode2.getNormalizer2(options2).isNormalized(str);
    }

    @Deprecated
    public static boolean isNormalized(int char32, Mode mode2, int options2) {
        return isNormalized(UTF16.valueOf(char32), mode2, options2);
    }

    public static int compare(char[] s1, int s1Start, int s1Limit, char[] s2, int s2Start, int s2Limit, int options2) {
        if (s1 != null && s1Start >= 0 && s1Limit >= 0 && s2 != null && s2Start >= 0 && s2Limit >= 0 && s1Limit >= s1Start && s2Limit >= s2Start) {
            return internalCompare(CharBuffer.wrap(s1, s1Start, s1Limit - s1Start), CharBuffer.wrap(s2, s2Start, s2Limit - s2Start), options2);
        }
        throw new IllegalArgumentException();
    }

    public static int compare(String s1, String s2, int options2) {
        return internalCompare(s1, s2, options2);
    }

    public static int compare(char[] s1, char[] s2, int options2) {
        return internalCompare(CharBuffer.wrap(s1), CharBuffer.wrap(s2), options2);
    }

    public static int compare(int char32a, int char32b, int options2) {
        return internalCompare(UTF16.valueOf(char32a), UTF16.valueOf(char32b), 131072 | options2);
    }

    public static int compare(int char32a, String str2, int options2) {
        return internalCompare(UTF16.valueOf(char32a), str2, options2);
    }

    @Deprecated
    public static int concatenate(char[] left, int leftStart, int leftLimit, char[] right, int rightStart, int rightLimit, char[] dest, int destStart, int destLimit, Mode mode2, int options2) {
        if (dest == null) {
            throw new IllegalArgumentException();
        } else if (right != dest || rightStart >= destLimit || destStart >= rightLimit) {
            StringBuilder destBuilder = new StringBuilder((((leftLimit - leftStart) + rightLimit) - rightStart) + 16);
            destBuilder.append(left, leftStart, leftLimit - leftStart);
            mode2.getNormalizer2(options2).append(destBuilder, CharBuffer.wrap(right, rightStart, rightLimit - rightStart));
            int destLength = destBuilder.length();
            if (destLength <= destLimit - destStart) {
                destBuilder.getChars(0, destLength, dest, destStart);
                return destLength;
            }
            throw new IndexOutOfBoundsException(Integer.toString(destLength));
        } else {
            throw new IllegalArgumentException("overlapping right and dst ranges");
        }
    }

    @Deprecated
    public static String concatenate(char[] left, char[] right, Mode mode2, int options2) {
        return mode2.getNormalizer2(options2).append(new StringBuilder(left.length + right.length + 16).append(left), CharBuffer.wrap(right)).toString();
    }

    @Deprecated
    public static String concatenate(String left, String right, Mode mode2, int options2) {
        return mode2.getNormalizer2(options2).append(new StringBuilder(left.length() + right.length() + 16).append(left), right).toString();
    }

    @Deprecated
    public static int getFC_NFKC_Closure(int c, char[] dest) {
        String closure = getFC_NFKC_Closure(c);
        int length = closure.length();
        if (!(length == 0 || dest == null || length > dest.length)) {
            closure.getChars(0, length, dest, 0);
        }
        return length;
    }

    @Deprecated
    public static String getFC_NFKC_Closure(int c) {
        Normalizer2 nfkc = NFKCModeImpl.INSTANCE.normalizer2;
        UCaseProps csp = UCaseProps.INSTANCE;
        StringBuilder folded = new StringBuilder();
        int folded1Length = csp.toFullFolding(c, folded, 0);
        if (folded1Length < 0) {
            Normalizer2Impl nfkcImpl = ((Norm2AllModes.Normalizer2WithImpl) nfkc).impl;
            if (nfkcImpl.getCompQuickCheck(nfkcImpl.getNorm16(c)) != 0) {
                return "";
            }
            folded.appendCodePoint(c);
        } else if (folded1Length > 31) {
            folded.appendCodePoint(folded1Length);
        }
        String kc1 = nfkc.normalize(folded);
        String kc2 = nfkc.normalize(UCharacter.foldCase(kc1, 0));
        if (kc1.equals(kc2)) {
            return "";
        }
        return kc2;
    }

    @Deprecated
    public int current() {
        if (this.bufferPos < this.buffer.length() || nextNormalize()) {
            return this.buffer.codePointAt(this.bufferPos);
        }
        return -1;
    }

    @Deprecated
    public int next() {
        if (this.bufferPos >= this.buffer.length() && !nextNormalize()) {
            return -1;
        }
        int c = this.buffer.codePointAt(this.bufferPos);
        this.bufferPos += Character.charCount(c);
        return c;
    }

    @Deprecated
    public int previous() {
        if (this.bufferPos <= 0 && !previousNormalize()) {
            return -1;
        }
        int c = this.buffer.codePointBefore(this.bufferPos);
        this.bufferPos -= Character.charCount(c);
        return c;
    }

    @Deprecated
    public void reset() {
        this.text.setToStart();
        this.nextIndex = 0;
        this.currentIndex = 0;
        clearBuffer();
    }

    @Deprecated
    public void setIndexOnly(int index) {
        this.text.setIndex(index);
        this.nextIndex = index;
        this.currentIndex = index;
        clearBuffer();
    }

    @Deprecated
    public int setIndex(int index) {
        setIndexOnly(index);
        return current();
    }

    @Deprecated
    public int getBeginIndex() {
        return 0;
    }

    @Deprecated
    public int getEndIndex() {
        return endIndex();
    }

    @Deprecated
    public int first() {
        reset();
        return next();
    }

    @Deprecated
    public int last() {
        this.text.setToLimit();
        int index = this.text.getIndex();
        this.nextIndex = index;
        this.currentIndex = index;
        clearBuffer();
        return previous();
    }

    @Deprecated
    public int getIndex() {
        if (this.bufferPos < this.buffer.length()) {
            return this.currentIndex;
        }
        return this.nextIndex;
    }

    @Deprecated
    public int startIndex() {
        return 0;
    }

    @Deprecated
    public int endIndex() {
        return this.text.getLength();
    }

    @Deprecated
    public void setMode(Mode newMode) {
        this.mode = newMode;
        this.norm2 = this.mode.getNormalizer2(this.options);
    }

    @Deprecated
    public Mode getMode() {
        return this.mode;
    }

    @Deprecated
    public void setOption(int option, boolean value) {
        if (value) {
            this.options |= option;
        } else {
            this.options &= ~option;
        }
        this.norm2 = this.mode.getNormalizer2(this.options);
    }

    @Deprecated
    public int getOption(int option) {
        if ((this.options & option) != 0) {
            return 1;
        }
        return 0;
    }

    @Deprecated
    public int getText(char[] fillIn) {
        return this.text.getText(fillIn);
    }

    @Deprecated
    public int getLength() {
        return this.text.getLength();
    }

    @Deprecated
    public String getText() {
        return this.text.getText();
    }

    @Deprecated
    public void setText(StringBuffer newText) {
        UCharacterIterator newIter = UCharacterIterator.getInstance(newText);
        if (newIter != null) {
            this.text = newIter;
            reset();
            return;
        }
        throw new IllegalStateException("Could not create a new UCharacterIterator");
    }

    @Deprecated
    public void setText(char[] newText) {
        UCharacterIterator newIter = UCharacterIterator.getInstance(newText);
        if (newIter != null) {
            this.text = newIter;
            reset();
            return;
        }
        throw new IllegalStateException("Could not create a new UCharacterIterator");
    }

    @Deprecated
    public void setText(String newText) {
        UCharacterIterator newIter = UCharacterIterator.getInstance(newText);
        if (newIter != null) {
            this.text = newIter;
            reset();
            return;
        }
        throw new IllegalStateException("Could not create a new UCharacterIterator");
    }

    @Deprecated
    public void setText(CharacterIterator newText) {
        UCharacterIterator newIter = UCharacterIterator.getInstance(newText);
        if (newIter != null) {
            this.text = newIter;
            reset();
            return;
        }
        throw new IllegalStateException("Could not create a new UCharacterIterator");
    }

    @Deprecated
    public void setText(UCharacterIterator newText) {
        try {
            UCharacterIterator newIter = (UCharacterIterator) newText.clone();
            if (newIter != null) {
                this.text = newIter;
                reset();
                return;
            }
            throw new IllegalStateException("Could not create a new UCharacterIterator");
        } catch (CloneNotSupportedException e) {
            throw new ICUCloneNotSupportedException("Could not clone the UCharacterIterator", e);
        }
    }

    private void clearBuffer() {
        this.buffer.setLength(0);
        this.bufferPos = 0;
    }

    private boolean nextNormalize() {
        clearBuffer();
        int i = this.nextIndex;
        this.currentIndex = i;
        this.text.setIndex(i);
        int c = this.text.nextCodePoint();
        if (c < 0) {
            return false;
        }
        StringBuilder segment = new StringBuilder().appendCodePoint(c);
        while (true) {
            int c2 = this.text.nextCodePoint();
            if (c2 < 0) {
                break;
            } else if (this.norm2.hasBoundaryBefore(c2)) {
                this.text.moveCodePointIndex(-1);
                break;
            } else {
                segment.appendCodePoint(c2);
            }
        }
        this.nextIndex = this.text.getIndex();
        this.norm2.normalize((CharSequence) segment, this.buffer);
        if (this.buffer.length() != 0) {
            return true;
        }
        return false;
    }

    private boolean previousNormalize() {
        int c;
        clearBuffer();
        int i = this.currentIndex;
        this.nextIndex = i;
        this.text.setIndex(i);
        StringBuilder segment = new StringBuilder();
        do {
            c = this.text.previousCodePoint();
            if (c < 0) {
                break;
            } else if (c <= 65535) {
                segment.insert(0, (char) c);
            } else {
                segment.insert(0, Character.toChars(c));
            }
        } while (!this.norm2.hasBoundaryBefore(c));
        this.currentIndex = this.text.getIndex();
        this.norm2.normalize((CharSequence) segment, this.buffer);
        this.bufferPos = this.buffer.length();
        if (this.buffer.length() != 0) {
            return true;
        }
        return false;
    }

    private static int internalCompare(CharSequence s1, CharSequence s2, int options2) {
        Normalizer2 n2;
        int normOptions = options2 >>> 20;
        int options3 = options2 | 524288;
        if ((131072 & options3) == 0 || (options3 & 1) != 0) {
            if ((options3 & 1) != 0) {
                n2 = NFD.getNormalizer2(normOptions);
            } else {
                n2 = FCD.getNormalizer2(normOptions);
            }
            int spanQCYes1 = n2.spanQuickCheckYes(s1);
            int spanQCYes2 = n2.spanQuickCheckYes(s2);
            if (spanQCYes1 < s1.length()) {
                s1 = n2.normalizeSecondAndAppend(new StringBuilder(s1.length() + 16).append(s1, 0, spanQCYes1), s1.subSequence(spanQCYes1, s1.length()));
            }
            if (spanQCYes2 < s2.length()) {
                s2 = n2.normalizeSecondAndAppend(new StringBuilder(s2.length() + 16).append(s2, 0, spanQCYes2), s2.subSequence(spanQCYes2, s2.length()));
            }
        }
        return cmpEquivFold(s1, s2, options3);
    }

    /* access modifiers changed from: private */
    public static final class CmpEquivLevel {
        CharSequence cs;
        int s;

        private CmpEquivLevel() {
        }
    }

    private static final CmpEquivLevel[] createCmpEquivLevelStack() {
        return new CmpEquivLevel[]{new CmpEquivLevel(), new CmpEquivLevel()};
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:199:0x0048 */
    /* JADX WARNING: Code restructure failed: missing block: B:163:0x038a, code lost:
        if (java.lang.Character.isLowSurrogate(r7.charAt(r10)) != false) goto L_0x03a9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:176:0x03b7, code lost:
        if (java.lang.Character.isLowSurrogate(r12.charAt(r15)) != false) goto L_0x03db;
     */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0140  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x017f  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x0193  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x01fa  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static int cmpEquivFold(java.lang.CharSequence r25, java.lang.CharSequence r26, int r27) {
        /*
        // Method dump skipped, instructions count: 990
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.text.Normalizer.cmpEquivFold(java.lang.CharSequence, java.lang.CharSequence, int):int");
    }

    /* access modifiers changed from: private */
    public static final class CharsAppendable implements Appendable {
        private final char[] chars;
        private final int limit;
        private int offset;
        private final int start;

        public CharsAppendable(char[] dest, int destStart, int destLimit) {
            this.chars = dest;
            this.offset = destStart;
            this.start = destStart;
            this.limit = destLimit;
        }

        public int length() {
            int i = this.offset;
            int len = i - this.start;
            if (i <= this.limit) {
                return len;
            }
            throw new IndexOutOfBoundsException(Integer.toString(len));
        }

        @Override // java.lang.Appendable
        public Appendable append(char c) {
            int i = this.offset;
            if (i < this.limit) {
                this.chars[i] = c;
            }
            this.offset++;
            return this;
        }

        @Override // java.lang.Appendable
        public Appendable append(CharSequence s) {
            return append(s, 0, s.length());
        }

        @Override // java.lang.Appendable
        public Appendable append(CharSequence s, int sStart, int sLimit) {
            int len = sLimit - sStart;
            int i = this.limit;
            int i2 = this.offset;
            if (len <= i - i2) {
                while (sStart < sLimit) {
                    char[] cArr = this.chars;
                    int i3 = this.offset;
                    this.offset = i3 + 1;
                    cArr[i3] = s.charAt(sStart);
                    sStart++;
                }
            } else {
                this.offset = i2 + len;
            }
            return this;
        }
    }
}
