/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import dao.PessoaDAO;
import dao.UsuarioDAO;
import entidade.Pessoa;
import entidade.Usuario;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author gabri
 */
public class acao extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet acao</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet acao at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);

        String a = request.getParameter("a");

        if (a.equals("editarPessoa")) {
            String id = request.getParameter("id");
            int codigo = Integer.parseInt(id);

            Pessoa pessoa = new PessoaDAO().consultar(codigo);

            request.setAttribute("pessoa", pessoa);

            encaminharPagina("pessoa.jsp", request, response);
        }

        if (a.equals("excluirPessoa")) {
            String id = request.getParameter("id");
            int codigo = Integer.parseInt(id);

            if (new PessoaDAO().excluir(codigo)) {
                encaminharPagina("pessoa.jsp", request, response);
            } else {
                encaminharPagina("erro.jsp", request, response);
            }
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);

        String a = request.getParameter("a");

        if (a.equals("entrar")) {
            String nome = request.getParameter("nome");
            String senha = request.getParameter("senha");

            Usuario us = new Usuario();
            us.setNome(nome);
            us.setSenha(senha);

            if (new UsuarioDAO().autenticar(us)) {
                HttpSession sessao = request.getSession();
                sessao.setAttribute("user", us);

                encaminharPagina("menu.jsp", request, response);
            } else {
                encaminharPagina("erro.jsp", request, response);
            }
        }

        if (a.equals("logout")) {
            HttpSession sessao = request.getSession();
            sessao.invalidate();

            response.sendRedirect("login.jsp");
        }

        if (a.equals("salvarPessoa")) {
            String id = request.getParameter("id");
            String nome = request.getParameter("nome");
            String email = request.getParameter("email");
            String telefone = request.getParameter("telefone");
            String data_nascimento = request.getParameter("data_nascimento");

            Pessoa pessoa = new Pessoa();
            int codigo = Integer.parseInt(id);

            pessoa.setId(codigo);
            pessoa.setNome(nome);
            pessoa.setEmail(email);
            pessoa.setTelefone(telefone);
            pessoa.setData_nascimento(data_nascimento);

            if (codigo == 0) {
                if (new PessoaDAO().salvar(pessoa)) {
                    encaminharPagina("sucesso.jsp", request, response);
                } else {
                    encaminharPagina("erro.jsp", request, response);
                }
            } else {
                if (new PessoaDAO().atualizar(pessoa)) {
                    encaminharPagina("sucesso.jsp", request, response);
                } else {
                    encaminharPagina("erro.jsp", request, response);
                }
            }

        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void encaminharPagina(String pagina, HttpServletRequest request, HttpServletResponse response) {
        try {
            RequestDispatcher rd = request.getRequestDispatcher(pagina);
            rd.forward(request, response);
        } catch (Exception e) {
            System.out.println("Erro ao encaminhar: " + e);
        }
    }
}
