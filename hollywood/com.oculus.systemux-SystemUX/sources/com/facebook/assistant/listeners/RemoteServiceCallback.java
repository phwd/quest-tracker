package com.facebook.assistant.listeners;

import android.os.RemoteException;

public interface RemoteServiceCallback<T> {
    boolean run(String str, T t) throws RemoteException;
}
