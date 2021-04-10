package defpackage;

/* renamed from: Ww0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1395Ww0 implements D30 {

    /* renamed from: a  reason: collision with root package name */
    public static final D30 f9182a = new C1395Ww0();

    @Override // defpackage.D30
    public boolean a(int i) {
        char c = 4;
        if (i == 0) {
            c = 1;
        } else if (i == 1) {
            c = 2;
        } else if (i == 2) {
            c = 3;
        } else if (i != 3) {
            c = i != 4 ? (char) 0 : 5;
        }
        return c != 0;
    }
}
