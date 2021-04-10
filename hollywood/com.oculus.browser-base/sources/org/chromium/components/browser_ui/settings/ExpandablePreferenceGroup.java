package org.chromium.components.browser_ui.settings;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimatedStateListDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import com.oculus.browser.R;
import java.util.ArrayList;
import org.chromium.ui.widget.CheckableImageView;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ExpandablePreferenceGroup extends AbstractC2837hF0 {
    public boolean C0 = true;
    public Drawable D0;

    public ExpandablePreferenceGroup(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.f7070_resource_name_obfuscated_RES_2130969153, 0);
        this.l0 = R.layout.f37240_resource_name_obfuscated_RES_2131624033;
    }

    public void i0() {
    }

    public final void j0(boolean z) {
        if (this.C0 != z) {
            this.C0 = z;
            i0();
            s();
        }
    }

    @Override // androidx.preference.Preference
    public void x(C4886tF0 tf0) {
        super.x(tf0);
        if (this.D0 == null) {
            Context context = this.F;
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            int size = arrayList.size() + 1;
            arrayList.add(new C5015u11(R.drawable.f30140_resource_name_obfuscated_RES_2131231054, new int[]{16842912}, size, null));
            int size2 = arrayList.size() + 1;
            arrayList.add(new C5015u11(R.drawable.f30150_resource_name_obfuscated_RES_2131231055, new int[0], size2, null));
            arrayList2.add(new C5185v11(R.drawable.f35450_resource_name_obfuscated_RES_2131231585, size, size2, null));
            arrayList2.add(new C5185v11(R.drawable.f35460_resource_name_obfuscated_RES_2131231586, size2, size, null));
            AnimatedStateListDrawable animatedStateListDrawable = new AnimatedStateListDrawable();
            int size3 = arrayList.size();
            for (int i = 0; i < size3; i++) {
                C5015u11 u11 = (C5015u11) arrayList.get(i);
                animatedStateListDrawable.addState(u11.b, AbstractC5544x8.a(context, u11.f11381a), u11.c);
            }
            int size4 = arrayList2.size();
            for (int i2 = 0; i2 < size4; i2++) {
                C5185v11 v11 = (C5185v11) arrayList2.get(i2);
                animatedStateListDrawable.addTransition(v11.b, v11.c, (Drawable) ((Animatable) AbstractC5544x8.a(context, v11.f11455a)), false);
            }
            ThreadLocal threadLocal = AbstractC5544x8.f11592a;
            animatedStateListDrawable.setTintList(context.getColorStateList(R.color.f11390_resource_name_obfuscated_RES_2131099829));
            this.D0 = animatedStateListDrawable;
        }
        CheckableImageView checkableImageView = (CheckableImageView) tf0.x(R.id.checkable_image_view);
        checkableImageView.setImageDrawable(this.D0);
        checkableImageView.setChecked(this.C0);
        View view = tf0.G;
        StringBuilder sb = new StringBuilder();
        sb.append((Object) this.M);
        sb.append(this.F.getResources().getString(this.C0 ? R.string.f45460_resource_name_obfuscated_RES_2131951863 : R.string.f45340_resource_name_obfuscated_RES_2131951851));
        view.setContentDescription(sb.toString());
    }
}
