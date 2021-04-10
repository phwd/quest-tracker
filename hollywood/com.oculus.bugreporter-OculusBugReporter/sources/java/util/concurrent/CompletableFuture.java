package java.util.concurrent;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.locks.LockSupport;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import sun.misc.Unsafe;

public class CompletableFuture<T> implements Future<T>, CompletionStage<T> {
    static final int ASYNC = 1;
    private static final Executor ASYNC_POOL;
    static final int NESTED = -1;
    private static final long NEXT;
    static final AltResult NIL = new AltResult(null);
    private static final long RESULT;
    static final int SPINS;
    private static final long STACK;
    static final int SYNC = 0;
    private static final Unsafe U = Unsafe.getUnsafe();
    private static final boolean USE_COMMON_POOL = (ForkJoinPool.getCommonPoolParallelism() > 1);
    volatile Object result;
    volatile Completion stack;

    public interface AsynchronousCompletionTask {
    }

    /* access modifiers changed from: package-private */
    public final boolean internalComplete(Object r) {
        return U.compareAndSwapObject(this, RESULT, null, r);
    }

    /* access modifiers changed from: package-private */
    public final boolean casStack(Completion cmp, Completion val) {
        return U.compareAndSwapObject(this, STACK, cmp, val);
    }

    /* access modifiers changed from: package-private */
    public final boolean tryPushStack(Completion c) {
        Completion h = this.stack;
        lazySetNext(c, h);
        return U.compareAndSwapObject(this, STACK, h, c);
    }

    /* access modifiers changed from: package-private */
    public final void pushStack(Completion c) {
        do {
        } while (!tryPushStack(c));
    }

    /* access modifiers changed from: package-private */
    public static final class AltResult {
        final Throwable ex;

        AltResult(Throwable x) {
            this.ex = x;
        }
    }

