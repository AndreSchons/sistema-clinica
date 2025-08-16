package ClinicaUI;

import ClinicaCadastro.Paciente;
import Structure.PacienteDao;
import java.util.Scanner;

public class Painel {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PacienteDao<Paciente> dao = new PacienteDao<>(Paciente.class);

		dao.abrirEM();

		while (true) {
			System.out.println("SEJA BEM VINDO!");
			System.out.println("1-Cadastrar Paciente");
			System.out.println("2-Excluir Paciente");
			System.out.println("3-Editar Paciente");
			System.out.println("4-Consultar Paciente");
			System.out.println("5-Listagens");			
			System.out.println("6-Fechar Sistema");
			int option = sc.nextInt();

			if (option == 1) {
				sc.nextLine();
				System.out.print("Digite o nome do paciente: ");
				String nome = sc.nextLine();
				System.out.print("Digite o CPF do paciente: ");
				String cpf = sc.nextLine();
				System.out.print("Digite o sexo do paciente: ");
				String sexo = sc.next();
				System.out.print("Digite o peso do paciente: ");
				double peso = sc.nextDouble();
				System.out.print("Digite a altura do paciente: ");
				double altura = sc.nextDouble();
				Paciente p = new Paciente(cpf, nome, sexo, altura, peso);
				dao.cadastrarPaciente(p);
			}

			else if (option == 2) {
				sc.nextLine();
				System.out.print("Digite o CPF do paciente a ser excluido: ");
				String cpf = sc.nextLine();

				try {
					dao.excluirPaciente(cpf);
					System.out.println("Paciente excluido com sucesso!");
				} catch (Exception e) {
					System.out.println("Paciente Nao Encontrado!");
				}
			}

			else if (option == 3) {
				sc.nextLine();
				System.out.print("Digite o CPF do paciente que voce deseja editar: ");
				String cpf = sc.nextLine();
				dao.atualizarPaciente(cpf);			
			}
			
			else if (option == 4) {
				sc.nextLine();
				System.out.print("Digite o CPF do paciente: ");
				String cpf = sc.nextLine();
				dao.consultarPaciente(cpf);
			}
			
			else if (option == 5) {
				System.out.println("1-Consultar Todos os Pacientes.");
				System.out.println("2-Consultar Pacientes Magros.");
				System.out.println("3-Consultar Pacientes Peso/Ideal.");
				System.out.println("4-Consultar Pacientes Acima do Peso.");
				int optionList = sc.nextInt();
				
				switch(optionList) {
				case 1: 
					dao.pacientesCadastrados();
					break;
				case 2:
					dao.listagemPacienteMagro();
					break;
				case 3:
					dao.listagemPacientePesoIdeal();
					break;
				case 4: 
					dao.listagemAcimaIdeal();
					break;
				default: 
					System.out.println("Opcao invalida! Tente Novamente.");
				}
 			}
			
			
			else if (option == 6) {
				System.out.println("Fechando sistema...");
				break;
			}
				
			}
		dao.fechar();
		sc.close();
		}
	}

