package defpackage;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.InflateException;
import androidx.preference.Preference;
import androidx.preference.SwitchPreference;
import java.io.IOException;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* renamed from: mF0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3691mF0 {

    /* renamed from: a  reason: collision with root package name */
    public static final Class[] f10408a = {Context.class, AttributeSet.class};
    public static final HashMap b = new HashMap();
    public final Context c;
    public final Object[] d = new Object[2];
    public C4375qF0 e;
    public String[] f;

    public C3691mF0(Context context, C4375qF0 qf0) {
        this.c = context;
        this.e = qf0;
        this.f = new String[]{Preference.class.getPackage().getName() + ".", SwitchPreference.class.getPackage().getName() + "."};
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0078, code lost:
        r12 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0079, code lost:
        r0 = new android.view.InflateException(r13.getPositionDescription() + ": Error inflating class " + r11);
        r0.initCause(r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0097, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0098, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0099, code lost:
        throw r11;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0078 A[ExcHandler: Exception (r12v4 'e' java.lang.Exception A[CUSTOM_DECLARE]), Splitter:B:2:0x000d] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final androidx.preference.Preference a(java.lang.String r11, java.lang.String[] r12, android.util.AttributeSet r13) {
        /*
        // Method dump skipped, instructions count: 154
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C3691mF0.a(java.lang.String, java.lang.String[], android.util.AttributeSet):androidx.preference.Preference");
    }

    public final Preference b(String str, AttributeSet attributeSet) {
        try {
            if (-1 == str.indexOf(46)) {
                return a(str, this.f, attributeSet);
            }
            return a(str, null, attributeSet);
        } catch (InflateException e2) {
            throw e2;
        } catch (ClassNotFoundException e3) {
            InflateException inflateException = new InflateException(attributeSet.getPositionDescription() + ": Error inflating class (not found)" + str);
            inflateException.initCause(e3);
            throw inflateException;
        } catch (Exception e4) {
            InflateException inflateException2 = new InflateException(attributeSet.getPositionDescription() + ": Error inflating class " + str);
            inflateException2.initCause(e4);
            throw inflateException2;
        }
    }

    public Preference c(XmlPullParser xmlPullParser, AbstractC2837hF0 hf0) {
        int next;
        synchronized (this.d) {
            AttributeSet asAttributeSet = Xml.asAttributeSet(xmlPullParser);
            this.d[0] = this.c;
            do {
                try {
                    next = xmlPullParser.next();
                    if (next == 2) {
                        break;
                    }
                } catch (InflateException e2) {
                    throw e2;
                } catch (XmlPullParserException e3) {
                    InflateException inflateException = new InflateException(e3.getMessage());
                    inflateException.initCause(e3);
                    throw inflateException;
                } catch (IOException e4) {
                    InflateException inflateException2 = new InflateException(xmlPullParser.getPositionDescription() + ": " + e4.getMessage());
                    inflateException2.initCause(e4);
                    throw inflateException2;
                }
            } while (next != 1);
            if (next == 2) {
                AbstractC2837hF0 hf02 = (AbstractC2837hF0) b(xmlPullParser.getName(), asAttributeSet);
                if (hf0 == null) {
                    hf02.w(this.e);
                    hf0 = hf02;
                }
                d(xmlPullParser, hf0, asAttributeSet);
            } else {
                throw new InflateException(xmlPullParser.getPositionDescription() + ": No start tag found!");
            }
        }
        return hf0;
    }

    public final void d(XmlPullParser xmlPullParser, Preference preference, AttributeSet attributeSet) {
        int depth = xmlPullParser.getDepth();
        while (true) {
            int next = xmlPullParser.next();
            if ((next == 3 && xmlPullParser.getDepth() <= depth) || next == 1) {
                return;
            }
            if (next == 2) {
                String name = xmlPullParser.getName();
                if ("intent".equals(name)) {
                    try {
                        preference.R = Intent.parseIntent(this.c.getResources(), xmlPullParser, attributeSet);
                    } catch (IOException e2) {
                        XmlPullParserException xmlPullParserException = new XmlPullParserException("Error parsing preference");
                        xmlPullParserException.initCause(e2);
                        throw xmlPullParserException;
                    }
                } else if ("extra".equals(name)) {
                    this.c.getResources().parseBundleExtra("extra", attributeSet, preference.j());
                    try {
                        int depth2 = xmlPullParser.getDepth();
                        while (true) {
                            int next2 = xmlPullParser.next();
                            if (next2 == 1 || (next2 == 3 && xmlPullParser.getDepth() <= depth2)) {
                                break;
                            }
                        }
                    } catch (IOException e3) {
                        XmlPullParserException xmlPullParserException2 = new XmlPullParserException("Error parsing preference");
                        xmlPullParserException2.initCause(e3);
                        throw xmlPullParserException2;
                    }
                } else {
                    Preference b2 = b(name, attributeSet);
                    ((AbstractC2837hF0) preference).a0(b2);
                    d(xmlPullParser, b2, attributeSet);
                }
            }
        }
    }
}
