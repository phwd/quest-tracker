package X;

import android.content.Context;
import java.io.File;

/* renamed from: X.sV  reason: case insensitive filesystem */
public class C0502sV extends AbstractC0486rs {
    public final File A00;
    public final String A01 = "^lib/([^/]+)/([^/]+\\.so)$";

    public C0502sV(Context context, String str, File file) {
        super(context, str);
        this.A00 = file;
    }
}
