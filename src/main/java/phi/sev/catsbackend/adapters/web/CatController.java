package phi.sev.catsbackend.adapters.web;

import java.net.URI;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import phi.sev.catsbackend.application.domain.Cat;
import phi.sev.catsbackend.application.services.CatService;

@RestController
@RequestMapping("/cats")
@RequiredArgsConstructor
public class CatController {

    private final CatService catService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@RequestBody CatCreateRequest request) {
        final UUID createdCatId = catService.createCat(request.name());
        URI catUri = UriComponentsBuilder.newInstance().path("/cats/{id}").buildAndExpand(createdCatId).toUri();
        return ResponseEntity.created(catUri).build();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CatDto> get(@PathVariable("id") UUID id) {
        Cat cat = catService.load(id);
        return ResponseEntity.ok(new CatDto(cat.getId(), cat.getName(), cat.getStatus()));
    }
}
