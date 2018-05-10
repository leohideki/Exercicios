package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import Model.Pais;
import Service.PaisService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PaisTeste {

	Pais pais, copia;
	PaisService paisService;
	static int id = 0;

	/*
	 * Antes de rodar este teste, certifique-se que nao ha no banco nenhuma
	 * linha com o id igual ao do escolhido para o to instanciado abaixo. Se
	 * houver, delete. 
	 * Certifique-se também que sobrecarregou o equals na classe
	 * Cliente. 
	 * Certifique-se que a fixture cliente1 foi criada no banco.
	 * Além disso, a ordem de execução dos testes é importante; por
	 * isso a anotação FixMethodOrder logo acima do nome desta classe
	 */
	@Before
	public void setUp() throws Exception {
		System.out.println("setup");
		pais = new Pais();
		pais.setId(id);
		pais.setNome("PaisTeste");
		pais.setPopulacao(123456);
		pais.setArea(212121.00);
		copia = new Pais();
		copia.setId(id);
		copia.setNome("PaisTeste");
		copia.setPopulacao(123456);
		copia.setArea(212121.00);
		paisService = new PaisService();
		System.out.println(pais);
		System.out.println(copia);
		System.out.println(id);
	}
	
	@Test
	public void test00Carregar() throws IOException{
		System.out.println("carregar");
		Pais fixture = new Pais();
		fixture.setId(1);
		fixture.setNome("Brasil");
		fixture.setPopulacao(207000000);
		fixture.setArea(8516000.00);
		PaisService novoService = new PaisService();
		Pais novo = novoService.carregar(1);
		assertEquals("testa inclusao", novo.getId(), fixture.getId());
	}

	@Test
	public void test01Criar() throws IOException{
		System.out.println("criar");
		id = paisService.criar(pais);
		System.out.println(id);
		copia.setId(id);
		assertEquals("testa criacao", pais.getId(), copia.getId());
	}

	@Test
	public void test02Atualizar() throws IOException{
		System.out.println("atualizar");
		pais.setPopulacao(101010);
		copia.setArea(202020.00);		
		paisService.atualizar(pais);
		pais = paisService.carregar(pais.getId());
		assertEquals("testa atualizacao", pais, copia);
	}

	@Test
	public void test03Excluir() throws IOException{
		System.out.println("excluir");
		copia.setId(-1);
		copia.setNome(null);
		copia.setPopulacao(0);
		copia.setArea(0.00);
		paisService.excluir(id);
		pais = paisService.carregar(id);
		assertEquals("testa exclusao", pais, copia);
	}
	
	@Test
	public void test04MaiorPopulacao() {
		System.out.println("maior populacao");
		paisService.maiorPopulacao(pais);
		long populacao = pais.getPopulacao();
		copia.setPopulacao(populacao);
		assertEquals("testa maior populacao",pais.getPopulacao(),copia.getPopulacao());
	}
	
   @Test
	public void test05MenorArea() {
		System.out.println("menor area");
		paisService.menorArea(pais);
		double area = pais.getArea();
		copia.setArea(area);
		assertEquals(pais.getArea(),copia.getArea(),"Teste Menor Area");
	}
    
	@Test
	public void test06VetorTresPaises() {
		System.out.println("vetor de 3 paises");
		String[] vetorTresPais = paisService.vetorTresPaises(pais);
		assertEquals("Testando Vetor de paises",3,(paisService.vetorTresPaises(pais).length));
	}

}
