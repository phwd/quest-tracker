package org.chromium.components.browser_ui.contacts_picker;

import J.N;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.oculus.browser.R;
import java.util.HashSet;
import java.util.Objects;
import org.chromium.ui.widget.ChipView;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TopView extends RelativeLayout implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {
    public final Context F;
    public View G;
    public CheckBox H;
    public TextView I;

    /* renamed from: J  reason: collision with root package name */
    public AbstractC3262jm1 f10814J;
    public ChipView K;
    public ChipView L;
    public ChipView M;
    public ChipView N;
    public ChipView O;
    public AbstractC3091im1 P;
    public boolean Q;

    public TopView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.F = context;
    }

    public void a(int i) {
        int i2;
        ChipView chipView;
        if (i == 0) {
            chipView = this.K;
            i2 = R.drawable.f34160_resource_name_obfuscated_RES_2131231456;
        } else if (i == 1) {
            chipView = this.M;
            i2 = R.drawable.f29200_resource_name_obfuscated_RES_2131230960;
        } else if (i == 2) {
            chipView = this.N;
            i2 = R.drawable.f35250_resource_name_obfuscated_RES_2131231565;
        } else if (i == 3) {
            chipView = this.L;
            i2 = R.drawable.f28130_resource_name_obfuscated_RES_2131230853;
        } else if (i == 4) {
            chipView = this.O;
            i2 = R.drawable.f29230_resource_name_obfuscated_RES_2131230963;
        } else {
            return;
        }
        chipView.setSelected(!chipView.isSelected());
        if (chipView.isSelected()) {
            i2 = R.drawable.f29730_resource_name_obfuscated_RES_2131231013;
        }
        chipView.c(i2, true);
        C0472Hs hs = (C0472Hs) this.P;
        Objects.requireNonNull(hs);
        if (i == 0) {
            C0472Hs.f8185J = !C0472Hs.f8185J;
        } else if (i == 1) {
            C0472Hs.K = !C0472Hs.K;
        } else if (i == 2) {
            C0472Hs.L = !C0472Hs.L;
        } else if (i == 3) {
            C0472Hs.I = !C0472Hs.I;
        } else if (i == 4) {
            C0472Hs.M = !C0472Hs.M;
        }
        hs.F.b();
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (!this.Q) {
            IC0 ic0 = (IC0) this.f10814J;
            if (this.H.isChecked()) {
                C3209jS0 js0 = ic0.Q;
                ic0.T = js0.c;
                js0.c = new HashSet(ic0.N.R);
                js0.e();
                ic0.f8206J.a(2, null, 0, 0);
                return;
            }
            C3209jS0 js02 = ic0.Q;
            js02.c = new HashSet();
            js02.e();
            ic0.T = null;
            ic0.f8206J.a(3, null, 0, 0);
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.names_filter) {
            a(0);
        } else if (id == R.id.address_filter) {
            a(3);
        } else if (id == R.id.email_filter) {
            a(1);
        } else if (id == R.id.tel_filter) {
            a(2);
        } else if (id == R.id.icon_filter) {
            a(4);
        }
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.G = findViewById(R.id.content);
        if (N.Mk6X8tWe("ContactsPickerSelectAll")) {
            this.G.setVisibility(0);
        }
        this.H = (CheckBox) findViewById(R.id.checkbox);
        this.I = (TextView) findViewById(R.id.checkbox_details);
        ((TextView) findViewById(R.id.checkbox_title)).setText(R.string.f49540_resource_name_obfuscated_RES_2131952271);
        ChipView chipView = (ChipView) findViewById(R.id.names_filter);
        this.K = chipView;
        chipView.F.setText(R.string.f63510_resource_name_obfuscated_RES_2131953668);
        this.K.setSelected(true);
        this.K.setOnClickListener(this);
        this.K.c(R.drawable.f29730_resource_name_obfuscated_RES_2131231013, false);
        ChipView chipView2 = (ChipView) findViewById(R.id.address_filter);
        this.L = chipView2;
        chipView2.F.setText(R.string.f63480_resource_name_obfuscated_RES_2131953665);
        this.L.setSelected(true);
        this.L.setOnClickListener(this);
        this.L.c(R.drawable.f29730_resource_name_obfuscated_RES_2131231013, false);
        ChipView chipView3 = (ChipView) findViewById(R.id.email_filter);
        this.M = chipView3;
        chipView3.F.setText(R.string.f63490_resource_name_obfuscated_RES_2131953666);
        this.M.setSelected(true);
        this.M.setOnClickListener(this);
        this.M.c(R.drawable.f29730_resource_name_obfuscated_RES_2131231013, false);
        ChipView chipView4 = (ChipView) findViewById(R.id.tel_filter);
        this.N = chipView4;
        chipView4.F.setText(R.string.f63520_resource_name_obfuscated_RES_2131953669);
        this.N.setSelected(true);
        this.N.setOnClickListener(this);
        this.N.c(R.drawable.f29730_resource_name_obfuscated_RES_2131231013, false);
        ChipView chipView5 = (ChipView) findViewById(R.id.icon_filter);
        this.O = chipView5;
        chipView5.F.setText(R.string.f63500_resource_name_obfuscated_RES_2131953667);
        this.O.setSelected(true);
        this.O.setOnClickListener(this);
        this.O.c(R.drawable.f29730_resource_name_obfuscated_RES_2131231013, false);
    }
}
