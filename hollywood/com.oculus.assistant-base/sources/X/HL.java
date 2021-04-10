package X;

public final class HL {
    public static final C0913og A00 = ((C0913og) A05.A01("config/"));
    public static final C0913og A01;
    public static final C0913og A02;
    public static final C0913og A03;
    public static final C0913og A04 = ((C0913og) A05.A01("prefs_user_id"));
    public static final C0913og A05;
    public static final C0913og A06 = ((C0913og) A05.A01("settings/"));
    public static final C0913og A07 = ((C0913og) A05.A01("shared/"));
    public static final C0913og A08 = ((C0913og) A05.A01("shortcut/"));
    public static final ZW A09;
    public static final ZW A0A;
    public static final ZW A0B;
    public static final ZW A0C;
    public static final ZW A0D;
    public static final ZW A0E;

    static {
        C0913og ogVar = new C0913og();
        A05 = ogVar;
        A03 = (C0913og) ogVar.A01("prefs/");
        ZW zw = new ZW();
        A0C = zw;
        A0B = new ZW(zw, "prefs/", zw.A00);
        ZW zw2 = A0C;
        A0D = new ZW(zw2, "settings/", zw2.A00);
        A0A = new ZW(zw2, "config/", zw2.A00);
        A0E = new ZW(zw2, "shared/", zw2.A00);
        C0913og ogVar2 = A05;
        A01 = (C0913og) ogVar2.A01("dash/");
        A02 = (C0913og) ogVar2.A01("fb_android/");
        A09 = new ZW(zw2, "fb_android/", zw2.A00);
    }
}
