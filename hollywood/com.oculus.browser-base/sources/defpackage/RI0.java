package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.browser.R;

/* renamed from: RI0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RI0 {

    /* renamed from: a  reason: collision with root package name */
    public final Context f8825a;
    public final View b;
    public final View c;

    public RI0(Context context, OI0 oi0) {
        this.f8825a = context;
        View inflate = LayoutInflater.from(context).inflate(R.layout.f40970_resource_name_obfuscated_RES_2131624406, (ViewGroup) null);
        this.c = inflate;
        this.b = LayoutInflater.from(context).inflate(R.layout.f40980_resource_name_obfuscated_RES_2131624407, (ViewGroup) null);
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.screenshots_container);
        recyclerView.g(new QI0(this));
        recyclerView.q0(oi0);
    }
}
