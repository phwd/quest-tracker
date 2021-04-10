package defpackage;

import android.os.Build;
import android.text.TextUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.chromium.base.PathUtils;

/* renamed from: pH  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4209pH {
    public File a() {
        String downloadsDirectory = PathUtils.getDownloadsDirectory();
        if (TextUtils.isEmpty(downloadsDirectory)) {
            return null;
        }
        File file = new File(downloadsDirectory);
        if (!file.exists()) {
            try {
                file.mkdirs();
            } catch (SecurityException e) {
                AbstractC1220Ua0.a("DownloadDirectory", "Exception when creating download directory.", e);
            }
        }
        return file;
    }

    public List b() {
        ArrayList arrayList = new ArrayList();
        if (Build.VERSION.SDK_INT > 29) {
            for (String str : PathUtils.getExternalDownloadVolumesNames()) {
                arrayList.add(new File(str));
            }
        } else {
            String[] allPrivateDownloadsDirectories = PathUtils.getAllPrivateDownloadsDirectories();
            for (int i = 1; i < allPrivateDownloadsDirectories.length; i++) {
                arrayList.add(new File(allPrivateDownloadsDirectories[i]));
            }
        }
        return arrayList;
    }
}
