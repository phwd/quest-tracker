package defpackage;

import android.graphics.Bitmap;
import java.util.Objects;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: e61  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2298e61 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final TabContentManager f9832a;
    public final Callback b;
    public final int c;
    public final boolean d;

    public C2298e61(TabContentManager tabContentManager, Callback callback, int i, boolean z) {
        this.f9832a = tabContentManager;
        this.b = callback;
        this.c = i;
        this.d = z;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        Tab a2;
        TabContentManager tabContentManager = this.f9832a;
        Callback callback = this.b;
        int i = this.c;
        boolean z = this.d;
        Bitmap bitmap = (Bitmap) obj;
        Objects.requireNonNull(tabContentManager);
        if (bitmap != null) {
            callback.onResult(bitmap);
        }
        AbstractC2982i61 i61 = tabContentManager.j;
        if (i61 != null && (a2 = i61.a(i)) != null) {
            tabContentManager.c(a2, z, new C2469f61(callback));
        }
    }
}
