package imd.ufrn.foodrecipes.api.mapper;

import imd.ufrn.foodrecipes.api.dto.IngredienteRequest;
import imd.ufrn.foodrecipes.api.dto.IngredienteResponse;
import imd.ufrn.foodrecipes.api.dto.ReceitaRequest;
import imd.ufrn.foodrecipes.api.dto.ReceitaResponse;
import imd.ufrn.foodrecipes.domain.entity.Ingrediente;
import imd.ufrn.foodrecipes.domain.entity.Receita;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ReceitaMapper {

    Receita toEntity(ReceitaRequest request);

    @Mapping(target = "id", ignore = true)
    void updateEntity(@MappingTarget Receita target, ReceitaRequest request);

    ReceitaResponse toResponse(Receita entity);

    Ingrediente toEntity(IngredienteRequest request);

    IngredienteResponse toResponse(Ingrediente entity);

}