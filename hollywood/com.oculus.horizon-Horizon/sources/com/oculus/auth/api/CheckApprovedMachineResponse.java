package com.oculus.auth.api;

import android.os.Bundle;
import com.google.gson.annotations.SerializedName;
import com.oculus.auth.service.contract.ServiceContract;
import java.util.ArrayList;
import java.util.List;

public class CheckApprovedMachineResponse {
    @SerializedName("data")
    public List<ApprovalStatus> approvalStatuses = new ArrayList();

    public static class ApprovalStatus {
        public Boolean approved = false;
    }

    public Bundle getResultBundle() {
        Bundle bundle = new Bundle();
        bundle.putBoolean(ServiceContract.EXTRA_MACHINE_APPROVED, isApproved());
        return bundle;
    }

    public boolean isApproved() {
        for (ApprovalStatus approvalStatus : this.approvalStatuses) {
            if (approvalStatus.approved.booleanValue()) {
                return true;
            }
        }
        return false;
    }
}
