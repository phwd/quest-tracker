package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.oculus.browser.R;
import org.chromium.ui.widget.ViewLookupCachingFrameLayout;

/* renamed from: En1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class En1 implements AbstractC5105ub0 {

    /* renamed from: a  reason: collision with root package name */
    public final Context f7977a;

    public En1(Context context) {
        this.f7977a = context;
    }

    @Override // defpackage.AbstractC5105ub0
    public View a(ViewGroup viewGroup) {
        ViewLookupCachingFrameLayout viewLookupCachingFrameLayout = (ViewLookupCachingFrameLayout) LayoutInflater.from(this.f7977a).inflate(R.layout.f42160_resource_name_obfuscated_RES_2131624525, viewGroup, false);
        viewLookupCachingFrameLayout.setClickable(true);
        return viewLookupCachingFrameLayout;
    }
}
