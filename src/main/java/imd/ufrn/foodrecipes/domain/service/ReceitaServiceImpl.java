package imd.ufrn.foodrecipes.domain.service;

import imd.ufrn.foodrecipes.api.dto.ReceitaRequest;
import imd.ufrn.foodrecipes.api.dto.ReceitaResponse;
import imd.ufrn.foodrecipes.api.mapper.ReceitaMapper;
import imd.ufrn.foodrecipes.domain.entity.Receita;
import imd.ufrn.foodrecipes.domain.repository.ReceitaRepository;
import imd.ufrn.foodrecipes.service.ReceitaService;
import imd.ufrn.foodrecipes.infrastructure.exceptions.ResourceNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReceitaServiceImpl implements ReceitaService {
    private final ReceitaRepository receitaRepository;
    private final ReceitaMapper receitaMapper;

    public ReceitaServiceImpl(ReceitaRepository receitaRepository, ReceitaMapper receitaMapper) {
        this.receitaRepository = receitaRepository;
        this.receitaMapper = receitaMapper;
    }

    @Transactional
    public ReceitaResponse create(ReceitaRequest request) {
        Receita entity = receitaMapper.toEntity(request);
        if (entity.getIngredientes() != null) {
            for (var i : entity.getIngredientes()) {
                i.setReceita(entity);
            }
        }
        entity = receitaRepository.save(entity);
        return receitaMapper.toResponse(entity);
    }

    @Transactional(readOnly = true)
    public ReceitaResponse getById(Long id) {
        Receita entity = receitaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Receita não encontrada: " + id));
        return receitaMapper.toResponse(entity);
    }

    @Transactional(readOnly = true)
    public Page<ReceitaResponse> list(Pageable pageable) {
        return receitaRepository.findAll(pageable).map(receitaMapper::toResponse);
    }

    @Transactional
    public ReceitaResponse update(Long id, ReceitaRequest request) {
        Receita entity = receitaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Receita não encontrada: " + id));
        receitaMapper.updateEntity(entity, request);
        if (entity.getIngredientes() != null) {
            for (var i : entity.getIngredientes()) {
                i.setReceita(entity);
            }
        }
        entity = receitaRepository.save(entity);
        return receitaMapper.toResponse(entity);
    }

    @Transactional
    public void delete(Long id) {
        Receita entity = receitaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Receita não encontrada: " + id));
        receitaRepository.delete(entity);
    }
}