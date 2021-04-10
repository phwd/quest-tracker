package com.facebook.assistant.oacr;

import X.C0863l4;
import X.KJ;
import X.l2;
import X.lA;
import android.util.Pair;
import com.facebook.hyperthrift.HyperThriftBase;
import com.facebook.jni.HybridData;
import com.facebook.messenger.assistant.thrift.Account;
import com.facebook.messenger.assistant.thrift.AccountContacts;
import com.facebook.messenger.assistant.thrift.AccountsList;
import com.facebook.messenger.assistant.thrift.AllowList;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class AccountsApi {
    public final HybridData mHybridData;

    private native void activateNative(ByteBuffer byteBuffer, ByteBuffer byteBuffer2);

    private native void setAccountContactsNative(ByteBuffer byteBuffer);

    private native void setAccountsNative(ByteBuffer byteBuffer);

    private native void wipeoutLearnedDataNative(ByteBuffer byteBuffer);

    static {
        KJ.A05("oacr_api_jni", 0);
    }

    private void activateImpl(List list, List list2) {
        List arrayList;
        C0863l4 l4Var = new C0863l4();
        l4Var.A02(0, list);
        Object[] A03 = l4Var.A03();
        HyperThriftBase.Builder.A01(A03, 0);
        AccountsList accountsList = new AccountsList();
        accountsList.A02("com.facebook.messenger.assistant.thrift.AccountsList", A03);
        if (list2 == null) {
            arrayList = Collections.emptyList();
        } else {
            arrayList = new ArrayList(list2.size());
            Iterator it = list2.iterator();
            while (it.hasNext()) {
                Pair pair = (Pair) it.next();
                l2 l2Var = new l2();
                l2Var.A02(0, pair.first);
                l2Var.A02(1, pair.second);
                Object[] A032 = l2Var.A03();
                HyperThriftBase.Builder.A01(A032, 1);
                AccountContacts accountContacts = new AccountContacts();
                accountContacts.A02("com.facebook.messenger.assistant.thrift.AccountContacts", A032);
                arrayList.add(accountContacts);
            }
        }
        lA lAVar = new lA();
        lAVar.A02(0, arrayList);
        Object[] A033 = lAVar.A03();
        HyperThriftBase.Builder.A01(A033, 0);
        AllowList allowList = new AllowList();
        allowList.A02("com.facebook.messenger.assistant.thrift.AllowList", A033);
        activateNative(OacrUtil.serialize("com.facebook.messenger.assistant.thrift.AccountsList", accountsList), OacrUtil.serialize("com.facebook.messenger.assistant.thrift.AllowList", allowList));
    }

    public void setAccountContacts(Account account, List list) {
        l2 l2Var = new l2();
        l2Var.A02(0, account);
        l2Var.A02(1, list);
        Object[] A03 = l2Var.A03();
        HyperThriftBase.Builder.A01(A03, 1);
        AccountContacts accountContacts = new AccountContacts();
        accountContacts.A02("com.facebook.messenger.assistant.thrift.AccountContacts", A03);
        setAccountContactsNative(OacrUtil.serialize("com.facebook.messenger.assistant.thrift.AccountContacts", accountContacts));
    }

    public void setAccounts(List list) {
        C0863l4 l4Var = new C0863l4();
        l4Var.A02(0, list);
        Object[] A03 = l4Var.A03();
        HyperThriftBase.Builder.A01(A03, 0);
        AccountsList accountsList = new AccountsList();
        accountsList.A02("com.facebook.messenger.assistant.thrift.AccountsList", A03);
        setAccountsNative(OacrUtil.serialize("com.facebook.messenger.assistant.thrift.AccountsList", accountsList));
    }

    public void wipeoutLearnedData(List list) {
        C0863l4 l4Var = new C0863l4();
        l4Var.A02(0, list);
        Object[] A03 = l4Var.A03();
        HyperThriftBase.Builder.A01(A03, 0);
        AccountsList accountsList = new AccountsList();
        accountsList.A02("com.facebook.messenger.assistant.thrift.AccountsList", A03);
        wipeoutLearnedDataNative(OacrUtil.serialize("com.facebook.messenger.assistant.thrift.AccountsList", accountsList));
    }

    public AccountsApi(HybridData hybridData) {
        this.mHybridData = hybridData;
    }

    public void activate(List list) {
        activateImpl(list, null);
    }

    public void activate(List list, List list2) {
        activateImpl(list, list2);
    }
}
