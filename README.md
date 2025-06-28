# CA Onboarding - LinkBuddy Project

Hey there! Welcome to my CA Onboarding project! This is a full-stack app I built for LinkBuddy using Spring Boot. It has a form where new joinees can enter their name and email, and they get a welcome email with a UTM link. The app tracks clicks on the UTM link and shows the data in a performance dashboard. I deployed it on Render with a PostgreSQL database.

## Project Overview
- **Tech Stack**:
  - Backend: Spring Boot (Java)
  - Frontend: HTML, CSS, Bootstrap, JavaScript
  - Database: PostgreSQL
  - Email Service: Gmail (SMTP)
  - Deployment: Render (Free Tier)
- **Features**:
  - New Joinee Form: Enter your name and email, hit submit.
  - Welcome Email: On submission, you get an email with a UTM link.
  - UTM Link Tracking: Tracks how many clicks the UTM link gets.
  - Performance Dashboard: Shows a table with ambassadors’ data (name, email, UTM link, and clicks).
  - Dark Mode: Toggle between light and dark mode with a button.

## How to Run Locally
If you want to run this project on your machine, here’s how you can do it:

1. **Clone the Repo**:

2. **Set Up PostgreSQL**:
- Install PostgreSQL on your system (available for Windows/Linux/Mac).
- Create a database:
  ```
  CREATE DATABASE linkbuddy;
  ```
- Update the `src/main/resources/application.properties` file with your database details:
  ```
  spring.datasource.url=jdbc:postgresql://localhost:5432/linkbuddy
  spring.datasource.username=postgres
  spring.datasource.password=your_password
  spring.jpa.hibernate.ddl-auto=update
  spring.mail.host=smtp.gmail.com
  spring.mail.port=587
  spring.mail.username=your_gmail@gmail.com
  spring.mail.password=your_gmail_app_password
  spring.mail.properties.mail.smtp.auth=true
  spring.mail.properties.mail.smtp.starttls.enable=true
  app.base-url=http://localhost:8080
  ```

3. **Generate Gmail App Password**:
- Enable 2-Step Verification on your Gmail account.
- Go to Google Account > Security > App passwords, and generate a new app password.
- Add that password to `application.properties`.

4. **Run the App**:
- Build and run using Maven:
  ```
  mvn clean package
  java -jar target/ca-onboarding-0.0.1-SNAPSHOT.jar
  ```
- Or just run it directly from IntelliJ IDEA.

5. **Access the App**:
- Open your browser and go to: `http://localhost:8080/`
- Fill the form, check your email, and explore the dashboard.

## Deployment on Render
I deployed this app on Render. Here’s how I did it:

1. **GitHub Repo**:
- Pushed the code to GitHub: `https://github.com/AbhishekGupta-Arch/ca_onboarding`

2. **Render Setup**:
- Signed up on Render using GitHub.
- Created a new Web Service and linked my repo.
- Added these environment variables:
  ```
  SPRING_DATASOURCE_URL=jdbc:postgresql://dpg-d0pqu5re5dus73e461g0-a.singapore-postgres.render.com:5432/linkbuddy
  SPRING_DATASOURCE_USERNAME=linkbuddy_user
  SPRING_DATASOURCE_PASSWORD=Nyuo2MNhtWzC1xIo09VllRYHUvLrojC6
  SPRING_MAIL_HOST=smtp.gmail.com
  SPRING_MAIL_PORT=587
  SPRING_MAIL_USERNAME=your_gmail@gmail.com
  SPRING_MAIL_PASSWORD=your_gmail_app_password
  SPRING_MAIL_PROPERTIES_MAIL_SMTP_AUTH=true
  SPRING_MAIL_PROPERTIES_MAIL_SMTP_STARTTLS_ENABLE=true
  APP_BASE_URL=https://ca-onboarding-xyz123.onrender.com
  ```
- Set up a PostgreSQL database on Render (free tier).

3. **Deploy**:
- Render built the app using the `Dockerfile` and deployed it.
- Live URL: `https://ca-onboarding-xyz123.onrender.com/`

## Testing
- Filled the form at `https://ca-onboarding-xyz123.onrender.com/`.
- Checked the email, tested the UTM link on my phone (it redirects to `www.industryacademiacommunity.com`).
- Viewed the dashboard to see the data (name, email, UTM link, clicks).
- Used pgAdmin 4 to check the database.

## Challenges Faced
- Initially used H2 database, but switched to PostgreSQL for deployment.
- Faced an issue with the `Dockerfile` (Maven image `maven:3.8.6-openjdk-17` wasn’t found), so I switched to `maven:3.8.7-eclipse-temurin-17`.
- Got a `psql` command error, but managed with pgAdmin 4 instead.

## Future Improvements
- Could add a login system for users.
- Maybe add filters to the dashboard.
- Improve the email templates to make them look nicer.
