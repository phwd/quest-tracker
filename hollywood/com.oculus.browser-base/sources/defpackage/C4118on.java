package defpackage;

import J.N;
import android.app.Dialog;
import android.content.Context;
import android.net.http.SslCertificate;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.oculus.browser.R;
import java.io.ByteArrayInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* renamed from: on  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4118on implements AdapterView.OnItemSelectedListener {
    public final Context F;
    public final int G;
    public ArrayList H;
    public ArrayList I;

    /* renamed from: J  reason: collision with root package name */
    public CertificateFactory f10576J;
    public Dialog K = null;

    public C4118on(Context context) {
        this.F = context;
        this.G = ((int) context.getResources().getDimension(R.dimen.f17650_resource_name_obfuscated_RES_2131165384)) / 2;
    }

    public static String e(byte[] bArr, char c) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bArr.length; i++) {
            sb.append(String.format("%02X", Byte.valueOf(bArr[i])));
            if (i != bArr.length - 1) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public final void a(Certificate certificate, byte[] bArr, byte[] bArr2) {
        Collection<List<?>> collection;
        LinearLayout linearLayout = new LinearLayout(this.F);
        this.I.add(linearLayout);
        linearLayout.setOrientation(1);
        X509Certificate x509Certificate = (X509Certificate) certificate;
        SslCertificate sslCertificate = new SslCertificate(x509Certificate);
        this.H.add(sslCertificate.getIssuedTo().getCName());
        c(linearLayout, N.MVBnBWgu()).setAllCaps(true);
        b(linearLayout, N.M61$9xnN(), sslCertificate.getIssuedTo().getCName());
        b(linearLayout, N.M3Q$lPl0(), sslCertificate.getIssuedTo().getOName());
        b(linearLayout, N.MtQn_hO9(), sslCertificate.getIssuedTo().getUName());
        b(linearLayout, N.M2rGgvRp(), e(x509Certificate.getSerialNumber().toByteArray(), ':'));
        c(linearLayout, N.MsQpEHij()).setAllCaps(true);
        b(linearLayout, N.M61$9xnN(), sslCertificate.getIssuedBy().getCName());
        b(linearLayout, N.M3Q$lPl0(), sslCertificate.getIssuedBy().getOName());
        b(linearLayout, N.MtQn_hO9(), sslCertificate.getIssuedBy().getUName());
        c(linearLayout, N.MdXOhLT3()).setAllCaps(true);
        DateFormat dateInstance = DateFormat.getDateInstance(2);
        b(linearLayout, N.MnsPVX7Z(), dateInstance.format(sslCertificate.getValidNotBeforeDate()));
        b(linearLayout, N.Mrqn7ZPZ(), dateInstance.format(sslCertificate.getValidNotAfterDate()));
        c(linearLayout, N.Mj6CXcHR()).setAllCaps(true);
        b(linearLayout, N.MGc5t$JX(), e(bArr, ' '));
        b(linearLayout, N.M3Qp55TF(), e(bArr2, ' '));
        ArrayList arrayList = new ArrayList();
        try {
            collection = x509Certificate.getSubjectAlternativeNames();
        } catch (CertificateParsingException unused) {
            collection = null;
        }
        if (collection != null && !collection.isEmpty()) {
            for (List<?> list : collection) {
                if (list != null && list.size() == 2 && list.get(0) != null && list.get(0).getClass() == Integer.class && list.get(1) != null && list.get(1).getClass() == String.class) {
                    int intValue = ((Integer) list.get(0)).intValue();
                    if (intValue == 2 || intValue == 7) {
                        arrayList.add(list.get(1).toString());
                    }
                }
            }
        }
        if (!arrayList.isEmpty()) {
            c(linearLayout, N.MRBlj4Pf()).setAllCaps(true);
            c(linearLayout, N.M_b6sZk8());
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                d(linearLayout, (String) it.next());
            }
        }
    }

    public final void b(LinearLayout linearLayout, String str, String str2) {
        if (!str2.isEmpty()) {
            c(linearLayout, str);
            d(linearLayout, str2);
        }
    }

    public final TextView c(LinearLayout linearLayout, String str) {
        TextView textView = new TextView(this.F);
        textView.setTextAlignment(5);
        int i = this.G;
        textView.setPadding(i, i / 2, i, 0);
        textView.setText(str);
        textView.setTextAppearance(textView.getContext(), R.style.f72100_resource_name_obfuscated_RES_2132017783);
        linearLayout.addView(textView);
        return textView;
    }

    public final void d(LinearLayout linearLayout, String str) {
        TextView textView = new TextView(this.F);
        textView.setTextAlignment(5);
        textView.setText(str);
        int i = this.G;
        textView.setPadding(i, 0, i, i / 2);
        textView.setTextAppearance(textView.getContext(), R.style.f71970_resource_name_obfuscated_RES_2132017770);
        linearLayout.addView(textView);
    }

    public void f(byte[][] bArr) {
        byte[] bArr2;
        Dialog dialog = this.K;
        if (dialog == null || !dialog.isShowing()) {
            this.H = new ArrayList();
            this.I = new ArrayList();
            for (byte[] bArr3 : bArr) {
                try {
                    if (this.f10576J == null) {
                        this.f10576J = CertificateFactory.getInstance("X.509");
                    }
                    Certificate generateCertificate = this.f10576J.generateCertificate(new ByteArrayInputStream(bArr3));
                    byte[] bArr4 = null;
                    try {
                        MessageDigest instance = MessageDigest.getInstance("SHA-256");
                        instance.update(bArr3);
                        bArr2 = instance.digest();
                    } catch (NoSuchAlgorithmException unused) {
                        bArr2 = null;
                    }
                    try {
                        MessageDigest instance2 = MessageDigest.getInstance("SHA-1");
                        instance2.update(bArr3);
                        bArr4 = instance2.digest();
                    } catch (NoSuchAlgorithmException unused2) {
                    }
                    a(generateCertificate, bArr2, bArr4);
                } catch (CertificateException e) {
                    StringBuilder i = AbstractC2531fV.i("Error parsing certificate");
                    i.append(e.toString());
                    AbstractC1220Ua0.a("CertViewer", i.toString(), new Object[0]);
                }
            }
            C3947nn nnVar = new C3947nn(this, this.F, 17367048, this.H);
            nnVar.setDropDownViewResource(17367049);
            LinearLayout linearLayout = new LinearLayout(this.F);
            linearLayout.setOrientation(1);
            TextView textView = new TextView(this.F);
            textView.setText(R.string.f48570_resource_name_obfuscated_RES_2131952174);
            textView.setTextAlignment(5);
            textView.setTextAppearance(textView.getContext(), 16973890);
            textView.setTypeface(textView.getTypeface(), 1);
            int i2 = this.G;
            textView.setPadding(i2, i2, i2, i2 / 2);
            linearLayout.addView(textView);
            Spinner spinner = new Spinner(this.F);
            spinner.setTextAlignment(5);
            spinner.setAdapter((SpinnerAdapter) nnVar);
            spinner.setOnItemSelectedListener(this);
            spinner.setDropDownWidth(-1);
            spinner.setPadding(0, 0, 0, 0);
            linearLayout.addView(spinner);
            LinearLayout linearLayout2 = new LinearLayout(this.F);
            linearLayout2.setOrientation(1);
            for (int i3 = 0; i3 < this.I.size(); i3++) {
                LinearLayout linearLayout3 = (LinearLayout) this.I.get(i3);
                if (i3 != 0) {
                    linearLayout3.setVisibility(8);
                }
                linearLayout2.addView(linearLayout3);
            }
            ScrollView scrollView = new ScrollView(this.F);
            scrollView.addView(linearLayout2);
            linearLayout.addView(scrollView);
            Dialog dialog2 = new Dialog(this.F);
            this.K = dialog2;
            dialog2.requestWindowFeature(1);
            this.K.addContentView(linearLayout, new LinearLayout.LayoutParams(-1, -1));
            this.K.show();
        }
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onItemSelected(AdapterView adapterView, View view, int i, long j) {
        int i2 = 0;
        while (i2 < this.I.size()) {
            ((LinearLayout) this.I.get(i2)).setVisibility(i2 == i ? 0 : 8);
            i2++;
        }
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onNothingSelected(AdapterView adapterView) {
    }
}
