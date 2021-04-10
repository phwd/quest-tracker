package org.chromium.chrome.browser.browsing_data;

import J.N;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import org.chromium.chrome.browser.browsing_data.BrowsingDataBridge;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ClearBrowsingDataFetcher implements BrowsingDataBridge.ImportantSitesCallback, BrowsingDataBridge.OtherFormsOfBrowsingHistoryListener, Parcelable {
    public static final Parcelable.Creator CREATOR = new C0415Gu();
    public int F;
    public String[] G;
    public int[] H;
    public String[] I;

    /* renamed from: J  reason: collision with root package name */
    public boolean f10628J;

    public ClearBrowsingDataFetcher() {
        this.F = N.Mz7sCzLM();
    }

    public int describeContents() {
        return 0;
    }

    @Override // org.chromium.chrome.browser.browsing_data.BrowsingDataBridge.OtherFormsOfBrowsingHistoryListener
    public void enableDialogAboutOtherFormsOfBrowsingHistory() {
        this.f10628J = true;
    }

    @Override // org.chromium.chrome.browser.browsing_data.BrowsingDataBridge.ImportantSitesCallback
    public void onImportantRegisterableDomainsReady(String[] strArr, String[] strArr2, int[] iArr, boolean z) {
        if (strArr != null && !z) {
            int length = strArr.length;
            int i = this.F;
            AbstractC3364kK0.h("History.ClearBrowsingData.NumImportant", length, 1, i + 1, i + 1);
            this.G = (String[]) Arrays.copyOf(strArr, strArr.length);
            this.H = Arrays.copyOf(iArr, iArr.length);
            this.I = (String[]) Arrays.copyOf(strArr2, strArr2.length);
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.F);
        parcel.writeStringArray(this.G);
        parcel.writeIntArray(this.H);
        parcel.writeStringArray(this.I);
        parcel.writeByte(this.f10628J ? (byte) 1 : 0);
    }

    public ClearBrowsingDataFetcher(Parcel parcel) {
        this.F = parcel.readInt();
        this.G = parcel.createStringArray();
        this.H = parcel.createIntArray();
        this.I = parcel.createStringArray();
        this.f10628J = parcel.readByte() != 0;
    }
}
