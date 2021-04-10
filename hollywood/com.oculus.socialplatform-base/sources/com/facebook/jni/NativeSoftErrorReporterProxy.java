package com.facebook.jni;

import X.AnonymousClass006;
import X.AnonymousClass0I6;
import X.AnonymousClass0I7;
import com.facebook.proguard.annotations.DoNotStrip;
import java.util.LinkedList;

@DoNotStrip
public final class NativeSoftErrorReporterProxy {
    public static final LinkedList<AnonymousClass0I6> sSoftErrorCache = new LinkedList<>();

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
        String A09 = AnonymousClass006.A09("[Native] ", str3, str);
        synchronized (NativeSoftErrorReporterProxy.class) {
            LinkedList<AnonymousClass0I6> linkedList = sSoftErrorCache;
            synchronized (linkedList) {
                AnonymousClass0I7 r1 = new AnonymousClass0I7();
                r1.A01 = A09;
                r1.A02 = str2;
                r1.A03 = th;
                r1.A00 = i2;
                linkedList.addLast(new AnonymousClass0I6(r1));
                while (linkedList.size() >= 50) {
                    linkedList.removeFirst();
                }
            }
        }
        synchronized (NativeSoftErrorReporterProxy.class) {
        }
    }
}
