package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Ascii;
import com.google.common.base.CharMatcher;
import com.google.common.base.Preconditions;
import com.google.common.io.GwtWorkarounds;
import com.google.common.math.IntMath;
import com.google.common.primitives.UnsignedBytes;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.math.RoundingMode;
import java.util.Arrays;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;

@Beta
@GwtCompatible(emulated = true)
public abstract class BaseEncoding {
    private static final BaseEncoding BASE16 = new StandardBaseEncoding("base16()", "0123456789ABCDEF", null);
    private static final BaseEncoding BASE32 = new StandardBaseEncoding("base32()", "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567", '=');
    private static final BaseEncoding BASE32_HEX = new StandardBaseEncoding("base32Hex()", "0123456789ABCDEFGHIJKLMNOPQRSTUV", '=');
    private static final BaseEncoding BASE64 = new StandardBaseEncoding("base64()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", '=');
    private static final BaseEncoding BASE64_URL = new StandardBaseEncoding("base64Url()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_", '=');

    /* access modifiers changed from: package-private */
    public abstract GwtWorkarounds.ByteInput decodingStream(GwtWorkarounds.CharInput charInput);

    /* access modifiers changed from: package-private */
    public abstract GwtWorkarounds.ByteOutput encodingStream(GwtWorkarounds.CharOutput charOutput);

    @CheckReturnValue
    public abstract BaseEncoding lowerCase();

    /* access modifiers changed from: package-private */
    public abstract int maxDecodedSize(int i);

    /* access modifiers changed from: package-private */
    public abstract int maxEncodedSize(int i);

    @CheckReturnValue
    public abstract BaseEncoding omitPadding();

    /* access modifiers changed from: package-private */
    public abstract CharMatcher padding();

    @CheckReturnValue
    public abstract BaseEncoding upperCase();

    @CheckReturnValue
    public abstract BaseEncoding withPadChar(char c);

    @CheckReturnValue
    public abstract BaseEncoding withSeparator(String str, int i);

    BaseEncoding() {
    }

    public static final class DecodingException extends IOException {
        DecodingException(String message) {
            super(message);
        }

        DecodingException(Throwable cause) {
            super(cause);
        }
    }

    public String encode(byte[] bytes) {
        return encode((byte[]) Preconditions.checkNotNull(bytes), 0, bytes.length);
    }

    public final String encode(byte[] bytes, int off, int len) {
        Preconditions.checkNotNull(bytes);
        Preconditions.checkPositionIndexes(off, off + len, bytes.length);
        GwtWorkarounds.CharOutput result = GwtWorkarounds.stringBuilderOutput(maxEncodedSize(len));
        GwtWorkarounds.ByteOutput byteOutput = encodingStream(result);
        for (int i = 0; i < len; i++) {
            try {
                byteOutput.write(bytes[off + i]);
            } catch (IOException e) {
                throw new AssertionError("impossible");
            }
        }
        byteOutput.close();
        return result.toString();
    }

    @GwtIncompatible("Writer,OutputStream")
    public final OutputStream encodingStream(Writer writer) {
        return GwtWorkarounds.asOutputStream(encodingStream(GwtWorkarounds.asCharOutput(writer)));
    }

    @GwtIncompatible("ByteSink,CharSink")
    public final ByteSink encodingSink(final CharSink encodedSink) {
        Preconditions.checkNotNull(encodedSink);
        return new ByteSink() {
            /* class com.google.common.io.BaseEncoding.AnonymousClass1 */

            @Override // com.google.common.io.ByteSink
            public OutputStream openStream() throws IOException {
                return BaseEncoding.this.encodingStream(encodedSink.openStream());
            }
        };
    }

    private static byte[] extract(byte[] result, int length) {
        if (length == result.length) {
            return result;
        }
        byte[] trunc = new byte[length];
        System.arraycopy(result, 0, trunc, 0, length);
        return trunc;
    }

