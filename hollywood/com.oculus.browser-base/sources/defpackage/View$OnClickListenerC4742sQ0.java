package defpackage;

import J.N;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.locale.LocaleManager;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.site_settings.SingleWebsiteSettings;
import org.chromium.components.search_engines.TemplateUrl;
import org.chromium.components.search_engines.TemplateUrlService;

/* renamed from: sQ0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnClickListenerC4742sQ0 extends BaseAdapter implements AbstractC0322Ff1, AbstractC0383Gf1, View.OnClickListener {
    public Context F;
    public LayoutInflater G;
    public List H = new ArrayList();
    public List I = new ArrayList();

    /* renamed from: J  reason: collision with root package name */
    public int f11273J = -1;
    public int K = -1;
    public boolean L;
    public boolean M;

    public View$OnClickListenerC4742sQ0(Context context) {
        this.F = context;
        this.G = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    public static boolean b(List list, TemplateUrl templateUrl) {
        for (int i = 0; i < list.size(); i++) {
            TemplateUrl templateUrl2 = (TemplateUrl) list.get(i);
            if (templateUrl2.a() == templateUrl.a() && TextUtils.equals(templateUrl2.b(), templateUrl.b()) && TextUtils.equals(templateUrl2.d(), templateUrl.d())) {
                return true;
            }
        }
        return false;
    }

    public static int d(TemplateUrl templateUrl, TemplateUrl templateUrl2) {
        if (templateUrl.a()) {
            return 1;
        }
        return templateUrl.equals(templateUrl2) ? 0 : 2;
    }

    @Override // defpackage.AbstractC0383Gf1
    public void N() {
        e();
    }

    public final int a() {
        if (this.I.size() > 0) {
            return this.H.size() + 1;
        }
        return this.H.size();
    }

    public final int c(String str) {
        if (str == null) {
            return 0;
        }
        Profile b = Profile.b();
        boolean z = true;
        boolean z2 = new OB0(6, str, null, false).a(b).intValue() == 1 && N.Mno5HIHV(b, 6, str);
        if (new OB0(5, str, null, false).a(b).intValue() != 1 || !N.Mno5HIHV(b, 5, str)) {
            z = false;
        }
        boolean e = C1159Ta0.a().e();
        if (z && e) {
            return z2 ? R.string.f61030_resource_name_obfuscated_RES_2131953420 : R.string.f61020_resource_name_obfuscated_RES_2131953419;
        }
        if (z) {
            return z2 ? R.string.f61050_resource_name_obfuscated_RES_2131953422 : R.string.f61080_resource_name_obfuscated_RES_2131953425;
        }
        if (z2) {
            return R.string.f61040_resource_name_obfuscated_RES_2131953421;
        }
        return 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00a3  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void e() {
        /*
        // Method dump skipped, instructions count: 323
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.View$OnClickListenerC4742sQ0.e():void");
    }

    @Override // defpackage.AbstractC0322Ff1
    public void f() {
        AbstractC0444Hf1.a().k(this);
        this.L = false;
        e();
    }

    public final String g(int i) {
        if (i < this.H.size()) {
            return ((TemplateUrl) this.H.get(i)).b();
        }
        return ((TemplateUrl) this.I.get(i - a())).b();
    }

    public int getCount() {
        List list = this.H;
        int i = 0;
        if (list != null) {
            i = 0 + list.size();
        }
        List list2 = this.I;
        return (list2 == null || list2.size() == 0) ? i : i + this.I.size() + 1;
    }

    public Object getItem(int i) {
        if (i < this.H.size()) {
            return this.H.get(i);
        }
        if (i <= this.H.size()) {
            return null;
        }
        return this.I.get(i - a());
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public int getItemViewType(int i) {
        return (i != this.H.size() || this.I.size() == 0) ? 0 : 1;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        int itemViewType = getItemViewType(i);
        if (view == null) {
            view = this.G.inflate((itemViewType != 1 || this.I.size() == 0) ? R.layout.f41270_resource_name_obfuscated_RES_2131624436 : R.layout.f41280_resource_name_obfuscated_RES_2131624437, (ViewGroup) null);
        }
        if (itemViewType == 1) {
            return view;
        }
        view.setOnClickListener(this);
        view.setTag(Integer.valueOf(i));
        RadioButton radioButton = (RadioButton) view.findViewById(R.id.radiobutton);
        boolean z = i == this.f11273J;
        radioButton.setChecked(z);
        TextView textView = (TextView) view.findViewById(R.id.name);
        this.F.getResources();
        TemplateUrl templateUrl = (TemplateUrl) getItem(i);
        textView.setText(templateUrl.d());
        TextView textView2 = (TextView) view.findViewById(R.id.url);
        textView2.setText(templateUrl.b());
        if (TextUtils.isEmpty(templateUrl.b())) {
            textView2.setVisibility(8);
        }
        radioButton.setImportantForAccessibility(2);
        textView.setAccessibilityDelegate(new C4572rQ0(this, z));
        TextView textView3 = (TextView) view.findViewById(R.id.location_permission);
        textView3.setVisibility(8);
        if (AbstractC0444Hf1.a().b(templateUrl.b()) == null) {
            AbstractC1220Ua0.a("SearchEngines", "Invalid template URL found: %s", templateUrl);
        } else if (z) {
            String b = AbstractC0444Hf1.a().b(templateUrl.b());
            if (b == null) {
                AbstractC1220Ua0.a("SearchEngines", "Invalid template URL found: %s", templateUrl);
                b = "";
            }
            int c = c(b);
            if (c != 0) {
                ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(this.F.getResources().getColor(R.color.f11590_resource_name_obfuscated_RES_2131099849));
                textView3.setVisibility(0);
                textView3.setOnClickListener(this);
                if (c == R.string.f61080_resource_name_obfuscated_RES_2131953425) {
                    textView3.setText(FY0.a(this.F.getString(c), new EY0("<link>", "</link>", foregroundColorSpan)));
                } else {
                    SpannableString spannableString = new SpannableString(this.F.getString(c));
                    spannableString.setSpan(foregroundColorSpan, 0, spannableString.length(), 0);
                    textView3.setText(spannableString);
                }
            }
        }
        return view;
    }

    public int getViewTypeCount() {
        return 2;
    }

    public void onClick(View view) {
        boolean z = true;
        if (view.getTag() == null) {
            this.M = true;
            String b = AbstractC0444Hf1.a().b(g(this.f11273J));
            if (c(b) == R.string.f61080_resource_name_obfuscated_RES_2131953425) {
                this.F.startActivity(C1159Ta0.a().b());
                return;
            }
            Context context = this.F;
            Bundle k1 = SingleWebsiteSettings.k1(b);
            String name = SingleWebsiteSettings.class.getName();
            Intent l = AbstractC2531fV.l(context, XS0.class);
            if (!(context instanceof Activity)) {
                l.addFlags(268435456);
                l.addFlags(67108864);
            }
            l.putExtra("show_fragment", name);
            l.putExtra("show_fragment_args", k1);
            U20.q(context, l);
            return;
        }
        int intValue = ((Integer) view.getTag()).intValue();
        this.f11273J = intValue;
        String g = g(intValue);
        TemplateUrlService a2 = AbstractC0444Hf1.a();
        Objects.requireNonNull(a2);
        Object obj = ThreadUtils.f10596a;
        N.MxknP4iP(a2.c, a2, g);
        if (this.f11273J == this.K) {
            z = false;
        }
        if (z) {
            AbstractC3535lK0.a("SearchEngine_ManualChange");
            LocaleManager.getInstance().e(false);
        }
        notifyDataSetChanged();
    }
}
