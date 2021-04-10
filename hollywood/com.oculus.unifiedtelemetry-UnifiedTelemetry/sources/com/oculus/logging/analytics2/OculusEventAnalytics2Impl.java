package com.oculus.logging.analytics2;

import X.AnonymousClass06;
import X.AnonymousClass2z;
import X.C0219We;
import X.C0482rT;
import X.GK;
import X.GV;
import X.Mu;
import X.YE;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.errorreporting.interfaces.IErrorReporter;
import com.oculus.logging.EventTag;
import com.oculus.logging.ExtraKeys;
import com.oculus.logging.OculusLoggingEvent;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class OculusEventAnalytics2Impl implements OculusLoggingEvent {
    public static final String TAG = "OculusEventAnalytics2Impl";
    @Nullable
    public JSONObject mCacheableEvent;
    public final IErrorReporter mErrorReporter;
    public final GK mEventBuilder;
    public final String mEventName;
    public final boolean mIsSampled;
    @Nullable
    public final String mModuleName;
    public final C0219We mObjectMapper;

    private void A01(String str, @Nullable Object obj) {
        JSONObject jSONObject = this.mCacheableEvent;
        if (jSONObject != null) {
            if (obj == null) {
                obj = "null";
            }
            try {
                jSONObject.put(str, obj);
            } catch (JSONException e) {
                Mu.A02(TAG, "Error on adding extra to cacheable event", e);
            }
        }
    }

    @Nullable
    private final AnonymousClass2z A02(IErrorReporter iErrorReporter, C0219We we, String str) {
        try {
            new JSONObject(str);
            return JsonAndParamsCollectionHelper.A00(str, we);
        } catch (JSONException e) {
            Mu.A02(TAG, "JSONException", e);
            A16(ExtraKeys.CORRUPTED, true);
            iErrorReporter.A5H("failed_to_parse_telemetry_data", AnonymousClass06.A05(this.mEventName, "|", e.getMessage()));
            return null;
        }
    }

    @Override // com.oculus.logging.OculusLoggingEvent
    public final OculusLoggingEvent A12(String str, String str2) {
        this.mEventBuilder.A04(str, str2);
        return this;
    }

    @Override // com.oculus.logging.OculusLoggingEvent
    public final OculusLoggingEvent A1A(String str) {
        AnonymousClass2z A02;
        AnonymousClass2z A022;
        if (!(this instanceof OculusLoggingEventAnalytics2Impl)) {
            if (!(str == null || (A022 = A02(this.mErrorReporter, this.mObjectMapper, str)) == null)) {
                C0482rT.A01(A022, this.mEventBuilder.A06());
            }
            return this;
        }
        OculusLoggingEventAnalytics2Impl oculusLoggingEventAnalytics2Impl = (OculusLoggingEventAnalytics2Impl) this;
        if (oculusLoggingEventAnalytics2Impl.mDoNotCacheEvent) {
            if (!(str == null || (A02 = oculusLoggingEventAnalytics2Impl.A02(oculusLoggingEventAnalytics2Impl.mErrorReporter, oculusLoggingEventAnalytics2Impl.mObjectMapper, str)) == null)) {
                C0482rT.A01(A02, oculusLoggingEventAnalytics2Impl.mEventBuilder.A06());
            }
            return oculusLoggingEventAnalytics2Impl;
        }
        OculusLoggingEventAnalytics2Impl.A00(oculusLoggingEventAnalytics2Impl, str);
        return oculusLoggingEventAnalytics2Impl;
    }

    @Override // com.oculus.logging.OculusLoggingEvent
    @Nullable
    public final YE A2R() {
        return this.mEventBuilder.A06();
    }

    @Override // com.oculus.logging.OculusLoggingEvent
    public final void A3Q() {
        GK gk;
        if (!(this instanceof OculusLoggingEventAnalytics2Impl)) {
            gk = this.mEventBuilder;
        } else {
            OculusLoggingEventAnalytics2Impl oculusLoggingEventAnalytics2Impl = (OculusLoggingEventAnalytics2Impl) this;
            JSONObject jSONObject = oculusLoggingEventAnalytics2Impl.mCacheableEvent;
            if (jSONObject != null) {
                EventCache eventCache = oculusLoggingEventAnalytics2Impl.mEventCache;
                try {
                    jSONObject.put(EventCache.EVENT_RTC_TIME, eventCache.mRtcTime.get());
                } catch (JSONException e) {
                    Mu.A02(EventCache.TAG, "failed to add timestamp", e);
                }
                byte[] bytes = AnonymousClass06.A04(jSONObject.toString(), "\n").getBytes(StandardCharsets.UTF_8);
                byte[] bArr = null;
                synchronized (eventCache) {
                    ByteBuffer byteBuffer = eventCache.mEventBuffer;
                    if (byteBuffer == null) {
                        byteBuffer = ByteBuffer.allocate(65536);
                        eventCache.mEventBuffer = byteBuffer;
                    }
                    if (bytes.length + byteBuffer.position() >= 65536) {
                        bArr = new byte[eventCache.mEventBuffer.position()];
                        eventCache.mEventBuffer.flip();
                        eventCache.mEventBuffer.get(bArr);
                        eventCache.mEventBuffer.clear();
                    } else {
                        eventCache.mEventBuffer.put(bytes);
                    }
                }
                if (bArr != null) {
                    EventCache.A01(eventCache, bArr);
                    EventCache.A01(eventCache, bytes);
                    return;
                }
                return;
            }
            gk = oculusLoggingEventAnalytics2Impl.mEventBuilder;
        }
        gk.A07();
    }

    @Override // com.oculus.logging.OculusLoggingEvent
    public final void A4z() {
        A01(EventTag.EVENT_TAG, Integer.valueOf(EventTag.TagType.EVENT_IN_SAMPLING_CONFIG.ordinal()));
        GK gk = this.mEventBuilder;
        gk.A03 = GV.IS_EVENT_IN_DOWNLOADED_SAMPLING_CONFIG.getTag() | gk.A03;
    }

    @Override // com.oculus.logging.OculusLoggingEvent
    public final void A50() {
        A01(EventTag.EVENT_TAG, Integer.valueOf(EventTag.TagType.HAS_DOWNLOADED_SAMPLING_POLICY.ordinal()));
        GK gk = this.mEventBuilder;
        gk.A03 = GV.HAS_DOWNLOADED_SAMPLING_POLICY.getTag() | gk.A03;
    }

    @Override // com.oculus.logging.OculusLoggingEvent
    public final void A53() {
        A01(EventTag.EVENT_TAG, Integer.valueOf(EventTag.TagType.IS_NT_EVENTS.ordinal()));
        GK gk = this.mEventBuilder;
        gk.A03 = GV.IS_NT_EVENTS.getTag() | gk.A03;
    }

    @Override // com.oculus.logging.OculusLoggingEvent
    public final void A55() {
        A01(EventTag.EVENT_TAG, Integer.valueOf(EventTag.TagType.LOGGED_THROUGH_REACT_NATIVE.ordinal()));
        GK gk = this.mEventBuilder;
        gk.A03 = GV.LOGGED_THROUGH_REACT_NATIVE.getTag() | gk.A03;
    }

    @Override // com.oculus.logging.OculusLoggingEvent
    public final OculusLoggingEvent A59(long j) {
        this.mEventBuilder.A01(j);
        return this;
    }

    @Override // com.oculus.logging.OculusLoggingEvent
    public final void A5A() {
        A01(EventTag.EVENT_TAG, Integer.valueOf(EventTag.TagType.USL_ENABLED.ordinal()));
        GK gk = this.mEventBuilder;
        gk.A03 = GV.USL_ENABLED.getTag() | gk.A03;
    }

    @Override // com.oculus.logging.OculusLoggingEvent
    public final void A5C() {
        A01(EventTag.EVENT_TAG, Integer.valueOf(EventTag.TagType.LOGGED_THROUGH_XPLAT.ordinal()));
        GK gk = this.mEventBuilder;
        gk.A03 = GV.LOGGED_THROUGH_XPLAT.getTag() | gk.A03;
    }

    public OculusEventAnalytics2Impl(@Nullable String str, String str2, IErrorReporter iErrorReporter, C0219We we, GK gk) {
        this.mModuleName = str;
        this.mEventName = str2;
        this.mErrorReporter = iErrorReporter;
        this.mObjectMapper = we;
        this.mEventBuilder = gk;
        this.mIsSampled = gk.A08();
    }

    @Override // com.oculus.logging.OculusLoggingEvent
    public final OculusLoggingEvent A13(String str, int i) {
        Integer valueOf = Integer.valueOf(i);
        A01(str, valueOf);
        this.mEventBuilder.A03(str, valueOf);
        return this;
    }

    @Override // com.oculus.logging.OculusLoggingEvent
    public final OculusLoggingEvent A14(String str, long j) {
        Long valueOf = Long.valueOf(j);
        A01(str, valueOf);
        this.mEventBuilder.A03(str, valueOf);
        return this;
    }

    @Override // com.oculus.logging.OculusLoggingEvent
    public final OculusLoggingEvent A15(String str, @Nullable String str2) {
        A01(str, str2);
        this.mEventBuilder.A05(str, str2);
        return this;
    }

    @Override // com.oculus.logging.OculusLoggingEvent
    public final OculusLoggingEvent A16(String str, boolean z) {
        Boolean valueOf = Boolean.valueOf(z);
        A01(str, valueOf);
        this.mEventBuilder.A02(str, valueOf);
        return this;
    }

    @Override // com.oculus.logging.OculusLoggingEvent
    public final boolean A3I() {
        return this.mIsSampled;
    }
}
