package defpackage;

import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;
import java.util.Objects;
import org.chromium.base.Callback;

/* renamed from: OP  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class OP extends SP {
    public Callback d;
    public final /* synthetic */ TP e;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public OP(TP tp, int i, String str, Callback callback) {
        super(tp, i, str);
        this.e = tp;
        this.d = callback;
    }

    @Override // defpackage.SP
    public AbstractC2032cb a() {
        return new NP(this);
    }

    public byte[] b() {
        byte[] bArr;
        boolean z = true;
        try {
            File file = this.c;
            File file2 = new File(file.getPath() + ".new");
            File file3 = new File(file.getPath() + ".bak");
            if (file3.exists()) {
                C2203db.c(file3, file);
            }
            if (file2.exists() && file.exists() && !file2.delete()) {
                Log.e("AtomicFile", "Failed to delete outdated new file " + file2);
            }
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                bArr = new byte[fileInputStream.available()];
                int i = 0;
                while (true) {
                    int read = fileInputStream.read(bArr, i, bArr.length - i);
                    if (read <= 0) {
                        break;
                    }
                    i += read;
                    int available = fileInputStream.available();
                    if (available > bArr.length - i) {
                        byte[] bArr2 = new byte[(available + i)];
                        System.arraycopy(bArr, 0, bArr2, 0, i);
                        bArr = bArr2;
                    }
                }
                Objects.requireNonNull(this.e);
                AbstractC3100ip1.f10165a.a("Tabs.PersistedTabData.Storage.Restore.File", z);
                return bArr;
            } finally {
                fileInputStream.close();
            }
        } catch (FileNotFoundException e2) {
            AbstractC1220Ua0.a("FilePTDS", String.format(Locale.ENGLISH, "FileNotFoundException while attempting to restore  %s. Details: %s", this.c, e2.getMessage()), new Object[0]);
            bArr = null;
            z = false;
            Objects.requireNonNull(this.e);
            AbstractC3100ip1.f10165a.a("Tabs.PersistedTabData.Storage.Restore.File", z);
            return bArr;
        } catch (IOException e3) {
            AbstractC1220Ua0.a("FilePTDS", String.format(Locale.ENGLISH, "IOException while attempting to restore %s. Details: %s", this.c, e3.getMessage()), new Object[0]);
            bArr = null;
            z = false;
            Objects.requireNonNull(this.e);
            AbstractC3100ip1.f10165a.a("Tabs.PersistedTabData.Storage.Restore.File", z);
            return bArr;
        }
    }

    @Override // defpackage.SP
    public boolean equals(Object obj) {
        if (!(obj instanceof OP)) {
            return false;
        }
        return super.equals(obj);
    }
}
