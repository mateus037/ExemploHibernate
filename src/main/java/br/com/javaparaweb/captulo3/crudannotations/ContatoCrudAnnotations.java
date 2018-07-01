package br.com.javaparaweb.captulo3.crudannotations;

import java.sql.Date;
import java.util.List;
import org.hibernate.*;
import br.com.javaparaweb.captulo3.conexao.HibernateUtil;

public class ContatoCrudAnnotations {

	private Session sessao;

	public ContatoCrudAnnotations(Session sessao) {

		this.sessao = sessao;

	}

	public void salvar(Contato contato) {
		sessao.save(contato);
	}
	public void atualizar(Contato contato) {
		sessao.update(contato);
	}
	public void excluir(Contato contato) {
		sessao.delete(contato);
	}
	public List<Contato> lista(){
		Query consulta = sessao.createQuery("from Contato");
		return consulta.list();
	}
	public Contato buscaContato(int valor) {
		Query consulta = sessao.createQuery("from Contato where codigo = :parametro");
		consulta.setInteger("parametro", valor);
		return (Contato) consulta.uniqueResult();
	}
	
	public static void main(String[] args) {
		
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = sessao.beginTransaction();
		ContatoCrudAnnotations contatoCrud = new ContatoCrudAnnotations(sessao);
		Contato contato1 = new Contato();
		contato1.setNome("Mateus");
		contato1.setTelefone("(99) 3333-4444");
		contato1.setEmail("Mateus@java.com.br");
		contato1.setDataCadastro(new Date(System.currentTimeMillis()));
		contato1.setObservacao("Novo cliente");
		contatoCrud.salvar(contato1);
		//contato1.setObservacao("Retomar Contato");
		contatoCrud.atualizar(contato1);
		
		
		transacao.commit();
		System.out.println("Total de registros cadastrados: "+ contatoCrud.lista().size());
				
	}
}
