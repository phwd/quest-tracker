package defpackage;

import org.chromium.content.browser.selection.SelectionPopupControllerImpl;
import org.chromium.content_public.browser.WebContents;

/* renamed from: rS0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C4576rS0 implements AbstractC5682xx1 {
    @Override // defpackage.AbstractC5682xx1
    public Object a(WebContents webContents) {
        SelectionPopupControllerImpl selectionPopupControllerImpl = new SelectionPopupControllerImpl(webContents, null, true);
        selectionPopupControllerImpl.L = B2.F;
        return selectionPopupControllerImpl;
    }
}
