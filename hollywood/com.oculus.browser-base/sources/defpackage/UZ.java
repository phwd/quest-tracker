package defpackage;

import org.chromium.content.browser.input.ImeAdapterImpl;
import org.chromium.content_public.browser.WebContents;

/* renamed from: UZ  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class UZ implements AbstractC5682xx1 {
    @Override // defpackage.AbstractC5682xx1
    public Object a(WebContents webContents) {
        return new ImeAdapterImpl(webContents);
    }
}
