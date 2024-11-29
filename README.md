# CPSC 210 Personal Project (project-g5n6b)
I've chosen to make a **book/word count tracker** application that will allow the user to enter many things such as:
- Book names
- Word/page counts
- Time they finished reading

They can also add specific books to certain user libraries which they could filter by and get statistics such as:
- Total words per library
- Average book length
- Average books per library
- Longest and shortest books

It could also include tags which could be filtered by and could have its own statistics. This application would work well as a console application and also as a GUI desktop application as I'll need to make it as both versions for the other phases of this project.

I think that my target audience would be mostly people who enjoy reading books a lot and would like to know about their reading history and habits as there could be visualisations about that information. This project is also something that interested me because I read a lot of books and there aren't many apps that show all of that information without having a paywall or just not having those statistics and features. I wanted to have an app that I could also use after the project finishes for my own personal use and also for other people to use.

# User Stories
- As a user, I want to be able to add a book to my reading history.
- As a user, I want to be able to create a library and add books to it.
- As a user, I want to be able to see the list of my reading history.
- As a user, I want to be able to add statistics such as word count, page count, and reading duration to the books in my library.
- As a user, I want to have the option to save my libraries and reading history to a file.
- As a user, I want to have the option to load my libraries and reading history from a file.

# Instructions for End User

- You can generate the first required action related to the user story "adding multiple Xs to a Y" (adding libraries to the application) by:
    
    1. Run the application.
    2. Click the "**New Library**" button.
    3. Enter a name for the library and click "**OK**".
    4. Repeat Steps 2-3 for adding more libraries.

- You can generate the second required action related to the user story "adding multiple Xs to a Y" (adding books to a library) by:

    1. Run the application.
    2. Click the "**New Library**" button.
    3. Enter a name for the library.
    4. Click "**OK**".
    5. Click on the name of the library in the panel on the right.
    6. Click the "**Open selected library**" button.
    7. Click the "**New Book**" button in the new window that popped up.
    8. Enter a name for the book and click "**OK**".
    9. Enter the book's page count and click "**OK**".
    10. Enter the book's word count and click "**OK**".
    11. Enter the time taken (in minutes) to read the book and click "**OK**".

- You can locate my visual component by running the application. The visual component is the splash screen image that shows up for 4 seconds before the program starts.

- You can save the state of my application by:

    1. Run the application.
    2. Click the "**Save Library**" button at the bottom left of the screen.
    3. The data should be saved to an external file called "*bookTrackerAppData.json*".

- You can reload the state of my application by:

    1. Run the application.
    2. Click the "**Load Library**" button at the bottom of the screen.

# Phase 4: Task 2

```txt
Wed Nov 27 11:26:01 PST 2024
Created a new library
Wed Nov 27 11:26:08 PST 2024
Added a book to a library
Wed Nov 27 11:26:12 PST 2024
Created a new library
Wed Nov 27 11:26:21 PST 2024
Added a book to a library
Wed Nov 27 11:26:25 PST 2024
Added a book to a library
Wed Nov 27 11:26:30 PST 2024
Created a new library
Wed Nov 27 11:26:38 PST 2024
Added a book to a library
Wed Nov 27 11:26:47 PST 2024
Removed a library
Wed Nov 27 11:26:54 PST 2024
Removed a book from a library
```