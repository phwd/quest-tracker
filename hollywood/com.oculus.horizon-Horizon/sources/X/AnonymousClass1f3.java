package X;

import android.os.Bundle;
import android.text.TextUtils;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.PlatformServiceClient;
import com.facebook.internal.Utility;
import com.facebook.login.GetTokenLoginMethodHandler;
import com.facebook.login.LoginClient;
import com.oculus.horizon.R;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/* renamed from: X.1f3  reason: invalid class name */
public class AnonymousClass1f3 implements PlatformServiceClient.CompletedListener {
    public final /* synthetic */ GetTokenLoginMethodHandler A00;
    public final /* synthetic */ LoginClient.Request A01;

    public AnonymousClass1f3(GetTokenLoginMethodHandler getTokenLoginMethodHandler, LoginClient.Request request) {
        this.A00 = getTokenLoginMethodHandler;
        this.A01 = request;
    }

    @Override // com.facebook.internal.PlatformServiceClient.CompletedListener
    public final void completed(Bundle bundle) {
        GetTokenLoginMethodHandler getTokenLoginMethodHandler = this.A00;
        LoginClient.Request request = this.A01;
        C09391dV r1 = getTokenLoginMethodHandler.A00;
        if (r1 != null) {
            r1.listener = null;
        }
        getTokenLoginMethodHandler.A00 = null;
        AnonymousClass1gR r0 = getTokenLoginMethodHandler.A01.A06;
        if (r0 != null) {
            r0.A01.findViewById(R.id.com_facebook_login_activity_progress_bar).setVisibility(8);
        }
        if (bundle != null) {
            ArrayList<String> stringArrayList = bundle.getStringArrayList(NativeProtocol.EXTRA_PERMISSIONS);
            Set<String> set = request.A00;
            if (stringArrayList == null || (set != null && !stringArrayList.containsAll(set))) {
                HashSet hashSet = new HashSet();
                for (String str : set) {
                    if (!stringArrayList.contains(str)) {
                        hashSet.add(str);
                    }
                }
                if (!hashSet.isEmpty()) {
                    getTokenLoginMethodHandler.A03("new_permissions", TextUtils.join(",", hashSet));
                }
                request.A00 = hashSet;
            } else {
                String string = bundle.getString(NativeProtocol.EXTRA_USER_ID);
                if (string == null || string.isEmpty()) {
                    AnonymousClass1gR r02 = getTokenLoginMethodHandler.A01.A06;
                    if (r02 != null) {
                        r02.A01.findViewById(R.id.com_facebook_login_activity_progress_bar).setVisibility(0);
                    }
                    Utility.getGraphMeRequestWithCacheAsync(bundle.getString(NativeProtocol.EXTRA_ACCESS_TOKEN), new AnonymousClass1fA(getTokenLoginMethodHandler, bundle, request));
                    return;
                }
                getTokenLoginMethodHandler.A04(request, bundle);
                return;
            }
        }
        getTokenLoginMethodHandler.A01.A04();
    }
}
