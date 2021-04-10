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

/* renamed from: X.0kD  reason: invalid class name */
public final class AnonymousClass0kD {
    @Nullable
    public static AnonymousClass0k7 A00(Context context, @Nullable Intent intent, @Nullable AbstractC02660jW r7, boolean z) {
        int callingUid;
        List unmodifiableList;
        String[] strArr;
        ComponentName callingActivity;
        AnonymousClass0k7 A00;
        int i;
        if (!(intent == null || (A00 = AnonymousClass0kG.A00(context, intent, z, r7)) == null)) {
            if (Binder.getCallingPid() == Process.myPid() || Binder.getCallingUid() == (i = A00.A00)) {
                return A00;
            }
            String format = String.format(Locale.US, "Uid %d from PI not equal to uid %d from binder data", Integer.valueOf(i), Integer.valueOf(Binder.getCallingUid()));
            if (r7 != null && !format.isEmpty()) {
                r7.report(format);
            }
        }
        if ((context instanceof Activity) && (callingActivity = ((Activity) context).getCallingActivity()) != null) {
            String packageName = callingActivity.getPackageName();
            callingUid = AnonymousClass0kC.A01(context, packageName).applicationInfo.uid;
            unmodifiableList = Collections.unmodifiableList(Arrays.asList(packageName));
            strArr = new String[0];
        } else if (Binder.getCallingPid() == Process.myPid()) {
            if (r7 != null && !"This method must be called on behalf of an IPC transaction from binder thread.".isEmpty()) {
                r7.report("This method must be called on behalf of an IPC transaction from binder thread.");
            }
            if (r7 == null || "AppIdentity not found for caller".isEmpty()) {
                return null;
            }
            r7.report("AppIdentity not found for caller");
            return null;
        } else {
            callingUid = Binder.getCallingUid();
            unmodifiableList = Collections.unmodifiableList(Arrays.asList(AnonymousClass0kC.A07(context, callingUid)));
            strArr = new String[0];
        }
        return new AnonymousClass0k7(callingUid, unmodifiableList, AnonymousClass0kC.A04(context, (String[]) unmodifiableList.toArray(strArr)), null, null);
    }
}
