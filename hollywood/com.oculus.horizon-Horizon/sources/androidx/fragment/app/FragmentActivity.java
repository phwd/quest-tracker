package androidx.fragment.app;

import X.AbstractC003209a;
import X.AbstractC00530Ak;
import X.AbstractC00540Am;
import X.AnonymousClass006;
import X.AnonymousClass02C;
import X.AnonymousClass02f;
import X.AnonymousClass034;
import X.AnonymousClass036;
import X.AnonymousClass037;
import X.AnonymousClass04C;
import X.AnonymousClass09P;
import X.AnonymousClass0AN;
import X.AnonymousClass0AO;
import X.AnonymousClass0K6;
import X.AnonymousClass0s9;
import X.C000702p;
import X.C07210rd;
import X.C07280rn;
import X.C07460sp;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.activity.ComponentActivity;
import androidx.annotation.CallSuper;
import androidx.annotation.ContentView;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class FragmentActivity extends ComponentActivity implements AnonymousClass034, AnonymousClass036 {
    public static final String ALLOCATED_REQUEST_INDICIES_TAG = "android:support:request_indicies";
    public static final String FRAGMENTS_TAG = "android:support:fragments";
    public static final int MAX_NUM_PENDING_FRAGMENT_ACTIVITY_RESULTS = 65534;
    public static final String NEXT_CANDIDATE_REQUEST_INDEX_TAG = "android:support:next_request_index";
    public static final String REQUEST_FRAGMENT_WHO_TAG = "android:support:request_fragment_who";
    public static final String TAG = "FragmentActivity";
    public boolean mCreated;
    public final C07280rn mFragmentLifecycleRegistry = new C07280rn(this);
    public final AnonymousClass09P mFragments = new AnonymousClass09P(new AnonymousClass0K6(this));
    public int mNextCandidateRequestIndex;
    public C000702p<String> mPendingFragmentActivityResults;
    public boolean mRequestedPermissionsFromFragment;
    public boolean mResumed;
    public boolean mStartedActivityFromFragment;
    public boolean mStartedIntentSenderFromFragment;
    public boolean mStopped = true;

    public void onAttachFragment(@NonNull Fragment fragment) {
    }

    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    @Deprecated
    public boolean onPrepareOptionsPanel(@Nullable View view, @NonNull Menu menu) {
        return super.onPreparePanel(0, view, menu);
    }

    /* JADX INFO: finally extract failed */
    public void requestPermissionsFromFragment(@NonNull Fragment fragment, @NonNull String[] strArr, int i) {
        if (i == -1) {
            C07460sp.A02(this, strArr, i);
            return;
        }
        checkForValidRequestCode(i);
        try {
            this.mRequestedPermissionsFromFragment = true;
            C07460sp.A02(this, strArr, ((allocateRequestIndex(fragment) + 1) << 16) + (i & 65535));
            this.mRequestedPermissionsFromFragment = false;
        } catch (Throwable th) {
            this.mRequestedPermissionsFromFragment = false;
            throw th;
        }
    }

    private int allocateRequestIndex(@NonNull Fragment fragment) {
        C000702p<String> r4 = this.mPendingFragmentActivityResults;
        if (r4.A01() >= 65534) {
            throw new IllegalStateException("Too many pending Fragment activity results.");
        }
        while (true) {
            int i = this.mNextCandidateRequestIndex;
            if (r4.A01) {
                C000702p.A00(r4);
            }
            if (AnonymousClass02f.A00(r4.A02, r4.A00, i) >= 0) {
                this.mNextCandidateRequestIndex = (i + 1) % MAX_NUM_PENDING_FRAGMENT_ACTIVITY_RESULTS;
            } else {
                r4.A02(i, fragment.mWho);
                this.mNextCandidateRequestIndex = (this.mNextCandidateRequestIndex + 1) % MAX_NUM_PENDING_FRAGMENT_ACTIVITY_RESULTS;
                return i;
            }
        }
    }

    public static void checkForValidRequestCode(int i) {
        if ((i & -65536) != 0) {
            throw new IllegalArgumentException("Can only use lower 16 bits for requestCode");
        }
    }

    public static boolean markState(AbstractC003209a r4, AnonymousClass0AO r5) {
        boolean z = false;
        for (Fragment fragment : r4.A0P.A01()) {
            if (fragment != null) {
                if (fragment.getHost() != null) {
                    z |= markState(fragment.getChildFragmentManager(), r5);
                }
                if (fragment.getLifecycle().A05().isAtLeast(AnonymousClass0AO.STARTED)) {
                    C07280rn.A04(fragment.mLifecycleRegistry, r5);
                    z = true;
                }
            }
        }
        return z;
    }

    @Nullable
    public final View dispatchFragmentsOnCreateView(@Nullable View view, @NonNull String str, @NonNull Context context, @NonNull AttributeSet attributeSet) {
        return this.mFragments.A00.A03.A0N.onCreateView(view, str, context, attributeSet);
    }

    @NonNull
    public AbstractC003209a getSupportFragmentManager() {
        return this.mFragments.A00.A03;
    }

    @CallSuper
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        Object obj;
        String A05;
        Object[] objArr;
        Object obj2;
        this.mFragments.A00.A03.A0P();
        int i3 = i >> 16;
        if (i3 != 0) {
            C000702p<String> r5 = this.mPendingFragmentActivityResults;
            int A00 = AnonymousClass02f.A00(r5.A02, r5.A00, i3 - 1);
            if (A00 < 0 || (obj = r5.A03[A00]) == C000702p.A04) {
                obj = null;
            }
            String str = obj;
            if (A00 >= 0 && (objArr = r5.A03)[A00] != (obj2 = C000702p.A04)) {
                objArr[A00] = obj2;
                r5.A01 = true;
            }
            if (str == null) {
                A05 = "Activity result delivered for unknown Fragment.";
            } else {
                Fragment A0K = this.mFragments.A00.A03.A0K(str);
                if (A0K == null) {
                    A05 = AnonymousClass006.A05("Activity result no fragment exists for who: ", str);
                } else {
                    A0K.onActivityResult(i & 65535, i2, intent);
                    return;
                }
            }
            Log.w(TAG, A05);
            return;
        }
        super.onActivityResult(i, i2, intent);
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity
    public void onCreate(@Nullable Bundle bundle) {
        int length;
        AnonymousClass0s9<?> r1 = this.mFragments.A00;
        r1.A03.A0h(r1, r1, null);
        if (bundle != null) {
            Parcelable parcelable = bundle.getParcelable(FRAGMENTS_TAG);
            AnonymousClass0s9<?> r12 = this.mFragments.A00;
            if (r12 instanceof AbstractC00530Ak) {
                r12.A03.A0S(parcelable);
                if (bundle.containsKey(NEXT_CANDIDATE_REQUEST_INDEX_TAG)) {
                    this.mNextCandidateRequestIndex = bundle.getInt(NEXT_CANDIDATE_REQUEST_INDEX_TAG);
                    int[] intArray = bundle.getIntArray(ALLOCATED_REQUEST_INDICIES_TAG);
                    String[] stringArray = bundle.getStringArray(REQUEST_FRAGMENT_WHO_TAG);
                    if (intArray == null || stringArray == null || (length = intArray.length) != stringArray.length) {
                        Log.w(TAG, "Invalid requestCode mapping in savedInstanceState.");
                    } else {
                        this.mPendingFragmentActivityResults = new C000702p<>(length);
                        for (int i = 0; i < length; i++) {
                            this.mPendingFragmentActivityResults.A02(intArray[i], stringArray[i]);
                        }
                    }
                }
            } else {
                throw new IllegalStateException("Your FragmentHostCallback must implement ViewModelStoreOwner to call restoreSaveState(). Call restoreAllState()  if you're still using retainNestedNonConfig().");
            }
        }
        if (this.mPendingFragmentActivityResults == null) {
            this.mPendingFragmentActivityResults = new C000702p<>();
            this.mNextCandidateRequestIndex = 0;
        }
        super.onCreate(bundle);
        this.mFragmentLifecycleRegistry.A08(AnonymousClass0AN.ON_CREATE);
        AbstractC003209a r13 = this.mFragments.A00.A03;
        r13.A0H = false;
        r13.A0I = false;
        AbstractC003209a.A07(r13, 1);
    }

    public boolean onCreatePanelMenu(int i, @NonNull Menu menu) {
        if (i != 0) {
            return super.onCreatePanelMenu(i, menu);
        }
        boolean onCreatePanelMenu = super.onCreatePanelMenu(i, menu);
        AnonymousClass09P r0 = this.mFragments;
        return onCreatePanelMenu | r0.A00.A03.A0q(menu, getMenuInflater());
    }

    @CallSuper
    public void onMultiWindowModeChanged(boolean z) {
        this.mFragments.A00.A03.A0l(z);
    }

    public void onPanelClosed(int i, @NonNull Menu menu) {
        if (i == 0) {
            this.mFragments.A00.A03.A0T(menu);
        }
        super.onPanelClosed(i, menu);
    }

    @CallSuper
    public void onPictureInPictureModeChanged(boolean z) {
        this.mFragments.A00.A03.A0m(z);
    }

    public boolean onPreparePanel(int i, @Nullable View view, @NonNull Menu menu) {
        if (i == 0) {
            return onPrepareOptionsPanel(view, menu) | this.mFragments.A00.A03.A0p(menu);
        }
        return super.onPreparePanel(i, view, menu);
    }

    @Override // X.AnonymousClass034
    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        Object obj;
        String A05;
        Object[] objArr;
        Object obj2;
        this.mFragments.A00.A03.A0P();
        int i2 = (i >> 16) & 65535;
        if (i2 != 0) {
            C000702p<String> r5 = this.mPendingFragmentActivityResults;
            int A00 = AnonymousClass02f.A00(r5.A02, r5.A00, i2 - 1);
            if (A00 < 0 || (obj = r5.A03[A00]) == C000702p.A04) {
                obj = null;
            }
            String str = obj;
            if (A00 >= 0 && (objArr = r5.A03)[A00] != (obj2 = C000702p.A04)) {
                objArr[A00] = obj2;
                r5.A01 = true;
            }
            if (str == null) {
                A05 = "Activity result delivered for unknown Fragment.";
            } else if (this.mFragments.A00.A03.A0K(str) == null) {
                A05 = AnonymousClass006.A05("Activity result no fragment exists for who: ", str);
            } else {
                return;
            }
            Log.w(TAG, A05);
        }
    }

    public void onResumeFragments() {
        this.mFragmentLifecycleRegistry.A08(AnonymousClass0AN.ON_RESUME);
        AbstractC003209a r1 = this.mFragments.A00.A03;
        r1.A0H = false;
        r1.A0I = false;
        AbstractC003209a.A07(r1, 4);
    }

    public void onStateNotSaved() {
        this.mFragments.A00.A03.A0P();
    }

    public void setEnterSharedElementCallback(@Nullable AnonymousClass04C r2) {
        AnonymousClass037 r0;
        if (r2 != null) {
            r0 = new AnonymousClass037(r2);
        } else {
            r0 = null;
        }
        setEnterSharedElementCallback(r0);
    }

    public void setExitSharedElementCallback(@Nullable AnonymousClass04C r2) {
        AnonymousClass037 r0;
        if (r2 != null) {
            r0 = new AnonymousClass037(r2);
        } else {
            r0 = null;
        }
        setExitSharedElementCallback(r0);
    }

    @Override // X.AnonymousClass036
    public final void validateRequestPermissionsRequestCode(int i) {
        if (!this.mRequestedPermissionsFromFragment && i != -1) {
            checkForValidRequestCode(i);
        }
    }

    private void markFragmentsCreated() {
        do {
        } while (markState(getSupportFragmentManager(), AnonymousClass0AO.CREATED));
    }

    public void dump(@NonNull String str, @Nullable FileDescriptor fileDescriptor, @NonNull PrintWriter printWriter, @Nullable String[] strArr) {
        super.dump(str, fileDescriptor, printWriter, strArr);
        printWriter.print(str);
        printWriter.print("Local FragmentActivity ");
        printWriter.print(Integer.toHexString(System.identityHashCode(this)));
        printWriter.println(" State:");
        String A05 = AnonymousClass006.A05(str, "  ");
        printWriter.print(A05);
        printWriter.print("mCreated=");
        printWriter.print(this.mCreated);
        printWriter.print(" mResumed=");
        printWriter.print(this.mResumed);
        printWriter.print(" mStopped=");
        printWriter.print(this.mStopped);
        if (getApplication() != null) {
            new C07210rd(this, getViewModelStore()).A01(A05, fileDescriptor, printWriter, strArr);
        }
        this.mFragments.A00.A03.A0k(str, fileDescriptor, printWriter, strArr);
    }

    @NonNull
    @Deprecated
    public AbstractC00540Am getSupportLoaderManager() {
        return new C07210rd(this, getViewModelStore());
    }

    public void onConfigurationChanged(@NonNull Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.mFragments.A00.A03.A0P();
        this.mFragments.A00.A03.A0R(configuration);
    }

    public void onDestroy() {
        super.onDestroy();
        this.mFragments.A00.A03.A0N();
        this.mFragmentLifecycleRegistry.A08(AnonymousClass0AN.ON_DESTROY);
    }

    public void onLowMemory() {
        super.onLowMemory();
        this.mFragments.A00.A03.A0O();
    }

    public boolean onMenuItemSelected(int i, @NonNull MenuItem menuItem) {
        if (super.onMenuItemSelected(i, menuItem)) {
            return true;
        }
        if (i == 0) {
            return this.mFragments.A00.A03.A0s(menuItem);
        }
        if (i != 6) {
            return false;
        }
        return this.mFragments.A00.A03.A0r(menuItem);
    }

    @CallSuper
    public void onNewIntent(@SuppressLint({"UnknownNullness"}) Intent intent) {
        super.onNewIntent(intent);
        this.mFragments.A00.A03.A0P();
    }

    public void onPause() {
        super.onPause();
        this.mResumed = false;
        AbstractC003209a.A07(this.mFragments.A00.A03, 3);
        this.mFragmentLifecycleRegistry.A08(AnonymousClass0AN.ON_PAUSE);
    }

    public void onPostResume() {
        super.onPostResume();
        onResumeFragments();
    }

    public void onResume() {
        super.onResume();
        this.mResumed = true;
        this.mFragments.A00.A03.A0P();
        this.mFragments.A00.A03.A0n(true);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.lang.String[] */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        markFragmentsCreated();
        this.mFragmentLifecycleRegistry.A08(AnonymousClass0AN.ON_STOP);
        Parcelable A0G = this.mFragments.A00.A03.A0G();
        if (A0G != null) {
            bundle.putParcelable(FRAGMENTS_TAG, A0G);
        }
        if (this.mPendingFragmentActivityResults.A01() > 0) {
            bundle.putInt(NEXT_CANDIDATE_REQUEST_INDEX_TAG, this.mNextCandidateRequestIndex);
            C000702p<String> r4 = this.mPendingFragmentActivityResults;
            int[] iArr = new int[r4.A01()];
            String[] strArr = new String[r4.A01()];
            for (int i = 0; i < r4.A01(); i++) {
                if (r4.A01) {
                    C000702p.A00(r4);
                }
                iArr[i] = r4.A02[i];
                if (r4.A01) {
                    C000702p.A00(r4);
                }
                strArr[i] = r4.A03[i];
            }
            bundle.putIntArray(ALLOCATED_REQUEST_INDICIES_TAG, iArr);
            bundle.putStringArray(REQUEST_FRAGMENT_WHO_TAG, strArr);
        }
    }

    public void onStart() {
        super.onStart();
        this.mStopped = false;
        if (!this.mCreated) {
            this.mCreated = true;
            AbstractC003209a r1 = this.mFragments.A00.A03;
            r1.A0H = false;
            r1.A0I = false;
            AbstractC003209a.A07(r1, 2);
        }
        this.mFragments.A00.A03.A0P();
        this.mFragments.A00.A03.A0n(true);
        this.mFragmentLifecycleRegistry.A08(AnonymousClass0AN.ON_START);
        AbstractC003209a r12 = this.mFragments.A00.A03;
        r12.A0H = false;
        r12.A0I = false;
        AbstractC003209a.A07(r12, 3);
    }

    public void onStop() {
        super.onStop();
        this.mStopped = true;
        markFragmentsCreated();
        AbstractC003209a r1 = this.mFragments.A00.A03;
        r1.A0I = true;
        AbstractC003209a.A07(r1, 2);
        this.mFragmentLifecycleRegistry.A08(AnonymousClass0AN.ON_STOP);
    }

    public void supportFinishAfterTransition() {
        finishAfterTransition();
    }

    @Deprecated
    public void supportInvalidateOptionsMenu() {
        invalidateOptionsMenu();
    }

    public void supportPostponeEnterTransition() {
        postponeEnterTransition();
    }

    public void supportStartPostponedEnterTransition() {
        startPostponedEnterTransition();
    }

    public FragmentActivity() {
    }

    @ContentView
    public FragmentActivity(@LayoutRes int i) {
        super(i);
    }

    @Nullable
    public View onCreateView(@Nullable View view, @NonNull String str, @NonNull Context context, @NonNull AttributeSet attributeSet) {
        View dispatchFragmentsOnCreateView = dispatchFragmentsOnCreateView(view, str, context, attributeSet);
        return dispatchFragmentsOnCreateView == null ? super.onCreateView(view, str, context, attributeSet) : dispatchFragmentsOnCreateView;
    }

    @Nullable
    public View onCreateView(@NonNull String str, @NonNull Context context, @NonNull AttributeSet attributeSet) {
        View dispatchFragmentsOnCreateView = dispatchFragmentsOnCreateView(null, str, context, attributeSet);
        return dispatchFragmentsOnCreateView == null ? super.onCreateView(str, context, attributeSet) : dispatchFragmentsOnCreateView;
    }

    public void startActivityForResult(@SuppressLint({"UnknownNullness"}) Intent intent, int i) {
        if (!this.mStartedActivityFromFragment && i != -1) {
            checkForValidRequestCode(i);
        }
        super.startActivityForResult(intent, i);
    }

    public void startActivityForResult(@SuppressLint({"UnknownNullness"}) Intent intent, int i, @Nullable Bundle bundle) {
        if (!this.mStartedActivityFromFragment && i != -1) {
            checkForValidRequestCode(i);
        }
        super.startActivityForResult(intent, i, bundle);
    }

    public void startActivityFromFragment(@NonNull Fragment fragment, @SuppressLint({"UnknownNullness"}) Intent intent, int i) {
        startActivityFromFragment(fragment, intent, i, null);
    }

    public void startActivityFromFragment(@NonNull Fragment fragment, @SuppressLint({"UnknownNullness"}) Intent intent, int i, @Nullable Bundle bundle) {
        this.mStartedActivityFromFragment = true;
        if (i == -1) {
            try {
                startActivityForResult(intent, -1, bundle);
            } catch (Throwable th) {
                this.mStartedActivityFromFragment = false;
                throw th;
            }
        } else {
            checkForValidRequestCode(i);
            startActivityForResult(intent, ((allocateRequestIndex(fragment) + 1) << 16) + (i & 65535), bundle);
        }
        this.mStartedActivityFromFragment = false;
    }

    @Override // android.app.Activity
    public void startIntentSenderForResult(@SuppressLint({"UnknownNullness"}) IntentSender intentSender, int i, @Nullable Intent intent, int i2, int i3, int i4) throws IntentSender.SendIntentException {
        if (!this.mStartedIntentSenderFromFragment && i != -1) {
            checkForValidRequestCode(i);
        }
        super.startIntentSenderForResult(intentSender, i, intent, i2, i3, i4);
    }

    @Override // android.app.Activity
    public void startIntentSenderForResult(@SuppressLint({"UnknownNullness"}) IntentSender intentSender, int i, @Nullable Intent intent, int i2, int i3, int i4, @Nullable Bundle bundle) throws IntentSender.SendIntentException {
        if (!this.mStartedIntentSenderFromFragment && i != -1) {
            checkForValidRequestCode(i);
        }
        super.startIntentSenderForResult(intentSender, i, intent, i2, i3, i4, bundle);
    }

    public void startIntentSenderFromFragment(@NonNull Fragment fragment, @SuppressLint({"UnknownNullness"}) IntentSender intentSender, int i, @Nullable Intent intent, int i2, int i3, int i4, @Nullable Bundle bundle) throws IntentSender.SendIntentException {
        this.mStartedIntentSenderFromFragment = true;
        if (i == -1) {
            try {
                startIntentSenderForResult(intentSender, i, intent, i2, i3, i4, bundle);
            } catch (Throwable th) {
                this.mStartedIntentSenderFromFragment = false;
                throw th;
            }
        } else {
            checkForValidRequestCode(i);
            startIntentSenderForResult(intentSender, ((allocateRequestIndex(fragment) + 1) << 16) + (i & 65535), intent, i2, i3, i4, bundle);
        }
        this.mStartedIntentSenderFromFragment = false;
    }
}
