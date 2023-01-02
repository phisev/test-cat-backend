package phi.sev.catsbackend.adapters.persistence;

import java.util.NoSuchElementException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import phi.sev.catsbackend.application.domain.Cat;
import phi.sev.catsbackend.application.domain.CatStatus;
import phi.sev.catsbackend.application.port.outgoing.LoadCatPort;
import phi.sev.catsbackend.application.port.outgoing.SaveCatPort;

@Component
@RequiredArgsConstructor
public class CatDBAdapter implements SaveCatPort, LoadCatPort {

    private final SpringDataCatRepository repository;

    @Override
    public Cat load(UUID id) {
        return repository.findById(id)
            .orElseThrow(NoSuchElementException::new)
            .toCat();
    }

    @Override
    public UUID create(String name) {
        final CatEntity createdCat = repository.save(CatEntity.builder().name(name).status(CatStatus.HAPPY.name()).build());
        return createdCat.getId();
    }

    @Override
    public void save(Cat cat) {
        CatEntity existingCat = repository.findById(cat.getId()).orElseThrow(NoSuchElementException::new);
        existingCat.setName(cat.getName());
        existingCat.setStatus(cat.getStatus().name());
        repository.save(existingCat);
    }
}
