package com.oculus.panelapp.androiddialog.dialogs.filepicker;

import android.content.Context;
import android.text.format.Formatter;
import com.oculus.panelapp.androiddialog.dialogs.filepicker.FilePickerViewModel;

/* renamed from: com.oculus.panelapp.androiddialog.dialogs.filepicker.-$$Lambda$YsWP1t5iQ0tqx4IMH6ZcDxjyVAE  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$YsWP1t5iQ0tqx4IMH6ZcDxjyVAE implements FilePickerViewModel.FormatFileSize {
    public static final /* synthetic */ $$Lambda$YsWP1t5iQ0tqx4IMH6ZcDxjyVAE INSTANCE = new $$Lambda$YsWP1t5iQ0tqx4IMH6ZcDxjyVAE();

    private /* synthetic */ $$Lambda$YsWP1t5iQ0tqx4IMH6ZcDxjyVAE() {
    }

    @Override // com.oculus.panelapp.androiddialog.dialogs.filepicker.FilePickerViewModel.FormatFileSize
    public final String run(Context context, long j) {
        return Formatter.formatShortFileSize(context, j);
    }
}
