package X;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/* renamed from: X.gV  reason: case insensitive filesystem */
public final class C0745gV implements AbstractC00588i {
    public final /* synthetic */ C00598j A00;

    public C0745gV(C00598j r1) {
        this.A00 = r1;
    }

    @Override // X.AbstractC00588i
    public final boolean A4u() {
        C00598j r4 = this.A00;
        synchronized (r4.A06) {
            File file = new File(r4.A05, r4.A07);
            C0139Dd.A09("LastAudioLogger", AnonymousClass08.A04("Recording mic input to: ", file.getAbsolutePath()));
            try {
                if (file.getParentFile() != null && !file.getParentFile().isDirectory() && !file.getParentFile().mkdirs()) {
                    C0139Dd.A0A("LastAudioLogger", "Can't write to file.");
                }
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                r4.A01 = fileOutputStream;
                r4.A03 = new DataOutputStream(new BufferedOutputStream(fileOutputStream));
            } catch (IOException e) {
                C0139Dd.A0L("LastAudioLogger", "Failed opening file.", e);
                C00598j.A00(r4);
            }
        }
        return false;
    }
}
