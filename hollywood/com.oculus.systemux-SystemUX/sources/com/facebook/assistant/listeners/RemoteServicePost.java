package com.facebook.assistant.listeners;

import android.os.RemoteException;

public interface RemoteServicePost<T> {
    void run(String str, T t) throws RemoteException;
}
