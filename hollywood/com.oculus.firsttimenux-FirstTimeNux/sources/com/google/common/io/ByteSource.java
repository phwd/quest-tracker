package com.google.common.io;

import com.facebook.common.time.Clock;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Ascii;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.hash.Funnels;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hasher;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

@GwtIncompatible
public abstract class ByteSource {
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
        return new SlicedByteSource(this, offset, length);
    }

    public boolean isEmpty() throws IOException {
        boolean z = true;
        Optional<Long> sizeIfKnown = sizeIfKnown();
        if (sizeIfKnown.isPresent()) {
            return sizeIfKnown.get().longValue() == 0;
        }
        Closer closer = Closer.create();
        try {
            if (((InputStream) closer.register(openStream())).read() != -1) {
                z = false;
            }
            closer.close();
            return z;
        } catch (Throwable th) {
            closer.close();
            throw th;
        }
    }

    @Beta
    public Optional<Long> sizeIfKnown() {
        return Optional.absent();
    }

    public long size() throws IOException {
        Closer closer;
        Optional<Long> sizeIfKnown = sizeIfKnown();
        if (sizeIfKnown.isPresent()) {
            return sizeIfKnown.get().longValue();
        }
        Closer closer2 = Closer.create();
        try {
            long countBySkipping = countBySkipping((InputStream) closer2.register(openStream()));
            closer2.close();
            return countBySkipping;
        } catch (IOException e) {
            closer2.close();
            closer = Closer.create();
            long exhaust = ByteStreams.exhaust((InputStream) closer.register(openStream()));
            closer.close();
            return exhaust;
        } catch (Throwable e2) {
            try {
                throw closer.rethrow(e2);
            } catch (Throwable th) {
                closer.close();
                throw th;
            }
        }
    }

    private long countBySkipping(InputStream in) throws IOException {
        long count = 0;
        while (true) {
            long skipped = ByteStreams.skipUpTo(in, 2147483647L);
            if (skipped <= 0) {
                return count;
            }
            count += skipped;
        }
    }

    @CanIgnoreReturnValue
    public long copyTo(OutputStream output) throws IOException {
        Preconditions.checkNotNull(output);
        Closer closer = Closer.create();
        try {
            long copy = ByteStreams.copy((InputStream) closer.register(openStream()), output);
            closer.close();
            return copy;
        } catch (Throwable th) {
            closer.close();
            throw th;
        }
    }

    @CanIgnoreReturnValue
    public long copyTo(ByteSink sink) throws IOException {
        Preconditions.checkNotNull(sink);
        Closer closer = Closer.create();
        try {
            long copy = ByteStreams.copy((InputStream) closer.register(openStream()), (OutputStream) closer.register(sink.openStream()));
            closer.close();
            return copy;
        } catch (Throwable th) {
            closer.close();
            throw th;
        }
    }

    public byte[] read() throws IOException {
        byte[] byteArray;
        Closer closer = Closer.create();
        try {
            InputStream in = (InputStream) closer.register(openStream());
            Optional<Long> size = sizeIfKnown();
            if (size.isPresent()) {
                byteArray = ByteStreams.toByteArray(in, size.get().longValue());
            } else {
                byteArray = ByteStreams.toByteArray(in);
            }
            closer.close();
            return byteArray;
        } catch (Throwable th) {
            closer.close();
            throw th;
        }
    }

    @Beta
    @CanIgnoreReturnValue
    public <T> T read(ByteProcessor<T> processor) throws IOException {
        Preconditions.checkNotNull(processor);
        Closer closer = Closer.create();
        try {
            T t = (T) ByteStreams.readBytes((InputStream) closer.register(openStream()), processor);
            closer.close();
            return t;
        } catch (Throwable th) {
            closer.close();
            throw th;
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
        byte[] buf1 = ByteStreams.createBuffer();
        byte[] buf2 = ByteStreams.createBuffer();
        Closer closer = Closer.create();
        try {
            InputStream in1 = (InputStream) closer.register(openStream());
            InputStream in2 = (InputStream) closer.register(other.openStream());
            do {
                read1 = ByteStreams.read(in1, buf1, 0, buf1.length);
                if (read1 != ByteStreams.read(in2, buf2, 0, buf2.length) || !Arrays.equals(buf1, buf2)) {
                    closer.close();
                    return false;
                }
            } while (read1 == buf1.length);
            closer.close();
            return true;
        } catch (Throwable th) {
            closer.close();
            throw th;
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

    class AsCharSource extends CharSource {
        final Charset charset;

        AsCharSource(Charset charset2) {
            this.charset = (Charset) Preconditions.checkNotNull(charset2);
        }

        @Override // com.google.common.io.CharSource
        public ByteSource asByteSource(Charset charset2) {
            if (charset2.equals(this.charset)) {
                return ByteSource.this;
            }
            return super.asByteSource(charset2);
        }

        @Override // com.google.common.io.CharSource
        public Reader openStream() throws IOException {
            return new InputStreamReader(ByteSource.this.openStream(), this.charset);
        }

        @Override // com.google.common.io.CharSource
        public String read() throws IOException {
            return new String(ByteSource.this.read(), this.charset);
        }

        public String toString() {
            return ByteSource.this.toString() + ".asCharSource(" + this.charset + ")";
        }
    }

    /* access modifiers changed from: private */
    public final class SlicedByteSource extends ByteSource {
        final long length;
        final long offset;
        final /* synthetic */ ByteSource this$0;

        SlicedByteSource(ByteSource byteSource, long offset2, long length2) {
            boolean z;
            boolean z2 = true;
            this.this$0 = byteSource;
            if (offset2 >= 0) {
                z = true;
            } else {
                z = false;
            }
            Preconditions.checkArgument(z, "offset (%s) may not be negative", offset2);
            Preconditions.checkArgument(length2 < 0 ? false : z2, "length (%s) may not be negative", length2);
            this.offset = offset2;
            this.length = length2;
        }

        @Override // com.google.common.io.ByteSource
        public InputStream openStream() throws IOException {
            return sliceStream(this.this$0.openStream());
        }

        @Override // com.google.common.io.ByteSource
        public InputStream openBufferedStream() throws IOException {
            return sliceStream(this.this$0.openBufferedStream());
        }

        private InputStream sliceStream(InputStream in) throws IOException {
            Closer closer;
            if (this.offset > 0) {
                try {
                    if (ByteStreams.skipUpTo(in, this.offset) < this.offset) {
                        in.close();
                        return new ByteArrayInputStream(new byte[0]);
                    }
                } catch (Throwable th) {
                    closer.close();
                    throw th;
                }
            }
            return ByteStreams.limit(in, this.length);
        }

        @Override // com.google.common.io.ByteSource
        public ByteSource slice(long offset2, long length2) {
            boolean z;
            boolean z2 = true;
            if (offset2 >= 0) {
                z = true;
            } else {
                z = false;
            }
            Preconditions.checkArgument(z, "offset (%s) may not be negative", offset2);
            if (length2 < 0) {
                z2 = false;
            }
            Preconditions.checkArgument(z2, "length (%s) may not be negative", length2);
            return this.this$0.slice(this.offset + offset2, Math.min(length2, this.length - offset2));
        }

        @Override // com.google.common.io.ByteSource
        public boolean isEmpty() throws IOException {
            return this.length == 0 || ByteSource.super.isEmpty();
        }

        @Override // com.google.common.io.ByteSource
        public Optional<Long> sizeIfKnown() {
            Optional<Long> optionalUnslicedSize = this.this$0.sizeIfKnown();
            if (!optionalUnslicedSize.isPresent()) {
                return Optional.absent();
            }
            long unslicedSize = optionalUnslicedSize.get().longValue();
            return Optional.of(Long.valueOf(Math.min(this.length, unslicedSize - Math.min(this.offset, unslicedSize))));
        }

        public String toString() {
            return this.this$0.toString() + ".slice(" + this.offset + ", " + this.length + ")";
        }
    }

    private static class ByteArrayByteSource extends ByteSource {
        final byte[] bytes;
        final int length;
        final int offset;

        ByteArrayByteSource(byte[] bytes2) {
            this(bytes2, 0, bytes2.length);
        }

        ByteArrayByteSource(byte[] bytes2, int offset2, int length2) {
            this.bytes = bytes2;
            this.offset = offset2;
            this.length = length2;
        }

        @Override // com.google.common.io.ByteSource
        public InputStream openStream() {
            return new ByteArrayInputStream(this.bytes, this.offset, this.length);
        }

        @Override // com.google.common.io.ByteSource
        public InputStream openBufferedStream() throws IOException {
            return openStream();
        }

        @Override // com.google.common.io.ByteSource
        public boolean isEmpty() {
            return this.length == 0;
        }

        @Override // com.google.common.io.ByteSource
        public long size() {
            return (long) this.length;
        }

        @Override // com.google.common.io.ByteSource
        public Optional<Long> sizeIfKnown() {
            return Optional.of(Long.valueOf((long) this.length));
        }

        @Override // com.google.common.io.ByteSource
        public byte[] read() {
            return Arrays.copyOfRange(this.bytes, this.offset, this.offset + this.length);
        }

        @Override // com.google.common.io.ByteSource
        public <T> T read(ByteProcessor<T> processor) throws IOException {
            processor.processBytes(this.bytes, this.offset, this.length);
            return processor.getResult();
        }

        @Override // com.google.common.io.ByteSource
        public long copyTo(OutputStream output) throws IOException {
            output.write(this.bytes, this.offset, this.length);
            return (long) this.length;
        }

        @Override // com.google.common.io.ByteSource
        public HashCode hash(HashFunction hashFunction) throws IOException {
            return hashFunction.hashBytes(this.bytes, this.offset, this.length);
        }

        @Override // com.google.common.io.ByteSource
        public ByteSource slice(long offset2, long length2) {
            boolean z;
            boolean z2 = true;
            if (offset2 >= 0) {
                z = true;
            } else {
                z = false;
            }
            Preconditions.checkArgument(z, "offset (%s) may not be negative", offset2);
            if (length2 < 0) {
                z2 = false;
            }
            Preconditions.checkArgument(z2, "length (%s) may not be negative", length2);
            long offset3 = Math.min(offset2, (long) this.length);
            return new ByteArrayByteSource(this.bytes, this.offset + ((int) offset3), (int) Math.min(length2, ((long) this.length) - offset3));
        }

        public String toString() {
            return "ByteSource.wrap(" + Ascii.truncate(BaseEncoding.base16().encode(this.bytes, this.offset, this.length), 30, "...") + ")";
        }
    }

    private static final class EmptyByteSource extends ByteArrayByteSource {
        static final EmptyByteSource INSTANCE = new EmptyByteSource();

        EmptyByteSource() {
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
        final Iterable<? extends ByteSource> sources;

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
        public Optional<Long> sizeIfKnown() {
            if (!(this.sources instanceof Collection)) {
                return Optional.absent();
            }
            long result = 0;
            for (ByteSource source : this.sources) {
                Optional<Long> sizeIfKnown = source.sizeIfKnown();
                if (!sizeIfKnown.isPresent()) {
                    return Optional.absent();
                }
                result += sizeIfKnown.get().longValue();
                if (result < 0) {
                    return Optional.of(Long.valueOf((long) Clock.MAX_TIME));
                }
            }
            return Optional.of(Long.valueOf(result));
        }

        @Override // com.google.common.io.ByteSource
        public long size() throws IOException {
            long result = 0;
            for (ByteSource source : this.sources) {
                result += source.size();
                if (result < 0) {
                    return Clock.MAX_TIME;
                }
            }
            return result;
        }

        public String toString() {
            return "ByteSource.concat(" + this.sources + ")";
        }
    }
}
