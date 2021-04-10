package X;

/* renamed from: X.Vv  reason: case insensitive filesystem */
public class C0211Vv extends KI {
    public final /* synthetic */ String A00;
    public final /* synthetic */ String A01;

    public C0211Vv(String str, String str2) {
        this.A00 = str;
        this.A01 = str2;
    }

    @Override // X.KI
    public final String A00(String str) {
        return AnonymousClass06.A05(this.A00, str, this.A01);
    }

    public final String toString() {
        return AnonymousClass06.A07("[PreAndSuffixTransformer('", this.A00, "','", this.A01, "')]");
    }
}
