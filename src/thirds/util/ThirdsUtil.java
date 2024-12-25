package thirds.util;

import thirds.io.ThirdsObjectReader;
import thirds.place.Place;
import thirds.user.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThirdsUtil {
    private static List<Place> placeList = new ArrayList<>();
    private static Map<String, User> userMap = new HashMap<>();

    // Prevent instantiation of this utility class
    private ThirdsUtil() {}

    /**
     * Loads all places from the serialized files and populates the placeList.
     */
    public static void loadAllPlaces() {
        placeList = ThirdsObjectReader.readAllPlaces();
    }

    public static boolean userExists(String username) {
        return userMap.containsKey(username);
    }

    public static boolean placeExists(String placeName) {
        for (Place place : placeList) {
            if (place.getName().equals(placeName)) {
                return true;
            }
        }
        return false;
    }

    public static User getUser(String username) {
        return userMap.get(username); // Returns null if the user doesn't exist
    }

    public static Place getPlace(String placeName) {
        for (Place place : placeList) {
            if (place.getName().equals(placeName)) {
                return place;
            }
        }
        return null; // Returns null if the place doesn't exist
    }

    public static void removeUser(String username) {
        userMap.remove(username);
    }

    public static void removePlace(String placeName) {
        placeList.removeIf(place -> place.getName().equals(placeName));
    }

    /**
     * Loads all users from the serialized files and populates the userMap.
     */
    public static void loadAllUsers() {
        List<User> users = ThirdsObjectReader.readAllUsers();
        userMap.clear(); // Clear the map before adding
        for (User user : users) {
            userMap.put(user.getUsername(), user);
        }
    }

    /**
     * Gets the list of all places.
     *
     * @return The list of places.
     */
    public static List<Place> getPlaceList() {
        return new ArrayList<>(placeList); // Return a copy to prevent external modification
    }

    /**
     * Gets the map of all users (username -> User).
     *
     * @return The map of users.
     */
    public static Map<String, User> getUserMap() {
        return new HashMap<>(userMap); // Return a copy to prevent external modification
    }

    /**
     * Adds a new place to the placeList.
     * @param place the place to be added
     */
    public static void addPlace(Place place)
    {
        placeList.add(place);
    }

    /**
     * Adds a new user to the user map.
     * @param user the user to be added
     */
    public static void addUser(User user)
    {
        userMap.put(user.getUsername(), user);
    }

    /**
     * Clears the place list
     */
    public static void clearPlaces()
    {
        placeList.clear();
    }

    /**
     * Clears the user map
     */
    public static void clearUsers()
    {
        userMap.clear();
    }
}