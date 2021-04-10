package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import androidx.preference.Preference;
import com.oculus.browser.R;

/* renamed from: p3  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4167p3 extends Preference implements YE0 {
    public AbstractC3996o3 t0;
    public int u0;
    public String v0;
    public final QX0 w0;
    public int x0;
    public int y0;

    public C4167p3(Context context, String str, String str2, QX0 qx0, AbstractC3996o3 o3Var) {
        super(context, null);
        this.v0 = str2;
        this.w0 = qx0;
        this.t0 = o3Var;
        this.K = this;
        O(str);
        Resources resources = this.F.getResources();
        this.u0 = resources.getColor(R.color.f11100_resource_name_obfuscated_RES_2131099800);
        this.x0 = resources.getColor(R.color.f11410_resource_name_obfuscated_RES_2131099831);
        this.y0 = resources.getColor(R.color.f11450_resource_name_obfuscated_RES_2131099835);
        Drawable c = AbstractC3153j7.c(resources, R.drawable.f34580_resource_name_obfuscated_RES_2131231498);
        c.mutate();
        c.setColorFilter(this.u0, PorterDuff.Mode.SRC_IN);
        if (this.P != c) {
            this.P = c;
            this.O = 0;
            s();
        }
        V(resources.getString(R.string.f64730_resource_name_obfuscated_RES_2131953790));
    }

    @Override // defpackage.YE0
    public boolean d(Preference preference) {
        View inflate = ((LayoutInflater) this.F.getSystemService("layout_inflater")).inflate(R.layout.f36790_resource_name_obfuscated_RES_2131623988, (ViewGroup) null);
        EditText editText = (EditText) inflate.findViewById(R.id.site);
        CheckBox checkBox = (CheckBox) inflate.findViewById(R.id.third_parties_exception_checkbox);
        if (!this.w0.r(8)) {
            checkBox.setVisibility(8);
            checkBox.setChecked(false);
        }
        DialogInterface$OnClickListenerC3483l3 l3Var = new DialogInterface$OnClickListenerC3483l3(this, checkBox, editText);
        C2290e4 e4Var = new C2290e4(this.F, R.style.f72700_resource_name_obfuscated_RES_2132017843);
        e4Var.g(R.string.f64830_resource_name_obfuscated_RES_2131953800);
        String str = this.v0;
        C1598a4 a4Var = e4Var.f9828a;
        a4Var.f = str;
        a4Var.r = inflate;
        a4Var.q = 0;
        e4Var.e(R.string.f64740_resource_name_obfuscated_RES_2131953791, l3Var);
        e4Var.d(R.string.f48470_resource_name_obfuscated_RES_2131952164, l3Var);
        DialogC2461f4 a2 = e4Var.a();
        ((LayoutInflater$Factory2C3156j8) a2.a()).c0 = false;
        a2.setOnShowListener(new DialogInterface$OnShowListenerC3654m3(this, editText));
        a2.show();
        Button c = a2.c(-1);
        c.setEnabled(false);
        editText.addTextChangedListener(new C3825n3(this, c, editText));
        return true;
    }

    @Override // androidx.preference.Preference
    public void x(C4886tF0 tf0) {
        super.x(tf0);
        TextView textView = (TextView) tf0.x(16908310);
        textView.setAllCaps(true);
        textView.setTextColor(this.u0);
    }
}
