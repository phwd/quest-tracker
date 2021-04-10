package defpackage;

import android.content.DialogInterface;
import org.chromium.components.browser_ui.site_settings.SingleCategorySettings;
import org.chromium.components.embedder_support.browser_context.BrowserContextHandle;

/* renamed from: ZW0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class ZW0 implements DialogInterface.OnClickListener {
    public final SingleCategorySettings F;
    public final C3469ky1 G;
    public final BrowserContextHandle H;
    public final int I;

    public ZW0(SingleCategorySettings singleCategorySettings, C3469ky1 ky1, BrowserContextHandle browserContextHandle, int i) {
        this.F = singleCategorySettings;
        this.G = ky1;
        this.H = browserContextHandle;
        this.I = i;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.F.p1(this.G, this.H, this.I, dialogInterface);
    }
}
