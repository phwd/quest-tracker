package com.facebook.msys.mci;

import X.AnonymousClass1Kd;
import X.AnonymousClass1Nh;
import X.AnonymousClass1O1;
import X.AnonymousClass1O7;
import X.C06021Nx;
import android.annotation.SuppressLint;
import android.os.Trace;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.msys.mci.network.common.DataTask;
import com.facebook.msys.mci.network.common.DataTaskListener;
import com.facebook.msys.mci.network.common.NetworkSessionCallbacks;
import com.facebook.msys.mci.network.common.UrlResponse;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.simplejni.NativeHolder;
import java.io.IOException;
import java.util.HashMap;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@DoNotStrip
@SuppressLint({"MissingNativeLoadLibrary"})
@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public class NetworkSession implements AnonymousClass1O1 {
    public final HashMap<String, NetworkSessionCallbacks.ProgressObserverCallback> mCallbackMap = new HashMap<>();
    @Nullable
    public final DataTaskListener mDataTaskListener;
    @Nullable
    public final C06021Nx mDisposer;
    @DoNotStrip
    public final NativeHolder mNativeHolder;
    public final NotificationCenter mNotificationCenter;

    @DoNotStrip
    private native void canHandleStreamingUploadUpdate(String str);

    @DoNotStrip
    private native NativeHolder initNativeHolder(String str, NotificationCenter notificationCenter);

    @DoNotStrip
    private native void markDataTaskAsCompleted(String str, String str2, int i, UrlResponse urlResponse, @Nullable byte[] bArr, @Nullable String str3, @Nullable Throwable th);

    @DoNotStrip
    private native void nativeDispose();

    @DoNotStrip
    private native void registerDownloadTaskProgressObserver(String str);

    @DoNotStrip
    private native void registerUploadTaskProgressObserver(String str);

    @DoNotStrip
    private native void setNetworkStateConnectedNative(NotificationCenter notificationCenter);

    @DoNotStrip
    private native void setNetworkStateDisconnectedNative(NotificationCenter notificationCenter);

    @DoNotStrip
    private native void updateDataTaskDownloadProgress(String str, long j, long j2, long j3);

    @DoNotStrip
    private native void updateDataTaskUploadProgress(String str, long j, long j2, long j3);

    @Override // X.AnonymousClass1O1
    public void executeInNetworkContext(AnonymousClass1Kd r3) {
        Execution.executeAsyncWithPriority(r3, 3, 0);
    }

    @DoNotStrip
    public native int getNetworkSessionTimeoutIntervalMs();

    @DoNotStrip
    public native DataTask[] getPendingDataTasks();

    @Override // X.AnonymousClass1O1
    @GuardedBy("this")
    public synchronized void markDataTaskAsCompletedCallback(String str, String str2, int i, UrlResponse urlResponse, @Nullable byte[] bArr, @Nullable String str3, @Nullable IOException iOException) {
        markDataTaskAsCompleted(str, str2, i, urlResponse, bArr, str3, iOException);
        if (this.mCallbackMap.containsKey(str2)) {
            this.mCallbackMap.remove(str2);
        }
    }

    @DoNotStrip
    private void dispatchProgressUpdateToObserver(String str, long j, long j2, long j3) {
        if (this.mCallbackMap.containsKey(str)) {
            this.mCallbackMap.get(str);
        }
    }

    @DoNotStrip
    private void onNewDataTask(DataTask dataTask) {
        DataTaskListener dataTaskListener = this.mDataTaskListener;
        if (dataTaskListener != null) {
            dataTaskListener.onNewTask(dataTask, this);
        }
    }

    @DoNotStrip
    private void onUpdateStreamingDataTask(byte[] bArr, String str) {
        DataTaskListener dataTaskListener = this.mDataTaskListener;
        if (dataTaskListener != null) {
            dataTaskListener.onUpdateStreamingDataTask(bArr, str, this);
        }
    }

    static {
        AnonymousClass1Nh.A00();
    }

    public NetworkSession(String str, NotificationCenter notificationCenter, AnonymousClass1O7 r4) {
        Trace.beginSection("NetworkSession.new");
        try {
            this.mNotificationCenter = notificationCenter;
            this.mDataTaskListener = r4.A01;
            this.mDisposer = new C06021Nx(this, r4);
            this.mNativeHolder = initNativeHolder(str, notificationCenter);
            int networkSessionTimeoutIntervalMs = getNetworkSessionTimeoutIntervalMs();
            if (networkSessionTimeoutIntervalMs > 0) {
                r4.A00 = networkSessionTimeoutIntervalMs;
            }
        } finally {
            Trace.endSection();
        }
    }

    @Override // X.AnonymousClass1O1
    public void updateDataTaskDownloadProgressCallback(String str, long j, long j2, long j3) {
        updateDataTaskDownloadProgress(str, j, j2, j3);
    }

    @Override // X.AnonymousClass1O1
    public void updateDataTaskUploadProgressCallback(String str, long j, long j2, long j3) {
        updateDataTaskUploadProgress(str, j, j2, j3);
    }
}
