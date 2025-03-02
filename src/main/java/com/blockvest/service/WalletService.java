package com.blockvest.service;

import com.blockvest.model.Order;
import com.blockvest.model.User;
import com.blockvest.model.Wallet;

public interface WalletService {
    Wallet getUserWallet(User user);
    Wallet addBalance(Wallet wallet, Long money);
    Wallet findWalletById(Long id) throws Exception;
    Wallet walletToWalletTransfer(User sender, Wallet recieverWallet,Long amount) throws Exception;
    Wallet payOrderPayment(Order order, User user) throws Exception;
}
