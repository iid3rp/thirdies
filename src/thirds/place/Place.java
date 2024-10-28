package thirds.place;

import thirds.lang.Vec2;
import thirds.user.Rating;
import thirds.util.LinkList;

import java.awt.image.BufferedImage;

public abstract class Place
{
    // statics; the information about
    // the places and stuff are put here
    public static final int
        COFFEE_SHOP         = 0x01,
        PARK                = 0x02,
        HOTEL               = 0x03,
        INTERNET_CAFE       = 0x04,
        CONVENIENCE_STORE   = 0x05,
        LIBRARY             = 0x06,
        BAR                 = 0x07,
        COMMUNITY           = 0x08,
        SOCIAL              = 0x09,
        STUDY               = 0x0A,
        GYM                 = 0x0B,
        IT_SPACE            = 0x0C,
        EATERY              = 0x0D,
        MALL                = 0x0E,
        SHOPPING            = 0x0F,
        MULTIPURPOSE        = 0x10,
        DEFAULT_PLACE       = 0x00;

    // the basics
    private int type;
    private String name;
    private String address;
    private String desc;

    // attributes
    private double rating;
    private Vec2 priceRange;
    private int restroomCount;
    private boolean isAirConditioned;
    private boolean wifiAvailable;
    private int noiseLevel;
    private int lightLevel;
    private boolean isIndoor;
    private boolean isOutdoor;
    private boolean ageRange;
    private int capacity;

    // other miscellaneous things
    private LinkList<Rating> ratings;
    LinkList<String> tags;
    LinkList<BufferedImage> images;

    // the UI map things
    private Vec2 location;

    public Place(int id)
    {
        this.type = id;
        name = address = desc = "";
        tags = new LinkList<>();
        ratings = new LinkList<>();
        images = new LinkList<>();
        location = new Vec2();
    }

    public int getType()
    {
        return type;
    }

    public void setType(int type)
    {
        this.type = type;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getDesc()
    {
        return desc;
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    public LinkList<String> getTags()
    {
        return tags;
    }

    public void setTags(LinkList<String> tags)
    {
        this.tags = tags;
    }

    public void addTags(String tag)
    {
        String[] tags = tag.split(" ");
        for(String s : tags)
            this.tags.add(s);
    }

    public Vec2 getPriceRange()
    {
        return priceRange;
    }

    public void setPriceRange(Vec2 priceRange)
    {
        this.priceRange = priceRange;
    }

    public int getRestroomCount()
    {
        return restroomCount;
    }

    public void setRestroomCount(int restroomCount)
    {
        this.restroomCount = restroomCount;
    }

    public boolean isAirConditioned()
    {
        return isAirConditioned;
    }

    public void setAirConditioned(boolean airConditioned)
    {
        isAirConditioned = airConditioned;
    }

    public boolean isWifiAvailable()
    {
        return wifiAvailable;
    }

    public void setWifiAvailable(boolean wifiAvailable)
    {
        this.wifiAvailable = wifiAvailable;
    }

    public int getNoiseLevel()
    {
        return noiseLevel;
    }

    public void setNoiseLevel(int noiseLevel)
    {
        this.noiseLevel = noiseLevel;
    }

    public int getLightLevel()
    {
        return lightLevel;
    }

    public void setLightLevel(int lightLevel)
    {
        this.lightLevel = lightLevel;
    }

    public boolean isIndoor()
    {
        return isIndoor;
    }

    public void setIndoor(boolean indoor)
    {
        isIndoor = indoor;
    }

    public boolean isOutdoor()
    {
        return isOutdoor;
    }

    public void setOutdoor(boolean outdoor)
    {
        isOutdoor = outdoor;
    }

    public boolean isAgeRange()
    {
        return ageRange;
    }

    public void setAgeRange(boolean ageRange)
    {
        this.ageRange = ageRange;
    }

    public int getCapacity()
    {
        return capacity;
    }

    public void setCapacity(int capacity)
    {
        this.capacity = capacity;
    }

    public LinkList<Rating> getRatings()
    {
        return ratings;
    }

    public void setRatings(LinkList<Rating> ratings)
    {
        this.ratings = ratings;
    }

    public void addImage(BufferedImage image)
    {
        images.add(image);
    }

    public void addRating(Rating rating)
    {
        ratings.add(rating);
    }

    public void addTag(String tag)
    {
        tags.add(tag);
    }
}
