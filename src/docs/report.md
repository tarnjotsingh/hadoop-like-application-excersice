# Advanced computing report

## Introduction

MapReduce, a programming model that is part of Apache Hadoop, allows big data to be processed via a parallel, distributed algorithm via clusters. The focus on this assignment was to create a prototype that focuses on the basic building blocks of the MapReduce. With a provided set of big data, in the form of csv files, a basic set of requirements needed to be met have been provided in order to illustrate an understanding of how a MapReduce system works. 

### Technologies used

Java was chosen as programming language of choice, mostly out of familiarity and experience that the author has with the language.  

Gradle was chosen as the build automation tool, all dependencies that have been included in the project can be seen under the build.gradle file.

External libraries used were limited, but 
proved to be very useful. The libraries used for the assignment included:

| Group 			 | name 	   		   | version |
|--------------------|---------------------|---------|
| org.slf4j 		 | sl4j-simple 		   | 1.6.1   |
| commons-collections| commons-io  		   | 1.3.2   |
| org.apache.commons | commons-collections | 4.0     |

* sl4j logger to log events into the command-line during runtime
* commons-io to help deal with some of the streams being opened to read the csv data.
* commons-collections to help make the splitting of data much more trivial.

### High level description of prototype

Hadoop's MapReduce uses three main stages in order to process data: mapping, shuffling and reducing. These stages are separate in order to help achieve the scalability that is required by those that want to process these vast quantities of data. Churning through that data quickly would also require an implementation of parallelisation that can run on a number of nodes where the nodes are connected to some form of network. 

The prototype being developed in this assignment aims to focus on creating the Mapper, Shuffler and Reducer components that are able to process the csv data provided. Where Hadoop aims to run on clusters, this prototype is designed to just work on the system it is being run on as there is no requirement to include this feature. 


## Version Control process

Git was used as the version control system for this project. The university GitLab was used to host the repository for this project.

Link to the CS-GitLab repository: <https://csgitlab.reading.ac.uk/gv009864/advanced-computing-coursework>

### Commands used to commit and push changes to the repository

#### Setting up the git project

1. After setting up the Gradle project, ```git init``` was used in root directory to initialise it as a git project.

2. Set up the project to use the newly created repository on CS-GitLab:  ```git remote add origin https://csgitlab.reading.ac.uk/gv009864/advanced-computing-coursework.git```

3. Create an initial commit for the current project so that they are pushed to the remote server in the next step: 
	* ```git add *``` 
	* ```git commit -m "First commit"```

4. Since it is the first commit: 
	* ```git push -u origin --all```
	* ```git push -u origin --tags```

#### Every other commit

1. Using ```git status``` and ```git diff``` to check the changes made since the last commit/status of the last point a file was added to be tracked. Review the status and the diff to confirm the changes made.

2. Add everything or just the files to add to staging/to be tracked.
	* ```git add *``` or ```git add _filename_```

3. Using ```git status``` and ```git diff``` again to check to ensure everything has been added, check for any other changes made. Using ```git add _filenam_``` where appropriate.

4. Once happy with what is has been added to staging, commit the files into a local commit. using ```git commit``` or ```git commit -m "message"```.
	* Using meaningful commit messages is always best practice to track progress made on each commit and makes it easier for others to see what has been done. In a professional environment, use of references to other tickets would be used but is not possible for this assignment. 

5. Once ready to push all local commits to the remote server that has been assigned to the git project. Use ```git push```. 
	* Since https was used over ssh,CS-GitLab credentials are required to complete the process.
	* Once the push has been completed, go to repository in the web browser to check that the push was successful.


## Method




## References