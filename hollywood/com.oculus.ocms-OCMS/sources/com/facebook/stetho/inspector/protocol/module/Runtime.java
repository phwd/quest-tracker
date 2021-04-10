package com.facebook.stetho.inspector.protocol.module;

import android.content.Context;
import com.facebook.debug.log.LoggingUtil;
import com.facebook.ipc.activity.ActivityConstants;
import com.facebook.metagen.CodeGeneratorMetadataAnnotation;
import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.inspector.console.RuntimeRepl;
import com.facebook.stetho.inspector.console.RuntimeReplFactory;
import com.facebook.stetho.inspector.helper.ObjectIdMapper;
import com.facebook.stetho.inspector.jsonrpc.DisconnectReceiver;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcException;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcPeer;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcResult;
import com.facebook.stetho.inspector.jsonrpc.protocol.JsonRpcError;
import com.facebook.stetho.inspector.protocol.ChromeDevtoolsDomain;
import com.facebook.stetho.inspector.protocol.ChromeDevtoolsMethod;
import com.facebook.stetho.inspector.runtime.RhinoDetectingRuntimeReplFactory;
import com.facebook.stetho.json.ObjectMapper;
import com.facebook.stetho.json.annotation.JsonProperty;
import com.facebook.stetho.json.annotation.JsonValue;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

public class Runtime implements ChromeDevtoolsDomain {
    private static final Map<JsonRpcPeer, Session> sSessions = Collections.synchronizedMap(new HashMap());
    private final ObjectMapper mObjectMapper;
    private final RuntimeReplFactory mReplFactory;

    public static class RemoteObject {
        @JsonProperty
        public String className;
        @JsonProperty
        public String description;
        @JsonProperty
        public String objectId;
        @JsonProperty
        public ObjectSubType subtype;
        @JsonProperty(required = true)
        public ObjectType type;
        @JsonProperty
        public Object value;
    }

    @Deprecated
    public Runtime() {
        this(new RuntimeReplFactory() {
            /* class com.facebook.stetho.inspector.protocol.module.Runtime.AnonymousClass1 */

            @Override // com.facebook.stetho.inspector.console.RuntimeReplFactory
            public RuntimeRepl newInstance() {
                return new RuntimeRepl() {
                    /* class com.facebook.stetho.inspector.protocol.module.Runtime.AnonymousClass1.AnonymousClass1 */

                    @Override // com.facebook.stetho.inspector.console.RuntimeRepl
                    public Object evaluate(String str) throws Throwable {
                        return "Not supported with legacy Runtime module";
                    }
                };
            }
        });
    }

    public Runtime(Context context) {
        this(new RhinoDetectingRuntimeReplFactory(context));
    }

    public Runtime(RuntimeReplFactory runtimeReplFactory) {
        this.mObjectMapper = new ObjectMapper();
        this.mReplFactory = runtimeReplFactory;
    }

    public static int mapObject(JsonRpcPeer jsonRpcPeer, Object obj) {
        return getSession(jsonRpcPeer).getObjects().putObject(obj);
    }

    @Nonnull
    private static synchronized Session getSession(final JsonRpcPeer jsonRpcPeer) {
        Session session;
        synchronized (Runtime.class) {
            session = sSessions.get(jsonRpcPeer);
            if (session == null) {
                session = new Session();
                sSessions.put(jsonRpcPeer, session);
                jsonRpcPeer.registerDisconnectReceiver(new DisconnectReceiver() {
                    /* class com.facebook.stetho.inspector.protocol.module.Runtime.AnonymousClass2 */

                    @Override // com.facebook.stetho.inspector.jsonrpc.DisconnectReceiver
                    public void onDisconnect() {
                        Runtime.sSessions.remove(jsonRpcPeer);
                    }
                });
            }
        }
        return session;
    }

    @ChromeDevtoolsMethod
    public void releaseObject(JsonRpcPeer jsonRpcPeer, JSONObject jSONObject) throws JSONException {
        getSession(jsonRpcPeer).getObjects().removeObjectById(Integer.parseInt(jSONObject.getString("objectId")));
    }

