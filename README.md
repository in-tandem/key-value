
# Code Examples for KeyValue store 

Welcome to the code repository for Key Value store. The code stored serves the following purposes:

* Provide a rest api for persisting a key value object where key is a string value auto generated and value is a Type of concrete implementations of Item
* Provide a rest api for querying the backend key value store by Id
* Provide a rest api for querying the backend key value store by Name
* Provide a rest api for querying the backend key value store by Kind
* Provides an automated build tool for generating different concrete implementations of type Item. Details below
* Backend store used in Mongo Atlas DB
* Project includes Open API yaml for specifying details . Please check KeyValueOpenAPI.yaml at project root
* Project includes a postman collection KeyValueStore.postman_collection.json for easy execution of API


## How to build 
1. download and save in local directory say C:\keyvalue
2. open command prompt 
3. cd C:\keyvalue
4. gradle build
5. how to run the project gradle bootRun

## How to run 
* We will describe 3 different ways to execute rest api

--- Via Swagger endpoint <br>
1. open command prompt and execute the command gradle bootRun<br>
2. open a web browser and open http://localhost:9000/swagger-ui/index.html# <br>
3. You will find multiple methods exposed under item-db-controller which you can try out <br>

--- Via Post Man Collection <br>
1. If you have postman installed in your system, kindly File->import and select the postman collection "KeyValueStore.postman_collection.json" <br>
2. Collection via  the name KeyValueStore <br>
3. You may try out methods /requests specified under the collection <br>
4. Do ensure project is in running status <br>
	
--- Via Curl commands <br>
1. Do ensure project is in running status <br>
2. Samples provided below <br>

save : curl POST http://localhost:9000/rest/store/ -H "Content-Type: application/json" -d "{\"name\":\"charger\",\"kind\":\"product\"}" -i <br> <br>
getById : curl http://localhost:9000/rest/store/?id=5d224acc-5fa6-4f84-a1e5-08cb6daa4967 -i  <br> <br>
getByName: curl http://localhost:9000/rest/store/name?name=hb%20pencil -i <br> <br>
getByKind : curl http://localhost:9000/rest/store/findAll?kind=Lappie -i <br> <br>
delete : curl -X DELETE http://localhost:9000/rest/store/?id=ed91e1d8-176e-4a91-bfcf-1a69899bbb7b <br> <br>

## Soure Code auto generation 

The project comes with a tool for auto generation of source code. <br>
Purpose: If you ever need to create a concrete implementation of com.org.somak.store.keyvalue.dto.Item, eg say Animal ,  open command prompt

cd directory of the project

gradle run --args='Laptop'  

the above method will create 2 classes:

1. com.org.somak.store.keyvalue.dto.Laptop
2. com.org.somak.store.keyvalue.factory.LaptopFactory

once you create the above, the tool will auto generate the java types in your prject and you can simply go ahead , start the project
and run a simple curl command (eg below) to create a key-value pair for Laptop

curl POST http://localhost:9000/rest/store/ -H "Content-Type: application/json" -d "{\"name\":\"DEll\",\"kind\":\"Laptop\"}" -i


#Jacoco test coverage / test reports would be present under build /reports directory
to generate jacoco report you may run gradle jacocoTestReport