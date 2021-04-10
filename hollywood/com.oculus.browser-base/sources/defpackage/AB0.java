package defpackage;

import J.N;
import android.content.Context;
import android.os.Handler;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Pair;
import com.oculus.browser.R;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ExecutionException;
import org.chromium.base.Callback;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.autofill.PersonalDataManager;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.components.payments.PaymentApp;
import org.chromium.content_public.browser.WebContents;

/* renamed from: AB0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AB0 implements AbstractC2016cT0, AbstractC5007tz0, AbstractC5381wA0, PersonalDataManager.NormalizedAddressRequestDelegate, NA0 {

    /* renamed from: a  reason: collision with root package name */
    public static final Comparator f7659a = new C4533rB0();
    public boolean A;
    public C5084uR0 B;
    public C5084uR0 C;
    public C3980ny D;
    public AbstractC1289Vd E;
    public boolean F = true;
    public List G;
    public boolean H;
    public AbstractC0124Ca1 I;

    /* renamed from: J  reason: collision with root package name */
    public TabModel f7660J;
    public AbstractC2260du0 K;
    public C0640Kk0 L;
    public final Comparator b;
    public final boolean c;
    public final Handler d = new Handler();
    public final Queue e = new LinkedList();
    public final QK f;
    public final AbstractC0612Ka1 g;
    public final AbstractC5783ya1 h;
    public C4663ry i;
    public C5177uz0 j;
    public Callback k;
    public C5084uR0 l;
    public final AbstractC5724yB0 m;
    public final WebContents n;
    public final String o;
    public final String p;
    public final Map q;
    public final S3 r;
    public final C5647xm s;
    public final C5894zB0 t;
    public final AbstractC4701sA0 u;
    public final H40 v;
    public TA0 w;
    public UU0 x;
    public boolean y;
    public boolean z;

    public AB0(AbstractC5724yB0 yb0, AbstractC4701sA0 sa0, WebContents webContents, boolean z2, H40 h40, String str) {
        this.m = yb0;
        this.u = sa0;
        S3 s3 = new S3(1, !z2);
        this.r = s3;
        this.s = new C5647xm(webContents, s3, false);
        this.v = h40;
        this.n = webContents;
        this.o = str;
        this.p = webContents.getTitle();
        this.t = new C5894zB0(this);
        this.q = new HashMap();
        this.c = z2;
        this.b = new C1338Vy0(sa0);
        this.g = new C4704sB0(this);
        this.h = new C4874tB0(this);
        this.f = new C5044uB0(this);
    }

    public final void a(Context context) {
        int i2;
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < this.G.size(); i3++) {
            PersonalDataManager.AutofillProfile autofillProfile = (PersonalDataManager.AutofillProfile) this.G.get(i3);
            S3 s3 = this.r;
            String phoneNumber = autofillProfile.getPhoneNumber();
            Objects.requireNonNull(s3);
            if (!TextUtils.isEmpty(phoneNumber)) {
                s3.e.add(phoneNumber.toString());
            }
            if (!TextUtils.isEmpty(autofillProfile.getStreetAddress())) {
                arrayList.add(new C2892hd(context, autofillProfile));
            }
        }
        Collections.sort(arrayList, f7659a);
        List subList = arrayList.subList(0, Math.min(arrayList.size(), 4));
        HashSet hashSet = new HashSet();
        for (int i4 = 0; i4 < subList.size(); i4++) {
            String i5 = C2892hd.i(((C2892hd) subList.get(i4)).m);
            if (!hashSet.contains(i5)) {
                hashSet.add(i5);
                PersonalDataManager c2 = PersonalDataManager.c();
                Objects.requireNonNull(c2);
                Object obj = ThreadUtils.f10596a;
                N.Mj65Bkg_(c2.b, c2, i5);
            }
        }
        boolean z2 = !subList.isEmpty() && ((C2892hd) subList.get(0)).f9599a;
        int i6 = -1;
        if (this.l.d() != null && z2) {
            ((C2892hd) subList.get(0)).n();
            i6 = 0;
        }
        this.v.b(2, subList.size(), z2);
        if (subList.isEmpty()) {
            i2 = 11;
        } else {
            i2 = C2892hd.g(((C2892hd) subList.get(0)).m, 0);
        }
        if (i2 != 0) {
            AbstractC3100ip1.f10165a.d("PaymentRequest.MissingShippingFields", i2);
        }
        this.C = new C5084uR0(1, i6, subList);
    }

    public final void b(C2892hd hdVar) {
        this.r.d(hdVar, new C5384wB0(this, hdVar));
    }

    public final void c(C1870be beVar) {
        C1870be beVar2;
        C5409wK wKVar;
        boolean z2;
        boolean z3;
        C5647xm xmVar = this.s;
        C5554xB0 xb0 = new C5554xB0(this, beVar);
        Objects.requireNonNull(xmVar);
        if (beVar == null) {
            C1870be beVar3 = new C1870be(xmVar.c, new PersonalDataManager.CreditCard("", "Chrome settings", true, false, "", "", "", "", "", "", 0, "", "", "", ""), null, null);
            wKVar = new C5409wK(xmVar.b.getString(R.string.f58330_resource_name_obfuscated_RES_2131953150));
            beVar2 = beVar3;
            z2 = true;
        } else {
            beVar2 = beVar;
            wKVar = new C5409wK(beVar.e);
            z2 = false;
        }
        PersonalDataManager.CreditCard creditCard = beVar2.m;
        if (creditCard.getIsLocal()) {
            try {
                Calendar calendar = (Calendar) xmVar.n.g();
                if (xmVar.o == null) {
                    ArrayList arrayList = new ArrayList();
                    ArrayList arrayList2 = new ArrayList();
                    for (int i2 = 0; i2 < xmVar.j.size(); i2++) {
                        arrayList.add(Integer.valueOf(((C5477wm) xmVar.j.get(i2)).f11567a));
                        arrayList2.add(Integer.valueOf(((C5477wm) xmVar.j.get(i2)).b));
                    }
                    String string = xmVar.b.getString(R.string.f58300_resource_name_obfuscated_RES_2131953147);
                    C4729sK sKVar = new C4729sK(10);
                    sKVar.p = string;
                    sKVar.b = arrayList;
                    sKVar.c = arrayList2;
                    xmVar.o = sKVar;
                }
                wKVar.b.add(xmVar.o);
                if (xmVar.v == null) {
                    xmVar.v = new C4703sB(xmVar.c, xmVar);
                }
                if (xmVar.p == null) {
                    xmVar.p = C4729sK.c(7, xmVar.b.getString(R.string.f47350_resource_name_obfuscated_RES_2131952052), null, null, xmVar.l, xmVar.m, xmVar.b.getString(R.string.f59020_resource_name_obfuscated_RES_2131953219), xmVar.b.getString(R.string.f58500_resource_name_obfuscated_RES_2131953167), null);
                }
                xmVar.p.s = creditCard.getNumber();
                wKVar.b.add(xmVar.p);
                if (xmVar.q == null) {
                    xmVar.q = C4729sK.c(4, xmVar.b.getString(R.string.f47330_resource_name_obfuscated_RES_2131952050), null, null, null, null, xmVar.b.getString(R.string.f59020_resource_name_obfuscated_RES_2131953219), null, null);
                }
                xmVar.q.s = creditCard.getName();
                wKVar.b.add(xmVar.q);
                if (xmVar.r == null) {
                    xmVar.z = calendar.get(1);
                    xmVar.y = calendar.get(2) + 1;
                    if (xmVar.w == null) {
                        xmVar.w = new C4967tm(xmVar);
                    }
                    String string2 = xmVar.b.getString(R.string.f47310_resource_name_obfuscated_RES_2131952048);
                    ArrayList arrayList3 = new ArrayList();
                    Locale locale = Locale.getDefault();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM", locale);
                    SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("MMMM (MM)", locale);
                    calendar.set(5, 1);
                    for (int i3 = 0; i3 < 12; i3++) {
                        calendar.set(2, i3);
                        Date time = calendar.getTime();
                        arrayList3.add(new C3749me(simpleDateFormat.format(time), simpleDateFormat2.format(time)));
                    }
                    AbstractC4559rK rKVar = xmVar.w;
                    String string3 = xmVar.b.getString(R.string.f58490_resource_name_obfuscated_RES_2131953166);
                    C4729sK a2 = C4729sK.a(string2, arrayList3, null);
                    a2.j = rKVar;
                    a2.m = string3;
                    xmVar.r = a2;
                    a2.z = false;
                }
                if (xmVar.r.g.contains(creditCard.getMonth())) {
                    xmVar.r.s = creditCard.getMonth();
                } else {
                    C4729sK sKVar2 = xmVar.r;
                    sKVar2.s = (String) ((Pair) ((C3749me) sKVar2.d.get(0))).first;
                }
                wKVar.b.add(xmVar.r);
                String year = creditCard.getYear();
                ArrayList arrayList4 = new ArrayList();
                int i4 = calendar.get(1);
                boolean z4 = false;
                for (int i5 = i4; i5 < i4 + 10; i5++) {
                    String num = Integer.toString(i5);
                    if (num.equals(year)) {
                        z4 = true;
                    }
                    arrayList4.add(new C3749me(num, num));
                }
                if (z4 || TextUtils.isEmpty(year)) {
                    z3 = false;
                } else {
                    C3749me meVar = new C3749me(year, year);
                    z3 = false;
                    arrayList4.add(0, meVar);
                }
                C4729sK a3 = C4729sK.a(null, arrayList4, null);
                xmVar.s = a3;
                a3.z = z3;
                if (a3.g.contains(creditCard.getYear())) {
                    xmVar.s.s = creditCard.getYear();
                } else {
                    C4729sK sKVar3 = xmVar.s;
                    List list = sKVar3.d;
                    int i6 = z3 ? 1 : 0;
                    int i7 = z3 ? 1 : 0;
                    int i8 = z3 ? 1 : 0;
                    sKVar3.s = (String) ((Pair) ((C3749me) list.get(i6))).first;
                }
                wKVar.b.add(xmVar.s);
            } catch (InterruptedException | ExecutionException unused) {
                xmVar.k.post(new RunnableC0884Ol(xb0, beVar));
                return;
            }
        } else {
            String str = creditCard.g;
            String name = creditCard.getName();
            String string4 = xmVar.b.getString(R.string.f58540_resource_name_obfuscated_RES_2131953171, creditCard.getMonth(), creditCard.getYear());
            int i9 = creditCard.k;
            C4729sK sKVar4 = new C4729sK(12);
            sKVar4.p = str;
            sKVar4.q = name;
            sKVar4.r = string4;
            sKVar4.w = i9;
            wKVar.b.add(sKVar4);
        }
        ArrayList arrayList5 = new ArrayList();
        for (int i10 = 0; i10 < xmVar.d.size(); i10++) {
            PersonalDataManager.AutofillProfile autofillProfile = (PersonalDataManager.AutofillProfile) xmVar.d.get(i10);
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(autofillProfile.p);
            if (xmVar.e.containsKey(autofillProfile.getGUID())) {
                spannableStringBuilder.append((CharSequence) xmVar.b.getString(R.string.f47030_resource_name_obfuscated_RES_2131952020));
                int length = spannableStringBuilder.length();
                spannableStringBuilder.append((CharSequence) xmVar.b.getString(((Integer) xmVar.e.get(autofillProfile.getGUID())).intValue()));
                int length2 = spannableStringBuilder.length();
                spannableStringBuilder.setSpan(new ForegroundColorSpan(xmVar.b.getResources().getColor(R.color.f11590_resource_name_obfuscated_RES_2131099849)), length, length2, 0);
                spannableStringBuilder.setSpan(new AbsoluteSizeSpan(14, true), length, length2, 0);
            }
            arrayList5.add(new C3749me(((PersonalDataManager.AutofillProfile) xmVar.d.get(i10)).getGUID(), spannableStringBuilder));
        }
        arrayList5.add(new C3749me("add", xmVar.b.getString(R.string.f58310_resource_name_obfuscated_RES_2131953148)));
        C4729sK a4 = C4729sK.a(xmVar.b.getString(R.string.f47300_resource_name_obfuscated_RES_2131952047), arrayList5, xmVar.b.getString(R.string.f61160_resource_name_obfuscated_RES_2131953433));
        xmVar.t = a4;
        a4.A = true;
        a4.l = xmVar.b.getString(R.string.f59020_resource_name_obfuscated_RES_2131953219);
        C4729sK sKVar5 = xmVar.t;
        sKVar5.u = new C5307vm(xmVar, creditCard, arrayList5);
        if (sKVar5.g.contains(creditCard.getBillingAddressId())) {
            xmVar.t.s = creditCard.getBillingAddressId();
        }
        wKVar.b.add(xmVar.t);
        if (z2 && !xmVar.A) {
            if (xmVar.u == null) {
                String string5 = xmVar.b.getString(R.string.f58820_resource_name_obfuscated_RES_2131953199);
                C4729sK sKVar6 = new C4729sK(11);
                sKVar6.p = string5;
                sKVar6.s = "check_save_card_to_device";
                xmVar.u = sKVar6;
            }
            wKVar.b.add(xmVar.u);
        }
        wKVar.d = new RunnableC0884Ol(xb0, beVar);
        wKVar.c = new RunnableC4457qm(xmVar, creditCard, z2, beVar2, xb0);
        xmVar.f9770a.g(wKVar);
    }

    public final void d(C5960zd zdVar) {
        String str;
        C4663ry ryVar = this.i;
        C5214vB0 vb0 = new C5214vB0(this, zdVar);
        C5960zd zdVar2 = zdVar == null ? new C5960zd(ryVar.b, new PersonalDataManager.AutofillProfile(), null, null, null, 7, ryVar.c, ryVar.d, ryVar.e) : zdVar;
        C4729sK c2 = ryVar.c ? C4729sK.c(4, ryVar.b.getString(R.string.f58690_resource_name_obfuscated_RES_2131953186), ryVar.g, null, null, null, ryVar.b.getString(R.string.f59020_resource_name_obfuscated_RES_2131953219), null, zdVar2.q) : null;
        C4729sK c3 = ryVar.d ? C4729sK.c(1, ryVar.b.getString(R.string.f47580_resource_name_obfuscated_RES_2131952075), ryVar.h, new C4024oC0(), ryVar.e(), null, ryVar.b.getString(R.string.f59020_resource_name_obfuscated_RES_2131953219), ryVar.b.getString(R.string.f58740_resource_name_obfuscated_RES_2131953191), zdVar2.r) : null;
        C4729sK c4 = ryVar.e ? C4729sK.c(2, ryVar.b.getString(R.string.f47560_resource_name_obfuscated_RES_2131952073), ryVar.i, null, ryVar.d(), null, ryVar.b.getString(R.string.f59020_resource_name_obfuscated_RES_2131953219), ryVar.b.getString(R.string.f58620_resource_name_obfuscated_RES_2131953179), zdVar2.s) : null;
        if (zdVar == null) {
            str = ryVar.b.getString(R.string.f58350_resource_name_obfuscated_RES_2131953152);
        } else {
            str = zdVar.e;
        }
        C5409wK wKVar = new C5409wK(str);
        if (c2 != null) {
            C0972Py0 py0 = ryVar.j;
            c2.n = py0 != null ? py0.e : null;
            wKVar.b.add(c2);
        }
        if (c3 != null) {
            C0972Py0 py02 = ryVar.j;
            c3.n = py02 != null ? py02.f : null;
            wKVar.b.add(c3);
        }
        if (c4 != null) {
            C0972Py0 py03 = ryVar.j;
            c4.n = py03 != null ? py03.d : null;
            wKVar.b.add(c4);
        }
        wKVar.d = new RunnableC0884Ol(vb0, zdVar);
        wKVar.c = new RunnableC4151oy(ryVar, zdVar2, c2, c3, c4, vb0);
        ryVar.f9770a.g(wKVar);
        if (ryVar.j != null) {
            ryVar.f9770a.h();
        }
    }

    public void e() {
        if (this.k == null || this.B == null) {
            this.w.m(this.x);
            if (q()) {
                this.w.o(2, this.l);
                return;
            }
            return;
        }
        o();
    }

    public final List f(List list) {
        if (list == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (int i2 = 0; i2 < list.size(); i2++) {
            C1035Qz0 qz0 = (C1035Qz0) list.get(i2);
            C5723yB h2 = h(qz0.e);
            String str = qz0.d;
            boolean z2 = true;
            if (this.q.size() <= 1) {
                z2 = false;
            }
            arrayList.add(new C5375w80(str, z2 ? h2.b() : "", h2.a(qz0.e.e), qz0.f));
        }
        return Collections.unmodifiableList(arrayList);
    }

    public final C2959hz0 g(PaymentApp paymentApp) {
        if (this.u.e()) {
            return null;
        }
        Map g2 = this.u.g();
        if (!g2.isEmpty() && paymentApp != null) {
            HashSet hashSet = new HashSet(paymentApp.p());
            hashSet.retainAll(g2.keySet());
            if (hashSet.isEmpty()) {
                return null;
            }
            Iterator it = hashSet.iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                C2959hz0 hz0 = (C2959hz0) g2.get(str);
                if (paymentApp.B(str, hz0.f)) {
                    return hz0;
                }
            }
        }
        return null;
    }

    public final C5723yB h(C2617fz0 fz0) {
        String str = fz0.d;
        C5723yB yBVar = (C5723yB) this.q.get(str);
        if (yBVar != null) {
            return yBVar;
        }
        C5723yB yBVar2 = new C5723yB(fz0.d, Locale.getDefault());
        this.q.put(str, yBVar2);
        return yBVar2;
    }

    public List i() {
        ArrayList arrayList = new ArrayList();
        C5084uR0 ur0 = this.B;
        if (ur0 == null) {
            return arrayList;
        }
        for (C1997cK cKVar : ur0.f11410a) {
            arrayList.add((PaymentApp) cKVar);
        }
        return arrayList;
    }

    public void j(int i2, Callback callback) {
        Object obj;
        if (i2 == 1) {
            obj = this.C;
        } else if (i2 == 2) {
            obj = this.l;
        } else if (i2 != 3) {
            obj = i2 != 4 ? null : this.B;
        } else {
            obj = this.D;
        }
        this.d.post(new RunnableC0884Ol((AbstractC0823Nl) callback, obj));
    }

    public PaymentApp k() {
        C5084uR0 ur0 = this.B;
        if (ur0 == null) {
            return null;
        }
        return (PaymentApp) ur0.d();
    }

    public int l(int i2, Callback callback) {
        if (i2 == 1) {
            b(null);
            this.k = callback;
            return 1;
        } else if (i2 == 3) {
            d(null);
            return 2;
        } else if (i2 != 4) {
            return 3;
        } else {
            c(null);
            return 2;
        }
    }

    public int m(int i2, C1997cK cKVar, Callback callback) {
        if (i2 == 1) {
            b((C2892hd) cKVar);
            this.k = callback;
            return 1;
        } else if (i2 == 3) {
            d((C5960zd) cKVar);
            return 2;
        } else if (i2 != 4) {
            return 3;
        } else {
            c((C1870be) cKVar);
            return 2;
        }
    }

    public int n(int i2, C1997cK cKVar, Callback callback) {
        AbstractC1797bA0 ba0;
        AbstractC1797bA0 ba02;
        Context e2 = ((C0289Es) this.m).e();
        if (e2 == null) {
            return 3;
        }
        boolean z2 = ((C0289Es) this.m).g;
        if (i2 == 1) {
            C2892hd hdVar = (C2892hd) cKVar;
            if (hdVar.f9599a) {
                this.C.g(cKVar);
                PersonalDataManager.c().i(hdVar.m, this);
            } else {
                this.r.d(hdVar, new C5384wB0(this, hdVar));
            }
            this.k = callback;
            return 1;
        } else if (i2 == 2) {
            this.l.g(cKVar);
            AbstractC5724yB0 yb0 = this.m;
            String str = cKVar.g;
            EA0 ea0 = ((C0289Es) yb0).f7982a;
            if (!(ea0 == null || (ba02 = ea0.A) == null)) {
                ((C4018oA0) ba02).o0(str);
            }
            this.k = callback;
            return 1;
        } else if (i2 == 3) {
            C5960zd zdVar = (C5960zd) cKVar;
            if (zdVar.f9599a) {
                this.D.g(cKVar);
                if (!z2) {
                    return 3;
                }
                AbstractC5724yB0 yb02 = this.m;
                C0911Oy0 oy0 = new C0911Oy0();
                oy0.e = zdVar.q;
                oy0.f = zdVar.r;
                oy0.d = zdVar.s;
                C0289Es es = (C0289Es) yb02;
                EA0 ea02 = es.f7982a;
                if (!(ea02 == null || !es.g || (ba0 = ea02.A) == null)) {
                    ((C4018oA0) ba0).k0(oy0);
                }
            } else {
                d(zdVar);
                if (!z2) {
                    return 2;
                }
            }
            this.k = callback;
            return 1;
        } else {
            if (i2 == 4) {
                if (q() && this.C == null) {
                    a(e2);
                }
                if (p() && this.D == null) {
                    this.D = new C3980ny(e2, this.G, this.i, this.v);
                }
                TA0 ta0 = this.w;
                C5084uR0 ur0 = this.C;
                C5084uR0 ur02 = this.l;
                C3980ny nyVar = this.D;
                if (((AB0) ta0.I).q() && ta0.c0.getVisibility() == 8) {
                    ta0.o(1, ur0);
                    ta0.o(2, ur02);
                    ta0.c0.setVisibility(0);
                    ta0.U.getChildAt(ta0.U.indexOfChild(ta0.c0) - 1).setVisibility(0);
                    int indexOfChild = ta0.U.indexOfChild(ta0.d0);
                    if (indexOfChild != -1) {
                        ta0.d0.setVisibility(0);
                        ta0.U.getChildAt(indexOfChild - 1).setVisibility(0);
                    }
                } else if (!((AB0) ta0.I).q() && ta0.c0.getVisibility() == 0) {
                    ta0.c0.setVisibility(8);
                    ta0.U.getChildAt(ta0.U.indexOfChild(ta0.c0) - 1).setVisibility(8);
                    int indexOfChild2 = ta0.U.indexOfChild(ta0.d0);
                    if (indexOfChild2 != -1) {
                        ta0.d0.setVisibility(8);
                        ta0.U.getChildAt(indexOfChild2 - 1).setVisibility(8);
                    }
                }
                if (((AB0) ta0.I).p() && ta0.e0.getVisibility() == 8) {
                    ta0.o(3, nyVar);
                    ta0.e0.setVisibility(0);
                    ta0.U.getChildAt(ta0.U.indexOfChild(ta0.e0) - 1).setVisibility(0);
                } else if (!((AB0) ta0.I).p() && ta0.e0.getVisibility() == 0) {
                    ta0.e0.setVisibility(8);
                    ta0.U.getChildAt(ta0.U.indexOfChild(ta0.e0) - 1).setVisibility(8);
                }
                ta0.U.requestLayout();
                PaymentApp paymentApp = (PaymentApp) cKVar;
                if (paymentApp instanceof C1870be) {
                    C1870be beVar = (C1870be) paymentApp;
                    if (!beVar.f9599a) {
                        c(beVar);
                        return 2;
                    }
                }
                u(paymentApp);
                this.B.g(cKVar);
            }
            return 3;
        }
    }

    public void o() {
        int i2;
        C5084uR0 ur0 = this.B;
        PaymentApp k2 = k();
        boolean z2 = false;
        if (k2 == null) {
            i2 = 0;
        } else {
            i2 = k2.q();
        }
        if (i2 != 3) {
            z2 = true;
        }
        ur0.d = z2;
        this.k.onResult(new C0974Pz0(this.x, this.C, this.l, this.D, this.B));
        this.k = null;
        H40 h40 = this.v;
        N.Mb7SmCEg(h40.f8134a, h40, 1);
    }

    @Override // org.chromium.chrome.browser.autofill.PersonalDataManager.NormalizedAddressRequestDelegate
    public void onAddressNormalized(PersonalDataManager.AutofillProfile autofillProfile) {
        Context e2 = ((C0289Es) this.m).e();
        if (e2 == null) {
            C0289Es es = (C0289Es) this.m;
            es.e.a(8);
            es.d("Unable to find Chrome context.");
            return;
        }
        C2892hd hdVar = new C2892hd(e2, autofillProfile);
        AbstractC5724yB0 yb0 = this.m;
        C1033Qy0 o2 = hdVar.o();
        EA0 ea0 = ((C0289Es) yb0).f7982a;
        if (ea0 != null) {
            ea0.B(o2);
        }
    }

    @Override // org.chromium.chrome.browser.autofill.PersonalDataManager.NormalizedAddressRequestDelegate
    public void onCouldNotNormalize(PersonalDataManager.AutofillProfile autofillProfile) {
        Context e2 = ((C0289Es) this.m).e();
        if (e2 == null) {
            C0289Es es = (C0289Es) this.m;
            es.e.a(8);
            es.d("Unable to find Chrome context.");
            return;
        }
        C2892hd hdVar = new C2892hd(e2, autofillProfile);
        AbstractC5724yB0 yb0 = this.m;
        C1033Qy0 o2 = hdVar.o();
        EA0 ea0 = ((C0289Es) yb0).f7982a;
        if (ea0 != null) {
            ea0.B(o2);
        }
    }

    public boolean p() {
        PaymentApp k2 = k();
        if (this.u.e()) {
            return false;
        }
        C1523Yz0 b2 = this.u.b();
        if (b2.d && (k2 == null || !k2.t())) {
            return true;
        }
        if (b2.f && (k2 == null || !k2.u())) {
            return true;
        }
        if (!b2.e || (k2 != null && k2.s())) {
            return false;
        }
        return true;
    }

    public boolean q() {
        if (this.u.e() || !this.u.b().g) {
            return false;
        }
        PaymentApp k2 = k();
        if (k2 == null || !k2.v()) {
            return true;
        }
        return false;
    }

    public void r(String str) {
        if (!q()) {
            return;
        }
        if ((this.l.f() || !TextUtils.isEmpty(str)) && this.C.d() != null) {
            this.C.d().j = false;
            C5084uR0 ur0 = this.C;
            ur0.c = -2;
            ur0.e = str;
        }
    }

    public final void s() {
        C1035Qz0 qz0;
        if (N.M1X7xdZV("WebPaymentsModifiers") && !this.u.e() && !this.u.h().isEmpty() && this.B != null) {
            for (int i2 = 0; i2 < this.B.e(); i2++) {
                PaymentApp paymentApp = (PaymentApp) this.B.c(i2);
                C2959hz0 g2 = g(paymentApp);
                paymentApp.f = (g2 == null || (qz0 = g2.d) == null) ? null : h(qz0.e).a(g2.d.e.e);
            }
            u(k());
        }
    }

    public void t(C2788gz0 gz0) {
        List list;
        C5084uR0 ur0;
        C1035Qz0 qz0 = gz0.d;
        if (qz0 != null) {
            h(qz0.e);
        }
        C1035Qz0[] qz0Arr = gz0.e;
        if (qz0Arr != null) {
            for (C1035Qz0 qz02 : qz0Arr) {
                h(qz02.e);
            }
        }
        C3337kB0[] kb0Arr = gz0.f;
        if (kb0Arr != null) {
            for (C3337kB0 kb0 : kb0Arr) {
                h(kb0.f);
            }
        }
        C2959hz0[] hz0Arr = gz0.g;
        if (hz0Arr != null) {
            for (C2959hz0 hz0 : hz0Arr) {
                C1035Qz0 qz03 = hz0.d;
                if (qz03 != null) {
                    h(qz03.e);
                }
                for (C1035Qz0 qz04 : hz0.e) {
                    h(qz04.e);
                }
            }
        }
        C5723yB h2 = h(gz0.d.e);
        C5375w80 w80 = new C5375w80(gz0.d.d, h2.b(), h2.a(gz0.d.e.e), false);
        C1035Qz0[] qz0Arr2 = gz0.e;
        if (qz0Arr2 == null) {
            list = new ArrayList();
        } else {
            list = Arrays.asList(qz0Arr2);
        }
        this.x = new UU0(w80, f(list));
        if (this.l == null || gz0.f != null) {
            C3337kB0[] kb0Arr2 = gz0.f;
            if (kb0Arr2 == null || kb0Arr2.length == 0) {
                ur0 = new C5084uR0(2, null);
            } else {
                ArrayList arrayList = new ArrayList();
                int i2 = -1;
                for (int i3 = 0; i3 < kb0Arr2.length; i3++) {
                    C3337kB0 kb02 = kb0Arr2[i3];
                    C5723yB h3 = h(kb02.f);
                    boolean z2 = true;
                    if (this.q.size() <= 1) {
                        z2 = false;
                    }
                    String str = z2 ? h3.b() + " " : "";
                    String str2 = kb02.d;
                    String str3 = kb02.e;
                    StringBuilder i4 = AbstractC2531fV.i(str);
                    i4.append(h3.a(kb02.f.e));
                    arrayList.add(new C1997cK(str2, str3, i4.toString(), null, null));
                    if (kb02.g) {
                        i2 = i3;
                    }
                }
                ur0 = new C5084uR0(2, i2, Collections.unmodifiableList(arrayList));
            }
            this.l = ur0;
        }
        s();
    }

    public final void u(PaymentApp paymentApp) {
        C1035Qz0 qz0;
        if (N.M1X7xdZV("WebPaymentsModifiers") && !this.u.e()) {
            C2959hz0 g2 = g(paymentApp);
            List list = null;
            if (g2 == null) {
                qz0 = null;
            } else {
                qz0 = g2.d;
            }
            if (qz0 == null) {
                qz0 = this.u.f();
            }
            C5723yB h2 = h(qz0.e);
            this.x.f9030a = new C5375w80(qz0.d, h2.b(), h2.a(qz0.e.e), false);
            UU0 uu0 = this.x;
            if (g2 != null) {
                list = f(Arrays.asList(g2.e));
            }
            uu0.c = list;
            TA0 ta0 = this.w;
            if (ta0 != null) {
                ta0.m(this.x);
            }
        }
    }
}
