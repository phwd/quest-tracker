package defpackage;

/* renamed from: RT0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RT0 implements WT {

    /* renamed from: a  reason: collision with root package name */
    public final String f8834a;

    public RT0(String str) {
        this.f8834a = str;
    }

    @Override // defpackage.WT
    public Object apply(Object obj) {
        String str = this.f8834a;
        String str2 = (String) obj;
        return Boolean.valueOf(str2 == null || str == null || !str2.endsWith(str));
    }
}
