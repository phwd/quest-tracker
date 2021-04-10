package com.oculus.horizon.cast;

import X.AnonymousClass0NO;
import org.json.JSONException;
import org.json.JSONObject;

public class Message {
    public static final int DEFAULT_VIDEO_FPS = 30;
    public static final int DEFAULT_VIDEO_HEIGHT = 512;
    public static final VideoSpec DEFAULT_VIDEO_SPEC;
    public static final int DEFAULT_VIDEO_WIDTH = 512;
    public static final Message INVALID_MESSAGE;
    public static final String KEY_ALLOW_SCREEN_RECORDING = "allow_screen_recording";
    public static final String KEY_ALLOW_VR_CASTING = "allow_vr_casting";
    public static final String KEY_DATA = "data";
    public static final String KEY_ENABLE_DATACHANNEL = "enable_data_channel";
    public static final String KEY_ENABLE_SEND_APP_INFO = "enable_send_app_info";
    public static final String KEY_ERROR_CODE = "error";
    public static final String KEY_PACKAGE_NAME = "package_name";
    public static final String KEY_SESSION_ID = "sessionId";
    public static final String KEY_TYPE = "type";
    public static final String KEY_VIDEO_FPS = "video_fps";
    public static final String KEY_VIDEO_HEIGHT = "video_height";
    public static final String KEY_VIDEO_WIDTH = "video_width";
    public static final String TAG = "Message";
    public boolean mAllowScreenRecording;
    public boolean mAllowVrCasting;
    public final String mData;
    public boolean mEnableDataChannel;
    public boolean mEnableSendAppInfo;
    public ErrorCode mErrorCode;
    public String mPackageName;
    public final String mSessionId;
    public VideoSpec mSpec;
    public final Type mType;

    /* renamed from: com.oculus.horizon.cast.Message$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$horizon$cast$Message$Type;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0036 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0024 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002d */
        static {
            /*
                com.oculus.horizon.cast.Message$Type[] r0 = com.oculus.horizon.cast.Message.Type.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.oculus.horizon.cast.Message.AnonymousClass1.$SwitchMap$com$oculus$horizon$cast$Message$Type = r2
                com.oculus.horizon.cast.Message$Type r0 = com.oculus.horizon.cast.Message.Type.START     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.oculus.horizon.cast.Message$Type r0 = com.oculus.horizon.cast.Message.Type.OFFER     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                com.oculus.horizon.cast.Message$Type r0 = com.oculus.horizon.cast.Message.Type.ANSWER     // Catch:{ NoSuchFieldError -> 0x0024 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0024 }
                r0 = 3
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0024 }
            L_0x0024:
                com.oculus.horizon.cast.Message$Type r0 = com.oculus.horizon.cast.Message.Type.STOP     // Catch:{ NoSuchFieldError -> 0x002d }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x002d }
                r0 = 4
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x002d }
            L_0x002d:
                com.oculus.horizon.cast.Message$Type r0 = com.oculus.horizon.cast.Message.Type.ERROR     // Catch:{ NoSuchFieldError -> 0x0036 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0036 }
                r0 = 5
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0036 }
            L_0x0036:
                com.oculus.horizon.cast.Message$Type r0 = com.oculus.horizon.cast.Message.Type.APPINFO     // Catch:{ NoSuchFieldError -> 0x003f }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x003f }
                r0 = 6
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x003f }
            L_0x003f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.cast.Message.AnonymousClass1.<clinit>():void");
        }
    }

    public enum ErrorCode {
        NONE(0),
        GENERIC_ERROR(1),
        UNKNOWN_MESSAGE(2);
        
        public final int mValue;

        /* access modifiers changed from: public */
        ErrorCode(int i) {
            this.mValue = i;
        }
    }

    public enum Type {
        START(0),
        OFFER(1),
        ANSWER(2),
        STOP(3),
        ERROR(4),
        APPINFO(5);
        
        public final int mValue;

        /* access modifiers changed from: public */
        Type(int i) {
            this.mValue = i;
        }

