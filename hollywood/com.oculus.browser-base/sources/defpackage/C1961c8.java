package defpackage;

import android.content.Context;
import android.content.IntentFilter;
import android.os.PowerManager;

/* renamed from: c8  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1961c8 extends AbstractC2302e8 {
    public final PowerManager c;
    public final /* synthetic */ LayoutInflater$Factory2C3156j8 d;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C1961c8(LayoutInflater$Factory2C3156j8 j8Var, Context context) {
        super(j8Var);
        this.d = j8Var;
        this.c = (PowerManager) context.getApplicationContext().getSystemService("power");
    }

    @Override // defpackage.AbstractC2302e8
    public IntentFilter b() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.os.action.POWER_SAVE_MODE_CHANGED");
        return intentFilter;
    }

    @Override // defpackage.AbstractC2302e8
    public int c() {
        return this.c.isPowerSaveMode() ? 2 : 1;
    }

    @Override // defpackage.AbstractC2302e8
    public void d() {
        this.d.d();
    }
}
