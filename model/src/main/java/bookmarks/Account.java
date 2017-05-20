package bookmarks;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by Me on 20/05/2017.
 */

@Entity
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "account")
    @JsonIgnore
    private Set<Bookmark> bookmarks = new HashSet<>();

     @JsonIgnore
     public String password;
     public String username;

    public Account(String name, String password) {
        this.username = name;
        this.password = password;
    }

    public Set<Bookmark> getBookmarks() {
        return bookmarks;
    }

    public Long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    Account() { // jpa only
    }
}
