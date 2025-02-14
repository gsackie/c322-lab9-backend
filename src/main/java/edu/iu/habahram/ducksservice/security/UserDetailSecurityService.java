package edu.iu.habahram.ducksservice.security;

import edu.iu.habahram.ducksservice.model.Customer;
import edu.iu.habahram.ducksservice.repository.CustomerRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailSecurityService implements
        UserDetailsService {
    CustomerRepository customerRepository;

    public UserDetailSecurityService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try{
            Customer customer=
                    customerRepository.findByUsername(username);
            if(customer == null){
                throw new UsernameNotFoundException("");
            }
            return User
                    .withUsername(username)
                    .password(customer.password())
                    .build();
        }
        catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }
}
