package android.support.v4.app;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider$Factory;
import android.support.v4.util.SparseArrayCompat;

class LoaderManagerImpl$LoaderViewModel extends ViewModel {
    private static final ViewModelProvider$Factory FACTORY = new ViewModelProvider$Factory() {
        /* class android.support.v4.app.LoaderManagerImpl$LoaderViewModel.AnonymousClass1 */
    };
    private boolean mCreatingLoader = false;
    private SparseArrayCompat<Object> mLoaders = new SparseArrayCompat<>();

    LoaderManagerImpl$LoaderViewModel() {
    }
}
