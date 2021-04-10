package X;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.infer.annotation.ReturnsOwnership;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1lf  reason: invalid class name and case insensitive filesystem */
public class C10231lf extends C10241lg<AnonymousClass1lX> {
    @Nullable
    public static Drawable A01(Context context, TypedArray typedArray, int i) {
        int resourceId = typedArray.getResourceId(i, 0);
        if (resourceId == 0) {
            return null;
        }
        return context.getResources().getDrawable(resourceId);
    }

    @Nullable
    public static AnonymousClass2eu A02(TypedArray typedArray, int i) {
        switch (typedArray.getInt(i, -2)) {
            case -1:
                return null;
            case 0:
                return AnonymousClass2eu.A08;
            case 1:
                return AnonymousClass2eu.A06;
            case 2:
                return AnonymousClass2eu.A04;
            case 3:
                return AnonymousClass2eu.A05;
            case 4:
                return AnonymousClass2eu.A00;
            case 5:
                return AnonymousClass2eu.A02;
            case 6:
                return AnonymousClass2eu.A01;
            case 7:
                return AnonymousClass2eu.A0A;
            case 8:
                return AnonymousClass2eu.A03;
            default:
                throw new RuntimeException("XML attribute not specified!");
        }
    }

    @ReturnsOwnership
    public static AnonymousClass1lm A03(C10271lj r1) {
        AnonymousClass1lm r0 = r1.A0E;
        if (r0 != null) {
            return r0;
        }
        AnonymousClass1lm r02 = new AnonymousClass1lm();
        r1.A0E = r02;
        return r02;
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x01eb, code lost:
        if (r6 == false) goto L_0x01ed;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x01f5, code lost:
        if (r5 == false) goto L_0x01f7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x01fb, code lost:
        if (r3 == false) goto L_0x01fd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x0204, code lost:
        if (r7 == false) goto L_0x0206;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:133:0x020e, code lost:
        if (r3 == false) goto L_0x0210;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:0x0214, code lost:
        if (r5 == false) goto L_0x01fd;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void A04(android.content.Context r23, @javax.annotation.Nullable android.util.AttributeSet r24) {
        /*
        // Method dump skipped, instructions count: 684
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C10231lf.A04(android.content.Context, android.util.AttributeSet):void");
    }

    public C10231lf(Context context) {
        super(context);
        A04(context, null);
    }

    public C10231lf(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        A04(context, attributeSet);
    }

    public C10231lf(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        A04(context, attributeSet);
    }

    @TargetApi(21)
    public C10231lf(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        A04(context, attributeSet);
    }

    public C10231lf(Context context, AnonymousClass1lX r2) {
        super(context);
        setHierarchy(r2);
    }
}
