package controllers;

import models.Fridge;
import models.Model;
import models.Product;
import models.User;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.helper.form;
import views.html.index;
import views.html.login;

public class Application extends Controller {
    /*
     * GET /login
     */
    public static Result login() {
        return ok(login.render(Form.form(User.class)));
    }

    /*
     * GET /
     */
    public static Result index() {
        final Model model = new Model();
        return ok(index.render(model));
    }

    /*
     * GET /:fridgeId/edit
     */
    public static Result editFridge(Long fridgeId) {
        Model model = new Model(fridgeId);
        model.initFridgeForm();
        return ok(index.render(model));
    }

    /*
     * GET /add
     */
    public static Result addFridge() {
        Model model = new Model();
        model.initFridgeForm();
        return ok(index.render(model));
    }

    /*
     * POST /
     */
    public static Result saveFridge() {
        Form<Fridge> form = Form.form(Fridge.class).bindFromRequest();
        if (form.hasErrors()) {
            Long fridgeId = null;
            if (form.data().containsKey("id")) {
                fridgeId = Long.valueOf(form.data().get("id"));
            }
            redirect(routes.Application.editFridge(fridgeId));
        }
        Fridge fridge = form.get();
        if (fridge.id == null) {
            fridge.save();
        } else {
            fridge.update();
        }
        return redirect(routes.Application.openFridge(fridge.id));
    }

    /*
     * GET /:fridgeId
     */
    public static Result openFridge(Long fridgeId) {
        final Model model = new Model(fridgeId);
        model.initProductForm();
        return ok(index.render(model));
    }

    /*
     * GET /:fridgeId/delete
     */
    public static Result deleteFridge(Long fridgeId) {
        Fridge fridge = Fridge.find.byId(fridgeId);
        if (fridge != null) {
            fridge.delete();
        }
        return redirect(routes.Application.index());
    }

    /*
     * POST /:fridgeId
     */
    public static Result saveProduct(Long fridgeId) {
        Form<Product> form = Form.form(Product.class).bindFromRequest();
        if (form.hasErrors()) {
            Long productId = null;
            if (form.data().containsKey("id")) {
                productId = Long.valueOf(form.data().get("id"));
            }
            redirect(routes.Application.editProduct(fridgeId, productId));
        }
        Product product = form.get();
        product.fridge = Fridge.find.byId(fridgeId);
        if (product.id == null) {
            product.save();
        } else {
            product.update();
        }
        return redirect(routes.Application.openFridge(fridgeId));
    }

    /*
     * GET /:fridgeId/:productId/edit
     */
    public static Result editProduct(Long fridgeId, Long productId) {
        Model model = new Model(fridgeId, productId);
        model.initProductForm();
        return ok(index.render(model));
    }

    /*
     * GET /:fridgeId/:productId/delete
     */
    public static Result deleteProduct(Long fridgeId, Long productId) {
        Product product = Product.find.byId(productId);
        if (product != null) {
            product.delete();
        }
        return redirect(routes.Application.openFridge(fridgeId));
    }
}
