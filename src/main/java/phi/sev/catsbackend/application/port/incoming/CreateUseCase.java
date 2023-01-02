package phi.sev.catsbackend.application.port.incoming;

import java.util.UUID;

public interface CreateUseCase {

    UUID createCat(String name);
}
