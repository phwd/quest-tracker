package com.facebook.jni;

import X.AnonymousClass06;
import X.J5;
import X.J6;
import com.facebook.proguard.annotations.DoNotStrip;
import java.util.LinkedList;

@DoNotStrip
public final class NativeSoftErrorReporterProxy {
    public static final LinkedList<J5> sSoftErrorCache = new LinkedList<>();

    @DoNotStrip
    public static native void generateNativeSoftError();

    @DoNotStrip
    public static void softReport(int i, String str, String str2, int i2) {
        softReport(i, str, str2, null, i2);
    }

    @DoNotStrip
    public static void softReport(int i, String str, String str2, Throwable th, int i2) {
        String str3;
        if (i != 1) {
            str3 = i != 2 ? "<level:unknown> " : "<level:mustfix> ";
        } else {
            str3 = "<level:warning> ";
        }
        String A05 = AnonymousClass06.A05("[Native] ", str3, str);
        synchronized (NativeSoftErrorReporterProxy.class) {
            LinkedList<J5> linkedList = sSoftErrorCache;
            synchronized (linkedList) {
                J6 j6 = new J6();
                j6.A01 = A05;
                j6.A02 = str2;
                j6.A03 = th;
                j6.A00 = i2;
                linkedList.addLast(new J5(j6));
                while (linkedList.size() >= 50) {
                    linkedList.removeFirst();
                }
            }
        }
        synchronized (NativeSoftErrorReporterProxy.class) {
        }
    }
}
