package com.facebook.stetho.common.android;

import android.app.Dialog;
import android.content.res.Resources;
import android.view.View;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.facebook.stetho.common.android.FragmentCompat;
import javax.annotation.Nullable;

final class FragmentCompatSupportLib extends FragmentCompat<Fragment, DialogFragment, FragmentManager, FragmentActivity> {
    private static final DialogFragmentAccessorSupportLib sDialogFragmentAccessor = new DialogFragmentAccessorSupportLib();
    private static final FragmentAccessorSupportLib sFragmentAccessor = new FragmentAccessorSupportLib();
    private static final FragmentActivityAccessorSupportLib sFragmentActivityAccessor = new FragmentActivityAccessorSupportLib();
    private static final FragmentCompat.FragmentManagerAccessorViaReflection<FragmentManager, Fragment> sFragmentManagerAccessor = new FragmentCompat.FragmentManagerAccessorViaReflection<>();

    FragmentCompatSupportLib() {
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
    public Class<FragmentActivity> getFragmentActivityClass() {
        return FragmentActivity.class;
    }

    /* Return type fixed from 'com.facebook.stetho.common.android.FragmentCompatSupportLib$FragmentAccessorSupportLib' to match base method */
    @Override // com.facebook.stetho.common.android.FragmentCompat
    public FragmentAccessor<Fragment, FragmentManager> forFragment() {
        return sFragmentAccessor;
    }

    /* Return type fixed from 'com.facebook.stetho.common.android.FragmentCompatSupportLib$DialogFragmentAccessorSupportLib' to match base method */
    @Override // com.facebook.stetho.common.android.FragmentCompat
    public DialogFragmentAccessor<DialogFragment, Fragment, FragmentManager> forDialogFragment() {
        return sDialogFragmentAccessor;
    }

    @Override // com.facebook.stetho.common.android.FragmentCompat
    public FragmentManagerAccessor<FragmentManager, Fragment> forFragmentManager() {
        return sFragmentManagerAccessor;
    }

    /* Return type fixed from 'com.facebook.stetho.common.android.FragmentCompatSupportLib$FragmentActivityAccessorSupportLib' to match base method */
    @Override // com.facebook.stetho.common.android.FragmentCompat
    public FragmentActivityAccessor<FragmentActivity, FragmentManager> forFragmentActivity() {
        return sFragmentActivityAccessor;
    }

    /* access modifiers changed from: private */
    public static class FragmentAccessorSupportLib implements FragmentAccessor<Fragment, FragmentManager> {
        private FragmentAccessorSupportLib() {
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

        @Nullable
        public FragmentManager getChildFragmentManager(Fragment fragment) {
            return fragment.getChildFragmentManager();
        }
    }

    /* access modifiers changed from: private */
    public static class DialogFragmentAccessorSupportLib extends FragmentAccessorSupportLib implements DialogFragmentAccessor<DialogFragment, Fragment, FragmentManager> {
        private DialogFragmentAccessorSupportLib() {
            super();
        }

        public Dialog getDialog(DialogFragment dialogFragment) {
            return dialogFragment.getDialog();
        }
    }

    /* access modifiers changed from: private */
    public static class FragmentActivityAccessorSupportLib implements FragmentActivityAccessor<FragmentActivity, FragmentManager> {
        private FragmentActivityAccessorSupportLib() {
        }

        @Nullable
        public FragmentManager getFragmentManager(FragmentActivity fragmentActivity) {
            return fragmentActivity.getSupportFragmentManager();
        }
    }
}
