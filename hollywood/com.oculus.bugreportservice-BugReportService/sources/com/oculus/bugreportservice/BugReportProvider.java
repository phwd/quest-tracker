package com.oculus.bugreportservice;

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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import oculus.internal.FileUtils;

public class BugReportProvider extends ContentProvider {
    private static final UriMatcher URI_MATCHER = new UriMatcher(-1);
    private BugFileUtils mBugFileUtils;

    static {
        URI_MATCHER.addURI("com.oculus.bugreportservice.provider", "report", 1);
        URI_MATCHER.addURI("com.oculus.bugreportservice.provider", "report/id/*", 2);
        URI_MATCHER.addURI("com.oculus.bugreportservice.provider", "report/screenshot/id/*", 4);
        URI_MATCHER.addURI("com.oculus.bugreportservice.provider", "report/logfile/id/*", 5);
        URI_MATCHER.addURI("com.oculus.bugreportservice.provider", "report/incomplete", 6);
        URI_MATCHER.addURI("com.oculus.bugreportservice.provider", "report/ready_to_upload", 3);
        URI_MATCHER.addURI("com.oculus.bugreportservice.provider", "report/extraMedia/name/*/id/*", 7);
        URI_MATCHER.addURI("com.oculus.bugreportservice.provider", "report/pastAudioData/id/*", 8);
    }

    public boolean onCreate() {
        this.mBugFileUtils = new BugFileUtils(getContext());
        return true;
    }

    private boolean isReportReadyToUpload(String str) {
        Metadata readFromFile = Metadata.readFromFile(new File(this.mBugFileUtils.reportFilename(str, "metadata.json")));
        return readFromFile != null && readFromFile.isReadyToUpload();
    }

