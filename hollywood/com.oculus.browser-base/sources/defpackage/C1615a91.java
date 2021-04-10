package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.oculus.browser.R;

/* renamed from: a91  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C1615a91 implements AbstractC5105ub0 {

    /* renamed from: a  reason: collision with root package name */
    public final Context f9413a;
    public final ViewGroup b;

    public C1615a91(Context context, ViewGroup viewGroup) {
        this.f9413a = context;
        this.b = viewGroup;
    }

    @Override // defpackage.AbstractC5105ub0
    public View a(ViewGroup viewGroup) {
        Context context = this.f9413a;
        ViewGroup viewGroup2 = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.f41380_resource_name_obfuscated_RES_2131624447, this.b, false);
        viewGroup2.setClickable(true);
        return viewGroup2;
    }
}
