package defpackage;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.Log;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import java.util.ArrayList;
import java.util.List;

/* renamed from: Wl0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1373Wl0 {

    /* renamed from: a  reason: collision with root package name */
    public final BW0 f9171a = new BW0();
    public final BW0 b = new BW0();

    public static C1373Wl0 a(Context context, TypedArray typedArray, int i) {
        int resourceId;
        if (typedArray.hasValue(i) && (resourceId = typedArray.getResourceId(i, 0)) != 0) {
            try {
                Animator loadAnimator = AnimatorInflater.loadAnimator(context, resourceId);
                if (loadAnimator instanceof AnimatorSet) {
                    return b(((AnimatorSet) loadAnimator).getChildAnimations());
                }
                if (loadAnimator != null) {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(loadAnimator);
                    return b(arrayList);
                }
            } catch (Exception e) {
                StringBuilder i2 = AbstractC2531fV.i("Can't load animation resource ID #0x");
                i2.append(Integer.toHexString(resourceId));
                Log.w("MotionSpec", i2.toString(), e);
            }
        }
        return null;
    }

    public static C1373Wl0 b(List list) {
        C1373Wl0 wl0 = new C1373Wl0();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Animator animator = (Animator) list.get(i);
            if (animator instanceof ObjectAnimator) {
                ObjectAnimator objectAnimator = (ObjectAnimator) animator;
                wl0.b.put(objectAnimator.getPropertyName(), objectAnimator.getValues());
                String propertyName = objectAnimator.getPropertyName();
                long startDelay = objectAnimator.getStartDelay();
                long duration = objectAnimator.getDuration();
                TimeInterpolator interpolator = objectAnimator.getInterpolator();
                if ((interpolator instanceof AccelerateDecelerateInterpolator) || interpolator == null) {
                    interpolator = P6.b;
                } else if (interpolator instanceof AccelerateInterpolator) {
                    interpolator = P6.c;
                } else if (interpolator instanceof DecelerateInterpolator) {
                    interpolator = P6.d;
                }
                C1434Xl0 xl0 = new C1434Xl0(startDelay, duration, interpolator);
                xl0.d = objectAnimator.getRepeatCount();
                xl0.e = objectAnimator.getRepeatMode();
                wl0.f9171a.put(propertyName, xl0);
            } else {
                throw new IllegalArgumentException("Animator must be an ObjectAnimator: " + animator);
            }
        }
        return wl0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C1373Wl0)) {
            return false;
        }
        return this.f9171a.equals(((C1373Wl0) obj).f9171a);
    }

    public int hashCode() {
        return this.f9171a.hashCode();
    }

    public String toString() {
        return '\n' + C1373Wl0.class.getName() + '{' + Integer.toHexString(System.identityHashCode(this)) + " timings: " + this.f9171a + "}\n";
    }
}
