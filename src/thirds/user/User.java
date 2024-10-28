package thirds.user;

import thirds.place.Place;
import thirds.util.LinkList;

import java.awt.image.BufferedImage;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

public class User implements Serializable
{
    @Serial
    private static final long serialVersionUID = 7369882029739916352L;

    // the basics
    private String firstName;
    private String middleName;
    private String lastName;
    private String username;
    private String handleName;
    private Date dateOfBirth;

    // account info
    private transient String password;
    private String hashedPassword;

    // images
    private BufferedImage profilePicture;
    private BufferedImage profileBanner;

    // social
    private LinkList<Place> favePlaces;
    private LinkList<Place> madePlaces;


    public User() {}

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getMiddleName()
    {
        return middleName;
    }

    public void setMiddleName(String middleName)
    {
        this.middleName = middleName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

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

    public String getHashedPassword()
    {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword)
    {
        this.hashedPassword = hashedPassword;
    }

    public BufferedImage getProfilePicture()
    {
        return profilePicture;
    }

    public void setProfilePicture(BufferedImage profilePicture)
    {
        this.profilePicture = profilePicture;
    }

    public BufferedImage getProfileBanner()
    {
        return profileBanner;
    }

    public void setProfileBanner(BufferedImage profileBanner)
    {
        this.profileBanner = profileBanner;
    }

    public LinkList<Place> getFavePlaces()
    {
        return favePlaces;
    }

    public void setFavePlaces(LinkList<Place> favePlaces)
    {
        this.favePlaces = favePlaces;
    }

    public LinkList<Place> getMadePlaces()
    {
        return madePlaces;
    }

    public void setMadePlaces(LinkList<Place> madePlaces)
    {
        this.madePlaces = madePlaces;
    }

    public void addFavePlace(Place place)
    {
        favePlaces.add(place);
    }

    public void addMadePlace(Place place)
    {
        madePlaces.add(place);
    }
}
