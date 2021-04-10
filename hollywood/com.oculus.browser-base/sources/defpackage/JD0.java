package defpackage;

/* renamed from: JD0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class JD0 implements Runnable {
    public final KD0 F;

    public JD0(KD0 kd0) {
        this.F = kd0;
    }

    public void run() {
        KD0 kd0 = this.F;
        ID0 id0 = kd0.b;
        if (id0 != null) {
            if (!(id0.d == null || id0.g == null)) {
                for (int i = 0; i < id0.d.length; i++) {
                    int i2 = 0;
                    while (true) {
                        C2952hx[][] hxVarArr = id0.d;
                        if (i2 >= hxVarArr[i].length) {
                            break;
                        }
                        C2952hx hxVar = hxVarArr[i][i2];
                        if (!id0.g[i][i2] && hxVar != null) {
                            hxVar.b();
                            id0.d[i][i2] = null;
                        }
                        i2++;
                    }
                }
            }
            kd0.b(kd0.b);
        }
    }
}
