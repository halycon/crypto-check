package com.cryptocheck.microservices.neo.repository;

import com.cryptocheck.microservices.neo.domain.repository.Price;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public interface INeoH2Repository extends CrudRepository<Price, BigInteger> {
    public Price findTopByOrderByIdDesc();

    @Query(nativeQuery = true,
            value = " select distinct created_at, priceUSD, priceBTC " +
            " from price as m " +
            " where m.created_at < :firstDate and m.created_at > secondDate and "+
            " FORMATDATETIME(m.created_at, 'yyyy.MM.dd.HH.mm') = ( " +
            " select max(priceUSD) " +
            " from price as f where "+
            " FORMATDATETIME(m.created_at, 'yyyy.MM.dd.HH.mm') = FORMATDATETIME(f.created_at, 'yyyy.MM.dd.HH.mm') " +
            " and f.created_at < :firstDate and f.created_at > secondDate ) ")

    public List<Price> findDataForSMACalculation(@Param("firstDate") Date firstDate,
                                                 @Param("firstDate") Date secondDate);

}
