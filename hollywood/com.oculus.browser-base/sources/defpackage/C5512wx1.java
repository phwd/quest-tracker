package defpackage;

import J.N;
import android.os.Bundle;
import android.os.Parcel;
import android.os.ParcelUuid;
import android.os.Parcelable;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.content_public.browser.WebContents;

/* renamed from: wx1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5512wx1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public Object createFromParcel(Parcel parcel) {
        Bundle readBundle = parcel.readBundle();
        if (readBundle.getLong("version", -1) == 0 && WebContentsImpl.F.compareTo(((ParcelUuid) readBundle.getParcelable("processguard")).getUuid()) == 0) {
            return (WebContents) N.M$eaBDjM(readBundle.getLong("webcontents"));
        }
        return null;
    }

    @Override // android.os.Parcelable.Creator
    public Object[] newArray(int i) {
        return new WebContents[i];
    }
}
