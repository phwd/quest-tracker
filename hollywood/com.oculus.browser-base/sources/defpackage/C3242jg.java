package defpackage;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;

/* renamed from: jg  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3242jg implements AbstractC3071ig {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ BaseGmsClient f10226a;

    public C3242jg(BaseGmsClient baseGmsClient) {
        this.f10226a = baseGmsClient;
    }

    @Override // defpackage.AbstractC3071ig
    public void a(ConnectionResult connectionResult) {
        if (connectionResult.A()) {
            BaseGmsClient baseGmsClient = this.f10226a;
            baseGmsClient.j(null, baseGmsClient.k());
            return;
        }
        PB1 pb1 = this.f10226a.u;
        if (pb1 != null) {
            pb1.f8675a.e0(connectionResult);
        }
    }
}
