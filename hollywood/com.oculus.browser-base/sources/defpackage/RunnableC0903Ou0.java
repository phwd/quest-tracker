package defpackage;

import android.content.Intent;
import android.net.Uri;
import java.util.Objects;
import org.chromium.components.page_info.PageInfoController;

/* renamed from: Ou0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC0903Ou0 implements Runnable {
    public final PageInfoController F;
    public final Intent G;

    public RunnableC0903Ou0(PageInfoController pageInfoController, Intent intent) {
        this.F = pageInfoController;
        this.G = intent;
    }

    public void run() {
        PageInfoController pageInfoController = this.F;
        Intent intent = this.G;
        Objects.requireNonNull(pageInfoController);
        if (intent == null) {
            intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
            StringBuilder i = AbstractC2531fV.i("package:");
            i.append(pageInfoController.F.getPackageName());
            intent.setData(Uri.parse(i.toString()));
        }
        intent.setFlags(268435456);
        pageInfoController.F.startActivity(intent);
    }
}
