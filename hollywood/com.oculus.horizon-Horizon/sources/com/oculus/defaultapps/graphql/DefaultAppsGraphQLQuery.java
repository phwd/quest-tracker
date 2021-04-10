package com.oculus.defaultapps.graphql;

import X.AnonymousClass006;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.device.DeviceType;
import com.oculus.http.common.graphql.GraphQLQueryConstants;

@Dependencies({})
public class DefaultAppsGraphQLQuery {
    public static final String DEFAULT_APPS_CONFIG_QUERY = "query DefaultAppsConfig($hmd: HmdTypeEnum!) { default_apps_nux_config(hmd_type: $hmd) {   hi_pri_apps_wait_timeout }}";
    public String mDefaultAppsQuery;

    @Inject
    public DefaultAppsGraphQLQuery() {
        String str;
        if (DeviceType.is6DOF()) {
            str = GraphQLQueryConstants.OCULUS_6DOF_STORE_ID;
        } else {
            str = GraphQLQueryConstants.HORIZON_STORE_FB_ID;
        }
        this.mDefaultAppsQuery = AnonymousClass006.A0B("query DefaultApps($hmd: HmdTypeEnum!, $high_priority: Boolean, $machine_id: ID) {  node(node_id:\"", str, "\") {      ... on AppStore {        default_applications(hmd_type:$hmd, high_priority:$high_priority) {", GraphQLQueryConstants.ITEM_CORE_ATTRIBUTES_OSS, "        }        default_environment(hmd_type:$hmd) {", GraphQLQueryConstants.ITEM_CORE_ATTRIBUTES_OSS, "        }    }  }}");
    }
}
