package Structure;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import java.util.Scanner;

import ClinicaCadastro.Paciente;

public class PacienteDao<E> {
	Scanner sc = new Scanner(System.in);
	private static EntityManagerFactory emf;
	private EntityManager em;
	@SuppressWarnings("unused")
	private Class<E> classe;

	static {
		try {
			emf = Persistence.createEntityManagerFactory("ClinicaProject");
		} catch (Exception e) {
		}
	}

	public PacienteDao() {
		this(null);
	}

	public PacienteDao(Class<E> classe) {
		this.classe = classe;
	}

	public PacienteDao<E> abrirEM() {
		em = emf.createEntityManager();
		return this;
	}

	public PacienteDao<E> abrirTran() {
		em.getTransaction().begin();
		return this;
	}

	public PacienteDao<E> fecharTran() {
		em.getTransaction().commit();
		return this;
	}

	public PacienteDao<E> inserir(E entidade) {
		em.persist(entidade);
		return this;
	}

	public PacienteDao<E> cadastrarPaciente(E entidade) {
		em.getTransaction().begin();
		em.persist(entidade);
		em.getTransaction().commit();
		return this;
	}

	public PacienteDao<E> atualizarPaciente(String cpf) {
		abrirTran();
		Paciente paciente = em.find(Paciente.class, cpf);
		if (paciente == null) {
			System.out.println("Paciente nao encontrado!");
		} else {

			System.out.println("PACIENTE: " + paciente.getNome());
			System.out.println("CPF: " + paciente.getCpf());
			int option;

			do {
				System.out.println("ALTERAR: ");
				System.out.println("1-NOME");
				System.out.println("2-SEXO");
				System.out.println("3-PESO");
				System.out.println("4-ALTURA");
				System.out.println("5-SAIR");

				option = sc.nextInt();
				sc.nextLine();

				switch (option) {
				case 1:
					System.out.println("Digite o novo nome: ");
					String newNome = sc.nextLine();
					paciente.setNome(newNome);
					break;
				case 2:
					System.out.println("Digite o novo sexo: ");
					String newSexo = sc.nextLine();
					paciente.setSexo(newSexo);
					break;
				case 3:
					System.out.println("Digite o novo peso: ");
					double newPeso = sc.nextDouble();
					paciente.setPeso(newPeso);
					break;
				case 4:
					System.out.println("Digite a nova altura: ");
					double newAltura = sc.nextDouble();
					paciente.setAltura(newAltura);
					break;
				case 5:
					System.out.println("Saindo da atualização...");
					break;
				default:
					System.out.println("Opção inválida! Tente novamente.");
				}

			} while (option != 5);

		}
		fecharTran();
		return this;
	}

	public PacienteDao<E> excluirPaciente(Object cpf) {
		Paciente paciente = em.find(Paciente.class, cpf);
		abrirTran();
		em.remove(paciente);
		fecharTran();
		return this;
	}

	public PacienteDao<E> fechar() {
		em.close();
		return this;
	}

	public Paciente consultarPaciente(String cpf) {
		try {
			String jpql = "SELECT p FROM Paciente p WHERE p.cpf = :cpf";
			Paciente p = em.createQuery(jpql, Paciente.class).setParameter("cpf", cpf).getSingleResult();
			System.out.println(p);
			return p;
		} catch (NoResultException e) {
			System.out.println("Usuario nao encontrado!");
			return null;
		}
	}

	public List<Paciente> listagemPacienteMagro() {
		String jpql = "SELECT p FROM Paciente p WHERE p.imc < 20";
		List<Paciente> listaMagro = em.createQuery(jpql, Paciente.class).getResultList();
		for(Paciente p: listaMagro) {
			System.out.println(p);
		}
		if (listaMagro.isEmpty()) {
			System.out.println("Lista Vazia!");
		}
		return listaMagro;
	}

	public List<Paciente> listagemPacientePesoIdeal() {
		String jpql = "SELECT p FROM Paciente p WHERE p.imc >= 20 and p.imc <=25";
		List<Paciente> listaPesoIdeal = em.createQuery(jpql, Paciente.class).getResultList();
		for (Paciente p: listaPesoIdeal) {
			System.out.println(p);
		}
		if (listaPesoIdeal.isEmpty()) {
			System.out.println("Lista Vazia!");
		} 
		return listaPesoIdeal;
	}

	public List<Paciente> listagemAcimaIdeal() {
		String jpql = "SELECT p FROM Paciente p WHERE p.imc > 25";
		List<Paciente> listaAcimaIdeal = em.createQuery(jpql, Paciente.class).getResultList();
		for(Paciente p : listaAcimaIdeal) {
			System.out.println(p);
		}
		if (listaAcimaIdeal.isEmpty()) {
			System.out.println("Lista Vazia!");
		}
		return listaAcimaIdeal;
	}

	public List<Paciente> pacientesCadastrados() {
		String jpql = "SELECT p FROM Paciente p";
		List<Paciente> listaCadastrados = em.createQuery(jpql, Paciente.class).getResultList();
		for (Paciente p : listaCadastrados) {
			System.out.println(p);
		}
		return listaCadastrados;
	}
}
