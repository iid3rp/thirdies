package thirds.io;

import thirds.place.Place;
import thirds.user.User;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public final class ThirdsObjectReader
{
    public static final String appdata = System.getenv("APPDATA");
    public static final String thirdsDir = appdata + File.separator + "thirds";

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

    public User readUser(InputStream in)
    {
        try
        {
            return (User) new ObjectInputStream(in).readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Place readPlace(InputStream in)
    {
        try
        {
            return (Place) new ObjectInputStream(in).readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
