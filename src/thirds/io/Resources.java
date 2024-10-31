package thirds.io;

import java.io.InputStream;

public final class Resources
{
    public static InputStream getResourceAsStream(String name)
    {
        return Resources.class.getResourceAsStream("resources/" + name);
    }
}
