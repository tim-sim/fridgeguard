package models;

import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.beans.Transient;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author tim
 */
@Entity
public class Product extends Model {
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static Finder<Long, Product> find = new Finder<>(Long.class, Product.class);
    @Id
    public Long id;
    @Constraints.Required
    public String name;
    public Integer quantity;
    public QuantityUnit quantityUnit = QuantityUnit.PIECES;
    @Formats.DateTime(pattern = DATE_FORMAT)
    public Date productionDate;
    @Formats.DateTime(pattern = DATE_FORMAT)
    public Date expirationDate;
    @ManyToOne
    public Fridge fridge;

    public Product() {
    }

    public Product(Fridge fridge) {
        this.fridge = fridge;
    }

    public static Product defaultItem() {
        return new Product();
    }

    @Transient
    public Long age() {
        return productionDate != null ? TimeUnit.MILLISECONDS.toDays(new Date().getTime() - productionDate.getTime()) : null;
    }

    @Transient
    public Long lifeTime() {
        return expirationDate != null ? TimeUnit.MILLISECONDS.toDays(expirationDate.getTime() - new Date().getTime()) : null;
    }

    public static enum QuantityUnit {
        PIECES("piece"),
        PACKS("pack"),
        LITRES("litre"),
        KILOS("kilo");

        public String singleValue;
        public String multipleValue;

        QuantityUnit(String propertyName) {
            this.singleValue = "product.unit.single." + propertyName;
            this.multipleValue = "product.unit.multiple." + propertyName;
        }
    }
}
