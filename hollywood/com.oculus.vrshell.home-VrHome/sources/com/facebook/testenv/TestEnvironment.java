package com.facebook.testenv;

import com.facebook.common.build.BuildConstants;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public abstract class TestEnvironment {
    private static volatile int sIsTest = 0;

    private TestEnvironment() {
    }

    public static boolean isTest() {
        boolean z = true;
        if (sIsTest != 0) {
            return sIsTest == 1;
        }
        synchronized (TestEnvironment.class) {
            if (sIsTest != 0) {
                if (sIsTest != 1) {
                    z = false;
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
            if (sIsTest != 1) {
                z = false;
            }
            return z;
        }
    }

    public static boolean isE2eTest() {
        return hasClass("io.selendroid.client.SelendroidDriver") || hasClass("io.selendroid.server.UncaughtExceptionHandling");
    }

    private static boolean isCamTest() {
        return hasClass("com.facebook.cam.CamInstrumentationTestRunner");
    }

    private static boolean hasClass(String className) {
        try {
            Class.forName(className);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
