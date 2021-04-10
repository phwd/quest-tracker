package com.oculus.panelapp.androiddialog.dialogs.filepicker;

import android.content.Context;
import android.text.format.DateUtils;
import com.oculus.panelapp.androiddialog.dialogs.filepicker.FilePickerViewModel;

/* renamed from: com.oculus.panelapp.androiddialog.dialogs.filepicker.-$$Lambda$ua6MQopMN_SsMnCD14aK8zI5vlA  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$ua6MQopMN_SsMnCD14aK8zI5vlA implements FilePickerViewModel.FormatDateTime {
    public static final /* synthetic */ $$Lambda$ua6MQopMN_SsMnCD14aK8zI5vlA INSTANCE = new $$Lambda$ua6MQopMN_SsMnCD14aK8zI5vlA();

    private /* synthetic */ $$Lambda$ua6MQopMN_SsMnCD14aK8zI5vlA() {
    }

    @Override // com.oculus.panelapp.androiddialog.dialogs.filepicker.FilePickerViewModel.FormatDateTime
    public final String run(Context context, long j, int i) {
        return DateUtils.formatDateTime(context, j, i);
    }
}
