package defpackage;

import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.LoadUrlParams;

/* renamed from: Zj1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Zj1 implements AbstractC1511Yt0 {

    /* renamed from: a  reason: collision with root package name */
    public final Q31 f9363a;

    public Zj1(Q31 q31) {
        this.f9363a = q31;
    }

    @Override // defpackage.AbstractC1511Yt0
    public boolean a(String str, int i, String str2, byte[] bArr, boolean z) {
        return EM0.h(new LoadUrlParams(str, i | 33554432), str2, bArr, Boolean.valueOf(z), (Tab) this.f9363a.get());
    }
}
