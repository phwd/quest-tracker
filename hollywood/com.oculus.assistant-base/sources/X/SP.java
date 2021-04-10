package X;

import com.google.android.gms.common.Feature;

public final class SP {
    public static final Feature A00;
    public static final Feature[] A01;

    static {
        Feature feature = new Feature();
        A00 = feature;
        A01 = new Feature[]{feature};
    }
}
