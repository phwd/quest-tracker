package X;

import android.content.Context;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0PF  reason: invalid class name */
public final class AnonymousClass0PF {
    public static boolean A00(Context context) {
        try {
            C01780Lp<AbstractC03240bt> r1 = new C01780Lp(context);
            r1.A00 = 0;
            boolean z = false;
            boolean z2 = true;
            for (AbstractC03240bt r0 : r1) {
                if (!z) {
                    synchronized (r0.A02) {
                    }
                    int i = C01780Lp.A00;
                    int i2 = (r0.A01 << 8) | 0;
                    if (i != 0) {
                        throw new IllegalArgumentException(AnonymousClass006.A01("pluginMethodId: ", i2));
                    } else if (i2 == 0) {
                        Boolean bool = true;
                        z2 = bool.booleanValue();
                        z = true;
                    } else {
                        throw new IllegalArgumentException(AnonymousClass006.A01("method not found: ", i2));
                    }
                } else {
                    throw new RuntimeException("Found multiple IGraphServiceAssetSwitchSocket implementations");
                }
            }
            if (z) {
                return z2;
            }
            throw new RuntimeException("Found no IGraphServiceAssetSwitchSocket implementation");
        } catch (NoClassDefFoundError unused) {
            return true;
        }
    }
}
