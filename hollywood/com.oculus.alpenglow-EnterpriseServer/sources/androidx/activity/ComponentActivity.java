package androidx.activity;

import X.AbstractC01030Da;
import X.AbstractC01120Dp;
import X.AbstractC01160Dt;
import X.AbstractC03380cC;
import X.AbstractC03550cd;
import X.AnonymousClass01O;
import X.AnonymousClass01P;
import X.AnonymousClass01R;
import X.AnonymousClass0DW;
import X.AnonymousClass0DX;
import X.AnonymousClass0DY;
import X.AnonymousClass0Dm;
import X.AnonymousClass0GJ;
import X.AnonymousClass0GK;
import X.AnonymousClass0MD;
import X.AnonymousClass0f4;
import X.C01150Ds;
import X.C03540cc;
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

public class ComponentActivity extends androidx.core.app.ComponentActivity implements AbstractC01030Da, AbstractC01160Dt, AbstractC03380cC, AnonymousClass0f4 {
    @LayoutRes
    public int mContentLayoutId;
    public AbstractC01120Dp mDefaultFactory;
    public final C03540cc mLifecycleRegistry;
    public final AnonymousClass01R mOnBackPressedDispatcher;
    public final AnonymousClass0GK mSavedStateRegistryController;
    public C01150Ds mViewModelStore;

    @Nullable
    @Deprecated
    public Object onRetainCustomNonConfigurationInstance() {
        return null;
    }

    @Nullable
    public final Object onRetainNonConfigurationInstance() {
        AnonymousClass01P r0;
        C01150Ds r1 = this.mViewModelStore;
        if (r1 == null && ((r0 = (AnonymousClass01P) getLastNonConfigurationInstance()) == null || (r1 = r0.A00) == null)) {
            return null;
        }
        AnonymousClass01P r02 = new AnonymousClass01P();
        r02.A00 = r1;
        return r02;
    }

    @Override // androidx.core.app.ComponentActivity, X.AbstractC01030Da
    @NonNull
    public AnonymousClass0DY getLifecycle() {
        return this.mLifecycleRegistry;
    }

    @Override // X.AnonymousClass0f4
    @NonNull
    public final AnonymousClass01R getOnBackPressedDispatcher() {
        return this.mOnBackPressedDispatcher;
    }

    @Override // X.AbstractC03380cC
    @NonNull
    public final AnonymousClass0GJ getSavedStateRegistry() {
        return this.mSavedStateRegistryController.A00;
    }

    @MainThread
    public void onBackPressed() {
        this.mOnBackPressedDispatcher.A00();
    }

    @NonNull
    public AbstractC01120Dp getDefaultViewModelProviderFactory() {
        Bundle bundle;
        if (getApplication() != null) {
            AbstractC01120Dp r2 = this.mDefaultFactory;
            if (r2 != null) {
                return r2;
            }
            Application application = getApplication();
            if (getIntent() != null) {
                bundle = getIntent().getExtras();
            } else {
                bundle = null;
            }
            AnonymousClass0MD r22 = new AnonymousClass0MD(application, this, bundle);
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

    @Override // X.AbstractC01160Dt
    @NonNull
    public C01150Ds getViewModelStore() {
        if (getApplication() != null) {
            C01150Ds r0 = this.mViewModelStore;
            if (r0 != null) {
                return r0;
            }
            AnonymousClass01P r02 = (AnonymousClass01P) getLastNonConfigurationInstance();
            if (r02 != null) {
                this.mViewModelStore = r02.A00;
            }
            C01150Ds r03 = this.mViewModelStore;
            if (r03 != null) {
                return r03;
            }
            C01150Ds r04 = new C01150Ds();
            this.mViewModelStore = r04;
            return r04;
        }
        throw new IllegalStateException("Your activity is not yet attached to the Application instance. You can't request ViewModel before onCreate call.");
    }

    @Override // androidx.core.app.ComponentActivity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.mSavedStateRegistryController.A00(bundle);
        AnonymousClass0Dm.A00(this);
        int i = this.mContentLayoutId;
        if (i != 0) {
            setContentView(i);
        }
    }

    @Override // androidx.core.app.ComponentActivity
    @CallSuper
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        AnonymousClass0DY lifecycle = getLifecycle();
        if (lifecycle instanceof C03540cc) {
            C03540cc.A04((C03540cc) lifecycle, AnonymousClass0DX.CREATED);
        }
        super.onSaveInstanceState(bundle);
        this.mSavedStateRegistryController.A01(bundle);
    }

    public ComponentActivity() {
        this.mLifecycleRegistry = new C03540cc(this);
        this.mSavedStateRegistryController = new AnonymousClass0GK(this);
        this.mOnBackPressedDispatcher = new AnonymousClass01R(new AnonymousClass01O(this));
        AnonymousClass0DY lifecycle = getLifecycle();
        if (lifecycle != null) {
            lifecycle.A06(new AbstractC03550cd() {
                /* class androidx.activity.ComponentActivity.AnonymousClass2 */

                @Override // X.AbstractC03550cd
                public final void A6c(@NonNull AbstractC01030Da r2, @NonNull AnonymousClass0DW r3) {
                    Window window;
                    View peekDecorView;
                    if (r3 == AnonymousClass0DW.ON_STOP && (window = ComponentActivity.this.getWindow()) != null && (peekDecorView = window.peekDecorView()) != null) {
                        peekDecorView.cancelPendingInputEvents();
                    }
                }
            });
            getLifecycle().A06(new AbstractC03550cd() {
                /* class androidx.activity.ComponentActivity.AnonymousClass3 */

                @Override // X.AbstractC03550cd
                public final void A6c(@NonNull AbstractC01030Da r3, @NonNull AnonymousClass0DW r4) {
                    if (r4 == AnonymousClass0DW.ON_DESTROY) {
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
