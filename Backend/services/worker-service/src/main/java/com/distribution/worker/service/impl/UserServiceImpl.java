package com.distribution.worker.service.impl;

import com.distribution.worker.entity.User;
import com.distribution.worker.enums.UserStatus;
import com.distribution.worker.exception.ComplexValidationException;
import com.distribution.worker.repository.UserRepository;
import com.distribution.worker.service.CryptoService;
import com.distribution.worker.service.RoleService;
import com.distribution.worker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Transactional
@Service("userServiceImpl")
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CryptoService cryptoService;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            user.getRole().getAuthorities().size();
            return user;
        } else {
            throw new UsernameNotFoundException("User not found!");
        }
    }

    @Override
    public User save(User user) {
        user.setStatus(UserStatus.ACTIVE);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User view(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new ComplexValidationException("view", "userNotExist");
        }
        return user.get();
    }
}
