package models;

import play.data.Form;

import java.util.List;

/**
 * @author tim
 */
public class PageModel {
    public List<Fridge> allFridges;

    public Fridge fridge;

    public Form<Fridge> fridgeForm;
    public Form<Product> productForm;

    public PageModel() {
        this.allFridges = Fridge.find.all();
        this.fridge = Fridge.defaultItem();
        productForm = Form.form(Product.class);
    }

    public PageModel(final Long fridgeId) {
        this.allFridges = Fridge.find.all();
        this.fridge = Fridge.find.byId(fridgeId);
        if (fridge == null) {
            fridge = Fridge.defaultItem();
        }
        productForm = Form.form(Product.class);
    }

    public PageModel(final Long fridgeId, final Long productId) {
        this.allFridges = Fridge.find.all();
        this.fridge = Fridge.find.byId(fridgeId);
        productForm = Form.form(Product.class).fill(Product.find.byId(productId));
    }
}
