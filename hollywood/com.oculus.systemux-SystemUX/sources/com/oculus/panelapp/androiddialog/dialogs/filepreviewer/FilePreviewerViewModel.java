package com.oculus.panelapp.androiddialog.dialogs.filepreviewer;

import android.content.Context;
import androidx.databinding.BaseObservable;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.localfilemanager.FileModel;

public class FilePreviewerViewModel extends BaseObservable {
    private static final String TAG = LoggingUtil.tag(FilePreviewerViewModel.class);
    private FileModel.FileData mSelectedFile;

    /* access modifiers changed from: package-private */
    public void setSelectedFile(FileModel.FileData fileData, Context context) {
        this.mSelectedFile = fileData;
    }

    /* access modifiers changed from: package-private */
    public FileModel.FileData getSelectedFile() {
        return this.mSelectedFile;
    }

    public String getSelectedFilePath() {
        FileModel.FileData fileData = this.mSelectedFile;
        if (fileData != null) {
            return fileData.path;
        }
        return null;
    }
}
