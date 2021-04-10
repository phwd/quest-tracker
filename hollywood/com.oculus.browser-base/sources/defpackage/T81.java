package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.oculus.browser.R;
import java.util.Objects;

/* renamed from: T81  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class T81 implements AbstractC5105ub0 {

    /* renamed from: a  reason: collision with root package name */
    public final C1795b91 f8941a;
    public final Context b;
    public final ViewGroup c;

    public T81(C1795b91 b91, Context context, ViewGroup viewGroup) {
        this.f8941a = b91;
        this.b = context;
        this.c = viewGroup;
    }

    @Override // defpackage.AbstractC5105ub0
    public View a(ViewGroup viewGroup) {
        C1795b91 b91 = this.f8941a;
        Context context = this.b;
        ViewGroup viewGroup2 = this.c;
        Objects.requireNonNull(b91);
        ViewGroup viewGroup3 = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.f37320_resource_name_obfuscated_RES_2131624041, viewGroup2, false);
        if (b91.I == 2) {
            viewGroup3.getLayoutParams().width = context.getResources().getDimensionPixelSize(R.dimen.f25350_resource_name_obfuscated_RES_2131166154);
        }
        if (AbstractC4772sd1.d()) {
            C1795b91.o(viewGroup3);
        }
        viewGroup3.setClickable(true);
        return viewGroup3;
    }
}
