package models;

import java.util.List;

/**
 * @author tim
 */
public class PageModel {
    public List<Fridge> allFridges;

    public Fridge activeFridge;

    public Product activeProduct;

    public PageModel() {
        this.allFridges = Fridge.find.all();
        this.activeFridge = Fridge.defaultItem();
        this.activeProduct = Product.defaultItem();
    }

    public PageModel(final Long fridgeId) {
        this.allFridges = Fridge.find.all();
        this.activeFridge = Fridge.find.byId(fridgeId);
        if (activeFridge == null) {
            activeFridge = Fridge.defaultItem();
        }
        this.activeProduct = Product.defaultItem();
    }

    public PageModel(final Long fridgeId, final Long productId) {
        this.allFridges = Fridge.find.all();
        this.activeFridge = Fridge.find.byId(fridgeId);
        this.activeProduct = Product.find.byId(productId);
    }
}
