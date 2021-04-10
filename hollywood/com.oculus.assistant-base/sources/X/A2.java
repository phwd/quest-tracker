package X;

import android.os.Build;
import java.security.Provider;
import java.security.Security;
import java.util.concurrent.TimeUnit;

public final class A2 {
    public static C0548bl A00;

    public static C0548bl A00() {
        C0548bl blVar = A00;
        if (blVar != null) {
            return blVar;
        }
        C0547bk bkVar = new C0547bk();
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        bkVar.A0B = C0547bk.A00(0, timeUnit);
        bkVar.A0C = C0547bk.A00(0, timeUnit);
        bkVar.A0D = C0547bk.A00(0, timeUnit);
        bkVar.A0E = C0175Gg.A00(Build.TIME);
        try {
            Security.insertProviderAt((Provider) Class.forName("org.conscrypt.OpenSSLProvider").newInstance(), 1);
        } catch (Exception unused) {
        }
        C0548bl blVar2 = new C0548bl(bkVar);
        A00 = blVar2;
        return blVar2;
    }
}
