package defpackage;

/* renamed from: tm  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4967tm implements AbstractC4559rK {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C5647xm f11369a;

    public C4967tm(C5647xm xmVar) {
        this.f11369a = xmVar;
    }

    @Override // defpackage.AbstractC4559rK
    public boolean a(CharSequence charSequence) {
        CharSequence charSequence2 = this.f11369a.s.s;
        if (!(charSequence == null || charSequence2 == null)) {
            int parseInt = Integer.parseInt(charSequence.toString());
            int parseInt2 = Integer.parseInt(charSequence2.toString());
            C5647xm xmVar = this.f11369a;
            int i = xmVar.z;
            if (parseInt2 > i || (parseInt2 == i && parseInt >= xmVar.y)) {
                return true;
            }
        }
        return false;
    }

    @Override // defpackage.AbstractC4559rK
    public boolean b(CharSequence charSequence) {
        return false;
    }
}
