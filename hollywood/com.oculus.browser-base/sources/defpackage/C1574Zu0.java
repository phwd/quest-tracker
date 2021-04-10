package defpackage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import com.oculus.browser.R;
import org.chromium.components.page_info.PageInfoController;

/* renamed from: Zu0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C1574Zu0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final PageInfoController f9381a;

    public C1574Zu0(PageInfoController pageInfoController) {
        this.f9381a = pageInfoController;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        PageInfoController pageInfoController = this.f9381a;
        Drawable drawable = (Drawable) obj;
        Context context = pageInfoController.F;
        if (context != null) {
            if (drawable != null) {
                pageInfoController.L.G.setCompoundDrawablesRelative(drawable, null, null, null);
                return;
            }
            C0599Ju0 ju0 = pageInfoController.L;
            ju0.G.setCompoundDrawablesRelative(AbstractC2870hT0.b(context, R.drawable.f30310_resource_name_obfuscated_RES_2131231071), null, null, null);
        }
    }
}
