package kasianov.fx.dto.impl;

import kasianov.fx.dto.markerinterface.Dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDtoViewAllUsers implements Dto {
    private  Integer id;
    private  String userName;
    private  String email;

    @Override
    public String toString() {
        return userName;
    }
}
