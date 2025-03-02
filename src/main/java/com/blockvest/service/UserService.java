package com.blockvest.service;

import com.blockvest.domain.VerificationType;
import com.blockvest.model.User;

public interface UserService {
    public User findUserProfileByJwt(String jwt) throws Exception;
    public User findUserProfileByEmail(String email) throws Exception;
    public User findUserProfileById(Long userId) throws Exception;
    public User enableTwoFactorAuthentication(VerificationType verificationType,String sendTo,User user);
    public User updatePassword(User user, String newPassword);
}
