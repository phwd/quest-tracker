package com.google.android.gms.vision.barcode;

import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Barcode extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new C3860nE1();
    public int F;
    public String G;
    public String H;
    public int I;

    /* renamed from: J  reason: collision with root package name */
    public Point[] f9678J;
    public Email K;
    public Phone L;
    public Sms M;
    public WiFi N;
    public UrlBookmark O;
    public GeoPoint P;
    public CalendarEvent Q;
    public ContactInfo R;
    public DriverLicense S;

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class Address extends AbstractSafeParcelable {
        public static final Parcelable.Creator CREATOR = new C3170jC1();
        public int F;
        public String[] G;

        public Address(int i, String[] strArr) {
            this.F = i;
            this.G = strArr;
        }

        public void writeToParcel(Parcel parcel, int i) {
            int l = AbstractC5758yO0.l(parcel, 20293);
            int i2 = this.F;
            AbstractC5758yO0.o(parcel, 2, 4);
            parcel.writeInt(i2);
            AbstractC5758yO0.h(parcel, 3, this.G, false);
            AbstractC5758yO0.n(parcel, l);
        }
    }

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class CalendarDateTime extends AbstractSafeParcelable {
        public static final Parcelable.Creator CREATOR = new C4887tF1();
        public int F;
        public int G;
        public int H;
        public int I;

        /* renamed from: J  reason: collision with root package name */
        public int f9679J;
        public int K;
        public boolean L;
        public String M;

        public CalendarDateTime(int i, int i2, int i3, int i4, int i5, int i6, boolean z, String str) {
            this.F = i;
            this.G = i2;
            this.H = i3;
            this.I = i4;
            this.f9679J = i5;
            this.K = i6;
            this.L = z;
            this.M = str;
        }

        public void writeToParcel(Parcel parcel, int i) {
            int l = AbstractC5758yO0.l(parcel, 20293);
            int i2 = this.F;
            AbstractC5758yO0.o(parcel, 2, 4);
            parcel.writeInt(i2);
            int i3 = this.G;
            AbstractC5758yO0.o(parcel, 3, 4);
            parcel.writeInt(i3);
            int i4 = this.H;
            AbstractC5758yO0.o(parcel, 4, 4);
            parcel.writeInt(i4);
            int i5 = this.I;
            AbstractC5758yO0.o(parcel, 5, 4);
            parcel.writeInt(i5);
            int i6 = this.f9679J;
            AbstractC5758yO0.o(parcel, 6, 4);
            parcel.writeInt(i6);
            int i7 = this.K;
            AbstractC5758yO0.o(parcel, 7, 4);
            parcel.writeInt(i7);
            boolean z = this.L;
            AbstractC5758yO0.o(parcel, 8, 4);
            parcel.writeInt(z ? 1 : 0);
            AbstractC5758yO0.g(parcel, 9, this.M, false);
            AbstractC5758yO0.n(parcel, l);
        }
    }

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class CalendarEvent extends AbstractSafeParcelable {
        public static final Parcelable.Creator CREATOR = new WF1();
        public String F;
        public String G;
        public String H;
        public String I;

        /* renamed from: J  reason: collision with root package name */
        public String f9680J;
        public CalendarDateTime K;
        public CalendarDateTime L;

        public CalendarEvent(String str, String str2, String str3, String str4, String str5, CalendarDateTime calendarDateTime, CalendarDateTime calendarDateTime2) {
            this.F = str;
            this.G = str2;
            this.H = str3;
            this.I = str4;
            this.f9680J = str5;
            this.K = calendarDateTime;
            this.L = calendarDateTime2;
        }

        public void writeToParcel(Parcel parcel, int i) {
            int l = AbstractC5758yO0.l(parcel, 20293);
            AbstractC5758yO0.g(parcel, 2, this.F, false);
            AbstractC5758yO0.g(parcel, 3, this.G, false);
            AbstractC5758yO0.g(parcel, 4, this.H, false);
            AbstractC5758yO0.g(parcel, 5, this.I, false);
            AbstractC5758yO0.g(parcel, 6, this.f9680J, false);
            AbstractC5758yO0.f(parcel, 7, this.K, i, false);
            AbstractC5758yO0.f(parcel, 8, this.L, i, false);
            AbstractC5758yO0.n(parcel, l);
        }
    }

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class ContactInfo extends AbstractSafeParcelable {
        public static final Parcelable.Creator CREATOR = new C3012iG1();
        public PersonName F;
        public String G;
        public String H;
        public Phone[] I;

        /* renamed from: J  reason: collision with root package name */
        public Email[] f9681J;
        public String[] K;
        public Address[] L;

        public ContactInfo(PersonName personName, String str, String str2, Phone[] phoneArr, Email[] emailArr, String[] strArr, Address[] addressArr) {
            this.F = personName;
            this.G = str;
            this.H = str2;
            this.I = phoneArr;
            this.f9681J = emailArr;
            this.K = strArr;
            this.L = addressArr;
        }

        public void writeToParcel(Parcel parcel, int i) {
            int l = AbstractC5758yO0.l(parcel, 20293);
            AbstractC5758yO0.f(parcel, 2, this.F, i, false);
            AbstractC5758yO0.g(parcel, 3, this.G, false);
            AbstractC5758yO0.g(parcel, 4, this.H, false);
            AbstractC5758yO0.j(parcel, 5, this.I, i, false);
            AbstractC5758yO0.j(parcel, 6, this.f9681J, i, false);
            AbstractC5758yO0.h(parcel, 7, this.K, false);
            AbstractC5758yO0.j(parcel, 8, this.L, i, false);
            AbstractC5758yO0.n(parcel, l);
        }
    }

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class DriverLicense extends AbstractSafeParcelable {
        public static final Parcelable.Creator CREATOR = new C5570xG1();
        public String F;
        public String G;
        public String H;
        public String I;

        /* renamed from: J  reason: collision with root package name */
        public String f9682J;
        public String K;
        public String L;
        public String M;
        public String N;
        public String O;
        public String P;
        public String Q;
        public String R;
        public String S;

        public DriverLicense(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14) {
            this.F = str;
            this.G = str2;
            this.H = str3;
            this.I = str4;
            this.f9682J = str5;
            this.K = str6;
            this.L = str7;
            this.M = str8;
            this.N = str9;
            this.O = str10;
            this.P = str11;
            this.Q = str12;
            this.R = str13;
            this.S = str14;
        }

        public void writeToParcel(Parcel parcel, int i) {
            int l = AbstractC5758yO0.l(parcel, 20293);
            AbstractC5758yO0.g(parcel, 2, this.F, false);
            AbstractC5758yO0.g(parcel, 3, this.G, false);
            AbstractC5758yO0.g(parcel, 4, this.H, false);
            AbstractC5758yO0.g(parcel, 5, this.I, false);
            AbstractC5758yO0.g(parcel, 6, this.f9682J, false);
            AbstractC5758yO0.g(parcel, 7, this.K, false);
            AbstractC5758yO0.g(parcel, 8, this.L, false);
            AbstractC5758yO0.g(parcel, 9, this.M, false);
            AbstractC5758yO0.g(parcel, 10, this.N, false);
            AbstractC5758yO0.g(parcel, 11, this.O, false);
            AbstractC5758yO0.g(parcel, 12, this.P, false);
            AbstractC5758yO0.g(parcel, 13, this.Q, false);
            AbstractC5758yO0.g(parcel, 14, this.R, false);
            AbstractC5758yO0.g(parcel, 15, this.S, false);
            AbstractC5758yO0.n(parcel, l);
        }
    }

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class Email extends AbstractSafeParcelable {
        public static final Parcelable.Creator CREATOR = new PG1();
        public int F;
        public String G;
        public String H;
        public String I;

        public Email(int i, String str, String str2, String str3) {
            this.F = i;
            this.G = str;
            this.H = str2;
            this.I = str3;
        }

        public void writeToParcel(Parcel parcel, int i) {
            int l = AbstractC5758yO0.l(parcel, 20293);
            int i2 = this.F;
            AbstractC5758yO0.o(parcel, 2, 4);
            parcel.writeInt(i2);
            AbstractC5758yO0.g(parcel, 3, this.G, false);
            AbstractC5758yO0.g(parcel, 4, this.H, false);
            AbstractC5758yO0.g(parcel, 5, this.I, false);
            AbstractC5758yO0.n(parcel, l);
        }
    }

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class GeoPoint extends AbstractSafeParcelable {
        public static final Parcelable.Creator CREATOR = new RG1();
        public double F;
        public double G;

        public GeoPoint(double d, double d2) {
            this.F = d;
            this.G = d2;
        }

        public void writeToParcel(Parcel parcel, int i) {
            int l = AbstractC5758yO0.l(parcel, 20293);
            double d = this.F;
            AbstractC5758yO0.o(parcel, 2, 8);
            parcel.writeDouble(d);
            double d2 = this.G;
            AbstractC5758yO0.o(parcel, 3, 8);
            parcel.writeDouble(d2);
            AbstractC5758yO0.n(parcel, l);
        }
    }

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class PersonName extends AbstractSafeParcelable {
        public static final Parcelable.Creator CREATOR = new C3698mH1();
        public String F;
        public String G;
        public String H;
        public String I;

        /* renamed from: J  reason: collision with root package name */
        public String f9683J;
        public String K;
        public String L;

        public PersonName(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
            this.F = str;
            this.G = str2;
            this.H = str3;
            this.I = str4;
            this.f9683J = str5;
            this.K = str6;
            this.L = str7;
        }

        public void writeToParcel(Parcel parcel, int i) {
            int l = AbstractC5758yO0.l(parcel, 20293);
            AbstractC5758yO0.g(parcel, 2, this.F, false);
            AbstractC5758yO0.g(parcel, 3, this.G, false);
            AbstractC5758yO0.g(parcel, 4, this.H, false);
            AbstractC5758yO0.g(parcel, 5, this.I, false);
            AbstractC5758yO0.g(parcel, 6, this.f9683J, false);
            AbstractC5758yO0.g(parcel, 7, this.K, false);
            AbstractC5758yO0.g(parcel, 8, this.L, false);
            AbstractC5758yO0.n(parcel, l);
        }
    }

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class Phone extends AbstractSafeParcelable {
        public static final Parcelable.Creator CREATOR = new C4893tH1();
        public int F;
        public String G;

        public Phone(int i, String str) {
            this.F = i;
            this.G = str;
        }

        public void writeToParcel(Parcel parcel, int i) {
            int l = AbstractC5758yO0.l(parcel, 20293);
            int i2 = this.F;
            AbstractC5758yO0.o(parcel, 2, 4);
            parcel.writeInt(i2);
            AbstractC5758yO0.g(parcel, 3, this.G, false);
            AbstractC5758yO0.n(parcel, l);
        }
    }

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class Sms extends AbstractSafeParcelable {
        public static final Parcelable.Creator CREATOR = new KH1();
        public String F;
        public String G;

        public Sms(String str, String str2) {
            this.F = str;
            this.G = str2;
        }

        public void writeToParcel(Parcel parcel, int i) {
            int l = AbstractC5758yO0.l(parcel, 20293);
            AbstractC5758yO0.g(parcel, 2, this.F, false);
            AbstractC5758yO0.g(parcel, 3, this.G, false);
            AbstractC5758yO0.n(parcel, l);
        }
    }

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class UrlBookmark extends AbstractSafeParcelable {
        public static final Parcelable.Creator CREATOR = new SH1();
        public String F;
        public String G;

        public UrlBookmark(String str, String str2) {
            this.F = str;
            this.G = str2;
        }

        public void writeToParcel(Parcel parcel, int i) {
            int l = AbstractC5758yO0.l(parcel, 20293);
            AbstractC5758yO0.g(parcel, 2, this.F, false);
            AbstractC5758yO0.g(parcel, 3, this.G, false);
            AbstractC5758yO0.n(parcel, l);
        }
    }

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class WiFi extends AbstractSafeParcelable {
        public static final Parcelable.Creator CREATOR = new ZH1();
        public String F;
        public String G;
        public int H;

        public WiFi(String str, String str2, int i) {
            this.F = str;
            this.G = str2;
            this.H = i;
        }

        public void writeToParcel(Parcel parcel, int i) {
            int l = AbstractC5758yO0.l(parcel, 20293);
            AbstractC5758yO0.g(parcel, 2, this.F, false);
            AbstractC5758yO0.g(parcel, 3, this.G, false);
            int i2 = this.H;
            AbstractC5758yO0.o(parcel, 4, 4);
            parcel.writeInt(i2);
            AbstractC5758yO0.n(parcel, l);
        }
    }

    public Barcode(int i, String str, String str2, int i2, Point[] pointArr, Email email, Phone phone, Sms sms, WiFi wiFi, UrlBookmark urlBookmark, GeoPoint geoPoint, CalendarEvent calendarEvent, ContactInfo contactInfo, DriverLicense driverLicense) {
        this.F = i;
        this.G = str;
        this.H = str2;
        this.I = i2;
        this.f9678J = pointArr;
        this.K = email;
        this.L = phone;
        this.M = sms;
        this.N = wiFi;
        this.O = urlBookmark;
        this.P = geoPoint;
        this.Q = calendarEvent;
        this.R = contactInfo;
        this.S = driverLicense;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int l = AbstractC5758yO0.l(parcel, 20293);
        int i2 = this.F;
        AbstractC5758yO0.o(parcel, 2, 4);
        parcel.writeInt(i2);
        AbstractC5758yO0.g(parcel, 3, this.G, false);
        AbstractC5758yO0.g(parcel, 4, this.H, false);
        int i3 = this.I;
        AbstractC5758yO0.o(parcel, 5, 4);
        parcel.writeInt(i3);
        AbstractC5758yO0.j(parcel, 6, this.f9678J, i, false);
        AbstractC5758yO0.f(parcel, 7, this.K, i, false);
        AbstractC5758yO0.f(parcel, 8, this.L, i, false);
        AbstractC5758yO0.f(parcel, 9, this.M, i, false);
        AbstractC5758yO0.f(parcel, 10, this.N, i, false);
        AbstractC5758yO0.f(parcel, 11, this.O, i, false);
        AbstractC5758yO0.f(parcel, 12, this.P, i, false);
        AbstractC5758yO0.f(parcel, 13, this.Q, i, false);
        AbstractC5758yO0.f(parcel, 14, this.R, i, false);
        AbstractC5758yO0.f(parcel, 15, this.S, i, false);
        AbstractC5758yO0.n(parcel, l);
    }
}
