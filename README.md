# fse-project-manager-backend
API Details:
================================================
Users:

http://localhost:8099/fse/api/users   	[POST]
{
"firstName":"suren",
"lastName":"Velu",
"empId":"6852542"
}

http://localhost:8099/fse/api/users/{id}  [PUT]
please check database and get id to update user
(ex)
http://localhost:8099/fse/api/users/3
{
"firstName":"Shrilatha",
"lastName":"Surendran",
"empId":"6852542"
}

http://localhost:8099//users/{userid}      [DELETE]
please check database and get id to delete user
(example)
http://localhost:8099/fse/api/users/4

http://localhost:8099/fse/api/users        [GET]
===============================================
Projects:

http://localhost:8099/fse/api/project    	[POST]
get user id from database 

{
  "project": "Te",
  "priority": 7,
  "startDate": "2020-06-18",
  "endDate": "2020-06-20",
  "userId": 5
}

http://localhost:8099/fse/api/project/{id}    [PUT]
get userid and project id from database
http://localhost:8099/fse/api/project/2
{
  "project": "Java",
  "startDate": "2020-06-18",
  "endDate": "2020-06-18",
  "priority": 10,
  "userId": 4
}

http://localhost:8099/fse/api/project    [GET]

===========================================
Task:
	
http://localhost:8099/fse/api/task [POST]
get user id ,projectid and parenttask id  from database 
we need to pass isParentTask field as false if it is not parenttask
{
  "task": "Junit",
  "startDate": "2020-06-18",
  "endDate": "2020-06-18",
  "status": "Started",
  "parentId": 1,
  "projectId": 1,
  "userId": 1,
  "isParentTask": "false"
}
get user id and projectid from database 
we need to pass isParentTask field as true if it is parenttask and status,startDate,endDate as empty
{
  "task": "Mockito",
  "startDate": "",
  "endDate": "",
  "status": "",
  "parentId": 0,
  "projectId": 1,
  "userId": 1,
  "isParentTask": "true"
}
	
http://localhost:8099/fse/api/task/{id} [POST]
get task id from database
http://localhost:8099/fse/api/task/3
{
  "task": "Junit Testing",
  "startDate": "2020-06-18",
  "endDate": "2020-06-18",
  "status": "Started",
  "parentId": 1,
  "projectId": 1,
  "userId": 1,
  "isParentTask": "false"
}
http://localhost:8099/fse/api/task/4
{
  "task": "Mockito Testing",
  "startDate": "",
  "endDate": "",
  "status": "",
  "parentId": 1,
  "projectId": 1,
  "userId": 1,
  "isParentTask": "true"
}
http://localhost:8099/fse/api/parenttask [GET]

Find task by project id ,get project id from database

http://localhost:8099/fse/api/task/{projectId}
(for Example)
http://localhost:8099/fse/api/task/1
====================================
