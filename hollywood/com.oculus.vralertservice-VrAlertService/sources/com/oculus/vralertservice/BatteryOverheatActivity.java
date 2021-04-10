package com.oculus.vralertservice;

import android.os.Bundle;
import com.oculus.vralertservice.AlertApplication;

public class BatteryOverheatActivity extends CriticalOverheatActivity {
    @Override // com.oculus.vralertservice.CriticalOverheatActivity
    public /* bridge */ /* synthetic */ void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public BatteryOverheatActivity() {
        super(R.layout.battery_overheat_dialog, "thermal,battery", AlertApplication.AlertLevel.BATTERY_TEMPERATURE);
    }
}
