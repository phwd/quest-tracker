package com.oculus.common.socialtablet.config;

import X.AnonymousClass1g0;
import X.C05700wg;
import X.C07651cn;
import X.C08381eW;
import X.C08411eb;
import X.ComponentCallbacks2C07631cl;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.FragmentActivity;
import com.bumptech.glide.GeneratedAppGlideModule;
import java.io.File;
import java.util.Iterator;
import java.util.List;

public final class GlideApp {
    @NonNull
    public static ComponentCallbacks2C07631cl get(@NonNull Context context) {
        return ComponentCallbacks2C07631cl.A01(context);
    }

    @SuppressLint({"VisibleForTests"})
    @VisibleForTesting
    public static void tearDown() {
        ComponentCallbacks2C07631cl.A04();
    }

    @Nullable
    public static File getPhotoCacheDir(@NonNull Context context) {
        return ComponentCallbacks2C07631cl.A03(context, "image_manager_disk_cache");
    }

    @Nullable
    public static File getPhotoCacheDir(@NonNull Context context, @NonNull String str) {
        return ComponentCallbacks2C07631cl.A03(context, str);
    }

    @SuppressLint({"VisibleForTests"})
    @VisibleForTesting
    public static void init(@NonNull Context context, @NonNull C07651cn r4) {
        GeneratedAppGlideModule A00 = ComponentCallbacks2C07631cl.A00(context);
        synchronized (ComponentCallbacks2C07631cl.class) {
            if (ComponentCallbacks2C07631cl.A0A != null) {
                ComponentCallbacks2C07631cl.A04();
            }
            ComponentCallbacks2C07631cl.A05(context, r4, A00);
        }
    }

    @SuppressLint({"VisibleForTests"})
    @VisibleForTesting
    @Deprecated
    public static void init(ComponentCallbacks2C07631cl r2) {
        synchronized (ComponentCallbacks2C07631cl.class) {
            if (ComponentCallbacks2C07631cl.A0A != null) {
                ComponentCallbacks2C07631cl.A04();
            }
            ComponentCallbacks2C07631cl.A0A = r2;
        }
    }

    @NonNull
    public static GlideRequests with(@NonNull Activity activity) {
        return (GlideRequests) ComponentCallbacks2C07631cl.A02(activity).A04(activity);
    }

    @NonNull
    @Deprecated
    public static GlideRequests with(@NonNull Fragment fragment) {
        return (GlideRequests) ComponentCallbacks2C07631cl.A02(fragment.getActivity()).A05(fragment);
    }

    @NonNull
    public static GlideRequests with(@NonNull Context context) {
        return (GlideRequests) ComponentCallbacks2C07631cl.A02(context).A06(context);
    }

    @NonNull
    public static GlideRequests with(@NonNull View view) {
        AnonymousClass1g0 r0;
        Context context = view.getContext();
        C08411eb A02 = ComponentCallbacks2C07631cl.A02(context);
        if (!C08381eW.A05()) {
            if (context != null) {
                Activity A00 = C08411eb.A00(context);
                if (A00 != null) {
                    if (A00 instanceof FragmentActivity) {
                        FragmentActivity fragmentActivity = (FragmentActivity) A00;
                        C05700wg<View, androidx.fragment.app.Fragment> r3 = A02.A04;
                        r3.clear();
                        List<androidx.fragment.app.Fragment> A01 = fragmentActivity.getSupportFragmentManager().A0O.A01();
                        if (A01 != null) {
                            Iterator<androidx.fragment.app.Fragment> it = A01.iterator();
                            while (it.hasNext()) {
                                it.next();
                            }
                        }
                        View findViewById = fragmentActivity.findViewById(16908290);
                        androidx.fragment.app.Fragment fragment = null;
                        while (!view.equals(findViewById) && (fragment = r3.get(view)) == null && (view.getParent() instanceof View)) {
                            view = (View) view.getParent();
                        }
                        r3.clear();
                        if (fragment != null) {
                            r0 = A02.A07(fragment);
                        } else {
                            r0 = A02.A08(fragmentActivity);
                        }
                    } else {
                        C05700wg<View, Fragment> r32 = A02.A03;
                        r32.clear();
                        C08411eb.A03(A02, A00.getFragmentManager(), r32);
                        View findViewById2 = A00.findViewById(16908290);
                        Fragment fragment2 = null;
                        while (!view.equals(findViewById2) && (fragment2 = r32.get(view)) == null && (view.getParent() instanceof View)) {
                            view = (View) view.getParent();
                        }
                        r32.clear();
                        if (fragment2 == null) {
                            r0 = A02.A04(A00);
                        } else {
                            r0 = A02.A05(fragment2);
                        }
                    }
                    return (GlideRequests) r0;
                }
            } else {
                throw new NullPointerException("Unable to obtain a request manager for a view without a Context");
            }
        }
        r0 = A02.A06(context.getApplicationContext());
        return (GlideRequests) r0;
    }

    @NonNull
    public static GlideRequests with(@NonNull androidx.fragment.app.Fragment fragment) {
        return (GlideRequests) ComponentCallbacks2C07631cl.A02(fragment.A02()).A07(fragment);
    }

    @NonNull
    public static GlideRequests with(@NonNull FragmentActivity fragmentActivity) {
        return (GlideRequests) ComponentCallbacks2C07631cl.A02(fragmentActivity).A08(fragmentActivity);
    }
}
