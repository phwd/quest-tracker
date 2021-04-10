package defpackage;

import org.chromium.base.Callback;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;

/* renamed from: yb1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5786yb1 implements H91 {

    /* renamed from: a  reason: collision with root package name */
    public final TabContentManager f11689a;

    public C5786yb1(TabContentManager tabContentManager) {
        this.f11689a = tabContentManager;
    }

    @Override // defpackage.H91
    public void a(int i, Callback callback, boolean z, boolean z2) {
        this.f11689a.f(i, callback, z, z2);
    }
}
