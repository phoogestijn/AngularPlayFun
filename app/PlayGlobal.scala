package angfun

import play.api._
import play.api.mvc._
import play.api.mvc.Results._
import play.api.libs.json.JsString
import play.api.http.MediaRange
import play.api.http.MimeTypes
import play.api.libs.json._
import org.joda.time.DateTime
import com.typesafe.config.ConfigFactory
import java.util.Date
/**
 * Log requests voor debug doeleinden
 */
object AccessLog extends Filter {
  override def apply(next: RequestHeader => Result)(request: RequestHeader): Result = {
    val result = next(request)
    println(s"$request \n\t => $result")
    result
  }
}

/** Zorgt voor json respons op BadRequest, NotFound en ApplicationError wanneer gevraagd. Biedt ook hooks voor debuggen. */
object Global extends  WithFilters(AccessLog) {
  override def onBadRequest(request: RequestHeader, error: String) = {
    if (acceptsJson(request)) {
      BadRequest(JsString("Bad Request: " + error))
    } else { super.onBadRequest(request, error) }
  }

  private def acceptsJson(request: RequestHeader) = request.acceptedTypes.exists(mt => mt.mediaType == "application" && mt.mediaSubType == "json")

  private def acceptsHtml(request:RequestHeader)= request.acceptedTypes.exists(mt => mt.mediaType == "text" && mt.mediaSubType == "html")

  override def beforeStart(app: Application) {}

  override def onStart(app: Application) {}

  override def onStop(app: Application) { }

  override def doFilter(a: EssentialAction): EssentialAction = a

  override def onRouteRequest(request: RequestHeader): Option[Handler] =  {
    if(acceptsHtml(request)) Logger.debug("html get: "+request.path)
    super.onRouteRequest(request)
  }
  private def debugRequest(request: RequestHeader) {

    Logger.warn("req: "+request.toString )
    Logger.warn("headers")
    Logger.warn(request.headers.toString)
    Logger.warn("acceptedTypes")
    for (mt <- request.acceptedTypes) Logger.warn(mt.toString)
  }
  override def onError(request: RequestHeader, ex: Throwable): Result = {
    if (acceptsJson(request)) {
      InternalServerError(Play.maybeApplication.map {
        case app if app.mode != Mode.Prod => JsString(ex.toString())
        case app => JsString("an error occurred")
      }.getOrElse(JsString("an error occurred")))
    } else {
      super.onError(request, ex)
    }
  }

  override def onHandlerNotFound(request: RequestHeader): Result = {
    if (acceptsJson(request)) {
      NotFound(JsString("Not found"))
    } else {
      super.onHandlerNotFound(request)
    }
  }

  override def onRequestCompletion(request: RequestHeader) { }

}

