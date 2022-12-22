package recipes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import recipes.domain.entity.Chef;
import recipes.domain.repository.ChefRepository;
import recipes.exceptions.ChefAlreadyExistException;
import recipes.exceptions.ChefNotFoundException;
import recipes.security.ChefDetailsImpl;

@Service

public class ChefDetailsServiceImpl implements UserDetailsService {

    private final ChefRepository chefRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    public ChefDetailsServiceImpl(ChefRepository chefRepository) {
        this.chefRepository = chefRepository;
    }


    public Chef findChefByUsername(String login) {
        return chefRepository.findById(login).orElseThrow(() -> new ChefNotFoundException(""));
    }

    public void add(Chef chef) {
        if (chefRepository.existsById(chef.getEmail()))
            throw new ChefAlreadyExistException("");
        chef.setPassword(encoder.encode(chef.getPassword()));
        chefRepository.save(chef);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new ChefDetailsImpl(findChefByUsername(username));
    }

}
