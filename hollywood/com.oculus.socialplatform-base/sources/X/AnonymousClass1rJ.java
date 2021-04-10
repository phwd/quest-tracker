package X;

import android.app.Activity;
import android.app.Dialog;
import android.app.UiModeManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.LocaleList;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ActionBarContextView;
import bolts.WebViewAppLinkResolver;
import com.oculus.horizon.logging.LoggingKeys;
import com.oculus.socialplatform.R;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RestrictTo({AnonymousClass02C.LIBRARY})
/* renamed from: X.1rJ  reason: invalid class name */
public final class AnonymousClass1rJ extends AbstractC11211rM implements AnonymousClass1tQ, LayoutInflater.Factory2 {
    public static final C000502v<String, Integer> A0n = new C000502v<>();
    public static final boolean A0o = (!"robolectric".equals(Build.FINGERPRINT));
    public static final int[] A0p = {16842836};
    public int A00;
    public int A01 = -100;
    public int A02;
    public Rect A03;
    public Rect A04;
    public MenuInflater A05;
    public View A06;
    public ViewGroup A07;
    public Window A08;
    public PopupWindow A09;
    public TextView A0A;
    public AbstractC11251rX A0B;
    public C11231rV A0C;
    public AbstractC11341rp A0D;
    public AbstractC11341rp A0E;
    public AnonymousClass1rh A0F;
    public AnonymousClass1ri A0G;
    public AnonymousClass1CS A0H;
    public AbstractC11301rk A0I;
    public ActionBarContextView A0J;
    public AnonymousClass1rl A0K;
    public C003007j A0L = null;
    public CharSequence A0M;
    public Runnable A0N;
    public boolean A0O;
    public boolean A0P;
    public boolean A0Q;
    public boolean A0R;
    public boolean A0S;
    public boolean A0T;
    public boolean A0U;
    public boolean A0V = true;
    public boolean A0W;
    public boolean A0X;
    public boolean A0Y;
    public boolean A0Z;
    public boolean A0a;
    public boolean A0b;
    public boolean A0c;
    public boolean A0d;
    public boolean A0e;
    public boolean A0f;
    public AnonymousClass1rh[] A0g;
    public C11381rt A0h;
    public boolean A0i;
    public final Context A0j;
    public final AbstractC11541s9 A0k;
    public final Object A0l;
    public final Runnable A0m = new RunnableC11511s6(this);

    @NonNull
    public static Configuration A01(@NonNull Context context, int i, @Nullable Configuration configuration) {
        int i2;
        if (i == 1) {
            i2 = 16;
        } else if (i != 2) {
            i2 = context.getApplicationContext().getResources().getConfiguration().uiMode & 48;
        } else {
            i2 = 32;
        }
        Configuration configuration2 = new Configuration();
        configuration2.fontScale = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
        if (configuration != null) {
            configuration2.setTo(configuration);
        }
        configuration2.uiMode = i2 | (configuration2.uiMode & -49);
        return configuration2;
    }

