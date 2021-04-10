package X;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* renamed from: X.1fz  reason: invalid class name */
public class AnonymousClass1fz {
    public final File A00;
    public final OutputStream A01;

    public AnonymousClass1fz(File file) throws IOException {
        File createTempFile = File.createTempFile("NanoHTTPD-", "", file);
        this.A00 = createTempFile;
        this.A01 = new FileOutputStream(createTempFile);
    }
}
