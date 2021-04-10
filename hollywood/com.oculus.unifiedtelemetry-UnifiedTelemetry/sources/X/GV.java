package X;

import com.facebook.acra.util.StatFsUtil;
import com.squareup.okhttp.internal.framed.FramedStream;

public enum GV {
    USL_ENABLED(1),
    LOGGED_THROUGH_REACT_NATIVE(2),
    IS_NT_EVENTS(4),
    LOGGED_FROM_COMPONENT_SCRIPT(8),
    LOGGED_THROUGH_XPLAT(16),
    LOGGED_FROM_VIEWPOINT(32),
    HAS_DOWNLOADED_SAMPLING_POLICY(64),
    IS_EVENT_IN_DOWNLOADED_SAMPLING_CONFIG(128),
    IS_EVENT_IN_SESSIONLESS_SAMPLING_CONFIG(256),
    IS_EVENT_LOGGED_DURING_STARTUP(512),
    IS_EVENT_LOGGED_UNSAMPLED(StatFsUtil.IN_KILO_BYTE),
    LOGGED_FROM_BLOKS(FramedStream.FramedDataSink.EMIT_BUFFER_SIZE);
    
    public final long mVal;

    /* access modifiers changed from: public */
    GV(long j) {
        this.mVal = j;
    }

    public long getTag() {
        return this.mVal;
    }
}
