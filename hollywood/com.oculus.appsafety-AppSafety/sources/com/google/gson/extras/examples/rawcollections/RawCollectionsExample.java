package com.google.gson.extras.examples.rawcollections;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;

public class RawCollectionsExample {

    static class Event {
        private String name;
        private String source;

        private Event(String name2, String source2) {
            this.name = name2;
            this.source = source2;
        }

        public String toString() {
            return String.format("(name=%s, source=%s)", this.name, this.source);
        }
    }

    public static void main(String[] args) {
        Gson gson = new Gson();
        Collection collection = new ArrayList();
        collection.add("hello");
        collection.add(5);
        collection.add(new Event("GREETINGS", "guest"));
        String json = gson.toJson(collection);
        PrintStream printStream = System.out;
        printStream.println("Using Gson.toJson() on a raw collection: " + json);
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        int number = ((Integer) gson.fromJson(array.get(1), (Class) Integer.TYPE)).intValue();
        System.out.printf("Using Gson.fromJson() to get: %s, %d, %s", (String) gson.fromJson(array.get(0), String.class), Integer.valueOf(number), (Event) gson.fromJson(array.get(2), Event.class));
    }
}
