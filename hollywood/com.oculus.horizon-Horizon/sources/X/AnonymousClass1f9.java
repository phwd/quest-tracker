package X;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.facebook.appevents.AppEventsLogger;

/* renamed from: X.1f9  reason: invalid class name */
public final class AnonymousClass1f9 {
    public String A00;
    public final AppEventsLogger A01;

    public static Bundle A00(String str) {
        Bundle bundle = new Bundle();
        bundle.putLong("1_timestamp_ms", System.currentTimeMillis());
        bundle.putString("0_auth_logger_id", str);
        bundle.putString("3_method", "");
        bundle.putString("2_result", "");
        bundle.putString("5_error_message", "");
        bundle.putString("4_error_code", "");
        bundle.putString("6_extras", "");
        return bundle;
    }

    public AnonymousClass1f9(Context context, String str) {
        this.A00 = str;
        this.A01 = new AppEventsLogger(context, str);
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                packageManager.getPackageInfo("com.facebook.katana", 0);
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
    }
}
