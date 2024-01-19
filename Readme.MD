# SolvdNavigatorApp

## Project Overview

SolvdNavigatorApp is a Java-based application designed to assist users in finding the shortest distance between two locations, utilizing Floyd's algorithm. The application provides users with the flexibility to choose between a car or public transport, offering alternative routes and detailed information on public transport options.

## Project Structure

1. **Database Setup:**
    - The project involves a database with 7 stations containing information about cities, streets, houses, and other relevant data.

2. **Data Retrieval:**
    - The application retrieves information from the database using either JDBC or MyBatis for seamless interaction.

3. **Pathfinding Algorithm:**
    - Utilizes Floyd’s algorithm to calculate the shortest distance between two locations.

4. **Car Route Information:**
    - Displays the distance when the user opts for a car as the mode of transport.

5. **Public Transport Information:**
    - For public transport, the application not only identifies the necessary stops but also suggests bus numbers and stations where users need to change buses.

6. **User Interaction:**
    - The application accepts user input through the console, allowing them to specify the starting and destination points, as well as choose between a car or public transport.

## Project Implementation

### 1. Database Configuration

- The application is designed to work with a database containing 7 stations. Ensure the database setup is complete and includes relevant data about cities, streets, houses, etc.

### 2. Data Retrieval

- Choose between JDBC or MyBatis for efficient data retrieval from the database. Implement the necessary logic to fetch information about stations and routes.

### 3. Pathfinding Algorithm

- Implement Floyd's algorithm to calculate the shortest distance between two locations.

### 4. Car Route Information

- Provide functionality to print the distance when the user opts for a car as their mode of transport.

### 5. Public Transport Information

- For public transport, implement the logic to suggest bus numbers and stations where users need to change buses. Consider displaying additional information about the transport options.

### 6. User Interaction

- Develop a user-friendly console interface that allows users to input their desired starting and destination points, as well as choose between a car or public transport.

## How to Run the Application

1. Clone the repository:

   ```bash
   git clone https://github.com/yourusername/SolvdNavigatorApp.git
   ```

2. Navigate to the project directory:

   ```bash
   cd SolvdNavigatorApp
   ```

3. Compile and run the application:

   ```bash
   javac SolvdNavigatorApp.java
   java SolvdNavigatorApp
   ```

4. Follow the on-screen instructions to interact with the application.

## Dependencies

- Java Development Kit (JDK)
- JDBC or MyBatis library (based on your choice)

## Contributors

1. **Carlos Mauricio Mazzaroli**
   - Email: cmmazzaroli.laba@solvd.com

1. **Carla Flavia Legrottaglie**

   - Email: cflegrottaglie.laba@solvd.com

1. **Fausto Villegas**

   - Email: fvillegas.laba@solvd.com

1. **Manuel Miguez Lauria**
   - Email: mmiguezlauria.laba@solvd.com

## Project Rules and Coding Conventions

### Branching Strategy

- Each developer will have a branch with their name and last name.

### Naming Conventions

- ***Camel Case:** We use Camel Case for naming variables and methods. For example: `myVariable`, `myMethod()`.

- ***Pascal Case:** We use Pascal Case for naming classes and types. For example: `MyClass`, `MyType`.

- ***Uppercase for Constants:** Constants are named in uppercase with words separated by underscores. For example: `IMPORTANT_CONSTANT`.

- ***Lowercase for Packages:** Package names are written in lowercase. For example: `database`, `controller`, etc.

## Commit Message Format

### Type of Change
- **Feature:** Introduce a new feature for the user.
- **Fix:** Resolve a bug for the user.
- **Refactor:** Restructure code without changing its behavior.
- **Docs:** Make changes to project documentation.
- **Test:** Add or modify test cases.
- **Style:** Address code style (formatting, linting, etc.).
- **Chore:** Perform routine tasks, maintenance, or housekeeping.

### Title
- Summary, imperative, start upper case, don't end with a period
- No more than 50 characters.

### Description
- Briefly describe the purpose and details of the changes made.

### Context
- Provide any necessary context or background information.

### Body
- Explain *what* and *why* (not *how*).
- Include task ID (Jira issue).
- Wrap at 72 characters.

### Related Issue
- Jira Ticket: [JIRA-123](https://your-jira-url/browse/JIRA-123)

### Co-authored-by
- Include Co-authored-by for all contributors.
- Include at least one empty line before it.
- Format: Co-authored-by: name <user@users.noreply.github.com>

### Additional Guidelines
- Separate subject from body with a blank line.
- Capitalize the subject line.
- Do not end the subject line with a period.
- Use the imperative mood in the subject line.
- Wrap the body at 72 characters.
- Use the body to explain what and why vs. how.

### Helpful Resources
- [How to Write a Git Commit Message](https://chris.beams.io/posts/git-commit/)

### Example Commit Message
```
Feature: Implement user authentication

Description:
This feature enhances the system's security by implementing user authentication.

Context:
The authentication system was necessary to ensure user data protection.

Body:
- Add user authentication to enhance security.
- Resolve Jira issue: ABC-123.
- Followed guidelines from the provided resource.

Related Issue:
- Jira Ticket: [ABC-123](https://your-jira-url/browse/ABC-123)

Co-authored-by: John Doe <john@example.com>
```


## License

This project is licensed under the [MIT License](LICENSE).

