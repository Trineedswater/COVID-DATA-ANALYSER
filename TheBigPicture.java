import java.util.*;
import java.math.*;
import java.io.*;

/*  Author: Tri Dao
    Date:   19/05/2022
    Purpose:    Create statistics from an imported csv file about covid-19 in relation to their countries as per user's request */

public class TheBigPicture
{
    public static void main(String[] args)
    {
        int menuOneOption, menuTwoOption;
        long cumulativeData = 0;
        long tempData1, tempData2; //for menu 2, tempData is used if more than 1 variable is required from covid report
        double outputData = 0;
        Country [] countryArray = new Country[1777];
        CovidRecord [] covidRecordArray = new CovidRecord[1777];
        String continentChoice = "All";
        String countryChoice = "All";
        String dateChoice = "31/12/2021";
        String displayMessage = "the world";

        importCSVData(covidRecordArray, countryArray);

        menuOneOption = menuOne();
        while(menuOneOption != 0)
        {
            menuTwoOption = menuTwo();
            while(menuTwoOption != 0)
            {
                switch(menuOneOption)
                {
                    case 1:
                        continentChoice = "SA";
                        displayMessage = "South America";
                    break;
                    case 2:
                        continentChoice = "NA";
                        displayMessage = "North America";
                    break;
                    case 3:
                        continentChoice = "OC";
                        displayMessage = "Oceania";
                    break;
                    case 4:
                        continentChoice = "AS";
                        displayMessage = "Asia";
                    break;
                    case 5:
                        continentChoice = "AF";
                        displayMessage = "Africa";
                    break;
                    case 6:
                        continentChoice = "EU";
                        displayMessage = "Europe";
                    break;
                    case 7://specific country
                        countryChoice = userCountryInput(countryArray);
                        displayMessage = countryChoice;
                    break;
                    case 8://specific date
                        dateChoice = userDateInput(covidRecordArray);
                        displayMessage = dateChoice;
                    break;
                    case 9://all
                        displayMessage = "the world";
                    break;
                }

                //formula used is an if statement written with numbers; if menuOneOption < 7, multiply by menuOneOption by 10 and add menuTwoOption
                switch((Math.abs(menuOneOption - 6) + (menuOneOption - 6)) * 5 + menuTwoOption)
                {

                    case 1: case 2: case 3:
                        cumulativeData = accumulateContinentData(continentChoice, menuTwoOption, covidRecordArray);
                    break;
                    case 4://assuming reports are provided monthly and the current positive is the amount of currently positive cases for that day of the month (the last day of each month).
                        cumulativeData = accumulateContinentData(continentChoice, 1, covidRecordArray) / 365;//Assuming positive cases accumulate from the start of January 1st 2021 to the end of December 31st 2021
                    case 5:
                        tempData1 = accumulateContinentData(continentChoice, 3, covidRecordArray);
                        tempData2 = accumulateContinentData(continentChoice, 1, covidRecordArray);
                        outputData = (double) tempData1 / (double) tempData2;
                    break;
                    case 6:
                        tempData1 = accumulateContinentData(continentChoice, 2, covidRecordArray);
                        tempData2 = accumulateContinentData(continentChoice, 1, covidRecordArray);
                        outputData = (double) tempData1/ (double) tempData2;
                    break;
                    case 7:
                        for(int i = 1; i <= 3; i++)
                        {
                            cumulativeData = accumulateContinentData(continentChoice, i, covidRecordArray);
                        }
                        cumulativeData = accumulateContinentData(continentChoice, 1, covidRecordArray) / 365;
                        for(int i = 3; i >= 2; i--)
                        {
                            tempData1 = accumulateContinentData(continentChoice, i, covidRecordArray);
                            tempData2 = accumulateContinentData(continentChoice, 1, covidRecordArray);
                            outputData = (double) tempData1 / (double) tempData2;
                        }
                    break;
                    case 11: case 12: case  13:
                        cumulativeData = accumulateCountryData(countryChoice, menuTwoOption, covidRecordArray);
                    break;
                    case 14:
                        cumulativeData = accumulateCountryData(countryChoice, 1, covidRecordArray) / 365;
                    break;
                    case 15:
                        tempData1 = accumulateCountryData(countryChoice, 3, covidRecordArray);
                        tempData2 = accumulateCountryData(countryChoice, 1, covidRecordArray);
                        outputData = (double) tempData1 / (double) tempData2;
                    break;
                    case 16:
                        tempData1 = accumulateCountryData(countryChoice, 2, covidRecordArray);
                        tempData2 = accumulateCountryData(countryChoice, 1, covidRecordArray);
                        outputData = (double) tempData1 / (double) tempData2;
                    break;
                    case 17:

                        for(int i = 1; i <= 3; i++)
                        {
                            cumulativeData = accumulateCountryData(countryChoice, i, covidRecordArray);
                        }
                        cumulativeData = accumulateCountryData(countryChoice, 1, covidRecordArray) / 365;
                        for(int i = 3; i >= 2; i--)
                        {
                            tempData1 = accumulateCountryData(countryChoice, i, covidRecordArray);
                            tempData2 = accumulateCountryData(countryChoice, 1, covidRecordArray);
                            outputData = (double) tempData1 / (double) tempData2;
                        }
                    break;
                    case 21: case 22: case 23:
                        cumulativeData = accumulateDateData(dateChoice, menuTwoOption, covidRecordArray);
                    break;
                    case 24:
                        cumulativeData = accumulateDateData(dateChoice, 1, covidRecordArray) / 365;
                    break;
                    case 25:
                        tempData1 = accumulateDateData(dateChoice, 3, covidRecordArray);
                        tempData2 = accumulateDateData(dateChoice, 1, covidRecordArray);
                        outputData = (double) tempData1 / (double) tempData2;
                    break;
                    case 26:
                        tempData1 = accumulateDateData(dateChoice, 2, covidRecordArray);
                        tempData2 = accumulateDateData(dateChoice, 1, covidRecordArray);
                        outputData = (double) tempData1 / (double) tempData2;
                    break;
                    case 27:
                        for(int i = 1; i <= 3; i++)
                        {
                            cumulativeData = accumulateDateData(dateChoice, i, covidRecordArray);
                        }
                        cumulativeData = accumulateDateData(dateChoice, 1, covidRecordArray) / 365;
                        for(int i = 3; i >= 2; i--)
                        {
                            tempData1 = accumulateDateData(dateChoice, i, covidRecordArray);
                            tempData2 = accumulateDateData(dateChoice, 1, covidRecordArray);
                            outputData = (double) tempData1/ (double) tempData2;
                        }
                    break;
                    case 31: case 32: case 33:
                        cumulativeData = loopAccumulateContinentData(menuTwoOption, covidRecordArray);
                    break;
                    case 34:
                        cumulativeData = loopAccumulateContinentData(menuTwoOption, covidRecordArray) / 365;
                    break;
                    case 35:
                        tempData1 = loopAccumulateContinentData(3, covidRecordArray);
                        tempData2 = loopAccumulateContinentData(1, covidRecordArray);
                        outputData = (double) tempData1 / (double) tempData2;
                    case 36:
                        tempData1 = loopAccumulateContinentData(2, covidRecordArray);
                        tempData2 = loopAccumulateContinentData(1, covidRecordArray);
                        outputData = (double)tempData1 / (double) tempData2;
                    break;
                    case 37:
                        for(int i = 1; i <= 3; i++)
                        {
                            cumulativeData = loopAccumulateContinentData(i, covidRecordArray);
                        }
                        cumulativeData = loopAccumulateContinentData(1, covidRecordArray) / 365;
                        for(int i = 3; i >= 2; i--)
                        {
                            tempData1 = loopAccumulateContinentData(i, covidRecordArray);
                            tempData2 = loopAccumulateContinentData(1, covidRecordArray);
                            outputData = (double) tempData1 / (double) tempData2;
                        }
                    break;
                }
                System.out.println("In " + displayMessage + ": ");
                if(menuTwoOption == 7)
                {
                    for(int i = 1; i < 7; i++)
                    {
                        displayToUser(i, cumulativeData, outputData);
                    }
                }
                else
                {
                    displayToUser(menuTwoOption, cumulativeData, outputData);
                }
                //reassign all values back to 0 just in case
                tempData1 = 0;
                tempData2 = 0;
                outputData = 0;
                cumulativeData = 0;
                menuTwoOption = menuTwo();
            }
            menuOneOption = menuOne();
        }
    }

