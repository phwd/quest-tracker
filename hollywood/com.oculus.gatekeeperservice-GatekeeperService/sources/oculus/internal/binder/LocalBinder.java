package oculus.internal.binder;

import android.os.Binder;

public class LocalBinder<T> extends Binder {
    private final T mService;

    public LocalBinder(T service) {
        this.mService = service;
    }

    public T getService() {
        return this.mService;
    }
}
