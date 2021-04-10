package androidx.appcompat.app;

import X.AnonymousClass006;
import X.AnonymousClass02W;
import X.AnonymousClass02Y;
import X.AnonymousClass02k;
import X.AnonymousClass02m;
import X.AnonymousClass03C;
import X.AnonymousClass03D;
import X.AnonymousClass07a;
import X.AnonymousClass07b;
import X.C006706u;
import X.LayoutInflater$Factory2C04430et;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
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
import java.util.ArrayList;

public class AppCompatActivity extends FragmentActivity implements AnonymousClass02k, AnonymousClass07a {
    public AnonymousClass02m mDelegate;
    public final Resources mResources;

    public void onNightModeChanged(int i) {
    }

    public void onPrepareSupportNavigateUpTaskStack(@NonNull AnonymousClass07b r1) {
    }

    @CallSuper
    public void onSupportActionModeFinished(@NonNull AnonymousClass03D r1) {
    }

    @CallSuper
    public void onSupportActionModeStarted(@NonNull AnonymousClass03D r1) {
    }

    @Deprecated
    public void onSupportContentChanged() {
    }

    @Override // X.AnonymousClass02k
    @Nullable
    public AnonymousClass03D onWindowStartingSupportActionMode(@NonNull AnonymousClass03C r2) {
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
    public AnonymousClass02m getDelegate() {
        AnonymousClass02m r0 = this.mDelegate;
        if (r0 != null) {
            return r0;
        }
        LayoutInflater$Factory2C04430et r02 = new LayoutInflater$Factory2C04430et(this, null, this, this);
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

    public void onCreateSupportNavigateUpTaskStack(@NonNull AnonymousClass07b r6) {
        Intent intent;
        if (((this instanceof AnonymousClass07a) && (intent = getSupportParentActivityIntent()) != null) || (intent = A00(this)) != null) {
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
            String A00 = C006706u.A00(activity, activity.getComponentName());
            if (A00 == null) {
                return null;
            }
            ComponentName componentName = new ComponentName(activity, A00);
            try {
                if (C006706u.A00(activity, componentName) == null) {
                    return Intent.makeMainActivity(componentName);
                }
                return new Intent().setComponent(componentName);
            } catch (PackageManager.NameNotFoundException unused) {
                Log.e("NavUtils", AnonymousClass006.A07("getParentActivityIntent: bad parentActivityName '", A00, "' in manifest"));
                return null;
            }
        } catch (PackageManager.NameNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Nullable
    public static Intent A01(@NonNull Context context, @NonNull ComponentName componentName) throws PackageManager.NameNotFoundException {
        String A00 = C006706u.A00(context, componentName);
        if (A00 == null) {
            return null;
        }
        ComponentName componentName2 = new ComponentName(componentName.getPackageName(), A00);
        if (C006706u.A00(context, componentName2) == null) {
            return Intent.makeMainActivity(componentName2);
        }
        return new Intent().setComponent(componentName2);
    }

    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        getDelegate().A0Y(view, layoutParams);
    }

    public void attachBaseContext(Context context) {
        super.attachBaseContext(getDelegate().A0G(context));
    }

    public void closeOptionsMenu() {
        AnonymousClass02W supportActionBar = getSupportActionBar();
        if (!getWindow().hasFeature(0)) {
            return;
        }
        if (supportActionBar == null || !supportActionBar.A04()) {
            super.closeOptionsMenu();
        }
    }

    @Override // androidx.core.app.ComponentActivity
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        AnonymousClass02W supportActionBar = getSupportActionBar();
        if (keyCode != 82 || supportActionBar == null || !supportActionBar.A07(keyEvent)) {
            return super.dispatchKeyEvent(keyEvent);
        }
        return true;
    }

    @Override // android.app.Activity
    public <T extends View> T findViewById(@IdRes int i) {
        return (T) getDelegate().A0I(i);
    }

    @Nullable
    public AnonymousClass02Y getDrawerToggleDelegate() {
        return getDelegate().A0K();
    }

    @NonNull
    public MenuInflater getMenuInflater() {
        return getDelegate().A0H();
    }

    @Nullable
    public AnonymousClass02W getSupportActionBar() {
        return getDelegate().A0J();
    }

    @Override // X.AnonymousClass07a
    @Nullable
    public Intent getSupportParentActivityIntent() {
        return A00(this);
    }

    public void invalidateOptionsMenu() {
        getDelegate().A0N();
    }

    @Override // androidx.fragment.app.FragmentActivity
    public void onConfigurationChanged(@NonNull Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.mResources != null) {
            this.mResources.updateConfiguration(configuration, super.getResources().getDisplayMetrics());
        }
        getDelegate().A0U(configuration);
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, androidx.fragment.app.FragmentActivity
    public void onCreate(@Nullable Bundle bundle) {
        AnonymousClass02m delegate = getDelegate();
        delegate.A0M();
        delegate.A0V(bundle);
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.FragmentActivity
    public void onDestroy() {
        super.onDestroy();
        getDelegate().A0O();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (performMenuItemShortcut(keyEvent)) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // androidx.fragment.app.FragmentActivity
    public final boolean onMenuItemSelected(int i, @NonNull MenuItem menuItem) {
        if (super.onMenuItemSelected(i, menuItem)) {
            return true;
        }
        AnonymousClass02W supportActionBar = getSupportActionBar();
        if (menuItem.getItemId() != 16908332 || supportActionBar == null || (supportActionBar.A08() & 4) == 0) {
            return false;
        }
        return onSupportNavigateUp();
    }

    public boolean onMenuOpened(int i, Menu menu) {
        return super.onMenuOpened(i, menu);
    }

    public void onPostCreate(@Nullable Bundle bundle) {
        super.onPostCreate(bundle);
        getDelegate().A0W(bundle);
    }

    @Override // androidx.fragment.app.FragmentActivity
    public void onPostResume() {
        super.onPostResume();
        getDelegate().A0P();
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, androidx.fragment.app.FragmentActivity
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        getDelegate();
    }

    @Override // androidx.fragment.app.FragmentActivity
    public void onStart() {
        super.onStart();
        getDelegate().A0Q();
    }

    @Override // androidx.fragment.app.FragmentActivity
    public void onStop() {
        super.onStop();
        getDelegate().A0R();
    }

    public boolean onSupportNavigateUp() {
        Intent supportParentActivityIntent = getSupportParentActivityIntent();
        if (supportParentActivityIntent == null) {
            return false;
        }
        if (shouldUpRecreateTask(supportParentActivityIntent)) {
            AnonymousClass07b r5 = new AnonymousClass07b(this);
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
        getDelegate().A0b(charSequence);
    }

    public void openOptionsMenu() {
        AnonymousClass02W supportActionBar = getSupportActionBar();
        if (!getWindow().hasFeature(0)) {
            return;
        }
        if (supportActionBar == null || !supportActionBar.A06()) {
            super.openOptionsMenu();
        }
    }

    public void setSupportActionBar(@Nullable Toolbar toolbar) {
        getDelegate().A0a(toolbar);
    }

    @Override // android.view.ContextThemeWrapper, android.app.Activity
    public void setTheme(@StyleRes int i) {
        super.setTheme(i);
        getDelegate().A0S(i);
    }

    @Nullable
    public AnonymousClass03D startSupportActionMode(@NonNull AnonymousClass03C r2) {
        return getDelegate().A0L(r2);
    }

    @Override // androidx.fragment.app.FragmentActivity
    public void supportInvalidateOptionsMenu() {
        getDelegate().A0N();
    }

    public boolean supportRequestWindowFeature(int i) {
        return getDelegate().A0c(i);
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
        getDelegate().A0T(i);
    }

    @Override // android.app.Activity
    public void setContentView(View view) {
        getDelegate().A0X(view);
    }

    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        getDelegate().A0Z(view, layoutParams);
    }
}