    @ChromeDevtoolsMethod
    public void releaseObjectGroup(JsonRpcPeer jsonRpcPeer, JSONObject jSONObject) {
        LogUtil.w("Ignoring request to releaseObjectGroup: " + jSONObject);
    }

    @ChromeDevtoolsMethod
    public CallFunctionOnResponse callFunctionOn(JsonRpcPeer jsonRpcPeer, JSONObject jSONObject) throws JsonRpcException {
        CallFunctionOnRequest callFunctionOnRequest = (CallFunctionOnRequest) this.mObjectMapper.convertValue(jSONObject, CallFunctionOnRequest.class);
        Session session = getSession(jsonRpcPeer);
        Object objectOrThrow = session.getObjectOrThrow(callFunctionOnRequest.objectId);
        if (callFunctionOnRequest.functionDeclaration.startsWith("function protoList(")) {
            ObjectProtoContainer objectProtoContainer = new ObjectProtoContainer(objectOrThrow);
            RemoteObject remoteObject = new RemoteObject();
            remoteObject.type = ObjectType.OBJECT;
            remoteObject.subtype = ObjectSubType.NODE;
            remoteObject.className = objectOrThrow.getClass().getName();
            remoteObject.description = getPropertyClassName(objectOrThrow);
            remoteObject.objectId = String.valueOf(session.getObjects().putObject(objectProtoContainer));
            CallFunctionOnResponse callFunctionOnResponse = new CallFunctionOnResponse();
            callFunctionOnResponse.result = remoteObject;
            callFunctionOnResponse.wasThrown = false;
            return callFunctionOnResponse;
        }
        JsonRpcError.ErrorCode errorCode = JsonRpcError.ErrorCode.INTERNAL_ERROR;
        throw new JsonRpcException(new JsonRpcError(errorCode, "Expected protoList, got: " + callFunctionOnRequest.functionDeclaration, null));
    }

    @ChromeDevtoolsMethod
    public JsonRpcResult evaluate(JsonRpcPeer jsonRpcPeer, JSONObject jSONObject) {
        return getSession(jsonRpcPeer).evaluate(this.mReplFactory, jSONObject);
    }

    @ChromeDevtoolsMethod
    public JsonRpcResult getProperties(JsonRpcPeer jsonRpcPeer, JSONObject jSONObject) throws JsonRpcException {
        return getSession(jsonRpcPeer).getProperties(jSONObject);
    }

    /* access modifiers changed from: private */
    public static String getPropertyClassName(Object obj) {
        String simpleName = obj.getClass().getSimpleName();
        return simpleName.length() == 0 ? obj.getClass().getName() : simpleName;
    }

    /* access modifiers changed from: private */
    public static class ObjectProtoContainer {
        public final Object object;

        public ObjectProtoContainer(Object obj) {
            this.object = obj;
        }
    }

    /* access modifiers changed from: private */
    public static class Session {
        private final ObjectMapper mObjectMapper;
        private final ObjectIdMapper mObjects;
        @Nullable
        private RuntimeRepl mRepl;

        private Session() {
            this.mObjects = new ObjectIdMapper();
            this.mObjectMapper = new ObjectMapper();
        }

        public ObjectIdMapper getObjects() {
            return this.mObjects;
        }

        public Object getObjectOrThrow(String str) throws JsonRpcException {
            Object objectForId = getObjects().getObjectForId(Integer.parseInt(str));
            if (objectForId != null) {
                return objectForId;
            }
            JsonRpcError.ErrorCode errorCode = JsonRpcError.ErrorCode.INVALID_REQUEST;
            throw new JsonRpcException(new JsonRpcError(errorCode, "No object found for " + str, null));
        }

