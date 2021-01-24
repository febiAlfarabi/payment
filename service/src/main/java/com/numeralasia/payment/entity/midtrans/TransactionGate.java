package com.numeralasia.payment.entity.midtrans;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.numeralasia.payment.entity.EBase;
import com.numeralasia.payment.entity.client.Client;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@Data
@Table(name="j_transaction_gate")
@Entity
@ToString(of = "id")
@EqualsAndHashCode(callSuper = true, of = "id")
public class TransactionGate extends EBase {

    @Column(unique = true)
    String orderId ;

    @ManyToOne(fetch= FetchType.LAZY)@JsonManagedReference
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name="client_id", nullable = false)
    Client client ;

}
