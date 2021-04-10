package com.oculus.clay.app;

import android.os.Trace;
import android.util.Log;

public class a {
    private final String a;
    private final String b;
    private Boolean c = Boolean.FALSE;

    public a(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    /* access modifiers changed from: package-private */
    public void a(Boolean bool) {
        this.c = bool;
    }

    public void a(String str) {
        Trace.beginSection(this.b + " " + str);
        if (this.c.booleanValue()) {
            String str2 = this.a;
            Log.i(str2, this.b + " " + str);
        }
    }
}
