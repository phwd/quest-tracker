package defpackage;

import android.os.Environment;
import android.os.StatFs;
import java.io.File;
import java.util.Objects;
import org.chromium.base.ContextUtils;

/* renamed from: Ea  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0244Ea extends AbstractC2032cb {
    public final Runnable i;
    public final /* synthetic */ E51 j;

    public C0244Ea(E51 e51, Runnable runnable) {
        this.j = e51;
        this.i = runnable;
    }

    @Override // defpackage.AbstractC2032cb
    public Object c() {
        E51 e51 = this.j;
        ContextUtils.getApplicationContext();
        Objects.requireNonNull(e51);
        File dataDirectory = Environment.getDataDirectory();
        if (!dataDirectory.exists()) {
            return null;
        }
        return new StatFs(dataDirectory.getPath());
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        this.i.run();
    }
}
