package med.voll.api.endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    private String logradouro;
    private String bairro;
    private String cep;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;

    public Endereco(DadosEndereco endereco) {
        this.logradouro = endereco.logradouro();
        this.bairro = endereco.bairro();
        this.cep = endereco.cep();
        this.complemento = endereco.complemento();
        this.uf = endereco.uf();
        this.cidade = endereco.cidade();
        this.numero = endereco.numero();
    }

    public void atualizarInformacoes(DadosEndereco dados) {
        if(dados.logradouro() != null){
            this.logradouro = dados.logradouro();
        } else if(dados.bairro() != null){
            this.bairro = dados.bairro();
        }else if(dados.cep() != null){
            this.cep = dados.cep();
        }else if(dados.uf() != null){
            this.uf = dados.uf();
        }else if(dados.cidade() != null){
            this.cidade = dados.cidade();
        }else if(dados.complemento() != null){
            this.complemento = dados.complemento();
        }else {
            this.numero = dados.numero();
        }
    }
}
