package defpackage;

import java.util.List;
import org.chromium.base.Callback;
import org.chromium.base.task.PostTask;

/* renamed from: lD0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3514lD0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final Callback f10332a;

    public C3514lD0(Callback callback) {
        this.f10332a = callback;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        PostTask.b(Zo1.f9374a, new RunnableC3856nD0(this.f10332a, (List) obj), 0);
    }
}
