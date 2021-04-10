package X;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.FragmentActivity;
import com.oculus.modules.PanelCookiesModuleImpl;
import java.util.HashMap;
import java.util.Map;

/* renamed from: X.1eb  reason: invalid class name and case insensitive filesystem */
public final class C08411eb implements Handler.Callback {
    public static final AbstractC08511el A08 = new C08491ej();
    @VisibleForTesting
    public final Map<FragmentManager, FragmentC08421ec> A00 = new HashMap();
    @VisibleForTesting
    public final Map<AnonymousClass09b, C08431ed> A01 = new HashMap();
    public final Bundle A02 = new Bundle();
    public final C05700wg<View, Fragment> A03 = new C05700wg<>();
    public final C05700wg<View, androidx.fragment.app.Fragment> A04 = new C05700wg<>();
    public final AbstractC08511el A05;
    public final Handler A06;
    public volatile AnonymousClass1g0 A07;

    @Nullable
    public static Activity A00(@NonNull Context context) {
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (context instanceof ContextWrapper) {
            return A00(((ContextWrapper) context).getBaseContext());
        }
        return null;
    }

    @NonNull
    public static FragmentC08421ec A01(@NonNull C08411eb r4, @Nullable FragmentManager fragmentManager, Fragment fragment, boolean z) {
        Map<FragmentManager, FragmentC08421ec> map;
        FragmentC08421ec r2 = (FragmentC08421ec) fragmentManager.findFragmentByTag("com.bumptech.glide.manager");
        if (r2 == null && (r2 = (map = r4.A00).get(fragmentManager)) == null) {
            r2 = new FragmentC08421ec();
            r2.A00 = fragment;
            if (!(fragment == null || fragment.getActivity() == null)) {
                FragmentC08421ec.A00(r2, fragment.getActivity());
            }
            if (z) {
                r2.A03.A01();
            }
            map.put(fragmentManager, r2);
            fragmentManager.beginTransaction().add(r2, "com.bumptech.glide.manager").commitAllowingStateLoss();
            r4.A06.obtainMessage(1, fragmentManager).sendToTarget();
        }
        return r2;
    }

    @NonNull
    public static C08431ed A02(@NonNull C08411eb r5, @Nullable AnonymousClass09b r6, androidx.fragment.app.Fragment fragment, boolean z) {
        Map<AnonymousClass09b, C08431ed> map;
        Context A022;
        C08431ed r3 = (C08431ed) r6.A0H("com.bumptech.glide.manager");
        if (r3 == null && (r3 = (map = r5.A01).get(r6)) == null) {
            r3 = new C08431ed();
            r3.A00 = fragment;
            if (!(fragment == null || (A022 = fragment.A02()) == null)) {
                while (fragment.A0D != null) {
                    fragment = fragment.A0D;
                }
                AnonymousClass09b r0 = fragment.A0H;
                if (r0 != null) {
                    C08431ed.A01(r3, A022, r0);
                }
            }
            if (z) {
                r3.A03.A01();
            }
            map.put(r6, r3);
            AnonymousClass0vO r2 = new AnonymousClass0vO(r6);
            r2.A02(0, r3, "com.bumptech.glide.manager", 1);
            r2.A00();
            r5.A06.obtainMessage(2, r6).sendToTarget();
        }
        return r3;
    }

    /* JADX WARN: Incorrect args count in method signature: (Landroid/app/FragmentManager;LX/0wg<Landroid/view/View;Landroid/app/Fragment;>;)V */
    @TargetApi(26)
    @Deprecated
    public static void A03(@NonNull C08411eb r4, @NonNull FragmentManager fragmentManager, C05700wg r6) {
        if (Build.VERSION.SDK_INT >= 26) {
            for (Fragment fragment : fragmentManager.getFragments()) {
                if (fragment.getView() != null) {
                    r6.put(fragment.getView(), fragment);
                    A03(r4, fragment.getChildFragmentManager(), r6);
                }
            }
            return;
        }
        int i = 0;
        while (true) {
            Bundle bundle = r4.A02;
            int i2 = i + 1;
            bundle.putInt(PanelCookiesModuleImpl.COOKIE_JSON_KEY, i);
            Fragment fragment2 = null;
            try {
                fragment2 = fragmentManager.getFragment(bundle, PanelCookiesModuleImpl.COOKIE_JSON_KEY);
            } catch (Exception unused) {
            }
            if (fragment2 != null) {
                if (fragment2.getView() != null) {
                    r6.put(fragment2.getView(), fragment2);
                    A03(r4, fragment2.getChildFragmentManager(), r6);
                }
                i = i2;
            } else {
                return;
            }
        }
    }

    @NonNull
    public final AnonymousClass1g0 A06(@NonNull Context context) {
        if (context != null) {
            if (Looper.myLooper() == Looper.getMainLooper() && !(context instanceof Application)) {
                if (context instanceof FragmentActivity) {
                    return A08((FragmentActivity) context);
                }
                if (context instanceof Activity) {
                    return A04((Activity) context);
                }
                if (context instanceof ContextWrapper) {
                    ContextWrapper contextWrapper = (ContextWrapper) context;
                    if (contextWrapper.getBaseContext().getApplicationContext() != null) {
                        return A06(contextWrapper.getBaseContext());
                    }
                }
            }
            if (this.A07 == null) {
                synchronized (this) {
                    if (this.A07 == null) {
                        this.A07 = this.A05.A1l(ComponentCallbacks2C07631cl.A01(context.getApplicationContext()), new C08521em(), new C08481ei(), context.getApplicationContext());
                    }
                }
            }
            return this.A07;
        }
        throw new IllegalArgumentException("You cannot start a load on a null Context");
    }

