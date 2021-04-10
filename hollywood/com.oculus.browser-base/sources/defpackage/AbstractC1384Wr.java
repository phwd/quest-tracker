package defpackage;

/* renamed from: Wr  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC1384Wr {
    public static void a(C0074Be0 be0) {
        C5624xe0 xe0 = (C5624xe0) AbstractC0196De0.f7901a.get(be0.k);
        if (xe0 == null) {
            xe0 = new C5624xe0(new C1323Vr(be0.k));
            AbstractC0196De0.f7901a.put(be0.k, xe0);
        }
        C5454we0 we0 = xe0.g;
        C0074Be0 be02 = we0.d;
        if (be02 == null) {
            be02 = we0.f11556a.e;
        }
        if (!C5624xe0.g(be02, be0)) {
            if (we0.c == null) {
                we0.a(be0);
            } else {
                we0.d = be0;
            }
        }
    }
}
