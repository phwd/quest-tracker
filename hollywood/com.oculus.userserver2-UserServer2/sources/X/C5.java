package X;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.lifecycle.ReportFragment$LifecycleCallbacks;

@RestrictTo({AnonymousClass2O.LIBRARY_GROUP_PREFIX})
public final class C5 extends Fragment {
    public static void A00(Activity activity) {
        if (Build.VERSION.SDK_INT >= 29) {
            activity.registerActivityLifecycleCallbacks(new ReportFragment$LifecycleCallbacks());
        }
        FragmentManager fragmentManager = activity.getFragmentManager();
        if (fragmentManager.findFragmentByTag("androidx.lifecycle.LifecycleDispatcher.report_fragment_tag") == null) {
            fragmentManager.beginTransaction().add(new C5(), "androidx.lifecycle.LifecycleDispatcher.report_fragment_tag").commit();
            fragmentManager.executePendingTransactions();
        }
    }

    public static void A01(@NonNull Activity activity, @NonNull EnumC0039Bo bo) {
        if (activity instanceof Bs) {
            AbstractC0041Bq lifecycle = ((Bs) activity).getLifecycle();
            if (lifecycle instanceof Tc) {
                ((Tc) lifecycle).A06(bo);
            }
        }
    }

    private void A02(@NonNull EnumC0039Bo bo) {
        if (Build.VERSION.SDK_INT < 29) {
            A01(getActivity(), bo);
        }
    }

    public final void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        A02(EnumC0039Bo.ON_CREATE);
    }

    public final void onDestroy() {
        super.onDestroy();
        A02(EnumC0039Bo.ON_DESTROY);
    }

    public final void onPause() {
        super.onPause();
        A02(EnumC0039Bo.ON_PAUSE);
    }

    public final void onResume() {
        super.onResume();
        A02(EnumC0039Bo.ON_RESUME);
    }

    public final void onStart() {
        super.onStart();
        A02(EnumC0039Bo.ON_START);
    }

    public final void onStop() {
        super.onStop();
        A02(EnumC0039Bo.ON_STOP);
    }
}
