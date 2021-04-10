package androidx.fragment.app;

import X.AbstractC003209a;
import X.AbstractC00530Ak;
import X.AbstractC00540Am;
import X.AbstractC07170rP;
import X.AbstractC07290ro;
import X.AnonymousClass006;
import X.AnonymousClass02C;
import X.AnonymousClass04C;
import X.AnonymousClass09A;
import X.AnonymousClass09B;
import X.AnonymousClass09C;
import X.AnonymousClass09D;
import X.AnonymousClass09E;
import X.AnonymousClass09F;
import X.AnonymousClass09Q;
import X.AnonymousClass0AA;
import X.AnonymousClass0AN;
import X.AnonymousClass0AO;
import X.AnonymousClass0AP;
import X.AnonymousClass0AR;
import X.AnonymousClass0AX;
import X.AnonymousClass0Ag;
import X.AnonymousClass0C0;
import X.AnonymousClass0C1;
import X.AnonymousClass0Jm;
import X.AnonymousClass0rm;
import X.AnonymousClass0s9;
import X.AnonymousClass0sB;
import X.C00520Aj;
import X.C07210rd;
import X.C07280rn;
import X.C07350ry;
import X.C07390s3;
import android.animation.Animator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.annotation.CallSuper;
import androidx.annotation.ContentView;
import androidx.annotation.LayoutRes;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.StringRes;
import androidx.annotation.UiThread;
import com.facebook.FacebookSdk;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Fragment implements AnonymousClass0AR, AbstractC00530Ak, AbstractC07170rP, ComponentCallbacks, View.OnCreateContextMenuListener {
    public static final int ACTIVITY_CREATED = 2;
    public static final int ATTACHED = 0;
    public static final int CREATED = 1;
    public static final int INITIALIZING = -1;
    public static final int RESUMED = 4;
    public static final int STARTED = 3;
    public static final Object USE_DEFAULT_TRANSITION = new Object();
    public boolean mAdded;
    public AnonymousClass09C mAnimationInfo;
    public Bundle mArguments;
    public int mBackStackNesting;
    public boolean mCalled;
    @NonNull
    public AbstractC003209a mChildFragmentManager;
    public ViewGroup mContainer;
    public int mContainerId;
    @LayoutRes
    public int mContentLayoutId;
    public AnonymousClass0Ag mDefaultFactory;
    public boolean mDeferStart;
    public boolean mDetached;
    public int mFragmentId;
    public AbstractC003209a mFragmentManager;
    public boolean mFromLayout;
    public boolean mHasMenu;
    public boolean mHidden;
    public boolean mHiddenChanged;
    public AnonymousClass0s9<?> mHost;
    public boolean mInLayout;
    public boolean mIsCreated;
    public boolean mIsNewlyAdded;
    public Boolean mIsPrimaryNavigationFragment;
    public LayoutInflater mLayoutInflater;
    public C07280rn mLifecycleRegistry;
    public AnonymousClass0AO mMaxState;
    public boolean mMenuVisible;
    public Fragment mParentFragment;
    public boolean mPerformedCreateView;
    public float mPostponedAlpha;
    public Runnable mPostponedDurationRunnable;
    public boolean mRemoving;
    public boolean mRestored;
    public boolean mRetainInstance;
    public boolean mRetainInstanceChangedWhileDetached;
    public Bundle mSavedFragmentState;
    public AnonymousClass0C1 mSavedStateRegistryController;
    @Nullable
    public Boolean mSavedUserVisibleHint;
    public SparseArray<Parcelable> mSavedViewState;
    public int mState;
    public String mTag;
    public Fragment mTarget;
    public int mTargetRequestCode;
    public String mTargetWho;
    public boolean mUserVisibleHint;
    public View mView;
    @Nullable
    public C07350ry mViewLifecycleOwner;
    public AnonymousClass0rm<AnonymousClass0AR> mViewLifecycleOwnerLiveData;
    @NonNull
    public String mWho;

    @SuppressLint({"BanParcelableUsage"})
    public static class SavedState implements Parcelable {
        @NonNull
        public static final Parcelable.Creator<SavedState> CREATOR = new AnonymousClass09F();
        public final Bundle A00;

        public final int describeContents() {
            return 0;
        }

        public final void writeToParcel(@NonNull Parcel parcel, int i) {
            parcel.writeBundle(this.A00);
        }

        public SavedState(@NonNull Parcel parcel, @Nullable ClassLoader classLoader) {
            Bundle readBundle = parcel.readBundle();
            this.A00 = readBundle;
            if (classLoader != null && readBundle != null) {
                readBundle.setClassLoader(classLoader);
            }
        }
    }

    @CallSuper
    @MainThread
    public void onActivityCreated(@Nullable Bundle bundle) {
        this.mCalled = true;
    }

    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
    }

    @MainThread
    public void onAttachFragment(@NonNull Fragment fragment) {
    }

    @CallSuper
    public void onConfigurationChanged(@NonNull Configuration configuration) {
        this.mCalled = true;
    }

    @MainThread
    public boolean onContextItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }

    @CallSuper
    @MainThread
    public void onCreate(@Nullable Bundle bundle) {
        this.mCalled = true;
        restoreChildFragmentState(bundle);
        AbstractC003209a r1 = this.mChildFragmentManager;
        if (r1.A00 < 1) {
            r1.A0H = false;
            r1.A0I = false;
            AbstractC003209a.A07(r1, 1);
        }
    }

    @Nullable
    @MainThread
    public Animation onCreateAnimation(int i, boolean z, int i2) {
        return null;
    }

    @Nullable
    @MainThread
    public Animator onCreateAnimator(int i, boolean z, int i2) {
        return null;
    }

    @MainThread
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
    }

    @CallSuper
    @MainThread
    public void onDestroy() {
        this.mCalled = true;
    }

    @MainThread
    public void onDestroyOptionsMenu() {
    }

    @CallSuper
    @MainThread
    public void onDestroyView() {
        this.mCalled = true;
    }

    @CallSuper
    @MainThread
    public void onDetach() {
        this.mCalled = true;
    }

    @MainThread
    public void onHiddenChanged(boolean z) {
    }

    @CallSuper
    @MainThread
    public void onLowMemory() {
        this.mCalled = true;
    }

    public void onMultiWindowModeChanged(boolean z) {
    }

    @MainThread
    public boolean onOptionsItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }

    @MainThread
    public void onOptionsMenuClosed(@NonNull Menu menu) {
    }

    @CallSuper
    @MainThread
    public void onPause() {
        this.mCalled = true;
    }

    public void onPictureInPictureModeChanged(boolean z) {
    }

    @MainThread
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
    }

    @MainThread
    public void onPrimaryNavigationFragmentChanged(boolean z) {
    }

    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
    }

    @CallSuper
    @MainThread
    public void onResume() {
        this.mCalled = true;
    }

    @MainThread
    public void onSaveInstanceState(@NonNull Bundle bundle) {
    }

    @CallSuper
    @MainThread
    public void onStart() {
        this.mCalled = true;
    }

    @CallSuper
    @MainThread
    public void onStop() {
        this.mCalled = true;
    }

    @MainThread
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
    }

    @CallSuper
    @MainThread
    public void onViewStateRestored(@Nullable Bundle bundle) {
        this.mCalled = true;
    }

    public void performDetach() {
        this.mState = -1;
        this.mCalled = false;
        onDetach();
        this.mLayoutInflater = null;
        if (this.mCalled) {
            AbstractC003209a r1 = this.mChildFragmentManager;
            if (!r1.A0D) {
                r1.A0N();
                this.mChildFragmentManager = new C07390s3();
                return;
            }
            return;
        }
        StringBuilder sb = new StringBuilder("Fragment ");
        sb.append(this);
        sb.append(" did not call through to super.onDetach()");
        throw new AnonymousClass0AA(sb.toString());
    }

    public void unregisterForContextMenu(@NonNull View view) {
        view.setOnCreateContextMenuListener(null);
    }

    private AnonymousClass09C ensureAnimationInfo() {
        AnonymousClass09C r0 = this.mAnimationInfo;
        if (r0 != null) {
            return r0;
        }
        AnonymousClass09C r02 = new AnonymousClass09C();
        this.mAnimationInfo = r02;
        return r02;
    }

    private void initLifecycle() {
        this.mLifecycleRegistry = new C07280rn(this);
        this.mSavedStateRegistryController = new AnonymousClass0C1(this);
        this.mLifecycleRegistry.A06(new AbstractC07290ro() {
            /* class androidx.fragment.app.Fragment.AnonymousClass2 */

            @Override // X.AbstractC07290ro
            public final void A70(@NonNull AnonymousClass0AR r2, @NonNull AnonymousClass0AN r3) {
                View view;
                if (r3 == AnonymousClass0AN.ON_STOP && (view = Fragment.this.mView) != null) {
                    view.cancelPendingInputEvents();
                }
            }
        });
    }

    public void callStartTransitionListener() {
        AnonymousClass09C r2 = this.mAnimationInfo;
        if (r2 != null) {
            r2.A0G = false;
            AnonymousClass09E r0 = r2.A07;
            r2.A07 = null;
            if (r0 != null) {
                r0.A6y();
            }
        }
    }

    @Nullable
    public Fragment findFragmentByWho(@NonNull String str) {
        if (str.equals(this.mWho)) {
            return this;
        }
        return this.mChildFragmentManager.A0K(str);
    }

    @Nullable
    public final FragmentActivity getActivity() {
        AnonymousClass0s9<?> r0 = this.mHost;
        if (r0 == null) {
            return null;
        }
        return (FragmentActivity) r0.A00;
    }

    public boolean getAllowEnterTransitionOverlap() {
        Boolean bool;
        AnonymousClass09C r0 = this.mAnimationInfo;
        if (r0 == null || (bool = r0.A08) == null) {
            return true;
        }
        return bool.booleanValue();
    }

    public boolean getAllowReturnTransitionOverlap() {
        Boolean bool;
        AnonymousClass09C r0 = this.mAnimationInfo;
        if (r0 == null || (bool = r0.A09) == null) {
            return true;
        }
        return bool.booleanValue();
    }

    public View getAnimatingAway() {
        AnonymousClass09C r0 = this.mAnimationInfo;
        if (r0 == null) {
            return null;
        }
        return r0.A04;
    }

    public Animator getAnimator() {
        AnonymousClass09C r0 = this.mAnimationInfo;
        if (r0 == null) {
            return null;
        }
        return r0.A03;
    }

    @NonNull
    public final AbstractC003209a getChildFragmentManager() {
        if (this.mHost != null) {
            return this.mChildFragmentManager;
        }
        StringBuilder sb = new StringBuilder("Fragment ");
        sb.append(this);
        sb.append(" has not been attached yet.");
        throw new IllegalStateException(sb.toString());
    }

    @Nullable
    public Context getContext() {
        AnonymousClass0s9<?> r0 = this.mHost;
        if (r0 == null) {
            return null;
        }
        return r0.A01;
    }

    @NonNull
    public AnonymousClass0Ag getDefaultViewModelProviderFactory() {
        if (this.mFragmentManager != null) {
            AnonymousClass0Ag r2 = this.mDefaultFactory;
            if (r2 != null) {
                return r2;
            }
            AnonymousClass0Jm r22 = new AnonymousClass0Jm(requireActivity().getApplication(), this, this.mArguments);
            this.mDefaultFactory = r22;
            return r22;
        }
        throw new IllegalStateException("Can't access ViewModels from detached fragment");
    }

    @Nullable
    public Object getEnterTransition() {
        AnonymousClass09C r0 = this.mAnimationInfo;
        if (r0 == null) {
            return null;
        }
        return r0.A0A;
    }

    @Nullable
    public Object getExitTransition() {
        AnonymousClass09C r0 = this.mAnimationInfo;
        if (r0 == null) {
            return null;
        }
        return r0.A0B;
    }

    @Nullable
    public final Object getHost() {
        AnonymousClass0s9<?> r0 = this.mHost;
        if (r0 == null) {
            return null;
        }
        return r0.A03();
    }

    public int getNextAnim() {
        AnonymousClass09C r0 = this.mAnimationInfo;
        if (r0 == null) {
            return 0;
        }
        return r0.A00;
    }

    public int getNextTransition() {
        AnonymousClass09C r0 = this.mAnimationInfo;
        if (r0 == null) {
            return 0;
        }
        return r0.A01;
    }

    @NonNull
    public final AbstractC003209a getParentFragmentManager() {
        AbstractC003209a r0 = this.mFragmentManager;
        if (r0 != null) {
            return r0;
        }
        StringBuilder sb = new StringBuilder("Fragment ");
        sb.append(this);
        sb.append(" not associated with a fragment manager.");
        throw new IllegalStateException(sb.toString());
    }

    @Nullable
    public Object getReenterTransition() {
        AnonymousClass09C r0 = this.mAnimationInfo;
        if (r0 == null) {
            return null;
        }
        Object obj = r0.A0C;
        if (obj == USE_DEFAULT_TRANSITION) {
            return getExitTransition();
        }
        return obj;
    }

    @Nullable
    public Object getReturnTransition() {
        AnonymousClass09C r0 = this.mAnimationInfo;
        if (r0 == null) {
            return null;
        }
        Object obj = r0.A0D;
        if (obj == USE_DEFAULT_TRANSITION) {
            return getEnterTransition();
        }
        return obj;
    }

    @Override // X.AbstractC07170rP
    @NonNull
    public final AnonymousClass0C0 getSavedStateRegistry() {
        return this.mSavedStateRegistryController.A00;
    }

    @Nullable
    public Object getSharedElementEnterTransition() {
        AnonymousClass09C r0 = this.mAnimationInfo;
        if (r0 == null) {
            return null;
        }
        return r0.A0E;
    }

    @Nullable
    public Object getSharedElementReturnTransition() {
        AnonymousClass09C r0 = this.mAnimationInfo;
        if (r0 == null) {
            return null;
        }
        Object obj = r0.A0F;
        if (obj == USE_DEFAULT_TRANSITION) {
            return getSharedElementEnterTransition();
        }
        return obj;
    }

    public int getStateAfterAnimating() {
        AnonymousClass09C r0 = this.mAnimationInfo;
        if (r0 == null) {
            return 0;
        }
        return r0.A02;
    }

    @Nullable
    public final Fragment getTargetFragment() {
        String str;
        Fragment fragment = this.mTarget;
        if (fragment != null) {
            return fragment;
        }
        AbstractC003209a r1 = this.mFragmentManager;
        if (r1 == null || (str = this.mTargetWho) == null) {
            return null;
        }
        return r1.A0I(str);
    }

    @NonNull
    @MainThread
    public AnonymousClass0AR getViewLifecycleOwner() {
        C07350ry r0 = this.mViewLifecycleOwner;
        if (r0 != null) {
            return r0;
        }
        throw new IllegalStateException("Can't access the Fragment View's LifecycleOwner when getView() is null i.e., before onCreateView() or after onDestroyView()");
    }

    @Override // X.AbstractC00530Ak
    @NonNull
    public C00520Aj getViewModelStore() {
        AbstractC003209a r0 = this.mFragmentManager;
        if (r0 != null) {
            HashMap<String, C00520Aj> hashMap = r0.A06.A03;
            C00520Aj r1 = hashMap.get(this.mWho);
            if (r1 != null) {
                return r1;
            }
            C00520Aj r12 = new C00520Aj();
            hashMap.put(this.mWho, r12);
            return r12;
        }
        throw new IllegalStateException("Can't access ViewModels from detached fragment");
    }

    public final boolean isAdded() {
        if (this.mHost == null || !this.mAdded) {
            return false;
        }
        return true;
    }

    public boolean isHideReplaced() {
        AnonymousClass09C r0 = this.mAnimationInfo;
        if (r0 == null) {
            return false;
        }
        return r0.A0H;
    }

    public final boolean isInBackStack() {
        if (this.mBackStackNesting > 0) {
            return true;
        }
        return false;
    }

    public boolean isPostponed() {
        AnonymousClass09C r0 = this.mAnimationInfo;
        if (r0 == null) {
            return false;
        }
        return r0.A0G;
    }

    public final boolean isRemovingParent() {
        Fragment fragment = this.mParentFragment;
        if (fragment == null) {
            return false;
        }
        if (fragment.mRemoving || fragment.isRemovingParent()) {
            return true;
        }
        return false;
    }

    public final boolean isResumed() {
        if (this.mState >= 4) {
            return true;
        }
        return false;
    }

    public final boolean isStateSaved() {
        AbstractC003209a r1 = this.mFragmentManager;
        if (r1 == null) {
            return false;
        }
        if (r1.A0H || r1.A0I) {
            return true;
        }
        return false;
    }

    public void noteStateNotSaved() {
        this.mChildFragmentManager.A0P();
    }

    @Nullable
    @MainThread
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        int i = this.mContentLayoutId;
        if (i != 0) {
            return layoutInflater.inflate(i, viewGroup, false);
        }
        return null;
    }

    public void performActivityCreated(Bundle bundle) {
        this.mChildFragmentManager.A0P();
        this.mState = 2;
        this.mCalled = false;
        onActivityCreated(bundle);
        if (this.mCalled) {
            AbstractC003209a r1 = this.mChildFragmentManager;
            r1.A0H = false;
            r1.A0I = false;
            AbstractC003209a.A07(r1, 2);
            return;
        }
        StringBuilder sb = new StringBuilder("Fragment ");
        sb.append(this);
        sb.append(" did not call through to super.onActivityCreated()");
        throw new AnonymousClass0AA(sb.toString());
    }

    public void performAttach() {
        this.mChildFragmentManager.A0h(this.mHost, new AnonymousClass0sB(this), this);
        this.mState = 0;
        this.mCalled = false;
        onAttach(this.mHost.A01);
        if (!this.mCalled) {
            StringBuilder sb = new StringBuilder("Fragment ");
            sb.append(this);
            sb.append(" did not call through to super.onAttach()");
            throw new AnonymousClass0AA(sb.toString());
        }
    }

    public boolean performContextItemSelected(@NonNull MenuItem menuItem) {
        if (this.mHidden || !this.mChildFragmentManager.A0r(menuItem)) {
            return false;
        }
        return true;
    }

    public void performCreate(Bundle bundle) {
        this.mChildFragmentManager.A0P();
        this.mState = 1;
        this.mCalled = false;
        this.mSavedStateRegistryController.A00(bundle);
        onCreate(bundle);
        this.mIsCreated = true;
        if (this.mCalled) {
            this.mLifecycleRegistry.A08(AnonymousClass0AN.ON_CREATE);
            return;
        }
        StringBuilder sb = new StringBuilder("Fragment ");
        sb.append(this);
        sb.append(" did not call through to super.onCreate()");
        throw new AnonymousClass0AA(sb.toString());
    }

    public boolean performCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
        boolean z = false;
        if (this.mHidden) {
            return false;
        }
        if (this.mHasMenu && this.mMenuVisible) {
            z = true;
        }
        return z | this.mChildFragmentManager.A0q(menu, menuInflater);
    }

    public void performCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        this.mChildFragmentManager.A0P();
        this.mPerformedCreateView = true;
        this.mViewLifecycleOwner = new C07350ry();
        View onCreateView = onCreateView(layoutInflater, viewGroup, bundle);
        this.mView = onCreateView;
        if (onCreateView != null) {
            C07350ry r1 = this.mViewLifecycleOwner;
            if (r1.A00 == null) {
                r1.A00 = new C07280rn(r1);
            }
            this.mViewLifecycleOwnerLiveData.A02(this.mViewLifecycleOwner);
        } else if (this.mViewLifecycleOwner.A00 != null) {
            throw new IllegalStateException("Called getViewLifecycleOwner() but onCreateView() returned null");
        } else {
            this.mViewLifecycleOwner = null;
        }
    }

    public void performDestroy() {
        this.mChildFragmentManager.A0N();
        this.mLifecycleRegistry.A08(AnonymousClass0AN.ON_DESTROY);
        this.mState = 0;
        this.mCalled = false;
        this.mIsCreated = false;
        onDestroy();
        if (!this.mCalled) {
            StringBuilder sb = new StringBuilder("Fragment ");
            sb.append(this);
            sb.append(" did not call through to super.onDestroy()");
            throw new AnonymousClass0AA(sb.toString());
        }
    }

    public void performDestroyView() {
        AbstractC003209a.A07(this.mChildFragmentManager, 1);
        if (this.mView != null) {
            C07350ry r0 = this.mViewLifecycleOwner;
            r0.A00.A08(AnonymousClass0AN.ON_DESTROY);
        }
        this.mState = 1;
        this.mCalled = false;
        onDestroyView();
        if (this.mCalled) {
            new C07210rd(this, getViewModelStore()).A00();
            this.mPerformedCreateView = false;
            return;
        }
        StringBuilder sb = new StringBuilder("Fragment ");
        sb.append(this);
        sb.append(" did not call through to super.onDestroyView()");
        throw new AnonymousClass0AA(sb.toString());
    }

    public void performMultiWindowModeChanged(boolean z) {
        this.mChildFragmentManager.A0l(z);
    }

    public boolean performOptionsItemSelected(@NonNull MenuItem menuItem) {
        if (this.mHidden || !this.mChildFragmentManager.A0s(menuItem)) {
            return false;
        }
        return true;
    }

    public void performOptionsMenuClosed(@NonNull Menu menu) {
        if (!this.mHidden) {
            this.mChildFragmentManager.A0T(menu);
        }
    }

    public void performPause() {
        AbstractC003209a.A07(this.mChildFragmentManager, 3);
        if (this.mView != null) {
            C07350ry r0 = this.mViewLifecycleOwner;
            r0.A00.A08(AnonymousClass0AN.ON_PAUSE);
        }
        this.mLifecycleRegistry.A08(AnonymousClass0AN.ON_PAUSE);
        this.mState = 3;
        this.mCalled = false;
        onPause();
        if (!this.mCalled) {
            StringBuilder sb = new StringBuilder("Fragment ");
            sb.append(this);
            sb.append(" did not call through to super.onPause()");
            throw new AnonymousClass0AA(sb.toString());
        }
    }

    public void performPictureInPictureModeChanged(boolean z) {
        this.mChildFragmentManager.A0m(z);
    }

    public boolean performPrepareOptionsMenu(@NonNull Menu menu) {
        boolean z = false;
        if (this.mHidden) {
            return false;
        }
        if (this.mHasMenu && this.mMenuVisible) {
            z = true;
        }
        return z | this.mChildFragmentManager.A0p(menu);
    }

    public void performPrimaryNavigationFragmentChanged() {
        boolean A0t = this.mFragmentManager.A0t(this);
        Boolean bool = this.mIsPrimaryNavigationFragment;
        if (bool == null || bool.booleanValue() != A0t) {
            this.mIsPrimaryNavigationFragment = Boolean.valueOf(A0t);
            AbstractC003209a r1 = this.mChildFragmentManager;
            AbstractC003209a.A06(r1);
            AbstractC003209a.A09(r1, r1.A03);
        }
    }

    public void performResume() {
        this.mChildFragmentManager.A0P();
        this.mChildFragmentManager.A0n(true);
        this.mState = 4;
        this.mCalled = false;
        onResume();
        if (this.mCalled) {
            C07280rn r0 = this.mLifecycleRegistry;
            AnonymousClass0AN r1 = AnonymousClass0AN.ON_RESUME;
            r0.A08(r1);
            if (this.mView != null) {
                this.mViewLifecycleOwner.A00.A08(r1);
            }
            AbstractC003209a r12 = this.mChildFragmentManager;
            r12.A0H = false;
            r12.A0I = false;
            AbstractC003209a.A07(r12, 4);
            return;
        }
        StringBuilder sb = new StringBuilder("Fragment ");
        sb.append(this);
        sb.append(" did not call through to super.onResume()");
        throw new AnonymousClass0AA(sb.toString());
    }

    public void performStart() {
        this.mChildFragmentManager.A0P();
        this.mChildFragmentManager.A0n(true);
        this.mState = 3;
        this.mCalled = false;
        onStart();
        if (this.mCalled) {
            C07280rn r0 = this.mLifecycleRegistry;
            AnonymousClass0AN r1 = AnonymousClass0AN.ON_START;
            r0.A08(r1);
            if (this.mView != null) {
                this.mViewLifecycleOwner.A00.A08(r1);
            }
            AbstractC003209a r12 = this.mChildFragmentManager;
            r12.A0H = false;
            r12.A0I = false;
            AbstractC003209a.A07(r12, 3);
            return;
        }
        StringBuilder sb = new StringBuilder("Fragment ");
        sb.append(this);
        sb.append(" did not call through to super.onStart()");
        throw new AnonymousClass0AA(sb.toString());
    }

    public void performStop() {
        AbstractC003209a r1 = this.mChildFragmentManager;
        r1.A0I = true;
        AbstractC003209a.A07(r1, 2);
        if (this.mView != null) {
            C07350ry r0 = this.mViewLifecycleOwner;
            r0.A00.A08(AnonymousClass0AN.ON_STOP);
        }
        this.mLifecycleRegistry.A08(AnonymousClass0AN.ON_STOP);
        this.mState = 2;
        this.mCalled = false;
        onStop();
        if (!this.mCalled) {
            StringBuilder sb = new StringBuilder("Fragment ");
            sb.append(this);
            sb.append(" did not call through to super.onStop()");
            throw new AnonymousClass0AA(sb.toString());
        }
    }

    public final void requestPermissions(@NonNull String[] strArr, int i) {
        AnonymousClass0s9<?> r0 = this.mHost;
        if (r0 != null) {
            r0.A07(this, strArr, i);
            return;
        }
        StringBuilder sb = new StringBuilder("Fragment ");
        sb.append(this);
        sb.append(" not attached to Activity");
        throw new IllegalStateException(sb.toString());
    }

    @NonNull
    public final Bundle requireArguments() {
        Bundle bundle = this.mArguments;
        if (bundle != null) {
            return bundle;
        }
        StringBuilder sb = new StringBuilder("Fragment ");
        sb.append(this);
        sb.append(" does not have any arguments.");
        throw new IllegalStateException(sb.toString());
    }

    @NonNull
    public final Fragment requireParentFragment() {
        Fragment fragment = this.mParentFragment;
        if (fragment != null) {
            return fragment;
        }
        Context context = getContext();
        StringBuilder sb = new StringBuilder();
        sb.append("Fragment ");
        if (context == null) {
            sb.append(this);
            sb.append(" is not attached to any Fragment or host");
        } else {
            sb.append(this);
            sb.append(" is not a child Fragment, it is directly attached to ");
            sb.append(getContext());
        }
        throw new IllegalStateException(sb.toString());
    }

    @NonNull
    public final View requireView() {
        View view = this.mView;
        if (view != null) {
            return view;
        }
        StringBuilder sb = new StringBuilder("Fragment ");
        sb.append(this);
        sb.append(" did not return a View from onCreateView() or this was called before onCreateView().");
        throw new IllegalStateException(sb.toString());
    }

    public void restoreChildFragmentState(@Nullable Bundle bundle) {
        Parcelable parcelable;
        if (bundle != null && (parcelable = bundle.getParcelable(FragmentActivity.FRAGMENTS_TAG)) != null) {
            this.mChildFragmentManager.A0S(parcelable);
            AbstractC003209a r1 = this.mChildFragmentManager;
            r1.A0H = false;
            r1.A0I = false;
            AbstractC003209a.A07(r1, 1);
        }
    }

    public final void restoreViewState(Bundle bundle) {
        SparseArray<Parcelable> sparseArray = this.mSavedViewState;
        if (sparseArray != null) {
            this.mView.restoreHierarchyState(sparseArray);
            this.mSavedViewState = null;
        }
        this.mCalled = false;
        this.mCalled = true;
        if (this.mView != null) {
            C07350ry r0 = this.mViewLifecycleOwner;
            r0.A00.A08(AnonymousClass0AN.ON_CREATE);
        }
    }

    public void setArguments(@Nullable Bundle bundle) {
        if (this.mFragmentManager == null || !isStateSaved()) {
            this.mArguments = bundle;
            return;
        }
        throw new IllegalStateException("Fragment already added and state has been saved");
    }

    public void setHasOptionsMenu(boolean z) {
        if (this.mHasMenu != z) {
            this.mHasMenu = z;
            if (isAdded() && !this.mHidden) {
                this.mHost.A04();
            }
        }
    }

    public void setInitialSavedState(@Nullable SavedState savedState) {
        Bundle bundle;
        if (this.mFragmentManager == null) {
            if (savedState == null || (bundle = savedState.A00) == null) {
                bundle = null;
            }
            this.mSavedFragmentState = bundle;
            return;
        }
        throw new IllegalStateException("Fragment already added");
    }

    public void setMenuVisibility(boolean z) {
        if (this.mMenuVisible != z) {
            this.mMenuVisible = z;
            if (this.mHasMenu && isAdded() && !this.mHidden) {
                this.mHost.A04();
            }
        }
    }

    public void setNextAnim(int i) {
        if (this.mAnimationInfo != null || i != 0) {
            ensureAnimationInfo().A00 = i;
        }
    }

    public void setNextTransition(int i) {
        if (this.mAnimationInfo != null || i != 0) {
            ensureAnimationInfo();
            this.mAnimationInfo.A01 = i;
        }
    }

    @Deprecated
    public void setRetainInstance(boolean z) {
        this.mRetainInstance = z;
        AbstractC003209a r0 = this.mFragmentManager;
        if (r0 == null) {
            this.mRetainInstanceChangedWhileDetached = true;
        } else if (z) {
            r0.A0W(this);
        } else {
            r0.A0c(this);
        }
    }

    public void setTargetFragment(@Nullable Fragment fragment, int i) {
        StringBuilder sb;
        String str;
        AbstractC003209a r2 = this.mFragmentManager;
        AbstractC003209a r0 = fragment != null ? fragment.mFragmentManager : null;
        if (r2 == null || r0 == null || r2 == r0) {
            for (Fragment fragment2 = fragment; fragment2 != null; fragment2 = fragment2.getTargetFragment()) {
                if (fragment2 == this) {
                    sb = new StringBuilder("Setting ");
                    sb.append(fragment);
                    sb.append(" as the target of ");
                    sb.append(this);
                    str = " would create a target cycle";
                }
            }
            if (fragment == null) {
                this.mTargetWho = null;
            } else if (this.mFragmentManager == null || fragment.mFragmentManager == null) {
                this.mTargetWho = null;
                this.mTarget = fragment;
                this.mTargetRequestCode = i;
                return;
            } else {
                this.mTargetWho = fragment.mWho;
            }
            this.mTarget = null;
            this.mTargetRequestCode = i;
            return;
        }
        sb = new StringBuilder("Fragment ");
        sb.append(fragment);
        str = " must share the same FragmentManager to be set as a target fragment";
        sb.append(str);
        throw new IllegalArgumentException(sb.toString());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0021, code lost:
        if (r4 != false) goto L_0x0023;
     */
    @java.lang.Deprecated
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setUserVisibleHint(boolean r4) {
        /*
            r3 = this;
            boolean r0 = r3.mUserVisibleHint
            r2 = 3
            if (r0 != 0) goto L_0x001a
            if (r4 == 0) goto L_0x001a
            int r0 = r3.mState
            if (r0 >= r2) goto L_0x001a
            X.09a r1 = r3.mFragmentManager
            if (r1 == 0) goto L_0x001a
            boolean r0 = r3.mDeferStart
            if (r0 == 0) goto L_0x001a
            boolean r0 = r1.A0E
            if (r0 == 0) goto L_0x0031
            r0 = 1
            r1.A0F = r0
        L_0x001a:
            r3.mUserVisibleHint = r4
            int r0 = r3.mState
            if (r0 >= r2) goto L_0x0023
            r0 = 1
            if (r4 == 0) goto L_0x0024
        L_0x0023:
            r0 = 0
        L_0x0024:
            r3.mDeferStart = r0
            android.os.Bundle r0 = r3.mSavedFragmentState
            if (r0 == 0) goto L_0x0030
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r4)
            r3.mSavedUserVisibleHint = r0
        L_0x0030:
            return
        L_0x0031:
            r0 = 0
            r3.mDeferStart = r0
            int r0 = r1.A00
            r1.A0e(r3, r0)
            goto L_0x001a
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.Fragment.setUserVisibleHint(boolean):void");
    }

    public boolean shouldShowRequestPermissionRationale(@NonNull String str) {
        AnonymousClass0s9<?> r0 = this.mHost;
        if (r0 != null) {
            return r0.A09(str);
        }
        return false;
    }

    public void startIntentSenderForResult(@SuppressLint({"UnknownNullness"}) IntentSender intentSender, int i, @Nullable Intent intent, int i2, int i3, int i4, @Nullable Bundle bundle) throws IntentSender.SendIntentException {
        AnonymousClass0s9<?> r1 = this.mHost;
        if (r1 != null) {
            r1.A06(this, intentSender, i, intent, i2, i3, i4, bundle);
            return;
        }
        StringBuilder sb = new StringBuilder("Fragment ");
        sb.append(this);
        sb.append(" not attached to Activity");
        throw new IllegalStateException(sb.toString());
    }

    public void startPostponedEnterTransition() {
        AbstractC003209a r0 = this.mFragmentManager;
        if (r0 == null || r0.A05 == null) {
            ensureAnimationInfo().A0G = false;
        } else if (Looper.myLooper() != this.mFragmentManager.A05.A02.getLooper()) {
            this.mFragmentManager.A05.A02.postAtFrontOfQueue(new AnonymousClass09B(this));
        } else {
            callStartTransitionListener();
        }
    }

    @NonNull
    public String toString() {
        StringBuilder sb = new StringBuilder((int) FacebookSdk.DEFAULT_MAXIMUM_POOL_SIZE);
        sb.append(getClass().getSimpleName());
        sb.append("{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("}");
        sb.append(" (");
        sb.append(this.mWho);
        sb.append(")");
        int i = this.mFragmentId;
        if (i != 0) {
            sb.append(" id=0x");
            sb.append(Integer.toHexString(i));
        }
        String str = this.mTag;
        if (str != null) {
            sb.append(" ");
            sb.append(str);
        }
        sb.append('}');
        return sb.toString();
    }

    public void dump(@NonNull String str, @Nullable FileDescriptor fileDescriptor, @NonNull PrintWriter printWriter, @Nullable String[] strArr) {
        printWriter.print(str);
        printWriter.print("mFragmentId=#");
        printWriter.print(Integer.toHexString(this.mFragmentId));
        printWriter.print(" mContainerId=#");
        printWriter.print(Integer.toHexString(this.mContainerId));
        printWriter.print(" mTag=");
        printWriter.println(this.mTag);
        printWriter.print(str);
        printWriter.print("mState=");
        printWriter.print(this.mState);
        printWriter.print(" mWho=");
        printWriter.print(this.mWho);
        printWriter.print(" mBackStackNesting=");
        printWriter.println(this.mBackStackNesting);
        printWriter.print(str);
        printWriter.print("mAdded=");
        printWriter.print(this.mAdded);
        printWriter.print(" mRemoving=");
        printWriter.print(this.mRemoving);
        printWriter.print(" mFromLayout=");
        printWriter.print(this.mFromLayout);
        printWriter.print(" mInLayout=");
        printWriter.println(this.mInLayout);
        printWriter.print(str);
        printWriter.print("mHidden=");
        printWriter.print(this.mHidden);
        printWriter.print(" mDetached=");
        printWriter.print(this.mDetached);
        printWriter.print(" mMenuVisible=");
        printWriter.print(this.mMenuVisible);
        printWriter.print(" mHasMenu=");
        printWriter.println(this.mHasMenu);
        printWriter.print(str);
        printWriter.print("mRetainInstance=");
        printWriter.print(this.mRetainInstance);
        printWriter.print(" mUserVisibleHint=");
        printWriter.println(this.mUserVisibleHint);
        if (this.mFragmentManager != null) {
            printWriter.print(str);
            printWriter.print("mFragmentManager=");
            printWriter.println(this.mFragmentManager);
        }
        if (this.mHost != null) {
            printWriter.print(str);
            printWriter.print("mHost=");
            printWriter.println(this.mHost);
        }
        if (this.mParentFragment != null) {
            printWriter.print(str);
            printWriter.print("mParentFragment=");
            printWriter.println(this.mParentFragment);
        }
        if (this.mArguments != null) {
            printWriter.print(str);
            printWriter.print("mArguments=");
            printWriter.println(this.mArguments);
        }
        if (this.mSavedFragmentState != null) {
            printWriter.print(str);
            printWriter.print("mSavedFragmentState=");
            printWriter.println(this.mSavedFragmentState);
        }
        if (this.mSavedViewState != null) {
            printWriter.print(str);
            printWriter.print("mSavedViewState=");
            printWriter.println(this.mSavedViewState);
        }
        Fragment targetFragment = getTargetFragment();
        if (targetFragment != null) {
            printWriter.print(str);
            printWriter.print("mTarget=");
            printWriter.print(targetFragment);
            printWriter.print(" mTargetRequestCode=");
            printWriter.println(this.mTargetRequestCode);
        }
        if (getNextAnim() != 0) {
            printWriter.print(str);
            printWriter.print("mNextAnim=");
            printWriter.println(getNextAnim());
        }
        if (this.mContainer != null) {
            printWriter.print(str);
            printWriter.print("mContainer=");
            printWriter.println(this.mContainer);
        }
        if (this.mView != null) {
            printWriter.print(str);
            printWriter.print("mView=");
            printWriter.println(this.mView);
        }
        if (getAnimatingAway() != null) {
            printWriter.print(str);
            printWriter.print("mAnimatingAway=");
            printWriter.println(getAnimatingAway());
            printWriter.print(str);
            printWriter.print("mStateAfterAnimating=");
            printWriter.println(getStateAfterAnimating());
        }
        if (getContext() != null) {
            new C07210rd(this, getViewModelStore()).A01(str, fileDescriptor, printWriter, strArr);
        }
        printWriter.print(str);
        StringBuilder sb = new StringBuilder("Child ");
        sb.append(this.mChildFragmentManager);
        sb.append(":");
        printWriter.println(sb.toString());
        this.mChildFragmentManager.A0k(AnonymousClass006.A05(str, "  "), fileDescriptor, printWriter, strArr);
    }

    @Nullable
    public final Bundle getArguments() {
        return this.mArguments;
    }

    public AnonymousClass04C getEnterTransitionCallback() {
        return null;
    }

    public AnonymousClass04C getExitTransitionCallback() {
        return null;
    }

    @Nullable
    @Deprecated
    public final AbstractC003209a getFragmentManager() {
        return this.mFragmentManager;
    }

    public final int getId() {
        return this.mFragmentId;
    }

    @Override // X.AnonymousClass0AR
    @NonNull
    public AnonymousClass0AP getLifecycle() {
        return this.mLifecycleRegistry;
    }

    @NonNull
    @Deprecated
    public AbstractC00540Am getLoaderManager() {
        return new C07210rd(this, getViewModelStore());
    }

    @Nullable
    public final Fragment getParentFragment() {
        return this.mParentFragment;
    }

    @NonNull
    public final Resources getResources() {
        return requireContext().getResources();
    }

    @Deprecated
    public final boolean getRetainInstance() {
        return this.mRetainInstance;
    }

    @Nullable
    public final String getTag() {
        return this.mTag;
    }

    public final int getTargetRequestCode() {
        return this.mTargetRequestCode;
    }

    @NonNull
    public final CharSequence getText(@StringRes int i) {
        return getResources().getText(i);
    }

    @Deprecated
    public boolean getUserVisibleHint() {
        return this.mUserVisibleHint;
    }

    @Nullable
    public View getView() {
        return this.mView;
    }

    @NonNull
    public AnonymousClass0AX<AnonymousClass0AR> getViewLifecycleOwnerLiveData() {
        return this.mViewLifecycleOwnerLiveData;
    }

    @SuppressLint({"KotlinPropertyAccess"})
    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public final boolean hasOptionsMenu() {
        return this.mHasMenu;
    }

    public void initState() {
        initLifecycle();
        this.mWho = UUID.randomUUID().toString();
        this.mAdded = false;
        this.mRemoving = false;
        this.mFromLayout = false;
        this.mInLayout = false;
        this.mRestored = false;
        this.mBackStackNesting = 0;
        this.mFragmentManager = null;
        this.mChildFragmentManager = new C07390s3();
        this.mHost = null;
        this.mFragmentId = 0;
        this.mContainerId = 0;
        this.mTag = null;
        this.mHidden = false;
        this.mDetached = false;
    }

    public final boolean isDetached() {
        return this.mDetached;
    }

    public final boolean isHidden() {
        return this.mHidden;
    }

    public final boolean isInLayout() {
        return this.mInLayout;
    }

    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public final boolean isMenuVisible() {
        return this.mMenuVisible;
    }

    public final boolean isRemoving() {
        return this.mRemoving;
    }

    public final boolean isVisible() {
        View view;
        if (!isAdded() || this.mHidden || (view = this.mView) == null || view.getWindowToken() == null || this.mView.getVisibility() != 0) {
            return false;
        }
        return true;
    }

    @MainThread
    public void onCreateContextMenu(@NonNull ContextMenu contextMenu, @NonNull View view, @Nullable ContextMenu.ContextMenuInfo contextMenuInfo) {
        requireActivity().onCreateContextMenu(contextMenu, view, contextMenuInfo);
    }

    @NonNull
    public LayoutInflater onGetLayoutInflater(@Nullable Bundle bundle) {
        return getLayoutInflater(bundle);
    }

    public void performConfigurationChanged(@NonNull Configuration configuration) {
        onConfigurationChanged(configuration);
        this.mChildFragmentManager.A0R(configuration);
    }

    @NonNull
    public LayoutInflater performGetLayoutInflater(@Nullable Bundle bundle) {
        LayoutInflater onGetLayoutInflater = onGetLayoutInflater(bundle);
        this.mLayoutInflater = onGetLayoutInflater;
        return onGetLayoutInflater;
    }

    public void performLowMemory() {
        onLowMemory();
        this.mChildFragmentManager.A0O();
    }

    public void performSaveInstanceState(Bundle bundle) {
        onSaveInstanceState(bundle);
        this.mSavedStateRegistryController.A01(bundle);
        Parcelable A0G = this.mChildFragmentManager.A0G();
        if (A0G != null) {
            bundle.putParcelable(FragmentActivity.FRAGMENTS_TAG, A0G);
        }
    }

    @NonNull
    public final FragmentActivity requireActivity() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            return activity;
        }
        StringBuilder sb = new StringBuilder("Fragment ");
        sb.append(this);
        sb.append(" not attached to an activity.");
        throw new IllegalStateException(sb.toString());
    }

    @NonNull
    public final Context requireContext() {
        Context context = getContext();
        if (context != null) {
            return context;
        }
        StringBuilder sb = new StringBuilder("Fragment ");
        sb.append(this);
        sb.append(" not attached to a context.");
        throw new IllegalStateException(sb.toString());
    }

    @NonNull
    @Deprecated
    public final AbstractC003209a requireFragmentManager() {
        return getParentFragmentManager();
    }

    @NonNull
    public final Object requireHost() {
        Object host = getHost();
        if (host != null) {
            return host;
        }
        StringBuilder sb = new StringBuilder("Fragment ");
        sb.append(this);
        sb.append(" not attached to a host.");
        throw new IllegalStateException(sb.toString());
    }

    public void setAllowEnterTransitionOverlap(boolean z) {
        ensureAnimationInfo().A08 = Boolean.valueOf(z);
    }

    public void setAllowReturnTransitionOverlap(boolean z) {
        ensureAnimationInfo().A09 = Boolean.valueOf(z);
    }

    public void setAnimatingAway(View view) {
        ensureAnimationInfo().A04 = view;
    }

    public void setAnimator(Animator animator) {
        ensureAnimationInfo().A03 = animator;
    }

    public void setEnterSharedElementCallback(@Nullable AnonymousClass04C r2) {
        ensureAnimationInfo().A05 = r2;
    }

    public void setEnterTransition(@Nullable Object obj) {
        ensureAnimationInfo().A0A = obj;
    }

    public void setExitSharedElementCallback(@Nullable AnonymousClass04C r2) {
        ensureAnimationInfo().A06 = r2;
    }

    public void setExitTransition(@Nullable Object obj) {
        ensureAnimationInfo().A0B = obj;
    }

    public void setHideReplaced(boolean z) {
        ensureAnimationInfo().A0H = z;
    }

    public void setOnStartEnterTransitionListener(AnonymousClass09E r3) {
        ensureAnimationInfo();
        AnonymousClass09C r1 = this.mAnimationInfo;
        AnonymousClass09E r0 = r1.A07;
        if (r3 == r0) {
            return;
        }
        if (r3 == null || r0 == null) {
            if (r1.A0G) {
                r1.A07 = r3;
            }
            if (r3 != null) {
                r3.A9I();
                return;
            }
            return;
        }
        StringBuilder sb = new StringBuilder("Trying to set a replacement startPostponedEnterTransition on ");
        sb.append(this);
        throw new IllegalStateException(sb.toString());
    }

    public void setReenterTransition(@Nullable Object obj) {
        ensureAnimationInfo().A0C = obj;
    }

    public void setReturnTransition(@Nullable Object obj) {
        ensureAnimationInfo().A0D = obj;
    }

    public void setSharedElementEnterTransition(@Nullable Object obj) {
        ensureAnimationInfo().A0E = obj;
    }

    public void setSharedElementReturnTransition(@Nullable Object obj) {
        ensureAnimationInfo().A0F = obj;
    }

    public void setStateAfterAnimating(int i) {
        ensureAnimationInfo().A02 = i;
    }

    public void registerForContextMenu(@NonNull View view) {
        view.setOnCreateContextMenuListener(this);
    }

    public Fragment() {
        this.mState = -1;
        this.mWho = UUID.randomUUID().toString();
        this.mTargetWho = null;
        this.mIsPrimaryNavigationFragment = null;
        this.mChildFragmentManager = new C07390s3();
        this.mMenuVisible = true;
        this.mUserVisibleHint = true;
        this.mPostponedDurationRunnable = new AnonymousClass09A(this);
        this.mMaxState = AnonymousClass0AO.RESUMED;
        this.mViewLifecycleOwnerLiveData = new AnonymousClass0rm<>();
        initLifecycle();
    }

    @ContentView
    public Fragment(@LayoutRes int i) {
        this();
        this.mContentLayoutId = i;
    }

    @NonNull
    @Deprecated
    public static Fragment instantiate(@NonNull Context context, @NonNull String str) {
        return instantiate(context, str, null);
    }

    @NonNull
    @Deprecated
    public static Fragment instantiate(@NonNull Context context, @NonNull String str, @Nullable Bundle bundle) {
        try {
            Fragment fragment = (Fragment) AnonymousClass09Q.A00(context.getClassLoader(), str).getConstructor(new Class[0]).newInstance(new Object[0]);
            if (bundle != null) {
                bundle.setClassLoader(fragment.getClass().getClassLoader());
                fragment.setArguments(bundle);
            }
            return fragment;
        } catch (InstantiationException e) {
            throw new AnonymousClass09D(AnonymousClass006.A07("Unable to instantiate fragment ", str, ": make sure class name exists, is public, and has an empty constructor that is public"), e);
        } catch (IllegalAccessException e2) {
            throw new AnonymousClass09D(AnonymousClass006.A07("Unable to instantiate fragment ", str, ": make sure class name exists, is public, and has an empty constructor that is public"), e2);
        } catch (NoSuchMethodException e3) {
            throw new AnonymousClass09D(AnonymousClass006.A07("Unable to instantiate fragment ", str, ": could not find Fragment constructor"), e3);
        } catch (InvocationTargetException e4) {
            throw new AnonymousClass09D(AnonymousClass006.A07("Unable to instantiate fragment ", str, ": calling Fragment constructor caused an exception"), e4);
        }
    }

    @NonNull
    public final LayoutInflater getLayoutInflater() {
        LayoutInflater layoutInflater = this.mLayoutInflater;
        if (layoutInflater != null) {
            return layoutInflater;
        }
        LayoutInflater onGetLayoutInflater = onGetLayoutInflater(null);
        this.mLayoutInflater = onGetLayoutInflater;
        return onGetLayoutInflater;
    }

    @NonNull
    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    @Deprecated
    public LayoutInflater getLayoutInflater(@Nullable Bundle bundle) {
        AnonymousClass0s9<?> r0 = this.mHost;
        if (r0 != null) {
            LayoutInflater A02 = r0.A02();
            A02.setFactory2(this.mChildFragmentManager.A0N);
            return A02;
        }
        throw new IllegalStateException("onGetLayoutInflater() cannot be executed until the Fragment is attached to the FragmentManager.");
    }

    @NonNull
    public final String getString(@StringRes int i) {
        return getResources().getString(i);
    }

    @NonNull
    public final String getString(@StringRes int i, @Nullable Object... objArr) {
        return getResources().getString(i, objArr);
    }

    @CallSuper
    @MainThread
    @Deprecated
    public void onAttach(@NonNull Activity activity) {
        this.mCalled = true;
    }

    @CallSuper
    @MainThread
    public void onAttach(@NonNull Context context) {
        this.mCalled = true;
        AnonymousClass0s9<?> r0 = this.mHost;
        if (r0 != null && r0.A00 != null) {
            this.mCalled = false;
            this.mCalled = true;
        }
    }

    @CallSuper
    @UiThread
    @Deprecated
    public void onInflate(@NonNull Activity activity, @NonNull AttributeSet attributeSet, @Nullable Bundle bundle) {
        this.mCalled = true;
    }

    @CallSuper
    @UiThread
    public void onInflate(@NonNull Context context, @NonNull AttributeSet attributeSet, @Nullable Bundle bundle) {
        this.mCalled = true;
        AnonymousClass0s9<?> r0 = this.mHost;
        if (r0 != null && r0.A00 != null) {
            this.mCalled = false;
            this.mCalled = true;
        }
    }

    public void postponeEnterTransition() {
        ensureAnimationInfo().A0G = true;
    }

    public final void postponeEnterTransition(long j, @NonNull TimeUnit timeUnit) {
        Handler handler;
        ensureAnimationInfo().A0G = true;
        AbstractC003209a r0 = this.mFragmentManager;
        if (r0 != null) {
            handler = r0.A05.A02;
        } else {
            handler = new Handler(Looper.getMainLooper());
        }
        handler.removeCallbacks(this.mPostponedDurationRunnable);
        handler.postDelayed(this.mPostponedDurationRunnable, timeUnit.toMillis(j));
    }

    public void startActivity(@SuppressLint({"UnknownNullness"}) Intent intent) {
        startActivity(intent, null);
    }

    public void startActivity(@SuppressLint({"UnknownNullness"}) Intent intent, @Nullable Bundle bundle) {
        AnonymousClass0s9<?> r1 = this.mHost;
        if (r1 != null) {
            r1.A05(this, intent, -1, bundle);
            return;
        }
        StringBuilder sb = new StringBuilder("Fragment ");
        sb.append(this);
        sb.append(" not attached to Activity");
        throw new IllegalStateException(sb.toString());
    }

    public void startActivityForResult(@SuppressLint({"UnknownNullness"}) Intent intent, int i) {
        startActivityForResult(intent, i, null);
    }

    public void startActivityForResult(@SuppressLint({"UnknownNullness"}) Intent intent, int i, @Nullable Bundle bundle) {
        AnonymousClass0s9<?> r0 = this.mHost;
        if (r0 != null) {
            r0.A05(this, intent, i, bundle);
            return;
        }
        StringBuilder sb = new StringBuilder("Fragment ");
        sb.append(this);
        sb.append(" not attached to Activity");
        throw new IllegalStateException(sb.toString());
    }
}
