package com.facebook.msys.mci;

import X.AnonymousClass1Nh;
import android.annotation.SuppressLint;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.proguard.annotations.DoNotStrip;
import java.util.HashMap;
import javax.annotation.concurrent.ThreadSafe;

@DoNotStrip
@SuppressLint({"MissingNativeLoadLibrary"})
@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public class Stats {
    @DoNotStrip
    public static native void decrement(int i);

    @DoNotStrip
    public static native HashMap<String, Number> getPerformanceData();

    @DoNotStrip
    public static native void increment(int i);

    static {
        AnonymousClass1Nh.A00();
    }
}
