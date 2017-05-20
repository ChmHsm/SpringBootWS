package bookmarks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

/**
 * Created by Me on 20/05/2017.
 */

@RestController
@RequestMapping("/{userId}/bookmarks")
public class BookmarkRestController {

    private AccountRepository accountRepository;
    private BookmarkRepository bookmarkRepository;

    @Autowired
    BookmarkRestController(AccountRepository accountRepository, BookmarkRepository bookmarkRepository){
        this.accountRepository = accountRepository;
        this.bookmarkRepository = bookmarkRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    Collection<Bookmark> readBookmarks(@PathVariable String userId){
        this.validateUser(userId);
        return this.bookmarkRepository.findByAccountUsername(userId);
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> add(@PathVariable String userId, @PathVariable Bookmark input){
        this.validateUser(userId);

        return accountRepository.findByUsername(userId)
                .map(account -> {
                    Bookmark result = this.bookmarkRepository.save(new Bookmark(account, input.uri, input.description));
                    URI location = ServletUriComponentsBuilder
                            .fromCurrentRequest().path("/{id}")
                            .buildAndExpand(result.getId()).toUri();
                    return ResponseEntity.created(location).build();
                })
                .orElse(ResponseEntity.noContent().build());
    }

    @RequestMapping(method = RequestMethod.GET, value="/{bookmarkId}")
    Bookmark readBookmark(@PathVariable String userId, @PathVariable Long bookmarkId){
        this.validateUser(userId);
        return bookmarkRepository.findOne(bookmarkId);
    }

    private void validateUser(String userId){
        this.accountRepository.findByUsername(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }
}
