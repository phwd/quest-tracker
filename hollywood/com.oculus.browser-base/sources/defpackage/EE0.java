package defpackage;

import java.util.ArrayList;
import java.util.List;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.content_public.browser.WebContents;

/* renamed from: EE0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class EE0 extends Pr1 {
    public final List F = new ArrayList();

    public EE0(WebContents webContents) {
    }

    public static EE0 c(WebContents webContents) {
        return (EE0) ((WebContentsImpl) webContents).v0(EE0.class, DE0.f7874a);
    }

    public void e() {
        for (BE0 be0 : this.F) {
            be0.b();
        }
    }
}
