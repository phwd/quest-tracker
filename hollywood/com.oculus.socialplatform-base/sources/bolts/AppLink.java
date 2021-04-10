package bolts;

import android.net.Uri;
import java.util.Collections;
import java.util.List;

public class AppLink {
    public Uri sourceUrl;
    public List<Target> targets;
    public Uri webUrl;

    public static class Target {
        public final String appName;
        public final String className;
        public final String packageName;
        public final Uri url;

        public String getAppName() {
            return this.appName;
        }

        public String getClassName() {
            return this.className;
        }

        public String getPackageName() {
            return this.packageName;
        }

        public Uri getUrl() {
            return this.url;
        }

        public Target(String str, String str2, Uri uri, String str3) {
            this.packageName = str;
            this.className = str2;
            this.url = uri;
            this.appName = str3;
        }
    }

    public Uri getSourceUrl() {
        return this.sourceUrl;
    }

    public List<Target> getTargets() {
        return Collections.unmodifiableList(this.targets);
    }

    public Uri getWebUrl() {
        return this.webUrl;
    }

    public AppLink(Uri uri, List<Target> list, Uri uri2) {
        this.sourceUrl = uri;
        this.targets = list == null ? Collections.emptyList() : list;
        this.webUrl = uri2;
    }
}
