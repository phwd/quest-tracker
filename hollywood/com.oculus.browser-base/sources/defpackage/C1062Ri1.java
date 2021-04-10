package defpackage;

import android.view.View;
import org.chromium.chrome.browser.firstrun.ToSAndUMAFirstRunFragment;

/* renamed from: Ri1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C1062Ri1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final ToSAndUMAFirstRunFragment f8848a;

    public C1062Ri1(ToSAndUMAFirstRunFragment toSAndUMAFirstRunFragment) {
        this.f8848a = toSAndUMAFirstRunFragment;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        View view = (View) obj;
        this.f8848a.j1();
    }
}
