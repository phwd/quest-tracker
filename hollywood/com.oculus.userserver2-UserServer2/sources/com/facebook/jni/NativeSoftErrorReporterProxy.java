package com.facebook.jni;

import X.AnonymousClass06;
import X.JP;
import X.JQ;
import com.facebook.proguard.annotations.DoNotStrip;
import java.util.LinkedList;

@DoNotStrip
public final class NativeSoftErrorReporterProxy {
    public static final LinkedList<JP> sSoftErrorCache = new LinkedList<>();

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
        String A04 = AnonymousClass06.A04("[Native] ", str3, str);
        synchronized (NativeSoftErrorReporterProxy.class) {
            LinkedList<JP> linkedList = sSoftErrorCache;
            synchronized (linkedList) {
                JQ jq = new JQ();
                jq.A01 = A04;
                jq.A02 = str2;
                jq.A03 = th;
                jq.A00 = i2;
                linkedList.addLast(new JP(jq));
                while (linkedList.size() >= 50) {
                    linkedList.removeFirst();
                }
            }
        }
        synchronized (NativeSoftErrorReporterProxy.class) {
        }
    }
}
