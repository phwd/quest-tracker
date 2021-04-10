package defpackage;

import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.vision.barcode.Barcode;
import com.oculus.os.Version;

/* renamed from: nE1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3860nE1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        int i = 0;
        int i2 = 0;
        String str = null;
        String str2 = null;
        Point[] pointArr = null;
        Barcode.Email email = null;
        Barcode.Phone phone = null;
        Barcode.Sms sms = null;
        Barcode.WiFi wiFi = null;
        Barcode.UrlBookmark urlBookmark = null;
        Barcode.GeoPoint geoPoint = null;
        Barcode.CalendarEvent calendarEvent = null;
        Barcode.ContactInfo contactInfo = null;
        Barcode.DriverLicense driverLicense = null;
        while (parcel.dataPosition() < u) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    i = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case 3:
                    str = AbstractC5588xO0.e(parcel, readInt);
                    break;
                case 4:
                    str2 = AbstractC5588xO0.e(parcel, readInt);
                    break;
                case 5:
                    i2 = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case 6:
                    pointArr = (Point[]) AbstractC5588xO0.h(parcel, readInt, Point.CREATOR);
                    break;
                case Version.VERSION_7:
                    email = (Barcode.Email) AbstractC5588xO0.d(parcel, readInt, Barcode.Email.CREATOR);
                    break;
                case Version.VERSION_8:
                    phone = (Barcode.Phone) AbstractC5588xO0.d(parcel, readInt, Barcode.Phone.CREATOR);
                    break;
                case Version.VERSION_9:
                    sms = (Barcode.Sms) AbstractC5588xO0.d(parcel, readInt, Barcode.Sms.CREATOR);
                    break;
                case Version.VERSION_10:
                    wiFi = (Barcode.WiFi) AbstractC5588xO0.d(parcel, readInt, Barcode.WiFi.CREATOR);
                    break;
                case Version.VERSION_11:
                    urlBookmark = (Barcode.UrlBookmark) AbstractC5588xO0.d(parcel, readInt, Barcode.UrlBookmark.CREATOR);
                    break;
                case Version.VERSION_12:
                    geoPoint = (Barcode.GeoPoint) AbstractC5588xO0.d(parcel, readInt, Barcode.GeoPoint.CREATOR);
                    break;
                case Version.VERSION_13:
                    calendarEvent = (Barcode.CalendarEvent) AbstractC5588xO0.d(parcel, readInt, Barcode.CalendarEvent.CREATOR);
                    break;
                case Version.VERSION_14:
                    contactInfo = (Barcode.ContactInfo) AbstractC5588xO0.d(parcel, readInt, Barcode.ContactInfo.CREATOR);
                    break;
                case Version.VERSION_15:
                    driverLicense = (Barcode.DriverLicense) AbstractC5588xO0.d(parcel, readInt, Barcode.DriverLicense.CREATOR);
                    break;
                default:
                    AbstractC5588xO0.t(parcel, readInt);
                    break;
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new Barcode(i, str, str2, i2, pointArr, email, phone, sms, wiFi, urlBookmark, geoPoint, calendarEvent, contactInfo, driverLicense);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new Barcode[i];
    }
}
