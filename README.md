# RestProject

Assumptions:
 1.The OS Should be UBUNTU.
 2.The CSV or Excel File should be in the folder Excel of the Project.
 3. The CSV or Excel File should in the format utf-8 otherwise the code will not be able to read the file.
 4.The Google Drive Link of WAR file is shared with the Abhiram Bhat email id as ("abhirama.bhat@go-mmt.coom").
 5. The inclusion of WAR file in the project reduces the effort to reun the project.
  
  
  
1. Download the WAR fiel of the project from the gooogle drive link (https://drive.google.com/file/d/1Nkj9C0fVCHtHkfXbmCzdNgwC7EjBonui/view?usp=sharing)
2. Download the DockerFile from the github repository.
3. Copy Both the DockerFile and WAR file in the same directory.
4. Now go to the Directory To which you have copied the DockerFile and the WAR file and build the DockerFile with the command 
`  ("sudo docker  -t build app .")
5. Now Run the image with the command ("docker run -d  -p 8080:8080  --name mydockerappnew app")
6. Now to to your browser and and enter the URL("http://localhost:8080/RestProject/one") which will give the output corresponding to the Key "one".
7. Similarly we can change the key and it will return the json corresponding to it.
