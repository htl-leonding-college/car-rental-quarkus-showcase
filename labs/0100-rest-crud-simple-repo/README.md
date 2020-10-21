# Assignment 1: CRUD-REST-Endpoint for One Entity

Create a simple application for your micro-project-topic in Quarkus:

1. lastname-projectname zB mustermann-restaurant
2. The Quarkus project is to locate in the root of the project folder (no sub-dir)
2. In the package `at.htl.<projectname>.entity` zB. `at.htl.restaurant.entity` create a new entity-class ie Product
(in this restaurant are the products dishes and beverages)
3. Store the data in a collection in a appropriate repository (you use NO database)
4. in the package `at.htl.<projectname>.boundary` (ie `at.htl.restaurant.boundary`) create
a class `<Entity>Service.java` ie `ProductService.java`
5. In this class create some endpoints to provide CRUD-functionality. +

* Use
  * JsonObject
  * JsonArray
  * Response
  * marshalling of an object

6. In a file `request.http` create the appropriate requests, for consuming your endpoints.
7. The endpoints are supposed to work with data in JSON- or XML-format
8. Use Swagger for documenting your endpoints.
9. Create an essential description of your project in the `README.md`
10. Don't forget to exclude the files, which are not supposed to be stored in the github-repo.
11. Create an commit for each endpoint with appropriate messages for each commit.
12. *Deadline: dd.mmm yyyy, hh:MM*
13. You can find the Classroom Link at Discord.

TIP: Use a master data table (Stammdatentabelle)

```
____   ____.__       .__    ___________        _____      .__
\   \ /   /|__| ____ |  |   \_   _____/_______/ ____\____ |  |    ____
 \   Y   / |  |/ __ \|  |    |    __)_\_  __ \   __\/  _ \|  |   / ___\
  \     /  |  \  ___/|  |__  |        \|  | \/|  | (  <_> )  |__/ /_/  >
   \___/   |__|\___  >____/ /_______  /|__|   |__|  \____/|____/\___  /
                   \/               \/                         /_____/
```

