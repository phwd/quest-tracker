package X;

import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Process;
import java.util.List;
import java.util.Locale;
import javax.annotation.Nullable;

/* renamed from: X.82  reason: invalid class name */
public class AnonymousClass82 extends BO {
    @Override // X.AbstractC0224fr
    public final boolean A3f(Context context, Object obj, Intent intent, @Nullable AbstractC0201ew ewVar) {
        int i;
        fX A00 = C0215ff.A00(context, intent, null);
        if (A00 == null) {
            return false;
        }
        if (Binder.getCallingPid() == Process.myPid() || (i = A00.A00) == -1 || Binder.getCallingUid() == i) {
            List<String> list = A00.A04;
            fX fXVar = new fX(A00.A00, list, C0214fc.A04(context, (String[]) list.toArray(new String[0])), A00.A03, A00.A02);
            if (!C0221fn.A02(C0214fc.A03(context, context.getPackageName())) || fXVar.A00() == null || !C0221fn.A02(C0214fc.A03(context, fXVar.A00()))) {
                return false;
            }
            return true;
        }
        throw new SecurityException(String.format(Locale.US, "Uid %d from PI not equal to uid %d from binder data", Integer.valueOf(i), Integer.valueOf(Binder.getCallingUid())));
    }
}
