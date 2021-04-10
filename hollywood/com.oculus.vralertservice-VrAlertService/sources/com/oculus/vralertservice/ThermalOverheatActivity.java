package com.oculus.vralertservice;

import android.os.Bundle;
import com.oculus.vralertservice.AlertApplication;

public class ThermalOverheatActivity extends CriticalOverheatActivity {
    @Override // com.oculus.vralertservice.CriticalOverheatActivity
    public /* bridge */ /* synthetic */ void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public ThermalOverheatActivity() {
        super(R.layout.thermal_shutdown_dialog, "thermal", AlertApplication.AlertLevel.SOC_TEMPERATURE);
    }
}
