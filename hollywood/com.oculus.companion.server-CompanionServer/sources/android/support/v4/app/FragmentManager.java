package android.support.v4.app;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

public abstract class FragmentManager {

    public interface BackStackEntry {
    }

    public static abstract class FragmentLifecycleCallbacks {
        public abstract void onFragmentActivityCreated(FragmentManager fragmentManager, Fragment fragment, Bundle bundle);

        public abstract void onFragmentAttached(FragmentManager fragmentManager, Fragment fragment, Context context);

        public abstract void onFragmentCreated(FragmentManager fragmentManager, Fragment fragment, Bundle bundle);

        public abstract void onFragmentDestroyed(FragmentManager fragmentManager, Fragment fragment);

        public abstract void onFragmentDetached(FragmentManager fragmentManager, Fragment fragment);

        public abstract void onFragmentPaused(FragmentManager fragmentManager, Fragment fragment);

        public abstract void onFragmentPreAttached(FragmentManager fragmentManager, Fragment fragment, Context context);

        public abstract void onFragmentPreCreated(FragmentManager fragmentManager, Fragment fragment, Bundle bundle);

        public abstract void onFragmentResumed(FragmentManager fragmentManager, Fragment fragment);

        public abstract void onFragmentSaveInstanceState(FragmentManager fragmentManager, Fragment fragment, Bundle bundle);

        public abstract void onFragmentStarted(FragmentManager fragmentManager, Fragment fragment);

        public abstract void onFragmentStopped(FragmentManager fragmentManager, Fragment fragment);

        public abstract void onFragmentViewCreated(FragmentManager fragmentManager, Fragment fragment, View view, Bundle bundle);

        public abstract void onFragmentViewDestroyed(FragmentManager fragmentManager, Fragment fragment);
    }

    public interface OnBackStackChangedListener {
        void onBackStackChanged();
    }

    public abstract FragmentTransaction beginTransaction();

    public abstract void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);

    public abstract boolean executePendingTransactions();

    public abstract Fragment findFragmentByTag(String str);

    public abstract List<Fragment> getFragments();

    public abstract boolean isStateSaved();

    public abstract boolean popBackStackImmediate();
}
