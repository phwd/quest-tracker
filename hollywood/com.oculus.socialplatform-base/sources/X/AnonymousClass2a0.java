package X;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import org.xmlpull.v1.XmlPullParser;

/* renamed from: X.2a0  reason: invalid class name */
public class AnonymousClass2a0 {
    public float A00 = Float.NaN;
    public float A01 = Float.NaN;
    public float A02 = Float.NaN;
    public float A03 = Float.NaN;
    public int A04 = -1;
    public AnonymousClass2a4 A05;

    public final boolean A00(float f, float f2) {
        float f3 = this.A03;
        if (!Float.isNaN(f3) && f < f3) {
            return false;
        }
        float f4 = this.A02;
        if (!Float.isNaN(f4) && f2 < f4) {
            return false;
        }
        float f5 = this.A01;
        if (!Float.isNaN(f5) && f > f5) {
            return false;
        }
        float f6 = this.A00;
        if (Float.isNaN(f6) || f2 <= f6) {
            return true;
        }
        return false;
    }

    public AnonymousClass2a0(Context context, XmlPullParser xmlPullParser) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), AnonymousClass2aI.A08);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = obtainStyledAttributes.getIndex(i);
            if (index == 0) {
                this.A04 = obtainStyledAttributes.getResourceId(index, this.A04);
                String resourceTypeName = context.getResources().getResourceTypeName(this.A04);
                context.getResources().getResourceName(this.A04);
                if ("layout".equals(resourceTypeName)) {
                    AnonymousClass2a4 r6 = new AnonymousClass2a4();
                    this.A05 = r6;
                    r6.A0A((ConstraintLayout) LayoutInflater.from(context).inflate(this.A04, (ViewGroup) null));
                }
            } else if (index == 1) {
                this.A00 = obtainStyledAttributes.getDimension(index, this.A00);
            } else if (index == 2) {
                this.A02 = obtainStyledAttributes.getDimension(index, this.A02);
            } else if (index == 3) {
                this.A01 = obtainStyledAttributes.getDimension(index, this.A01);
            } else if (index == 4) {
                this.A03 = obtainStyledAttributes.getDimension(index, this.A03);
            }
        }
        obtainStyledAttributes.recycle();
    }
}
