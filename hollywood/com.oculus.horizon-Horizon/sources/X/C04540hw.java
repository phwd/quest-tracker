package X;

import android.content.Context;
import android.os.StrictMode;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import javax.annotation.Nullable;

/* renamed from: X.0hw  reason: invalid class name and case insensitive filesystem */
public final class C04540hw extends AbstractC03170cL {
    public int A00;
    public Context A01;
    public C04530hv A02;

    public C04540hw(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.A01 = applicationContext;
        if (applicationContext == null) {
            Log.w("SoLoader", "context.getApplicationContext returned null, holding reference to original context.");
            this.A01 = context;
            applicationContext = context;
        }
        this.A00 = 0;
        this.A02 = new C04530hv(new File(applicationContext.getApplicationInfo().nativeLibraryDir), 0);
    }

    @Override // X.AbstractC03170cL
    public final void A04(int i) throws IOException {
        this.A02.A04(i);
    }

    @Override // X.AbstractC03170cL
    public final int A05(String str, int i, StrictMode.ThreadPolicy threadPolicy) throws IOException {
        return this.A02.A05(str, i, threadPolicy);
    }

    @Override // X.AbstractC03170cL
    @Nullable
    public final String A06(String str) throws IOException {
        return this.A02.A06(str);
    }

    @Override // X.AbstractC03170cL
    public final String toString() {
        return this.A02.toString();
    }
}
