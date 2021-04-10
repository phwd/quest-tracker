package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Executor;
import org.chromium.components.browser_ui.contacts_picker.ContactsPickerToolbar;
import org.chromium.components.browser_ui.contacts_picker.TopView;
import org.chromium.components.browser_ui.widget.selectable_list.SelectableListLayout;
import org.chromium.content.browser.ContactsDialogHost;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.widget.OptimizedFrameLayout;

/* renamed from: IC0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class IC0 extends OptimizedFrameLayout implements View.OnClickListener, QK0, AbstractC3039iS0, AbstractC1843bS0, AbstractC3262jm1, AbstractC1577Zw {
    public DialogC6023zy G;
    public SelectableListLayout H;
    public WindowAndroid I;

    /* renamed from: J  reason: collision with root package name */
    public ContactsDialogHost f8206J;
    public ContactsPickerToolbar K;
    public RecyclerView L;
    public TopView M;
    public C0472Hs N;
    public LinearLayoutManager O;
    public KN0 P;
    public C3209jS0 Q;
    public FC0 R;
    public ImageView S;
    public Set T;
    public Button U;
    public boolean V;
    public final boolean W;
    public final boolean a0;
    public final boolean b0;
    public final boolean c0;
    public final boolean d0;

    public IC0(WindowAndroid windowAndroid, C0472Hs hs, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, String str, AbstractC0118By by) {
        super((Context) windowAndroid.f11022J.get(), null);
        this.I = windowAndroid;
        Context context = (Context) windowAndroid.f11022J.get();
        this.V = z;
        this.W = z2;
        this.a0 = z3;
        this.b0 = z4;
        this.c0 = z5;
        this.d0 = z6;
        C3209jS0 js0 = new C3209jS0();
        this.Q = js0;
        if (!z) {
            js0.f10206a = true;
        }
        js0.d.b(this);
        Resources resources = context.getResources();
        this.P = new KN0(resources, 36, 36, 20, resources.getColor(R.color.f11180_resource_name_obfuscated_RES_2131099808), 12);
        SelectableListLayout selectableListLayout = (SelectableListLayout) LayoutInflater.from(context).inflate(R.layout.f37450_resource_name_obfuscated_RES_2131624054, this).findViewById(R.id.selectable_list);
        this.H = selectableListLayout;
        selectableListLayout.P = R.string.f49550_resource_name_obfuscated_RES_2131952272;
        selectableListLayout.I.setText(R.string.f49550_resource_name_obfuscated_RES_2131952272);
        selectableListLayout.f10827J.setOnTouchListener(new WR0());
        this.N = hs;
        hs.N = this;
        hs.Q = context.getContentResolver();
        hs.P = str;
        C0472Hs.I = true;
        C0472Hs.f8185J = true;
        C0472Hs.K = true;
        C0472Hs.L = true;
        C0472Hs.M = true;
        if (hs.R == null) {
            IC0 ic0 = hs.N;
            C5683xy xyVar = new C5683xy(context, hs, ic0.W, ic0.a0, ic0.b0, ic0.c0);
            hs.T = xyVar;
            Executor executor = AbstractC2032cb.f9616a;
            xyVar.f();
            ((ExecutorC1463Ya) executor).execute(xyVar.e);
        } else {
            hs.s(null);
        }
        this.L = this.H.e(this.N, null);
        ContactsPickerToolbar contactsPickerToolbar = (ContactsPickerToolbar) this.H.f(R.layout.f37460_resource_name_obfuscated_RES_2131624055, this.Q, z ? R.string.f49580_resource_name_obfuscated_RES_2131952275 : R.string.f49570_resource_name_obfuscated_RES_2131952274, 0, 0, null, false, false);
        this.K = contactsPickerToolbar;
        contactsPickerToolbar.h();
        contactsPickerToolbar.I.setOnClickListener(this);
        ContactsPickerToolbar contactsPickerToolbar2 = this.K;
        contactsPickerToolbar2.y0 = true;
        contactsPickerToolbar2.C0 = this;
        contactsPickerToolbar2.K0 = 0;
        contactsPickerToolbar2.P0 = -1;
        LayoutInflater.from(contactsPickerToolbar2.getContext()).inflate(R.layout.f41290_resource_name_obfuscated_RES_2131624438, contactsPickerToolbar2);
        LinearLayout linearLayout = (LinearLayout) contactsPickerToolbar2.findViewById(R.id.search_view);
        contactsPickerToolbar2.z0 = linearLayout;
        EditText editText = (EditText) linearLayout.findViewById(R.id.search_text);
        contactsPickerToolbar2.A0 = editText;
        editText.setHint(R.string.f49560_resource_name_obfuscated_RES_2131952273);
        contactsPickerToolbar2.A0.setOnEditorActionListener(contactsPickerToolbar2);
        contactsPickerToolbar2.A0.addTextChangedListener(new C1663aS0(contactsPickerToolbar2));
        ImageButton imageButton = (ImageButton) contactsPickerToolbar2.findViewById(R.id.clear_text_button);
        contactsPickerToolbar2.B0 = imageButton;
        imageButton.setOnClickListener(new ZR0(contactsPickerToolbar2));
        this.K.b1 = by;
        this.N.F.registerObserver(new BC0(this));
        SelectableListLayout selectableListLayout2 = this.H;
        Objects.requireNonNull(selectableListLayout2);
        Yo1 yo1 = new Yo1(selectableListLayout2);
        selectableListLayout2.Q = yo1;
        selectableListLayout2.M.M(yo1);
        Yo1 yo12 = selectableListLayout2.Q;
        yo12.b.add(selectableListLayout2);
        selectableListLayout2.a(yo12.f9298a);
        ImageView imageView = (ImageView) this.K.findViewById(R.id.search);
        this.S = imageView;
        imageView.setOnClickListener(this);
        Button button = (Button) this.K.findViewById(R.id.done);
        this.U = button;
        button.setOnClickListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(1, false);
        this.O = linearLayoutManager;
        RecyclerView recyclerView = this.L;
        recyclerView.e0 = true;
        recyclerView.t0(linearLayoutManager);
        this.R = new FC0();
    }

    @Override // defpackage.QK0
    public void a(XK0 xk0) {
        C5173uy uyVar = (C5173uy) xk0;
        uyVar.d0.b(true);
        uyVar.d0 = null;
    }

    @Override // defpackage.AbstractC3039iS0
    public void b(List list) {
        if (this.K.x0 && list.size() > 0) {
            this.K.O();
        }
        boolean z = list.size() == this.N.b() - 1;
        TopView topView = this.M;
        if (topView != null) {
            topView.Q = true;
            topView.H.setChecked(z);
            topView.Q = false;
        }
    }

    public final void d(int i, List list, int i2) {
        int i3 = 0;
        int size = list != null ? list.size() : 0;
        int size2 = this.N.R.size();
        int i4 = size2 > 0 ? (size * 100) / size2 : 0;
        if (this.W) {
            i3 = 4;
        }
        if (this.a0) {
            i3 |= 2;
        }
        if (this.b0) {
            i3 |= 1;
        }
        if (this.c0) {
            i3 |= 8;
        }
        if (this.d0) {
            i3 |= 16;
        }
        this.f8206J.a(i, list, i4, i3);
        this.G.dismiss();
        AbstractC5853yy.b = null;
        AbstractC3364kK0.g("Android.ContactsPicker.DialogAction", i2, 2);
        AbstractC3364kK0.d("Android.ContactsPicker.ContactCount", size2);
        AbstractC3364kK0.d("Android.ContactsPicker.SelectCount", size);
        AbstractC3364kK0.g("Android.ContactsPicker.SelectPercentage", i4, 101);
        AbstractC3364kK0.g("Android.ContactsPicker.PropertiesRequested", i3, 32);
    }

    public final List e(boolean z, boolean z2, List list) {
        if (!z) {
            return null;
        }
        return !z2 ? new ArrayList() : list;
    }

    public final void f(List list) {
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            C3638ly lyVar = (C3638ly) it.next();
            arrayList.add(new C0057Ay(e(this.W, C0472Hs.f8185J, Arrays.asList(lyVar.G)), e(this.a0, C0472Hs.K, lyVar.H), e(this.b0, C0472Hs.L, lyVar.I), e(this.c0, C0472Hs.I, lyVar.f10389J), e(this.d0, C0472Hs.M, lyVar.K)));
        }
        d(1, arrayList, 1);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.done) {
            List b = this.Q.b();
            Collections.sort(b);
            if (!this.d0 || !C0472Hs.M) {
                f(b);
                return;
            }
            C1756ax axVar = new C1756ax(((Context) this.I.f11022J.get()).getContentResolver(), this.R, b, this);
            Executor executor = AbstractC2032cb.f9616a;
            axVar.f();
            ((ExecutorC1463Ya) executor).execute(axVar.e);
        } else if (id == R.id.search) {
            this.U.setVisibility(8);
            this.T = new HashSet(this.Q.c);
            this.S.setVisibility(8);
            C0472Hs hs = this.N;
            hs.U = true;
            hs.g();
            ContactsPickerToolbar contactsPickerToolbar = this.K;
            contactsPickerToolbar.x0 = true;
            contactsPickerToolbar.w0.a();
            contactsPickerToolbar.V();
            contactsPickerToolbar.A0.requestFocus();
            C3493l60.F.i(contactsPickerToolbar.A0);
            contactsPickerToolbar.I(null);
        } else {
            d(0, null, 0);
        }
    }
}
