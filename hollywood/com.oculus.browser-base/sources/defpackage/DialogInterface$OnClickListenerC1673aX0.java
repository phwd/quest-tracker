package defpackage;

import android.content.DialogInterface;
import java.util.Objects;
import org.chromium.components.browser_ui.site_settings.SingleCategorySettings;
import org.chromium.components.embedder_support.browser_context.BrowserContextHandle;

/* renamed from: aX0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class DialogInterface$OnClickListenerC1673aX0 implements DialogInterface.OnClickListener {
    public final SingleCategorySettings F;
    public final C3469ky1 G;
    public final BrowserContextHandle H;
    public final int I;

    public DialogInterface$OnClickListenerC1673aX0(SingleCategorySettings singleCategorySettings, C3469ky1 ky1, BrowserContextHandle browserContextHandle, int i) {
        this.F = singleCategorySettings;
        this.G = ky1;
        this.H = browserContextHandle;
        this.I = i;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        SingleCategorySettings singleCategorySettings = this.F;
        C3469ky1 ky1 = this.G;
        BrowserContextHandle browserContextHandle = this.H;
        int i2 = this.I;
        Objects.requireNonNull(singleCategorySettings);
        ky1.l(browserContextHandle, i2, i == 0 ? 1 : 2);
        singleCategorySettings.n1();
        dialogInterface.dismiss();
    }
}
