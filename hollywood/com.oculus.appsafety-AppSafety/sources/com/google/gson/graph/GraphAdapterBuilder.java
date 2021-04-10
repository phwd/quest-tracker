package com.google.gson.graph;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;
import com.google.gson.JsonElement;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public final class GraphAdapterBuilder {
    private final ConstructorConstructor constructorConstructor = new ConstructorConstructor(this.instanceCreators);
    private final Map<Type, InstanceCreator<?>> instanceCreators = new HashMap();

    public GraphAdapterBuilder addType(Type type) {
        final ObjectConstructor<?> objectConstructor = this.constructorConstructor.get(TypeToken.get(type));
        return addType(type, new InstanceCreator<Object>() {
            /* class com.google.gson.graph.GraphAdapterBuilder.AnonymousClass1 */

            @Override // com.google.gson.InstanceCreator
            public Object createInstance(Type type) {
                return objectConstructor.construct();
            }
        });
    }

    public GraphAdapterBuilder addType(Type type, InstanceCreator<?> instanceCreator) {
        if (type == null || instanceCreator == null) {
            throw new NullPointerException();
        }
        this.instanceCreators.put(type, instanceCreator);
        return this;
    }

    public void registerOn(GsonBuilder gsonBuilder) {
        Factory factory = new Factory(this.instanceCreators);
        gsonBuilder.registerTypeAdapterFactory(factory);
        for (Map.Entry<Type, InstanceCreator<?>> entry : this.instanceCreators.entrySet()) {
            gsonBuilder.registerTypeAdapter(entry.getKey(), factory);
        }
    }

    static class Factory implements TypeAdapterFactory, InstanceCreator {
        private final ThreadLocal<Graph> graphThreadLocal = new ThreadLocal<>();
        private final Map<Type, InstanceCreator<?>> instanceCreators;

        Factory(Map<Type, InstanceCreator<?>> instanceCreators2) {
            this.instanceCreators = instanceCreators2;
        }

        @Override // com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            if (!this.instanceCreators.containsKey(type.getType())) {
                return null;
            }
            final TypeAdapter<T> typeAdapter = gson.getDelegateAdapter(this, type);
            final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
            return new TypeAdapter<T>() {
                /* class com.google.gson.graph.GraphAdapterBuilder.Factory.AnonymousClass1 */

                @Override // com.google.gson.TypeAdapter
                public void write(JsonWriter out, T value) throws IOException {
                    if (value == null) {
                        out.nullValue();
                        return;
                    }
                    Graph graph = (Graph) Factory.this.graphThreadLocal.get();
                    boolean writeEntireGraph = false;
                    if (graph == null) {
                        writeEntireGraph = true;
                        graph = new Graph(new IdentityHashMap());
                    }
                    Element<T> element = (Element) graph.map.get(value);
                    if (element == null) {
                        element = new Element<>(value, graph.nextName(), typeAdapter, null);
                        graph.map.put(value, element);
                        graph.queue.add(element);
                    }
                    if (writeEntireGraph) {
                        Factory.this.graphThreadLocal.set(graph);
                        try {
                            out.beginObject();
                            while (true) {
                                Element<?> current = (Element) graph.queue.poll();
                                if (current != null) {
                                    out.name(((Element) current).id);
                                    current.write(out);
                                } else {
                                    out.endObject();
                                    return;
                                }
                            }
                        } finally {
                            Factory.this.graphThreadLocal.remove();
                        }
                    } else {
                        out.value(((Element) element).id);
                    }
                }

                @Override // com.google.gson.TypeAdapter
                public T read(JsonReader in) throws IOException {
                    if (in.peek() == JsonToken.NULL) {
                        in.nextNull();
                        return null;
                    }
                    String currentName = null;
                    Graph graph = (Graph) Factory.this.graphThreadLocal.get();
                    boolean readEntireGraph = false;
                    if (graph == null) {
                        graph = new Graph(new HashMap());
                        readEntireGraph = true;
                        in.beginObject();
                        while (in.hasNext()) {
                            String name = in.nextName();
                            if (currentName == null) {
                                currentName = name;
                            }
                            graph.map.put(name, new Element(null, name, typeAdapter, (JsonElement) elementAdapter.read(in)));
                        }
                        in.endObject();
                    } else {
                        currentName = in.nextString();
                    }
                    if (readEntireGraph) {
                        Factory.this.graphThreadLocal.set(graph);
                    }
                    try {
                        Element<T> element = (Element) graph.map.get(currentName);
                        if (((Element) element).value == null) {
                            ((Element) element).typeAdapter = typeAdapter;
                            element.read(graph);
                        }
                        return (T) ((Element) element).value;
                    } finally {
                        if (readEntireGraph) {
                            Factory.this.graphThreadLocal.remove();
                        }
                    }
                }
            };
        }

        @Override // com.google.gson.InstanceCreator
        public Object createInstance(Type type) {
            Graph graph = this.graphThreadLocal.get();
            if (graph == null || graph.nextCreate == null) {
                throw new IllegalStateException("Unexpected call to createInstance() for " + ((Object) type));
            }
            Object result = this.instanceCreators.get(type).createInstance(type);
            graph.nextCreate.value = result;
            graph.nextCreate = null;
            return result;
        }
    }

    /* access modifiers changed from: package-private */
    public static class Graph {
        private final Map<Object, Element<?>> map;
        private Element nextCreate;
        private final Queue<Element> queue;

        private Graph(Map<Object, Element<?>> map2) {
            this.queue = new LinkedList();
            this.map = map2;
        }

        public String nextName() {
            return "0x" + Integer.toHexString(this.map.size() + 1);
        }
    }

    /* access modifiers changed from: package-private */
    public static class Element<T> {
        private final JsonElement element;
        private final String id;
        private TypeAdapter<T> typeAdapter;
        private T value;

        Element(T value2, String id2, TypeAdapter<T> typeAdapter2, JsonElement element2) {
            this.value = value2;
            this.id = id2;
            this.typeAdapter = typeAdapter2;
            this.element = element2;
        }

        /* access modifiers changed from: package-private */
        public void write(JsonWriter out) throws IOException {
            this.typeAdapter.write(out, this.value);
        }

        /* access modifiers changed from: package-private */
        public void read(Graph graph) throws IOException {
            if (graph.nextCreate == null) {
                graph.nextCreate = this;
                this.value = this.typeAdapter.fromJsonTree(this.element);
                if (this.value == null) {
                    throw new IllegalStateException("non-null value deserialized to null: " + ((Object) this.element));
                }
                return;
            }
            throw new IllegalStateException("Unexpected recursive call to read() for " + this.id);
        }
    }
}
