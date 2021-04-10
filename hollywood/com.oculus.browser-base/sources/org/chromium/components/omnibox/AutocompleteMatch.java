package org.chromium.components.omnibox;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import org.chromium.url.GURL;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AutocompleteMatch {

    /* renamed from: a  reason: collision with root package name */
    public final int f10861a;
    public final Set b;
    public final boolean c;
    public final String d;
    public final List e;
    public final String f;
    public final List g;
    public final SuggestionAnswer h;
    public final String i;
    public final GURL j;
    public final GURL k;
    public final String l;
    public final int m;
    public final int n;
    public final boolean o;
    public final String p;
    public final byte[] q;
    public final int r;
    public final List s;
    public final byte[] t;
    public final boolean u;
    public final List v;

    public AutocompleteMatch(int i2, Set set, boolean z, int i3, int i4, String str, List list, String str2, List list2, SuggestionAnswer suggestionAnswer, String str3, GURL gurl, GURL gurl2, String str4, boolean z2, String str5, byte[] bArr, int i5, List list3, byte[] bArr2, boolean z3, List list4) {
        int i6;
        Set set2;
        if (set == null) {
            set2 = Collections.emptySet();
            i6 = i2;
        } else {
            i6 = i2;
            set2 = set;
        }
        this.f10861a = i6;
        this.b = set2;
        this.c = z;
        this.m = i3;
        this.n = i4;
        String str6 = str;
        this.d = str6;
        this.e = list;
        this.f = str2;
        this.g = list2;
        this.h = suggestionAnswer;
        this.i = !TextUtils.isEmpty(str3) ? str3 : str6;
        this.j = gurl;
        this.k = gurl2;
        this.l = str4;
        this.o = z2;
        this.p = str5;
        this.q = bArr;
        this.r = i5;
        this.s = list3;
        this.t = bArr2;
        this.u = z3;
        this.v = list4;
    }

    public static AutocompleteMatch build(int i2, int[] iArr, boolean z, int i3, int i4, String str, int[] iArr2, int[] iArr3, String str2, int[] iArr4, int[] iArr5, SuggestionAnswer suggestionAnswer, String str3, GURL gurl, GURL gurl2, String str4, boolean z2, String str5, byte[] bArr, int i5, List list, byte[] bArr2, boolean z3, String[] strArr, GURL[] gurlArr) {
        ArrayList arrayList = new ArrayList();
        for (int i6 = 0; i6 < iArr2.length; i6++) {
            arrayList.add(new C1347Wc(iArr2[i6], iArr3[i6]));
        }
        ArrayList arrayList2 = new ArrayList();
        for (int i7 = 0; i7 < iArr4.length; i7++) {
            arrayList2.add(new C1347Wc(iArr4[i7], iArr5[i7]));
        }
        ArrayList arrayList3 = new ArrayList();
        for (int i8 = 0; i8 < strArr.length; i8++) {
            arrayList3.add(new C1408Xc(strArr[i8], gurlArr[i8]));
        }
        C5271va vaVar = new C5271va(iArr.length);
        for (int i9 : iArr) {
            vaVar.add(Integer.valueOf(i9));
        }
        return new AutocompleteMatch(i2, vaVar, z, i3, i4, str, arrayList, str2, arrayList2, suggestionAnswer, str3, gurl, gurl2, str4, z2, str5, bArr, i5, list, bArr2, z3, arrayList3);
    }

    public boolean a() {
        return this.h != null;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AutocompleteMatch)) {
            return false;
        }
        AutocompleteMatch autocompleteMatch = (AutocompleteMatch) obj;
        if (this.f10861a != autocompleteMatch.f10861a || !Objects.equals(this.b, autocompleteMatch.b) || !TextUtils.equals(this.i, autocompleteMatch.i) || !TextUtils.equals(this.d, autocompleteMatch.d) || !Objects.equals(this.e, autocompleteMatch.e) || !TextUtils.equals(this.f, autocompleteMatch.f) || !Objects.equals(this.g, autocompleteMatch.g) || this.o != autocompleteMatch.o || this.m != autocompleteMatch.m || !Objects.equals(this.h, autocompleteMatch.h) || !TextUtils.equals(this.p, autocompleteMatch.p) || !Arrays.equals(this.q, autocompleteMatch.q) || this.r != autocompleteMatch.r || !Objects.equals(this.s, autocompleteMatch.s)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        String str = this.d;
        int i2 = 0;
        int hashCode = str != null ? str.hashCode() : 0;
        String str2 = this.i;
        if (str2 != null) {
            i2 = str2.hashCode();
        }
        int i3 = (i2 * 1901) + (hashCode * 2017) + (this.f10861a * 37) + (this.o ? 1 : 0);
        SuggestionAnswer suggestionAnswer = this.h;
        return suggestionAnswer != null ? i3 + suggestionAnswer.hashCode() : i3;
    }

    public String toString() {
        StringBuilder i2 = AbstractC2531fV.i("mType=");
        i2.append(this.f10861a);
        StringBuilder i3 = AbstractC2531fV.i("mSubtypes=");
        i3.append(this.b.toString());
        StringBuilder i4 = AbstractC2531fV.i("mIsSearchType=");
        i4.append(this.c);
        StringBuilder i5 = AbstractC2531fV.i("mDisplayText=");
        i5.append(this.d);
        StringBuilder i6 = AbstractC2531fV.i("mDescription=");
        i6.append(this.f);
        StringBuilder i7 = AbstractC2531fV.i("mFillIntoEdit=");
        i7.append(this.i);
        StringBuilder i8 = AbstractC2531fV.i("mUrl=");
        i8.append(this.j);
        StringBuilder i9 = AbstractC2531fV.i("mImageUrl=");
        i9.append(this.k);
        StringBuilder i10 = AbstractC2531fV.i("mImageDominatColor=");
        i10.append(this.l);
        StringBuilder i11 = AbstractC2531fV.i("mRelevance=");
        i11.append(this.m);
        StringBuilder i12 = AbstractC2531fV.i("mTransition=");
        i12.append(this.n);
        StringBuilder i13 = AbstractC2531fV.i("mIsDeletable=");
        i13.append(this.o);
        StringBuilder i14 = AbstractC2531fV.i("mPostContentType=");
        i14.append(this.p);
        StringBuilder i15 = AbstractC2531fV.i("mPostData=");
        i15.append(Arrays.toString(this.q));
        StringBuilder i16 = AbstractC2531fV.i("mGroupId=");
        i16.append(this.r);
        StringBuilder i17 = AbstractC2531fV.i("mDisplayTextClassifications=");
        i17.append(this.e);
        StringBuilder i18 = AbstractC2531fV.i("mDescriptionClassifications=");
        i18.append(this.g);
        StringBuilder i19 = AbstractC2531fV.i("mAnswer=");
        i19.append(this.h);
        return Arrays.asList(i2.toString(), i3.toString(), i4.toString(), i5.toString(), i6.toString(), i7.toString(), i8.toString(), i9.toString(), i10.toString(), i11.toString(), i12.toString(), i13.toString(), i14.toString(), i15.toString(), i16.toString(), i17.toString(), i18.toString(), i19.toString()).toString();
    }
}
