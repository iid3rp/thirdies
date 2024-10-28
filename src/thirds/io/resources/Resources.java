package thirds.io.resources;

import java.io.InputStream;

public final class Resources
{
    public static InputStream getResourceAsStream(String name)
    {
        return Resources.class.getClassLoader().getResourceAsStream(name);
    }
}
