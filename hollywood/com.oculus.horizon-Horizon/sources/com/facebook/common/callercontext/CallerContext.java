package com.facebook.common.callercontext;

import X.AnonymousClass0KS;
import X.AnonymousClass0KT;
import X.C09531lb;
import android.os.Parcel;
import android.os.Parcelable;
import com.oculus.horizon.notifications.core.NotificationBuilder;
import java.util.Arrays;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
public final class CallerContext implements Parcelable {
    public static final CallerContext A05 = new CallerContext();
    public static final Parcelable.Creator<CallerContext> CREATOR = new C09531lb();
    @Nullable
    public final ContextChain A00;
    public final String A01;
    public final String A02;
    public final String A03;
    public final String A04;

    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof CallerContext)) {
            return false;
        }
        CallerContext callerContext = (CallerContext) obj;
        return AnonymousClass0KT.A01(this.A02, callerContext.A02) && AnonymousClass0KT.A01(this.A01, callerContext.A01) && AnonymousClass0KT.A01(this.A03, callerContext.A03) && AnonymousClass0KT.A01(this.A04, callerContext.A04) && AnonymousClass0KT.A01(this.A00, callerContext.A00);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.A02, this.A01, this.A03, this.A04, this.A00});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.A02);
        parcel.writeString(this.A03);
        parcel.writeString(this.A01);
        parcel.writeString(this.A04);
        parcel.writeParcelable(this.A00, i);
    }

    public final String toString() {
        AnonymousClass0KS A002 = AnonymousClass0KT.A00(this);
        AnonymousClass0KS.A00(A002, "Calling Class Name", this.A02);
        AnonymousClass0KS.A00(A002, "Analytics Tag", this.A01);
        AnonymousClass0KS.A00(A002, "Feature tag", this.A03);
        AnonymousClass0KS.A00(A002, "Module Analytics Tag", this.A04);
        AnonymousClass0KS.A00(A002, "Context Chain", this.A00);
        return A002.toString();
    }

    public CallerContext() {
        this.A02 = null;
        this.A01 = null;
        this.A04 = null;
        this.A03 = null;
        this.A00 = null;
    }

    public CallerContext(Parcel parcel) {
        this.A02 = parcel.readString();
        this.A03 = parcel.readString();
        this.A01 = parcel.readString();
        this.A04 = parcel.readString();
        this.A00 = (ContextChain) parcel.readParcelable(ContextChain.class.getClassLoader());
    }

    public CallerContext(Class<? extends CallerContextable> cls, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable ContextChain contextChain) {
        String str4;
        try {
            str4 = (String) NotificationBuilder.class.getDeclaredField("__redex_internal_original_name").get(NotificationBuilder.class);
        } catch (NoSuchFieldException unused) {
            str4 = NotificationBuilder.class.getName();
        } catch (Exception e) {
            throw new Error(e);
        }
        this.A02 = str4;
        this.A01 = null;
        this.A03 = null;
        this.A04 = null;
        this.A00 = null;
    }
}
