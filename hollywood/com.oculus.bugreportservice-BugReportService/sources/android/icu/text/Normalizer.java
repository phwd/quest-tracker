package android.icu.text;

import android.icu.impl.Norm2AllModes;
import android.icu.util.ICUCloneNotSupportedException;

public final class Normalizer implements Cloneable {
    public static final Mode COMPOSE = NFC;
    public static final Mode COMPOSE_COMPAT = NFKC;
    public static final Mode DECOMP = NFD;
    public static final Mode DECOMP_COMPAT = NFKD;
    public static final Mode DEFAULT = NFC;
    public static final Mode FCD = new FCDMode();
    public static final QuickCheckResult MAYBE = new QuickCheckResult(2);
    public static final Mode NFC = new NFCMode();
    public static final Mode NFD = new NFDMode();
    public static final Mode NFKC = new NFKCMode();
    public static final Mode NFKD = new NFKDMode();
    public static final QuickCheckResult NO = new QuickCheckResult(0);
    public static final Mode NONE = new NONEMode();
    public static final Mode NO_OP = NONE;
    public static final QuickCheckResult YES = new QuickCheckResult(1);
    private StringBuilder buffer = new StringBuilder();
    private int bufferPos;
    private int currentIndex;
    private Mode mode;
    private int nextIndex;
    private Normalizer2 norm2;
    private int options;
    private UCharacterIterator text;

    private static final class FCD32ModeImpl {
        private static final ModeImpl INSTANCE = new ModeImpl(new FilteredNormalizer2(Norm2AllModes.getFCDNormalizer2(), Unicode32.INSTANCE));
    }

    private static final class FCDModeImpl {
        private static final ModeImpl INSTANCE = new ModeImpl(Norm2AllModes.getFCDNormalizer2());
    }

    private static final class NFC32ModeImpl {
        private static final ModeImpl INSTANCE = new ModeImpl(new FilteredNormalizer2(Normalizer2.getNFCInstance(), Unicode32.INSTANCE));
    }

    private static final class NFCModeImpl {
        private static final ModeImpl INSTANCE = new ModeImpl(Normalizer2.getNFCInstance());
    }

    private static final class NFD32ModeImpl {
        private static final ModeImpl INSTANCE = new ModeImpl(new FilteredNormalizer2(Normalizer2.getNFDInstance(), Unicode32.INSTANCE));
    }

    private static final class NFDModeImpl {
        private static final ModeImpl INSTANCE = new ModeImpl(Normalizer2.getNFDInstance());
    }

    private static final class NFKC32ModeImpl {
        private static final ModeImpl INSTANCE = new ModeImpl(new FilteredNormalizer2(Normalizer2.getNFKCInstance(), Unicode32.INSTANCE));
    }

    private static final class NFKCModeImpl {
        private static final ModeImpl INSTANCE = new ModeImpl(Normalizer2.getNFKCInstance());
    }

    private static final class NFKD32ModeImpl {
        private static final ModeImpl INSTANCE = new ModeImpl(new FilteredNormalizer2(Normalizer2.getNFKDInstance(), Unicode32.INSTANCE));
    }

    private static final class NFKDModeImpl {
        private static final ModeImpl INSTANCE = new ModeImpl(Normalizer2.getNFKDInstance());
    }

    /* access modifiers changed from: private */
    public static final class ModeImpl {
        private final Normalizer2 normalizer2;

        private ModeImpl(Normalizer2 normalizer22) {
            this.normalizer2 = normalizer22;
        }
    }

    private static final class Unicode32 {
        private static final UnicodeSet INSTANCE;

        static {
            UnicodeSet unicodeSet = new UnicodeSet("[:age=3.2:]");
            unicodeSet.freeze();
            INSTANCE = unicodeSet;
        }
    }

    public static abstract class Mode {
        /* access modifiers changed from: protected */
        public abstract Normalizer2 getNormalizer2(int i);

        protected Mode() {
        }
    }

    private static final class NONEMode extends Mode {
        private NONEMode() {
        }

        /* access modifiers changed from: protected */
        @Override // android.icu.text.Normalizer.Mode
        public Normalizer2 getNormalizer2(int i) {
            return Norm2AllModes.NOOP_NORMALIZER2;
        }
    }

    private static final class NFDMode extends Mode {
        private NFDMode() {
        }

        /* access modifiers changed from: protected */
        @Override // android.icu.text.Normalizer.Mode
        public Normalizer2 getNormalizer2(int i) {
            return ((i & 32) != 0 ? NFD32ModeImpl.INSTANCE : NFDModeImpl.INSTANCE).normalizer2;
        }
    }

