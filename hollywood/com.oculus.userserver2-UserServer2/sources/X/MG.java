package X;

import com.google.common.annotations.GwtCompatible;
import com.google.common.util.concurrent.ListenableFuture;
import com.oculus.common.build.BuildConfig;

@GwtCompatible(emulated = BuildConfig.IS_LIBCXX_BUILD)
public abstract class MG<V> implements ListenableFuture<V> {
}
