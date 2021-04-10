package com.oculus.panelapp.bugreporter;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.bugreporter.util.BugReporterUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MediaViewModel extends BaseObservable {
    private static final long SELECTED_FILE_SIZE_LIMIT = 94371840;
    private static final String TAG = LoggingUtil.tag(MediaViewModel.class);
    private final BugReporterUtil mBugReporterUtil;
    private final Context mContext;
    private long mSelectedFileSize = 0;
    private Set<String> mSelectedFiles = new HashSet();

    public MediaViewModel(Context context, BugReporterUtil bugReporterUtil) {
        this.mContext = context;
        this.mBugReporterUtil = bugReporterUtil;
    }

    private Cursor getMediaCursor() {
        return this.mContext.getContentResolver().query(MediaStore.Files.getContentUri("external"), new String[]{"_id", "media_type", "_data", "_size"}, "media_type = 1 or media_type = 3", null, "date_added DESC");
    }

    public List<MediaFile> getMediaFiles() {
        ArrayList arrayList = new ArrayList();
        Cursor mediaCursor = getMediaCursor();
        if (mediaCursor == null) {
            Log.w(TAG, "Unable to get cursor");
        } else {
            int columnIndex = mediaCursor.getColumnIndex("_data");
            int columnIndex2 = mediaCursor.getColumnIndex("media_type");
            int columnIndex3 = mediaCursor.getColumnIndex("_size");
            while (mediaCursor.moveToNext()) {
                String string = mediaCursor.getString(columnIndex);
                int i = mediaCursor.getInt(columnIndex2);
                long j = mediaCursor.getLong(columnIndex3);
                if (string != null && new File(string).isFile()) {
                    arrayList.add(new MediaFile(string, i, j));
                }
            }
            mediaCursor.close();
        }
        return arrayList;
    }

    public boolean hasMediaFiles() {
        Cursor mediaCursor = getMediaCursor();
        int count = mediaCursor.getCount();
        mediaCursor.close();
        return count != 0;
    }

    @Bindable
    public int getContinueButtonText() {
        if (this.mBugReporterUtil.isPublicUser()) {
            return R.string.bug_report_submit_button;
        }
        return this.mSelectedFiles.isEmpty() ? R.string.bug_report_skip_button : R.string.bug_report_continue_button;
    }

    public boolean isFileSelected(MediaFile mediaFile) {
        return this.mSelectedFiles.contains(mediaFile.getFilePath());
    }

    public void selectFile(MediaFile mediaFile) {
        this.mSelectedFiles.add(mediaFile.getFilePath());
        this.mSelectedFileSize += mediaFile.getFileSize();
        notifyPropertyChanged(BR.continueButtonText);
        notifyPropertyChanged(BR.hasExceededFileSizeLimit);
    }

    public void deselectFile(MediaFile mediaFile) {
        this.mSelectedFiles.remove(mediaFile.getFilePath());
        this.mSelectedFileSize -= mediaFile.getFileSize();
        notifyPropertyChanged(BR.continueButtonText);
        notifyPropertyChanged(BR.hasExceededFileSizeLimit);
    }

    @Bindable
    public boolean getHasExceededFileSizeLimit() {
        return this.mSelectedFileSize > SELECTED_FILE_SIZE_LIMIT;
    }

    public Set<String> getSelectedFiles() {
        return this.mSelectedFiles;
    }
}
