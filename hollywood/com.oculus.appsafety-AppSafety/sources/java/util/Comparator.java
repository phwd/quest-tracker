package java.util;

import java.lang.invoke.SerializedLambda;
import java.util.Comparators;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

@FunctionalInterface
public interface Comparator<T> {
    int compare(T t, T t2);

    boolean equals(Object obj);

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    private static /* synthetic */ default Object $deserializeLambda$(SerializedLambda lambda) {
        char c;
        String implMethodName = lambda.getImplMethodName();
        switch (implMethodName.hashCode()) {
            case -1669453324:
                if (implMethodName.equals("lambda$comparing$77a9974f$1")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -902634255:
                if (implMethodName.equals("lambda$comparingLong$6043328a$1")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -860761860:
                if (implMethodName.equals("lambda$thenComparing$36697e65$1")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -564296216:
                if (implMethodName.equals("lambda$comparing$ea9a8b3a$1")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 826214802:
                if (implMethodName.equals("lambda$comparingInt$7b0bb60$1")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1964526692:
                if (implMethodName.equals("lambda$comparingDouble$8dcf42ea$1")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c != 0) {
            if (c != 1) {
                if (c != 2) {
                    if (c != 3) {
                        if (c != 4) {
                            if (c == 5 && lambda.getImplMethodKind() == 6 && lambda.getFunctionalInterfaceClass().equals("java/util/Comparator") && lambda.getFunctionalInterfaceMethodName().equals("compare") && lambda.getFunctionalInterfaceMethodSignature().equals("(Ljava/lang/Object;Ljava/lang/Object;)I") && lambda.getImplClass().equals("java/util/Comparator") && lambda.getImplMethodSignature().equals("(Ljava/util/function/ToIntFunction;Ljava/lang/Object;Ljava/lang/Object;)I")) {
                                return new Object() {
                                    /* class java.util.$$Lambda$Comparator$DNgpxUFZqmT4lOBzlVyPjWwvEvw */

                                    @Override // java.util.Comparator
                                    public final int compare(Object obj, Object obj2) {
                                        ToIntFunction toIntFunction;
                                        return Integer.compare(toIntFunction.applyAsInt(obj), ToIntFunction.this.applyAsInt(obj2));
                                    }
                                };
                            }
                        } else if (lambda.getImplMethodKind() == 7 && lambda.getFunctionalInterfaceClass().equals("java/util/Comparator") && lambda.getFunctionalInterfaceMethodName().equals("compare") && lambda.getFunctionalInterfaceMethodSignature().equals("(Ljava/lang/Object;Ljava/lang/Object;)I") && lambda.getImplClass().equals("java/util/Comparator") && lambda.getImplMethodSignature().equals("(Ljava/util/Comparator;Ljava/lang/Object;Ljava/lang/Object;)I")) {
                            return new Object((Comparator) lambda.getCapturedArg(1)) {
                                /* class java.util.$$Lambda$Comparator$BZSVCoA8i87ehjxxZ1weEounfDQ */
                                private final /* synthetic */ Comparator f$1;

                                {
                                    this.f$1 = r2;
                                }

                                @Override // java.util.Comparator
                                public final int compare(Object obj, Object obj2) {
                                    return Comparator.lambda$thenComparing$36697e65$1(Comparator.this, this.f$1, obj, obj2);
                                }
                            };
                        }
                    } else if (lambda.getImplMethodKind() == 6 && lambda.getFunctionalInterfaceClass().equals("java/util/Comparator") && lambda.getFunctionalInterfaceMethodName().equals("compare") && lambda.getFunctionalInterfaceMethodSignature().equals("(Ljava/lang/Object;Ljava/lang/Object;)I") && lambda.getImplClass().equals("java/util/Comparator") && lambda.getImplMethodSignature().equals("(Ljava/util/function/ToDoubleFunction;Ljava/lang/Object;Ljava/lang/Object;)I")) {
                        return new Object() {
                            /* class java.util.$$Lambda$Comparator$edSxqANnwdmzeJ1aMMcwJWE2wII */

                            @Override // java.util.Comparator
                            public final int compare(Object obj, Object obj2) {
                                ToDoubleFunction toDoubleFunction;
                                return Double.compare(toDoubleFunction.applyAsDouble(obj), ToDoubleFunction.this.applyAsDouble(obj2));
                            }
                        };
                    }
                } else if (lambda.getImplMethodKind() == 6 && lambda.getFunctionalInterfaceClass().equals("java/util/Comparator") && lambda.getFunctionalInterfaceMethodName().equals("compare") && lambda.getFunctionalInterfaceMethodSignature().equals("(Ljava/lang/Object;Ljava/lang/Object;)I") && lambda.getImplClass().equals("java/util/Comparator") && lambda.getImplMethodSignature().equals("(Ljava/util/function/Function;Ljava/lang/Object;Ljava/lang/Object;)I")) {
                    return new Object() {
                        /* class java.util.$$Lambda$Comparator$SPB8K9Yj7Pw1mljm7LpasV7zxWw */

                        @Override // java.util.Comparator
                        public final int compare(Object obj, Object obj2) {
                            Function function;
                            return ((Comparable) function.apply(obj)).compareTo(Function.this.apply(obj2));
                        }
                    };
                }
            } else if (lambda.getImplMethodKind() == 6 && lambda.getFunctionalInterfaceClass().equals("java/util/Comparator") && lambda.getFunctionalInterfaceMethodName().equals("compare") && lambda.getFunctionalInterfaceMethodSignature().equals("(Ljava/lang/Object;Ljava/lang/Object;)I") && lambda.getImplClass().equals("java/util/Comparator") && lambda.getImplMethodSignature().equals("(Ljava/util/Comparator;Ljava/util/function/Function;Ljava/lang/Object;Ljava/lang/Object;)I")) {
                return new Object((Function) lambda.getCapturedArg(1)) {
                    /* class java.util.$$Lambda$Comparator$KVN0LWz1D1wyrL2gs1CbubvLa9o */
                    private final /* synthetic */ Function f$1;

                    {
                        this.f$1 = r2;
                    }

                    @Override // java.util.Comparator
                    public final int compare(Object obj, Object obj2) {
                        Function function;
                        return Comparator.this.compare(function.apply(obj), this.f$1.apply(obj2));
                    }
                };
            }
        } else if (lambda.getImplMethodKind() == 6 && lambda.getFunctionalInterfaceClass().equals("java/util/Comparator") && lambda.getFunctionalInterfaceMethodName().equals("compare") && lambda.getFunctionalInterfaceMethodSignature().equals("(Ljava/lang/Object;Ljava/lang/Object;)I") && lambda.getImplClass().equals("java/util/Comparator") && lambda.getImplMethodSignature().equals("(Ljava/util/function/ToLongFunction;Ljava/lang/Object;Ljava/lang/Object;)I")) {
            return new Object() {
                /* class java.util.$$Lambda$Comparator$4V5k8aLimtS0VsEILEAqQ9UGZYo */

                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    ToLongFunction toLongFunction;
                    return Long.compare(toLongFunction.applyAsLong(obj), ToLongFunction.this.applyAsLong(obj2));
                }
            };
        }
        throw new IllegalArgumentException("Invalid lambda deserialization");
    }

    default Comparator<T> reversed() {
        return Collections.reverseOrder(this);
    }

    default Comparator<T> thenComparing(Comparator<? super T> other) {
        Objects.requireNonNull(other);
        return new Object(other) {
            /* class java.util.$$Lambda$Comparator$BZSVCoA8i87ehjxxZ1weEounfDQ */
            private final /* synthetic */ Comparator f$1;

            {
                this.f$1 = r2;
            }

            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return Comparator.lambda$thenComparing$36697e65$1(Comparator.this, this.f$1, obj, obj2);
            }
        };
    }

    /* JADX INFO: Multiple debug info for r2v0 java.util.Comparator: [D('this' java.util.Comparator<T>), D('_this' java.util.Comparator)] */
    static /* synthetic */ default int lambda$thenComparing$36697e65$1(Comparator _this, Comparator other, Object c1, Object c2) {
        int res = _this.compare(c1, c2);
        return res != 0 ? res : other.compare(c1, c2);
    }

    default <U> Comparator<T> thenComparing(Function<? super T, ? extends U> keyExtractor, Comparator<? super U> keyComparator) {
        return thenComparing(comparing(keyExtractor, keyComparator));
    }

    default <U extends Comparable<? super U>> Comparator<T> thenComparing(Function<? super T, ? extends U> keyExtractor) {
        return thenComparing(comparing(keyExtractor));
    }

    default Comparator<T> thenComparingInt(ToIntFunction<? super T> keyExtractor) {
        return thenComparing(comparingInt(keyExtractor));
    }

    default Comparator<T> thenComparingLong(ToLongFunction<? super T> keyExtractor) {
        return thenComparing(comparingLong(keyExtractor));
    }

    default Comparator<T> thenComparingDouble(ToDoubleFunction<? super T> keyExtractor) {
        return thenComparing(comparingDouble(keyExtractor));
    }

    static default <T extends Comparable<? super T>> Comparator<T> reverseOrder() {
        return Collections.reverseOrder();
    }

    static default <T extends Comparable<? super T>> Comparator<T> naturalOrder() {
        return Comparators.NaturalOrderComparator.INSTANCE;
    }

    static default <T> Comparator<T> nullsFirst(Comparator<? super T> comparator) {
        return new Comparators.NullComparator(true, comparator);
    }

    static default <T> Comparator<T> nullsLast(Comparator<? super T> comparator) {
        return new Comparators.NullComparator(false, comparator);
    }

    static default <T, U> Comparator<T> comparing(Function<? super T, ? extends U> keyExtractor, Comparator<? super U> keyComparator) {
        Objects.requireNonNull(keyExtractor);
        Objects.requireNonNull(keyComparator);
        return new Object(keyExtractor) {
            /* class java.util.$$Lambda$Comparator$KVN0LWz1D1wyrL2gs1CbubvLa9o */
            private final /* synthetic */ Function f$1;

            {
                this.f$1 = r2;
            }

            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                Function function;
                return Comparator.this.compare(function.apply(obj), this.f$1.apply(obj2));
            }
        };
    }

    static default <T, U extends Comparable<? super U>> Comparator<T> comparing(Function<? super T, ? extends U> keyExtractor) {
        Objects.requireNonNull(keyExtractor);
        return new Object() {
            /* class java.util.$$Lambda$Comparator$SPB8K9Yj7Pw1mljm7LpasV7zxWw */

            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                Function function;
                return ((Comparable) function.apply(obj)).compareTo(Function.this.apply(obj2));
            }
        };
    }

    static default <T> Comparator<T> comparingInt(ToIntFunction<? super T> keyExtractor) {
        Objects.requireNonNull(keyExtractor);
        return new Object() {
            /* class java.util.$$Lambda$Comparator$DNgpxUFZqmT4lOBzlVyPjWwvEvw */

            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                ToIntFunction toIntFunction;
                return Integer.compare(toIntFunction.applyAsInt(obj), ToIntFunction.this.applyAsInt(obj2));
            }
        };
    }

    static default <T> Comparator<T> comparingLong(ToLongFunction<? super T> keyExtractor) {
        Objects.requireNonNull(keyExtractor);
        return new Object() {
            /* class java.util.$$Lambda$Comparator$4V5k8aLimtS0VsEILEAqQ9UGZYo */

            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                ToLongFunction toLongFunction;
                return Long.compare(toLongFunction.applyAsLong(obj), ToLongFunction.this.applyAsLong(obj2));
            }
        };
    }

    static default <T> Comparator<T> comparingDouble(ToDoubleFunction<? super T> keyExtractor) {
        Objects.requireNonNull(keyExtractor);
        return new Object() {
            /* class java.util.$$Lambda$Comparator$edSxqANnwdmzeJ1aMMcwJWE2wII */

            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                ToDoubleFunction toDoubleFunction;
                return Double.compare(toDoubleFunction.applyAsDouble(obj), ToDoubleFunction.this.applyAsDouble(obj2));
            }
        };
    }
}
