Shraddha Jain and Veronika Alex

***********************************************************************************************************************************************************
Classes:

The following classes are included in Homework 5:

	1 - Main - tester class
	2 - FileReader - reads in the file, and creates a list of lines 
	3 - FileParser - takes the list created by FileReader and parses the information. Creates User, Item and Library objects, and passes the information to Library.
	4 - User - represents the user in the real world. Holds an identifier (userID), and a map of all the movies the user rated, with the ratings 
	5 - Item - represents an item in the real world. Has a unique identifier (itemID) 
	6 - Library - Acts like the hosting service (example, Netflix). Holds the list of all the movies, along with the users that rated those movies. Also holds the list of all the users and the movies that user has rated.
	7 - Predictions - This is the implementation of the interface Prediction that does the predictions. It receives a copy of the Library class when it is created.  
	8 - Neighborhoods - This class generates the neighborhood for the user based on a given neighborhood size
	9 - PearsonCorrelation - This implements the interface Similarity. It takes in the user, and its neighborhood to return the similarity for the user. Prediction uses this similarity to make predictions.
	10 - ValueComparator - Overrides the compare method for one of our maps


**************************************************************************************************************************************************************

The basic idea behind the design is: 

Main is the interface the user will interact with. It will ask the user which User object and Item object to make predictions on, it will also ask for the threshold value. In doing so, the user does not see the implementation details of the project. 
Prediction and Similarity are created as interfaces because it is possible we use different methodologies for creating predictions, and Similarities (instead of Pearson coefficient). 

**************************************************************************************************************************************************************

Program Instructions:

You should navigate to the Tester classes and from there you can run the correct functionality.