    public synchronized Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{"reportId", "screenshot", "pastAudioData", "description", "logs", "extraMedia", "appId", "categoryId"});
        File file = new File(this.mBugFileUtils.rootPath());
        if (file.exists() || file.mkdirs()) {
            ArrayList<String> arrayList = new ArrayList();
            Collections.addAll(arrayList, file.list());
            int match = URI_MATCHER.match(uri);
            if (match == 1) {
                for (String str3 : arrayList) {
                    addRow(str3, matrixCursor, false);
                }
            } else if (match == 6) {
                MatrixCursor matrixCursor2 = new MatrixCursor(new String[]{"reportId"});
                for (String str4 : arrayList) {
                    if (!isReportReadyToUpload(str4)) {
                        matrixCursor2.addRow(new String[]{str4});
                    }
                }
                return matrixCursor2;
            } else if (match == 8) {
                MatrixCursor matrixCursor3 = new MatrixCursor(new String[]{"reportId"});
                String lastPathSegment = uri.getLastPathSegment();
                Metadata readFromFile = Metadata.readFromFile(new File(this.mBugFileUtils.reportFilename(lastPathSegment, "metadata.json")));
                if (readFromFile != null && readFromFile.isPastAudioDataAdded) {
                    matrixCursor3.addRow(new String[]{lastPathSegment});
                }
                return matrixCursor3;
            } else if (match == 3) {
                for (String str5 : arrayList) {
                    addRow(str5, matrixCursor, true);
                }
            } else if (match == 4) {
                MatrixCursor matrixCursor4 = new MatrixCursor(new String[]{"reportId"});
                String lastPathSegment2 = uri.getLastPathSegment();
                Metadata readFromFile2 = Metadata.readFromFile(new File(this.mBugFileUtils.reportFilename(lastPathSegment2, "metadata.json")));
                if (readFromFile2 != null && readFromFile2.isScreenshotAdded) {
                    matrixCursor4.addRow(new String[]{lastPathSegment2});
                }
                return matrixCursor4;
            }
            return matrixCursor;
        }
        Log.e("BugReportProvider", "Could not create report dir.");
        return matrixCursor;
    }

    private void addRow(String str, MatrixCursor matrixCursor, boolean z) {
        Extradata readFromFile;
        Metadata readFromFile2;
        String str2;
        if ((isReportReadyToUpload(str) || !z) && (readFromFile = Extradata.readFromFile(new File(this.mBugFileUtils.reportFilename(str, "extradata.json")))) != null && (readFromFile2 = Metadata.readFromFile(new File(this.mBugFileUtils.reportFilename(str, "metadata.json")))) != null) {
            String readAllText = FileUtils.readAllText(new File(this.mBugFileUtils.reportFilename(str, "description.txt")));
            if (!TextUtils.isEmpty(readFromFile.extraMedia)) {
                str2 = readFromFile.extraMedia + ",";
            } else {
                str2 = "";
            }
            matrixCursor.addRow(new String[]{str, this.mBugFileUtils.reportFilename(str, "screenshot.png"), this.mBugFileUtils.reportFilename(str, "audio_data_dump.zip"), readAllText, this.mBugFileUtils.reportFilename(str, "logs.zip"), str2 + readFromFile2.extraMedia, readFromFile.appId, readFromFile.categoryId});
        }
    }

    public synchronized Uri insert(Uri uri, ContentValues contentValues) {
        if (URI_MATCHER.match(uri) != 1) {
            return null;
        }
        String newReportId = this.mBugFileUtils.newReportId();
        Uri build = uri.buildUpon().appendPath(newReportId).build();
        if (!new File(this.mBugFileUtils.reportDirname(newReportId)).mkdirs()) {
            Log.e("BugReportProvider", "Could not create report dir.");
            return null;
        }
        String[] strArr = {"screenshot.png", "audio_data_dump.zip", "logs.zip", "metadata.json", "description.txt", "extradata.json"};
        for (String str : strArr) {
            try {
                File file = new File(this.mBugFileUtils.reportFilename(newReportId, str));
                if (file.createNewFile()) {
                    if (str.equals("metadata.json")) {
                        Metadata metadata = new Metadata();
                        metadata.bugId = newReportId;
                        if (!Metadata.writeToFile(file, metadata)) {
                            Log.w("BugReportProvider", "Could not initialize new report metadata.");
                        }
                    }
                } else {
                    throw new IOException("Could not create default files.");
                }
            } catch (IOException e) {
                Log.e("BugReportProvider", "Could not insert bug report.", e);
                delete(build, null, null);
                return null;
            }
        }
        return build;
    }

    public synchronized int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        if (URI_MATCHER.match(uri) != 2) {
            return 0;
        }
        String lastPathSegment = uri.getLastPathSegment();
        File file = new File(this.mBugFileUtils.reportFilename(lastPathSegment, "metadata.json"));
        Metadata readFromFile = Metadata.readFromFile(file);
        if (readFromFile == null) {
            Log.e("BugReportProvider", "BugReport not available for id " + lastPathSegment);
            return 0;
        }
        if (contentValues.containsKey("logs")) {
            readFromFile.isLogsFileAdded = true;
        }
        if (contentValues.containsKey("description")) {
            readFromFile.isUserDetailsAdded = true;
        }
        if (contentValues.containsKey("screenshot")) {
            readFromFile.isScreenshotAdded = true;
            getContext().getContentResolver().notifyChange(Uri.parse("content://com.oculus.bugreportservice.provider/report/screenshot/id/" + lastPathSegment), null);
        }
        if (contentValues.containsKey("pastAudioData")) {
            readFromFile.isPastAudioDataAdded = true;
        }
        if (contentValues.containsKey("extraMedia")) {
            String reportFilename = this.mBugFileUtils.reportFilename(lastPathSegment, contentValues.getAsString("extraMedia"));
            if (TextUtils.isEmpty(readFromFile.extraMedia)) {
                readFromFile.extraMedia = reportFilename;
            } else {
                readFromFile.extraMedia += "," + reportFilename;
            }
        }
        Metadata.writeToFile(file, readFromFile);
        if (readFromFile.isReadyToUpload()) {
            Log.d("BugReportProvider", "ready to upload bug report: " + lastPathSegment);
            BugReportUploaderJobService.scheduleUploadJob(getContext());
        }
        return 1;
    }

    private static int deleteDirectory(File file) {
        int i = 1;
        for (File file2 : file.listFiles()) {
            if (!file2.delete()) {
                Log.e("BugReportProvider", "Could not delete files in bugreport directory.");
                i = 0;
            }
        }
        if (i == 0 || file.delete()) {
            return i;
        }
        Log.e("BugReportProvider", "Could not delete bugreport directory.");
        return 0;
    }

    public synchronized int delete(Uri uri, String str, String[] strArr) {
        if (URI_MATCHER.match(uri) != 2) {
            return 0;
        }
        return deleteDirectory(new File(this.mBugFileUtils.reportDirname(uri.getLastPathSegment())));
    }

    public String getType(Uri uri) {
        throw new RuntimeException("getType() not implemented");
    }

    @Override // android.content.ContentProvider
    public ParcelFileDescriptor openFile(Uri uri, String str) {
        String str2;
        int i;
        String lastPathSegment = uri.getLastPathSegment();
        int match = URI_MATCHER.match(uri);
        if (match == 4) {
            str2 = this.mBugFileUtils.reportFilename(lastPathSegment, "screenshot.png");
        } else if (match == 5) {
            str2 = this.mBugFileUtils.reportFilename(lastPathSegment, "logs.zip");
        } else if (match == 7) {
            List<String> pathSegments = uri.getPathSegments();
            int indexOf = pathSegments.indexOf("name");
            if (indexOf < 0 || (i = indexOf + 1) >= pathSegments.size()) {
                throw new IllegalArgumentException("Invalid file type request" + uri.toString());
            }
            str2 = this.mBugFileUtils.reportFilename(lastPathSegment, (String) pathSegments.get(i));
        } else if (match == 8) {
            str2 = this.mBugFileUtils.reportFilename(lastPathSegment, "audio_data_dump.zip");
        } else {
            throw new IllegalArgumentException("Invalid file type request" + uri.toString());
        }
        return ParcelFileDescriptor.open(new File(str2), ParcelFileDescriptor.parseMode(str));
    }
}
