package X;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class JU {
    public final JT A00;

    public boolean A00(Context context, Object obj, Intent intent) {
        return A01(context, obj, intent, null);
    }

    public boolean A01(Context context, Object obj, Intent intent, J6 j6) {
        boolean z;
        int length;
        JT jt = this.A00;
        if (!jt.A53()) {
            return true;
        }
        JS[] A2K = jt.A2K();
        if (A2K == null || (length = A2K.length) <= 0) {
            z = false;
        } else {
            int i = 0;
            while (true) {
                JS js = A2K[i];
                Pattern pattern = js.A03;
                if (pattern == null || pattern.matcher(obj.getClass().getName()).matches()) {
                    try {
                        C0194Jh A002 = C0199Jm.A00(context, intent);
                        JR jr = js.A00;
                        if (jr != null) {
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
                                if (!jr.A01(jSONObject, null)) {
                                }
                            }
                        }
                        if (js.A01(intent, j6)) {
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
            JQ[] A2W = jt.A2W();
            for (JQ jq : A2W) {
                String str3 = jq.A02;
                if (str3 == null || str3.equals(obj.getClass().getName())) {
                    IntentFilter intentFilter = jq.A01;
                    if (intentFilter != null) {
                        boolean z2 = false;
                        if (intentFilter.match(jq.A00, intent, false, "TAG") > 0) {
                            z2 = true;
                        }
                        if (jq.A03) {
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

    public JU(JT jt) {
        this.A00 = jt;
    }
}
