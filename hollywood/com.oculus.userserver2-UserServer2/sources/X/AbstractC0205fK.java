package X;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: X.fK  reason: case insensitive filesystem */
public abstract class AbstractC0205fK<EndpointType> {
    public final fJ A00;

    public boolean A00(Context context, EndpointType endpointtype, Intent intent) {
        return A01(context, endpointtype, intent, null);
    }

    public boolean A01(Context context, EndpointType endpointtype, Intent intent, @Nullable ep epVar) {
        boolean z;
        int length;
        fJ fJVar = this.A00;
        if (!fJVar.A3g()) {
            return true;
        }
        fI[] A1e = fJVar.A1e();
        if (A1e == null || (length = A1e.length) <= 0) {
            z = false;
        } else {
            int i = 0;
            while (true) {
                fI fIVar = A1e[i];
                Pattern pattern = fIVar.A03;
                if (pattern == null || pattern.matcher(endpointtype.getClass().getName()).matches()) {
                    try {
                        fX A002 = C0215ff.A00(context, intent, null);
                        fH fHVar = fIVar.A00;
                        if (fHVar != null) {
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
                                if (!fHVar.A01(jSONObject, null)) {
                                }
                            }
                        }
                        if (fIVar.A01(intent, epVar)) {
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
            fG[] A1k = fJVar.A1k();
            for (fG fGVar : A1k) {
                String str3 = fGVar.A02;
                if (str3 == null || str3.equals(endpointtype.getClass().getName())) {
                    IntentFilter intentFilter = fGVar.A01;
                    if (intentFilter != null) {
                        boolean z2 = false;
                        if (intentFilter.match(fGVar.A00, intent, false, "TAG") > 0) {
                            z2 = true;
                        }
                        if (fGVar.A03) {
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

    public AbstractC0205fK(fJ fJVar) {
        this.A00 = fJVar;
    }
}
