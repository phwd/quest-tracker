package com.oculus.systemdialog;

import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import org.json.JSONException;
import org.json.JSONObject;

public final class DialogProgressIndicator {
    private static final String DIALOG_PROGRESS_INDICATOR_PROGRESS_KEY = "progress";
    private static final String DIALOG_PROGRESS_INDICATOR_TYPE_KEY = "type";
    private static final String TAG = LoggingUtil.tag(DialogProgressIndicator.class);
    private final float mProgress;
    private final ProgressIndicatorType mType;

    /* access modifiers changed from: private */
    public enum ProgressIndicatorType {
        BAR("bar"),
        SPINNER("spinner");
        
        private final String mIPCString;

        private ProgressIndicatorType(String str) {
            this.mIPCString = str;
        }

        public String getIPCString() {
            return this.mIPCString;
        }
    }

    public static DialogProgressIndicator Bar(float f) {
        return new DialogProgressIndicator(ProgressIndicatorType.BAR, f);
    }

    public static DialogProgressIndicator Spinner() {
        return new DialogProgressIndicator(ProgressIndicatorType.SPINNER, 0.0f);
    }

    private DialogProgressIndicator(ProgressIndicatorType progressIndicatorType, float f) {
        this.mType = progressIndicatorType;
        this.mProgress = f;
    }

    public JSONObject getDialogProgressIndicatorConfigurationIPCParcel() throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", this.mType.getIPCString());
            if (AnonymousClass1.$SwitchMap$com$oculus$systemdialog$DialogProgressIndicator$ProgressIndicatorType[this.mType.ordinal()] == 1) {
                jSONObject.put("progress", (double) this.mProgress);
            }
            return jSONObject;
        } catch (JSONException e) {
            Log.e(TAG, "Unable to get dialog progress indicator configuration JSON.", e);
            throw e;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.systemdialog.DialogProgressIndicator$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$systemdialog$DialogProgressIndicator$ProgressIndicatorType = new int[ProgressIndicatorType.values().length];

        static {
            try {
                $SwitchMap$com$oculus$systemdialog$DialogProgressIndicator$ProgressIndicatorType[ProgressIndicatorType.BAR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }
}
