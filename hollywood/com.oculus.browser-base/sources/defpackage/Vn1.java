package defpackage;

import android.os.Bundle;
import java.util.Objects;

/* renamed from: Vn1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Vn1 extends AbstractC2073co1 {

    /* renamed from: a  reason: collision with root package name */
    public final String f9105a;
    public final int b;

    public Vn1(String str, int i) {
        this.f9105a = str;
        this.b = i;
    }

    @Override // defpackage.AbstractC2073co1
    public void a(C4649rt0 rt0, C3268jo1 jo1) {
        String str = this.f9105a;
        int i = this.b;
        Objects.requireNonNull(jo1);
        Bundle bundle = new Bundle();
        bundle.putString("android.support.customtabs.trusted.PLATFORM_TAG", str);
        bundle.putInt("android.support.customtabs.trusted.PLATFORM_ID", i);
        ((C4077oZ) jo1.f10238a).d(bundle);
    }
}
