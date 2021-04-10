package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import java.util.ArrayList;
import java.util.Objects;

/* renamed from: I6  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class I6 extends AnimatorListenerAdapter {
    public final /* synthetic */ L6 F;

    public I6(L6 l6) {
        this.F = l6;
    }

    public void onAnimationEnd(Animator animator) {
        ArrayList arrayList = new ArrayList(this.F.K);
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ((D6) arrayList.get(i)).a(this.F);
        }
    }

    public void onAnimationStart(Animator animator) {
        ArrayList arrayList = new ArrayList(this.F.K);
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            Objects.requireNonNull((D6) arrayList.get(i));
        }
    }
}