    public final byte[] decode(CharSequence chars) {
        try {
            return decodeChecked(chars);
        } catch (DecodingException badInput) {
            throw new IllegalArgumentException(badInput);
        }
    }

    /* access modifiers changed from: package-private */
    public final byte[] decodeChecked(CharSequence chars) throws DecodingException {
        IOException impossible;
        CharSequence chars2 = padding().trimTrailingFrom(chars);
        GwtWorkarounds.ByteInput decodedInput = decodingStream(GwtWorkarounds.asCharInput(chars2));
        byte[] tmp = new byte[maxDecodedSize(chars2.length())];
        int index = 0;
        try {
            int i = decodedInput.read();
            while (i != -1) {
                int index2 = index + 1;
                try {
                    tmp[index] = (byte) i;
                    i = decodedInput.read();
                    index = index2;
                } catch (DecodingException badInput) {
                    throw badInput;
                } catch (IOException e) {
                    impossible = e;
                    throw new AssertionError(impossible);
                }
            }
            return extract(tmp, index);
        } catch (DecodingException badInput2) {
            throw badInput2;
        } catch (IOException e2) {
            impossible = e2;
            throw new AssertionError(impossible);
        }
    }

    @GwtIncompatible("Reader,InputStream")
    public final InputStream decodingStream(Reader reader) {
        return GwtWorkarounds.asInputStream(decodingStream(GwtWorkarounds.asCharInput(reader)));
    }

    @GwtIncompatible("ByteSource,CharSource")
    public final ByteSource decodingSource(final CharSource encodedSource) {
        Preconditions.checkNotNull(encodedSource);
        return new ByteSource() {
            /* class com.google.common.io.BaseEncoding.AnonymousClass2 */

            @Override // com.google.common.io.ByteSource
            public InputStream openStream() throws IOException {
                return BaseEncoding.this.decodingStream(encodedSource.openStream());
            }
        };
    }

    public static BaseEncoding base64() {
        return BASE64;
    }

    public static BaseEncoding base64Url() {
        return BASE64_URL;
    }

    public static BaseEncoding base32() {
        return BASE32;
    }

    public static BaseEncoding base32Hex() {
        return BASE32_HEX;
    }

    public static BaseEncoding base16() {
        return BASE16;
    }

    /* access modifiers changed from: private */
    public static final class Alphabet extends CharMatcher {
        final int bitsPerChar;
        final int bytesPerChunk;
        private final char[] chars;
        final int charsPerChunk;
        private final byte[] decodabet;
        final int mask;
        private final String name;
        private final boolean[] validPadding;

        Alphabet(String name2, char[] chars2) {
            this.name = (String) Preconditions.checkNotNull(name2);
            this.chars = (char[]) Preconditions.checkNotNull(chars2);
            try {
                this.bitsPerChar = IntMath.log2(chars2.length, RoundingMode.UNNECESSARY);
                int gcd = Math.min(8, Integer.lowestOneBit(this.bitsPerChar));
                this.charsPerChunk = 8 / gcd;
                this.bytesPerChunk = this.bitsPerChar / gcd;
                this.mask = chars2.length - 1;
                byte[] decodabet2 = new byte[128];
                Arrays.fill(decodabet2, (byte) -1);
                for (int i = 0; i < chars2.length; i++) {
                    char c = chars2[i];
                    Preconditions.checkArgument(CharMatcher.ASCII.matches(c), "Non-ASCII character: %s", Character.valueOf(c));
                    Preconditions.checkArgument(decodabet2[c] == -1, "Duplicate character: %s", Character.valueOf(c));
                    decodabet2[c] = (byte) i;
                }
                this.decodabet = decodabet2;
                boolean[] validPadding2 = new boolean[this.charsPerChunk];
                for (int i2 = 0; i2 < this.bytesPerChunk; i2++) {
                    validPadding2[IntMath.divide(i2 * 8, this.bitsPerChar, RoundingMode.CEILING)] = true;
                }
                this.validPadding = validPadding2;
            } catch (ArithmeticException e) {
                throw new IllegalArgumentException("Illegal alphabet length " + chars2.length, e);
            }
        }

