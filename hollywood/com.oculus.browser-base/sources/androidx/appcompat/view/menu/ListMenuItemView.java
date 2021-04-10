package androidx.appcompat.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import com.oculus.browser.R;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ListMenuItemView extends LinearLayout implements AbstractC2228dj0, AbsListView.SelectionBoundsAdjuster {
    public C0817Ni0 F;
    public ImageView G;
    public RadioButton H;
    public TextView I;

    /* renamed from: J  reason: collision with root package name */
    public CheckBox f9456J;
    public TextView K;
    public ImageView L;
    public ImageView M;
    public LinearLayout N;
    public Drawable O;
    public int P;
    public Context Q;
    public boolean R;
    public Drawable S;
    public boolean T;
    public LayoutInflater U;
    public boolean V;

    public ListMenuItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        C0514Ii1 q = C0514Ii1.q(getContext(), attributeSet, FJ0.m0, R.attr.f5940_resource_name_obfuscated_RES_2130969040, 0);
        this.O = q.g(5);
        this.P = q.l(1, -1);
        this.R = q.a(7, false);
        this.Q = context;
        this.S = q.g(8);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(null, new int[]{16843049}, R.attr.f4030_resource_name_obfuscated_RES_2130968849, 0);
        this.T = obtainStyledAttributes.hasValue(0);
        q.b.recycle();
        obtainStyledAttributes.recycle();
    }

    public final LayoutInflater a() {
        if (this.U == null) {
            this.U = LayoutInflater.from(getContext());
        }
        return this.U;
    }

    public void adjustListItemSelectionBounds(Rect rect) {
        ImageView imageView = this.M;
        if (imageView != null && imageView.getVisibility() == 0) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.M.getLayoutParams();
            rect.top = this.M.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin + rect.top;
        }
    }

    public void b(boolean z) {
        String str;
        int i = (!z || !this.F.l()) ? 8 : 0;
        if (i == 0) {
            TextView textView = this.K;
            C0817Ni0 ni0 = this.F;
            char e = ni0.e();
            if (e == 0) {
                str = "";
            } else {
                Resources resources = ni0.n.b.getResources();
                StringBuilder sb = new StringBuilder();
                if (ViewConfiguration.get(ni0.n.b).hasPermanentMenuKey()) {
                    sb.append(resources.getString(R.string.f45140_resource_name_obfuscated_RES_2131951831));
                }
                int i2 = ni0.n.n() ? ni0.k : ni0.i;
                C0817Ni0.c(sb, i2, 65536, resources.getString(R.string.f45100_resource_name_obfuscated_RES_2131951827));
                C0817Ni0.c(sb, i2, 4096, resources.getString(R.string.f45060_resource_name_obfuscated_RES_2131951823));
                C0817Ni0.c(sb, i2, 2, resources.getString(R.string.f45050_resource_name_obfuscated_RES_2131951822));
                C0817Ni0.c(sb, i2, 1, resources.getString(R.string.f45110_resource_name_obfuscated_RES_2131951828));
                C0817Ni0.c(sb, i2, 4, resources.getString(R.string.f45130_resource_name_obfuscated_RES_2131951830));
                C0817Ni0.c(sb, i2, 8, resources.getString(R.string.f45090_resource_name_obfuscated_RES_2131951826));
                if (e == '\b') {
                    sb.append(resources.getString(R.string.f45070_resource_name_obfuscated_RES_2131951824));
                } else if (e == '\n') {
                    sb.append(resources.getString(R.string.f45080_resource_name_obfuscated_RES_2131951825));
                } else if (e != ' ') {
                    sb.append(e);
                } else {
                    sb.append(resources.getString(R.string.f45120_resource_name_obfuscated_RES_2131951829));
                }
                str = sb.toString();
            }
            textView.setText(str);
        }
        if (this.K.getVisibility() != i) {
            this.K.setVisibility(i);
        }
    }

    @Override // defpackage.AbstractC2228dj0
    public C0817Ni0 d() {
        return this.F;
    }

    @Override // defpackage.AbstractC2228dj0
    public void e(C0817Ni0 ni0, int i) {
        ImageView imageView;
        CompoundButton compoundButton;
        CompoundButton compoundButton2;
        this.F = ni0;
        int i2 = 0;
        setVisibility(ni0.isVisible() ? 0 : 8);
        CharSequence charSequence = ni0.e;
        if (charSequence != null) {
            this.I.setText(charSequence);
            if (this.I.getVisibility() != 0) {
                this.I.setVisibility(0);
            }
        } else if (this.I.getVisibility() != 8) {
            this.I.setVisibility(8);
        }
        boolean isCheckable = ni0.isCheckable();
        if (!(!isCheckable && this.H == null && this.f9456J == null)) {
            if ((this.F.x & 4) != 0) {
                if (this.H == null) {
                    RadioButton radioButton = (RadioButton) a().inflate(R.layout.f36440_resource_name_obfuscated_RES_2131623953, (ViewGroup) this, false);
                    this.H = radioButton;
                    LinearLayout linearLayout = this.N;
                    if (linearLayout != null) {
                        linearLayout.addView(radioButton, -1);
                    } else {
                        addView(radioButton, -1);
                    }
                }
                compoundButton2 = this.H;
                compoundButton = this.f9456J;
            } else {
                if (this.f9456J == null) {
                    CheckBox checkBox = (CheckBox) a().inflate(R.layout.f36410_resource_name_obfuscated_RES_2131623950, (ViewGroup) this, false);
                    this.f9456J = checkBox;
                    LinearLayout linearLayout2 = this.N;
                    if (linearLayout2 != null) {
                        linearLayout2.addView(checkBox, -1);
                    } else {
                        addView(checkBox, -1);
                    }
                }
                compoundButton2 = this.f9456J;
                compoundButton = this.H;
            }
            if (isCheckable) {
                compoundButton2.setChecked(this.F.isChecked());
                if (compoundButton2.getVisibility() != 0) {
                    compoundButton2.setVisibility(0);
                }
                if (!(compoundButton == null || compoundButton.getVisibility() == 8)) {
                    compoundButton.setVisibility(8);
                }
            } else {
                CheckBox checkBox2 = this.f9456J;
                if (checkBox2 != null) {
                    checkBox2.setVisibility(8);
                }
                RadioButton radioButton2 = this.H;
                if (radioButton2 != null) {
                    radioButton2.setVisibility(8);
                }
            }
        }
        boolean l = ni0.l();
        ni0.e();
        b(l);
        Drawable icon = ni0.getIcon();
        Objects.requireNonNull(this.F.n);
        boolean z = this.V;
        if ((z || this.R) && !((imageView = this.G) == null && icon == null && !this.R)) {
            if (imageView == null) {
                ImageView imageView2 = (ImageView) a().inflate(R.layout.f36420_resource_name_obfuscated_RES_2131623951, (ViewGroup) this, false);
                this.G = imageView2;
                LinearLayout linearLayout3 = this.N;
                if (linearLayout3 != null) {
                    linearLayout3.addView(imageView2, 0);
                } else {
                    addView(imageView2, 0);
                }
            }
            if (icon != null || this.R) {
                ImageView imageView3 = this.G;
                if (!z) {
                    icon = null;
                }
                imageView3.setImageDrawable(icon);
                if (this.G.getVisibility() != 0) {
                    this.G.setVisibility(0);
                }
            } else {
                this.G.setVisibility(8);
            }
        }
        setEnabled(ni0.isEnabled());
        boolean hasSubMenu = ni0.hasSubMenu();
        ImageView imageView4 = this.L;
        if (imageView4 != null) {
            if (!hasSubMenu) {
                i2 = 8;
            }
            imageView4.setVisibility(i2);
        }
        setContentDescription(ni0.q);
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        Drawable drawable = this.O;
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        setBackground(drawable);
        TextView textView = (TextView) findViewById(R.id.title);
        this.I = textView;
        int i = this.P;
        if (i != -1) {
            textView.setTextAppearance(this.Q, i);
        }
        this.K = (TextView) findViewById(R.id.shortcut);
        ImageView imageView = (ImageView) findViewById(R.id.submenuarrow);
        this.L = imageView;
        if (imageView != null) {
            imageView.setImageDrawable(this.S);
        }
        this.M = (ImageView) findViewById(R.id.group_divider);
        this.N = (LinearLayout) findViewById(R.id.content);
    }

    public void onMeasure(int i, int i2) {
        if (this.G != null && this.R) {
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.G.getLayoutParams();
            int i3 = layoutParams.height;
            if (i3 > 0 && layoutParams2.width <= 0) {
                layoutParams2.width = i3;
            }
        }
        super.onMeasure(i, i2);
    }
}
