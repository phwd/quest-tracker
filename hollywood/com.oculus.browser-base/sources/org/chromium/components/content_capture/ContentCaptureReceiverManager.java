package org.chromium.components.content_capture;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ContentCaptureReceiverManager {

    /* renamed from: a  reason: collision with root package name */
    public static Boolean f10832a;
    public ArrayList b = new ArrayList();

    public ContentCaptureReceiverManager() {
        if (f10832a == null) {
            f10832a = Boolean.valueOf(AbstractC0423Gy.a());
        }
    }

    public final String[] a(HT ht, ContentCaptureData contentCaptureData) {
        ArrayList arrayList = new ArrayList();
        Iterator it = ht.iterator();
        while (it.hasNext()) {
            arrayList.add(((ContentCaptureData) it.next()).b);
        }
        if (contentCaptureData != null) {
            arrayList.add(contentCaptureData.b);
        }
        String[] strArr = new String[arrayList.size()];
        arrayList.toArray(strArr);
        return strArr;
    }

    public final HT b(Object[] objArr) {
        HT ht = new HT(objArr.length);
        for (Object obj : objArr) {
            ht.add((ContentCaptureData) obj);
        }
        return ht;
    }

    public final void didCaptureContent(Object[] objArr, ContentCaptureData contentCaptureData) {
        HT b2 = b(objArr);
        String[] a2 = a(b2, contentCaptureData);
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            AbstractC0240Dy dy = (AbstractC0240Dy) it.next();
            if (dy.f(a2)) {
                dy.a(b2, contentCaptureData);
            }
        }
        if (f10832a.booleanValue()) {
            AbstractC1220Ua0.d("ContentCapture", "Captured Content: %s", contentCaptureData);
        }
    }

    public final void didRemoveContent(Object[] objArr, long[] jArr) {
        HT b2 = b(objArr);
        String[] a2 = a(b2, null);
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            AbstractC0240Dy dy = (AbstractC0240Dy) it.next();
            if (dy.f(a2)) {
                dy.b(b2, jArr);
            }
        }
        if (f10832a.booleanValue()) {
            AbstractC1220Ua0.d("ContentCapture", "Removed Content: %s", b2.get(0) + " " + Arrays.toString(jArr));
        }
    }

    public final void didRemoveSession(Object[] objArr) {
        HT b2 = b(objArr);
        String[] a2 = a(b2, null);
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            AbstractC0240Dy dy = (AbstractC0240Dy) it.next();
            if (dy.f(a2)) {
                dy.d(b2);
            }
        }
        if (f10832a.booleanValue()) {
            AbstractC1220Ua0.d("ContentCapture", "Removed Session: %s", b2.get(0));
        }
    }

    public final void didUpdateContent(Object[] objArr, ContentCaptureData contentCaptureData) {
        HT b2 = b(objArr);
        String[] a2 = a(b2, contentCaptureData);
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            AbstractC0240Dy dy = (AbstractC0240Dy) it.next();
            if (dy.f(a2)) {
                dy.c(b2, contentCaptureData);
            }
        }
        if (f10832a.booleanValue()) {
            AbstractC1220Ua0.d("ContentCapture", "Updated Content: %s", contentCaptureData);
        }
    }
}
