package defpackage;

import org.chromium.chrome.browser.tab.TabImpl;

/* renamed from: q81  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C4355q81 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final TabImpl f11119a;

    public C4355q81(TabImpl tabImpl) {
        this.f11119a = tabImpl;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        this.f11119a.m0(((Integer) obj).intValue());
    }
}
