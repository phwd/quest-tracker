package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.oculus.browser.R;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* renamed from: Ug  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1237Ug implements AdapterView.OnItemClickListener {
    public final ListView F;
    public final C4284pl0 G;
    public final View H;
    public final List I = new LinkedList();

    /* renamed from: J  reason: collision with root package name */
    public final R80 f9041J;

    public C1237Ug(Context context, C4935tb0 tb0, R80 r80) {
        X80 x80 = new X80(tb0);
        this.G = x80;
        View inflate = LayoutInflater.from(context).inflate(R.layout.f36860_resource_name_obfuscated_RES_2131623995, (ViewGroup) null);
        this.H = inflate;
        ListView listView = (ListView) inflate.findViewById(R.id.app_menu_list);
        this.F = listView;
        listView.setAdapter((ListAdapter) x80);
        listView.setDivider(null);
        listView.setOnItemClickListener(this);
        this.f9041J = r80;
        x80.a(1, new L70(R.layout.f39130_resource_name_obfuscated_RES_2131624222), new C1115Sg());
        x80.a(0, new L70(R.layout.f36850_resource_name_obfuscated_RES_2131623994), new C1176Tg());
    }

    public static C4765sb0 a(int i, int i2, int i3) {
        return new C4765sb0(1, b(i, i2, i3, true));
    }

    public static UH0 b(int i, int i2, int i3, boolean z) {
        Map c = UH0.c(Y80.h);
        SH0 sh0 = Y80.f9255a;
        JH0 jh0 = new JH0(null);
        jh0.f8282a = i;
        c.put(sh0, jh0);
        SH0 sh02 = Y80.f;
        JH0 jh02 = new JH0(null);
        jh02.f8282a = i2;
        c.put(sh02, jh02);
        SH0 sh03 = Y80.c;
        JH0 jh03 = new JH0(null);
        jh03.f8282a = i3;
        c.put(sh03, jh03);
        QH0 qh0 = Y80.g;
        GH0 gh0 = new GH0(null);
        gh0.f8081a = z;
        c.put(qh0, gh0);
        SH0 sh04 = Y80.e;
        JH0 jh04 = new JH0(null);
        jh04.f8282a = R.color.f11340_resource_name_obfuscated_RES_2131099824;
        c.put(sh04, jh04);
        return new UH0(c, null);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        this.f9041J.I(((C4765sb0) this.G.F.G.get(i)).b);
        for (Runnable runnable : this.I) {
            runnable.run();
        }
    }
}
