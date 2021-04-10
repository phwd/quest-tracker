package com.oculus.mediaupload.io;

import java.io.File;
import java.io.FileFilter;
import java.util.HashSet;

public class FileUtils {
    public static final String JPG_FILE_EXTENSION = ".jpg";
    public static final String MEDIA_METADATA_EXT = "meta";
    public static final String MEDIA_METADATA_PATH = "media_metadata";
    public static final String MP4_FILE_EXTENSION = ".mp4";

    public static HashSet<String> A00(final HashSet<String> hashSet, File file) {
        if (!file.exists()) {
            return new HashSet<>();
        }
        File[] listFiles = file.listFiles(new FileFilter() {
            /* class com.oculus.mediaupload.io.FileUtils.AnonymousClass1 */

            public final boolean accept(File file) {
                String name = file.getName();
                if (name.lastIndexOf(46) == -1 || !file.isFile() || hashSet.contains(name)) {
                    return false;
                }
                return true;
            }
        });
        HashSet<String> hashSet2 = new HashSet<>();
        for (File file2 : listFiles) {
            String name = file2.getName();
            hashSet2.add(name.substring(0, name.lastIndexOf(46)));
        }
        return hashSet2;
    }
}
