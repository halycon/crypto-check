package com.cryptocheck.microservices.btc.repository;

import com.cryptocheck.microservices.btc.domain.repository.Price;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.persistence.Tuple;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public interface IBtcH2Repository extends CrudRepository<Price, BigInteger> {
    Price findTopByOrderByIdDesc();

    @Query(nativeQuery = true,
            value = " select distinct 1 as id , FORMATDATETIME(m.created_at, 'yyyy.MM.dd.HH.mm') as created_at, priceBTC, priceUSD , version " +
            " from price as m " +
            " where m.created_at > :firstDate and m.created_at < :secondDate and "+
            " m.priceUSD = ( " +
            " select max(f.priceUSD) " +
            " from price as f where "+
            " FORMATDATETIME(m.created_at, 'yyyy.MM.dd.HH.mm') = FORMATDATETIME(f.created_at, 'yyyy.MM.dd.HH.mm') " +
            " and f.created_at > :firstDate and f.created_at < :secondDate ) ")

    List<Tuple> findDataForSMACalculation(@Param("firstDate") Date firstDate,
                                          @Param("secondDate") Date secondDate);

}
