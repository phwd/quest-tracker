package defpackage;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.oculus.browser.R;
import java.util.Map;
import org.chromium.components.browser_ui.modaldialog.ModalDialogView;

/* renamed from: nl0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC3942nl0 implements YH0 {
    public void b(UH0 uh0, ModalDialogView modalDialogView, KH0 kh0) {
        TH0 th0 = AbstractC3258jl0.c;
        if (th0 == kh0) {
            modalDialogView.L.setText((CharSequence) uh0.g(th0));
            modalDialogView.d();
            return;
        }
        TH0 th02 = AbstractC3258jl0.d;
        if (th02 == kh0) {
            modalDialogView.M.setImageDrawable((Drawable) uh0.g(th02));
            modalDialogView.d();
            return;
        }
        TH0 th03 = AbstractC3258jl0.e;
        if (th03 == kh0) {
            modalDialogView.N.setText((String) uh0.g(th03));
            modalDialogView.d();
            return;
        }
        TH0 th04 = AbstractC3258jl0.f;
        if (th04 == kh0) {
            View view = (View) uh0.g(th04);
            if (modalDialogView.O.getChildCount() > 0) {
                modalDialogView.O.removeAllViews();
            }
            if (view != null) {
                Map map = AbstractC2417ep1.f9884a;
                ViewGroup viewGroup = (ViewGroup) view.getParent();
                if (viewGroup != null) {
                    viewGroup.removeView(view);
                }
                modalDialogView.O.addView(view);
                modalDialogView.O.setVisibility(0);
                return;
            }
            modalDialogView.O.setVisibility(8);
            return;
        }
        TH0 th05 = AbstractC3258jl0.g;
        if (th05 == kh0) {
            modalDialogView.a(0).setText((String) uh0.g(th05));
            modalDialogView.c();
            return;
        }
        TH0 th06 = AbstractC3258jl0.h;
        if (th06 == kh0) {
            modalDialogView.a(0).setContentDescription((String) uh0.g(th06));
            return;
        }
        QH0 qh0 = AbstractC3258jl0.i;
        if (qh0 == kh0) {
            modalDialogView.a(0).setEnabled(!uh0.h(qh0));
            return;
        }
        TH0 th07 = AbstractC3258jl0.j;
        if (th07 == kh0) {
            modalDialogView.a(1).setText((String) uh0.g(th07));
            modalDialogView.c();
            return;
        }
        TH0 th08 = AbstractC3258jl0.k;
        if (th08 == kh0) {
            modalDialogView.a(1).setContentDescription((String) uh0.g(th08));
            return;
        }
        QH0 qh02 = AbstractC3258jl0.l;
        if (qh02 == kh0) {
            modalDialogView.a(1).setEnabled(!uh0.h(qh02));
            return;
        }
        QH0 qh03 = AbstractC3258jl0.p;
        if (qh03 == kh0) {
            boolean h = uh0.h(qh03);
            if (modalDialogView.T != h) {
                modalDialogView.T = h;
                CharSequence text = modalDialogView.L.getText();
                Drawable drawable = modalDialogView.M.getDrawable();
                modalDialogView.K.setVisibility(8);
                ViewGroup viewGroup2 = (ViewGroup) modalDialogView.findViewById(h ? R.id.scrollable_title_container : R.id.title_container);
                modalDialogView.K = viewGroup2;
                modalDialogView.L = (TextView) viewGroup2.findViewById(R.id.title);
                modalDialogView.M = (ImageView) modalDialogView.K.findViewById(R.id.title_icon);
                modalDialogView.L.setText(text);
                modalDialogView.d();
                modalDialogView.M.setImageDrawable(drawable);
                modalDialogView.d();
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) modalDialogView.O.getLayoutParams();
                if (h) {
                    layoutParams.height = -2;
                    layoutParams.weight = 0.0f;
                    modalDialogView.f10815J.b(1, 1);
                } else {
                    layoutParams.height = 0;
                    layoutParams.weight = 1.0f;
                    modalDialogView.f10815J.b(0, 0);
                }
                modalDialogView.O.setLayoutParams(layoutParams);
            }
        } else if (AbstractC3258jl0.f10235a == kh0) {
            modalDialogView.S = new C3771ml0(uh0);
        } else if (AbstractC3258jl0.m != kh0) {
            MH0 mh0 = AbstractC3258jl0.n;
            if (mh0 == kh0) {
                boolean h2 = uh0.h(mh0);
                if (modalDialogView.U != h2) {
                    modalDialogView.U = h2;
                    if (h2) {
                        Button a2 = modalDialogView.a(0);
                        Button a3 = modalDialogView.a(1);
                        View$OnTouchListenerC3600ll0 ll0 = new View$OnTouchListenerC3600ll0(modalDialogView);
                        a2.setFilterTouchesWhenObscured(true);
                        a2.setOnTouchListener(ll0);
                        a3.setFilterTouchesWhenObscured(true);
                        a3.setOnTouchListener(ll0);
                        return;
                    }
                    return;
                }
                return;
            }
            OH0 oh0 = AbstractC3258jl0.o;
            if (oh0 == kh0) {
                modalDialogView.W = (Runnable) uh0.g(oh0);
            } else {
                OH0 oh02 = AbstractC3258jl0.b;
            }
        }
    }
}
