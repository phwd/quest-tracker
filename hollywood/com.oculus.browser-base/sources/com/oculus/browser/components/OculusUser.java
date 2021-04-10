package com.oculus.browser.components;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import org.chromium.base.TraceEvent;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class OculusUser {

    /* renamed from: a  reason: collision with root package name */
    public static OculusUser f9719a;
    public static Object b = new Object();
    public String c;
    public Context d;

    public OculusUser(Context context) {
        this.d = context;
        this.c = "Default";
    }

    public static String getUserID() {
        synchronized (b) {
            if (f9719a == null) {
                AbstractC1220Ua0.d("OculusUser", "mInstance == null", new Object[0]);
                while (f9719a == null) {
                    try {
                        b.wait();
                    } catch (InterruptedException unused) {
                        throw new IllegalStateException("OculusUser not instantiated");
                    }
                }
            }
        }
        return f9719a.c;
    }

    public final void a() {
        TraceEvent.Y("OculusUser.queryUserID", null);
        Cursor query = this.d.getContentResolver().query(Uri.parse("content://com.oculus.horizon/profile?app_id=" + "1916519981771802"), null, null, null, null);
        if (query != null) {
            try {
                query.moveToFirst();
                int columnIndex = query.getColumnIndex("id");
                if (columnIndex >= 0 && query.getType(columnIndex) == 3) {
                    this.c = query.getString(columnIndex);
                }
            } catch (Throwable th) {
                AbstractC0754Mh1.f8495a.a(th, th);
            }
        }
        if (query != null) {
            query.close();
        }
        TraceEvent.f0("OculusUser.queryUserID");
        return;
        throw th;
    }

    public OculusUser(String str) {
        this.c = str;
    }
}
