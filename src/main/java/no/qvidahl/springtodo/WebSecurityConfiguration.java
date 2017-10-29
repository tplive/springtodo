package no.qvidahl.springtodo;

import no.qvidahl.springtodo.model.Bruker;
import no.qvidahl.springtodo.model.BrukerRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
public class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {

    private static final Logger log = Logger.getLogger(TodoController.class);

    @Autowired
    BrukerRepository brukerRepository;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }

    @Bean
    UserDetailsService userDetailsService() {

        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

                Bruker bruker = brukerRepository.findByUsername(username);
                log.info(bruker.getUsername());

                if(bruker != null) {

                    return new User(bruker.getUsername(), bruker.getPassword(), true, true, true, true,
                            AuthorityUtils.createAuthorityList("USER"));
                }else {
                    throw new UsernameNotFoundException("Fant ikke brukeren: " + username);
                }
            }
        };
    }
}
