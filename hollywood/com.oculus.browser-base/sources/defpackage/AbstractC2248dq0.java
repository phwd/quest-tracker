package defpackage;

import android.graphics.Rect;
import android.text.TextUtils;
import android.view.ViewStructure;
import android.view.autofill.AutofillId;
import android.view.contentcapture.ContentCaptureSession;
import org.chromium.components.content_capture.ContentCaptureData;

/* renamed from: dq0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC2248dq0 extends AbstractC2032cb {
    public static Boolean i;
    public final HT j;
    public final C4369qD0 k;

    public AbstractC2248dq0(HT ht, C4369qD0 qd0) {
        this.j = ht;
        this.k = qd0;
        if (i == null) {
            i = Boolean.valueOf(AbstractC0423Gy.a());
        }
    }

    public static boolean p(NullPointerException nullPointerException) {
        StackTraceElement[] stackTrace = nullPointerException.getStackTrace();
        for (StackTraceElement stackTraceElement : stackTrace) {
            if (stackTraceElement.getClassName().startsWith("android.view.contentcapture.MainContentCaptureSession") && stackTraceElement.getMethodName().startsWith("sendEvent")) {
                return true;
            }
        }
        return false;
    }

    @Override // defpackage.AbstractC2032cb
    public /* bridge */ /* synthetic */ Object c() {
        o();
        return Boolean.TRUE;
    }

    @Override // defpackage.AbstractC2032cb
    public /* bridge */ /* synthetic */ void k(Object obj) {
        Boolean bool = (Boolean) obj;
        s();
    }

    public C4198pD0 m() {
        HT ht = this.j;
        if (ht == null || ht.isEmpty()) {
            return this.k.c();
        }
        C4198pD0 c = this.k.c();
        for (int size = this.j.size() - 1; size >= 0; size--) {
            c = n(c, (ContentCaptureData) this.j.get(size));
            if (c == null) {
                break;
            }
        }
        return c;
    }

    public C4198pD0 n(C4198pD0 pd0, ContentCaptureData contentCaptureData) {
        C4198pD0 pd02 = (C4198pD0) this.k.b().get(Long.valueOf(contentCaptureData.f10831a));
        if (pd02 != null || TextUtils.isEmpty(contentCaptureData.b)) {
            return pd02;
        }
        ContentCaptureSession a2 = AbstractC3172jD0.c().a(pd0.f11057a, contentCaptureData.b);
        AbstractC3172jD0.c().d(pd0.f11057a, this.k.c().b, contentCaptureData.f10831a);
        C4198pD0 pd03 = new C4198pD0(a2, r(pd0, contentCaptureData));
        this.k.b().put(Long.valueOf(contentCaptureData.f10831a), pd03);
        return pd03;
    }

    public final Boolean o() {
        try {
            t();
        } catch (NullPointerException e) {
            if (p(e)) {
                AbstractC1220Ua0.a("ContentCapture", "PlatformException", e);
            } else {
                throw e;
            }
        }
        return Boolean.TRUE;
    }

    public void q(String str) {
        if (i.booleanValue()) {
            AbstractC1220Ua0.d("ContentCapture", str, new Object[0]);
        }
    }

    public AutofillId r(C4198pD0 pd0, ContentCaptureData contentCaptureData) {
        ViewStructure e = AbstractC3172jD0.c().e(pd0.f11057a, pd0.b, contentCaptureData.f10831a);
        if (!contentCaptureData.a()) {
            e.setText(contentCaptureData.b);
        }
        Rect rect = contentCaptureData.c;
        e.setDimens(rect.left, rect.top, 0, 0, rect.width(), rect.height());
        AbstractC3172jD0.c().f(pd0.f11057a, e);
        return e.getAutofillId();
    }

    public void s() {
    }

    public abstract void t();
}