        /* access modifiers changed from: package-private */
        public char encode(int bits) {
            return this.chars[bits];
        }

        /* access modifiers changed from: package-private */
        public boolean isValidPaddingStartPosition(int index) {
            return this.validPadding[index % this.charsPerChunk];
        }

        /* access modifiers changed from: package-private */
        public int decode(char ch) throws IOException {
            if (ch <= 127) {
                byte[] bArr = this.decodabet;
                if (bArr[ch] != -1) {
                    return bArr[ch];
                }
            }
            throw new DecodingException("Unrecognized character: " + ch);
        }

        private boolean hasLowerCase() {
            for (char c : this.chars) {
                if (Ascii.isLowerCase(c)) {
                    return true;
                }
            }
            return false;
        }

        private boolean hasUpperCase() {
            for (char c : this.chars) {
                if (Ascii.isUpperCase(c)) {
                    return true;
                }
            }
            return false;
        }

        /* access modifiers changed from: package-private */
        public Alphabet upperCase() {
            if (!hasLowerCase()) {
                return this;
            }
            Preconditions.checkState(!hasUpperCase(), "Cannot call upperCase() on a mixed-case alphabet");
            char[] upperCased = new char[this.chars.length];
            int i = 0;
            while (true) {
                char[] cArr = this.chars;
                if (i < cArr.length) {
                    upperCased[i] = Ascii.toUpperCase(cArr[i]);
                    i++;
                } else {
                    return new Alphabet(this.name + ".upperCase()", upperCased);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public Alphabet lowerCase() {
            if (!hasUpperCase()) {
                return this;
            }
            Preconditions.checkState(!hasLowerCase(), "Cannot call lowerCase() on a mixed-case alphabet");
            char[] lowerCased = new char[this.chars.length];
            int i = 0;
            while (true) {
                char[] cArr = this.chars;
                if (i < cArr.length) {
                    lowerCased[i] = Ascii.toLowerCase(cArr[i]);
                    i++;
                } else {
                    return new Alphabet(this.name + ".lowerCase()", lowerCased);
                }
            }
        }

        @Override // com.google.common.base.CharMatcher
        public boolean matches(char c) {
            return CharMatcher.ASCII.matches(c) && this.decodabet[c] != -1;
        }

        @Override // com.google.common.base.CharMatcher
        public String toString() {
            return this.name;
        }
    }

    static final class StandardBaseEncoding extends BaseEncoding {
        private final Alphabet alphabet;
        private transient BaseEncoding lowerCase;
        @Nullable
        private final Character paddingChar;
        private transient BaseEncoding upperCase;

        StandardBaseEncoding(String name, String alphabetChars, @Nullable Character paddingChar2) {
            this(new Alphabet(name, alphabetChars.toCharArray()), paddingChar2);
        }

        StandardBaseEncoding(Alphabet alphabet2, @Nullable Character paddingChar2) {
            this.alphabet = (Alphabet) Preconditions.checkNotNull(alphabet2);
            Preconditions.checkArgument(paddingChar2 == null || !alphabet2.matches(paddingChar2.charValue()), "Padding character %s was already in alphabet", paddingChar2);
            this.paddingChar = paddingChar2;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.io.BaseEncoding
        public CharMatcher padding() {
            Character ch = this.paddingChar;
            return ch == null ? CharMatcher.NONE : CharMatcher.is(ch.charValue());
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.io.BaseEncoding
        public int maxEncodedSize(int bytes) {
            return this.alphabet.charsPerChunk * IntMath.divide(bytes, this.alphabet.bytesPerChunk, RoundingMode.CEILING);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.io.BaseEncoding
        public GwtWorkarounds.ByteOutput encodingStream(final GwtWorkarounds.CharOutput out) {
            Preconditions.checkNotNull(out);
            return new GwtWorkarounds.ByteOutput() {
                /* class com.google.common.io.BaseEncoding.StandardBaseEncoding.AnonymousClass1 */
                int bitBuffer = 0;
                int bitBufferLength = 0;
                int writtenChars = 0;

                @Override // com.google.common.io.GwtWorkarounds.ByteOutput
                public void write(byte b) throws IOException {
                    this.bitBuffer <<= 8;
                    this.bitBuffer |= b & UnsignedBytes.MAX_VALUE;
                    this.bitBufferLength += 8;
                    while (this.bitBufferLength >= StandardBaseEncoding.this.alphabet.bitsPerChar) {
                        out.write(StandardBaseEncoding.this.alphabet.encode((this.bitBuffer >> (this.bitBufferLength - StandardBaseEncoding.this.alphabet.bitsPerChar)) & StandardBaseEncoding.this.alphabet.mask));
                        this.writtenChars++;
                        this.bitBufferLength -= StandardBaseEncoding.this.alphabet.bitsPerChar;
                    }
                }

                @Override // com.google.common.io.GwtWorkarounds.ByteOutput
                public void flush() throws IOException {
                    out.flush();
                }

                @Override // com.google.common.io.GwtWorkarounds.ByteOutput
                public void close() throws IOException {
                    if (this.bitBufferLength > 0) {
                        out.write(StandardBaseEncoding.this.alphabet.encode((this.bitBuffer << (StandardBaseEncoding.this.alphabet.bitsPerChar - this.bitBufferLength)) & StandardBaseEncoding.this.alphabet.mask));
                        this.writtenChars++;
                        if (StandardBaseEncoding.this.paddingChar != null) {
                            while (this.writtenChars % StandardBaseEncoding.this.alphabet.charsPerChunk != 0) {
                                out.write(StandardBaseEncoding.this.paddingChar.charValue());
                                this.writtenChars++;
                            }
                        }
                    }
                    out.close();
                }
            };
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.io.BaseEncoding
        public int maxDecodedSize(int chars) {
            return (int) (((((long) this.alphabet.bitsPerChar) * ((long) chars)) + 7) / 8);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.io.BaseEncoding
        public GwtWorkarounds.ByteInput decodingStream(final GwtWorkarounds.CharInput reader) {
            Preconditions.checkNotNull(reader);
            return new GwtWorkarounds.ByteInput() {
                /* class com.google.common.io.BaseEncoding.StandardBaseEncoding.AnonymousClass2 */
                int bitBuffer = 0;
                int bitBufferLength = 0;
                boolean hitPadding = false;
                final CharMatcher paddingMatcher = StandardBaseEncoding.this.padding();
                int readChars = 0;

                @Override // com.google.common.io.GwtWorkarounds.ByteInput
                public int read() throws IOException {
                    while (true) {
                        int readChar = reader.read();
                        if (readChar != -1) {
                            this.readChars++;
                            char ch = (char) readChar;
                            if (this.paddingMatcher.matches(ch)) {
                                if (this.hitPadding || (this.readChars != 1 && StandardBaseEncoding.this.alphabet.isValidPaddingStartPosition(this.readChars - 1))) {
                                    this.hitPadding = true;
                                }
                            } else if (!this.hitPadding) {
                                this.bitBuffer <<= StandardBaseEncoding.this.alphabet.bitsPerChar;
                                this.bitBuffer |= StandardBaseEncoding.this.alphabet.decode(ch);
                                this.bitBufferLength += StandardBaseEncoding.this.alphabet.bitsPerChar;
                                int i = this.bitBufferLength;
                                if (i >= 8) {
                                    this.bitBufferLength = i - 8;
                                    return (this.bitBuffer >> this.bitBufferLength) & 255;
                                }
                            } else {
                                throw new DecodingException("Expected padding character but found '" + ch + "' at index " + this.readChars);
                            }
                        } else if (this.hitPadding || StandardBaseEncoding.this.alphabet.isValidPaddingStartPosition(this.readChars)) {
                            return -1;
                        } else {
                            throw new DecodingException("Invalid input length " + this.readChars);
                        }
                    }
                    throw new DecodingException("Padding cannot start at index " + this.readChars);
                }

                @Override // com.google.common.io.GwtWorkarounds.ByteInput
                public void close() throws IOException {
                    reader.close();
                }
            };
        }

        @Override // com.google.common.io.BaseEncoding
        public BaseEncoding omitPadding() {
            return this.paddingChar == null ? this : new StandardBaseEncoding(this.alphabet, null);
        }

        @Override // com.google.common.io.BaseEncoding
        public BaseEncoding withPadChar(char padChar) {
            Character ch;
            if (8 % this.alphabet.bitsPerChar == 0 || ((ch = this.paddingChar) != null && ch.charValue() == padChar)) {
                return this;
            }
            return new StandardBaseEncoding(this.alphabet, Character.valueOf(padChar));
        }

        @Override // com.google.common.io.BaseEncoding
        public BaseEncoding withSeparator(String separator, int afterEveryChars) {
            Preconditions.checkNotNull(separator);
            Preconditions.checkArgument(padding().or(this.alphabet).matchesNoneOf(separator), "Separator cannot contain alphabet or padding characters");
            return new SeparatedBaseEncoding(this, separator, afterEveryChars);
        }

        @Override // com.google.common.io.BaseEncoding
        public BaseEncoding upperCase() {
            BaseEncoding result = this.upperCase;
            if (result != null) {
                return result;
            }
            Alphabet upper = this.alphabet.upperCase();
            BaseEncoding result2 = upper == this.alphabet ? this : new StandardBaseEncoding(upper, this.paddingChar);
            this.upperCase = result2;
            return result2;
        }

        @Override // com.google.common.io.BaseEncoding
        public BaseEncoding lowerCase() {
            BaseEncoding result = this.lowerCase;
            if (result != null) {
                return result;
            }
            Alphabet lower = this.alphabet.lowerCase();
            BaseEncoding result2 = lower == this.alphabet ? this : new StandardBaseEncoding(lower, this.paddingChar);
            this.lowerCase = result2;
            return result2;
        }

        public String toString() {
            StringBuilder builder = new StringBuilder("BaseEncoding.");
            builder.append(this.alphabet.toString());
            if (8 % this.alphabet.bitsPerChar != 0) {
                if (this.paddingChar == null) {
                    builder.append(".omitPadding()");
                } else {
                    builder.append(".withPadChar(");
                    builder.append(this.paddingChar);
                    builder.append(')');
                }
            }
            return builder.toString();
        }
    }

    static GwtWorkarounds.CharInput ignoringInput(final GwtWorkarounds.CharInput delegate, final CharMatcher toIgnore) {
        Preconditions.checkNotNull(delegate);
        Preconditions.checkNotNull(toIgnore);
        return new GwtWorkarounds.CharInput() {
            /* class com.google.common.io.BaseEncoding.AnonymousClass3 */

            @Override // com.google.common.io.GwtWorkarounds.CharInput
            public int read() throws IOException {
                int readChar;
                do {
                    readChar = GwtWorkarounds.CharInput.this.read();
                    if (readChar == -1) {
                        break;
                    }
                } while (toIgnore.matches((char) readChar));
                return readChar;
            }

            @Override // com.google.common.io.GwtWorkarounds.CharInput
            public void close() throws IOException {
                GwtWorkarounds.CharInput.this.close();
            }
        };
    }

    static GwtWorkarounds.CharOutput separatingOutput(final GwtWorkarounds.CharOutput delegate, final String separator, final int afterEveryChars) {
        Preconditions.checkNotNull(delegate);
        Preconditions.checkNotNull(separator);
        Preconditions.checkArgument(afterEveryChars > 0);
        return new GwtWorkarounds.CharOutput() {
            /* class com.google.common.io.BaseEncoding.AnonymousClass4 */
            int charsUntilSeparator = afterEveryChars;

            @Override // com.google.common.io.GwtWorkarounds.CharOutput
            public void write(char c) throws IOException {
                if (this.charsUntilSeparator == 0) {
                    for (int i = 0; i < separator.length(); i++) {
                        delegate.write(separator.charAt(i));
                    }
                    this.charsUntilSeparator = afterEveryChars;
                }
                delegate.write(c);
                this.charsUntilSeparator--;
            }

            @Override // com.google.common.io.GwtWorkarounds.CharOutput
            public void flush() throws IOException {
                delegate.flush();
            }

            @Override // com.google.common.io.GwtWorkarounds.CharOutput
            public void close() throws IOException {
                delegate.close();
            }
        };
    }

    static final class SeparatedBaseEncoding extends BaseEncoding {
        private final int afterEveryChars;
        private final BaseEncoding delegate;
        private final String separator;
        private final CharMatcher separatorChars;

        SeparatedBaseEncoding(BaseEncoding delegate2, String separator2, int afterEveryChars2) {
            this.delegate = (BaseEncoding) Preconditions.checkNotNull(delegate2);
            this.separator = (String) Preconditions.checkNotNull(separator2);
            this.afterEveryChars = afterEveryChars2;
            Preconditions.checkArgument(afterEveryChars2 > 0, "Cannot add a separator after every %s chars", Integer.valueOf(afterEveryChars2));
            this.separatorChars = CharMatcher.anyOf(separator2).precomputed();
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.io.BaseEncoding
        public CharMatcher padding() {
            return this.delegate.padding();
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.io.BaseEncoding
        public int maxEncodedSize(int bytes) {
            int unseparatedSize = this.delegate.maxEncodedSize(bytes);
            return (this.separator.length() * IntMath.divide(Math.max(0, unseparatedSize - 1), this.afterEveryChars, RoundingMode.FLOOR)) + unseparatedSize;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.io.BaseEncoding
        public GwtWorkarounds.ByteOutput encodingStream(GwtWorkarounds.CharOutput output) {
            return this.delegate.encodingStream(separatingOutput(output, this.separator, this.afterEveryChars));
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.io.BaseEncoding
        public int maxDecodedSize(int chars) {
            return this.delegate.maxDecodedSize(chars);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.io.BaseEncoding
        public GwtWorkarounds.ByteInput decodingStream(GwtWorkarounds.CharInput input) {
            return this.delegate.decodingStream(ignoringInput(input, this.separatorChars));
        }

        @Override // com.google.common.io.BaseEncoding
        public BaseEncoding omitPadding() {
            return this.delegate.omitPadding().withSeparator(this.separator, this.afterEveryChars);
        }

        @Override // com.google.common.io.BaseEncoding
        public BaseEncoding withPadChar(char padChar) {
            return this.delegate.withPadChar(padChar).withSeparator(this.separator, this.afterEveryChars);
        }

        @Override // com.google.common.io.BaseEncoding
        public BaseEncoding withSeparator(String separator2, int afterEveryChars2) {
            throw new UnsupportedOperationException("Already have a separator");
        }

        @Override // com.google.common.io.BaseEncoding
        public BaseEncoding upperCase() {
            return this.delegate.upperCase().withSeparator(this.separator, this.afterEveryChars);
        }

        @Override // com.google.common.io.BaseEncoding
        public BaseEncoding lowerCase() {
            return this.delegate.lowerCase().withSeparator(this.separator, this.afterEveryChars);
        }

        public String toString() {
            return this.delegate.toString() + ".withSeparator(\"" + this.separator + "\", " + this.afterEveryChars + ")";
        }
    }
}
