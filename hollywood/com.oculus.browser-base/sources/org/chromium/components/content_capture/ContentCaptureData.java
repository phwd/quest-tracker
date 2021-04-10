package org.chromium.components.content_capture;

import android.graphics.Rect;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ContentCaptureData {

    /* renamed from: a  reason: collision with root package name */
    public long f10831a;
    public String b;
    public Rect c;
    public ArrayList d;

    public ContentCaptureData(long j, String str, int i, int i2, int i3, int i4) {
        this.f10831a = j;
        this.b = str;
        this.c = new Rect(i, i2, i3 + i, i4 + i2);
    }

    public static ContentCaptureData createContentCaptureData(Object obj, long j, String str, int i, int i2, int i3, int i4) {
        ContentCaptureData contentCaptureData = new ContentCaptureData(j, str, i, i2, i3, i4);
        if (obj != null) {
            ContentCaptureData contentCaptureData2 = (ContentCaptureData) obj;
            if (contentCaptureData2.d == null) {
                contentCaptureData2.d = new ArrayList();
            }
            contentCaptureData2.d.add(contentCaptureData);
        }
        return contentCaptureData;
    }

    public boolean a() {
        ArrayList arrayList = this.d;
        return arrayList != null && !arrayList.isEmpty();
    }

    public String toString() {
        StringBuilder i = AbstractC2531fV.i("id:");
        i.append(this.f10831a);
        i.append(" value:");
        i.append(this.b);
        i.append(" bounds:");
        i.append(this.c);
        i.append('\n');
        if (a()) {
            i.append("children:");
            i.append(this.d.size());
            Iterator it = this.d.iterator();
            while (it.hasNext()) {
                i.append(((ContentCaptureData) it.next()).toString());
            }
        }
        return i.toString();
    }
}
