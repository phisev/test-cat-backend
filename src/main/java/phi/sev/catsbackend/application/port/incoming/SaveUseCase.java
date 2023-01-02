package phi.sev.catsbackend.application.port.incoming;

import phi.sev.catsbackend.application.domain.Cat;

public interface SaveUseCase {

    void save(Cat cat);
}
