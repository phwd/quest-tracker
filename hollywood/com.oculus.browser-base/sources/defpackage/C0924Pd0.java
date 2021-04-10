package defpackage;

import android.media.session.MediaController;

/* renamed from: Pd0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0924Pd0 extends AbstractC0863Od0 {

    /* renamed from: a  reason: collision with root package name */
    public final MediaController.TransportControls f8701a;

    public C0924Pd0(MediaController.TransportControls transportControls) {
        this.f8701a = transportControls;
    }

    @Override // defpackage.AbstractC0863Od0
    public void a() {
        this.f8701a.pause();
    }

    @Override // defpackage.AbstractC0863Od0
    public void b() {
        this.f8701a.play();
    }

    @Override // defpackage.AbstractC0863Od0
    public void c() {
        this.f8701a.stop();
    }
}
