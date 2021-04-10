package X;

import android.content.Context;
import java.io.File;

public final class BZ extends AnonymousClass2N {
    public final int A00;

    public BZ(Context context, File file, String str, int i) {
        super(context, str, file, "^lib/([^/]+)/([^/]+\\.so)$");
        this.A00 = i;
    }
}
