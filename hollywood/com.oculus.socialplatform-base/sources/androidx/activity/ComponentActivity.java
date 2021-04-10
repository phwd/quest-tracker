package androidx.activity;

import X.AbstractC00480Al;
import X.AbstractC05180ub;
import X.AbstractC05230uw;
import X.AnonymousClass01N;
import X.AnonymousClass01O;
import X.AnonymousClass01Q;
import X.AnonymousClass0AO;
import X.AnonymousClass0AP;
import X.AnonymousClass0AQ;
import X.AnonymousClass0AS;
import X.AnonymousClass0Ae;
import X.AnonymousClass0Ah;
import X.AnonymousClass0C4;
import X.AnonymousClass0C5;
import X.AnonymousClass0Vy;
import X.AnonymousClass0uv;
import X.AnonymousClass0wo;
import X.C00470Ak;
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

public class ComponentActivity extends androidx.core.app.ComponentActivity implements AnonymousClass0AS, AbstractC00480Al, AbstractC05180ub, AnonymousClass0wo {
    @LayoutRes
    public int mContentLayoutId;
    public AnonymousClass0Ah mDefaultFactory;
    public final AnonymousClass0uv mLifecycleRegistry;
    public final AnonymousClass01Q mOnBackPressedDispatcher;
    public final AnonymousClass0C5 mSavedStateRegistryController;
    public C00470Ak mViewModelStore;

    @Nullable
    @Deprecated
    public Object onRetainCustomNonConfigurationInstance() {
        return null;
    }

    @Nullable
    public final Object onRetainNonConfigurationInstance() {
        AnonymousClass01O r0;
        C00470Ak r1 = this.mViewModelStore;
        if (r1 == null && ((r0 = (AnonymousClass01O) getLastNonConfigurationInstance()) == null || (r1 = r0.A00) == null)) {
            return null;
        }
        AnonymousClass01O r02 = new AnonymousClass01O();
        r02.A00 = r1;
        return r02;
    }

    @Override // X.AbstractC05180ub
    @NonNull
    public final AnonymousClass0C4 getSavedStateRegistry() {
        return this.mSavedStateRegistryController.A00;
    }

    @MainThread
    public void onBackPressed() {
        this.mOnBackPressedDispatcher.A00();
    }

    @NonNull
    public AnonymousClass0Ah getDefaultViewModelProviderFactory() {
        Bundle bundle;
        if (getApplication() != null) {
            AnonymousClass0Ah r2 = this.mDefaultFactory;
            if (r2 != null) {
                return r2;
            }
            Application application = getApplication();
            if (getIntent() != null) {
                bundle = getIntent().getExtras();
            } else {
                bundle = null;
            }
            AnonymousClass0Vy r22 = new AnonymousClass0Vy(application, this, bundle);
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

    @Override // X.AnonymousClass0AS, androidx.core.app.ComponentActivity
    @NonNull
    public AnonymousClass0AQ getLifecycle() {
        return this.mLifecycleRegistry;
    }

    @Override // X.AnonymousClass0wo
    @NonNull
    public final AnonymousClass01Q getOnBackPressedDispatcher() {
        return this.mOnBackPressedDispatcher;
    }

    @Override // X.AbstractC00480Al
    @NonNull
    public C00470Ak getViewModelStore() {
        if (getApplication() != null) {
            C00470Ak r0 = this.mViewModelStore;
            if (r0 != null) {
                return r0;
            }
            AnonymousClass01O r02 = (AnonymousClass01O) getLastNonConfigurationInstance();
            if (r02 != null) {
                this.mViewModelStore = r02.A00;
            }
            C00470Ak r03 = this.mViewModelStore;
            if (r03 != null) {
                return r03;
            }
            C00470Ak r04 = new C00470Ak();
            this.mViewModelStore = r04;
            return r04;
        }
        throw new IllegalStateException("Your activity is not yet attached to the Application instance. You can't request ViewModel before onCreate call.");
    }

    @Override // androidx.core.app.ComponentActivity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.mSavedStateRegistryController.A00(bundle);
        AnonymousClass0Ae.A00(this);
        int i = this.mContentLayoutId;
        if (i != 0) {
            setContentView(i);
        }
    }

    @Override // androidx.core.app.ComponentActivity
    @CallSuper
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        AnonymousClass0AQ lifecycle = getLifecycle();
        if (lifecycle instanceof AnonymousClass0uv) {
            AnonymousClass0uv.A04((AnonymousClass0uv) lifecycle, AnonymousClass0AP.CREATED);
        }
        super.onSaveInstanceState(bundle);
        this.mSavedStateRegistryController.A01(bundle);
    }

    public ComponentActivity() {
        this.mLifecycleRegistry = new AnonymousClass0uv(this);
        this.mSavedStateRegistryController = new AnonymousClass0C5(this);
        this.mOnBackPressedDispatcher = new AnonymousClass01Q(new AnonymousClass01N(this));
        AnonymousClass0AQ lifecycle = getLifecycle();
        if (lifecycle != null) {
            lifecycle.A06(new AbstractC05230uw() {
                /* class androidx.activity.ComponentActivity.AnonymousClass2 */

                @Override // X.AbstractC05230uw
                public final void A87(@NonNull AnonymousClass0AS r2, @NonNull AnonymousClass0AO r3) {
                    Window window;
                    View peekDecorView;
                    if (r3 == AnonymousClass0AO.ON_STOP && (window = ComponentActivity.this.getWindow()) != null && (peekDecorView = window.peekDecorView()) != null) {
                        peekDecorView.cancelPendingInputEvents();
                    }
                }
            });
            getLifecycle().A06(new AbstractC05230uw() {
                /* class androidx.activity.ComponentActivity.AnonymousClass3 */

                @Override // X.AbstractC05230uw
                public final void A87(@NonNull AnonymousClass0AS r3, @NonNull AnonymousClass0AO r4) {
                    if (r4 == AnonymousClass0AO.ON_DESTROY) {
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
