package defpackage;

/* renamed from: K31  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class K31 {

    /* renamed from: a  reason: collision with root package name */
    public int f8339a;
    public int b;

    public K31(int i) {
        this.f8339a = i;
    }

    public void a(boolean z) {
        int i = this.f8339a - 1;
        this.f8339a = i;
        if (z) {
            this.b++;
        }
        if (i == 0) {
            AbstractC3364kK0.g("NewTabPage.ContentSuggestions.CountOnNtpOpenedIfVisible.Articles.Prefetched.Offline2", this.b, 20);
        }
    }
}
