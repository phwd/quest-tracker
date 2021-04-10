package X;

import android.graphics.drawable.Drawable;
import androidx.annotation.VisibleForTesting;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.localmedia.MediaProviderUtils;
import java.util.Arrays;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1lW  reason: invalid class name */
public final class AnonymousClass1lW extends AnonymousClass0o5 {
    @VisibleForTesting
    public int A00;
    @VisibleForTesting
    public long A01;
    @VisibleForTesting
    public int A02;
    @VisibleForTesting
    public int A03;
    @VisibleForTesting
    public int A04;
    public boolean A05;
    @VisibleForTesting
    public int[] A06;
    @VisibleForTesting
    public int[] A07;
    @VisibleForTesting
    public boolean[] A08;
    public final int A09;
    public final Drawable[] A0A;

    public AnonymousClass1lW(Drawable[] drawableArr) {
        super(drawableArr);
        int length = drawableArr.length;
        C00740Ii.A05(length < 1 ? false : true, "At least one layer required!");
        this.A0A = drawableArr;
        int[] iArr = new int[length];
        this.A07 = iArr;
        this.A06 = new int[length];
        this.A00 = MediaProviderUtils.JPEG_HEADER;
        this.A08 = new boolean[length];
        this.A03 = 0;
        this.A09 = 2;
        this.A04 = 2;
        Arrays.fill(iArr, 0);
        this.A07[0] = 255;
        Arrays.fill(this.A06, 0);
        this.A06[0] = 255;
        Arrays.fill(this.A08, false);
        this.A08[0] = true;
    }

    private boolean A00(float f) {
        boolean z = true;
        for (int i = 0; i < this.A0A.length; i++) {
            boolean[] zArr = this.A08;
            int i2 = -1;
            if (zArr[i]) {
                i2 = 1;
            }
            int[] iArr = this.A06;
            int i3 = (int) (((float) this.A07[i]) + (((float) (i2 * MediaProviderUtils.JPEG_HEADER)) * f));
            iArr[i] = i3;
            if (i3 < 0) {
                iArr[i] = 0;
            }
            if (iArr[i] > 255) {
                iArr[i] = 255;
            }
            if (zArr[i] && iArr[i] < 255) {
                z = false;
            }
            if (!zArr[i] && iArr[i] > 0) {
                z = false;
            }
        }
        return z;
    }

    public final void A03() {
        this.A04 = 2;
        for (int i = 0; i < this.A0A.length; i++) {
            int[] iArr = this.A06;
            int i2 = 0;
            if (this.A08[i]) {
                i2 = MediaProviderUtils.JPEG_HEADER;
            }
            iArr[i] = i2;
        }
        invalidateSelf();
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0095  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x008b A[EDGE_INSN: B:34:0x008b->B:28:0x008b ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:6:0x000f  */
    @Override // X.AnonymousClass0o5
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void draw(android.graphics.Canvas r9) {
        /*
        // Method dump skipped, instructions count: 153
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1lW.draw(android.graphics.Canvas):void");
    }

    public final int getAlpha() {
        return this.A00;
    }

    public final void invalidateSelf() {
        if (this.A03 == 0) {
            super.invalidateSelf();
        }
    }

    @Override // X.AnonymousClass0o5
    public final void setAlpha(int i) {
        if (this.A00 != i) {
            this.A00 = i;
            invalidateSelf();
        }
    }
}
