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

      val sqlStatement =
        "SELECT alumnos.nombre, alumnos.apellidos, alumnos_cursos.fecha_inscripcion, cursos.titulo " +
          "FROM alumnos " +
          "INNER JOIN alumnos_cursos ON alumnos.id=alumnos_cursos.alumno_id " +
          "INNER JOIN cursos ON alumnos_cursos.curso_id=cursos.id " +
          "WHERE alumnos_cursos.fecha_inscripcion <> 'null' " +
          "AND alumnos.nombre < 'Alejandra'"

      val rs: ResultSet = stmt.executeQuery(sqlStatement)


      while (rs.next) {
        //out += "Read from DB: " + rs.getInt("nombre") + "\n"
        out += rs.getString("alumnos.nombre") + " " +
          rs.getString("alumnos.apellidos")  + " " +
          rs.getDate("alumnos_cursos.fecha_inscripcion") + " " +
          rs.getString("cursos.titulo") +
          "\n"
      }
    } finally {
      conn.close()
    }
    Ok(out)
  }
}
