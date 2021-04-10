package defpackage;

import java.util.LinkedList;
import org.chromium.base.Callback;

/* renamed from: vu0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5332vu0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final Callback f11505a;

    public C5332vu0(Callback callback) {
        this.f11505a = callback;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        Object obj2;
        Callback callback = this.f11505a;
        C2536fX0 fx0 = (C2536fX0) obj;
        if (fx0 == null) {
            obj2 = new LinkedList();
        } else {
            obj2 = fx0.f9927a;
        }
        callback.onResult(obj2);
    }
}
