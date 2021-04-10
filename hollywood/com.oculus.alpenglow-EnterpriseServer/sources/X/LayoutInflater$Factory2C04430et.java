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
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.StyleRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.ContentFrameLayout;
import androidx.appcompat.widget.Toolbar;
import com.oculus.alpenglow.R;
import com.oculus.library.utils.app.ImagesBuilder;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RestrictTo({AnonymousClass02D.LIBRARY})
/* renamed from: X.0et  reason: invalid class name and case insensitive filesystem */
public final class LayoutInflater$Factory2C04430et extends AnonymousClass02m implements AnonymousClass03V, LayoutInflater.Factory2 {
    public static final boolean A0n = (!"robolectric".equals(Build.FINGERPRINT));
    public static final AnonymousClass06D<String, Integer> A0o = new AnonymousClass06D<>();
    public static final int[] A0p = {16842836};
    public MenuInflater A00;
    public boolean A01;
    public boolean A02;
    public boolean A03;
    public int A04;
    public int A05 = -100;
    public int A06;
    public Rect A07;
    public Rect A08;
    public View A09;
    public ViewGroup A0A;
    public Window A0B;
    public PopupWindow A0C;
    public AnonymousClass02W A0D;
    public C04480ey A0E;
    public AbstractC000302q A0F;
    public AbstractC000302q A0G;
    public AnonymousClass02x A0H;
    public C04440eu A0I;
    public AnonymousClass03D A0J;
    public ActionBarContextView A0K;
    public AbstractC002504b A0L;
    public AnonymousClass0B0 A0M = null;
    public CharSequence A0N;
    public Runnable A0O;
    public boolean A0P;
    public boolean A0Q;
    public boolean A0R;
    public boolean A0S;
    public boolean A0T = true;
    public boolean A0U;
    public boolean A0V;
    public boolean A0W;
    public boolean A0X;
    public boolean A0Y;
    public boolean A0Z;
    public boolean A0a;
    public AnonymousClass02x[] A0b;
    public TextView A0c;
    public AnonymousClass0f0 A0d;
    public AnonymousClass02z A0e;
    public boolean A0f;
    public boolean A0g;
    public boolean A0h;
    public boolean A0i;
    public final Context A0j;
    public final AnonymousClass02k A0k;
    public final Object A0l;
    public final Runnable A0m = new AnonymousClass02n(this);

    @NonNull
    public static Configuration A02(@NonNull Context context, int i, @Nullable Configuration configuration) {
        int i2;
        if (i == 1) {
            i2 = 16;
        } else if (i != 2) {
            i2 = context.getApplicationContext().getResources().getConfiguration().uiMode & 48;
        } else {
            i2 = 32;
        }
        Configuration configuration2 = new Configuration();
        configuration2.fontScale = 0.0f;
        if (configuration != null) {
            configuration2.setTo(configuration);
        }
        configuration2.uiMode = i2 | (configuration2.uiMode & -49);
        return configuration2;
    }

