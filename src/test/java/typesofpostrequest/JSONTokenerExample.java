package typesofpostrequest;


import org.json.JSONTokener;

import net.minidev.json.JSONObject;

public class JSONTokenerExample {
    public static void main(String[] args) {
        // JSON data as a string
        String jsonData = "{\"name\":\"Alice\", \"age\":25, \"isStudent\":false}";

        // Create a JSONTokener instance with the JSON data
        JSONTokener tokener = new JSONTokener(jsonData);

        // Parse the JSON data into a JSONObject
        JSONObject jsonObject = new JSONObject(tokener);

        // Accessing data in the JSONObject
        String name = jsonObject.getString("name");        // Output: Alice
        int age = jsonObject.getInt("age");                // Output: 25
        boolean isStudent = jsonObject.getBoolean("isStudent"); // Output: false

        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Is Student: " + isStudent);
    }
}
