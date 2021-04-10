package androidx.appcompat.view.menu;

import X.AbstractC11971tg;
import X.AnonymousClass02C;
import X.C10901qA;
import X.C11081qa;
import X.C11601sP;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.annotation.RestrictTo;
import com.oculus.socialplatform.R;

@RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
public class ListMenuItemView extends LinearLayout implements AbstractC11971tg, AbsListView.SelectionBoundsAdjuster {
    public ImageView A00;
    public LinearLayout A01;
    public TextView A02;
    public C11601sP A03;
    public int A04;
    public Context A05;
    public Drawable A06;
    public Drawable A07;
    public LayoutInflater A08;
    public CheckBox A09;
    public ImageView A0A;
    public ImageView A0B;
    public RadioButton A0C;
    public TextView A0D;
    public boolean A0E;
    public boolean A0F;
    public boolean A0G;

    @Override // X.AbstractC11971tg
    public final boolean A8U() {
        return false;
    }

    public void setIcon(Drawable drawable) {
        boolean z = true;
        if (!this.A0E) {
            z = false;
            if (!this.A0G) {
                return;
            }
        }
        ImageView imageView = this.A00;
        if (imageView != null || drawable != null || this.A0G) {
            if (imageView == null) {
                ImageView imageView2 = (ImageView) getInflater().inflate(R.layout.abc_list_menu_item_icon, (ViewGroup) this, false);
                this.A00 = imageView2;
                LinearLayout linearLayout = this.A01;
                if (linearLayout != null) {
                    linearLayout.addView(imageView2, 0);
                } else {
                    addView(imageView2, 0);
                }
            }
            if (drawable != null || this.A0G) {
                ImageView imageView3 = this.A00;
                if (!z) {
                    drawable = null;
                }
                imageView3.setImageDrawable(drawable);
                if (this.A00.getVisibility() != 0) {
                    this.A00.setVisibility(0);
                    return;
                }
                return;
            }
            this.A00.setVisibility(8);
        }
    }

    private LayoutInflater getInflater() {
        LayoutInflater layoutInflater = this.A08;
        if (layoutInflater != null) {
            return layoutInflater;
        }
        LayoutInflater from = LayoutInflater.from(getContext());
        this.A08 = from;
        return from;
    }

