# Day Planner Assistant

## Overview

The *Day Planner Assistant* helps you organize your day to maximize productivity! Users can add details such as:
- name
- location
- time
- reminders

Whether you are a university student managing their school-day, a working professional balancing endless numbers  
of meetings or a stay-at-home parent frantically juggling all their kids' extra-curriculars; the Day Planner Assistant
is perfect for anyone looking to efficiently organize and record the events and/or activities in store for the day.
As a university student myself, I find it difficult keeping track of my class schedule along with pending assignment
deadlines or club meetings and other errands. An application such as the Day Planner Assistant would allow me to 
keep an organized record and track different activities or events I have for the day. 


## User Stories
1. As a user, I want to be able to add a new event entry.
2. As a user, I want to be able to get the list of names of the events I have added.
3. As a user, I want to be able to get the number of entries I have added.
4. As a user, I want to be able to receive a warning message if I try to enter an event at the same time as an existing one.
5. As a user, I want to be able to save my Day planner to file.
6. As a user, I want to be able to load my Day planner from file.

## Phase 4: Task 2
I designed and tested the Event class in my model package to be robust. Previously, the constructor for event had a requires
clause that needed the user to ensure they were entering a valid time (between 0 and 2400) and an entry name. However, I redesigned the 
constructor to throw an InvalidTimeException if the user entered a value outside of the range of 0 to 2400. The exception
is caught in the gui where an error message is printed notifying the user that they have entered an invalid time and prompts
them to edit their entry and try again. I also constructed the EmptyNameException to ensure the user has entered an event 
name as the entry would not be meaningful otherwise. The exception prints a message notifying the user they must require a
name for the even entry before they can continue. The class is now robust as there are no requires clauses and now only 
creates events with a name and a valid time. The location and reminder fields are optional ones, and the getEventDetails
method in the Event class has been redesigned to print a proper entry in those scenarios. 

## Phase 4: Task 3
I would refactor both the PlanningAssistant and PlannerWindow classes. To increase cohesion, I would refactor the methods 
within these classes to better group them such that each method provides a unified implementation for a specific concept 
served in the class. I would also consider creating an abstract class which creates JFrames of set sizes that were frequently
designed in the gui class to be inherited as a constructor with a super implementation to reduce code redundancy.




