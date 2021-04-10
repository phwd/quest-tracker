package X;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.util.Log;
import com.facebook.infer.annotation.Nullsafe;
import java.util.regex.Pattern;
import javax.annotation.concurrent.ThreadSafe;
import javax.inject.Inject;

@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0Kg  reason: invalid class name and case insensitive filesystem */
public final class C01640Kg {
    public static final Pattern A03 = Pattern.compile("^[0-9]+L$");
    public final String A00;
    public final Context A01;
    public final C01650Kl A02;

    public static String A00(C01640Kg r3, String str, String str2) {
        Bundle bundle;
        Object obj;
        C01650Kl r0 = r3.A02;
        String str3 = null;
        try {
            ApplicationInfo applicationInfo = r0.A00.getPackageManager().getApplicationInfo(str2, 128);
            if (!(applicationInfo == null || (bundle = applicationInfo.metaData) == null || (obj = bundle.get(str)) == null)) {
                str3 = obj.toString();
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(C01650Kl.class.getName(), "Error reading <meta-data> from AndroidManifest.xml.", e);
        } catch (RuntimeException e2) {
            if (e2.getCause() instanceof DeadObjectException) {
                Log.e(C01650Kl.class.getName(), "Error reading <meta-data> from AndroidManifest.xml.", e2);
            } else {
                throw e2;
            }
        }
        if (str3 == null) {
            return "";
        }
        return str3;
    }

    @Inject
    public C01640Kg(Context context, C01650Kl r3) {
        this.A01 = context;
        if (r3 != null) {
            this.A02 = r3;
            this.A00 = context.getPackageName();
            return;
        }
        throw null;
    }
}
