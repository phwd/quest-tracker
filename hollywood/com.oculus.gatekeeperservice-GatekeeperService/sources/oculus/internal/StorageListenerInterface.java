package oculus.internal;

public interface StorageListenerInterface {

    public interface Callback {
        void onStorageChange();
    }

    void unregister();

    static default StorageListenerInterface register(Callback callback) {
        return StorageListener.create(callback);
    }

    public static class Null implements StorageListenerInterface {
        @Override // oculus.internal.StorageListenerInterface
        public void unregister() {
        }
    }
}
