package kasianov.fx.decoders;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import kasianov.fx.dto.impl.BaseErrorDTO;
import kasianov.fx.exceptions.BaseError;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public class CustomErrorDecoder implements ErrorDecoder {
    
    @Override
    public BaseError decode(String methodKey, Response response) {
        BaseErrorDTO baseErrorDTO;
        try {
            Reader reader = response.body().asReader();
            String targetString = IOUtils.toString(reader);
            reader.close();
            ObjectMapper objectMapper = new ObjectMapper();
            baseErrorDTO = objectMapper.readValue(targetString, BaseErrorDTO.class);
        } catch (IOException e) {
            throw  new RuntimeException(e.getMessage());
        }
        return new BaseError(baseErrorDTO.getMessage());
    }
}
