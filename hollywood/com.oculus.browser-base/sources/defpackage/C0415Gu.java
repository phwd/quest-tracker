package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import org.chromium.chrome.browser.browsing_data.ClearBrowsingDataFetcher;

/* renamed from: Gu  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0415Gu implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public Object createFromParcel(Parcel parcel) {
        return new ClearBrowsingDataFetcher(parcel);
    }

    @Override // android.os.Parcelable.Creator
    public Object[] newArray(int i) {
        return new ClearBrowsingDataFetcher[i];
    }
}
