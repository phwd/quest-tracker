package com.facebook.inject;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Executor;

public class LazyFutures {
    public static <T> Lazy<ListenableFuture<T>> wrap(final ListenableFuture<T> listenableFuture) {
        return new Lazy<ListenableFuture<T>>() {
            /* class com.facebook.inject.LazyFutures.AnonymousClass1 */

            @Override // com.facebook.inject.Lazy, javax.inject.Provider
            public ListenableFuture<T> get() {
                return ListenableFuture.this;
            }
        };
    }

    public static <T, E> Lazy<ListenableFuture<T>> transform(final Lazy<? extends ListenableFuture<E>> lazy, final Function<E, T> function, final Executor executor) {
        return new LazyFuture<T>() {
            /* class com.facebook.inject.LazyFutures.AnonymousClass2 */

            /* access modifiers changed from: protected */
            @Override // com.facebook.inject.LazyFuture
            public ListenableFuture<T> createFuture() {
                return Futures.transform((ListenableFuture) Lazy.this.get(), function, executor);
            }
        };
    }

    public static <T, E> Lazy<ListenableFuture<T>> transform(ListenableFuture<E> listenableFuture, Function<E, T> function, Executor executor) {
        return transform(wrap(listenableFuture), function, executor);
    }

    public static <T, E> Lazy<ListenableFuture<T>> transform(final Lazy<? extends ListenableFuture<E>> lazy, final AsyncFunction<E, T> asyncFunction, final Executor executor) {
        return new LazyFuture<T>() {
            /* class com.facebook.inject.LazyFutures.AnonymousClass3 */

            /* access modifiers changed from: protected */
            @Override // com.facebook.inject.LazyFuture
            public ListenableFuture<T> createFuture() {
                return Futures.transformAsync((ListenableFuture) Lazy.this.get(), asyncFunction, executor);
            }
        };
    }

    public static <T, E> Lazy<ListenableFuture<T>> transform(ListenableFuture<E> listenableFuture, AsyncFunction<E, T> asyncFunction, Executor executor) {
        return transform(wrap(listenableFuture), asyncFunction, executor);
    }

    public static ListBuilder listBuilder() {
        return new ListBuilder();
    }

    public static class ListBuilder {
        private final List<Lazy<ListenableFuture<Object>>> mLazies = Lists.newLinkedList();

        public ListBuilder add(final Lazy<? extends ListenableFuture<?>> lazy) {
            this.mLazies.add(new Lazy<ListenableFuture<Object>>() {
                /* class com.facebook.inject.LazyFutures.ListBuilder.AnonymousClass1 */

                @Override // com.facebook.inject.Lazy, javax.inject.Provider
                public ListenableFuture<Object> get() {
                    return (ListenableFuture) lazy.get();
                }
            });
            return this;
        }

        public ListBuilder add(final ListenableFuture<?> listenableFuture) {
            this.mLazies.add(new Lazy<ListenableFuture<Object>>() {
                /* class com.facebook.inject.LazyFutures.ListBuilder.AnonymousClass2 */

                @Override // com.facebook.inject.Lazy, javax.inject.Provider
                public ListenableFuture<Object> get() {
                    return listenableFuture;
                }
            });
            return this;
        }

        public Lazy<ListenableFuture<List<Object>>> build() {
            final ImmutableList copyOf = ImmutableList.copyOf((Collection) this.mLazies);
            return new LazyFuture<List<Object>>() {
                /* class com.facebook.inject.LazyFutures.ListBuilder.AnonymousClass3 */

                /* access modifiers changed from: protected */
                @Override // com.facebook.inject.LazyFuture
                public ListenableFuture<List<Object>> createFuture() {
                    return Futures.allAsList(Lists.transform(copyOf, new Function<Lazy<ListenableFuture<Object>>, ListenableFuture<Object>>() {
                        /* class com.facebook.inject.LazyFutures.ListBuilder.AnonymousClass3.AnonymousClass1 */

                        public ListenableFuture<Object> apply(Lazy<ListenableFuture<Object>> lazy) {
                            return lazy.get();
                        }
                    }));
                }
            };
        }

        public <T> Lazy<ListenableFuture<T>> buildAndTransform(Function<List<Object>, T> function, Executor executor) {
            return LazyFutures.transform(build(), function, executor);
        }

        public <T> Lazy<ListenableFuture<T>> buildAndTransform(AsyncFunction<List<Object>, T> asyncFunction, Executor executor) {
            return LazyFutures.transform(build(), asyncFunction, executor);
        }
    }
}
