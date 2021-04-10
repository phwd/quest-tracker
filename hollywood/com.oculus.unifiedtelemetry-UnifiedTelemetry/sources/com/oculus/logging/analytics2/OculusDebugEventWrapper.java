package com.oculus.logging.analytics2;

import X.Mu;
import X.YE;
import android.content.SharedPreferences;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.logging.OculusLoggingEvent;
import com.oculus.logging.analytics2.DebugEventBaseParameterHandler;
import com.oculus.time.Clock;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class OculusDebugEventWrapper implements OculusLoggingEvent {
    public static final long INVALID_TIME = -1;
    public static final String TAG = "OculusDebugEventWrapper";
    public final OculusLoggingEvent mActualEvent;
    public final DebugEventBaseParameterHandler mBaseParamsHandler = new DebugEventBaseParameterHandler();
    public final Clock mClock;
    public final EventBuilderConfig mConfig;
    public final String mEventName;
    public long mLoggedWallTimeMillis = -1;
    @Nullable
    public final String mModuleName;

    @Override // com.oculus.logging.OculusLoggingEvent
    public final OculusLoggingEvent A12(String str, String str2) {
        this.mBaseParamsHandler.A00(str, str2);
        this.mActualEvent.A12(str, str2);
        return this;
    }

    @Override // com.oculus.logging.OculusLoggingEvent
    public final OculusLoggingEvent A13(String str, int i) {
        this.mActualEvent.A13(str, i);
        return this;
    }

    @Override // com.oculus.logging.OculusLoggingEvent
    public final OculusLoggingEvent A14(String str, long j) {
        this.mActualEvent.A14(str, j);
        return this;
    }

    @Override // com.oculus.logging.OculusLoggingEvent
    public final OculusLoggingEvent A15(String str, @Nullable String str2) {
        this.mActualEvent.A15(str, str2);
        return this;
    }

    @Override // com.oculus.logging.OculusLoggingEvent
    public final OculusLoggingEvent A16(String str, boolean z) {
        this.mActualEvent.A16(str, z);
        return this;
    }

    @Override // com.oculus.logging.OculusLoggingEvent
    public final OculusLoggingEvent A1A(String str) {
        this.mActualEvent.A1A(str);
        return this;
    }

    @Override // com.oculus.logging.OculusLoggingEvent
    @Nullable
    public final YE A2R() {
        return this.mActualEvent.A2R();
    }

    @Override // com.oculus.logging.OculusLoggingEvent
    public final boolean A3I() {
        return this.mActualEvent.A3I();
    }

    @Override // com.oculus.logging.OculusLoggingEvent
    public final void A3Q() {
        SharedPreferences A00;
        if (this.mLoggedWallTimeMillis == -1) {
            this.mLoggedWallTimeMillis = System.currentTimeMillis();
        }
        DebugEventBaseParameterHandler debugEventBaseParameterHandler = this.mBaseParamsHandler;
        YE A2R = A2R();
        String str = this.mEventName;
        String str2 = this.mModuleName;
        long j = this.mLoggedWallTimeMillis;
        if (A2R != null) {
            JSONObject jSONObject = new JSONObject();
            int i = A2R.A00;
            for (int i2 = 0; i2 < i; i2++) {
                String A0B = A2R.A0B(i2);
                try {
                    jSONObject.put(A0B, A2R.A0A(i2));
                } catch (JSONException e) {
                    Mu.A0B(JsonAndParamsCollectionHelper.TAG, e, "Failed to add (%1$s) to JSON Object", A0B);
                }
            }
            debugEventBaseParameterHandler.A00(DebugEventBaseParameterHandler.ParamKeys.EXTRA, jSONObject);
        }
        debugEventBaseParameterHandler.A00(DebugEventBaseParameterHandler.ParamKeys.TIME, Double.valueOf(((double) j) / 1000.0d));
        debugEventBaseParameterHandler.A00("name", str);
        debugEventBaseParameterHandler.A00(DebugEventBaseParameterHandler.ParamKeys.MODULE_NAME, str2);
        try {
            if (!(this.mBaseParamsHandler.mBaseParameters.toString(5) == null || (A00 = EventBuilderConfig.A00(this.mConfig)) == null)) {
                A00.getBoolean(EventBuilderConfig.KEY_LOGCAT_ENABLED, false);
            }
        } catch (JSONException e2) {
            Mu.A08(JsonAndParamsCollectionHelper.TAG, e2, "Failed to convert mBaseParameters to json string");
        }
        this.mActualEvent.A3Q();
    }

    @Override // com.oculus.logging.OculusLoggingEvent
    public final void A4z() {
        this.mActualEvent.A4z();
    }

    @Override // com.oculus.logging.OculusLoggingEvent
    public final void A50() {
        this.mActualEvent.A50();
    }

    @Override // com.oculus.logging.OculusLoggingEvent
    public final void A53() {
        this.mActualEvent.A53();
    }

    @Override // com.oculus.logging.OculusLoggingEvent
    public final void A55() {
        this.mActualEvent.A55();
    }

    @Override // com.oculus.logging.OculusLoggingEvent
    public final OculusLoggingEvent A59(long j) {
        this.mLoggedWallTimeMillis = j;
        this.mActualEvent.A59(j);
        return this;
    }

    @Override // com.oculus.logging.OculusLoggingEvent
    public final void A5A() {
        this.mActualEvent.A5A();
    }

    @Override // com.oculus.logging.OculusLoggingEvent
    public final void A5C() {
        this.mActualEvent.A5C();
    }

    public OculusDebugEventWrapper(OculusLoggingEvent oculusLoggingEvent, @Nullable String str, String str2, EventBuilderConfig eventBuilderConfig, Clock clock) {
        this.mEventName = str2;
        this.mModuleName = str;
        this.mConfig = eventBuilderConfig;
        this.mClock = clock;
        this.mActualEvent = oculusLoggingEvent;
    }
}
