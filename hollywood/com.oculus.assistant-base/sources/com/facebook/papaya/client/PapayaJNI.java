package com.facebook.papaya.client;

import X.EnumC0181Gs;
import X.KJ;
import android.content.ComponentName;
import android.content.Context;
import android.os.Looper;
import com.facebook.jni.HybridClassBase;
import com.facebook.papaya.client.executor.IExecutorFactory;
import com.facebook.papaya.client.platform.IDispatcher;
import com.facebook.papaya.client.platform.IFilesystem;
import com.facebook.papaya.client.platform.IPlatform;
import com.facebook.papaya.client.transport.ITransport;
import com.google.common.base.Preconditions;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class PapayaJNI extends HybridClassBase {
    public static native void nativeAddLogSink(int i, ILogSink iLogSink);

    public static native void nativeCancelAllExecutors();

    public static native void nativeCancelExecutor(String str);

    public static native void nativeInitialize(String str, Context context, ComponentName componentName, ITransport iTransport, IPlatform iPlatform, IDispatcher iDispatcher, IFilesystem iFilesystem, Map map, String str2);

    public static native void nativeRegisterExecutor(String str, IExecutorFactory iExecutorFactory);

    public static native void nativeRun();

    public static native void nativeSetCallback(ICallback iCallback);

    public static native void nativeStop();

    public static native void nativeSubmitExecutor(String str);

    public static native void nativeUninitialize();

    static {
        KJ.A05("papaya", 0);
    }

    public static synchronized void initialize(String str, Context context, ComponentName componentName, ITransport iTransport, IPlatform iPlatform, IDispatcher iDispatcher, IFilesystem iFilesystem, List list, Map map, String str2) {
        synchronized (PapayaJNI.class) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                KJ.A05((String) it.next(), 16);
            }
            if (str == null) {
                throw null;
            } else if (context != null) {
                Context applicationContext = context.getApplicationContext();
                if (componentName == null) {
                    throw null;
                } else if (iTransport != null) {
                    nativeInitialize(str, applicationContext, componentName, iTransport, iPlatform, iDispatcher, iFilesystem, map, str2);
                } else {
                    throw null;
                }
            } else {
                throw null;
            }
        }
    }

    public static void addLogSink(EnumC0181Gs gs, ILogSink iLogSink) {
        nativeAddLogSink(gs.getValue(), iLogSink);
    }

    public static void cancelAllExecutors() {
        nativeCancelAllExecutors();
    }

    public static void run() {
        boolean z = false;
        if (Looper.myLooper() != Looper.getMainLooper()) {
            z = true;
        }
        Preconditions.checkState(z, "Papaya.run() may only be invoked from background thread!");
        nativeRun();
    }

    public static void stop() {
        boolean z = false;
        if (Looper.myLooper() != Looper.getMainLooper()) {
            z = true;
        }
        Preconditions.checkState(z, "Papaya.stop() may only be invoked from background thread!");
        nativeStop();
    }

    public static void uninitialize() {
        nativeUninitialize();
    }

    public static void cancelExecutor(String str) {
        nativeCancelExecutor(str);
    }

    public static void setCallback(ICallback iCallback) {
        nativeSetCallback(iCallback);
    }

    public static void submitExecutor(String str) {
        nativeSubmitExecutor(str);
    }

    public static void registerExecutor(String str, IExecutorFactory iExecutorFactory) {
        nativeRegisterExecutor(str, iExecutorFactory);
    }
}
