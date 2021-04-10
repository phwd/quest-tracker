package X;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.lifecycle.ReportFragment$LifecycleCallbacks;

@RestrictTo({AnonymousClass2C.LIBRARY_GROUP_PREFIX})
public final class Ad extends Fragment {
    public static void A00(Activity activity) {
        if (Build.VERSION.SDK_INT >= 29) {
            activity.registerActivityLifecycleCallbacks(new ReportFragment$LifecycleCallbacks());
        }
        FragmentManager fragmentManager = activity.getFragmentManager();
        if (fragmentManager.findFragmentByTag("androidx.lifecycle.LifecycleDispatcher.report_fragment_tag") == null) {
            fragmentManager.beginTransaction().add(new Ad(), "androidx.lifecycle.LifecycleDispatcher.report_fragment_tag").commit();
            fragmentManager.executePendingTransactions();
        }
    }

    public static void A01(@NonNull Activity activity, @NonNull AN an) {
        if (activity instanceof AR) {
            AP lifecycle = ((AR) activity).getLifecycle();
            if (lifecycle instanceof Zw) {
                ((Zw) lifecycle).A08(an);
            }
        }
    }

    private void A02(@NonNull AN an) {
        if (Build.VERSION.SDK_INT < 29) {
            A01(getActivity(), an);
        }
    }

    public final void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        A02(AN.ON_CREATE);
    }

    public final void onDestroy() {
        super.onDestroy();
        A02(AN.ON_DESTROY);
    }

    public final void onPause() {
        super.onPause();
        A02(AN.ON_PAUSE);
    }

    public final void onResume() {
        super.onResume();
        A02(AN.ON_RESUME);
    }

    public final void onStart() {
        super.onStart();
        A02(AN.ON_START);
    }

    public final void onStop() {
        super.onStop();
        A02(AN.ON_STOP);
    }
}
