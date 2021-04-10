package X;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: X.gz  reason: case insensitive filesystem */
public abstract class AbstractC0397gz<EndpointType> {
    public final AbstractC0396gy A00;

    public boolean A00(Context context, EndpointType endpointtype, Intent intent) {
        return A01(context, endpointtype, intent, null);
    }

    public boolean A01(Context context, EndpointType endpointtype, Intent intent, @Nullable C0379gY gYVar) {
        boolean z;
        int length;
        AbstractC0396gy gyVar = this.A00;
        if (!gyVar.A5E()) {
            return true;
        }
        C0395gx[] A2P = gyVar.A2P();
        if (A2P == null || (length = A2P.length) <= 0) {
            z = false;
        } else {
            int i = 0;
            while (true) {
                C0395gx gxVar = A2P[i];
                Pattern pattern = gxVar.A03;
                if (pattern == null || pattern.matcher(endpointtype.getClass().getName()).matches()) {
                    try {
                        C0408hC A002 = C0414hK.A00(context, intent, null);
                        C0394gw gwVar = gxVar.A00;
                        if (gwVar != null) {
                            if (A002 != null) {
                                JSONObject jSONObject = new JSONObject();
                                jSONObject.put("caller_uid", A002.A00);
                                String A003 = A002.A00();
                                if (A003 != null) {
                                    jSONObject.put("caller_package_name", A003);
                                }
                                String str = A002.A03;
                                if (str != null) {
                                    jSONObject.put("caller_version_name", str);
                                }
                                String str2 = A002.A02;
                                if (str2 != null) {
                                    jSONObject.put("caller_domain", str2);
                                }
                                if (!gwVar.A01(jSONObject, null)) {
                                }
                            }
                        }
                        if (gxVar.A01(intent, gYVar)) {
                            z = true;
                            break;
                        }
                    } catch (JSONException unused) {
                    }
                }
                i++;
                if (i >= length) {
                    break;
                }
            }
            z = false;
        }
        if (!z) {
            C0393gv[] A2Y = gyVar.A2Y();
            for (C0393gv gvVar : A2Y) {
                String str3 = gvVar.A02;
                if (str3 == null || str3.equals(endpointtype.getClass().getName())) {
                    IntentFilter intentFilter = gvVar.A01;
                    if (intentFilter != null) {
                        boolean z2 = false;
                        if (intentFilter.match(gvVar.A00, intent, false, "TAG") > 0) {
                            z2 = true;
                        }
                        if (gvVar.A03) {
                            if (!z2) {
                            }
                        } else if (z2) {
                            return false;
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }

    public AbstractC0397gz(AbstractC0396gy gyVar) {
        this.A00 = gyVar;
    }
}
