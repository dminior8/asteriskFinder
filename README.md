# AsteriskFinder App

AsteriskFinder is a JavaFX application designed to validate JSON files and check for the presence of the asterisk (*) character within the JSON content. 
This application utilizes BootstrapFX and CSS stylesheet for styling. It also includes a set of unit tests for the `JsonService` class implemented using JUnit and mock tests using Mockito.

## Features

- Validates JSON files for correct syntax.
- Checks for the presence of the asterisk (*) character within JSON content.
- JavaFX application with a graphical user interface.
- Utilizes BootstrapFX for enhanced styling.

## Installation

To run the AsteriskFinder application, follow these steps:

1. Clone the repository to your local machine:

   ```bash
   git clone https://github.com/your-username/AsteriskFinder.git
   ```

2. Open the project in your preferred Java IDE (e.g., IntelliJ IDEA, Eclipse).

3. Build the project and resolve any dependencies.

4. Run the `Main` class located in the `pl.dminior8.asteriskfinder` package.

### Usage

1. Launch the application.
2. Enter the name of the JSON file you want to validate in the provided text field.
3. Click the "Check" button.
4. The application will display whether the JSON file is valid and if it contains the asterisk (*) character.


## Description
The main panel of the Asterisk Finder application serves as the user interface for interacting 
with the JSON validation and asterisk detection functionalities.

![image](https://github.com/dminior8/asteriskFinder/assets/86890266/77dcd8e3-9a66-4944-a5b1-e6386fdd3b41)</br>
Main panel of Asterisk Finder.</br>
Features:
* File Input Field: Allows users to input the filename of the JSON file they want to validate and search.
* Check Button: Initiates the validation and search process based on the provided filename.
* Status Labels: Displays instructions and status messages to guide the user through the validation process.
* Result Label: Displays the validation result and asterisk detection status.</br></br>

![image](https://github.com/dminior8/asteriskFinder/assets/86890266/c501dd35-ea86-458e-98a2-421d39770b70)</br>
Message after wrote wrong filename.</br>
* Incorrect Filename: If the entered filename does not correspond to an existing JSON file, an error message will be displayed prompting the user to try again.
* Invalid JSON File: If the JSON file's syntax or structure is invalid, an error message will be shown, 
indicating that the file is incorrect, and the user will be prompted to try again.</br></br>

![image](https://github.com/dminior8/asteriskFinder/assets/86890266/cc72c93e-b0bb-47b4-8e0b-eac590af00b5)</br>
If an asterisk (*) symbol is found within the JSON file, the application will display a message indicating "FALSE".</br></br>



![image](https://github.com/dminior8/asteriskFinder/assets/86890266/915ad05d-5b03-488f-9b75-dc22cebe2b2c)</br>
If no asterisk is found, the application will display "TRUE".</br></br>

### Testing

The project includes unit tests for the `JsonService` class. These tests ensure the correctness of JSON validation and asterisk detection functionalities.

To run the tests:

1. Navigate to the `JsonServiceTest` class located in `src/test/java/pl.dminior8.asteriskfinder`.
2. Run the tests using your IDE's testing facilities or via Maven command line. Navigate to the root directory of your Maven project (where the `pom.xml` file is located) and run the following command:

```bash
mvn test
```
This command will compile your project's source code, execute all unit tests in the `src/test/java` directory, and provide a summary of the test results.

### Dependencies

- JavaFX: Provides the framework for building the graphical user interface.
- BootstrapFX: Used for styling the JavaFX application.
- JUnit: Testing framework for unit tests.
- Mockito: Testing framework for mock tests.
- Gson: Library for JSON parsing and manipulation in accordance with the [AWS::IAM::Role Policy](https://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/aws-properties-iam-role-policy.html).


## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Author

©2024 [Daniel Minior](https://github.com/dminior8)

