# Covid-data-analyser

Small object oriented program to analyse data read from a CSV file that runs in terminal. The program creates a covid record object for each country in the record which contains data on the cumulative positive, hospitalized, deceased, etc. cases for each country if one is given for them. The user can choose which country and which data to display by entering it into the terminal.
This project was made to demonstrate object oriented programming implementation and programing design techniques.

## Table of Contents

- [Covid-data-analyser](#covid-data-analyser)
  - [Table of Contents](#table-of-contents)
  - [Usage](#usage)
- [Country Class](#country-class)
- [Covid Record Class](#covid-record-class)

## Usage

Compile all java files using JDK 8, then enter "java TheBigPicture" into the terminal.

```bash
javac *.java
java TheBigPicture
```

Then follow the menu prompt by entering a valid number or string into the terminal.

# Country Class

A class to store data on the country, such as its name, longitude and latitude

# Covid Record Class

A class to store data related to covid cases of a given country, eg. the number of positive or negative cases, etc.