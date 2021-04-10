package defpackage;

/* renamed from: xq0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5660xq0 {

    /* renamed from: a  reason: collision with root package name */
    public final long f11640a;
    public final String b;

    public C5660xq0(long j, String str) {
        this.f11640a = j;
        this.b = str;
    }

    public static C5660xq0 a(String str) {
        int indexOf = str.indexOf(",");
        return new C5660xq0(Long.parseLong(str.substring(0, indexOf)), str.substring(indexOf + 1));
    }
}
