package X;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;

/* renamed from: X.0II  reason: invalid class name */
public final class AnonymousClass0II extends AnonymousClass0iD {
    @Override // X.AbstractC02610as
    @Nullable
    public final Intent A2W(Intent intent, Context context, @Nullable String str) {
        return intent;
    }

    @Override // X.AnonymousClass0iD
    public final boolean A08(Context context, PackageInfo packageInfo) {
        throw new UnsupportedOperationException();
    }

    @Override // X.AbstractC02610as
    public final Intent A2S(Intent intent, Context context, @Nullable String str) {
        this.A00.report("AnyIntentScope", AnonymousClass006.A05("Any_UNSAFE scope used for launching activity: ", AnonymousClass0iD.A03(intent)), null);
        return intent;
    }

    @Override // X.AbstractC02610as
    public final List<Intent> A2T(Intent intent, Context context, @Nullable String str) {
        this.A00.report("AnyIntentScope", AnonymousClass006.A05("Any_UNSAFE scope used for sending a broadcast: ", AnonymousClass0iD.A03(intent)), null);
        return Collections.singletonList(intent);
    }

    @Override // X.AbstractC02610as
    public final Intent A2X(Intent intent, Context context, @Nullable String str) {
        this.A00.report("AnyIntentScope", AnonymousClass006.A05("Any_UNSAFE scope used for launching service: ", AnonymousClass0iD.A03(intent)), null);
        return intent;
    }

    public AnonymousClass0II(C02620au r1, AnonymousClass0b1 r2, C02660b0 r3) {
        super(r1, r2, r3);
    }
}
