package controllers;

import models.Fridge;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

public class Application extends Controller {

    public static Result index() {
        Form<Fridge> newFridgeForm = Form.form(Fridge.class);
        return ok(index.render(Fridge.find.all(), newFridgeForm));
    }

    public static Result saveFridge() {
        Form<Fridge> fridgeForm = Form.form(Fridge.class).bindFromRequest();
        fridgeForm.get().save();
        return redirect(routes.Application.index());
    }
}
