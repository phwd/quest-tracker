package org.chromium.chrome.browser.autofill.settings;

import J.N;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import com.google.android.material.textfield.TextInputLayout;
import com.oculus.browser.R;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;
import org.chromium.base.ThreadUtils;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.autofill.PersonalDataManager;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AutofillLocalCardEditor extends AbstractC0009Ad {
    public Button E0;
    public TextInputLayout F0;
    public EditText G0;
    public TextInputLayout H0;
    public EditText I0;
    public TextInputLayout J0;
    public EditText K0;
    public Spinner L0;
    public Spinner M0;
    public boolean N0 = true;
    public int O0;
    public int P0;

    @Override // org.chromium.chrome.browser.autofill.settings.AutofillEditorBase
    public void afterTextChanged(Editable editable) {
        k1();
    }

    @Override // org.chromium.chrome.browser.autofill.settings.AutofillEditorBase
    public void e1() {
        if (this.y0 != null) {
            PersonalDataManager c = PersonalDataManager.c();
            String str = this.y0;
            Objects.requireNonNull(c);
            Object obj = ThreadUtils.f10596a;
            N.MIAwuIe5(c.b, c, str);
            C2187dT0 a2 = C2187dT0.a();
            String str2 = this.y0;
            Objects.requireNonNull(a2);
            for (AbstractC2016cT0 ct0 : C2187dT0.f9784a) {
                PostTask.b(Zo1.f9374a, new RunnableC1845bT0(a2, ct0, str2), 0);
            }
        }
    }

    @Override // org.chromium.chrome.browser.autofill.settings.AutofillEditorBase
    public int f1() {
        return R.layout.f36970_resource_name_obfuscated_RES_2131624006;
    }

    @Override // org.chromium.chrome.browser.autofill.settings.AutofillEditorBase
    public int g1(boolean z) {
        return z ? R.string.f47280_resource_name_obfuscated_RES_2131952045 : R.string.f47370_resource_name_obfuscated_RES_2131952054;
    }

    @Override // org.chromium.chrome.browser.autofill.settings.AutofillEditorBase
    public boolean h1() {
        String replaceAll = this.K0.getText().toString().replaceAll("\\s+", "");
        PersonalDataManager c = PersonalDataManager.c();
        if (TextUtils.isEmpty(c.a(replaceAll, true))) {
            this.J0.w(this.A0.getString(R.string.f58500_resource_name_obfuscated_RES_2131953167));
            return false;
        }
        Object obj = ThreadUtils.f10596a;
        PersonalDataManager.CreditCard creditCard = (PersonalDataManager.CreditCard) N.MHzz0BSK(c.b, c, replaceAll);
        creditCard.f10614a = this.y0;
        creditCard.b = "Chrome settings";
        creditCard.e = this.G0.getText().toString().trim();
        creditCard.h = String.valueOf(this.L0.getSelectedItemPosition() + 1);
        creditCard.i = (String) this.M0.getSelectedItem();
        creditCard.l = ((PersonalDataManager.AutofillProfile) this.C0.getSelectedItem()).getGUID();
        creditCard.o = this.I0.getText().toString().trim();
        creditCard.f10614a = c.j(creditCard);
        C2187dT0.a().b(creditCard);
        if (this.z0) {
            AbstractC3535lK0.a("AutofillCreditCardsAdded");
            if (!creditCard.getNickname().isEmpty()) {
                AbstractC3535lK0.a("AutofillCreditCardsAddedWithNickname");
            }
        }
        return true;
    }

    @Override // defpackage.AbstractC0009Ad
    public void i1(View view) {
        super.i1(view);
        this.G0.addTextChangedListener(this);
        this.K0.addTextChangedListener(this);
        this.L0.setOnItemSelectedListener(this);
        this.M0.setOnItemSelectedListener(this);
        this.L0.setOnTouchListener(this);
        this.M0.setOnTouchListener(this);
    }

    public final /* synthetic */ void j1(boolean z) {
        this.H0.r(z);
    }

    public final void k1() {
        this.E0.setEnabled(!TextUtils.isEmpty(this.K0.getText()) && this.N0);
    }

    @Override // defpackage.AbstractC0009Ad, defpackage.AbstractComponentCallbacksC3550lS, org.chromium.chrome.browser.autofill.settings.AutofillEditorBase
    public View l0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        WindowManager.LayoutParams attributes = u().getWindow().getAttributes();
        attributes.flags |= 8192;
        u().getWindow().setAttributes(attributes);
        View l0 = super.l0(layoutInflater, viewGroup, bundle);
        this.E0 = (Button) l0.findViewById(R.id.button_primary);
        this.F0 = (TextInputLayout) l0.findViewById(R.id.credit_card_name_label);
        this.G0 = (EditText) l0.findViewById(R.id.credit_card_name_edit);
        this.H0 = (TextInputLayout) l0.findViewById(R.id.credit_card_nickname_label);
        this.I0 = (EditText) l0.findViewById(R.id.credit_card_nickname_edit);
        this.J0 = (TextInputLayout) l0.findViewById(R.id.credit_card_number_label);
        this.K0 = (EditText) l0.findViewById(R.id.credit_card_number_edit);
        this.H0.setVisibility(N.M09VlOh_("AutofillEnableCardNicknameManagement") ? 0 : 8);
        this.I0.addTextChangedListener(new C0862Od(this));
        this.I0.setOnFocusChangeListener(new View$OnFocusChangeListenerC0801Nd(this));
        this.K0.addTextChangedListener(new C4362qB());
        this.L0 = (Spinner) l0.findViewById(R.id.autofill_credit_card_editor_month_spinner);
        this.M0 = (Spinner) l0.findViewById(R.id.autofill_credit_card_editor_year_spinner);
        ArrayAdapter arrayAdapter = new ArrayAdapter(u(), 17367048);
        Calendar instance = Calendar.getInstance();
        boolean z = true;
        instance.set(5, 1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM (MM)", Locale.getDefault());
        for (int i = 0; i < 12; i++) {
            instance.set(2, i);
            arrayAdapter.add(simpleDateFormat.format(instance.getTime()));
        }
        arrayAdapter.setDropDownViewResource(17367049);
        this.L0.setAdapter((SpinnerAdapter) arrayAdapter);
        ArrayAdapter arrayAdapter2 = new ArrayAdapter(u(), 17367048);
        int i2 = instance.get(1);
        for (int i3 = i2; i3 < i2 + 10; i3++) {
            arrayAdapter2.add(Integer.toString(i3));
        }
        arrayAdapter2.setDropDownViewResource(17367049);
        this.M0.setAdapter((SpinnerAdapter) arrayAdapter2);
        PersonalDataManager.CreditCard creditCard = this.B0;
        if (creditCard == null) {
            this.J0.requestFocus();
        } else {
            if (!TextUtils.isEmpty(creditCard.getName())) {
                this.F0.f9696J.setText(this.B0.getName());
            }
            if (!TextUtils.isEmpty(this.B0.getNumber())) {
                this.J0.f9696J.setText(this.B0.getNumber());
            }
            this.F0.setFocusableInTouchMode(true);
            int parseInt = (!this.B0.getMonth().isEmpty() ? Integer.parseInt(this.B0.getMonth()) : 1) - 1;
            this.O0 = parseInt;
            this.L0.setSelection(parseInt);
            this.P0 = 0;
            int i4 = 0;
            while (true) {
                if (i4 >= this.M0.getAdapter().getCount()) {
                    z = false;
                    break;
                } else if (this.B0.getYear().equals(this.M0.getAdapter().getItem(i4))) {
                    this.P0 = i4;
                    break;
                } else {
                    i4++;
                }
            }
            if (!z && !this.B0.getYear().isEmpty()) {
                ((ArrayAdapter) this.M0.getAdapter()).insert(this.B0.getYear(), 0);
                this.P0 = 0;
            }
            this.M0.setSelection(this.P0);
            if (!this.B0.getNickname().isEmpty()) {
                this.I0.setText(this.B0.getNickname());
            }
        }
        i1(l0);
        return l0;
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onItemSelected(AdapterView adapterView, View view, int i, long j) {
        if ((adapterView == this.M0 && i != this.P0) || ((adapterView == this.L0 && i != this.O0) || (adapterView == this.C0 && i != this.D0))) {
            k1();
        }
    }
}
