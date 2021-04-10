package defpackage;

import android.hardware.Camera;

/* renamed from: bJ0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C1824bJ0 implements Camera.PreviewCallback {

    /* renamed from: a  reason: collision with root package name */
    public final C2678gJ0 f9533a;

    public C1824bJ0(C2678gJ0 gj0) {
        this.f9533a = gj0;
    }

    public void onPreviewFrame(byte[] bArr, Camera camera) {
        this.f9533a.onPreviewFrame(bArr, camera);
    }
}
