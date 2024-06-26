package uz.app.entity;

import uz.app.enums.EntityStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "card")
@Getter
@Setter
public class CardEntity extends BaseEntity {

    @Column(name = "card_number", nullable = false, unique = true)
    private String cardNumber;

    @Column(name = "expired_date")
    private LocalDate expiredDate;

    @Column
    private Long balance = 0L;

    @Column(name = "client_id")
    private String clientId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id",insertable = false,updatable = false)
    private ClientEntity client;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EntityStatus status;

    @Column(nullable = false)
    private Boolean visible = true;
}
