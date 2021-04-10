package defpackage;

import J.N;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.oculus.browser.R;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;
import org.chromium.chrome.browser.autofill.CardUnmaskBridge;

/* renamed from: Im  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnClickListenerC0521Im implements TextWatcher, View.OnClickListener, AbstractC3087il0 {
    public final CardUnmaskBridge F;
    public UH0 G;
    public boolean H;
    public final View I;

    /* renamed from: J  reason: collision with root package name */
    public final TextView f8249J;
    public final TextView K;
    public final TextView L;
    public final EditText M;
    public final EditText N;
    public final EditText O;
    public final View P;
    public final TextView Q;
    public final TextView R;
    public final CheckBox S;
    public final CheckBox T;
    public final ImageView U;
    public PopupWindow V;
    public final ViewGroup W;
    public final View X;
    public final ProgressBar Y;
    public final TextView Z;
    public final long a0;
    public int b0;
    public int c0;
    public C2746gl0 d0;
    public Context e0;
    public boolean f0;
    public boolean g0;
    public boolean h0;

    public View$OnClickListenerC0521Im(Context context, CardUnmaskBridge cardUnmaskBridge, String str, String str2, String str3, int i, int i2, boolean z, boolean z2, boolean z3, boolean z4, long j) {
        String str4;
        this.F = cardUnmaskBridge;
        View inflate = LayoutInflater.from(context).inflate(R.layout.f36900_resource_name_obfuscated_RES_2131623999, (ViewGroup) null);
        TextView textView = (TextView) inflate.findViewById(R.id.instructions);
        this.f8249J = textView;
        textView.setText(str2);
        TextView textView2 = (TextView) inflate.findViewById(R.id.title);
        this.K = textView2;
        this.I = inflate;
        this.L = (TextView) inflate.findViewById(R.id.no_retry_error_message);
        EditText editText = (EditText) inflate.findViewById(R.id.card_unmask_input);
        this.M = editText;
        EditText editText2 = (EditText) inflate.findViewById(R.id.expiration_month);
        this.N = editText2;
        EditText editText3 = (EditText) inflate.findViewById(R.id.expiration_year);
        this.O = editText3;
        this.P = inflate.findViewById(R.id.expiration_container);
        TextView textView3 = (TextView) inflate.findViewById(R.id.new_card_link);
        this.Q = textView3;
        textView3.setOnClickListener(this);
        this.R = (TextView) inflate.findViewById(R.id.error_message);
        this.S = (CheckBox) inflate.findViewById(R.id.store_locally_checkbox);
        CheckBox checkBox = (CheckBox) inflate.findViewById(R.id.use_screenlock_checkbox);
        this.T = checkBox;
        checkBox.setChecked(z4);
        if (!z3) {
            checkBox.setVisibility(8);
            checkBox.setChecked(false);
        }
        ImageView imageView = (ImageView) inflate.findViewById(R.id.store_locally_tooltip_icon);
        this.U = imageView;
        imageView.setOnClickListener(this);
        inflate.findViewById(R.id.store_locally_container).setVisibility(8);
        this.W = (ViewGroup) inflate.findViewById(R.id.controls_container);
        this.X = inflate.findViewById(R.id.verification_overlay);
        this.Y = (ProgressBar) inflate.findViewById(R.id.verification_progress_bar);
        this.Z = (TextView) inflate.findViewById(R.id.verification_message);
        this.a0 = j;
        ((ImageView) inflate.findViewById(R.id.cvc_hint_image)).setImageResource(i);
        Resources resources = context.getResources();
        if (!N.M09VlOh_("AutofillDownstreamCvcPromptUseGooglePayLogo") || z) {
            textView2.setVisibility(8);
            str4 = str;
        } else {
            Drawable drawable = resources.getDrawable(i2);
            SpannableString spannableString = new SpannableString(AbstractC2531fV.f("   ", str));
            float textSize = textView2.getTextSize() / ((float) drawable.getIntrinsicHeight());
            drawable.setBounds(0, 0, (int) (((float) drawable.getIntrinsicWidth()) * textSize), (int) (textSize * ((float) drawable.getIntrinsicHeight())));
            spannableString.setSpan(new ImageSpan(drawable, 2), 0, 1, 17);
            textView2.setText(spannableString, TextView.BufferType.SPANNABLE);
            str4 = null;
        }
        HH0 hh0 = new HH0(AbstractC3258jl0.r);
        hh0.e(AbstractC3258jl0.f10235a, this);
        hh0.e(AbstractC3258jl0.f, inflate);
        hh0.e(AbstractC3258jl0.g, str3);
        hh0.d(AbstractC3258jl0.j, resources, R.string.f48470_resource_name_obfuscated_RES_2131952164);
        if (str4 != null) {
            hh0.e(AbstractC3258jl0.c, str4);
        }
        this.G = hh0.a();
        this.H = z2;
        this.b0 = -1;
        this.c0 = -1;
        if (z2) {
            C0460Hm hm = new C0460Hm(this, null);
            Executor executor = AbstractC2032cb.f9616a;
            hm.f();
            ((ExecutorC1463Ya) executor).execute(hm.e);
        }
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(N.Mu0etYO0(cardUnmaskBridge.f10610a, cardUnmaskBridge))});
        editText.setOnEditorActionListener(new C5987zm(this));
        editText.setOnFocusChangeListener(new View$OnFocusChangeListenerC0033Am(this));
        editText2.setOnFocusChangeListener(new View$OnFocusChangeListenerC0094Bm(this));
        editText3.setOnFocusChangeListener(new View$OnFocusChangeListenerC0155Cm(this));
    }

    public final void a() {
        TextView textView = this.R;
        textView.setText((CharSequence) null);
        textView.setVisibility(8);
        AbstractC0073Be.f(7, this.e0, this.N, this.O, this.M);
    }

    public void afterTextChanged(Editable editable) {
        h();
    }

    public final void b() {
        InputMethodManager inputMethodManager = (InputMethodManager) this.e0.getSystemService("input_method");
        EditText editText = this.H ? this.N : this.M;
        editText.requestFocus();
        inputMethodManager.showSoftInput(editText, 1);
        editText.sendAccessibilityEvent(8);
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public final void c(boolean z) {
        this.M.setEnabled(z);
        this.N.setEnabled(z);
        this.O.setEnabled(z);
        this.S.setEnabled(z);
        this.G.j(AbstractC3258jl0.i, !z);
    }

    @Override // defpackage.AbstractC3087il0
    public void d(UH0 uh0, int i) {
        boolean z = true;
        if (i == 0) {
            CardUnmaskBridge cardUnmaskBridge = this.F;
            String obj = this.M.getText().toString();
            String obj2 = this.N.getText().toString();
            String num = Integer.toString(AbstractC0073Be.b(this.O));
            CheckBox checkBox = this.S;
            if (checkBox == null || !checkBox.isChecked()) {
                z = false;
            }
            N.McBOMUil(cardUnmaskBridge.f10610a, cardUnmaskBridge, obj, obj2, num, z, this.T.isChecked());
        } else if (i == 1) {
            this.d0.b(uh0, 2);
        }
    }

    public final void e(int i) {
        this.X.setVisibility(i);
        this.W.setAlpha(1.0f);
        int i2 = 0;
        boolean z = i == 8;
        if (!z) {
            this.X.setAlpha(0.0f);
            long j = (long) 250;
            this.X.animate().alpha(1.0f).setDuration(j);
            this.W.animate().alpha(0.0f).setDuration(j);
        }
        ViewGroup viewGroup = this.W;
        if (!z) {
            i2 = 4;
        }
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        viewGroup.setImportantForAccessibility(i2);
        this.W.setDescendantFocusability(z ? 131072 : 393216);
    }

    @Override // defpackage.AbstractC3087il0
    public void f(UH0 uh0, int i) {
        CardUnmaskBridge cardUnmaskBridge = this.F;
        N.Mek0Fv7c(cardUnmaskBridge.f10610a, cardUnmaskBridge);
        this.G = null;
    }

    public final void g() {
        if (this.H && this.P.getVisibility() != 0) {
            this.P.setVisibility(0);
            this.M.setEms(3);
            this.N.addTextChangedListener(this);
            this.O.addTextChangedListener(this);
        }
    }

    public final void h() {
        int a2 = this.H ? AbstractC0073Be.a(this.N, this.O, this.f0, this.g0) : 7;
        CardUnmaskBridge cardUnmaskBridge = this.F;
        if (!N.MRcUBmjo(cardUnmaskBridge.f10610a, cardUnmaskBridge, this.M.getText().toString())) {
            if (this.h0 && !this.M.isFocused()) {
                a2 = (a2 == 7 || a2 == 6) ? 4 : 5;
            } else if (a2 == 7) {
                a2 = 6;
            }
        }
        this.G.j(AbstractC3258jl0.i, a2 != 7);
        AbstractC0073Be.d(a2, this.e0, this.R);
        AbstractC0073Be.f(a2, this.e0, this.N, this.O, this.M);
        if (a2 != 6) {
            return;
        }
        if (!this.N.isFocused() || this.N.getText().length() != 2) {
            if (this.O.isFocused() && this.O.getText().length() == 2) {
                this.M.requestFocus();
                this.h0 = true;
            }
        } else if (this.O.getText().length() == 2) {
            this.M.requestFocus();
            this.h0 = true;
        } else {
            this.O.requestFocus();
            this.g0 = true;
        }
    }

    public void onClick(View view) {
        if (view != this.U) {
            CardUnmaskBridge cardUnmaskBridge = this.F;
            N.Mxa$aTDN(cardUnmaskBridge.f10610a, cardUnmaskBridge);
            this.Q.setVisibility(8);
            this.M.setText((CharSequence) null);
            a();
            this.N.requestFocus();
        } else if (this.V == null) {
            this.V = new PopupWindow(this.e0);
            RunnableC0338Fm fm = new RunnableC0338Fm(this);
            Locale locale = Locale.getDefault();
            Locale locale2 = AbstractC0630Kg1.f8381a;
            AbstractC0073Be.e(this.e0, this.V, R.string.f47150_resource_name_obfuscated_RES_2131952032, new C0399Gm(this), TextUtils.getLayoutDirectionFromLocale(locale) == 0 ? this.S : this.U, fm);
        }
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
