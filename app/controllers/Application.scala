package controllers

import play.api._
import play.api.mvc._
import play.api.cache.Cache
import play.api.Play.current
import play.api.db._

import java.sql.{ResultSet, Statement}

object Application extends Controller {

  def index = Action {
    Ok(views.html.index(null))
  }

  def db = Action {
    var out = ""
    val conn = DB.getConnection()
    try {
      val stmt = conn.createStatement

      //stmt.executeUpdate("CREATE TABLE IF NOT EXISTS mates (numero INT)")
      //stmt.executeUpdate("INSERT INTO mates VALUES (3)")

      val sqlStatement = "SELECT nombre, apellidos FROM alumnos"
      // val ordensql = "SELECT alumnos.nombre, alumnos.apellidos, alumnos_cursos.fecha_inscripcion FROM alumnos INNER JOIN alumnos_cursos ON alumnos.id=alumnos_cursos.alumno_id"

      val rs: ResultSet = stmt.executeQuery(sqlStatement)


      while (rs.next) {
        //out += "Read from DB: " + rs.getInt("nombre") + "\n"
        out += "Read from DB: " + rs.getString("nombre") + " " + rs.getString("apellidos") +  "\n"
      }
    } finally {
      conn.close()
    }
    Ok(out)
  }
}
