package X;

import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Process;
import java.util.List;
import java.util.Locale;
import javax.annotation.Nullable;

/* renamed from: X.0G8  reason: invalid class name */
public class AnonymousClass0G8 extends AnonymousClass0LS {
    @Override // X.AnonymousClass0j1
    public final boolean A8K(Context context, Object obj, Intent intent, @Nullable AbstractC04970iB r12) {
        int i;
        C05130ih A00 = C05200ip.A00(context, intent, false, null);
        if (A00 == null) {
            return false;
        }
        if (Binder.getCallingPid() == Process.myPid() || (i = A00.A00) == -1 || Binder.getCallingUid() == i) {
            List<String> list = A00.A04;
            C05130ih r2 = new C05130ih(A00.A00, list, C05180im.A04(context, (String[]) list.toArray(new String[0])), A00.A03, A00.A02);
            if (!C05280ix.A00(C05180im.A03(context, context.getPackageName())) || r2.A00() == null || !C05280ix.A00(C05180im.A03(context, r2.A00()))) {
                return false;
            }
            return true;
        }
        throw new SecurityException(String.format(Locale.US, "Uid %d from PI not equal to uid %d from binder data", Integer.valueOf(i), Integer.valueOf(Binder.getCallingUid())));
    }
}
