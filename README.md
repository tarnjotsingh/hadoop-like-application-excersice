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

1. AComp_Passenger_data.csv 
2. Top30_airports_LatLong.csv	

The first data file contains details of passengers that have flown between airports over a certain period. The data is in a comma delimited text file, one line per record using this format:

| Data                       | Format                              |
|----------------------------|-------------------------------------|
| Passenger ID               | *XXXnnnnXXn*                        |
| Flight ID                  | *XXXnnnnX*                          |
| From airport IATA/FAA code | *XXX*                               |
| Departure time (GMT)       | *n*\[10\] (This is in 'epoch' time) |
| Total flight time (mins)   | *n*[1..4]                           |



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

## Objectives

* Determine the number of flights from each airport; include a list of any airports not used.
* Create a list of flights based on the Flight id, this output should include the passenger Id, relevant IATA/FAA codes, the departure time, the arrival time (times to be converted to HH:MM:SS format), and the flight times.
* Calculate the number of passengers on each flight.
* Calculate the line-of-sight (nautical) miles for each flight and the total travelled by each passenger and thus output the passenger having earned the highest air miles.

## Tasks

1.  For this task in the development process, develop a **non-MapReduce** executable **prototype**, (in Java, C, or C++). The objective is to develop the basic functional ‘building-blocks’ that will support the development objectives listed above, in a way that mimics something of the operation of the MapReduce/Hadoop framework. This does not mean that you have to implement a client/server infrastructure such as Hadoop! The solution may use multi-threading if this suits your particular design and implementation strategy, the marking strategy will reflect the appropriate use of: coding techniques, succinct standard and appropriate usage of Javadoc comments, data structures and overall program design. The code should be subject to command line version control using a Git repository under your university username under https://csgitlab.reading.ac.uk.
   
    The final results/output must use the **AComp_Passenger_data.csv** file. Error detection and handling for this task can be quite basic, but it must be robust and follow a logical, well considered strategy – the latter is entirely for you to decide.
   
2.  Write a brief report (no more than 7 pages for the actual content, not including title page) explaining:
    * The high-level description of the development of the prototype software.
    * A simple description of the Git command line process undertaken.
    * A detailed description of the MapReduce functions you are replicating.
    * The output format of any reports that each job produces.
    * The strategy derived to handle input data error detection/correction and/or run-time recovery.
    * A self-appraisal of your (equivalent) MapReduce run-time software, with suggestions as to how it may be usefully improved upon. You may comment on any aspect of the development process.