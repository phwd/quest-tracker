package com.google.common.collect;

public abstract class ComparisonChain {
    private static final ComparisonChain ACTIVE = new ComparisonChain() {
        /* class com.google.common.collect.ComparisonChain.AnonymousClass1 */

        @Override // com.google.common.collect.ComparisonChain
        public int result() {
            return 0;
        }

        @Override // com.google.common.collect.ComparisonChain
        public ComparisonChain compare(Comparable comparable, Comparable comparable2) {
            return classify(comparable.compareTo(comparable2));
        }

        /* access modifiers changed from: package-private */
        public ComparisonChain classify(int i) {
            if (i < 0) {
                return ComparisonChain.LESS;
            }
            return i > 0 ? ComparisonChain.GREATER : ComparisonChain.ACTIVE;
        }
    };
    private static final ComparisonChain GREATER = new InactiveComparisonChain(1);
    private static final ComparisonChain LESS = new InactiveComparisonChain(-1);

    public abstract ComparisonChain compare(Comparable<?> comparable, Comparable<?> comparable2);

    public abstract int result();

    private ComparisonChain() {
    }

    public static ComparisonChain start() {
        return ACTIVE;
    }

    private static final class InactiveComparisonChain extends ComparisonChain {
        final int result;

        @Override // com.google.common.collect.ComparisonChain
        public ComparisonChain compare(Comparable comparable, Comparable comparable2) {
            return this;
        }

        InactiveComparisonChain(int i) {
            super();
            this.result = i;
        }

        @Override // com.google.common.collect.ComparisonChain
        public int result() {
            return this.result;
        }
    }
}
