package X;

import android.content.SharedPreferences;

public final class HH {
    public static final ZW A02;
    public static final ZW A03;
    public static final ZW A04;
    public final boolean A00 = true;
    public final SharedPreferences A01;

    public HH(SharedPreferences sharedPreferences) {
        this.A01 = sharedPreferences;
    }

    static {
        ZW zw = HL.A0C;
        ZW zw2 = new ZW(zw, "papaya/", zw.A00);
        A03 = zw2;
        A02 = new ZW(zw2, "client_id_key", zw2.A00);
        ZW zw3 = A03;
        A04 = new ZW(zw3, "store_path", zw3.A00);
    }
}
