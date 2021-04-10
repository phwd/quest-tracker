package X;

import android.content.Context;
import android.util.Log;
import java.io.File;

/* renamed from: X.sB  reason: case insensitive filesystem */
public final class C0492sB extends AbstractC0507sb {
    public int A00;
    public Context A01;
    public C0485rr A02;

    public C0492sB(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.A01 = applicationContext;
        if (applicationContext == null) {
            Log.w("SoLoader", "context.getApplicationContext returned null, holding reference to original context.");
            this.A01 = context;
            applicationContext = context;
        }
        this.A00 = 0;
        this.A02 = new C0485rr(new File(applicationContext.getApplicationInfo().nativeLibraryDir), 0);
    }

    @Override // X.AbstractC0507sb
    public final String toString() {
        return this.A02.toString();
    }
}
