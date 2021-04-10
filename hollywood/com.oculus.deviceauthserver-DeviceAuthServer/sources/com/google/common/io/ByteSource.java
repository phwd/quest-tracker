package com.google.common.io;

import com.google.common.annotations.Beta;
import com.google.common.base.Ascii;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.hash.Funnels;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hasher;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Iterator;

public abstract class ByteSource {
    private static final int BUF_SIZE = 4096;
    private static final byte[] countBuffer = new byte[BUF_SIZE];

    public abstract InputStream openStream() throws IOException;

    protected ByteSource() {
    }

    public CharSource asCharSource(Charset charset) {
        return new AsCharSource(charset);
    }

    public InputStream openBufferedStream() throws IOException {
        InputStream in = openStream();
        if (in instanceof BufferedInputStream) {
            return (BufferedInputStream) in;
        }
        return new BufferedInputStream(in);
    }

    public ByteSource slice(long offset, long length) {
        return new SlicedByteSource(offset, length);
    }

    public boolean isEmpty() throws IOException {
        Closer closer = Closer.create();
        try {
            boolean z = ((InputStream) closer.register(openStream())).read() == -1;
            closer.close();
            return z;
        } catch (Throwable e) {
            closer.close();
            throw e;
        }
    }

    public long size() throws IOException {
        Closer closer;
        Closer closer2 = Closer.create();
        try {
            long countBySkipping = countBySkipping((InputStream) closer2.register(openStream()));
            closer2.close();
            return countBySkipping;
        } catch (IOException e) {
            closer2.close();
            closer = Closer.create();
            long countByReading = countByReading((InputStream) closer.register(openStream()));
            closer.close();
            return countByReading;
        } catch (Throwable e2) {
            try {
                throw closer.rethrow(e2);
            } catch (Throwable e3) {
                closer.close();
                throw e3;
            }
        }
    }

    private long countBySkipping(InputStream in) throws IOException {
        long count = 0;
        while (true) {
            long skipped = in.skip((long) Math.min(in.available(), Integer.MAX_VALUE));
            if (skipped > 0) {
                count += skipped;
            } else if (in.read() == -1) {
                return count;
            } else {
                if (count == 0 && in.available() == 0) {
                    throw new IOException();
                }
                count++;
            }
        }
    }

    private long countByReading(InputStream in) throws IOException {
        long count = 0;
        while (true) {
            long read = (long) in.read(countBuffer);
            if (read == -1) {
                return count;
            }
            count += read;
        }
    }

    public long copyTo(OutputStream output) throws IOException {
        Preconditions.checkNotNull(output);
        Closer closer = Closer.create();
        try {
            long copy = ByteStreams.copy((InputStream) closer.register(openStream()), output);
            closer.close();
            return copy;
        } catch (Throwable e) {
            closer.close();
            throw e;
        }
    }

    public long copyTo(ByteSink sink) throws IOException {
        Preconditions.checkNotNull(sink);
        Closer closer = Closer.create();
        try {
            long copy = ByteStreams.copy((InputStream) closer.register(openStream()), (OutputStream) closer.register(sink.openStream()));
            closer.close();
            return copy;
        } catch (Throwable e) {
            closer.close();
            throw e;
        }
    }

    public byte[] read() throws IOException {
        Closer closer = Closer.create();
        try {
            byte[] byteArray = ByteStreams.toByteArray((InputStream) closer.register(openStream()));
            closer.close();
            return byteArray;
        } catch (Throwable e) {
            closer.close();
            throw e;
        }
    }

    @Beta
    public <T> T read(ByteProcessor<T> processor) throws IOException {
        Preconditions.checkNotNull(processor);
        Closer closer = Closer.create();
        try {
            T t = (T) ByteStreams.readBytes((InputStream) closer.register(openStream()), processor);
            closer.close();
            return t;
        } catch (Throwable e) {
            closer.close();
            throw e;
        }
    }

    public HashCode hash(HashFunction hashFunction) throws IOException {
        Hasher hasher = hashFunction.newHasher();
        copyTo(Funnels.asOutputStream(hasher));
        return hasher.hash();
    }

    public boolean contentEquals(ByteSource other) throws IOException {
        int read1;
        Preconditions.checkNotNull(other);
        byte[] buf1 = new byte[BUF_SIZE];
        byte[] buf2 = new byte[BUF_SIZE];
        Closer closer = Closer.create();
        try {
            InputStream in1 = (InputStream) closer.register(openStream());
            InputStream in2 = (InputStream) closer.register(other.openStream());
            do {
                read1 = ByteStreams.read(in1, buf1, 0, BUF_SIZE);
                if (read1 != ByteStreams.read(in2, buf2, 0, BUF_SIZE) || !Arrays.equals(buf1, buf2)) {
                    closer.close();
                    return false;
                }
            } while (read1 == BUF_SIZE);
            closer.close();
            return true;
        } catch (Throwable e) {
            closer.close();
            throw e;
        }
    }

    public static ByteSource concat(Iterable<? extends ByteSource> sources) {
        return new ConcatenatedByteSource(sources);
    }

    public static ByteSource concat(Iterator<? extends ByteSource> sources) {
        return concat(ImmutableList.copyOf(sources));
    }

    public static ByteSource concat(ByteSource... sources) {
        return concat(ImmutableList.copyOf(sources));
    }

    public static ByteSource wrap(byte[] b) {
        return new ByteArrayByteSource(b);
    }

    public static ByteSource empty() {
        return EmptyByteSource.INSTANCE;
    }

    private final class AsCharSource extends CharSource {
        private final Charset charset;

