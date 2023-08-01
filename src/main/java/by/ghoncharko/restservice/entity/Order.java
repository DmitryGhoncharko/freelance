package by.ghoncharko.restservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "order")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Order {
    @Id
    @SequenceGenerator(name = "order_seq",
            sequenceName = "order_sequence",
            initialValue = 1, allocationSize = 20)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "order_seq")
    private BigInteger id;
    @Column(nullable = false, name = "order_name")
    @EqualsAndHashCode.Exclude
    private String orderName;
    @Column(nullable = false, name = "order_description")
    @EqualsAndHashCode.Exclude
    private String orderDescription;
    @Column(nullable = false, name = "order_cost")
    @EqualsAndHashCode.Exclude
    private Double orderCost;
    @Column(nullable = false, name = "date_created")
    @EqualsAndHashCode.Exclude
    private Timestamp dateCreated;
    @Column(nullable = false, name = "last_update_date")
    @EqualsAndHashCode.Exclude
    private Timestamp lastUpdateDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private OrderStatus orderStatus;

    public String fullToString(){
        return "Order [id= " + getId().toString() + ", orderName= " + getOrderName() + ", orderDescription= " + orderDescription + ", orderCost= " + getOrderCost() + ", dateCreated= " + getDateCreated() + ", lastUpdateDate= " + getLastUpdateDate() +   ", orderStatus= " + getOrderStatus() + "]";
    }
}
