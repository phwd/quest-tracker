package defpackage;

import android.net.Uri;
import java.io.File;
import java.io.IOException;
import org.chromium.base.ContentUriUtils;
import org.chromium.ui.base.Clipboard;

/* renamed from: MT0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class MT0 implements Runnable {
    public void run() {
        try {
            Uri c = Clipboard.getInstance().c();
            File c2 = AbstractC1667aU0.c();
            boolean z = false;
            if (c != null) {
                Uri b = ContentUriUtils.b(c2);
                if (b != null) {
                    z = c.toString().startsWith(b.toString());
                }
            }
            AbstractC3375kQ.e(AbstractC1667aU0.c(), new RT0(z ? c.getPath() : null));
        } catch (IOException unused) {
        }
    }
}
