package defpackage;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.TextView;
import com.oculus.browser.R;
import java.util.Objects;
import org.chromium.ui.widget.ChromeImageView;

/* renamed from: wJ0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5408wJ0 implements YH0 {
    @Override // defpackage.YH0
    public void a(Object obj, Object obj2, Object obj3) {
        UH0 uh0 = (UH0) obj;
        C5238vJ0 vj0 = (C5238vJ0) obj2;
        KH0 kh0 = (KH0) obj3;
        TH0 th0 = AbstractC5578xJ0.f11604a;
        if (th0 == kh0) {
            ((ChromeImageView) vj0.b.findViewById(R.id.qrcode)).setImageDrawable(new BitmapDrawable(vj0.f11474a.getResources(), (Bitmap) uh0.g(th0)));
            vj0.b.findViewById(R.id.download).setEnabled(true);
            vj0.b.findViewById(R.id.settings).setEnabled(true);
            return;
        }
        TH0 th02 = AbstractC5578xJ0.b;
        if (th02 == kh0) {
            TextView textView = (TextView) vj0.b.findViewById(R.id.error_message);
            textView.setText((String) uh0.g(th02));
            textView.setVisibility(0);
            vj0.b.findViewById(R.id.download).setEnabled(false);
            vj0.b.findViewById(R.id.settings).setEnabled(false);
            return;
        }
        QH0 qh0 = AbstractC5578xJ0.c;
        if (qh0 == kh0) {
            Boolean valueOf = Boolean.valueOf(uh0.h(qh0));
            if (!vj0.c || !valueOf.booleanValue()) {
                vj0.c = valueOf.booleanValue();
                vj0.a();
                return;
            }
            return;
        }
        QH0 qh02 = AbstractC5578xJ0.d;
        if (qh02 == kh0) {
            Boolean valueOf2 = Boolean.valueOf(uh0.h(qh02));
            Objects.requireNonNull(vj0);
            vj0.d = valueOf2.booleanValue();
            vj0.a();
            return;
        }
        QH0 qh03 = AbstractC5578xJ0.e;
        if (qh03 == kh0) {
            Boolean valueOf3 = Boolean.valueOf(uh0.h(qh03));
            Objects.requireNonNull(vj0);
            boolean booleanValue = valueOf3.booleanValue();
            vj0.e = booleanValue;
            if (booleanValue) {
                vj0.a();
            }
        }
    }
}
