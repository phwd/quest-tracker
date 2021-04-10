package androidx.appcompat.widget;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.customview.view.AbsSavedState;
import com.oculus.browser.R;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.WeakHashMap;
import org.chromium.chrome.browser.language.settings.AddLanguageFragment;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SearchView extends AbstractC5715y80 implements AbstractC0234Dv {
    public static final C2183dR0 U = (Build.VERSION.SDK_INT < 29 ? new C2183dR0() : null);
    public CharSequence A0;
    public boolean B0;
    public int C0;
    public final Runnable D0;
    public Runnable E0;
    public final View.OnClickListener F0;
    public View.OnKeyListener G0;
    public final TextView.OnEditorActionListener H0;
    public final AdapterView.OnItemClickListener I0;
    public final AdapterView.OnItemSelectedListener J0;
    public TextWatcher K0;
    public final SearchAutoComplete V;
    public final View W;
    public final View a0;
    public final View b0;
    public final ImageView c0;
    public final ImageView d0;
    public final ImageView e0;
    public final ImageView f0;
    public final View g0;
    public C2695gR0 h0;
    public Rect i0;
    public Rect j0;
    public int[] k0;
    public int[] l0;
    public final ImageView m0;
    public final Drawable n0;
    public final Intent o0;
    public final Intent p0;
    public final CharSequence q0;
    public AbstractC2012cR0 r0;
    public C4508r3 s0;
    public View.OnClickListener t0;
    public boolean u0;
    public boolean v0;
    public CharSequence w0;
    public boolean x0;
    public int y0;
    public CharSequence z0;

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class SavedState extends AbsSavedState {
        public static final Parcelable.Creator CREATOR = new C2353eR0();
        public boolean H;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            StringBuilder i = AbstractC2531fV.i("SearchView.SavedState{");
            i.append(Integer.toHexString(System.identityHashCode(this)));
            i.append(" isIconified=");
            i.append(this.H);
            i.append("}");
            return i.toString();
        }

        @Override // androidx.customview.view.AbsSavedState
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.G, i);
            parcel.writeValue(Boolean.valueOf(this.H));
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.H = ((Boolean) parcel.readValue(null)).booleanValue();
        }
    }

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class SearchAutoComplete extends J7 {
        public int I = getThreshold();

        /* renamed from: J  reason: collision with root package name */
        public SearchView f9462J;
        public boolean K;
        public final Runnable L = new RunnableC2524fR0(this);

        public SearchAutoComplete(Context context, AttributeSet attributeSet) {
            super(context, attributeSet, R.attr.f1830_resource_name_obfuscated_RES_2130968629);
        }

        public void a() {
            if (Build.VERSION.SDK_INT >= 29) {
                setInputMethodMode(1);
                if (enoughToFilter()) {
                    showDropDown();
                    return;
                }
                return;
            }
            C2183dR0 dr0 = SearchView.U;
            Objects.requireNonNull(dr0);
            C2183dR0.a();
            Method method = dr0.c;
            if (method != null) {
                try {
                    method.invoke(this, Boolean.TRUE);
                } catch (Exception unused) {
                }
            }
        }

        public void b(boolean z) {
            InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
            if (!z) {
                this.K = false;
                removeCallbacks(this.L);
                inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
            } else if (inputMethodManager.isActive(this)) {
                this.K = false;
                removeCallbacks(this.L);
                inputMethodManager.showSoftInput(this, 0);
            } else {
                this.K = true;
            }
        }

        public boolean enoughToFilter() {
            return this.I <= 0 || super.enoughToFilter();
        }

        @Override // defpackage.J7
        public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
            InputConnection onCreateInputConnection = super.onCreateInputConnection(editorInfo);
            if (this.K) {
                removeCallbacks(this.L);
                post(this.L);
            }
            return onCreateInputConnection;
        }

        public void onFinishInflate() {
            super.onFinishInflate();
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            Configuration configuration = getResources().getConfiguration();
            int i = configuration.screenWidthDp;
            int i2 = configuration.screenHeightDp;
            setMinWidth((int) TypedValue.applyDimension(1, (float) ((i < 960 || i2 < 720 || configuration.orientation != 2) ? (i >= 600 || (i >= 640 && i2 >= 480)) ? 192 : 160 : 256), displayMetrics));
        }

        public void onFocusChanged(boolean z, int i, Rect rect) {
            super.onFocusChanged(z, i, rect);
            SearchView searchView = this.f9462J;
            searchView.B(searchView.v0);
            searchView.post(searchView.D0);
            if (searchView.V.hasFocus()) {
                searchView.r();
            }
        }

        public boolean onKeyPreIme(int i, KeyEvent keyEvent) {
            if (i == 4) {
                if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                    KeyEvent.DispatcherState keyDispatcherState = getKeyDispatcherState();
                    if (keyDispatcherState != null) {
                        keyDispatcherState.startTracking(keyEvent, this);
                    }
                    return true;
                } else if (keyEvent.getAction() == 1) {
                    KeyEvent.DispatcherState keyDispatcherState2 = getKeyDispatcherState();
                    if (keyDispatcherState2 != null) {
                        keyDispatcherState2.handleUpEvent(keyEvent);
                    }
                    if (keyEvent.isTracking() && !keyEvent.isCanceled()) {
                        this.f9462J.clearFocus();
                        b(false);
                        return true;
                    }
                }
            }
            return super.onKeyPreIme(i, keyEvent);
        }

        public void onWindowFocusChanged(boolean z) {
            super.onWindowFocusChanged(z);
            if (z && this.f9462J.hasFocus() && getVisibility() == 0) {
                boolean z2 = true;
                this.K = true;
                Context context = getContext();
                C2183dR0 dr0 = SearchView.U;
                if (context.getResources().getConfiguration().orientation != 2) {
                    z2 = false;
                }
                if (z2) {
                    a();
                }
            }
        }

        public void performCompletion() {
        }

        public void replaceText(CharSequence charSequence) {
        }

        public void setThreshold(int i) {
            super.setThreshold(i);
            this.I = i;
        }
    }

    public SearchView(Context context) {
        this(context, null);
    }

    public final void A() {
        SpannableStringBuilder spannableStringBuilder = this.w0;
        if (spannableStringBuilder == null) {
            spannableStringBuilder = this.q0;
        }
        SearchAutoComplete searchAutoComplete = this.V;
        if (spannableStringBuilder == null) {
            spannableStringBuilder = "";
        }
        if (this.u0 && this.n0 != null) {
            int textSize = (int) (((double) searchAutoComplete.getTextSize()) * 1.25d);
            this.n0.setBounds(0, 0, textSize, textSize);
            SpannableStringBuilder spannableStringBuilder2 = new SpannableStringBuilder("   ");
            spannableStringBuilder2.setSpan(new ImageSpan(this.n0), 1, 2, 33);
            spannableStringBuilder2.append(spannableStringBuilder);
            spannableStringBuilder = spannableStringBuilder2;
        }
        searchAutoComplete.setHint(spannableStringBuilder);
    }

    public final void B(boolean z) {
        this.v0 = z;
        int i = 0;
        int i2 = z ? 0 : 8;
        TextUtils.isEmpty(this.V.getText());
        this.c0.setVisibility(i2);
        this.d0.setVisibility(8);
        this.W.setVisibility(z ? 8 : 0);
        if (this.m0.getDrawable() == null || this.u0) {
            i = 8;
        }
        this.m0.setVisibility(i);
        y();
        this.f0.setVisibility(8);
        this.b0.setVisibility(8);
    }

    @Override // defpackage.AbstractC0234Dv
    public void b() {
        if (!this.B0) {
            this.B0 = true;
            int imeOptions = this.V.getImeOptions();
            this.C0 = imeOptions;
            this.V.setImeOptions(imeOptions | 33554432);
            this.V.setText("");
            w(false);
        }
    }

    public void clearFocus() {
        this.x0 = true;
        super.clearFocus();
        this.V.clearFocus();
        this.V.b(false);
        this.x0 = false;
    }

    @Override // defpackage.AbstractC0234Dv
    public void f() {
        x("", false);
        clearFocus();
        B(true);
        this.V.setImeOptions(this.C0);
        this.B0 = false;
    }

    public void onDetachedFromWindow() {
        removeCallbacks(this.D0);
        post(this.E0);
        super.onDetachedFromWindow();
    }

    @Override // defpackage.AbstractC5715y80
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (z) {
            SearchAutoComplete searchAutoComplete = this.V;
            Rect rect = this.i0;
            searchAutoComplete.getLocationInWindow(this.k0);
            getLocationInWindow(this.l0);
            int[] iArr = this.k0;
            int i5 = iArr[1];
            int[] iArr2 = this.l0;
            int i6 = i5 - iArr2[1];
            int i7 = iArr[0] - iArr2[0];
            rect.set(i7, i6, searchAutoComplete.getWidth() + i7, searchAutoComplete.getHeight() + i6);
            Rect rect2 = this.j0;
            Rect rect3 = this.i0;
            rect2.set(rect3.left, 0, rect3.right, i4 - i2);
            C2695gR0 gr0 = this.h0;
            if (gr0 == null) {
                C2695gR0 gr02 = new C2695gR0(this.j0, this.i0, this.V);
                this.h0 = gr02;
                setTouchDelegate(gr02);
                return;
            }
            gr0.a(this.j0, this.i0);
        }
    }

    @Override // defpackage.AbstractC5715y80
    public void onMeasure(int i, int i2) {
        int i3;
        if (this.v0) {
            super.onMeasure(i, i2);
            return;
        }
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == Integer.MIN_VALUE) {
            int i4 = this.y0;
            size = i4 > 0 ? Math.min(i4, size) : Math.min(getContext().getResources().getDimensionPixelSize(R.dimen.f16200_resource_name_obfuscated_RES_2131165239), size);
        } else if (mode == 0) {
            size = this.y0;
            if (size <= 0) {
                size = getContext().getResources().getDimensionPixelSize(R.dimen.f16200_resource_name_obfuscated_RES_2131165239);
            }
        } else if (mode == 1073741824 && (i3 = this.y0) > 0) {
            size = Math.min(i3, size);
        }
        int mode2 = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        if (mode2 == Integer.MIN_VALUE) {
            size2 = Math.min(getContext().getResources().getDimensionPixelSize(R.dimen.f16190_resource_name_obfuscated_RES_2131165238), size2);
        } else if (mode2 == 0) {
            size2 = getContext().getResources().getDimensionPixelSize(R.dimen.f16190_resource_name_obfuscated_RES_2131165238);
        }
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(size, 1073741824), View.MeasureSpec.makeMeasureSpec(size2, 1073741824));
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.G);
        B(savedState.H);
        requestLayout();
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.H = this.v0;
        return savedState;
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        post(this.D0);
    }

    public void r() {
        if (Build.VERSION.SDK_INT >= 29) {
            this.V.refreshAutoCompleteResults();
            return;
        }
        C2183dR0 dr0 = U;
        SearchAutoComplete searchAutoComplete = this.V;
        Objects.requireNonNull(dr0);
        C2183dR0.a();
        Method method = dr0.f9781a;
        if (method != null) {
            try {
                method.invoke(searchAutoComplete, new Object[0]);
            } catch (Exception unused) {
            }
        }
        C2183dR0 dr02 = U;
        SearchAutoComplete searchAutoComplete2 = this.V;
        Objects.requireNonNull(dr02);
        C2183dR0.a();
        Method method2 = dr02.b;
        if (method2 != null) {
            try {
                method2.invoke(searchAutoComplete2, new Object[0]);
            } catch (Exception unused2) {
            }
        }
    }

    public boolean requestFocus(int i, Rect rect) {
        if (this.x0 || !isFocusable()) {
            return false;
        }
        if (this.v0) {
            return super.requestFocus(i, rect);
        }
        boolean requestFocus = this.V.requestFocus(i, rect);
        if (requestFocus) {
            B(false);
        }
        return requestFocus;
    }

    public void s() {
        if (!TextUtils.isEmpty(this.V.getText())) {
            this.V.setText("");
            this.V.requestFocus();
            this.V.b(true);
        } else if (this.u0) {
            C4508r3 r3Var = this.s0;
            if (r3Var != null) {
                AddLanguageFragment addLanguageFragment = r3Var.f11180a;
                addLanguageFragment.A0 = "";
                addLanguageFragment.C0.t(addLanguageFragment.D0);
            }
            clearFocus();
            B(true);
        }
    }

    public boolean t(int i) {
        throw null;
    }

    public void u() {
        B(false);
        this.V.requestFocus();
        this.V.b(true);
        View.OnClickListener onClickListener = this.t0;
        if (onClickListener != null) {
            onClickListener.onClick(this);
        }
    }

    public void v() {
        Editable text = this.V.getText();
        if (text != null && TextUtils.getTrimmedLength(text) > 0) {
            AbstractC2012cR0 cr0 = this.r0;
            if (cr0 == null || !cr0.onQueryTextSubmit(text.toString())) {
                this.V.b(false);
                this.V.dismissDropDown();
            }
        }
    }

    public void w(boolean z) {
        if (z) {
            s();
        } else {
            u();
        }
    }

    public void x(CharSequence charSequence, boolean z) {
        this.V.setText(charSequence);
        if (charSequence != null) {
            SearchAutoComplete searchAutoComplete = this.V;
            searchAutoComplete.setSelection(searchAutoComplete.length());
            this.A0 = charSequence;
        }
        if (z && !TextUtils.isEmpty(charSequence)) {
            v();
        }
    }

    public final void y() {
        boolean z = true;
        boolean z2 = !TextUtils.isEmpty(this.V.getText());
        int i = 0;
        if (!z2 && (!this.u0 || this.B0)) {
            z = false;
        }
        ImageView imageView = this.e0;
        if (!z) {
            i = 8;
        }
        imageView.setVisibility(i);
        Drawable drawable = this.e0.getDrawable();
        if (drawable != null) {
            drawable.setState(z2 ? ViewGroup.ENABLED_STATE_SET : ViewGroup.EMPTY_STATE_SET);
        }
    }

    public void z() {
        int[] iArr = this.V.hasFocus() ? ViewGroup.FOCUSED_STATE_SET : ViewGroup.EMPTY_STATE_SET;
        Drawable background = this.a0.getBackground();
        if (background != null) {
            background.setState(iArr);
        }
        Drawable background2 = this.b0.getBackground();
        if (background2 != null) {
            background2.setState(iArr);
        }
        invalidate();
    }

    public SearchView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.f7400_resource_name_obfuscated_RES_2130969186);
    }

    public SearchView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.i0 = new Rect();
        this.j0 = new Rect();
        this.k0 = new int[2];
        this.l0 = new int[2];
        this.D0 = new TQ0(this);
        this.E0 = new UQ0(this);
        new WeakHashMap();
        XQ0 xq0 = new XQ0(this);
        this.F0 = xq0;
        this.G0 = new YQ0(this);
        ZQ0 zq0 = new ZQ0(this);
        this.H0 = zq0;
        C1661aR0 ar0 = new C1661aR0(this);
        this.I0 = ar0;
        C1841bR0 br0 = new C1841bR0(this);
        this.J0 = br0;
        this.K0 = new SQ0(this);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, FJ0.y0, i, 0);
        C0514Ii1 ii1 = new C0514Ii1(context, obtainStyledAttributes);
        LayoutInflater.from(context).inflate(ii1.l(9, R.layout.f36520_resource_name_obfuscated_RES_2131623961), (ViewGroup) this, true);
        SearchAutoComplete searchAutoComplete = (SearchAutoComplete) findViewById(R.id.search_src_text);
        this.V = searchAutoComplete;
        searchAutoComplete.f9462J = this;
        this.W = findViewById(R.id.search_edit_frame);
        View findViewById = findViewById(R.id.search_plate);
        this.a0 = findViewById;
        View findViewById2 = findViewById(R.id.submit_area);
        this.b0 = findViewById2;
        ImageView imageView = (ImageView) findViewById(R.id.search_button);
        this.c0 = imageView;
        ImageView imageView2 = (ImageView) findViewById(R.id.search_go_btn);
        this.d0 = imageView2;
        ImageView imageView3 = (ImageView) findViewById(R.id.search_close_btn);
        this.e0 = imageView3;
        ImageView imageView4 = (ImageView) findViewById(R.id.search_voice_btn);
        this.f0 = imageView4;
        ImageView imageView5 = (ImageView) findViewById(R.id.search_mag_icon);
        this.m0 = imageView5;
        findViewById.setBackground(ii1.g(10));
        findViewById2.setBackground(ii1.g(14));
        imageView.setImageDrawable(ii1.g(13));
        imageView2.setImageDrawable(ii1.g(7));
        imageView3.setImageDrawable(ii1.g(4));
        imageView4.setImageDrawable(ii1.g(16));
        imageView5.setImageDrawable(ii1.g(13));
        this.n0 = ii1.g(12);
        Il1.a(imageView, getResources().getString(R.string.f45180_resource_name_obfuscated_RES_2131951835));
        ii1.l(15, R.layout.f36510_resource_name_obfuscated_RES_2131623960);
        ii1.l(5, 0);
        imageView.setOnClickListener(xq0);
        imageView3.setOnClickListener(xq0);
        imageView2.setOnClickListener(xq0);
        imageView4.setOnClickListener(xq0);
        searchAutoComplete.setOnClickListener(xq0);
        searchAutoComplete.addTextChangedListener(this.K0);
        searchAutoComplete.setOnEditorActionListener(zq0);
        searchAutoComplete.setOnItemClickListener(ar0);
        searchAutoComplete.setOnItemSelectedListener(br0);
        searchAutoComplete.setOnKeyListener(this.G0);
        searchAutoComplete.setOnFocusChangeListener(new VQ0(this));
        boolean a2 = ii1.a(8, true);
        if (this.u0 != a2) {
            this.u0 = a2;
            B(a2);
            A();
        }
        int f = ii1.f(1, -1);
        if (f != -1) {
            this.y0 = f;
            requestLayout();
        }
        this.q0 = ii1.n(6);
        this.w0 = ii1.n(11);
        int j = ii1.j(3, -1);
        if (j != -1) {
            searchAutoComplete.setImeOptions(j);
        }
        int j2 = ii1.j(2, -1);
        if (j2 != -1) {
            searchAutoComplete.setInputType(j2);
        }
        setFocusable(ii1.a(0, true));
        obtainStyledAttributes.recycle();
        Intent intent = new Intent("android.speech.action.WEB_SEARCH");
        this.o0 = intent;
        intent.addFlags(268435456);
        intent.putExtra("android.speech.extra.LANGUAGE_MODEL", "web_search");
        Intent intent2 = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        this.p0 = intent2;
        intent2.addFlags(268435456);
        View findViewById3 = findViewById(searchAutoComplete.getDropDownAnchor());
        this.g0 = findViewById3;
        if (findViewById3 != null) {
            findViewById3.addOnLayoutChangeListener(new WQ0(this));
        }
        B(this.u0);
        A();
    }
}
