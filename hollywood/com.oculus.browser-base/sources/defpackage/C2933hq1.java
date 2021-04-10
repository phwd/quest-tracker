package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.oculus.browser.R;

/* renamed from: hq1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2933hq1 implements AbstractC5386wC {
    @Override // defpackage.AbstractC5386wC
    public View a(MenuItem menuItem, View view, ViewGroup viewGroup, LayoutInflater layoutInflater, AbstractC4867t9 t9Var, Integer num) {
        C2762gq1 gq1;
        if (view == null || !(view.getTag() instanceof C2762gq1)) {
            C2762gq1 gq12 = new C2762gq1(null);
            View inflate = layoutInflater.inflate(R.layout.f42190_resource_name_obfuscated_RES_2131624528, viewGroup, false);
            gq12.f10026a = (TextView) inflate.findViewById(R.id.menu_item_text);
            gq12.b = (ImageView) inflate.findViewById(R.id.menu_item_icon);
            gq12.c = (TextView) inflate.findViewById(R.id.menu_item_summary);
            inflate.setTag(gq12);
            gq1 = gq12;
            view = inflate;
        } else {
            gq1 = (C2762gq1) view.getTag();
        }
        C1908bq1 bq1 = C2249dq1.a().f.f9724a;
        if (bq1 == null) {
            return view;
        }
        Resources resources = view.getResources();
        Drawable icon = menuItem.getIcon();
        gq1.b.setImageDrawable(icon);
        gq1.b.setVisibility(icon == null ? 8 : 0);
        gq1.f10026a.setText(bq1.f9564a);
        gq1.f10026a.setContentDescription(resources.getString(bq1.f9564a));
        gq1.f10026a.setTextColor(resources.getColor(bq1.b));
        gq1.f10026a.setEnabled(menuItem.isEnabled());
        if (!TextUtils.isEmpty(bq1.c)) {
            gq1.c.setText(bq1.c);
            gq1.c.setVisibility(0);
        } else {
            gq1.c.setText("");
            gq1.c.setVisibility(8);
        }
        gq1.b.setImageResource(bq1.d);
        if (bq1.e != 0) {
            gq1.b.getDrawable().setTint(resources.getColor(bq1.e));
        }
        view.setEnabled(bq1.f);
        view.setOnClickListener(new View$OnClickListenerC2420eq1(t9Var, menuItem));
        return view;
    }

    @Override // defpackage.AbstractC5386wC
    public boolean b(int i) {
        return true;
    }

    @Override // defpackage.AbstractC5386wC
    public int c(Context context) {
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.f23450_resource_name_obfuscated_RES_2131165964);
        return (context.getResources().getDimensionPixelSize(R.dimen.f23460_resource_name_obfuscated_RES_2131165965) * 2) + Math.max(dimensionPixelSize, AbstractC5544x8.a(context, R.drawable.f33680_resource_name_obfuscated_RES_2131231408).getIntrinsicHeight());
    }

    @Override // defpackage.AbstractC5386wC
    public int getItemViewType(int i) {
        return i == R.id.update_menu_id ? 0 : -1;
    }

    @Override // defpackage.AbstractC5386wC
    public int getViewTypeCount() {
        return 1;
    }
}
