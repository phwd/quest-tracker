package defpackage;

import java.util.Locale;
import java.util.Objects;
import org.chromium.base.task.PostTask;

/* renamed from: KP  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class KP extends AbstractC2032cb {
    public final /* synthetic */ LP i;

    public KP(LP lp) {
        this.i = lp;
    }

    @Override // defpackage.AbstractC2032cb
    public Object c() {
        LP lp = this.i;
        boolean exists = lp.c.exists();
        Objects.requireNonNull(lp.e);
        AbstractC3100ip1.f10165a.a("Tabs.PersistedTabData.Storage.Exists.File", exists);
        if (!exists) {
            return null;
        }
        boolean delete = lp.c.delete();
        Objects.requireNonNull(lp.e);
        AbstractC3100ip1.f10165a.a("Tabs.PersistedTabData.Storage.Delete.File", delete);
        if (delete) {
            return null;
        }
        AbstractC1220Ua0.a("FilePTDS", String.format(Locale.ENGLISH, "Error deleting file %s", lp.c), new Object[0]);
        return null;
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        Void r2 = (Void) obj;
        PostTask.c(Zo1.f9374a, new JP(this));
        this.i.e.g();
    }
}
