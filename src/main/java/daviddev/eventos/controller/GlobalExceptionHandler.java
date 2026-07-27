package daviddev.eventos.controller;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Trata erro 400 específico para parâmetros inválidos na URL
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleTypeMismatch(MethodArgumentTypeMismatchException ex, Model model) {
        model.addAttribute("mensagem", "O parâmetro informado na URL é inválido.");
        return "error/400"; // Retorna o template em src/main/resources/templates/error/400.html
    }

    // 2. Trata erro 403 (Forbidden - Quando o usuário tenta acessar algo sem ter a ROLE necessária)
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String handleAccessDenied(AccessDeniedException ex, Model model) {
        model.addAttribute("mensagem", "Você não tem permissão para acessar esta página.");
        return "error/403"; // templates/error/403.html
    }

    // Trata qualquer outro erro 500 (Exceção não capturada)
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleGeneralException(Exception ex, Model model) {
        model.addAttribute("mensagem", "Ocorreu um erro interno no servidor.");
        return "error/500"; // Retorna o template em src/main/resources/templates/error/500.html
    }
}