    public final boolean handleMessage(Message message) {
        Object obj;
        Map map;
        int i = message.what;
        if (i == 1) {
            obj = message.obj;
            map = this.A00;
        } else if (i != 2) {
            return false;
        } else {
            obj = message.obj;
            map = this.A01;
        }
        if (map.remove(obj) != null || !Log.isLoggable("RMRetriever", 5)) {
            return true;
        }
        StringBuilder sb = new StringBuilder("Failed to remove expected request manager fragment, manager: ");
        sb.append(obj);
        Log.w("RMRetriever", sb.toString());
        return true;
    }

    public C08411eb(@Nullable AbstractC08511el r3) {
        this.A05 = r3 == null ? A08 : r3;
        this.A06 = new Handler(Looper.getMainLooper(), this);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0025, code lost:
        if (r1 == false) goto L_0x0027;
     */
    @androidx.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.AnonymousClass1g0 A04(@androidx.annotation.NonNull android.app.Activity r6) {
        /*
            r5 = this;
            boolean r0 = X.C08381eW.A05()
            if (r0 == 0) goto L_0x000f
            android.content.Context r0 = r6.getApplicationContext()
            X.1g0 r0 = r5.A06(r0)
        L_0x000e:
            return r0
        L_0x000f:
            boolean r0 = r6.isDestroyed()
            if (r0 != 0) goto L_0x0041
            android.app.FragmentManager r3 = r6.getFragmentManager()
            r2 = 0
            android.app.Activity r0 = A00(r6)
            if (r0 == 0) goto L_0x0027
            boolean r1 = r0.isFinishing()
            r0 = 0
            if (r1 != 0) goto L_0x0028
        L_0x0027:
            r0 = 1
        L_0x0028:
            X.1ec r4 = A01(r5, r3, r2, r0)
            X.1g0 r0 = r4.A01
            if (r0 != 0) goto L_0x000e
            X.1cl r3 = X.ComponentCallbacks2C07631cl.A01(r6)
            X.1el r2 = r5.A05
            X.1eg r1 = r4.A03
            X.1en r0 = r4.A04
            X.1g0 r0 = r2.A1l(r3, r1, r0, r6)
            r4.A01 = r0
            return r0
        L_0x0041:
            java.lang.String r1 = "You cannot start a load for a destroyed activity"
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C08411eb.A04(android.app.Activity):X.1g0");
    }

    @NonNull
    @TargetApi(17)
    @Deprecated
    public final AnonymousClass1g0 A05(@NonNull Fragment fragment) {
        if (fragment.getActivity() == null) {
            throw new IllegalArgumentException("You cannot start a load on a fragment before it is attached");
        } else if (C08381eW.A05()) {
            return A06(fragment.getActivity().getApplicationContext());
        } else {
            FragmentManager childFragmentManager = fragment.getChildFragmentManager();
            Activity activity = fragment.getActivity();
            FragmentC08421ec A012 = A01(this, childFragmentManager, fragment, fragment.isVisible());
            AnonymousClass1g0 r0 = A012.A01;
            if (r0 != null) {
                return r0;
            }
            AnonymousClass1g0 A1l = this.A05.A1l(ComponentCallbacks2C07631cl.A01(activity), A012.A03, A012.A04, activity);
            A012.A01 = A1l;
            return A1l;
        }
    }

    @NonNull
    public final AnonymousClass1g0 A07(@NonNull androidx.fragment.app.Fragment fragment) {
        if (fragment.A02() == null) {
            throw new NullPointerException("You cannot start a load on a fragment before it is attached or after it is destroyed");
        } else if (C08381eW.A05()) {
            return A06(fragment.A02().getApplicationContext());
        } else {
            AnonymousClass09b A032 = fragment.A03();
            Context A022 = fragment.A02();
            C08431ed A023 = A02(this, A032, fragment, false);
            AnonymousClass1g0 r0 = A023.A01;
            if (r0 != null) {
                return r0;
            }
            AnonymousClass1g0 A1l = this.A05.A1l(ComponentCallbacks2C07631cl.A01(A022), A023.A03, A023.A04, A022);
            A023.A01 = A1l;
            return A1l;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0025, code lost:
        if (r1 == false) goto L_0x0027;
     */
    @androidx.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.AnonymousClass1g0 A08(@androidx.annotation.NonNull androidx.fragment.app.FragmentActivity r6) {
        /*
            r5 = this;
            boolean r0 = X.C08381eW.A05()
            if (r0 == 0) goto L_0x000f
            android.content.Context r0 = r6.getApplicationContext()
            X.1g0 r0 = r5.A06(r0)
        L_0x000e:
            return r0
        L_0x000f:
            boolean r0 = r6.isDestroyed()
            if (r0 != 0) goto L_0x0041
            X.09b r3 = r6.getSupportFragmentManager()
            r2 = 0
            android.app.Activity r0 = A00(r6)
            if (r0 == 0) goto L_0x0027
            boolean r1 = r0.isFinishing()
            r0 = 0
            if (r1 != 0) goto L_0x0028
        L_0x0027:
            r0 = 1
        L_0x0028:
            X.1ed r4 = A02(r5, r3, r2, r0)
            X.1g0 r0 = r4.A01
            if (r0 != 0) goto L_0x000e
            X.1cl r3 = X.ComponentCallbacks2C07631cl.A01(r6)
            X.1el r2 = r5.A05
            X.1eg r1 = r4.A03
            X.1en r0 = r4.A04
            X.1g0 r0 = r2.A1l(r3, r1, r0, r6)
            r4.A01 = r0
            return r0
        L_0x0041:
            java.lang.String r1 = "You cannot start a load for a destroyed activity"
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C08411eb.A08(androidx.fragment.app.FragmentActivity):X.1g0");
    }
}
