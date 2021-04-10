package defpackage;

import J.N;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import com.oculus.browser.R;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.widget.RadioButtonWithDescription;
import org.chromium.components.browser_ui.widget.RadioButtonWithDescriptionLayout;
import org.chromium.content_public.browser.WebContents;

/* renamed from: FZ  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class FZ implements AbstractC3087il0, RadioGroup.OnCheckedChangeListener {
    public BZ F;
    public C2746gl0 G;
    public UH0 H;
    public AbstractC6022zx1 I;

    /* renamed from: J  reason: collision with root package name */
    public RadioButtonWithDescriptionLayout f8022J;
    public RadioButtonWithDescription K;
    public RadioButtonWithDescription L;
    public CheckBox M;
    public boolean N;
    public boolean O;
    public boolean P;
    public WebContents Q;
    public Profile R = Profile.b();
    public Context S;

    public FZ(Context context, C2746gl0 gl0, BZ bz, boolean z, WebContents webContents) {
        this.G = gl0;
        this.F = bz;
        this.Q = webContents;
        this.S = context;
        this.N = z;
        this.O = true;
        this.P = false;
        View inflate = LayoutInflater.from(context).inflate(R.layout.f38690_resource_name_obfuscated_RES_2131624178, (ViewGroup) null);
        RadioButtonWithDescriptionLayout radioButtonWithDescriptionLayout = (RadioButtonWithDescriptionLayout) inflate.findViewById(R.id.image_descriptions_dialog_radio_button_group);
        this.f8022J = radioButtonWithDescriptionLayout;
        radioButtonWithDescriptionLayout.G = this;
        this.K = (RadioButtonWithDescription) inflate.findViewById(R.id.image_descriptions_dialog_radio_button_just_once);
        this.L = (RadioButtonWithDescription) inflate.findViewById(R.id.image_descriptions_dialog_radio_button_always);
        CheckBox checkBox = (CheckBox) inflate.findViewById(R.id.image_descriptions_dialog_check_box);
        this.M = checkBox;
        checkBox.setOnCheckedChangeListener(new DZ(this));
        this.K.f(true);
        if (this.N) {
            a(R.string.f50920_resource_name_obfuscated_RES_2131952409, this.P);
        }
        this.I = new EZ(this, this.Q);
        HH0 hh0 = new HH0(AbstractC3258jl0.r);
        hh0.e(AbstractC3258jl0.f10235a, this);
        hh0.d(AbstractC3258jl0.c, this.S.getResources(), R.string.f52900_resource_name_obfuscated_RES_2131952607);
        hh0.e(AbstractC3258jl0.f, inflate);
        hh0.d(AbstractC3258jl0.j, this.S.getResources(), R.string.f55970_resource_name_obfuscated_RES_2131952914);
        hh0.d(AbstractC3258jl0.g, this.S.getResources(), R.string.f52890_resource_name_obfuscated_RES_2131952606);
        hh0.b(AbstractC3258jl0.q, true);
        this.H = hh0.a();
    }

    public final void a(int i, boolean z) {
        this.M.setVisibility(0);
        this.M.setText(i);
        this.M.setChecked(z);
    }

    @Override // defpackage.AbstractC3087il0
    public void d(UH0 uh0, int i) {
        int i2;
        int i3 = 2;
        if (i == 0) {
            if (this.L.e()) {
                i2 = R.string.f52990_resource_name_obfuscated_RES_2131952616;
                N.Mf2ABpoH(CZ.a(this.F.f7745a, this.R).f10883a, "settings.a11y.enable_accessibility_image_labels_android", true);
                this.F.b(this.O, this.R);
                if (this.O && C5222vE.d(this.S) != 2) {
                    i2 = R.string.f53000_resource_name_obfuscated_RES_2131952617;
                }
            } else if (this.K.e()) {
                this.F.a(this.P, this.Q);
                i2 = R.string.f52970_resource_name_obfuscated_RES_2131952614;
            } else {
                i2 = -1;
            }
            i3 = 1;
        } else {
            i2 = -1;
        }
        if (i2 != -1) {
            C1184Ti1.a(this.S, i2, 1).b.show();
        }
        this.G.b(this.H, i3);
    }

    @Override // defpackage.AbstractC3087il0
    public void f(UH0 uh0, int i) {
    }

    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if (i == this.L.getId()) {
            a(R.string.f52910_resource_name_obfuscated_RES_2131952608, this.O);
        } else if (i != this.K.getId()) {
        } else {
            if (this.N) {
                a(R.string.f50920_resource_name_obfuscated_RES_2131952409, this.P);
            } else {
                this.M.setVisibility(8);
            }
        }
    }
}
