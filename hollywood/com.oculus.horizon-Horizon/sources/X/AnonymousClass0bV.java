package X;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Process;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import javax.annotation.Nullable;

/* renamed from: X.0bV  reason: invalid class name */
public final class AnonymousClass0bV {
    @Nullable
    public static C02790bO A00(Context context, @Nullable Intent intent, @Nullable AnonymousClass0b1 r7, boolean z) {
        ComponentName callingActivity;
        C02790bO A01;
        int i;
        if (!(intent == null || (A01 = C02800bY.A01(context, intent, z, r7)) == null)) {
            if (Binder.getCallingPid() == Process.myPid() || Binder.getCallingUid() == (i = A01.A00)) {
                return A01;
            }
            String format = String.format(Locale.US, "Uid %d from PI not equal to uid %d from binder data", Integer.valueOf(i), Integer.valueOf(Binder.getCallingUid()));
            if (r7 != null && !format.isEmpty()) {
                r7.report(format);
            }
        }
        if ((context instanceof Activity) && (callingActivity = ((Activity) context).getCallingActivity()) != null) {
            return AnonymousClass0bP.A00(context, callingActivity.getPackageName());
        }
        if (Binder.getCallingPid() == Process.myPid()) {
            if (r7 != null && !"This method must be called on behalf of an IPC transaction from binder thread.".isEmpty()) {
                r7.report("This method must be called on behalf of an IPC transaction from binder thread.");
            }
            if (r7 == null || "AppIdentity not found for caller".isEmpty()) {
                return null;
            }
            r7.report("AppIdentity not found for caller");
            return null;
        }
        int callingUid = Binder.getCallingUid();
        List unmodifiableList = Collections.unmodifiableList(Arrays.asList(AnonymousClass0bU.A06(context, callingUid)));
        return new C02790bO(callingUid, unmodifiableList, AnonymousClass0bU.A04(context, (String[]) unmodifiableList.toArray(new String[0])), null, null);
    }
}
