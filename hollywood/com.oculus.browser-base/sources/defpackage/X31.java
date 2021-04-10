package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.InflateException;
import android.view.Menu;
import android.view.MenuInflater;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* renamed from: X31  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class X31 extends MenuInflater {

    /* renamed from: a  reason: collision with root package name */
    public static final Class[] f9191a;
    public static final Class[] b;
    public final Object[] c;
    public final Object[] d;
    public Context e;
    public Object f;

    static {
        Class[] clsArr = {Context.class};
        f9191a = clsArr;
        b = clsArr;
    }

    public X31(Context context) {
        super(context);
        this.e = context;
        Object[] objArr = {context};
        this.c = objArr;
        this.d = objArr;
    }

    public final Object a(Object obj) {
        return (!(obj instanceof Activity) && (obj instanceof ContextWrapper)) ? a(((ContextWrapper) obj).getBaseContext()) : obj;
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:93:0x022f */
    public final void b(XmlPullParser xmlPullParser, AttributeSet attributeSet, Menu menu) {
        char c2;
        char c3;
        ColorStateList colorStateList;
        W31 w31 = new W31(this, menu);
        int eventType = xmlPullParser.getEventType();
        while (true) {
            if (eventType == 2) {
                String name = xmlPullParser.getName();
                if (name.equals("menu")) {
                    eventType = xmlPullParser.next();
                } else {
                    throw new RuntimeException(AbstractC2531fV.f("Expecting menu, got ", name));
                }
            } else {
                eventType = xmlPullParser.next();
                if (eventType == 1) {
                    break;
                }
            }
        }
        String str = null;
        boolean z = false;
        boolean z2 = false;
        while (!z) {
            if (eventType != 1) {
                z = z;
                z = z;
                if (eventType != 2) {
                    if (eventType == 3) {
                        String name2 = xmlPullParser.getName();
                        if (z2 && name2.equals(str)) {
                            str = null;
                            z2 = false;
                            eventType = xmlPullParser.next();
                            z = z;
                            z2 = z2;
                        } else if (name2.equals("group")) {
                            w31.b = 0;
                            w31.c = 0;
                            w31.d = 0;
                            w31.e = 0;
                            w31.f = true;
                            w31.g = true;
                            z = z;
                        } else if (name2.equals("item")) {
                            z = z;
                            if (!w31.h) {
                                H2 h2 = w31.A;
                                if (h2 == null || !h2.a()) {
                                    w31.h = true;
                                    w31.c(w31.f9126a.add(w31.b, w31.i, w31.j, w31.k));
                                    z = z;
                                } else {
                                    w31.a();
                                    z = z;
                                }
                            }
                        } else {
                            z = z;
                            if (name2.equals("menu")) {
                                z = true;
                            }
                        }
                    }
                } else if (!z2) {
                    String name3 = xmlPullParser.getName();
                    if (name3.equals("group")) {
                        TypedArray obtainStyledAttributes = w31.F.e.obtainStyledAttributes(attributeSet, FJ0.k0);
                        w31.b = obtainStyledAttributes.getResourceId(1, 0);
                        w31.c = obtainStyledAttributes.getInt(3, 0);
                        w31.d = obtainStyledAttributes.getInt(4, 0);
                        w31.e = obtainStyledAttributes.getInt(5, 0);
                        w31.f = obtainStyledAttributes.getBoolean(2, true);
                        w31.g = obtainStyledAttributes.getBoolean(0, true);
                        obtainStyledAttributes.recycle();
                        z = z;
                    } else if (name3.equals("item")) {
                        C0514Ii1 p = C0514Ii1.p(w31.F.e, attributeSet, FJ0.l0);
                        w31.i = p.l(2, 0);
                        w31.j = (p.j(5, w31.c) & -65536) | (p.j(6, w31.d) & 65535);
                        w31.k = p.n(7);
                        w31.l = p.n(8);
                        w31.m = p.l(0, 0);
                        String m = p.m(9);
                        if (m == null) {
                            c2 = 0;
                        } else {
                            c2 = m.charAt(0);
                        }
                        w31.n = c2;
                        w31.o = p.j(16, 4096);
                        String m2 = p.m(10);
                        if (m2 == null) {
                            c3 = 0;
                        } else {
                            c3 = m2.charAt(0);
                        }
                        w31.p = c3;
                        w31.q = p.j(20, 4096);
                        if (p.o(11)) {
                            w31.r = p.a(11, false) ? 1 : 0;
                        } else {
                            w31.r = w31.e;
                        }
                        w31.s = p.a(3, false);
                        w31.t = p.a(4, w31.f);
                        w31.u = p.a(1, w31.g);
                        w31.v = p.j(21, -1);
                        w31.z = p.m(12);
                        w31.w = p.l(13, 0);
                        w31.x = p.m(15);
                        String m3 = p.m(14);
                        w31.y = m3;
                        boolean z3 = m3 != null;
                        if (z3 && w31.w == 0 && w31.x == null) {
                            w31.A = (H2) w31.b(m3, b, w31.F.d);
                        } else {
                            if (z3) {
                                Log.w("SupportMenuInflater", "Ignoring attribute 'actionProviderClass'. Action view already specified.");
                            }
                            w31.A = null;
                        }
                        w31.B = p.n(17);
                        w31.C = p.n(22);
                        if (p.o(19)) {
                            w31.E = XI.c(p.j(19, -1), w31.E);
                            colorStateList = null;
                        } else {
                            colorStateList = null;
                            w31.E = null;
                        }
                        if (p.o(18)) {
                            w31.D = p.c(18);
                        } else {
                            w31.D = colorStateList;
                        }
                        p.b.recycle();
                        w31.h = false;
                        z = z;
                    } else {
                        if (name3.equals("menu")) {
                            b(xmlPullParser, attributeSet, w31.a());
                        } else {
                            str = name3;
                            z2 = true;
                        }
                        eventType = xmlPullParser.next();
                        z = z;
                        z2 = z2;
                    }
                }
                eventType = xmlPullParser.next();
                z = z;
                z2 = z2;
            } else {
                throw new RuntimeException("Unexpected end of document");
            }
        }
    }

    public void inflate(int i, Menu menu) {
        if (!(menu instanceof U31)) {
            super.inflate(i, menu);
            return;
        }
        XmlResourceParser xmlResourceParser = null;
        try {
            xmlResourceParser = this.e.getResources().getLayout(i);
            b(xmlResourceParser, Xml.asAttributeSet(xmlResourceParser), menu);
            xmlResourceParser.close();
        } catch (XmlPullParserException e2) {
            throw new InflateException("Error inflating menu XML", e2);
        } catch (IOException e3) {
            throw new InflateException("Error inflating menu XML", e3);
        } catch (Throwable th) {
            if (xmlResourceParser != null) {
                xmlResourceParser.close();
            }
            throw th;
        }
    }
}
