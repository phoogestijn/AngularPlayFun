package models

import play.api.libs.json.Json

case class Person(id:Int,name:String,age:Int)

object Person{
  implicit val jsonFormat = Json.format[Person]

  var persons = List(Person(nextId(),name="Saasha",age=6),
  	Person(nextId(),name="Planet", age=8))	

  private var id=0
   def nextId() = { 
    id+=1
    id
  }

  private def isUnique(person:Person)= ! persons.exists(_.name==person.name)
  
  def add(person:Person):Either[Person,Message]={
  	if(isUnique(person)){
  		persons=person::persons
  		Left(person)
  	}else{
  		Right(Message(s"Duplicate!","${person.name} is a duplicate.  Please enter a new name."))
  	}
  }
  def findById(id:Int):Option[Person]={
  	persons.map(f=> if(f.id==id) Some(f) else None).head
  }
}
case class Message(title:String,message:String)
object Message{
	implicit val jsonFormat= Json.format[Message]
}

