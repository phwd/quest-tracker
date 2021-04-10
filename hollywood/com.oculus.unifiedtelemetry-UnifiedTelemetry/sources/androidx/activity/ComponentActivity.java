package androidx.activity;

import X.AN;
import X.AO;
import X.AP;
import X.AR;
import X.AbstractC0047Ak;
import X.AbstractC0305ad;
import X.Ad;
import X.Ag;
import X.AnonymousClass1N;
import X.AnonymousClass1O;
import X.AnonymousClass1Q;
import X.Bz;
import X.C0;
import X.C0046Aj;
import X.C0102Ig;
import X.Zg;
import X.Zw;
import X.Zx;
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

public class ComponentActivity extends androidx.core.app.ComponentActivity implements AR, AbstractC0047Ak, Zg, AbstractC0305ad {
    @LayoutRes
    public int mContentLayoutId;
    public Ag mDefaultFactory;
    public final Zw mLifecycleRegistry;
    public final AnonymousClass1Q mOnBackPressedDispatcher;
    public final C0 mSavedStateRegistryController;
    public C0046Aj mViewModelStore;

    @Nullable
    @Deprecated
    public Object onRetainCustomNonConfigurationInstance() {
        return null;
    }

    @Nullable
    public final Object onRetainNonConfigurationInstance() {
        AnonymousClass1O r0;
        C0046Aj aj = this.mViewModelStore;
        if (aj == null && ((r0 = (AnonymousClass1O) getLastNonConfigurationInstance()) == null || (aj = r0.A00) == null)) {
            return null;
        }
        AnonymousClass1O r02 = new AnonymousClass1O();
        r02.A00 = aj;
        return r02;
    }

    @Override // X.Zg
    @NonNull
    public final Bz getSavedStateRegistry() {
        return this.mSavedStateRegistryController.A00;
    }

    @MainThread
    public void onBackPressed() {
        this.mOnBackPressedDispatcher.A00();
    }

    @NonNull
    public Ag getDefaultViewModelProviderFactory() {
        Bundle bundle;
        if (getApplication() != null) {
            Ag ag = this.mDefaultFactory;
            if (ag != null) {
                return ag;
            }
            Application application = getApplication();
            if (getIntent() != null) {
                bundle = getIntent().getExtras();
            } else {
                bundle = null;
            }
            C0102Ig ig = new C0102Ig(application, this, bundle);
            this.mDefaultFactory = ig;
            return ig;
        }
        throw new IllegalStateException("Your activity is not yet attached to the Application instance. You can't request ViewModel before onCreate call.");
    }

    @Nullable
    @Deprecated
    public Object getLastCustomNonConfigurationInstance() {
        getLastNonConfigurationInstance();
        return null;
    }

    @Override // X.AbstractC0047Ak
    @NonNull
    public C0046Aj getViewModelStore() {
        if (getApplication() != null) {
            C0046Aj aj = this.mViewModelStore;
            if (aj != null) {
                return aj;
            }
            AnonymousClass1O r0 = (AnonymousClass1O) getLastNonConfigurationInstance();
            if (r0 != null) {
                this.mViewModelStore = r0.A00;
            }
            C0046Aj aj2 = this.mViewModelStore;
            if (aj2 != null) {
                return aj2;
            }
            C0046Aj aj3 = new C0046Aj();
            this.mViewModelStore = aj3;
            return aj3;
        }
        throw new IllegalStateException("Your activity is not yet attached to the Application instance. You can't request ViewModel before onCreate call.");
    }

    @Override // androidx.core.app.ComponentActivity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.mSavedStateRegistryController.A00(bundle);
        Ad.A00(this);
        int i = this.mContentLayoutId;
        if (i != 0) {
            setContentView(i);
        }
    }

    @Override // androidx.core.app.ComponentActivity
    @CallSuper
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        AP lifecycle = getLifecycle();
        if (lifecycle instanceof Zw) {
            Zw.A04((Zw) lifecycle, AO.CREATED);
        }
        super.onSaveInstanceState(bundle);
        this.mSavedStateRegistryController.A01(bundle);
    }

    @Override // androidx.core.app.ComponentActivity, X.AR
    @NonNull
    public AP getLifecycle() {
        return this.mLifecycleRegistry;
    }

    @Override // X.AbstractC0305ad
    @NonNull
    public final AnonymousClass1Q getOnBackPressedDispatcher() {
        return this.mOnBackPressedDispatcher;
    }

    public ComponentActivity() {
        this.mLifecycleRegistry = new Zw(this);
        this.mSavedStateRegistryController = new C0(this);
        this.mOnBackPressedDispatcher = new AnonymousClass1Q(new AnonymousClass1N(this));
        AP lifecycle = getLifecycle();
        if (lifecycle != null) {
            lifecycle.A06(new Zx() {
                /* class androidx.activity.ComponentActivity.AnonymousClass2 */

                @Override // X.Zx
                public final void A42(@NonNull AR ar, @NonNull AN an) {
                    Window window;
                    View peekDecorView;
                    if (an == AN.ON_STOP && (window = ComponentActivity.this.getWindow()) != null && (peekDecorView = window.peekDecorView()) != null) {
                        peekDecorView.cancelPendingInputEvents();
                    }
                }
            });
            getLifecycle().A06(new Zx() {
                /* class androidx.activity.ComponentActivity.AnonymousClass3 */

                @Override // X.Zx
                public final void A42(@NonNull AR ar, @NonNull AN an) {
                    if (an == AN.ON_DESTROY) {
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
