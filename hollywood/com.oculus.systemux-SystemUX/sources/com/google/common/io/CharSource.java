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
import com.google.common.collect.Streams;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.MustBeClosed;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.UncheckedIOException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Stream;
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
        Reader openStream = openStream();
        return openStream instanceof BufferedReader ? (BufferedReader) openStream : new BufferedReader(openStream);
    }

    @Beta
    @MustBeClosed
    public Stream<String> lines() throws IOException {
        BufferedReader openBufferedStream = openBufferedStream();
        return (Stream) openBufferedStream.lines().onClose(new Runnable(openBufferedStream) {
            /* class com.google.common.io.$$Lambda$CharSource$0xYa2aCdM0rdIymT3zRov4EKd9k */
            private final /* synthetic */ BufferedReader f$0;

            {
                this.f$0 = r1;
            }

            public final void run() {
                CharSource.lambda$lines$0(this.f$0);
            }
        });
    }

    static /* synthetic */ void lambda$lines$0(BufferedReader bufferedReader) {
        try {
            bufferedReader.close();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
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
        Closer create = Closer.create();
        try {
            long countBySkipping = countBySkipping((Reader) create.register(openStream()));
            create.close();
            return countBySkipping;
        } catch (Throwable th) {
            create.close();
            throw th;
        }
    }

    private long countBySkipping(Reader reader) throws IOException {
        long j = 0;
        while (true) {
            long skip = reader.skip(Clock.MAX_TIME);
            if (skip == 0) {
                return j;
            }
            j += skip;
        }
    }

    @CanIgnoreReturnValue
    public long copyTo(Appendable appendable) throws IOException {
        Preconditions.checkNotNull(appendable);
        Closer create = Closer.create();
        try {
            long copy = CharStreams.copy((Reader) create.register(openStream()), appendable);
            create.close();
            return copy;
        } catch (Throwable th) {
            create.close();
            throw th;
        }
    }

    @CanIgnoreReturnValue
    public long copyTo(CharSink charSink) throws IOException {
        Preconditions.checkNotNull(charSink);
        Closer create = Closer.create();
        try {
            long copy = CharStreams.copy((Reader) create.register(openStream()), (Writer) create.register(charSink.openStream()));
            create.close();
            return copy;
        } catch (Throwable th) {
            create.close();
            throw th;
        }
    }

    public String read() throws IOException {
        Closer create = Closer.create();
        try {
            String charStreams = CharStreams.toString((Reader) create.register(openStream()));
            create.close();
            return charStreams;
        } catch (Throwable th) {
            create.close();
            throw th;
        }
    }

    @NullableDecl
    public String readFirstLine() throws IOException {
        Closer create = Closer.create();
        try {
            String readLine = ((BufferedReader) create.register(openBufferedStream())).readLine();
            create.close();
            return readLine;
        } catch (Throwable th) {
            create.close();
            throw th;
        }
    }

    public ImmutableList<String> readLines() throws IOException {
        Closer create = Closer.create();
        try {
            BufferedReader bufferedReader = (BufferedReader) create.register(openBufferedStream());
            ArrayList newArrayList = Lists.newArrayList();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    newArrayList.add(readLine);
                } else {
                    ImmutableList<String> copyOf = ImmutableList.copyOf((Collection) newArrayList);
                    create.close();
                    return copyOf;
                }
            }
        } catch (Throwable th) {
            create.close();
            throw th;
        }
    }

    @Beta
    @CanIgnoreReturnValue
    public <T> T readLines(LineProcessor<T> lineProcessor) throws IOException {
        Preconditions.checkNotNull(lineProcessor);
        Closer create = Closer.create();
        try {
            T t = (T) CharStreams.readLines((Reader) create.register(openStream()), lineProcessor);
            create.close();
            return t;
        } catch (Throwable th) {
            create.close();
            throw th;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0012, code lost:
        if (r0 != null) goto L_0x0014;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0014, code lost:
        if (r1 != null) goto L_0x0016;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001a, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x001b, code lost:
        r1.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x001f, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x000e, code lost:
        r3 = move-exception;
     */
    @com.google.common.annotations.Beta
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void forEachLine(java.util.function.Consumer<? super java.lang.String> r3) throws java.io.IOException {
        /*
            r2 = this;
            java.util.stream.Stream r0 = r2.lines()     // Catch:{ UncheckedIOException -> 0x0023 }
            r1 = 0
            r0.forEachOrdered(r3)     // Catch:{ Throwable -> 0x0010 }
            if (r0 == 0) goto L_0x000d
            r0.close()
        L_0x000d:
            return
        L_0x000e:
            r3 = move-exception
            goto L_0x0012
        L_0x0010:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x000e }
        L_0x0012:
            if (r0 == 0) goto L_0x0022
            if (r1 == 0) goto L_0x001f
            r0.close()     // Catch:{ Throwable -> 0x001a }
            goto L_0x0022
        L_0x001a:
            r0 = move-exception
            r1.addSuppressed(r0)
            goto L_0x0022
        L_0x001f:
            r0.close()
        L_0x0022:
            throw r3
        L_0x0023:
            r3 = move-exception
            java.io.IOException r3 = r3.getCause()
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.io.CharSource.forEachLine(java.util.function.Consumer):void");
    }

    public boolean isEmpty() throws IOException {
        Optional<Long> lengthIfKnown = lengthIfKnown();
        boolean z = true;
        if (lengthIfKnown.isPresent()) {
            return lengthIfKnown.get().longValue() == 0;
        }
        Closer create = Closer.create();
        try {
            if (((Reader) create.register(openStream())).read() != -1) {
                z = false;
            }
            create.close();
            return z;
        } catch (Throwable th) {
            create.close();
            throw th;
        }
    }

    public static CharSource concat(Iterable<? extends CharSource> iterable) {
        return new ConcatenatedCharSource(iterable);
    }

    public static CharSource concat(Iterator<? extends CharSource> it) {
        return concat(ImmutableList.copyOf(it));
    }

    public static CharSource concat(CharSource... charSourceArr) {
        return concat(ImmutableList.copyOf(charSourceArr));
    }

    public static CharSource wrap(CharSequence charSequence) {
        return charSequence instanceof String ? new StringCharSource((String) charSequence) : new CharSequenceCharSource(charSequence);
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

        protected CharSequenceCharSource(CharSequence charSequence) {
            this.seq = (CharSequence) Preconditions.checkNotNull(charSequence);
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
        public Stream<String> lines() {
            return Streams.stream(linesIterator());
        }

        @Override // com.google.common.io.CharSource
        public String readFirstLine() {
            Iterator<String> linesIterator = linesIterator();
            if (linesIterator.hasNext()) {
                return linesIterator.next();
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
                java.lang.Object r3 = r3.getResult()
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.io.CharSource.CharSequenceCharSource.readLines(com.google.common.io.LineProcessor):java.lang.Object");
        }

        public String toString() {
            return "CharSource.wrap(" + Ascii.truncate(this.seq, 30, "...") + ")";
        }
    }

    private static class StringCharSource extends CharSequenceCharSource {
        protected StringCharSource(String str) {
            super(str);
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
        public long copyTo(CharSink charSink) throws IOException {
            Preconditions.checkNotNull(charSink);
            Closer create = Closer.create();
            try {
                ((Writer) create.register(charSink.openStream())).write((String) this.seq);
                long length = (long) this.seq.length();
                create.close();
                return length;
            } catch (Throwable th) {
                create.close();
                throw th;
            }
        }
    }

    /* access modifiers changed from: private */
    public static final class EmptyCharSource extends StringCharSource {
        private static final EmptyCharSource INSTANCE = new EmptyCharSource();

        @Override // com.google.common.io.CharSource.CharSequenceCharSource
        public String toString() {
            return "CharSource.empty()";
        }

        private EmptyCharSource() {
            super("");
        }
    }

    /* access modifiers changed from: private */
    public static final class ConcatenatedCharSource extends CharSource {
        private final Iterable<? extends CharSource> sources;

        ConcatenatedCharSource(Iterable<? extends CharSource> iterable) {
            this.sources = (Iterable) Preconditions.checkNotNull(iterable);
        }

        @Override // com.google.common.io.CharSource
        public Reader openStream() throws IOException {
            return new MultiReader(this.sources.iterator());
        }

        @Override // com.google.common.io.CharSource
        public boolean isEmpty() throws IOException {
            for (CharSource charSource : this.sources) {
                if (!charSource.isEmpty()) {
                    return false;
                }
            }
            return true;
        }

        @Override // com.google.common.io.CharSource
        public Optional<Long> lengthIfKnown() {
            long j = 0;
            for (CharSource charSource : this.sources) {
                Optional<Long> lengthIfKnown = charSource.lengthIfKnown();
                if (!lengthIfKnown.isPresent()) {
                    return Optional.absent();
                }
                j += lengthIfKnown.get().longValue();
            }
            return Optional.of(Long.valueOf(j));
        }

        @Override // com.google.common.io.CharSource
        public long length() throws IOException {
            long j = 0;
            for (CharSource charSource : this.sources) {
                j += charSource.length();
            }
            return j;
        }

        public String toString() {
            return "CharSource.concat(" + this.sources + ")";
        }
    }
}
