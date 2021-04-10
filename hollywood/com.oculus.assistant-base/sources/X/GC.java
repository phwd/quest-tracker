package X;

public final class GC implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.mobileconfig.initlight.MobileConfigInitUtils$1";
    public final /* synthetic */ EnumC0164Fp A00;
    public final /* synthetic */ GD A01;
    public final /* synthetic */ String A02;

    public GC(GD gd, String str, EnumC0164Fp fp) {
        this.A01 = gd;
        this.A02 = str;
        this.A00 = fp;
    }

    public final void run() {
        this.A01.A01(this.A02, this.A00);
    }
}
