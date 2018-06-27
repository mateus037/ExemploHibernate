package br.com.javaparaweb.captulo3.conexao;

import org.hibernate.Session;
public class ConectarHibernateMySQL {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
   Session sessao= null;
   sessao = HibernateUtil.getSessionFactory().openSession();
   System.out.println("Conectou!");
   sessao.close();
	}

}
