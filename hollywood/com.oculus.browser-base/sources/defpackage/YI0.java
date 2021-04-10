package defpackage;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.Iterator;
import org.chromium.ui.widget.ChromeImageButton;

/* renamed from: YI0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class YI0 extends DialogFragment {
    public ArrayList F;
    public M81 G;

    public Dialog onCreateDialog(Bundle bundle) {
        Activity activity = getActivity();
        C4045oJ0 oj0 = new C4045oJ0(activity, new WI0(this), getArguments().getString("url_key"));
        C2166dJ0 dj0 = new C2166dJ0(activity, new XI0(this));
        ArrayList arrayList = new ArrayList();
        this.F = arrayList;
        arrayList.add(oj0);
        this.F.add(dj0);
        C2290e4 e4Var = new C2290e4(getActivity(), R.style.f72740_resource_name_obfuscated_RES_2132017847);
        View inflate = getActivity().getLayoutInflater().inflate(R.layout.f41000_resource_name_obfuscated_RES_2131624409, (ViewGroup) null);
        ((ChromeImageButton) inflate.findViewById(R.id.close_button)).setOnClickListener(new VI0(this));
        ArrayList arrayList2 = new ArrayList();
        Iterator it = this.F.iterator();
        while (it.hasNext()) {
            arrayList2.add(((ZI0) it.next()).b());
        }
        C1644aJ0 aj0 = new C1644aJ0(arrayList2);
        TabLayout tabLayout = (TabLayout) inflate.findViewById(R.id.tab_layout);
        ViewPager viewPager = (ViewPager) inflate.findViewById(R.id.qrcode_view_pager);
        viewPager.w(aj0);
        M81 m81 = new M81(tabLayout, this.F);
        this.G = m81;
        viewPager.b(m81);
        H81 h81 = new H81(viewPager);
        if (!tabLayout.m0.contains(h81)) {
            tabLayout.m0.add(h81);
        }
        C1598a4 a4Var = e4Var.f9828a;
        a4Var.r = inflate;
        a4Var.q = 0;
        return e4Var.a();
    }

    public void onDestroy() {
        super.onDestroy();
        Iterator it = this.F.iterator();
        while (it.hasNext()) {
            ((ZI0) it.next()).a();
        }
        this.F.clear();
    }

    public void onPause() {
        super.onPause();
        Iterator it = this.G.d.iterator();
        while (it.hasNext()) {
            ((ZI0) it.next()).d();
        }
    }

    public void onResume() {
        super.onResume();
        M81 m81 = this.G;
        ((ZI0) m81.d.get(m81.e)).c();
    }
}
