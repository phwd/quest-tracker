package X;

import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Process;
import java.util.List;
import java.util.Locale;
import javax.annotation.Nullable;

/* renamed from: X.0Hb  reason: invalid class name */
public class AnonymousClass0Hb extends AnonymousClass0TP {
    @Override // X.AnonymousClass0kS
    public final boolean AAL(Context context, Object obj, Intent intent, @Nullable AbstractC02660jW r12) {
        int i;
        AnonymousClass0k7 A00 = AnonymousClass0kG.A00(context, intent, false, null);
        if (A00 == null) {
            return false;
        }
        if (Binder.getCallingPid() == Process.myPid() || (i = A00.A00) == -1 || Binder.getCallingUid() == i) {
            List<String> list = A00.A04;
            AnonymousClass0k7 r2 = new AnonymousClass0k7(A00.A00, list, AnonymousClass0kC.A04(context, (String[]) list.toArray(new String[0])), A00.A03, A00.A02);
            if (!AnonymousClass0kO.A01(AnonymousClass0kC.A03(context, context.getPackageName())) || r2.A00() == null || !AnonymousClass0kO.A01(AnonymousClass0kC.A03(context, r2.A00()))) {
                return false;
            }
            return true;
        }
        throw new SecurityException(String.format(Locale.US, "Uid %d from PI not equal to uid %d from binder data", Integer.valueOf(i), Integer.valueOf(Binder.getCallingUid())));
    }
}
