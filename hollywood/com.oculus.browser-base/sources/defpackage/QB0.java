package defpackage;

/* renamed from: QB0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class QB0 implements HB0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SB0 f8741a;

    public QB0(SB0 sb0) {
        this.f8741a = sb0;
    }

    @Override // defpackage.HB0
    public void b(String[] strArr, int[] iArr) {
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= iArr.length) {
                z = true;
                break;
            } else if (iArr[i] != 0) {
                break;
            } else {
                i++;
            }
        }
        if (z) {
            SB0 sb0 = this.f8741a;
            sb0.g.onResult(sb0.a());
        }
    }
}
