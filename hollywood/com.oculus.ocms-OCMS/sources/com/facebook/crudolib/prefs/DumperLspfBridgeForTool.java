package com.facebook.crudolib.prefs;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import com.facebook.infer.annotation.Nullsafe;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

@VisibleForTesting
@Nullsafe(Nullsafe.Mode.LOCAL)
public class DumperLspfBridgeForTool {
    public static List<String> getProcessPrivateDirFileNames(Context context) {
        File createProcessPrivateDir = LightSharedPreferencesFactory.createProcessPrivateDir(context);
        ArrayList arrayList = new ArrayList();
        addFilesRecursively(arrayList, createProcessPrivateDir, null);
        return arrayList;
    }

    private static void addFilesRecursively(List<String> list, File file, @Nullable String str) {
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (File file2 : listFiles) {
                StringBuilder sb = new StringBuilder();
                if (str != null) {
                    sb.append(str);
                    sb.append(File.separator);
                }
                sb.append(file2.getName());
                if (file2.isDirectory()) {
                    addFilesRecursively(list, file2, sb.toString());
                } else {
                    list.add(sb.toString());
                }
            }
        }
    }
}
