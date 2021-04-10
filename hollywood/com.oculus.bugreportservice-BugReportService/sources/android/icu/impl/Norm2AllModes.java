package android.icu.impl;

import android.icu.impl.Normalizer2Impl;
import android.icu.text.Normalizer;
import android.icu.text.Normalizer2;
import java.nio.ByteBuffer;

public final class Norm2AllModes {
    public static final NoopNormalizer2 NOOP_NORMALIZER2 = new NoopNormalizer2();
    private static CacheBase cache = new SoftCache() {
        /* class android.icu.impl.Norm2AllModes.AnonymousClass1 */

        /* access modifiers changed from: protected */
        public Norm2AllModes createInstance(String str, ByteBuffer byteBuffer) {
            Normalizer2Impl normalizer2Impl;
            if (byteBuffer == null) {
                normalizer2Impl = new Normalizer2Impl();
                normalizer2Impl.load(str + ".nrm");
            } else {
                normalizer2Impl = new Normalizer2Impl();
                normalizer2Impl.load(byteBuffer);
            }
            return new Norm2AllModes(normalizer2Impl);
        }
    };
    public final ComposeNormalizer2 comp;
    public final DecomposeNormalizer2 decomp;
    public final ComposeNormalizer2 fcc;
    public final FCDNormalizer2 fcd;
    public final Normalizer2Impl impl;

    /* access modifiers changed from: private */
    public static final class NFCSingleton {
        private static final Norm2AllModesSingleton INSTANCE = new Norm2AllModesSingleton("nfc");
    }

    /* access modifiers changed from: private */
    public static final class NFKCSingleton {
        private static final Norm2AllModesSingleton INSTANCE = new Norm2AllModesSingleton("nfkc");
    }

    /* access modifiers changed from: private */
    public static final class NFKC_CFSingleton {
        private static final Norm2AllModesSingleton INSTANCE = new Norm2AllModesSingleton("nfkc_cf");
    }

    public static final class NoopNormalizer2 extends Normalizer2 {
        @Override // android.icu.text.Normalizer2
        public boolean hasBoundaryAfter(int i) {
            return true;
        }

        @Override // android.icu.text.Normalizer2
        public boolean hasBoundaryBefore(int i) {
            return true;
        }

        @Override // android.icu.text.Normalizer2
        public boolean isNormalized(CharSequence charSequence) {
            return true;
        }

        @Override // android.icu.text.Normalizer2
        public StringBuilder normalize(CharSequence charSequence, StringBuilder sb) {
            if (sb != charSequence) {
                sb.setLength(0);
                sb.append(charSequence);
                return sb;
            }
            throw new IllegalArgumentException();
        }

        @Override // android.icu.text.Normalizer2
        public StringBuilder normalizeSecondAndAppend(StringBuilder sb, CharSequence charSequence) {
            if (sb != charSequence) {
                sb.append(charSequence);
                return sb;
            }
            throw new IllegalArgumentException();
        }

        @Override // android.icu.text.Normalizer2
        public StringBuilder append(StringBuilder sb, CharSequence charSequence) {
            if (sb != charSequence) {
                sb.append(charSequence);
                return sb;
            }
            throw new IllegalArgumentException();
        }

        @Override // android.icu.text.Normalizer2
        public Normalizer.QuickCheckResult quickCheck(CharSequence charSequence) {
            return Normalizer.YES;
        }

        @Override // android.icu.text.Normalizer2
        public int spanQuickCheckYes(CharSequence charSequence) {
            return charSequence.length();
        }
    }

    public static abstract class Normalizer2WithImpl extends Normalizer2 {
        public final Normalizer2Impl impl;

        public abstract int getQuickCheck(int i);

        /* access modifiers changed from: protected */
        public abstract void normalize(CharSequence charSequence, Normalizer2Impl.ReorderingBuffer reorderingBuffer);

        /* access modifiers changed from: protected */
        public abstract void normalizeAndAppend(CharSequence charSequence, boolean z, Normalizer2Impl.ReorderingBuffer reorderingBuffer);

        public Normalizer2WithImpl(Normalizer2Impl normalizer2Impl) {
            this.impl = normalizer2Impl;
        }

        @Override // android.icu.text.Normalizer2
        public StringBuilder normalize(CharSequence charSequence, StringBuilder sb) {
            if (sb != charSequence) {
                sb.setLength(0);
                normalize(charSequence, new Normalizer2Impl.ReorderingBuffer(this.impl, sb, charSequence.length()));
                return sb;
            }
            throw new IllegalArgumentException();
        }

        @Override // android.icu.text.Normalizer2
        public StringBuilder normalizeSecondAndAppend(StringBuilder sb, CharSequence charSequence) {
            normalizeSecondAndAppend(sb, charSequence, true);
            return sb;
        }

        @Override // android.icu.text.Normalizer2
        public StringBuilder append(StringBuilder sb, CharSequence charSequence) {
            normalizeSecondAndAppend(sb, charSequence, false);
            return sb;
        }

