package X;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import com.facebook.acra.AppComponentStats;
import com.oculus.vrshell.panels.AndroidPanelLayer;

/* renamed from: X.1cs  reason: invalid class name and case insensitive filesystem */
public final class C07701cs {
    public static final int A04;
    public float A00 = ((float) A04);
    public ActivityManager A01;
    public C07991dn A02;
    public final Context A03;

    static {
        int i = 1;
        if (Build.VERSION.SDK_INT < 26) {
            i = 4;
        }
        A04 = i;
    }

    public C07701cs(Context context) {
        this.A03 = context;
        this.A01 = (ActivityManager) context.getSystemService(AppComponentStats.TAG_ACTIVITY);
        this.A02 = new C07991dn(context.getResources().getDisplayMetrics());
        if (Build.VERSION.SDK_INT >= 26 && this.A01.isLowRamDevice()) {
            this.A00 = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
        }
    }
}
