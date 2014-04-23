package models;

import com.google.common.collect.Lists;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.List;

/**
 * @author tim
 */

@Entity
public class Fridge extends Model {
    public static Finder<Long, Fridge> find = new Finder<>(Long.class, Fridge.class);
    @Id
    public Long id;
    @Constraints.Required
    public String name;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<Product> products = Lists.newArrayList();

    public static Fridge defaultItem() {
        Fridge defaultFridge = find.byId(1l);
        if (defaultFridge == null) {
            defaultFridge = new Fridge();
            defaultFridge.name = "Default";
            defaultFridge.save();
        }
        return defaultFridge;
    }
}
