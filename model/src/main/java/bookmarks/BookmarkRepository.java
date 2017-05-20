package bookmarks;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

/**
 * Created by Me on 20/05/2017.
 */
public interface BookmarkRepository extends JpaRepository<Bookmark, Long>{
    Collection<Bookmark> findByAccountUsername(String username);
}
