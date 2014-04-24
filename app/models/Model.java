package models;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import play.data.Form;

import javax.annotation.Nullable;
import java.util.List;

/**
 * @author tim
 */
public class Model {
    public List<Fridge> allFridges;
    public Form<Product> productForm;
    public Form<Fridge> fridgeForm;

    private Long activeFridgeId;
    private Long activeProductId;
    private Fridge activeFridge;
    private Product activeProduct;

    public Model() {
        this.allFridges = Fridge.find.all();
    }

    public Model(final Long fridgeId) {
        this();
        this.activeFridgeId = fridgeId;
    }

    public Model(final Long fridgeId, final Long productId) {
        this(fridgeId);
        this.activeProductId = productId;
    }

    public Fridge fridge() {
        if (activeFridgeId == null) {
            return null;
        }
        if (activeFridge == null) {
            activeFridge = Iterables.find(allFridges, new Predicate<Fridge>() {
                @Override
                public boolean apply(@Nullable Fridge fridge) {
                    return fridge.id.equals(activeFridgeId);
                }
            });
        }
        return activeFridge;
    }

    public Product product() {
        if (fridge() == null || activeProductId == null) {
            return null;
        }
        if (activeProduct == null) {
            activeProduct = Iterables.find(fridge().products, new Predicate<Product>() {
                @Override
                public boolean apply(@Nullable Product product) {
                    return product.id.equals(activeProductId);
                }
            });
        }
        return activeProduct;
    }

    public void initFridgeForm() {
        fridgeForm = Form.form(Fridge.class);
        if (fridge() != null) {
            fridgeForm = fridgeForm.fill(fridge());
        }
    }

    public void initProductForm() {
        productForm = Form.form(Product.class);
        if (product() != null) {
            productForm = productForm.fill(product());
        }
    }
}
