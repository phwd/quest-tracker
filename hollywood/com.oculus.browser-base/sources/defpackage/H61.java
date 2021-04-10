package defpackage;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.browser.R;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.chromium.chrome.browser.tasks.tab_management.TabGridDialogView;
import org.chromium.chrome.browser.tasks.tab_management.TabGroupUiToolbarView;

/* renamed from: H61  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class H61 implements YH0 {
    @Override // defpackage.YH0
    public void a(Object obj, Object obj2, Object obj3) {
        float f;
        UH0 uh0 = (UH0) obj;
        C5203v71 v71 = (C5203v71) obj2;
        KH0 kh0 = (KH0) obj3;
        TH0 th0 = AbstractC5033u71.f11387a;
        if (th0 == kh0) {
            v71.f11462a.G.setOnClickListener((View.OnClickListener) uh0.g(th0));
            return;
        }
        TH0 th02 = AbstractC5033u71.b;
        if (th02 == kh0) {
            v71.f11462a.F.setOnClickListener((View.OnClickListener) uh0.g(th02));
            return;
        }
        TH0 th03 = AbstractC5033u71.c;
        if (th03 == kh0) {
            TabGroupUiToolbarView tabGroupUiToolbarView = v71.f11462a;
            String str = (String) uh0.g(th03);
            EditText editText = tabGroupUiToolbarView.L;
            if (editText != null) {
                editText.setText(str);
                return;
            }
            throw new IllegalStateException("Current Toolbar doesn't have a title text view");
        }
        SH0 sh0 = AbstractC5033u71.d;
        if (sh0 == kh0) {
            ((FrameLayout.LayoutParams) v71.b.getLayoutParams()).topMargin = uh0.f(sh0);
            v71.b.requestLayout();
            return;
        }
        SH0 sh02 = AbstractC5033u71.e;
        if (sh02 == kh0) {
            v71.f11462a.a(uh0.f(sh02));
            v71.b.setBackgroundColor(uh0.f(sh02));
            return;
        }
        TH0 th04 = AbstractC5033u71.f;
        if (th04 == kh0) {
            v71.f11462a.b((ColorStateList) uh0.g(th04));
            return;
        }
        TH0 th05 = AbstractC5033u71.h;
        if (th05 == kh0) {
            TabGridDialogView tabGridDialogView = v71.c;
            Objects.requireNonNull(tabGridDialogView);
            Map c = UH0.c(MP0.m);
            OH0 oh0 = MP0.c;
            RelativeLayout relativeLayout = tabGridDialogView.Q;
            LH0 lh0 = new LH0(null);
            lh0.f8415a = relativeLayout;
            HashMap hashMap = (HashMap) c;
            hashMap.put(oh0, lh0);
            MH0 mh0 = MP0.d;
            GH0 gh0 = new GH0(null);
            gh0.f8081a = false;
            hashMap.put(mh0, gh0);
            MH0 mh02 = MP0.b;
            GH0 gh02 = new GH0(null);
            gh02.f8081a = true;
            hashMap.put(mh02, gh02);
            NH0 nh0 = MP0.f8474a;
            JH0 jh0 = new JH0(null);
            jh0.f8282a = 0;
            hashMap.put(nh0, jh0);
            OH0 oh02 = MP0.f;
            LH0 lh02 = new LH0(null);
            lh02.f8415a = (Runnable) uh0.g(th05);
            hashMap.put(oh02, lh02);
            QH0 qh0 = MP0.k;
            GH0 gh03 = new GH0(null);
            gh03.f8081a = true;
            hashMap.put(qh0, gh03);
            tabGridDialogView.R = new UH0(c, null);
            return;
        }
        QH0 qh02 = AbstractC5033u71.g;
        if (qh02 != kh0) {
            TH0 th06 = AbstractC5033u71.i;
            if (th06 == kh0) {
                TabGridDialogView tabGridDialogView2 = v71.c;
                View view = (View) uh0.g(th06);
                Objects.requireNonNull(tabGridDialogView2);
                if (view == null) {
                    AnimatorSet animatorSet = new AnimatorSet();
                    tabGridDialogView2.e0 = animatorSet;
                    animatorSet.play(tabGridDialogView2.a0);
                    tabGridDialogView2.e0.removeAllListeners();
                    tabGridDialogView2.e0.addListener(tabGridDialogView2.g0);
                    AnimatorSet animatorSet2 = new AnimatorSet();
                    tabGridDialogView2.f0 = animatorSet2;
                    animatorSet2.play(tabGridDialogView2.b0);
                    tabGridDialogView2.f0.removeAllListeners();
                    tabGridDialogView2.f0.addListener(tabGridDialogView2.h0);
                    return;
                }
                tabGridDialogView2.L = view;
                Rect rect = new Rect();
                tabGridDialogView2.L.getGlobalVisibleRect(rect);
                Rect rect2 = new Rect();
                tabGridDialogView2.O.getGlobalVisibleRect(rect2);
                rect.offset(0, -rect2.top);
                View view2 = tabGridDialogView2.L;
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) tabGridDialogView2.K.getLayoutParams();
                layoutParams.width = view2.getWidth();
                layoutParams.height = view2.getHeight();
                if (view2.findViewById(R.id.tab_title) != null) {
                    tabGridDialogView2.K.findViewById(R.id.card_view).setBackground(view2.findViewById(R.id.card_view).getBackground());
                    ImageView imageView = (ImageView) view2.findViewById(R.id.tab_favicon);
                    ImageView imageView2 = (ImageView) tabGridDialogView2.K.findViewById(R.id.tab_favicon);
                    if (imageView.getDrawable() != null) {
                        int dimensionPixelSize = tabGridDialogView2.F.getResources().getDimensionPixelSize(R.dimen.f25610_resource_name_obfuscated_RES_2131166180);
                        imageView2.setPadding(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
                        imageView2.setImageDrawable(imageView.getDrawable());
                    } else {
                        imageView2.setImageDrawable(null);
                    }
                    ((TextView) tabGridDialogView2.K.findViewById(R.id.tab_title)).setText(((TextView) view2.findViewById(R.id.tab_title)).getText());
                    AbstractC3153j7.i((TextView) tabGridDialogView2.K.findViewById(R.id.tab_title), R.style.f72100_resource_name_obfuscated_RES_2132017783);
                    ((ImageView) tabGridDialogView2.K.findViewById(R.id.tab_thumbnail)).setImageDrawable(((ImageView) view2.findViewById(R.id.tab_thumbnail)).getDrawable());
                    ImageView imageView3 = (ImageView) tabGridDialogView2.K.findViewById(R.id.action_button);
                    imageView3.setImageDrawable(((ImageView) view2.findViewById(R.id.action_button)).getDrawable());
                    imageView3.setImageTintList(((ImageView) view2.findViewById(R.id.action_button)).getImageTintList());
                    tabGridDialogView2.K.findViewById(R.id.divider_view).setBackgroundColor(((ColorDrawable) view2.findViewById(R.id.divider_view).getBackground()).getColor());
                    tabGridDialogView2.K.findViewById(R.id.background_view).setBackground(null);
                }
                int i = tabGridDialogView2.m0 - (tabGridDialogView2.k0 * 2);
                int i2 = tabGridDialogView2.n0 - (tabGridDialogView2.j0 * 2);
                float f2 = tabGridDialogView2.I;
                float f3 = ((float) rect.left) + f2;
                float f4 = ((float) rect.top) + f2;
                float height = ((float) rect.height()) - (tabGridDialogView2.I * 2.0f);
                float width = ((float) rect.width()) - (tabGridDialogView2.I * 2.0f);
                float f5 = -((((float) ((i / 2) + tabGridDialogView2.k0)) - (height / 2.0f)) - f4);
                float f6 = -((((float) ((i2 / 2) + tabGridDialogView2.j0)) - (width / 2.0f)) - f3);
                float f7 = (float) i;
                float f8 = height / f7;
                float f9 = (float) i2;
                float f10 = width / f9;
                if (tabGridDialogView2.l0 == 1) {
                    f = f9 / ((float) rect.width());
                } else {
                    f = f7 / ((float) rect.height());
                }
                float f11 = (f - 1.0f) / 2.0f;
                float f12 = (f11 * height) + ((float) tabGridDialogView2.k0);
                float f13 = (f11 * width) + ((float) tabGridDialogView2.j0);
                float f14 = tabGridDialogView2.I;
                float f15 = f4 - f14;
                float f16 = f3 - f14;
                float f17 = f5 - ((height - (f7 * f10)) / 2.0f);
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(tabGridDialogView2.K, View.TRANSLATION_Y, f15, f12);
                ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(tabGridDialogView2.K, View.TRANSLATION_X, f16, f13);
                ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(tabGridDialogView2.K, View.SCALE_X, 1.0f, f);
                ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(tabGridDialogView2.K, View.SCALE_Y, 1.0f, f);
                AnimatorSet animatorSet3 = new AnimatorSet();
                animatorSet3.setDuration(300L);
                animation.InterpolatorC2176dO dOVar = G30.c;
                animatorSet3.setInterpolator(dOVar);
                animatorSet3.play(ofFloat).with(ofFloat2).with(ofFloat4).with(ofFloat3);
                ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(tabGridDialogView2.K, View.ALPHA, 1.0f, 0.0f);
                ofFloat5.setDuration(150L);
                LinearInterpolator linearInterpolator = G30.d;
                ofFloat5.setInterpolator(linearInterpolator);
                ObjectAnimator ofFloat6 = ObjectAnimator.ofFloat(tabGridDialogView2.Q, View.TRANSLATION_Y, f17, 0.0f);
                ObjectAnimator ofFloat7 = ObjectAnimator.ofFloat(tabGridDialogView2.Q, View.TRANSLATION_X, f6, 0.0f);
                ObjectAnimator ofFloat8 = ObjectAnimator.ofFloat(tabGridDialogView2.Q, View.SCALE_Y, f10, 1.0f);
                ObjectAnimator ofFloat9 = ObjectAnimator.ofFloat(tabGridDialogView2.Q, View.SCALE_X, f10, 1.0f);
                AnimatorSet animatorSet4 = new AnimatorSet();
                animatorSet4.setDuration(300L);
                animatorSet4.setInterpolator(dOVar);
                animatorSet4.play(ofFloat6).with(ofFloat7).with(ofFloat8).with(ofFloat9);
                ObjectAnimator ofFloat10 = ObjectAnimator.ofFloat(tabGridDialogView2.Q, View.ALPHA, 0.0f, 1.0f);
                ofFloat10.setDuration(150L);
                ofFloat10.setStartDelay(150);
                ofFloat10.setInterpolator(linearInterpolator);
                ObjectAnimator ofFloat11 = ObjectAnimator.ofFloat(tabGridDialogView2.f10781J, View.TRANSLATION_Y, f5, 0.0f);
                ObjectAnimator ofFloat12 = ObjectAnimator.ofFloat(tabGridDialogView2.f10781J, View.TRANSLATION_X, f6, 0.0f);
                ObjectAnimator ofFloat13 = ObjectAnimator.ofFloat(tabGridDialogView2.f10781J, View.SCALE_Y, f8, 1.0f);
                ObjectAnimator ofFloat14 = ObjectAnimator.ofFloat(tabGridDialogView2.f10781J, View.SCALE_X, f10, 1.0f);
                AnimatorSet animatorSet5 = new AnimatorSet();
                animatorSet5.setDuration(300L);
                animatorSet5.setInterpolator(dOVar);
                animatorSet5.play(ofFloat11).with(ofFloat12).with(ofFloat13).with(ofFloat14);
                ObjectAnimator ofFloat15 = ObjectAnimator.ofFloat(tabGridDialogView2.L, View.ALPHA, 1.0f, 0.0f);
                ofFloat15.setDuration(50L);
                animatorSet4.addListener(new C3326k71(tabGridDialogView2));
                ofFloat10.addListener(new C3497l71(tabGridDialogView2));
                AnimatorSet animatorSet6 = new AnimatorSet();
                tabGridDialogView2.e0 = animatorSet6;
                animatorSet6.play(animatorSet3).with(ofFloat5).with(animatorSet5).with(animatorSet4).with(ofFloat10).with(ofFloat15);
                tabGridDialogView2.e0.addListener(tabGridDialogView2.g0);
                ObjectAnimator ofFloat16 = ObjectAnimator.ofFloat(tabGridDialogView2.Q, View.TRANSLATION_Y, 0.0f, f17);
                ObjectAnimator ofFloat17 = ObjectAnimator.ofFloat(tabGridDialogView2.Q, View.TRANSLATION_X, 0.0f, f6);
                ObjectAnimator ofFloat18 = ObjectAnimator.ofFloat(tabGridDialogView2.Q, View.SCALE_Y, 1.0f, f10);
                ObjectAnimator ofFloat19 = ObjectAnimator.ofFloat(tabGridDialogView2.Q, View.SCALE_X, 1.0f, f10);
                AnimatorSet animatorSet7 = new AnimatorSet();
                animatorSet7.play(ofFloat16).with(ofFloat17).with(ofFloat18).with(ofFloat19);
                animatorSet7.setDuration(300L);
                animatorSet7.setInterpolator(dOVar);
                animatorSet7.addListener(new C3668m71(tabGridDialogView2));
                ObjectAnimator ofFloat20 = ObjectAnimator.ofFloat(tabGridDialogView2.Q, View.ALPHA, 1.0f, 0.0f);
                ofFloat20.setDuration(150L);
                ofFloat20.setInterpolator(linearInterpolator);
                ObjectAnimator ofFloat21 = ObjectAnimator.ofFloat(tabGridDialogView2.K, View.TRANSLATION_Y, f12, f15);
                ObjectAnimator ofFloat22 = ObjectAnimator.ofFloat(tabGridDialogView2.K, View.TRANSLATION_X, f13, f16);
                ObjectAnimator ofFloat23 = ObjectAnimator.ofFloat(tabGridDialogView2.K, View.SCALE_X, f, 1.0f);
                ObjectAnimator ofFloat24 = ObjectAnimator.ofFloat(tabGridDialogView2.K, View.SCALE_Y, f, 1.0f);
                AnimatorSet animatorSet8 = new AnimatorSet();
                animatorSet8.play(ofFloat21).with(ofFloat22).with(ofFloat23).with(ofFloat24);
                animatorSet8.setDuration(300L);
                animatorSet8.setInterpolator(dOVar);
                ObjectAnimator ofFloat25 = ObjectAnimator.ofFloat(tabGridDialogView2.K, View.ALPHA, 0.0f, 1.0f);
                ofFloat25.setDuration(150L);
                ofFloat25.setStartDelay(150);
                ofFloat25.setInterpolator(linearInterpolator);
                ofFloat25.addListener(new C3839n71(tabGridDialogView2));
                ObjectAnimator ofFloat26 = ObjectAnimator.ofFloat(tabGridDialogView2.f10781J, View.TRANSLATION_Y, 0.0f, f5);
                ObjectAnimator ofFloat27 = ObjectAnimator.ofFloat(tabGridDialogView2.f10781J, View.TRANSLATION_X, 0.0f, f6);
                ObjectAnimator ofFloat28 = ObjectAnimator.ofFloat(tabGridDialogView2.f10781J, View.SCALE_Y, 1.0f, f8);
                ObjectAnimator ofFloat29 = ObjectAnimator.ofFloat(tabGridDialogView2.f10781J, View.SCALE_X, 1.0f, f10);
                AnimatorSet animatorSet9 = new AnimatorSet();
                animatorSet9.play(ofFloat26).with(ofFloat27).with(ofFloat28).with(ofFloat29);
                animatorSet9.setDuration(300L);
                animatorSet9.setInterpolator(dOVar);
                animatorSet9.addListener(new C2301e71(tabGridDialogView2));
                ObjectAnimator ofFloat30 = ObjectAnimator.ofFloat(tabGridDialogView2.L, View.ALPHA, 0.0f, 1.0f);
                ofFloat30.setDuration(50L);
                ofFloat30.setStartDelay(250);
                AnimatorSet animatorSet10 = new AnimatorSet();
                tabGridDialogView2.f0 = animatorSet10;
                animatorSet10.play(animatorSet7).with(ofFloat20).with(animatorSet9).with(animatorSet8).with(ofFloat25).with(ofFloat30);
                tabGridDialogView2.f0.addListener(tabGridDialogView2.h0);
                return;
            }
            SH0 sh03 = AbstractC5033u71.j;
            if (sh03 == kh0) {
                TabGridDialogView tabGridDialogView3 = v71.c;
                int f18 = uh0.f(sh03);
                if (f18 != tabGridDialogView3.o0) {
                    if (f18 == 0) {
                        tabGridDialogView3.b(false);
                        if (tabGridDialogView3.o0 == 1) {
                            tabGridDialogView3.c0.start();
                        }
                    } else if (f18 == 1) {
                        tabGridDialogView3.d0.start();
                    } else if (f18 == 2) {
                        tabGridDialogView3.b(true);
                        if (tabGridDialogView3.o0 == 1) {
                            tabGridDialogView3.c0.start();
                        }
                    }
                    tabGridDialogView3.o0 = f18;
                    return;
                }
                return;
            }
            SH0 sh04 = AbstractC5033u71.k;
            if (sh04 != kh0) {
                SH0 sh05 = AbstractC5033u71.l;
                if (sh05 == kh0) {
                    TabGridDialogView tabGridDialogView4 = v71.c;
                    if (tabGridDialogView4 != null) {
                        tabGridDialogView4.p0 = uh0.f(sh05);
                        return;
                    }
                    return;
                }
                SH0 sh06 = AbstractC5033u71.m;
                if (sh06 == kh0) {
                    TabGridDialogView tabGridDialogView5 = v71.c;
                    if (tabGridDialogView5 != null) {
                        tabGridDialogView5.q0 = uh0.f(sh06);
                        return;
                    }
                    return;
                }
                SH0 sh07 = AbstractC5033u71.n;
                if (sh07 == kh0) {
                    TabGridDialogView tabGridDialogView6 = v71.c;
                    if (tabGridDialogView6 != null) {
                        tabGridDialogView6.r0 = uh0.f(sh07);
                        return;
                    }
                    return;
                }
                TH0 th07 = AbstractC5033u71.o;
                if (th07 == kh0) {
                    ((LinearLayoutManager) v71.b.U).C1(((Integer) uh0.g(th07)).intValue(), 0);
                } else if (AbstractC5033u71.p == kh0) {
                    v71.b.setVisibility(0);
                } else {
                    TH0 th08 = AbstractC5033u71.q;
                    if (th08 == kh0) {
                        v71.f11462a.H.setOnClickListener((View.OnClickListener) uh0.g(th08));
                        return;
                    }
                    TH0 th09 = AbstractC5033u71.r;
                    if (th09 == kh0) {
                        v71.f11462a.L.addTextChangedListener((TextWatcher) uh0.g(th09));
                        return;
                    }
                    TH0 th010 = AbstractC5033u71.s;
                    if (th010 == kh0) {
                        v71.f11462a.L.setOnFocusChangeListener((View.OnFocusChangeListener) uh0.g(th010));
                        return;
                    }
                    QH0 qh03 = AbstractC5033u71.t;
                    if (qh03 == kh0) {
                        TabGroupUiToolbarView tabGroupUiToolbarView2 = v71.f11462a;
                        tabGroupUiToolbarView2.L.setCursorVisible(uh0.h(qh03));
                        return;
                    }
                    QH0 qh04 = AbstractC5033u71.u;
                    if (qh04 != kh0) {
                        QH0 qh05 = AbstractC5033u71.v;
                        if (qh05 != kh0) {
                            TH0 th011 = AbstractC5033u71.w;
                            if (th011 == kh0 && AbstractC4772sd1.d()) {
                                v71.f11462a.G.setContentDescription((String) uh0.g(th011));
                            }
                        } else if (AbstractC4772sd1.d()) {
                            TabGroupUiToolbarView tabGroupUiToolbarView3 = v71.f11462a;
                            boolean h = uh0.h(qh05);
                            Objects.requireNonNull(tabGroupUiToolbarView3);
                            if (AbstractC4772sd1.d()) {
                                if (h) {
                                    tabGroupUiToolbarView3.postDelayed(new RunnableC2817h81(tabGroupUiToolbarView3, C3493l60.F), (long) 150);
                                } else {
                                    C3493l60.F.d(tabGroupUiToolbarView3);
                                }
                            }
                        } else if (!uh0.h(qh05)) {
                            TabGroupUiToolbarView tabGroupUiToolbarView4 = v71.f11462a;
                            Objects.requireNonNull(tabGroupUiToolbarView4);
                            C3493l60.F.d(tabGroupUiToolbarView4);
                        }
                    } else if (AbstractC4772sd1.d()) {
                        TabGroupUiToolbarView tabGroupUiToolbarView5 = v71.f11462a;
                        boolean h2 = uh0.h(qh04);
                        Objects.requireNonNull(tabGroupUiToolbarView5);
                        if (!AbstractC4772sd1.d() || tabGroupUiToolbarView5.L.isFocused() == h2) {
                            return;
                        }
                        if (h2) {
                            tabGroupUiToolbarView5.L.requestFocus();
                        } else {
                            tabGroupUiToolbarView5.L.clearFocus();
                        }
                    } else if (!uh0.h(qh04)) {
                        v71.f11462a.L.clearFocus();
                    }
                }
            } else if (v71.c != null) {
                int f19 = uh0.f(sh04);
                TabGridDialogView tabGridDialogView7 = v71.c;
                tabGridDialogView7.Q.setBackgroundResource(f19);
                tabGridDialogView7.f10781J.setBackgroundResource(f19);
                v71.f11462a.setBackgroundResource(f19);
            }
        } else if (uh0.h(qh02)) {
            TabGridDialogView tabGridDialogView8 = v71.c;
            TabGroupUiToolbarView tabGroupUiToolbarView6 = v71.f11462a;
            RecyclerView recyclerView = v71.b;
            tabGridDialogView8.Q.removeAllViews();
            tabGridDialogView8.Q.addView(tabGroupUiToolbarView6);
            tabGridDialogView8.Q.addView(recyclerView);
            tabGridDialogView8.Q.addView(tabGridDialogView8.M);
            tabGridDialogView8.Q.addView(tabGridDialogView8.N);
            ((RelativeLayout.LayoutParams) recyclerView.getLayoutParams()).setMargins(0, tabGridDialogView8.G, 0, 0);
            recyclerView.setVisibility(0);
            TabGridDialogView tabGridDialogView9 = v71.c;
            Animator animator = tabGridDialogView9.V;
            if (!(animator == null || animator == tabGridDialogView9.e0)) {
                animator.end();
            }
            tabGridDialogView9.V = tabGridDialogView9.e0;
            tabGridDialogView9.S.a(tabGridDialogView9.R);
            tabGridDialogView9.setVisibility(0);
            tabGridDialogView9.e0.start();
        } else {
            TabGridDialogView tabGridDialogView10 = v71.c;
            if (tabGridDialogView10.getVisibility() == 0) {
                Animator animator2 = tabGridDialogView10.V;
                if (!(animator2 == null || animator2 == tabGridDialogView10.f0)) {
                    animator2.end();
                }
                tabGridDialogView10.V = tabGridDialogView10.f0;
                tabGridDialogView10.S.b.a(true);
                tabGridDialogView10.f0.start();
            }
        }
    }
}
