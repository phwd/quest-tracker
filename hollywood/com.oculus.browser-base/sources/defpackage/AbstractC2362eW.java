package defpackage;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.auth.TokenData;
import java.util.List;

/* renamed from: eW  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC2362eW extends EF1 {
    public static void f(Context context, String str) {
        SE0.h("Calling this from your main thread can lead to deadlock");
        EF1.a(context, 8400000);
        Bundle bundle = new Bundle();
        String str2 = context.getApplicationInfo().packageName;
        bundle.putString("clientPackageName", str2);
        if (!bundle.containsKey("androidPackageName")) {
            bundle.putString("androidPackageName", str2);
        }
        EF1.b(context, EF1.b, new C4890tG1(str, bundle));
    }

    public static List g(Context context, int i, String str) {
        SE0.g(str, "accountName must be provided");
        SE0.h("Calling this from your main thread can lead to deadlock");
        EF1.a(context, 8400000);
        return (List) EF1.b(context, EF1.b, new HG1(str, i));
    }

    public static String h(Context context, String str) {
        SE0.g(str, "accountName must be provided");
        SE0.h("Calling this from your main thread can lead to deadlock");
        EF1.a(context, 8400000);
        Bundle bundle = new Bundle();
        Account account = new Account(str, "com.google");
        EF1.e(account);
        return EF1.c(context, account, "^^_account_id_^^", bundle).G;
    }

    public static String i(Context context, Account account, String str, Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putBoolean("handle_notification", true);
        try {
            TokenData c = EF1.c(context, account, str, bundle2);
            AbstractC3729mW.a(context);
            return c.G;
        } catch (C3046iW e) {
            int i = e.F;
            int i2 = AbstractC3558lW.c;
            Object obj = SV.c;
            SV sv = SV.d;
            if (!AbstractC3729mW.c(context, i)) {
                if (!(i == 9 ? AbstractC3729mW.d(context, "com.android.vending") : false)) {
                    sv.g(context, i);
                    Log.w("GoogleAuthUtil", "Error when getting token", e);
                    throw new C1914bs1("User intervention required. Notification has been pushed.");
                }
            }
            new RV(sv, context).sendEmptyMessageDelayed(1, 120000);
            Log.w("GoogleAuthUtil", "Error when getting token", e);
            throw new C1914bs1("User intervention required. Notification has been pushed.");
        } catch (Xr1 e2) {
            AbstractC3729mW.a(context);
            Log.w("GoogleAuthUtil", "Error when getting token", e2);
            throw new C1914bs1("User intervention required. Notification has been pushed.");
        }
    }
}
