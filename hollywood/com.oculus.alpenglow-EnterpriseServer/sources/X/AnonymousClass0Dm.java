package X;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.lifecycle.ReportFragment$LifecycleCallbacks;

@RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
/* renamed from: X.0Dm  reason: invalid class name */
public final class AnonymousClass0Dm extends Fragment {
    public static void A00(Activity activity) {
        if (Build.VERSION.SDK_INT >= 29) {
            activity.registerActivityLifecycleCallbacks(new ReportFragment$LifecycleCallbacks());
        }
        FragmentManager fragmentManager = activity.getFragmentManager();
        if (fragmentManager.findFragmentByTag("androidx.lifecycle.LifecycleDispatcher.report_fragment_tag") == null) {
            fragmentManager.beginTransaction().add(new AnonymousClass0Dm(), "androidx.lifecycle.LifecycleDispatcher.report_fragment_tag").commit();
            fragmentManager.executePendingTransactions();
        }
    }

    public static void A01(@NonNull Activity activity, @NonNull AnonymousClass0DW r2) {
        if (activity instanceof AbstractC01030Da) {
            AnonymousClass0DY lifecycle = ((AbstractC01030Da) activity).getLifecycle();
            if (lifecycle instanceof C03540cc) {
                ((C03540cc) lifecycle).A08(r2);
            }
        }
    }

    private void A02(@NonNull AnonymousClass0DW r3) {
        if (Build.VERSION.SDK_INT < 29) {
            A01(getActivity(), r3);
        }
    }

    public final void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        A02(AnonymousClass0DW.ON_CREATE);
    }

    public final void onDestroy() {
        super.onDestroy();
        A02(AnonymousClass0DW.ON_DESTROY);
    }

    public final void onPause() {
        super.onPause();
        A02(AnonymousClass0DW.ON_PAUSE);
    }

    public final void onResume() {
        super.onResume();
        A02(AnonymousClass0DW.ON_RESUME);
    }

    public final void onStart() {
        super.onStart();
        A02(AnonymousClass0DW.ON_START);
    }

    public final void onStop() {
        super.onStop();
        A02(AnonymousClass0DW.ON_STOP);
    }
}
