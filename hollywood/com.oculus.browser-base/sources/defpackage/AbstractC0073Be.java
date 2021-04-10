package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.oculus.browser.R;
import java.util.Calendar;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.profiles.Profile;

/* renamed from: Be  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC0073Be {
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0020, code lost:
        if (r5 <= 12) goto L_0x0023;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int a(android.widget.EditText r8, android.widget.EditText r9, boolean r10, boolean r11) {
        /*
            java.util.Calendar r0 = java.util.Calendar.getInstance()
            r1 = 1
            int r2 = r0.get(r1)
            r3 = 2
            int r0 = r0.get(r3)
            int r0 = r0 + r1
            r4 = -1
            android.text.Editable r5 = r8.getText()     // Catch:{ NumberFormatException -> 0x0022 }
            java.lang.String r5 = r5.toString()     // Catch:{ NumberFormatException -> 0x0022 }
            int r5 = java.lang.Integer.parseInt(r5)     // Catch:{ NumberFormatException -> 0x0022 }
            if (r5 < r1) goto L_0x0022
            r6 = 12
            if (r5 <= r6) goto L_0x0023
        L_0x0022:
            r5 = r4
        L_0x0023:
            r6 = 6
            if (r5 != r4) goto L_0x003d
            android.text.Editable r7 = r8.getText()
            int r7 = r7.length()
            if (r7 == r3) goto L_0x003c
            boolean r8 = r8.isFocused()
            if (r8 != 0) goto L_0x0039
            if (r10 == 0) goto L_0x0039
            goto L_0x003c
        L_0x0039:
            if (r11 != 0) goto L_0x003d
            return r6
        L_0x003c:
            return r1
        L_0x003d:
            int r8 = b(r9)
            if (r8 != r4) goto L_0x0058
            android.text.Editable r8 = r9.getText()
            int r8 = r8.length()
            if (r8 == r3) goto L_0x0057
            boolean r8 = r9.isFocused()
            if (r8 != 0) goto L_0x0056
            if (r11 == 0) goto L_0x0056
            goto L_0x0057
        L_0x0056:
            return r6
        L_0x0057:
            return r3
        L_0x0058:
            if (r5 != r4) goto L_0x005b
            return r6
        L_0x005b:
            if (r8 != r2) goto L_0x0061
            if (r5 >= r0) goto L_0x0061
            r8 = 3
            return r8
        L_0x0061:
            r8 = 7
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.AbstractC0073Be.a(android.widget.EditText, android.widget.EditText, boolean, boolean):int");
    }

    public static int b(EditText editText) {
        int i = Calendar.getInstance().get(1);
        try {
            int parseInt = Integer.parseInt(editText.getText().toString());
            if (parseInt < 0) {
                return -1;
            }
            if (parseInt < 100) {
                parseInt += i - (i % 100);
            }
            if (parseInt < i || parseInt > i + 10) {
                return -1;
            }
            return parseInt;
        } catch (NumberFormatException unused) {
        }
    }

    public static void c(Activity activity, Profile profile) {
        C2535fX.a().b(activity, activity.getString(R.string.f52460_resource_name_obfuscated_RES_2131952563), profile, null);
    }

    public static void d(int i, Context context, TextView textView) {
        String str;
        if (i == 6 || i == 7) {
            textView.setText((CharSequence) null);
            textView.setVisibility(8);
            return;
        }
        Resources resources = context.getResources();
        if (i == 1) {
            str = resources.getString(R.string.f47120_resource_name_obfuscated_RES_2131952029);
        } else if (i == 2) {
            str = resources.getString(R.string.f47130_resource_name_obfuscated_RES_2131952030);
        } else if (i == 3) {
            str = resources.getString(R.string.f47110_resource_name_obfuscated_RES_2131952028);
        } else if (i == 4) {
            str = resources.getString(R.string.f47090_resource_name_obfuscated_RES_2131952026);
        } else if (i != 5) {
            str = "";
        } else {
            str = resources.getString(R.string.f47100_resource_name_obfuscated_RES_2131952027);
        }
        textView.setText(str);
        textView.setVisibility(0);
        textView.announceForAccessibility(str);
    }

    public static void e(Context context, PopupWindow popupWindow, int i, AbstractC0012Ae ae, View view, Runnable runnable) {
        TextView textView = new TextView(context);
        textView.setText(i);
        textView.setTextAppearance(R.style.f72000_resource_name_obfuscated_RES_2132017773);
        Resources resources = context.getResources();
        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.f16710_resource_name_obfuscated_RES_2131165290);
        int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.f16720_resource_name_obfuscated_RES_2131165291);
        textView.setPadding(dimensionPixelSize, dimensionPixelSize2, dimensionPixelSize, dimensionPixelSize2);
        textView.measure(0, 0);
        popupWindow.setContentView(textView);
        popupWindow.setHeight(-2);
        popupWindow.setWidth(-2);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(AbstractC3153j7.c(resources, R.drawable.f35070_resource_name_obfuscated_RES_2131231547));
        ComponentCallbacksC5963ze zeVar = new ComponentCallbacksC5963ze(popupWindow);
        ContextUtils.getApplicationContext().registerComponentCallbacks(zeVar);
        popupWindow.setOnDismissListener(new C5793ye(runnable, zeVar));
        popupWindow.showAsDropDown(view, ae.b(textView), ae.a(textView));
        textView.announceForAccessibility(textView.getText());
    }

    public static void f(int i, Context context, EditText editText, EditText editText2, EditText editText3) {
        PorterDuffColorFilter porterDuffColorFilter = new PorterDuffColorFilter(context.getResources().getColor(R.color.f12860_resource_name_obfuscated_RES_2131099976), PorterDuff.Mode.SRC_IN);
        boolean z = false;
        boolean z2 = i == 1 || i == 3 || i == 5;
        boolean z3 = i == 2 || i == 3 || i == 5;
        editText.getBackground().mutate().setColorFilter(z2 ? porterDuffColorFilter : null);
        editText2.getBackground().mutate().setColorFilter(z3 ? porterDuffColorFilter : null);
        if (editText3 != null) {
            if (i == 4 || i == 5) {
                z = true;
            }
            if (!z) {
                porterDuffColorFilter = null;
            }
            editText3.getBackground().mutate().setColorFilter(porterDuffColorFilter);
        }
    }
}