        public String description() {
            switch (ordinal()) {
                case 0:
                    return "START";
                case 1:
                    return "OFFER";
                case 2:
                    return "ANSWER";
                case 3:
                    return "STOP";
                case 4:
                    return "ERROR";
                case 5:
                    return "APPINFO";
                default:
                    throw new Error("Unexpected message type.");
            }
        }
    }

    static {
        VideoSpec videoSpec = new VideoSpec(512, 512, 30);
        DEFAULT_VIDEO_SPEC = videoSpec;
        INVALID_MESSAGE = new Message(Type.ERROR, ErrorCode.UNKNOWN_MESSAGE, videoSpec);
    }

    public static Message A00(JSONObject jSONObject) {
        String str;
        int i;
        String str2;
        int i2;
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        int i3;
        int i4;
        try {
            int i5 = jSONObject.getInt("type");
            String str3 = "";
            if (jSONObject.has(KEY_SESSION_ID)) {
                str = jSONObject.getString(KEY_SESSION_ID);
            } else {
                str = str3;
            }
            if (jSONObject.has("error")) {
                i = jSONObject.getInt("error");
            } else {
                i = 0;
            }
            if (jSONObject.has("data")) {
                str2 = jSONObject.getString("data");
            } else {
                str2 = str3;
            }
            int i6 = 512;
            if (!jSONObject.has("video_height") || (i2 = jSONObject.getInt("video_height")) <= 0) {
                i2 = 512;
            }
            if (jSONObject.has("video_width") && (i4 = jSONObject.getInt("video_width")) > 0) {
                i6 = i4;
            }
            int i7 = 30;
            if (jSONObject.has("video_fps") && (i3 = jSONObject.getInt("video_fps")) > 0) {
                i7 = i3;
            }
            if (jSONObject.has(KEY_ENABLE_DATACHANNEL)) {
                z = jSONObject.getBoolean(KEY_ENABLE_DATACHANNEL);
            } else {
                z = false;
            }
            if (jSONObject.has(KEY_ENABLE_SEND_APP_INFO)) {
                z2 = jSONObject.getBoolean(KEY_ENABLE_SEND_APP_INFO);
            } else {
                z2 = false;
            }
            if (jSONObject.has("package_name")) {
                str3 = jSONObject.getString("package_name");
            }
            if (jSONObject.has(KEY_ALLOW_SCREEN_RECORDING)) {
                z3 = jSONObject.getBoolean(KEY_ALLOW_SCREEN_RECORDING);
            } else {
                z3 = false;
            }
            if (jSONObject.has(KEY_ALLOW_VR_CASTING)) {
                z4 = jSONObject.getBoolean(KEY_ALLOW_VR_CASTING);
            } else {
                z4 = false;
            }
            if (i5 >= 0 && i5 < Type.values().length) {
                if (i5 == 5) {
                    return new Message(str, Type.values()[i5], str2, str3, z3, z4);
                }
                return new Message(str, Type.values()[i5], str2, ErrorCode.values()[i], new VideoSpec(i2, i6, i7), z, z2);
            }
        } catch (JSONException e) {
            AnonymousClass0NO.A0E(TAG, "Failed parse json %s", jSONObject, e);
        }
        return INVALID_MESSAGE;
    }

