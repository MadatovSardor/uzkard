package uz.app.dto.card;

import uz.app.enums.EntityStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardFilterDTO {

    private String phone;

    private String cardNumber;

    private LocalDate expDate;

    private LocalDateTime createdDate;

    private Long fromBalance;

    private Long toBalance;

    private String profileName;

    private EntityStatus status;

    private String clientId;

    private String clientName;

    private EntityStatus clientStatus;

}
