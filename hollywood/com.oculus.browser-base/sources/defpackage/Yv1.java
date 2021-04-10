package defpackage;

import android.media.VolumeProvider;

/* renamed from: Yv1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Yv1 extends VolumeProvider {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C1238Ug0 f9305a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Yv1(C1238Ug0 ug0, int i, int i2, int i3, String str) {
        super(i, i2, i3, str);
        this.f9305a = ug0;
    }

    public void onAdjustVolume(int i) {
        C1238Ug0 ug0 = this.f9305a;
        ug0.g.c.k.post(new RunnableC1177Tg0(ug0, i));
    }

    public void onSetVolumeTo(int i) {
        C1238Ug0 ug0 = this.f9305a;
        ug0.g.c.k.post(new RunnableC1116Sg0(ug0, i));
    }
}
