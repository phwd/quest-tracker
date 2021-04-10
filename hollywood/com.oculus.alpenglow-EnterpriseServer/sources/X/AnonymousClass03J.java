package X;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.XmlResourceParser;
import android.util.Xml;
import android.view.InflateException;
import android.view.Menu;
import android.view.MenuInflater;
import androidx.annotation.LayoutRes;
import androidx.annotation.RestrictTo;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

@RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
/* renamed from: X.03J  reason: invalid class name */
public final class AnonymousClass03J extends MenuInflater {
    public static final Class<?>[] A04;
    public static final Class<?>[] A05;
    public Context A00;
    public Object A01;
    public final Object[] A02;
    public final Object[] A03;

    static {
        Class<?>[] clsArr = {Context.class};
        A05 = clsArr;
        A04 = clsArr;
    }

    public static Object A00(AnonymousClass03J r1, Object obj) {
        if ((obj instanceof Activity) || !(obj instanceof ContextWrapper)) {
            return obj;
        }
        return A00(r1, ((ContextWrapper) obj).getBaseContext());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00e2, code lost:
        r1 = "Unexpected end of document";
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void A01(org.xmlpull.v1.XmlPullParser r14, android.util.AttributeSet r15, android.view.Menu r16) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
        // Method dump skipped, instructions count: 241
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass03J.A01(org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.view.Menu):void");
    }

    public final void inflate(@LayoutRes int i, Menu menu) {
        if (!(menu instanceof AbstractMenuC007608p)) {
            super.inflate(i, menu);
            return;
        }
        XmlResourceParser xmlResourceParser = null;
        try {
            XmlResourceParser layout = this.A00.getResources().getLayout(i);
            A01(layout, Xml.asAttributeSet(layout), menu);
            if (layout != null) {
                layout.close();
            }
        } catch (XmlPullParserException e) {
            throw new InflateException("Error inflating menu XML", e);
        } catch (IOException e2) {
            throw new InflateException("Error inflating menu XML", e2);
        } catch (Throwable th) {
            if (0 != 0) {
                xmlResourceParser.close();
            }
            throw th;
        }
    }

    public AnonymousClass03J(Context context) {
        super(context);
        this.A00 = context;
        Object[] objArr = {context};
        this.A03 = objArr;
        this.A02 = objArr;
    }
}
