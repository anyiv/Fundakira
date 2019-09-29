package controllers;

import play.mvc.*;
import javax.inject.Inject;
import play.data.Form;
import play.data.FormFactory;
import models.Servicio;

public class CFundacion extends Controller {

    private Form<Servicio> servicioForm;

    @Inject
    public CFundacion(FormFactory formFactory) {
    this.servicioForm = formFactory.form(Servicio.class);
    }

//      public Result listaFundaciones(){ //lista de fundaciones
//         return TODO; //TODO significa que aun no se ha implementado
//     }

//     public Result createFundacion(){ 
//         return TODO;
//     }

//     public Result modificarF(String ean){ 
//         return TODO;
//     }

//     public Result guardarF(){
//         return TODO;
//     }

}