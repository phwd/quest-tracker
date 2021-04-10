package com.oculus.auth.api;

public final class GraphQLAuthQuery {
    public static final String CLAIM_ACTIVATION_BUNDLE = "Mutation ClaimActivationBundle : ClaimActivationBundlePayload {   claim_activation_bundle(<input>) {    entitlements {      item {        id,      },    },  }}";
    public static final String REGISTER_HMD = "Mutation RegisterHmd : RegisterHmdPayload {   hmd_create(<input>) {    hmd {      hmd_serial,      hmd_type,    },  }}";
    public static final String SET_DEVICE_OWNER = "Mutation SetDeviceOwner : SetDeviceOwnerPayload {   set_device_owner(<input>) {    user {      id,    },  }}";
    public static final String USER_INFO = "me() { device_secret }";
}
