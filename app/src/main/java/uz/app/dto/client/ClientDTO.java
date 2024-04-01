package uz.app.dto.client;

import uz.app.dto.BaseDTO;
import uz.app.enums.EntityStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class ClientDTO extends BaseDTO {

    @NotBlank(message = "Name required")
    private String name;

    @NotBlank(message = "Surname required")
    private String surname;

    @NotBlank(message = "Phone required")
    private String phone;

    private EntityStatus status;

    private String profileName;

    public ClientDTO(String id,String name, String surname, String phone) {
        super.id = id;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
    }
}
