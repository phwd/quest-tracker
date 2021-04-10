package defpackage;

import android.graphics.drawable.Drawable;
import com.oculus.browser.R;
import org.chromium.base.ContextUtils;

/* renamed from: oY0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4076oY0 {

    /* renamed from: a  reason: collision with root package name */
    public AbstractC4758sY0 f10557a;
    public CharSequence b;
    public String c;
    public String d;
    public Object e;
    public String f;
    public int g;
    public int h;
    public boolean i = true;
    public int j;
    public Drawable k;
    public int l;
    public int m = -1;
    public int n = 0;

    public static C4076oY0 c(CharSequence charSequence, AbstractC4758sY0 sy0, int i2, int i3) {
        C4076oY0 oy0 = new C4076oY0();
        oy0.b = charSequence;
        oy0.f10557a = sy0;
        oy0.l = i2;
        oy0.m = i3;
        if (i2 == 2) {
            oy0.d = ContextUtils.getApplicationContext().getResources().getString(R.string.f56550_resource_name_obfuscated_RES_2131952972);
        }
        return oy0;
    }

    public boolean a() {
        return this.l == 0;
    }

    public boolean b() {
        return this.l == 2;
    }
}
