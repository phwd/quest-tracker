package defpackage;

import android.os.Build;
import org.chromium.content.browser.selection.SelectionPopupControllerImpl;
import org.chromium.content_public.browser.WebContents;

/* renamed from: hS0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2868hS0 {

    /* renamed from: a  reason: collision with root package name */
    public final boolean f10071a;
    public AbstractC2185dS0 b;

    public C2868hS0(WebContents webContents) {
        if (Build.VERSION.SDK_INT > 26) {
            this.b = AbstractC2185dS0.b(webContents);
            SelectionPopupControllerImpl.r(webContents).F(this.b);
        }
        this.f10071a = this.b != null;
    }
}
