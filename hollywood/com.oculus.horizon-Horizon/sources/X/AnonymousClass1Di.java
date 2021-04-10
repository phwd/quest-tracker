package X;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.DeadObjectException;
import com.facebook.secure.context.IntentLaunchingPlugin;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;

@SuppressLint({"BadSuperClassIntentLauncher", "ImprovedNewApi", "BadMethodUse-androidx.fragment.app.Fragment.startActivityForResult"})
/* renamed from: X.1Di  reason: invalid class name */
public final class AnonymousClass1Di {
    @Nullable
    public String A00;
    public final AnonymousClass0iD A01;
    public final List<IntentLaunchingPlugin> A02;

    public final boolean A03(Intent intent, Context context) {
        List<Intent> A2T = this.A01.A2T(intent, context, this.A00);
        this.A00 = null;
        if (A2T.isEmpty()) {
            return false;
        }
        for (Intent intent2 : A2T) {
            try {
                context.sendBroadcast(intent2, null);
            } catch (RuntimeException e) {
                if (!(e.getCause() instanceof DeadObjectException)) {
                    throw e;
                }
            }
        }
        return true;
    }

    public final boolean A04(Intent intent, ServiceConnection serviceConnection, Context context) {
        Intent A2X = this.A01.A2X(intent, context, this.A00);
        this.A00 = null;
        if (A2X == null) {
            return false;
        }
        return context.bindService(A2X, serviceConnection, 1);
    }

    @Nullable
    public final ComponentName A00(Intent intent, Context context) {
        Intent A2X = this.A01.A2X(intent, context, this.A00);
        this.A00 = null;
        if (A2X != null) {
            return context.startService(A2X);
        }
        return null;
    }

    public final void A01() {
        this.A00 = "fbns_aidl_auth_domain";
    }

    public final boolean A02(Intent intent, Context context) {
        Intent A2S = this.A01.A2S(intent, context, this.A00);
        this.A00 = null;
        if (A2S == null) {
            return false;
        }
        if (context != null) {
            List<IntentLaunchingPlugin> list = this.A02;
            if (!list.isEmpty()) {
                Iterator<IntentLaunchingPlugin> it = list.iterator();
                while (it.hasNext()) {
                    it.next();
                }
            }
        }
        context.startActivity(A2S);
        return true;
    }

    public AnonymousClass1Di() {
    }

    public AnonymousClass1Di(AnonymousClass0iD r2, List<IntentLaunchingPlugin> list) {
        this.A00 = null;
        this.A01 = r2;
        this.A02 = list;
    }
}
