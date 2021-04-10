package X;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.DeadObjectException;
import javax.annotation.Nullable;

/* renamed from: X.0Xx  reason: invalid class name and case insensitive filesystem */
public final class C01890Xx {
    public final Context A00;
    public final AnonymousClass0Wc A01 = AnonymousClass0Wc.A00;

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
        AnonymousClass0NO.A08("SignatureAuthSecureIntent", str);
        return null;
    }

    public final void A02(Intent intent) {
        AnonymousClass0b4 r4 = new AnonymousClass0b4();
        r4.A0B = true;
        Context context = this.A00;
        PendingIntent activity = PendingIntent.getActivity(context, 0, AnonymousClass0b4.A01(r4, context), AnonymousClass0b4.A00(r4, 134217728));
        Bundle bundle = new Bundle();
        bundle.putParcelable("auth_pending_intent", activity);
        intent.putExtra("auth_bundle", bundle);
    }

    public final void A04(Intent intent, String str) {
        if (AnonymousClass0WZ.A01(this.A00, str)) {
            A01(intent, str);
        }
    }

    public C01890Xx(Context context) {
        this.A00 = context;
    }

    public final EnumC01870Xu A01(Intent intent, String str) {
        intent.setPackage(str);
        Context context = this.A00;
        A02(intent);
        try {
            context.sendBroadcast(intent);
            return EnumC01870Xu.BROADCAST_SENT;
        } catch (SecurityException e) {
            AnonymousClass0NO.A0H("RtiGracefulSystemMethodHelper", e, "Failed to sendBroadcast");
            return EnumC01870Xu.BROADCAST_FAILED;
        } catch (RuntimeException e2) {
            if (!(e2.getCause() instanceof DeadObjectException)) {
                throw e2;
            }
            return EnumC01870Xu.BROADCAST_FAILED;
        }
    }

    @Nullable
    public final void A03(Intent intent) {
        String packageName = intent.getComponent().getPackageName();
        if (packageName != null) {
            Context context = this.A00;
            AnonymousClass0Wc r1 = this.A01;
            if (AnonymousClass0WZ.A01(context, packageName)) {
                A02(intent);
                r1.A05(context, intent);
            }
        }
    }
}
