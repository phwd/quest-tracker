package X;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.text.TextUtils;
import javax.annotation.Nullable;

/* renamed from: X.0w2  reason: invalid class name and case insensitive filesystem */
public final class C07800w2 {
    public final Context A00;
    public final C08110wa A01 = C08110wa.A00;

    @Nullable
    public static final String A00(Intent intent) {
        String str;
        Bundle bundleExtra = intent.getBundleExtra("auth_bundle");
        if (bundleExtra == null) {
            str = "Invalid auth bundle";
        } else {
            PendingIntent pendingIntent = (PendingIntent) bundleExtra.getParcelable("auth_pending_intent");
            if (pendingIntent != null) {
                return pendingIntent.getCreatorPackage();
            }
            str = "Invalid auth intent";
        }
        AnonymousClass0NK.A01("SignatureAuthSecureIntent", str);
        return null;
    }

    public final void A02(Intent intent) {
        C04980iE r4 = new C04980iE();
        r4.A0B = true;
        Context context = this.A00;
        PendingIntent activity = PendingIntent.getActivity(context, 0, C04980iE.A01(r4, context), C04980iE.A00(r4, 134217728));
        Bundle bundle = new Bundle();
        bundle.putParcelable("auth_pending_intent", activity);
        intent.putExtra("auth_bundle", bundle);
    }

    public final boolean A03(String str) {
        Context context = this.A00;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (str.equals(context.getPackageName()) || C08100wZ.A00(context, str, 64).A02 == AnonymousClass007.A0G) {
            return true;
        }
        return false;
    }

    public C07800w2(Context context) {
        this.A00 = context;
    }

    public final AnonymousClass0ut A01(Intent intent, String str) {
        intent.setPackage(str);
        Context context = this.A00;
        A02(intent);
        try {
            context.sendBroadcast(intent);
            return AnonymousClass0ut.BROADCAST_SENT;
        } catch (SecurityException e) {
            AnonymousClass0NK.A09("RtiGracefulSystemMethodHelper", e, "Failed to sendBroadcast");
            return AnonymousClass0ut.BROADCAST_FAILED;
        } catch (RuntimeException e2) {
            if (!(e2.getCause() instanceof DeadObjectException)) {
                throw e2;
            }
            return AnonymousClass0ut.BROADCAST_FAILED;
        }
    }
}
