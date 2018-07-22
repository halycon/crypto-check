package com.cryptocheck.microservices.eth.domain.repository;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "price")
public class Price implements Serializable {

    private static final long serialVersionUID = -4149871241519244778L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;

    @Column(name = "priceUSD" , columnDefinition="Decimal")
    private BigDecimal priceUSD;

    @Column(name = "priceBTC" , columnDefinition="Decimal")
    private BigDecimal priceBTC;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", columnDefinition = "DATETIME")
    public Date createdAt ;

    @Version
    private long version;

    @PrePersist
    private void createdAt(){
        this.createdAt = new Date();
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public BigInteger getId() {

        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public BigDecimal getPriceUSD() {

        return priceUSD;
    }

    public void setPriceUSD(BigDecimal priceUSD) {
        this.priceUSD = priceUSD;
    }

    public BigDecimal getPriceBTC() {
        return priceBTC;
    }

    public void setPriceBTC(BigDecimal priceBTC) {
        priceBTC = priceBTC.setScale(10,BigDecimal.ROUND_HALF_UP);
        this.priceBTC = priceBTC;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price price = (Price) o;
        return version == price.version &&
                Objects.equals(id, price.id) &&
                Objects.equals(priceUSD, price.priceUSD) &&
                Objects.equals(priceBTC, price.priceBTC);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, priceUSD, priceBTC, version);
    }

    @Override
    public String toString() {
        return "Price{" +
                "id=" + id +
                ", priceUSD=" + priceUSD +
                ", priceBTC=" + priceBTC +
                ", createdAt=" + createdAt +
                ", version=" + version +
                '}';
    }
}
