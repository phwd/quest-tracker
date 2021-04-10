package defpackage;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;
import java.util.Objects;

/* renamed from: og  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4097og extends AbstractC3413kg {
    public final /* synthetic */ BaseGmsClient g;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C4097og(BaseGmsClient baseGmsClient, int i) {
        super(baseGmsClient, i, null);
        this.g = baseGmsClient;
    }

    @Override // defpackage.AbstractC3413kg
    public final void b(ConnectionResult connectionResult) {
        Objects.requireNonNull(this.g);
        this.g.o.a(connectionResult);
        this.g.q(connectionResult);
    }

    @Override // defpackage.AbstractC3413kg
    public final boolean d() {
        this.g.o.a(ConnectionResult.F);
        return true;
    }
}
