package com.facebook.acra;

import java.io.File;

public interface BatchUploader {
    void uploadReports(File[] fileArr);
}
