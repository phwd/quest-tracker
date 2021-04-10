package com.oculus.config.updater;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.oculus.http.core.base.ApiRequest;
import java.util.List;

public class RefreshGatekeepersRequest extends ApiRequest<RefreshGatekeepersResponse> {
    public final ImmutableList<String> gatekeepers;

    public RefreshGatekeepersRequest(List<String> list) {
        Preconditions.checkArgument(!list.isEmpty());
        this.gatekeepers = ImmutableList.A0C(list);
    }
}
