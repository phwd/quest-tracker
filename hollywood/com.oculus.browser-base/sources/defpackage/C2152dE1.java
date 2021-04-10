package defpackage;

import android.util.Log;
import com.google.android.gms.common.api.Status;
import java.util.Objects;
import org.json.JSONObject;

/* renamed from: dE1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C2152dE1 implements RF1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ KL0 f9762a;

    public C2152dE1(KL0 kl0, ML0 ml0) {
        this.f9762a = kl0;
    }

    @Override // defpackage.RF1
    public final void a(long j) {
        try {
            KL0 kl0 = this.f9762a;
            Status status = new Status(2103);
            Objects.requireNonNull(kl0);
            kl0.f(new C2664gE1(status));
        } catch (IllegalStateException e) {
            Log.e("RemoteMediaClient", "Result already set when calling onRequestReplaced", e);
        }
    }

    @Override // defpackage.RF1
    public final void b(long j, int i, Object obj) {
        try {
            this.f9762a.f(new LL0(new Status(1, i, null, null), obj instanceof JSONObject ? (JSONObject) obj : null));
        } catch (IllegalStateException e) {
            Log.e("RemoteMediaClient", "Result already set when calling onRequestCompleted", e);
        }
    }
}
