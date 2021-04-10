package defpackage;

import org.chromium.chrome.browser.compositor.CompositorViewHolder;

/* renamed from: Lt  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C0717Lt implements Q31 {
    public final AbstractActivityC2601fu F;

    public C0717Lt(AbstractActivityC2601fu fuVar) {
        this.F = fuVar;
    }

    @Override // defpackage.Q31
    public Object get() {
        CompositorViewHolder compositorViewHolder = this.F.I0;
        if (compositorViewHolder == null) {
            return null;
        }
        return compositorViewHolder.L;
    }
}
