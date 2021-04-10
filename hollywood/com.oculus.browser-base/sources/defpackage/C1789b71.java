package defpackage;

import android.app.Activity;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.oculus.browser.R;
import java.util.Map;
import org.chromium.base.Callback;

/* renamed from: b71  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1789b71 {

    /* renamed from: a  reason: collision with root package name */
    public final Context f9514a;
    public final ComponentCallbacks b;
    public final Callback c;
    public O4 d;

    public C1789b71(Context context, View view, Callback callback) {
        this.f9514a = context;
        this.c = callback;
        Z61 z61 = new Z61(this);
        this.b = z61;
        context.registerComponentCallbacks(z61);
        View inflate = LayoutInflater.from(context).inflate(R.layout.f41790_resource_name_obfuscated_RES_2131624488, (ViewGroup) null);
        ListView listView = (ListView) inflate.findViewById(R.id.tab_switcher_action_menu_list);
        C4935tb0 tb0 = new C4935tb0();
        tb0.q(new C4765sb0(0, a(context, R.string.f63130_resource_name_obfuscated_RES_2131953630, R.id.ungroup_tab)));
        tb0.q(new C4765sb0(0, a(context, R.string.f63140_resource_name_obfuscated_RES_2131953631, R.id.share_tab_group)));
        if (AbstractC4772sd1.d()) {
            tb0.q(new C4765sb0(0, a(context, R.string.f63120_resource_name_obfuscated_RES_2131953629, R.id.edit_group_name)));
        }
        C1609a71 a71 = new C1609a71(this, tb0);
        listView.setAdapter((ListAdapter) a71);
        a71.a(0, new L70(R.layout.f39130_resource_name_obfuscated_RES_2131624222), new W61());
        listView.setOnItemClickListener(new X61(this));
        View decorView = ((Activity) inflate.getContext()).getWindow().getDecorView();
        ViewTreeObserver$OnGlobalLayoutListenerC2606fv1 fv1 = new ViewTreeObserver$OnGlobalLayoutListenerC2606fv1(view);
        Rect rect = new Rect();
        decorView.getWindowVisibleDisplayFrame(rect);
        int i = rect.top;
        fv1.e(0, i, 0, i);
        O4 o4 = new O4(context, decorView, AbstractC3153j7.c(context.getResources(), R.drawable.f34630_resource_name_obfuscated_RES_2131231503), inflate, fv1);
        this.d = o4;
        o4.K.setFocusable(true);
        O4 o42 = this.d;
        o42.e0 = true;
        o42.d0 = true;
        o42.K.setAnimationStyle(R.style.f69010_resource_name_obfuscated_RES_2132017474);
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.f20690_resource_name_obfuscated_RES_2131165688);
        O4 o43 = this.d;
        o43.W = dimensionPixelSize;
        o43.P.b(new Y61(this));
    }

    public final UH0 a(Context context, int i, int i2) {
        Map c2 = UH0.c(AbstractC1960c71.c);
        TH0 th0 = AbstractC1960c71.f9585a;
        String string = context.getString(i);
        LH0 lh0 = new LH0(null);
        lh0.f8415a = string;
        c2.put(th0, lh0);
        SH0 sh0 = AbstractC1960c71.b;
        JH0 jh0 = new JH0(null);
        jh0.f8282a = i2;
        c2.put(sh0, jh0);
        return new UH0(c2, null);
    }
}
