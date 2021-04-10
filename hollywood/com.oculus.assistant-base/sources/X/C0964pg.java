package X;

import android.content.Context;
import android.util.Log;
import java.io.File;

/* renamed from: X.pg  reason: case insensitive filesystem */
public final class C0964pg extends KK {
    public int A00;
    public Context A01;
    public C0965ph A02;

    public C0964pg(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.A01 = applicationContext;
        if (applicationContext == null) {
            Log.w("SoLoader", "context.getApplicationContext returned null, holding reference to original context.");
            this.A01 = context;
            applicationContext = context;
        }
        this.A00 = 0;
        this.A02 = new C0965ph(new File(applicationContext.getApplicationInfo().nativeLibraryDir), 0);
    }

    @Override // X.KK
    public final String toString() {
        return this.A02.toString();
    }
}
