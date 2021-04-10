package com.oculus.panelapp.messenger.databinding;

import X.AnonymousClass1uU;
import X.AnonymousClass1uW;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import com.oculus.ocui.OCTextView;
import com.oculus.panelapp.messenger.views.MessengerViewModel;
import com.oculus.socialplatform.R;

public abstract class AnytimeTabletMessengerThreadListNullStateBinding extends AnonymousClass1uW {
    @Bindable
    public MessengerViewModel mMessengerViewModel;
    @NonNull
    public final OCTextView threadListNullStateTitle;
    @NonNull
    public final ConstraintLayout threadListNullStateView;

    public abstract void setMessengerViewModel(@Nullable MessengerViewModel messengerViewModel);

    @Nullable
    public MessengerViewModel getMessengerViewModel() {
        return this.mMessengerViewModel;
    }

    public AnytimeTabletMessengerThreadListNullStateBinding(Object obj, View view, int i, OCTextView oCTextView, ConstraintLayout constraintLayout) {
        super(obj, view, i);
        this.threadListNullStateTitle = oCTextView;
        this.threadListNullStateView = constraintLayout;
    }

    public static AnytimeTabletMessengerThreadListNullStateBinding bind(@NonNull View view) {
        return bind(view, null);
    }

    @Deprecated
    public static AnytimeTabletMessengerThreadListNullStateBinding bind(@NonNull View view, @Nullable Object obj) {
        return (AnytimeTabletMessengerThreadListNullStateBinding) AnonymousClass1uW.bind(obj, view, R.layout.anytime_tablet_messenger_thread_list_null_state);
    }

    @NonNull
    public static AnytimeTabletMessengerThreadListNullStateBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null);
    }

    @NonNull
    public static AnytimeTabletMessengerThreadListNullStateBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, null);
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletMessengerThreadListNullStateBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (AnytimeTabletMessengerThreadListNullStateBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.anytime_tablet_messenger_thread_list_null_state, viewGroup, z);
    }

    @NonNull
    @Deprecated
    public static AnytimeTabletMessengerThreadListNullStateBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        AnonymousClass1uW.checkAndCastToBindingComponent(obj);
        return (AnytimeTabletMessengerThreadListNullStateBinding) AnonymousClass1uU.A00(layoutInflater, R.layout.anytime_tablet_messenger_thread_list_null_state, null, false);
    }
}
