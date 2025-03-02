package com.blockvest.repository;

import com.blockvest.model.Coin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoinRepository extends JpaRepository<Coin, String>
{

}
