package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author tim
 */
@Entity
public class User extends Model {
    public static Model.Finder<Long, User> find = new Model.Finder<>(Long.class, User.class);
    @Id
    private Long id;

    @Constraints.Required
    private String username;
    @Constraints.Required
    private String password;

    private String firstName;
    private String lastName;
    @Constraints.Required
    @Constraints.Email
    private String email;

    public User authenticate(String username, String password) {
        return find.where().eq("username", username).eq("password", password).findUnique();
    }
}
