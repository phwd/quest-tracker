package com.google.common.io;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

@GwtCompatible(emulated = true)
final class GwtWorkarounds {

    /* access modifiers changed from: package-private */
    public interface ByteInput {
        void close() throws IOException;

        int read() throws IOException;
    }

    interface ByteOutput {
        void close() throws IOException;

        void flush() throws IOException;

        void write(byte b) throws IOException;
    }

    interface CharInput {
        void close() throws IOException;

        int read() throws IOException;
    }

    interface CharOutput {
        void close() throws IOException;

        void flush() throws IOException;

        void write(char c) throws IOException;
    }

    private GwtWorkarounds() {
    }

    @GwtIncompatible("Reader")
    static CharInput asCharInput(final Reader reader) {
        Preconditions.checkNotNull(reader);
        return new CharInput() {
            /* class com.google.common.io.GwtWorkarounds.AnonymousClass1 */

            @Override // com.google.common.io.GwtWorkarounds.CharInput
            public int read() throws IOException {
                return reader.read();
            }

            @Override // com.google.common.io.GwtWorkarounds.CharInput
            public void close() throws IOException {
                reader.close();
            }
        };
    }

    static CharInput asCharInput(final CharSequence chars) {
        Preconditions.checkNotNull(chars);
        return new CharInput() {
            /* class com.google.common.io.GwtWorkarounds.AnonymousClass2 */
            int index = 0;

            @Override // com.google.common.io.GwtWorkarounds.CharInput
            public int read() {
                if (this.index >= chars.length()) {
                    return -1;
                }
                CharSequence charSequence = chars;
                int i = this.index;
                this.index = i + 1;
                return charSequence.charAt(i);
            }

            @Override // com.google.common.io.GwtWorkarounds.CharInput
            public void close() {
                this.index = chars.length();
            }
        };
    }

    @GwtIncompatible("InputStream")
    static InputStream asInputStream(final ByteInput input) {
        Preconditions.checkNotNull(input);
        return new InputStream() {
            /* class com.google.common.io.GwtWorkarounds.AnonymousClass3 */

            @Override // java.io.InputStream
            public int read() throws IOException {
                return ByteInput.this.read();
            }

            @Override // java.io.InputStream
            public int read(byte[] b, int off, int len) throws IOException {
                Preconditions.checkNotNull(b);
                Preconditions.checkPositionIndexes(off, off + len, b.length);
                if (len == 0) {
                    return 0;
                }
                int firstByte = read();
                if (firstByte == -1) {
                    return -1;
                }
                b[off] = (byte) firstByte;
                for (int dst = 1; dst < len; dst++) {
                    int readByte = read();
                    if (readByte == -1) {
                        return dst;
                    }
                    b[off + dst] = (byte) readByte;
                }
                return len;
            }

            @Override // java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
            public void close() throws IOException {
                ByteInput.this.close();
            }
        };
    }

    @GwtIncompatible("OutputStream")
    static OutputStream asOutputStream(final ByteOutput output) {
        Preconditions.checkNotNull(output);
        return new OutputStream() {
            /* class com.google.common.io.GwtWorkarounds.AnonymousClass4 */

            @Override // java.io.OutputStream
            public void write(int b) throws IOException {
                ByteOutput.this.write((byte) b);
            }

            @Override // java.io.OutputStream, java.io.Flushable
            public void flush() throws IOException {
                ByteOutput.this.flush();
            }

            @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                ByteOutput.this.close();
            }
        };
    }

    @GwtIncompatible("Writer")
    static CharOutput asCharOutput(final Writer writer) {
        Preconditions.checkNotNull(writer);
        return new CharOutput() {
            /* class com.google.common.io.GwtWorkarounds.AnonymousClass5 */

            @Override // com.google.common.io.GwtWorkarounds.CharOutput
            public void write(char c) throws IOException {
                writer.append(c);
            }

            @Override // com.google.common.io.GwtWorkarounds.CharOutput
            public void flush() throws IOException {
                writer.flush();
            }

            @Override // com.google.common.io.GwtWorkarounds.CharOutput
            public void close() throws IOException {
                writer.close();
            }
        };
    }

    static CharOutput stringBuilderOutput(int initialSize) {
        final StringBuilder builder = new StringBuilder(initialSize);
        return new CharOutput() {
            /* class com.google.common.io.GwtWorkarounds.AnonymousClass6 */

            @Override // com.google.common.io.GwtWorkarounds.CharOutput
            public void write(char c) {
                builder.append(c);
            }

            @Override // com.google.common.io.GwtWorkarounds.CharOutput
            public void flush() {
            }

            @Override // com.google.common.io.GwtWorkarounds.CharOutput
            public void close() {
            }

            public String toString() {
                return builder.toString();
            }
        };
    }
}
