package com.facebook.msys.mci;

import X.AnonymousClass0MD;
import X.AnonymousClass1Nh;
import X.AnonymousClass1ZE;
import X.C06341Yz;
import android.annotation.SuppressLint;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.proguard.annotations.DoNotStrip;
import javax.annotation.concurrent.ThreadSafe;

@DoNotStrip
@SuppressLint({"MissingNativeLoadLibrary"})
@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public class Log {
    public static boolean sRegistered;

    @DoNotStrip
    public static native void registerLoggerNative(long j, int i);

    @DoNotStrip
    public static native void setLogLevel(int i);

    @DoNotStrip
    public static void log(int i, String str) {
        if (AnonymousClass0MD.A01.A64(i)) {
            AnonymousClass0MD.A01.A6J(i, "msys", str);
        }
        if (i >= 5) {
            synchronized (C06341Yz.A01) {
                System.currentTimeMillis();
                AnonymousClass1ZE r2 = new AnonymousClass1ZE();
                AnonymousClass1ZE[] r1 = C06341Yz.A02;
                int i2 = C06341Yz.A00;
                r1[i2] = r2;
                C06341Yz.A00 = (i2 + 1) % 100;
            }
        }
    }

    static {
        AnonymousClass1Nh.A00();
    }
}
