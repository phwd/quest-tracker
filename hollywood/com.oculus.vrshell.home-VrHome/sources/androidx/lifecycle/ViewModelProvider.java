package androidx.lifecycle;

public class ViewModelProvider {
    private final Factory mFactory;
    private final ViewModelStore mViewModelStore;

    public interface Factory {
        <T extends ViewModel> T create(Class<T> cls);
    }

    /* access modifiers changed from: package-private */
    public static class OnRequeryFactory {
        OnRequeryFactory() {
        }

        /* access modifiers changed from: package-private */
        public void onRequery(ViewModel viewModel) {
        }
    }

    /* access modifiers changed from: package-private */
    public static abstract class KeyedFactory extends OnRequeryFactory implements Factory {
        public abstract <T extends ViewModel> T create(String str, Class<T> cls);

        KeyedFactory() {
        }

        @Override // androidx.lifecycle.ViewModelProvider.Factory
        public <T extends ViewModel> T create(Class<T> cls) {
            throw new UnsupportedOperationException("create(String, Class<?>) must be called on implementaions of KeyedFactory");
        }
    }

    public ViewModelProvider(ViewModelStore store, Factory factory) {
        this.mFactory = factory;
        this.mViewModelStore = store;
    }

    public <T extends ViewModel> T get(Class<T> modelClass) {
        String canonicalName = modelClass.getCanonicalName();
        if (canonicalName != null) {
            return (T) get("androidx.lifecycle.ViewModelProvider.DefaultKey:" + canonicalName, modelClass);
        }
        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    }

    public <T extends ViewModel> T get(String key, Class<T> modelClass) {
        T t = (T) this.mViewModelStore.get(key);
        if (modelClass.isInstance(t)) {
            if (this.mFactory instanceof OnRequeryFactory) {
                ((OnRequeryFactory) this.mFactory).onRequery(t);
            }
            return t;
        }
        if (t != null) {
        }
        ViewModel viewModel = this.mFactory instanceof KeyedFactory ? (T) ((KeyedFactory) this.mFactory).create(key, modelClass) : (T) this.mFactory.create(modelClass);
        this.mViewModelStore.put(key, viewModel);
        return (T) viewModel;
    }
}
