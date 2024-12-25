package thirds.io;

import thirds.place.Place;
import thirds.user.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public final class ThirdsObjectReader
{
    public static final String appdata = System.getenv("APPDATA");
    public static final String thirdsDir = appdata + File.separator + "thirds";
    public static final String usersDir = thirdsDir + File.separator + "users";
    public static final String placesDir = thirdsDir + File.separator + "places";
    public static final String utilsDir = thirdsDir + File.separator + "utils";

    static {
        createDirectory(thirdsDir);
        createDirectory(thirdsDir + File.separator + "users");
        createDirectory(thirdsDir + File.separator + "places");
        createDirectory(thirdsDir + File.separator + "util");
    }

    private static void createDirectory(String path)
    {
        File dir = new File(path);
        if (!dir.exists())
            if (!dir.mkdirs())
                System.err.println("Failed to create directory: " + dir.getAbsolutePath());
            else System.out.println("Successfully created directory: " + dir.getAbsolutePath());
        else System.out.println("Directory already exists: " + dir.getAbsolutePath());
    }

    // --- Serialization Methods ---
    public static void serializeUser(User user) {
        File userFile = new File(usersDir, user.getUsername() + ".ser");
        try (FileOutputStream fos = new FileOutputStream(userFile);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(user);
        } catch (IOException e) {
            throw new RuntimeException("Failed to serialize user: " + user.getUsername(), e);
        }
    }

    public static void serializePlace(Place place)
    {
        File placeFile = new File(placesDir, place.getName() + ".ser");
        try (FileOutputStream fos = new FileOutputStream(placeFile);
             ObjectOutputStream oos = new ObjectOutputStream(fos))
        {
            oos.writeObject(place);
        } catch (IOException e) {
            throw new RuntimeException("Failed to serialize place: " + place.getName(), e);
        }
    }

    public static void serializeAllUsers(List<User> users)
    {
        for (User user : users)
            serializeUser(user);
    }

    public static void serializeAllPlaces(List<Place> places) {
        for (Place place : places)
            serializePlace(place);
    }

    public static List<Place> readAllPlaces()
    {
        List<Place> places = new ArrayList<>();
        File placeDir = new File(placesDir);
        File[] files = placeDir.listFiles();

        if (files != null)
            for (File file : files)
                if (file.isFile())
                    try (FileInputStream fis = new FileInputStream(file);
                         ObjectInputStream ois = new ObjectInputStream(fis))
                    {
                        Place place = (Place) ois.readObject();
                        places.add(place);
                    } catch (IOException | ClassNotFoundException e) {
                        throw new RuntimeException("Failed to read place from file: " + file.getName());
                    }

        return places;
    }

    public static List<User> readAllUsers()
    {
        List<User> users = new ArrayList<>();
        File userDir = new File(usersDir);
        File[] files = userDir.listFiles();

        if (files != null)
            for (File file : files)
                if (file.isFile())
                    try (FileInputStream fis = new FileInputStream(file);
                         ObjectInputStream ois = new ObjectInputStream(fis))
                    {
                        User user = (User) ois.readObject();
                        users.add(user);
                    } catch (IOException | ClassNotFoundException e) {
                        throw new RuntimeException("Failed to read user from file: " + file.getName());
                    }
        return users;
    }



    public static User readUser(InputStream in)
    {
        try
        {
            return (User) new ObjectInputStream(in).readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Place readPlace(InputStream in)
    {
        try
        {
            return (Place) new ObjectInputStream(in).readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
