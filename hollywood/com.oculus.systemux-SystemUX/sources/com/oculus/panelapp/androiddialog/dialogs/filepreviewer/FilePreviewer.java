package com.oculus.panelapp.androiddialog.dialogs.filepreviewer;

import android.content.Context;
import android.widget.ImageView;
import com.oculus.panelapp.androiddialog.databinding.FilePreviewerDialogBinding;
import com.oculus.panelapp.androiddialog.dialogs.filepicker.ThumbnailLoader;

public class FilePreviewer {

    @FunctionalInterface
    interface OnCancel {
        void run();
    }

    @FunctionalInterface
    interface OnFileSelected {
        void run(String str);
    }

    private FilePreviewer() {
    }

    static void initializePreview(ThumbnailLoader thumbnailLoader, ImageView imageView, FilePreviewerViewModel filePreviewerViewModel) {
        String selectedFilePath = filePreviewerViewModel.getSelectedFilePath();
        if (selectedFilePath != null) {
            thumbnailLoader.load(selectedFilePath, imageView);
        }
    }

    static void init(Context context, ThumbnailLoader thumbnailLoader, FilePreviewerDialogBinding filePreviewerDialogBinding) {
        FilePreviewerViewModel filePreviewerViewModel = new FilePreviewerViewModel();
        filePreviewerDialogBinding.setViewModel(filePreviewerViewModel);
        initializePreview(thumbnailLoader, filePreviewerDialogBinding.previewLayout.previewImage, filePreviewerViewModel);
    }
}
