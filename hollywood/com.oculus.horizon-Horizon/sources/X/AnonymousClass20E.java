package X;

import android.content.Context;
import android.content.pm.PackageManager;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.20E  reason: invalid class name */
public final class AnonymousClass20E {
    public final PackageManager A00;
    public final AnonymousClass20I A01;
    public final AnonymousClass20F A02;
    public final AnonymousClass20G A03;
    public final AnonymousClass20H A04;
    public final C09381by A05 = new C09381by();
    public final AnonymousClass20J A06;

    public AnonymousClass20E(Context context, PackageManager packageManager) {
        this.A00 = packageManager;
        this.A03 = new AnonymousClass20G(packageManager);
        this.A06 = new AnonymousClass20J(packageManager);
        this.A02 = new AnonymousClass20F(context, packageManager);
        this.A01 = new AnonymousClass20I(packageManager);
        this.A04 = new AnonymousClass20H(context, packageManager);
    }
}
