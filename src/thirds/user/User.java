package thirds.user;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

public class User implements Serializable
{
    @Serial
    private static final long serialVersionUID = 7369882029739916352L;

    // the basics
    private String username;
    private String handleName;
    private Date dateOfBirth;
    private String password;


    public User() {}

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getHandleName()
    {
        return handleName;
    }

    public void setHandleName(String handleName)
    {
        this.handleName = handleName;
    }

    public Date getDateOfBirth()
    {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth)
    {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
