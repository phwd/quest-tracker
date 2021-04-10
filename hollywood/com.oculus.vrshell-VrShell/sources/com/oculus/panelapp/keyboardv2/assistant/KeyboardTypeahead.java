package com.oculus.panelapp.keyboardv2.assistant;

import android.os.Handler;
import android.util.Log;
import com.oculus.assistant.api.OculusAssistantTextSDK;
import com.oculus.assistant.api.TypeAheadConfiguration;
import com.oculus.assistant.api.TypeAheadListener;
import com.oculus.panelapp.keyboardv2.KeyboardPanelApp;
import com.oculus.panelapp.keyboardv2.assistant.KeyboardTypeahead;

public class KeyboardTypeahead {
    private static final String ENDS_WITH_PUNCTUATION_REGEX = ".*[.?!,;]";
    private static final String PUNCTUATIONS = ".?!,;";
    private static final String TAG = "KeyboardTypeahead";
    private OculusAssistantTextSDK mAssistantTextSDK;
    private final KeyboardPanelApp mKeyboardPanelApp;
    private final Handler mMainLooperHandler;
    private String mRequestingPanel;
    private TypeAheadConfiguration mTypeAheadConfiguration = new TypeAheadConfiguration();
    private TypeAheadListener mTypeAheadListener = new TypeAheadListener() {
        /* class com.oculus.panelapp.keyboardv2.assistant.KeyboardTypeahead.AnonymousClass1 */

        @Override // com.oculus.assistant.api.TypeAheadListener
        public void onSuggestion(String str, String[] strArr) {
            KeyboardTypeahead.this.mMainLooperHandler.post(new Runnable(str, strArr) {
                /* class com.oculus.panelapp.keyboardv2.assistant.$$Lambda$KeyboardTypeahead$1$Fye_532rnqZo8Hiqdo3NLeeFUmE */
                private final /* synthetic */ String f$1;
                private final /* synthetic */ String[] f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void run() {
                    KeyboardTypeahead.AnonymousClass1.this.lambda$onSuggestion$0$KeyboardTypeahead$1(this.f$1, this.f$2);
                }
            });
        }

        public /* synthetic */ void lambda$onSuggestion$0$KeyboardTypeahead$1(String str, String[] strArr) {
            KeyboardTypeahead.this.mKeyboardPanelApp.setTypeaheadSuggestions(str, strArr);
        }
    };

    public static String getAdditionalText(String str, String str2) {
        if (str2.isEmpty()) {
            return str2;
        }
        if (str.isEmpty() || str.endsWith(" ")) {
            return str2 + " ";
        } else if (str.matches(ENDS_WITH_PUNCTUATION_REGEX)) {
            return " " + str2 + " ";
        } else {
            int length = str.length();
            int min = Math.min(length, str2.length());
            int i = 0;
            for (int i2 = 1; i2 <= min; i2++) {
                if (str.substring(length - i2).equalsIgnoreCase(str2.substring(0, i2))) {
                    i = i2;
                }
            }
            return str2.substring(i) + " ";
        }
    }

    public KeyboardTypeahead(KeyboardPanelApp keyboardPanelApp) {
        this.mKeyboardPanelApp = keyboardPanelApp;
        this.mMainLooperHandler = new Handler(keyboardPanelApp.getContext().getMainLooper());
        this.mAssistantTextSDK = new OculusAssistantTextSDK(keyboardPanelApp.getContext());
    }

    public void startTypeahead(String str) {
        Log.d(TAG, "startTypeahead");
        this.mRequestingPanel = str;
        TypeAheadConfiguration typeAheadConfiguration = this.mTypeAheadConfiguration;
        typeAheadConfiguration.scenario = str;
        this.mAssistantTextSDK.startTypeAhead(this.mTypeAheadListener, typeAheadConfiguration);
    }

    public void stopTypeahead(String str) {
        Log.d(TAG, "stopTypeAhead");
        this.mAssistantTextSDK.stopTypeAhead(str);
    }

    public void getTypeahead(String str) {
        Log.d(TAG, "getTypeahead " + str.length());
        this.mAssistantTextSDK.predictWords(str, "");
    }

    public void typeaheadUsed(String str) {
        Log.d(TAG, "typeaheadUsed " + str.length());
        this.mAssistantTextSDK.logEntryUsed(str.length());
    }
}
