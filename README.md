# CS3AC16: Advanced Computing 2018

**Module code:** CS3AC16

**Lecturer responsible:** Julian Kunkel

**Coursework description:** Develop a Software Prototype of a MapReduce-like system

**Deadlines:**

* Work to be handed in by 12:00 on: 28-02-2019
* Work will be marked and returned by: 15-03-2019

## Coursework: Passenger Airline Flights

**Description:**

For this coursework there are two files containing lists of data. These are located on the Blackboard system in the Cloud Computing assignments directory – download them from:

**Blackboard --> Enrollments  --> CS3AC16**

In the Lecture Notes tab, select as follows

**Assignments --> Coursework Data**


The coursework data includes:

Top30_airports_LatLong.csv	AComp_Passenger_data.csv

The first data file contains details of passengers that have flown between airports over a certain period. The data is in a comma delimited text file, one line per record using this format:

| Data                       | Format                            |
|----------------------------|-----------------------------------|
| Passenger ID               | *XXXnnnnXXn*                      |
| Flight ID                  | *XXXnnnnX*                        |
| From airport IATA/FAA code | *XXX*                             |
| Departure time (GMT)       | *n*[10] (This is in 'epoch' time) |
| Total flight time (mins)   | *n*[1..4]                         |



The second data file is a list of airport data comprising the name, IATA/FAA code, and location of the airport. The data is in a comma delimited text file, one line per record using this format:

| Data                  | Format       |
|-----------------------|--------------|
| Airport name          | *X*[3..20]   |
| Airport IATA/FAA code | *XXX*        |
| Latitude              | *n.n*[3..13] |
| Longitude             | *n.n*[3..13] |


Where ***X*** is Uppercase ASCII,  is digit 0..9 and  is the min/max range of the number of digits/characters in a string.

There are various errors in the **AComp_Passenger_data.csv** input data file, your code should successfully handle these in an **appropriate** manner. The output can be to screen, but must also be written to text files, the format of which is your decision.

There are two additional data input files that can be downloaded from this directory. These two additional input files are as follows:

* **AComp_Passenger_data_no_error_DateTime.csv**           
* **AComp_Passenger_data_no_error.csv**

These can be used during the **initial** development and debugging phases only.


For the final stages of development (i.e. requiring error handling) use the **AComp_Passenger_data.csv** file. Thus the ‘no_error’ files are not to be used for the software runs that generate the data for the final report, to do so will result in loss of marks.

