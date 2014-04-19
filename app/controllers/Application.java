package controllers;

import models.Fridge;
import models.PageModel;
import models.Product;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

public class Application extends Controller {
    /*
     * GET /
     */
    public static Result home() {
        final PageModel pageModel = new PageModel();
        return ok(index.render(pageModel));
    }

    /*
     * POST /
     */
    public static Result saveFridge() {
        Form<Fridge> form = Form.form(Fridge.class).bindFromRequest();
        Fridge fridge = form.get();
        fridge.save();
        return redirect(routes.Application.index(fridge.id));
    }

    /*
     * GET /:fridgeId
     */
    public static Result index(Long fridgeId) {
        final PageModel pageModel = new PageModel(fridgeId);
        return ok(index.render(pageModel));
    }


    /*
     * POST /:fridgeId
     */
    public static Result saveProduct(Long fridgeId) {
        Form<Product> form = Form.form(Product.class).bindFromRequest();
        Product product = form.get();
        product.fridge = Fridge.find.byId(fridgeId);
        if (product.id == null) {
            product.save();
        } else {
            product.update();
        }
        return redirect(routes.Application.index(fridgeId));
    }

    /*
     * GET /:fridgeId/:productId
     */
    public static Result editProduct(Long fridgeId, Long productId) {
        PageModel pageModel = new PageModel(fridgeId, productId);
        return ok(index.render(pageModel));
    }

    /*
     * GET /:fridgeId/:productId/delete
     */
    public static Result deleteProduct(Long fridgeId, Long productId) {
        Product product = Product.find.byId(productId);
        if (product != null) {
            product.delete();
        }
        return redirect(routes.Application.index(fridgeId));
    }
}
