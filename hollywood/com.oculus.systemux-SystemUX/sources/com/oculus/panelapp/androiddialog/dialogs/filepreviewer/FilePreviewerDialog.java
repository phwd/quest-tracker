package com.oculus.panelapp.androiddialog.dialogs.filepreviewer;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.androiddialog.AndroidDialogPanelApp;
import com.oculus.panelapp.androiddialog.Dialog;
import com.oculus.panelapp.androiddialog.R;
import com.oculus.panelapp.androiddialog.databinding.FilePreviewerDialogBinding;
import com.oculus.panelapp.androiddialog.dialogs.filepicker.ThumbnailLoader;
import com.oculus.systemdialog.CommonSystemDialog;
import com.oculus.systemdialog.DialogDefinitionCustom;

public class FilePreviewerDialog extends ConstraintLayout implements Dialog {
    private static final String TAG = LoggingUtil.tag(FilePreviewerDialog.class);
    Context mContext;
    AndroidDialogPanelApp mPanelApp;
    ThumbnailLoader mThumbnailLoader;

    public FilePreviewerDialog(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        this.mThumbnailLoader = new ThumbnailLoader(context);
        Log.d(TAG, "FilePreviewerDialog dialog constructed");
    }

    @Override // com.oculus.panelapp.androiddialog.Dialog
    public DialogDefinitionCustom getDialogDefinition() {
        return new DialogDefinitionCustom(CommonSystemDialog.FILE_PREVIEWER.getDialogId(), getResources().getString(R.string.file_previewer_title), getResources().getString(R.string.file_previewer_empty));
    }

    public void initialize(AndroidDialogPanelApp androidDialogPanelApp, FilePreviewerDialogBinding filePreviewerDialogBinding) {
        this.mPanelApp = androidDialogPanelApp;
        FilePreviewer.init(this.mContext, this.mThumbnailLoader, filePreviewerDialogBinding);
    }

    @Override // com.oculus.panelapp.androiddialog.Dialog
    public void destroy() {
        this.mThumbnailLoader.destroy();
    }

    @Override // com.oculus.panelapp.androiddialog.Dialog
    public boolean onControllerBackButton() {
        this.mPanelApp.closeDialog();
        return true;
    }
}
