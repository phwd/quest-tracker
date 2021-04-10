package defpackage;

import android.net.Uri;
import java.io.File;
import org.chromium.base.ContentUriUtils;

/* renamed from: VT0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class VT0 extends AbstractC2032cb {
    public final /* synthetic */ String i;
    public final /* synthetic */ WT0 j;

    public VT0(WT0 wt0, String str) {
        this.j = wt0;
        this.i = str;
    }

    @Override // defpackage.AbstractC2032cb
    public Object c() {
        return ContentUriUtils.b(new File(this.i));
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        this.j.f9148a.onResult((Uri) obj);
    }
}
