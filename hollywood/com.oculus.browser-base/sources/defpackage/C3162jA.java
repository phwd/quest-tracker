package defpackage;

import android.os.Handler;
import android.view.View;
import java.util.Objects;

/* renamed from: jA  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3162jA extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C3675mA f10192a;

    public C3162jA(C3675mA mAVar) {
        this.f10192a = mAVar;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C3675mA mAVar = this.f10192a;
        View view = (View) obj;
        Objects.requireNonNull(mAVar);
        new Handler().post(new RunnableC3504lA(mAVar));
    }
}
