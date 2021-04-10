package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.oculus.browser.R;

/* renamed from: Wb0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1345Wb0 implements AbstractC5386wC {
    @Override // defpackage.AbstractC5386wC
    public View a(MenuItem menuItem, View view, ViewGroup viewGroup, LayoutInflater layoutInflater, AbstractC4867t9 t9Var, Integer num) {
        if (view == null) {
            view = layoutInflater.inflate(R.layout.f39220_resource_name_obfuscated_RES_2131624231, viewGroup, false);
        }
        view.setFocusable(false);
        return view;
    }

    @Override // defpackage.AbstractC5386wC
    public boolean b(int i) {
        return true;
    }

    @Override // defpackage.AbstractC5386wC
    public int c(Context context) {
        return context.getResources().getDimensionPixelSize(R.dimen.f23440_resource_name_obfuscated_RES_2131165963) + context.getResources().getDimensionPixelSize(R.dimen.f18710_resource_name_obfuscated_RES_2131165490);
    }

    @Override // defpackage.AbstractC5386wC
    public int getItemViewType(int i) {
        return i == R.id.managed_by_menu_id ? 0 : -1;
    }

    @Override // defpackage.AbstractC5386wC
    public int getViewTypeCount() {
        return 1;
    }
}
