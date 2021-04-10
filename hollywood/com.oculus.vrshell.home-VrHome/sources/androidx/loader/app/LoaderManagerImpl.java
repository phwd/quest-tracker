package androidx.loader.app;

import android.os.Bundle;
import android.util.Log;
import androidx.collection.SparseArrayCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import com.adobe.xmp.options.PropertyOptions;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* access modifiers changed from: package-private */
public class LoaderManagerImpl extends LoaderManager {
    static boolean DEBUG = false;
    private final LifecycleOwner mLifecycleOwner;
    private final LoaderViewModel mLoaderViewModel;

    static boolean isLoggingEnabled(int level) {
        return DEBUG || Log.isLoggable("LoaderManager", level);
    }

    public static class LoaderInfo<D> extends MutableLiveData<D> implements Loader.OnLoadCompleteListener<D> {
        private final Bundle mArgs;
        private final int mId;
        private LifecycleOwner mLifecycleOwner;
        private final Loader<D> mLoader;
        private LoaderObserver<D> mObserver;
        private Loader<D> mPriorLoader;

        /* access modifiers changed from: package-private */
        public Loader<D> getLoader() {
            return this.mLoader;
        }

        /* access modifiers changed from: protected */
        @Override // androidx.lifecycle.LiveData
        public void onActive() {
            if (LoaderManagerImpl.isLoggingEnabled(2)) {
                Log.v("LoaderManager", "  Starting: " + this);
            }
            this.mLoader.startLoading();
        }

        /* access modifiers changed from: protected */
        @Override // androidx.lifecycle.LiveData
        public void onInactive() {
            if (LoaderManagerImpl.isLoggingEnabled(2)) {
                Log.v("LoaderManager", "  Stopping: " + this);
            }
            this.mLoader.stopLoading();
        }

        /* access modifiers changed from: package-private */
        public void markForRedelivery() {
            LifecycleOwner lifecycleOwner = this.mLifecycleOwner;
            LoaderObserver<D> observer = this.mObserver;
            if (lifecycleOwner != null && observer != null) {
                super.removeObserver(observer);
                observe(lifecycleOwner, observer);
            }
        }

        /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: androidx.lifecycle.Observer<? super D> */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.lifecycle.LiveData
        public void removeObserver(Observer<? super D> observer) {
            super.removeObserver(observer);
            this.mLifecycleOwner = null;
            this.mObserver = null;
        }

        /* access modifiers changed from: package-private */
        public Loader<D> destroy(boolean reset) {
            if (LoaderManagerImpl.isLoggingEnabled(3)) {
                Log.d("LoaderManager", "  Destroying: " + this);
            }
            this.mLoader.cancelLoad();
            this.mLoader.abandon();
            LoaderObserver<D> observer = this.mObserver;
            if (observer != null) {
                removeObserver(observer);
                if (reset) {
                    observer.reset();
                }
            }
            this.mLoader.unregisterListener(this);
            if ((observer == null || observer.hasDeliveredData()) && !reset) {
                return this.mLoader;
            }
            this.mLoader.reset();
            return this.mPriorLoader;
        }

        @Override // androidx.lifecycle.MutableLiveData, androidx.lifecycle.LiveData
        public void setValue(D value) {
            super.setValue(value);
            if (this.mPriorLoader != null) {
                this.mPriorLoader.reset();
                this.mPriorLoader = null;
            }
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(64);
            sb.append("LoaderInfo{");
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            sb.append(" #");
            sb.append(this.mId);
            sb.append(" : ");
            sb.append(this.mLoader.getClass().getSimpleName());
            sb.append("{");
            sb.append(Integer.toHexString(System.identityHashCode(this.mLoader)));
            sb.append("}}");
            return sb.toString();
        }

        public void dump(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) {
            writer.print(prefix);
            writer.print("mId=");
            writer.print(this.mId);
            writer.print(" mArgs=");
            writer.println(this.mArgs);
            writer.print(prefix);
            writer.print("mLoader=");
            writer.println(this.mLoader);
            this.mLoader.dump(prefix + "  ", fd, writer, args);
            if (this.mObserver != null) {
                writer.print(prefix);
                writer.print("mCallbacks=");
                writer.println(this.mObserver);
                this.mObserver.dump(prefix + "  ", writer);
            }
            writer.print(prefix);
            writer.print("mData=");
            writer.println(getLoader().dataToString((D) getValue()));
            writer.print(prefix);
            writer.print("mStarted=");
            writer.println(hasActiveObservers());
        }
    }

