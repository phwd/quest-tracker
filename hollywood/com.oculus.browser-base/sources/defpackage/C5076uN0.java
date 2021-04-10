package defpackage;

import com.oculus.browser.R;

/* renamed from: uN0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5076uN0 implements Q31 {
    public final C0638Kj0 F;

    public C5076uN0(C0638Kj0 kj0) {
        this.F = kj0;
    }

    @Override // defpackage.Q31
    public Object get() {
        C0638Kj0 kj0 = this.F;
        int height = kj0.F.findViewById(R.id.message_banner).getHeight();
        return Integer.valueOf(kj0.a() + kj0.F.getResources().getDimensionPixelOffset(R.dimen.f20830_resource_name_obfuscated_RES_2131165702) + height);
    }
}
