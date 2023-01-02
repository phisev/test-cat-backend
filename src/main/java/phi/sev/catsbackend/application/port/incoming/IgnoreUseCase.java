package phi.sev.catsbackend.application.port.incoming;

import java.util.UUID;

public interface IgnoreUseCase {

    void ignore(UUID id);
}
