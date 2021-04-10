package defpackage;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MenuItem;
import android.widget.PopupWindow;
import java.lang.reflect.Method;

/* renamed from: aj0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1706aj0 extends AbstractC4186p90 implements AbstractC0696Li0 {
    public static Method h0;
    public AbstractC0696Li0 i0;

    static {
        try {
            if (Build.VERSION.SDK_INT <= 28) {
                h0 = PopupWindow.class.getDeclaredMethod("setTouchModal", Boolean.TYPE);
            }
        } catch (NoSuchMethodException unused) {
            Log.i("MenuPopupWindow", "Could not find method setTouchModal() on PopupWindow. Oh well.");
        }
    }

    public C1706aj0(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, null, i, i2);
    }

    @Override // defpackage.AbstractC0696Li0
    public void a(C4616ri0 ri0, MenuItem menuItem) {
        AbstractC0696Li0 li0 = this.i0;
        if (li0 != null) {
            li0.a(ri0, menuItem);
        }
    }

    @Override // defpackage.AbstractC0696Li0
    public void n(C4616ri0 ri0, MenuItem menuItem) {
        AbstractC0696Li0 li0 = this.i0;
        if (li0 != null) {
            li0.n(ri0, menuItem);
        }
    }

    @Override // defpackage.AbstractC4186p90
    public C1823bJ q(Context context, boolean z) {
        C1549Zi0 zi0 = new C1549Zi0(context, z);
        zi0.U = this;
        return zi0;
    }
}
