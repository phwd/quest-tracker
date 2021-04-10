package defpackage;

/* renamed from: i6  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC2980i6 implements Runnable {
    public final /* synthetic */ String[] F;
    public final /* synthetic */ HB0 G;
    public final /* synthetic */ I2 H;

    public RunnableC2980i6(I2 i2, String[] strArr, HB0 hb0) {
        this.H = i2;
        this.F = strArr;
        this.G = hb0;
    }

    public void run() {
        int[] iArr = new int[this.F.length];
        int i = 0;
        while (true) {
            String[] strArr = this.F;
            if (i < strArr.length) {
                iArr[i] = this.H.hasPermission(strArr[i]) ? 0 : -1;
                i++;
            } else {
                this.G.b(strArr, iArr);
                return;
            }
        }
    }
}