    @Override // X.AbstractC11211rM
    @NonNull
    @CallSuper
    public final Context A0C(@NonNull Context context) {
        this.A0Q = true;
        int i = this.A01;
        if (i == -100) {
            i = -100;
        }
        int A002 = A00(context, i);
        Configuration configuration = null;
        if (context instanceof ContextThemeWrapper) {
            try {
                ((ContextThemeWrapper) context).applyOverrideConfiguration(A01(context, A002, null));
                return context;
            } catch (IllegalStateException unused) {
            }
        }
        if (context instanceof AnonymousClass1rZ) {
            try {
                ((AnonymousClass1rZ) context).A01(A01(context, A002, null));
                return context;
            } catch (IllegalStateException unused2) {
            }
        }
        if (!A0o) {
            super.A0C(context);
            return context;
        }
        try {
            Configuration configuration2 = context.getPackageManager().getResourcesForApplication(context.getApplicationInfo()).getConfiguration();
            Configuration configuration3 = context.getResources().getConfiguration();
            if (!configuration2.equals(configuration3)) {
                configuration = new Configuration();
                configuration.fontScale = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
                if (!(configuration3 == null || configuration2.diff(configuration3) == 0)) {
                    float f = configuration2.fontScale;
                    float f2 = configuration3.fontScale;
                    if (f != f2) {
                        configuration.fontScale = f2;
                    }
                    int i2 = configuration2.mcc;
                    int i3 = configuration3.mcc;
                    if (i2 != i3) {
                        configuration.mcc = i3;
                    }
                    int i4 = configuration2.mnc;
                    int i5 = configuration3.mnc;
                    if (i4 != i5) {
                        configuration.mnc = i5;
                    }
                    LocaleList locales = configuration2.getLocales();
                    LocaleList locales2 = configuration3.getLocales();
                    if (!locales.equals(locales2)) {
                        configuration.setLocales(locales2);
                        configuration.locale = configuration3.locale;
                    }
                    int i6 = configuration2.touchscreen;
                    int i7 = configuration3.touchscreen;
                    if (i6 != i7) {
                        configuration.touchscreen = i7;
                    }
                    int i8 = configuration2.keyboard;
                    int i9 = configuration3.keyboard;
                    if (i8 != i9) {
                        configuration.keyboard = i9;
                    }
                    int i10 = configuration2.keyboardHidden;
                    int i11 = configuration3.keyboardHidden;
                    if (i10 != i11) {
                        configuration.keyboardHidden = i11;
                    }
                    int i12 = configuration2.navigation;
                    int i13 = configuration3.navigation;
                    if (i12 != i13) {
                        configuration.navigation = i13;
                    }
                    int i14 = configuration2.navigationHidden;
                    int i15 = configuration3.navigationHidden;
                    if (i14 != i15) {
                        configuration.navigationHidden = i15;
                    }
                    int i16 = configuration2.orientation;
                    int i17 = configuration3.orientation;
                    if (i16 != i17) {
                        configuration.orientation = i17;
                    }
                    int i18 = configuration2.screenLayout & 15;
                    int i19 = configuration3.screenLayout & 15;
                    if (i18 != i19) {
                        configuration.screenLayout |= i19;
                    }
                    int i20 = configuration2.screenLayout & 192;
                    int i21 = configuration3.screenLayout & 192;
                    if (i20 != i21) {
                        configuration.screenLayout |= i21;
                    }
                    int i22 = configuration2.screenLayout & 48;
                    int i23 = configuration3.screenLayout & 48;
                    if (i22 != i23) {
                        configuration.screenLayout |= i23;
                    }
                    int i24 = configuration2.screenLayout & 768;
                    int i25 = configuration3.screenLayout & 768;
                    if (i24 != i25) {
                        configuration.screenLayout |= i25;
                    }
                    if (Build.VERSION.SDK_INT >= 26) {
                        AnonymousClass1Ev.A00(configuration2, configuration3, configuration);
                    }
                    int i26 = configuration2.uiMode & 15;
                    int i27 = configuration3.uiMode & 15;
                    if (i26 != i27) {
                        configuration.uiMode |= i27;
                    }
                    int i28 = configuration2.uiMode & 48;
                    int i29 = configuration3.uiMode & 48;
                    if (i28 != i29) {
                        configuration.uiMode |= i29;
                    }
                    int i30 = configuration2.screenWidthDp;
                    int i31 = configuration3.screenWidthDp;
                    if (i30 != i31) {
                        configuration.screenWidthDp = i31;
                    }
                    int i32 = configuration2.screenHeightDp;
                    int i33 = configuration3.screenHeightDp;
                    if (i32 != i33) {
                        configuration.screenHeightDp = i33;
                    }
                    int i34 = configuration2.smallestScreenWidthDp;
                    int i35 = configuration3.smallestScreenWidthDp;
                    if (i34 != i35) {
                        configuration.smallestScreenWidthDp = i35;
                    }
                    int i36 = configuration2.densityDpi;
                    int i37 = configuration3.densityDpi;
                    if (i36 != i37) {
                        configuration.densityDpi = i37;
                    }
                }
            }
            Configuration A012 = A01(context, A002, configuration);
            AnonymousClass1rZ r6 = new AnonymousClass1rZ(context, 2131755175);
            r6.A01(A012);
            try {
                if (context.getTheme() != null) {
                    Resources.Theme theme = r6.getTheme();
                    if (Build.VERSION.SDK_INT >= 29) {
                        C001804u.A00(theme);
                    } else {
                        synchronized (AnonymousClass04t.A02) {
                            if (!AnonymousClass04t.A01) {
                                try {
                                    Method declaredMethod = Resources.Theme.class.getDeclaredMethod("rebase", new Class[0]);
                                    AnonymousClass04t.A00 = declaredMethod;
                                    declaredMethod.setAccessible(true);
                                } catch (NoSuchMethodException unused3) {
                                }
                                AnonymousClass04t.A01 = true;
                            }
                            Method method = AnonymousClass04t.A00;
                            if (method != null) {
                                try {
                                    method.invoke(theme, new Object[0]);
                                } catch (IllegalAccessException | InvocationTargetException unused4) {
                                    AnonymousClass04t.A00 = null;
                                }
                            }
                        }
                    }
                }
            } catch (NullPointerException unused5) {
            }
            super.A0C(r6);
            return r6;
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException("Application failed to obtain resources from itself", e);
        }
    }

