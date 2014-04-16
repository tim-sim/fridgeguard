package controllers;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import models.Fridge;
import models.MainPage;
import models.Product;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.fridge;
import views.html.index;
import views.html.product;

import javax.annotation.Nullable;

public class Application extends Controller {
    public static Result root() {
        return redirect(routes.Application.index(-1));
    }

    public static Result index(Long fridgeId) {
        final MainPage mainPage = new MainPage();
        mainPage.fridges = Fridge.find.all();
        mainPage.fridgeId = fridgeId;
        if (mainPage.fridges.size() > 0) {
            mainPage.products = Iterables.find(mainPage.fridges, new Predicate<Fridge>() {
                @Override
                public boolean apply(@Nullable Fridge fridge) {
                    return fridge.id == mainPage.fridgeId;
                }
            }, Fridge.empty()).products;
        }
        return ok(index.render(mainPage));
    }

    public static Result newFridge() {
        Form<Fridge> form = Form.form(Fridge.class);
        return ok(fridge.render(form));
    }

    public static Result newProduct(Long fridgeId) {
        Form<Product> form = Form.form(Product.class);
        return ok(product.render(fridgeId, form));
    }

    public static Result saveFridge() {
        Form<Fridge> form = Form.form(Fridge.class).bindFromRequest();
        Fridge fridge = form.get();
        fridge.save();
        return redirect(routes.Application.index(fridge.id));
    }

    public static Result saveProduct(Long fridgeId) {
        Form<Product> form = Form.form(Product.class).bindFromRequest();
        Product product = form.get();
        product.fridge = Fridge.find.byId(fridgeId);
        product.save();
        return redirect(routes.Application.index(fridgeId));
    }
}
