package X;

import android.os.Handler;
import androidx.annotation.RequiresApi;
import com.facebook.infer.annotation.Nullsafe;

@RequiresApi(api = 29)
@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1yw  reason: invalid class name and case insensitive filesystem */
public final class C11471yw implements AnonymousClass1zF {
    public final Handler A00;
    public final AnonymousClass1lF A01;
    public final AnonymousClass1zF A02;
    public final Runnable A03;

    @Override // X.AnonymousClass1zF
    public final void A5y(AnonymousClass1lF r4) {
        Handler handler = this.A00;
        Runnable runnable = this.A03;
        if (handler.hasCallbacks(runnable)) {
            handler.removeCallbacks(runnable);
            AnonymousClass1z7.A01(this.A02, handler, r4);
        }
    }

    @Override // X.AnonymousClass1zF
    public final void onSuccess() {
        Handler handler = this.A00;
        Runnable runnable = this.A03;
        if (handler.hasCallbacks(runnable)) {
            handler.removeCallbacks(runnable);
            AnonymousClass1z7.A00(this.A02, handler);
        }
    }

    public C11471yw(AnonymousClass1zF r4, Handler handler, int i, AnonymousClass1lF r7) {
        AnonymousClass1z0 r2 = new AnonymousClass1z0(this);
        this.A03 = r2;
        this.A02 = r4;
        this.A00 = handler;
        this.A01 = r7;
        handler.postDelayed(r2, (long) i);
    }

    public final Handler A00() {
        return this.A00;
    }
}
