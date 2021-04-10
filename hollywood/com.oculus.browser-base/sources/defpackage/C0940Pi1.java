package defpackage;

import android.view.View;
import org.chromium.chrome.browser.firstrun.ToSAndUMAFirstRunFragment;

/* renamed from: Pi1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C0940Pi1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final ToSAndUMAFirstRunFragment f8709a;

    public C0940Pi1(ToSAndUMAFirstRunFragment toSAndUMAFirstRunFragment) {
        this.f8709a = toSAndUMAFirstRunFragment;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        View view = (View) obj;
        this.f8709a.h1();
    }
}
