# SecJSFApp
Secure JSFApp for M183

Author's Note (05.01.2020): Everything below is legacy documentation from the work process. All the necessary information is stored in the project documentation pdf.

## Task 1 - 09.12.2019
Organize this project:
break down the goals into tasks and create issues for each. get to know githubs project function.

Also write down how to get the project running on a new workbench/environment, because that will be necessary.

## How to: get this project running
1. import project from github via Eclipse import function
2. start MySQL and have a Tomcat v9.0 somewhere
3. switch to Java EE and configure build patch to include all .jar files from the tomcat directory
4. select project to run on server and manually select the Tomcat v9.0
5. pray

## Admin Login
see StartupBean.java

## To keep in mind upon finishing the project:
- everything necessary to get the project running needs to be in the project
- that includes userdata from the MySQL database
- also any authorization data: MySQL logins and JSFApp admin logins