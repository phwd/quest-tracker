package X;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.DeadObjectException;
import com.facebook.secure.context.IntentLaunchingPlugin;
import java.util.List;
import javax.annotation.Nullable;

@SuppressLint({"BadSuperClassIntentLauncher", "ImprovedNewApi", "BadMethodUse-androidx.fragment.app.Fragment.startActivityForResult"})
/* renamed from: X.0Lb  reason: invalid class name and case insensitive filesystem */
public final class C01710Lb extends AbstractC02860aw {
    public final AbstractC02840au A00;
    public final List<IntentLaunchingPlugin> A01;

    public final void A02(Intent intent, Context context) {
        List<Intent> A2L = this.A00.A2L(intent, context, null);
        if (!A2L.isEmpty()) {
            for (Intent intent2 : A2L) {
                try {
                    context.sendBroadcast(intent2, null);
                } catch (RuntimeException e) {
                    if (!(e.getCause() instanceof DeadObjectException)) {
                        throw e;
                    }
                }
            }
        }
    }

    @Nullable
    public final ComponentName A00(Intent intent, Context context) {
        Intent A2O = this.A00.A2O(intent, context, null);
        if (A2O == null) {
            return null;
        }
        return context.startService(A2O);
    }

    @Nullable
    public final void A01(Intent intent, Context context) {
        Intent A2O = this.A00.A2O(intent, context, null);
        if (A2O == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 26) {
            C04890ht.A00(context, A2O);
        } else {
            context.startService(A2O);
        }
    }

    public C01710Lb(AbstractC02840au r1, List<IntentLaunchingPlugin> list) {
        this.A00 = r1;
        this.A01 = list;
    }
}
