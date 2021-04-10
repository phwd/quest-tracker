package defpackage;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.Property;
import com.oculus.browser.R;

/* renamed from: G2  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class G2 {

    /* renamed from: a  reason: collision with root package name */
    public static final Property f8057a = new C2(Integer.class, "controlTopMargin");
    public ActionMode$CallbackC5300vj1 b;
    public ObjectAnimator c;
    public boolean d;
    public float e;
    public final Context f;
    public final C3802mv1 g;

    public G2(Context context, C3802mv1 mv1, ActionMode$CallbackC5300vj1 vj1) {
        this.g = mv1;
        this.f = context;
        this.b = vj1;
        vj1.f11492a = this;
        this.e = context.getResources().getDimension(R.dimen.f25730_resource_name_obfuscated_RES_2131166192);
    }

    public final int a() {
        AbstractC1772b2 b2Var = this.g.f10460a;
        if (b2Var != null) {
            return ((Ty1) b2Var).f.getHeight();
        }
        TypedArray obtainStyledAttributes = this.f.obtainStyledAttributes(new int[]{R.attr.f1330_resource_name_obfuscated_RES_2130968579});
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(0, 0);
        obtainStyledAttributes.recycle();
        return dimensionPixelSize;
    }

    public void b() {
        if (this.d) {
            ObjectAnimator objectAnimator = this.c;
            if (objectAnimator != null) {
                objectAnimator.cancel();
            }
            ObjectAnimator duration = ObjectAnimator.ofInt(this.g, f8057a, 0).setDuration(200L);
            this.c = duration;
            duration.addListener(new F2(this));
            this.c.start();
            this.d = false;
        }
    }

    public void c() {
        ObjectAnimator objectAnimator = this.c;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
        ObjectAnimator duration = ObjectAnimator.ofInt(this.g, f8057a, (int) Math.max(0.0f, ((float) a()) - this.e)).setDuration(200L);
        this.c = duration;
        duration.addListener(new D2(this));
        this.c.addUpdateListener(new E2(this));
        this.g.c.setVisibility(0);
        this.c.start();
        this.d = true;
    }
}
