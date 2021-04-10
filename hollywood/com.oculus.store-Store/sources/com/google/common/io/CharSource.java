package com.google.common.io;

import com.facebook.common.time.Clock;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Ascii;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.AbstractIterator;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtIncompatible
public abstract class CharSource {
    public abstract Reader openStream() throws IOException;

    protected CharSource() {
    }

    @Beta
    public ByteSource asByteSource(Charset charset) {
        return new AsByteSource(charset);
    }

    public BufferedReader openBufferedStream() throws IOException {
        Reader reader = openStream();
        if (reader instanceof BufferedReader) {
            return (BufferedReader) reader;
        }
        return new BufferedReader(reader);
    }

    @Beta
    public Optional<Long> lengthIfKnown() {
        return Optional.absent();
    }

    @Beta
    public long length() throws IOException {
        Optional<Long> lengthIfKnown = lengthIfKnown();
        if (lengthIfKnown.isPresent()) {
            return lengthIfKnown.get().longValue();
        }
        Closer closer = Closer.create();
        try {
            long countBySkipping = countBySkipping((Reader) closer.register(openStream()));
            closer.close();
            return countBySkipping;
        } catch (Throwable th) {
            closer.close();
            throw th;
        }
    }

    private long countBySkipping(Reader reader) throws IOException {
        long count = 0;
        while (true) {
            long read = reader.skip(Clock.MAX_TIME);
            if (read == 0) {
                return count;
            }
            count += read;
        }
    }

    @CanIgnoreReturnValue
    public long copyTo(Appendable appendable) throws IOException {
        Preconditions.checkNotNull(appendable);
        Closer closer = Closer.create();
        try {
            long copy = CharStreams.copy((Reader) closer.register(openStream()), appendable);
            closer.close();
            return copy;
        } catch (Throwable th) {
            closer.close();
            throw th;
        }
    }

    @CanIgnoreReturnValue
    public long copyTo(CharSink sink) throws IOException {
        Preconditions.checkNotNull(sink);
        Closer closer = Closer.create();
        try {
            long copy = CharStreams.copy((Reader) closer.register(openStream()), (Writer) closer.register(sink.openStream()));
            closer.close();
            return copy;
        } catch (Throwable th) {
            closer.close();
            throw th;
        }
    }

    public String read() throws IOException {
        Closer closer = Closer.create();
        try {
            String charStreams = CharStreams.toString((Reader) closer.register(openStream()));
            closer.close();
            return charStreams;
        } catch (Throwable th) {
            closer.close();
            throw th;
        }
    }

    @NullableDecl
    public String readFirstLine() throws IOException {
        Closer closer = Closer.create();
        try {
            String readLine = ((BufferedReader) closer.register(openBufferedStream())).readLine();
            closer.close();
            return readLine;
        } catch (Throwable th) {
            closer.close();
            throw th;
        }
    }

    public ImmutableList<String> readLines() throws IOException {
        Closer closer = Closer.create();
        try {
            BufferedReader reader = (BufferedReader) closer.register(openBufferedStream());
            List<String> result = Lists.newArrayList();
            while (true) {
                String line = reader.readLine();
                if (line != null) {
                    result.add(line);
                } else {
                    ImmutableList<String> copyOf = ImmutableList.copyOf((Collection) result);
                    closer.close();
                    return copyOf;
                }
            }
        } catch (Throwable th) {
            closer.close();
            throw th;
        }
    }

    @Beta
    @CanIgnoreReturnValue
    public <T> T readLines(LineProcessor<T> processor) throws IOException {
        Preconditions.checkNotNull(processor);
        Closer closer = Closer.create();
        try {
            T t = (T) CharStreams.readLines((Reader) closer.register(openStream()), processor);
            closer.close();
            return t;
        } catch (Throwable th) {
            closer.close();
            throw th;
        }
    }

    public boolean isEmpty() throws IOException {
        boolean z = true;
        Optional<Long> lengthIfKnown = lengthIfKnown();
        if (lengthIfKnown.isPresent()) {
            return lengthIfKnown.get().longValue() == 0;
        }
        Closer closer = Closer.create();
        try {
            if (((Reader) closer.register(openStream())).read() != -1) {
                z = false;
            }
            closer.close();
            return z;
        } catch (Throwable th) {
            closer.close();
            throw th;
        }
    }

    public static CharSource concat(Iterable<? extends CharSource> sources) {
        return new ConcatenatedCharSource(sources);
    }

    public static CharSource concat(Iterator<? extends CharSource> sources) {
        return concat(ImmutableList.copyOf(sources));
    }

    public static CharSource concat(CharSource... sources) {
        return concat(ImmutableList.copyOf(sources));
    }

    public static CharSource wrap(CharSequence charSequence) {
        if (charSequence instanceof String) {
            return new StringCharSource((String) charSequence);
        }
        return new CharSequenceCharSource(charSequence);
    }

    public static CharSource empty() {
        return EmptyCharSource.INSTANCE;
    }

    /* access modifiers changed from: private */
    public final class AsByteSource extends ByteSource {
        final Charset charset;

        AsByteSource(Charset charset2) {
            this.charset = (Charset) Preconditions.checkNotNull(charset2);
        }

        @Override // com.google.common.io.ByteSource
        public CharSource asCharSource(Charset charset2) {
            if (charset2.equals(this.charset)) {
                return CharSource.this;
            }
            return super.asCharSource(charset2);
        }

        @Override // com.google.common.io.ByteSource
        public InputStream openStream() throws IOException {
            return new ReaderInputStream(CharSource.this.openStream(), this.charset, 8192);
        }

