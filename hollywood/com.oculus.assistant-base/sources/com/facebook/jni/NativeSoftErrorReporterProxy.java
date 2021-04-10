package com.facebook.jni;

import X.AnonymousClass08;
import X.C0120Bn;
import X.C0121Bo;
import java.util.LinkedList;

public final class NativeSoftErrorReporterProxy {
    public static final LinkedList sSoftErrorCache = new LinkedList();

    public static native void generateNativeSoftError();

    public static void softReport(int i, String str, String str2, int i2) {
        softReport(i, str, str2, null, i2);
    }

    public static void softReport(int i, String str, String str2, Throwable th, int i2) {
        String str3;
        if (i != 1) {
            str3 = i != 2 ? "<level:unknown> " : "<level:mustfix> ";
        } else {
            str3 = "<level:warning> ";
        }
        String A05 = AnonymousClass08.A05("[Native] ", str3, str);
        synchronized (NativeSoftErrorReporterProxy.class) {
            LinkedList linkedList = sSoftErrorCache;
            synchronized (linkedList) {
                C0121Bo bo = new C0121Bo();
                bo.A01 = A05;
                bo.A02 = str2;
                bo.A03 = th;
                bo.A00 = i2;
                linkedList.addLast(new C0120Bn(bo));
                while (linkedList.size() >= 50) {
                    linkedList.removeFirst();
                }
            }
        }
        synchronized (NativeSoftErrorReporterProxy.class) {
        }
    }
}
