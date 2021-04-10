package defpackage;

import android.content.Context;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import com.oculus.browser.R;

/* renamed from: Ml1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Ml1 {

    /* renamed from: a  reason: collision with root package name */
    public final Context f8501a;
    public final View b;
    public final TextView c;
    public final WindowManager.LayoutParams d;
    public final Rect e = new Rect();
    public final int[] f = new int[2];
    public final int[] g = new int[2];

    public Ml1(Context context) {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        this.d = layoutParams;
        this.f8501a = context;
        View inflate = LayoutInflater.from(context).inflate(R.layout.f36540_resource_name_obfuscated_RES_2131623963, (ViewGroup) null);
        this.b = inflate;
        this.c = (TextView) inflate.findViewById(R.id.message);
        layoutParams.setTitle(Ml1.class.getSimpleName());
        layoutParams.packageName = context.getPackageName();
        layoutParams.type = 1002;
        layoutParams.width = -2;
        layoutParams.height = -2;
        layoutParams.format = -3;
        layoutParams.windowAnimations = R.style.f65870_resource_name_obfuscated_RES_2132017160;
        layoutParams.flags = 24;
    }

    public void a() {
        if (this.b.getParent() != null) {
            ((WindowManager) this.f8501a.getSystemService("window")).removeView(this.b);
        }
    }
}
