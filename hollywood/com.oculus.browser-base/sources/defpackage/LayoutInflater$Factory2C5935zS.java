package defpackage;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

/* renamed from: zS  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class LayoutInflater$Factory2C5935zS implements LayoutInflater.Factory2 {
    public final KS F;

    public LayoutInflater$Factory2C5935zS(KS ks) {
        this.F = ks;
    }

    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }

    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        boolean z;
        if (C5425wS.class.getName().equals(str)) {
            return new C5425wS(context, attributeSet, this.F);
        }
        AbstractComponentCallbacksC3550lS lSVar = null;
        if (!"fragment".equals(str)) {
            return null;
        }
        String attributeValue = attributeSet.getAttributeValue(null, "class");
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, FJ0.Q);
        int i = 0;
        if (attributeValue == null) {
            attributeValue = obtainStyledAttributes.getString(0);
        }
        int resourceId = obtainStyledAttributes.getResourceId(1, -1);
        String string = obtainStyledAttributes.getString(2);
        obtainStyledAttributes.recycle();
        if (attributeValue != null) {
            ClassLoader classLoader = context.getClassLoader();
            BW0 bw0 = AbstractC5765yS.f11681a;
            try {
                z = AbstractComponentCallbacksC3550lS.class.isAssignableFrom(AbstractC5765yS.b(classLoader, attributeValue));
            } catch (ClassNotFoundException unused) {
                z = false;
            }
            if (z) {
                if (view != null) {
                    i = view.getId();
                }
                if (i == -1 && resourceId == -1 && string == null) {
                    throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Must specify unique android:id, android:tag, or have a parent with an id for " + attributeValue);
                }
                if (resourceId != -1) {
                    lSVar = this.F.I(resourceId);
                }
                if (lSVar == null && string != null) {
                    lSVar = this.F.J(string);
                }
                if (lSVar == null && i != -1) {
                    lSVar = this.F.I(i);
                }
                if (KS.R(2)) {
                    StringBuilder i2 = AbstractC2531fV.i("onCreateView: id=0x");
                    i2.append(Integer.toHexString(resourceId));
                    i2.append(" fname=");
                    i2.append(attributeValue);
                    i2.append(" existing=");
                    i2.append(lSVar);
                    i2.toString();
                }
                if (lSVar == null) {
                    lSVar = this.F.P().a(context.getClassLoader(), attributeValue);
                    lSVar.R = true;
                    lSVar.a0 = resourceId != 0 ? resourceId : i;
                    lSVar.b0 = i;
                    lSVar.c0 = string;
                    lSVar.S = true;
                    KS ks = this.F;
                    lSVar.W = ks;
                    C3721mS mSVar = ks.n;
                    lSVar.X = mSVar;
                    Context context2 = mSVar.G;
                    lSVar.s0(attributeSet, lSVar.H);
                    this.F.b(lSVar);
                    KS ks2 = this.F;
                    ks2.a0(lSVar, ks2.m);
                } else if (!lSVar.S) {
                    lSVar.S = true;
                    C3721mS mSVar2 = this.F.n;
                    lSVar.X = mSVar2;
                    Context context3 = mSVar2.G;
                    lSVar.s0(attributeSet, lSVar.H);
                } else {
                    throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(resourceId) + ", tag " + string + ", or parent id 0x" + Integer.toHexString(i) + " with another fragment for " + attributeValue);
                }
                KS ks3 = this.F;
                int i3 = ks3.m;
                if (i3 >= 1 || !lSVar.R) {
                    ks3.a0(lSVar, i3);
                } else {
                    ks3.a0(lSVar, 1);
                }
                View view2 = lSVar.k0;
                if (view2 != null) {
                    if (resourceId != 0) {
                        view2.setId(resourceId);
                    }
                    if (lSVar.k0.getTag() == null) {
                        lSVar.k0.setTag(string);
                    }
                    return lSVar.k0;
                }
                throw new IllegalStateException(AbstractC2531fV.g("Fragment ", attributeValue, " did not create a view."));
            }
        }
        return null;
    }
}
