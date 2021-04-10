package X;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.DeadObjectException;
import com.facebook.secure.context.IntentLaunchingPlugin;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;

@SuppressLint({"BadSuperClassIntentLauncher", "ImprovedNewApi", "BadMethodUse-androidx.fragment.app.Fragment.startActivityForResult"})
/* renamed from: X.1SN  reason: invalid class name */
public final class AnonymousClass1SN {
    public final AbstractC03020km A00;
    public final List<IntentLaunchingPlugin> A01;

    public final boolean A02(Intent intent, Context context) {
        List<Intent> A2o = this.A00.A2o(intent, context, null);
        if (A2o.isEmpty()) {
            return false;
        }
        for (Intent intent2 : A2o) {
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

    @Nullable
    public final void A00(Intent intent, Context context) {
        Intent A2r = this.A00.A2r(intent, context, null);
        if (A2r != null) {
            context.startService(A2r);
        }
    }

    public final boolean A01(Intent intent, Context context) {
        Intent A2n = this.A00.A2n(intent, context, null);
        if (A2n == null) {
            return false;
        }
        if (context != null) {
            List<IntentLaunchingPlugin> list = this.A01;
            if (!list.isEmpty()) {
                Iterator<IntentLaunchingPlugin> it = list.iterator();
                while (it.hasNext()) {
                    it.next();
                }
            }
        }
        context.startActivity(A2n);
        return true;
    }

    public AnonymousClass1SN() {
    }

    public AnonymousClass1SN(AbstractC03020km r1, List<IntentLaunchingPlugin> list) {
        this.A00 = r1;
        this.A01 = list;
    }
}
