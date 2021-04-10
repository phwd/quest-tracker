package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.oculus.browser.R;

/* renamed from: TR0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class TR0 extends VR0 {
    public final int O = getResources().getInteger(R.integer.f35900_resource_name_obfuscated_RES_2131492887);
    public final int P = getResources().getInteger(R.integer.f35920_resource_name_obfuscated_RES_2131492889);
    public final L6 Q = L6.a(getContext(), R.drawable.f29740_resource_name_obfuscated_RES_2131231014);
    public LinearLayout R;
    public ImageView S;
    public C4353q8 T;
    public TextView U;
    public TextView V;
    public ColorStateList W;
    public Drawable a0;

    public TR0(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Context context2 = getContext();
        ThreadLocal threadLocal = AbstractC5544x8.f11592a;
        this.W = context2.getColorStateList(R.color.f11310_resource_name_obfuscated_RES_2131099821);
    }

    @Override // defpackage.VR0
    public void j(boolean z) {
        if (this.S != null) {
            if (isChecked()) {
                this.S.getBackground().setLevel(this.P);
                this.S.setImageDrawable(this.Q);
                this.S.setImageTintList(this.W);
                if (z) {
                    this.Q.start();
                    return;
                }
                return;
            }
            this.S.getBackground().setLevel(this.O);
            this.S.setImageDrawable(this.a0);
            this.S.setImageTintList(k());
        }
    }

    public ColorStateList k() {
        return null;
    }

    public void l(Drawable drawable) {
        this.a0 = drawable;
        j(false);
    }

    @Override // defpackage.VR0
    public void onFinishInflate() {
        super.onFinishInflate();
        LayoutInflater.from(getContext()).inflate(R.layout.f39320_resource_name_obfuscated_RES_2131624241, this);
        this.R = (LinearLayout) findViewById(R.id.content);
        this.S = (ImageView) findViewById(R.id.start_icon);
        this.T = (C4353q8) findViewById(R.id.end_button);
        this.U = (TextView) findViewById(R.id.title);
        this.V = (TextView) findViewById(R.id.description);
        ImageView imageView = this.S;
        if (imageView != null) {
            imageView.setBackgroundResource(R.drawable.f33510_resource_name_obfuscated_RES_2131231391);
            this.S.setImageTintList(k());
        }
    }
}
