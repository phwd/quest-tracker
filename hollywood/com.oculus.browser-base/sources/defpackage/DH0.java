package defpackage;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.oculus.browser.R;
import java.util.Objects;
import org.chromium.components.browser_ui.widget.DualControlLayout;
import org.chromium.components.browser_ui.widget.PromoDialogLayout;

/* renamed from: DH0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class DH0 extends K4 implements View.OnClickListener, DialogInterface.OnDismissListener {
    public static final int[] F = {R.id.button_primary, R.id.button_secondary};
    public final FrameLayout G;
    public PromoDialogLayout H;

    public DH0(Activity activity) {
        super(activity, R.style.f69590_resource_name_obfuscated_RES_2132017532);
        FrameLayout frameLayout = new FrameLayout(activity);
        this.G = frameLayout;
        frameLayout.setBackgroundColor(activity.getResources().getColor(R.color.f13320_resource_name_obfuscated_RES_2131100022));
        LayoutInflater.from(activity).inflate(R.layout.f40960_resource_name_obfuscated_RES_2131624405, (ViewGroup) frameLayout, true);
        this.H = (PromoDialogLayout) frameLayout.findViewById(R.id.promo_dialog_layout);
    }

    public abstract CH0 a();

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(this.G);
        PromoDialogLayout promoDialogLayout = this.H;
        CH0 a2 = a();
        promoDialogLayout.P = a2;
        Objects.requireNonNull(a2);
        if (promoDialogLayout.P.f7799a != 0) {
            promoDialogLayout.L.setImageDrawable(Fs1.a(promoDialogLayout.getResources(), promoDialogLayout.P.f7799a, promoDialogLayout.getContext().getTheme()));
        } else {
            ((ViewGroup) promoDialogLayout.L.getParent()).removeView(promoDialogLayout.L);
        }
        Objects.requireNonNull(promoDialogLayout.P);
        promoDialogLayout.M.setText(promoDialogLayout.P.b);
        CH0 ch0 = promoDialogLayout.P;
        CharSequence charSequence = ch0.c;
        if (charSequence != null) {
            promoDialogLayout.O.setText(charSequence);
            if (promoDialogLayout.P.d) {
                promoDialogLayout.O.setMovementMethod(LinkMovementMethod.getInstance());
            }
        } else {
            int i = ch0.e;
            if (i == 0) {
                ((ViewGroup) promoDialogLayout.O.getParent()).removeView(promoDialogLayout.O);
            } else {
                promoDialogLayout.O.setText(i);
            }
        }
        ViewStub viewStub = (ViewStub) promoDialogLayout.findViewById(R.id.footer_stub);
        if (promoDialogLayout.P.f == 0) {
            ((ViewGroup) viewStub.getParent()).removeView(viewStub);
        } else {
            TextView textView = (TextView) viewStub.inflate();
            promoDialogLayout.N = textView;
            textView.setText(promoDialogLayout.P.f);
        }
        DualControlLayout dualControlLayout = (DualControlLayout) promoDialogLayout.findViewById(R.id.button_bar);
        Objects.requireNonNull(promoDialogLayout.P);
        dualControlLayout.addView(DualControlLayout.a(promoDialogLayout.getContext(), true, promoDialogLayout.getResources().getString(promoDialogLayout.P.g), null));
        if (promoDialogLayout.P.h != 0) {
            dualControlLayout.addView(DualControlLayout.a(promoDialogLayout.getContext(), false, promoDialogLayout.getResources().getString(promoDialogLayout.P.h), null));
        }
        getWindow().setLayout(-1, -1);
        for (int i2 : F) {
            View findViewById = findViewById(i2);
            if (findViewById != null) {
                findViewById.setOnClickListener(this);
            }
        }
    }
}
