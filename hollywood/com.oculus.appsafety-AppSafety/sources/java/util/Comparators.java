package java.util;

import java.io.Serializable;

class Comparators {
    private Comparators() {
        throw new AssertionError((Object) "no instances");
    }

    /* access modifiers changed from: package-private */
    public enum NaturalOrderComparator implements Comparator<Comparable<Object>> {
        INSTANCE;

        public int compare(Comparable<Object> c1, Comparable<Object> c2) {
            return c1.compareTo(c2);
        }

        @Override // java.util.Comparator
        public Comparator<Comparable<Object>> reversed() {
            return Comparator.reverseOrder();
        }
    }

    static final class NullComparator<T> implements Comparator<T>, Serializable {
        private static final long serialVersionUID = -7569533591570686392L;
        private final boolean nullFirst;
        private final Comparator<T> real;

        /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.util.Comparator<? super T> */
        /* JADX WARN: Multi-variable type inference failed */
        NullComparator(boolean nullFirst2, Comparator<? super T> real2) {
            this.nullFirst = nullFirst2;
            this.real = real2;
        }

        @Override // java.util.Comparator
        public int compare(T a, T b) {
            if (a == null) {
                if (b == null) {
                    return 0;
                }
                return this.nullFirst ? -1 : 1;
            } else if (b == null) {
                return this.nullFirst ? 1 : -1;
            } else {
                Comparator<T> comparator = this.real;
                if (comparator == null) {
                    return 0;
                }
                return comparator.compare(a, b);
            }
        }

        @Override // java.util.Comparator
        public Comparator<T> thenComparing(Comparator<? super T> other) {
            Objects.requireNonNull(other);
            boolean z = this.nullFirst;
            Comparator<T> comparator = this.real;
            return new NullComparator(z, comparator == null ? other : comparator.thenComparing(other));
        }

        @Override // java.util.Comparator
        public Comparator<T> reversed() {
            boolean z = !this.nullFirst;
            Comparator<T> comparator = this.real;
            return new NullComparator(z, comparator == null ? null : comparator.reversed());
        }
    }
}
