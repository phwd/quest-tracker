package com.oculus.bugreporter;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import oculus.internal.FileUtils;

public class BugReportProvider extends ContentProvider {
    private static final int ACTION_EXTRAMEDIA_WITH_ID = 7;
    private static final int ACTION_LOGFILE_WITH_ID = 5;
    private static final int ACTION_PASTAUDIODATA_WITH_ID = 8;
    private static final int ACTION_REPORT = 1;
    private static final int ACTION_REPORT_INCOMPLETE = 6;
    private static final int ACTION_REPORT_READY_TO_UPLOAD = 3;
    private static final int ACTION_REPORT_WITH_ID = 2;
    private static final int ACTION_SCREENSHOT_WITH_ID = 4;
    public static final String APPID = "appId";
    public static final String AUTHORITY = "com.oculus.bugreporter.provider";
    public static final String CATEGORYID = "categoryId";
    public static final String DESCRIPTION = "description";
    public static final String DESCRIPTION_RECORDING = "descriptionRecording";
    public static final String EXTRAMEDIA = "extraMedia";
    public static final String LOGS = "logs";
    public static final String PASTAUDIODATA = "pastAudioData";
    public static final Uri READY_TO_UPLOAD_REPORTS_URI = Uri.parse("content://com.oculus.bugreporter.provider/report/ready_to_upload");
    public static final String REPORTID = "reportId";
    public static final String SCREENSHOT = "screenshot";
    public static final String SESSIONID = "sessionId";
    public static final String TAG = "BugReportProvider";
    private static final UriMatcher URI_MATCHER = new UriMatcher(-1);
    private BugFileUtils mBugFileUtils;

    static {
        URI_MATCHER.addURI(AUTHORITY, "report", 1);
        URI_MATCHER.addURI(AUTHORITY, "report/id/*", 2);
        URI_MATCHER.addURI(AUTHORITY, "report/ready_to_upload", 3);
        URI_MATCHER.addURI(AUTHORITY, "report/screenshot/id/*", 4);
        URI_MATCHER.addURI(AUTHORITY, "report/logfile/id/*", 5);
        URI_MATCHER.addURI(AUTHORITY, "report/incomplete", 6);
        URI_MATCHER.addURI(AUTHORITY, "report/extraMedia/name/*/id/*", 7);
        URI_MATCHER.addURI(AUTHORITY, "report/pastAudioData/id/*", 8);
    }

    public boolean onCreate() {
        this.mBugFileUtils = new BugFileUtils(getContext());
        return true;
    }

    private boolean isReportReadyToUpload(String id) {
        Metadata metadata = Metadata.readFromFile(new File(this.mBugFileUtils.reportFilename(id, BugFileUtils.METADATA)));
        return metadata != null && metadata.isReadyToUpload();
    }

    public synchronized Cursor query(Uri uri, String[] projection, String selection, String[] args, String sort) {
        MatrixCursor cursor = new MatrixCursor(new String[]{REPORTID, SCREENSHOT, PASTAUDIODATA, DESCRIPTION, LOGS, EXTRAMEDIA, APPID, CATEGORYID});
        File reportsDir = new File(this.mBugFileUtils.rootPath());
        if (reportsDir.exists() || reportsDir.mkdirs()) {
            List<String> ids = new ArrayList<>();
            Collections.addAll(ids, reportsDir.list());
            int match = URI_MATCHER.match(uri);
            if (match == 1) {
                for (String id : ids) {
                    addRow(id, cursor, false);
                }
            } else if (match == 6) {
                MatrixCursor incompleteCursor = new MatrixCursor(new String[]{REPORTID});
                for (String id2 : ids) {
                    if (!isReportReadyToUpload(id2)) {
                        incompleteCursor.addRow(new String[]{id2});
                    }
                }
                return incompleteCursor;
            } else if (match == 8) {
                MatrixCursor pastAudioDataCursor = new MatrixCursor(new String[]{REPORTID});
                String id3 = uri.getLastPathSegment();
                Metadata metadata = Metadata.readFromFile(new File(this.mBugFileUtils.reportFilename(id3, BugFileUtils.METADATA)));
                if (metadata != null && metadata.isPastAudioDataAdded) {
                    pastAudioDataCursor.addRow(new String[]{id3});
                }
                return pastAudioDataCursor;
            } else if (match == 3) {
                for (String id4 : ids) {
                    addRow(id4, cursor, true);
                }
            } else if (match == 4) {
                MatrixCursor screenshotCursor = new MatrixCursor(new String[]{REPORTID});
                String id5 = uri.getLastPathSegment();
                Metadata metadata2 = Metadata.readFromFile(new File(this.mBugFileUtils.reportFilename(id5, BugFileUtils.METADATA)));
                if (metadata2 != null && metadata2.isScreenshotAdded) {
                    screenshotCursor.addRow(new String[]{id5});
                }
                return screenshotCursor;
            }
            return cursor;
        }
        Log.e(TAG, "Could not create report dir.");
        return cursor;
    }

    private void addRow(String id, MatrixCursor curson, boolean filterIfIncomplete) {
        Extradata extradata;
        Metadata metadata;
        String extraMedia;
        if ((isReportReadyToUpload(id) || !filterIfIncomplete) && (extradata = Extradata.readFromFile(new File(this.mBugFileUtils.reportFilename(id, BugFileUtils.EXTRADATA)))) != null && (metadata = Metadata.readFromFile(new File(this.mBugFileUtils.reportFilename(id, BugFileUtils.METADATA)))) != null) {
            String description = FileUtils.readAllText(new File(this.mBugFileUtils.reportFilename(id, BugFileUtils.DESCRIPTION)));
            if (!TextUtils.isEmpty(extradata.extraMedia)) {
                extraMedia = extradata.extraMedia + ",";
            } else {
                extraMedia = "";
            }
            curson.addRow(new String[]{id, this.mBugFileUtils.reportFilename(id, BugFileUtils.SCREENSHOT), this.mBugFileUtils.reportFilename(id, BugFileUtils.PASTAUDIODATA), description, this.mBugFileUtils.reportFilename(id, BugFileUtils.LOG), extraMedia + metadata.extraMedia, extradata.appId, extradata.categoryId});
        }
    }

