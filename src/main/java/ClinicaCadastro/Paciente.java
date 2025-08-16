package ClinicaCadastro;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Pacientes")
public class Paciente {

	@Id
	@Column(length = 14, nullable = false, unique = true)
	private String cpf;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private String sexo;

	@Column(nullable = false)
	private double altura;

	@Column(nullable = false)
	private double peso;

	@Column(nullable = false)
	private double imc;

	public Paciente() {
	}

	public Paciente(String cpf, String nome, String sexo, double altura, double peso) {
		this.cpf = cpf;
		this.nome = nome;
		this.sexo = sexo;
		this.altura = altura;
		this.peso = peso;
		calcularImc();
	}

	public void calcularImc() {
		this.imc = peso / (altura * altura);
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
		calcularImc();
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
		calcularImc();
	}

	public double getImc() {
		return imc;
	}

	public void setImc(double imc) {
		this.imc = imc;
	}

	public String toString() {
		return "Nome: (" + getNome() + ") Cpf: (" + getCpf() + ") Sexo: (" + getSexo() + ") Altura: (" + getAltura()
				+ ") Peso: (" + getPeso() + ")";
	}

}
