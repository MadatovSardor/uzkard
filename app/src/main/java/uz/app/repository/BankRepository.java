package uz.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.app.entity.CardEntity;
import uz.app.mapper.BankInfoMapper;

import javax.transaction.Transactional;

public interface BankRepository extends JpaRepository<CardEntity, String> {

    @Modifying
    @Transactional
    @Query(value = "insert into card (id, created_date, balance, card_number, status, visible) " +
            "values (cast(gen_random_uuid() as varchar), now(), 0, :bankCard, :keyWord , true);",
            nativeQuery = true)
    void bankCard(@Param("keyWord") String keyWord, @Param("bankCard") String bankCard);


    @Query(value = "select c.cardNumber as c_card from CardEntity c where c.cardNumber = :bankCard")
    BankInfoMapper findByBankCardNumber(@Param("bankCard") String bankCard);
}
