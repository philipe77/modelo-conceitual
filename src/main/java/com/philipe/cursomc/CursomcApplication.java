package com.philipe.cursomc;


import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.philipe.cursomc.domain.Categoria;
import com.philipe.cursomc.domain.Cidade;
import com.philipe.cursomc.domain.Cliente;
import com.philipe.cursomc.domain.Endereco;
import com.philipe.cursomc.domain.Estado;
import com.philipe.cursomc.domain.Pagamento;
import com.philipe.cursomc.domain.PagamentoComBoleto;
import com.philipe.cursomc.domain.PagamentoComCartao;
import com.philipe.cursomc.domain.Pedido;
import com.philipe.cursomc.domain.Produto;
import com.philipe.cursomc.domain.enums.EstadoPagamento;
import com.philipe.cursomc.domain.enums.TipoCliente;
import com.philipe.cursomc.repository.CategoriaRepository;
import com.philipe.cursomc.repository.CidadeRepository;
import com.philipe.cursomc.repository.ClienteRepository;
import com.philipe.cursomc.repository.EnderecoRepository;
import com.philipe.cursomc.repository.EstadoRepository;
import com.philipe.cursomc.repository.PagamentoRepository;
import com.philipe.cursomc.repository.PedidoRepository;
import com.philipe.cursomc.repository.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepo;
	
	@Autowired
	private ProdutoRepository produtoRepo;
	
	@Autowired
	private EstadoRepository estadoRepo;
	
	@Autowired
	private CidadeRepository cidadeRepo;
	
	@Autowired
	private ClienteRepository clienteRepo;
	
	@Autowired
	private EnderecoRepository enderecoRepo;
	
	@Autowired
	private PedidoRepository pedidoRepo;
	
	@Autowired
	private PagamentoRepository pagamentoRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		//Listando as categorias manualmente
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		//Listando os produtos manualmente
		Produto p1 = new Produto(null,"Computador",2000.00);
		Produto p2 = new Produto(null,"Impressora",800.00);
		Produto p3 = new Produto(null,"Mouse",80.00);
		
		//Associando as categorias com os produtos 
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		//Associando os produtos com as categorias
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		
		categoriaRepo.saveAll(Arrays.asList(cat1,cat2));
		produtoRepo.saveAll(Arrays.asList(p1,p2,p3));
		
		//Listando Estados
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null,"São Paulo");
				
		//Listando as Cidades juntando os estados
		Cidade c1 = new Cidade(null,"Uberlandia",est1);
		Cidade c2 = new Cidade(null,"São Paulo",est2);
		Cidade c3 = new Cidade(null,"Campinas",est2);
				
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
				
		estadoRepo.saveAll(Arrays.asList(est1,est2));
		cidadeRepo.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente(null,"Maria Silva","maria@gmail.com","237432742394", TipoCliente.PESSOA_FISICA);
		cli1.getTelefones().addAll(Arrays.asList("323232344","2435345334"));
		
		Endereco e1 = new Endereco(null,"Rua Flores","200A","apt 803","Tijuca","205204083",cli1,c1);
		Endereco e2 = new Endereco(null,"Av Mattos" ,"105B","101","Vila Isabel","28474923",cli1,c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepo.saveAll(Arrays.asList(cli1));
		enderecoRepo.saveAll(Arrays.asList(e1,e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1,e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 10:32"), cli1,e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null,EstadoPagamento.QUITADO,ped1,6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null,EstadoPagamento.PENDENTE,ped2,sdf.parse("20/10/2017 00:30"),null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepo.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepo.saveAll(Arrays.asList(pagto1,pagto2));
		
		
	}
}
