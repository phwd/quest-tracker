package org.chromium.components.browser_ui.site_settings;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.RadioGroup;
import androidx.preference.Preference;
import com.oculus.browser.R;
import org.chromium.components.browser_ui.widget.RadioButtonWithDescription;
import org.chromium.components.browser_ui.widget.text.TextViewWithCompoundDrawables;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class FourStateCookieSettingsPreference extends Preference implements RadioGroup.OnCheckedChangeListener {
    public C2525fS t0;
    public RadioButtonWithDescription u0;
    public RadioButtonWithDescription v0;
    public RadioButtonWithDescription w0;
    public RadioButtonWithDescription x0;
    public RadioGroup y0;
    public TextViewWithCompoundDrawables z0;

    public FourStateCookieSettingsPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.k0 = R.layout.f38570_resource_name_obfuscated_RES_2131624166;
        Q(false);
    }

    public final void a0(C2525fS fSVar) {
        RadioButtonWithDescription[] radioButtonWithDescriptionArr;
        RadioButtonWithDescription radioButtonWithDescription;
        this.u0.setEnabled(true);
        this.v0.setEnabled(true);
        this.w0.setEnabled(true);
        this.x0.setEnabled(true);
        boolean z = fSVar.c;
        int i = 0;
        if (z || fSVar.d) {
            radioButtonWithDescriptionArr = (!z || !fSVar.d) ? z ? fSVar.f9922a ? new RadioButtonWithDescription[]{this.x0} : new RadioButtonWithDescription[]{this.u0, this.v0, this.w0, this.x0} : fSVar.b == 1 ? new RadioButtonWithDescription[]{this.u0, this.v0} : new RadioButtonWithDescription[]{this.v0, this.w0} : new RadioButtonWithDescription[]{this.u0, this.v0, this.w0, this.x0};
        } else {
            radioButtonWithDescriptionArr = new RadioButtonWithDescription[0];
        }
        for (RadioButtonWithDescription radioButtonWithDescription2 : radioButtonWithDescriptionArr) {
            radioButtonWithDescription2.setEnabled(false);
        }
        TextViewWithCompoundDrawables textViewWithCompoundDrawables = this.z0;
        if (!fSVar.c && !fSVar.d) {
            i = 8;
        }
        textViewWithCompoundDrawables.setVisibility(i);
        int ordinal = b0(fSVar).ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                radioButtonWithDescription = this.u0;
            } else if (ordinal == 2) {
                radioButtonWithDescription = this.v0;
            } else if (ordinal == 3) {
                radioButtonWithDescription = this.w0;
            } else if (ordinal == 4) {
                radioButtonWithDescription = this.x0;
            }
            radioButtonWithDescription.setEnabled(true);
            radioButtonWithDescription.f(true);
            this.t0 = null;
        }
        radioButtonWithDescription = null;
        radioButtonWithDescription.setEnabled(true);
        radioButtonWithDescription.f(true);
        this.t0 = null;
    }

    public final EnumC2354eS b0(C2525fS fSVar) {
        if (!fSVar.f9922a) {
            return EnumC2354eS.BLOCK;
        }
        int i = fSVar.b;
        if (i == 1) {
            return EnumC2354eS.BLOCK_THIRD_PARTY;
        }
        if (i == 2) {
            return EnumC2354eS.BLOCK_THIRD_PARTY_INCOGNITO;
        }
        return EnumC2354eS.ALLOW;
    }

    public EnumC2354eS c0() {
        if (this.y0 == null && this.t0 == null) {
            return EnumC2354eS.UNINITIALIZED;
        }
        C2525fS fSVar = this.t0;
        if (fSVar != null) {
            return b0(fSVar);
        }
        if (this.u0.e()) {
            return EnumC2354eS.ALLOW;
        }
        if (this.v0.e()) {
            return EnumC2354eS.BLOCK_THIRD_PARTY_INCOGNITO;
        }
        if (this.w0.e()) {
            return EnumC2354eS.BLOCK_THIRD_PARTY;
        }
        return EnumC2354eS.BLOCK;
    }

    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        f(c0());
    }

    @Override // androidx.preference.Preference
    public void x(C4886tF0 tf0) {
        super.x(tf0);
        this.u0 = (RadioButtonWithDescription) tf0.x(R.id.allow);
        this.v0 = (RadioButtonWithDescription) tf0.x(R.id.block_third_party_incognito);
        this.w0 = (RadioButtonWithDescription) tf0.x(R.id.block_third_party);
        this.x0 = (RadioButtonWithDescription) tf0.x(R.id.block);
        RadioGroup radioGroup = (RadioGroup) tf0.x(R.id.radio_button_layout);
        this.y0 = radioGroup;
        radioGroup.setOnCheckedChangeListener(this);
        TextViewWithCompoundDrawables textViewWithCompoundDrawables = (TextViewWithCompoundDrawables) tf0.x(R.id.managed_view);
        this.z0 = textViewWithCompoundDrawables;
        Drawable[] compoundDrawablesRelative = textViewWithCompoundDrawables.getCompoundDrawablesRelative();
        this.z0.setCompoundDrawablesRelativeWithIntrinsicBounds(AbstractC3153j7.c(this.F.getResources(), R.drawable.f29660_resource_name_obfuscated_RES_2131231006), compoundDrawablesRelative[1], compoundDrawablesRelative[2], compoundDrawablesRelative[3]);
        C2525fS fSVar = this.t0;
        if (fSVar != null) {
            a0(fSVar);
        }
    }
}
