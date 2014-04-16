package models;

import com.google.common.collect.Lists;
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
    public static Finder<Long, Fridge> find = new Finder<>(Long.class, Fridge.class);
    @Id
    public Long id;
    @Constraints.Required
    public String name;
    @OneToMany(fetch = FetchType.LAZY)
    public List<Product> products = Lists.newArrayList();

    public static Fridge empty() {
        return new Fridge();
    }
}