    public synchronized Uri insert(Uri uri, ContentValues values) {
        if (URI_MATCHER.match(uri) != 1) {
            return null;
        }
        String reportId = this.mBugFileUtils.newReportId();
        Uri ret = uri.buildUpon().appendPath(reportId).build();
        if (!new File(this.mBugFileUtils.reportDirname(reportId)).mkdirs()) {
            Log.e(TAG, "Could not create report dir.");
            return null;
        }
        String[] defaultFiles = {BugFileUtils.SCREENSHOT, BugFileUtils.PASTAUDIODATA, BugFileUtils.LOG, BugFileUtils.METADATA, BugFileUtils.DESCRIPTION, BugFileUtils.EXTRADATA};
        for (String filename : defaultFiles) {
            try {
                File file = new File(this.mBugFileUtils.reportFilename(reportId, filename));
                if (file.createNewFile()) {
                    if (filename.equals(BugFileUtils.METADATA)) {
                        Metadata data = new Metadata();
                        data.bugId = reportId;
                        if (!Metadata.writeToFile(file, data)) {
                            Log.w(TAG, "Could not initialize new report metadata.");
                        }
                    }
                } else {
                    throw new IOException("Could not create default files.");
                }
            } catch (IOException e) {
                Log.e(TAG, "Could not insert bug report.", e);
                delete(ret, null, null);
                return null;
            }
        }
        return ret;
    }

    public synchronized int update(Uri uri, ContentValues values, String selection, String[] args) {
        if (URI_MATCHER.match(uri) != 2) {
            return 0;
        }
        String id = uri.getLastPathSegment();
        File metadataFile = new File(this.mBugFileUtils.reportFilename(id, BugFileUtils.METADATA));
        Metadata metadata = Metadata.readFromFile(metadataFile);
        if (metadata == null) {
            Log.e(TAG, "BugReport not available for id " + id);
            return 0;
        }
        if (values.containsKey(LOGS)) {
            metadata.isLogsFileAdded = true;
        }
        if (values.containsKey(DESCRIPTION)) {
            metadata.isUserDetailsAdded = true;
        }
        if (values.containsKey(SCREENSHOT)) {
            metadata.isScreenshotAdded = true;
            getContext().getContentResolver().notifyChange(Uri.parse("content://com.oculus.bugreporter.provider/report/screenshot/id/" + id), null);
        }
        if (values.containsKey(PASTAUDIODATA)) {
            metadata.isPastAudioDataAdded = true;
        }
        if (values.containsKey(EXTRAMEDIA)) {
            String filePath = this.mBugFileUtils.reportFilename(id, values.getAsString(EXTRAMEDIA));
            if (TextUtils.isEmpty(metadata.extraMedia)) {
                metadata.extraMedia = filePath;
            } else {
                metadata.extraMedia += "," + filePath;
            }
        }
        Metadata.writeToFile(metadataFile, metadata);
        if (metadata.isReadyToUpload()) {
            getContext().getContentResolver().notifyChange(READY_TO_UPLOAD_REPORTS_URI, null);
        }
        return 1;
    }

    private static int deleteDirectory(File directory) {
        int res = 1;
        for (File file : directory.listFiles()) {
            if (!file.delete()) {
                Log.e(TAG, "Could not delete files in bugreport directory.");
                res = 0;
            }
        }
        if (res == 0 || directory.delete()) {
            return res;
        }
        Log.e(TAG, "Could not delete bugreport directory.");
        return 0;
    }

    public synchronized int delete(Uri uri, String selection, String[] args) {
        if (URI_MATCHER.match(uri) != 2) {
            return 0;
        }
        return deleteDirectory(new File(this.mBugFileUtils.reportDirname(uri.getLastPathSegment())));
    }

    public String getType(Uri uri) {
        throw new RuntimeException("getType() not implemented");
    }

    @Override // android.content.ContentProvider
    public ParcelFileDescriptor openFile(Uri uri, String mode) throws FileNotFoundException, IllegalArgumentException {
        String path;
        String id = uri.getLastPathSegment();
        int match = URI_MATCHER.match(uri);
        if (match == 4) {
            path = this.mBugFileUtils.reportFilename(id, BugFileUtils.SCREENSHOT);
        } else if (match == 5) {
            path = this.mBugFileUtils.reportFilename(id, BugFileUtils.LOG);
        } else if (match == 7) {
            List<String> segments = uri.getPathSegments();
            int index = segments.indexOf("name");
            if (index < 0 || index + 1 >= segments.size()) {
                throw new IllegalArgumentException("Invalid file type request" + uri.toString());
            }
            path = this.mBugFileUtils.reportFilename(id, segments.get(index + 1));
        } else if (match == 8) {
            path = this.mBugFileUtils.reportFilename(id, BugFileUtils.PASTAUDIODATA);
        } else {
            throw new IllegalArgumentException("Invalid file type request" + uri.toString());
        }
        return ParcelFileDescriptor.open(new File(path), ParcelFileDescriptor.parseMode(mode));
    }
}
