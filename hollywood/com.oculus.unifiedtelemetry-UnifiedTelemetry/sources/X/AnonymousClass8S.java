package X;

import android.content.Context;
import android.content.Intent;
import android.content.pm.Signature;
import android.os.Binder;
import android.os.Process;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import javax.annotation.Nullable;

/* renamed from: X.8S  reason: invalid class name */
public class AnonymousClass8S extends FY {
    @Override // X.AbstractC0421hW
    public final boolean A5D(Context context, Object obj, Intent intent, @Nullable AbstractC0382ge geVar) {
        int i;
        C0408hC A00 = C0414hK.A00(context, intent, null);
        if (A00 == null) {
            return false;
        }
        if (Binder.getCallingPid() == Process.myPid() || (i = A00.A00) == -1 || Binder.getCallingUid() == i) {
            List<String> list = A00.A04;
            String[] strArr = (String[]) list.toArray(new String[0]);
            Signature A01 = C0413hH.A01(C0413hH.A00(context, strArr[0]));
            int length = strArr.length;
            if (length > 1) {
                for (int i2 = 1; i2 < length; i2++) {
                    if (!A01.equals(C0413hH.A01(C0413hH.A00(context, strArr[i2])))) {
                        throw new C0423hZ(AnonymousClass06.A04("packageName=", Arrays.toString(strArr)));
                    }
                }
            }
            C0408hC hCVar = new C0408hC(A00.A00, list, C0413hH.A02(A01), A00.A03, A00.A02);
            if (!C0418hS.A00(C0413hH.A02(C0413hH.A01(C0413hH.A00(context, context.getPackageName())))) || hCVar.A00() == null || !C0418hS.A00(C0413hH.A02(C0413hH.A01(C0413hH.A00(context, hCVar.A00()))))) {
                return false;
            }
            return true;
        }
        throw new SecurityException(String.format(Locale.US, "Uid %d from PI not equal to uid %d from binder data", Integer.valueOf(i), Integer.valueOf(Binder.getCallingUid())));
    }
}
