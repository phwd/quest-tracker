package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.ForOverride;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.io.Serializable;
import java.util.Iterator;
import org.checkerframework.checker.nullness.compatqual.MonotonicNonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
public abstract class Converter<A, B> implements Function<A, B> {
    private final boolean handleNullAutomatically;
    @LazyInit
    @MonotonicNonNullDecl
    private transient Converter<B, A> reverse;

    /* access modifiers changed from: protected */
    @ForOverride
    public abstract A doBackward(B b);

    /* access modifiers changed from: protected */
    @ForOverride
    public abstract B doForward(A a);

    protected Converter() {
        this(true);
    }

    Converter(boolean z) {
        this.handleNullAutomatically = z;
    }

    @CanIgnoreReturnValue
    @NullableDecl
    public final B convert(@NullableDecl A a) {
        return correctedDoForward(a);
    }

    /* access modifiers changed from: package-private */
    @NullableDecl
    public B correctedDoForward(@NullableDecl A a) {
        if (!this.handleNullAutomatically) {
            return doForward(a);
        }
        if (a == null) {
            return null;
        }
        return (B) Preconditions.checkNotNull(doForward(a));
    }

    /* access modifiers changed from: package-private */
    @NullableDecl
    public A correctedDoBackward(@NullableDecl B b) {
        if (!this.handleNullAutomatically) {
            return doBackward(b);
        }
        if (b == null) {
            return null;
        }
        return (A) Preconditions.checkNotNull(doBackward(b));
    }

    @CanIgnoreReturnValue
    public Iterable<B> convertAll(final Iterable<? extends A> iterable) {
        Preconditions.checkNotNull(iterable, "fromIterable");
        return new Iterable<B>() {
            /* class com.google.common.base.Converter.AnonymousClass1 */

            @Override // java.lang.Iterable
            public Iterator<B> iterator() {
                return new Iterator<B>() {
                    /* class com.google.common.base.Converter.AnonymousClass1.AnonymousClass1 */
                    private final Iterator<? extends A> fromIterator = iterable.iterator();

                    public boolean hasNext() {
                        return this.fromIterator.hasNext();
                    }

                    /* JADX DEBUG: Multi-variable search result rejected for r0v1, resolved type: com.google.common.base.Converter */
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.util.Iterator
                    public B next() {
                        return (B) Converter.this.convert(this.fromIterator.next());
                    }

                    public void remove() {
                        this.fromIterator.remove();
                    }
                };
            }
        };
    }

    @CanIgnoreReturnValue
    public Converter<B, A> reverse() {
        Converter<B, A> converter = this.reverse;
        if (converter != null) {
            return converter;
        }
        ReverseConverter reverseConverter = new ReverseConverter(this);
        this.reverse = reverseConverter;
        return reverseConverter;
    }

    private static final class ReverseConverter<A, B> extends Converter<B, A> implements Serializable {
        private static final long serialVersionUID = 0;
        final Converter<A, B> original;

