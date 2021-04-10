package defpackage;

/* renamed from: eL  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2340eL extends AbstractC5856yz {

    /* renamed from: a  reason: collision with root package name */
    public final PU0 f9848a;
    public final boolean b;
    public final boolean c = AbstractC5686xz.c(11);

    public C2340eL() {
        PU0 pu0 = NU0.f8549a;
        this.f9848a = pu0;
        boolean z = false;
        boolean z2 = pu0.f("contextual_search_entity_impressions_count", 0) > 0;
        boolean z3 = pu0.f("contextual_search_entity_opens_count", 0) > 0;
        boolean z4 = pu0.f("contextual_search_quick_action_impressions_count", 0) > 0;
        boolean z5 = pu0.f("contextual_search_quick_actions_taken_count", 0) > 0;
        boolean z6 = pu0.f("contextual_search_quick_actions_ignored_count", 0) > 0;
        if ((z2 && !z3) || (z4 && !z5 && z6)) {
            z = true;
        }
        this.b = z;
    }

    @Override // defpackage.AbstractC5856yz
    public boolean a() {
        return this.b && this.c;
    }

    @Override // defpackage.AbstractC5856yz
    public void e(AbstractC0486Hz hz) {
        C4017oA oAVar = (C4017oA) hz;
        oAVar.b(24, Integer.valueOf(this.f9848a.f("contextual_search_all_time_tap_count", 0)));
        oAVar.b(25, Integer.valueOf(this.f9848a.f("contextual_search_all_time_open_count", 0)));
        oAVar.b(26, Integer.valueOf(this.f9848a.f("contextual_search_all_time_tap_quick_answer_count", 0)));
        oAVar.b(27, Integer.valueOf(this.f9848a.f("contextual_search_entity_impressions_count", 0)));
        oAVar.b(28, Integer.valueOf(this.f9848a.f("contextual_search_entity_opens_count", 0)));
        oAVar.b(29, Integer.valueOf(this.f9848a.f("contextual_search_quick_action_impressions_count", 0)));
        oAVar.b(30, Integer.valueOf(this.f9848a.f("contextual_search_quick_actions_taken_count", 0)));
        oAVar.b(31, Integer.valueOf(this.f9848a.f("contextual_search_quick_actions_ignored_count", 0)));
    }
}
