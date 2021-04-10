package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;

@Beta
@GwtIncompatible
public final class CharStreams {
    private static final int DEFAULT_BUF_SIZE = 2048;

    static CharBuffer createBuffer() {
        return CharBuffer.allocate(2048);
    }

    private CharStreams() {
    }

    @CanIgnoreReturnValue
    public static long copy(Readable from, Appendable to) throws IOException {
        if (!(from instanceof Reader)) {
            Preconditions.checkNotNull(from);
            Preconditions.checkNotNull(to);
            long total = 0;
            CharBuffer buf = createBuffer();
            while (from.read(buf) != -1) {
                buf.flip();
                to.append(buf);
                total += (long) buf.remaining();
                buf.clear();
            }
            return total;
        } else if (to instanceof StringBuilder) {
            return copyReaderToBuilder((Reader) from, (StringBuilder) to);
        } else {
            return copyReaderToWriter((Reader) from, asWriter(to));
        }
    }

    @CanIgnoreReturnValue
    static long copyReaderToBuilder(Reader from, StringBuilder to) throws IOException {
        Preconditions.checkNotNull(from);
        Preconditions.checkNotNull(to);
        char[] buf = new char[2048];
        long total = 0;
        while (true) {
            int nRead = from.read(buf);
            if (nRead == -1) {
                return total;
            }
            to.append(buf, 0, nRead);
            total += (long) nRead;
        }
    }

    @CanIgnoreReturnValue
    static long copyReaderToWriter(Reader from, Writer to) throws IOException {
        Preconditions.checkNotNull(from);
        Preconditions.checkNotNull(to);
        char[] buf = new char[2048];
        long total = 0;
        while (true) {
            int nRead = from.read(buf);
            if (nRead == -1) {
                return total;
            }
            to.write(buf, 0, nRead);
            total += (long) nRead;
        }
    }

    public static String toString(Readable r) throws IOException {
        return toStringBuilder(r).toString();
    }

    private static StringBuilder toStringBuilder(Readable r) throws IOException {
        StringBuilder sb = new StringBuilder();
        if (r instanceof Reader) {
            copyReaderToBuilder((Reader) r, sb);
        } else {
            copy(r, sb);
        }
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

    @CanIgnoreReturnValue
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

    @CanIgnoreReturnValue
    public static long exhaust(Readable readable) throws IOException {
        long total = 0;
        CharBuffer buf = createBuffer();
        while (true) {
            long read = (long) readable.read(buf);
            if (read == -1) {
                return total;
            }
            total += read;
            buf.clear();
        }
    }

    public static void skipFully(Reader reader, long n) throws IOException {
        Preconditions.checkNotNull(reader);
        while (n > 0) {
            long amt = reader.skip(n);
            if (amt == 0) {
                throw new EOFException();
            }
            n -= amt;
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
}
