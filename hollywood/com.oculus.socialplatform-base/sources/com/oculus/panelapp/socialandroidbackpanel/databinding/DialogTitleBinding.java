package com.oculus.panelapp.socialandroidbackpanel.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import com.oculus.ocui.OCTextView;
import com.oculus.socialplatform.R;

public abstract class DialogTitleBinding extends AnonymousClass1uW {
    @NonNull
    public final OCTextView dialogTitleTextView;
    @Bindable
    public String mDialogTitleText;

    public abstract void setDialogTitleText(@Nullable String str);

    @Nullable
    public String getDialogTitleText() {
        return this.mDialogTitleText;
    }

    public DialogTitleBinding(Object obj, View view, int i, OCTextView oCTextView) {
        super(obj, view, i);
        this.dialogTitleTextView = oCTextView;
    }

    public static DialogTitleBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static DialogTitleBinding bind(@NonNull View view, @Nullable Object obj) {
        return (DialogTitleBinding) AnonymousClass1uW.bind(obj, view, R.layout.dialog_title);
    }

    @NonNull
    public static DialogTitleBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static DialogTitleBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static DialogTitleBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (DialogTitleBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.dialog_title, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static DialogTitleBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (DialogTitleBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.dialog_title, null, false);
    }
}
