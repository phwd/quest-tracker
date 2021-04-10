package X;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.lang.ref.WeakReference;
import java.util.Iterator;

/* renamed from: X.1rM  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC11211rM {
    public static final AnonymousClass02j<WeakReference<AbstractC11211rM>> A00 = new AnonymousClass02j<>();
    public static final Object A01 = new Object();

    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0159, code lost:
        if (r1 == false) goto L_0x015b;
     */
    @androidx.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.AbstractC11301rk A0D(@androidx.annotation.NonNull X.AbstractC11461s1 r11) {
        /*
        // Method dump skipped, instructions count: 495
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AbstractC11211rM.A0D(X.1s1):X.1rk");
    }

    public final void A0E() {
        AnonymousClass1rJ r3 = (AnonymousClass1rJ) this;
        AnonymousClass1rJ.A07(r3);
        AbstractC11251rX r2 = r3.A0B;
        if (r2 == null || !(r2 instanceof AnonymousClass1rR)) {
            r3.A00 = (1 << 0) | r3.A00;
            if (!r3.A0X) {
                r3.A08.getDecorView().postOnAnimation(r3.A0m);
                r3.A0X = true;
                return;
            }
            return;
        }
        AnonymousClass1rR r22 = (AnonymousClass1rR) r2;
        ViewGroup A5I = r22.A02.A5I();
        Runnable runnable = r22.A06;
        A5I.removeCallbacks(runnable);
        r22.A02.A5I().postOnAnimation(runnable);
    }

    public final void A0F() {
        AnonymousClass1rJ r3 = (AnonymousClass1rJ) this;
        Object obj = r3.A0l;
        boolean z = obj instanceof Activity;
        if (z) {
            synchronized (A01) {
                A0B(r3);
            }
        }
        if (r3.A0X) {
            r3.A08.getDecorView().removeCallbacks(r3.A0m);
        }
        r3.A0d = false;
        r3.A0Y = true;
        if (r3.A01 == -100 || !z || !((Activity) obj).isChangingConfigurations()) {
            AnonymousClass1rJ.A0n.remove(obj.getClass().getName());
        } else {
            AnonymousClass1rJ.A0n.put(obj.getClass().getName(), Integer.valueOf(r3.A01));
        }
        AbstractC11251rX r2 = r3.A0B;
        if (r2 != null && (r2 instanceof AnonymousClass1rR)) {
            AnonymousClass1rR r22 = (AnonymousClass1rR) r2;
            r22.A02.A5I().removeCallbacks(r22.A06);
        }
        AbstractC11341rp r0 = r3.A0E;
        if (r0 != null) {
            r0.A02();
        }
        AbstractC11341rp r02 = r3.A0D;
        if (r02 != null) {
            r02.A02();
        }
    }

    public final void A0G() {
        AnonymousClass1rJ r0 = (AnonymousClass1rJ) this;
        r0.A0d = false;
        AnonymousClass1rJ.A07(r0);
        AbstractC11251rX r1 = r0.A0B;
        if (r1 != null && (r1 instanceof C11201rK)) {
            C11201rK r12 = (C11201rK) r1;
            r12.A0I = false;
            AnonymousClass1rT r02 = r12.A07;
            if (r02 != null) {
                r02.A00();
            }
        }
    }

    public final void A0H(@LayoutRes int i) {
        AnonymousClass1rJ r2 = (AnonymousClass1rJ) this;
        AnonymousClass1rJ.A05(r2);
        ViewGroup viewGroup = (ViewGroup) r2.A07.findViewById(16908290);
        viewGroup.removeAllViews();
        LayoutInflater.from(r2.A0j).inflate(i, viewGroup);
        ((Window$CallbackC11241rW) r2.A0C).A00.onContentChanged();
    }

    public final void A0I(Bundle bundle) {
        AnonymousClass1rJ r4 = (AnonymousClass1rJ) this;
        r4.A0Q = true;
        AnonymousClass1rJ.A09(r4, false);
        AnonymousClass1rJ.A06(r4);
        Object obj = r4.A0l;
        if (obj instanceof Activity) {
            String str = null;
            try {
                Activity activity = (Activity) obj;
                try {
                    str = C000703c.A00(activity, activity.getComponentName());
                } catch (PackageManager.NameNotFoundException e) {
                    throw new IllegalArgumentException(e);
                }
            } catch (IllegalArgumentException unused) {
            }
            if (str != null) {
                AbstractC11251rX r0 = r4.A0B;
                if (r0 == null) {
                    r4.A0S = true;
                } else {
                    r0.A05(true);
                }
            }
            synchronized (A01) {
                A0B(r4);
                A00.add(new WeakReference<>(r4));
            }
        }
        r4.A0R = true;
    }

    public final void A0J(View view) {
        AnonymousClass1rJ r2 = (AnonymousClass1rJ) this;
        AnonymousClass1rJ.A05(r2);
        ViewGroup viewGroup = (ViewGroup) r2.A07.findViewById(16908290);
        viewGroup.removeAllViews();
        viewGroup.addView(view);
        ((Window$CallbackC11241rW) r2.A0C).A00.onContentChanged();
    }

    public final void A0K(View view, ViewGroup.LayoutParams layoutParams) {
        AnonymousClass1rJ r2 = (AnonymousClass1rJ) this;
        AnonymousClass1rJ.A05(r2);
        ViewGroup viewGroup = (ViewGroup) r2.A07.findViewById(16908290);
        viewGroup.removeAllViews();
        viewGroup.addView(view, layoutParams);
        ((Window$CallbackC11241rW) r2.A0C).A00.onContentChanged();
    }

    public final void A0L(@Nullable CharSequence charSequence) {
        AbstractC06001Eq r0;
        AnonymousClass1rJ r2 = (AnonymousClass1rJ) this;
        r2.A0M = charSequence;
        AnonymousClass1rl r02 = r2.A0K;
        if (r02 != null) {
            r02.setWindowTitle(charSequence);
            return;
        }
        AbstractC11251rX r1 = r2.A0B;
        if (r1 != null) {
            if (r1 instanceof C11201rK) {
                r0 = ((C11201rK) r1).A0B;
            } else if (r1 instanceof AnonymousClass1rR) {
                r0 = ((AnonymousClass1rR) r1).A02;
            } else {
                return;
            }
            r0.setWindowTitle(charSequence);
            return;
        }
        TextView textView = r2.A0A;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    public final boolean A0M(int i) {
        AnonymousClass1rJ r4 = (AnonymousClass1rJ) this;
        if (i == 8) {
            i = 108;
        } else if (i == 9) {
            i = 109;
        }
        if (r4.A0f && i == 108) {
            return false;
        }
        if (r4.A0W && i == 1) {
            r4.A0W = false;
        } else if (i != 1) {
            if (i == 2) {
                AnonymousClass1rJ.A08(r4);
                r4.A0U = true;
                return true;
            } else if (i == 5) {
                AnonymousClass1rJ.A08(r4);
                r4.A0T = true;
                return true;
            } else if (i == 10) {
                AnonymousClass1rJ.A08(r4);
                r4.A0c = true;
                return true;
            } else if (i == 108) {
                AnonymousClass1rJ.A08(r4);
                r4.A0W = true;
                return true;
            } else if (i != 109) {
                return r4.A08.requestFeature(i);
            } else {
                AnonymousClass1rJ.A08(r4);
                r4.A0b = true;
                return true;
            }
        }
        AnonymousClass1rJ.A08(r4);
        r4.A0f = true;
        return true;
    }

    public static void A0B(@NonNull AbstractC11211rM r3) {
        synchronized (A01) {
            Iterator<WeakReference<AbstractC11211rM>> it = A00.iterator();
            while (it.hasNext()) {
                AbstractC11211rM r0 = it.next().get();
                if (r0 == r3 || r0 == null) {
                    it.remove();
                }
            }
        }
    }

    @NonNull
    @CallSuper
    public Context A0C(@NonNull Context context) {
        return context;
    }
}
