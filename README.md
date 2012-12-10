jenkins-netio230b-monitor
=========================

Tool for monitoring the status of jenkins jobs. If a job is building or failed the NetIO-230b network device will get an event and can switch on traffic lights.  

For this tool to work you have to have a running Jenkins and a NetIO-230b Power Distribution Unit from Koukaam (see http://www.koukaam.se/showproduct.php?article_id=1502.)
The firmware has to be >= 4.0. It was tested with v4.01. The tested jenkins version is 1.442. The tool uses the JSON API of Jenkins.

This tool can be used to monitor several jobs on jenkins. If there are errors in the jobs an event can be send to the NetIO device so that
e.g. traffic lights can be switched on and off. It is assumed that there is a Red, Green and Yellow light connected to the plugs beginning from the first plug.

Steps to use the tool:
* Configure the application.conf with your personal settings
* Build the project with the command ```sbt one-jar```
* Take the executable jar and write a cron job for invoking the jar with ```java -jar jenkins-netio230b-monitor.jar``` at regualar intervals (e.g. 1 minute).
* You can also configure a cron job which switches the lights off (e.g. at night) with the command ```java -jar jenkins-netio230b-monitor.jar -x```
