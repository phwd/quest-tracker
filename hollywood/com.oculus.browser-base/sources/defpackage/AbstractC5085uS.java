package defpackage;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.oculus.browser.R;

/* renamed from: uS  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC5085uS {
    public static C4745sS a(Context context, AbstractC5255vS vSVar, AbstractComponentCallbacksC3550lS lSVar, boolean z) {
        int i;
        C3038iS iSVar = lSVar.n0;
        boolean z2 = false;
        if (iSVar == null) {
            i = 0;
        } else {
            i = iSVar.e;
        }
        int F = lSVar.F();
        lSVar.Y0(0);
        View a2 = vSVar.a(lSVar.b0);
        if (!(a2 == null || a2.getTag(R.id.visible_removing_fragment_view_tag) == null)) {
            a2.setTag(R.id.visible_removing_fragment_view_tag, null);
        }
        ViewGroup viewGroup = lSVar.j0;
        if (viewGroup != null && viewGroup.getLayoutTransition() != null) {
            return null;
        }
        Animation i0 = lSVar.i0();
        if (i0 != null) {
            return new C4745sS(i0);
        }
        Animator j0 = lSVar.j0();
        if (j0 != null) {
            return new C4745sS(j0);
        }
        if (F != 0) {
            boolean equals = "anim".equals(context.getResources().getResourceTypeName(F));
            if (equals) {
                try {
                    Animation loadAnimation = AnimationUtils.loadAnimation(context, F);
                    if (loadAnimation != null) {
                        return new C4745sS(loadAnimation);
                    }
                    z2 = true;
                } catch (Resources.NotFoundException e) {
                    throw e;
                } catch (RuntimeException unused) {
                }
            }
            if (!z2) {
                try {
                    Animator loadAnimator = AnimatorInflater.loadAnimator(context, F);
                    if (loadAnimator != null) {
                        return new C4745sS(loadAnimator);
                    }
                } catch (RuntimeException e2) {
                    if (!equals) {
                        Animation loadAnimation2 = AnimationUtils.loadAnimation(context, F);
                        if (loadAnimation2 != null) {
                            return new C4745sS(loadAnimation2);
                        }
                    } else {
                        throw e2;
                    }
                }
            }
        }
        if (i == 0) {
            return null;
        }
        int i2 = i != 4097 ? i != 4099 ? i != 8194 ? -1 : z ? R.anim.f350_resource_name_obfuscated_RES_2130772003 : R.anim.f360_resource_name_obfuscated_RES_2130772004 : z ? R.anim.f370_resource_name_obfuscated_RES_2130772005 : R.anim.f380_resource_name_obfuscated_RES_2130772006 : z ? R.anim.f400_resource_name_obfuscated_RES_2130772008 : R.anim.f410_resource_name_obfuscated_RES_2130772009;
        if (i2 < 0) {
            return null;
        }
        return new C4745sS(AnimationUtils.loadAnimation(context, i2));
    }
}
