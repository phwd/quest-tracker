package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.widget.AppCompatImageView;
import com.oculus.browser.R;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.chromium.components.browser_ui.widget.RoundedCornerImageView;

/* renamed from: Fg  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C0323Fg implements YH0 {

    /* renamed from: a  reason: collision with root package name */
    public final YH0 f8029a;

    public C0323Fg(YH0 yh0) {
        this.f8029a = yh0;
    }

    public static void c(UH0 uh0, DD dd) {
        int i;
        int i2;
        dd.setPaddingRelative(((C5361w31) uh0.g(AbstractC0871Og.f8641a)) == null ? dd.getResources().getDimensionPixelSize(R.dimen.f23390_resource_name_obfuscated_RES_2131165958) : 0, 0, dd.getResources().getDimensionPixelSize(R.dimen.f23360_resource_name_obfuscated_RES_2131165955), 0);
        int f = uh0.f(AbstractC0871Og.d);
        if (f == 1) {
            i = R.dimen.f23380_resource_name_obfuscated_RES_2131165957;
            i2 = R.dimen.f23370_resource_name_obfuscated_RES_2131165956;
        } else if (f != 2) {
            i = R.dimen.f23270_resource_name_obfuscated_RES_2131165946;
            i2 = R.dimen.f23260_resource_name_obfuscated_RES_2131165945;
        } else {
            i = R.dimen.f23290_resource_name_obfuscated_RES_2131165948;
            i2 = R.dimen.f23280_resource_name_obfuscated_RES_2131165947;
        }
        int dimensionPixelSize = dd.getResources().getDimensionPixelSize(i);
        dd.G.setPaddingRelative(0, dimensionPixelSize, 0, dimensionPixelSize);
        dd.G.setMinimumHeight(dd.getResources().getDimensionPixelSize(i2));
    }

    public static void d(ImageView imageView, C5361w31 w31, int i) {
        imageView.getContext().getResources();
        imageView.setVisibility(w31 == null ? 8 : 0);
        ColorStateList colorStateList = null;
        if (w31 == null) {
            imageView.setImageDrawable(null);
            return;
        }
        if (w31.b) {
            Context context = imageView.getContext();
            ThreadLocal threadLocal = AbstractC5544x8.f11592a;
            colorStateList = context.getColorStateList(i);
        }
        imageView.setImageDrawable(w31.f11515a);
        imageView.setImageTintList(colorStateList);
    }

    public static void e(UH0 uh0, C0079Bg bg) {
        RoundedCornerImageView roundedCornerImageView = bg.G.F;
        C5361w31 w31 = (C5361w31) uh0.g(AbstractC0871Og.f8641a);
        if (w31 != null) {
            Resources resources = roundedCornerImageView.getContext().getResources();
            int dimensionPixelSize = resources.getDimensionPixelSize(w31.d ? R.dimen.f23220_resource_name_obfuscated_RES_2131165941 : R.dimen.f23190_resource_name_obfuscated_RES_2131165938);
            int dimensionPixelSize2 = resources.getDimensionPixelSize(w31.d ? R.dimen.f23210_resource_name_obfuscated_RES_2131165940 : R.dimen.f23180_resource_name_obfuscated_RES_2131165937);
            int dimensionPixelSize3 = resources.getDimensionPixelSize(w31.d ? R.dimen.f23230_resource_name_obfuscated_RES_2131165942 : R.dimen.f23200_resource_name_obfuscated_RES_2131165939);
            int i = 0;
            roundedCornerImageView.setPadding(dimensionPixelSize, 0, dimensionPixelSize2, 0);
            roundedCornerImageView.setMinimumHeight(dimensionPixelSize3);
            if (w31.c) {
                i = resources.getDimensionPixelSize(R.dimen.f18130_resource_name_obfuscated_RES_2131165432);
            }
            roundedCornerImageView.d(i, i, i, i);
        }
        d(roundedCornerImageView, w31, (AbstractC4476qs0.a(uh0.f(AbstractC4851t31.f11318a)) ^ true) ^ true ? R.color.f11370_resource_name_obfuscated_RES_2131099827 : R.color.f11380_resource_name_obfuscated_RES_2131099828);
    }

    /* renamed from: b */
    public void a(UH0 uh0, C0079Bg bg, KH0 kh0) {
        this.f8029a.a(uh0, bg.G.G, kh0);
        if (AbstractC0871Og.f8641a == kh0) {
            e(uh0, bg);
            c(uh0, bg.G);
        } else if (AbstractC0871Og.d == kh0) {
            c(uh0, bg.G);
        } else {
            SH0 sh0 = AbstractC4851t31.b;
            if (sh0 == kh0) {
                int f = uh0.f(sh0);
                AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
                bg.setLayoutDirection(f);
                c(uh0, bg.G);
                return;
            }
            SH0 sh02 = AbstractC4851t31.f11318a;
            int i = 0;
            if (sh02 == kh0) {
                e(uh0, bg);
                Drawable b = AbstractC4476qs0.b(bg.getContext(), uh0.f(sh02), R.attr.f7490_resource_name_obfuscated_RES_2130969195);
                bg.G.setBackground(b);
                List list = (List) uh0.g(AbstractC0871Og.b);
                if (list != null) {
                    List list2 = bg.F;
                    while (i < list2.size()) {
                        ImageView imageView = (ImageView) list2.get(i);
                        imageView.setBackground(b.getConstantState().newDrawable());
                        d(imageView, ((C0810Ng) list.get(i)).f8565a, AbstractC2934hr.d(!(!AbstractC4476qs0.a(uh0.f(AbstractC4851t31.f11318a)))));
                        i++;
                    }
                    return;
                }
                return;
            }
            TH0 th0 = AbstractC0871Og.b;
            if (th0 == kh0) {
                List list3 = (List) uh0.g(th0);
                int size = list3 != null ? list3.size() : 0;
                int size2 = bg.F.size();
                if (size2 < size) {
                    for (int size3 = bg.F.size(); size3 < size; size3++) {
                        AppCompatImageView appCompatImageView = new AppCompatImageView(bg.getContext(), null);
                        appCompatImageView.setClickable(true);
                        appCompatImageView.setFocusable(true);
                        appCompatImageView.setScaleType(ImageView.ScaleType.CENTER);
                        appCompatImageView.setLayoutParams(new CW0(bg.getResources().getDimensionPixelSize(R.dimen.f23240_resource_name_obfuscated_RES_2131165943), -1));
                        bg.F.add(appCompatImageView);
                        bg.addView(appCompatImageView);
                    }
                } else if (size2 > size) {
                    for (int i2 = size; i2 < bg.F.size(); i2++) {
                        bg.removeView((View) bg.F.get(i2));
                    }
                    List list4 = bg.F;
                    list4.subList(size, list4.size()).clear();
                }
                Drawable b2 = AbstractC4476qs0.b(bg.getContext(), uh0.f(AbstractC4851t31.f11318a), R.attr.f7490_resource_name_obfuscated_RES_2130969195);
                List list5 = bg.F;
                while (i < size) {
                    ImageView imageView2 = (ImageView) list5.get(i);
                    C0810Ng ng = (C0810Ng) list3.get(i);
                    imageView2.setOnClickListener(new View$OnClickListenerC0262Eg(ng));
                    imageView2.setContentDescription(ng.c);
                    imageView2.setBackground(b2.getConstantState().newDrawable());
                    d(imageView2, ng.f8565a, AbstractC2934hr.d(!(!AbstractC4476qs0.a(uh0.f(AbstractC4851t31.f11318a)))));
                    i++;
                }
                return;
            }
            TH0 th02 = AbstractC0871Og.c;
            if (th02 == kh0) {
                bg.H = (Runnable) uh0.g(th02);
                return;
            }
            TH0 th03 = AbstractC0871Og.e;
            if (th03 == kh0) {
                Runnable runnable = (Runnable) uh0.g(th03);
                if (runnable == null) {
                    bg.G.setOnClickListener(null);
                } else {
                    bg.G.setOnClickListener(new View$OnClickListenerC0140Cg(runnable));
                }
            } else {
                TH0 th04 = AbstractC0871Og.f;
                if (th04 == kh0) {
                    Runnable runnable2 = (Runnable) uh0.g(th04);
                    if (runnable2 == null) {
                        bg.G.setOnLongClickListener(null);
                    } else {
                        bg.G.setOnLongClickListener(new View$OnLongClickListenerC0201Dg(runnable2));
                    }
                }
            }
        }
    }
}