        ReverseConverter(Converter<A, B> converter) {
            this.original = converter;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.base.Converter
        public A doForward(B b) {
            throw new AssertionError();
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.base.Converter
        public B doBackward(A a) {
            throw new AssertionError();
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.base.Converter
        @NullableDecl
        public A correctedDoForward(@NullableDecl B b) {
            return this.original.correctedDoBackward(b);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.base.Converter
        @NullableDecl
        public B correctedDoBackward(@NullableDecl A a) {
            return this.original.correctedDoForward(a);
        }

        @Override // com.google.common.base.Converter
        public Converter<A, B> reverse() {
            return this.original;
        }

        @Override // com.google.common.base.Function, com.google.common.base.Converter
        public boolean equals(@NullableDecl Object obj) {
            if (obj instanceof ReverseConverter) {
                return this.original.equals(((ReverseConverter) obj).original);
            }
            return false;
        }

        public int hashCode() {
            return this.original.hashCode() ^ -1;
        }

        public String toString() {
            return this.original + ".reverse()";
        }
    }

    public final <C> Converter<A, C> andThen(Converter<B, C> converter) {
        return doAndThen(converter);
    }

    /* access modifiers changed from: package-private */
    public <C> Converter<A, C> doAndThen(Converter<B, C> converter) {
        return new ConverterComposition(this, (Converter) Preconditions.checkNotNull(converter));
    }

    /* access modifiers changed from: private */
    public static final class ConverterComposition<A, B, C> extends Converter<A, C> implements Serializable {
        private static final long serialVersionUID = 0;
        final Converter<A, B> first;
        final Converter<B, C> second;

        ConverterComposition(Converter<A, B> converter, Converter<B, C> converter2) {
            this.first = converter;
            this.second = converter2;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.base.Converter
        public C doForward(A a) {
            throw new AssertionError();
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.base.Converter
        public A doBackward(C c) {
            throw new AssertionError();
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.base.Converter
        @NullableDecl
        public C correctedDoForward(@NullableDecl A a) {
            return this.second.correctedDoForward(this.first.correctedDoForward(a));
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.base.Converter
        @NullableDecl
        public A correctedDoBackward(@NullableDecl C c) {
            return this.first.correctedDoBackward(this.second.correctedDoBackward(c));
        }

        @Override // com.google.common.base.Function, com.google.common.base.Converter
        public boolean equals(@NullableDecl Object obj) {
            if (!(obj instanceof ConverterComposition)) {
                return false;
            }
            ConverterComposition converterComposition = (ConverterComposition) obj;
            if (!this.first.equals(converterComposition.first) || !this.second.equals(converterComposition.second)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return (this.first.hashCode() * 31) + this.second.hashCode();
        }

        public String toString() {
            return this.first + ".andThen(" + this.second + ")";
        }
    }

    @Override // com.google.common.base.Function
    @NullableDecl
    @Deprecated
    @CanIgnoreReturnValue
    public final B apply(@NullableDecl A a) {
        return convert(a);
    }

    @Override // com.google.common.base.Function
    public boolean equals(@NullableDecl Object obj) {
        return super.equals(obj);
    }

    public static <A, B> Converter<A, B> from(Function<? super A, ? extends B> function, Function<? super B, ? extends A> function2) {
        return new FunctionBasedConverter(function, function2);
    }

    private static final class FunctionBasedConverter<A, B> extends Converter<A, B> implements Serializable {
        private final Function<? super B, ? extends A> backwardFunction;
        private final Function<? super A, ? extends B> forwardFunction;

        private FunctionBasedConverter(Function<? super A, ? extends B> function, Function<? super B, ? extends A> function2) {
            this.forwardFunction = (Function) Preconditions.checkNotNull(function);
            this.backwardFunction = (Function) Preconditions.checkNotNull(function2);
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.base.Converter
        public B doForward(A a) {
            return (B) this.forwardFunction.apply(a);
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.base.Converter
        public A doBackward(B b) {
            return (A) this.backwardFunction.apply(b);
        }

        @Override // com.google.common.base.Function, com.google.common.base.Converter
        public boolean equals(@NullableDecl Object obj) {
            if (!(obj instanceof FunctionBasedConverter)) {
                return false;
            }
            FunctionBasedConverter functionBasedConverter = (FunctionBasedConverter) obj;
            if (!this.forwardFunction.equals(functionBasedConverter.forwardFunction) || !this.backwardFunction.equals(functionBasedConverter.backwardFunction)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return (this.forwardFunction.hashCode() * 31) + this.backwardFunction.hashCode();
        }

        public String toString() {
            return "Converter.from(" + this.forwardFunction + ", " + this.backwardFunction + ")";
        }
    }

    public static <T> Converter<T, T> identity() {
        return IdentityConverter.INSTANCE;
    }

    private static final class IdentityConverter<T> extends Converter<T, T> implements Serializable {
        static final IdentityConverter INSTANCE = new IdentityConverter();
        private static final long serialVersionUID = 0;

        /* access modifiers changed from: protected */
        @Override // com.google.common.base.Converter
        public T doBackward(T t) {
            return t;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.base.Converter
        public T doForward(T t) {
            return t;
        }

        @Override // com.google.common.base.Converter
        public IdentityConverter<T> reverse() {
            return this;
        }

        public String toString() {
            return "Converter.identity()";
        }

        private IdentityConverter() {
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.base.Converter
        public <S> Converter<T, S> doAndThen(Converter<T, S> converter) {
            return (Converter) Preconditions.checkNotNull(converter, "otherConverter");
        }

        private Object readResolve() {
            return INSTANCE;
        }
    }
}
