package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;

@Beta
public final class CharStreams {
    private static final int BUF_SIZE = 2048;

    private CharStreams() {
    }

    public static long copy(Readable from, Appendable to) throws IOException {
        Preconditions.checkNotNull(from);
        Preconditions.checkNotNull(to);
        CharBuffer buf = CharBuffer.allocate(BUF_SIZE);
        long total = 0;
        while (from.read(buf) != -1) {
            buf.flip();
            to.append(buf);
            total += (long) buf.remaining();
            buf.clear();
        }
        return total;
    }

    public static String toString(Readable r) throws IOException {
        return toStringBuilder(r).toString();
    }

    private static StringBuilder toStringBuilder(Readable r) throws IOException {
        StringBuilder sb = new StringBuilder();
        copy(r, sb);
        return sb;
    }

    public static List<String> readLines(Readable r) throws IOException {
        List<String> result = new ArrayList<>();
        LineReader lineReader = new LineReader(r);
        while (true) {
            String line = lineReader.readLine();
            if (line == null) {
                return result;
            }
            result.add(line);
        }
    }

    public static <T> T readLines(Readable readable, LineProcessor<T> processor) throws IOException {
        String line;
        Preconditions.checkNotNull(readable);
        Preconditions.checkNotNull(processor);
        LineReader lineReader = new LineReader(readable);
        do {
            line = lineReader.readLine();
            if (line == null) {
                break;
            }
        } while (processor.processLine(line));
        return processor.getResult();
    }

    public static void skipFully(Reader reader, long n) throws IOException {
        Preconditions.checkNotNull(reader);
        while (n > 0) {
            long amt = reader.skip(n);
            if (amt != 0) {
                n -= amt;
            } else if (reader.read() != -1) {
                n--;
            } else {
                throw new EOFException();
            }
        }
    }

    public static Writer nullWriter() {
        return NullWriter.INSTANCE;
    }

    private static final class NullWriter extends Writer {
        private static final NullWriter INSTANCE = new NullWriter();

        private NullWriter() {
        }

        @Override // java.io.Writer
        public void write(int c) {
        }

        @Override // java.io.Writer
        public void write(char[] cbuf) {
            Preconditions.checkNotNull(cbuf);
        }

        @Override // java.io.Writer
        public void write(char[] cbuf, int off, int len) {
            Preconditions.checkPositionIndexes(off, off + len, cbuf.length);
        }

        @Override // java.io.Writer
        public void write(String str) {
            Preconditions.checkNotNull(str);
        }

        @Override // java.io.Writer
        public void write(String str, int off, int len) {
            Preconditions.checkPositionIndexes(off, off + len, str.length());
        }

        @Override // java.lang.Appendable, java.io.Writer, java.io.Writer
        public Writer append(CharSequence csq) {
            Preconditions.checkNotNull(csq);
            return this;
        }

        @Override // java.lang.Appendable, java.io.Writer, java.io.Writer
        public Writer append(CharSequence csq, int start, int end) {
            Preconditions.checkPositionIndexes(start, end, csq.length());
            return this;
        }

        @Override // java.lang.Appendable, java.io.Writer, java.io.Writer
        public Writer append(char c) {
            return this;
        }

        @Override // java.io.Writer, java.io.Flushable
        public void flush() {
        }

        @Override // java.io.Closeable, java.io.Writer, java.lang.AutoCloseable
        public void close() {
        }

        public String toString() {
            return "CharStreams.nullWriter()";
        }
    }

    public static Writer asWriter(Appendable target) {
        if (target instanceof Writer) {
            return (Writer) target;
        }
        return new AppendableWriter(target);
    }

    static Reader asReader(final Readable readable) {
        Preconditions.checkNotNull(readable);
        if (readable instanceof Reader) {
            return (Reader) readable;
        }
        return new Reader() {
            /* class com.google.common.io.CharStreams.AnonymousClass1 */

            @Override // java.io.Reader
            public int read(char[] cbuf, int off, int len) throws IOException {
                return read(CharBuffer.wrap(cbuf, off, len));
            }

            @Override // java.lang.Readable, java.io.Reader
            public int read(CharBuffer target) throws IOException {
                return readable.read(target);
            }

            @Override // java.io.Closeable, java.io.Reader, java.lang.AutoCloseable
            public void close() throws IOException {
                Readable readable = readable;
                if (readable instanceof Closeable) {
                    ((Closeable) readable).close();
                }
            }
        };
    }
}
