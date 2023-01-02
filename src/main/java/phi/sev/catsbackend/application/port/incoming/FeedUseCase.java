package phi.sev.catsbackend.application.port.incoming;

import java.util.UUID;

public interface FeedUseCase {

    void feed(UUID id);
}
