package com.example.demo.models;

import com.example.demo.models.enums.OrderStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "_room") // room is reserved so it has to be changed
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Order extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
