import java.util.*;
/*  Author: Tri Dao
    Date:   18/05/2022
    Purpose:    Country Class for PDI assignment */
public class Country
{
    private String iso3;
    private String continent;
    private String countryName;
    private String nuts;
    private double lat;
    private double longitudeCoordinate;

    public String getIso3()
    {
        return iso3;
    }

    public String getContinent()
    {
        return continent;
    }

    public String getCountryName()
    {
        return countryName;
    }

    public String getNuts()
    {
        return nuts;
    }

    public double getLat()
    {
        return lat;
    }

    public double getLongitudeCoordinate()
    {
        return longitudeCoordinate;
    }

    public void setIso3(String pIso3)
    {
        iso3 = pIso3;
    }

    public void setContinent(String pContinent)
    {
        continent = pContinent;
    }

    public void setCountryName(String pCountryName)
    {
        countryName = pCountryName;
    }

    public void setNuts(String pNuts)
    {
        nuts = pNuts;
    }

    public void setLat(double pLat)
    {
        lat = pLat;
    }

    public void setLongitudeCoordinate(double pLongitudeCoordinate)
    {
        longitudeCoordinate = pLongitudeCoordinate;
    }

    public boolean equals(Object inObject)
    {
        boolean isEqual = false;
        Country inCountry = null;
        if(inObject instanceof Country)
        {
            inCountry = (Country)inObject;
            if(iso3.equals(inCountry.getIso3()))
                if(continent.equals(inCountry.getContinent()))
                    if(countryName.equals(inCountry.getCountryName()))
                        if(nuts.equals(inCountry.getNuts()))
                            if(lat == inCountry.getLat())
                                if(longitudeCoordinate == inCountry.getLongitudeCoordinate())
                                    isEqual = true;
        }
        return isEqual;
    }

    public String toString()
    {
        String countryString;
        countryString =  "Country: " + countryName + "\nContinent: " + continent + "\nNUTS: " + nuts + "\niso3: " + iso3 + "\nLongitude Coordinate: " + longitudeCoordinate + "\nLatitude Coordinate: " + lat;
        return countryString;
    }

    public Country()
    {
        iso3 = "AUS";
        continent = "OC";
        countryName = "Australia";
        nuts = "AU";
        lat = -26.853388;
        longitudeCoordinate = -69.965112;
    }

    public Country(Country pCountry)
    {
        iso3 = pCountry.getIso3();
        continent = pCountry.getContinent();
        countryName = pCountry.getCountryName();
        nuts = pCountry.getNuts();
        lat = pCountry.getLat();
        longitudeCoordinate = pCountry.getLongitudeCoordinate();
    }

    public Country(String pIso3, String pContinent, String pCountryName, String pNuts, double pLat, double pLongitudeCoordinate)
    {
        iso3 = pIso3;
        continent = pContinent;
        countryName = pCountryName;
        nuts = pNuts;
        lat = pLat;
        longitudeCoordinate = pLongitudeCoordinate;
    }
}
