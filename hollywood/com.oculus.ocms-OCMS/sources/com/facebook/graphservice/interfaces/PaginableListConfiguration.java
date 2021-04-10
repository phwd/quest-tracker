package com.facebook.graphservice.interfaces;

import com.facebook.graphservice.interfaces.GraphQLService;
import java.util.Collections;
import java.util.Map;
import javax.annotation.Nullable;

public class PaginableListConfiguration {
    private String[] mAnalyticTags = new String[0];
    @Nullable
    private ConnectionTransientParameters mConnectionTransientParameters;
    private boolean mEnableAtStream = false;
    private int mInitialCount = 1;
    private GraphQLService.OperationCallbacks mOperationCallbacks;
    private int mTotalPageSize = 0;

    public int getTotalPageSize() {
        return this.mTotalPageSize;
    }

    public PaginableListConfiguration setTotalPageSize(int i) {
        this.mTotalPageSize = i;
        return this;
    }

    public int getInitialCount() {
        return this.mInitialCount;
    }

    public PaginableListConfiguration setInitialCount(int i) {
        this.mInitialCount = i;
        return this;
    }

    public boolean isEnableAtStream() {
        return this.mEnableAtStream;
    }

    public PaginableListConfiguration setEnableAtStream(boolean z) {
        this.mEnableAtStream = z;
        return this;
    }

    public GraphQLService.OperationCallbacks getOperationCallbacks() {
        return this.mOperationCallbacks;
    }

    public PaginableListConfiguration setOperationCallbacks(GraphQLService.OperationCallbacks operationCallbacks) {
        this.mOperationCallbacks = operationCallbacks;
        return this;
    }

    public Map<String, Object> getOverriddenParameters() {
        ConnectionTransientParameters connectionTransientParameters = this.mConnectionTransientParameters;
        if (connectionTransientParameters == null) {
            return Collections.unmodifiableMap(Collections.emptyMap());
        }
        return connectionTransientParameters.getParameterMap();
    }

    public PaginableListConfiguration setOverriddenParameters(@Nullable ConnectionTransientParameters connectionTransientParameters) {
        this.mConnectionTransientParameters = connectionTransientParameters;
        return this;
    }

    public PaginableListConfiguration setAnalyticTags(String[] strArr) {
        this.mAnalyticTags = strArr;
        return this;
    }

    public String[] getAnalyticTags() {
        return this.mAnalyticTags;
    }
}