        public StringBuilder normalizeSecondAndAppend(StringBuilder sb, CharSequence charSequence, boolean z) {
            if (sb != charSequence) {
                normalizeAndAppend(charSequence, z, new Normalizer2Impl.ReorderingBuffer(this.impl, sb, sb.length() + charSequence.length()));
                return sb;
            }
            throw new IllegalArgumentException();
        }

        @Override // android.icu.text.Normalizer2
        public int getCombiningClass(int i) {
            Normalizer2Impl normalizer2Impl = this.impl;
            return normalizer2Impl.getCC(normalizer2Impl.getNorm16(i));
        }

        @Override // android.icu.text.Normalizer2
        public boolean isNormalized(CharSequence charSequence) {
            return charSequence.length() == spanQuickCheckYes(charSequence);
        }

        @Override // android.icu.text.Normalizer2
        public Normalizer.QuickCheckResult quickCheck(CharSequence charSequence) {
            return isNormalized(charSequence) ? Normalizer.YES : Normalizer.NO;
        }
    }

    public static final class DecomposeNormalizer2 extends Normalizer2WithImpl {
        public DecomposeNormalizer2(Normalizer2Impl normalizer2Impl) {
            super(normalizer2Impl);
        }

        /* access modifiers changed from: protected */
        @Override // android.icu.impl.Norm2AllModes.Normalizer2WithImpl
        public void normalize(CharSequence charSequence, Normalizer2Impl.ReorderingBuffer reorderingBuffer) {
            this.impl.decompose(charSequence, 0, charSequence.length(), reorderingBuffer);
        }

        /* access modifiers changed from: protected */
        @Override // android.icu.impl.Norm2AllModes.Normalizer2WithImpl
        public void normalizeAndAppend(CharSequence charSequence, boolean z, Normalizer2Impl.ReorderingBuffer reorderingBuffer) {
            this.impl.decomposeAndAppend(charSequence, z, reorderingBuffer);
        }

        @Override // android.icu.text.Normalizer2
        public int spanQuickCheckYes(CharSequence charSequence) {
            return this.impl.decompose(charSequence, 0, charSequence.length(), null);
        }

        @Override // android.icu.impl.Norm2AllModes.Normalizer2WithImpl
        public int getQuickCheck(int i) {
            Normalizer2Impl normalizer2Impl = this.impl;
            return normalizer2Impl.isDecompYes(normalizer2Impl.getNorm16(i)) ? 1 : 0;
        }

        @Override // android.icu.text.Normalizer2
        public boolean hasBoundaryBefore(int i) {
            return this.impl.hasDecompBoundaryBefore(i);
        }

        @Override // android.icu.text.Normalizer2
        public boolean hasBoundaryAfter(int i) {
            return this.impl.hasDecompBoundaryAfter(i);
        }

        @Override // android.icu.text.Normalizer2
        public boolean isInert(int i) {
            return this.impl.isDecompInert(i);
        }
    }

    public static final class ComposeNormalizer2 extends Normalizer2WithImpl {
        private final boolean onlyContiguous;

        public ComposeNormalizer2(Normalizer2Impl normalizer2Impl, boolean z) {
            super(normalizer2Impl);
            this.onlyContiguous = z;
        }

        /* access modifiers changed from: protected */
        @Override // android.icu.impl.Norm2AllModes.Normalizer2WithImpl
        public void normalize(CharSequence charSequence, Normalizer2Impl.ReorderingBuffer reorderingBuffer) {
            this.impl.compose(charSequence, 0, charSequence.length(), this.onlyContiguous, true, reorderingBuffer);
        }

        /* access modifiers changed from: protected */
        @Override // android.icu.impl.Norm2AllModes.Normalizer2WithImpl
        public void normalizeAndAppend(CharSequence charSequence, boolean z, Normalizer2Impl.ReorderingBuffer reorderingBuffer) {
            this.impl.composeAndAppend(charSequence, z, this.onlyContiguous, reorderingBuffer);
        }

        @Override // android.icu.text.Normalizer2, android.icu.impl.Norm2AllModes.Normalizer2WithImpl
        public boolean isNormalized(CharSequence charSequence) {
            return this.impl.compose(charSequence, 0, charSequence.length(), this.onlyContiguous, false, new Normalizer2Impl.ReorderingBuffer(this.impl, new StringBuilder(), 5));
        }

        @Override // android.icu.text.Normalizer2, android.icu.impl.Norm2AllModes.Normalizer2WithImpl
        public Normalizer.QuickCheckResult quickCheck(CharSequence charSequence) {
            int composeQuickCheck = this.impl.composeQuickCheck(charSequence, 0, charSequence.length(), this.onlyContiguous, false);
            if ((composeQuickCheck & 1) != 0) {
                return Normalizer.MAYBE;
            }
            if ((composeQuickCheck >>> 1) == charSequence.length()) {
                return Normalizer.YES;
            }
            return Normalizer.NO;
        }

