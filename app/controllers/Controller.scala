package controllers

import play.api.Logger
import play.api.mvc.{ Controller, Action }
import play.api.mvc.Results._
import play.api.mvc.BodyParsers._
import play.api.Play.{ current, configuration }
import play.api.libs.json.Json
import models.{Person, Message}

object Application extends Controller {

  def index = Action {
  	Ok(views.html.index())
  }

  def persons = Action {
  	Ok(Json.toJson(Person.persons))
  }
  /* add a person from the POST */
  def addPerson = Action(parse.json){ implicit request =>
    val name = (request.body \ "name").as[String]
    val age = (request.body \ "age").as[Int]

  	Person.add(Person(Person.nextId(),name,age)) match  {
  		case Left(person:Person) => Ok(Json.toJson(person))
  		case Right(message:Message) => Forbidden(Json.toJson(message))
  	}
  }

  def findPerson(id:Int) = Action {
  	Person.findById(id) match{
  		case Some(person) => Ok(Json.toJson(person))
  		case None => NotFound
  	}
  }
}