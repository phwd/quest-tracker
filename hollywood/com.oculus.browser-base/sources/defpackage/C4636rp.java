package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.oculus.browser.R;
import org.chromium.components.browser_ui.widget.text.TextViewWithCompoundDrawables;
import org.chromium.ui.widget.ChipView;

/* renamed from: rp  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4636rp implements AbstractC5386wC {
    public final int F;

    public C4636rp(int i) {
        this.F = i;
    }

    @Override // defpackage.AbstractC5386wC
    public View a(MenuItem menuItem, View view, ViewGroup viewGroup, LayoutInflater layoutInflater, AbstractC4867t9 t9Var, Integer num) {
        C4466qp qpVar;
        MenuItem item = menuItem.getSubMenu().getItem(0);
        MenuItem item2 = menuItem.getSubMenu().getItem(1);
        int i = this.F;
        if (i != 1) {
            if (i == 2) {
                item = item2;
                item2 = item;
            } else {
                item2 = null;
            }
        }
        if (view == null || !(view.getTag() instanceof C4466qp)) {
            C4466qp qpVar2 = new C4466qp(null);
            View inflate = layoutInflater.inflate(R.layout.f37260_resource_name_obfuscated_RES_2131624035, viewGroup, false);
            qpVar2.f11165a = (TextViewWithCompoundDrawables) inflate.findViewById(R.id.title);
            qpVar2.b = (ChipView) inflate.findViewById(R.id.chip_view);
            inflate.setTag(qpVar2);
            qpVar = qpVar2;
            view = inflate;
        } else {
            qpVar = (C4466qp) view.getTag();
        }
        qpVar.f11165a.setCompoundDrawablesRelative(item.getIcon(), null, null, null);
        qpVar.f11165a.setText(item.getTitle());
        qpVar.f11165a.setEnabled(item.isEnabled());
        qpVar.f11165a.setOnClickListener(new View$OnClickListenerC3953np(t9Var, item));
        int i2 = item.isChecked() ? R.color.f10010_resource_name_obfuscated_RES_2131099691 : R.color.f11380_resource_name_obfuscated_RES_2131099828;
        TextViewWithCompoundDrawables textViewWithCompoundDrawables = qpVar.f11165a;
        Context context = view.getContext();
        ThreadLocal threadLocal = AbstractC5544x8.f11592a;
        textViewWithCompoundDrawables.h(context.getColorStateList(i2));
        if (item2 != null) {
            qpVar.b.setVisibility(0);
            qpVar.b.F.setText(item2.getTitle());
            qpVar.b.setEnabled(item2.isEnabled());
            qpVar.b.setOnClickListener(new View$OnClickListenerC4124op(t9Var, item2));
            if (num == null || item2.getItemId() != num.intValue()) {
                AbstractC3628lu1.b(qpVar.b);
            } else {
                ChipView chipView = qpVar.b;
                AbstractC3628lu1.a(chipView, HI0.c(chipView.getContext(), chipView.N));
            }
        }
        if (num == null || item.getItemId() != num.intValue()) {
            AbstractC3628lu1.b(qpVar.f11165a);
        } else {
            AbstractC3628lu1.d(qpVar.f11165a);
        }
        view.setEnabled(false);
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

    @Override // defpackage.AbstractC5386wC
    public int getItemViewType(int i) {
        return (i == R.id.downloads_row_menu_id || i == R.id.all_bookmarks_row_menu_id) ? 0 : -1;
    }

    @Override // defpackage.AbstractC5386wC
    public int getViewTypeCount() {
        return 1;
    }
}
