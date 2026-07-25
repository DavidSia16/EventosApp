package daviddev.eventos.controller;

import daviddev.eventos.model.Convidado;
import daviddev.eventos.model.Evento;
import daviddev.eventos.repository.ConvidadoRepository;
import daviddev.eventos.repository.EventoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.validation.BindingResult;

@Controller
public class EventoController {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private ConvidadoRepository convidadoRepository;

    @RequestMapping(value = "/cadastrarEvento", method = RequestMethod.GET)
    public String form() {
        return "evento/formEvento";
    }

    @RequestMapping(value = "/cadastrarEvento", method = RequestMethod.POST)
    public String form( @Valid Evento evento, BindingResult result, RedirectAttributes attributes ) {
        if(result.hasErrors()) {
            attributes.addFlashAttribute("mensagem", "Verifique os campos!");
            return "redirect:/cadastrarEvento";
        }

        eventoRepository.save(evento);
        attributes.addFlashAttribute("mensagem", "Evento cadastrado com sucesso!");
        return "redirect:/cadastrarEvento";
    }
    @RequestMapping("/eventos")
    public ModelAndView listaEventos() {
        ModelAndView mv = new ModelAndView("index");
        Iterable<Evento> eventos = eventoRepository.findAll();
        mv.addObject("eventos", eventos);
        return mv;
    }

    @RequestMapping(value="/{codigo}" , method = RequestMethod.GET )
    public ModelAndView detalhesEvento(@PathVariable("codigo") long codigo) {

        Evento evento = eventoRepository.findById(codigo);

        if (evento == null) {
            // Redireciona ou trata caso o evento não exista no banco
            return new ModelAndView("redirect:/eventos");
        }

        ModelAndView mv = new ModelAndView("evento/detalhesEvento");
        mv.addObject("evento", evento);
        System.out.println("evento: " + evento);

        Iterable<Convidado> convidados = convidadoRepository.findByEvento(evento);
        mv.addObject("convidados", convidados);
        mv.addObject("convidado", new Convidado());
        return mv;
    }

    @RequestMapping("/deletarEvento")
    public String deletarEvento(long codigo) {
        Evento evento = eventoRepository.findById(codigo);
        eventoRepository.delete(evento);
        return "redirect:/eventos";
    }
    @RequestMapping("/deletarConvidado")
    public String deletarConvidado(String rg) {
        Convidado convidado = convidadoRepository.findByRg(rg);
        convidadoRepository.delete(convidado);
        Evento evento = convidado.getEvento();
        long codigoLong = evento.getId();
        String codigo =""+codigoLong;
        return "redirect:/" + codigo;
    }

    @RequestMapping(value="/{codigo}" , method = RequestMethod.POST)
    public String detalhesEventoPost(@PathVariable("codigo") long codigo,@Valid Convidado convidado, BindingResult result,RedirectAttributes attributes){
        if(result.hasErrors()) {
            attributes.addFlashAttribute("mensagem", "Verifique os campos!");
            return "redirect:/{codigo}";
        }
        Evento evento = eventoRepository.findById(codigo);

        if (evento == null) {
            attributes.addFlashAttribute("mensagem", "Evento não encontrado!");
            return "redirect:/eventos";
        }

        convidado.setEvento(evento);
        convidadoRepository.save(convidado);
        attributes.addFlashAttribute("mensagem", "Convidado adicionado com sucesso!");

        // Adiciona o código de forma segura para o redirecionamento
        attributes.addAttribute("codigo", codigo);

        return "redirect:/{codigo}";
    }

}
