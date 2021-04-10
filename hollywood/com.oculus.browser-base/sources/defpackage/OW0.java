package defpackage;

import java.util.LinkedList;
import java.util.Queue;

/* renamed from: OW0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class OW0 {

    /* renamed from: a  reason: collision with root package name */
    public Queue f8629a;
    public final Object b;

    public OW0(int i) {
        Object obj = new Object();
        this.b = obj;
        synchronized (obj) {
            this.f8629a = new LinkedList();
        }
    }

    public String a(int i) {
        String sb;
        synchronized (this.b) {
            StringBuilder sb2 = new StringBuilder();
            int i2 = 0;
            int max = i <= 0 ? 0 : Math.max(this.f8629a.size() - i, 0);
            for (Object obj : this.f8629a) {
                C5859z.a(obj);
                if (i2 < max) {
                    i2++;
                } else {
                    throw null;
                }
            }
            sb = sb2.toString();
        }
        return sb;
    }

    public String toString() {
        String a2;
        synchronized (this.b) {
            a2 = a(0);
        }
        return a2;
    }
}
