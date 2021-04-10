package com.google.common.util.concurrent;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.AbstractFuture;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

/* access modifiers changed from: package-private */
public abstract class AbstractTransformFuture<I, O, F, T> extends AbstractFuture.TrustedFuture<O> implements Runnable {
    F function;
    ListenableFuture<? extends I> inputFuture;

    /* access modifiers changed from: package-private */
    public abstract T doTransform(F f, I i) throws Exception;

    /* access modifiers changed from: package-private */
    public abstract void setResult(T t);

    static <I, O> ListenableFuture<O> create(ListenableFuture<I> input, Function<? super I, ? extends O> function2, Executor executor) {
        Preconditions.checkNotNull(function2);
        TransformFuture<I, O> output = new TransformFuture<>(input, function2);
        input.addListener(output, MoreExecutors.rejectionPropagatingExecutor(executor, output));
        return output;
    }

    AbstractTransformFuture(ListenableFuture<? extends I> inputFuture2, F function2) {
        this.inputFuture = (ListenableFuture) Preconditions.checkNotNull(inputFuture2);
        this.function = (F) Preconditions.checkNotNull(function2);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r11v0, resolved type: com.google.common.util.concurrent.AbstractTransformFuture<I, O, F, T> */
    /* JADX WARN: Multi-variable type inference failed */
    public final void run() {
        boolean z;
        boolean z2 = true;
        ListenableFuture<? extends I> localInputFuture = this.inputFuture;
        F localFunction = this.function;
        boolean isCancelled = isCancelled();
        if (localInputFuture == null) {
            z = true;
        } else {
            z = false;
        }
        boolean z3 = z | isCancelled;
        if (localFunction != null) {
            z2 = false;
        }
        if (!z2 && !z3) {
            this.inputFuture = null;
            try {
                try {
                    T transformResult = doTransform(localFunction, Futures.getDone(localInputFuture));
                    this.function = null;
                    setResult(transformResult);
                } catch (Throwable th) {
                    this.function = null;
                    throw th;
                }
            } catch (CancellationException e) {
                cancel(false);
            } catch (ExecutionException e2) {
                setException(e2.getCause());
            } catch (RuntimeException e3) {
                setException(e3);
            } catch (Error e4) {
                setException(e4);
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.google.common.util.concurrent.AbstractFuture
    public final void afterDone() {
        maybePropagateCancellationTo(this.inputFuture);
        this.inputFuture = null;
        this.function = null;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.common.util.concurrent.AbstractFuture
    public String pendingToString() {
        ListenableFuture<? extends I> localInputFuture = this.inputFuture;
        F localFunction = this.function;
        String superString = super.pendingToString();
        String resultString = "";
        if (localInputFuture != null) {
            resultString = "inputFuture=[" + localInputFuture + "], ";
        }
        if (localFunction != null) {
            return resultString + "function=[" + ((Object) localFunction) + "]";
        }
        if (superString != null) {
            return resultString + superString;
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static final class TransformFuture<I, O> extends AbstractTransformFuture<I, O, Function<? super I, ? extends O>, O> {
        /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: java.lang.Object */
        /* JADX WARN: Multi-variable type inference failed */
        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.AbstractTransformFuture
        public /* bridge */ /* synthetic */ Object doTransform(Object obj, Object obj2) throws Exception {
            return doTransform((Function) ((Function) obj), obj2);
        }

        TransformFuture(ListenableFuture<? extends I> inputFuture, Function<? super I, ? extends O> function) {
            super(inputFuture, function);
        }

        /* access modifiers changed from: package-private */
        public O doTransform(Function<? super I, ? extends O> function, I input) {
            return (O) function.apply(input);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.AbstractTransformFuture
        public void setResult(O result) {
            set(result);
        }
    }
}
