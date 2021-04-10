package defpackage;

import java.util.Objects;

/* renamed from: EO0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class EO0 implements AbstractC3841n80 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ HO0 f7960a;

    public EO0(HO0 ho0) {
        this.f7960a = ho0;
    }

    @Override // defpackage.AbstractC3841n80
    public void a(AbstractC4524r80 r80, EnumC3157j80 j80) {
        if (j80 == EnumC3157j80.ON_START) {
            Objects.requireNonNull(this.f7960a);
        } else if (j80 == EnumC3157j80.ON_STOP) {
            Objects.requireNonNull(this.f7960a);
        }
    }
}
