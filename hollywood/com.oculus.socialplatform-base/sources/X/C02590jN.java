package X;

import android.content.Context;
import android.os.StrictMode;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import javax.annotation.Nullable;

/* renamed from: X.0jN  reason: invalid class name and case insensitive filesystem */
public final class C02590jN extends AnonymousClass0l1 {
    public int A00;
    public Context A01;
    public AnonymousClass0jM A02;

    public C02590jN(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.A01 = applicationContext;
        if (applicationContext == null) {
            Log.w("SoLoader", "context.getApplicationContext returned null, holding reference to original context.");
            this.A01 = context;
            applicationContext = context;
        }
        this.A00 = 0;
        this.A02 = new AnonymousClass0jM(new File(applicationContext.getApplicationInfo().nativeLibraryDir), 0);
    }

    @Override // X.AnonymousClass0l1
    public final void A04(int i) throws IOException {
        this.A02.A04(i);
    }

    @Override // X.AnonymousClass0l1
    public final int A05(String str, int i, StrictMode.ThreadPolicy threadPolicy) throws IOException {
        return this.A02.A05(str, i, threadPolicy);
    }

    @Override // X.AnonymousClass0l1
    @Nullable
    public final String A06(String str) throws IOException {
        return this.A02.A06(str);
    }

    @Override // X.AnonymousClass0l1
    public final String toString() {
        return this.A02.toString();
    }
}
