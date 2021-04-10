package com.oculus.logging.analytics2;

import X.C0219We;
import X.Fb;
import X.Fe;
import X.GO;
import X.Mu;
import com.oculus.errorreporting.interfaces.IErrorReporter;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

public class OculusLoggingEventAnalytics2Impl extends OculusEventAnalytics2Impl {
    public static final String TAG = "OculusLoggingEventAnalytics2Impl";
    public final boolean mDoNotCacheEvent;
    public final EventCache mEventCache;

    public static void A00(@Nullable OculusLoggingEventAnalytics2Impl oculusLoggingEventAnalytics2Impl, String str) {
        JSONObject jSONObject;
        if (str == null) {
            jSONObject = new JSONObject();
        } else {
            try {
                jSONObject = new JSONObject(str);
            } catch (JSONException e) {
                Mu.A02(TAG, "Fail to add data to cacheable Event", e);
                return;
            }
        }
        oculusLoggingEventAnalytics2Impl.mCacheableEvent = jSONObject;
        String str2 = oculusLoggingEventAnalytics2Impl.mModuleName;
        if (str2 == null) {
            str2 = "null";
        }
        jSONObject.put(EventCache.MODULE_NAME_KEY, str2);
        oculusLoggingEventAnalytics2Impl.mCacheableEvent.put(EventCache.EVENT_NAME_KEY, oculusLoggingEventAnalytics2Impl.mEventName);
    }

    public OculusLoggingEventAnalytics2Impl(Fe fe, IErrorReporter iErrorReporter, C0219We we, EventCache eventCache, @Nullable String str, String str2, boolean z, boolean z2) {
        super(str, str2, iErrorReporter, we, fe.A00(new Fb(str, str2, GO.CLIENT_EVENT, z)));
        this.mDoNotCacheEvent = z2;
        this.mEventCache = eventCache;
        if (!z2) {
            A00(this, null);
        }
    }
}
