package web;

import java.util.HashMap;

import modelo.ViewTransformador;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class GenerateController {

	public ModelAndView mostrar(Request request, Response response) {
			return loadLists();
		  }

	private ModelAndView loadLists() {	

		    HashMap<String, Object> viewModel = new HashMap<>();
		 //   viewModel.put("jugadores", jugadores);  
		   // viewModel.put("jugadoresIzq",jugadoresE1);  	 
		//    viewModel.put("jugadoresDer",jugadoresE2);  
		    
		    return new ModelAndView(viewModel, "generar.hbs"); //recetas
	}
	
	public ModelAndView generarEquipos(Request request, Response response) {
			return loadLists();
		  }
	
		public ModelAndView generar(Request request, Response response) {
	//	  long id = Long.parseLong(request.params(":id")); ListadorDeRecetas.getListador().obtenerReceta(id);

				String n = request.queryParams("message");
				 ViewTransformador t = new ViewTransformador();
				 //System.out.print("se crea");
				// System.out.print(n);
			
		    HashMap<String, Object> viewModel = new HashMap<>();
		    viewModel.put("converted", t.convertService(n));  
		/*	int h = Integer.parseInt(request.params(":team"));
			*/
			return new ModelAndView(viewModel, "home.hbs");
//			return new ModelAndView(null, "textoGenerado.hbs");
		// 	return new ModelAndView(null,"d.hbs");
	  }
}
