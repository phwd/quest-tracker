package defpackage;

import java.util.Objects;
import org.chromium.base.Callback;
import org.chromium.content.browser.selection.SelectionPopupControllerImpl;
import org.chromium.content_public.browser.WebContents;

/* renamed from: Gp  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C0405Gp extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C0649Kp f8112a;
    public final AbstractC3467ky b;
    public final Callback c;
    public final Q31 d;

    public C0405Gp(C0649Kp kp, AbstractC3467ky kyVar, Callback callback, Q31 q31) {
        this.f8112a = kp;
        this.b = kyVar;
        this.c = callback;
        this.d = q31;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C0649Kp kp = this.f8112a;
        AbstractC3467ky kyVar = this.b;
        Callback callback = this.c;
        Q31 q31 = this.d;
        WebContents webContents = (WebContents) obj;
        Objects.requireNonNull(kp);
        SelectionPopupControllerImpl.r(webContents).L = new ActionMode$CallbackC0588Jp(kp.b, webContents, kyVar, callback, q31);
    }
}
