package androidx.appcompat.app;

import X.AbstractC06001Eq;
import X.AbstractC11211rM;
import X.AbstractC11251rX;
import X.AbstractC11301rk;
import X.AbstractC11461s1;
import X.AbstractC11541s9;
import X.AnonymousClass006;
import X.AnonymousClass02n;
import X.AnonymousClass04I;
import X.AnonymousClass04J;
import X.AnonymousClass1rJ;
import X.AnonymousClass1rR;
import X.AnonymousClass1sE;
import X.C000703c;
import X.C10821pj;
import X.C10911qB;
import X.C11201rK;
import X.C11391ru;
import X.C11561sB;
import X.C11571sK;
import X.Window$CallbackC11241rW;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.annotation.CallSuper;
import androidx.annotation.ContentView;
import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;
import com.oculus.socialplatform.R;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class AppCompatActivity extends FragmentActivity implements AbstractC11541s9, AnonymousClass04I {
    public AbstractC11211rM mDelegate;
    public final Resources mResources;

    public void onNightModeChanged(int i) {
    }

    public void onPrepareSupportNavigateUpTaskStack(@NonNull AnonymousClass04J r1) {
    }

    @CallSuper
    public void onSupportActionModeFinished(@NonNull AbstractC11301rk r1) {
    }

    @CallSuper
    public void onSupportActionModeStarted(@NonNull AbstractC11301rk r1) {
    }

    @Deprecated
    public void onSupportContentChanged() {
    }

    @Override // X.AbstractC11541s9
    @Nullable
    public AbstractC11301rk onWindowStartingSupportActionMode(@NonNull AbstractC11461s1 r2) {
        return null;
    }

    @Deprecated
    public void setSupportProgress(int i) {
    }

    @Deprecated
    public void setSupportProgressBarIndeterminate(boolean z) {
    }

    @Deprecated
    public void setSupportProgressBarIndeterminateVisibility(boolean z) {
    }

    @Deprecated
    public void setSupportProgressBarVisibility(boolean z) {
    }

    private boolean performMenuItemShortcut(KeyEvent keyEvent) {
        Window window;
        if (Build.VERSION.SDK_INT >= 26 || keyEvent.isCtrlPressed() || KeyEvent.metaStateHasNoModifiers(keyEvent.getMetaState()) || keyEvent.getRepeatCount() != 0 || KeyEvent.isModifierKey(keyEvent.getKeyCode()) || (window = getWindow()) == null || window.getDecorView() == null || !window.getDecorView().dispatchKeyShortcutEvent(keyEvent)) {
            return false;
        }
        return true;
    }

    @NonNull
    public AbstractC11211rM getDelegate() {
        AbstractC11211rM r0 = this.mDelegate;
        if (r0 != null) {
            return r0;
        }
        AnonymousClass1rJ r02 = new AnonymousClass1rJ(this, null, this, this);
        this.mDelegate = r02;
        return r02;
    }

    public Resources getResources() {
        Resources resources = this.mResources;
        if (resources == null) {
            return super.getResources();
        }
        return resources;
    }

    public void onContentChanged() {
    }

    public void onCreateSupportNavigateUpTaskStack(@NonNull AnonymousClass04J r6) {
        Intent intent;
        if (((this instanceof AnonymousClass04I) && (intent = getSupportParentActivityIntent()) != null) || (intent = A00(this)) != null) {
            ComponentName component = intent.getComponent();
            if (component == null) {
                component = intent.resolveActivity(r6.A00.getPackageManager());
            }
            ArrayList<Intent> arrayList = r6.A01;
            int size = arrayList.size();
            try {
                Context context = r6.A00;
                for (Intent A01 = A01(context, component); A01 != null; A01 = A01(context, A01.getComponent())) {
                    arrayList.add(size, A01);
                }
                arrayList.add(intent);
            } catch (PackageManager.NameNotFoundException e) {
                Log.e("TaskStackBuilder", "Bad ComponentName while traversing activity parent metadata");
                throw new IllegalArgumentException(e);
            }
        }
    }

    @Nullable
    public static Intent A00(@NonNull Activity activity) {
        Intent parentActivityIntent = activity.getParentActivityIntent();
        if (parentActivityIntent != null) {
            return parentActivityIntent;
        }
        try {
            String A00 = C000703c.A00(activity, activity.getComponentName());
            if (A00 == null) {
                return null;
            }
            ComponentName componentName = new ComponentName(activity, A00);
            try {
                if (C000703c.A00(activity, componentName) == null) {
                    return Intent.makeMainActivity(componentName);
                }
                return new Intent().setComponent(componentName);
            } catch (PackageManager.NameNotFoundException unused) {
                Log.e("NavUtils", AnonymousClass006.A09("getParentActivityIntent: bad parentActivityName '", A00, "' in manifest"));
                return null;
            }
        } catch (PackageManager.NameNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Nullable
    public static Intent A01(@NonNull Context context, @NonNull ComponentName componentName) throws PackageManager.NameNotFoundException {
        String A00 = C000703c.A00(context, componentName);
        if (A00 == null) {
            return null;
        }
        ComponentName componentName2 = new ComponentName(componentName.getPackageName(), A00);
        if (C000703c.A00(context, componentName2) == null) {
            return Intent.makeMainActivity(componentName2);
        }
        return new Intent().setComponent(componentName2);
    }

    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        AnonymousClass1rJ r2 = (AnonymousClass1rJ) getDelegate();
        AnonymousClass1rJ.A05(r2);
        ((ViewGroup) r2.A07.findViewById(16908290)).addView(view, layoutParams);
        ((Window$CallbackC11241rW) r2.A0C).A00.onContentChanged();
    }

    public void attachBaseContext(Context context) {
        super.attachBaseContext(getDelegate().A0C(context));
    }

    public void closeOptionsMenu() {
        AbstractC11251rX supportActionBar = getSupportActionBar();
        if (!getWindow().hasFeature(0)) {
            return;
        }
        if (supportActionBar == null || !(supportActionBar instanceof AnonymousClass1rR) || !((AnonymousClass1rR) supportActionBar).A02.A5V()) {
            super.closeOptionsMenu();
        }
    }

    @Override // androidx.core.app.ComponentActivity
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        boolean z;
        int keyCode = keyEvent.getKeyCode();
        AbstractC11251rX supportActionBar = getSupportActionBar();
        if (keyCode != 82 || supportActionBar == null || !((z = supportActionBar instanceof AnonymousClass1rR))) {
            return super.dispatchKeyEvent(keyEvent);
        }
        if (keyEvent.getAction() != 1 || !z) {
            return true;
        }
        ((AnonymousClass1rR) supportActionBar).A02.AAR();
        return true;
    }

    @Override // android.app.Activity
    public <T extends View> T findViewById(@IdRes int i) {
        AnonymousClass1rJ r0 = (AnonymousClass1rJ) getDelegate();
        AnonymousClass1rJ.A05(r0);
        return (T) r0.A08.findViewById(i);
    }

    @Nullable
    public AnonymousClass1sE getDrawerToggleDelegate() {
        return new C11561sB((AnonymousClass1rJ) getDelegate());
    }

    @NonNull
    public MenuInflater getMenuInflater() {
        Context context;
        AnonymousClass1rJ r2 = (AnonymousClass1rJ) getDelegate();
        MenuInflater menuInflater = r2.A05;
        if (menuInflater != null) {
            return menuInflater;
        }
        AnonymousClass1rJ.A07(r2);
        AbstractC11251rX r0 = r2.A0B;
        if (r0 != null) {
            context = r0.A03();
        } else {
            context = r2.A0j;
        }
        C11571sK r1 = new C11571sK(context);
        r2.A05 = r1;
        return r1;
    }

    @Nullable
    public AbstractC11251rX getSupportActionBar() {
        AnonymousClass1rJ r0 = (AnonymousClass1rJ) getDelegate();
        AnonymousClass1rJ.A07(r0);
        return r0.A0B;
    }

    @Override // X.AnonymousClass04I
    @Nullable
    public Intent getSupportParentActivityIntent() {
        return A00(this);
    }

    public void invalidateOptionsMenu() {
        getDelegate().A0E();
    }

    @Override // androidx.fragment.app.FragmentActivity
    public void onConfigurationChanged(@NonNull Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.mResources != null) {
            this.mResources.updateConfiguration(configuration, super.getResources().getDisplayMetrics());
        }
        AnonymousClass1rJ r7 = (AnonymousClass1rJ) getDelegate();
        if (r7.A0W && r7.A0e) {
            AnonymousClass1rJ.A07(r7);
            AbstractC11251rX r2 = r7.A0B;
            if (r2 != null && (r2 instanceof C11201rK)) {
                C11201rK r22 = (C11201rK) r2;
                C11201rK.A01(r22, new C11391ru(r22.A01).A00.getResources().getBoolean(R.bool.abc_action_bar_embed_tabs));
            }
        }
        C10911qB A00 = C10911qB.A00();
        Context context = r7.A0j;
        synchronized (A00) {
            C10821pj r6 = A00.A00;
            synchronized (r6) {
                AnonymousClass02n<WeakReference<Drawable.ConstantState>> r5 = r6.A04.get(context);
                if (r5 != null) {
                    int i = r5.A00;
                    Object[] objArr = r5.A03;
                    for (int i2 = 0; i2 < i; i2++) {
                        objArr[i2] = null;
                    }
                    r5.A00 = 0;
                    r5.A01 = false;
                }
            }
        }
        AnonymousClass1rJ.A09(r7, false);
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, androidx.fragment.app.FragmentActivity
    public void onCreate(@Nullable Bundle bundle) {
        AbstractC11211rM delegate = getDelegate();
        AnonymousClass1rJ r2 = (AnonymousClass1rJ) delegate;
        LayoutInflater from = LayoutInflater.from(r2.A0j);
        if (from.getFactory() == null) {
            from.setFactory2(r2);
        } else {
            from.getFactory2();
        }
        delegate.A0I(bundle);
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.FragmentActivity
    public void onDestroy() {
        super.onDestroy();
        getDelegate().A0F();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (performMenuItemShortcut(keyEvent)) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // androidx.fragment.app.FragmentActivity
    public final boolean onMenuItemSelected(int i, @NonNull MenuItem menuItem) {
        AbstractC06001Eq r0;
        if (super.onMenuItemSelected(i, menuItem)) {
            return true;
        }
        AbstractC11251rX supportActionBar = getSupportActionBar();
        if (menuItem.getItemId() != 16908332 || supportActionBar == null) {
            return false;
        }
        if (!(supportActionBar instanceof C11201rK)) {
            r0 = ((AnonymousClass1rR) supportActionBar).A02;
        } else {
            r0 = ((C11201rK) supportActionBar).A0B;
        }
        if ((r0.A3o() & 4) != 0) {
            return onSupportNavigateUp();
        }
        return false;
    }

    public boolean onMenuOpened(int i, Menu menu) {
        return super.onMenuOpened(i, menu);
    }

    public void onPostCreate(@Nullable Bundle bundle) {
        super.onPostCreate(bundle);
        AnonymousClass1rJ.A05((AnonymousClass1rJ) getDelegate());
    }

    @Override // androidx.fragment.app.FragmentActivity
    public void onPostResume() {
        super.onPostResume();
        AnonymousClass1rJ r0 = (AnonymousClass1rJ) getDelegate();
        AnonymousClass1rJ.A07(r0);
        AbstractC11251rX r2 = r0.A0B;
        if (r2 != null && (r2 instanceof C11201rK)) {
            ((C11201rK) r2).A0I = true;
        }
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, androidx.fragment.app.FragmentActivity
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        getDelegate();
    }

    @Override // androidx.fragment.app.FragmentActivity
    public void onStart() {
        super.onStart();
        AnonymousClass1rJ r1 = (AnonymousClass1rJ) getDelegate();
        r1.A0d = true;
        AnonymousClass1rJ.A09(r1, true);
    }

    @Override // androidx.fragment.app.FragmentActivity
    public void onStop() {
        super.onStop();
        getDelegate().A0G();
    }

    public boolean onSupportNavigateUp() {
        Intent supportParentActivityIntent = getSupportParentActivityIntent();
        if (supportParentActivityIntent == null) {
            return false;
        }
        if (shouldUpRecreateTask(supportParentActivityIntent)) {
            AnonymousClass04J r5 = new AnonymousClass04J(this);
            onCreateSupportNavigateUpTaskStack(r5);
            ArrayList<Intent> arrayList = r5.A01;
            if (!arrayList.isEmpty()) {
                Intent[] intentArr = (Intent[]) arrayList.toArray(new Intent[arrayList.size()]);
                intentArr[0] = new Intent(intentArr[0]).addFlags(268484608);
                r5.A00.startActivities(intentArr, null);
                try {
                    finishAffinity();
                    return true;
                } catch (IllegalStateException unused) {
                    finish();
                    return true;
                }
            } else {
                throw new IllegalStateException("No intents added to TaskStackBuilder; cannot startActivities");
            }
        } else {
            navigateUpTo(supportParentActivityIntent);
            return true;
        }
    }

    public void onTitleChanged(CharSequence charSequence, int i) {
        super.onTitleChanged(charSequence, i);
        getDelegate().A0L(charSequence);
    }

    public void openOptionsMenu() {
        AbstractC11251rX supportActionBar = getSupportActionBar();
        if (!getWindow().hasFeature(0)) {
            return;
        }
        if (supportActionBar == null || !(supportActionBar instanceof AnonymousClass1rR) || !((AnonymousClass1rR) supportActionBar).A02.AAR()) {
            super.openOptionsMenu();
        }
    }

    public void setSupportActionBar(@Nullable Toolbar toolbar) {
        CharSequence charSequence;
        AnonymousClass1rJ r4 = (AnonymousClass1rJ) getDelegate();
        Object obj = r4.A0l;
        boolean z = obj instanceof Activity;
        if (z) {
            AnonymousClass1rJ.A07(r4);
            AbstractC11251rX r3 = r4.A0B;
            if (!(r3 instanceof C11201rK)) {
                r4.A05 = null;
                if (r3 != null && (r3 instanceof AnonymousClass1rR)) {
                    AnonymousClass1rR r32 = (AnonymousClass1rR) r3;
                    r32.A02.A5I().removeCallbacks(r32.A06);
                }
                if (toolbar != null) {
                    if (z) {
                        charSequence = ((Activity) obj).getTitle();
                    } else {
                        charSequence = r4.A0M;
                    }
                    AnonymousClass1rR r0 = new AnonymousClass1rR(toolbar, charSequence, r4.A0C);
                    r4.A0B = r0;
                    r4.A08.setCallback(r0.A01);
                } else {
                    r4.A0B = null;
                    r4.A08.setCallback(r4.A0C);
                }
                r4.A0E();
                return;
            }
            throw new IllegalStateException("This Activity already has an action bar supplied by the window decor. Do not request Window.FEATURE_SUPPORT_ACTION_BAR and set windowActionBar to false in your theme to use a Toolbar instead.");
        }
    }

    @Override // android.view.ContextThemeWrapper, android.app.Activity
    public void setTheme(@StyleRes int i) {
        super.setTheme(i);
        AbstractC11211rM delegate = getDelegate();
        if (delegate instanceof AnonymousClass1rJ) {
            ((AnonymousClass1rJ) delegate).A02 = i;
        }
    }

    @Nullable
    public AbstractC11301rk startSupportActionMode(@NonNull AbstractC11461s1 r2) {
        return getDelegate().A0D(r2);
    }

    @Override // androidx.fragment.app.FragmentActivity
    public void supportInvalidateOptionsMenu() {
        getDelegate().A0E();
    }

    public boolean supportRequestWindowFeature(int i) {
        return getDelegate().A0M(i);
    }

    public boolean supportShouldUpRecreateTask(@NonNull Intent intent) {
        return shouldUpRecreateTask(intent);
    }

    public void supportNavigateUpTo(@NonNull Intent intent) {
        navigateUpTo(intent);
    }

    public AppCompatActivity() {
    }

    @ContentView
    public AppCompatActivity(@LayoutRes int i) {
        super(i);
    }

    @Override // android.app.Activity
    public void setContentView(@LayoutRes int i) {
        getDelegate().A0H(i);
    }

    @Override // android.app.Activity
    public void setContentView(View view) {
        getDelegate().A0J(view);
    }

    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        getDelegate().A0K(view, layoutParams);
    }
}
