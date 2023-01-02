package phi.sev.catsbackend.application.port.incoming;

import java.util.UUID;

public interface PetUseCase {

    void pet(UUID id);
}
