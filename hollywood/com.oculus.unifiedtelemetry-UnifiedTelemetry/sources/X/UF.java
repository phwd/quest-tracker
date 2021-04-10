package X;

import com.google.common.annotations.GwtCompatible;
import com.google.common.util.concurrent.ListenableFuture;

@GwtCompatible(emulated = true)
public abstract class UF<V> implements ListenableFuture<V> {
}