    public final void A0O(int i) {
        AnonymousClass1rh A0N2 = A0N(i);
        if (A0N2.A0A != null) {
            Bundle bundle = new Bundle();
            A0N2.A0A.A0B(bundle);
            if (bundle.size() > 0) {
                A0N2.A00 = bundle;
            }
            C11581sN r0 = A0N2.A0A;
            r0.A09();
            r0.clear();
        }
        A0N2.A0F = true;
        A0N2.A0E = true;
        if ((i == 108 || i == 0) && this.A0K != null) {
            AnonymousClass1rh A0N3 = A0N(0);
            A0N3.A0D = false;
            A0A(this, A0N3, null);
        }
    }

    private final int A00(@NonNull Context context, int i) {
        AbstractC11341rp A022;
        if (i != -100) {
            if (i != -1) {
                if (i != 0) {
                    if (!(i == 1 || i == 2)) {
                        if (i == 3) {
                            A022 = this.A0D;
                            if (A022 == null) {
                                A022 = new AnonymousClass1YL(this, context);
                                this.A0D = A022;
                            }
                        } else {
                            throw new IllegalStateException("Unknown value set for night mode. Please use one of the MODE_NIGHT values from AppCompatDelegate.");
                        }
                    }
                } else if (((UiModeManager) context.getApplicationContext().getSystemService(UiModeManager.class)).getNightMode() != 0) {
                    A022 = A02(context);
                }
                return A022.A00();
            }
            return i;
        }
        return -1;
    }

    private AbstractC11341rp A02(@NonNull Context context) {
        AbstractC11341rp r0 = this.A0E;
        if (r0 != null) {
            return r0;
        }
        C11441rz r2 = C11441rz.A03;
        if (r2 == null) {
            Context applicationContext = context.getApplicationContext();
            r2 = new C11441rz(applicationContext, (LocationManager) applicationContext.getSystemService(LoggingKeys.NOTIFICATION_PREFERENCE_CHANGE_LOCATION));
            C11441rz.A03 = r2;
        }
        C11311rm r02 = new C11311rm(this, r2);
        this.A0E = r02;
        return r02;
    }

