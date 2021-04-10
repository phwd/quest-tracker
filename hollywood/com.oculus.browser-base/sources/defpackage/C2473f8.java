package defpackage;

import android.content.IntentFilter;

/* renamed from: f8  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2473f8 extends AbstractC2302e8 {
    public final Do1 c;
    public final /* synthetic */ LayoutInflater$Factory2C3156j8 d;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C2473f8(LayoutInflater$Factory2C3156j8 j8Var, Do1 do1) {
        super(j8Var);
        this.d = j8Var;
        this.c = do1;
    }

    @Override // defpackage.AbstractC2302e8
    public IntentFilter b() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.TIME_SET");
        intentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
        intentFilter.addAction("android.intent.action.TIME_TICK");
        return intentFilter;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00e2  */
    @Override // defpackage.AbstractC2302e8
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int c() {
        /*
        // Method dump skipped, instructions count: 259
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C2473f8.c():int");
    }

    @Override // defpackage.AbstractC2302e8
    public void d() {
        this.d.d();
    }
}
