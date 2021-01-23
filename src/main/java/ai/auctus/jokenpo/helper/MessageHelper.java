package ai.auctus.jokenpo.helper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;

import javax.annotation.PostConstruct;
import java.util.Locale;

@RequiredArgsConstructor
public class MessageHelper {

    private final MessageSource messageSource;
    private MessageSourceAccessor accessor;

    @PostConstruct
    public void init() {
        accessor = new MessageSourceAccessor(messageSource, Locale.getDefault());
    }

    public String get(String code, Object... args) {
        return accessor.getMessage(code, args);
    }

    public String get(ErrorCode code, Object... args) {
        return accessor.getMessage(code.getMessageKey(), args);
    }

    @AllArgsConstructor
    @Getter
    public enum ErrorCode {

        ERROR_VALID_DATA("error.valid.data"),
        SWAGGER_DESCRIPTION("swagger.description"),
        SWAGGER_VERSION("swagger.version"),
        SWAGGER_ORGANIZATION_NAME("swagger.organization.name"),
        SWAGGER_ORGANIZATION_URL("swagger.organization.url"),
        SWAGGER_EMAIL("swagger.email"),
        SWAGGER_NAME("swagger.name"),
        ERROR_SERIALIZER_DATA("error.serializer.data"),
        SWAGGER_API_JOGADOR("swagger.api.jogador");

        private final String messageKey;
    }
}
