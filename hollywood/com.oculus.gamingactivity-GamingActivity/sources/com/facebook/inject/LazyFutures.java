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
    public static <T> Lazy<ListenableFuture<T>> wrap(final ListenableFuture<T> future) {
        return new Lazy<ListenableFuture<T>>() {
            /* class com.facebook.inject.LazyFutures.AnonymousClass1 */

            @Override // com.facebook.inject.Lazy, javax.inject.Provider
            public ListenableFuture<T> get() {
                return ListenableFuture.this;
            }
        };
    }

    public static <T, E> Lazy<ListenableFuture<T>> transform(final Lazy<? extends ListenableFuture<E>> fromLazyFuture, final Function<E, T> function, final Executor executor) {
        return new LazyFuture<T>() {
            /* class com.facebook.inject.LazyFutures.AnonymousClass2 */

            /* access modifiers changed from: protected */
            @Override // com.facebook.inject.LazyFuture
            public ListenableFuture<T> createFuture() {
                return Futures.transform((ListenableFuture) Lazy.this.get(), function, executor);
            }
        };
    }

    public static <T, E> Lazy<ListenableFuture<T>> transform(ListenableFuture<E> fromFuture, Function<E, T> function, Executor executor) {
        return transform(wrap(fromFuture), function, executor);
    }

    public static <T, E> Lazy<ListenableFuture<T>> transform(final Lazy<? extends ListenableFuture<E>> fromLazyFuture, final AsyncFunction<E, T> function, final Executor executor) {
        return new LazyFuture<T>() {
            /* class com.facebook.inject.LazyFutures.AnonymousClass3 */

            /* access modifiers changed from: protected */
            @Override // com.facebook.inject.LazyFuture
            public ListenableFuture<T> createFuture() {
                return Futures.transformAsync((ListenableFuture) Lazy.this.get(), function, executor);
            }
        };
    }

    public static <T, E> Lazy<ListenableFuture<T>> transform(ListenableFuture<E> fromFuture, AsyncFunction<E, T> function, Executor executor) {
        return transform(wrap(fromFuture), function, executor);
    }

    public static ListBuilder listBuilder() {
        return new ListBuilder();
    }

    public static class ListBuilder {
        private final List<Lazy<ListenableFuture<Object>>> mLazies = Lists.newLinkedList();

        public ListBuilder add(final Lazy<? extends ListenableFuture<?>> lazyFuture) {
            this.mLazies.add(new Lazy<ListenableFuture<Object>>() {
                /* class com.facebook.inject.LazyFutures.ListBuilder.AnonymousClass1 */

                @Override // com.facebook.inject.Lazy, javax.inject.Provider
                public ListenableFuture<Object> get() {
                    return (ListenableFuture) lazyFuture.get();
                }
            });
            return this;
        }

        public ListBuilder add(final ListenableFuture<?> future) {
            this.mLazies.add(new Lazy<ListenableFuture<Object>>() {
                /* class com.facebook.inject.LazyFutures.ListBuilder.AnonymousClass2 */

                @Override // com.facebook.inject.Lazy, javax.inject.Provider
                public ListenableFuture<Object> get() {
                    return future;
                }
            });
            return this;
        }

        public Lazy<ListenableFuture<List<Object>>> build() {
            final ImmutableList<Lazy<ListenableFuture<Object>>> lazies = ImmutableList.copyOf((Collection) this.mLazies);
            return new LazyFuture<List<Object>>() {
                /* class com.facebook.inject.LazyFutures.ListBuilder.AnonymousClass3 */

                /* access modifiers changed from: protected */
                @Override // com.facebook.inject.LazyFuture
                public ListenableFuture<List<Object>> createFuture() {
                    return Futures.allAsList(Lists.transform(lazies, new Function<Lazy<ListenableFuture<Object>>, ListenableFuture<Object>>() {
                        /* class com.facebook.inject.LazyFutures.ListBuilder.AnonymousClass3.AnonymousClass1 */

                        public ListenableFuture<Object> apply(Lazy<ListenableFuture<Object>> input) {
                            return input.get();
                        }
                    }));
                }
            };
        }

        public <T> Lazy<ListenableFuture<T>> buildAndTransform(Function<List<Object>, T> function, Executor executor) {
            return LazyFutures.transform(build(), function, executor);
        }

        public <T> Lazy<ListenableFuture<T>> buildAndTransform(AsyncFunction<List<Object>, T> function, Executor executor) {
            return LazyFutures.transform(build(), function, executor);
        }
    }
}
