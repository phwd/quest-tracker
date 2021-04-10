package defpackage;

import android.media.VolumeProvider;

/* renamed from: Zv1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Zv1 extends VolumeProvider {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C1238Ug0 f9385a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Zv1(C1238Ug0 ug0, int i, int i2, int i3) {
        super(i, i2, i3);
        this.f9385a = ug0;
    }

    public void onAdjustVolume(int i) {
        C1238Ug0 ug0 = this.f9385a;
        ug0.g.c.k.post(new RunnableC1177Tg0(ug0, i));
    }

    public void onSetVolumeTo(int i) {
        C1238Ug0 ug0 = this.f9385a;
        ug0.g.c.k.post(new RunnableC1116Sg0(ug0, i));
    }
}
