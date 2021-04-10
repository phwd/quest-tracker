package X;

import com.facebook.infer.annotation.NullsafeStrict;
import com.oculus.logging.analytics2.DebugEventBaseParameterHandler;

@NullsafeStrict
public enum GO {
    CLIENT_EVENT("client_event", DebugEventBaseParameterHandler.ParamKeys.EXTRA),
    EXPERIMENT("experiment", "result");
    
    public final String mExtraJsonKey;
    public final String mProtocolValue;

    /* access modifiers changed from: public */
    GO(String str, String str2) {
        this.mProtocolValue = str;
        this.mExtraJsonKey = str2;
    }

    public String getExtraJsonKey() {
        return this.mExtraJsonKey;
    }

    public String getProtocolValue() {
        return this.mProtocolValue;
    }

    public String toString() {
        return this.mProtocolValue;
    }
}
