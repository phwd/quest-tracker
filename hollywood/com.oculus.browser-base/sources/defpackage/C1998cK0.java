package defpackage;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import org.chromium.components.navigation_interception.InterceptNavigationDelegate;
import org.chromium.components.navigation_interception.NavigationParams;

/* renamed from: cK0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C1998cK0 implements InterceptNavigationDelegate {

    /* renamed from: a  reason: collision with root package name */
    public final Activity f9600a;

    public C1998cK0(Activity activity) {
        this.f9600a = activity;
    }

    @Override // org.chromium.components.navigation_interception.InterceptNavigationDelegate
    public boolean shouldIgnoreNavigation(NavigationParams navigationParams) {
        Activity activity = this.f9600a;
        if (HG.c(navigationParams.f10856a) || navigationParams.f) {
            return false;
        }
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(navigationParams.f10856a));
        intent.setClassName(activity, Lr.class.getName());
        intent.putExtra("org.chromium.chrome.browser.dom_distiller.EXTRA_READER_MODE_PARENT", U20.g(activity.getIntent().getExtras(), "org.chromium.chrome.browser.dom_distiller.EXTRA_READER_MODE_PARENT", -1));
        activity.startActivity(intent);
        activity.finish();
        return true;
    }
}
