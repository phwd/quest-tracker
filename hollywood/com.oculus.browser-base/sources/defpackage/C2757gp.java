package defpackage;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.material.chip.Chip;
import com.oculus.browser.R;
import java.util.List;

/* renamed from: gp  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2757gp extends AbstractC5583xM {
    public final /* synthetic */ Chip q;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C2757gp(Chip chip, Chip chip2) {
        super(chip2);
        this.q = chip;
    }

    @Override // defpackage.AbstractC5583xM
    public int o(float f, float f2) {
        Chip chip = this.q;
        Rect rect = Chip.I;
        return (!chip.f() || !this.q.d().contains(f, f2)) ? 0 : 1;
    }

    @Override // defpackage.AbstractC5583xM
    public void p(List list) {
        C3098ip ipVar;
        list.add(0);
        Chip chip = this.q;
        Rect rect = Chip.I;
        if (chip.f() && (ipVar = this.q.L) != null && ipVar.s0) {
        }
    }

    @Override // defpackage.AbstractC5583xM
    public boolean s(int i, int i2, Bundle bundle) {
        if (i2 == 16) {
            if (i == 0) {
                return this.q.performClick();
            }
            if (i == 1) {
                Chip chip = this.q;
                chip.playSoundEffect(0);
                chip.W.y(1, 1);
            }
        }
        return false;
    }

    @Override // defpackage.AbstractC5583xM
    public void u(D d) {
        d.b.setCheckable(this.q.g());
        d.b.setClickable(this.q.isClickable());
        if (this.q.g() || this.q.isClickable()) {
            d.b.setClassName(this.q.g() ? "android.widget.CompoundButton" : "android.widget.Button");
        } else {
            d.b.setClassName("android.view.View");
        }
        d.b.setText(this.q.getText());
    }

    @Override // defpackage.AbstractC5583xM
    public void v(int i, D d) {
        String str = "";
        if (i == 1) {
            Chip chip = this.q;
            C3098ip ipVar = chip.L;
            CharSequence text = chip.getText();
            Context context = this.q.getContext();
            Object[] objArr = new Object[1];
            if (!TextUtils.isEmpty(text)) {
                str = text;
            }
            objArr[0] = str;
            d.b.setContentDescription(context.getString(R.string.f55410_resource_name_obfuscated_RES_2131952858, objArr).trim());
            d.b.setBoundsInParent(this.q.e());
            d.a(A.f7647a);
            d.b.setEnabled(this.q.isEnabled());
            return;
        }
        d.b.setContentDescription(str);
        d.b.setBoundsInParent(Chip.I);
    }

    @Override // defpackage.AbstractC5583xM
    public void w(int i, boolean z) {
        if (i == 1) {
            Chip chip = this.q;
            chip.S = z;
            chip.refreshDrawableState();
        }
    }
}
