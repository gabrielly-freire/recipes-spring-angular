package imd.ufrn.foodrecipes.api;

import imd.ufrn.foodrecipes.api.dto.ReceitaRequest;
import imd.ufrn.foodrecipes.api.dto.ReceitaResponse;
import imd.ufrn.foodrecipes.service.ReceitaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springdoc.core.annotations.ParameterObject;

@Validated
@RestController
@RequestMapping("/api/receitas")
@Tag(name = "Receitas", description = "Operações de CRUD para receitas")
public class ReceitaApi {
    private final ReceitaService receitaService;

    public ReceitaApi(ReceitaService receitaService) {
        this.receitaService = receitaService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Criar receita",
            description = "Cria uma nova receita com seus ingredientes"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Receita criada", content = @Content(schema = @Schema(implementation = ReceitaResponse.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    @PostMapping
    public ReceitaResponse create(@RequestBody ReceitaRequest request) {
        return receitaService.create(request);
    }

    @Operation(summary = "Buscar receita por ID", description = "Retorna a receita pelo identificador")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Receita encontrada", content = @Content(schema = @Schema(implementation = ReceitaResponse.class))),
            @ApiResponse(responseCode = "404", description = "Receita não encontrada")
    })
    @GetMapping("/{id}")
    public ReceitaResponse getById(@Parameter(description = "ID da receita", example = "1") @PathVariable Long id) {
        return receitaService.getById(id);
    }

    @GetMapping
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Receitas encontradas", content = @Content(schema = @Schema(implementation = Page.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    @Operation(summary = "Listar receitas", description = "Lista paginada de receitas ativas")
    public Page<ReceitaResponse> list(@ParameterObject Pageable pageable) {
        return receitaService.list(pageable);
    }

    @Operation(summary = "Atualizar receita", description = "Atualiza os dados de uma receita existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Receita atualizada", content = @Content(schema = @Schema(implementation = ReceitaResponse.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Receita não encontrada")
    })
    @PutMapping("/{id}")
    public ReceitaResponse update(
            @Parameter(description = "ID da receita", example = "1") @PathVariable Long id,
            @RequestBody ReceitaRequest request
    ) {
        return receitaService.update(id, request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Excluir receita", description = "Exclusão lógica da receita pelo identificador")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Receita excluída"),
            @ApiResponse(responseCode = "404", description = "Receita não encontrada")
    })
    @DeleteMapping("/{id}")
    public void delete(@Parameter(description = "ID da receita", example = "1") @PathVariable Long id) {
        receitaService.delete(id);
    }
}