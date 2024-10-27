package thirds.place;

public class Rating
{
    private int noiseLevel;
    private int lightLevel;
    private int humidity;
    private int qualityOfService;
    private int recommendedLevel;
    private int totalRating;
    public Rating() {}

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

    public int getHumidity()
    {
        return humidity;
    }

    public void setHumidity(int humidity)
    {
        this.humidity = humidity;
    }

    public int getQualityOfService()
    {
        return qualityOfService;
    }

    public void setQualityOfService(int qualityOfService)
    {
        this.qualityOfService = qualityOfService;
    }

    public int getRecommendedLevel()
    {
        return recommendedLevel;
    }

    public void setRecommendedLevel(int recommendedLevel)
    {
        this.recommendedLevel = recommendedLevel;
    }

    public int getTotalRating()
    {
        return totalRating;
    }

    public void setTotalRating(int totalRating)
    {
        this.totalRating = totalRating;
    }
}
