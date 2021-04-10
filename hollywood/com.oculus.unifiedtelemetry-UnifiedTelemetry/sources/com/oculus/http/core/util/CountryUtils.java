package com.oculus.http.core.util;

import com.google.common.collect.SingletonImmutableSet;
import java.util.Set;

public class CountryUtils {
    public static final Set<String> BLACKLISTED_COUNTRIES = new SingletonImmutableSet("ZZ");
}
