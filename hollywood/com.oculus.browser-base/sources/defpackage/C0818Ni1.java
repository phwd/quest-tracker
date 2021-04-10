package defpackage;

import org.chromium.chrome.browser.firstrun.ToSAndUMAFirstRunFragment;

/* renamed from: Ni1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C0818Ni1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final ToSAndUMAFirstRunFragment f8569a;

    public C0818Ni1(ToSAndUMAFirstRunFragment toSAndUMAFirstRunFragment) {
        this.f8569a = toSAndUMAFirstRunFragment;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        Boolean bool = (Boolean) obj;
        this.f8569a.f1();
    }
}
