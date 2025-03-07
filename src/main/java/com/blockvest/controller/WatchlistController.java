package com.blockvest.controller;

import com.blockvest.model.Coin;
import com.blockvest.model.User;
import com.blockvest.model.Watchlist;
import com.blockvest.model.Withdrawal;
import com.blockvest.service.CoinService;
import com.blockvest.service.UserService;
import com.blockvest.service.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/watchlist")
public class WatchlistController {
    @Autowired
    private  WatchlistService watchlistService;

    @Autowired
    private  UserService userService;

    @Autowired
    private CoinService coinService;

    @GetMapping("/user")
    public ResponseEntity<Watchlist> getUserWatchList(@RequestHeader("Authorization") String jwt) throws Exception
    {
        User user = userService.findUserProfileByJwt(jwt);
        Watchlist watchlist = watchlistService.findUserWatchlist(user.getId());
        return ResponseEntity.ok(watchlist);
    }

    @GetMapping("/{watchlistId}")
    public ResponseEntity<Watchlist> getWatchlistById(@PathVariable long watchlistId)throws Exception
    {
        Watchlist watchlist = watchlistService.findById(watchlistId);
        return ResponseEntity.ok(watchlist);
    }

    @PatchMapping("/add/coin/{coinId}")
    public ResponseEntity<Coin> addItemToWatchlist(@RequestHeader("Authorization") String jwt, @PathVariable String coinId) throws Exception
    {
        User user = userService.findUserProfileByJwt(jwt);
        Coin coin = coinService.findById(coinId);
        Coin addedCoin = watchlistService.addItemToWatchlist(coin,user);
        return ResponseEntity.ok(addedCoin);
    }


}
