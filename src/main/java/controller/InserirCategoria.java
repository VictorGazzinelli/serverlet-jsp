package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CategoriaDAO;
import model.Categoria;

import java.util.*;


@WebServlet("/InserirCategoria")
public class InserirCategoria extends HttpServlet{
   
    private static final long serialVersionUID = 1L;

    CategoriaDAO dao = new CategoriaDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        String nome = request.getParameter("nome");     
        Categoria categoria = new Categoria();
        categoria.setNome(nome);
        
        String retorno = dao.inserir(categoria);

        if (retorno.equals("sucesso")) {
			listaCategoria(request, response);
		} else {
			PrintWriter out = response.getWriter();
			out.print("<html>");
			out.print("<h2> Nao foi possivel inserir a categoria!</h2>");
			out.print("<br");
			out.print("<a href = 'index.jsp'> Voltar </a>");
			out.print("</html>");
		}

    }

    private void listaCategoria(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException{
        List<Categoria> listaDeCategorias = dao.listar();
        request.setAttribute("listaCategoria", listaDeCategorias);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(request, response);
    }
    

}
