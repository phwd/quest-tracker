package defpackage;

import java.util.ArrayList;
import org.chromium.base.Callback;

/* renamed from: OG  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class OG extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Callback f8613a;
    public final /* synthetic */ PG b;

    public OG(PG pg, Callback callback) {
        this.b = pg;
        this.f8613a = callback;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        this.f8613a.onResult(this.b.l((ArrayList) obj));
    }
}
