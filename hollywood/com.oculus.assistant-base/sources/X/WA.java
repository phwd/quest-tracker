package X;

import java.util.HashMap;
import java.util.Map;

public final class WA {
    public Map A00;

    public WA() {
        HashMap hashMap = new HashMap();
        this.A00 = hashMap;
        hashMap.put("StartDictationMessage", C1209vO.class);
        this.A00.put("StopDictationMessage", C1210vP.class);
        this.A00.put("DictationFinalResponseMessage", C1207vM.class);
        this.A00.put("DictationPartialResponseMessage", C1206vL.class);
        this.A00.put("DictationStateMessage", C1208vN.class);
        this.A00.put("DictationMicVolumeMessage", C1205vK.class);
        this.A00.put("DictationErrorMessage", C1204vJ.class);
        this.A00.put("StartTypeaheadSessionMessage", C1215vV.class);
        this.A00.put("StopTypeaheadSessionMessage", C1216vW.class);
        this.A00.put("GetTypeaheadSuggestionMessage", C1218vY.class);
        this.A00.put("OnTypeaheadSuggestionMessage", C1219vZ.class);
        this.A00.put("OnSuggestionClickedMessage", C1217vX.class);
        this.A00.put("SubmitTranscriptionFeedbackMessage", C1211vQ.class);
        this.A00.put("VoiceSdkActivateAssistantApplication", C1220va.class);
        this.A00.put("VoiceSdkDeActivateAssistantApplication", C1222vc.class);
        this.A00.put("VoiceSdkAssistantCommandCallback", C1221vb.class);
        this.A00.put("VoiceSdkRegisterVoiceCommand", C1223vd.class);
        this.A00.put("VoiceSdkUnregisterVoiceCommand", C1224ve.class);
        this.A00.put("StartSpeakingMessage", C1202vH.class);
        this.A00.put("StopSpeakingMessage", C1203vI.class);
        this.A00.put("SpeakingStateMessage", C1201vG.class);
        this.A00.put("ConvertTextToSpeechMessage", vR.class);
        this.A00.put("TTSSpeechAudioMessage", C1212vS.class);
        this.A00.put("StopTTSMessage", C1213vT.class);
        this.A00.put("TTSSpeechStateMessage", C1214vU.class);
    }
}
