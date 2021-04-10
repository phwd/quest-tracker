package com.oculus.horizon.cast;

import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.Dependencies;

@Dependencies({})
@ApplicationScoped
public class NetworkUtils {
    public static final String FAILED_TO_GET_NETWORK_INTERFACES = "Failed to get network interfaces";
    public static final String NO_INTERNET_CONNECTION_ERR = "No available internet";
    public static final String TAG = "NetworkUtils";
    public static volatile NetworkUtils _UL__ULSEP_com_oculus_horizon_cast_NetworkUtils_ULSEP_INSTANCE;
}
