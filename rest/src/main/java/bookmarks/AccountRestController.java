package bookmarks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * Created by Me on 20/05/2017.
 */

@RestController
@RequestMapping("/users")
public class AccountRestController {

    private AccountRepository accountRepository;
    private BookmarkRepository bookmarkRepository;

    @Autowired
    AccountRestController(AccountRepository accountRepository, BookmarkRepository bookmarkRepository){
        this.accountRepository = accountRepository;
        this.bookmarkRepository = bookmarkRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    Collection<Account> readAllUsers(){
        return this.accountRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value="/{userId}")
    Account readOneUser(@PathVariable Long userId){

        return this.accountRepository.findOne(userId);
    }

    private void validateUser(String userId){
        this.accountRepository.findByUsername(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }
}
