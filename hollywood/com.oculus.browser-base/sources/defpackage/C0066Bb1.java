package defpackage;

import android.content.res.ColorStateList;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.Map;
import org.chromium.chrome.browser.tasks.tab_management.TabSelectionEditorLayout;
import org.chromium.chrome.browser.tasks.tab_management.TabSelectionEditorToolbar;
import org.chromium.components.browser_ui.widget.NumberRollView;

/* renamed from: Bb1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C0066Bb1 implements YH0 {
    @Override // defpackage.YH0
    public void a(Object obj, Object obj2, Object obj3) {
        UH0 uh0 = (UH0) obj;
        TabSelectionEditorLayout tabSelectionEditorLayout = (TabSelectionEditorLayout) obj2;
        KH0 kh0 = (KH0) obj3;
        QH0 qh0 = AbstractC0736Mb1.f8486a;
        if (qh0 == kh0) {
            int i = 0;
            if (uh0.h(qh0)) {
                tabSelectionEditorLayout.V = true;
                while (i < tabSelectionEditorLayout.T.getChildCount()) {
                    View childAt = tabSelectionEditorLayout.T.getChildAt(i);
                    tabSelectionEditorLayout.W.put(childAt, Integer.valueOf(childAt.getImportantForAccessibility()));
                    childAt.setImportantForAccessibility(4);
                    i++;
                }
                Map map = tabSelectionEditorLayout.W;
                ViewGroup viewGroup = tabSelectionEditorLayout.T;
                map.put(viewGroup, Integer.valueOf(viewGroup.getImportantForAccessibility()));
                tabSelectionEditorLayout.T.setImportantForAccessibility(2);
                tabSelectionEditorLayout.T.addView(tabSelectionEditorLayout);
                return;
            }
            tabSelectionEditorLayout.V = false;
            tabSelectionEditorLayout.T.removeView(tabSelectionEditorLayout);
            for (int i2 = 0; i2 < tabSelectionEditorLayout.T.getChildCount(); i2++) {
                View childAt2 = tabSelectionEditorLayout.T.getChildAt(i2);
                Integer num = (Integer) tabSelectionEditorLayout.W.get(childAt2);
                childAt2.setImportantForAccessibility(num == null ? 0 : num.intValue());
            }
            Integer num2 = (Integer) tabSelectionEditorLayout.W.get(tabSelectionEditorLayout.T);
            ViewGroup viewGroup2 = tabSelectionEditorLayout.T;
            if (num2 != null) {
                i = num2.intValue();
            }
            viewGroup2.setImportantForAccessibility(i);
            tabSelectionEditorLayout.W.clear();
            return;
        }
        TH0 th0 = AbstractC0736Mb1.b;
        if (th0 == kh0) {
            tabSelectionEditorLayout.S.c1.setOnClickListener((View.OnClickListener) uh0.g(th0));
            return;
        }
        TH0 th02 = AbstractC0736Mb1.e;
        if (th02 == kh0) {
            tabSelectionEditorLayout.S.E((View.OnClickListener) uh0.g(th02));
            return;
        }
        SH0 sh0 = AbstractC0736Mb1.f;
        if (sh0 == kh0) {
            tabSelectionEditorLayout.setBackgroundColor(uh0.f(sh0));
            return;
        }
        SH0 sh02 = AbstractC0736Mb1.g;
        if (sh02 == kh0) {
            tabSelectionEditorLayout.S.e1 = uh0.f(sh02);
            return;
        }
        TH0 th03 = AbstractC0736Mb1.h;
        if (th03 == kh0) {
            TabSelectionEditorToolbar tabSelectionEditorToolbar = tabSelectionEditorLayout.S;
            ColorStateList colorStateList = (ColorStateList) uh0.g(th03);
            tabSelectionEditorToolbar.c1.setTextColor(colorStateList);
            ((C0636Ki1) tabSelectionEditorToolbar.q()).c(colorStateList);
            return;
        }
        SH0 sh03 = AbstractC0736Mb1.i;
        if (sh03 == kh0) {
            TabSelectionEditorToolbar tabSelectionEditorToolbar2 = tabSelectionEditorLayout.S;
            int f = uh0.f(sh03);
            NumberRollView numberRollView = tabSelectionEditorToolbar2.E0;
            TextView textView = numberRollView.G;
            textView.setTextAppearance(textView.getContext(), f);
            TextView textView2 = numberRollView.H;
            textView2.setTextAppearance(textView2.getContext(), f);
            return;
        }
        TH0 th04 = AbstractC0736Mb1.c;
        if (th04 == kh0) {
            tabSelectionEditorLayout.S.c1.setText((String) uh0.g(th04));
            return;
        }
        SH0 sh04 = AbstractC0736Mb1.d;
        if (sh04 == kh0) {
            tabSelectionEditorLayout.S.f1 = uh0.f(sh04);
            return;
        }
        SH0 sh05 = AbstractC0736Mb1.j;
        if (sh05 == kh0) {
            tabSelectionEditorLayout.S.d1 = Integer.valueOf(uh0.f(sh05));
        }
    }
}
