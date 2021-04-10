package com.oculus.horizon.logging;

import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import X.AnonymousClass0QC;
import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.Dependencies;
import com.oculus.logging.utils.Event;
import com.oculus.logging.utils.EventManager;
import javax.annotation.Nullable;
import javax.inject.Inject;

@Deprecated
@Dependencies({"_UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID"})
@ApplicationScoped
public class OculusLogger {
    public static volatile OculusLogger _UL__ULSEP_com_oculus_horizon_logging_OculusLogger_ULSEP_INSTANCE;
    public AnonymousClass0QC _UL_mInjectionContext;

    public static class EventLinkToPdpModal {

        public static class Data {
            public final String mEventId;
            public final boolean mIsSubscribed;
            public final Source mSource;
        }

        public enum Source {
            VIA_JOIN_NOW,
            VIA_SUBSCRIBE
        }
    }

    public class VideoUploaderLogger {
        @Nullable
        public Long audioInputFileSize = null;
        @Nullable
        public Long muxedVideoFileSize = null;
        public final String recordingUUID;
        public final String reportID;
        @Nullable
        public String uploadSessionID = null;
        @Nullable
        public Long videoInputFileSize = null;

        public VideoUploaderLogger(String str, String str2) {
            this.recordingUUID = str;
            this.reportID = str2;
        }

        public static void A00(VideoUploaderLogger videoUploaderLogger, Event event) {
            event.A15("recording_uuid", videoUploaderLogger.recordingUUID);
            event.A15("report_id", videoUploaderLogger.reportID);
            Long l = videoUploaderLogger.videoInputFileSize;
            if (l != null) {
                event.A14(LoggingKeys.VIDEO_INPUT_FILE_SIZE, l.longValue());
            }
            Long l2 = videoUploaderLogger.audioInputFileSize;
            if (l2 != null) {
                event.A14(LoggingKeys.AUDIO_INPUT_FILE_SIZE, l2.longValue());
            }
            Long l3 = videoUploaderLogger.muxedVideoFileSize;
            if (l3 != null) {
                event.A14(LoggingKeys.MUXED_VIDEO_FILE_SIZE, l3.longValue());
            }
            String str = videoUploaderLogger.uploadSessionID;
            if (str != null) {
                event.A15(LoggingKeys.UPLOAD_SESSION_ID, str);
            }
            event.A5L();
        }
    }

    public static void A00(OculusLogger oculusLogger, String str, String str2, String[] strArr, String[] strArr2, String str3) {
        for (int i = 0; i < strArr.length; i++) {
            Event A22 = ((EventManager) AnonymousClass0J2.A03(0, 242, oculusLogger._UL_mInjectionContext)).A22(str);
            A22.A1G();
            A22.A15("event_value", strArr[i]);
            A22.A15(LoggingKeys.ITEM_STATUS, strArr2[i]);
            A22.A15(LoggingKeys.INSTALL_ID, str2);
            A22.A15("source", str3);
            A22.A5L();
        }
    }

    public final void A01(String str, String str2) {
        if (str == null) {
            str = "";
        }
        Event A22 = ((EventManager) AnonymousClass0J2.A03(0, 242, this._UL_mInjectionContext)).A22(LoggingEvents.ABUSE_RECORDING_START);
        A22.A15("recording_uuid", str);
        A22.A15("error", str2);
        A22.A5L();
    }

    public final void A02(String str, String str2, boolean z, String str3) {
        if (str == null) {
            str = "";
        }
        Event A22 = ((EventManager) AnonymousClass0J2.A03(0, 242, this._UL_mInjectionContext)).A22(LoggingEvents.ABUSE_RECORDING_CANCEL);
        A22.A15("recording_uuid", str);
        A22.A15("source", str2);
        A22.A16("success", z);
        A22.A15("error", str3);
        A22.A5L();
    }

    public final void A03(String str, String str2, boolean z, String str3) {
        if (str == null) {
            str = "";
        }
        Event A22 = ((EventManager) AnonymousClass0J2.A03(0, 242, this._UL_mInjectionContext)).A22(LoggingEvents.ABUSE_RECORDING_STOP);
        A22.A15("recording_uuid", str);
        A22.A15("source", str2);
        A22.A16("success", z);
        A22.A15("error", str3);
        A22.A5L();
    }

    @Inject
    public OculusLogger(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(1, r3);
    }
}
