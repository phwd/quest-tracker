package com.facebook.testenv;

import com.facebook.common.build.BuildConstants;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public abstract class TestEnvironment {
    private static volatile int sIsTest;

    private TestEnvironment() {
    }

    public static boolean isTest() {
        boolean z = false;
        if (sIsTest == 0) {
            synchronized (TestEnvironment.class) {
                if (sIsTest != 0) {
                    if (sIsTest == 1) {
                        z = true;
                    }
                    return z;
                }
                if (!BuildConstants.isInternalBuild()) {
                    sIsTest = -1;
                } else if (!hasClass("org.junit.Test") || isE2eTest() || isCamTest()) {
                    sIsTest = -1;
                } else {
                    sIsTest = 1;
                }
                if (sIsTest == 1) {
                    z = true;
                }
                return z;
            }
        } else if (sIsTest == 1) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isE2eTest() {
        return hasClass("io.selendroid.client.SelendroidDriver") || hasClass("io.selendroid.server.UncaughtExceptionHandling");
    }

    private static boolean isCamTest() {
        return hasClass("com.facebook.cam.CamInstrumentationTestRunner");
    }

    private static boolean hasClass(String str) {
        try {
            Class.forName(str);
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }
}