    private void setSubMenuArrowVisible(boolean z) {
        ImageView imageView = this.A0B;
        if (imageView != null) {
            int i = 8;
            if (z) {
                i = 0;
            }
            imageView.setVisibility(i);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0064  */
    @Override // X.AbstractC11971tg
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A5i(X.C11601sP r10, int r11) {
        /*
        // Method dump skipped, instructions count: 355
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.view.menu.ListMenuItemView.A5i(X.1sP, int):void");
    }

    public final void adjustListItemSelectionBounds(Rect rect) {
        ImageView imageView = this.A0A;
        if (imageView != null && imageView.getVisibility() == 0) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.A0A.getLayoutParams();
            rect.top += this.A0A.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
        }
    }

    @Override // X.AbstractC11971tg
    public C11601sP getItemData() {
        return this.A03;
    }

    public final void onMeasure(int i, int i2) {
        if (this.A00 != null && this.A0G) {
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.A00.getLayoutParams();
            int i3 = layoutParams.height;
            if (i3 > 0 && layoutParams2.width <= 0) {
                layoutParams2.width = i3;
            }
        }
        super.onMeasure(i, i2);
    }

    public void setCheckable(boolean z) {
        CheckBox checkBox;
        CompoundButton compoundButton;
        CompoundButton compoundButton2;
        if (z || this.A0C != null || this.A09 != null) {
            if ((this.A03.A02 & 4) != 0) {
                if (this.A0C == null) {
                    A01();
                }
                CompoundButton compoundButton3 = this.A0C;
                CompoundButton compoundButton4 = this.A09;
                checkBox = compoundButton4;
                compoundButton2 = compoundButton4;
                compoundButton = compoundButton3;
            } else {
                if (this.A09 == null) {
                    A00();
                }
                CompoundButton compoundButton5 = this.A09;
                checkBox = compoundButton5;
                compoundButton2 = this.A0C;
                compoundButton = compoundButton5;
            }
            if (z) {
                compoundButton.setChecked(this.A03.isChecked());
                if (compoundButton.getVisibility() != 0) {
                    compoundButton.setVisibility(0);
                }
                if (compoundButton2 != null && compoundButton2.getVisibility() != 8) {
                    compoundButton2.setVisibility(8);
                    return;
                }
                return;
            }
            if (checkBox != null) {
                checkBox.setVisibility(8);
            }
            RadioButton radioButton = this.A0C;
            if (radioButton != null) {
                radioButton.setVisibility(8);
            }
        }
    }

    public void setChecked(boolean z) {
        CompoundButton compoundButton;
        if ((this.A03.A02 & 4) != 0) {
            if (this.A0C == null) {
                A01();
            }
            compoundButton = this.A0C;
        } else {
            if (this.A09 == null) {
                A00();
            }
            compoundButton = this.A09;
        }
        compoundButton.setChecked(z);
    }

    public void setForceShowIcon(boolean z) {
        this.A0E = z;
        this.A0G = z;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0009, code lost:
        if (r3 == false) goto L_0x000b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setGroupDividerEnabled(boolean r3) {
        /*
            r2 = this;
            android.widget.ImageView r1 = r2.A0A
            if (r1 == 0) goto L_0x0010
            boolean r0 = r2.A0F
            if (r0 != 0) goto L_0x000b
            r0 = 0
            if (r3 != 0) goto L_0x000d
        L_0x000b:
            r0 = 8
        L_0x000d:
            r1.setVisibility(r0)
        L_0x0010:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.view.menu.ListMenuItemView.setGroupDividerEnabled(boolean):void");
    }

    public void setTitle(CharSequence charSequence) {
        int i;
        TextView textView;
        if (charSequence != null) {
            this.A0D.setText(charSequence);
            if (this.A0D.getVisibility() != 0) {
                textView = this.A0D;
                i = 0;
            } else {
                return;
            }
        } else {
            i = 8;
            if (this.A0D.getVisibility() != 8) {
                textView = this.A0D;
            } else {
                return;
            }
        }
        textView.setVisibility(i);
    }

    private void A00() {
        CheckBox checkBox = (CheckBox) getInflater().inflate(R.layout.abc_list_menu_item_checkbox, (ViewGroup) this, false);
        this.A09 = checkBox;
        LinearLayout linearLayout = this.A01;
        if (linearLayout != null) {
            linearLayout.addView(checkBox, -1);
        } else {
            addView(checkBox, -1);
        }
    }

    private void A01() {
        RadioButton radioButton = (RadioButton) getInflater().inflate(R.layout.abc_list_menu_item_radio, (ViewGroup) this, false);
        this.A0C = radioButton;
        LinearLayout linearLayout = this.A01;
        if (linearLayout != null) {
            linearLayout.addView(radioButton, -1);
        } else {
            addView(radioButton, -1);
        }
    }

    public final void onFinishInflate() {
        super.onFinishInflate();
        setBackground(this.A06);
        TextView textView = (TextView) findViewById(R.id.title);
        this.A0D = textView;
        int i = this.A04;
        if (i != -1) {
            textView.setTextAppearance(this.A05, i);
        }
        this.A02 = (TextView) findViewById(R.id.shortcut);
        ImageView imageView = (ImageView) findViewById(R.id.submenuarrow);
        this.A0B = imageView;
        if (imageView != null) {
            imageView.setImageDrawable(this.A07);
        }
        this.A0A = (ImageView) findViewById(R.id.group_divider);
        this.A01 = (LinearLayout) findViewById(R.id.content);
    }

    public ListMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.listMenuViewStyle);
    }

    public ListMenuItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        C10901qA A002 = C10901qA.A00(getContext(), attributeSet, C11081qa.A0H, i, 0);
        this.A06 = A002.A02(5);
        TypedArray typedArray = A002.A02;
        this.A04 = typedArray.getResourceId(1, -1);
        this.A0G = typedArray.getBoolean(7, false);
        this.A05 = context;
        this.A07 = A002.A02(8);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(null, new int[]{16843049}, R.attr.dropDownListViewStyle, 0);
        this.A0F = obtainStyledAttributes.hasValue(0);
        A002.A04();
        obtainStyledAttributes.recycle();
    }
}
