package controllers

import play.api.Logger
import play.api.mvc.{ Controller, Action }
import play.api.mvc.Results._
import play.api.Play.{ current, configuration }
import play.api.libs.json.Json

/** De overall controller **/
object Application extends Controller {

  def index = Action {
  	Ok(index())
  }

  def persons = Action {
  	Ok(Json.toJson(Person.persons)
  }
  /* add a person from the POST */
  def addPerson = Action(parser.json){ implicit request =>
    val name = (request.body \ "name").as[String]
    val age = (request.body \ "age").as[Int]
  	match Person.add(Person(nextId,name,age)) {
  		case Left(person:Person) => Ok(Json.toJson(person))
  		case Right(message:Message) => Forbidden(Json.toJson(message))
  	}
  }

  def findPerson(id:Int) = Action {
  	match Person.findById(id){
  		case Some(person) => Ok(Json.toJson(person))
  		case None => NotFound
  	}
  }
}