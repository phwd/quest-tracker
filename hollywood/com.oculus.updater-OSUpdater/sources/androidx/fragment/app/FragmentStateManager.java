package androidx.fragment.app;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.util.SparseArray;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.R;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelStoreOwner;

/* access modifiers changed from: package-private */
public class FragmentStateManager {
    private final FragmentLifecycleCallbacksDispatcher mDispatcher;
    @NonNull
    private final Fragment mFragment;
    private int mFragmentManagerState = -1;

    FragmentStateManager(@NonNull FragmentLifecycleCallbacksDispatcher fragmentLifecycleCallbacksDispatcher, @NonNull Fragment fragment) {
        this.mDispatcher = fragmentLifecycleCallbacksDispatcher;
        this.mFragment = fragment;
    }

    FragmentStateManager(@NonNull FragmentLifecycleCallbacksDispatcher fragmentLifecycleCallbacksDispatcher, @NonNull ClassLoader classLoader, @NonNull FragmentFactory fragmentFactory, @NonNull FragmentState fragmentState) {
        this.mDispatcher = fragmentLifecycleCallbacksDispatcher;
        this.mFragment = fragmentFactory.instantiate(classLoader, fragmentState.mClassName);
        if (fragmentState.mArguments != null) {
            fragmentState.mArguments.setClassLoader(classLoader);
        }
        this.mFragment.setArguments(fragmentState.mArguments);
        this.mFragment.mWho = fragmentState.mWho;
        this.mFragment.mFromLayout = fragmentState.mFromLayout;
        Fragment fragment = this.mFragment;
        fragment.mRestored = true;
        fragment.mFragmentId = fragmentState.mFragmentId;
        this.mFragment.mContainerId = fragmentState.mContainerId;
        this.mFragment.mTag = fragmentState.mTag;
        this.mFragment.mRetainInstance = fragmentState.mRetainInstance;
        this.mFragment.mRemoving = fragmentState.mRemoving;
        this.mFragment.mDetached = fragmentState.mDetached;
        this.mFragment.mHidden = fragmentState.mHidden;
        this.mFragment.mMaxState = Lifecycle.State.values()[fragmentState.mMaxLifecycleState];
        if (fragmentState.mSavedFragmentState != null) {
            this.mFragment.mSavedFragmentState = fragmentState.mSavedFragmentState;
        } else {
            this.mFragment.mSavedFragmentState = new Bundle();
        }
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "Instantiated fragment " + this.mFragment);
        }
    }

    FragmentStateManager(@NonNull FragmentLifecycleCallbacksDispatcher fragmentLifecycleCallbacksDispatcher, @NonNull Fragment fragment, @NonNull FragmentState fragmentState) {
        this.mDispatcher = fragmentLifecycleCallbacksDispatcher;
        this.mFragment = fragment;
        Fragment fragment2 = this.mFragment;
        fragment2.mSavedViewState = null;
        fragment2.mBackStackNesting = 0;
        fragment2.mInLayout = false;
        fragment2.mAdded = false;
        fragment2.mTargetWho = fragment2.mTarget != null ? this.mFragment.mTarget.mWho : null;
        this.mFragment.mTarget = null;
        if (fragmentState.mSavedFragmentState != null) {
            this.mFragment.mSavedFragmentState = fragmentState.mSavedFragmentState;
        } else {
            this.mFragment.mSavedFragmentState = new Bundle();
        }
    }

    /* access modifiers changed from: package-private */
    @NonNull
    public Fragment getFragment() {
        return this.mFragment;
    }

    /* access modifiers changed from: package-private */
    public void setFragmentManagerState(int i) {
        this.mFragmentManagerState = i;
    }

    /* access modifiers changed from: package-private */
    public int computeMaxState() {
        int i = this.mFragmentManagerState;
        if (this.mFragment.mFromLayout) {
            if (this.mFragment.mInLayout) {
                i = Math.max(this.mFragmentManagerState, 1);
            } else {
                i = Math.min(i, this.mFragment.mState);
            }
        }
        if (!this.mFragment.mAdded) {
            i = Math.min(i, 1);
        }
        if (this.mFragment.mRemoving) {
            if (this.mFragment.isInBackStack()) {
                i = Math.min(i, 1);
            } else {
                i = Math.min(i, -1);
            }
        }
        if (this.mFragment.mDeferStart && this.mFragment.mState < 3) {
            i = Math.min(i, 2);
        }
        int i2 = AnonymousClass1.$SwitchMap$androidx$lifecycle$Lifecycle$State[this.mFragment.mMaxState.ordinal()];
        if (i2 == 1) {
            return i;
        }
        if (i2 == 2) {
            return Math.min(i, 3);
        }
        if (i2 != 3) {
            return Math.min(i, -1);
        }
        return Math.min(i, 1);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: androidx.fragment.app.FragmentStateManager$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$androidx$lifecycle$Lifecycle$State = new int[Lifecycle.State.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            /*
                androidx.lifecycle.Lifecycle$State[] r0 = androidx.lifecycle.Lifecycle.State.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                androidx.fragment.app.FragmentStateManager.AnonymousClass1.$SwitchMap$androidx$lifecycle$Lifecycle$State = r0
                int[] r0 = androidx.fragment.app.FragmentStateManager.AnonymousClass1.$SwitchMap$androidx$lifecycle$Lifecycle$State     // Catch:{ NoSuchFieldError -> 0x0014 }
                androidx.lifecycle.Lifecycle$State r1 = androidx.lifecycle.Lifecycle.State.RESUMED     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = androidx.fragment.app.FragmentStateManager.AnonymousClass1.$SwitchMap$androidx$lifecycle$Lifecycle$State     // Catch:{ NoSuchFieldError -> 0x001f }
                androidx.lifecycle.Lifecycle$State r1 = androidx.lifecycle.Lifecycle.State.STARTED     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = androidx.fragment.app.FragmentStateManager.AnonymousClass1.$SwitchMap$androidx$lifecycle$Lifecycle$State     // Catch:{ NoSuchFieldError -> 0x002a }
                androidx.lifecycle.Lifecycle$State r1 = androidx.lifecycle.Lifecycle.State.CREATED     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.FragmentStateManager.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: package-private */
    public void ensureInflatedView() {
        if (this.mFragment.mFromLayout && !this.mFragment.mPerformedCreateView) {
            if (FragmentManager.isLoggingEnabled(3)) {
                Log.d("FragmentManager", "moveto CREATE_VIEW: " + this.mFragment);
            }
            Fragment fragment = this.mFragment;
            fragment.performCreateView(fragment.performGetLayoutInflater(fragment.mSavedFragmentState), null, this.mFragment.mSavedFragmentState);
            if (this.mFragment.mView != null) {
                this.mFragment.mView.setSaveFromParentEnabled(false);
                if (this.mFragment.mHidden) {
                    this.mFragment.mView.setVisibility(8);
                }
                Fragment fragment2 = this.mFragment;
                fragment2.onViewCreated(fragment2.mView, this.mFragment.mSavedFragmentState);
                FragmentLifecycleCallbacksDispatcher fragmentLifecycleCallbacksDispatcher = this.mDispatcher;
                Fragment fragment3 = this.mFragment;
                fragmentLifecycleCallbacksDispatcher.dispatchOnFragmentViewCreated(fragment3, fragment3.mView, this.mFragment.mSavedFragmentState, false);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void restoreState(@NonNull ClassLoader classLoader) {
        if (this.mFragment.mSavedFragmentState != null) {
            this.mFragment.mSavedFragmentState.setClassLoader(classLoader);
            Fragment fragment = this.mFragment;
            fragment.mSavedViewState = fragment.mSavedFragmentState.getSparseParcelableArray("android:view_state");
            Fragment fragment2 = this.mFragment;
            fragment2.mTargetWho = fragment2.mSavedFragmentState.getString("android:target_state");
            if (this.mFragment.mTargetWho != null) {
                Fragment fragment3 = this.mFragment;
                fragment3.mTargetRequestCode = fragment3.mSavedFragmentState.getInt("android:target_req_state", 0);
            }
            if (this.mFragment.mSavedUserVisibleHint != null) {
                Fragment fragment4 = this.mFragment;
                fragment4.mUserVisibleHint = fragment4.mSavedUserVisibleHint.booleanValue();
                this.mFragment.mSavedUserVisibleHint = null;
            } else {
                Fragment fragment5 = this.mFragment;
                fragment5.mUserVisibleHint = fragment5.mSavedFragmentState.getBoolean("android:user_visible_hint", true);
            }
            if (!this.mFragment.mUserVisibleHint) {
                this.mFragment.mDeferStart = true;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void attach(@NonNull FragmentHostCallback<?> fragmentHostCallback, @NonNull FragmentManager fragmentManager, @Nullable Fragment fragment) {
        Fragment fragment2 = this.mFragment;
        fragment2.mHost = fragmentHostCallback;
        fragment2.mParentFragment = fragment;
        fragment2.mFragmentManager = fragmentManager;
        this.mDispatcher.dispatchOnFragmentPreAttached(fragment2, fragmentHostCallback.getContext(), false);
        this.mFragment.performAttach();
        if (this.mFragment.mParentFragment == null) {
            fragmentHostCallback.onAttachFragment(this.mFragment);
        } else {
            this.mFragment.mParentFragment.onAttachFragment(this.mFragment);
        }
        this.mDispatcher.dispatchOnFragmentAttached(this.mFragment, fragmentHostCallback.getContext(), false);
    }

    /* access modifiers changed from: package-private */
    public void create() {
        if (FragmentManager.isLoggingEnabled(3)) {
            Log.d("FragmentManager", "moveto CREATED: " + this.mFragment);
        }
        if (!this.mFragment.mIsCreated) {
            FragmentLifecycleCallbacksDispatcher fragmentLifecycleCallbacksDispatcher = this.mDispatcher;
            Fragment fragment = this.mFragment;
            fragmentLifecycleCallbacksDispatcher.dispatchOnFragmentPreCreated(fragment, fragment.mSavedFragmentState, false);
            Fragment fragment2 = this.mFragment;
            fragment2.performCreate(fragment2.mSavedFragmentState);
            FragmentLifecycleCallbacksDispatcher fragmentLifecycleCallbacksDispatcher2 = this.mDispatcher;
            Fragment fragment3 = this.mFragment;
            fragmentLifecycleCallbacksDispatcher2.dispatchOnFragmentCreated(fragment3, fragment3.mSavedFragmentState, false);
            return;
        }
        Fragment fragment4 = this.mFragment;
        fragment4.restoreChildFragmentState(fragment4.mSavedFragmentState);
        this.mFragment.mState = 1;
    }

    /* access modifiers changed from: package-private */
    public void createView(@NonNull FragmentContainer fragmentContainer) {
        String str;
        if (!this.mFragment.mFromLayout) {
            if (FragmentManager.isLoggingEnabled(3)) {
                Log.d("FragmentManager", "moveto CREATE_VIEW: " + this.mFragment);
            }
            ViewGroup viewGroup = null;
            if (this.mFragment.mContainer != null) {
                viewGroup = this.mFragment.mContainer;
            } else if (this.mFragment.mContainerId != 0) {
                if (this.mFragment.mContainerId != -1) {
                    viewGroup = (ViewGroup) fragmentContainer.onFindViewById(this.mFragment.mContainerId);
                    if (viewGroup == null && !this.mFragment.mRestored) {
                        try {
                            str = this.mFragment.getResources().getResourceName(this.mFragment.mContainerId);
                        } catch (Resources.NotFoundException unused) {
                            str = "unknown";
                        }
                        throw new IllegalArgumentException("No view found for id 0x" + Integer.toHexString(this.mFragment.mContainerId) + " (" + str + ") for fragment " + this.mFragment);
                    }
                } else {
                    throw new IllegalArgumentException("Cannot create fragment " + this.mFragment + " for a container view with no id");
                }
            }
            Fragment fragment = this.mFragment;
            fragment.mContainer = viewGroup;
            fragment.performCreateView(fragment.performGetLayoutInflater(fragment.mSavedFragmentState), viewGroup, this.mFragment.mSavedFragmentState);
            if (this.mFragment.mView != null) {
                boolean z = false;
                this.mFragment.mView.setSaveFromParentEnabled(false);
                this.mFragment.mView.setTag(R.id.fragment_container_view_tag, this.mFragment);
                if (viewGroup != null) {
                    viewGroup.addView(this.mFragment.mView);
                }
                if (this.mFragment.mHidden) {
                    this.mFragment.mView.setVisibility(8);
                }
                ViewCompat.requestApplyInsets(this.mFragment.mView);
                Fragment fragment2 = this.mFragment;
                fragment2.onViewCreated(fragment2.mView, this.mFragment.mSavedFragmentState);
                FragmentLifecycleCallbacksDispatcher fragmentLifecycleCallbacksDispatcher = this.mDispatcher;
                Fragment fragment3 = this.mFragment;
                fragmentLifecycleCallbacksDispatcher.dispatchOnFragmentViewCreated(fragment3, fragment3.mView, this.mFragment.mSavedFragmentState, false);
                Fragment fragment4 = this.mFragment;
                if (fragment4.mView.getVisibility() == 0 && this.mFragment.mContainer != null) {
                    z = true;
                }
                fragment4.mIsNewlyAdded = z;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void activityCreated() {
        if (FragmentManager.isLoggingEnabled(3)) {
            Log.d("FragmentManager", "moveto ACTIVITY_CREATED: " + this.mFragment);
        }
        Fragment fragment = this.mFragment;
        fragment.performActivityCreated(fragment.mSavedFragmentState);
        FragmentLifecycleCallbacksDispatcher fragmentLifecycleCallbacksDispatcher = this.mDispatcher;
        Fragment fragment2 = this.mFragment;
        fragmentLifecycleCallbacksDispatcher.dispatchOnFragmentActivityCreated(fragment2, fragment2.mSavedFragmentState, false);
    }

    /* access modifiers changed from: package-private */
    public void restoreViewState() {
        if (FragmentManager.isLoggingEnabled(3)) {
            Log.d("FragmentManager", "moveto RESTORE_VIEW_STATE: " + this.mFragment);
        }
        if (this.mFragment.mView != null) {
            Fragment fragment = this.mFragment;
            fragment.restoreViewState(fragment.mSavedFragmentState);
        }
        this.mFragment.mSavedFragmentState = null;
    }

    /* access modifiers changed from: package-private */
    public void start() {
        if (FragmentManager.isLoggingEnabled(3)) {
            Log.d("FragmentManager", "moveto STARTED: " + this.mFragment);
        }
        this.mFragment.performStart();
        this.mDispatcher.dispatchOnFragmentStarted(this.mFragment, false);
    }

    /* access modifiers changed from: package-private */
    public void resume() {
        if (FragmentManager.isLoggingEnabled(3)) {
            Log.d("FragmentManager", "moveto RESUMED: " + this.mFragment);
        }
        this.mFragment.performResume();
        this.mDispatcher.dispatchOnFragmentResumed(this.mFragment, false);
        Fragment fragment = this.mFragment;
        fragment.mSavedFragmentState = null;
        fragment.mSavedViewState = null;
    }

    /* access modifiers changed from: package-private */
    public void pause() {
        if (FragmentManager.isLoggingEnabled(3)) {
            Log.d("FragmentManager", "movefrom RESUMED: " + this.mFragment);
        }
        this.mFragment.performPause();
        this.mDispatcher.dispatchOnFragmentPaused(this.mFragment, false);
    }

    /* access modifiers changed from: package-private */
    public void stop() {
        if (FragmentManager.isLoggingEnabled(3)) {
            Log.d("FragmentManager", "movefrom STARTED: " + this.mFragment);
        }
        this.mFragment.performStop();
        this.mDispatcher.dispatchOnFragmentStopped(this.mFragment, false);
    }

    /* access modifiers changed from: package-private */
    @NonNull
    public FragmentState saveState() {
        FragmentState fragmentState = new FragmentState(this.mFragment);
        if (this.mFragment.mState <= -1 || fragmentState.mSavedFragmentState != null) {
            fragmentState.mSavedFragmentState = this.mFragment.mSavedFragmentState;
        } else {
            fragmentState.mSavedFragmentState = saveBasicState();
            if (this.mFragment.mTargetWho != null) {
                if (fragmentState.mSavedFragmentState == null) {
                    fragmentState.mSavedFragmentState = new Bundle();
                }
                fragmentState.mSavedFragmentState.putString("android:target_state", this.mFragment.mTargetWho);
                if (this.mFragment.mTargetRequestCode != 0) {
                    fragmentState.mSavedFragmentState.putInt("android:target_req_state", this.mFragment.mTargetRequestCode);
                }
            }
        }
        return fragmentState;
    }

    private Bundle saveBasicState() {
        Bundle bundle = new Bundle();
        this.mFragment.performSaveInstanceState(bundle);
        this.mDispatcher.dispatchOnFragmentSaveInstanceState(this.mFragment, bundle, false);
        if (bundle.isEmpty()) {
            bundle = null;
        }
        if (this.mFragment.mView != null) {
            saveViewState();
        }
        if (this.mFragment.mSavedViewState != null) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putSparseParcelableArray("android:view_state", this.mFragment.mSavedViewState);
        }
        if (!this.mFragment.mUserVisibleHint) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putBoolean("android:user_visible_hint", this.mFragment.mUserVisibleHint);
        }
        return bundle;
    }

    /* access modifiers changed from: package-private */
    public void saveViewState() {
        if (this.mFragment.mView != null) {
            SparseArray<Parcelable> sparseArray = new SparseArray<>();
            this.mFragment.mView.saveHierarchyState(sparseArray);
            if (sparseArray.size() > 0) {
                this.mFragment.mSavedViewState = sparseArray;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void destroy(@NonNull FragmentHostCallback<?> fragmentHostCallback, @NonNull FragmentManagerViewModel fragmentManagerViewModel) {
        if (FragmentManager.isLoggingEnabled(3)) {
            Log.d("FragmentManager", "movefrom CREATED: " + this.mFragment);
        }
        boolean z = true;
        boolean z2 = this.mFragment.mRemoving && !this.mFragment.isInBackStack();
        if (z2 || fragmentManagerViewModel.shouldDestroy(this.mFragment)) {
            if (fragmentHostCallback instanceof ViewModelStoreOwner) {
                z = fragmentManagerViewModel.isCleared();
            } else if (fragmentHostCallback.getContext() instanceof Activity) {
                z = true ^ ((Activity) fragmentHostCallback.getContext()).isChangingConfigurations();
            }
            if (z2 || z) {
                fragmentManagerViewModel.clearNonConfigState(this.mFragment);
            }
            this.mFragment.performDestroy();
            this.mDispatcher.dispatchOnFragmentDestroyed(this.mFragment, false);
            return;
        }
        this.mFragment.mState = 0;
    }

    /* access modifiers changed from: package-private */
    public void detach(@NonNull FragmentManagerViewModel fragmentManagerViewModel) {
        if (FragmentManager.isLoggingEnabled(3)) {
            Log.d("FragmentManager", "movefrom ATTACHED: " + this.mFragment);
        }
        this.mFragment.performDetach();
        boolean z = false;
        this.mDispatcher.dispatchOnFragmentDetached(this.mFragment, false);
        Fragment fragment = this.mFragment;
        fragment.mState = -1;
        fragment.mHost = null;
        fragment.mParentFragment = null;
        fragment.mFragmentManager = null;
        if (fragment.mRemoving && !this.mFragment.isInBackStack()) {
            z = true;
        }
        if (z || fragmentManagerViewModel.shouldDestroy(this.mFragment)) {
            if (FragmentManager.isLoggingEnabled(3)) {
                Log.d("FragmentManager", "initState called for fragment: " + this.mFragment);
            }
            this.mFragment.initState();
        }
    }
}
