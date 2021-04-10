package defpackage;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import com.oculus.browser.R;

/* renamed from: nd0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC3918nd0 {

    /* renamed from: a  reason: collision with root package name */
    public static final int[] f10504a = {16842752, R.attr.f8770_resource_name_obfuscated_RES_2130969323};
    public static final int[] b = {R.attr.f6240_resource_name_obfuscated_RES_2130969070};

    public static Context a(Context context, AttributeSet attributeSet, int i, int i2) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, b, i, i2);
        int resourceId = obtainStyledAttributes.getResourceId(0, 0);
        obtainStyledAttributes.recycle();
        boolean z = (context instanceof C3812mz) && ((C3812mz) context).f10464a == resourceId;
        if (resourceId == 0 || z) {
            return context;
        }
        C3812mz mzVar = new C3812mz(context, resourceId);
        TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, f10504a);
        int resourceId2 = obtainStyledAttributes2.getResourceId(0, 0);
        int resourceId3 = obtainStyledAttributes2.getResourceId(1, 0);
        obtainStyledAttributes2.recycle();
        if (resourceId2 == 0) {
            resourceId2 = resourceId3;
        }
        if (resourceId2 != 0) {
            mzVar.getTheme().applyStyle(resourceId2, true);
        }
        return mzVar;
    }
}
