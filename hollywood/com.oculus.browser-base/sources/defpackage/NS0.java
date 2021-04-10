package defpackage;

/* renamed from: NS0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class NS0 {

    /* renamed from: a  reason: collision with root package name */
    public static final NS0 f8547a = new NS0();
    public int[] b = new int[4];
    public boolean c = false;

    public static int a(boolean z, boolean z2, boolean z3) {
        if (z) {
            return -1;
        }
        return z2 ? z3 ? 3 : 1 : z3 ? 2 : 0;
    }

    public void b(int i) {
        if (i >= 0) {
            if (this.c) {
                AbstractC3364kK0.g("Servicification.Startup2", i, 4);
                return;
            }
            int[] iArr = this.b;
            iArr[i] = iArr[i] + 1;
        }
    }
}
