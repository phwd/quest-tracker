package com.facebook.msys.mcs;

import X.AnonymousClass1NZ;
import android.annotation.SuppressLint;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.msys.mci.AuthData;
import com.facebook.msys.mci.Database;
import com.facebook.msys.mci.DatabaseHealthMonitor;
import com.facebook.msys.mci.NetworkSession;
import com.facebook.msys.mci.NotificationCenter;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.simplejni.NativeHolder;
import javax.annotation.concurrent.ThreadSafe;

@DoNotStrip
@SuppressLint({"MissingNativeLoadLibrary"})
@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public class SyncHandler {
    @DoNotStrip
    public NativeHolder mNativeHolder;

    @DoNotStrip
    private native void checkClientUpdateNative(String str);

    @DoNotStrip
    private native void enableSyncNative();

    @DoNotStrip
    private native void enableTraceTypeNative(int i);

    @DoNotStrip
    private native void executeSyncNative();

    @DoNotStrip
    public static native NativeHolder initNativeHolder(Database database, String str, NetworkSession networkSession, AuthData authData, NotificationCenter notificationCenter, DatabaseHealthMonitor databaseHealthMonitor, DasmConfigCreator dasmConfigCreator, boolean z);

    @DoNotStrip
    private native void notifyAppEnterForegroundNative();

    @DoNotStrip
    private native void notifyTaskResponseProcessedNative();

    @DoNotStrip
    private native void reportAppStateNative();

    @DoNotStrip
    private native void updateAppStateToBackgroundNative();

    @DoNotStrip
    private native void updateAppStateToForegroundNative();

    @DoNotStrip
    private native void updateNetworkStateFromNetworkSessionNative();

    static {
        AnonymousClass1NZ.A00();
    }

    @DoNotStrip
    public SyncHandler(NativeHolder nativeHolder) {
        this.mNativeHolder = nativeHolder;
    }
}
