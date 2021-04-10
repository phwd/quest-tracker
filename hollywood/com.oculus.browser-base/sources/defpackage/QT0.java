package defpackage;

import android.graphics.Bitmap;
import java.io.FileOutputStream;

/* renamed from: QT0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class QT0 implements XT0 {

    /* renamed from: a  reason: collision with root package name */
    public final Bitmap f8763a;

    public QT0(Bitmap bitmap) {
        this.f8763a = bitmap;
    }

    @Override // defpackage.XT0
    public void a(FileOutputStream fileOutputStream) {
        this.f8763a.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
    }
}
