package com.facebook.internal;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;

public class FragmentWrapper {
    public Fragment nativeFragment;
    public androidx.fragment.app.Fragment supportFragment;

    public final Activity getActivity() {
        androidx.fragment.app.Fragment fragment = this.supportFragment;
        if (fragment != null) {
            return fragment.getActivity();
        }
        return this.nativeFragment.getActivity();
    }

    public void startActivityForResult(Intent intent, int i) {
        androidx.fragment.app.Fragment fragment = this.supportFragment;
        if (fragment != null) {
            fragment.startActivityForResult(intent, i);
        } else {
            this.nativeFragment.startActivityForResult(intent, i);
        }
    }

    public Fragment getNativeFragment() {
        return this.nativeFragment;
    }

    public androidx.fragment.app.Fragment getSupportFragment() {
        return this.supportFragment;
    }

    public FragmentWrapper(Fragment fragment) {
        Validate.notNull(fragment, "fragment");
        this.nativeFragment = fragment;
    }

    public FragmentWrapper(androidx.fragment.app.Fragment fragment) {
        Validate.notNull(fragment, "fragment");
        this.supportFragment = fragment;
    }
}
