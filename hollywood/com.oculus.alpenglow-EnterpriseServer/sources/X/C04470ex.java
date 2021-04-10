package X;

import android.content.Context;
import android.content.IntentFilter;
import android.os.PowerManager;
import androidx.annotation.NonNull;

/* renamed from: X.0ex  reason: invalid class name and case insensitive filesystem */
public class C04470ex extends AbstractC000302q {
    public final PowerManager A00;
    public final /* synthetic */ LayoutInflater$Factory2C04430et A01;

    @Override // X.AbstractC000302q
    public final int A03() {
        return this.A00.isPowerSaveMode() ? 2 : 1;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C04470ex(@NonNull LayoutInflater$Factory2C04430et r3, Context context) {
        super(r3);
        this.A01 = r3;
        this.A00 = (PowerManager) context.getApplicationContext().getSystemService("power");
    }

    @Override // X.AbstractC000302q
    public final IntentFilter A04() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.os.action.POWER_SAVE_MODE_CHANGED");
        return intentFilter;
    }

    @Override // X.AbstractC000302q
    public final void A05() {
        LayoutInflater$Factory2C04430et.A0D(this.A01, true);
    }
}