        private AsCharSource(Charset charset2) {
            this.charset = (Charset) Preconditions.checkNotNull(charset2);
        }

        @Override // com.google.common.io.CharSource
        public Reader openStream() throws IOException {
            return new InputStreamReader(ByteSource.this.openStream(), this.charset);
        }

        public String toString() {
            return ByteSource.this.toString() + ".asCharSource(" + this.charset + ")";
        }
    }

    /* access modifiers changed from: private */
    public final class SlicedByteSource extends ByteSource {
        private final long length;
        private final long offset;

        private SlicedByteSource(long offset2, long length2) {
            Preconditions.checkArgument(offset2 >= 0, "offset (%s) may not be negative", Long.valueOf(offset2));
            Preconditions.checkArgument(length2 >= 0, "length (%s) may not be negative", Long.valueOf(length2));
            this.offset = offset2;
            this.length = length2;
        }

        @Override // com.google.common.io.ByteSource
        public InputStream openStream() throws IOException {
            return sliceStream(ByteSource.this.openStream());
        }

        @Override // com.google.common.io.ByteSource
        public InputStream openBufferedStream() throws IOException {
            return sliceStream(ByteSource.this.openBufferedStream());
        }

        private InputStream sliceStream(InputStream in) throws IOException {
            Closer closer;
            long j = this.offset;
            if (j > 0) {
                try {
                    ByteStreams.skipFully(in, j);
                } catch (Throwable th) {
                    closer.close();
                    throw th;
                }
            }
            return ByteStreams.limit(in, this.length);
        }

        @Override // com.google.common.io.ByteSource
        public ByteSource slice(long offset2, long length2) {
            Preconditions.checkArgument(offset2 >= 0, "offset (%s) may not be negative", Long.valueOf(offset2));
            Preconditions.checkArgument(length2 >= 0, "length (%s) may not be negative", Long.valueOf(length2));
            return ByteSource.this.slice(this.offset + offset2, Math.min(length2, this.length - offset2));
        }

        @Override // com.google.common.io.ByteSource
        public boolean isEmpty() throws IOException {
            return this.length == 0 || ByteSource.super.isEmpty();
        }

        public String toString() {
            return ByteSource.this.toString() + ".slice(" + this.offset + ", " + this.length + ")";
        }
    }

    private static class ByteArrayByteSource extends ByteSource {
        protected final byte[] bytes;

        protected ByteArrayByteSource(byte[] bytes2) {
            this.bytes = (byte[]) Preconditions.checkNotNull(bytes2);
        }

        @Override // com.google.common.io.ByteSource
        public InputStream openStream() {
            return new ByteArrayInputStream(this.bytes);
        }

        @Override // com.google.common.io.ByteSource
        public InputStream openBufferedStream() throws IOException {
            return openStream();
        }

        @Override // com.google.common.io.ByteSource
        public boolean isEmpty() {
            return this.bytes.length == 0;
        }

        @Override // com.google.common.io.ByteSource
        public long size() {
            return (long) this.bytes.length;
        }

        @Override // com.google.common.io.ByteSource
        public byte[] read() {
            return (byte[]) this.bytes.clone();
        }

        @Override // com.google.common.io.ByteSource
        public long copyTo(OutputStream output) throws IOException {
            output.write(this.bytes);
            return (long) this.bytes.length;
        }

        @Override // com.google.common.io.ByteSource
        public <T> T read(ByteProcessor<T> processor) throws IOException {
            byte[] bArr = this.bytes;
            processor.processBytes(bArr, 0, bArr.length);
            return processor.getResult();
        }

        @Override // com.google.common.io.ByteSource
        public HashCode hash(HashFunction hashFunction) throws IOException {
            return hashFunction.hashBytes(this.bytes);
        }

        public String toString() {
            return "ByteSource.wrap(" + Ascii.truncate(BaseEncoding.base16().encode(this.bytes), 30, "...") + ")";
        }
    }

    private static final class EmptyByteSource extends ByteArrayByteSource {
        private static final EmptyByteSource INSTANCE = new EmptyByteSource();

        private EmptyByteSource() {
            super(new byte[0]);
        }

        @Override // com.google.common.io.ByteSource
        public CharSource asCharSource(Charset charset) {
            Preconditions.checkNotNull(charset);
            return CharSource.empty();
        }

        @Override // com.google.common.io.ByteSource.ByteArrayByteSource, com.google.common.io.ByteSource
        public byte[] read() {
            return this.bytes;
        }

        @Override // com.google.common.io.ByteSource.ByteArrayByteSource
        public String toString() {
            return "ByteSource.empty()";
        }
    }

    /* access modifiers changed from: private */
    public static final class ConcatenatedByteSource extends ByteSource {
        private final Iterable<? extends ByteSource> sources;

        ConcatenatedByteSource(Iterable<? extends ByteSource> sources2) {
            this.sources = (Iterable) Preconditions.checkNotNull(sources2);
        }

        @Override // com.google.common.io.ByteSource
        public InputStream openStream() throws IOException {
            return new MultiInputStream(this.sources.iterator());
        }

        @Override // com.google.common.io.ByteSource
        public boolean isEmpty() throws IOException {
            for (ByteSource source : this.sources) {
                if (!source.isEmpty()) {
                    return false;
                }
            }
            return true;
        }

        @Override // com.google.common.io.ByteSource
        public long size() throws IOException {
            long result = 0;
            for (ByteSource source : this.sources) {
                result += source.size();
            }
            return result;
        }

        public String toString() {
            return "ByteSource.concat(" + this.sources + ")";
        }
    }
}
