package com.oculus.http.core;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class ApiResponseConverterAutoProvider extends AbstractProvider<ApiResponseConverter> {
    @Override // javax.inject.Provider
    public ApiResponseConverter get() {
        return new ApiResponseConverter(this);
    }
}
