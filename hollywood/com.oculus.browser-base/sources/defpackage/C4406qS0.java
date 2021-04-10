package defpackage;

import J.N;
import org.chromium.content.browser.selection.SelectionPopupControllerImpl;
import org.chromium.content.browser.webcontents.WebContentsImpl;

/* renamed from: qS0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4406qS0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SelectionPopupControllerImpl f11142a;

    public C4406qS0(SelectionPopupControllerImpl selectionPopupControllerImpl, RunnableC3893nS0 ns0) {
        this.f11142a = selectionPopupControllerImpl;
    }

    public void a(C2355eS0 es0) {
        int i;
        SelectionPopupControllerImpl selectionPopupControllerImpl = this.f11142a;
        if (!selectionPopupControllerImpl.c0) {
            selectionPopupControllerImpl.i0 = null;
            return;
        }
        int i2 = es0.f9855a;
        if (i2 > 0 || (i = es0.b) < 0) {
            selectionPopupControllerImpl.i0 = null;
            selectionPopupControllerImpl.G();
            return;
        }
        selectionPopupControllerImpl.i0 = es0;
        if (i2 == 0 && i == 0) {
            ZX0 zx0 = selectionPopupControllerImpl.g0;
            if (zx0 != null) {
                zx0.g(selectionPopupControllerImpl.Z, selectionPopupControllerImpl.a0, es0);
            }
            this.f11142a.G();
            return;
        }
        WebContentsImpl webContentsImpl = selectionPopupControllerImpl.K;
        N.MjgOFo_o(webContentsImpl.H, webContentsImpl, i2, i, true);
    }
}
