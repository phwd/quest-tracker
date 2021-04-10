package defpackage;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.oculus.browser.FacebookLoginManager;

/* renamed from: LN  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class LN extends AbstractC2032cb {
    public final /* synthetic */ FacebookLoginManager i;

    public LN(FacebookLoginManager facebookLoginManager) {
        this.i = facebookLoginManager;
    }

    @Override // defpackage.AbstractC2032cb
    public Object c() {
        Context context = this.i.d;
        if (!FacebookLoginManager.h(context)) {
            AbstractC1220Ua0.d("FACEBOOK_LOGIN_MANAGER", "Horizon Signature check failed", new Object[0]);
            return null;
        }
        ContentResolver contentResolver = context.getContentResolver();
        ON on = new ON(null);
        try {
            Cursor query = contentResolver.query(Uri.parse("content://com.oculus.horizon.fbconnect/account"), null, null, null, null);
            if (query == null) {
                AbstractC1220Ua0.d("FACEBOOK_LOGIN_MANAGER", "Null Cursor Querying FBConnect", new Object[0]);
                return null;
            }
            query.moveToFirst();
            int columnIndex = query.getColumnIndex("userid");
            if (columnIndex >= 0 && query.getType(columnIndex) == 3) {
                on.f8622a = query.getString(columnIndex);
            }
            int columnIndex2 = query.getColumnIndex("user_name");
            if (columnIndex2 >= 0 && query.getType(columnIndex2) == 3) {
                on.b = query.getString(columnIndex2);
            }
            int columnIndex3 = query.getColumnIndex("accesstoken");
            if (columnIndex3 >= 0 && query.getType(columnIndex3) == 3) {
                on.c = query.getString(columnIndex3);
            }
            int columnIndex4 = query.getColumnIndex("profile_picture");
            if (columnIndex4 >= 0 && query.getType(columnIndex4) == 3) {
                String string = query.getString(columnIndex4);
                on.d = string;
                Uri parse = Uri.parse(string);
                Uri.Builder buildUpon = parse.buildUpon();
                buildUpon.clearQuery();
                buildUpon.appendQueryParameter("height", "200");
                buildUpon.appendQueryParameter("width", "200");
                for (String str : parse.getQueryParameterNames()) {
                    if (!str.equals("height") && !str.equals("width")) {
                        buildUpon.appendQueryParameter(str, parse.getQueryParameter(str));
                    }
                }
                on.e = buildUpon.build().toString();
            }
            return on;
        } catch (Exception e) {
            StringBuilder i2 = AbstractC2531fV.i("Error querying Horizon FbConnect: ");
            i2.append(e.toString());
            AbstractC1220Ua0.a("FACEBOOK_LOGIN_MANAGER", i2.toString(), new Object[0]);
            return null;
        }
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        ON on = (ON) obj;
        if (on != null) {
            FacebookLoginManager facebookLoginManager = this.i;
            facebookLoginManager.j = on.f8622a;
            facebookLoginManager.k = on.b;
            facebookLoginManager.l = on.e;
            QN qn = new QN();
            qn.b = facebookLoginManager;
            qn.d(facebookLoginManager.l, null, 1, 1);
        }
        FacebookLoginManager facebookLoginManager2 = this.i;
        facebookLoginManager2.nativeProfileIsLoggedInAsync(facebookLoginManager2.n);
    }
}
