package com.oculus.modules.codegen;

import com.oculus.panellib.modules.RCTResolveCaller;
import java.util.List;
import org.json.JSONObject;

public class ResolverFactory {
    public static final <T> Resolver<T> createNullableResolver(Resolver<T> typedResolver, RCTResolveCaller caller, int resolveID, int rejectID) {
        return new NullableResolverImpl(typedResolver, caller, resolveID, rejectID);
    }

    public static final Resolver<Void> createVoidResolver(RCTResolveCaller caller, int resolveID, int rejectID) {
        return new VoidResolverImpl(caller, resolveID, rejectID);
    }

    public static final <T extends Enum<T>> Resolver<T> createEnumResolver(RCTResolveCaller caller, int resolveID, int rejectID) {
        return new EnumResolverImpl(caller, resolveID, rejectID);
    }

    public static final <T> Resolver<T> createBasicResolver(RCTResolveCaller caller, int resolveID, int rejectID) {
        return new BasicResolverImpl(caller, resolveID, rejectID);
    }

    public static final <T extends NativeModuleParcel> Resolver<T> createParcelResolver(RCTResolveCaller caller, int resolveID, int rejectID) {
        return new ParcelResolverImpl(caller, resolveID, rejectID);
    }

    public static final Resolver<List<Boolean>> createBooleanListResolver(RCTResolveCaller caller, int resolveID, int rejectID) {
        return new BooleanListResolverImpl(caller, resolveID, rejectID);
    }

    public static final Resolver<List<Double>> createDoubleListResolver(RCTResolveCaller caller, int resolveID, int rejectID) {
        return new DoubleListResolverImpl(caller, resolveID, rejectID);
    }

    public static final <T extends Enum<T>> Resolver<List<T>> createEnumListResolver(RCTResolveCaller caller, int resolveID, int rejectID) {
        return new EnumListResolverImpl(caller, resolveID, rejectID);
    }

    public static final Resolver<List<Integer>> createIntListResolver(RCTResolveCaller caller, int resolveID, int rejectID) {
        return new IntListResolverImpl(caller, resolveID, rejectID);
    }

    public static final Resolver<List<JSONObject>> createObjectListResolver(RCTResolveCaller caller, int resolveID, int rejectID) {
        return new ObjectListResolverImpl(caller, resolveID, rejectID);
    }

    public static final <T extends NativeModuleParcel> Resolver<List<T>> createParcelListResolver(RCTResolveCaller caller, int resolveID, int rejectID) {
        return new ParcelListResolverImpl(caller, resolveID, rejectID);
    }

    public static final Resolver<List<String>> createStringListResolver(RCTResolveCaller caller, int resolveID, int rejectID) {
        return new StringListResolverImpl(caller, resolveID, rejectID);
    }

    public static final Resolver<List<Void>> createVoidListResolver(RCTResolveCaller caller, int resolveID, int rejectID) {
        return new VoidListResolverImpl(caller, resolveID, rejectID);
    }
}