        public RemoteObject objectForRemote(Object obj) {
            RemoteObject remoteObject = new RemoteObject();
            if (obj == null) {
                remoteObject.type = ObjectType.OBJECT;
                remoteObject.subtype = ObjectSubType.NULL;
                remoteObject.value = JSONObject.NULL;
            } else if (obj instanceof Boolean) {
                remoteObject.type = ObjectType.BOOLEAN;
                remoteObject.value = obj;
            } else if (obj instanceof Number) {
                remoteObject.type = ObjectType.NUMBER;
                remoteObject.value = obj;
            } else if (obj instanceof Character) {
                remoteObject.type = ObjectType.NUMBER;
                remoteObject.value = Integer.valueOf(((Character) obj).charValue());
            } else if (obj instanceof String) {
                remoteObject.type = ObjectType.STRING;
                remoteObject.value = String.valueOf(obj);
            } else {
                remoteObject.type = ObjectType.OBJECT;
                remoteObject.className = "What??";
                remoteObject.objectId = String.valueOf(this.mObjects.putObject(obj));
                if (obj.getClass().isArray()) {
                    remoteObject.description = "array";
                } else if (obj instanceof List) {
                    remoteObject.description = "List";
                } else if (obj instanceof Set) {
                    remoteObject.description = "Set";
                } else if (obj instanceof Map) {
                    remoteObject.description = "Map";
                } else {
                    remoteObject.description = Runtime.getPropertyClassName(obj);
                }
            }
            return remoteObject;
        }

        public EvaluateResponse evaluate(RuntimeReplFactory runtimeReplFactory, JSONObject jSONObject) {
            EvaluateRequest evaluateRequest = (EvaluateRequest) this.mObjectMapper.convertValue(jSONObject, EvaluateRequest.class);
            try {
                if (!evaluateRequest.objectGroup.equals("console")) {
                    return buildExceptionResponse("Not supported by FAB");
                }
                return buildNormalResponse(getRepl(runtimeReplFactory).evaluate(evaluateRequest.expression));
            } catch (Throwable th) {
                return buildExceptionResponse(th);
            }
        }

        @Nonnull
        private synchronized RuntimeRepl getRepl(RuntimeReplFactory runtimeReplFactory) {
            if (this.mRepl == null) {
                this.mRepl = runtimeReplFactory.newInstance();
            }
            return this.mRepl;
        }

        private EvaluateResponse buildNormalResponse(Object obj) {
            EvaluateResponse evaluateResponse = new EvaluateResponse();
            evaluateResponse.wasThrown = false;
            evaluateResponse.result = objectForRemote(obj);
            return evaluateResponse;
        }

        private EvaluateResponse buildExceptionResponse(Object obj) {
            EvaluateResponse evaluateResponse = new EvaluateResponse();
            evaluateResponse.wasThrown = true;
            evaluateResponse.result = objectForRemote(obj);
            evaluateResponse.exceptionDetails = new ExceptionDetails();
            evaluateResponse.exceptionDetails.text = obj.toString();
            return evaluateResponse;
        }

        public GetPropertiesResponse getProperties(JSONObject jSONObject) throws JsonRpcException {
            GetPropertiesRequest getPropertiesRequest = (GetPropertiesRequest) this.mObjectMapper.convertValue(jSONObject, GetPropertiesRequest.class);
            if (!getPropertiesRequest.ownProperties) {
                GetPropertiesResponse getPropertiesResponse = new GetPropertiesResponse();
                getPropertiesResponse.result = new ArrayList();
                return getPropertiesResponse;
            }
            Object objectOrThrow = getObjectOrThrow(getPropertiesRequest.objectId);
            if (objectOrThrow.getClass().isArray()) {
                objectOrThrow = arrayToList(objectOrThrow);
            }
            if (objectOrThrow instanceof ObjectProtoContainer) {
                return getPropertiesForProtoContainer((ObjectProtoContainer) objectOrThrow);
            }
            if (objectOrThrow instanceof List) {
                return getPropertiesForIterable((List) objectOrThrow, true);
            }
            if (objectOrThrow instanceof Set) {
                return getPropertiesForIterable((Set) objectOrThrow, false);
            }
            if (objectOrThrow instanceof Map) {
                return getPropertiesForMap(objectOrThrow);
            }
            return getPropertiesForObject(objectOrThrow);
        }

