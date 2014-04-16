package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * @author tim
 */

@Entity
public class Fridge extends Model {
    @Id
    public Long id;

    @Constraints.Required
    public String name;

    @OneToMany(fetch = FetchType.LAZY)
    public List<Product> products;

    public static Finder<Long, Fridge> find = new Finder<>(Long.class, Fridge.class);
}
