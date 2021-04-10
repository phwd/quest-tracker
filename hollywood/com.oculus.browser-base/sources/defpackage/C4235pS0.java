package defpackage;

import J.N;
import android.content.Intent;
import org.chromium.content.browser.selection.SelectionPopupControllerImpl;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: pS0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4235pS0 implements Ky1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ SelectionPopupControllerImpl f11066a;

    public C4235pS0(SelectionPopupControllerImpl selectionPopupControllerImpl) {
        this.f11066a = selectionPopupControllerImpl;
    }

    @Override // defpackage.Ky1
    public void a(WindowAndroid windowAndroid, int i, Intent intent) {
        CharSequence charSequenceExtra;
        SelectionPopupControllerImpl selectionPopupControllerImpl = this.f11066a;
        if (selectionPopupControllerImpl.K != null && i == -1 && intent != null && selectionPopupControllerImpl.c0 && selectionPopupControllerImpl.U && (charSequenceExtra = intent.getCharSequenceExtra("android.intent.extra.PROCESS_TEXT")) != null) {
            WebContentsImpl webContentsImpl = selectionPopupControllerImpl.K;
            String charSequence = charSequenceExtra.toString();
            webContentsImpl.r0();
            N.MevqfbP8(webContentsImpl.H, webContentsImpl, charSequence);
        }
    }
}
