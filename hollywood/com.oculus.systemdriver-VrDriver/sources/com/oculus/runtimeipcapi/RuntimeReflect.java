package com.oculus.runtimeipcapi;

import android.util.Log;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class RuntimeReflect {
    private static final String TAG = "RuntimeReflectJava";
    static Map<Class<?>, TypeInfo> typeInfoMap = new HashMap();

    public interface ReflectBase {
    }

    public static int hashString(String str) {
        int hash = 5381;
        for (int i = 0; i < str.length(); i++) {
            hash = (hash << 5) + hash + str.charAt(i);
        }
        return hash;
    }

    public static class TypeInfo {
        Class arrayType = null;
        Class clazz = null;
        int hash = 0;

        /* access modifiers changed from: package-private */
        public Writer serializeToBytes(Object obj, Writer inWriter) {
            Writer writer = inWriter != null ? inWriter : new Writer();
            Class c = obj.getClass();
            try {
                if (RuntimeReflect.isInt(c)) {
                    writer.writeInt(((Integer) obj).intValue());
                } else if (RuntimeReflect.isFloat(c)) {
                    writer.writeFloat(((Float) obj).floatValue());
                } else if (RuntimeReflect.isDouble(c)) {
                    writer.writeDouble(((Double) obj).doubleValue());
                } else if (RuntimeReflect.isBoolean(c)) {
                    writer.writeBoolean(((Boolean) obj).booleanValue());
                } else if (RuntimeReflect.isString(c)) {
                    byte[] bytes = ((String) obj).getBytes();
                    writer.writeInt(bytes.length);
                    writer.write(bytes);
                } else if (RuntimeReflect.isArray(c)) {
                    int arrayLength = Array.getLength(obj);
                    writer.writeInt(arrayLength);
                    TypeInfo arrayTypeInfo = RuntimeReflect.getTypeInfo(this.arrayType);
                    for (int i = 0; i < arrayLength; i++) {
                        arrayTypeInfo.serializeToBytes(Array.get(obj, i), writer);
                    }
                }
                writer.flush();
                Log.d(RuntimeReflect.TAG, "TypeInfo::serializeToBytes: " + obj);
            } catch (Exception ex) {
                Log.d(RuntimeReflect.TAG, "TypeInfo::serializeToBytes ex: " + ex);
            }
            return writer;
        }

        /* access modifiers changed from: package-private */
        public Object serializeFromBytes(Class<?> c, Reader reader) {
            try {
                if (RuntimeReflect.isInt(c)) {
                    return Integer.valueOf(reader.readInt());
                }
                if (RuntimeReflect.isBoolean(c)) {
                    return Boolean.valueOf(reader.readBoolean());
                }
                if (RuntimeReflect.isFloat(c)) {
                    return Float.valueOf(reader.readFloat());
                }
                if (RuntimeReflect.isDouble(c)) {
                    return Double.valueOf(reader.readDouble());
                }
                if (RuntimeReflect.isString(c)) {
                    int strLength = reader.readInt();
                    byte[] bytes = new byte[strLength];
                    if (reader.read(bytes) != strLength) {
                        return false;
                    }
                    return new String(bytes);
                } else if (!RuntimeReflect.isArray(c)) {
                    return null;
                } else {
                    TypeInfo arrayTypeInfo = RuntimeReflect.getTypeInfo(this.arrayType);
                    int arrayLength = reader.readInt();
                    Object obj = Array.newInstance(this.arrayType, arrayLength);
                    for (int i = 0; i < arrayLength; i++) {
                        Object arrayElement = arrayTypeInfo.serializeFromBytes(this.arrayType, reader);
                        if (arrayElement == null) {
                            return null;
                        }
                        Array.set(obj, i, arrayElement);
                    }
                    return obj;
                }
            } catch (Exception ex) {
                Log.d(RuntimeReflect.TAG, "TypeInfo::serializeFromBytes ex: " + ex);
                return null;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static class Member {
        Field field;
        int hash = 0;
        TypeInfo typeInfo;

        Member() {
        }
    }

    public static class TypeInfo_Struct extends TypeInfo {
        Map<Integer, Member> memberMap = new HashMap();
        Member[] members;

        /* access modifiers changed from: package-private */
        @Override // com.oculus.runtimeipcapi.RuntimeReflect.TypeInfo
        public Writer serializeToBytes(Object obj, Writer inWriter) {
            Writer writer = inWriter != null ? inWriter : new Writer();
            int i = 0;
            while (true) {
                Member[] memberArr = this.members;
                if (i >= memberArr.length) {
                    break;
                }
                if (memberArr[i] != null) {
                    try {
                        Object memberObj = memberArr[i].field.get(obj);
                        if (memberObj != null) {
                            byte[] bytes = this.members[i].typeInfo.serializeToBytes(memberObj, null).toByteArray();
                            writer.writeInt(bytes.length);
                            writer.writeInt(this.members[i].hash);
                            writer.write(bytes);
                        }
                    } catch (Exception ex) {
                        Log.d(RuntimeReflect.TAG, "TypeInfo_Struct::serializeToBytes )ex: " + ex);
                    }
                }
                i++;
            }
            try {
                writer.writeInt(0);
                writer.flush();
            } catch (Exception ex2) {
                Log.d(RuntimeReflect.TAG, "TypeInfo_Struct::serializeToBytes ex: " + ex2);
            }
            return writer;
        }

        /* access modifiers changed from: package-private */
        public Object newInstanceFromClass(Class<?> c) {
            try {
                return c.newInstance();
            } catch (Exception e) {
                return null;
            }
        }

        /* access modifiers changed from: package-private */
        @Override // com.oculus.runtimeipcapi.RuntimeReflect.TypeInfo
        public Object serializeFromBytes(Class c, Reader reader) {
            Object obj = newInstanceFromClass(c);
            if (obj == null) {
                return null;
            }
            while (true) {
                try {
                    int size = reader.readInt();
                    if (size == 0) {
                        return obj;
                    }
                    int hash = reader.readInt();
                    byte[] bytes = new byte[size];
                    if (reader.read(bytes) != size) {
                        Log.d(RuntimeReflect.TAG, "TypeInfo_Struct::serializeFromBytes: reader.read(bytes) != size: " + size);
                        return false;
                    }
                    Member member = this.memberMap.get(Integer.valueOf(hash));
                    if (member != null) {
                        member.field.setAccessible(true);
                        Object memberObj = member.typeInfo.serializeFromBytes(member.typeInfo.clazz, new Reader(bytes));
                        if (memberObj == null) {
                            return null;
                        }
                        member.field.set(obj, memberObj);
                    }
                } catch (Exception ex) {
                    Log.d(RuntimeReflect.TAG, "TypeInfo_Struct::serializeFromBytes ex: " + ex);
                    return null;
                }
            }
        }
    }

    public static boolean isBoolean(Class<?> c) {
        return c == Boolean.TYPE || c == Boolean.class;
    }

    public static boolean isInt(Class<?> c) {
        return c == Integer.TYPE || c == Integer.class;
    }

    public static boolean isFloat(Class<?> c) {
        return c == Float.TYPE || c == Float.class;
    }

    public static boolean isDouble(Class<?> c) {
        return c == Double.TYPE || c == Double.class;
    }

    public static boolean isString(Class<?> c) {
        return c == String.class;
    }

    public static boolean isPrimitive(Class<?> c) {
        return isInt(c) || isBoolean(c) || isFloat(c) || isDouble(c) || isString(c);
    }

    public static boolean isArray(Class<?> c) {
        return c.isArray();
    }

    public static boolean isIntArray(Class<?> c) {
        return c == int[].class;
    }

    public static boolean isReflected(Class<?> c) {
        Class[] interfaces;
        for (Class cls : c.getInterfaces()) {
            if (cls == ReflectBase.class) {
                return true;
            }
        }
        return false;
    }

    public static String getHashName(Class<?> c) {
        if (isInt(c)) {
            return "int32_t";
        }
        if (isBoolean(c)) {
            return "bool";
        }
        if (isFloat(c)) {
            return "float";
        }
        if (isDouble(c)) {
            return "double";
        }
        if (isString(c)) {
            return "std::string";
        }
        if (!isArray(c)) {
            return c.getSimpleName();
        }
        return "std::vector<" + getHashName(c.getComponentType()) + ">";
    }

    public static TypeInfo createTypeInfo(Class<?> c) {
        Log.d(TAG, "TypeInfo: createTypeInfo: " + c.getSimpleName());
        if (isPrimitive(c)) {
            TypeInfo typeInfo = new TypeInfo();
            typeInfo.clazz = c;
            typeInfo.hash = hashString(getHashName(c));
            Log.d(TAG, "createTypeInfo: TypeInfo: primitive type: " + c.getSimpleName());
            return typeInfo;
        } else if (isArray(c)) {
            TypeInfo typeInfo2 = new TypeInfo();
            typeInfo2.clazz = c;
            typeInfo2.arrayType = c.getComponentType();
            typeInfo2.hash = hashString(getHashName(c));
            Log.d(TAG, "createTypeInfo: TypeInfo: array type: " + c.getSimpleName());
            return typeInfo2;
        } else if (!isReflected(c)) {
            Log.d(TAG, "createTypeInfo: Does not implement ReflectBase: " + c.getSimpleName());
            return null;
        } else {
            Field[] fields = c.getDeclaredFields();
            if (fields.length == 0) {
                return null;
            }
            TypeInfo_Struct typeInfo_Struct = new TypeInfo_Struct();
            typeInfo_Struct.members = new Member[fields.length];
            typeInfo_Struct.clazz = c;
            typeInfo_Struct.hash = hashString(c.getSimpleName());
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                try {
                    field.setAccessible(true);
                    Log.d(TAG, "TypeInfo: createTypeInfo Type: " + field.getType().getSimpleName() + " Field: " + field.getName());
                    typeInfo_Struct.members[i] = new Member();
                    typeInfo_Struct.members[i].typeInfo = getTypeInfo(field.getType());
                    if (typeInfo_Struct.members[i].typeInfo == null) {
                        typeInfo_Struct.members[i] = null;
                        Log.d(TAG, "createTypeInfo: TypeInfo not found: " + field.getName());
                    } else {
                        typeInfo_Struct.members[i].field = field;
                        Member member = typeInfo_Struct.members[i];
                        member.hash = hashString(getHashName(field.getType()) + field.getName());
                        typeInfo_Struct.memberMap.put(Integer.valueOf(typeInfo_Struct.members[i].hash), typeInfo_Struct.members[i]);
                    }
                } catch (Exception ex) {
                    typeInfo_Struct.members[i] = null;
                    Log.d(TAG, "createTypeInfo: ex " + ex);
                }
            }
            return typeInfo_Struct;
        }
    }

    public static TypeInfo getTypeInfo(Class c) {
        TypeInfo typeInfo = typeInfoMap.get(c);
        if (typeInfo != null) {
            return typeInfo;
        }
        TypeInfo typeInfo2 = createTypeInfo(c);
        if (typeInfo2 == null) {
            return null;
        }
        typeInfoMap.put(c, typeInfo2);
        return typeInfo2;
    }

    public static Writer serializeObjectToBytes(Object obj, Writer inWriter) {
        TypeInfo typeInfo = getTypeInfo(obj.getClass());
        if (typeInfo == null) {
            return null;
        }
        return typeInfo.serializeToBytes(obj, inWriter);
    }

    public static <T> T serializeObjectFromBytes(Class<T> c, Reader reader) {
        TypeInfo typeInfo = getTypeInfo(c);
        if (typeInfo == null) {
            return null;
        }
        return c.cast(typeInfo.serializeFromBytes(c, reader));
    }
}