    public final JSONObject A01() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (this.mType.ordinal() == 5) {
                jSONObject.put("package_name", this.mPackageName);
                jSONObject.put(KEY_ALLOW_SCREEN_RECORDING, this.mAllowScreenRecording);
                jSONObject.put(KEY_ALLOW_VR_CASTING, this.mAllowVrCasting);
            }
            jSONObject.put(KEY_SESSION_ID, this.mSessionId);
            jSONObject.put("type", this.mType.mValue);
            jSONObject.put("data", this.mData);
            jSONObject.put("error", this.mErrorCode.mValue);
            VideoSpec videoSpec = this.mSpec;
            if (videoSpec != null) {
                jSONObject.put("video_height", videoSpec.mHeight);
                jSONObject.put("video_width", this.mSpec.mWidth);
                jSONObject.put("video_fps", this.mSpec.mFps);
            }
            if (this.mEnableDataChannel) {
                jSONObject.put(KEY_ENABLE_DATACHANNEL, true);
            }
            if (this.mEnableSendAppInfo) {
                jSONObject.put(KEY_ENABLE_SEND_APP_INFO, true);
                return jSONObject;
            }
        } catch (JSONException e) {
            AnonymousClass0NO.A0E(TAG, "failed to create json object %s", toString(), e);
        }
        return jSONObject;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("{sessionId:");
        sb.append(this.mSessionId);
        sb.append(", ");
        sb.append("type");
        sb.append(":");
        sb.append(this.mType.mValue);
        sb.append(", ");
        sb.append("data");
        sb.append(":");
        sb.append(this.mData);
        sb.append(", VideoSpec :");
        sb.append(this.mSpec.toString());
        sb.append(", ");
        sb.append("error");
        sb.append(":");
        sb.append(this.mErrorCode.mValue);
        sb.append(",");
        sb.append(KEY_ENABLE_DATACHANNEL);
        sb.append(":");
        sb.append(this.mEnableDataChannel);
        sb.append(",");
        sb.append("package_name");
        sb.append(":");
        sb.append(this.mPackageName);
        sb.append(",");
        sb.append(KEY_ALLOW_SCREEN_RECORDING);
        sb.append(":");
        sb.append(this.mAllowScreenRecording);
        sb.append(",");
        sb.append(KEY_ALLOW_VR_CASTING);
        sb.append(":");
        sb.append(this.mAllowVrCasting);
        sb.append(",");
        sb.append(KEY_ENABLE_SEND_APP_INFO);
        sb.append(":");
        sb.append(this.mEnableSendAppInfo);
        sb.append("}");
        return sb.toString();
    }

    public Message(String str, Type type, String str2) {
        this.mSpec = DEFAULT_VIDEO_SPEC;
        this.mErrorCode = ErrorCode.NONE;
        this.mEnableDataChannel = false;
        this.mPackageName = "";
        this.mAllowScreenRecording = false;
        this.mAllowVrCasting = false;
        this.mEnableSendAppInfo = false;
        this.mSessionId = str;
        this.mType = type;
        this.mData = str2;
    }

    public Message(Type type, ErrorCode errorCode, VideoSpec videoSpec) {
        this.mSpec = DEFAULT_VIDEO_SPEC;
        this.mErrorCode = ErrorCode.NONE;
        this.mEnableDataChannel = false;
        this.mPackageName = "";
        this.mAllowScreenRecording = false;
        this.mAllowVrCasting = false;
        this.mEnableSendAppInfo = false;
        this.mSessionId = "";
        this.mType = type;
        this.mData = "Invalid Message type";
        this.mSpec = videoSpec;
        this.mErrorCode = errorCode;
    }

    public Message(String str, Type type, String str2, ErrorCode errorCode, VideoSpec videoSpec, boolean z, boolean z2) {
        this.mSpec = DEFAULT_VIDEO_SPEC;
        this.mErrorCode = ErrorCode.NONE;
        this.mEnableDataChannel = false;
        this.mPackageName = "";
        this.mAllowScreenRecording = false;
        this.mAllowVrCasting = false;
        this.mEnableSendAppInfo = false;
        this.mSessionId = str;
        this.mType = type;
        this.mData = str2;
        this.mSpec = videoSpec;
        this.mErrorCode = errorCode;
        this.mEnableDataChannel = z;
        this.mEnableSendAppInfo = z2;
    }

    public Message(String str, Type type, String str2, String str3, boolean z, boolean z2) {
        this.mSpec = DEFAULT_VIDEO_SPEC;
        this.mErrorCode = ErrorCode.NONE;
        this.mEnableDataChannel = false;
        this.mPackageName = "";
        this.mAllowScreenRecording = false;
        this.mAllowVrCasting = false;
        this.mEnableSendAppInfo = false;
        this.mSessionId = str;
        this.mType = type;
        this.mData = str2;
        this.mPackageName = str3;
        this.mAllowScreenRecording = z;
        this.mAllowVrCasting = z2;
    }
}
