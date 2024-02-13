# Logistics App

## Spring boot + MySQL
## Introduction
This is a Logistics App. In this project I've used  Spring boot for the backend, MySQL for production database

(**Note: While creating the project my aim was to focus entirely in the Backend part**)

## Routes
## GET Routes
```sh
-GET /loads
-GET /loads/{loadId}
-GET /loads/shipper?shipperId = {shipperId}
```
- The first Route gets all the loads from the database
- The second Route gets the specific load identified by the loadId
- The third Route gets all the loads assigned to a Particular shipper using query params shipper Id (Type UUID)

  ## POST Route

```sh
- POST /loads
Payload : {
	"loadingPoint": "delhi",
	"unloadingPoint": "jaipur",
	"productType": "chemicals",
	"truckType": "canter",
	"noOfTrucks": "1",
	"weight": "100",
   optional:"comment": "",
	“shipperId” : “shipper:<UUID>”,
	“Date” : “dd-mm-yyyy”
}
Response:loads details added successfully 
```
- Generates a Unique loadId at the backend for this new Load and Creates a new Load and saves it in the database.


## DELETE Route

```sh
- DELETE /loads/{loadId}
```
- Deleted a given load specified by the loadId. and returns null. (Can also return a message deleted successfully, Not Implemented)

## PUT Route
```sh
PUT /loads/{loadId}
Payload : {
	"loadingPoint": "alwar",
	"unloadingPoint": "jaipur",
	"productType": "chemicals",
	"truckType": "canter",
	"noOfTrucks": "1",
	"weight": "100",
  "comment": "",
  “Date” : “dd-mm-yyyy”	
}
```
- Identifies the specific load using the loadId passed in the PathVariable and then updates that load based on the payload
