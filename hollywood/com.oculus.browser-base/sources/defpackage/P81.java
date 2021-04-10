package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.oculus.browser.R;

/* renamed from: P81  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class P81 implements AbstractC5105ub0 {

    /* renamed from: a  reason: collision with root package name */
    public final Context f8672a;
    public final ViewGroup b;

    public P81(Context context, ViewGroup viewGroup) {
        this.f8672a = context;
        this.b = viewGroup;
    }

    @Override // defpackage.AbstractC5105ub0
    public View a(ViewGroup viewGroup) {
        Context context = this.f8672a;
        ViewGroup viewGroup2 = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.f41370_resource_name_obfuscated_RES_2131624446, this.b, false);
        viewGroup2.setClickable(true);
        if (AbstractC4772sd1.d()) {
            C1795b91.o(viewGroup2);
        }
        return viewGroup2;
    }
}
