package com.oculus.bugreporter;

import android.content.Context;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class BugFileUtils {
    public static final String DESCRIPTION = "description.txt";
    public static final String DESCRIPTION_RECORDING = "description.mp4";
    public static final String DETAILS = "details.txt";
    private static final String DIRECTORY = "reports";
    public static final String EXTRADATA = "extradata.json";
    public static final String LOG = "logs.zip";
    public static final String METADATA = "metadata.json";
    public static final String PACKAGES = "package.txt";
    public static final String PASTAUDIODATA = "audio_data_dump.zip";
    public static final String SCREENSHOT = "screenshot.png";
    private static final String s = File.separator;
    private Context mContext;

    public BugFileUtils(Context context) {
        this.mContext = context;
    }

    public String rootPath() {
        return ((Object) this.mContext.getFilesDir()) + s + DIRECTORY;
    }

    public String newReportId() {
        return new SimpleDateFormat("yyyyMMddHHmmss", Locale.US).format(new Date());
    }

    public String reportDirname(String id) {
        return rootPath() + s + id;
    }

    public String reportFilename(String id, String filename) {
        return reportDirname(id) + s + filename;
    }
}
