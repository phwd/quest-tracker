package com.oculus.assistant.api;

import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public final class OculusAssistantMessageTypes {
    public static final String DICTATION_ERROR = "DictationErrorMessage";
    public static final String DICTATION_FINAL_RESPONSE = "DictationFinalResponseMessage";
    public static final String DICTATION_MIC_VOLUME = "DictationMicVolumeMessage";
    public static final String DICTATION_PARTIAL_RESPONSE = "DictationPartialResponseMessage";
    public static final String DICTATION_STATE_CHANGED = "DictationStateMessage";
    public static final String GET_TYPEAHEAD = "GetTypeaheadSuggestionMessage";
    public static final String ON_SUGGESTION_CLICKED = "OnSuggestionClickedMessage";
    public static final String ON_TYPEAHEAD_SUGGESTION = "OnTypeaheadSuggestionMessage";
    public static final String SPEAKING_STATE_CHANGED = "SpeakingStateMessage";
    public static final String START_DICTATION = "StartDictationMessage";
    public static final String START_SPEAKING = "StartSpeakingMessage";
    public static final String START_TYPEAHEAD_SESSION = "StartTypeaheadSessionMessage";
    public static final String STOP_DICTATION = "StopDictationMessage";
    public static final String STOP_SPEAKING = "StopSpeakingMessage";
    public static final String STOP_TYPEAHEAD_SESSION = "StopTypeaheadSessionMessage";
    public static final String SUBMIT_TRANSCRIPTION_FEEDBACK = "SubmitTranscriptionFeedbackMessage";
    public static final String VOICE_SDK_ACTIVATE_ASSISTANT_APPLICATION = "VoiceSdkActivateAssistantApplication";
    public static final String VOICE_SDK_ASSISTANT_COMMAND_CALLBACK = "VoiceSdkAssistantCommandCallback";
    public static final String VOICE_SDK_DEACTIVATE_ASSISTANT_APPLICATION = "VoiceSdkDeActivateAssistantApplication";
    public static final String VOICE_SDK_REGISTER_VOICE_COMMAND = "VoiceSdkRegisterVoiceCommand";
    public static final String VOICE_SDK_UNREGISTER_VOICE_COMMAND = "VoiceSdkUnregisterVoiceCommand";

    public static final class Dictation {
        public static final String CONFIGURATION = "configuration";
        public static final String ERROR_MESSAGE = "errorMessage";
        public static final String ERROR_TYPE = "errorType";
        public static final String INTERACTION_ID = "interactionId";
        public static final String MIC_VOLUME = "micVolume";
        public static final String RESPONSE = "transcriptionResponse";
        public static final String STATE = "transcriptionState";
    }

    public static final class DictationConfiguration {
        public static final String KEYBOARD_ACTION_TYPE = "action_type";
        public static final String KEYBOARD_INPUT_TYPE = "input_type";
        public static final String MULTIPHRASE = "multiphrase";
        public static final String SCENARIO = "scenario";
    }

    public static final class GetTypeaheadSuggestion {
        public static final String INPUT_CONTEXT = "inputContext";
        public static final String INPUT_TEXT = "inputText";
        public static final String REQUESTING_PANEL = "requestingPanel";
    }

    public static final class OnSuggestionClicked {
        public static final String KEYSTROKES_SAVED = "keystrokesSaved";
    }

    public static final class OnTypeaheadSuggestion {
        public static final String OTHER_SUGGESTIONS = "otherSuggestions";
        public static final String OTHER_SUGGESTIONS_LIST = "otherSuggestionsList";
        public static final String SUGGESTED_WORD = "suggestedWord";
        public static final String SUGGESTION_SEPARATOR = ";";
        public static final String SUPPLEMENTAL_DICTIONARY = "fromSupplementalDict";
    }

    public static final class SpeakingStateProperties {
        public static final String STATE = "speakingState";
    }

    public static final class SpeechStates {
        public static final String INACTIVE = "INACTIVE";
        public static final String SPEAKING = "SPEAKING";
    }

    public static final class StartSpeakingProperties {
        public static final String MESSSAGE = "message";
    }

    public static final class StartTypeAhead {
        public static final String CONFIGURATION = "configuration";
    }

    public static final class SubmitTranscriptionFeedback {
        public static final String FEEDBACK = "feedback";
        public static final String INTERACTION_ID = "interactionId";
        public static final String NEGATIVE = "negative";
        public static final String POSITIVE = "positive";
    }

    public static final class TypeAheadConfiguration {
        public static final String KEYBOARD_INPUT_TYPE = "input_type";
        public static final String SCENARIO = "scenario";
    }

    public static final class VoiceSdk {
        public static final String ACTION_ID = "actionId";
        public static final String APP_ID = "appId";
        public static final String COMMAND_INPUT = "commandInput";
        public static final String COMMAND_OUTPUT = "commandOutput";
        public static final String DEBUG_PHRASE = "debugPhrase";
        public static final String JSON_RESPONSE = "jsonResponse";
        public static final String SLOTS = "slots";
    }
}
