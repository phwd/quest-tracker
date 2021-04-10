package defpackage;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.oculus.browser.R;
import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.chromium.base.ThreadUtils;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tasks.tab_management.ClosableTabGridView;
import org.chromium.chrome.browser.tasks.tab_management.PriceCardView;
import org.chromium.chrome.browser.tasks.tab_management.PriceWelcomeMessageCardView;
import org.chromium.chrome.browser.tasks.tab_management.SelectableTabGridView;
import org.chromium.ui.widget.ButtonCompat;
import org.chromium.ui.widget.ChipView;
import org.chromium.ui.widget.ChromeImageView;
import org.chromium.ui.widget.ViewLookupCachingFrameLayout;

/* renamed from: E71  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class E71 {
    public static void a(UH0 uh0, ViewLookupCachingFrameLayout viewLookupCachingFrameLayout, KH0 kh0) {
        TH0 th0;
        TH0 th02 = AbstractC5106ub1.c;
        if (th02 != kh0) {
            TH0 th03 = AbstractC5106ub1.b;
            if (th03 != kh0) {
                TH0 th04 = AbstractC5106ub1.j;
                boolean z = false;
                if (th04 == kh0) {
                    D91 d91 = (D91) uh0.g(th04);
                    ButtonCompat buttonCompat = (ButtonCompat) viewLookupCachingFrameLayout.d(R.id.create_group_button);
                    if (d91 == null) {
                        buttonCompat.setVisibility(8);
                        buttonCompat.setOnClickListener(null);
                        return;
                    }
                    buttonCompat.setVisibility(0);
                    buttonCompat.setOnClickListener(new View$OnClickListenerC5713y71(uh0, d91));
                    return;
                }
                RH0 rh0 = J91.b;
                if (rh0 == kh0) {
                    viewLookupCachingFrameLayout.setAlpha(uh0.e(rh0));
                    return;
                }
                TH0 th05 = AbstractC5106ub1.g;
                if (th05 != kh0) {
                    TH0 th06 = AbstractC5106ub1.f;
                    if (th06 == kh0) {
                        C3674m91 m91 = (C3674m91) uh0.g(th06);
                        if (m91 != null) {
                            View d = viewLookupCachingFrameLayout.d(R.id.tab_thumbnail);
                            I91 i91 = m91.f10402a;
                            if (!i91.d) {
                                i91.d = true;
                                new Handler().postDelayed(new RunnableC2991i91(d), 1000);
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    SH0 sh0 = AbstractC5106ub1.k;
                    if (sh0 == kh0) {
                        boolean h = uh0.h(AbstractC5106ub1.h);
                        ClosableTabGridView closableTabGridView = (ClosableTabGridView) viewLookupCachingFrameLayout;
                        int f = uh0.f(sh0);
                        View d2 = closableTabGridView.d(R.id.background_view);
                        View d3 = closableTabGridView.d(R.id.content_view);
                        View d4 = closableTabGridView.d(R.id.selected_view_below_lollipop);
                        boolean z2 = f == 2 || f == 4;
                        boolean z3 = f == 4 || f == 3;
                        long j = f == 0 ? 50 : 218;
                        float f2 = z2 ? 0.8f : 1.0f;
                        if (!z3) {
                            d3 = closableTabGridView;
                        }
                        if (f == 4) {
                            d2.setVisibility(0);
                        }
                        AnimatorSet animatorSet = new AnimatorSet();
                        animatorSet.addListener(new C5164uv(closableTabGridView, z2, d2, d4, h));
                        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(d3, View.SCALE_X, f2);
                        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(d3, View.SCALE_Y, f2);
                        ofFloat.setDuration(j);
                        ofFloat2.setDuration(j);
                        animatorSet.play(ofFloat).with(ofFloat2);
                        animatorSet.start();
                        return;
                    }
                    MH0 mh0 = AbstractC5106ub1.n;
                    if (mh0 == kh0) {
                        f(viewLookupCachingFrameLayout, uh0.h(mh0), 1);
                        return;
                    }
                    TH0 th07 = AbstractC5106ub1.t;
                    if (th07 == kh0) {
                        viewLookupCachingFrameLayout.setAccessibilityDelegate((View.AccessibilityDelegate) uh0.g(th07));
                        return;
                    }
                    TH0 th08 = AbstractC5106ub1.u;
                    if (th08 == kh0) {
                        String str = (String) uh0.g(th08);
                        ChipView chipView = (ChipView) viewLookupCachingFrameLayout.d(R.id.page_info_button);
                        if (TextUtils.isEmpty(str)) {
                            chipView.setVisibility(8);
                            return;
                        }
                        chipView.setVisibility(0);
                        chipView.F.setText(str);
                        return;
                    }
                    TH0 th09 = AbstractC5106ub1.v;
                    if (th09 == kh0) {
                        PriceCardView priceCardView = (PriceCardView) viewLookupCachingFrameLayout.d(R.id.price_info_box_outer);
                        if (uh0.g(th09) != null) {
                            C91 c91 = (C91) uh0.g(th09);
                            C5883z71 z71 = new C5883z71(priceCardView);
                            Tab tab = c91.f7791a;
                            A91 a91 = new A91(c91, z71);
                            P20 p20 = C2361eV0.Q;
                            VU0 vu0 = new VU0(tab);
                            WU0 wu0 = new WU0(tab);
                            Object obj = ThreadUtils.f10596a;
                            AbstractC2145dC0 dc0 = (AbstractC2145dC0) C2361eV0.class.cast(tab.M().c(C2361eV0.class));
                            if (dc0 == null) {
                                String format = String.format(Locale.ENGLISH, "%d-%s", Integer.valueOf(tab.getId()), C2361eV0.class.toString());
                                Map map = AbstractC2145dC0.F;
                                if (!map.containsKey(format)) {
                                    map.put(format, new LinkedList());
                                }
                                ((List) map.get(format)).add(a91);
                                if (((List) map.get(format)).size() <= 1) {
                                    EnumC3169jC0 a2 = EnumC3169jC0.a(C2361eV0.class, tab.a());
                                    a2.b().a(tab.getId(), a2.R, new YB0(wu0, tab, C2361eV0.class, format, vu0, a2));
                                }
                            } else if (dc0.l()) {
                                wu0.onResult(new WB0(tab, C2361eV0.class, a91));
                            } else {
                                PostTask.c(Zo1.f9374a, new XB0(a91, dc0));
                            }
                        } else {
                            priceCardView.setVisibility(8);
                        }
                    } else {
                        QH0 qh0 = AbstractC5106ub1.A;
                        if (qh0 != kh0) {
                            TH0 th010 = AbstractC5106ub1.w;
                            if (th010 == kh0) {
                                D91 d912 = (D91) uh0.g(th010);
                                ChipView chipView2 = (ChipView) viewLookupCachingFrameLayout.d(R.id.page_info_button);
                                if (d912 == null) {
                                    chipView2.setOnClickListener(null);
                                } else {
                                    chipView2.setOnClickListener(new A71(uh0, d912));
                                }
                            } else {
                                SH0 sh02 = AbstractC5106ub1.x;
                                if (sh02 == kh0) {
                                    ChipView chipView3 = (ChipView) viewLookupCachingFrameLayout.d(R.id.page_info_button);
                                    int f3 = uh0.f(sh02);
                                    if (f3 != R.drawable.f30810_resource_name_obfuscated_RES_2131231121) {
                                        z = true;
                                    }
                                    chipView3.c(f3, z);
                                    return;
                                }
                                QH0 qh02 = AbstractC5106ub1.h;
                                if (qh02 == kh0) {
                                    viewLookupCachingFrameLayout.setSelected(uh0.h(qh02));
                                } else if (AbstractC4772sd1.d() && (th0 = AbstractC5106ub1.z) == kh0) {
                                    viewLookupCachingFrameLayout.d(R.id.action_button).setContentDescription((CharSequence) uh0.g(th0));
                                }
                            }
                        } else if (uh0.h(qh0)) {
                            View findViewById = ((PriceCardView) viewLookupCachingFrameLayout.d(R.id.price_info_box_outer)).findViewById(R.id.current_price);
                            WeakReference weakReference = PriceWelcomeMessageCardView.F;
                            C1175Tf1 tf1 = new C1175Tf1(findViewById.getContext(), findViewById, (int) R.string.f59410_resource_name_obfuscated_RES_2131953258, (int) R.string.f59410_resource_name_obfuscated_RES_2131953258, true, (C4390qK0) new ViewTreeObserver$OnGlobalLayoutListenerC2606fv1(findViewById), C0283Ep.h().d());
                            tf1.e(true);
                            tf1.f();
                        }
                    }
                } else if (!AbstractC4772sd1.d()) {
                    viewLookupCachingFrameLayout.d(R.id.action_button).setContentDescription(viewLookupCachingFrameLayout.getResources().getString(R.string.f46030_resource_name_obfuscated_RES_2131951920, (String) uh0.g(th05)));
                }
            } else if (uh0.g(th03) == null) {
                viewLookupCachingFrameLayout.setOnClickListener(null);
            } else {
                viewLookupCachingFrameLayout.setOnClickListener(new View$OnClickListenerC5543x71(uh0));
            }
        } else if (uh0.g(th02) == null) {
            viewLookupCachingFrameLayout.d(R.id.action_button).setOnClickListener(null);
        } else {
            viewLookupCachingFrameLayout.d(R.id.action_button).setOnClickListener(new View$OnClickListenerC5373w71(uh0));
        }
    }

    public static void b(UH0 uh0, ViewLookupCachingFrameLayout viewLookupCachingFrameLayout, KH0 kh0) {
        TH0 th0 = AbstractC5106ub1.g;
        int i = 0;
        if (th0 == kh0) {
            String str = (String) uh0.g(th0);
            TextView textView = (TextView) viewLookupCachingFrameLayout.d(R.id.tab_title);
            textView.setText(str);
            textView.setContentDescription(viewLookupCachingFrameLayout.getResources().getString(R.string.f46110_resource_name_obfuscated_RES_2131951928, str));
            return;
        }
        QH0 qh0 = AbstractC5106ub1.h;
        if (qh0 == kh0) {
            int f = uh0.f(AbstractC5106ub1.o);
            Resources resources = viewLookupCachingFrameLayout.getResources();
            InsetDrawable insetDrawable = new InsetDrawable(resources.getDrawable(f, viewLookupCachingFrameLayout.getContext().getTheme()), (int) resources.getDimension(R.dimen.f25650_resource_name_obfuscated_RES_2131166184));
            if (!uh0.h(qh0)) {
                insetDrawable = null;
            }
            viewLookupCachingFrameLayout.setForeground(insetDrawable);
            if (AbstractC4772sd1.d.c()) {
                ChipView chipView = (ChipView) viewLookupCachingFrameLayout.d(R.id.page_info_button);
                chipView.F.setTextAlignment(5);
                chipView.F.setEllipsize(TextUtils.TruncateAt.END);
                chipView.setSelected(false);
                return;
            }
            return;
        }
        TH0 th02 = AbstractC5106ub1.d;
        if (th02 == kh0) {
            Drawable drawable = (Drawable) uh0.g(th02);
            ImageView imageView = (ImageView) viewLookupCachingFrameLayout.d(R.id.tab_favicon);
            imageView.setImageDrawable(drawable);
            if (drawable != null) {
                i = (int) viewLookupCachingFrameLayout.getResources().getDimension(R.dimen.f25610_resource_name_obfuscated_RES_2131166180);
            }
            imageView.setPadding(i, i, i, i);
            return;
        }
        TH0 th03 = AbstractC5106ub1.e;
        if (th03 == kh0) {
            G91 g91 = (G91) uh0.g(th03);
            ImageView imageView2 = (ImageView) viewLookupCachingFrameLayout.d(R.id.tab_thumbnail);
            if (g91 == null) {
                e(imageView2);
                return;
            }
            D71 d71 = new D71(imageView2);
            AbstractC4772sd1.d();
            g91.a(d71);
            return;
        }
        TH0 th04 = AbstractC5106ub1.y;
        if (th04 == kh0) {
            viewLookupCachingFrameLayout.setContentDescription((CharSequence) uh0.g(th04));
        }
    }

    public static void c(UH0 uh0, ViewLookupCachingFrameLayout viewLookupCachingFrameLayout, KH0 kh0) {
        ColorStateList colorStateList;
        int integer = viewLookupCachingFrameLayout.getResources().getInteger(R.integer.f35900_resource_name_obfuscated_RES_2131492887);
        int integer2 = viewLookupCachingFrameLayout.getResources().getInteger(R.integer.f35920_resource_name_obfuscated_RES_2131492889);
        int f = uh0.f(AbstractC5106ub1.f11420a);
        QH0 qh0 = AbstractC5106ub1.h;
        int i = 0;
        if (qh0 == kh0) {
            boolean h = uh0.h(qh0);
            ImageView imageView = (ImageView) viewLookupCachingFrameLayout.d(R.id.action_button);
            Drawable background = imageView.getBackground();
            if (h) {
                integer = integer2;
            }
            background.setLevel(integer);
            Drawable mutate = imageView.getBackground().mutate();
            if (h) {
                colorStateList = (ColorStateList) uh0.g(AbstractC5106ub1.r);
            } else {
                colorStateList = (ColorStateList) uh0.g(AbstractC5106ub1.q);
            }
            mutate.setTintList(colorStateList);
            Drawable drawable = imageView.getDrawable();
            if (h) {
                i = 255;
            }
            drawable.setAlpha(i);
            imageView.setImageTintList(h ? (ColorStateList) uh0.g(AbstractC5106ub1.i) : null);
            if (h) {
                ((L6) imageView.getDrawable()).start();
            }
        } else if (AbstractC5106ub1.l == kh0) {
            viewLookupCachingFrameLayout.setOnClickListener(new B71(uh0, f, viewLookupCachingFrameLayout));
            viewLookupCachingFrameLayout.setOnLongClickListener(new C71(uh0, f, viewLookupCachingFrameLayout));
        } else {
            TH0 th0 = AbstractC5106ub1.m;
            if (th0 == kh0) {
                SelectableTabGridView selectableTabGridView = (SelectableTabGridView) viewLookupCachingFrameLayout;
                selectableTabGridView.h((C3209jS0) uh0.g(th0));
                selectableTabGridView.g(Integer.valueOf(f));
                return;
            }
            MH0 mh0 = AbstractC5106ub1.n;
            if (mh0 == kh0) {
                f(viewLookupCachingFrameLayout, uh0.h(mh0), 0);
            }
        }
    }

    public static void d(ViewLookupCachingFrameLayout viewLookupCachingFrameLayout, UH0 uh0, int i) {
        KH0[] kh0Arr = AbstractC5106ub1.B;
        for (KH0 kh0 : kh0Arr) {
            b(uh0, viewLookupCachingFrameLayout, kh0);
            if (i == 0) {
                c(uh0, viewLookupCachingFrameLayout, kh0);
            } else if (i == 1) {
                a(uh0, viewLookupCachingFrameLayout, kh0);
            }
        }
    }

    public static void e(ImageView imageView) {
        if (AbstractC4772sd1.d()) {
            imageView.setImageDrawable(null);
        } else if (AbstractC4772sd1.i()) {
            imageView.setMinimumHeight(Math.min(imageView.getHeight(), (int) ((((double) imageView.getWidth()) * 1.0d) / ((double) AbstractC4089od0.b((float) AbstractC4772sd1.c.c(), 0.5f, 2.0f)))));
            imageView.setImageDrawable(null);
        } else {
            imageView.setImageDrawable(null);
            imageView.setMinimumHeight(imageView.getWidth());
        }
    }

    public static void f(ViewLookupCachingFrameLayout viewLookupCachingFrameLayout, boolean z, int i) {
        View d = viewLookupCachingFrameLayout.d(R.id.card_view);
        View d2 = viewLookupCachingFrameLayout.d(R.id.divider_view);
        ImageView imageView = (ImageView) viewLookupCachingFrameLayout.d(R.id.tab_thumbnail);
        ImageView imageView2 = (ImageView) viewLookupCachingFrameLayout.d(R.id.action_button);
        ChromeImageView chromeImageView = (ChromeImageView) viewLookupCachingFrameLayout.d(R.id.background_view);
        Context context = d.getContext();
        int i2 = z ? R.color.f15220_resource_name_obfuscated_RES_2131100212 : R.color.f15210_resource_name_obfuscated_RES_2131100211;
        ThreadLocal threadLocal = AbstractC5544x8.f11592a;
        ColorStateList colorStateList = context.getColorStateList(i2);
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        d.setBackgroundTintList(colorStateList);
        d2.setBackgroundColor(d2.getContext().getResources().getColor(z ? R.color.f15140_resource_name_obfuscated_RES_2131100204 : R.color.f15130_resource_name_obfuscated_RES_2131100203));
        AbstractC3153j7.i((TextView) viewLookupCachingFrameLayout.d(R.id.tab_title), z ? R.style.f72120_resource_name_obfuscated_RES_2132017785 : R.style.f72100_resource_name_obfuscated_RES_2132017783);
        if (imageView.getDrawable() == null) {
            imageView.setImageResource(z ? R.color.f15180_resource_name_obfuscated_RES_2131100208 : R.color.f15170_resource_name_obfuscated_RES_2131100207);
        }
        if (AbstractC4772sd1.g()) {
            chromeImageView.setBackgroundTintList(chromeImageView.getContext().getColorStateList(z ? R.color.f12750_resource_name_obfuscated_RES_2131099965 : R.color.f12740_resource_name_obfuscated_RES_2131099964));
        }
        if (i == 1) {
            imageView2.setImageTintList(imageView2.getContext().getColorStateList(z ? R.color.f15120_resource_name_obfuscated_RES_2131100202 : R.color.f15110_resource_name_obfuscated_RES_2131100201));
        }
    }
}