    private static final class NFKDMode extends Mode {
        private NFKDMode() {
        }

        /* access modifiers changed from: protected */
        @Override // android.icu.text.Normalizer.Mode
        public Normalizer2 getNormalizer2(int i) {
            return ((i & 32) != 0 ? NFKD32ModeImpl.INSTANCE : NFKDModeImpl.INSTANCE).normalizer2;
        }
    }

    private static final class NFCMode extends Mode {
        private NFCMode() {
        }

        /* access modifiers changed from: protected */
        @Override // android.icu.text.Normalizer.Mode
        public Normalizer2 getNormalizer2(int i) {
            return ((i & 32) != 0 ? NFC32ModeImpl.INSTANCE : NFCModeImpl.INSTANCE).normalizer2;
        }
    }

    private static final class NFKCMode extends Mode {
        private NFKCMode() {
        }

        /* access modifiers changed from: protected */
        @Override // android.icu.text.Normalizer.Mode
        public Normalizer2 getNormalizer2(int i) {
            return ((i & 32) != 0 ? NFKC32ModeImpl.INSTANCE : NFKCModeImpl.INSTANCE).normalizer2;
        }
    }

    private static final class FCDMode extends Mode {
        private FCDMode() {
        }

        /* access modifiers changed from: protected */
        @Override // android.icu.text.Normalizer.Mode
        public Normalizer2 getNormalizer2(int i) {
            return ((i & 32) != 0 ? FCD32ModeImpl.INSTANCE : FCDModeImpl.INSTANCE).normalizer2;
        }
    }

    public static final class QuickCheckResult {
        private QuickCheckResult(int i) {
        }
    }

    public Normalizer(String str, Mode mode2, int i) {
        this.text = UCharacterIterator.getInstance(str);
        this.mode = mode2;
        this.options = i;
        this.norm2 = mode2.getNormalizer2(i);
    }

    public Object clone() {
        try {
            Normalizer normalizer = (Normalizer) super.clone();
            normalizer.text = (UCharacterIterator) this.text.clone();
            normalizer.mode = this.mode;
            normalizer.options = this.options;
            normalizer.norm2 = this.norm2;
            normalizer.buffer = new StringBuilder(this.buffer);
            normalizer.bufferPos = this.bufferPos;
            normalizer.currentIndex = this.currentIndex;
            normalizer.nextIndex = this.nextIndex;
            return normalizer;
        } catch (CloneNotSupportedException e) {
            throw new ICUCloneNotSupportedException(e);
        }
    }

    public static String normalize(String str, Mode mode2, int i) {
        return mode2.getNormalizer2(i).normalize(str);
    }

    public static String normalize(String str, Mode mode2) {
        return normalize(str, mode2, 0);
    }

    public static QuickCheckResult quickCheck(String str, Mode mode2) {
        return quickCheck(str, mode2, 0);
    }

    public static QuickCheckResult quickCheck(String str, Mode mode2, int i) {
        return mode2.getNormalizer2(i).quickCheck(str);
    }

    public static boolean isNormalized(String str, Mode mode2, int i) {
        return mode2.getNormalizer2(i).isNormalized(str);
    }

    public int next() {
        if (this.bufferPos >= this.buffer.length() && !nextNormalize()) {
            return -1;
        }
        int codePointAt = this.buffer.codePointAt(this.bufferPos);
        this.bufferPos += Character.charCount(codePointAt);
        return codePointAt;
    }

    public int getIndex() {
        if (this.bufferPos < this.buffer.length()) {
            return this.currentIndex;
        }
        return this.nextIndex;
    }

    public int endIndex() {
        return this.text.getLength();
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
        int nextCodePoint = this.text.nextCodePoint();
        if (nextCodePoint < 0) {
            return false;
        }
        StringBuilder appendCodePoint = new StringBuilder().appendCodePoint(nextCodePoint);
        while (true) {
            int nextCodePoint2 = this.text.nextCodePoint();
            if (nextCodePoint2 < 0) {
                break;
            } else if (this.norm2.hasBoundaryBefore(nextCodePoint2)) {
                this.text.moveCodePointIndex(-1);
                break;
            } else {
                appendCodePoint.appendCodePoint(nextCodePoint2);
            }
        }
        this.nextIndex = this.text.getIndex();
        this.norm2.normalize(appendCodePoint, this.buffer);
        if (this.buffer.length() != 0) {
            return true;
        }
        return false;
    }
}
