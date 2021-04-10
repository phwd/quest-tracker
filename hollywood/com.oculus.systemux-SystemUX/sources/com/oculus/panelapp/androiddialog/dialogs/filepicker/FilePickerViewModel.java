package com.oculus.panelapp.androiddialog.dialogs.filepicker;

import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.localfilemanager.FileModel;
import com.oculus.localfilemanager.MediaStoreFileLoader;
import com.oculus.panelapp.androiddialog.dialogs.filepicker.FilePickerViewModel;
import com.oculus.vrshell.util.ThreadExecutor;
import com.oculus.vrshell.util.UiThreadExecutor;
import java.io.File;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class FilePickerViewModel extends BaseObservable {
    private static final int DATE_FLAGS = 65557;
    private static final int PAGE_SIZE = 25;
    private static final String TAG = LoggingUtil.tag(FilePickerViewModel.class);
    private ArrayList<FileModel.FileData> mFiles;
    private int mLatestRequestId = 0;
    private final MediaStoreFileLoader mMediaStoreFileLoader = new MediaStoreFileLoader();
    private final MediaMetadataRetriever mMetadataRetriever = new MediaMetadataRetriever();
    @Nullable
    private MediaStoreFileLoader.MediaStoreFileLoaderRequest mNextMediaStoreFileLoaderRequest = MediaStoreFileLoader.MediaStoreFileLoaderRequest.create(25, MediaStoreFileLoader.FileOrderingFilter.MOST_RECENT, MediaStoreFileLoader.FileCategoryFilter.ALL);
    private FileModel.FileData mSelectedFile;
    private String mSelectedFileDateAdded;
    private String mSelectedFileDuration;
    private String mSelectedFileTypeAndSize;

    /* access modifiers changed from: package-private */
    @FunctionalInterface
    public interface FormatDateTime {
        String run(Context context, long j, int i);
    }

    /* access modifiers changed from: package-private */
    @FunctionalInterface
    public interface FormatFileSize {
        String run(Context context, long j);
    }

    /* access modifiers changed from: package-private */
    @FunctionalInterface
    public interface OnFilesLoaded {
        void run(boolean z, int i, int i2);
    }

    public int getFilesCount() {
        ArrayList<FileModel.FileData> arrayList = this.mFiles;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    private void setSelectedFileMetadata(FileModel.FileData fileData, Context context, FormatFileSize formatFileSize, FormatDateTime formatDateTime) {
        if (fileData != null) {
            this.mSelectedFileDateAdded = formatDateTime.run(context, TimeUnit.MILLISECONDS.convert((long) fileData.dateAdded, TimeUnit.SECONDS), DATE_FLAGS);
            if (fileData.fileMetadata != null) {
                String run = formatFileSize.run(context, fileData.fileMetadata.size);
                if (fileData.fileMetadata.mimeType != null) {
                    run = String.format(Locale.getDefault(), "%s - %s", fileData.fileMetadata.mimeType, run);
                }
                this.mSelectedFileTypeAndSize = run;
                if (fileData.fileMetadata.type == FileModel.FileType.VIDEO) {
                    try {
                        long j = fileData.fileMetadata.durationInMs;
                        long minutes = TimeUnit.MILLISECONDS.toMinutes(j);
                        this.mSelectedFileDuration = String.format(Locale.getDefault(), "%d:%02d", Long.valueOf(minutes), Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(j) - (60 * minutes)));
                    } catch (RuntimeException e) {
                        Log.e(TAG, "Unable to retrieve the video metadata", e);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void setSelectedFile(FileModel.FileData fileData, Context context, FormatFileSize formatFileSize, FormatDateTime formatDateTime) {
        this.mSelectedFile = fileData;
        this.mSelectedFileTypeAndSize = null;
        this.mSelectedFileDateAdded = null;
        this.mSelectedFileDuration = null;
        setSelectedFileMetadata(fileData, context, formatFileSize, formatDateTime);
        notifyPropertyChanged(BR.selectedFilePath);
        notifyPropertyChanged(BR.selectedFileName);
        notifyPropertyChanged(BR.selectedFileTypeAndSize);
        notifyPropertyChanged(BR.selectedFileDateAdded);
        notifyPropertyChanged(BR.selectedFileDimensions);
        notifyPropertyChanged(BR.selectedFileDuration);
    }

    /* access modifiers changed from: package-private */
    public void setSelectedFile(FileModel.FileData fileData, Context context) {
        setSelectedFile(fileData, context, $$Lambda$YsWP1t5iQ0tqx4IMH6ZcDxjyVAE.INSTANCE, $$Lambda$ua6MQopMN_SsMnCD14aK8zI5vlA.INSTANCE);
    }

    /* access modifiers changed from: package-private */
    public FileModel.FileData getSelectedFile() {
        return this.mSelectedFile;
    }

    @Bindable
    public String getSelectedFilePath() {
        FileModel.FileData fileData = this.mSelectedFile;
        if (fileData != null) {
            return fileData.path;
        }
        return null;
    }

    @Bindable
    public String getSelectedFileName() {
        FileModel.FileData fileData = this.mSelectedFile;
        if (fileData != null) {
            return new File(fileData.path).getName();
        }
        return null;
    }

    @Bindable
    public String getSelectedFileTypeAndSize() {
        return this.mSelectedFileTypeAndSize;
    }

    @Bindable
    public String getSelectedFileDateAdded() {
        return this.mSelectedFileDateAdded;
    }

    @Bindable
    public String getSelectedFileDimensions() {
        FileModel.FileData fileData = this.mSelectedFile;
        if (fileData == null || fileData.fileMetadata == null || this.mSelectedFile.fileMetadata.width == 0 || this.mSelectedFile.fileMetadata.height == 0) {
            return null;
        }
        return String.valueOf(this.mSelectedFile.fileMetadata.width) + " x " + String.valueOf(this.mSelectedFile.fileMetadata.height);
    }

    public FileModel.FileData getFile(int i) {
        return this.mFiles.get(i);
    }

    @Bindable
    public String getSelectedFileDuration() {
        return this.mSelectedFileDuration;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    /* renamed from: onFilesLoadedInternal */
    public boolean lambda$null$16$FilePickerViewModel(int i, Context context, boolean z, MediaStoreFileLoader.MediaStoreFileLoaderResult mediaStoreFileLoaderResult, OnFilesLoaded onFilesLoaded) {
        if (i != this.mLatestRequestId) {
            return false;
        }
        this.mNextMediaStoreFileLoaderRequest = mediaStoreFileLoaderResult.nextMediaStoreFileLoaderRequest;
        if (z) {
            setSelectedFile(null, context);
            this.mFiles = mediaStoreFileLoaderResult.files;
            onFilesLoaded.run(true, 0, 0);
        } else {
            int size = this.mFiles.size();
            this.mFiles.addAll(mediaStoreFileLoaderResult.files);
            onFilesLoaded.run(false, size, mediaStoreFileLoaderResult.files.size());
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public boolean loadFiles(MediaStoreFileLoader mediaStoreFileLoader, int i, Context context, boolean z, @Nullable MediaStoreFileLoader.MediaStoreFileLoaderRequest mediaStoreFileLoaderRequest, OnFilesLoaded onFilesLoaded, ThreadExecutor threadExecutor, UiThreadExecutor uiThreadExecutor, MediaMetadataRetriever mediaMetadataRetriever) {
        if (mediaStoreFileLoaderRequest == null) {
            return false;
        }
        threadExecutor.execute(new Callable(mediaStoreFileLoader, context, mediaStoreFileLoaderRequest, mediaMetadataRetriever, uiThreadExecutor, i, z, onFilesLoaded) {
            /* class com.oculus.panelapp.androiddialog.dialogs.filepicker.$$Lambda$FilePickerViewModel$VvN8ezSP5jyACEVErnlAPo9w84 */
            private final /* synthetic */ MediaStoreFileLoader f$1;
            private final /* synthetic */ Context f$2;
            private final /* synthetic */ MediaStoreFileLoader.MediaStoreFileLoaderRequest f$3;
            private final /* synthetic */ MediaMetadataRetriever f$4;
            private final /* synthetic */ UiThreadExecutor f$5;
            private final /* synthetic */ int f$6;
            private final /* synthetic */ boolean f$7;
            private final /* synthetic */ FilePickerViewModel.OnFilesLoaded f$8;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
                this.f$4 = r5;
                this.f$5 = r6;
                this.f$6 = r7;
                this.f$7 = r8;
                this.f$8 = r9;
            }

            @Override // java.util.concurrent.Callable
            public final Object call() {
                return FilePickerViewModel.this.lambda$loadFiles$17$FilePickerViewModel(this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8);
            }
        });
        return true;
    }

    public /* synthetic */ Object lambda$loadFiles$17$FilePickerViewModel(MediaStoreFileLoader mediaStoreFileLoader, Context context, @Nullable MediaStoreFileLoader.MediaStoreFileLoaderRequest mediaStoreFileLoaderRequest, MediaMetadataRetriever mediaMetadataRetriever, UiThreadExecutor uiThreadExecutor, int i, boolean z, OnFilesLoaded onFilesLoaded) throws Exception {
        uiThreadExecutor.execute(new Runnable(i, context, z, mediaStoreFileLoader.getFiles(context, mediaStoreFileLoaderRequest, mediaMetadataRetriever), onFilesLoaded) {
            /* class com.oculus.panelapp.androiddialog.dialogs.filepicker.$$Lambda$FilePickerViewModel$hTDALZwUwgsoNOfv6HlJNRwpo */
            private final /* synthetic */ int f$1;
            private final /* synthetic */ Context f$2;
            private final /* synthetic */ boolean f$3;
            private final /* synthetic */ MediaStoreFileLoader.MediaStoreFileLoaderResult f$4;
            private final /* synthetic */ FilePickerViewModel.OnFilesLoaded f$5;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
                this.f$4 = r5;
                this.f$5 = r6;
            }

            public final void run() {
                FilePickerViewModel.this.lambda$null$16$FilePickerViewModel(this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
            }
        });
        return null;
    }

    public void loadFiles(boolean z, Context context, MediaStoreFileLoader.FileCategoryFilter fileCategoryFilter, MediaStoreFileLoader.FileOrderingFilter fileOrderingFilter, OnFilesLoaded onFilesLoaded) {
        MediaStoreFileLoader mediaStoreFileLoader = this.mMediaStoreFileLoader;
        int i = this.mLatestRequestId + 1;
        this.mLatestRequestId = i;
        loadFiles(mediaStoreFileLoader, i, context, z, z ? MediaStoreFileLoader.MediaStoreFileLoaderRequest.create(25, fileOrderingFilter, fileCategoryFilter) : this.mNextMediaStoreFileLoaderRequest, onFilesLoaded, ThreadExecutor.getInstance(), UiThreadExecutor.getInstance(), this.mMetadataRetriever);
    }
}
