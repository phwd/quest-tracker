package defpackage;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Build;
import android.os.Bundle;

/* renamed from: ZL0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ZL0 extends Fragment {
    public static void b(Activity activity, EnumC3157j80 j80) {
        if (activity instanceof AbstractC4524r80) {
            AbstractC3499l80 Q = ((AbstractC4524r80) activity).Q();
            if (Q instanceof C4865t80) {
                ((C4865t80) Q).e(j80);
            }
        }
    }

    public static void c(Activity activity) {
        if (Build.VERSION.SDK_INT >= 29) {
            activity.registerActivityLifecycleCallbacks(new YL0());
        }
        FragmentManager fragmentManager = activity.getFragmentManager();
        if (fragmentManager.findFragmentByTag("androidx.lifecycle.LifecycleDispatcher.report_fragment_tag") == null) {
            fragmentManager.beginTransaction().add(new ZL0(), "androidx.lifecycle.LifecycleDispatcher.report_fragment_tag").commit();
            fragmentManager.executePendingTransactions();
        }
    }

    public final void a(EnumC3157j80 j80) {
        if (Build.VERSION.SDK_INT < 29) {
            b(getActivity(), j80);
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        a(EnumC3157j80.ON_CREATE);
    }

    public void onDestroy() {
        super.onDestroy();
        a(EnumC3157j80.ON_DESTROY);
    }

    public void onPause() {
        super.onPause();
        a(EnumC3157j80.ON_PAUSE);
    }

    public void onResume() {
        super.onResume();
        a(EnumC3157j80.ON_RESUME);
    }

    public void onStart() {
        super.onStart();
        a(EnumC3157j80.ON_START);
    }

    public void onStop() {
        super.onStop();
        a(EnumC3157j80.ON_STOP);
    }
}
