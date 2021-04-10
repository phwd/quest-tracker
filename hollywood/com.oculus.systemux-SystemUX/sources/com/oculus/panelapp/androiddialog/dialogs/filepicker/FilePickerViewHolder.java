package com.oculus.panelapp.androiddialog.dialogs.filepicker;

import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.VisibleForTesting;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.localfilemanager.FileModel;
import com.oculus.ocui.OCEventHandler;
import com.oculus.panelapp.androiddialog.R;
import com.oculus.panelapp.androiddialog.databinding.FilePickerRowBinding;
import com.oculus.panelapp.androiddialog.dialogs.filepicker.FilePickerViewHolder;
import java.io.File;

/* access modifiers changed from: package-private */
public class FilePickerViewHolder extends RecyclerView.ViewHolder {
    private static final int FADE_IN_DURATION = 100;
    private static final int FADE_OUT_DURATION = 70;
    final FilePickerRowBinding mBinding;

    /* access modifiers changed from: package-private */
    @FunctionalInterface
    public interface OnFileClicked {
        void run();
    }

    FilePickerViewHolder(FilePickerRowBinding filePickerRowBinding) {
        super(filePickerRowBinding.getRoot());
        this.mBinding = filePickerRowBinding;
    }

    private static void fadeIn(View view) {
        view.animate().alpha(1.0f).setDuration(100);
    }

    private static void fadeOut(View view) {
        view.animate().alpha(0.0f).setDuration(70);
    }

    @VisibleForTesting
    static void setFile(ThumbnailLoader thumbnailLoader, OnFileClicked onFileClicked, FileModel.FileData fileData, View view, TextView textView, ImageView imageView, View view2, FileModel.FileData fileData2, OCEventHandler oCEventHandler) {
        String name = new File(fileData.path).getName();
        view.setTag(name);
        textView.setText(name);
        boolean equals = fileData.path.equals(fileData2 != null ? fileData2.path : null);
        view2.setSelected(equals);
        view2.setAlpha(equals ? 1.0f : 0.0f);
        int i = AnonymousClass1.$SwitchMap$com$oculus$localfilemanager$FileModel$FileType[(fileData.fileMetadata != null ? fileData.fileMetadata.type : FileModel.FileType.UNKNOWN).ordinal()];
        if (i == 1) {
            thumbnailLoader.load(R.drawable.file_picker_list_unknown_file, imageView);
        } else if (i != 2) {
            thumbnailLoader.load(fileData.path, imageView, 0.25f);
        } else {
            thumbnailLoader.load(R.drawable.file_picker_list_known_file, imageView);
        }
        view.setOnClickListener(new View.OnClickListener(onFileClicked) {
            /* class com.oculus.panelapp.androiddialog.dialogs.filepicker.$$Lambda$FilePickerViewHolder$_EJ9EtC1UomX72hGJGHGedCbgbo */
            private final /* synthetic */ FilePickerViewHolder.OnFileClicked f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                FilePickerViewHolder.lambda$setFile$14(OCEventHandler.this, this.f$1, view);
            }
        });
        view.setOnHoverListener(new View.OnHoverListener(view2) {
            /* class com.oculus.panelapp.androiddialog.dialogs.filepicker.$$Lambda$FilePickerViewHolder$IHPPJV9vgCte5htSesp5mRv924 */
            private final /* synthetic */ View f$1;

            {
                this.f$1 = r2;
            }

            public final boolean onHover(View view, MotionEvent motionEvent) {
                return FilePickerViewHolder.lambda$setFile$15(OCEventHandler.this, this.f$1, view, motionEvent);
            }
        });
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.panelapp.androiddialog.dialogs.filepicker.FilePickerViewHolder$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$localfilemanager$FileModel$FileType = new int[FileModel.FileType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        static {
            /*
                com.oculus.localfilemanager.FileModel$FileType[] r0 = com.oculus.localfilemanager.FileModel.FileType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oculus.panelapp.androiddialog.dialogs.filepicker.FilePickerViewHolder.AnonymousClass1.$SwitchMap$com$oculus$localfilemanager$FileModel$FileType = r0
                int[] r0 = com.oculus.panelapp.androiddialog.dialogs.filepicker.FilePickerViewHolder.AnonymousClass1.$SwitchMap$com$oculus$localfilemanager$FileModel$FileType     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.localfilemanager.FileModel$FileType r1 = com.oculus.localfilemanager.FileModel.FileType.UNKNOWN     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.oculus.panelapp.androiddialog.dialogs.filepicker.FilePickerViewHolder.AnonymousClass1.$SwitchMap$com$oculus$localfilemanager$FileModel$FileType     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.localfilemanager.FileModel$FileType r1 = com.oculus.localfilemanager.FileModel.FileType.KNOWN     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.androiddialog.dialogs.filepicker.FilePickerViewHolder.AnonymousClass1.<clinit>():void");
        }
    }

    static /* synthetic */ void lambda$setFile$14(OCEventHandler oCEventHandler, OnFileClicked onFileClicked, View view) {
        oCEventHandler.onButtonClick();
        onFileClicked.run();
    }

    static /* synthetic */ boolean lambda$setFile$15(OCEventHandler oCEventHandler, View view, View view2, MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            oCEventHandler.onButtonEnter();
            fadeIn(view);
            return false;
        } else if (actionMasked != 10 || view.isSelected()) {
            return false;
        } else {
            fadeOut(view);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public void setFile(ThumbnailLoader thumbnailLoader, OnFileClicked onFileClicked, FileModel.FileData fileData, FileModel.FileData fileData2, OCEventHandler oCEventHandler) {
        setFile(thumbnailLoader, onFileClicked, fileData, this.mBinding.container, this.mBinding.fileName, this.mBinding.image, this.mBinding.selectedIndicator, fileData2, oCEventHandler);
    }

    /* access modifiers changed from: package-private */
    public void onViewRecycled(ThumbnailLoader thumbnailLoader) {
        thumbnailLoader.unload(this.mBinding.image);
    }
}
