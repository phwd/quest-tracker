package defpackage;

import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.material.textfield.TextInputLayout;
import com.oculus.browser.R;
import com.oculus.os.Version;
import java.util.List;
import java.util.Objects;
import org.chromium.chrome.browser.autofill.PersonalDataManager;

/* renamed from: DK  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DK extends FrameLayout implements AbstractC4899tK, View.OnClickListener {
    public static final /* synthetic */ int F = 0;
    public C4729sK G;
    public TextView.OnEditorActionListener H;
    public TextInputLayout I = ((TextInputLayout) findViewById(R.id.text_input_layout));

    /* renamed from: J  reason: collision with root package name */
    public AutoCompleteTextView f7881J;
    public View K;
    public ImageView L;
    public ImageView M;
    public int N;
    public boolean O;

    public DK(Context context, C4729sK sKVar, TextView.OnEditorActionListener onEditorActionListener, InputFilter inputFilter, TextWatcher textWatcher) {
        super(context);
        this.G = sKVar;
        this.H = onEditorActionListener;
        LayoutInflater.from(context).inflate(R.layout.f40560_resource_name_obfuscated_RES_2131624365, (ViewGroup) this, true);
        CharSequence charSequence = sKVar.p;
        if (sKVar.d()) {
            charSequence = ((Object) charSequence) + "*";
        }
        this.I.B(charSequence);
        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) this.I.findViewById(R.id.text_view);
        this.f7881J = autoCompleteTextView;
        autoCompleteTextView.setText(sKVar.s);
        this.f7881J.setContentDescription(charSequence);
        this.f7881J.setOnEditorActionListener(this.H);
        this.f7881J.setOnKeyListener(new View$OnKeyListenerC5919zK());
        View findViewById = findViewById(R.id.icons_layer);
        this.K = findViewById;
        findViewById.addOnLayoutChangeListener(new AK(this));
        if (sKVar.v != null) {
            ImageView imageView = (ImageView) this.K.findViewById(R.id.action_icon);
            this.L = imageView;
            imageView.setImageDrawable(C0636Ki1.b(context, sKVar.x, R.color.f11230_resource_name_obfuscated_RES_2131099813));
            this.L.setContentDescription(context.getResources().getString(sKVar.y));
            this.L.setOnClickListener(this);
            this.L.setVisibility(0);
        }
        if (sKVar.k != null) {
            ImageView imageView2 = (ImageView) this.K.findViewById(R.id.value_icon);
            this.M = imageView2;
            imageView2.setVisibility(0);
        }
        this.f7881J.setOnFocusChangeListener(new BK(this));
        this.f7881J.addTextChangedListener(new CK(this, sKVar));
        List list = sKVar.h;
        if (list != null && !list.isEmpty()) {
            this.f7881J.setAdapter(new ArrayAdapter(getContext(), 17367049, sKVar.h));
            this.f7881J.setThreshold(0);
        }
        if (inputFilter != null) {
            this.f7881J.setFilters(new InputFilter[]{inputFilter});
        }
        if (textWatcher != null) {
            this.f7881J.addTextChangedListener(textWatcher);
            textWatcher.afterTextChanged(this.f7881J.getText());
        }
        switch (sKVar.f11266a) {
            case 1:
            case Version.VERSION_7 /*{ENCODED_INT: 7}*/:
                this.f7881J.setInputType(3);
                return;
            case 2:
                this.f7881J.setInputType(33);
                return;
            case 3:
                this.f7881J.setInputType(139377);
                return;
            case 4:
                this.f7881J.setInputType(8289);
                return;
            case 5:
            case 6:
                this.f7881J.setInputType(4209);
                return;
            default:
                this.f7881J.setInputType(8305);
                return;
        }
    }

    @Override // defpackage.AbstractC4899tK
    public boolean a() {
        return this.G.e();
    }

    @Override // defpackage.AbstractC4899tK
    public boolean b() {
        return this.G.d();
    }

    @Override // defpackage.AbstractC4899tK
    public void c(boolean z) {
        this.I.w(z ? this.G.o : null);
    }

    @Override // defpackage.AbstractC4899tK
    public void d() {
        ViewGroup viewGroup = (ViewGroup) getParent();
        if (viewGroup != null) {
            viewGroup.requestChildFocus(this, this);
        }
        requestFocus();
        sendAccessibilityEvent(8);
    }

    public final void e(boolean z) {
        int i;
        C5477wm wmVar;
        if (this.M != null) {
            C4286pm pmVar = this.G.k;
            Editable text = this.f7881J.getText();
            C5647xm xmVar = pmVar.f11086a;
            Objects.requireNonNull(xmVar);
            if (text == null || (wmVar = (C5477wm) xmVar.g.get(PersonalDataManager.c().a(text.toString(), false))) == null) {
                i = 0;
            } else {
                i = wmVar.f11567a;
            }
            if (this.N != i || z) {
                this.N = i;
                if (i == 0) {
                    this.M.setVisibility(8);
                    return;
                }
                this.M.setImageDrawable(AbstractC5544x8.a(getContext(), this.N));
                this.M.setVisibility(0);
            }
        }
    }

    public void onClick(View view) {
        this.G.v.run();
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (z) {
            this.K.setTranslationY((((this.f7881J.getY() + this.I.getY()) + ((float) this.f7881J.getHeight())) - ((float) this.K.getHeight())) - ((float) this.K.getTop()));
        }
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            e(true);
        }
    }
}