        private List<?> arrayToList(Object obj) {
            Class<?> cls = obj.getClass();
            if (!cls.isArray()) {
                throw new IllegalArgumentException("Argument must be an array.  Was " + cls);
            } else if (!cls.getComponentType().isPrimitive()) {
                return Arrays.asList((Object[]) obj);
            } else {
                int length = Array.getLength(obj);
                ArrayList arrayList = new ArrayList(length);
                for (int i = 0; i < length; i++) {
                    arrayList.add(Array.get(obj, i));
                }
                return arrayList;
            }
        }

        private GetPropertiesResponse getPropertiesForProtoContainer(ObjectProtoContainer objectProtoContainer) {
            Object obj = objectProtoContainer.object;
            RemoteObject remoteObject = new RemoteObject();
            remoteObject.type = ObjectType.OBJECT;
            remoteObject.subtype = ObjectSubType.NODE;
            remoteObject.className = obj.getClass().getName();
            remoteObject.description = Runtime.getPropertyClassName(obj);
            remoteObject.objectId = String.valueOf(this.mObjects.putObject(obj));
            PropertyDescriptor propertyDescriptor = new PropertyDescriptor();
            propertyDescriptor.name = ActivityConstants.Extras.WATCH_FEED_INJECTION;
            propertyDescriptor.value = remoteObject;
            GetPropertiesResponse getPropertiesResponse = new GetPropertiesResponse();
            getPropertiesResponse.result = new ArrayList(1);
            getPropertiesResponse.result.add(propertyDescriptor);
            return getPropertiesResponse;
        }

        private GetPropertiesResponse getPropertiesForIterable(Iterable<?> iterable, boolean z) {
            int i;
            String str;
            GetPropertiesResponse getPropertiesResponse = new GetPropertiesResponse();
            ArrayList arrayList = new ArrayList();
            int i2 = 0;
            for (Object obj : iterable) {
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor();
                if (z) {
                    i = i2 + 1;
                    str = String.valueOf(i2);
                } else {
                    i = i2;
                    str = null;
                }
                propertyDescriptor.name = str;
                propertyDescriptor.value = objectForRemote(obj);
                arrayList.add(propertyDescriptor);
                i2 = i;
            }
            getPropertiesResponse.result = arrayList;
            return getPropertiesResponse;
        }

        private GetPropertiesResponse getPropertiesForMap(Object obj) {
            GetPropertiesResponse getPropertiesResponse = new GetPropertiesResponse();
            ArrayList arrayList = new ArrayList();
            for (Map.Entry entry : ((Map) obj).entrySet()) {
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor();
                propertyDescriptor.name = String.valueOf(entry.getKey());
                propertyDescriptor.value = objectForRemote(entry.getValue());
                arrayList.add(propertyDescriptor);
            }
            getPropertiesResponse.result = arrayList;
            return getPropertiesResponse;
        }

