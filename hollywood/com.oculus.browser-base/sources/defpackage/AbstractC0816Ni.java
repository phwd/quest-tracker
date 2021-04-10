package defpackage;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import com.oculus.browser.R;
import java.util.List;
import org.chromium.components.browser_ui.widget.listmenu.ListMenuButton;

/* renamed from: Ni  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC0816Ni extends TR0 {
    public ListMenuButton b0;
    public ImageView c0;

    public AbstractC0816Ni(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // defpackage.AbstractC3039iS0, defpackage.VR0
    public void b(List list) {
        setChecked(this.I.c(this.f9084J));
    }

    @Override // defpackage.VR0
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override // defpackage.VR0
    public void onClick(View view) {
        throw null;
    }

    @Override // defpackage.VR0
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.b0.d();
        this.b0.N.c(null);
    }

    @Override // defpackage.TR0, defpackage.VR0
    public void onFinishInflate() {
        super.onFinishInflate();
        LayoutInflater.from(getContext()).inflate(R.layout.f39120_resource_name_obfuscated_RES_2131624221, this.R);
        ListMenuButton listMenuButton = (ListMenuButton) findViewById(R.id.more);
        this.b0 = listMenuButton;
        C0755Mi mi = new C0755Mi(this);
        listMenuButton.d();
        listMenuButton.M = mi;
        C4353q8 q8Var = this.T;
        this.c0 = q8Var;
        q8Var.setImageResource(R.drawable.f29960_resource_name_obfuscated_RES_2131231036);
        ImageView imageView = this.c0;
        Context context = getContext();
        ThreadLocal threadLocal = AbstractC5544x8.f11592a;
        imageView.setImageTintList(context.getColorStateList(R.color.f11390_resource_name_obfuscated_RES_2131099829));
    }

    @Override // defpackage.VR0
    public boolean onLongClick(View view) {
        throw null;
    }
}
