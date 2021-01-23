package ai.auctus.jokenpo.helper;

import ai.auctus.jokenpo.exception.SerializerException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static ai.auctus.jokenpo.helper.MessageHelper.ErrorCode.ERROR_SERIALIZER_DATA;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
@NoArgsConstructor
@Component
public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
    private static final String PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    @Autowired
    private MessageHelper messageHelper;

    public void serialize(LocalDateTime zonedDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN);
            jsonGenerator.writeString(formatter.format(zonedDateTime));
        } catch (Exception ex) {
            log.error(this.messageHelper.get(ERROR_SERIALIZER_DATA), ex);
            throw new SerializerException(BAD_REQUEST, this.messageHelper.get(ERROR_SERIALIZER_DATA));
        }
    }
}
