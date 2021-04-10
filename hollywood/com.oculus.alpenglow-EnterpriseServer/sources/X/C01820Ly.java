package X;

import android.content.Context;
import com.facebook.xzdecoder.XzInputStream;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: X.0Ly  reason: invalid class name and case insensitive filesystem */
public class C01820Ly extends C03270bw {
    @Override // X.AnonymousClass0J9
    public final InputStream A00(Context context) throws IOException {
        return new XzInputStream(super.A00(context));
    }

    public C01820Ly(String str, String str2) {
        super(str, str2);
    }
}
