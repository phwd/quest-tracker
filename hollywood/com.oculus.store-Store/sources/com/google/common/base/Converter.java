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

    Converter(boolean handleNullAutomatically2) {
        this.handleNullAutomatically = handleNullAutomatically2;
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
    public Iterable<B> convertAll(final Iterable<? extends A> fromIterable) {
        Preconditions.checkNotNull(fromIterable, "fromIterable");
        return new Iterable<B>() {
            /* class com.google.common.base.Converter.AnonymousClass1 */

            @Override // java.lang.Iterable
            public Iterator<B> iterator() {
                return new Iterator<B>() {
                    /* class com.google.common.base.Converter.AnonymousClass1.AnonymousClass1 */
                    private final Iterator<? extends A> fromIterator = fromIterable.iterator();

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
        Converter<B, A> result = this.reverse;
        if (result != null) {
            return result;
        }
        Converter<B, A> result2 = new ReverseConverter<>(this);
        this.reverse = result2;
        return result2;
    }

    private static final class ReverseConverter<A, B> extends Converter<B, A> implements Serializable {
        private static final long serialVersionUID = 0;
        final Converter<A, B> original;

        ReverseConverter(Converter<A, B> original2) {
            this.original = original2;
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
        public boolean equals(@NullableDecl Object object) {
            if (object instanceof ReverseConverter) {
                return this.original.equals(((ReverseConverter) object).original);
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

    public final <C> Converter<A, C> andThen(Converter<B, C> secondConverter) {
        return doAndThen(secondConverter);
    }

    /* access modifiers changed from: package-private */
    public <C> Converter<A, C> doAndThen(Converter<B, C> secondConverter) {
        return new ConverterComposition(this, (Converter) Preconditions.checkNotNull(secondConverter));
    }

    /* access modifiers changed from: private */
    public static final class ConverterComposition<A, B, C> extends Converter<A, C> implements Serializable {
        private static final long serialVersionUID = 0;
        final Converter<A, B> first;
        final Converter<B, C> second;

        ConverterComposition(Converter<A, B> first2, Converter<B, C> second2) {
            this.first = first2;
            this.second = second2;
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
        public boolean equals(@NullableDecl Object object) {
            if (!(object instanceof ConverterComposition)) {
                return false;
            }
            ConverterComposition<?, ?, ?> that = (ConverterComposition) object;
            if (!this.first.equals(that.first) || !this.second.equals(that.second)) {
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
    public boolean equals(@NullableDecl Object object) {
        return super.equals(object);
    }

    public static <A, B> Converter<A, B> from(Function<? super A, ? extends B> forwardFunction, Function<? super B, ? extends A> backwardFunction) {
        return new FunctionBasedConverter(forwardFunction, backwardFunction);
    }

    private static final class FunctionBasedConverter<A, B> extends Converter<A, B> implements Serializable {
        private final Function<? super B, ? extends A> backwardFunction;
        private final Function<? super A, ? extends B> forwardFunction;

        private FunctionBasedConverter(Function<? super A, ? extends B> forwardFunction2, Function<? super B, ? extends A> backwardFunction2) {
            this.forwardFunction = (Function) Preconditions.checkNotNull(forwardFunction2);
            this.backwardFunction = (Function) Preconditions.checkNotNull(backwardFunction2);
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
        public boolean equals(@NullableDecl Object object) {
            if (!(object instanceof FunctionBasedConverter)) {
                return false;
            }
            FunctionBasedConverter<?, ?> that = (FunctionBasedConverter) object;
            if (!this.forwardFunction.equals(that.forwardFunction) || !this.backwardFunction.equals(that.backwardFunction)) {
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

        private IdentityConverter() {
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.base.Converter
        public T doForward(T t) {
            return t;
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.base.Converter
        public T doBackward(T t) {
            return t;
        }

        @Override // com.google.common.base.Converter
        public IdentityConverter<T> reverse() {
            return this;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.base.Converter
        public <S> Converter<T, S> doAndThen(Converter<T, S> otherConverter) {
            return (Converter) Preconditions.checkNotNull(otherConverter, "otherConverter");
        }

        public String toString() {
            return "Converter.identity()";
        }

        private Object readResolve() {
            return INSTANCE;
        }
    }
}
