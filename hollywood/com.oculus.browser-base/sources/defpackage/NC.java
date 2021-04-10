package defpackage;

import android.app.Activity;
import android.content.res.Resources;
import android.view.View;
import com.oculus.browser.R;

/* renamed from: NC  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class NC extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final Activity f8533a;
    public final Resources b;

    public NC(Activity activity, Resources resources) {
        this.f8533a = activity;
        this.b = resources;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        View view = (View) obj;
        AbstractActivityC5822yn1.r1(this.f8533a, this.b.getString(R.string.f50570_resource_name_obfuscated_RES_2131952374));
    }
}
