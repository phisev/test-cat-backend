package phi.sev.catsbackend.application.services;

import java.util.UUID;
import phi.sev.catsbackend.application.domain.Cat;
import phi.sev.catsbackend.application.port.incoming.CreateUseCase;
import phi.sev.catsbackend.application.port.incoming.EntertainUseCase;
import phi.sev.catsbackend.application.port.incoming.FeedUseCase;
import phi.sev.catsbackend.application.port.incoming.IgnoreUseCase;
import phi.sev.catsbackend.application.port.incoming.PetUseCase;
import phi.sev.catsbackend.application.port.outgoing.LoadCatPort;
import phi.sev.catsbackend.application.port.outgoing.SaveCatPort;

public class CatService implements CreateUseCase, EntertainUseCase, FeedUseCase, IgnoreUseCase, PetUseCase {

    private SaveCatPort saveCatPort;
    private LoadCatPort loadCatPort;

    public CatService(SaveCatPort saveCatPort, LoadCatPort loadCatPort) {
        this.saveCatPort = saveCatPort;
        this.loadCatPort = loadCatPort;
    }

    @Override
    public UUID createCat(String name) {
        return saveCatPort.create(name);
    }

    public Cat load(UUID id) {
        return loadCatPort.load(id);
    }

    @Override
    public void entertain(UUID id) {
        load(id).entertain();
    }

    @Override
    public void feed(UUID id) {
        load(id).feed();
    }

    @Override
    public void ignore(UUID id) {
        load(id).ignore();
    }

    @Override
    public void pet(UUID id) {
        load(id).pet();
    }
}
