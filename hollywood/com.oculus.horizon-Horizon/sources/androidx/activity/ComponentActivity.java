package androidx.activity;

import X.AbstractC00530Ak;
import X.AbstractC07170rP;
import X.AbstractC07290ro;
import X.AbstractC07560sz;
import X.AnonymousClass01N;
import X.AnonymousClass01O;
import X.AnonymousClass01Q;
import X.AnonymousClass0AN;
import X.AnonymousClass0AO;
import X.AnonymousClass0AP;
import X.AnonymousClass0AR;
import X.AnonymousClass0Ad;
import X.AnonymousClass0Ag;
import X.AnonymousClass0C0;
import X.AnonymousClass0C1;
import X.AnonymousClass0Jm;
import X.C00520Aj;
import X.C07280rn;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import androidx.annotation.CallSuper;
import androidx.annotation.ContentView;
import androidx.annotation.LayoutRes;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ComponentActivity extends androidx.core.app.ComponentActivity implements AnonymousClass0AR, AbstractC00530Ak, AbstractC07170rP, AbstractC07560sz {
    @LayoutRes
    public int mContentLayoutId;
    public AnonymousClass0Ag mDefaultFactory;
    public final C07280rn mLifecycleRegistry;
    public final AnonymousClass01Q mOnBackPressedDispatcher;
    public final AnonymousClass0C1 mSavedStateRegistryController;
    public C00520Aj mViewModelStore;

    @Nullable
    @Deprecated
    public Object onRetainCustomNonConfigurationInstance() {
        return null;
    }

    @Nullable
    public final Object onRetainNonConfigurationInstance() {
        AnonymousClass01O r0;
        C00520Aj r1 = this.mViewModelStore;
        if (r1 == null && ((r0 = (AnonymousClass01O) getLastNonConfigurationInstance()) == null || (r1 = r0.A00) == null)) {
            return null;
        }
        AnonymousClass01O r02 = new AnonymousClass01O();
        r02.A00 = r1;
        return r02;
    }

    @Override // X.AbstractC07170rP
    @NonNull
    public final AnonymousClass0C0 getSavedStateRegistry() {
        return this.mSavedStateRegistryController.A00;
    }

    @MainThread
    public void onBackPressed() {
        this.mOnBackPressedDispatcher.A00();
    }

    @NonNull
    public AnonymousClass0Ag getDefaultViewModelProviderFactory() {
        Bundle bundle;
        if (getApplication() != null) {
            AnonymousClass0Ag r2 = this.mDefaultFactory;
            if (r2 != null) {
                return r2;
            }
            Application application = getApplication();
            if (getIntent() != null) {
                bundle = getIntent().getExtras();
            } else {
                bundle = null;
            }
            AnonymousClass0Jm r22 = new AnonymousClass0Jm(application, this, bundle);
            this.mDefaultFactory = r22;
            return r22;
        }
        throw new IllegalStateException("Your activity is not yet attached to the Application instance. You can't request ViewModel before onCreate call.");
    }

    @Nullable
    @Deprecated
    public Object getLastCustomNonConfigurationInstance() {
        getLastNonConfigurationInstance();
        return null;
    }

    @Override // androidx.core.app.ComponentActivity, X.AnonymousClass0AR
    @NonNull
    public AnonymousClass0AP getLifecycle() {
        return this.mLifecycleRegistry;
    }

    @Override // X.AbstractC07560sz
    @NonNull
    public final AnonymousClass01Q getOnBackPressedDispatcher() {
        return this.mOnBackPressedDispatcher;
    }

    @Override // X.AbstractC00530Ak
    @NonNull
    public C00520Aj getViewModelStore() {
        if (getApplication() != null) {
            C00520Aj r0 = this.mViewModelStore;
            if (r0 != null) {
                return r0;
            }
            AnonymousClass01O r02 = (AnonymousClass01O) getLastNonConfigurationInstance();
            if (r02 != null) {
                this.mViewModelStore = r02.A00;
            }
            C00520Aj r03 = this.mViewModelStore;
            if (r03 != null) {
                return r03;
            }
            C00520Aj r04 = new C00520Aj();
            this.mViewModelStore = r04;
            return r04;
        }
        throw new IllegalStateException("Your activity is not yet attached to the Application instance. You can't request ViewModel before onCreate call.");
    }

    @Override // androidx.core.app.ComponentActivity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.mSavedStateRegistryController.A00(bundle);
        AnonymousClass0Ad.A00(this);
        int i = this.mContentLayoutId;
        if (i != 0) {
            setContentView(i);
        }
    }

    @Override // androidx.core.app.ComponentActivity
    @CallSuper
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        AnonymousClass0AP lifecycle = getLifecycle();
        if (lifecycle instanceof C07280rn) {
            C07280rn.A04((C07280rn) lifecycle, AnonymousClass0AO.CREATED);
        }
        super.onSaveInstanceState(bundle);
        this.mSavedStateRegistryController.A01(bundle);
    }

    public ComponentActivity() {
        this.mLifecycleRegistry = new C07280rn(this);
        this.mSavedStateRegistryController = new AnonymousClass0C1(this);
        this.mOnBackPressedDispatcher = new AnonymousClass01Q(new AnonymousClass01N(this));
        AnonymousClass0AP lifecycle = getLifecycle();
        if (lifecycle != null) {
            lifecycle.A06(new AbstractC07290ro() {
                /* class androidx.activity.ComponentActivity.AnonymousClass2 */

                @Override // X.AbstractC07290ro
                public final void A70(@NonNull AnonymousClass0AR r2, @NonNull AnonymousClass0AN r3) {
                    Window window;
                    View peekDecorView;
                    if (r3 == AnonymousClass0AN.ON_STOP && (window = ComponentActivity.this.getWindow()) != null && (peekDecorView = window.peekDecorView()) != null) {
                        peekDecorView.cancelPendingInputEvents();
                    }
                }
            });
            getLifecycle().A06(new AbstractC07290ro() {
                /* class androidx.activity.ComponentActivity.AnonymousClass3 */

                @Override // X.AbstractC07290ro
                public final void A70(@NonNull AnonymousClass0AR r3, @NonNull AnonymousClass0AN r4) {
                    if (r4 == AnonymousClass0AN.ON_DESTROY) {
                        ComponentActivity componentActivity = ComponentActivity.this;
                        if (!componentActivity.isChangingConfigurations()) {
                            componentActivity.getViewModelStore().A00();
                        }
                    }
                }
            });
            if (Build.VERSION.SDK_INT <= 23) {
                getLifecycle().A06(new ImmLeaksCleaner(this));
                return;
            }
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
