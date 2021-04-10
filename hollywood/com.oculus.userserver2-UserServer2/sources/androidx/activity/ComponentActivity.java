package androidx.activity;

import X.AbstractC0041Bq;
import X.AnonymousClass1O;
import X.AnonymousClass1P;
import X.AnonymousClass1R;
import X.Bs;
import X.C5;
import X.C8;
import X.CB;
import X.CC;
import X.CQ;
import X.Ds;
import X.Dt;
import X.EnumC0039Bo;
import X.EnumC0040Bp;
import X.TM;
import X.Tc;
import X.Td;
import X.UI;
import android.app.Application;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import androidx.annotation.CallSuper;
import androidx.annotation.ContentView;
import androidx.annotation.LayoutRes;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ComponentActivity extends androidx.core.app.ComponentActivity implements Bs, CC, TM, UI {
    @LayoutRes
    public int mContentLayoutId;
    public C8 mDefaultFactory;
    public final Tc mLifecycleRegistry;
    public final AnonymousClass1R mOnBackPressedDispatcher;
    public final Dt mSavedStateRegistryController;
    public CB mViewModelStore;

    @Nullable
    @Deprecated
    public Object onRetainCustomNonConfigurationInstance() {
        return null;
    }

    @Nullable
    public final Object onRetainNonConfigurationInstance() {
        AnonymousClass1P r0;
        CB cb = this.mViewModelStore;
        if (cb == null && ((r0 = (AnonymousClass1P) getLastNonConfigurationInstance()) == null || (cb = r0.A00) == null)) {
            return null;
        }
        AnonymousClass1P r02 = new AnonymousClass1P();
        r02.A00 = cb;
        return r02;
    }

    @Override // X.TM
    @NonNull
    public final Ds getSavedStateRegistry() {
        return this.mSavedStateRegistryController.A00;
    }

    @MainThread
    public void onBackPressed() {
        this.mOnBackPressedDispatcher.A00();
    }

    @NonNull
    public C8 getDefaultViewModelProviderFactory() {
        Bundle bundle;
        if (getApplication() != null) {
            C8 c8 = this.mDefaultFactory;
            if (c8 != null) {
                return c8;
            }
            Application application = getApplication();
            if (getIntent() != null) {
                bundle = getIntent().getExtras();
            } else {
                bundle = null;
            }
            CQ cq = new CQ(application, this, bundle);
            this.mDefaultFactory = cq;
            return cq;
        }
        throw new IllegalStateException("Your activity is not yet attached to the Application instance. You can't request ViewModel before onCreate call.");
    }

    @Nullable
    @Deprecated
    public Object getLastCustomNonConfigurationInstance() {
        getLastNonConfigurationInstance();
        return null;
    }

    @Override // androidx.core.app.ComponentActivity, X.Bs
    @NonNull
    public AbstractC0041Bq getLifecycle() {
        return this.mLifecycleRegistry;
    }

    @Override // X.UI
    @NonNull
    public final AnonymousClass1R getOnBackPressedDispatcher() {
        return this.mOnBackPressedDispatcher;
    }

    @Override // X.CC
    @NonNull
    public CB getViewModelStore() {
        if (getApplication() != null) {
            CB cb = this.mViewModelStore;
            if (cb != null) {
                return cb;
            }
            AnonymousClass1P r0 = (AnonymousClass1P) getLastNonConfigurationInstance();
            if (r0 != null) {
                this.mViewModelStore = r0.A00;
            }
            CB cb2 = this.mViewModelStore;
            if (cb2 != null) {
                return cb2;
            }
            CB cb3 = new CB();
            this.mViewModelStore = cb3;
            return cb3;
        }
        throw new IllegalStateException("Your activity is not yet attached to the Application instance. You can't request ViewModel before onCreate call.");
    }

    @Override // androidx.core.app.ComponentActivity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.mSavedStateRegistryController.A00(bundle);
        C5.A00(this);
        int i = this.mContentLayoutId;
        if (i != 0) {
            setContentView(i);
        }
    }

    @Override // androidx.core.app.ComponentActivity
    @CallSuper
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        AbstractC0041Bq lifecycle = getLifecycle();
        if (lifecycle instanceof Tc) {
            Tc.A04((Tc) lifecycle, EnumC0040Bp.CREATED);
        }
        super.onSaveInstanceState(bundle);
        this.mSavedStateRegistryController.A01(bundle);
    }

    public ComponentActivity() {
        this.mLifecycleRegistry = new Tc(this);
        this.mSavedStateRegistryController = new Dt(this);
        this.mOnBackPressedDispatcher = new AnonymousClass1R(new AnonymousClass1O(this));
        AbstractC0041Bq lifecycle = getLifecycle();
        if (lifecycle != null) {
            lifecycle.A05(new Td() {
                /* class androidx.activity.ComponentActivity.AnonymousClass2 */

                @Override // X.Td
                public final void A2i(@NonNull Bs bs, @NonNull EnumC0039Bo bo) {
                    Window window;
                    View peekDecorView;
                    if (bo == EnumC0039Bo.ON_STOP && (window = ComponentActivity.this.getWindow()) != null && (peekDecorView = window.peekDecorView()) != null) {
                        peekDecorView.cancelPendingInputEvents();
                    }
                }
            });
            getLifecycle().A05(new Td() {
                /* class androidx.activity.ComponentActivity.AnonymousClass3 */

                @Override // X.Td
                public final void A2i(@NonNull Bs bs, @NonNull EnumC0039Bo bo) {
                    if (bo == EnumC0039Bo.ON_DESTROY) {
                        ComponentActivity componentActivity = ComponentActivity.this;
                        if (!componentActivity.isChangingConfigurations()) {
                            componentActivity.getViewModelStore().A00();
                        }
                    }
                }
            });
            return;
        }
        throw new IllegalStateException("getLifecycle() returned null in ComponentActivity's constructor. Please make sure you are lazily constructing your Lifecycle in the first call to getLifecycle() rather than relying on field initialization.");
    }

    @ContentView
    public ComponentActivity(@LayoutRes int i) {
        this();
        this.mContentLayoutId = i;
    }
}
