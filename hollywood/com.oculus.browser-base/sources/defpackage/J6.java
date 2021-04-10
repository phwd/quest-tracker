package defpackage;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import java.util.ArrayList;

/* renamed from: J6  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class J6 extends Drawable.ConstantState {

    /* renamed from: a  reason: collision with root package name */
    public int f8269a;
    public Fs1 b;
    public AnimatorSet c;
    public ArrayList d;
    public C4931ta e;

    public J6(J6 j6, Drawable.Callback callback, Resources resources) {
        if (j6 != null) {
            this.f8269a = j6.f8269a;
            Fs1 fs1 = j6.b;
            if (fs1 != null) {
                Drawable.ConstantState constantState = fs1.getConstantState();
                if (resources != null) {
                    this.b = (Fs1) constantState.newDrawable(resources);
                } else {
                    this.b = (Fs1) constantState.newDrawable();
                }
                Fs1 fs12 = this.b;
                fs12.mutate();
                this.b = fs12;
                fs12.setCallback(callback);
                this.b.setBounds(j6.b.getBounds());
                this.b.L = false;
            }
            ArrayList arrayList = j6.d;
            if (arrayList != null) {
                int size = arrayList.size();
                this.d = new ArrayList(size);
                this.e = new C4931ta(size);
                for (int i = 0; i < size; i++) {
                    Animator animator = (Animator) j6.d.get(i);
                    Animator clone = animator.clone();
                    String str = (String) j6.e.getOrDefault(animator, null);
                    clone.setTarget(this.b.H.b.q.getOrDefault(str, null));
                    this.d.add(clone);
                    this.e.put(clone, str);
                }
                if (this.c == null) {
                    this.c = new AnimatorSet();
                }
                this.c.playTogether(this.d);
            }
        }
    }

    public int getChangingConfigurations() {
        return this.f8269a;
    }

    public Drawable newDrawable() {
        throw new IllegalStateException("No constant state support for SDK < 24.");
    }

    public Drawable newDrawable(Resources resources) {
        throw new IllegalStateException("No constant state support for SDK < 24.");
    }
}
