package defpackage;

import android.net.Uri;
import org.chromium.base.Callback;

/* renamed from: ST0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ST0 implements ZT0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Callback f8896a;

    public ST0(Callback callback) {
        this.f8896a = callback;
    }

    @Override // defpackage.ZT0
    public void a(Uri uri, String str) {
        this.f8896a.onResult(uri);
    }

    @Override // defpackage.ZT0
    public void b(String str) {
    }
}