    private void A03(@NonNull Window window) {
        if (this.A08 == null) {
            Window.Callback callback = window.getCallback();
            if (!(callback instanceof C11231rV)) {
                C11231rV r0 = new C11231rV(this, callback);
                this.A0C = r0;
                window.setCallback(r0);
                Context context = this.A0j;
                C10901qA r1 = new C10901qA(context, context.obtainStyledAttributes((AttributeSet) null, A0p));
                Drawable A032 = r1.A03(0);
                if (A032 != null) {
                    window.setBackgroundDrawable(A032);
                }
                r1.A04();
                this.A08 = window;
                return;
            }
            throw new IllegalStateException("AppCompat has already installed itself into the Window");
        }
        throw new IllegalStateException("AppCompat has already installed itself into the Window");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x005f, code lost:
        if (r0.width != -1) goto L_0x0061;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x010d, code lost:
        if (r0.getCount() > 0) goto L_0x010f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x015e, code lost:
        if (r0 != null) goto L_0x00f8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void A04(X.AnonymousClass1rh r14, android.view.KeyEvent r15) {
        /*
        // Method dump skipped, instructions count: 441
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1rJ.A04(X.1rh, android.view.KeyEvent):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0066, code lost:
        if (r4 != null) goto L_0x0068;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void A05(X.AnonymousClass1rJ r9) {
        /*
        // Method dump skipped, instructions count: 680
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1rJ.A05(X.1rJ):void");
    }

    public static void A06(AnonymousClass1rJ r2) {
        if (r2.A08 == null) {
            Object obj = r2.A0l;
            if (obj instanceof Activity) {
                r2.A03(((Activity) obj).getWindow());
            }
        }
        if (r2.A08 == null) {
            throw new IllegalStateException("We have not been given a Window");
        }
    }

    public static void A08(AnonymousClass1rJ r1) {
        if (r1.A0e) {
            throw new AndroidRuntimeException("Window feature must be requested before adding content");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0105, code lost:
        if (r1 == 0) goto L_0x0107;
     */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0052  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x005f  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x006a  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0089  */
    /* JADX WARNING: Removed duplicated region for block: B:73:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void A09(X.AnonymousClass1rJ r11, boolean r12) {
        /*
        // Method dump skipped, instructions count: 275
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1rJ.A09(X.1rJ, boolean):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002a, code lost:
        if (r1 == 108) goto L_0x002c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00c6, code lost:
        if (r6 == null) goto L_0x00c8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00c9, code lost:
        if (r6 != null) goto L_0x0099;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean A0A(X.AnonymousClass1rJ r10, X.AnonymousClass1rh r11, android.view.KeyEvent r12) {
        /*
        // Method dump skipped, instructions count: 348
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1rJ.A0A(X.1rJ, X.1rh, android.view.KeyEvent):boolean");
    }

    public final AnonymousClass1rh A0N(int i) {
        AnonymousClass1rh[] r3 = this.A0g;
        if (r3 == null || r3.length <= i) {
            AnonymousClass1rh[] r2 = new AnonymousClass1rh[(i + 1)];
            if (r3 != null) {
                System.arraycopy(r3, 0, r2, 0, r3.length);
            }
            this.A0g = r2;
            r3 = r2;
        }
        AnonymousClass1rh r0 = r3[i];
        if (r0 != null) {
            return r0;
        }
        AnonymousClass1rh r02 = new AnonymousClass1rh(i);
        r3[i] = r02;
        return r02;
    }

    public final void A0P(AnonymousClass1rh r5, boolean z) {
        ViewGroup viewGroup;
        AnonymousClass1rl r0;
        if (!z || r5.A02 != 0 || (r0 = this.A0K) == null || !r0.A67()) {
            WindowManager windowManager = (WindowManager) this.A0j.getSystemService("window");
            if (!(windowManager == null || !r5.A0C || (viewGroup = r5.A08) == null)) {
                windowManager.removeView(viewGroup);
                if (z) {
                    int i = r5.A02;
                    C11581sN r1 = r5.A0A;
                    if (r5.A0C && !this.A0Y) {
                        ((Window$CallbackC11241rW) this.A0C).A00.onPanelClosed(i, r1);
                    }
                }
            }
            r5.A0D = false;
            r5.A0B = false;
            r5.A0C = false;
            r5.A07 = null;
            r5.A0E = true;
            if (this.A0F == r5) {
                this.A0F = null;
                return;
            }
            return;
        }
        A0Q(r5.A0A);
    }

    public final void A0Q(@NonNull C11581sN r3) {
        if (!this.A0i) {
            this.A0i = true;
            this.A0K.A2e();
            Window.Callback callback = this.A08.getCallback();
            if (callback != null && !this.A0Y) {
                callback.onPanelClosed(108, r3);
            }
            this.A0i = false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:47:0x00a2  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00cd  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean A0R(android.view.KeyEvent r6) {
        /*
        // Method dump skipped, instructions count: 292
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1rJ.A0R(android.view.KeyEvent):boolean");
    }

    @Override // X.AnonymousClass1tQ
    public final boolean A7I(@NonNull C11581sN r8, @NonNull MenuItem menuItem) {
        Window.Callback callback = this.A08.getCallback();
        if (callback == null || this.A0Y) {
            return false;
        }
        C11581sN A042 = r8.A04();
        AnonymousClass1rh[] r4 = this.A0g;
        if (r4 == null) {
            return false;
        }
        for (AnonymousClass1rh r1 : r4) {
            if (r1 != null && r1.A0A == A042) {
                return callback.onMenuItemSelected(r1.A02, menuItem);
            }
        }
        return false;
    }

    @Override // X.AnonymousClass1tQ
    public final void A7J(@NonNull C11581sN r7) {
        AnonymousClass1rl r0 = this.A0K;
        if (r0 == null || !r0.A1z() || (ViewConfiguration.get(this.A0j).hasPermanentMenuKey() && !this.A0K.A66())) {
            AnonymousClass1rh A0N2 = A0N(0);
            A0N2.A0E = true;
            A0P(A0N2, false);
            A04(A0N2, null);
            return;
        }
        Window.Callback callback = this.A08.getCallback();
        if (this.A0K.A67()) {
            this.A0K.A5V();
            if (!this.A0Y) {
                callback.onPanelClosed(108, A0N(0).A0A);
            }
        } else if (callback != null && !this.A0Y) {
            if (this.A0X && (this.A00 & 1) != 0) {
                View decorView = this.A08.getDecorView();
                Runnable runnable = this.A0m;
                decorView.removeCallbacks(runnable);
                runnable.run();
            }
            AnonymousClass1rh A0N3 = A0N(0);
            C11581sN r1 = A0N3.A0A;
            if (r1 != null && !A0N3.A0F && callback.onPreparePanel(0, A0N3.A06, r1)) {
                callback.onMenuOpened(108, A0N3.A0A);
                this.A0K.AAR();
            }
        }
    }

    public AnonymousClass1rJ(Context context, Window window, AbstractC11541s9 r6, Object obj) {
        C000502v<String, Integer> r1;
        Integer num;
        int i;
        this.A0j = context;
        this.A0k = r6;
        this.A0l = obj;
        if (obj instanceof Dialog) {
            while (true) {
                if (context != null) {
                    if (!(context instanceof AppCompatActivity)) {
                        if (!(context instanceof ContextWrapper)) {
                            break;
                        }
                        context = ((ContextWrapper) context).getBaseContext();
                    } else {
                        AppCompatActivity appCompatActivity = (AppCompatActivity) context;
                        if (appCompatActivity != null) {
                            AbstractC11211rM delegate = appCompatActivity.getDelegate();
                            if (!(delegate instanceof AnonymousClass1rJ)) {
                                i = -100;
                            } else {
                                i = ((AnonymousClass1rJ) delegate).A01;
                            }
                            this.A01 = i;
                        }
                    }
                } else {
                    break;
                }
            }
        }
        if (this.A01 == -100 && (num = (r1 = A0n).get(this.A0l.getClass().getName())) != null) {
            this.A01 = num.intValue();
            r1.remove(this.A0l.getClass().getName());
        }
        if (window != null) {
            A03(window);
        }
        C10911qB.A01();
    }

    public static void A07(AnonymousClass1rJ r3) {
        AbstractC11251rX r2;
        A05(r3);
        if (r3.A0W && (r2 = r3.A0B) == null) {
            Object obj = r3.A0l;
            if (obj instanceof Activity) {
                r2 = new C11201rK((Activity) obj, r3.A0b);
            } else if (obj instanceof Dialog) {
                r2 = new C11201rK((Dialog) obj);
            } else {
                if (r2 == null) {
                    return;
                }
                r2.A05(r3.A0S);
            }
            r3.A0B = r2;
            r2.A05(r3.A0S);
        }
    }

    public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        View r6;
        AnonymousClass1CS r4 = this.A0H;
        if (r4 == null) {
            String string = this.A0j.obtainStyledAttributes(C11081qa.A09).getString(114);
            if (string == null) {
                r4 = new AnonymousClass1CS();
                this.A0H = r4;
            } else {
                try {
                    r4 = (AnonymousClass1CS) Class.forName(string).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                    this.A0H = r4;
                } catch (Throwable unused) {
                    r4 = new AnonymousClass1CS();
                    this.A0H = r4;
                }
            }
        }
        Context context2 = context;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C11081qa.A0O, 0, 0);
        int resourceId = obtainStyledAttributes.getResourceId(4, 0);
        obtainStyledAttributes.recycle();
        if (resourceId != 0 && (!(context instanceof AnonymousClass1rZ) || ((AnonymousClass1rZ) context).A00 != resourceId)) {
            context2 = new AnonymousClass1rZ(context, resourceId);
        }
        char c = 65535;
        switch (str.hashCode()) {
            case -1946472170:
                if (str.equals("RatingBar")) {
                    c = 11;
                    break;
                }
                break;
            case -1455429095:
                if (str.equals("CheckedTextView")) {
                    c = '\b';
                    break;
                }
                break;
            case -1346021293:
                if (str.equals("MultiAutoCompleteTextView")) {
                    c = '\n';
                    break;
                }
                break;
            case -938935918:
                if (str.equals("TextView")) {
                    c = 0;
                    break;
                }
                break;
            case -937446323:
                if (str.equals("ImageButton")) {
                    c = 5;
                    break;
                }
                break;
            case -658531749:
                if (str.equals("SeekBar")) {
                    c = '\f';
                    break;
                }
                break;
            case -339785223:
                if (str.equals("Spinner")) {
                    c = 4;
                    break;
                }
                break;
            case 776382189:
                if (str.equals("RadioButton")) {
                    c = 7;
                    break;
                }
                break;
            case 799298502:
                if (str.equals("ToggleButton")) {
                    c = '\r';
                    break;
                }
                break;
            case 1125864064:
                if (str.equals("ImageView")) {
                    c = 1;
                    break;
                }
                break;
            case 1413872058:
                if (str.equals("AutoCompleteTextView")) {
                    c = '\t';
                    break;
                }
                break;
            case 1601505219:
                if (str.equals("CheckBox")) {
                    c = 6;
                    break;
                }
                break;
            case 1666676343:
                if (str.equals("EditText")) {
                    c = 3;
                    break;
                }
                break;
            case 2001146706:
                if (str.equals("Button")) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                r6 = new C10931qF(context2, attributeSet, 16842884);
                break;
            case 1:
                r6 = new C11011qQ(context2, attributeSet, 0);
                break;
            case 2:
                r6 = new C10951qI(context2, attributeSet);
                break;
            case 3:
                r6 = new AnonymousClass1qJ(context2, attributeSet);
                break;
            case 4:
                r6 = new AnonymousClass1sR(context2, attributeSet);
                break;
            case 5:
                r6 = new C11021qR(context2, attributeSet, R.attr.imageButtonStyle);
                break;
            case 6:
                r6 = new C11181r5(context2, attributeSet);
                break;
            case 7:
                r6 = new C11191r6(context2, attributeSet);
                break;
            case '\b':
                r6 = new C11041qT(context2, attributeSet);
                break;
            case '\t':
                r6 = new C10961qL(context2, attributeSet, R.attr.autoCompleteTextViewStyle);
                break;
            case '\n':
                r6 = new C10981qN(context2, attributeSet);
                break;
            case 11:
                r6 = new C10871q7(context2, attributeSet);
                break;
            case '\f':
                r6 = new C10861q6(context2, attributeSet);
                break;
            case '\r':
                r6 = new C11111qd(context2, attributeSet);
                break;
            default:
                r6 = null;
                break;
        }
        if (r6 == null && context != context2) {
            if (str.equals("view")) {
                str = attributeSet.getAttributeValue(null, WebViewAppLinkResolver.KEY_CLASS);
            }
            try {
                Object[] objArr = r4.A00;
                objArr[0] = context2;
                objArr[1] = attributeSet;
                if (-1 == str.indexOf(46)) {
                    int i = 0;
                    while (true) {
                        String[] strArr = AnonymousClass1CS.A02;
                        if (i < strArr.length) {
                            r6 = AnonymousClass1CS.A00(r4, context2, str, strArr[i]);
                            if (r6 == null) {
                                i++;
                            }
                        } else {
                            objArr[0] = null;
                            objArr[1] = null;
                            r6 = null;
                        }
                    }
                } else {
                    r6 = AnonymousClass1CS.A00(r4, context2, str, null);
                }
                objArr[0] = null;
                objArr[1] = null;
            } catch (Exception unused2) {
                Object[] objArr2 = r4.A00;
                objArr2[0] = null;
                objArr2[1] = null;
                r6 = null;
            } catch (Throwable th) {
                Object[] objArr3 = r4.A00;
                objArr3[0] = null;
                objArr3[1] = null;
                throw th;
            }
        }
        if (r6 != null) {
            Context context3 = r6.getContext();
            if ((context3 instanceof ContextWrapper) && r6.hasOnClickListeners()) {
                TypedArray obtainStyledAttributes2 = context3.obtainStyledAttributes(attributeSet, AnonymousClass1CS.A01);
                String string2 = obtainStyledAttributes2.getString(0);
                if (string2 != null) {
                    r6.setOnClickListener(new AnonymousClass1Dx(r6, string2));
                }
                obtainStyledAttributes2.recycle();
            }
        }
        return r6;
    }

    public final View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }
}
