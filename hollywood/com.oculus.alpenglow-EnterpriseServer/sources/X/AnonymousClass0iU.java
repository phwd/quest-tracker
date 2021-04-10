package X;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: X.0iU  reason: invalid class name */
public abstract class AnonymousClass0iU<EndpointType> {
    public final AnonymousClass0iT A00;

    public boolean A00(Context context, EndpointType endpointtype, Intent intent) {
        return A01(context, endpointtype, intent, null);
    }

    public boolean A01(Context context, EndpointType endpointtype, Intent intent, @Nullable AnonymousClass0i5 r14) {
        boolean z;
        int length;
        AnonymousClass0iT r6 = this.A00;
        if (!r6.A8M()) {
            return true;
        }
        C05050iS[] A3J = r6.A3J();
        if (A3J == null || (length = A3J.length) <= 0) {
            z = false;
        } else {
            int i = 0;
            while (true) {
                C05050iS r7 = A3J[i];
                Pattern pattern = r7.A03;
                if (pattern == null || pattern.matcher(endpointtype.getClass().getName()).matches()) {
                    try {
                        C05130ih A002 = C05200ip.A00(context, intent, false, null);
                        AnonymousClass0iR r8 = r7.A00;
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
            AnonymousClass0iQ[] A3o = r6.A3o();
            for (AnonymousClass0iQ r72 : A3o) {
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

    public AnonymousClass0iU(AnonymousClass0iT r1) {
        this.A00 = r1;
    }
}
