package med.voll.api.paciente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.endereco.DadosEndereco;
import med.voll.api.endereco.Endereco;

@Table(name = "tb_pacientes")
@Entity(name = "Paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private String telefone;

    private String cpf;

    @Embedded
    private Endereco endereco;

    public Paciente(DadosCadastroPaciente dados){
        this.id = null;
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.cpf = dados.cpf();

        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizaInformacoes(DadosAtualizaPaciente paciente) {
        if(paciente.nome() != null){
            this.nome = paciente.nome();
        }
        if(paciente.email() != null){
            this.email = paciente.email();
        }
        if(paciente.telefone() != null){
            this.telefone = paciente.telefone();
        } if (paciente.endereco() != null) {
            this.endereco.atualizarInformacoes(paciente.endereco());
        }
    }
}
