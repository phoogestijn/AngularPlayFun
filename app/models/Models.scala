package models

import play.api.libs.json.Json
import play.api.Logger

case class Person(id: Int, name: String, age: Int)

object Person {
  implicit val jsonFormat = Json.format[Person]

  var persons = List(Person(nextId(), name = "Saasha", age = 6),
    Person(nextId(), name = "Planet", age = 8))

  private var id = 0
  def nextId() = {
    id += 1
    id
  }

  def add(person: Person): Either[Person, Message] = {
    def isUnique(person: Person) = !persons.exists(_.name == person.name)

    if (isUnique(person)) {
      persons = person :: persons
      Left(person)
    } else {
      Right(Message("Duplicate!", s"${person.name} is a duplicate.  Please enter a new name."))
    }
  }

  def findById(id: Int): Option[Person] = {
    persons.find(_.id == id)
  }
}
case class Message(title: String, message: String)
object Message {
  implicit val jsonFormat = Json.format[Message]
}

