package com.oculus.bugreportservice;

import android.content.Context;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class BugFileUtils {
    private static final String s = File.separator;
    private Context mContext;

    public BugFileUtils(Context context) {
        this.mContext = context;
    }

    public String rootPath() {
        return this.mContext.getFilesDir() + s + "reports";
    }

    public String newReportId() {
        return new SimpleDateFormat("yyyyMMddHHmmss", Locale.US).format(new Date());
    }

    public String reportDirname(String str) {
        return rootPath() + s + str;
    }

    public String reportFilename(String str, String str2) {
        return reportDirname(str) + s + str2;
    }
}
