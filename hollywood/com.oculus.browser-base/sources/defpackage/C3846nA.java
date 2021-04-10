package defpackage;

import android.content.Context;
import android.content.Intent;
import com.oculus.browser.R;

/* renamed from: nA  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3846nA extends AbstractC3631lv1 {
    public Context Q;
    public String R;
    public int S;
    public int T;
    public boolean U;
    public boolean V;
    public Intent W;
    public String X;

    public C3846nA(Context context, IJ ij) {
        super(R.layout.f37540_resource_name_obfuscated_RES_2131624063, R.id.contextual_search_quick_action_icon_view, context, null, ij);
        this.Q = context;
    }

    public static Integer l(int i) {
        if (i == 1) {
            return Integer.valueOf((int) R.string.f50120_resource_name_obfuscated_RES_2131952329);
        }
        if (i == 2) {
            return Integer.valueOf((int) R.string.f50060_resource_name_obfuscated_RES_2131952323);
        }
        if (i == 3) {
            return Integer.valueOf((int) R.string.f50070_resource_name_obfuscated_RES_2131952324);
        }
        if (i == 4) {
            return Integer.valueOf((int) R.string.f50130_resource_name_obfuscated_RES_2131952330);
        }
        if (i != 5) {
            return null;
        }
        return Integer.valueOf((int) R.string.f50120_resource_name_obfuscated_RES_2131952329);
    }

    public static Integer m(int i) {
        if (i == 1) {
            return Integer.valueOf((int) R.string.f50100_resource_name_obfuscated_RES_2131952327);
        }
        if (i == 2) {
            return Integer.valueOf((int) R.string.f50080_resource_name_obfuscated_RES_2131952325);
        }
        if (i == 3) {
            return Integer.valueOf((int) R.string.f50090_resource_name_obfuscated_RES_2131952326);
        }
        if (i == 4) {
            return Integer.valueOf((int) R.string.f50130_resource_name_obfuscated_RES_2131952330);
        }
        if (i != 5) {
            return null;
        }
        return Integer.valueOf((int) R.string.f50110_resource_name_obfuscated_RES_2131952328);
    }

    public static Integer n(int i) {
        if (i == 1) {
            return Integer.valueOf((int) R.drawable.f32480_resource_name_obfuscated_RES_2131231288);
        }
        if (i == 2) {
            return Integer.valueOf((int) R.drawable.f30020_resource_name_obfuscated_RES_2131231042);
        }
        if (i == 3) {
            return Integer.valueOf((int) R.drawable.f30110_resource_name_obfuscated_RES_2131231051);
        }
        if (i == 4) {
            return Integer.valueOf((int) R.drawable.f32440_resource_name_obfuscated_RES_2131231284);
        }
        if (i != 5) {
            return null;
        }
        return Integer.valueOf((int) R.drawable.f30780_resource_name_obfuscated_RES_2131231118);
    }

    @Override // defpackage.AbstractC3631lv1
    public boolean k() {
        return false;
    }

    public void o() {
        this.R = "";
        this.S = 0;
        this.U = false;
        this.V = false;
        this.W = null;
        this.X = "";
        this.T = 0;
    }
}
