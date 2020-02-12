package kasianov.fx.dto.impl;

import kasianov.fx.dto.markerinterface.Dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BaseErrorDTO implements Dto {
    private HttpStatus status;
    private String message;
    private String debugMessage;
}
