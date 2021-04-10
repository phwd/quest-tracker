package defpackage;

/* renamed from: HF0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class HF0 {

    /* renamed from: a  reason: collision with root package name */
    public final int f8147a;
    public final C2020cV0 b;

    public HF0(int i, C2020cV0 cv0) {
        this.f8147a = i;
        this.b = cv0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof HF0)) {
            return false;
        }
        HF0 hf0 = (HF0) obj;
        if (this.f8147a != hf0.f8147a || !this.b.equals(hf0.b)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i = (527 + this.f8147a) * 31;
        C2020cV0 cv0 = this.b;
        return i + (cv0 == null ? 0 : cv0.hashCode());
    }
}
