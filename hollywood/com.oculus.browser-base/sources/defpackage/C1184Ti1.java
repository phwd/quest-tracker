package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.oculus.browser.R;
import java.util.Objects;
import org.chromium.base.SysUtils;

/* renamed from: Ti1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1184Ti1 {

    /* renamed from: a  reason: collision with root package name */
    public static int f8977a;
    public Toast b;
    public ViewGroup c;

    public C1184Ti1(Context context, View view) {
        if (SysUtils.isLowEndDevice()) {
            this.c = new FrameLayout(new C1123Si1(this, context));
        }
        if (C2588fp1.f9958a == null) {
            C2588fp1.f9958a = new C2588fp1();
        }
        Objects.requireNonNull(C2588fp1.f9958a);
        Toast toast = new Toast(context);
        this.b = toast;
        ViewGroup viewGroup = this.c;
        if (viewGroup != null) {
            viewGroup.removeAllViews();
            this.c.addView(view, -2, -2);
            this.b.setView(this.c);
        } else {
            toast.setView(view);
        }
        Toast toast2 = this.b;
        toast2.setGravity(toast2.getGravity(), this.b.getXOffset(), this.b.getYOffset() + f8977a);
    }

    public static C1184Ti1 a(Context context, int i, int i2) {
        return b(context, context.getResources().getText(i), i2);
    }

    public static C1184Ti1 b(Context context, CharSequence charSequence, int i) {
        TextView textView = (TextView) LayoutInflater.from(context).inflate(R.layout.f37700_resource_name_obfuscated_RES_2131624079, (ViewGroup) null);
        textView.setText(charSequence);
        textView.announceForAccessibility(charSequence);
        C1184Ti1 ti1 = new C1184Ti1(context, textView);
        ti1.b.setDuration(i);
        return ti1;
    }

    public static boolean c(Context context, View view, CharSequence charSequence) {
        int i;
        int i2;
        if (charSequence == null) {
            return false;
        }
        int i3 = context.getResources().getDisplayMetrics().widthPixels;
        int i4 = context.getResources().getDisplayMetrics().heightPixels;
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        int width = view.getWidth();
        int height = view.getHeight();
        int i5 = i3 / 2;
        int i6 = iArr[0] < i5 ? 3 : 5;
        if (iArr[0] < i5) {
            i = (width / 2) + iArr[0];
        } else {
            i = (i3 - iArr[0]) - (width / 2);
        }
        if (iArr[1] < i4 / 2) {
            i2 = (height / 2) + iArr[1];
        } else {
            i2 = iArr[1] - ((height * 3) / 2);
        }
        C1184Ti1 b2 = b(context, charSequence, 0);
        b2.b.setGravity(i6 | 48, i, i2);
        b2.b.show();
        return true;
    }
}