    public static int menuOne()
    {//collecting and validating user menu option
        int userChoice = 0;//automatically set to 0 so system exits indicating error within system to user
        Scanner sc = new Scanner(System.in);
        boolean validChoice = false;
        String displayMenu = "Welcome to the JRC COVID-19 Analysis Program. There are a total of 1777 records loaded. Please make a selection from the menu below to choose which continent or date you would like to analyse. Ensure you enter a whole number to make a valid selection.";
        String displayRestOfMenu = "\n1. Countries in South America\n2. Countries in North America\n3. Countries in Oceania\n4. Countries in Asia\n5. Countries in Africa\n6. Countries in Europe\n7. User specified Country\n8. User specified Date\n9. All countries by the end of 2021\n0. Exit";

        System.out.println(displayMenu + displayRestOfMenu);
        while(!validChoice)
        {//sanitising user option
            try
            {
                userChoice = sc.nextInt();
                validChoice = true;
            }
            catch(InputMismatchException error)
            {//user did not input an integer
                System.out.println("Error!! " + error + "\nYou likely did not enter a whole number. Please ensure you enter a whole number between 0 and 9.");
                validChoice = false;
                sc.nextLine();//This line is included so an infinite loop does not occur
            }
            if((userChoice < 0) || (userChoice > 9))
            {//user inputted in integer outside of domain
                System.out.println("Error! Please input a whole number between 0 and 9");
                validChoice = false;
                sc.nextLine();
            }
        }
        return userChoice;
    }

