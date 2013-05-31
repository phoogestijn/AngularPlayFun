
GET 	/ 				controllers.Application.index

GET  	/people 	  	controllers.Application.persons

POST 	/people  		controllers.Application.addPerson

GET  	/people/:id  	controllers.Application.findPerson(id)
