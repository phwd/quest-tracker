package com.oculus.fitnesstracker;

import android.content.Context;
import android.database.Cursor;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.proguard.annotations.DoNotStrip;
import com.oculus.common.build.BuildConfig;
import com.oculus.os.SettingsManager;
import com.oculus.os.SettingsObserverCallback;
import com.oculus.widgets.BaseWidget;
import org.json.JSONException;
import org.json.JSONObject;

public class FitnessWidget extends BaseWidget {
    private static final String SETTING_FITNESS_TRACKING_OVERLAY_POSITION = "fitness_tracking_overlay_position";
    private static final String SETTING_FITNESS_TRACKING_OVERLAY_POSITION_DEFAULT_KEY = "default";
    private static final String TAG = "FitnessWidget";
    private final View mContentView = LayoutInflater.from(this.mContext).inflate(R.layout.widget, (ViewGroup) null);
    private final Context mContext;
    private BaseWidget.WidgetPosition mPosition;
    private SettingsManager mSettingsManager = new SettingsManager();
    private SettingsObserverCallback mSettingsObserverCallback = new SettingsObserverCallback() {
        /* class com.oculus.fitnesstracker.FitnessWidget.AnonymousClass1 */

        @DoNotStrip
        public final void onSettingChange(String str) {
            String str2 = FitnessWidget.TAG;
            Log.d(str2, "Setting changed:" + str);
            FitnessWidget.this.updatePosition();
        }
    };

    @Override // com.oculus.widgets.BaseWidget
    public float getOffsetPitch() {
        return 0.2f;
    }

    @Override // com.oculus.widgets.BaseWidget
    public float getOffsetX() {
        return 0.0f;
    }

    @Override // com.oculus.widgets.BaseWidget
    public float getOffsetY() {
        return 2.2f;
    }

    @Override // com.oculus.widgets.BaseWidget
    public float getOffsetYaw() {
        return 0.0f;
    }

    public FitnessWidget(Context context) {
        super(context);
        this.mContext = context;
        this.mSettingsManager.registerSettingsObserver(SETTING_FITNESS_TRACKING_OVERLAY_POSITION, this.mSettingsObserverCallback, new Handler(this.mContext.getMainLooper()));
        updatePosition();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0120, code lost:
        if (r6.isClosed() == false) goto L_0x0135;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0133, code lost:
        if (r6.isClosed() == false) goto L_0x0135;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0135, code lost:
        r6.close();
     */
    @Override // com.oculus.widgets.BaseWidget
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.Bitmap refresh() {
        /*
        // Method dump skipped, instructions count: 341
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.fitnesstracker.FitnessWidget.refresh():android.graphics.Bitmap");
    }

    private static double getCursorDouble(Cursor cursor, String str) {
        if (cursor.isNull(cursor.getColumnIndex(str))) {
            return 0.0d;
        }
        return cursor.getDouble(cursor.getColumnIndex(str));
    }

    @Override // com.oculus.widgets.BaseWidget
    public void destroy() {
        Log.d(TAG, "destroy");
        this.mSettingsManager.unregisterSettingsObserver(SETTING_FITNESS_TRACKING_OVERLAY_POSITION, this.mSettingsObserverCallback);
    }

    @Override // com.oculus.widgets.BaseWidget
    public BaseWidget.WidgetPosition getPosition() {
        return this.mPosition;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void updatePosition() {
        try {
            String string = new JSONObject(this.mSettingsManager.getString(SETTING_FITNESS_TRACKING_OVERLAY_POSITION, BuildConfig.PROVIDER_SUFFIX)).getString(SETTING_FITNESS_TRACKING_OVERLAY_POSITION_DEFAULT_KEY);
            char c = 65535;
            switch (string.hashCode()) {
                case -1383228885:
                    if (string.equals("bottom")) {
                        c = 0;
                        break;
                    }
                    break;
                case 115029:
                    if (string.equals("top")) {
                        c = 3;
                        break;
                    }
                    break;
                case 3317767:
                    if (string.equals("left")) {
                        c = 1;
                        break;
                    }
                    break;
                case 108511772:
                    if (string.equals("right")) {
                        c = 2;
                        break;
                    }
                    break;
            }
            if (c == 0) {
                this.mPosition = BaseWidget.WidgetPosition.BOTTOM;
            } else if (c == 1) {
                this.mPosition = BaseWidget.WidgetPosition.LEFT;
            } else if (c == 2) {
                this.mPosition = BaseWidget.WidgetPosition.RIGHT;
            } else if (c == 3) {
                this.mPosition = BaseWidget.WidgetPosition.TOP;
            }
        } catch (JSONException e) {
            Log.e(TAG, "Failed to parse overlay position setting", e);
            this.mPosition = BaseWidget.WidgetPosition.TOP;
        }
    }
}
