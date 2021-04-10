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

@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0JI  reason: invalid class name */
public final class AnonymousClass0JI {
    public static final Pattern A03 = Pattern.compile("^[0-9]+L$");
    public final String A00;
    public final Context A01;
    public final AnonymousClass0JN A02;

    public static String A00(AnonymousClass0JI r3, String str, String str2) {
        Bundle bundle;
        Object obj;
        AnonymousClass0JN r0 = r3.A02;
        String str3 = null;
        try {
            ApplicationInfo applicationInfo = r0.A00.getPackageManager().getApplicationInfo(str2, 128);
            if (!(applicationInfo == null || (bundle = applicationInfo.metaData) == null || (obj = bundle.get(str)) == null)) {
                str3 = obj.toString();
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(AnonymousClass0JN.class.getName(), "Error reading <meta-data> from AndroidManifest.xml.", e);
        } catch (RuntimeException e2) {
            if (e2.getCause() instanceof DeadObjectException) {
                Log.e(AnonymousClass0JN.class.getName(), "Error reading <meta-data> from AndroidManifest.xml.", e2);
            } else {
                throw e2;
            }
        }
        if (str3 == null) {
            return "";
        }
        return str3;
    }

    public AnonymousClass0JI(Context context, AnonymousClass0JN r3) {
        this.A01 = context;
        if (r3 != null) {
            this.A02 = r3;
            this.A00 = context.getPackageName();
            return;
        }
        throw null;
    }
}
