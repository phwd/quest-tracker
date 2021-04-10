package X;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: X.0ju  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC02830ju<EndpointType> {
    public final AbstractC02820jt A00;

    public boolean A00(Context context, EndpointType endpointtype, Intent intent) {
        return A01(context, endpointtype, intent, null);
    }

    public boolean A01(Context context, EndpointType endpointtype, Intent intent, @Nullable C02610jP r14) {
        boolean z;
        int length;
        AbstractC02820jt r6 = this.A00;
        if (!r6.AAN()) {
            return true;
        }
        C02810js[] A3g = r6.A3g();
        if (A3g == null || (length = A3g.length) <= 0) {
            z = false;
        } else {
            int i = 0;
            while (true) {
                C02810js r7 = A3g[i];
                Pattern pattern = r7.A03;
                if (pattern == null || pattern.matcher(endpointtype.getClass().getName()).matches()) {
                    try {
                        AnonymousClass0k7 A002 = AnonymousClass0kG.A00(context, intent, false, null);
                        C02800jr r8 = r7.A00;
                        if (r8 != null) {
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
                                if (!r8.A01(jSONObject, null)) {
                                }
                            }
                        }
                        if (r7.A01(intent, r14)) {
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
            C02790jq[] A4C = r6.A4C();
            for (C02790jq r72 : A4C) {
                String str3 = r72.A02;
                if (str3 == null || str3.equals(endpointtype.getClass().getName())) {
                    IntentFilter intentFilter = r72.A01;
                    if (intentFilter != null) {
                        boolean z2 = false;
                        if (intentFilter.match(r72.A00, intent, false, "TAG") > 0) {
                            z2 = true;
                        }
                        if (r72.A03) {
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

    public AbstractC02830ju(AbstractC02820jt r1) {
        this.A00 = r1;
    }
}
