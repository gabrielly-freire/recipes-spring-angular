package imd.ufrn.foodrecipes.service;

import imd.ufrn.foodrecipes.api.dto.ReceitaRequest;
import imd.ufrn.foodrecipes.api.dto.ReceitaResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReceitaService {
    ReceitaResponse create(ReceitaRequest request);
    ReceitaResponse getById(Long id);
    Page<ReceitaResponse> list(Pageable pageable);
    ReceitaResponse update(Long id, ReceitaRequest request);
    void delete(Long id);
}
