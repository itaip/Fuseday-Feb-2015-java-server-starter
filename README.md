#Fuseday Java Starter Server

This project was written only for Tikal's fuseday and should not be used for any other purpose.

## Requirements
* Java8
* Gradle

## API
### Checkin
**POST** /checkin  data:{"userId": "[UUID]", "latitude":[decimal latitude], "longitude":[decimal longitude]}

### Attack
**POST** /attack/{targetIP}/{num of threads}  - _starts sending checkin requests on http://[targetIp]:8080/checkin, using the given number of threads_ 

**DELETE** /attack - _stops all ongoing checkin bots_  



_ This software was created for educational purposes only. Any other usage is hereby forbidden _ 