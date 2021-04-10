package defpackage;

import android.content.ContentValues;

/* renamed from: Zq  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1565Zq {

    /* renamed from: a  reason: collision with root package name */
    public String f9375a;
    public Long b;

    public static C1565Zq a(ContentValues contentValues) {
        C1565Zq zq = new C1565Zq();
        if (contentValues.containsKey("search")) {
            zq.f9375a = contentValues.getAsString("search");
        }
        if (contentValues.containsKey("date")) {
            zq.b = contentValues.getAsLong("date");
        }
        return zq;
    }
}
