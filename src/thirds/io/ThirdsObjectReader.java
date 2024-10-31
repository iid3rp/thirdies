package thirds.io;

import thirds.user.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public final class ThirdsObjectReader
{
    public static final String appdata = System.getenv("APPDATA");
    public static final String thirdsDir = appdata + File.separator + "thirds";

    static
    {
        var dir = new File(thirdsDir);
        if(!dir.exists())
            dir.mkdirs();
        var users = new File(thirdsDir, "users");
        if(!users.exists())
            users.mkdirs();
        var places = new File(thirdsDir, "places");
        if(!places.exists())
            places.mkdirs();
        var util = new File(thirdsDir, "util");
        if(!util.exists())
            util.mkdirs();
    }

    public User readObject(String filePath) {
        try (InputStream fileStream = new FileInputStream(filePath);
            ObjectInputStream objectStream = new ObjectInputStream(fileStream))
        {
            // Cast the object to the generic type
            return (User) objectStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
