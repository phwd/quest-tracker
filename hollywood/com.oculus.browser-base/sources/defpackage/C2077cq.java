package defpackage;

import android.content.Intent;
import java.util.Objects;

/* renamed from: cq  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2077cq extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C2247dq f9722a;
    public final Intent b;

    public C2077cq(C2247dq dqVar, Intent intent) {
        this.f9722a = dqVar;
        this.b = intent;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C2247dq dqVar = this.f9722a;
        Intent intent = this.b;
        Boolean bool = (Boolean) obj;
        Objects.requireNonNull(dqVar);
        if (bool != null && bool.booleanValue()) {
            dqVar.f9810a.startActivity(intent);
        }
    }
}
