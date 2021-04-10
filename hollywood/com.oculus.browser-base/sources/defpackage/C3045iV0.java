package defpackage;

import android.graphics.Bitmap;
import org.chromium.chrome.browser.ShortcutHelper;

/* renamed from: iV0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3045iV0 extends AbstractC2032cb {
    public final /* synthetic */ Bitmap i;
    public final /* synthetic */ Xx1 j;

    public C3045iV0(Bitmap bitmap, Xx1 xx1) {
        this.i = bitmap;
        this.j = xx1;
    }

    @Override // defpackage.AbstractC2032cb
    public Object c() {
        return ShortcutHelper.b(this.i);
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        this.j.c.edit().putString("splash_icon", (String) obj).apply();
    }
}
