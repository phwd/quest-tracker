package defpackage;

import android.content.res.Resources;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.ActionMode;
import com.oculus.browser.R;
import java.util.Objects;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.omnibox.UrlBarApi26;

/* renamed from: Kq1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Kq1 implements YH0 {
    @Override // defpackage.YH0
    public void a(Object obj, Object obj2, Object obj3) {
        int i;
        int i2;
        int i3;
        UH0 uh0 = (UH0) obj;
        UrlBarApi26 urlBarApi26 = (UrlBarApi26) obj2;
        KH0 kh0 = (KH0) obj3;
        TH0 th0 = Wq1.f9176a;
        if (th0.equals(kh0)) {
            ActionMode.Callback callback = (ActionMode.Callback) uh0.g(th0);
            if (callback != null || urlBarApi26.getCustomSelectionActionModeCallback() != null) {
                urlBarApi26.setCustomSelectionActionModeCallback(callback);
                return;
            }
            return;
        }
        QH0 qh0 = Wq1.b;
        if (qh0.equals(kh0)) {
            boolean h = uh0.h(qh0);
            urlBarApi26.d0 = h;
            urlBarApi26.setFocusable(h);
            urlBarApi26.setFocusableInTouchMode(h);
            return;
        }
        TH0 th02 = Wq1.c;
        boolean z = false;
        if (th02.equals(kh0)) {
            Uq1 uq1 = (Uq1) uh0.g(th02);
            AbstractC1286Vc vc = urlBarApi26.K;
            if (vc != null) {
                z = vc.h();
            }
            if (z) {
                String str = uq1.f9051a;
                String str2 = uq1.b;
                if (!TextUtils.isEmpty(str2)) {
                    urlBarApi26.N = true;
                }
                AbstractC1286Vc vc2 = urlBarApi26.K;
                if (vc2 != null) {
                    vc2.c(str, str2);
                    return;
                }
                return;
            }
            return;
        }
        TH0 th03 = Wq1.d;
        if (th03.equals(kh0)) {
            urlBarApi26.Q = (Hq1) uh0.g(th03);
            return;
        }
        TH0 th04 = Wq1.e;
        if (th04.equals(kh0)) {
            urlBarApi26.setOnFocusChangeListener(new Xq1(urlBarApi26, (Callback) uh0.g(th04)));
            return;
        }
        QH0 qh02 = Wq1.f;
        if (qh02.equals(kh0)) {
            urlBarApi26.setCursorVisible(uh0.h(qh02));
            return;
        }
        TH0 th05 = Wq1.g;
        if (th05.equals(kh0)) {
            urlBarApi26.T = (Iq1) uh0.g(th05);
            return;
        }
        TH0 th06 = Wq1.h;
        if (th06.equals(kh0)) {
            Vq1 vq1 = (Vq1) uh0.g(th06);
            urlBarApi26.j(true);
            urlBarApi26.setText(vq1.f9109a);
            urlBarApi26.r0 = vq1.b;
            int i4 = vq1.c;
            int i5 = vq1.d;
            if (i4 == 1) {
                urlBarApi26.p0 = i5;
            } else {
                urlBarApi26.p0 = 0;
            }
            urlBarApi26.q0 = i4;
            urlBarApi26.f();
            urlBarApi26.j(false);
            if (urlBarApi26.hasFocus()) {
                int i6 = vq1.e;
                if (i6 == 0) {
                    urlBarApi26.selectAll();
                } else if (i6 == 1) {
                    urlBarApi26.setSelection(urlBarApi26.getText().length());
                }
            }
        } else {
            QH0 qh03 = Wq1.l;
            if (qh03.equals(kh0)) {
                boolean h2 = uh0.h(qh03);
                Object tag = urlBarApi26.getTag(R.id.highlight_color);
                if (tag == null || !(tag instanceof Integer)) {
                    int highlightColor = urlBarApi26.getHighlightColor();
                    urlBarApi26.setTag(R.id.highlight_color, Integer.valueOf(highlightColor));
                    i = highlightColor;
                } else {
                    i = ((Integer) tag).intValue();
                }
                Resources resources = urlBarApi26.getResources();
                if (h2) {
                    i2 = resources.getColor(R.color.f11470_resource_name_obfuscated_RES_2131099837);
                    i3 = resources.getColor(R.color.f12910_resource_name_obfuscated_RES_2131099981);
                } else {
                    i2 = resources.getColor(R.color.f11570_resource_name_obfuscated_RES_2131099847);
                    int color = resources.getColor(R.color.f12920_resource_name_obfuscated_RES_2131099982);
                    int color2 = resources.getColor(R.color.f12710_resource_name_obfuscated_RES_2131099961);
                    i3 = color;
                    i = color2;
                }
                urlBarApi26.setTextColor(i2);
                Editable text = urlBarApi26.getText();
                if (TextUtils.isEmpty(text)) {
                    urlBarApi26.setHintTextColor(i3);
                } else {
                    int selectionStart = urlBarApi26.getSelectionStart();
                    int selectionEnd = urlBarApi26.getSelectionEnd();
                    urlBarApi26.j(true);
                    urlBarApi26.setText("");
                    urlBarApi26.setHintTextColor(i3);
                    urlBarApi26.setText(text);
                    if (selectionStart >= 0 && selectionEnd >= 0 && urlBarApi26.hasFocus()) {
                        Selection.setSelection(urlBarApi26.getText(), selectionStart, selectionEnd);
                    }
                    urlBarApi26.j(false);
                }
                urlBarApi26.setHighlightColor(i);
                return;
            }
            TH0 th07 = Wq1.i;
            if (th07.equals(kh0)) {
                Callback callback2 = (Callback) uh0.g(th07);
                urlBarApi26.U = callback2;
                if (callback2 != null) {
                    callback2.onResult(Integer.valueOf(urlBarApi26.P));
                    return;
                }
                return;
            }
            TH0 th08 = Wq1.j;
            if (th08.equals(kh0)) {
                urlBarApi26.R = (Jq1) uh0.g(th08);
                return;
            }
            TH0 th09 = Wq1.k;
            if (th09.equals(kh0)) {
                TextWatcher textWatcher = (TextWatcher) uh0.g(th09);
                if (!Objects.equals(urlBarApi26.S, textWatcher)) {
                    TextWatcher textWatcher2 = urlBarApi26.S;
                    if (textWatcher2 != null) {
                        urlBarApi26.removeTextChangedListener(textWatcher2);
                    }
                    urlBarApi26.S = textWatcher;
                    urlBarApi26.addTextChangedListener(textWatcher);
                    return;
                }
                return;
            }
            TH0 th010 = Wq1.m;
            if (th010.equals(kh0)) {
                urlBarApi26.W.f9977J = (Uy1) uh0.g(th010);
            }
        }
    }
}
