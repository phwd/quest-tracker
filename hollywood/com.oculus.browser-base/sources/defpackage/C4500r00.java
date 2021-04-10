package defpackage;

import J.N;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.oculus.browser.R;
import org.chromium.components.browser_ui.widget.text.TextViewWithCompoundDrawables;
import org.chromium.ui.widget.ChromeImageView;

/* renamed from: r00  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4500r00 implements AbstractC5386wC {
    @Override // defpackage.AbstractC5386wC
    public View a(MenuItem menuItem, View view, ViewGroup viewGroup, LayoutInflater layoutInflater, AbstractC4867t9 t9Var, Integer num) {
        C4330q00 q00;
        if (view == null || !(view.getTag() instanceof C4330q00)) {
            C4330q00 q002 = new C4330q00(null);
            View inflate = layoutInflater.inflate(R.layout.f37710_resource_name_obfuscated_RES_2131624080, viewGroup, false);
            q002.f11107a = (TextViewWithCompoundDrawables) inflate.findViewById(R.id.title);
            q002.b = (ChromeImageView) inflate.findViewById(R.id.trailing_icon);
            inflate.setTag(q002);
            q00 = q002;
            view = inflate;
        } else {
            q00 = (C4330q00) view.getTag();
        }
        q00.f11107a.setCompoundDrawablesRelative(menuItem.getIcon(), null, null, null);
        q00.f11107a.setText(menuItem.getTitle());
        q00.f11107a.setEnabled(menuItem.isEnabled());
        q00.f11107a.setFocusable(false);
        view.setFocusable(menuItem.isEnabled());
        if (N.MRzPUMq7()) {
            q00.b.setImageResource(R.drawable.f29650_resource_name_obfuscated_RES_2131231005);
            q00.b.setVisibility(0);
        }
        view.setOnClickListener(new View$OnClickListenerC3988o00(t9Var, menuItem));
        if (num == null || menuItem.getItemId() != num.intValue()) {
            AbstractC3628lu1.b(view);
        } else {
            AbstractC3628lu1.d(view);
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

    @Override // defpackage.AbstractC5386wC
    public int getItemViewType(int i) {
        return i == R.id.new_incognito_tab_menu_id ? 0 : -1;
    }

    @Override // defpackage.AbstractC5386wC
    public int getViewTypeCount() {
        return 1;
    }
}
