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

@RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
/* renamed from: X.1sK  reason: invalid class name and case insensitive filesystem */
public final class C11571sK extends MenuInflater {
    public static final Class<?>[] A04;
    public static final Class<?>[] A05;
    public Context A00;
    public Object A01;
    public final Object[] A02;
    public final Object[] A03;

    static {
        Class<?>[] clsArr = {Context.class};
        A04 = clsArr;
        A05 = clsArr;
    }

    public static Object A00(C11571sK r1, Object obj) {
        if ((obj instanceof Activity) || !(obj instanceof ContextWrapper)) {
            return obj;
        }
        return A00(r1, ((ContextWrapper) obj).getBaseContext());
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v37, resolved type: int */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x01d9  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x01ef  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x01fe  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0201  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void A01(org.xmlpull.v1.XmlPullParser r19, android.util.AttributeSet r20, android.view.Menu r21) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
        // Method dump skipped, instructions count: 607
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C11571sK.A01(org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.view.Menu):void");
    }

    public final void inflate(@LayoutRes int i, Menu menu) {
        if (!(menu instanceof AnonymousClass05X)) {
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

    public C11571sK(Context context) {
        super(context);
        this.A00 = context;
        Object[] objArr = {context};
        this.A02 = objArr;
        this.A03 = objArr;
    }
}
