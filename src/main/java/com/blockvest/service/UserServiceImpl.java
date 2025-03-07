package com.blockvest.service;

import com.blockvest.config.JwtProvider;
import com.blockvest.domain.VerificationType;
import com.blockvest.model.TwoFactorAuth;
import com.blockvest.model.User;
import com.blockvest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findUserProfileByJwt(String jwt) throws Exception {
        String email = JwtProvider.getEmailFromToken(jwt);
        User user = userRepository.findByEmail(email);
        if(user==null)
        {
            throw new Exception("User Not Found");
        }
        else
        {
            return user;
        }
    }

    @Override
    public User findUserProfileByEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email);
        if(user==null)
        {
            throw new Exception("User Not Found");
        }
        else
        {
            return user;
        }
    }

    @Override
    public User findUserProfileById(Long userId) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new Exception("User Not Found");
        } else {
            return user.get();
        }
    }

    @Override
    public User enableTwoFactorAuthentication(VerificationType verificationType, String sendTo, User user) {
        TwoFactorAuth twoFactorAuth = new TwoFactorAuth();
        twoFactorAuth.setEnabled(true);
        twoFactorAuth.setSendTo(String.valueOf(verificationType));
        user.setTwoFactorAuth(twoFactorAuth);
        return userRepository.save(user);
    }

    @Override
    public User updatePassword(User user, String newPassword) {
        user.setPassword(newPassword);
        return userRepository.save(user);
    }
}
