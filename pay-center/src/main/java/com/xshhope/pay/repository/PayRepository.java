package com.xshhope.pay.repository;

import com.xshhope.model.pay.PayPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xshhope
 */
@Repository
public interface PayRepository extends JpaRepository<PayPO, String>, JpaSpecificationExecutor {

    @Query("from PayPO pay where pay.deleted = 0 and pay.id =:id")
    public PayPO getPayById(@Param("id") String id);

    @Query("from PayPO pay where pay.deleted = 0 and pay.payState = :state")
    List<PayPO> getByState(@Param("state") Integer state);

    @Query(nativeQuery = true, value = "select * from dt_pay where DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(gmt_created)")
    List<PayPO> getLastWeek();
}
