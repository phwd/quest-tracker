package X;

import fi.iki.elonen.NanoHTTPD;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/* renamed from: X.1ef  reason: invalid class name */
public class AnonymousClass1ef {
    public final File A00;
    public final List<NanoHTTPD.TempFile> A01;

    public final void A00() {
        List<NanoHTTPD.TempFile> list = this.A01;
        for (AnonymousClass1fz r1 : list) {
            try {
                AnonymousClass1ea.A02(r1.A01);
                File file = r1.A00;
                if (!file.delete()) {
                    throw new Exception(AnonymousClass006.A05("could not delete temporary file: ", file.getAbsolutePath()));
                }
            } catch (Exception e) {
                AnonymousClass1ea.LOG.log(Level.WARNING, "could not delete file ", (Throwable) e);
            }
        }
        list.clear();
    }

    public AnonymousClass1ef() {
        File file = new File(System.getProperty("java.io.tmpdir"));
        this.A00 = file;
        if (!file.exists()) {
            this.A00.mkdirs();
        }
        this.A01 = new ArrayList();
    }
}
