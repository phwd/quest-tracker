package defpackage;

/* renamed from: yU0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5770yU0 extends OK {
    public final /* synthetic */ View$OnLayoutChangeListenerC5940zU0 F;

    public C5770yU0(View$OnLayoutChangeListenerC5940zU0 zu0) {
        this.F = zu0;
    }

    @Override // defpackage.AbstractC0576Jj, defpackage.OK
    public void h(AbstractC4277pj pjVar) {
        View$OnLayoutChangeListenerC5940zU0 zu0 = this.F;
        C5090uU0 uu0 = zu0.U;
        if (pjVar == uu0) {
            uu0.I.addOnLayoutChangeListener(new View$OnLayoutChangeListenerC5430wU0(zu0));
        } else {
            uu0.I.removeOnLayoutChangeListener(new View$OnLayoutChangeListenerC5600xU0(zu0));
        }
    }
}
