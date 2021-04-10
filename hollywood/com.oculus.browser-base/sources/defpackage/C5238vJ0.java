package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.oculus.browser.R;

/* renamed from: vJ0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5238vJ0 {

    /* renamed from: a  reason: collision with root package name */
    public final Context f11474a;
    public final View b;
    public boolean c;
    public boolean d;
    public boolean e;

    public C5238vJ0(Context context, View.OnClickListener onClickListener) {
        this.f11474a = context;
        View inflate = LayoutInflater.from(context).inflate(R.layout.f41030_resource_name_obfuscated_RES_2131624412, (ViewGroup) null, false);
        this.b = inflate;
        ((Button) inflate.findViewById(R.id.download)).setOnClickListener(onClickListener);
        ((Button) inflate.findViewById(R.id.settings)).setOnClickListener(new View$OnClickListenerC5068uJ0(this));
        a();
    }

    public final void a() {
        if (this.e) {
            Button button = (Button) this.b.findViewById(R.id.download);
            Button button2 = (Button) this.b.findViewById(R.id.settings);
            if (this.c) {
                button.setVisibility(0);
                button2.setVisibility(8);
            } else if (this.d) {
                button.setVisibility(0);
                button2.setVisibility(8);
            } else {
                button.setVisibility(8);
                button2.setVisibility(0);
            }
        }
    }
}
