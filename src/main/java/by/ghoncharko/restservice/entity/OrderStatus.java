package by.ghoncharko.restservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
import java.util.List;

@Entity
@Table(name = "order_status")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class OrderStatus {
    @Id
    @SequenceGenerator(name = "order_status_seq",
            sequenceName = "order_status_sequence",
            initialValue = 1, allocationSize = 20)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_status_seq")
    private BigInteger id;
    @Column(nullable = false, name = "order_status_name")
    @EqualsAndHashCode.Exclude
    private String orderStatusName;
    @OneToMany
    @JoinColumn(name = "order_status_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Order> orders;

    public String fullToString(){
        return "Order [id= " + getId().toString() + ", orderStatusName= " + getOrderStatusName() + ", orders= " + orders.toString() + "]";
    }
}
