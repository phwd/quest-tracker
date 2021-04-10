package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.oculus.browser.R;

/* renamed from: W81  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class W81 implements AbstractC5105ub0 {

    /* renamed from: a  reason: collision with root package name */
    public final Context f9131a;
    public final ViewGroup b;

    public W81(Context context, ViewGroup viewGroup) {
        this.f9131a = context;
        this.b = viewGroup;
    }

    @Override // defpackage.AbstractC5105ub0
    public View a(ViewGroup viewGroup) {
        Context context = this.f9131a;
        return (ViewGroup) LayoutInflater.from(context).inflate(R.layout.f41780_resource_name_obfuscated_RES_2131624487, this.b, false);
    }
}
