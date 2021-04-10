package com.oculus.panelapp.anytimeui.v2.tablet.settings;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.oculus.ocui.OCEventHandler;
import com.oculus.ocui.OCLink;
import com.oculus.panelapp.anytimeui.databinding.AnytimeTabletAndroidSettingsDescriptiveTextBinding;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.util.SettingsLogger;

public class SettingsDescriptiveTextItem extends ConstraintLayout {
    public SettingsDescriptiveTextItem(@NonNull Context context, @NonNull AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void initialize(@NonNull SettingsDescriptiveText settingsDescriptiveText, @NonNull AnytimeTabletAndroidSettingsDescriptiveTextBinding anytimeTabletAndroidSettingsDescriptiveTextBinding, @NonNull OCEventHandler oCEventHandler, @NonNull OCLink.OCLinkHandler oCLinkHandler, @NonNull SettingsLogger settingsLogger) {
        anytimeTabletAndroidSettingsDescriptiveTextBinding.setDescription(settingsDescriptiveText);
        anytimeTabletAndroidSettingsDescriptiveTextBinding.body.setEventHandler(oCEventHandler);
        anytimeTabletAndroidSettingsDescriptiveTextBinding.body.setLinkHandler(oCLinkHandler);
        anytimeTabletAndroidSettingsDescriptiveTextBinding.button.setEventHandler(oCEventHandler);
        anytimeTabletAndroidSettingsDescriptiveTextBinding.button.setOnClickListener(new View.OnClickListener(settingsLogger) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.$$Lambda$SettingsDescriptiveTextItem$rDFHxfeQ4B6et03NqvFhIBj6ms4 */
            private final /* synthetic */ SettingsLogger f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                SettingsDescriptiveText.this.clickButton(this.f$1);
            }
        });
    }
}
