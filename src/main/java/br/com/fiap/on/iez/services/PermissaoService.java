package br.com.fiap.on.iez.services;

import br.com.fiap.on.iez.exceptions.ElementoNaoEncontradoException;
import br.com.fiap.on.iez.models.entities.dto.PermissaoDTO;
import br.com.fiap.on.iez.models.entities.orm.PerfilPermissaoORM;
import br.com.fiap.on.iez.models.entities.orm.PermissaoORM;
import br.com.fiap.on.iez.models.repositories.PerfilPermissaoRepository;
import br.com.fiap.on.iez.models.repositories.PermissaoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissaoService {

    @Autowired
    private PermissaoRepository permissaoRepository;

    @Autowired
    private PerfilPermissaoRepository perfilPermissaoRepository;

    @Autowired
    private ModelMapper mapper;

    public List<PermissaoDTO> listarTodas(Pageable pageable) {
        Page<PermissaoORM> permissoes = permissaoRepository.findAll(pageable);
        return permissoes.stream()
                .map(permissao -> mapper.map(permissao, PermissaoDTO.class))
                .toList();
    }

    public PermissaoDTO listarPorId(Integer id) {
        PermissaoORM permissaoORM = permissaoRepository.findById(id).orElseThrow(() -> new ElementoNaoEncontradoException("Não foi encontrada nenhuma permissão com os parâmetros enviados"));

        return mapper.map(permissaoORM, PermissaoDTO.class);
    }

    public void deletar(int id) {
        PermissaoORM permissaoDelete = permissaoRepository.findById(id).orElseThrow(() -> new ElementoNaoEncontradoException("Permissão não encontrada no banco de dados"));

        List<PerfilPermissaoORM> usosPermissao = perfilPermissaoRepository.findByPermissao(permissaoDelete);
        if (!usosPermissao.isEmpty()) {
            perfilPermissaoRepository.deleteAll(usosPermissao);
        }

        permissaoRepository.delete(permissaoDelete);
    }

    public PermissaoDTO novaPermissao(PermissaoDTO permissao) {
        PermissaoORM permissaoRecebida = mapper.map(permissao, PermissaoORM.class);
        PermissaoORM permissaoCriada = permissaoRepository.save(permissaoRecebida);

        return mapper.map(permissaoCriada, PermissaoDTO.class);
    }

    public PermissaoDTO editar(PermissaoDTO permissao) {
        permissaoRepository.findById(permissao.getId()).orElseThrow(() -> new ElementoNaoEncontradoException("Permissão não encontrada no banco de dados"));

        PermissaoORM permissaoRecebida = mapper.map(permissao, PermissaoORM.class);
        PermissaoORM permissaoAtualizada = permissaoRepository.save(permissaoRecebida);

        return mapper.map(permissaoAtualizada, PermissaoDTO.class);
    }
}
