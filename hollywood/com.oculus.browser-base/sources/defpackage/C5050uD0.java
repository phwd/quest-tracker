package defpackage;

/* renamed from: uD0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5050uD0 implements AbstractC1082Rs0 {

    /* renamed from: a  reason: collision with root package name */
    public final C5390wD0 f11398a;

    public C5050uD0(C5390wD0 wd0) {
        this.f11398a = wd0;
    }

    @Override // defpackage.AbstractC1082Rs0
    public void a(Object obj) {
        C5390wD0 wd0 = this.f11398a;
        C2823hA1 ha1 = (C2823hA1) obj;
        wd0.e = ha1;
        wd0.f = Integer.valueOf(ha1.c);
        wd0.g = Integer.valueOf(ha1.d);
        StringBuilder i = AbstractC2531fV.i("pullCurrentState(");
        i.append(wd0.f);
        i.append(", ");
        i.append(wd0.g);
        i.append(") success.");
        int i2 = 0;
        AbstractC1220Ua0.d("PlayInline", i.toString(), new Object[0]);
        int i3 = ha1.c;
        AbstractC3364kK0.g("GoogleUpdate.Inline.AppUpdateInfo.UpdateAvailability", i3 != 0 ? i3 != 1 ? i3 != 2 ? i3 != 3 ? 0 : 4 : 3 : 2 : 1, 5);
        int i4 = ha1.d;
        if (i4 == 10) {
            i2 = 2;
        } else if (i4 != 11) {
            switch (i4) {
                case 0:
                    i2 = 1;
                    break;
                case 1:
                    i2 = 3;
                    break;
                case 2:
                    i2 = 4;
                    break;
                case 3:
                    i2 = 6;
                    break;
                case 4:
                    i2 = 7;
                    break;
                case 5:
                    i2 = 8;
                    break;
                case 6:
                    i2 = 9;
                    break;
            }
        } else {
            i2 = 5;
        }
        AbstractC3364kK0.g("GoogleUpdate.Inline.AppUpdateInfo.InstallStatus", i2, 10);
        wd0.e();
    }
}