        @Override // android.icu.text.Normalizer2
        public int spanQuickCheckYes(CharSequence charSequence) {
            return this.impl.composeQuickCheck(charSequence, 0, charSequence.length(), this.onlyContiguous, true) >>> 1;
        }

        @Override // android.icu.impl.Norm2AllModes.Normalizer2WithImpl
        public int getQuickCheck(int i) {
            Normalizer2Impl normalizer2Impl = this.impl;
            return normalizer2Impl.getCompQuickCheck(normalizer2Impl.getNorm16(i));
        }

        @Override // android.icu.text.Normalizer2
        public boolean hasBoundaryBefore(int i) {
            return this.impl.hasCompBoundaryBefore(i);
        }

        @Override // android.icu.text.Normalizer2
        public boolean hasBoundaryAfter(int i) {
            return this.impl.hasCompBoundaryAfter(i, this.onlyContiguous);
        }

        @Override // android.icu.text.Normalizer2
        public boolean isInert(int i) {
            return this.impl.isCompInert(i, this.onlyContiguous);
        }
    }

    public static final class FCDNormalizer2 extends Normalizer2WithImpl {
        public FCDNormalizer2(Normalizer2Impl normalizer2Impl) {
            super(normalizer2Impl);
        }

        /* access modifiers changed from: protected */
        @Override // android.icu.impl.Norm2AllModes.Normalizer2WithImpl
        public void normalize(CharSequence charSequence, Normalizer2Impl.ReorderingBuffer reorderingBuffer) {
            this.impl.makeFCD(charSequence, 0, charSequence.length(), reorderingBuffer);
        }

        /* access modifiers changed from: protected */
        @Override // android.icu.impl.Norm2AllModes.Normalizer2WithImpl
        public void normalizeAndAppend(CharSequence charSequence, boolean z, Normalizer2Impl.ReorderingBuffer reorderingBuffer) {
            this.impl.makeFCDAndAppend(charSequence, z, reorderingBuffer);
        }

        @Override // android.icu.text.Normalizer2
        public int spanQuickCheckYes(CharSequence charSequence) {
            return this.impl.makeFCD(charSequence, 0, charSequence.length(), null);
        }

        @Override // android.icu.impl.Norm2AllModes.Normalizer2WithImpl
        public int getQuickCheck(int i) {
            Normalizer2Impl normalizer2Impl = this.impl;
            return normalizer2Impl.isDecompYes(normalizer2Impl.getNorm16(i)) ? 1 : 0;
        }

        @Override // android.icu.text.Normalizer2
        public boolean hasBoundaryBefore(int i) {
            return this.impl.hasFCDBoundaryBefore(i);
        }

        @Override // android.icu.text.Normalizer2
        public boolean hasBoundaryAfter(int i) {
            return this.impl.hasFCDBoundaryAfter(i);
        }

        @Override // android.icu.text.Normalizer2
        public boolean isInert(int i) {
            return this.impl.isFCDInert(i);
        }
    }

    private Norm2AllModes(Normalizer2Impl normalizer2Impl) {
        this.impl = normalizer2Impl;
        this.comp = new ComposeNormalizer2(normalizer2Impl, false);
        this.decomp = new DecomposeNormalizer2(normalizer2Impl);
        this.fcd = new FCDNormalizer2(normalizer2Impl);
        this.fcc = new ComposeNormalizer2(normalizer2Impl, true);
    }

    private static Norm2AllModes getInstanceFromSingleton(Norm2AllModesSingleton norm2AllModesSingleton) {
        if (norm2AllModesSingleton.exception == null) {
            return norm2AllModesSingleton.allModes;
        }
        throw norm2AllModesSingleton.exception;
    }

    public static Norm2AllModes getNFCInstance() {
        return getInstanceFromSingleton(NFCSingleton.INSTANCE);
    }

    public static Norm2AllModes getNFKCInstance() {
        return getInstanceFromSingleton(NFKCSingleton.INSTANCE);
    }

    public static Norm2AllModes getNFKC_CFInstance() {
        return getInstanceFromSingleton(NFKC_CFSingleton.INSTANCE);
    }

    public static Normalizer2WithImpl getN2WithImpl(int i) {
        if (i == 0) {
            return getNFCInstance().decomp;
        }
        if (i == 1) {
            return getNFKCInstance().decomp;
        }
        if (i == 2) {
            return getNFCInstance().comp;
        }
        if (i != 3) {
            return null;
        }
        return getNFKCInstance().comp;
    }

    public static Normalizer2 getFCDNormalizer2() {
        return getNFCInstance().fcd;
    }

    /* access modifiers changed from: private */
    public static final class Norm2AllModesSingleton {
        private Norm2AllModes allModes;
        private RuntimeException exception;

        private Norm2AllModesSingleton(String str) {
            try {
                Normalizer2Impl normalizer2Impl = new Normalizer2Impl();
                normalizer2Impl.load(str + ".nrm");
                this.allModes = new Norm2AllModes(normalizer2Impl);
            } catch (RuntimeException e) {
                this.exception = e;
            }
        }
    }
}
