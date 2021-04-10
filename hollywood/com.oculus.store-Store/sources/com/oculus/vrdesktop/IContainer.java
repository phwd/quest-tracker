package com.oculus.vrdesktop;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.InputEvent;

public interface IContainer extends IInterface {
    void addBackgroundSurface() throws RemoteException;

    void clear() throws RemoteException;

    void finish() throws RemoteException;

    boolean injectEvent(InputEvent inputEvent) throws RemoteException;

    boolean isActive() throws RemoteException;

    void pauseActivities() throws RemoteException;

    void resumeTopActivity() throws RemoteException;

    void setCallerToken(IBinder iBinder) throws RemoteException;

    void setInputFocusEnabled(boolean z) throws RemoteException;

    void setVolume(float f) throws RemoteException;

    void startActivity(Intent intent) throws RemoteException;

    public static class Default implements IContainer {
        public Default() {
            throw new RuntimeException("Stub!");
        }

        @Override // com.oculus.vrdesktop.IContainer
        public boolean injectEvent(InputEvent event) throws RemoteException {
            throw new RuntimeException("Stub!");
        }

        @Override // com.oculus.vrdesktop.IContainer
        public void finish() throws RemoteException {
            throw new RuntimeException("Stub!");
        }

        @Override // com.oculus.vrdesktop.IContainer
        public boolean isActive() throws RemoteException {
            throw new RuntimeException("Stub!");
        }

        @Override // com.oculus.vrdesktop.IContainer
        public void startActivity(Intent intent) throws RemoteException {
            throw new RuntimeException("Stub!");
        }

        @Override // com.oculus.vrdesktop.IContainer
        public void setCallerToken(IBinder token) throws RemoteException {
            throw new RuntimeException("Stub!");
        }

        @Override // com.oculus.vrdesktop.IContainer
        public void addBackgroundSurface() throws RemoteException {
            throw new RuntimeException("Stub!");
        }

        @Override // com.oculus.vrdesktop.IContainer
        public void setInputFocusEnabled(boolean enabled) throws RemoteException {
            throw new RuntimeException("Stub!");
        }

        @Override // com.oculus.vrdesktop.IContainer
        public void pauseActivities() throws RemoteException {
            throw new RuntimeException("Stub!");
        }

        @Override // com.oculus.vrdesktop.IContainer
        public void resumeTopActivity() throws RemoteException {
            throw new RuntimeException("Stub!");
        }

        @Override // com.oculus.vrdesktop.IContainer
        public void setVolume(float volume) throws RemoteException {
            throw new RuntimeException("Stub!");
        }

        @Override // com.oculus.vrdesktop.IContainer
        public void clear() throws RemoteException {
            throw new RuntimeException("Stub!");
        }

        public IBinder asBinder() {
            throw new RuntimeException("Stub!");
        }
    }

    public static abstract class Stub extends Binder implements IContainer {
        public Stub() {
            throw new RuntimeException("Stub!");
        }

        public static IContainer asInterface(IBinder obj) {
            throw new RuntimeException("Stub!");
        }

        public IBinder asBinder() {
            throw new RuntimeException("Stub!");
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            throw new RuntimeException("Stub!");
        }

        public static boolean setDefaultImpl(IContainer impl) {
            throw new RuntimeException("Stub!");
        }

        public static IContainer getDefaultImpl() {
            throw new RuntimeException("Stub!");
        }
    }
}
