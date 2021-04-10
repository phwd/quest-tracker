package android.icu.util;

import android.icu.impl.Normalizer2Impl;
import android.icu.util.StringTrieBuilder;
import java.nio.CharBuffer;

public final class CharsTrieBuilder extends StringTrieBuilder {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private char[] chars;
    private int charsLength;
    private final char[] intUnits = new char[3];

    public CharsTrieBuilder add(CharSequence s, int value) {
        addImpl(s, value);
        return this;
    }

    public CharsTrie build(StringTrieBuilder.Option buildOption) {
        return new CharsTrie(buildCharSequence(buildOption), 0);
    }

    public CharSequence buildCharSequence(StringTrieBuilder.Option buildOption) {
        buildChars(buildOption);
        char[] cArr = this.chars;
        int length = cArr.length;
        int i = this.charsLength;
        return CharBuffer.wrap(cArr, length - i, i);
    }

    private void buildChars(StringTrieBuilder.Option buildOption) {
        if (this.chars == null) {
            this.chars = new char[1024];
        }
        buildImpl(buildOption);
    }

    public CharsTrieBuilder clear() {
        clearImpl();
        this.chars = null;
        this.charsLength = 0;
        return this;
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.util.StringTrieBuilder
    @Deprecated
    public boolean matchNodesCanHaveValues() {
        return true;
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.util.StringTrieBuilder
    @Deprecated
    public int getMaxBranchLinearSubNodeLength() {
        return 5;
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.util.StringTrieBuilder
    @Deprecated
    public int getMinLinearMatch() {
        return 48;
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.util.StringTrieBuilder
    @Deprecated
    public int getMaxLinearMatchLength() {
        return 16;
    }

    private void ensureCapacity(int length) {
        char[] cArr = this.chars;
        if (length > cArr.length) {
            int newCapacity = cArr.length;
            do {
                newCapacity *= 2;
            } while (newCapacity <= length);
            char[] newChars = new char[newCapacity];
            char[] cArr2 = this.chars;
            int length2 = cArr2.length;
            int i = this.charsLength;
            System.arraycopy((Object) cArr2, length2 - i, (Object) newChars, newChars.length - i, i);
            this.chars = newChars;
        }
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.util.StringTrieBuilder
    @Deprecated
    public int write(int unit) {
        int newLength = this.charsLength + 1;
        ensureCapacity(newLength);
        this.charsLength = newLength;
        char[] cArr = this.chars;
        int length = cArr.length;
        int i = this.charsLength;
        cArr[length - i] = (char) unit;
        return i;
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.util.StringTrieBuilder
    @Deprecated
    public int write(int offset, int length) {
        int newLength = this.charsLength + length;
        ensureCapacity(newLength);
        this.charsLength = newLength;
        int charsOffset = this.chars.length - this.charsLength;
        while (length > 0) {
            this.chars[charsOffset] = this.strings.charAt(offset);
            length--;
            charsOffset++;
            offset++;
        }
        return this.charsLength;
    }

    private int write(char[] s, int length) {
        int newLength = this.charsLength + length;
        ensureCapacity(newLength);
        this.charsLength = newLength;
        char[] cArr = this.chars;
        System.arraycopy((Object) s, 0, (Object) cArr, cArr.length - this.charsLength, length);
        return this.charsLength;
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.util.StringTrieBuilder
    @Deprecated
    public int writeValueAndFinal(int i, boolean isFinal) {
        int length;
        char c = 32768;
        if (i < 0 || i > 16383) {
            if (i < 0 || i > 1073676287) {
                char[] cArr = this.intUnits;
                cArr[0] = 32767;
                cArr[1] = (char) (i >> 16);
                cArr[2] = (char) i;
                length = 3;
            } else {
                char[] cArr2 = this.intUnits;
                cArr2[0] = (char) ((i >> 16) + 16384);
                cArr2[1] = (char) i;
                length = 2;
            }
            char[] cArr3 = this.intUnits;
            char c2 = cArr3[0];
            if (!isFinal) {
                c = 0;
            }
            cArr3[0] = (char) (c | c2);
            return write(this.intUnits, length);
        }
        if (!isFinal) {
            c = 0;
        }
        return write(c | i);
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.util.StringTrieBuilder
    @Deprecated
    public int writeValueAndType(boolean hasValue, int value, int node) {
        int length;
        if (!hasValue) {
            return write(node);
        }
        if (value < 0 || value > 16646143) {
            char[] cArr = this.intUnits;
            cArr[0] = 32704;
            cArr[1] = (char) (value >> 16);
            cArr[2] = (char) value;
            length = 3;
        } else if (value <= 255) {
            this.intUnits[0] = (char) ((value + 1) << 6);
            length = 1;
        } else {
            char[] cArr2 = this.intUnits;
            cArr2[0] = (char) ((32704 & (value >> 10)) + 16448);
            cArr2[1] = (char) value;
            length = 2;
        }
        char[] cArr3 = this.intUnits;
        cArr3[0] = (char) (cArr3[0] | ((char) node));
        return write(cArr3, length);
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.util.StringTrieBuilder
    @Deprecated
    public int writeDeltaTo(int jumpTarget) {
        int length;
        int i = this.charsLength - jumpTarget;
        if (i <= 64511) {
            return write(i);
        }
        if (i <= 67043327) {
            this.intUnits[0] = (char) ((i >> 16) + Normalizer2Impl.MIN_NORMAL_MAYBE_YES);
            length = 1;
        } else {
            char[] cArr = this.intUnits;
            cArr[0] = 65535;
            cArr[1] = (char) (i >> 16);
            length = 2;
        }
        char[] cArr2 = this.intUnits;
        cArr2[length] = (char) i;
        return write(cArr2, length + 1);
    }
}
