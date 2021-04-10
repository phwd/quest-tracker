package defpackage;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.text.TextWatcher;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.oculus.browser.R;
import org.chromium.ui.widget.ChipView;

/* renamed from: pQ0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4231pQ0 implements YH0 {
    @Override // defpackage.YH0
    public void a(Object obj, Object obj2, Object obj3) {
        UH0 uh0 = (UH0) obj;
        View view = (View) obj2;
        KH0 kh0 = (KH0) obj3;
        ImageView imageView = (ImageView) view.findViewById(R.id.voice_search_button);
        TextView textView = (TextView) view.findViewById(R.id.search_box_text);
        ChipView chipView = (ChipView) view.findViewById(R.id.query_tiles_chip);
        QH0 qh0 = AbstractC4060oQ0.c;
        int i = 8;
        boolean z = false;
        if (qh0 == kh0) {
            if (uh0.h(qh0)) {
                i = 0;
            }
            view.setVisibility(i);
            return;
        }
        RH0 rh0 = AbstractC4060oQ0.f10548a;
        if (rh0 == kh0) {
            view.setAlpha(uh0.e(rh0));
            if (view.getAlpha() == 1.0f) {
                z = true;
            }
            AbstractC4656rv1.g(view, z);
            return;
        }
        TH0 th0 = AbstractC4060oQ0.b;
        if (th0 == kh0) {
            view.setBackground((Drawable) uh0.g(th0));
            return;
        }
        TH0 th02 = AbstractC4060oQ0.f;
        if (th02 == kh0) {
            imageView.setImageTintList((ColorStateList) uh0.g(th02));
            return;
        }
        TH0 th03 = AbstractC4060oQ0.e;
        if (th03 == kh0) {
            imageView.setImageDrawable((Drawable) uh0.g(th03));
            return;
        }
        QH0 qh02 = AbstractC4060oQ0.d;
        if (qh02 == kh0) {
            if (uh0.h(qh02)) {
                i = 0;
            }
            imageView.setVisibility(i);
            return;
        }
        TH0 th04 = AbstractC4060oQ0.j;
        if (th04 == kh0) {
            textView.setOnClickListener((View.OnClickListener) uh0.g(th04));
            return;
        }
        TH0 th05 = AbstractC4060oQ0.k;
        if (th05 == kh0) {
            textView.addTextChangedListener((TextWatcher) uh0.g(th05));
            return;
        }
        TH0 th06 = AbstractC4060oQ0.h;
        if (th06 == kh0) {
            textView.setText((CharSequence) ((Pair) uh0.g(th06)).first);
            return;
        }
        QH0 qh03 = AbstractC4060oQ0.i;
        if (qh03 == kh0) {
            textView.setHint(uh0.h(qh03) ? view.getContext().getString(R.string.f61100_resource_name_obfuscated_RES_2131953427) : null);
            return;
        }
        TH0 th07 = AbstractC4060oQ0.g;
        if (th07 == kh0) {
            imageView.setOnClickListener((View.OnClickListener) uh0.g(th07));
            return;
        }
        SH0 sh0 = AbstractC4060oQ0.l;
        if (sh0 == kh0) {
            textView.setHintTextColor(uh0.f(sh0));
            return;
        }
        TH0 th08 = AbstractC4060oQ0.m;
        if (th08 == kh0) {
            chipView.F.setText((CharSequence) uh0.g(th08));
            return;
        }
        QH0 qh04 = AbstractC4060oQ0.n;
        if (qh04 == kh0) {
            if (uh0.h(qh04)) {
                i = 0;
            }
            chipView.setVisibility(i);
            return;
        }
        TH0 th09 = AbstractC4060oQ0.o;
        if (th09 == kh0) {
            chipView.G.setVisibility(0);
            chipView.G.setImageDrawable((Drawable) uh0.g(th09));
            chipView.e(true);
            return;
        }
        TH0 th010 = AbstractC4060oQ0.p;
        if (th010 == kh0) {
            chipView.setOnClickListener((View.OnClickListener) uh0.g(th010));
            return;
        }
        TH0 th011 = AbstractC4060oQ0.q;
        if (th011 == kh0) {
            chipView.a();
            chipView.d((View.OnClickListener) uh0.g(th011));
        }
    }
}
