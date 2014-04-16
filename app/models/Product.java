package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;

/**
 * @author tim
 */
@Entity
public class Product extends Model {
    @Id
    private Long id;

    private String name;

    private Date purchaseDate;

    @ManyToOne
    private Fridge fridge;

    private static Finder<Long, Product> finder = new Finder<>(Long.class, Product.class);

    public static List<Product> getAll() {
        return finder.all();
    }

    public static Product getById(Long id) {
        return finder.byId(id);
    }

    public static List<Product> getByFridge(Long fridgeId) {
        return finder.where().eq("fridgeId", fridgeId).findList();
    }
}
