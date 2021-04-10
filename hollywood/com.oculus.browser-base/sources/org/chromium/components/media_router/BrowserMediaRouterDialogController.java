package org.chromium.components.media_router;

import J.N;
import org.chromium.content_public.browser.WebContents;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class BrowserMediaRouterDialogController {

    /* renamed from: a  reason: collision with root package name */
    public final long f10850a;
    public AbstractC4949tg b;
    public WebContents c;

    public BrowserMediaRouterDialogController(long j, WebContents webContents) {
        this.f10850a = j;
        this.c = webContents;
    }

    public static BrowserMediaRouterDialogController create(long j, WebContents webContents) {
        return new BrowserMediaRouterDialogController(j, webContents);
    }

    public void a() {
        if (this.b != null) {
            this.b = null;
            N.MsJMWxq0(this.f10850a, this);
        }
    }

    public void closeDialog() {
        if (isShowingDialog()) {
            AbstractC4949tg tgVar = this.b;
            OE oe = tgVar.e;
            if (oe != null) {
                oe.f1(false, false);
                tgVar.e = null;
            }
            this.b = null;
        }
    }

    public boolean isShowingDialog() {
        AbstractC4949tg tgVar = this.b;
        if (tgVar != null) {
            OE oe = tgVar.e;
            if (oe != null && oe.a0()) {
                return true;
            }
        }
        return false;
    }

    public void openRouteChooserDialog(String[] strArr) {
        if (!isShowingDialog()) {
            C0629Kg0 kg0 = null;
            AbstractC1424Xh0 xh0 = null;
            for (String str : strArr) {
                C1897bn e = C1897bn.e(str);
                xh0 = e == null ? NL0.d(str) : e;
                if (xh0 != null) {
                    break;
                }
            }
            if (xh0 != null) {
                kg0 = xh0.c();
            }
            if (kg0 == null) {
                N.MY1J7b0i(this.f10850a, this);
                return;
            }
            C4095of0 of0 = new C4095of0(xh0.b(), kg0, this);
            this.b = of0;
            of0.a(this.c);
        }
    }

    public void openRouteControllerDialog(String str, String str2) {
        C0629Kg0 kg0;
        if (!isShowingDialog()) {
            AbstractC1424Xh0 e = C1897bn.e(str);
            if (e == null) {
                e = NL0.d(str);
            }
            if (e == null) {
                kg0 = null;
            } else {
                kg0 = e.c();
            }
            if (kg0 == null) {
                N.MY1J7b0i(this.f10850a, this);
                return;
            }
            C0747Mf0 mf0 = new C0747Mf0(e.b(), kg0, str2, this);
            this.b = mf0;
            mf0.a(this.c);
        }
    }
}
