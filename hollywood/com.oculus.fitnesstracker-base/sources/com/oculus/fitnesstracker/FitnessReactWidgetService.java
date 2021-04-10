package com.oculus.fitnesstracker;

import android.util.Log;
import com.oculus.panellib.ReactVRApplication;
import com.oculus.widgets.ReactWidgetService;

public class FitnessReactWidgetService extends ReactWidgetService {
    private static final String TAG = "FitnessReactWidgetService";

    @Override // com.oculus.widgets.ReactWidgetService
    public String getLibraryName() {
        return "fitnesstracker";
    }

    @Override // com.oculus.widgets.ReactWidgetService
    public void initPanelApp() {
        ReactVRApplication.initPanelApp();
    }

    @Override // com.oculus.widgets.ReactWidgetService, com.oculus.widgets.ReactWidgetService, com.oculus.widgets.BaseWidgetService
    public FitnessReactWidget createWidgetInstance() {
        String str = TAG;
        Log.d(str, "Creating instance of " + FitnessReactWidget.class.getSimpleName());
        return new FitnessReactWidget(getApplicationContext());
    }
}
