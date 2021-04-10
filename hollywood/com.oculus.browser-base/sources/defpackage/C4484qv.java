package defpackage;

import android.content.ClipData;
import android.net.Uri;
import org.chromium.base.ContextUtils;
import org.chromium.ui.base.Clipboard;

/* renamed from: qv  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4484qv extends AbstractC2032cb {
    public final /* synthetic */ Uri i;
    public final /* synthetic */ Clipboard j;

    public C4484qv(Clipboard clipboard, Uri uri) {
        this.j = clipboard;
        this.i = uri;
    }

    @Override // defpackage.AbstractC2032cb
    public Object c() {
        return ClipData.newUri(ContextUtils.getApplicationContext().getContentResolver(), "image", this.i);
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        this.j.e((ClipData) obj);
    }
}
