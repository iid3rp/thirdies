package thirds.user;

import thirds.lang.Vec2;

public class Client
{
    private static User user;
    private static Vec2 location;

    public static void setUser(User user)
    {
        Client.user = user;
    }

    public static void setLocation(Vec2 location)
    {
        Client.location = location;
    }

    public static Vec2 currentLocation()
    {
        return location;
    }
}