    public static int menuTwo()
    {//same as menuOne but with a different domain and display
        Scanner sc = new Scanner(System.in);
        int userChoice = 0;
        boolean validChoice = false;
        String displayMenu = "Please select which statistic you would like to analyse.";
        String displayRestOfMenu = "\n1. Cumulative number of positive cases\n2. Cumulative number of deceased cases\n3. Cumulative number of recovered cases\n4. Average number of currently positive cases per day\n5. Number and percentage of cumulative positive cases recovered \n6. Number and percentage of cumulatively positive cases deceased\n7. All of the above statistice\n0. Go back";

        System.out.println(displayMenu + displayRestOfMenu);
        while(!validChoice)
        {
            try
            {
                userChoice = sc.nextInt();
                validChoice = true;
            }
            catch(InputMismatchException error)
            {
                System.out.println("Error!! " + error);
                validChoice = false;
                sc.nextLine();
            }
            if((userChoice < 0) || (userChoice > 7))
            {
                System.out.println("Error! Please input a whole number between 0 and 7");
                validChoice = false;
                sc.nextLine();
            }
        }
        return userChoice;
    }

    public static void displayToUser(int pDataChoice, long pRawData, double pPercentageData)
    {
        switch(pDataChoice)
        {
            case 1:
                System.out.println(pRawData + " people tested positive for Covid-19");
            break;
            case 2:
                System.out.println(pRawData + " people died from Covid-19");
            break;
            case 3:
                System.out.println(pRawData + " people recovered from Covid-19");
            break;
            case 4:
                System.out.println(pRawData + " people tested positive for Covid-19 per day");
            break;
            case 5:
                System.out.println(pPercentageData + " percent of people died from Covid-19 out of " + pRawData);
            break;
            case 6:
                System.out.println(pPercentageData + " percent of people recovered from Covid-19 out of " + pRawData);
            break;
       }
    }

    public static CovidRecord[] importCSVData(CovidRecord[] pCovidRecordArray, Country[] pCountryArray)
    {
        FileInputStream inputStream = null;
        InputStreamReader streamReader;
        BufferedReader buffReader;
        int lineNum;
        String line;

        try
        {
            /*open file */
            inputStream = new FileInputStream("jrc-covid-19-all-days-of-world.csv");
            streamReader = new InputStreamReader(inputStream);
            buffReader = new BufferedReader(streamReader);
            String[] splitLine;

            lineNum = 0;
            line = buffReader.readLine();
            line = buffReader.readLine();
            while((line != null) && (lineNum <= 1778))
            {
                int i = lineNum;
                lineNum++;
                splitLine = line.split(",", 13);

                /*Creating the country object and importing data in */
                Country countryObj = new Country();
                countryObj.setIso3(splitLine[1]);
                countryObj.setContinent(splitLine[2]);
                countryObj.setCountryName(splitLine[3]);
                countryObj.setNuts(splitLine[12]);
                countryObj.setLat(Double.parseDouble(splitLine[4]));
                countryObj.setLongitudeCoordinate(Double.parseDouble(splitLine[5]));
                pCountryArray[i] = countryObj;

                /*Creating an array of integers for integer data in the record.*/
                CovidRecord covidRecordObj = new CovidRecord();
                covidRecordObj.setDate(splitLine[0]);

                int[] splitLineInterpreted = new int[6];
                for(int j = 6; j < 12; j++)
                {
                    if(splitLine[j].equals(""))
                    {
                        splitLineInterpreted[j - 6] = 0;
                    }
                    else
                    {
                        splitLineInterpreted[j - 6] = Integer.parseInt(splitLine[j]);
                    }
                }

                /*Creating covid record object and importing data in.*/
                covidRecordObj.setCumulativePositive(splitLineInterpreted[0]);
                covidRecordObj.setCumulativeDeceased(splitLineInterpreted[1]);
                covidRecordObj.setCumulativeRecovered(splitLineInterpreted[2]);
                covidRecordObj.setCurrentlyPositive(splitLineInterpreted[3]);
                covidRecordObj.setHospitalized(splitLineInterpreted[4]);
                covidRecordObj.setIntensiveCare(splitLineInterpreted[5]);
                covidRecordObj.setCountry(countryObj);
                pCovidRecordArray[i] = covidRecordObj;

                line = buffReader.readLine();
            }
            inputStream.close();
        }
        catch(IOException error)
        {
            if(inputStream != null)
            {
                try
                {
                    inputStream.close();
                }
                catch(IOException error2)
                {
                }
            }
            System.out.println("Error in file processing: " + error.getMessage());
        }
        return pCovidRecordArray;
    }

