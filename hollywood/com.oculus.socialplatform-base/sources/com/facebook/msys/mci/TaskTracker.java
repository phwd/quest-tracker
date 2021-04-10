package com.facebook.msys.mci;

import X.AnonymousClass1Nh;
import android.annotation.SuppressLint;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.simplejni.NativeHolder;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
@SuppressLint({"MissingNativeLoadLibrary"})
@Nullsafe(Nullsafe.Mode.LOCAL)
public class TaskTracker {
    public static final TaskTracker TRACKER_CRYPTO = new TaskTracker(5, "Crypto");
    public static final TaskTracker TRACKER_DECODING = new TaskTracker(4, "Decoding");
    public static final TaskTracker TRACKER_DISK_IO = new TaskTracker(2, "Disk IO");
    public static final TaskTracker TRACKER_MAIN = new TaskTracker(1, "Main");
    public static final TaskTracker TRACKER_NETWORK = new TaskTracker(3, "Network");
    public static volatile boolean sInitialized;
    public final int mExecutionContext;
    @DoNotStrip
    @Nullable
    public NativeHolder mNativeHolder;
    public final String mQueueName;

    @DoNotStrip
    public static native NativeHolder initNativeHolder(int i, String str);

    @DoNotStrip
    private native long nativeGetLong(int i);

    @DoNotStrip
    @Nullable
    private native String nativeGetString(int i);

    @DoNotStrip
    private native int nativeGetTaskCount();

    static {
        AnonymousClass1Nh.A00();
    }

    public TaskTracker(int i, String str) {
        this.mExecutionContext = i;
        this.mQueueName = str;
    }
}
