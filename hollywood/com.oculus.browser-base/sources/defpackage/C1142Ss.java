package defpackage;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/* renamed from: Ss  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C1142Ss extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C1569Zs f8922a;

    public C1142Ss(C1569Zs zs) {
        this.f8922a = zs;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C1569Zs zs = this.f8922a;
        View view = (View) obj;
        Activity activity = zs.f9379a;
        String str = zs.m;
        YI0 yi0 = new YI0();
        Bundle bundle = new Bundle();
        bundle.putString("url_key", str);
        yi0.setArguments(bundle);
        yi0.show(activity.getFragmentManager(), (String) null);
    }
}
