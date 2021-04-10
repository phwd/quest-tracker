package defpackage;

import android.content.Intent;
import org.chromium.components.page_info.PageInfoController;

/* renamed from: PB0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class PB0 implements Runnable {
    public final SB0 F;
    public final Intent G;
    public final String[] H;

    public PB0(SB0 sb0, Intent intent, String[] strArr) {
        this.F = sb0;
        this.G = intent;
        this.H = strArr;
    }

    public void run() {
        SB0 sb0 = this.F;
        Intent intent = this.G;
        String[] strArr = this.H;
        if (!(intent != null || strArr == null || sb0.e == null)) {
            for (String str : strArr) {
                if (sb0.e.canRequestPermission(str)) {
                    sb0.e.i(strArr, new QB0(sb0));
                    return;
                }
            }
        }
        PageInfoController pageInfoController = (PageInfoController) sb0.f;
        pageInfoController.S = new RunnableC0903Ou0(pageInfoController, intent);
        pageInfoController.M.b(true);
    }
}
