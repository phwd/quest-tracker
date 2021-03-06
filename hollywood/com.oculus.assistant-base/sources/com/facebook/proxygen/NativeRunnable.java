package com.facebook.proxygen;

public class NativeRunnable extends NativeHandleImpl implements Runnable {
    public native void close();

    public native void run();

    @Override // java.lang.Object
    public void finalize() {
        try {
            close();
        } finally {
            super.finalize();
        }
    }
}
