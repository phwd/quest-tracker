package defpackage;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.res.ColorStateList;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import com.oculus.browser.R;
import java.util.Objects;
import org.chromium.chrome.browser.toolbar.menu_button.MenuButton;

/* renamed from: Ji0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0574Ji0 implements YH0 {
    @Override // defpackage.YH0
    public void a(Object obj, Object obj2, Object obj3) {
        AnimatorSet animatorSet;
        UH0 uh0 = (UH0) obj;
        MenuButton menuButton = (MenuButton) obj2;
        KH0 kh0 = (KH0) obj3;
        RH0 rh0 = AbstractC0513Ii0.f8243a;
        if (kh0 == rh0) {
            menuButton.setAlpha(uh0.e(rh0));
            return;
        }
        TH0 th0 = AbstractC0513Ii0.b;
        if (kh0 == th0) {
            AbstractView$OnTouchListenerC4526r9 r9Var = (AbstractView$OnTouchListenerC4526r9) uh0.g(th0);
            menuButton.I = r9Var;
            menuButton.F.setOnTouchListener(r9Var);
            ImageButton imageButton = menuButton.F;
            C4697s9 s9Var = (C4697s9) menuButton.I;
            Objects.requireNonNull(s9Var);
            imageButton.setAccessibilityDelegate(s9Var);
            return;
        }
        TH0 th02 = AbstractC0513Ii0.c;
        if (kh0 == th02) {
            menuButton.F.setContentDescription((String) uh0.g(th02));
            return;
        }
        QH0 qh0 = AbstractC0513Ii0.d;
        if (kh0 == qh0) {
            menuButton.setClickable(uh0.h(qh0));
            return;
        }
        QH0 qh02 = AbstractC0513Ii0.e;
        if (kh0 == qh02) {
            menuButton.f10787J = uh0.h(qh02);
            menuButton.d();
            return;
        }
        QH0 qh03 = AbstractC0513Ii0.f;
        int i = 0;
        if (kh0 == qh03) {
            if (!uh0.h(qh03)) {
                i = 8;
            }
            menuButton.setVisibility(i);
            return;
        }
        TH0 th03 = AbstractC0513Ii0.g;
        if (kh0 == th03) {
            C0391Gi0 gi0 = (C0391Gi0) uh0.g(th03);
            if (gi0.f8103a) {
                boolean z = gi0.b;
                if (menuButton.G != null && menuButton.F != null) {
                    menuButton.b();
                    if (!z || menuButton.N) {
                        menuButton.a(true);
                        return;
                    }
                    menuButton.G.setAlpha(0.0f);
                    menuButton.G.setVisibility(0);
                    ImageButton imageButton2 = menuButton.F;
                    ImageView imageView = menuButton.G;
                    ObjectAnimator ofFloat = ObjectAnimator.ofFloat(imageView, View.ALPHA, 1.0f);
                    ofFloat.setInterpolator(animation.InterpolatorC5286vf.g);
                    ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(imageView, View.TRANSLATION_Y, (float) imageView.getResources().getDimensionPixelSize(R.dimen.f20620_resource_name_obfuscated_RES_2131165681), 0.0f);
                    ofFloat2.setInterpolator(animation.InterpolatorC5286vf.e);
                    ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(imageButton2, View.ALPHA, 0.0f);
                    ofFloat3.setInterpolator(G30.d);
                    AnimatorSet animatorSet2 = new AnimatorSet();
                    animatorSet2.playTogether(ofFloat, ofFloat2, ofFloat3);
                    animatorSet2.setDuration(350L);
                    animatorSet2.addListener(new C5126ui0(imageButton2, imageView));
                    menuButton.M = animatorSet2;
                    animatorSet2.addListener(new C4786si0(menuButton));
                    menuButton.M.start();
                    return;
                }
                return;
            }
            boolean z2 = gi0.b;
            ImageView imageView2 = menuButton.G;
            if (imageView2 != null) {
                if (imageView2.getVisibility() == 0) {
                    if (!z2) {
                        menuButton.a(false);
                        return;
                    }
                    if (menuButton.N && (animatorSet = menuButton.M) != null) {
                        animatorSet.cancel();
                    }
                    menuButton.F.setAlpha(0.0f);
                    ImageButton imageButton3 = menuButton.F;
                    ImageView imageView3 = menuButton.G;
                    ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(imageView3, View.ALPHA, 0.0f);
                    ofFloat4.setInterpolator(animation.InterpolatorC5286vf.f);
                    ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(imageButton3, View.ALPHA, 1.0f);
                    ofFloat5.setInterpolator(animation.InterpolatorC5286vf.g);
                    AnimatorSet animatorSet3 = new AnimatorSet();
                    animatorSet3.playTogether(ofFloat4, ofFloat5);
                    animatorSet3.setDuration(200L);
                    animatorSet3.addListener(new C5296vi0(imageView3, imageButton3));
                    menuButton.M = animatorSet3;
                    animatorSet3.addListener(new C4956ti0(menuButton));
                    menuButton.M.start();
                    return;
                }
                return;
            }
            return;
        }
        TH0 th04 = AbstractC0513Ii0.h;
        if (kh0 == th04) {
            C0452Hi0 hi0 = (C0452Hi0) uh0.g(th04);
            ColorStateList colorStateList = hi0.f8176a;
            boolean z3 = hi0.b;
            menuButton.F.setImageTintList(colorStateList);
            menuButton.H = z3;
            menuButton.b();
            menuButton.d();
            return;
        }
        RH0 rh02 = AbstractC0513Ii0.i;
        if (kh0 == rh02) {
            menuButton.setTranslationX(uh0.e(rh02));
        }
    }
}
