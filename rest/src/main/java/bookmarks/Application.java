package bookmarks;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;


/**
 * Created by Me on 20/05/2017.
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner init(final AccountRepository accountRepository, final BookmarkRepository bookmarkRepository){
        return (evt) -> Arrays.asList("houssam,boualam".split(","))
                .forEach(
                        a -> {
                            Account account = accountRepository.save(new Account(a, "password"));
                            bookmarkRepository.save(new Bookmark(account, "http://bookmark.com/1/" + a, "A description"));
                            bookmarkRepository.save(new Bookmark(account, "http://bookmark.com/2/" + a, "A description"));

                        }
                );
    }
}
