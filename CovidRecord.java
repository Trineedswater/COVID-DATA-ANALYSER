import java.util.*;
/*  Author: Tri Dao
    Date:   18/05/2022
    Purpose:    Covid Record Class for PDI */
public class CovidRecord
{
    private String date;
    private int cumulativePositive;
    private int cumulativeDeceased;
    private int cumulativeRecovered;
    private int currentlyPositive;
    private int hospitalized;
    private int intensiveCare;
    private Country country;

    public String getDate()
    {
        return date;
    }

    public int getCumulativePositive()
    {
        return cumulativePositive;
    }

    public int getCumulativeDeceased()
    {
        return cumulativeDeceased;
    }

    public int getCumulativeRecovered()
    {
        return cumulativeRecovered;
    }

    public int getCurrentlyPositive()
    {
        return currentlyPositive;
    }

    public int getHospitalized()
    {
        return hospitalized;
    }

    public int getIntensiveCare()
    {
        return intensiveCare;
    }

    public Country getCountry()
    {
        return country;
    }

    public void setDate(String pDate)
    {
        date = pDate;
    }

    public void setCumulativePositive(int pCumulativePositive)
    {
        cumulativePositive = pCumulativePositive;
    }

    public void setCumulativeDeceased(int pCumulativeDeceased)
    {
        cumulativeDeceased = pCumulativeDeceased;
    }

    public void setCumulativeRecovered(int pCumulativeRecovered)
    {
        cumulativeRecovered = pCumulativeRecovered;
    }

    public void setCurrentlyPositive(int pCurrentlyPositive)
    {
        currentlyPositive = pCurrentlyPositive;
    }

    public void setHospitalized(int pHospitalized)
    {
        hospitalized = pHospitalized;
    }

    public void setIntensiveCare(int pIntensiveCare)
    {
        intensiveCare = pIntensiveCare;
    }

    public void setCountry(Country pCountry)
    {
        country = pCountry;
    }

    public boolean equals(Object inObject)
    {
        boolean isEquals = false;
        CovidRecord inCovidRecord = null;
        if(inObject instanceof CovidRecord)
        {
            inCovidRecord = (CovidRecord)inObject;
            if(date.equals(inCovidRecord.getDate()))
                if(cumulativePositive == inCovidRecord.getCumulativePositive())
                    if(cumulativeDeceased == inCovidRecord.getCumulativeDeceased())
                        if(cumulativeRecovered == inCovidRecord.getCumulativeRecovered())
                            if(cumulativePositive == inCovidRecord.getCumulativePositive())
                                if(currentlyPositive == inCovidRecord.getCurrentlyPositive())
                                    if(hospitalized == inCovidRecord.getHospitalized())
                                        if(intensiveCare == inCovidRecord.getIntensiveCare())
                                            if(country.equals(inCovidRecord.getCountry()))
                                                isEquals = true;
        }
        return isEquals;
    }

    public String toString()
    {
        String covidRecordString;
        covidRecordString = "Date: " + date + "\nCumulative Positive: " + cumulativePositive + "\nCumulative Deceased: " + cumulativeDeceased + "\nCumulative Recovered: " + cumulativeRecovered + "\nCurrently Positive: " + currentlyPositive + "\nHospitalized: " + hospitalized + "\nIntensive Care: " + intensiveCare +"\n" + country.toString();
        return covidRecordString;
    }

    public CovidRecord(String pDate, int pCumulativePositive, int pCumulativeDeceased, int pCumulativeRecovered, int pCurrentlyPositive, int pHospitalized, int pIntensiveCare, Country pCountry)
    {
        date = pDate;
        cumulativePositive = pCumulativePositive;
        cumulativeDeceased = pCumulativeDeceased;
        cumulativeRecovered = pCumulativeRecovered;
        currentlyPositive = pCurrentlyPositive;
        hospitalized = pHospitalized;
        intensiveCare = pIntensiveCare;
        country = pCountry;
    }

    public CovidRecord(CovidRecord pCovidRecord)
    {
        date = pCovidRecord.getDate();
        cumulativePositive = pCovidRecord.getCumulativePositive();
        cumulativeDeceased = pCovidRecord.getCumulativeDeceased();
        cumulativeRecovered = pCovidRecord.getCumulativeRecovered();
        currentlyPositive = pCovidRecord.getCurrentlyPositive();
        hospitalized = pCovidRecord.getHospitalized();
        intensiveCare = pCovidRecord.getIntensiveCare();
        country = pCovidRecord.getCountry();
    }

    public CovidRecord()
    {
        date = "31/12/2021";
        cumulativePositive = 0;
        cumulativeDeceased = 0;
        cumulativeRecovered = 0;
        currentlyPositive = 0;
        hospitalized = 0;
        intensiveCare = 0;
        country = new Country();//important all these values are set to 0 so that a default CovidRecord object can be set in place when there is a missing record for that country for that date
    }
}
