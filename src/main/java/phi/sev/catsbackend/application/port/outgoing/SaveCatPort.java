package phi.sev.catsbackend.application.port.outgoing;

import java.util.UUID;
import phi.sev.catsbackend.application.domain.Cat;

public interface SaveCatPort {

    UUID create(String name);

    void save(Cat cat);
}
