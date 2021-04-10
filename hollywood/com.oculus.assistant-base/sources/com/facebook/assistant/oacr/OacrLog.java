package com.facebook.assistant.oacr;

import X.AnonymousClass08;
import X.C0139Dd;

public class OacrLog {
    public static void log(int i, String str, String str2) {
        try {
            String A05 = AnonymousClass08.A05(str, " ", str2);
            if (C0139Dd.A01.A3Y(i)) {
                C0139Dd.A01.A3k(i, "GLOG_B", A05);
            }
        } catch (Exception unused) {
            C0139Dd.A0O("GLOG_B_Error", "logging violation at %s", str);
        }
    }
}
