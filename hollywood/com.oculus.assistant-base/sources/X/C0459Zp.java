package X;

import android.content.Context;
import java.util.HashMap;

/* renamed from: X.Zp  reason: case insensitive filesystem */
public final class C0459Zp {
    public final Context A00;
    public final C0457Zn A01;
    public final C0458Zo A02;
    public final HashMap A03 = new HashMap();
    public final HashMap A04 = new HashMap();
    public final HashMap A05 = new HashMap();

    public C0459Zp(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.A00 = applicationContext;
        this.A01 = new C0457Zn(applicationContext.getPackageManager());
        this.A02 = new C0458Zo();
    }
}
