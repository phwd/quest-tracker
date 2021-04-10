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
public final class KP {
    public static final Pattern A03 = Pattern.compile("^[0-9]+L$");
    public final String A00;
    public final Context A01;
    public final KU A02;

    public static String A00(KP kp, String str, String str2) {
        Bundle bundle;
        Object obj;
        KU ku = kp.A02;
        String str3 = null;
        try {
            ApplicationInfo applicationInfo = ku.A00.getPackageManager().getApplicationInfo(str2, 128);
            if (!(applicationInfo == null || (bundle = applicationInfo.metaData) == null || (obj = bundle.get(str)) == null)) {
                str3 = obj.toString();
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(KU.class.getName(), "Error reading <meta-data> from AndroidManifest.xml.", e);
        } catch (RuntimeException e2) {
            if (e2.getCause() instanceof DeadObjectException) {
                Log.e(KU.class.getName(), "Error reading <meta-data> from AndroidManifest.xml.", e2);
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
    public KP(Context context, KU ku) {
        this.A01 = context;
        if (ku != null) {
            this.A02 = ku;
            this.A00 = context.getPackageName();
            return;
        }
        throw null;
    }
}