    static {
        Executor executor;
        int i = 0;
        if (USE_COMMON_POOL) {
            executor = ForkJoinPool.commonPool();
        } else {
            executor = new ThreadPerTaskExecutor();
        }
        ASYNC_POOL = executor;
        if (Runtime.getRuntime().availableProcessors() > 1) {
            i = 256;
        }
        SPINS = i;
        try {
            RESULT = U.objectFieldOffset(CompletableFuture.class.getDeclaredField("result"));
            STACK = U.objectFieldOffset(CompletableFuture.class.getDeclaredField("stack"));
            NEXT = U.objectFieldOffset(Completion.class.getDeclaredField("next"));
        } catch (ReflectiveOperationException e) {
            throw new Error(e);
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean completeNull() {
        return U.compareAndSwapObject(this, RESULT, null, NIL);
    }

    /* access modifiers changed from: package-private */
    public final Object encodeValue(T t) {
        return t == null ? NIL : t;
    }

    /* access modifiers changed from: package-private */
    public final boolean completeValue(T t) {
        return U.compareAndSwapObject(this, RESULT, null, t == null ? NIL : t);
    }

    static AltResult encodeThrowable(Throwable x) {
        Throwable th;
        if (x instanceof CompletionException) {
            th = x;
        } else {
            th = new CompletionException(x);
        }
        return new AltResult(th);
    }

    /* access modifiers changed from: package-private */
    public final boolean completeThrowable(Throwable x) {
        return U.compareAndSwapObject(this, RESULT, null, encodeThrowable(x));
    }

    static Object encodeThrowable(Throwable x, Object r) {
        if (!(x instanceof CompletionException)) {
            x = new CompletionException(x);
        } else if ((r instanceof AltResult) && x == ((AltResult) r).ex) {
            return r;
        }
        return new AltResult(x);
    }

    /* access modifiers changed from: package-private */
    public final boolean completeThrowable(Throwable x, Object r) {
        return U.compareAndSwapObject(this, RESULT, null, encodeThrowable(x, r));
    }

    /* access modifiers changed from: package-private */
    public Object encodeOutcome(T t, Throwable x) {
        if (x == null) {
            return t == null ? NIL : t;
        }
        return encodeThrowable(x);
    }

    static Object encodeRelay(Object r) {
        Throwable x;
        if (!(r instanceof AltResult) || (x = ((AltResult) r).ex) == null || (x instanceof CompletionException)) {
            return r;
        }
        return new AltResult(new CompletionException(x));
    }

    /* access modifiers changed from: package-private */
    public final boolean completeRelay(Object r) {
        return U.compareAndSwapObject(this, RESULT, null, encodeRelay(r));
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    private static <T> T reportGet(Object r) throws InterruptedException, ExecutionException {
        Throwable cause;
        if (r == 0) {
            throw new InterruptedException();
        } else if (!(r instanceof AltResult)) {
            return r;
        } else {
            Throwable th = ((AltResult) r).ex;
            Throwable x = th;
            if (th == null) {
                return null;
            }
            if (!(x instanceof CancellationException)) {
                if ((x instanceof CompletionException) && (cause = x.getCause()) != null) {
                    x = cause;
                }
                throw new ExecutionException(x);
            }
            throw ((CancellationException) x);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    private static <T> T reportJoin(Object r) {
        if (!(r instanceof AltResult)) {
            return r;
        }
        Throwable x = ((AltResult) r).ex;
        if (x == null) {
            return null;
        }
        if (x instanceof CancellationException) {
            throw ((CancellationException) x);
        } else if (x instanceof CompletionException) {
            throw ((CompletionException) x);
        } else {
            throw new CompletionException(x);
        }
    }

    static final class ThreadPerTaskExecutor implements Executor {
        ThreadPerTaskExecutor() {
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable r) {
            new Thread(r).start();
        }
    }

    static Executor screenExecutor(Executor e) {
        if (!USE_COMMON_POOL && e == ForkJoinPool.commonPool()) {
            return ASYNC_POOL;
        }
        if (e != null) {
            return e;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: package-private */
    public static abstract class Completion extends ForkJoinTask<Void> implements Runnable, AsynchronousCompletionTask {
        volatile Completion next;

        /* access modifiers changed from: package-private */
        public abstract boolean isLive();

        /* access modifiers changed from: package-private */
        public abstract CompletableFuture<?> tryFire(int i);

        Completion() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            tryFire(1);
        }

        @Override // java.util.concurrent.ForkJoinTask
        public final boolean exec() {
            tryFire(1);
            return false;
        }

        @Override // java.util.concurrent.ForkJoinTask
        public final Void getRawResult() {
            return null;
        }

        public final void setRawResult(Void v) {
        }
    }

    static void lazySetNext(Completion c, Completion next) {
        U.putOrderedObject(c, NEXT, next);
    }

    /* access modifiers changed from: package-private */
    public final void postComplete() {
        CompletableFuture<T> completableFuture = this;
        while (true) {
            Completion completion = completableFuture.stack;
            Completion h = completion;
            if (completion == null) {
                if (completableFuture != this) {
                    completableFuture = this;
                    Completion completion2 = this.stack;
                    h = completion2;
                    if (completion2 == null) {
                        return;
                    }
                } else {
                    return;
                }
            }
            Completion t = h.next;
            if (completableFuture.casStack(h, t)) {
                if (t != null) {
                    if (completableFuture != this) {
                        pushStack(h);
                    } else {
                        h.next = null;
                    }
                }
                CompletableFuture<?> d = h.tryFire(-1);
                completableFuture = d == null ? this : d;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void cleanStack() {
        Completion p = null;
        Completion q = this.stack;
        while (q != null) {
            Completion s = q.next;
            if (q.isLive()) {
                p = q;
                q = s;
            } else if (p == null) {
                casStack(q, s);
                q = this.stack;
            } else {
                p.next = s;
                if (p.isLive()) {
                    q = s;
                } else {
                    p = null;
                    q = this.stack;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static abstract class UniCompletion<T, V> extends Completion {
        CompletableFuture<V> dep;
        Executor executor;
        CompletableFuture<T> src;

        UniCompletion(Executor executor2, CompletableFuture<V> dep2, CompletableFuture<T> src2) {
            this.executor = executor2;
            this.dep = dep2;
            this.src = src2;
        }

        /* access modifiers changed from: package-private */
        public final boolean claim() {
            Executor e = this.executor;
            if (compareAndSetForkJoinTaskTag(0, 1)) {
                if (e == null) {
                    return true;
                }
                this.executor = null;
                e.execute(this);
            }
            return false;
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.concurrent.CompletableFuture.Completion
        public final boolean isLive() {
            return this.dep != null;
        }
    }

    /* access modifiers changed from: package-private */
    public final void push(UniCompletion<?, ?> c) {
        if (c != null) {
            while (this.result == null && !tryPushStack(c)) {
                lazySetNext(c, null);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final CompletableFuture<T> postFire(CompletableFuture<?> a, int mode) {
        if (!(a == null || a.stack == null)) {
            if (mode < 0 || a.result == null) {
                a.cleanStack();
            } else {
                a.postComplete();
            }
        }
        if (this.result == null || this.stack == null) {
            return null;
        }
        if (mode < 0) {
            return this;
        }
        postComplete();
        return null;
    }

    /* access modifiers changed from: package-private */
    public static final class UniApply<T, V> extends UniCompletion<T, V> {
        Function<? super T, ? extends V> fn;

        UniApply(Executor executor, CompletableFuture<V> dep, CompletableFuture<T> src, Function<? super T, ? extends V> fn2) {
            super(executor, dep, src);
            this.fn = fn2;
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.concurrent.CompletableFuture.Completion
        public final CompletableFuture<V> tryFire(int mode) {
            CompletableFuture<V> d = this.dep;
            if (d != null) {
                CompletableFuture<?> completableFuture = this.src;
                if (d.uniApply(completableFuture, this.fn, mode > 0 ? null : this)) {
                    this.dep = null;
                    this.src = null;
                    this.fn = null;
                    return d.postFire(completableFuture, mode);
                }
            }
            return null;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: java.util.concurrent.CompletableFuture<T> */
    /* JADX WARN: Multi-variable type inference failed */
    /* access modifiers changed from: package-private */
    public final <S> boolean uniApply(CompletableFuture<S> a, Function<? super S, ? extends T> f, UniApply<S, T> c) {
        if (a != null) {
            Object obj = a.result;
            Object r = obj;
            if (!(obj == null || f == null)) {
                if (this.result != null) {
                    return true;
                }
                if (r instanceof AltResult) {
                    Throwable x = ((AltResult) r).ex;
                    if (x != null) {
                        completeThrowable(x, r);
                        return true;
                    }
                    r = null;
                }
                if (c != null) {
                    try {
                        if (!c.claim()) {
                            return false;
                        }
                    } catch (Throwable ex) {
                        completeThrowable(ex);
                        return true;
                    }
                }
                completeValue(f.apply(r));
                return true;
            }
        }
        return false;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: java.util.concurrent.CompletableFuture<T> */
    /* JADX WARN: Multi-variable type inference failed */
    private <V> CompletableFuture<V> uniApplyStage(Executor e, Function<? super T, ? extends V> f) {
        if (f != null) {
            CompletableFuture<V> d = newIncompleteFuture();
            if (e != null || !d.uniApply(this, f, null)) {
                UniApply<T, V> c = new UniApply<>(e, d, this, f);
                push(c);
                c.tryFire(0);
            }
            return d;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: package-private */
    public static final class UniAccept<T> extends UniCompletion<T, Void> {
        Consumer<? super T> fn;

        UniAccept(Executor executor, CompletableFuture<Void> dep, CompletableFuture<T> src, Consumer<? super T> fn2) {
            super(executor, dep, src);
            this.fn = fn2;
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.concurrent.CompletableFuture.Completion
        public final CompletableFuture<Void> tryFire(int mode) {
            CompletableFuture<Void> d = this.dep;
            if (d != null) {
                CompletableFuture<?> completableFuture = this.src;
                if (d.uniAccept(completableFuture, this.fn, mode > 0 ? null : this)) {
                    this.dep = null;
                    this.src = null;
                    this.fn = null;
                    return d.postFire(completableFuture, mode);
                }
            }
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public final <S> boolean uniAccept(CompletableFuture<S> a, Consumer<? super S> f, UniAccept<S> c) {
        if (a != null) {
            Object obj = a.result;
            Object r = obj;
            if (!(obj == null || f == null)) {
                if (this.result != null) {
                    return true;
                }
                if (r instanceof AltResult) {
                    Throwable x = ((AltResult) r).ex;
                    if (x != null) {
                        completeThrowable(x, r);
                        return true;
                    }
                    r = null;
                }
                if (c != null) {
                    try {
                        if (!c.claim()) {
                            return false;
                        }
                    } catch (Throwable ex) {
                        completeThrowable(ex);
                        return true;
                    }
                }
                f.accept(r);
                completeNull();
                return true;
            }
        }
        return false;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: java.util.concurrent.CompletableFuture<T> */
    /* JADX WARN: Multi-variable type inference failed */
    private CompletableFuture<Void> uniAcceptStage(Executor e, Consumer<? super T> f) {
        if (f != null) {
            CompletableFuture<Void> d = newIncompleteFuture();
            if (e != null || !d.uniAccept(this, f, null)) {
                UniAccept<T> c = new UniAccept<>(e, d, this, f);
                push(c);
                c.tryFire(0);
            }
            return d;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: package-private */
    public static final class UniRun<T> extends UniCompletion<T, Void> {
        Runnable fn;

        UniRun(Executor executor, CompletableFuture<Void> dep, CompletableFuture<T> src, Runnable fn2) {
            super(executor, dep, src);
            this.fn = fn2;
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.concurrent.CompletableFuture.Completion
        public final CompletableFuture<Void> tryFire(int mode) {
            CompletableFuture<Void> d = this.dep;
            if (d != null) {
                CompletableFuture<T> a = this.src;
                if (d.uniRun(a, this.fn, mode > 0 ? null : this)) {
                    this.dep = null;
                    this.src = null;
                    this.fn = null;
                    return d.postFire(a, mode);
                }
            }
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean uniRun(CompletableFuture<?> a, Runnable f, UniRun<?> c) {
        Object r;
        Throwable x;
        if (a == null || (r = a.result) == null || f == null) {
            return false;
        }
        if (this.result != null) {
            return true;
        }
        if (!(r instanceof AltResult) || (x = ((AltResult) r).ex) == null) {
            if (c != null) {
                try {
                    if (!c.claim()) {
                        return false;
                    }
                } catch (Throwable ex) {
                    completeThrowable(ex);
                    return true;
                }
            }
            f.run();
            completeNull();
            return true;
        }
        completeThrowable(x, r);
        return true;
    }

    private CompletableFuture<Void> uniRunStage(Executor e, Runnable f) {
        if (f != null) {
            CompletableFuture<Void> d = newIncompleteFuture();
            if (e != null || !d.uniRun(this, f, null)) {
                UniRun<T> c = new UniRun<>(e, d, this, f);
                push(c);
                c.tryFire(0);
            }
            return d;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: package-private */
    public static final class UniWhenComplete<T> extends UniCompletion<T, T> {
        BiConsumer<? super T, ? super Throwable> fn;

        UniWhenComplete(Executor executor, CompletableFuture<T> dep, CompletableFuture<T> src, BiConsumer<? super T, ? super Throwable> fn2) {
            super(executor, dep, src);
            this.fn = fn2;
        }

        /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: java.util.concurrent.CompletableFuture */
        /* JADX WARN: Multi-variable type inference failed */
        /* access modifiers changed from: package-private */
        @Override // java.util.concurrent.CompletableFuture.Completion
        public final CompletableFuture<T> tryFire(int mode) {
            CompletableFuture completableFuture = this.dep;
            if (completableFuture != 0) {
                CompletableFuture<T> a = this.src;
                if (completableFuture.uniWhenComplete(a, this.fn, mode > 0 ? null : this)) {
                    this.dep = null;
                    this.src = null;
                    this.fn = null;
                    return completableFuture.postFire(a, mode);
                }
            }
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean uniWhenComplete(CompletableFuture<T> a, BiConsumer<? super T, ? super Throwable> f, UniWhenComplete<T> c) {
        Object r;
        Object obj;
        Throwable x = null;
        if (a == null || (r = a.result) == null || f == null) {
            return false;
        }
        if (this.result == null) {
            if (c != null) {
                try {
                    if (!c.claim()) {
                        return false;
                    }
                } catch (Throwable ex) {
                    if (0 == 0) {
                        x = ex;
                    } else if (null != ex) {
                        x.addSuppressed(ex);
                    }
                }
            }
            if (r instanceof AltResult) {
                x = ((AltResult) r).ex;
                obj = null;
            } else {
                obj = r;
            }
            f.accept(obj, x);
            if (x == null) {
                internalComplete(r);
                return true;
            }
            completeThrowable(x, r);
        }
        return true;
    }

    private CompletableFuture<T> uniWhenCompleteStage(Executor e, BiConsumer<? super T, ? super Throwable> f) {
        if (f != null) {
            CompletableFuture<T> d = newIncompleteFuture();
            if (e != null || !d.uniWhenComplete(this, f, null)) {
                UniWhenComplete<T> c = new UniWhenComplete<>(e, d, this, f);
                push(c);
                c.tryFire(0);
            }
            return d;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: package-private */
    public static final class UniHandle<T, V> extends UniCompletion<T, V> {
        BiFunction<? super T, Throwable, ? extends V> fn;

        UniHandle(Executor executor, CompletableFuture<V> dep, CompletableFuture<T> src, BiFunction<? super T, Throwable, ? extends V> fn2) {
            super(executor, dep, src);
            this.fn = fn2;
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.concurrent.CompletableFuture.Completion
        public final CompletableFuture<V> tryFire(int mode) {
            CompletableFuture<V> d = this.dep;
            if (d != null) {
                CompletableFuture<?> completableFuture = this.src;
                if (d.uniHandle(completableFuture, this.fn, mode > 0 ? null : this)) {
                    this.dep = null;
                    this.src = null;
                    this.fn = null;
                    return d.postFire(completableFuture, mode);
                }
            }
            return null;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: java.util.concurrent.CompletableFuture<T> */
    /* JADX WARN: Multi-variable type inference failed */
    /* access modifiers changed from: package-private */
    public final <S> boolean uniHandle(CompletableFuture<S> a, BiFunction<? super S, Throwable, ? extends T> f, UniHandle<S, T> c) {
        Object r;
        Object obj;
        Throwable x;
        if (a == null || (r = a.result) == null || f == null) {
            return false;
        }
        if (this.result != null) {
            return true;
        }
        if (c != null) {
            try {
                if (!c.claim()) {
                    return false;
                }
            } catch (Throwable ex) {
                completeThrowable(ex);
                return true;
            }
        }
        if (r instanceof AltResult) {
            x = ((AltResult) r).ex;
            obj = null;
        } else {
            x = null;
            obj = r;
        }
        completeValue(f.apply(obj, x));
        return true;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: java.util.concurrent.CompletableFuture<T> */
    /* JADX WARN: Multi-variable type inference failed */
    private <V> CompletableFuture<V> uniHandleStage(Executor e, BiFunction<? super T, Throwable, ? extends V> f) {
        if (f != null) {
            CompletableFuture<V> d = newIncompleteFuture();
            if (e != null || !d.uniHandle(this, f, null)) {
                UniHandle<T, V> c = new UniHandle<>(e, d, this, f);
                push(c);
                c.tryFire(0);
            }
            return d;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: package-private */
    public static final class UniExceptionally<T> extends UniCompletion<T, T> {
        Function<? super Throwable, ? extends T> fn;

        UniExceptionally(CompletableFuture<T> dep, CompletableFuture<T> src, Function<? super Throwable, ? extends T> fn2) {
            super(null, dep, src);
            this.fn = fn2;
        }

        /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: java.util.concurrent.CompletableFuture */
        /* JADX WARN: Multi-variable type inference failed */
        /* access modifiers changed from: package-private */
        @Override // java.util.concurrent.CompletableFuture.Completion
        public final CompletableFuture<T> tryFire(int mode) {
            CompletableFuture completableFuture = this.dep;
            if (completableFuture != 0) {
                CompletableFuture<T> a = this.src;
                if (completableFuture.uniExceptionally(a, this.fn, this)) {
                    this.dep = null;
                    this.src = null;
                    this.fn = null;
                    return completableFuture.postFire(a, mode);
                }
            }
            return null;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: java.util.concurrent.CompletableFuture<T> */
    /* JADX WARN: Multi-variable type inference failed */
    /* access modifiers changed from: package-private */
    public final boolean uniExceptionally(CompletableFuture<T> a, Function<? super Throwable, ? extends T> f, UniExceptionally<T> c) {
        Object r;
        Throwable x;
        if (a == null || (r = a.result) == null || f == null) {
            return false;
        }
        if (this.result != null) {
            return true;
        }
        try {
            if (!(r instanceof AltResult) || (x = ((AltResult) r).ex) == null) {
                internalComplete(r);
                return true;
            } else if (c != null && !c.claim()) {
                return false;
            } else {
                completeValue(f.apply(x));
                return true;
            }
        } catch (Throwable ex) {
            completeThrowable(ex);
            return true;
        }
    }

    private CompletableFuture<T> uniExceptionallyStage(Function<Throwable, ? extends T> f) {
        if (f != null) {
            CompletableFuture<T> d = newIncompleteFuture();
            if (!d.uniExceptionally(this, f, null)) {
                UniExceptionally<T> c = new UniExceptionally<>(d, this, f);
                push(c);
                c.tryFire(0);
            }
            return d;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: package-private */
    public static final class UniRelay<T> extends UniCompletion<T, T> {
        UniRelay(CompletableFuture<T> dep, CompletableFuture<T> src) {
            super(null, dep, src);
        }

        /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: java.util.concurrent.CompletableFuture */
        /* JADX WARN: Multi-variable type inference failed */
        /* access modifiers changed from: package-private */
        @Override // java.util.concurrent.CompletableFuture.Completion
        public final CompletableFuture<T> tryFire(int mode) {
            CompletableFuture completableFuture = this.dep;
            if (completableFuture != 0) {
                CompletableFuture<T> a = this.src;
                if (completableFuture.uniRelay(a)) {
                    this.src = null;
                    this.dep = null;
                    return completableFuture.postFire(a, mode);
                }
            }
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean uniRelay(CompletableFuture<T> a) {
        Object r;
        if (a == null || (r = a.result) == null) {
            return false;
        }
        if (this.result != null) {
            return true;
        }
        completeRelay(r);
        return true;
    }

    private CompletableFuture<T> uniCopyStage() {
        CompletableFuture<T> d = newIncompleteFuture();
        Object r = this.result;
        if (r != null) {
            d.completeRelay(r);
        } else {
            UniRelay<T> c = new UniRelay<>(d, this);
            push(c);
            c.tryFire(0);
        }
        return d;
    }

    private MinimalStage<T> uniAsMinimalStage() {
        Object r = this.result;
        if (r != null) {
            return new MinimalStage<>(encodeRelay(r));
        }
        MinimalStage<T> d = new MinimalStage<>();
        UniRelay<T> c = new UniRelay<>(d, this);
        push(c);
        c.tryFire(0);
        return d;
    }

    /* access modifiers changed from: package-private */
    public static final class UniCompose<T, V> extends UniCompletion<T, V> {
        Function<? super T, ? extends CompletionStage<V>> fn;

        UniCompose(Executor executor, CompletableFuture<V> dep, CompletableFuture<T> src, Function<? super T, ? extends CompletionStage<V>> fn2) {
            super(executor, dep, src);
            this.fn = fn2;
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.concurrent.CompletableFuture.Completion
        public final CompletableFuture<V> tryFire(int mode) {
            CompletableFuture<V> d = this.dep;
            if (d != null) {
                CompletableFuture<?> completableFuture = this.src;
                if (d.uniCompose(completableFuture, this.fn, mode > 0 ? null : this)) {
                    this.dep = null;
                    this.src = null;
                    this.fn = null;
                    return d.postFire(completableFuture, mode);
                }
            }
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public final <S> boolean uniCompose(CompletableFuture<S> a, Function<? super S, ? extends CompletionStage<T>> f, UniCompose<S, T> c) {
        if (a != null) {
            Object obj = a.result;
            Object r = obj;
            if (!(obj == null || f == null)) {
                if (this.result != null) {
                    return true;
                }
                if (r instanceof AltResult) {
                    Throwable x = ((AltResult) r).ex;
                    if (x != null) {
                        completeThrowable(x, r);
                        return true;
                    }
                    r = null;
                }
                if (c != null) {
                    try {
                        if (!c.claim()) {
                            return false;
                        }
                    } catch (Throwable ex) {
                        completeThrowable(ex);
                        return true;
                    }
                }
                CompletableFuture<T> g = ((CompletionStage) f.apply(r)).toCompletableFuture();
                if (g.result != null && uniRelay(g)) {
                    return true;
                }
                UniRelay<T> copy = new UniRelay<>(this, g);
                g.push(copy);
                copy.tryFire(0);
                if (this.result == null) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    private <V> CompletableFuture<V> uniComposeStage(Executor e, Function<? super T, ? extends CompletionStage<V>> f) {
        if (f != null) {
            CompletableFuture<V> d = newIncompleteFuture();
            if (e == null) {
                Object obj = this.result;
                Object r = obj;
                if (obj != null) {
                    if (r instanceof AltResult) {
                        Throwable x = ((AltResult) r).ex;
                        if (x != null) {
                            d.result = encodeThrowable(x, r);
                            return d;
                        }
                        r = null;
                    }
                    try {
                        CompletableFuture<V> g = ((CompletionStage) f.apply(r)).toCompletableFuture();
                        Object s = g.result;
                        if (s != null) {
                            d.completeRelay(s);
                        } else {
                            UniRelay<V> c = new UniRelay<>(d, g);
                            g.push(c);
                            c.tryFire(0);
                        }
                        return d;
                    } catch (Throwable ex) {
                        d.result = encodeThrowable(ex);
                        return d;
                    }
                }
            }
            UniCompose<T, V> c2 = new UniCompose<>(e, d, this, f);
            push(c2);
            c2.tryFire(0);
            return d;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: package-private */
    public static abstract class BiCompletion<T, U, V> extends UniCompletion<T, V> {
        CompletableFuture<U> snd;

        BiCompletion(Executor executor, CompletableFuture<V> dep, CompletableFuture<T> src, CompletableFuture<U> snd2) {
            super(executor, dep, src);
            this.snd = snd2;
        }
    }

    /* access modifiers changed from: package-private */
    public static final class CoCompletion extends Completion {
        BiCompletion<?, ?, ?> base;

        CoCompletion(BiCompletion<?, ?, ?> base2) {
            this.base = base2;
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.concurrent.CompletableFuture.Completion
        public final CompletableFuture<?> tryFire(int mode) {
            CompletableFuture<?> d;
            BiCompletion<?, ?, ?> c = this.base;
            if (c == null || (d = c.tryFire(mode)) == null) {
                return null;
            }
            this.base = null;
            return d;
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.concurrent.CompletableFuture.Completion
        public final boolean isLive() {
            BiCompletion<?, ?, ?> c = this.base;
            return (c == null || c.dep == null) ? false : true;
        }
    }

    /* access modifiers changed from: package-private */
    public final void bipush(CompletableFuture<?> b, BiCompletion<?, ?, ?> c) {
        if (c != null) {
            while (true) {
                Object r = this.result;
                if (r == null && !tryPushStack(c)) {
                    lazySetNext(c, null);
                } else if (b != null && b != this && b.result == null) {
                    Completion q = r != null ? c : new CoCompletion(c);
                    while (b.result == null && !b.tryPushStack(q)) {
                        lazySetNext(q, null);
                    }
                    return;
                } else {
                    return;
                }
            }
            if (b != null) {
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final CompletableFuture<T> postFire(CompletableFuture<?> a, CompletableFuture<?> b, int mode) {
        if (!(b == null || b.stack == null)) {
            if (mode < 0 || b.result == null) {
                b.cleanStack();
            } else {
                b.postComplete();
            }
        }
        return postFire(a, mode);
    }

    /* access modifiers changed from: package-private */
    public static final class BiApply<T, U, V> extends BiCompletion<T, U, V> {
        BiFunction<? super T, ? super U, ? extends V> fn;

        BiApply(Executor executor, CompletableFuture<V> dep, CompletableFuture<T> src, CompletableFuture<U> snd, BiFunction<? super T, ? super U, ? extends V> fn2) {
            super(executor, dep, src, snd);
            this.fn = fn2;
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.concurrent.CompletableFuture.Completion
        public final CompletableFuture<V> tryFire(int mode) {
            CompletableFuture<V> d = this.dep;
            if (d != null) {
                CompletableFuture<?> completableFuture = this.src;
                CompletableFuture<?> completableFuture2 = this.snd;
                if (d.biApply(completableFuture, completableFuture2, this.fn, mode > 0 ? null : this)) {
                    this.dep = null;
                    this.src = null;
                    this.snd = null;
                    this.fn = null;
                    return d.postFire(completableFuture, completableFuture2, mode);
                }
            }
            return null;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r5v0, resolved type: java.util.concurrent.CompletableFuture<T> */
    /* JADX WARN: Multi-variable type inference failed */
    /* access modifiers changed from: package-private */
    public final <R, S> boolean biApply(CompletableFuture<R> a, CompletableFuture<S> b, BiFunction<? super R, ? super S, ? extends T> f, BiApply<R, S, T> c) {
        if (a != null) {
            Object obj = a.result;
            Object r = obj;
            if (!(obj == null || b == null)) {
                Object obj2 = b.result;
                Object s = obj2;
                if (!(obj2 == null || f == null)) {
                    if (this.result != null) {
                        return true;
                    }
                    if (r instanceof AltResult) {
                        Throwable x = ((AltResult) r).ex;
                        if (x != null) {
                            completeThrowable(x, r);
                            return true;
                        }
                        r = null;
                    }
                    if (s instanceof AltResult) {
                        Throwable x2 = ((AltResult) s).ex;
                        if (x2 != null) {
                            completeThrowable(x2, s);
                            return true;
                        }
                        s = null;
                    }
                    if (c != null) {
                        try {
                            if (!c.claim()) {
                                return false;
                            }
                        } catch (Throwable ex) {
                            completeThrowable(ex);
                            return true;
                        }
                    }
                    completeValue(f.apply(r, s));
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r9v0, resolved type: java.util.concurrent.CompletableFuture<T> */
    /* JADX WARN: Multi-variable type inference failed */
    private <U, V> CompletableFuture<V> biApplyStage(Executor e, CompletionStage<U> o, BiFunction<? super T, ? super U, ? extends V> f) {
        CompletableFuture<U> b;
        if (f == null || (b = o.toCompletableFuture()) == null) {
            throw new NullPointerException();
        }
        CompletableFuture<V> d = newIncompleteFuture();
        if (e != null || !d.biApply(this, b, f, null)) {
            BiApply<T, U, V> c = new BiApply<>(e, d, this, b, f);
            bipush(b, c);
            c.tryFire(0);
        }
        return d;
    }

    /* access modifiers changed from: package-private */
    public static final class BiAccept<T, U> extends BiCompletion<T, U, Void> {
        BiConsumer<? super T, ? super U> fn;

        BiAccept(Executor executor, CompletableFuture<Void> dep, CompletableFuture<T> src, CompletableFuture<U> snd, BiConsumer<? super T, ? super U> fn2) {
            super(executor, dep, src, snd);
            this.fn = fn2;
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.concurrent.CompletableFuture.Completion
        public final CompletableFuture<Void> tryFire(int mode) {
            CompletableFuture<Void> d = this.dep;
            if (d != null) {
                CompletableFuture<?> completableFuture = this.src;
                CompletableFuture<?> completableFuture2 = this.snd;
                if (d.biAccept(completableFuture, completableFuture2, this.fn, mode > 0 ? null : this)) {
                    this.dep = null;
                    this.src = null;
                    this.snd = null;
                    this.fn = null;
                    return d.postFire(completableFuture, completableFuture2, mode);
                }
            }
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public final <R, S> boolean biAccept(CompletableFuture<R> a, CompletableFuture<S> b, BiConsumer<? super R, ? super S> f, BiAccept<R, S> c) {
        if (a != null) {
            Object obj = a.result;
            Object r = obj;
            if (!(obj == null || b == null)) {
                Object obj2 = b.result;
                Object s = obj2;
                if (!(obj2 == null || f == null)) {
                    if (this.result != null) {
                        return true;
                    }
                    if (r instanceof AltResult) {
                        Throwable x = ((AltResult) r).ex;
                        if (x != null) {
                            completeThrowable(x, r);
                            return true;
                        }
                        r = null;
                    }
                    if (s instanceof AltResult) {
                        Throwable x2 = ((AltResult) s).ex;
                        if (x2 != null) {
                            completeThrowable(x2, s);
                            return true;
                        }
                        s = null;
                    }
                    if (c != null) {
                        try {
                            if (!c.claim()) {
                                return false;
                            }
                        } catch (Throwable ex) {
                            completeThrowable(ex);
                            return true;
                        }
                    }
                    f.accept(r, s);
                    completeNull();
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r9v0, resolved type: java.util.concurrent.CompletableFuture<T> */
    /* JADX WARN: Multi-variable type inference failed */
    private <U> CompletableFuture<Void> biAcceptStage(Executor e, CompletionStage<U> o, BiConsumer<? super T, ? super U> f) {
        CompletableFuture<U> b;
        if (f == null || (b = o.toCompletableFuture()) == null) {
            throw new NullPointerException();
        }
        CompletableFuture<Void> d = newIncompleteFuture();
        if (e != null || !d.biAccept(this, b, f, null)) {
            BiAccept<T, U> c = new BiAccept<>(e, d, this, b, f);
            bipush(b, c);
            c.tryFire(0);
        }
        return d;
    }

    /* access modifiers changed from: package-private */
    public static final class BiRun<T, U> extends BiCompletion<T, U, Void> {
        Runnable fn;

        BiRun(Executor executor, CompletableFuture<Void> dep, CompletableFuture<T> src, CompletableFuture<U> snd, Runnable fn2) {
            super(executor, dep, src, snd);
            this.fn = fn2;
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.concurrent.CompletableFuture.Completion
        public final CompletableFuture<Void> tryFire(int mode) {
            CompletableFuture<Void> d = this.dep;
            if (d != null) {
                CompletableFuture<T> a = this.src;
                CompletableFuture<U> b = this.snd;
                if (d.biRun(a, b, this.fn, mode > 0 ? null : this)) {
                    this.dep = null;
                    this.src = null;
                    this.snd = null;
                    this.fn = null;
                    return d.postFire(a, b, mode);
                }
            }
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean biRun(CompletableFuture<?> a, CompletableFuture<?> b, Runnable f, BiRun<?, ?> c) {
        Object r;
        Object s;
        Throwable x;
        Throwable x2;
        if (a == null || (r = a.result) == null || b == null || (s = b.result) == null || f == null) {
            return false;
        }
        if (this.result != null) {
            return true;
        }
        if ((r instanceof AltResult) && (x2 = ((AltResult) r).ex) != null) {
            completeThrowable(x2, r);
            return true;
        } else if (!(s instanceof AltResult) || (x = ((AltResult) s).ex) == null) {
            if (c != null) {
                try {
                    if (!c.claim()) {
                        return false;
                    }
                } catch (Throwable ex) {
                    completeThrowable(ex);
                    return true;
                }
            }
            f.run();
            completeNull();
            return true;
        } else {
            completeThrowable(x, s);
            return true;
        }
    }

    private CompletableFuture<Void> biRunStage(Executor e, CompletionStage<?> o, Runnable f) {
        CompletableFuture<?> b;
        if (f == null || (b = o.toCompletableFuture()) == null) {
            throw new NullPointerException();
        }
        CompletableFuture<Void> d = newIncompleteFuture();
        if (e != null || !d.biRun(this, b, f, null)) {
            BiRun<T, ?> c = new BiRun<>(e, d, this, b, f);
            bipush(b, c);
            c.tryFire(0);
        }
        return d;
    }

    /* access modifiers changed from: package-private */
    public static final class BiRelay<T, U> extends BiCompletion<T, U, Void> {
        BiRelay(CompletableFuture<Void> dep, CompletableFuture<T> src, CompletableFuture<U> snd) {
            super(null, dep, src, snd);
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.concurrent.CompletableFuture.Completion
        public final CompletableFuture<Void> tryFire(int mode) {
            CompletableFuture<Void> d = this.dep;
            if (d != null) {
                CompletableFuture<T> a = this.src;
                CompletableFuture<U> b = this.snd;
                if (d.biRelay(a, b)) {
                    this.src = null;
                    this.snd = null;
                    this.dep = null;
                    return d.postFire(a, b, mode);
                }
            }
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean biRelay(CompletableFuture<?> a, CompletableFuture<?> b) {
        Object r;
        Object s;
        Throwable x;
        Throwable x2;
        if (a == null || (r = a.result) == null || b == null || (s = b.result) == null) {
            return false;
        }
        if (this.result != null) {
            return true;
        }
        if ((r instanceof AltResult) && (x2 = ((AltResult) r).ex) != null) {
            completeThrowable(x2, r);
            return true;
        } else if (!(s instanceof AltResult) || (x = ((AltResult) s).ex) == null) {
            completeNull();
            return true;
        } else {
            completeThrowable(x, s);
            return true;
        }
    }

    static CompletableFuture<Void> andTree(CompletableFuture<?>[] cfs, int lo, int hi) {
        CompletableFuture<?> a;
        CompletableFuture<Void> d = new CompletableFuture<>();
        if (lo > hi) {
            d.result = NIL;
        } else {
            int mid = (lo + hi) >>> 1;
            if (lo == mid) {
                a = cfs[lo];
            } else {
                a = andTree(cfs, lo, mid);
            }
            if (a != null) {
                CompletableFuture<?> b = lo == hi ? a : hi == mid + 1 ? cfs[hi] : andTree(cfs, mid + 1, hi);
                if (b != null) {
                    if (!d.biRelay(a, b)) {
                        BiRelay<?, ?> c = new BiRelay<>(d, a, b);
                        a.bipush(b, c);
                        c.tryFire(0);
                    }
                }
            }
            throw new NullPointerException();
        }
        return d;
    }

    /* access modifiers changed from: package-private */
    public final void orpush(CompletableFuture<?> b, BiCompletion<?, ?, ?> c) {
        if (c != null) {
            while (true) {
                if ((b != null && b.result != null) || this.result != null) {
                    return;
                }
                if (!tryPushStack(c)) {
                    lazySetNext(c, null);
                } else if (b != null && b != this && b.result == null) {
                    Completion q = new CoCompletion(c);
                    while (this.result == null && b.result == null && !b.tryPushStack(q)) {
                        lazySetNext(q, null);
                    }
                    return;
                } else {
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static final class OrApply<T, U extends T, V> extends BiCompletion<T, U, V> {
        Function<? super T, ? extends V> fn;

        OrApply(Executor executor, CompletableFuture<V> dep, CompletableFuture<T> src, CompletableFuture<U> snd, Function<? super T, ? extends V> fn2) {
            super(executor, dep, src, snd);
            this.fn = fn2;
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.concurrent.CompletableFuture.Completion
        public final CompletableFuture<V> tryFire(int mode) {
            CompletableFuture<V> d = this.dep;
            if (d != null) {
                CompletableFuture<?> completableFuture = this.src;
                CompletableFuture<?> completableFuture2 = this.snd;
                if (d.orApply(completableFuture, completableFuture2, this.fn, mode > 0 ? null : this)) {
                    this.dep = null;
                    this.src = null;
                    this.snd = null;
                    this.fn = null;
                    return d.postFire(completableFuture, completableFuture2, mode);
                }
            }
            return null;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: java.util.concurrent.CompletableFuture<T> */
    /* JADX WARN: Multi-variable type inference failed */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x000d, code lost:
        if (r1 != null) goto L_0x000f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <R, S extends R> boolean orApply(java.util.concurrent.CompletableFuture<R> r4, java.util.concurrent.CompletableFuture<S> r5, java.util.function.Function<? super R, ? extends T> r6, java.util.concurrent.CompletableFuture.OrApply<R, S, T> r7) {
        /*
            r3 = this;
            r0 = 0
            if (r4 == 0) goto L_0x0040
            if (r5 == 0) goto L_0x0040
            java.lang.Object r1 = r4.result
            r2 = r1
            if (r1 != 0) goto L_0x000f
            java.lang.Object r1 = r5.result
            r2 = r1
            if (r1 == 0) goto L_0x0011
        L_0x000f:
            if (r6 != 0) goto L_0x0012
        L_0x0011:
            goto L_0x0040
        L_0x0012:
            java.lang.Object r1 = r3.result
            if (r1 != 0) goto L_0x003e
            if (r7 == 0) goto L_0x001f
            boolean r1 = r7.claim()     // Catch:{ all -> 0x003a }
            if (r1 != 0) goto L_0x001f
            return r0
        L_0x001f:
            boolean r0 = r2 instanceof java.util.concurrent.CompletableFuture.AltResult     // Catch:{ all -> 0x003a }
            if (r0 == 0) goto L_0x0030
            r0 = r2
            java.util.concurrent.CompletableFuture$AltResult r0 = (java.util.concurrent.CompletableFuture.AltResult) r0     // Catch:{ all -> 0x003a }
            java.lang.Throwable r0 = r0.ex     // Catch:{ all -> 0x003a }
            r1 = r0
            if (r0 == 0) goto L_0x002f
            r3.completeThrowable(r1, r2)     // Catch:{ all -> 0x003a }
            goto L_0x003e
        L_0x002f:
            r2 = 0
        L_0x0030:
            r0 = r2
            java.lang.Object r1 = r6.apply(r0)     // Catch:{ all -> 0x003a }
            r3.completeValue(r1)     // Catch:{ all -> 0x003a }
            goto L_0x003e
        L_0x003a:
            r0 = move-exception
            r3.completeThrowable(r0)
        L_0x003e:
            r0 = 1
            return r0
        L_0x0040:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.concurrent.CompletableFuture.orApply(java.util.concurrent.CompletableFuture, java.util.concurrent.CompletableFuture, java.util.function.Function, java.util.concurrent.CompletableFuture$OrApply):boolean");
    }

    /* JADX DEBUG: Multi-variable search result rejected for r9v0, resolved type: java.util.concurrent.CompletableFuture<T> */
    /* JADX WARN: Multi-variable type inference failed */
    private <U extends T, V> CompletableFuture<V> orApplyStage(Executor e, CompletionStage<U> o, Function<? super T, ? extends V> f) {
        CompletableFuture<U> b;
        if (f == null || (b = o.toCompletableFuture()) == null) {
            throw new NullPointerException();
        }
        CompletableFuture<V> d = newIncompleteFuture();
        if (e != null || !d.orApply(this, b, f, null)) {
            OrApply<T, U, V> c = new OrApply<>(e, d, this, b, f);
            orpush(b, c);
            c.tryFire(0);
        }
        return d;
    }

    /* access modifiers changed from: package-private */
    public static final class OrAccept<T, U extends T> extends BiCompletion<T, U, Void> {
        Consumer<? super T> fn;

        OrAccept(Executor executor, CompletableFuture<Void> dep, CompletableFuture<T> src, CompletableFuture<U> snd, Consumer<? super T> fn2) {
            super(executor, dep, src, snd);
            this.fn = fn2;
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.concurrent.CompletableFuture.Completion
        public final CompletableFuture<Void> tryFire(int mode) {
            CompletableFuture<Void> d = this.dep;
            if (d != null) {
                CompletableFuture<?> completableFuture = this.src;
                CompletableFuture<?> completableFuture2 = this.snd;
                if (d.orAccept(completableFuture, completableFuture2, this.fn, mode > 0 ? null : this)) {
                    this.dep = null;
                    this.src = null;
                    this.snd = null;
                    this.fn = null;
                    return d.postFire(completableFuture, completableFuture2, mode);
                }
            }
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x000d, code lost:
        if (r1 != null) goto L_0x000f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <R, S extends R> boolean orAccept(java.util.concurrent.CompletableFuture<R> r4, java.util.concurrent.CompletableFuture<S> r5, java.util.function.Consumer<? super R> r6, java.util.concurrent.CompletableFuture.OrAccept<R, S> r7) {
        /*
            r3 = this;
            r0 = 0
            if (r4 == 0) goto L_0x003f
            if (r5 == 0) goto L_0x003f
            java.lang.Object r1 = r4.result
            r2 = r1
            if (r1 != 0) goto L_0x000f
            java.lang.Object r1 = r5.result
            r2 = r1
            if (r1 == 0) goto L_0x0011
        L_0x000f:
            if (r6 != 0) goto L_0x0012
        L_0x0011:
            goto L_0x003f
        L_0x0012:
            java.lang.Object r1 = r3.result
            if (r1 != 0) goto L_0x003d
            if (r7 == 0) goto L_0x001f
            boolean r1 = r7.claim()     // Catch:{ all -> 0x0039 }
            if (r1 != 0) goto L_0x001f
            return r0
        L_0x001f:
            boolean r0 = r2 instanceof java.util.concurrent.CompletableFuture.AltResult     // Catch:{ all -> 0x0039 }
            if (r0 == 0) goto L_0x0030
            r0 = r2
            java.util.concurrent.CompletableFuture$AltResult r0 = (java.util.concurrent.CompletableFuture.AltResult) r0     // Catch:{ all -> 0x0039 }
            java.lang.Throwable r0 = r0.ex     // Catch:{ all -> 0x0039 }
            r1 = r0
            if (r0 == 0) goto L_0x002f
            r3.completeThrowable(r1, r2)     // Catch:{ all -> 0x0039 }
            goto L_0x003d
        L_0x002f:
            r2 = 0
        L_0x0030:
            r0 = r2
            r6.accept(r0)     // Catch:{ all -> 0x0039 }
            r3.completeNull()     // Catch:{ all -> 0x0039 }
            goto L_0x003d
        L_0x0039:
            r0 = move-exception
            r3.completeThrowable(r0)
        L_0x003d:
            r0 = 1
            return r0
        L_0x003f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.concurrent.CompletableFuture.orAccept(java.util.concurrent.CompletableFuture, java.util.concurrent.CompletableFuture, java.util.function.Consumer, java.util.concurrent.CompletableFuture$OrAccept):boolean");
    }

    /* JADX DEBUG: Multi-variable search result rejected for r9v0, resolved type: java.util.concurrent.CompletableFuture<T> */
    /* JADX WARN: Multi-variable type inference failed */
    private <U extends T> CompletableFuture<Void> orAcceptStage(Executor e, CompletionStage<U> o, Consumer<? super T> f) {
        CompletableFuture<U> b;
        if (f == null || (b = o.toCompletableFuture()) == null) {
            throw new NullPointerException();
        }
        CompletableFuture<Void> d = newIncompleteFuture();
        if (e != null || !d.orAccept(this, b, f, null)) {
            OrAccept<T, U> c = new OrAccept<>(e, d, this, b, f);
            orpush(b, c);
            c.tryFire(0);
        }
        return d;
    }

    /* access modifiers changed from: package-private */
    public static final class OrRun<T, U> extends BiCompletion<T, U, Void> {
        Runnable fn;

        OrRun(Executor executor, CompletableFuture<Void> dep, CompletableFuture<T> src, CompletableFuture<U> snd, Runnable fn2) {
            super(executor, dep, src, snd);
            this.fn = fn2;
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.concurrent.CompletableFuture.Completion
        public final CompletableFuture<Void> tryFire(int mode) {
            CompletableFuture<Void> d = this.dep;
            if (d != null) {
                CompletableFuture<T> a = this.src;
                CompletableFuture<U> b = this.snd;
                if (d.orRun(a, b, this.fn, mode > 0 ? null : this)) {
                    this.dep = null;
                    this.src = null;
                    this.snd = null;
                    this.fn = null;
                    return d.postFire(a, b, mode);
                }
            }
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x000d, code lost:
        if (r1 != null) goto L_0x000f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean orRun(java.util.concurrent.CompletableFuture<?> r4, java.util.concurrent.CompletableFuture<?> r5, java.lang.Runnable r6, java.util.concurrent.CompletableFuture.OrRun<?, ?> r7) {
        /*
            r3 = this;
            r0 = 0
            if (r4 == 0) goto L_0x003c
            if (r5 == 0) goto L_0x003c
            java.lang.Object r1 = r4.result
            r2 = r1
            if (r1 != 0) goto L_0x000f
            java.lang.Object r1 = r5.result
            r2 = r1
            if (r1 == 0) goto L_0x0011
        L_0x000f:
            if (r6 != 0) goto L_0x0012
        L_0x0011:
            goto L_0x003c
        L_0x0012:
            java.lang.Object r1 = r3.result
            if (r1 != 0) goto L_0x003a
            if (r7 == 0) goto L_0x001f
            boolean r1 = r7.claim()     // Catch:{ all -> 0x0036 }
            if (r1 != 0) goto L_0x001f
            return r0
        L_0x001f:
            boolean r0 = r2 instanceof java.util.concurrent.CompletableFuture.AltResult     // Catch:{ all -> 0x0036 }
            if (r0 == 0) goto L_0x002f
            r0 = r2
            java.util.concurrent.CompletableFuture$AltResult r0 = (java.util.concurrent.CompletableFuture.AltResult) r0     // Catch:{ all -> 0x0036 }
            java.lang.Throwable r0 = r0.ex     // Catch:{ all -> 0x0036 }
            r1 = r0
            if (r0 == 0) goto L_0x002f
            r3.completeThrowable(r1, r2)     // Catch:{ all -> 0x0036 }
            goto L_0x0035
        L_0x002f:
            r6.run()     // Catch:{ all -> 0x0036 }
            r3.completeNull()     // Catch:{ all -> 0x0036 }
        L_0x0035:
            goto L_0x003a
        L_0x0036:
            r0 = move-exception
            r3.completeThrowable(r0)
        L_0x003a:
            r0 = 1
            return r0
        L_0x003c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.concurrent.CompletableFuture.orRun(java.util.concurrent.CompletableFuture, java.util.concurrent.CompletableFuture, java.lang.Runnable, java.util.concurrent.CompletableFuture$OrRun):boolean");
    }

    private CompletableFuture<Void> orRunStage(Executor e, CompletionStage<?> o, Runnable f) {
        CompletableFuture<?> b;
        if (f == null || (b = o.toCompletableFuture()) == null) {
            throw new NullPointerException();
        }
        CompletableFuture<Void> d = newIncompleteFuture();
        if (e != null || !d.orRun(this, b, f, null)) {
            OrRun<T, ?> c = new OrRun<>(e, d, this, b, f);
            orpush(b, c);
            c.tryFire(0);
        }
        return d;
    }

    /* access modifiers changed from: package-private */
    public static final class OrRelay<T, U> extends BiCompletion<T, U, Object> {
        OrRelay(CompletableFuture<Object> dep, CompletableFuture<T> src, CompletableFuture<U> snd) {
            super(null, dep, src, snd);
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.concurrent.CompletableFuture.Completion
        public final CompletableFuture<Object> tryFire(int mode) {
            CompletableFuture<Object> d = this.dep;
            if (d != null) {
                CompletableFuture<T> a = this.src;
                CompletableFuture<U> b = this.snd;
                if (d.orRelay(a, b)) {
                    this.src = null;
                    this.snd = null;
                    this.dep = null;
                    return d.postFire(a, b, mode);
                }
            }
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean orRelay(CompletableFuture<?> a, CompletableFuture<?> b) {
        if (a == null || b == null) {
            return false;
        }
        Object obj = a.result;
        Object r = obj;
        if (obj == null) {
            Object obj2 = b.result;
            r = obj2;
            if (obj2 == null) {
                return false;
            }
        }
        if (this.result != null) {
            return true;
        }
        completeRelay(r);
        return true;
    }

    static CompletableFuture<Object> orTree(CompletableFuture<?>[] cfs, int lo, int hi) {
        CompletableFuture<?> a;
        CompletableFuture<Object> d = new CompletableFuture<>();
        if (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            if (lo == mid) {
                a = cfs[lo];
            } else {
                a = orTree(cfs, lo, mid);
            }
            if (a != null) {
                CompletableFuture<?> b = lo == hi ? a : hi == mid + 1 ? cfs[hi] : orTree(cfs, mid + 1, hi);
                if (b != null) {
                    if (!d.orRelay(a, b)) {
                        OrRelay<?, ?> c = new OrRelay<>(d, a, b);
                        a.orpush(b, c);
                        c.tryFire(0);
                    }
                }
            }
            throw new NullPointerException();
        }
        return d;
    }

    /* access modifiers changed from: package-private */
    public static final class AsyncSupply<T> extends ForkJoinTask<Void> implements Runnable, AsynchronousCompletionTask {
        CompletableFuture<T> dep;
        Supplier<? extends T> fn;

        AsyncSupply(CompletableFuture<T> dep2, Supplier<? extends T> fn2) {
            this.dep = dep2;
            this.fn = fn2;
        }

        @Override // java.util.concurrent.ForkJoinTask
        public final Void getRawResult() {
            return null;
        }

        public final void setRawResult(Void v) {
        }

        @Override // java.util.concurrent.ForkJoinTask
        public final boolean exec() {
            run();
            return true;
        }

        /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: java.util.concurrent.CompletableFuture<T> */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        public void run() {
            Supplier<? extends T> f;
            CompletableFuture<T> d = this.dep;
            if (d != 0 && (f = this.fn) != null) {
                this.dep = null;
                this.fn = null;
                if (d.result == null) {
                    try {
                        d.completeValue(f.get());
                    } catch (Throwable ex) {
                        d.completeThrowable(ex);
                    }
                }
                d.postComplete();
            }
        }
    }

    static <U> CompletableFuture<U> asyncSupplyStage(Executor e, Supplier<U> f) {
        if (f != null) {
            CompletableFuture<U> d = new CompletableFuture<>();
            e.execute(new AsyncSupply(d, f));
            return d;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: package-private */
    public static final class AsyncRun extends ForkJoinTask<Void> implements Runnable, AsynchronousCompletionTask {
        CompletableFuture<Void> dep;
        Runnable fn;

        AsyncRun(CompletableFuture<Void> dep2, Runnable fn2) {
            this.dep = dep2;
            this.fn = fn2;
        }

        @Override // java.util.concurrent.ForkJoinTask
        public final Void getRawResult() {
            return null;
        }

        public final void setRawResult(Void v) {
        }

        @Override // java.util.concurrent.ForkJoinTask
        public final boolean exec() {
            run();
            return true;
        }

        @Override // java.lang.Runnable
        public void run() {
            Runnable f;
            CompletableFuture<Void> d = this.dep;
            if (d != null && (f = this.fn) != null) {
                this.dep = null;
                this.fn = null;
                if (d.result == null) {
                    try {
                        f.run();
                        d.completeNull();
                    } catch (Throwable ex) {
                        d.completeThrowable(ex);
                    }
                }
                d.postComplete();
            }
        }
    }

    static CompletableFuture<Void> asyncRunStage(Executor e, Runnable f) {
        if (f != null) {
            CompletableFuture<Void> d = new CompletableFuture<>();
            e.execute(new AsyncRun(d, f));
            return d;
        }
        throw new NullPointerException();
    }

    /* access modifiers changed from: package-private */
    public static final class Signaller extends Completion implements ForkJoinPool.ManagedBlocker {
        final long deadline;
        boolean interrupted;
        final boolean interruptible;
        long nanos;
        volatile Thread thread = Thread.currentThread();

        Signaller(boolean interruptible2, long nanos2, long deadline2) {
            this.interruptible = interruptible2;
            this.nanos = nanos2;
            this.deadline = deadline2;
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.concurrent.CompletableFuture.Completion
        public final CompletableFuture<?> tryFire(int ignore) {
            Thread w = this.thread;
            if (w != null) {
                this.thread = null;
                LockSupport.unpark(w);
            }
            return null;
        }

        @Override // java.util.concurrent.ForkJoinPool.ManagedBlocker
        public boolean isReleasable() {
            if (Thread.interrupted()) {
                this.interrupted = true;
            }
            if (this.interrupted && this.interruptible) {
                return true;
            }
            long j = this.deadline;
            if (j != 0) {
                if (this.nanos <= 0) {
                    return true;
                }
                long nanoTime = j - System.nanoTime();
                this.nanos = nanoTime;
                if (nanoTime <= 0) {
                    return true;
                }
            }
            if (this.thread == null) {
                return true;
            }
            return false;
        }

        @Override // java.util.concurrent.ForkJoinPool.ManagedBlocker
        public boolean block() {
            while (!isReleasable()) {
                if (this.deadline == 0) {
                    LockSupport.park(this);
                } else {
                    LockSupport.parkNanos(this, this.nanos);
                }
            }
            return true;
        }

        /* access modifiers changed from: package-private */
        @Override // java.util.concurrent.CompletableFuture.Completion
        public final boolean isLive() {
            return this.thread != null;
        }
    }

    private Object waitingGet(boolean interruptible) {
        Object r;
        Signaller q = null;
        boolean queued = false;
        int spins = SPINS;
        while (true) {
            r = this.result;
            if (r == null) {
                if (spins <= 0) {
                    if (q != null) {
                        if (queued) {
                            try {
                                ForkJoinPool.managedBlock(q);
                            } catch (InterruptedException e) {
                                q.interrupted = true;
                            }
                            if (q.interrupted && interruptible) {
                                break;
                            }
                        } else {
                            queued = tryPushStack(q);
                        }
                    } else {
                        q = new Signaller(interruptible, 0, 0);
                    }
                } else if (ThreadLocalRandom.nextSecondarySeed() >= 0) {
                    spins--;
                }
            } else {
                break;
            }
        }
        if (q != null) {
            q.thread = null;
            if (q.interrupted) {
                if (interruptible) {
                    cleanStack();
                } else {
                    Thread.currentThread().interrupt();
                }
            }
        }
        if (r != null) {
            postComplete();
        }
        return r;
    }

    /* JADX INFO: Multiple debug info for r0v5 java.lang.Object: [D('q' java.util.concurrent.CompletableFuture$Signaller), D('r' java.lang.Object)] */
    private Object timedGet(long nanos) throws TimeoutException {
        Object r;
        if (Thread.interrupted()) {
            return null;
        }
        if (nanos > 0) {
            long d = System.nanoTime() + nanos;
            long deadline = d == 0 ? 1 : d;
            Signaller q = null;
            boolean queued = false;
            while (true) {
                r = this.result;
                if (r != null) {
                    break;
                } else if (q == null) {
                    q = new Signaller(true, nanos, deadline);
                } else if (!queued) {
                    queued = tryPushStack(q);
                } else if (q.nanos <= 0) {
                    break;
                } else {
                    try {
                        ForkJoinPool.managedBlock(q);
                    } catch (InterruptedException e) {
                        q.interrupted = true;
                    }
                    if (q.interrupted) {
                        break;
                    }
                }
            }
            if (q != null) {
                q.thread = null;
            }
            if (r != null) {
                postComplete();
            } else {
                cleanStack();
            }
            if (r != null || (q != null && q.interrupted)) {
                return r;
            }
        }
        throw new TimeoutException();
    }

    public CompletableFuture() {
    }

    CompletableFuture(Object r) {
        this.result = r;
    }

    public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier) {
        return asyncSupplyStage(ASYNC_POOL, supplier);
    }

    public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier, Executor executor) {
        return asyncSupplyStage(screenExecutor(executor), supplier);
    }

    public static CompletableFuture<Void> runAsync(Runnable runnable) {
        return asyncRunStage(ASYNC_POOL, runnable);
    }

    public static CompletableFuture<Void> runAsync(Runnable runnable, Executor executor) {
        return asyncRunStage(screenExecutor(executor), runnable);
    }

    public static <U> CompletableFuture<U> completedFuture(U value) {
        return new CompletableFuture<>(value == null ? NIL : value);
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        return this.result != null;
    }

    @Override // java.util.concurrent.Future
    public T get() throws InterruptedException, ExecutionException {
        Object r = this.result;
        return (T) reportGet(r == null ? waitingGet(true) : r);
    }

    @Override // java.util.concurrent.Future
    public T get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        long nanos = unit.toNanos(timeout);
        Object r = this.result;
        return (T) reportGet(r == null ? timedGet(nanos) : r);
    }

    public T join() {
        Object r = this.result;
        return (T) reportJoin(r == null ? waitingGet(false) : r);
    }

    public T getNow(T valueIfAbsent) {
        Object r = this.result;
        return r == null ? valueIfAbsent : (T) reportJoin(r);
    }

    public boolean complete(T value) {
        boolean triggered = completeValue(value);
        postComplete();
        return triggered;
    }

    public boolean completeExceptionally(Throwable ex) {
        if (ex != null) {
            boolean triggered = internalComplete(new AltResult(ex));
            postComplete();
            return triggered;
        }
        throw new NullPointerException();
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.util.function.Function<? super T, ? extends U> */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX DEBUG: Type inference failed for r0v1. Raw type applied. Possible types: java.util.concurrent.CompletableFuture<V>, java.util.concurrent.CompletableFuture<U> */
    @Override // java.util.concurrent.CompletionStage
    public <U> CompletableFuture<U> thenApply(Function<? super T, ? extends U> fn) {
        return (CompletableFuture<V>) uniApplyStage(null, fn);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.util.function.Function<? super T, ? extends U> */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX DEBUG: Type inference failed for r0v1. Raw type applied. Possible types: java.util.concurrent.CompletableFuture<V>, java.util.concurrent.CompletableFuture<U> */
    @Override // java.util.concurrent.CompletionStage
    public <U> CompletableFuture<U> thenApplyAsync(Function<? super T, ? extends U> fn) {
        return (CompletableFuture<V>) uniApplyStage(defaultExecutor(), fn);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.util.function.Function<? super T, ? extends U> */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX DEBUG: Type inference failed for r0v1. Raw type applied. Possible types: java.util.concurrent.CompletableFuture<V>, java.util.concurrent.CompletableFuture<U> */
    @Override // java.util.concurrent.CompletionStage
    public <U> CompletableFuture<U> thenApplyAsync(Function<? super T, ? extends U> fn, Executor executor) {
        return (CompletableFuture<V>) uniApplyStage(screenExecutor(executor), fn);
    }

    @Override // java.util.concurrent.CompletionStage
    public CompletableFuture<Void> thenAccept(Consumer<? super T> action) {
        return uniAcceptStage(null, action);
    }

    @Override // java.util.concurrent.CompletionStage
    public CompletableFuture<Void> thenAcceptAsync(Consumer<? super T> action) {
        return uniAcceptStage(defaultExecutor(), action);
    }

    @Override // java.util.concurrent.CompletionStage
    public CompletableFuture<Void> thenAcceptAsync(Consumer<? super T> action, Executor executor) {
        return uniAcceptStage(screenExecutor(executor), action);
    }

    @Override // java.util.concurrent.CompletionStage
    public CompletableFuture<Void> thenRun(Runnable action) {
        return uniRunStage(null, action);
    }

    @Override // java.util.concurrent.CompletionStage
    public CompletableFuture<Void> thenRunAsync(Runnable action) {
        return uniRunStage(defaultExecutor(), action);
    }

    @Override // java.util.concurrent.CompletionStage
    public CompletableFuture<Void> thenRunAsync(Runnable action, Executor executor) {
        return uniRunStage(screenExecutor(executor), action);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.util.concurrent.CompletionStage<? extends U> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.concurrent.CompletionStage
    public <U, V> CompletableFuture<V> thenCombine(CompletionStage<? extends U> other, BiFunction<? super T, ? super U, ? extends V> fn) {
        return biApplyStage(null, other, fn);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.util.concurrent.CompletionStage<? extends U> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.concurrent.CompletionStage
    public <U, V> CompletableFuture<V> thenCombineAsync(CompletionStage<? extends U> other, BiFunction<? super T, ? super U, ? extends V> fn) {
        return biApplyStage(defaultExecutor(), other, fn);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.util.concurrent.CompletionStage<? extends U> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.concurrent.CompletionStage
    public <U, V> CompletableFuture<V> thenCombineAsync(CompletionStage<? extends U> other, BiFunction<? super T, ? super U, ? extends V> fn, Executor executor) {
        return biApplyStage(screenExecutor(executor), other, fn);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.util.concurrent.CompletionStage<? extends U> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.concurrent.CompletionStage
    public <U> CompletableFuture<Void> thenAcceptBoth(CompletionStage<? extends U> other, BiConsumer<? super T, ? super U> action) {
        return biAcceptStage(null, other, action);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.util.concurrent.CompletionStage<? extends U> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.concurrent.CompletionStage
    public <U> CompletableFuture<Void> thenAcceptBothAsync(CompletionStage<? extends U> other, BiConsumer<? super T, ? super U> action) {
        return biAcceptStage(defaultExecutor(), other, action);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.util.concurrent.CompletionStage<? extends U> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.concurrent.CompletionStage
    public <U> CompletableFuture<Void> thenAcceptBothAsync(CompletionStage<? extends U> other, BiConsumer<? super T, ? super U> action, Executor executor) {
        return biAcceptStage(screenExecutor(executor), other, action);
    }

    @Override // java.util.concurrent.CompletionStage
    public CompletableFuture<Void> runAfterBoth(CompletionStage<?> other, Runnable action) {
        return biRunStage(null, other, action);
    }

    @Override // java.util.concurrent.CompletionStage
    public CompletableFuture<Void> runAfterBothAsync(CompletionStage<?> other, Runnable action) {
        return biRunStage(defaultExecutor(), other, action);
    }

    @Override // java.util.concurrent.CompletionStage
    public CompletableFuture<Void> runAfterBothAsync(CompletionStage<?> other, Runnable action, Executor executor) {
        return biRunStage(screenExecutor(executor), other, action);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.util.concurrent.CompletionStage<? extends T> */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX DEBUG: Type inference failed for r0v1. Raw type applied. Possible types: java.util.concurrent.CompletableFuture<V>, java.util.concurrent.CompletableFuture<U> */
    @Override // java.util.concurrent.CompletionStage
    public <U> CompletableFuture<U> applyToEither(CompletionStage<? extends T> other, Function<? super T, U> fn) {
        return (CompletableFuture<V>) orApplyStage(null, other, fn);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.util.concurrent.CompletionStage<? extends T> */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX DEBUG: Type inference failed for r0v1. Raw type applied. Possible types: java.util.concurrent.CompletableFuture<V>, java.util.concurrent.CompletableFuture<U> */
    @Override // java.util.concurrent.CompletionStage
    public <U> CompletableFuture<U> applyToEitherAsync(CompletionStage<? extends T> other, Function<? super T, U> fn) {
        return (CompletableFuture<V>) orApplyStage(defaultExecutor(), other, fn);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.util.concurrent.CompletionStage<? extends T> */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX DEBUG: Type inference failed for r0v1. Raw type applied. Possible types: java.util.concurrent.CompletableFuture<V>, java.util.concurrent.CompletableFuture<U> */
    @Override // java.util.concurrent.CompletionStage
    public <U> CompletableFuture<U> applyToEitherAsync(CompletionStage<? extends T> other, Function<? super T, U> fn, Executor executor) {
        return (CompletableFuture<V>) orApplyStage(screenExecutor(executor), other, fn);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.util.concurrent.CompletionStage<? extends T> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.concurrent.CompletionStage
    public CompletableFuture<Void> acceptEither(CompletionStage<? extends T> other, Consumer<? super T> action) {
        return orAcceptStage(null, other, action);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.util.concurrent.CompletionStage<? extends T> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.concurrent.CompletionStage
    public CompletableFuture<Void> acceptEitherAsync(CompletionStage<? extends T> other, Consumer<? super T> action) {
        return orAcceptStage(defaultExecutor(), other, action);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.util.concurrent.CompletionStage<? extends T> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.concurrent.CompletionStage
    public CompletableFuture<Void> acceptEitherAsync(CompletionStage<? extends T> other, Consumer<? super T> action, Executor executor) {
        return orAcceptStage(screenExecutor(executor), other, action);
    }

    @Override // java.util.concurrent.CompletionStage
    public CompletableFuture<Void> runAfterEither(CompletionStage<?> other, Runnable action) {
        return orRunStage(null, other, action);
    }

    @Override // java.util.concurrent.CompletionStage
    public CompletableFuture<Void> runAfterEitherAsync(CompletionStage<?> other, Runnable action) {
        return orRunStage(defaultExecutor(), other, action);
    }

    @Override // java.util.concurrent.CompletionStage
    public CompletableFuture<Void> runAfterEitherAsync(CompletionStage<?> other, Runnable action, Executor executor) {
        return orRunStage(screenExecutor(executor), other, action);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.util.function.Function<? super T, ? extends java.util.concurrent.CompletionStage<U>> */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX DEBUG: Type inference failed for r0v1. Raw type applied. Possible types: java.util.concurrent.CompletableFuture<V>, java.util.concurrent.CompletableFuture<U> */
    @Override // java.util.concurrent.CompletionStage
    public <U> CompletableFuture<U> thenCompose(Function<? super T, ? extends CompletionStage<U>> fn) {
        return (CompletableFuture<V>) uniComposeStage(null, fn);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.util.function.Function<? super T, ? extends java.util.concurrent.CompletionStage<U>> */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX DEBUG: Type inference failed for r0v1. Raw type applied. Possible types: java.util.concurrent.CompletableFuture<V>, java.util.concurrent.CompletableFuture<U> */
    @Override // java.util.concurrent.CompletionStage
    public <U> CompletableFuture<U> thenComposeAsync(Function<? super T, ? extends CompletionStage<U>> fn) {
        return (CompletableFuture<V>) uniComposeStage(defaultExecutor(), fn);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.util.function.Function<? super T, ? extends java.util.concurrent.CompletionStage<U>> */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX DEBUG: Type inference failed for r0v1. Raw type applied. Possible types: java.util.concurrent.CompletableFuture<V>, java.util.concurrent.CompletableFuture<U> */
    @Override // java.util.concurrent.CompletionStage
    public <U> CompletableFuture<U> thenComposeAsync(Function<? super T, ? extends CompletionStage<U>> fn, Executor executor) {
        return (CompletableFuture<V>) uniComposeStage(screenExecutor(executor), fn);
    }

    @Override // java.util.concurrent.CompletionStage
    public CompletableFuture<T> whenComplete(BiConsumer<? super T, ? super Throwable> action) {
        return uniWhenCompleteStage(null, action);
    }

    @Override // java.util.concurrent.CompletionStage
    public CompletableFuture<T> whenCompleteAsync(BiConsumer<? super T, ? super Throwable> action) {
        return uniWhenCompleteStage(defaultExecutor(), action);
    }

    @Override // java.util.concurrent.CompletionStage
    public CompletableFuture<T> whenCompleteAsync(BiConsumer<? super T, ? super Throwable> action, Executor executor) {
        return uniWhenCompleteStage(screenExecutor(executor), action);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.util.function.BiFunction<? super T, java.lang.Throwable, ? extends U> */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX DEBUG: Type inference failed for r0v1. Raw type applied. Possible types: java.util.concurrent.CompletableFuture<V>, java.util.concurrent.CompletableFuture<U> */
    @Override // java.util.concurrent.CompletionStage
    public <U> CompletableFuture<U> handle(BiFunction<? super T, Throwable, ? extends U> fn) {
        return (CompletableFuture<V>) uniHandleStage(null, fn);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.util.function.BiFunction<? super T, java.lang.Throwable, ? extends U> */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX DEBUG: Type inference failed for r0v1. Raw type applied. Possible types: java.util.concurrent.CompletableFuture<V>, java.util.concurrent.CompletableFuture<U> */
    @Override // java.util.concurrent.CompletionStage
    public <U> CompletableFuture<U> handleAsync(BiFunction<? super T, Throwable, ? extends U> fn) {
        return (CompletableFuture<V>) uniHandleStage(defaultExecutor(), fn);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.util.function.BiFunction<? super T, java.lang.Throwable, ? extends U> */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX DEBUG: Type inference failed for r0v1. Raw type applied. Possible types: java.util.concurrent.CompletableFuture<V>, java.util.concurrent.CompletableFuture<U> */
    @Override // java.util.concurrent.CompletionStage
    public <U> CompletableFuture<U> handleAsync(BiFunction<? super T, Throwable, ? extends U> fn, Executor executor) {
        return (CompletableFuture<V>) uniHandleStage(screenExecutor(executor), fn);
    }

    @Override // java.util.concurrent.CompletionStage
    public CompletableFuture<T> toCompletableFuture() {
        return this;
    }

    @Override // java.util.concurrent.CompletionStage
    public CompletableFuture<T> exceptionally(Function<Throwable, ? extends T> fn) {
        return uniExceptionallyStage(fn);
    }

    public static CompletableFuture<Void> allOf(CompletableFuture<?>... cfs) {
        return andTree(cfs, 0, cfs.length - 1);
    }

    public static CompletableFuture<Object> anyOf(CompletableFuture<?>... cfs) {
        return orTree(cfs, 0, cfs.length - 1);
    }

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean mayInterruptIfRunning) {
        boolean cancelled = this.result == null && internalComplete(new AltResult(new CancellationException()));
        postComplete();
        return cancelled || isCancelled();
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        Object r = this.result;
        return (r instanceof AltResult) && (((AltResult) r).ex instanceof CancellationException);
    }

    public boolean isCompletedExceptionally() {
        Object r = this.result;
        return (r instanceof AltResult) && r != NIL;
    }

    public void obtrudeValue(T value) {
        this.result = value == null ? NIL : value;
        postComplete();
    }

    public void obtrudeException(Throwable ex) {
        if (ex != null) {
            this.result = new AltResult(ex);
            postComplete();
            return;
        }
        throw new NullPointerException();
    }

    public int getNumberOfDependents() {
        int count = 0;
        for (Completion p = this.stack; p != null; p = p.next) {
            count++;
        }
        return count;
    }

    public String toString() {
        String str;
        Object r = this.result;
        int count = 0;
        for (Completion p = this.stack; p != null; p = p.next) {
            count++;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        if (r == null) {
            str = count == 0 ? "[Not completed]" : "[Not completed, " + count + " dependents]";
        } else if (!(r instanceof AltResult) || ((AltResult) r).ex == null) {
            str = "[Completed normally]";
        } else {
            str = "[Completed exceptionally]";
        }
        sb.append(str);
        return sb.toString();
    }

    public <U> CompletableFuture<U> newIncompleteFuture() {
        return new CompletableFuture<>();
    }

    public Executor defaultExecutor() {
        return ASYNC_POOL;
    }

    public CompletableFuture<T> copy() {
        return uniCopyStage();
    }

    public CompletionStage<T> minimalCompletionStage() {
        return uniAsMinimalStage();
    }

    public CompletableFuture<T> completeAsync(Supplier<? extends T> supplier, Executor executor) {
        if (supplier == null || executor == null) {
            throw new NullPointerException();
        }
        executor.execute(new AsyncSupply(this, supplier));
        return this;
    }

    public CompletableFuture<T> completeAsync(Supplier<? extends T> supplier) {
        return completeAsync(supplier, defaultExecutor());
    }

    public CompletableFuture<T> orTimeout(long timeout, TimeUnit unit) {
        if (unit != null) {
            if (this.result == null) {
                whenComplete((BiConsumer) new Canceller(Delayer.delay(new Timeout(this), timeout, unit)));
            }
            return this;
        }
        throw new NullPointerException();
    }

    public CompletableFuture<T> completeOnTimeout(T value, long timeout, TimeUnit unit) {
        if (unit != null) {
            if (this.result == null) {
                whenComplete((BiConsumer) new Canceller(Delayer.delay(new DelayedCompleter(this, value), timeout, unit)));
            }
            return this;
        }
        throw new NullPointerException();
    }

    public static Executor delayedExecutor(long delay, TimeUnit unit, Executor executor) {
        if (unit != null && executor != null) {
            return new DelayedExecutor(delay, unit, executor);
        }
        throw new NullPointerException();
    }

    public static Executor delayedExecutor(long delay, TimeUnit unit) {
        if (unit != null) {
            return new DelayedExecutor(delay, unit, ASYNC_POOL);
        }
        throw new NullPointerException();
    }

    public static <U> CompletionStage<U> completedStage(U value) {
        return new MinimalStage(value == null ? NIL : value);
    }

    public static <U> CompletableFuture<U> failedFuture(Throwable ex) {
        if (ex != null) {
            return new CompletableFuture<>(new AltResult(ex));
        }
        throw new NullPointerException();
    }

    public static <U> CompletionStage<U> failedStage(Throwable ex) {
        if (ex != null) {
            return new MinimalStage(new AltResult(ex));
        }
        throw new NullPointerException();
    }

    static final class Delayer {
        static final ScheduledThreadPoolExecutor delayer;

        Delayer() {
        }

        static ScheduledFuture<?> delay(Runnable command, long delay, TimeUnit unit) {
            return delayer.schedule(command, delay, unit);
        }

        static final class DaemonThreadFactory implements ThreadFactory {
            DaemonThreadFactory() {
            }

            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setDaemon(true);
                t.setName("CompletableFutureDelayScheduler");
                return t;
            }
        }

        static {
            ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1, new DaemonThreadFactory());
            delayer = scheduledThreadPoolExecutor;
            scheduledThreadPoolExecutor.setRemoveOnCancelPolicy(true);
        }
    }

    static final class DelayedExecutor implements Executor {
        final long delay;
        final Executor executor;
        final TimeUnit unit;

        DelayedExecutor(long delay2, TimeUnit unit2, Executor executor2) {
            this.delay = delay2;
            this.unit = unit2;
            this.executor = executor2;
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable r) {
            Delayer.delay(new TaskSubmitter(this.executor, r), this.delay, this.unit);
        }
    }

    static final class TaskSubmitter implements Runnable {
        final Runnable action;
        final Executor executor;

        TaskSubmitter(Executor executor2, Runnable action2) {
            this.executor = executor2;
            this.action = action2;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.executor.execute(this.action);
        }
    }

    static final class Timeout implements Runnable {
        final CompletableFuture<?> f;

        Timeout(CompletableFuture<?> f2) {
            this.f = f2;
        }

        @Override // java.lang.Runnable
        public void run() {
            CompletableFuture<?> completableFuture = this.f;
            if (completableFuture != null && !completableFuture.isDone()) {
                this.f.completeExceptionally(new TimeoutException());
            }
        }
    }

    static final class DelayedCompleter<U> implements Runnable {
        final CompletableFuture<U> f;
        final U u;

        DelayedCompleter(CompletableFuture<U> f2, U u2) {
            this.f = f2;
            this.u = u2;
        }

        @Override // java.lang.Runnable
        public void run() {
            CompletableFuture<U> completableFuture = this.f;
            if (completableFuture != null) {
                completableFuture.complete(this.u);
            }
        }
    }

    static final class Canceller implements BiConsumer<Object, Throwable> {
        final Future<?> f;

        Canceller(Future<?> f2) {
            this.f = f2;
        }

        public void accept(Object ignore, Throwable ex) {
            Future<?> future;
            if (ex == null && (future = this.f) != null && !future.isDone()) {
                this.f.cancel(false);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static final class MinimalStage<T> extends CompletableFuture<T> {
        MinimalStage() {
        }

        MinimalStage(Object r) {
            super(r);
        }

        @Override // java.util.concurrent.CompletableFuture
        public <U> CompletableFuture<U> newIncompleteFuture() {
            return new MinimalStage();
        }

        @Override // java.util.concurrent.Future, java.util.concurrent.CompletableFuture
        public T get() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.concurrent.Future, java.util.concurrent.CompletableFuture
        public T get(long timeout, TimeUnit unit) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.concurrent.CompletableFuture
        public T getNow(T t) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.concurrent.CompletableFuture
        public T join() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.concurrent.CompletableFuture
        public boolean complete(T t) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.concurrent.CompletableFuture
        public boolean completeExceptionally(Throwable ex) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.concurrent.Future, java.util.concurrent.CompletableFuture
        public boolean cancel(boolean mayInterruptIfRunning) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.concurrent.CompletableFuture
        public void obtrudeValue(T t) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.concurrent.CompletableFuture
        public void obtrudeException(Throwable ex) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.concurrent.Future, java.util.concurrent.CompletableFuture
        public boolean isDone() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.concurrent.Future, java.util.concurrent.CompletableFuture
        public boolean isCancelled() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.concurrent.CompletableFuture
        public boolean isCompletedExceptionally() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.concurrent.CompletableFuture
        public int getNumberOfDependents() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.concurrent.CompletableFuture
        public CompletableFuture<T> completeAsync(Supplier<? extends T> supplier, Executor executor) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.concurrent.CompletableFuture
        public CompletableFuture<T> completeAsync(Supplier<? extends T> supplier) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.concurrent.CompletableFuture
        public CompletableFuture<T> orTimeout(long timeout, TimeUnit unit) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.concurrent.CompletableFuture
        public CompletableFuture<T> completeOnTimeout(T t, long timeout, TimeUnit unit) {
            throw new UnsupportedOperationException();
        }
    }
}