        private GetPropertiesResponse getPropertiesForObject(Object obj) {
            GetPropertiesResponse getPropertiesResponse = new GetPropertiesResponse();
            ArrayList arrayList = new ArrayList();
            for (Class<?> cls = obj.getClass(); cls != null; cls = cls.getSuperclass()) {
                ArrayList<Field> arrayList2 = new ArrayList(Arrays.asList(cls.getDeclaredFields()));
                Collections.reverse(arrayList2);
                String str = cls == obj.getClass() ? "" : cls.getSimpleName() + ".";
                for (Field field : arrayList2) {
                    if (!Modifier.isStatic(field.getModifiers())) {
                        field.setAccessible(true);
                        try {
                            Object obj2 = field.get(obj);
                            PropertyDescriptor propertyDescriptor = new PropertyDescriptor();
                            propertyDescriptor.name = str + field.getName();
                            propertyDescriptor.value = objectForRemote(obj2);
                            arrayList.add(propertyDescriptor);
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
            Collections.reverse(arrayList);
            getPropertiesResponse.result = arrayList;
            return getPropertiesResponse;
        }
    }

    private static class CallFunctionOnRequest {
        @JsonProperty
        public List<CallArgument> arguments;
        @JsonProperty(required = false)
        public Boolean doNotPauseOnExceptionsAndMuteConsole;
        @JsonProperty
        public String functionDeclaration;
        @JsonProperty(required = false)
        public Boolean generatePreview;
        @JsonProperty
        public String objectId;
        @JsonProperty(required = false)
        public Boolean returnByValue;

        private CallFunctionOnRequest() {
        }
    }

    private static class CallFunctionOnResponse implements JsonRpcResult {
        @JsonProperty
        public RemoteObject result;
        @JsonProperty(required = false)
        public Boolean wasThrown;

        private CallFunctionOnResponse() {
        }
    }

    private static class CallArgument {
        @JsonProperty(required = false)
        public String objectId;
        @JsonProperty(required = false)
        public ObjectType type;
        @JsonProperty(required = false)
        public Object value;

        private CallArgument() {
        }
    }

    /* access modifiers changed from: private */
    public static class GetPropertiesRequest implements JsonRpcResult {
        @JsonProperty(required = true)
        public String objectId;
        @JsonProperty(required = true)
        public boolean ownProperties;

        private GetPropertiesRequest() {
        }
    }

    /* access modifiers changed from: private */
    public static class GetPropertiesResponse implements JsonRpcResult {
        @JsonProperty(required = true)
        public List<PropertyDescriptor> result;

        private GetPropertiesResponse() {
        }
    }

    /* access modifiers changed from: private */
    public static class EvaluateRequest implements JsonRpcResult {
        @JsonProperty(required = true)
        public String expression;
        @JsonProperty(required = true)
        public String objectGroup;

        private EvaluateRequest() {
        }
    }

    /* access modifiers changed from: private */
    public static class EvaluateResponse implements JsonRpcResult {
        @JsonProperty
        public ExceptionDetails exceptionDetails;
        @JsonProperty(required = true)
        public RemoteObject result;
        @JsonProperty(required = true)
        public boolean wasThrown;

        private EvaluateResponse() {
        }
    }

    /* access modifiers changed from: private */
    public static class ExceptionDetails {
        @JsonProperty(required = true)
        public String text;

        private ExceptionDetails() {
        }
    }

    /* access modifiers changed from: private */
    public static class PropertyDescriptor {
        @JsonProperty(required = true)
        public final boolean configurable;
        @JsonProperty(required = true)
        public final boolean enumerable;
        @JsonProperty(required = true)
        public final boolean isOwn;
        @JsonProperty(required = true)
        public String name;
        @JsonProperty(required = true)
        public RemoteObject value;
        @JsonProperty(required = true)
        public final boolean writable;

        private PropertyDescriptor() {
            this.isOwn = true;
            this.configurable = false;
            this.enumerable = true;
            this.writable = false;
        }
    }

    public enum ObjectType {
        OBJECT("object"),
        FUNCTION("function"),
        UNDEFINED("undefined"),
        STRING("string"),
        NUMBER("number"),
        BOOLEAN("boolean"),
        SYMBOL("symbol");
        
        private final String mProtocolValue;

        private ObjectType(String str) {
            this.mProtocolValue = str;
        }

        @JsonValue
        public String getProtocolValue() {
            return this.mProtocolValue;
        }
    }

    public enum ObjectSubType {
        ARRAY("array"),
        NULL(LoggingUtil.NO_HASHCODE),
        NODE("node"),
        REGEXP("regexp"),
        DATE("date"),
        MAP("map"),
        SET("set"),
        ITERATOR("iterator"),
        GENERATOR(CodeGeneratorMetadataAnnotation.GENERATOR),
        ERROR("error");
        
        private final String mProtocolValue;

        private ObjectSubType(String str) {
            this.mProtocolValue = str;
        }

        @JsonValue
        public String getProtocolValue() {
            return this.mProtocolValue;
        }
    }
}
