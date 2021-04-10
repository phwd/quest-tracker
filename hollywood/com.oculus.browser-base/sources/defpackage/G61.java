package defpackage;

import org.chromium.base.Callback;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;

/* renamed from: G61  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class G61 implements H91 {

    /* renamed from: a  reason: collision with root package name */
    public final TabContentManager f8064a;

    public G61(TabContentManager tabContentManager) {
        this.f8064a = tabContentManager;
    }

    @Override // defpackage.H91
    public void a(int i, Callback callback, boolean z, boolean z2) {
        this.f8064a.f(i, callback, z, z2);
    }
}
