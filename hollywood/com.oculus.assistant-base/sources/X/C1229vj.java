package X;

import android.content.Context;
import com.facebook.auth.viewercontext.ViewerContext;

/* renamed from: X.vj  reason: case insensitive filesystem */
public final class C1229vj implements AbstractC0463a6 {
    public final /* synthetic */ Context A00;
    public final /* synthetic */ ViewerContext A01;

    public C1229vj(Context context, ViewerContext viewerContext) {
        this.A00 = context;
        this.A01 = viewerContext;
    }

    @Override // X.AbstractC0463a6
    public final Object get() {
        return W4.A01(this.A01.mAuthToken);
    }
}
