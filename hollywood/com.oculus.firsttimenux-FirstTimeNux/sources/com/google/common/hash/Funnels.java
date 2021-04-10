package com.google.common.hash;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Iterator;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@Beta
public final class Funnels {
    private Funnels() {
    }

    public static Funnel<byte[]> byteArrayFunnel() {
        return ByteArrayFunnel.INSTANCE;
    }

    private enum ByteArrayFunnel implements Funnel<byte[]> {
        INSTANCE;

        public void funnel(byte[] from, PrimitiveSink into) {
            into.putBytes(from);
        }

        public String toString() {
            return "Funnels.byteArrayFunnel()";
        }
    }

    public static Funnel<CharSequence> unencodedCharsFunnel() {
        return UnencodedCharsFunnel.INSTANCE;
    }

    private enum UnencodedCharsFunnel implements Funnel<CharSequence> {
        INSTANCE;

        public void funnel(CharSequence from, PrimitiveSink into) {
            into.putUnencodedChars(from);
        }

        public String toString() {
            return "Funnels.unencodedCharsFunnel()";
        }
    }

    public static Funnel<CharSequence> stringFunnel(Charset charset) {
        return new StringCharsetFunnel(charset);
    }

    /* access modifiers changed from: private */
    public static class StringCharsetFunnel implements Funnel<CharSequence>, Serializable {
        private final Charset charset;

        StringCharsetFunnel(Charset charset2) {
            this.charset = (Charset) Preconditions.checkNotNull(charset2);
        }

        public void funnel(CharSequence from, PrimitiveSink into) {
            into.putString(from, this.charset);
        }

        public String toString() {
            return "Funnels.stringFunnel(" + this.charset.name() + ")";
        }

        public boolean equals(@NullableDecl Object o) {
            if (o instanceof StringCharsetFunnel) {
                return this.charset.equals(((StringCharsetFunnel) o).charset);
            }
            return false;
        }

        public int hashCode() {
            return StringCharsetFunnel.class.hashCode() ^ this.charset.hashCode();
        }

        /* access modifiers changed from: package-private */
        public Object writeReplace() {
            return new SerializedForm(this.charset);
        }

        private static class SerializedForm implements Serializable {
            private static final long serialVersionUID = 0;
            private final String charsetCanonicalName;

            SerializedForm(Charset charset) {
                this.charsetCanonicalName = charset.name();
            }

            private Object readResolve() {
                return Funnels.stringFunnel(Charset.forName(this.charsetCanonicalName));
            }
        }
    }

    public static Funnel<Integer> integerFunnel() {
        return IntegerFunnel.INSTANCE;
    }

    private enum IntegerFunnel implements Funnel<Integer> {
        INSTANCE;

        public void funnel(Integer from, PrimitiveSink into) {
            into.putInt(from.intValue());
        }

        public String toString() {
            return "Funnels.integerFunnel()";
        }
    }

    public static <E> Funnel<Iterable<? extends E>> sequentialFunnel(Funnel<E> elementFunnel) {
        return new SequentialFunnel(elementFunnel);
    }

    private static class SequentialFunnel<E> implements Funnel<Iterable<? extends E>>, Serializable {
        private final Funnel<E> elementFunnel;

        @Override // com.google.common.hash.Funnel
        public /* bridge */ /* synthetic */ void funnel(Object obj, PrimitiveSink primitiveSink) {
            funnel((Iterable) ((Iterable) obj), primitiveSink);
        }

        SequentialFunnel(Funnel<E> elementFunnel2) {
            this.elementFunnel = (Funnel) Preconditions.checkNotNull(elementFunnel2);
        }

        /* JADX DEBUG: Multi-variable search result rejected for r2v1, resolved type: com.google.common.hash.Funnel<E> */
        /* JADX WARN: Multi-variable type inference failed */
        public void funnel(Iterable<? extends E> from, PrimitiveSink into) {
            Iterator<? extends E> it = from.iterator();
            while (it.hasNext()) {
                this.elementFunnel.funnel(it.next(), into);
            }
        }

        public String toString() {
            return "Funnels.sequentialFunnel(" + this.elementFunnel + ")";
        }

        public boolean equals(@NullableDecl Object o) {
            if (o instanceof SequentialFunnel) {
                return this.elementFunnel.equals(((SequentialFunnel) o).elementFunnel);
            }
            return false;
        }

        public int hashCode() {
            return SequentialFunnel.class.hashCode() ^ this.elementFunnel.hashCode();
        }
    }

    public static Funnel<Long> longFunnel() {
        return LongFunnel.INSTANCE;
    }

    private enum LongFunnel implements Funnel<Long> {
        INSTANCE;

        public void funnel(Long from, PrimitiveSink into) {
            into.putLong(from.longValue());
        }

        public String toString() {
            return "Funnels.longFunnel()";
        }
    }

    public static OutputStream asOutputStream(PrimitiveSink sink) {
        return new SinkAsStream(sink);
    }

    private static class SinkAsStream extends OutputStream {
        final PrimitiveSink sink;

        SinkAsStream(PrimitiveSink sink2) {
            this.sink = (PrimitiveSink) Preconditions.checkNotNull(sink2);
        }

        @Override // java.io.OutputStream
        public void write(int b) {
            this.sink.putByte((byte) b);
        }

        @Override // java.io.OutputStream
        public void write(byte[] bytes) {
            this.sink.putBytes(bytes);
        }

        @Override // java.io.OutputStream
        public void write(byte[] bytes, int off, int len) {
            this.sink.putBytes(bytes, off, len);
        }

        public String toString() {
            return "Funnels.asOutputStream(" + this.sink + ")";
        }
    }
}
