package org.chromium.chrome.browser.download;

import android.text.TextUtils;
import org.chromium.components.offline_items_collection.OfflineItemSchedule;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DownloadInfoBarController$DownloadProgressInfoBarData {

    /* renamed from: a  reason: collision with root package name */
    public C0788My f10659a;
    public String b;
    public String c;
    public String d;
    public int e;
    public boolean f;
    public boolean g;
    public boolean h;
    public boolean i;
    public IH j = new IH(null);
    public int k;
    public OfflineItemSchedule l;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DownloadInfoBarController$DownloadProgressInfoBarData)) {
            return false;
        }
        DownloadInfoBarController$DownloadProgressInfoBarData downloadInfoBarController$DownloadProgressInfoBarData = (DownloadInfoBarController$DownloadProgressInfoBarData) obj;
        C0788My my = this.f10659a;
        return (my == null ? downloadInfoBarController$DownloadProgressInfoBarData.f10659a == null : my.equals(downloadInfoBarController$DownloadProgressInfoBarData.f10659a)) && TextUtils.equals(this.b, downloadInfoBarController$DownloadProgressInfoBarData.b) && TextUtils.equals(this.d, downloadInfoBarController$DownloadProgressInfoBarData.d) && this.e == downloadInfoBarController$DownloadProgressInfoBarData.e;
    }

    public int hashCode() {
        C0788My my = this.f10659a;
        int i2 = 0;
        int hashCode = (my == null ? 0 : my.hashCode()) * 31;
        String str = this.b;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.d;
        if (str2 != null) {
            i2 = str2.hashCode();
        }
        return ((hashCode2 + i2) * 31) + this.e;
    }
}
