package com.facebook.common.jniexecutors;

import X.AnonymousClass006;
import X.AnonymousClass0MD;
import X.AnonymousClass0lD;
import com.facebook.common.stringformat.StringFormatUtil;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.systrace.Systrace;
import java.util.IllegalFormatException;
import javax.annotation.Nullable;

@DoNotStrip
@Nullsafe(Nullsafe.Mode.LOCAL)
public class NativeRunnable implements Runnable {
    @DoNotStrip
    @Nullable
    public HybridData mHybridData;
    @DoNotStrip
    @Nullable
    public volatile String mNativeExecutor;

    @DoNotStrip
    private native void nativeRun();

    static {
        AnonymousClass0lD.A01("jniexecutors");
    }

    public void run() {
        String str = "%s";
        if (Systrace.A03(32)) {
            try {
                str = StringFormatUtil.formatStrLocaleSafe(str, this);
            } catch (IllegalFormatException e) {
                if (AnonymousClass0MD.A01.A64(6)) {
                    AnonymousClass0MD.A01.ABN("Tracer", "Bad format string", e);
                }
            }
            Systrace.A01(32, str);
        }
        try {
            nativeRun();
        } finally {
            Systrace.A00(32);
        }
    }

    public String toString() {
        String str = this.mNativeExecutor;
        if (str == null) {
            return "NativeRunnable";
        }
        return AnonymousClass006.A07("NativeRunnable - ", str);
    }

    @DoNotStrip
    public NativeRunnable(@Nullable HybridData hybridData) {
        this.mHybridData = hybridData;
    }
}
