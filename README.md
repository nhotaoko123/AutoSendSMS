# Auto Send SMS Mobile

An Android application that automatically sends SMS messages to a specified phone number continuously or at specified intervals. This project is developed using Android Studio and Java.

## Table of Contents
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [Contributing](#contributing)
- [Android UI Components](#android-ui-components)

## Features

- **Automated SMS Sending**: Send SMS messages automatically to a specified phone number at regular intervals or continuously.
- **Customizable Messages**: Create and store custom messages for repeated sending.
- **Continuous Sending Mode**: Enable continuous sending of messages to the specified number.
- **Interval-Based Sending**: Set a specific time interval for repeated message sending.
- **History Log**: View a log of all sent messages with timestamps.

## Technologies Used
- **Java**: The primary programming language used to develop the application.
- **Android Studio**: The integrated development environment (IDE) used for Android development.
- **Android SMS Manager**: The API used to handle SMS sending.
- **CSV File Processing**: Used for reading and processing data from CSV files.

## Usage
1. Launch the app on your Android device.
2. Specify a phone number for sending messages via CSV file.
4. Choose the sending mode: either continuous or interval-based.
5. Start the process and let the app handle the rest.
6. View sent messages in the SMS Log section.

## Project Structure
- MainActivity.java: The main screen of the app where users can configure and start the SMS sending process.
- SmsScheduler.java: Handles the scheduling and sending of SMS messages.
- DatabaseHelper.java: Manages the SQLite database for storing messages, recipient numbers, and logs.
- Receivers/: Contains BroadcastReceiver classes that trigger actions based on time or intervals.

## Contributing
- Contributions are welcome! Please fork this repository and submit a pull request with your changes.

## Android UI Components
The app uses standard Android UI components. The design may be basic, but all functions are working as expected.

  | **Image**              |
  |----------------------|
  | <img src="https://github.com/user-attachments/assets/13c935fe-d78d-4fd7-b644-2122f1fb3365" alt="Project Diagram" width="200"/>      <img src="https://github.com/user-attachments/assets/53e610e0-98c4-45c8-b481-5baa45f34be1" alt="Project Diagram" width="200"/>      <img src="https://github.com/user-attachments/assets/f41a0899-39e9-475a-b6a5-427500228551" alt="Project Diagram" width="200"/> |
