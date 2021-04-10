package defpackage;

import android.content.ContentValues;

/* renamed from: Yq  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1504Yq {

    /* renamed from: a  reason: collision with root package name */
    public Boolean f9299a;
    public Long b;
    public String c;
    public Long d;
    public byte[] e;
    public String f;
    public Integer g;
    public long h;

    public static C1504Yq a(ContentValues contentValues) {
        C1504Yq yq = new C1504Yq();
        if (contentValues.containsKey("url")) {
            yq.c = contentValues.getAsString("url");
        }
        if (contentValues.containsKey("bookmark")) {
            yq.f9299a = Boolean.valueOf(contentValues.getAsInteger("bookmark").intValue() != 0);
        }
        if (contentValues.containsKey("created")) {
            yq.b = contentValues.getAsLong("created");
        }
        if (contentValues.containsKey("date")) {
            yq.d = contentValues.getAsLong("date");
        }
        if (contentValues.containsKey("favicon")) {
            byte[] asByteArray = contentValues.getAsByteArray("favicon");
            yq.e = asByteArray;
            if (asByteArray == null) {
                yq.e = new byte[0];
            }
        }
        if (contentValues.containsKey("title")) {
            yq.f = contentValues.getAsString("title");
        }
        if (contentValues.containsKey("visits")) {
            yq.g = contentValues.getAsInteger("visits");
        }
        if (contentValues.containsKey("parentId")) {
            yq.h = contentValues.getAsLong("parentId").longValue();
        }
        return yq;
    }
}
