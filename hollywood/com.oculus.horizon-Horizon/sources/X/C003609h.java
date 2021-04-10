package X;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentState;

/* renamed from: X.09h  reason: invalid class name and case insensitive filesystem */
public final class C003609h {
    public int A00 = -1;
    @NonNull
    public final Fragment A01;
    public final AnonymousClass09T A02;

    public final void A00(@NonNull ClassLoader classLoader) {
        boolean z;
        Fragment fragment = this.A01;
        Bundle bundle = fragment.mSavedFragmentState;
        if (bundle != null) {
            bundle.setClassLoader(classLoader);
            fragment.mSavedViewState = fragment.mSavedFragmentState.getSparseParcelableArray("android:view_state");
            String string = fragment.mSavedFragmentState.getString("android:target_state");
            fragment.mTargetWho = string;
            if (string != null) {
                fragment.mTargetRequestCode = fragment.mSavedFragmentState.getInt("android:target_req_state", 0);
            }
            Boolean bool = fragment.mSavedUserVisibleHint;
            if (bool != null) {
                z = bool.booleanValue();
                fragment.mUserVisibleHint = z;
                fragment.mSavedUserVisibleHint = null;
            } else {
                z = fragment.mSavedFragmentState.getBoolean("android:user_visible_hint", true);
                fragment.mUserVisibleHint = z;
            }
            if (!z) {
                fragment.mDeferStart = true;
            }
        }
    }

    public C003609h(@NonNull AnonymousClass09T r2, @NonNull Fragment fragment) {
        this.A02 = r2;
        this.A01 = fragment;
    }

    public C003609h(@NonNull AnonymousClass09T r3, @NonNull Fragment fragment, @NonNull FragmentState fragmentState) {
        String str;
        this.A02 = r3;
        this.A01 = fragment;
        fragment.mSavedViewState = null;
        fragment.mBackStackNesting = 0;
        fragment.mInLayout = false;
        fragment.mAdded = false;
        Fragment fragment2 = fragment.mTarget;
        if (fragment2 != null) {
            str = fragment2.mWho;
        } else {
            str = null;
        }
        fragment.mTargetWho = str;
        fragment.mTarget = null;
        Bundle bundle = fragmentState.A00;
        fragment.mSavedFragmentState = bundle == null ? new Bundle() : bundle;
    }

    public C003609h(@NonNull AnonymousClass09T r4, @NonNull ClassLoader classLoader, @NonNull AnonymousClass09Q r6, @NonNull FragmentState fragmentState) {
        this.A02 = r4;
        this.A01 = r6.A01(classLoader, fragmentState.A05);
        Bundle bundle = fragmentState.A04;
        if (bundle != null) {
            bundle.setClassLoader(classLoader);
        }
        Fragment fragment = this.A01;
        fragment.setArguments(bundle);
        fragment.mWho = fragmentState.A07;
        fragment.mFromLayout = fragmentState.A09;
        fragment.mRestored = true;
        fragment.mFragmentId = fragmentState.A02;
        fragment.mContainerId = fragmentState.A01;
        fragment.mTag = fragmentState.A06;
        fragment.mRetainInstance = fragmentState.A0C;
        fragment.mRemoving = fragmentState.A0B;
        fragment.mDetached = fragmentState.A08;
        fragment.mHidden = fragmentState.A0A;
        fragment.mMaxState = AnonymousClass0AO.values()[fragmentState.A03];
        Bundle bundle2 = fragmentState.A00;
        if (bundle2 != null) {
            this.A01.mSavedFragmentState = bundle2;
            return;
        }
        this.A01.mSavedFragmentState = new Bundle();
    }
}
