package androidx.loader.app;

import android.annotation.SuppressLint;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.loader.content.Loader;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public abstract class LoaderManager {

    public interface LoaderCallbacks<D> {
        @MainThread
        void onLoadFinished(@NonNull Loader<D> loader, @SuppressLint({"UnknownNullness"}) D d);

        @MainThread
        void onLoaderReset(@NonNull Loader<D> loader);
    }

    @Deprecated
    public abstract void dump(@NonNull String str, @Nullable FileDescriptor fileDescriptor, @NonNull PrintWriter printWriter, @Nullable String[] strArr);

    public abstract void markForRedelivery();

    @NonNull
    public static <T extends LifecycleOwner & ViewModelStoreOwner> LoaderManager getInstance(@NonNull T t) {
        return new LoaderManagerImpl(t, t.getViewModelStore());
    }
}
