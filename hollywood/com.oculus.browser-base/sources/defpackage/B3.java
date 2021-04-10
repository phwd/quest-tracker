package defpackage;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.oculus.browser.R;

/* renamed from: B3  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class B3 implements View.OnClickListener {
    public final E3 F;

    public B3(E3 e3) {
        this.F = e3;
    }

    public void onClick(View view) {
        E3 e3 = this.F;
        Resources resources = e3.f7933J.getResources();
        HH0 hh0 = new HH0(AbstractC3258jl0.r);
        hh0.e(AbstractC3258jl0.f10235a, e3);
        hh0.d(AbstractC3258jl0.g, resources, R.string.f49160_resource_name_obfuscated_RES_2131952233);
        TH0 th0 = AbstractC3258jl0.f;
        View inflate = LayoutInflater.from(e3.f7933J).inflate(R.layout.f36810_resource_name_obfuscated_RES_2131623990, (ViewGroup) null);
        ListView listView = (ListView) inflate.findViewById(R.id.list);
        listView.setAdapter((ListAdapter) e3);
        listView.setOnItemClickListener(e3);
        hh0.e(th0, inflate);
        hh0.b(AbstractC3258jl0.m, true);
        UH0 a2 = hh0.a();
        e3.I = a2;
        e3.G.i(a2, 0, false);
    }
}