        public String toString() {
            return CharSource.this.toString() + ".asByteSource(" + this.charset + ")";
        }
    }

    private static class CharSequenceCharSource extends CharSource {
        private static final Splitter LINE_SPLITTER = Splitter.onPattern("\r\n|\n|\r");
        protected final CharSequence seq;

        protected CharSequenceCharSource(CharSequence seq2) {
            this.seq = (CharSequence) Preconditions.checkNotNull(seq2);
        }

        @Override // com.google.common.io.CharSource
        public Reader openStream() {
            return new CharSequenceReader(this.seq);
        }

        @Override // com.google.common.io.CharSource
        public String read() {
            return this.seq.toString();
        }

        @Override // com.google.common.io.CharSource
        public boolean isEmpty() {
            return this.seq.length() == 0;
        }

        @Override // com.google.common.io.CharSource
        public long length() {
            return (long) this.seq.length();
        }

        @Override // com.google.common.io.CharSource
        public Optional<Long> lengthIfKnown() {
            return Optional.of(Long.valueOf((long) this.seq.length()));
        }

        private Iterator<String> linesIterator() {
            return new AbstractIterator<String>() {
                /* class com.google.common.io.CharSource.CharSequenceCharSource.AnonymousClass1 */
                Iterator<String> lines = CharSequenceCharSource.LINE_SPLITTER.split(CharSequenceCharSource.this.seq).iterator();

                /* access modifiers changed from: protected */
                @Override // com.google.common.collect.AbstractIterator
                public String computeNext() {
                    if (this.lines.hasNext()) {
                        String next = this.lines.next();
                        if (this.lines.hasNext() || !next.isEmpty()) {
                            return next;
                        }
                    }
                    return (String) endOfData();
                }
            };
        }

        @Override // com.google.common.io.CharSource
        public String readFirstLine() {
            Iterator<String> lines = linesIterator();
            if (lines.hasNext()) {
                return lines.next();
            }
            return null;
        }

        @Override // com.google.common.io.CharSource
        public ImmutableList<String> readLines() {
            return ImmutableList.copyOf(linesIterator());
        }

        /* JADX WARNING: Removed duplicated region for block: B:3:0x000a  */
        @Override // com.google.common.io.CharSource
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public <T> T readLines(com.google.common.io.LineProcessor<T> r3) throws java.io.IOException {
            /*
                r2 = this;
                java.util.Iterator r0 = r2.linesIterator()
            L_0x0004:
                boolean r1 = r0.hasNext()
                if (r1 == 0) goto L_0x0016
                java.lang.Object r1 = r0.next()
                java.lang.String r1 = (java.lang.String) r1
                boolean r1 = r3.processLine(r1)
                if (r1 != 0) goto L_0x0004
            L_0x0016:
                java.lang.Object r1 = r3.getResult()
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.io.CharSource.CharSequenceCharSource.readLines(com.google.common.io.LineProcessor):java.lang.Object");
        }

        public String toString() {
            return "CharSource.wrap(" + Ascii.truncate(this.seq, 30, "...") + ")";
        }
    }

    private static class StringCharSource extends CharSequenceCharSource {
        protected StringCharSource(String seq) {
            super(seq);
        }

        @Override // com.google.common.io.CharSource.CharSequenceCharSource, com.google.common.io.CharSource
        public Reader openStream() {
            return new StringReader((String) this.seq);
        }

        @Override // com.google.common.io.CharSource
        public long copyTo(Appendable appendable) throws IOException {
            appendable.append(this.seq);
            return (long) this.seq.length();
        }

        @Override // com.google.common.io.CharSource
        public long copyTo(CharSink sink) throws IOException {
            Preconditions.checkNotNull(sink);
            Closer closer = Closer.create();
            try {
                ((Writer) closer.register(sink.openStream())).write((String) this.seq);
                long length = (long) this.seq.length();
                closer.close();
                return length;
            } catch (Throwable th) {
                closer.close();
                throw th;
            }
        }
    }

    /* access modifiers changed from: private */
    public static final class EmptyCharSource extends StringCharSource {
        private static final EmptyCharSource INSTANCE = new EmptyCharSource();

        private EmptyCharSource() {
            super("");
        }

        @Override // com.google.common.io.CharSource.CharSequenceCharSource
        public String toString() {
            return "CharSource.empty()";
        }
    }

    /* access modifiers changed from: private */
    public static final class ConcatenatedCharSource extends CharSource {
        private final Iterable<? extends CharSource> sources;

        ConcatenatedCharSource(Iterable<? extends CharSource> sources2) {
            this.sources = (Iterable) Preconditions.checkNotNull(sources2);
        }

        @Override // com.google.common.io.CharSource
        public Reader openStream() throws IOException {
            return new MultiReader(this.sources.iterator());
        }

        @Override // com.google.common.io.CharSource
        public boolean isEmpty() throws IOException {
            for (CharSource source : this.sources) {
                if (!source.isEmpty()) {
                    return false;
                }
            }
            return true;
        }

        @Override // com.google.common.io.CharSource
        public Optional<Long> lengthIfKnown() {
            long result = 0;
            for (CharSource source : this.sources) {
                Optional<Long> lengthIfKnown = source.lengthIfKnown();
                if (!lengthIfKnown.isPresent()) {
                    return Optional.absent();
                }
                result += lengthIfKnown.get().longValue();
            }
            return Optional.of(Long.valueOf(result));
        }

        @Override // com.google.common.io.CharSource
        public long length() throws IOException {
            long result = 0;
            for (CharSource source : this.sources) {
                result += source.length();
            }
            return result;
        }

        public String toString() {
            return "CharSource.concat(" + this.sources + ")";
        }
    }
}
