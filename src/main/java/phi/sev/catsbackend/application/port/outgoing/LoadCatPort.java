package phi.sev.catsbackend.application.port.outgoing;

import java.util.UUID;
import phi.sev.catsbackend.application.domain.Cat;

public interface LoadCatPort {

    Cat load(UUID id);
}
