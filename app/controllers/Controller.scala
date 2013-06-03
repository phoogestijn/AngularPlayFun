package controllers

import play.api.Logger
import play.api.mvc.{ Controller, Action }
import play.api.mvc.Results._
import play.api.mvc.BodyParsers._
import play.api.Play.{ current, configuration }
import play.api.libs.json.Json
import models.{ Person, Message }
import play.api.libs.Crypto
import java.io.File
import scala.io.Source
import play.api.libs.json.JsSuccess
import play.api.libs.json.JsResult
import play.api.libs.json.JsError
import play.api.libs.json.JsPath
import play.api.libs.functional.syntax._

object Application extends Controller {

  def index = Action {
    Ok(views.html.index())
  }

  def persons = Action {
    Logger.warn("get list of persons")
    Ok(Json.toJson(Person.persons))
  }
  /* add a person from the POST */
  def addPerson = Action(parse.json) { implicit request =>
    implicit val tupleReads = (
      (JsPath \ 'name).read[String] and
      (JsPath \ 'age).read[Int]) tupled

    Logger.warn("adding a person")
    request.body.validate[(String, Int)].map {
      case (name, age) => {
        Person.add(Person(Person.nextId(), name, age)) match {
          case Left(person: Person) => Ok(Json.toJson(person))
          case Right(message: Message) => Forbidden(Json.toJson(message))
        }
      }
    }.recoverTotal {
      e => BadRequest(Json.toJson("Invalid json request:" + JsError.toFlatJson(e)))
    }
  }

  def findPerson(id: Int) = Action {
    Logger.warn("get person by id")
    Person.findById(id) match {
      case Some(person) => Ok(Json.toJson(person))
      case None => NotFound
    }
  }
}
object AngFun {
  def addHash(uri: String): String = {
    current.getExistingFile(uri).map(toString(_)) match {
      case Some(string) => {
        val hash = Crypto.sign(string)
        s"$uri?h=$hash"
      }
      case None => {
        Logger.error(s"file for uri $uri not found")
        uri
      }
    }
  }
  private def toString(f: File): String = {
    Source.fromFile(f).mkString
  }
}