    @Override // X.AnonymousClass02m
    @NonNull
    @CallSuper
    public final Context A0G(@NonNull Context context) {
        this.A0R = true;
        int i = this.A05;
        if (i == -100) {
            i = -100;
        }
        int A012 = A01(context, i);
        Configuration configuration = null;
        if (context instanceof ContextThemeWrapper) {
            try {
                ((ContextThemeWrapper) context).applyOverrideConfiguration(A02(context, A012, null));
                return context;
            } catch (IllegalStateException unused) {
            }
        }
        if (context instanceof AnonymousClass03F) {
            try {
                ((AnonymousClass03F) context).A01(A02(context, A012, null));
                return context;
            } catch (IllegalStateException unused2) {
            }
        }
        if (!A0n) {
            super.A0G(context);
            return context;
        }
        try {
            Configuration configuration2 = context.getPackageManager().getResourcesForApplication(context.getApplicationInfo()).getConfiguration();
            Configuration configuration3 = context.getResources().getConfiguration();
            if (!configuration2.equals(configuration3)) {
                configuration = new Configuration();
                configuration.fontScale = 0.0f;
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
                        AnonymousClass02t.A00(configuration2, configuration3, configuration);
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
            Configuration A022 = A02(context, A012, configuration);
            AnonymousClass03F r6 = new AnonymousClass03F(context, 2131493002);
            r6.A01(A022);
            try {
                if (context.getTheme() != null) {
                    Resources.Theme theme = r6.getTheme();
                    if (Build.VERSION.SDK_INT >= 29) {
                        AnonymousClass08C.A00(theme);
                    } else {
                        synchronized (AnonymousClass08B.A02) {
                            if (!AnonymousClass08B.A01) {
                                try {
                                    Method declaredMethod = Resources.Theme.class.getDeclaredMethod("rebase", new Class[0]);
                                    AnonymousClass08B.A00 = declaredMethod;
                                    declaredMethod.setAccessible(true);
                                } catch (NoSuchMethodException unused3) {
                                }
                                AnonymousClass08B.A01 = true;
                            }
                            Method method = AnonymousClass08B.A00;
                            if (method != null) {
                                try {
                                    method.invoke(theme, new Object[0]);
                                } catch (IllegalAccessException | InvocationTargetException unused4) {
                                    AnonymousClass08B.A00 = null;
                                }
                            }
                        }
                    }
                }
            } catch (NullPointerException unused5) {
            }
            super.A0G(r6);
            return r6;
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException("Application failed to obtain resources from itself", e);
        }
    }

    @Override // X.AnonymousClass02m
    public final void A0Q() {
        this.A0Z = true;
        A0D(this, true);
    }

    @Override // X.AnonymousClass02m
    public final void A0R() {
        this.A0Z = false;
        AnonymousClass02W A0J2 = A0J();
        if (A0J2 != null) {
            A0J2.A0F(false);
        }
    }

    @Override // X.AnonymousClass02m
    public final void A0V(Bundle bundle) {
        this.A0R = true;
        A0D(this, false);
        A08();
        Object obj = this.A0l;
        if (obj instanceof Activity) {
            String str = null;
            try {
                Activity activity = (Activity) obj;
                try {
                    str = C006706u.A00(activity, activity.getComponentName());
                } catch (PackageManager.NameNotFoundException e) {
                    throw new IllegalArgumentException(e);
                }
            } catch (IllegalArgumentException unused) {
            }
            if (str != null) {
                AnonymousClass02W r0 = this.A0D;
                if (r0 == null) {
                    this.A0g = true;
                } else {
                    r0.A0E(true);
                }
            }
            synchronized (AnonymousClass02m.A01) {
                AnonymousClass02m.A00(this);
                AnonymousClass02m.A00.add(new WeakReference<>(this));
            }
        }
        this.A0S = true;
    }

    public final void A0e(int i) {
        AnonymousClass02x A0d2 = A0d(i);
        if (A0d2.A0A != null) {
            Bundle bundle = new Bundle();
            A0d2.A0A.A0B(bundle);
            if (bundle.size() > 0) {
                A0d2.A02 = bundle;
            }
            C04280eZ r0 = A0d2.A0A;
            r0.A09();
            r0.clear();
        }
        A0d2.A0F = true;
        A0d2.A0E = true;
        if ((i == 108 || i == 0) && this.A0L != null) {
            AnonymousClass02x A0d3 = A0d(0);
            A0d3.A0D = false;
            A0E(this, A0d3, null);
        }
    }

    private final int A01(@NonNull Context context, int i) {
        AbstractC000302q A052;
        if (i != -100) {
            if (i != -1) {
                if (i != 0) {
                    if (!(i == 1 || i == 2)) {
                        if (i == 3) {
                            A052 = this.A0F;
                            if (A052 == null) {
                                A052 = new C04470ex(this, context);
                                this.A0F = A052;
                            }
                        } else {
                            throw new IllegalStateException("Unknown value set for night mode. Please use one of the MODE_NIGHT values from AppCompatDelegate.");
                        }
                    }
                } else if (((UiModeManager) context.getApplicationContext().getSystemService(UiModeManager.class)).getNightMode() != 0) {
                    A052 = A05(context);
                }
                return A052.A03();
            }
            return i;
        }
        return -1;
    }

    private final View A03(String str, Context context, @NonNull AttributeSet attributeSet) {
        View r6;
        AnonymousClass02z r4 = this.A0e;
        if (r4 == null) {
            String string = this.A0j.obtainStyledAttributes(AnonymousClass18N.A09).getString(114);
            if (string == null) {
                r4 = new AnonymousClass02z();
                this.A0e = r4;
            } else {
                try {
                    r4 = (AnonymousClass02z) Class.forName(string).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                    this.A0e = r4;
                } catch (Throwable unused) {
                    r4 = new AnonymousClass02z();
                    this.A0e = r4;
                }
            }
        }
        Context A002 = AnonymousClass02z.A00(context, attributeSet);
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
                r6 = new C04090e2(A002, attributeSet, 16842884);
                break;
            case 1:
                r6 = new AnonymousClass0eA(A002, attributeSet, 0);
                break;
            case 2:
                r6 = new AnonymousClass0eE(A002, attributeSet);
                break;
            case 3:
                r6 = new C04170eC(A002, attributeSet);
                break;
            case 4:
                r6 = new C04110e4(A002, attributeSet);
                break;
            case 5:
                r6 = new AnonymousClass0eB(A002, attributeSet, R.attr.imageButtonStyle);
                break;
            case 6:
                r6 = new C04180eD(A002, attributeSet);
                break;
            case 7:
                r6 = new C04150e8(A002, attributeSet);
                break;
            case '\b':
                r6 = new AnonymousClass04B(A002, attributeSet);
                break;
            case '\t':
                r6 = new C04190eF(A002, attributeSet, R.attr.autoCompleteTextViewStyle);
                break;
            case '\n':
                r6 = new C04160e9(A002, attributeSet);
                break;
            case 11:
                r6 = new AnonymousClass04J(A002, attributeSet);
                break;
            case '\f':
                r6 = new AnonymousClass04K(A002, attributeSet);
                break;
            case '\r':
                r6 = new AnonymousClass04X(A002, attributeSet);
                break;
            default:
                r6 = null;
                break;
        }
        if (r6 == null && context != A002) {
            if (str.equals("view")) {
                str = attributeSet.getAttributeValue(null, "class");
            }
            try {
                Object[] objArr = r4.A00;
                objArr[0] = A002;
                objArr[1] = attributeSet;
                if (-1 == str.indexOf(46)) {
                    int i = 0;
                    while (true) {
                        String[] strArr = AnonymousClass02z.A02;
                        if (i < strArr.length) {
                            r6 = AnonymousClass02z.A01(r4, A002, str, strArr[i]);
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
                    r6 = AnonymousClass02z.A01(r4, A002, str, null);
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
            Context context2 = r6.getContext();
            if ((context2 instanceof ContextWrapper) && r6.hasOnClickListeners()) {
                TypedArray obtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, AnonymousClass02z.A01);
                String string2 = obtainStyledAttributes.getString(0);
                if (string2 != null) {
                    r6.setOnClickListener(new View$OnClickListenerC000402y(r6, string2));
                }
                obtainStyledAttributes.recycle();
            }
        }
        return r6;
    }

    private ViewGroup A04() {
        ViewGroup viewGroup;
        Context context;
        Context context2 = this.A0j;
        TypedArray obtainStyledAttributes = context2.obtainStyledAttributes(AnonymousClass18N.A09);
        if (obtainStyledAttributes.hasValue(115)) {
            if (obtainStyledAttributes.getBoolean(124, false)) {
                A0c(1);
            } else if (obtainStyledAttributes.getBoolean(115, false)) {
                A0c(108);
            }
            if (obtainStyledAttributes.getBoolean(116, false)) {
                A0c(109);
            }
            if (obtainStyledAttributes.getBoolean(117, false)) {
                A0c(10);
            }
            this.A0W = obtainStyledAttributes.getBoolean(0, false);
            obtainStyledAttributes.recycle();
            A08();
            this.A0B.getDecorView();
            LayoutInflater from = LayoutInflater.from(context2);
            if (this.A03) {
                boolean z = this.A0Y;
                int i = R.layout.abc_screen_simple;
                if (z) {
                    i = R.layout.abc_screen_simple_overlay_action_mode;
                }
                viewGroup = (ViewGroup) from.inflate(i, (ViewGroup) null);
            } else if (this.A0W) {
                viewGroup = (ViewGroup) from.inflate(R.layout.abc_dialog_title_material, (ViewGroup) null);
                this.A02 = false;
                this.A01 = false;
            } else if (this.A01) {
                TypedValue typedValue = new TypedValue();
                context2.getTheme().resolveAttribute(R.attr.actionBarTheme, typedValue, true);
                int i2 = typedValue.resourceId;
                if (i2 != 0) {
                    context = new AnonymousClass03F(context2, i2);
                } else {
                    context = context2;
                }
                viewGroup = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.abc_screen_toolbar, (ViewGroup) null);
                AbstractC002504b r1 = (AbstractC002504b) viewGroup.findViewById(R.id.decor_content_parent);
                this.A0L = r1;
                r1.setWindowCallback(this.A0B.getCallback());
                if (this.A02) {
                    this.A0L.A5D(109);
                }
                if (this.A0i) {
                    this.A0L.A5D(2);
                }
                if (this.A0h) {
                    this.A0L.A5D(5);
                }
            } else {
                viewGroup = null;
            }
            if (viewGroup != null) {
                viewGroup.setOnApplyWindowInsetsListener(new View$OnApplyWindowInsetsListenerC00860Ah(new C04510f3(this)));
                if (this.A0L == null) {
                    this.A0c = (TextView) viewGroup.findViewById(R.id.title);
                }
                try {
                    Method method = viewGroup.getClass().getMethod("makeOptionalFitsSystemWindows", new Class[0]);
                    if (!method.isAccessible()) {
                        method.setAccessible(true);
                    }
                    method.invoke(viewGroup, new Object[0]);
                } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
                }
                ContentFrameLayout contentFrameLayout = (ContentFrameLayout) viewGroup.findViewById(R.id.action_bar_activity_content);
                ViewGroup viewGroup2 = (ViewGroup) this.A0B.findViewById(16908290);
                if (viewGroup2 != null) {
                    while (viewGroup2.getChildCount() > 0) {
                        View childAt = viewGroup2.getChildAt(0);
                        viewGroup2.removeViewAt(0);
                        contentFrameLayout.addView(childAt);
                    }
                    viewGroup2.setId(-1);
                    contentFrameLayout.setId(16908290);
                    if (viewGroup2 instanceof FrameLayout) {
                        ((FrameLayout) viewGroup2).setForeground(null);
                    }
                }
                this.A0B.setContentView(viewGroup);
                contentFrameLayout.A00 = new C04500f2(this);
                return viewGroup;
            }
            throw new IllegalArgumentException("AppCompat does not support the current theme features: { windowActionBar: " + this.A01 + ", windowActionBarOverlay: " + this.A02 + ", android:windowIsFloating: " + this.A0W + ", windowActionModeOverlay: " + this.A0Y + ", windowNoTitle: " + this.A03 + " }");
        }
        obtainStyledAttributes.recycle();
        throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
    }

    private AbstractC000302q A05(@NonNull Context context) {
        AbstractC000302q r0 = this.A0G;
        if (r0 != null) {
            return r0;
        }
        AnonymousClass035 r2 = AnonymousClass035.A03;
        if (r2 == null) {
            Context applicationContext = context.getApplicationContext();
            r2 = new AnonymousClass035(applicationContext, (LocationManager) applicationContext.getSystemService("location"));
            AnonymousClass035.A03 = r2;
        }
        C04460ew r02 = new C04460ew(this, r2);
        this.A0G = r02;
        return r02;
    }

    private void A06() {
        ContentFrameLayout contentFrameLayout = (ContentFrameLayout) this.A0A.findViewById(16908290);
        View decorView = this.A0B.getDecorView();
        contentFrameLayout.A07.set(decorView.getPaddingLeft(), decorView.getPaddingTop(), decorView.getPaddingRight(), decorView.getPaddingBottom());
        if (contentFrameLayout.isLaidOut()) {
            contentFrameLayout.requestLayout();
        }
        TypedArray obtainStyledAttributes = this.A0j.obtainStyledAttributes(AnonymousClass18N.A09);
        obtainStyledAttributes.getValue(122, contentFrameLayout.getMinWidthMajor());
        obtainStyledAttributes.getValue(123, contentFrameLayout.getMinWidthMinor());
        if (obtainStyledAttributes.hasValue(ImagesBuilder.IMAGE_LANDSCAPE_HEIGHT)) {
            obtainStyledAttributes.getValue(ImagesBuilder.IMAGE_LANDSCAPE_HEIGHT, contentFrameLayout.getFixedWidthMajor());
        }
        if (obtainStyledAttributes.hasValue(121)) {
            obtainStyledAttributes.getValue(121, contentFrameLayout.getFixedWidthMinor());
        }
        if (obtainStyledAttributes.hasValue(118)) {
            obtainStyledAttributes.getValue(118, contentFrameLayout.getFixedHeightMajor());
        }
        if (obtainStyledAttributes.hasValue(119)) {
            obtainStyledAttributes.getValue(119, contentFrameLayout.getFixedHeightMinor());
        }
        obtainStyledAttributes.recycle();
        contentFrameLayout.requestLayout();
    }

    private void A07() {
        CharSequence charSequence;
        if (!this.A0a) {
            this.A0A = A04();
            Object obj = this.A0l;
            if (obj instanceof Activity) {
                charSequence = ((Activity) obj).getTitle();
            } else {
                charSequence = this.A0N;
            }
            if (!TextUtils.isEmpty(charSequence)) {
                AbstractC002504b r0 = this.A0L;
                if (r0 != null) {
                    r0.setWindowTitle(charSequence);
                } else {
                    AnonymousClass02W r02 = this.A0D;
                    if (r02 != null) {
                        r02.A0C(charSequence);
                    } else {
                        TextView textView = this.A0c;
                        if (textView != null) {
                            textView.setText(charSequence);
                        }
                    }
                }
            }
            A06();
            this.A0a = true;
            AnonymousClass02x A0d2 = A0d(0);
            if (!this.A0V && A0d2.A0A == null) {
                this.A04 = (1 << 108) | this.A04;
                if (!this.A0U) {
                    this.A0B.getDecorView().postOnAnimation(this.A0m);
                    this.A0U = true;
                }
            }
        }
    }

    private void A08() {
        if (this.A0B == null) {
            Object obj = this.A0l;
            if (obj instanceof Activity) {
                A0B(((Activity) obj).getWindow());
            }
        }
        if (this.A0B == null) {
            throw new IllegalStateException("We have not been given a Window");
        }
    }

    private void A0A() {
        if (this.A0a) {
            throw new AndroidRuntimeException("Window feature must be requested before adding content");
        }
    }

    private void A0B(@NonNull Window window) {
        if (this.A0B == null) {
            Window.Callback callback = window.getCallback();
            if (!(callback instanceof C04480ey)) {
                C04480ey r0 = new C04480ey(this, callback);
                this.A0E = r0;
                window.setCallback(r0);
                Context context = this.A0j;
                AnonymousClass05Y r1 = new AnonymousClass05Y(context, context.obtainStyledAttributes((AttributeSet) null, A0p));
                Drawable A032 = r1.A03(0);
                if (A032 != null) {
                    window.setBackgroundDrawable(A032);
                }
                r1.A04();
                this.A0B = window;
                return;
            }
        }
        throw new IllegalStateException("AppCompat has already installed itself into the Window");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x005f, code lost:
        if (r0.width != -1) goto L_0x0061;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00b9, code lost:
        if (r0.getCount() > 0) goto L_0x00bb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x010a, code lost:
        if (r0 != null) goto L_0x00a4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void A0C(X.AnonymousClass02x r13, android.view.KeyEvent r14) {
        /*
        // Method dump skipped, instructions count: 357
        */
        throw new UnsupportedOperationException("Method not decompiled: X.LayoutInflater$Factory2C04430et.A0C(X.02x, android.view.KeyEvent):void");
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
    public static void A0D(X.LayoutInflater$Factory2C04430et r11, boolean r12) {
        /*
        // Method dump skipped, instructions count: 275
        */
        throw new UnsupportedOperationException("Method not decompiled: X.LayoutInflater$Factory2C04430et.A0D(X.0et, boolean):void");
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
    public static boolean A0E(X.LayoutInflater$Factory2C04430et r10, X.AnonymousClass02x r11, android.view.KeyEvent r12) {
        /*
        // Method dump skipped, instructions count: 348
        */
        throw new UnsupportedOperationException("Method not decompiled: X.LayoutInflater$Factory2C04430et.A0E(X.0et, X.02x, android.view.KeyEvent):boolean");
    }

    @Override // X.AnonymousClass02m
    public final int A0F() {
        return this.A05;
    }

    @Override // X.AnonymousClass02m
    public final MenuInflater A0H() {
        Context context;
        MenuInflater menuInflater = this.A00;
        if (menuInflater != null) {
            return menuInflater;
        }
        A09();
        AnonymousClass02W r0 = this.A0D;
        if (r0 != null) {
            context = r0.A09();
        } else {
            context = this.A0j;
        }
        AnonymousClass03J r1 = new AnonymousClass03J(context);
        this.A00 = r1;
        return r1;
    }

    @Override // X.AnonymousClass02m
    public final AnonymousClass02Y A0K() {
        return new AnonymousClass0f1(this);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0104, code lost:
        if (r1 == false) goto L_0x0106;
     */
    @Override // X.AnonymousClass02m
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.AnonymousClass03D A0L(@androidx.annotation.NonNull X.AnonymousClass03C r10) {
        /*
        // Method dump skipped, instructions count: 409
        */
        throw new UnsupportedOperationException("Method not decompiled: X.LayoutInflater$Factory2C04430et.A0L(X.03C):X.03D");
    }

    @Override // X.AnonymousClass02m
    public final void A0M() {
        LayoutInflater from = LayoutInflater.from(this.A0j);
        if (from.getFactory() == null) {
            from.setFactory2(this);
        } else {
            from.getFactory2();
        }
    }

    @Override // X.AnonymousClass02m
    public final void A0O() {
        Object obj = this.A0l;
        boolean z = obj instanceof Activity;
        if (z) {
            synchronized (AnonymousClass02m.A01) {
                AnonymousClass02m.A00(this);
            }
        }
        if (this.A0U) {
            this.A0B.getDecorView().removeCallbacks(this.A0m);
        }
        this.A0Z = false;
        this.A0V = true;
        if (this.A05 == -100 || !z || !((Activity) obj).isChangingConfigurations()) {
            A0o.remove(obj.getClass().getName());
        } else {
            A0o.put(obj.getClass().getName(), Integer.valueOf(this.A05));
        }
        AnonymousClass02W r0 = this.A0D;
        if (r0 != null) {
            r0.A03();
        }
        AbstractC000302q r02 = this.A0G;
        if (r02 != null) {
            r02.A02();
        }
        AbstractC000302q r03 = this.A0F;
        if (r03 != null) {
            r03.A02();
        }
    }

    @Override // X.AnonymousClass02m
    public final void A0U(Configuration configuration) {
        AnonymousClass02W A0J2;
        if (this.A01 && this.A0a && (A0J2 = A0J()) != null) {
            A0J2.A0B(configuration);
        }
        AnonymousClass04E.A01().A06(this.A0j);
        A0D(this, false);
    }

    @Override // X.AnonymousClass02m
    public final void A0a(Toolbar toolbar) {
        CharSequence charSequence;
        Object obj = this.A0l;
        boolean z = obj instanceof Activity;
        if (z) {
            AnonymousClass02W A0J2 = A0J();
            if (!(A0J2 instanceof C04340ej)) {
                this.A00 = null;
                if (A0J2 != null) {
                    A0J2.A03();
                }
                if (toolbar != null) {
                    if (z) {
                        charSequence = ((Activity) obj).getTitle();
                    } else {
                        charSequence = this.A0N;
                    }
                    C04370em r0 = new C04370em(toolbar, charSequence, this.A0E);
                    this.A0D = r0;
                    this.A0B.setCallback(r0.A01);
                } else {
                    this.A0D = null;
                    this.A0B.setCallback(this.A0E);
                }
                A0N();
                return;
            }
            throw new IllegalStateException("This Activity already has an action bar supplied by the window decor. Do not request Window.FEATURE_SUPPORT_ACTION_BAR and set windowActionBar to false in your theme to use a Toolbar instead.");
        }
    }

    @Override // X.AnonymousClass02m
    public final void A0b(CharSequence charSequence) {
        this.A0N = charSequence;
        AbstractC002504b r0 = this.A0L;
        if (r0 != null) {
            r0.setWindowTitle(charSequence);
            return;
        }
        AnonymousClass02W r02 = this.A0D;
        if (r02 != null) {
            r02.A0C(charSequence);
            return;
        }
        TextView textView = this.A0c;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    @Override // X.AnonymousClass02m
    public final boolean A0c(int i) {
        if (i == 8) {
            i = 108;
        } else if (i == 9) {
            i = 109;
        }
        if (this.A03 && i == 108) {
            return false;
        }
        if (this.A01 && i == 1) {
            this.A01 = false;
        } else if (i != 1) {
            if (i == 2) {
                A0A();
                this.A0i = true;
                return true;
            } else if (i == 5) {
                A0A();
                this.A0h = true;
                return true;
            } else if (i == 10) {
                A0A();
                this.A0Y = true;
                return true;
            } else if (i == 108) {
                A0A();
                this.A01 = true;
                return true;
            } else if (i != 109) {
                return this.A0B.requestFeature(i);
            } else {
                A0A();
                this.A02 = true;
                return true;
            }
        }
        A0A();
        this.A03 = true;
        return true;
    }

    public final AnonymousClass02x A0d(int i) {
        AnonymousClass02x[] r3 = this.A0b;
        if (r3 == null || r3.length <= i) {
            AnonymousClass02x[] r2 = new AnonymousClass02x[(i + 1)];
            if (r3 != null) {
                System.arraycopy(r3, 0, r2, 0, r3.length);
            }
            this.A0b = r2;
            r3 = r2;
        }
        AnonymousClass02x r0 = r3[i];
        if (r0 != null) {
            return r0;
        }
        AnonymousClass02x r02 = new AnonymousClass02x(i);
        r3[i] = r02;
        return r02;
    }

    public final void A0f(AnonymousClass02x r5, boolean z) {
        ViewGroup viewGroup;
        AbstractC002504b r0;
        if (!z || r5.A03 != 0 || (r0 = this.A0L) == null || !r0.A5X()) {
            WindowManager windowManager = (WindowManager) this.A0j.getSystemService("window");
            if (!(windowManager == null || !r5.A0C || (viewGroup = r5.A08) == null)) {
                windowManager.removeView(viewGroup);
                if (z) {
                    int i = r5.A03;
                    C04280eZ r1 = r5.A0A;
                    if (r5.A0C && !this.A0V) {
                        ((AnonymousClass03L) this.A0E).A00.onPanelClosed(i, r1);
                    }
                }
            }
            r5.A0D = false;
            r5.A0B = false;
            r5.A0C = false;
            r5.A07 = null;
            r5.A0E = true;
            if (this.A0H == r5) {
                this.A0H = null;
                return;
            }
            return;
        }
        A0g(r5.A0A);
    }

    public final void A0g(@NonNull C04280eZ r3) {
        if (!this.A0f) {
            this.A0f = true;
            this.A0L.A29();
            Window.Callback callback = this.A0B.getCallback();
            if (callback != null && !this.A0V) {
                callback.onPanelClosed(108, r3);
            }
            this.A0f = false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:47:0x00a2  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00cd  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean A0h(android.view.KeyEvent r6) {
        /*
        // Method dump skipped, instructions count: 269
        */
        throw new UnsupportedOperationException("Method not decompiled: X.LayoutInflater$Factory2C04430et.A0h(android.view.KeyEvent):boolean");
    }

    @Override // X.AnonymousClass03V
    public final boolean A6D(@NonNull C04280eZ r8, @NonNull MenuItem menuItem) {
        Window.Callback callback = this.A0B.getCallback();
        if (callback == null || this.A0V) {
            return false;
        }
        C04280eZ A042 = r8.A04();
        AnonymousClass02x[] r4 = this.A0b;
        if (r4 == null) {
            return false;
        }
        for (AnonymousClass02x r1 : r4) {
            if (r1 != null && r1.A0A == A042) {
                return callback.onMenuItemSelected(r1.A03, menuItem);
            }
        }
        return false;
    }

    @Override // X.AnonymousClass03V
    public final void A6E(@NonNull C04280eZ r7) {
        AbstractC002504b r0 = this.A0L;
        if (r0 == null || !r0.A1d() || (ViewConfiguration.get(this.A0j).hasPermanentMenuKey() && !this.A0L.A5W())) {
            AnonymousClass02x A0d2 = A0d(0);
            A0d2.A0E = true;
            A0f(A0d2, false);
            A0C(A0d2, null);
            return;
        }
        Window.Callback callback = this.A0B.getCallback();
        if (this.A0L.A5X()) {
            this.A0L.A54();
            if (!this.A0V) {
                callback.onPanelClosed(108, A0d(0).A0A);
            }
        } else if (callback != null && !this.A0V) {
            if (this.A0U && (this.A04 & 1) != 0) {
                View decorView = this.A0B.getDecorView();
                Runnable runnable = this.A0m;
                decorView.removeCallbacks(runnable);
                runnable.run();
            }
            AnonymousClass02x A0d3 = A0d(0);
            C04280eZ r1 = A0d3.A0A;
            if (r1 != null && !A0d3.A0F && callback.onPreparePanel(0, A0d3.A06, r1)) {
                callback.onMenuOpened(108, A0d3.A0A);
                this.A0L.A8S();
            }
        }
    }

    public LayoutInflater$Factory2C04430et(Context context, Window window, AnonymousClass02k r5, Object obj) {
        AnonymousClass06D<String, Integer> r1;
        Integer num;
        this.A0j = context;
        this.A0k = r5;
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
                            this.A05 = appCompatActivity.getDelegate().A0F();
                        }
                    }
                } else {
                    break;
                }
            }
        }
        if (this.A05 == -100 && (num = (r1 = A0o).get(this.A0l.getClass().getName())) != null) {
            this.A05 = num.intValue();
            r1.remove(this.A0l.getClass().getName());
        }
        if (window != null) {
            A0B(window);
        }
        AnonymousClass04E.A02();
    }

    private void A09() {
        AnonymousClass02W r2;
        A07();
        if (this.A01 && (r2 = this.A0D) == null) {
            Object obj = this.A0l;
            if (obj instanceof Activity) {
                r2 = new C04340ej((Activity) obj, this.A02);
            } else if (obj instanceof Dialog) {
                r2 = new C04340ej((Dialog) obj);
            } else {
                if (r2 == null) {
                    return;
                }
                r2.A0E(this.A0g);
            }
            this.A0D = r2;
            r2.A0E(this.A0g);
        }
    }

    @Override // X.AnonymousClass02m
    @Nullable
    public final <T extends View> T A0I(@IdRes int i) {
        A07();
        return (T) this.A0B.findViewById(i);
    }

    @Override // X.AnonymousClass02m
    public final AnonymousClass02W A0J() {
        A09();
        return this.A0D;
    }

    @Override // X.AnonymousClass02m
    public final void A0N() {
        AnonymousClass02W A0J2 = A0J();
        if (A0J2 == null || !A0J2.A05()) {
            this.A04 = (1 << 0) | this.A04;
            if (!this.A0U) {
                this.A0B.getDecorView().postOnAnimation(this.A0m);
                this.A0U = true;
            }
        }
    }

    @Override // X.AnonymousClass02m
    public final void A0P() {
        AnonymousClass02W A0J2 = A0J();
        if (A0J2 != null) {
            A0J2.A0F(true);
        }
    }

    @Override // X.AnonymousClass02m
    public final void A0T(int i) {
        A07();
        ViewGroup viewGroup = (ViewGroup) this.A0A.findViewById(16908290);
        viewGroup.removeAllViews();
        LayoutInflater.from(this.A0j).inflate(i, viewGroup);
        ((AnonymousClass03L) this.A0E).A00.onContentChanged();
    }

    @Override // X.AnonymousClass02m
    public final void A0X(View view) {
        A07();
        ViewGroup viewGroup = (ViewGroup) this.A0A.findViewById(16908290);
        viewGroup.removeAllViews();
        viewGroup.addView(view);
        ((AnonymousClass03L) this.A0E).A00.onContentChanged();
    }

    @Override // X.AnonymousClass02m
    public final void A0Y(View view, ViewGroup.LayoutParams layoutParams) {
        A07();
        ((ViewGroup) this.A0A.findViewById(16908290)).addView(view, layoutParams);
        ((AnonymousClass03L) this.A0E).A00.onContentChanged();
    }

    @Override // X.AnonymousClass02m
    public final void A0Z(View view, ViewGroup.LayoutParams layoutParams) {
        A07();
        ViewGroup viewGroup = (ViewGroup) this.A0A.findViewById(16908290);
        viewGroup.removeAllViews();
        viewGroup.addView(view, layoutParams);
        ((AnonymousClass03L) this.A0E).A00.onContentChanged();
    }

    @Override // X.AnonymousClass02m
    public final void A0S(@StyleRes int i) {
        this.A06 = i;
    }

    @Override // X.AnonymousClass02m
    public final void A0W(Bundle bundle) {
        A07();
    }

    public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return A03(str, context, attributeSet);
    }

    public final View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }
}
