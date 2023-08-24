# 🕹️Game Manager

Inatel Practical Project Evaluation (Java Developers)

### 📝 Test description:
  ➡️ https://drive.google.com/file/d/1w4KTMgVD9KPzp1p_XAS-zlPM3q1iIrdL/view?usp=drive_link

---

### 💻 Technologies

#### ✅ Technologies used in the project:

  ➡️ Java 17
  
  ➡️ Spring Boot 3.1.2
  
  ➡️ JPA
  
  ➡️ MySQL
 
  ➡️ Git
  
  ➡️ Docker
  
  ➡️ SpringDoc  
  
---

### 📝 Documentation and info

#### ✅ API documentation:
 
  ➡️ http://localhost:8081/swagger-ui/index.html
 
---

### ⚙️How to run the project

#### ✅ Run the project

  ➡️ Step 1: Generate the a docker image with the command:
  	docker build --no-cache -t eriklima/game-manager .
  
  ➡️ Step 2: Create a docker container with the command:
     docker container run --name gamemanager --network=inatel -p 8081:8081 -d eriklima/game-manager
  
  🚩 OBS: For the game manager application to work correctly, it is necessary that the containers of the "publisher-manager" application and its respective MySQL database are running, and also that they are in the "inatel" network.

---

### 💻 Consuming the API

#### I recommend using postman to consume the API:

#### ✅ Postman (App or on the web):
  ➡️ https://www.postman.com/downloads/
  
<br>

#### Below is a file that you can import into postman. This file has the list of all application endpoints, and also with request examples:

#### ✅ Collection/request list (To import into Postman):
  ➡️ https://github.com/erikflima/Game-Manager/blob/main/documentation-files/Postman-files/Game%20Manager%20-%20Requests.postman_collection.json

