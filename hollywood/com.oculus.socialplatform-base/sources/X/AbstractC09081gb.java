package X;

/* renamed from: X.1gb  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC09081gb {
    public static final C07491cP<AbstractC09081gb> A00;
    public static final AbstractC09081gb A01 = new C09151gn();
    public static final AbstractC09081gb A02 = new C09111ge();
    public static final AbstractC09081gb A03 = new AnonymousClass1gi();
    public static final AbstractC09081gb A04;
    public static final AbstractC09081gb A05;
    public static final AbstractC09081gb A06 = new AnonymousClass1gx();
    public static final AbstractC09081gb A07 = new AnonymousClass1h9();

    static {
        AnonymousClass1gw r1 = new AnonymousClass1gw();
        A04 = r1;
        A05 = r1;
        A00 = C07491cP.A00("com.bumptech.glide.load.resource.bitmap.Downsampler.DownsampleStrategy", r1);
    }

    public final float A00(int i, int i2, int i3, int i4) {
        int i5;
        float f;
        if (this instanceof AnonymousClass1h9) {
            return 1.0f;
        }
        if (this instanceof AnonymousClass1gx) {
            return Math.min(((float) i3) / ((float) i), ((float) i4) / ((float) i2));
        }
        if (this instanceof AnonymousClass1gw) {
            return Math.max(((float) i3) / ((float) i), ((float) i4) / ((float) i2));
        }
        if (this instanceof AnonymousClass1gi) {
            return Math.min(1.0f, A06.A00(i, i2, i3, i4));
        }
        if (!(this instanceof C09111ge)) {
            int min = Math.min(i2 / i4, i / i3);
            f = 1.0f;
            if (min == 0) {
                return 1.0f;
            }
            i5 = Integer.highestOneBit(min);
        } else {
            int ceil = (int) Math.ceil((double) Math.max(((float) i2) / ((float) i4), ((float) i) / ((float) i3)));
            int i6 = 1;
            int max = Math.max(1, Integer.highestOneBit(ceil));
            if (max >= ceil) {
                i6 = 0;
            }
            i5 = max << i6;
            f = 1.0f;
        }
        return f / ((float) i5);
    }

    public final Integer A01(int i, int i2, int i3, int i4) {
        if (!(this instanceof AnonymousClass1h9) && !(this instanceof AnonymousClass1gx) && !(this instanceof AnonymousClass1gw)) {
            if (!(this instanceof AnonymousClass1gi)) {
                if (this instanceof C09111ge) {
                    return AnonymousClass007.A00;
                }
            } else if (A00(i, i2, i3, i4) != 1.0f) {
                return A06.A01(i, i2, i3, i4);
            }
        }
        return AnonymousClass007.A01;
    }
}
