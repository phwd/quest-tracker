package com.oculus.fitnesstracker;

import android.content.Context;
import android.util.Log;
import com.oculus.fitnesstracker.FitnessLogger;
import com.oculus.widgets.BaseWidgetService;

public class FitnessWidgetService extends BaseWidgetService {
    private static final String TAG = "FitnessWidgetService";

    @Override // com.oculus.widgets.BaseWidgetService
    public FitnessWidget createWidgetInstance() {
        String str = TAG;
        Log.d(str, "Creating instance of " + FitnessWidget.class.getSimpleName());
        Context applicationContext = getApplicationContext();
        FitnessLogger.log(applicationContext, FitnessLogger.EventType.overlay_instance_create);
        return new FitnessWidget(applicationContext);
    }
}
