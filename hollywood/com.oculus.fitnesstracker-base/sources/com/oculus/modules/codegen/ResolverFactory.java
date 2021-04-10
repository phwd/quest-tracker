package com.oculus.modules.codegen;

import com.oculus.panellib.modules.RCTResolveCaller;
import java.util.List;
import org.json.JSONObject;

public class ResolverFactory {
    public static final <T> Resolver<T> createNullableResolver(Resolver<T> resolver, RCTResolveCaller rCTResolveCaller, int i, int i2) {
        return new NullableResolverImpl(resolver, rCTResolveCaller, i, i2);
    }

    public static final Resolver<Void> createVoidResolver(RCTResolveCaller rCTResolveCaller, int i, int i2) {
        return new VoidResolverImpl(rCTResolveCaller, i, i2);
    }

    public static final <T extends Enum<T>> Resolver<T> createEnumResolver(RCTResolveCaller rCTResolveCaller, int i, int i2) {
        return new EnumResolverImpl(rCTResolveCaller, i, i2);
    }

    public static final <T> Resolver<T> createBasicResolver(RCTResolveCaller rCTResolveCaller, int i, int i2) {
        return new BasicResolverImpl(rCTResolveCaller, i, i2);
    }

    public static final <T extends NativeModuleParcel> Resolver<T> createParcelResolver(RCTResolveCaller rCTResolveCaller, int i, int i2) {
        return new ParcelResolverImpl(rCTResolveCaller, i, i2);
    }

    public static final Resolver<List<Boolean>> createBooleanListResolver(RCTResolveCaller rCTResolveCaller, int i, int i2) {
        return new BooleanListResolverImpl(rCTResolveCaller, i, i2);
    }

    public static final Resolver<List<Double>> createDoubleListResolver(RCTResolveCaller rCTResolveCaller, int i, int i2) {
        return new DoubleListResolverImpl(rCTResolveCaller, i, i2);
    }

    public static final <T extends Enum<T>> Resolver<List<T>> createEnumListResolver(RCTResolveCaller rCTResolveCaller, int i, int i2) {
        return new EnumListResolverImpl(rCTResolveCaller, i, i2);
    }

    public static final Resolver<List<Integer>> createIntListResolver(RCTResolveCaller rCTResolveCaller, int i, int i2) {
        return new IntListResolverImpl(rCTResolveCaller, i, i2);
    }

    public static final Resolver<List<JSONObject>> createObjectListResolver(RCTResolveCaller rCTResolveCaller, int i, int i2) {
        return new ObjectListResolverImpl(rCTResolveCaller, i, i2);
    }

    public static final <T extends NativeModuleParcel> Resolver<List<T>> createParcelListResolver(RCTResolveCaller rCTResolveCaller, int i, int i2) {
        return new ParcelListResolverImpl(rCTResolveCaller, i, i2);
    }

    public static final Resolver<List<String>> createStringListResolver(RCTResolveCaller rCTResolveCaller, int i, int i2) {
        return new StringListResolverImpl(rCTResolveCaller, i, i2);
    }

    public static final Resolver<List<Void>> createVoidListResolver(RCTResolveCaller rCTResolveCaller, int i, int i2) {
        return new VoidListResolverImpl(rCTResolveCaller, i, i2);
    }
}
