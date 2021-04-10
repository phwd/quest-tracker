package X;

import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Process;
import java.util.List;
import java.util.Locale;
import javax.annotation.Nullable;

/* renamed from: X.08h  reason: invalid class name */
public class AnonymousClass08h extends AbstractC00900Hh {
    @Override // X.AbstractC02910bj
    public final boolean A8w(Context context, Object obj, Intent intent, @Nullable AnonymousClass0b1 r13) {
        C02790bO r3;
        int i;
        C02790bO A00 = C02800bY.A00(context, intent);
        if (A00 == null) {
            r3 = null;
        } else if (Binder.getCallingPid() == Process.myPid() || (i = A00.A00) == -1 || Binder.getCallingUid() == i) {
            List<String> list = A00.A04;
            r3 = new C02790bO(A00.A00, list, AnonymousClass0bU.A04(context, (String[]) list.toArray(new String[0])), A00.A03, A00.A02);
        } else {
            throw new SecurityException(String.format(Locale.US, "Uid %d from PI not equal to uid %d from binder data", Integer.valueOf(i), Integer.valueOf(Binder.getCallingUid())));
        }
        if (r3 == null || !C02870bf.A03(AnonymousClass0bU.A03(context, context.getPackageName())) || r3.A01() == null || !C02870bf.A03(AnonymousClass0bU.A03(context, r3.A01()))) {
            return false;
        }
        return true;
    }
}