    /* access modifiers changed from: package-private */
    public static class LoaderObserver<D> implements Observer<D> {
        private final LoaderManager.LoaderCallbacks<D> mCallback;
        private boolean mDeliveredData;
        private final Loader<D> mLoader;

        @Override // androidx.lifecycle.Observer
        public void onChanged(D data) {
            if (LoaderManagerImpl.isLoggingEnabled(2)) {
                Log.v("LoaderManager", "  onLoadFinished in " + this.mLoader + ": " + this.mLoader.dataToString(data));
            }
            this.mDeliveredData = true;
            this.mCallback.onLoadFinished(this.mLoader, data);
        }

        /* access modifiers changed from: package-private */
        public boolean hasDeliveredData() {
            return this.mDeliveredData;
        }

        /* access modifiers changed from: package-private */
        public void reset() {
            if (this.mDeliveredData) {
                if (LoaderManagerImpl.isLoggingEnabled(2)) {
                    Log.v("LoaderManager", "  Resetting: " + this.mLoader);
                }
                this.mCallback.onLoaderReset(this.mLoader);
            }
        }

        public String toString() {
            return this.mCallback.toString();
        }

        public void dump(String prefix, PrintWriter writer) {
            writer.print(prefix);
            writer.print("mDeliveredData=");
            writer.println(this.mDeliveredData);
        }
    }

    static class LoaderViewModel extends ViewModel {
        private static final ViewModelProvider.Factory FACTORY = new ViewModelProvider.Factory() {
            /* class androidx.loader.app.LoaderManagerImpl.LoaderViewModel.AnonymousClass1 */

            @Override // androidx.lifecycle.ViewModelProvider.Factory
            public <T extends ViewModel> T create(Class<T> cls) {
                return new LoaderViewModel();
            }
        };
        private boolean mCreatingLoader = false;
        private SparseArrayCompat<LoaderInfo> mLoaders = new SparseArrayCompat<>();

        LoaderViewModel() {
        }

        static LoaderViewModel getInstance(ViewModelStore viewModelStore) {
            return (LoaderViewModel) new ViewModelProvider(viewModelStore, FACTORY).get(LoaderViewModel.class);
        }

        /* access modifiers changed from: package-private */
        public void markForRedelivery() {
            int size = this.mLoaders.size();
            for (int index = 0; index < size; index++) {
                this.mLoaders.valueAt(index).markForRedelivery();
            }
        }

        /* access modifiers changed from: protected */
        @Override // androidx.lifecycle.ViewModel
        public void onCleared() {
            super.onCleared();
            int size = this.mLoaders.size();
            for (int index = 0; index < size; index++) {
                this.mLoaders.valueAt(index).destroy(true);
            }
            this.mLoaders.clear();
        }

        public void dump(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) {
            if (this.mLoaders.size() > 0) {
                writer.print(prefix);
                writer.println("Loaders:");
                String innerPrefix = prefix + "    ";
                for (int i = 0; i < this.mLoaders.size(); i++) {
                    LoaderInfo info = this.mLoaders.valueAt(i);
                    writer.print(prefix);
                    writer.print("  #");
                    writer.print(this.mLoaders.keyAt(i));
                    writer.print(": ");
                    writer.println(info.toString());
                    info.dump(innerPrefix, fd, writer, args);
                }
            }
        }
    }

    LoaderManagerImpl(LifecycleOwner lifecycleOwner, ViewModelStore viewModelStore) {
        this.mLifecycleOwner = lifecycleOwner;
        this.mLoaderViewModel = LoaderViewModel.getInstance(viewModelStore);
    }

    @Override // androidx.loader.app.LoaderManager
    public void markForRedelivery() {
        this.mLoaderViewModel.markForRedelivery();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder((int) PropertyOptions.HAS_TYPE);
        sb.append("LoaderManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        sb.append(this.mLifecycleOwner.getClass().getSimpleName());
        sb.append("{");
        sb.append(Integer.toHexString(System.identityHashCode(this.mLifecycleOwner)));
        sb.append("}}");
        return sb.toString();
    }

    @Override // androidx.loader.app.LoaderManager
    @Deprecated
    public void dump(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) {
        this.mLoaderViewModel.dump(prefix, fd, writer, args);
    }
}
