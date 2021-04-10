package defpackage;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: xy  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5683xy extends AbstractC2032cb {
    public static final String[] i = {"_id", "lookup", "display_name"};
    public ContentResolver j;
    public AbstractC5513wy k;
    public final boolean l;
    public final boolean m;
    public final boolean n;
    public final boolean o;

    public C5683xy(Context context, AbstractC5513wy wyVar, boolean z, boolean z2, boolean z3, boolean z4) {
        this.j = context.getContentResolver();
        this.k = wyVar;
        this.l = z;
        this.m = z2;
        this.n = z3;
        this.o = z4;
    }

    @Override // defpackage.AbstractC2032cb
    public Object c() {
        HashMap hashMap;
        if (h()) {
            return null;
        }
        String str = "data1";
        Map m2 = this.m ? m(ContactsContract.CommonDataKinds.Email.CONTENT_URI, "contact_id", str, "contact_id ASC, data1 ASC") : null;
        Map m3 = this.n ? m(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, "contact_id", str, "contact_id ASC, data1 ASC") : null;
        if (this.o) {
            hashMap = new HashMap();
            Cursor query = this.j.query(ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_URI, null, null, null, "contact_id ASC, data1 ASC");
            ArrayList arrayList = new ArrayList();
            String str2 = "";
            while (query.moveToNext()) {
                String string = query.getString(query.getColumnIndex("contact_id"));
                String string2 = query.getString(query.getColumnIndex("data7"));
                String string3 = query.getString(query.getColumnIndex("data10"));
                String string4 = query.getString(query.getColumnIndex(str));
                String string5 = query.getString(query.getColumnIndex("data9"));
                String string6 = query.getString(query.getColumnIndex("data8"));
                C1033Qy0 qy0 = new C1033Qy0();
                if (string2 == null) {
                    string2 = "";
                }
                qy0.g = string2;
                if (string3 == null) {
                    string3 = "";
                }
                qy0.d = string3;
                qy0.e = string4 != null ? new String[]{string4} : new String[0];
                if (string5 == null) {
                    string5 = "";
                }
                qy0.i = string5;
                if (string6 == null) {
                    string6 = "";
                }
                qy0.f = string6;
                qy0.h = "";
                qy0.j = "";
                qy0.k = "";
                qy0.l = "";
                qy0.m = "";
                if (str2.isEmpty()) {
                    arrayList.add(qy0);
                } else if (str2.equals(string)) {
                    arrayList.add(qy0);
                    str = str;
                } else {
                    hashMap.put(str2, arrayList);
                    arrayList = new ArrayList();
                    arrayList.add(qy0);
                }
                str2 = string;
                str = str;
            }
            hashMap.put(str2, arrayList);
            query.close();
        } else {
            hashMap = null;
        }
        Cursor query2 = this.j.query(ContactsContract.Contacts.CONTENT_URI, i, null, null, "sort_key ASC");
        if (!query2.moveToFirst()) {
            query2.close();
            return new ArrayList();
        }
        ArrayList arrayList2 = new ArrayList(query2.getCount());
        do {
            String string7 = query2.getString(query2.getColumnIndex("_id"));
            String string8 = query2.getString(query2.getColumnIndex("display_name"));
            List list = this.m ? (List) m2.get(string7) : null;
            List list2 = this.n ? (List) m3.get(string7) : null;
            List list3 = this.o ? (List) hashMap.get(string7) : null;
            if (!(!this.l && list == null && list2 == null && list3 == null)) {
                arrayList2.add(new C3638ly(string7, string8, list, list2, list3));
            }
        } while (query2.moveToNext());
        query2.close();
        return arrayList2;
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        ArrayList arrayList = (ArrayList) obj;
        if (!h()) {
            ((C0472Hs) this.k).s(arrayList);
        }
    }

    public final Map m(Uri uri, String str, String str2, String str3) {
        HashMap hashMap = new HashMap();
        Cursor query = this.j.query(uri, null, null, null, str3);
        ArrayList arrayList = new ArrayList();
        String str4 = "";
        while (query.moveToNext()) {
            String string = query.getString(query.getColumnIndex(str));
            String string2 = query.getString(query.getColumnIndex(str2));
            if (string2 == null) {
                string2 = "";
            }
            if (str4.isEmpty()) {
                arrayList.add(string2);
            } else if (str4.equals(string)) {
                arrayList.add(string2);
            } else {
                hashMap.put(str4, arrayList);
                arrayList = new ArrayList();
                arrayList.add(string2);
            }
            str4 = string;
        }
        hashMap.put(str4, arrayList);
        query.close();
        return hashMap;
    }
}
