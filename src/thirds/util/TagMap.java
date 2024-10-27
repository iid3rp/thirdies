package thirds.util;

import thirds.place.Place;

public class TagMap extends Map<String, LinkList<Place>>
{
    public TagMap()
    {
        super();
    }

    public void addTag(Place place, LinkList<String> tags)
    {
        for(String tag : tags)
            if(get(tag) == null)
            {
                put(tag, new LinkList<>());
                get(tag).add(place);
            }
            else get(tag).add(place);
    }

    public LinkList<Place> getPlace(String tag)
    {
        return get(tag);
    }
}
