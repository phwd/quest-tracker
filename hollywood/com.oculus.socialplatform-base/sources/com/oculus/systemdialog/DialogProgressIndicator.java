package com.oculus.systemdialog;

import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import org.json.JSONException;
import org.json.JSONObject;

public final class DialogProgressIndicator {
    public static final String DIALOG_PROGRESS_INDICATOR_PROGRESS_KEY = "progress";
    public static final String DIALOG_PROGRESS_INDICATOR_TYPE_KEY = "type";
    public static final String TAG = LoggingUtil.tag(DialogProgressIndicator.class);
    public final float mProgress;
    public final ProgressIndicatorType mType;

    public enum ProgressIndicatorType {
        BAR("bar"),
        SPINNER(DialogIcon.SPINNER_IPC_STRING);
        
        public final String mIPCString;

        public String getIPCString() {
            return this.mIPCString;
        }

        /* access modifiers changed from: public */
        ProgressIndicatorType(String str) {
            this.mIPCString = str;
        }
    }

    /* renamed from: com.oculus.systemdialog.DialogProgressIndicator$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$systemdialog$DialogProgressIndicator$ProgressIndicatorType;

        static {
            int[] iArr = new int[ProgressIndicatorType.values().length];
            $SwitchMap$com$oculus$systemdialog$DialogProgressIndicator$ProgressIndicatorType = iArr;
            try {
                iArr[ProgressIndicatorType.BAR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public static DialogProgressIndicator Bar(float f) {
        return new DialogProgressIndicator(ProgressIndicatorType.BAR, f);
    }

    public static DialogProgressIndicator Spinner() {
        return new DialogProgressIndicator(ProgressIndicatorType.SPINNER, AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z);
    }

    public JSONObject getDialogProgressIndicatorConfigurationIPCParcel() throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", this.mType.getIPCString());
            if (this.mType.ordinal() == 0) {
                jSONObject.put(DIALOG_PROGRESS_INDICATOR_PROGRESS_KEY, (double) this.mProgress);
            }
            return jSONObject;
        } catch (JSONException e) {
            Log.e(TAG, "Unable to get dialog progress indicator configuration JSON.", e);
            throw e;
        }
    }

    public float getProgress() {
        return this.mProgress;
    }

    public ProgressIndicatorType getType() {
        return this.mType;
    }

    public DialogProgressIndicator(ProgressIndicatorType progressIndicatorType, float f) {
        this.mType = progressIndicatorType;
        this.mProgress = f;
    }
}
