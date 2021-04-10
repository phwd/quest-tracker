package com.facebook.stetho.common.android;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Resources;
import android.os.Build;
import android.view.View;
import com.facebook.stetho.common.android.FragmentCompat;
import javax.annotation.Nullable;

@TargetApi(11)
final class FragmentCompatFramework extends FragmentCompat<Fragment, DialogFragment, FragmentManager, Activity> {
    private static final DialogFragmentAccessorFramework sDialogFragmentAccessor = new DialogFragmentAccessorFramework(sFragmentAccessor);
    private static final FragmentAccessorFrameworkHoneycomb sFragmentAccessor;
    private static final FragmentActivityAccessorFramework sFragmentActivityAccessor = new FragmentActivityAccessorFramework();
    private static final FragmentCompat.FragmentManagerAccessorViaReflection<FragmentManager, Fragment> sFragmentManagerAccessor = new FragmentCompat.FragmentManagerAccessorViaReflection<>();

    FragmentCompatFramework() {
    }

    static {
        if (Build.VERSION.SDK_INT >= 17) {
            sFragmentAccessor = new FragmentAccessorFrameworkJellyBean();
        } else {
            sFragmentAccessor = new FragmentAccessorFrameworkHoneycomb();
        }
    }

    @Override // com.facebook.stetho.common.android.FragmentCompat
    public Class<Fragment> getFragmentClass() {
        return Fragment.class;
    }

    @Override // com.facebook.stetho.common.android.FragmentCompat
    public Class<DialogFragment> getDialogFragmentClass() {
        return DialogFragment.class;
    }

    @Override // com.facebook.stetho.common.android.FragmentCompat
    public Class<Activity> getFragmentActivityClass() {
        return Activity.class;
    }

    /* Return type fixed from 'com.facebook.stetho.common.android.FragmentCompatFramework$FragmentAccessorFrameworkHoneycomb' to match base method */
    @Override // com.facebook.stetho.common.android.FragmentCompat
    public FragmentAccessor<Fragment, FragmentManager> forFragment() {
        return sFragmentAccessor;
    }

    /* Return type fixed from 'com.facebook.stetho.common.android.FragmentCompatFramework$DialogFragmentAccessorFramework' to match base method */
    @Override // com.facebook.stetho.common.android.FragmentCompat
    public DialogFragmentAccessor<DialogFragment, Fragment, FragmentManager> forDialogFragment() {
        return sDialogFragmentAccessor;
    }

    /* Return type fixed from 'com.facebook.stetho.common.android.FragmentCompat$FragmentManagerAccessorViaReflection<android.app.FragmentManager, android.app.Fragment>' to match base method */
    @Override // com.facebook.stetho.common.android.FragmentCompat
    public FragmentManagerAccessor<FragmentManager, Fragment> forFragmentManager() {
        return sFragmentManagerAccessor;
    }

    /* Return type fixed from 'com.facebook.stetho.common.android.FragmentCompatFramework$FragmentActivityAccessorFramework' to match base method */
    @Override // com.facebook.stetho.common.android.FragmentCompat
    public FragmentActivityAccessor<Activity, FragmentManager> forFragmentActivity() {
        return sFragmentActivityAccessor;
    }

    /* access modifiers changed from: private */
    public static class FragmentAccessorFrameworkHoneycomb implements FragmentAccessor<Fragment, FragmentManager> {
        @Nullable
        public FragmentManager getChildFragmentManager(Fragment fragment) {
            return null;
        }

        private FragmentAccessorFrameworkHoneycomb() {
        }

        @Nullable
        public FragmentManager getFragmentManager(Fragment fragment) {
            return fragment.getFragmentManager();
        }

        public Resources getResources(Fragment fragment) {
            return fragment.getResources();
        }

        public int getId(Fragment fragment) {
            return fragment.getId();
        }

        @Nullable
        public String getTag(Fragment fragment) {
            return fragment.getTag();
        }

        @Nullable
        public View getView(Fragment fragment) {
            return fragment.getView();
        }
    }

    @TargetApi(17)
    private static class FragmentAccessorFrameworkJellyBean extends FragmentAccessorFrameworkHoneycomb {
        private FragmentAccessorFrameworkJellyBean() {
            super();
        }

        @Override // com.facebook.stetho.common.android.FragmentCompatFramework.FragmentAccessorFrameworkHoneycomb
        @Nullable
        public FragmentManager getChildFragmentManager(Fragment fragment) {
            return fragment.getChildFragmentManager();
        }
    }

    /* access modifiers changed from: private */
    public static class DialogFragmentAccessorFramework implements DialogFragmentAccessor<DialogFragment, Fragment, FragmentManager> {
        private final FragmentAccessor<Fragment, FragmentManager> mFragmentAccessor;

        public DialogFragmentAccessorFramework(FragmentAccessor<Fragment, FragmentManager> fragmentAccessor) {
            this.mFragmentAccessor = fragmentAccessor;
        }

        public Dialog getDialog(DialogFragment dialogFragment) {
            return dialogFragment.getDialog();
        }

        @Nullable
        public FragmentManager getFragmentManager(Fragment fragment) {
            return this.mFragmentAccessor.getFragmentManager(fragment);
        }

        public Resources getResources(Fragment fragment) {
            return this.mFragmentAccessor.getResources(fragment);
        }

        public int getId(Fragment fragment) {
            return this.mFragmentAccessor.getId(fragment);
        }

        @Nullable
        public String getTag(Fragment fragment) {
            return this.mFragmentAccessor.getTag(fragment);
        }

        @Nullable
        public View getView(Fragment fragment) {
            return this.mFragmentAccessor.getView(fragment);
        }

        @Nullable
        public FragmentManager getChildFragmentManager(Fragment fragment) {
            return this.mFragmentAccessor.getChildFragmentManager(fragment);
        }
    }

    /* access modifiers changed from: private */
    public static class FragmentActivityAccessorFramework implements FragmentActivityAccessor<Activity, FragmentManager> {
        private FragmentActivityAccessorFramework() {
        }

        @Override // com.facebook.stetho.common.android.FragmentActivityAccessor
        @Nullable
        public FragmentManager getFragmentManager(Activity activity) {
            return activity.getFragmentManager();
        }
    }
}
