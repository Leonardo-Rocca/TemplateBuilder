package web;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.*;


import spark.template.handlebars.HandlebarsTemplateEngine;

// ------ARMA TU EQUIPO-------------
public class Routes {

  public static void main(String[] args) {
    System.out.println("Iniciando servidor - Templates");

 /*       String projectDir = System.getProperty("user.dir");
        String staticDir = "/src/resources/templates";
        staticFiles.externalLocation(projectDir + staticDir);

    */
    HomeController home = new HomeController();
  
    HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
    GenerateController generate = new GenerateController();
    /*  SelectionController seleccionar = new SelectionController();   */
    port(8082);//port(8080);
    
    staticFileLocation("/templates");
  //  staticFiles.location("resources/templates");
  //  get("/",  (req, res) -> "Hello World");
    get("/", home::mostrar, engine);
    get("/index.html", (request, response) -> {
      response.redirect("/");
      return null;
    });
  //  get("/generar", generate::mostrar, engine);
    
    post("/generar", generate::generar,engine);
    
 /*   get("/generar/generate", generar::generarEquipos, engine);
    get("/generar/:name/:team", generar::asignar, engine);
    
    get("/jugadores",players::listarJugadores,engine);
    post("/jugadores", players::crear);
    get("/jugadores/eliminar/:name", players::eliminar);
    get("/jugador/:name/:hability", players::editar,engine);
    
	get("/seleccionar", seleccionar::mostrar, engine);
  /*  get("/login", login::loguear, engine);
    get("/consultaReceta",consulta::consultar,engine);
    get("/receta/:id", recetas::mostrar, engine);
    get("/recetas",recetas::listarRecetas,engine);
    get("/perfil",usuario::detallarUsuario,engine);
    post("/recetas", recetas::crear);
    get("/recetas/new", recetas::nuevo, engine);
   
*/
    get("/in.html",home::index, engine);
    
  }

}
