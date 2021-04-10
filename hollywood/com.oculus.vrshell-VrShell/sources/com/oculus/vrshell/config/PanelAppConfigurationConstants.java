package com.oculus.vrshell.config;

import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import java.util.Locale;

public class PanelAppConfigurationConstants {
    private static final String TAG = LoggingUtil.tag(PanelAppConfigurationConstants.class);

    public enum ConfigOptions {
        zerocopytw(1),
        securelayer(2),
        inhibitcapture(4),
        removecaptureproxy(8);
        
        private final int optionValue;

        private ConfigOptions(int i) {
            this.optionValue = i;
        }

        public int getOptionValue() {
            return this.optionValue;
        }

        public static int getValueOfOption(String str) {
            try {
                return valueOf(str.toLowerCase(Locale.ROOT)).getOptionValue();
            } catch (IllegalArgumentException e) {
                Log.e(PanelAppConfigurationConstants.TAG, e.toString());
                return 0;
            }
        }
    }
}
