package defpackage;

import android.graphics.Bitmap;
import org.chromium.components.favicon.LargeIconBridge$LargeIconCallback;

/* renamed from: mi1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3763mi1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final LargeIconBridge$LargeIconCallback f10442a;

    public C3763mi1(LargeIconBridge$LargeIconCallback largeIconBridge$LargeIconCallback) {
        this.f10442a = largeIconBridge$LargeIconCallback;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        this.f10442a.onLargeIconAvailable((Bitmap) obj, -16777216, false, 1);
    }
}
