package defpackage;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Objects;
import org.chromium.base.task.PostTask;

/* renamed from: QP  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class QP extends AbstractC2032cb {
    public final /* synthetic */ RP i;

    public QP(RP rp) {
        this.i = rp;
    }

    @Override // defpackage.AbstractC2032cb
    public Object c() {
        Throwable th;
        FileOutputStream fileOutputStream;
        FileNotFoundException e;
        IOException e2;
        RP rp = this.i;
        Objects.requireNonNull(rp);
        boolean z = true;
        FileOutputStream fileOutputStream2 = null;
        try {
            fileOutputStream = new FileOutputStream(rp.c);
            try {
                fileOutputStream.write(rp.d);
                O21.a(fileOutputStream);
            } catch (FileNotFoundException e3) {
                e = e3;
                AbstractC1220Ua0.a("FilePTDS", String.format(Locale.ENGLISH, "FileNotFoundException while attempting to save file %s Details: %s", rp.c, e.getMessage()), new Object[0]);
                O21.a(fileOutputStream);
                z = false;
                Objects.requireNonNull(rp.f);
                AbstractC3100ip1.f10165a.a("Tabs.PersistedTabData.Storage.Save.File", z);
                return null;
            } catch (IOException e4) {
                e2 = e4;
                AbstractC1220Ua0.a("FilePTDS", String.format(Locale.ENGLISH, "IOException while attempting to save for file %s.  Details: %s", rp.c, e2.getMessage()), new Object[0]);
                O21.a(fileOutputStream);
                z = false;
                Objects.requireNonNull(rp.f);
                AbstractC3100ip1.f10165a.a("Tabs.PersistedTabData.Storage.Save.File", z);
                return null;
            }
        } catch (FileNotFoundException e5) {
            e = e5;
            fileOutputStream = null;
            AbstractC1220Ua0.a("FilePTDS", String.format(Locale.ENGLISH, "FileNotFoundException while attempting to save file %s Details: %s", rp.c, e.getMessage()), new Object[0]);
            O21.a(fileOutputStream);
            z = false;
            Objects.requireNonNull(rp.f);
            AbstractC3100ip1.f10165a.a("Tabs.PersistedTabData.Storage.Save.File", z);
            return null;
        } catch (IOException e6) {
            e2 = e6;
            fileOutputStream = null;
            AbstractC1220Ua0.a("FilePTDS", String.format(Locale.ENGLISH, "IOException while attempting to save for file %s.  Details: %s", rp.c, e2.getMessage()), new Object[0]);
            O21.a(fileOutputStream);
            z = false;
            Objects.requireNonNull(rp.f);
            AbstractC3100ip1.f10165a.a("Tabs.PersistedTabData.Storage.Save.File", z);
            return null;
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream2 = fileOutputStream;
            O21.a(fileOutputStream2);
            throw th;
        }
        Objects.requireNonNull(rp.f);
        AbstractC3100ip1.f10165a.a("Tabs.PersistedTabData.Storage.Save.File", z);
        return null;
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        Void r2 = (Void) obj;
        PostTask.c(Zo1.f9374a, new PP(this));
        this.i.f.g();
    }
}
