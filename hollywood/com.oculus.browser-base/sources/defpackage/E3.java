package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.List;
import org.chromium.components.browser_ui.widget.text.TextViewWithCompoundDrawables;

/* renamed from: E3  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class E3 extends ArrayAdapter implements AbstractC5386wC, AdapterView.OnItemClickListener, AbstractC3087il0 {
    public final List F = new ArrayList();
    public final C2746gl0 G;
    public AbstractC4867t9 H;
    public UH0 I;

    /* renamed from: J  reason: collision with root package name */
    public Context f7933J;
    public Integer K;

    public E3(Context context, C2746gl0 gl0) {
        super(context, R.layout.f36820_resource_name_obfuscated_RES_2131623991);
        this.f7933J = context;
        this.G = gl0;
    }

    @Override // defpackage.AbstractC5386wC
    public View a(MenuItem menuItem, View view, ViewGroup viewGroup, LayoutInflater layoutInflater, AbstractC4867t9 t9Var, Integer num) {
        D3 d3;
        this.H = t9Var;
        if (view == null || !(view.getTag() instanceof D3)) {
            D3 d32 = new D3(null);
            View inflate = layoutInflater.inflate(R.layout.f37710_resource_name_obfuscated_RES_2131624080, viewGroup, false);
            d32.f7858a = (TextViewWithCompoundDrawables) inflate.findViewById(R.id.title);
            inflate.setTag(d32);
            d3 = d32;
            view = inflate;
        } else {
            d3 = (D3) view.getTag();
        }
        d3.f7858a.setCompoundDrawablesRelative(menuItem.getIcon(), null, null, null);
        d3.f7858a.setText(menuItem.getTitle());
        d3.f7858a.setFocusable(false);
        view.setOnClickListener(new B3(this));
        this.K = null;
        this.F.clear();
        for (int i = 0; i < menuItem.getSubMenu().size(); i++) {
            if (menuItem.getSubMenu().getItem(i).isVisible()) {
                this.F.add(menuItem.getSubMenu().getItem(i));
                if (num != null && menuItem.getSubMenu().getItem(i).getItemId() == num.intValue()) {
                    this.K = num;
                    AbstractC3628lu1.d(view);
                }
            }
        }
        if (this.K == null) {
            AbstractC3628lu1.b(view);
        }
        return view;
    }

    @Override // defpackage.AbstractC5386wC
    public boolean b(int i) {
        return true;
    }

    @Override // defpackage.AbstractC5386wC
    public int c(Context context) {
        return context.obtainStyledAttributes(new int[]{16843655}).getDimensionPixelSize(0, 0);
    }

    @Override // defpackage.AbstractC3087il0
    public void d(UH0 uh0, int i) {
        if (i == 0) {
            this.G.b(uh0, 1);
        }
    }

    @Override // defpackage.AbstractC3087il0
    public void f(UH0 uh0, int i) {
        this.I = null;
    }

    public int getCount() {
        return this.F.size();
    }

    @Override // defpackage.AbstractC5386wC
    public int getItemViewType(int i) {
        return i == R.id.add_to_menu_id ? 0 : -1;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(this.f7933J).inflate(R.layout.f36820_resource_name_obfuscated_RES_2131623991, viewGroup, false);
        }
        TextViewWithCompoundDrawables textViewWithCompoundDrawables = (TextViewWithCompoundDrawables) view;
        MenuItem menuItem = (MenuItem) this.F.get(i);
        textViewWithCompoundDrawables.setCompoundDrawablesRelative(menuItem.getIcon(), null, null, null);
        textViewWithCompoundDrawables.setText(menuItem.getTitle());
        textViewWithCompoundDrawables.setEnabled(menuItem.isEnabled());
        int i2 = menuItem.isChecked() ? R.color.f10010_resource_name_obfuscated_RES_2131099691 : R.color.f11380_resource_name_obfuscated_RES_2131099828;
        Context context = view.getContext();
        ThreadLocal threadLocal = AbstractC5544x8.f11592a;
        textViewWithCompoundDrawables.h(context.getColorStateList(i2));
        if (this.K == null || menuItem.getItemId() != this.K.intValue()) {
            AbstractC3628lu1.b(view);
        } else {
            AbstractC3628lu1.d(view);
        }
        return view;
    }

    @Override // defpackage.AbstractC5386wC
    public int getViewTypeCount() {
        return 1;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        if (view.isEnabled()) {
            ((View$OnKeyListenerC2476f9) this.H).c((MenuItem) this.F.get(i));
            this.G.b(this.I, 3);
        }
    }
}
