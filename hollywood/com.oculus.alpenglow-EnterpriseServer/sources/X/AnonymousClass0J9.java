package X;

import android.content.Context;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.annotation.Nullable;

/* renamed from: X.0J9  reason: invalid class name */
public abstract class AnonymousClass0J9 {
    @Nullable
    public File A00;
    public final String A01;
    public final String A02;

    public void A01(boolean z) throws IOException {
    }

    public abstract boolean A02(Context context, byte[] bArr) throws IOException;

    public AnonymousClass0J9(String str, String str2) {
        this.A02 = str;
        this.A01 = str2;
    }

    public InputStream A00(Context context) throws IOException {
        return context.getAssets().open(this.A02, 2);
    }
}
