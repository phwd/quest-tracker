package defpackage;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import java.util.ArrayList;

/* renamed from: hu0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2944hu0 implements AbstractC3114iu0 {
    @Override // defpackage.AbstractC3114iu0
    public boolean a(String str, PackageManager packageManager, C2229dj1 dj1) {
        dj1.b();
        String str2 = dj1.b;
        if (str2 == null) {
            throw new IllegalStateException();
        } else if (!str.equals(str2)) {
            return false;
        } else {
            PackageInfo packageInfo = packageManager.getPackageInfo(str, 64);
            ArrayList arrayList = new ArrayList(packageInfo.signatures.length);
            Signature[] signatureArr = packageInfo.signatures;
            int length = signatureArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                byte[] a2 = AbstractC3285ju0.a(signatureArr[i]);
                if (a2 == null) {
                    arrayList = null;
                    break;
                }
                arrayList.add(a2);
                i++;
            }
            if (arrayList == null) {
                return false;
            }
            return dj1.equals(C2229dj1.a(str, arrayList));
        }
    }
}
