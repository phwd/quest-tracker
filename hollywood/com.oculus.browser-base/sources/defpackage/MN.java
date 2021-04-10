package defpackage;

import android.content.ContentResolver;
import android.content.pm.Signature;
import android.database.Cursor;
import android.net.Uri;
import com.oculus.browser.FacebookLoginManager;
import java.util.ArrayList;
import java.util.Objects;
import org.chromium.base.ThreadUtils;

/* renamed from: MN  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class MN extends AbstractC2032cb {
    public final /* synthetic */ FacebookLoginManager i;

    public MN(FacebookLoginManager facebookLoginManager) {
        this.i = facebookLoginManager;
    }

    @Override // defpackage.AbstractC2032cb
    public Object c() {
        FacebookLoginManager facebookLoginManager = this.i;
        ContentResolver contentResolver = facebookLoginManager.d.getContentResolver();
        if (!FacebookLoginManager.h(facebookLoginManager.d)) {
            AbstractC1220Ua0.d("FACEBOOK_LOGIN_MANAGER", "Horizon Signature check failed", new Object[0]);
        } else {
            try {
                Cursor query = contentResolver.query(Uri.parse("content://com.oculus.horizon.sso/cookies?fb_app_id=1562231310457908"), null, null, null, null);
                if (query == null) {
                    AbstractC1220Ua0.d("FACEBOOK_LOGIN_MANAGER", "Null Cursor Querying SSO", new Object[0]);
                } else {
                    ArrayList arrayList = new ArrayList();
                    query.moveToFirst();
                    for (int i2 = 0; i2 < query.getCount(); i2++) {
                        QS0 qs0 = new QS0();
                        int columnIndex = query.getColumnIndex("domain");
                        if (columnIndex >= 0 && query.getType(columnIndex) == 3) {
                            qs0.f8762a = query.getString(columnIndex);
                        }
                        int columnIndex2 = query.getColumnIndex("expires");
                        if (columnIndex2 >= 0 && query.getType(columnIndex2) == 3) {
                            qs0.b = query.getString(columnIndex2);
                        }
                        int columnIndex3 = query.getColumnIndex("expires_timestamp");
                        if (columnIndex3 >= 0 && query.getType(columnIndex3) == 1) {
                            query.getInt(columnIndex3);
                        }
                        int columnIndex4 = query.getColumnIndex("name");
                        if (columnIndex4 >= 0 && query.getType(columnIndex4) == 3) {
                            qs0.c = query.getString(columnIndex4);
                        }
                        int columnIndex5 = query.getColumnIndex("path");
                        if (columnIndex5 >= 0 && query.getType(columnIndex5) == 3) {
                            query.getString(columnIndex5);
                        }
                        int columnIndex6 = query.getColumnIndex("httponly");
                        if (columnIndex6 >= 0 && query.getType(columnIndex6) == 1) {
                            qs0.d = query.getInt(columnIndex6) != 0;
                        }
                        int columnIndex7 = query.getColumnIndex("secure");
                        if (columnIndex7 >= 0 && query.getType(columnIndex7) == 1) {
                            query.getInt(columnIndex7);
                        }
                        int columnIndex8 = query.getColumnIndex("value");
                        if (columnIndex8 >= 0 && query.getType(columnIndex8) == 3) {
                            qs0.e = query.getString(columnIndex8);
                        }
                        arrayList.add(qs0);
                        query.moveToNext();
                    }
                    query.close();
                    if (arrayList.size() > 0) {
                        facebookLoginManager.e = arrayList;
                    }
                }
            } catch (Exception e) {
                StringBuilder i3 = AbstractC2531fV.i("Error querying Horizon Sso: ");
                i3.append(e.toString());
                AbstractC1220Ua0.a("FACEBOOK_LOGIN_MANAGER", i3.toString(), new Object[0]);
            }
        }
        return Boolean.TRUE;
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        Boolean bool = (Boolean) obj;
        FacebookLoginManager facebookLoginManager = this.i;
        Signature signature = FacebookLoginManager.f9699a;
        Objects.requireNonNull(facebookLoginManager);
        ThreadUtils.g(new NN(facebookLoginManager));
    }
}