    public static String userCountryInput(Country [] pCountryArray)
    {
        boolean match = false;
        boolean valid = false;
        Scanner sc = new Scanner(System.in);
        String userInput = "Australia";

        while(!match && !valid)
        {
            System.out.println("Please enter a country name. Ensure it is capitalised");
            try
            {
                userInput = sc.nextLine();
                valid = true;
            }
            catch(InputMismatchException error)
            {
                System.out.println("Error!! " + error);                    
                valid = false;
            }
            for(int i = 0; i < 1777; i++)
            {
                if(pCountryArray[i].getCountryName().equals(userInput))
                {
                    match = true;
                }
            }
            if(!match)
            {
                System.out.println("No country with that name can be found within the file");
                valid = false;
            }
        }
        return userInput;
    }

    public static String userDateInput(CovidRecord [] pCovidRecordArray)
    {
        boolean valid = false;
        Scanner sc = new Scanner(System.in);
        String userInput = "31/12/2021";

        System.out.println("Please enter a date in a dd/mm/yyyy (day/month/year) format; separated by '/' slashes. Records are stored from January 31st to December 31st, please ensure your date is within that range.");
        while(!valid)
        {
            try
            {
                userInput = sc.nextLine();
                valid = true;
            }
            catch(InputMismatchException error)
            {
                System.out.println("Error!! " + error);
                valid = false;
            }
        }
        return userInput;
    }

    public static int parseValue(String dateString)
    {
        String[] parsingString;
        int monthValue;

        parsingString = dateString.split("/", 4);
        monthValue = Integer.parseInt(parsingString[1]);
        return monthValue;
    }

    public static long accumulateContinentData(String pContinentChoice, int pDataChoice, CovidRecord [] pCovidRecordArray)
    {
        CovidRecord [] tempCovidRecordArray = new CovidRecord[1777];
        int tempCovidRecordDate, pCovidRecordDate;//date stored in system
        long accumulatedData = 0;

        for(int i = 0; i < 1777; i++)
        {//counting pCovidRecordArray
            
            for(int j = 0; j <= i; j++)
            {//counting tempCovidRecordArray and "selection sorting" it
                
                //only run if it is the continent the user asked for
                if(pCovidRecordArray[i].getCountry().getContinent().equals(pContinentChoice))
                {
                    CovidRecord tempCovidRecordObj = new CovidRecord(pCovidRecordArray[i]);//copy constructor and temp object is used to copy array information rather than assign one array element to another. Takes more memory but is safer. I think.
                    tempCovidRecordArray[i] = tempCovidRecordObj;

                    pCovidRecordDate = parseValue(pCovidRecordArray[i].getDate());
                    tempCovidRecordDate = parseValue(tempCovidRecordArray[j].getDate());
                    //"updating" date stored as counter increases.
                    //if there is a duplicate covid record for a country in the temp array and it is newer then accept it
                    if((pCovidRecordArray[i].getCountry().getCountryName().equals(tempCovidRecordArray[j].getCountry().getCountryName())) && (pCovidRecordDate > tempCovidRecordDate))
                    {
                        CovidRecord covidRecordObj = new CovidRecord();
                        tempCovidRecordArray[j] = covidRecordObj;//replace the later entry with a default object that contains 0 for all information
                    }
                    //if the duplicate that appears later in the array is not newer
                    else if((pCovidRecordArray[i].getCountry().getCountryName().equals(tempCovidRecordArray[j].getCountry().getCountryName())) && (tempCovidRecordDate > pCovidRecordDate))
                    {
                        CovidRecord covidRecordObj = new CovidRecord();//change the later appearance to a default covid record object
                        tempCovidRecordArray[i] = covidRecordObj;
                    }
                }
                else
                {
                    CovidRecord covidRecordObj2 = new CovidRecord();
                    tempCovidRecordArray[i] = covidRecordObj2;
                }
            }
        }
        accumulatedData = scanTempCovidRecordArray(pDataChoice, tempCovidRecordArray);
        return accumulatedData;
    }
    //in hindsight, "date checker" could have been made into another method

