package defpackage;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import com.oculus.browser.R;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.chromium.chrome.browser.flags.CachedFeatureFlags;
import org.chromium.chrome.browser.ui.appmenu.AppMenuItemIcon;
import org.chromium.components.browser_ui.widget.text.TextViewWithCompoundDrawables;
import org.chromium.ui.base.LocalizationUtils;
import org.chromium.ui.widget.ChromeImageButton;
import org.chromium.ui.widget.ChromeImageView;

/* renamed from: p9  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4185p9 extends BaseAdapter {
    public static final int[] F = {R.id.button_one, R.id.button_two, R.id.button_three, R.id.button_four, R.id.button_five};
    public final AbstractC4867t9 G;
    public final LayoutInflater H;
    public final List I;

    /* renamed from: J  reason: collision with root package name */
    public final int f11051J;
    public final Integer K;
    public final float L;
    public final List M;
    public final int N;
    public final Map O;
    public final boolean P;

    public C4185p9(AbstractC4867t9 t9Var, List list, LayoutInflater layoutInflater, Integer num, List list2, boolean z) {
        int i;
        this.G = t9Var;
        this.I = list;
        this.H = layoutInflater;
        this.K = num;
        this.M = list2;
        this.P = z;
        this.f11051J = list.size();
        this.L = layoutInflater.getContext().getResources().getDisplayMetrics().density;
        if (list2 == null) {
            i = 0;
        } else {
            i = 0;
            for (int i2 = 0; i2 < list2.size(); i2++) {
                i += ((AbstractC5386wC) list2.get(i2)).getViewTypeCount();
            }
        }
        this.N = i;
        HashMap hashMap = new HashMap();
        this.O = hashMap;
        int i3 = 5;
        if (list2 != null) {
            for (int i4 = 0; i4 < list2.size(); i4++) {
                AbstractC5386wC wCVar = (AbstractC5386wC) list2.get(i4);
                hashMap.put(wCVar, Integer.valueOf(i3));
                i3 += wCVar.getViewTypeCount();
            }
        }
    }

    public final Animator a(View view, int i) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ObjectAnimator.ofFloat(view, View.ALPHA, 0.0f, 1.0f), ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, this.L * -10.0f, 0.0f));
        animatorSet.setStartDelay((long) ((i * 30) + 80));
        animatorSet.setDuration(350L);
        animatorSet.setInterpolator(animation.InterpolatorC5286vf.g);
        animatorSet.addListener(new C3330k9(this, view));
        return animatorSet;
    }

    public final View b(View view, ViewGroup viewGroup, MenuItem menuItem, int i, int i2) {
        C3672m9 m9Var;
        View view2 = view;
        if (view2 == null || ((Integer) view2.getTag(R.id.menu_item_view_type)).intValue() != i2) {
            m9Var = new C3672m9(i);
            view2 = this.H.inflate(R.layout.f38680_resource_name_obfuscated_RES_2131624177, viewGroup, false);
            for (int i3 = 0; i3 < i; i3++) {
                m9Var.f10400a[i3] = (ImageButton) view2.findViewById(F[i3]);
            }
            for (int i4 = i; i4 < 5; i4++) {
                ((ViewGroup) view2).removeView(view2.findViewById(F[i4]));
            }
            view2.setTag(m9Var);
            ImageButton[] imageButtonArr = m9Var.f10400a;
            float f = this.L * 10.0f * (LocalizationUtils.isLayoutRtl() ? -1.0f : 1.0f);
            int length = imageButtonArr.length;
            AnimatorSet animatorSet = new AnimatorSet();
            AnimatorSet.Builder builder = null;
            for (int i5 = 0; i5 < length; i5++) {
                ImageButton imageButton = imageButtonArr[i5];
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(imageButton, View.ALPHA, 0.0f, 1.0f);
                ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(imageButton, View.TRANSLATION_X, f, 0.0f);
                long j = (long) (i5 * 30);
                ofFloat.setStartDelay(j);
                ofFloat2.setStartDelay(j);
                ofFloat.setDuration(350L);
                ofFloat2.setDuration(350L);
                if (builder == null) {
                    builder = animatorSet.play(ofFloat);
                } else {
                    builder.with(ofFloat);
                }
                builder.with(ofFloat2);
            }
            animatorSet.setStartDelay(80);
            animatorSet.setInterpolator(animation.InterpolatorC5286vf.g);
            animatorSet.addListener(new C3501l9(this, length, imageButtonArr));
            view2.setTag(R.id.menu_item_enter_anim_id, animatorSet);
        } else {
            m9Var = (C3672m9) view.getTag();
        }
        for (int i6 = 0; i6 < i; i6++) {
            e(m9Var.f10400a[i6], menuItem.getSubMenu().getItem(i6));
        }
        if (CachedFeatureFlags.isEnabled("TabbedAppOverflowMenuIcons") || CachedFeatureFlags.isEnabled("TabbedAppOverflowMenuThreeButtonActionbar")) {
            view2.setBackgroundDrawable(AbstractC3153j7.c(view2.getContext().getResources(), R.drawable.f33670_resource_name_obfuscated_RES_2131231407));
        }
        view2.setFocusable(false);
        view2.setEnabled(false);
        return view2;
    }

    public final int c(MenuItem menuItem) {
        if (this.M == null) {
            return -1;
        }
        for (int i = 0; i < this.M.size(); i++) {
            AbstractC5386wC wCVar = (AbstractC5386wC) this.M.get(i);
            int itemViewType = wCVar.getItemViewType(menuItem.getItemId());
            if (itemViewType != -1) {
                return ((Integer) this.O.get(wCVar)).intValue() + itemViewType;
            }
        }
        return -1;
    }

    /* renamed from: d */
    public MenuItem getItem(int i) {
        if (i == -1) {
            return null;
        }
        return (MenuItem) this.I.get(i);
    }

    public final void e(ImageButton imageButton, MenuItem menuItem) {
        int level = menuItem.getIcon().getLevel();
        imageButton.setImageDrawable(menuItem.getIcon());
        menuItem.getIcon().setLevel(level);
        if (menuItem.isChecked()) {
            Context context = imageButton.getContext();
            ThreadLocal threadLocal = AbstractC5544x8.f11592a;
            imageButton.setImageTintList(context.getColorStateList(R.color.f10010_resource_name_obfuscated_RES_2131099691));
        }
        f(imageButton, menuItem);
    }

    public final void f(View view, MenuItem menuItem) {
        view.setEnabled(menuItem.isEnabled());
        view.setFocusable(menuItem.isEnabled());
        int i = 0;
        if (TextUtils.isEmpty(menuItem.getTitleCondensed())) {
            view.setImportantForAccessibility(2);
        } else {
            view.setContentDescription(menuItem.getTitleCondensed());
            view.setImportantForAccessibility(0);
        }
        view.setOnClickListener(new View$OnClickListenerC2818h9(this, menuItem));
        view.setOnLongClickListener(new View$OnLongClickListenerC2989i9(this, menuItem));
        if (this.K == null || menuItem.getItemId() != this.K.intValue()) {
            AbstractC3628lu1.b(view);
        } else {
            AbstractC3628lu1.c(view);
        }
        if (!menuItem.isVisible()) {
            i = 8;
        }
        view.setVisibility(i);
    }

    public int getCount() {
        return this.f11051J;
    }

    public long getItemId(int i) {
        return (long) getItem(i).getItemId();
    }

    public int getItemViewType(int i) {
        MenuItem d = getItem(i);
        int size = d.hasSubMenu() ? d.getSubMenu().size() : 1;
        int c = c(d);
        if (c != -1) {
            return c;
        }
        if (size == 2) {
            return 1;
        }
        if (size == 3) {
            return 2;
        }
        if (size == 4) {
            return 3;
        }
        return size == 5 ? 4 : 0;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        int i2;
        int i3;
        C3843n9 n9Var;
        View view2;
        C4014o9 o9Var;
        View view3 = view;
        MenuItem d = getItem(i);
        int itemViewType = getItemViewType(i);
        int i4 = 8;
        int i5 = 0;
        if (itemViewType == 0) {
            i3 = R.id.menu_item_view_type;
            i2 = -1;
            if (view3 == null || ((Integer) view3.getTag(i3)).intValue() != 0) {
                C3843n9 n9Var2 = new C3843n9(null);
                if (this.P) {
                    view2 = this.H.inflate(R.layout.f39270_resource_name_obfuscated_RES_2131624236, viewGroup, false);
                } else {
                    view2 = this.H.inflate(R.layout.f39260_resource_name_obfuscated_RES_2131624235, viewGroup, false);
                }
                n9Var2.f10475a = (TextView) view2.findViewById(R.id.menu_item_text);
                n9Var2.b = (ChromeImageView) view2.findViewById(R.id.menu_item_icon);
                view2.setTag(n9Var2);
                view2.setTag(R.id.menu_item_enter_anim_id, a(view2, i));
                n9Var = n9Var2;
                view3 = view2;
            } else {
                n9Var = (C3843n9) view.getTag();
            }
            Drawable icon = d.getIcon();
            n9Var.b.setImageDrawable(icon);
            ChromeImageView chromeImageView = n9Var.b;
            if (icon != null) {
                i4 = 0;
            }
            chromeImageView.setVisibility(i4);
            n9Var.f10475a.setText(d.getTitle());
            n9Var.f10475a.setContentDescription(d.getTitleCondensed());
            boolean isEnabled = d.isEnabled();
            n9Var.f10475a.setEnabled(isEnabled);
            view3.setEnabled(isEnabled);
            view3.setOnClickListener(new View$OnClickListenerC3159j9(this, d));
        } else if (itemViewType == 1) {
            i3 = R.id.menu_item_view_type;
            i2 = -1;
            MenuItem item = d.getSubMenu().getItem(0);
            MenuItem item2 = d.getSubMenu().getItem(1);
            if (view3 == null || ((Integer) view3.getTag(i3)).intValue() != 1) {
                view3 = this.H.inflate(R.layout.f42040_resource_name_obfuscated_RES_2131624513, viewGroup, false);
                C4014o9 o9Var2 = new C4014o9(null);
                o9Var2.f10534a = (TextViewWithCompoundDrawables) view3.findViewById(R.id.title);
                o9Var2.b = (AppMenuItemIcon) view3.findViewById(R.id.checkbox);
                o9Var2.c = (ChromeImageButton) view3.findViewById(R.id.button);
                view3.setTag(o9Var2);
                view3.setTag(R.id.menu_item_enter_anim_id, a(view3, i));
                o9Var = o9Var2;
            } else {
                o9Var = (C4014o9) view.getTag();
            }
            if (this.P) {
                o9Var.f10534a.setCompoundDrawablesRelative(item.getIcon(), null, null, null);
            }
            o9Var.f10534a.setText(item.getTitle());
            o9Var.f10534a.setEnabled(item.isEnabled());
            o9Var.f10534a.setFocusable(item.isEnabled());
            o9Var.f10534a.setOnClickListener(new View$OnClickListenerC2647g9(this, item));
            if (TextUtils.isEmpty(item.getTitleCondensed())) {
                o9Var.f10534a.setContentDescription(null);
            } else {
                o9Var.f10534a.setContentDescription(item.getTitleCondensed());
            }
            if (item2.isCheckable()) {
                o9Var.b.setVisibility(0);
                o9Var.c.setVisibility(8);
                AppMenuItemIcon appMenuItemIcon = o9Var.b;
                appMenuItemIcon.setChecked(item2.isChecked());
                Context context = appMenuItemIcon.getContext();
                ThreadLocal threadLocal = AbstractC5544x8.f11592a;
                appMenuItemIcon.setImageTintList(context.getColorStateList(R.color.f10240_resource_name_obfuscated_RES_2131099714));
                f(appMenuItemIcon, item2);
            } else if (item2.getIcon() != null) {
                o9Var.b.setVisibility(8);
                o9Var.c.setVisibility(0);
                e(o9Var.c, item2);
            } else {
                o9Var.b.setVisibility(8);
                o9Var.c.setVisibility(8);
            }
            view3.setFocusable(false);
            view3.setEnabled(false);
        } else if (itemViewType == 2) {
            i3 = R.id.menu_item_view_type;
            i2 = -1;
            view3 = b(view, viewGroup, d, 3, itemViewType);
        } else if (itemViewType == 3) {
            i3 = R.id.menu_item_view_type;
            i2 = -1;
            view3 = b(view, viewGroup, d, 4, itemViewType);
        } else if (itemViewType != 4) {
            while (true) {
                if (i5 >= this.M.size()) {
                    break;
                }
                AbstractC5386wC wCVar = (AbstractC5386wC) this.M.get(i5);
                if (wCVar.getItemViewType(d.getItemId()) == -1) {
                    i5++;
                } else {
                    view3 = wCVar.a(d, (view3 == null || ((Integer) view3.getTag(R.id.menu_item_view_type)).intValue() == itemViewType) ? view3 : null, viewGroup, this.H, this.G, this.K);
                    if (wCVar.b(d.getItemId())) {
                        view3.setTag(R.id.menu_item_enter_anim_id, a(view3, i));
                    }
                    view3.setEnabled(d.isEnabled());
                }
            }
            i3 = R.id.menu_item_view_type;
            i2 = -1;
        } else {
            i3 = R.id.menu_item_view_type;
            i2 = -1;
            view3 = b(view, viewGroup, d, 5, itemViewType);
        }
        if (c(d) == i2) {
            if (this.K == null || d.getItemId() != this.K.intValue()) {
                AbstractC3628lu1.b(view3);
            } else {
                AbstractC3628lu1.d(view3);
            }
        }
        view3.setTag(i3, Integer.valueOf(itemViewType));
        return view3;
    }

    public int getViewTypeCount() {
        return this.N + 5;
    }
}
