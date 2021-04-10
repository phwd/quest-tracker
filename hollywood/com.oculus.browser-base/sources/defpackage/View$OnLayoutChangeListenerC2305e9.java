package defpackage;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.view.View;
import android.widget.ListView;
import com.oculus.browser.R;
import java.util.Objects;

/* renamed from: e9  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnLayoutChangeListenerC2305e9 implements View.OnLayoutChangeListener {
    public final /* synthetic */ View$OnKeyListenerC2476f9 F;

    public View$OnLayoutChangeListenerC2305e9(View$OnKeyListenerC2476f9 f9Var) {
        this.F = f9Var;
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        this.F.N.removeOnLayoutChangeListener(this);
        View$OnKeyListenerC2476f9 f9Var = this.F;
        Objects.requireNonNull(f9Var);
        f9Var.T = new AnimatorSet();
        ListView listView = f9Var.N;
        AnimatorSet.Builder builder = null;
        for (int i9 = 0; i9 < listView.getChildCount(); i9++) {
            Object tag = listView.getChildAt(i9).getTag(R.id.menu_item_enter_anim_id);
            if (tag != null) {
                if (builder == null) {
                    builder = f9Var.T.play((Animator) tag);
                } else {
                    builder.with((Animator) tag);
                }
            }
        }
        f9Var.T.start();
    }
}
