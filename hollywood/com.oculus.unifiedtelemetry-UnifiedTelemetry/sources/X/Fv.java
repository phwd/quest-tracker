package X;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

public class Fv {
    public final Context A00;
    public final String A01;

    public final void A00(YE ye) {
        String str;
        YE.A00(ye, "tier", this.A01);
        YE.A00(ye, "sent_time", Double.valueOf(((double) System.currentTimeMillis()) / 1000.0d));
        Context context = this.A00;
        String networkOperatorName = ((TelephonyManager) context.getSystemService("phone")).getNetworkOperatorName();
        if (TextUtils.isEmpty(networkOperatorName)) {
            networkOperatorName = "n/a";
        }
        YE.A00(ye, "carrier", networkOperatorName);
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null) {
            str = activeNetworkInfo.getTypeName();
        } else {
            str = "none";
        }
        YE.A00(ye, "conn", str);
    }

    public Fv(Context context, String str) {
        this.A00 = context.getApplicationContext();
        this.A01 = str;
    }
}
