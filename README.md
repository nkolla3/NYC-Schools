How to Use the app
In order to test the app, project settings.gradle need to be opened in Android Studio.
The application can run directly on a emulator. and app mainly consists of 2 screens.

What does app Do
* Once we open the app the main screen consists on list of NYC schools which is pulled from the city of newyork website.
* on tap of HighSchool card. it displays the school SAT scores from the dbn id and The data related to school is displayed from newyorkcity website.
* If No data is displayed a generic error message is displayed

Display a list of NYC High Schools.
Get your data here: https://data.cityofnewyork.us/Education/DOE-High-School-Directory-2017/s3k6-pzi2
Selecting a school should show additional information about the school
Display all the SAT scores - include Math, Reading and Writing.
SAT data here: https://data.cityofnewyork.us/Education/SAT-Results/f9bf-2cp4

Technology/Architecture Used:
 * This project is built on top of MVVM architecture. using kotlin language with coroutines.

Below are the few additional enhancements that can be added to the project. 

1. Add Unit tests for application.
2. Add search feature to search the desired school.
3. Display logs and error codes for quick debugging purpose.