    public static long accumulateCountryData(String pCountryChoice, int pDataChoice, CovidRecord [] pCovidRecordArray)
    {
        CovidRecord [] tempCovidRecordArray = new CovidRecord[1777];
        int tempCovidRecordDate, pCovidRecordDate;
        long accumulatedData = 0;

        for(int i = 0; i < 1777; i++)
        {
            for(int j = 0; j <= i; j++)
            {
                if(pCovidRecordArray[i].getCountry().getCountryName().equals(pCountryChoice))
                {
                    CovidRecord tempCovidRecordObj = new CovidRecord(pCovidRecordArray[i]);
                    tempCovidRecordArray[i] = tempCovidRecordObj;

                    pCovidRecordDate = parseValue(pCovidRecordArray[i].getDate());
                    tempCovidRecordDate = parseValue(tempCovidRecordArray[j].getDate());

                    if((pCovidRecordArray[i].getCountry().getCountryName().equals(tempCovidRecordArray[j].getCountry().getCountryName())) && (pCovidRecordDate > tempCovidRecordDate))
                    {
                        CovidRecord covidRecordObj = new CovidRecord();
                        tempCovidRecordArray[j] = covidRecordObj;
                    }
                    else if((pCovidRecordArray[i].getCountry().getCountryName().equals(tempCovidRecordArray[j].getCountry().getCountryName())) && (tempCovidRecordDate > pCovidRecordDate))
                    {
                        CovidRecord covidRecordObj = new CovidRecord();
                        tempCovidRecordArray[i] = covidRecordObj;
                    }
                }
                else
                {
                    CovidRecord covidRecordObj2 = new CovidRecord();
                    tempCovidRecordArray[i] = covidRecordObj2;
                }
            }
        }
        accumulatedData = scanTempCovidRecordArray(pDataChoice, tempCovidRecordArray);
        return accumulatedData;
    }

    public static long accumulateDateData(String pDateChoice, int pDataChoice, CovidRecord [] pCovidRecordArray)
    {//method is created assuming there is only 1 record per country per month, so there will be no "duplicate" country records for a date
        CovidRecord [] tempCovidRecordArray = new CovidRecord[1777];
        int pCovidRecordDate, dateMatch;
        long accumulatedData = 0;

        dateMatch = parseValue(pDateChoice);
        for(int i = 0; i < 1777; i++)
        {
            pCovidRecordDate = parseValue(pCovidRecordArray[i].getDate());
            if(pCovidRecordDate == dateMatch)
            {
                CovidRecord tempCovidRecordObj = new CovidRecord(pCovidRecordArray[i]);
                tempCovidRecordArray[i] = tempCovidRecordObj;
            }
            else
            {
                CovidRecord covidRecordObj = new CovidRecord();
                tempCovidRecordArray[i] = covidRecordObj;
            }
        }
        accumulatedData = scanTempCovidRecordArray(pDataChoice, tempCovidRecordArray);
        return accumulatedData;
    }

    public static long loopAccumulateContinentData(int pDataChoice, CovidRecord [] pCovidRecordArray)
    {//sort of doubling up on the 1 record per country per month assumption; the latest country record may be in different months depending on the country
        long accumulatedData = 0;

        accumulatedData += accumulateContinentData("SA", pDataChoice, pCovidRecordArray);
        accumulatedData += accumulateContinentData("NA", pDataChoice, pCovidRecordArray);
        accumulatedData += accumulateContinentData("OC", pDataChoice, pCovidRecordArray);
        accumulatedData += accumulateContinentData("AS", pDataChoice, pCovidRecordArray);
        accumulatedData += accumulateContinentData("AF", pDataChoice, pCovidRecordArray);
        accumulatedData += accumulateContinentData("EU", pDataChoice, pCovidRecordArray);
        return accumulatedData;
    }

    public static int scanTempCovidRecordArray(int pDataChoice, CovidRecord [] pTempCovidRecordArray)
    {
        int scannedData = 0;

        for(int i = 0; i < 1777; i++)
        {
            switch(pDataChoice)
            {
                case 1:
                    scannedData += (int)pTempCovidRecordArray[i].getCumulativePositive();
                break;
                case 2:
                    scannedData += (int)pTempCovidRecordArray[i].getCumulativeDeceased();
                break;
                case 3:
                    scannedData += (int)pTempCovidRecordArray[i].getCumulativeRecovered();
                break;
            }
        }
        return scannedData;
    } 
}
