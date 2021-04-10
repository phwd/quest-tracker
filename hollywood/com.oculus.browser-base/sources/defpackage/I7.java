package defpackage;

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
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.Objects;

/* renamed from: I7  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class I7 extends AbstractActivityC3892nS implements M7 {
    public Q7 T;

    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        b0().c(view, layoutParams);
    }

    /* JADX WARNING: Removed duplicated region for block: B:104:0x018b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void attachBaseContext(android.content.Context r11) {
        /*
        // Method dump skipped, instructions count: 483
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.I7.attachBaseContext(android.content.Context):void");
    }

    public Q7 b0() {
        if (this.T == null) {
            int i = Q7.F;
            this.T = new LayoutInflater$Factory2C3156j8(this, null, this, this);
        }
        return this.T;
    }

    public AbstractC1772b2 c0() {
        LayoutInflater$Factory2C3156j8 j8Var = (LayoutInflater$Factory2C3156j8) b0();
        j8Var.F();
        return j8Var.R;
    }

    public void closeOptionsMenu() {
        c0();
        if (getWindow().hasFeature(0)) {
            super.closeOptionsMenu();
        }
    }

    public void d0() {
    }

    @Override // defpackage.AbstractActivityC3119iw
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        keyEvent.getKeyCode();
        c0();
        return super.dispatchKeyEvent(keyEvent);
    }

    public void e0() {
    }

    @Override // android.app.Activity
    public View findViewById(int i) {
        LayoutInflater$Factory2C3156j8 j8Var = (LayoutInflater$Factory2C3156j8) b0();
        j8Var.z();
        return j8Var.O.findViewById(i);
    }

    public MenuInflater getMenuInflater() {
        LayoutInflater$Factory2C3156j8 j8Var = (LayoutInflater$Factory2C3156j8) b0();
        if (j8Var.S == null) {
            j8Var.F();
            AbstractC1772b2 b2Var = j8Var.R;
            j8Var.S = new X31(b2Var != null ? b2Var.b() : j8Var.N);
        }
        return j8Var.S;
    }

    public Resources getResources() {
        int i = Gs1.f8118a;
        return super.getResources();
    }

    @Override // defpackage.M7
    public AbstractC5696y2 i(AbstractC5526x2 x2Var) {
        return null;
    }

    public void invalidateOptionsMenu() {
        b0().f();
    }

    @Override // defpackage.AbstractActivityC3892nS
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        LayoutInflater$Factory2C3156j8 j8Var = (LayoutInflater$Factory2C3156j8) b0();
        if (j8Var.j0 && j8Var.d0) {
            j8Var.F();
            AbstractC1772b2 b2Var = j8Var.R;
            if (b2Var != null) {
                Ty1 ty1 = (Ty1) b2Var;
                ty1.f(ty1.c.getResources().getBoolean(R.bool.f9490_resource_name_obfuscated_RES_2131034112));
            }
        }
        C3840n8 a2 = C3840n8.a();
        Context context = j8Var.N;
        synchronized (a2) {
            C4904tM0 tm0 = a2.c;
            synchronized (tm0) {
                C4083ob0 ob0 = (C4083ob0) tm0.g.get(context);
                if (ob0 != null) {
                    ob0.a();
                }
            }
        }
        j8Var.q(false);
    }

    public void onContentChanged() {
    }

    @Override // defpackage.AbstractActivityC3892nS, defpackage.AbstractActivityC3119iw
    public void onCreate(Bundle bundle) {
        Q7 b0 = b0();
        b0.e();
        b0.g(bundle);
        super.onCreate(bundle);
    }

    @Override // defpackage.AbstractActivityC3892nS
    public void onDestroy() {
        super.onDestroy();
        b0().h();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        Window window;
        if (Build.VERSION.SDK_INT < 26 && !keyEvent.isCtrlPressed() && !KeyEvent.metaStateHasNoModifiers(keyEvent.getMetaState()) && keyEvent.getRepeatCount() == 0 && !KeyEvent.isModifierKey(keyEvent.getKeyCode()) && (window = getWindow()) != null && window.getDecorView() != null && window.getDecorView().dispatchKeyShortcutEvent(keyEvent)) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // defpackage.AbstractActivityC3892nS
    public final boolean onMenuItemSelected(int i, MenuItem menuItem) {
        if (super.onMenuItemSelected(i, menuItem)) {
            return true;
        }
        AbstractC1772b2 c0 = c0();
        if (menuItem.getItemId() != 16908332 || c0 == null || (((Ty1) c0).g.b & 4) == 0) {
            return false;
        }
        Intent a2 = AbstractC0522Im0.a(this);
        if (a2 == null) {
            return false;
        }
        if (shouldUpRecreateTask(a2)) {
            ArrayList arrayList = new ArrayList();
            Intent a3 = AbstractC0522Im0.a(this);
            if (a3 == null) {
                a3 = AbstractC0522Im0.a(this);
            }
            if (a3 != null) {
                ComponentName component = a3.getComponent();
                if (component == null) {
                    component = a3.resolveActivity(getPackageManager());
                }
                int size = arrayList.size();
                try {
                    Intent b = AbstractC0522Im0.b(this, component);
                    while (b != null) {
                        arrayList.add(size, b);
                        b = AbstractC0522Im0.b(this, b.getComponent());
                    }
                    arrayList.add(a3);
                } catch (PackageManager.NameNotFoundException e) {
                    Log.e("TaskStackBuilder", "Bad ComponentName while traversing activity parent metadata");
                    throw new IllegalArgumentException(e);
                }
            }
            e0();
            if (!arrayList.isEmpty()) {
                Intent[] intentArr = (Intent[]) arrayList.toArray(new Intent[arrayList.size()]);
                intentArr[0] = new Intent(intentArr[0]).addFlags(268484608);
                Object obj = K2.f8337a;
                startActivities(intentArr, null);
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
            navigateUpTo(a2);
            return true;
        }
    }

    public boolean onMenuOpened(int i, Menu menu) {
        return super.onMenuOpened(i, menu);
    }

    @Override // defpackage.AbstractActivityC3892nS
    public void onPanelClosed(int i, Menu menu) {
        super.onPanelClosed(i, menu);
    }

    public void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        ((LayoutInflater$Factory2C3156j8) b0()).z();
    }

    @Override // defpackage.AbstractActivityC3892nS
    public void onPostResume() {
        super.onPostResume();
        LayoutInflater$Factory2C3156j8 j8Var = (LayoutInflater$Factory2C3156j8) b0();
        j8Var.F();
        AbstractC1772b2 b2Var = j8Var.R;
        if (b2Var != null) {
            ((Ty1) b2Var).x = true;
        }
    }

    @Override // defpackage.AbstractActivityC3892nS, defpackage.AbstractActivityC3119iw
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        Objects.requireNonNull((LayoutInflater$Factory2C3156j8) b0());
    }

    @Override // defpackage.AbstractActivityC3892nS
    public void onStart() {
        super.onStart();
        LayoutInflater$Factory2C3156j8 j8Var = (LayoutInflater$Factory2C3156j8) b0();
        j8Var.u0 = true;
        j8Var.d();
    }

    @Override // defpackage.AbstractActivityC3892nS
    public void onStop() {
        super.onStop();
        b0().i();
    }

    public void onTitleChanged(CharSequence charSequence, int i) {
        super.onTitleChanged(charSequence, i);
        b0().p(charSequence);
    }

    public void openOptionsMenu() {
        c0();
        if (getWindow().hasFeature(0)) {
            super.openOptionsMenu();
        }
    }

    @Override // android.app.Activity
    public void setContentView(int i) {
        b0().l(i);
    }

    @Override // android.view.ContextThemeWrapper, android.app.Activity
    public void setTheme(int i) {
        super.setTheme(i);
        ((LayoutInflater$Factory2C3156j8) b0()).x0 = i;
    }

    @Override // defpackage.M7
    public void t(AbstractC5696y2 y2Var) {
    }

    @Override // defpackage.M7
    public void x(AbstractC5696y2 y2Var) {
    }

    @Override // android.app.Activity
    public void setContentView(View view) {
        b0().m(view);
    }

    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        b0().n(view, layoutParams);
    }
}
