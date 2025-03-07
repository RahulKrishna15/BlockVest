package com.blockvest.service;

import com.blockvest.model.Coin;
import com.blockvest.model.User;
import com.blockvest.model.Watchlist;

public interface WatchlistService {
    Watchlist findUserWatchlist(Long userId) throws Exception;
    Watchlist createWatchlist(User user);
    Watchlist findById(Long id) throws Exception;
    Coin addItemToWatchlist(Coin coin, User user) throws Exception;
}
