package models;

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
    public static Finder<Long, Product> find = new Finder<>(Long.class, Product.class);
    @Id
    public Long id;
    public String name;
    public Integer quantity;
    public QuantityUnit quantityUnit = QuantityUnit.PIECES;
    public Date purchaseDate = new Date();
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
    public long getAge() {
        return TimeUnit.MILLISECONDS.toDays(new Date().getTime() - purchaseDate.getTime());
    }

    public static enum QuantityUnit {
        PIECES("Pieces"),
        LITRES("Litres"),
        KILOS("Kilos");

        private String value;

        QuantityUnit(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
