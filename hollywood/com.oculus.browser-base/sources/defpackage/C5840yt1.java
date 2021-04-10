package defpackage;

import android.view.View;
import android.widget.TextView;
import com.oculus.browser.R;
import org.chromium.components.browser_ui.widget.async_image.AsyncImageView;

/* renamed from: yt1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5840yt1 implements YH0 {
    @Override // defpackage.YH0
    public void a(Object obj, Object obj2, Object obj3) {
        UH0 uh0 = (UH0) obj;
        Jt1 jt1 = (Jt1) obj2;
        KH0 kh0 = (KH0) obj3;
        QH0 qh0 = Gt1.f8119a;
        int i = 0;
        if (kh0 == qh0) {
            boolean h = uh0.h(qh0);
            if (h && jt1.b == null) {
                jt1.b = jt1.f8322a.inflate();
            }
            View view = jt1.b;
            if (view != null) {
                if (!h) {
                    i = 8;
                }
                view.setVisibility(i);
                return;
            }
            return;
        }
        TH0 th0 = Gt1.b;
        if (kh0 == th0) {
            ((TextView) jt1.b.findViewById(R.id.title)).setText((String) uh0.g(th0));
            return;
        }
        TH0 th02 = Gt1.c;
        if (kh0 == th02) {
            ((TextView) jt1.b.findViewById(R.id.video_length)).setText((String) uh0.g(th02));
            return;
        }
        QH0 qh02 = Gt1.d;
        if (kh0 == qh02) {
            boolean h2 = uh0.h(qh02);
            TextView textView = (TextView) jt1.b.findViewById(R.id.video_length);
            if (!h2) {
                i = 8;
            }
            textView.setVisibility(i);
            return;
        }
        TH0 th03 = Gt1.f;
        if (kh0 == th03) {
            jt1.b.setOnClickListener(new Ht1((Runnable) uh0.g(th03)));
            return;
        }
        TH0 th04 = Gt1.g;
        if (kh0 == th04) {
            jt1.b.findViewById(R.id.close_button).setOnClickListener(new It1(jt1, (Runnable) uh0.g(th04)));
            return;
        }
        TH0 th05 = Gt1.e;
        if (kh0 == th05) {
            AsyncImageView asyncImageView = (AsyncImageView) jt1.b.findViewById(R.id.thumbnail);
            Object obj4 = asyncImageView.V;
            asyncImageView.setImageDrawable(null);
            asyncImageView.P.d(asyncImageView.R);
            asyncImageView.V = null;
            asyncImageView.S = (Bt1) uh0.g(th05);
            asyncImageView.e();
        }
    }
}
