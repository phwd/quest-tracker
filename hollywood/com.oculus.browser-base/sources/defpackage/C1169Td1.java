package defpackage;

import java.util.concurrent.atomic.AtomicBoolean;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;

/* renamed from: Td1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1169Td1 extends AbstractC1526Za1 {

    /* renamed from: a  reason: collision with root package name */
    public static final Object f8971a = new Object();
    public static final Object b = new Object();
    public static final AtomicBoolean c = new AtomicBoolean();
    public static AbstractC2032cb d;
    public static AbstractC2032cb e;
    public final int f;
    public final int g;
    public TabContentManager h;
    public boolean i;

    public C1169Td1(int i2, boolean z) {
        this.f = i2;
        this.g = i2 == 0 ? 1 : 0;
    }

    public static String d(int i2) {
        String num = Integer.toString(i2);
        Object obj = C4766sb1.f11284a;
        return AbstractC2531fV.f("tab_state", num);
    }

    @Override // defpackage.AbstractC1526Za1
    public void a() {
        synchronized (b) {
            AbstractC2032cb cbVar = e;
            if (cbVar != null) {
                cbVar.b(true);
            }
        }
    }

    @Override // defpackage.AbstractC1526Za1
    public String b() {
        return d(this.f);
    }

    @Override // defpackage.AbstractC1526Za1
    public boolean c() {
        return c.get();
    }
}
