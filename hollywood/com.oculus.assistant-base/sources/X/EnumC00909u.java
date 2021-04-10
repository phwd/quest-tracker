package X;

/* renamed from: X.9u  reason: invalid class name and case insensitive filesystem */
public enum EnumC00909u {
    STARTED_LISTENING("started_listening"),
    END_OF_SPEECH("end_of_speech"),
    FINAL_TRANSCRIPTION("final_transcription"),
    RESULT_DELIVERED("result_delivered"),
    PREPARING_RESPONSE("preparing_response"),
    RESPONSE_PREPARED("response_prepared"),
    DELIVERING_RESPONSE("delivering_response"),
    ACP_TTS_START_RECEIVED("acp_tts_start_received"),
    ACP_RESULT_RECEIVED("acp_result_received"),
    OD_SERVER_FINAL_TRANSCRIPTION_RECD("od_server_final_transcription_recd"),
    OD_RBC_START("od_rbc_start"),
    OD_RBC_END("od_rbc_end"),
    OD_NLU_END("od_nlu_end"),
    OD_DMP_INTENT_SENT("od_dmp_intent_sent"),
    OD_DMP_RECEIVED_SERVER_RESPONSE("od_dmp_received_server_response"),
    OD_DMP_SERVER_RESPONSE_POSTED("od_dmp_server_response_posted"),
    OACR_TTS_START_RECEIVED("oacr_tts_start_received"),
    OACR_ON_ACTION_RECEIVED("oacr_on_action_received"),
    APP_STARTUP_BEGIN("app_startup_begin"),
    APP_STARTUP_END("app_startup_end"),
    SDK_STARTUP_BEGIN("sdk_startup_begin"),
    SDK_STARTUP_END("sdk_startup_end"),
    OACR_STARTUP_BEGIN("oacr_startup_begin"),
    OACR_STARTUP_END("oacr_startup_end"),
    RUNTIME_INIT_START("runtime_init_start"),
    RUNTIME_INIT_END("runtime_init_end");
    
    public String mPointName;

    /* access modifiers changed from: public */
    EnumC00909u(String str) {
        this.mPointName = str;
    }

    public String getPointName() {
        return this.mPointName;
    }
}
