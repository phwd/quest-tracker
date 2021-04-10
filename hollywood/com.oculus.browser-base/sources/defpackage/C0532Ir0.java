package defpackage;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import org.chromium.base.ApplicationStatus;
import org.chromium.content_public.browser.LoadUrlParams;

/* renamed from: Ir0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C0532Ir0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final int f8252a;
    public final long b;
    public final boolean c;
    public final boolean d;

    public C0532Ir0(int i, long j, boolean z, boolean z2) {
        this.f8252a = i;
        this.b = j;
        this.c = z;
        this.d = z2;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C0975Qa qa;
        int i = this.f8252a;
        boolean z = this.d;
        LoadUrlParams loadUrlParams = (LoadUrlParams) obj;
        if (loadUrlParams != null) {
            Activity activity = ApplicationStatus.e;
            ComponentName componentName = null;
            if (i != 4) {
                if (ApplicationStatus.hasVisibleActivities()) {
                    Activity activity2 = ApplicationStatus.e;
                    if (activity2 instanceof AbstractActivityC2601fu) {
                        componentName = activity2.getComponentName();
                    }
                }
                if (componentName == null) {
                    qa = new C0975Qa(loadUrlParams);
                } else {
                    qa = new C0975Qa(loadUrlParams, componentName);
                }
                new B61(z).g(qa, 2, -1);
            } else if (activity != null) {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(loadUrlParams.f10938a));
                S20.w(loadUrlParams.e, intent);
                intent.putExtra("com.android.browser.application_id", activity.getApplicationContext().getPackageName());
                intent.setPackage(activity.getApplicationContext().getPackageName());
                intent.setFlags(268435456);
                S20.y(intent, null);
            }
        }
    }
}
