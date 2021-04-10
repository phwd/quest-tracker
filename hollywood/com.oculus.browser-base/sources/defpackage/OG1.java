package defpackage;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.cast.framework.CastOptions;
import java.util.Map;

/* renamed from: OG1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class OG1 extends AbstractC5394wE1 {
    public OG1(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.cast.framework.internal.ICastDynamiteModule");
    }

    public final AbstractC2502fH1 y0(VY vy, CastOptions castOptions, AbstractC4382qH1 qh1, Map map) {
        AbstractC2502fH1 fh1;
        Parcel c = c();
        AbstractC4376qF1.b(c, vy);
        AbstractC4376qF1.c(c, castOptions);
        AbstractC4376qF1.b(c, qh1);
        c.writeMap(map);
        Parcel d = d(1, c);
        IBinder readStrongBinder = d.readStrongBinder();
        int i = AbstractBinderC3869nH1.f10481a;
        if (readStrongBinder == null) {
            fh1 = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.cast.framework.ICastContext");
            if (queryLocalInterface instanceof AbstractC2502fH1) {
                fh1 = (AbstractC2502fH1) queryLocalInterface;
            } else {
                fh1 = new MH1(readStrongBinder);
            }
        }
        d.recycle();
        return fh1;
    }
}
