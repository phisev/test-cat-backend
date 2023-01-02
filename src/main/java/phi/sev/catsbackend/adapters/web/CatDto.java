package phi.sev.catsbackend.adapters.web;

import java.util.UUID;
import phi.sev.catsbackend.application.domain.CatStatus;

public record CatDto(UUID id, String name, CatStatus status) {

}
