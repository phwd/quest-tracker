package com.facebook.papaya.client;

import X.AbstractC0370Ug;
import X.EnumC0181Gs;
import X.H0;
import android.content.ComponentName;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.papaya.fb.client.transport.FBTransport;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class PapayaMetadata implements Parcelable {
    public static final Parcelable.Creator CREATOR = new H0();
    public ComponentName A00;
    public ComponentName A01;
    public Bundle A02;
    public Bundle A03;
    public EnumC0181Gs A04;
    public ImmutableMap A05;
    public ImmutableMap A06;
    public Class A07;
    public Class A08;
    public Class A09;
    public String A0A;
    public String A0B;
    public List A0C;
    public boolean A0D;

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        String str;
        parcel.writeString(this.A0B);
        parcel.writeString(this.A09.getCanonicalName());
        Class cls = this.A08;
        String str2 = null;
        if (cls != null) {
            str = cls.getCanonicalName();
        } else {
            str = null;
        }
        parcel.writeString(str);
        Class cls2 = this.A07;
        if (cls2 != null) {
            str2 = cls2.getCanonicalName();
        }
        parcel.writeString(str2);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        AbstractC0370Ug A0E = this.A05.A08().iterator();
        while (A0E.hasNext()) {
            Object next = A0E.next();
            arrayList.add(next);
            arrayList2.add(((Class) this.A05.get(next)).getCanonicalName());
        }
        parcel.writeStringList(arrayList);
        parcel.writeStringList(arrayList2);
        parcel.writeBundle(this.A02);
        parcel.writeBundle(this.A03);
        int size = this.A06.size();
        ArrayList arrayList3 = new ArrayList();
        long[] jArr = new long[size];
        int i2 = 0;
        AbstractC0370Ug A0E2 = this.A06.A08().iterator();
        while (A0E2.hasNext()) {
            Object next2 = A0E2.next();
            arrayList3.add(next2);
            jArr[i2] = ((Number) this.A06.get(next2)).longValue();
            i2++;
        }
        parcel.writeStringList(arrayList3);
        parcel.writeLongArray(jArr);
        parcel.writeParcelable(this.A01, i);
        parcel.writeParcelable(this.A00, i);
        parcel.writeStringList(this.A0C);
        parcel.writeInt(this.A04.getValue());
        parcel.writeString(this.A0A);
    }

    public PapayaMetadata(Parcel parcel) {
        this.A0D = true;
        try {
            String readString = parcel.readString();
            if (readString != null) {
                this.A0B = readString;
                String readString2 = parcel.readString();
                if (readString2 != null) {
                    this.A09 = Class.forName(readString2);
                    String readString3 = parcel.readString();
                    Class<?> cls = null;
                    this.A08 = readString3 != null ? Class.forName(readString3) : null;
                    String readString4 = parcel.readString();
                    this.A07 = readString4 != null ? Class.forName(readString4) : cls;
                    ImmutableMap.Builder builder = new ImmutableMap.Builder(4);
                    ArrayList arrayList = new ArrayList();
                    parcel.readStringList(arrayList);
                    ArrayList arrayList2 = new ArrayList();
                    parcel.readStringList(arrayList2);
                    for (int i = 0; i < arrayList.size(); i++) {
                        Object obj = arrayList.get(i);
                        Object obj2 = arrayList2.get(i);
                        if (obj2 != null) {
                            builder.put(obj, Class.forName((String) obj2));
                        } else {
                            throw null;
                        }
                    }
                    this.A05 = builder.build();
                    this.A02 = parcel.readBundle();
                    this.A03 = parcel.readBundle();
                    ImmutableMap.Builder builder2 = new ImmutableMap.Builder(4);
                    ArrayList arrayList3 = new ArrayList();
                    parcel.readStringList(arrayList3);
                    long[] jArr = new long[arrayList3.size()];
                    parcel.readLongArray(jArr);
                    for (int i2 = 0; i2 < arrayList3.size(); i2++) {
                        builder2.put(arrayList3.get(i2), Long.valueOf(jArr[i2]));
                    }
                    this.A06 = builder2.build();
                    this.A01 = (ComponentName) parcel.readParcelable(ComponentName.class.getClassLoader());
                    this.A00 = (ComponentName) parcel.readParcelable(ComponentName.class.getClassLoader());
                    List<String> arrayList4 = new ArrayList<>();
                    parcel.readStringList(arrayList4);
                    this.A0C = ImmutableList.A06(arrayList4);
                    this.A04 = EnumC0181Gs.fromValue(parcel.readInt());
                    this.A0A = parcel.readString();
                    return;
                }
                throw null;
            }
            throw null;
        } catch (ClassNotFoundException unused) {
            this.A0D = false;
        }
    }

    public PapayaMetadata(Map map, Bundle bundle, Bundle bundle2, Map map2, ComponentName componentName, ComponentName componentName2, List list, EnumC0181Gs gs) {
        this.A0D = true;
        this.A0B = "assistant";
        this.A09 = FBTransport.class;
        this.A08 = null;
        this.A07 = null;
        this.A05 = ImmutableMap.A05(map);
        this.A02 = bundle;
        this.A03 = bundle2;
        this.A06 = ImmutableMap.A05(map2);
        this.A01 = componentName;
        this.A00 = componentName2;
        this.A0C = ImmutableList.A06(list);
        this.A04 = gs;
        this.A0A = null;
    }
}
