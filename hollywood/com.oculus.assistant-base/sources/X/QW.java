package X;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.Signature;
import android.util.Log;
import com.google.android.gms.common.zzd;
import com.google.android.gms.common.zzg;

public final class QW {
    public static QW A01;
    public final Context A00;

    public static zzd A00(PackageInfo packageInfo, zzd... zzdArr) {
        Signature[] signatureArr = packageInfo.signatures;
        if (signatureArr != null) {
            if (signatureArr.length != 1) {
                Log.w("GoogleSignatureVerifier", "Package has more than one signature.");
            } else {
                zzg zzg = new zzg(signatureArr[0].toByteArray());
                for (int i = 0; i < zzdArr.length; i++) {
                    if (zzdArr[i].equals(zzg)) {
                        return zzdArr[i];
                    }
                }
            }
        }
        return null;
    }

    public QW(Context context) {
        this.A00 = context.getApplicationContext();
    }
}
